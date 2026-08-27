package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.ChatModeSession;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.PairHistory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The single place that decides whether a villager may open a conversation (spec §11.2).
 *
 * <p>Before this, the anti-spam policy was spread across three places that each knew part of it: the
 * director penalised interrupting a working villager, the greeter checked mutes, and the daily cap
 * existed in the config and was read by nothing. A policy in three places is a policy with holes in
 * it, so every rule §11.2 states now lives here and every initiative path asks the same question.
 *
 * <p>The order of the checks is deliberate and is itself part of the policy. Being told to stop
 * talking outranks everything: a player who has said that is not owed an exception for a villager who
 * happens to have news. What a villager is in the middle of comes next, because an interruption that
 * should not happen at all should not consume a budget. Only then are the caps spent.
 *
 * <h2>What is not decided here</h2>
 *
 * <p>Whether a scene is <em>worth</em> opening. This gate answers "may they?", never "should they?" —
 * the director scores that, and a gate that also ranked would be two policies wearing one name.
 */
public final class InitiativeGate {

    /**
     * Real-time cooldown, in ticks, between one villager's initiatives towards one player.
     *
     * <p>Fifteen seconds. It exists for the two purposes that may bypass the daily cap — an acute
     * state and a genuine episode change — because "may bypass the daily cap" must not mean "may
     * happen twice in the same breath". Held in memory rather than in the save: it is measured in
     * seconds, and a cooldown that survived a restart would be a schema field earning nothing.
     */
    public static final long COOLDOWN_TICKS = 300L;

    /** (villager, player) -> the game tick an initiative last landed. */
    private static final Map<String, Long> LAST_INITIATIVE = new ConcurrentHashMap<>();

    /** Bound on the cooldown map, so a long-running server cannot accumulate pairs forever. */
    private static final int MAX_TRACKED_PAIRS = 8192;

    private InitiativeGate() {
    }

    /**
     * Whether {@code villager} may open {@code purpose} with {@code player} right now.
     *
     * @param concernsBusyState true when the scene is explicitly about what the villager is in the
     *                          middle of — the exception §11.2 grants, so "you look rushed" may be
     *                          said to somebody who is
     * @param weight            whether this opens a decision page or is a single passing line
     */
    public static Decision decide(Entity villager, ServerPlayer player, ScenePurpose purpose,
                                  boolean concernsBusyState, Weight weight, long today, long now) {
        if (villager == null || player == null || purpose == null) {
            return Decision.refuse("no villager, player or purpose");
        }
        if (!purpose.isInitiative()) {
            // The player opened this. Nothing here applies.
            return Decision.permit();
        }
        int dailyCap = dailyCap();
        if (dailyCap <= 0) {
            return Decision.refuse("villager initiative is switched off");
        }
        if (isMuted(villager, player, now)) {
            return Decision.refuse("this pairing was asked to stop talking");
        }
        BusyState busy = BusyState.of(villager, player.getUUID());
        if (busy.isBusy() && busy.suppresses(purpose) && !concernsBusyState) {
            return Decision.refuse("villager is " + busy.key());
        }
        if (onCooldown(villager, player, now)) {
            return Decision.refuse("spoke to this player moments ago");
        }
        if (weight == Weight.FULL && purpose.countsAgainstDailyCap()
                && initiativesToday(villager, player, today) >= dailyCap) {
            return Decision.refuse("daily initiative budget already spent");
        }
        return Decision.permit();
    }

    /**
     * Records that an initiative actually landed.
     *
     * <p>Separate from {@link #decide} on purpose. A caller that asks and then finds it has nothing
     * to say must not have spent the villager's budget for the day, so the budget is spent by the
     * code that speaks rather than by the code that checks.
     */
    public static void record(Entity villager, ServerPlayer player, ScenePurpose purpose,
                              Weight weight, long today, long now) {
        if (villager == null || player == null || purpose == null || !purpose.isInitiative()) {
            return;
        }
        touch(villager, player, now);
        if (weight == Weight.FULL && purpose.countsAgainstDailyCap()) {
            try {
                History.recordInitiative(villager, player, today);
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("initiative record failed; ignoring", t);
            }
        }
    }

