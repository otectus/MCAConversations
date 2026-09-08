package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.FeatureId;
import dev.otectus.mcaconversations.chat.ChatDelivery;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.CapitalsCompat;
import dev.otectus.mcaconversations.compat.ReputationBridge;
import dev.otectus.mcaconversations.court.CourtMemorySavedData;
import dev.otectus.mcaconversations.history.CommitmentRecord;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.NarrativeValue;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.template.SlotRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Villagers who bring something up.
 *
 * <p>Everything needed for this existed and none of it was connected. {@link InitiativeGate} could
 * decide whether a villager may speak first, {@link BusyState} could say whether now is a bad moment,
 * {@link ScenePurpose} ranked ten reasons by how much of the player's attention each is worth
 * interrupting for, and a daily budget bounded the lot. The only caller was the proximity greeting,
 * which passes {@code Weight.BARK} and says hello. Nine of the ten purposes had no runtime path at
 * all, so a promise could come due, a rupture could sit unacknowledged, and a villager's own
 * situation could change, and none of it would ever be mentioned unless the player happened to ask.
 *
 * <h2>What it will raise</h2>
 *
 * <p>Four things, each read from a record that already exists — never from a guess, and never from a
 * generic sense that it has been a while:
 *
 * <ol>
 *   <li>a promise that has come due;</li>
 *   <li>a rupture between them that has not been acknowledged;</li>
 *   <li>a thread they left open and that is ready to pick up;</li>
 *   <li>their own situation having changed since the two of them last spoke.</li>
 * </ol>
 *
 * <p>A fifth is live only with MCA: Reputation installed: the village has just changed its mind about
 * this player, and this resident knows a deed behind it. It is held to the same standard as the four
 * above — a recorded crossing and a deed this villager actually knows, never a general sense that the
 * player has been doing well.
 *
 * <p>{@code LOW_STAKES} is deliberately absent. With nothing else outstanding it would fire every
 * day, and unlike the four above there is no record behind it to make it true.
 *
 * <h2>One line, and then it is the player's move</h2>
 *
 * <p>An initiative is a bark: the villager says the one sentence and stops. No screen opens, no input
 * is taken, and nothing is waiting for an answer. The follow-through is already built — {@code
 * DynamicHub} draws its contextual entries from the same threads and episodes this planner reads, so
 * a villager who has just mentioned a thread is already offering to continue it when the player walks
 * over. That is why nothing is frozen across the gap and there is nothing to invalidate.
 *
 * <p>The line itself is authored per purpose rather than taken from a scene. Every one of the shipped
 * scenes is a {@code topic:*} scene the player chooses, so a director call for {@code REPAIR} would
 * find nothing to say; and a scene opener is written to be followed by choices, which a bark is not.
 */
public final class InitiativePlanner {

    /** Lang prefix for the four bark pools. Spoken from here, never named by a dialogue file. */
    public static final String PHRASE_PREFIX = "conversations.initiative.";

    private InitiativePlanner() {
    }

    /**
     * Something a villager has to say first, and the words for saying it.
     *
     * @param purpose why they are speaking, which is what the gate and the budget are told
     * @param slot    the one concrete thing the line names — the promised item — or empty when the
     *                line stands on its own
     */
    public record Opening(ScenePurpose purpose, Optional<NarrativeValue> slot) {

        /** The lang key for this opening's pool. */
        public String phrase() {
            return PHRASE_PREFIX + purpose.key();
        }
    }

    /**
     * Which purposes are live for this pair, from records alone.
     *
     * <p>Pure, and the whole of the detection rule. Order of insertion does not matter — the caller
     * ranks with {@link InitiativeGate#mostImportant}, which is the same ordering the plan uses for
     * how much interrupting somebody each class is worth.
     */
    static Set<ScenePurpose> candidates(PairHistory pair, List<EpisodeRecord> liveEpisodes, long today) {
        return candidates(pair, liveEpisodes, today, false, false);
    }

