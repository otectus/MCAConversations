package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.gossip.GossipConditionLogic;
import dev.otectus.mcaconversations.history.CommitmentRecord;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.TopicRecencyRecord;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * What these two have between them, as context a scene can be chosen on.
 *
 * <h2>Why this exists</h2>
 *
 * <p>{@link ContextKeys} declared nine fields about shared history and one about village news, and
 * until 1.5.0 <b>nothing wrote any of them</b>. A key with no source reads {@code UNAVAILABLE} on
 * every install, forever, and a scene condition on it under the default {@code unknown: "fail"}
 * policy is therefore a scene that can never be selected. Seventeen shipped scenes were gated that
 * way — fourteen on {@code time.days_since_first_met} alone — which is to say a seventh of the
 * dynamic corpus had been dark since it was written, with nothing in the build to notice.
 *
 * <p>None of the data is new. {@code PairHistory} has kept {@code firstMetDay} and
 * {@code lastTalkedDay} all along, {@code History} already answers every narrative question through
 * a facade, and the gossip log already knows what this villager has not told this player. This
 * source is the wire between them and the snapshot, and nothing more: it computes no state, stores
 * nothing, and asks no question the dialogue conditions could not already ask.
 *
 * <h2>Unknown is not zero</h2>
 *
 * <p>Two people who have never spoken have no days-since. Reporting {@code 0} there would read as
 * "we spoke today", which is the opposite of true and exactly the kind of confident wrong answer a
 * villager should never give. Every field here reports {@code UNKNOWN} rather than a stand-in, and
 * the condition's own {@code unknown} policy decides what that means for the scene.
 */
public final class HistoryContextSource implements ConversationContextSource {

    public static final String ID = "history";

    /**
     * Day thresholds for {@code time.absence_band}, the coarse form of "how long has it been".
     *
     * <p>Bands rather than a number because that is what somebody would actually say: nobody greets
     * you with "it has been nine days", they say it has been a while. {@code none} is same-day or
     * yesterday, {@code brief} is within the week, {@code long} is within the month, and beyond that
     * is {@code very_long} — long enough that the village has changed around you.
     */
    static final long BRIEF_AFTER_DAYS = 2L;
    static final long LONG_AFTER_DAYS = 7L;
    static final long VERY_LONG_AFTER_DAYS = 30L;

    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.TIME_DAYS_SINCE_LAST_TALK, ContextKeys.TIME_DAYS_SINCE_FIRST_MET,
            ContextKeys.TIME_ABSENCE_BAND,
            ContextKeys.NARRATIVE_ACTIVE_EPISODES, ContextKeys.NARRATIVE_READY_THREADS,
            ContextKeys.NARRATIVE_DUE_COMMITMENTS, ContextKeys.NARRATIVE_RUPTURE,
            ContextKeys.NARRATIVE_RECENT_SUBJECTS,
            ContextKeys.VILLAGE_RECENT_EVENT);

    @Override
    public String id() {
        return ID;
    }

    @Override
    public List<ContextKey<?>> declares() {
        return DECLARES;
    }

    /**
     * Available whenever the history layer is on.
     *
     * <p>With it off the fields go {@code ABSENT} together rather than {@code UNKNOWN} one by one,
     * which is the difference the capability report exists to show: "this install does not keep
     * history" reads differently from "these two have no history".
     */
    @Override
    public boolean isAvailable(ContextRequest request) {
        return History.enabled() && request.villager() != null && request.player() != null;
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        Entity villager = request.villager();
        ServerPlayer player = request.player();
        if (villager == null || player == null || villager.level() == null) {
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.ABSENT, "no villager or player");
            return;
        }
        if (request.volatileOnly()) {
            // Every field here is pinned: a promise does not come due, and a rupture does not heal,
            // between two turns of one conversation. Rewriting them mid-scene is how a bound
            // referent drifts, so a refresh contributes nothing.
            builder.reportCapability(ContextCapabilities.Status.READY, "");
            return;
        }
        try {
            long today = villager.level().getDayTime() / 24000L;
            Optional<PairHistory> pair = History.pair(villager, player);

            putDays(builder, ContextKeys.TIME_DAYS_SINCE_LAST_TALK,
                    pair.map(PairHistory::lastTalkedDay).orElse(OptionalLong.empty()), today);
            putDays(builder, ContextKeys.TIME_DAYS_SINCE_FIRST_MET,
                    pair.map(PairHistory::firstMetDay).orElse(OptionalLong.empty()), today);
            builder.put(ContextKeys.TIME_ABSENCE_BAND, absenceBand(
                    pair.map(PairHistory::lastTalkedDay).orElse(OptionalLong.empty()), today));

            builder.put(ContextKeys.NARRATIVE_ACTIVE_EPISODES, History.of(villager)
                    .map(history -> history.liveEpisodes(today).stream()
                            .map(EpisodeRecord::kind)
                            .distinct()
                            .toList()));
            builder.put(ContextKeys.NARRATIVE_READY_THREADS, pair
                    .map(history -> history.resumable(today).stream()
                            .map(SharedThreadRecord::templateId)
                            .toList()));
            builder.put(ContextKeys.NARRATIVE_DUE_COMMITMENTS, pair
                    .map(history -> history.due(today).stream()
                            .map(CommitmentRecord::id)
                            .toList()));
            builder.put(ContextKeys.NARRATIVE_RUPTURE,
                    pair.map(history -> history.rupture().isPresent()));
            builder.put(ContextKeys.NARRATIVE_RECENT_SUBJECTS,
                    pair.map(history -> recentSubjects(history.recency())));

            builder.put(ContextKeys.VILLAGE_RECENT_EVENT,
                    GossipConditionLogic.nextUntoldEventType(villager, player));

            builder.reportCapability(ContextCapabilities.Status.READY, "");
        } catch (Throwable t) {
            // The contract is that a source contains its own failure: one unreadable store degrades
            // exactly these fields and leaves the rest of the snapshot whole.
            McaConversations.LOGGER.debug("history context unavailable; those fields go dark", t);
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.FAILED, "history read failed");
        }
    }

    /**
     * Days between {@code day} and today, or UNKNOWN when there is no such day.
     *
     * <p>Never negative: a server clock moved backwards reads as "today" rather than as a meeting
     * that has not happened yet.
     */
    private static void putDays(ContextSnapshotBuilder builder, ContextKey<Long> key,
                                OptionalLong day, long today) {
        if (day.isEmpty()) {
            builder.unknown(key);
            return;
        }
        builder.put(key, Math.max(0L, today - day.getAsLong()));
    }

    /** The band for a last-talked day, or UNKNOWN when these two have never spoken. */
    static Optional<String> absenceBand(OptionalLong lastTalked, long today) {
        if (lastTalked.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(bandFor(Math.max(0L, today - lastTalked.getAsLong())));
    }

    /** Pure: the band name for a number of days. */
    static String bandFor(long days) {
        if (days < BRIEF_AFTER_DAYS) {
            return "none";
        }
        if (days < LONG_AFTER_DAYS) {
            return "brief";
        }
        return days < VERY_LONG_AFTER_DAYS ? "long" : "very_long";
    }

    /**
     * Subjects these two have touched, newest first.
     *
     * <p>Newest first because that is the order a callback wants them in: the thing said last is the
     * thing a line may refer back to without explaining itself.
     */
    static List<String> recentSubjects(TopicRecencyRecord recency) {
        if (recency == null) {
            return List.of();
        }
        return recency.subjects().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }
}