    /**
     * The one initiative worth surfacing out of several (spec §11.2: never more than one).
     *
     * <p>Ranked by interruption cost ascending, which is the same ordering §11.1 lists the classes
     * in and is not a coincidence: the things worth interrupting somebody for are exactly the things
     * that cost least to raise. Ties fall to declaration order, so the choice is stable rather than
     * dependent on how the candidates happened to be collected.
     */
    public static Optional<ScenePurpose> mostImportant(Iterable<ScenePurpose> candidates) {
        if (candidates == null) {
            return Optional.empty();
        }
        ScenePurpose best = null;
        for (ScenePurpose purpose : candidates) {
            if (purpose == null || !purpose.isInitiative()) {
                continue;
            }
            if (best == null || COMPARATOR.compare(purpose, best) < 0) {
                best = purpose;
            }
        }
        return Optional.ofNullable(best);
    }

    private static final Comparator<ScenePurpose> COMPARATOR =
            Comparator.comparingInt(ScenePurpose::interruptionCost)
                    .thenComparingInt(Enum::ordinal);

    /**
     * How much of the player's attention an initiative is asking for (spec §11.2).
     *
     * <p>The plan draws this line itself: "ambient one-line barks do not open a decision page unless
     * the player responds", and the daily budget is spent on "one unsolicited <b>full</b> initiative".
     * A villager calling hello across the square has not used up the day's one chance to raise the
     * promise they made — but it still respects a mute, still waits out the short cooldown, and still
     * does not happen while they are asleep.
     */
    public enum Weight {

        /** One line, no page, no budget. */
        BARK,

        /** A decision page. Spends the day's allowance. */
        FULL
    }

    /** Test seam: forgets every real-time cooldown. */
    public static void clearCooldownsForTesting() {
        LAST_INITIATIVE.clear();
    }

    /** Test seam: the cooldown state for one pair, in ticks, when one is held. */
    public static Optional<Long> lastInitiativeTick(Entity villager, ServerPlayer player) {
        return Optional.ofNullable(LAST_INITIATIVE.get(key(villager, player)));
    }

    // --- The individual rules ---------------------------------------------------------------------

    private static int dailyCap() {
        return McaConversationsConfig.dynamicInt(
                McaConversationsConfig.COMMON.maxInitiativesPerVillagerPlayerDay, 1);
    }

    private static boolean isMuted(Entity villager, ServerPlayer player, long now) {
        try {
            ChatModeSession.Session session = ChatModeSession.peek(player.getUUID());
            return session != null && session.isMuted(villager.getUUID(), now);
        } catch (Throwable t) {
            // A mute we cannot read is treated as present. Erring towards silence is the only safe
            // direction for a rule whose whole purpose is to honour "stop talking".
            return true;
        }
    }

    private static boolean onCooldown(Entity villager, ServerPlayer player, long now) {
        Long last = LAST_INITIATIVE.get(key(villager, player));
        return last != null && now - last < COOLDOWN_TICKS && now >= last;
    }

    private static int initiativesToday(Entity villager, ServerPlayer player, long today) {
        try {
            return History.of(villager)
                    .flatMap(history -> history.peekPair(player.getUUID()))
                    .map(PairHistory::recency)
                    .map(recency -> recency.initiativesOn(today))
                    .orElse(0);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("initiative budget read failed; assuming spent", t);
            // Unreadable history means an unenforceable cap, and an unenforceable cap should fail
            // closed: a quiet villager is a far smaller bug than one that talks without limit.
            return Integer.MAX_VALUE;
        }
    }

    private static void touch(Entity villager, ServerPlayer player, long now) {
        if (LAST_INITIATIVE.size() >= MAX_TRACKED_PAIRS) {
            LAST_INITIATIVE.clear();
        }
        LAST_INITIATIVE.put(key(villager, player), now);
    }

    private static String key(Entity villager, ServerPlayer player) {
        return (villager == null ? "?" : villager.getUUID().toString()) + "/"
                + (player == null ? "?" : player.getUUID().toString());
    }

    /**
     * The answer, with the reason it was given.
     *
     * <p>The reason is not decoration: it is what {@code /mcaconversations explain} prints, and the
     * plan requires the director to be able to say why it stayed quiet as readily as why it spoke.
     */
    public record Decision(boolean allowed, String reason) {

        private static final Decision PERMITTED = new Decision(true, "");

        public static Decision permit() {
            return PERMITTED;
        }

        public static Decision refuse(String reason) {
            return new Decision(false, reason == null ? "" : reason);
        }

        public boolean refused() {
            return !allowed;
        }
    }
}