    /**
     * The same rule with the one thing that is not read from this pair's own records: whether the
     * village has just changed its mind about this player and this villager is one to say so.
     *
     * <p>Passed in rather than looked up, so the detection rule stays pure and testable while the
     * three conditions behind the flag ({@link dev.otectus.mcaconversations.compat.ReputationBridge
     * #hasStandingRemark}) stay in the bridge that knows about the optional mod.
     */
    static Set<ScenePurpose> candidates(PairHistory pair, List<EpisodeRecord> liveEpisodes, long today,
                                        boolean standingRemark) {
        return candidates(pair, liveEpisodes, today, standingRemark, false);
    }

    /**
     * The same rule again with the second flag no pair record can supply: this villager's own place at
     * court has just changed, and they have not mentioned it yet.
     *
     * <p>Passed in for the same reason {@code standingRemark} is — the detection rule stays pure, and
     * the court memory and the optional mod behind it stay in {@link #hasCourtRemark}.
     */
    static Set<ScenePurpose> candidates(PairHistory pair, List<EpisodeRecord> liveEpisodes, long today,
                                        boolean standingRemark, boolean courtRemark) {
        Set<ScenePurpose> out = new LinkedHashSet<>();
        if (pair != null) {
            if (!pair.due(today).isEmpty()) {
                out.add(ScenePurpose.DUE_COMMITMENT);
            }
            if (pair.rupture().isPresent()) {
                out.add(ScenePurpose.REPAIR);
            }
            if (!pair.resumable(today).isEmpty()) {
                out.add(ScenePurpose.RESUME);
            }
        }
        if (changedSinceTheyLastSpoke(pair, liveEpisodes)) {
            out.add(ScenePurpose.STATE_CHANGE);
        }
        if (standingRemark) {
            out.add(ScenePurpose.STANDING_REMARK);
        }
        if (courtRemark) {
            out.add(ScenePurpose.COURT_REMARK);
        }
        return out;
    }

    /**
     * Whether the villager's own situation has moved on since this player last heard about it.
     *
     * <p>Anchored on when the two of them last spoke rather than on a fixed number of days, so the
     * player who was here yesterday and the player who has been away a season are each told the thing
     * that is news <em>to them</em>. A pair that has never spoken is not owed an update on anything.
     */
    private static boolean changedSinceTheyLastSpoke(PairHistory pair, List<EpisodeRecord> liveEpisodes) {
        if (pair == null || liveEpisodes == null || liveEpisodes.isEmpty()) {
            return false;
        }
        java.util.OptionalLong lastTalked = pair.lastTalkedDay();
        if (lastTalked.isEmpty()) {
            return false;
        }
        return liveEpisodes.stream().anyMatch(episode -> episode.updatedDay() > lastTalked.getAsLong());
    }

    /** The concrete thing the line names, for the one purpose that names one. */
    static Optional<NarrativeValue> slotFor(ScenePurpose purpose, PairHistory pair, long today) {
        if (purpose != ScenePurpose.DUE_COMMITMENT || pair == null) {
            return Optional.empty();
        }
        return pair.due(today).stream().findFirst().map(CommitmentRecord::target);
    }

    /** What this villager would raise with this player right now, if anything. */
    public static Optional<Opening> find(Entity villager, ServerPlayer player, long today) {
        if (villager == null || player == null
                || !McaConversationsConfig.dynamicFeature(FeatureId.HISTORY, false)) {
            return Optional.empty();
        }
        try {
            PairHistory pair = History.pair(villager, player).orElse(null);
            List<EpisodeRecord> live = History.of(villager)
                    .map(history -> history.liveEpisodes(today))
                    .orElse(List.of());
            Set<ScenePurpose> candidates = candidates(pair, live, today,
                    ReputationBridge.hasStandingRemark(player, villager, gameTime(villager)),
                    hasCourtRemark(villager, today));
            return InitiativeGate.mostImportant(candidates)
                    .map(purpose -> new Opening(purpose, slotFor(purpose, pair, today)));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("initiative planning failed; villager stays quiet", t);
            return Optional.empty();
        }
    }

