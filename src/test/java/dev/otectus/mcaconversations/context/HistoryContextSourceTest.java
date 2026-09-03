package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.history.TopicRecencyRecord;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The two judgements the history context source makes, and the one it refuses to make.
 *
 * <p>The refusal is the important one. Two people who have never spoken have no days-since, and
 * answering {@code 0} there would read as "we spoke today" — a confident wrong answer, which is the
 * one thing a villager must never give. Every field reports UNKNOWN instead and lets the condition's
 * own policy decide what that means.
 */
class HistoryContextSourceTest {

    @Test
    void aPairWhoHaveNeverSpokenHaveNoAbsenceBand() {
        assertEquals(Optional.empty(),
                HistoryContextSource.absenceBand(OptionalLong.empty(), 100));
    }

    @Test
    void theBandsCoverTheWholeRange() {
        assertEquals("none", HistoryContextSource.bandFor(0));
        assertEquals("none", HistoryContextSource.bandFor(1));
        assertEquals("brief", HistoryContextSource.bandFor(2));
        assertEquals("brief", HistoryContextSource.bandFor(6));
        assertEquals("long", HistoryContextSource.bandFor(7));
        assertEquals("long", HistoryContextSource.bandFor(29));
        assertEquals("very_long", HistoryContextSource.bandFor(30));
        assertEquals("very_long", HistoryContextSource.bandFor(4000));
    }

    @Test
    void theBandIsMeasuredFromTheDayTheyLastSpoke() {
        assertEquals(Optional.of("none"),
                HistoryContextSource.absenceBand(OptionalLong.of(100), 100));
        assertEquals(Optional.of("brief"),
                HistoryContextSource.absenceBand(OptionalLong.of(97), 100));
        assertEquals(Optional.of("very_long"),
                HistoryContextSource.absenceBand(OptionalLong.of(1), 100));
    }

    @Test
    void aClockMovedBackwardsReadsAsToday() {
        // Not as a conversation that has not happened yet. A negative gap would make every "it has
        // been a while" line fire at once.
        assertEquals(Optional.of("none"),
                HistoryContextSource.absenceBand(OptionalLong.of(500), 100));
    }

    @Test
    void recentSubjectsComeBackNewestFirst() {
        // Newest first because that is the order a callback wants: the thing said last is the thing
        // a line can refer back to without explaining itself.
        TopicRecencyRecord recency = new TopicRecencyRecord(
                Map.of(), Map.of("work.ink", 10L, "fears.dark", 30L, "day.early", 20L),
                Map.of(), Map.of(), Map.of(), Long.MIN_VALUE, 0, Long.MIN_VALUE);
        assertEquals(List.of("fears.dark", "day.early", "work.ink"),
                HistoryContextSource.recentSubjects(recency));
    }

    @Test
    void noRecencyMeansNoSubjects() {
        assertEquals(List.of(), HistoryContextSource.recentSubjects(null));
        assertEquals(List.of(), HistoryContextSource.recentSubjects(TopicRecencyRecord.EMPTY));
    }

    @Test
    void thisSourceOwnsExactlyTheFieldsItSaysItDoes() {
        // The declared list is what ContextSnapshotBuilder marks unavailable in one call when the
        // source is absent, so a field missing from it would go silently unwritten rather than
        // loudly unavailable — which is the whole failure this workstream fixed.
        List<String> declared = new HistoryContextSource().declares().stream()
                .map(ContextKey::id)
                .sorted()
                .toList();
        assertEquals(List.of("narrative.active_episodes", "narrative.due_commitments",
                "narrative.ready_threads", "narrative.recent_subjects", "narrative.rupture",
                "time.absence_band", "time.days_since_first_met", "time.days_since_last_talk",
                "village.recent_event"), declared);
    }

    @Test
    void everyFieldHereIsPinned() {
        // A promise does not come due, and a rupture does not heal, between two turns of one
        // conversation. Marking any of these volatile would let a bound referent drift mid-scene.
        for (ContextKey<?> key : new HistoryContextSource().declares()) {
            assertTrue(!key.isVolatile(), key.id() + " must be pinned");
        }
        assertTrue(!new HistoryContextSource().hasVolatileFields());
    }
}