    /**
     * Finds, gates and speaks — the whole attempt.
     *
     * <p>The order is load-bearing: the gate is asked before anything is said, and the budget is spent
     * only after something has been. A villager that has something to raise but is asleep, muted or
     * inside its cooldown says nothing and keeps its budget for when it can.
     *
     * @return true when a line was actually spoken, so the caller knows not to also greet
     */
    public static boolean tryRaise(Entity villager, ServerPlayer player, long today, long now) {
        try {
            Optional<Opening> opening = find(villager, player, today);
            if (opening.isEmpty()) {
                return false;
            }
            ScenePurpose purpose = opening.get().purpose();
            if (InitiativeGate.decide(villager, player, purpose, false,
                    InitiativeGate.Weight.FULL, today, now).refused()) {
                return false;
            }
            if (!speak(villager, player, opening.get())) {
                return false;
            }
            if (purpose == ScenePurpose.COURT_REMARK) {
                // Spent the moment it is said: one promotion is remarked on once, to whoever was
                // there to hear it, and never again by the same villager.
                markCourtRemarked(villager);
            }
            if (purpose == ScenePurpose.STANDING_REMARK) {
                // Spent the moment it is said, so the next resident the player walks past does not
                // congratulate them on the same thing. Untaken notes time out on their own.
                ReputationBridge.consumeStandingRemark(player.getUUID(), now);
            }
            InitiativeGate.record(villager, player, purpose, InitiativeGate.Weight.FULL, today, now);
            attend(villager, player, now);
            return true;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("initiative delivery failed; villager stays quiet", t);
            return false;
        }
    }

    /**
     * Says the line.
     *
     * <p>Built through MCA's own translatable path so the villager's personality and age voice apply,
     * and delivered through {@link ChatDelivery}, which pins the pooled variant on the server — so two
     * villagers raising the same kind of thing do not read as one narrator, and the same villager does
     * not use the same wording twice running.
     */
    private static boolean speak(Entity villager, ServerPlayer player, Opening opening) {
        List<Object> args = new ArrayList<>(1);
        opening.slot().ifPresent(value -> args.add(render(value, villager)));
        Optional<Component> line = McaCompat
                .getDialogueLine(villager, player, opening.phrase(), args.toArray())
                .map(built -> (Component) built);
        if (line.isEmpty()) {
            return false;
        }
        ChatDelivery.villagerSays(villager, player, line.get());
        return true;
    }

    /**
     * Whether this villager was given a court office recently enough to still be telling people.
     *
     * <p>Three gates, in the order that costs least: the switch, then the mod, then the memory. With
     * MCA Capitals absent the memory is empty anyway, but a check that reaches storage on every
     * approach scan of every villager is not one to leave to that.
     */
    private static boolean hasCourtRemark(Entity villager, long today) {
        try {
            if (!McaConversationsConfig.COMMON.capitalRoleRemarkEnabled.get()
                    || !CapitalsCompat.isActive()
                    || !(villager.level() instanceof ServerLevel level)
                    || level.getServer() == null) {
                return false;
            }
            return CourtMemorySavedData.get(level.getServer())
                    .freshChange(villager.getUUID(), today,
                            McaConversationsConfig.COMMON.capitalRoleRemarkDays.get())
                    .isPresent();
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("court remark check failed; villager stays quiet", t);
            return false;
        }
    }

    private static void markCourtRemarked(Entity villager) {
        try {
            if (villager.level() instanceof ServerLevel level && level.getServer() != null) {
                CourtMemorySavedData.get(level.getServer()).markRemarked(villager.getUUID());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("court remark could not be marked spoken", t);
        }
    }

    /** The villager's own clock, which is the one the remark window is measured against. */
    private static long gameTime(Entity villager) {
        return villager.level() instanceof ServerLevel level ? level.getGameTime() : 0L;
    }

    /** Renders the one slot a bark can carry, falling back the way every other slot does. */
    private static Component render(NarrativeValue value, Entity villager) {
        ServerLevel level = villager.level() instanceof ServerLevel serverLevel ? serverLevel : null;
        return SlotRenderer.render(value, level);
    }

    /** Stop and face them, exactly as a greeting does — a line said over a shoulder is not raised. */
    private static void attend(Entity villager, ServerPlayer player, long now) {
        int ticks = McaConversationsConfig.chatModeAttentionTicks();
        if (ticks > 0) {
            VillagerAttention.hold(villager, player, now + ticks,
                    dev.otectus.mcaconversations.chat.AttentionLedger.Source.CONVERSATION);
        }
    }
}
