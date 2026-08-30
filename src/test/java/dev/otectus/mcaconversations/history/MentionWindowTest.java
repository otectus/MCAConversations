package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The seven-day mention window that {@code max_mentions_per_7_days} is measured against (spec §9.4).
 *
 * <p>Through 1.4.0 the count was derived from the scene's last-seen day alone, so it was 0 or 1 and
 * nothing else. A cap is reached when the count is <em>at least</em> the cap, so every scene authored
 * with a cap of 2 or 3 — 134 of the 316 shipped — had no weekly cap at all. These tests are the ones
 * that would have caught it: the second, third and fourth mention have to be distinguishable.
 */
class MentionWindowTest {

    private static final String SCENE = "work.farmer.the_late_frost";

    @Test
    void countsEachMentionSeparatelyWithinTheWindow() {
        TopicRecencyRecord record = TopicRecencyRecord.EMPTY;
        assertEquals(0, record.mentionsInWindow(SCENE, 10), "never mentioned");

        record = play(record, 10);
        assertEquals(1, record.mentionsInWindow(SCENE, 10), "first mention");

        record = play(record, 11);
        assertEquals(2, record.mentionsInWindow(SCENE, 11), "second mention, next day");

        record = play(record, 12);
        assertEquals(3, record.mentionsInWindow(SCENE, 12), "third mention");

        record = play(record, 13);
        assertEquals(4, record.mentionsInWindow(SCENE, 13), "fourth mention");
    }

    @Test
    void countsMoreThanOneMentionOnTheSameDay() {
        // 37 shipped scenes declare cooldown_days 0, so the same day really can hold two mentions.
        TopicRecencyRecord record = play(play(TopicRecencyRecord.EMPTY, 40), 40);
        assertEquals(2, record.mentionsInWindow(SCENE, 40));
    }

    @Test
    void theWindowIsTheSevenDayLabelsEndingToday() {
        // "not more than seven days ago" would span eight labels. The window is today-6 .. today.
        TopicRecencyRecord record = play(TopicRecencyRecord.EMPTY, 100);
        assertEquals(1, record.mentionsInWindow(SCENE, 106), "day 100 is the sixth day back");
        assertEquals(0, record.mentionsInWindow(SCENE, 107), "day 100 has left the window");
    }

    @Test
    void mentionsRollOutOfTheWindowOneDayAtATime() {
        TopicRecencyRecord record = TopicRecencyRecord.EMPTY;
        for (long day = 200; day <= 205; day++) {
            record = play(record, day);
        }
        assertEquals(6, record.mentionsInWindow(SCENE, 205));
        assertEquals(6, record.mentionsInWindow(SCENE, 206), "day 200 is still the sixth day back");
        assertEquals(5, record.mentionsInWindow(SCENE, 207), "day 200 drops out");
        assertEquals(4, record.mentionsInWindow(SCENE, 208), "day 201 drops out");
        assertEquals(0, record.mentionsInWindow(SCENE, 212), "the whole run has aged out");
    }

    @Test
    void aGapLongerThanTheWindowStartsTheCountOver() {
        TopicRecencyRecord record = play(play(TopicRecencyRecord.EMPTY, 300), 301);
        assertEquals(2, record.mentionsInWindow(SCENE, 301));
        record = play(record, 400);
        assertEquals(1, record.mentionsInWindow(SCENE, 400), "the old run cannot reach day 400");
    }

    @Test
    void aClockMovedBackwardsUnderCountsRatherThanLockingTheSceneOut() {
        // Bins ahead of "today" are ignored, so an operator who rolls the world back gets a scene
        // that is available again — never one held out of its own cap by a day that has not happened.
        TopicRecencyRecord record = play(play(TopicRecencyRecord.EMPTY, 500), 501);
        assertEquals(1, record.mentionsInWindow(SCENE, 500), "day 501 has not happened yet");
        record = play(record, 495);
        assertEquals(1, record.mentionsInWindow(SCENE, 495), "the ring restarted at the earlier day");
    }

    @Test
    void survivesASaveAndLoadIntact() {
        TopicRecencyRecord record = TopicRecencyRecord.EMPTY;
        for (long day = 600; day <= 602; day++) {
            record = play(record, day);
        }
        TopicRecencyRecord loaded = TopicRecencyRecord.load(record.save());
        assertEquals(3, loaded.mentionsInWindow(SCENE, 602));
        assertEquals(record.mentionsInWindow(SCENE, 605), loaded.mentionsInWindow(SCENE, 605));
    }

    @Test
    void aSaveWrittenBeforeTheRingExistedCountsAsOneMention() {
        // The migration: a pre-1.4.1 save has a last-seen day and no ring. One day is evidence the
        // scene played once, and nothing is evidence it played twice.
        CompoundTag legacy = new CompoundTag();
        CompoundTag sceneDays = new CompoundTag();
        sceneDays.putLong(SCENE, 700L);
        legacy.put("scene", sceneDays);

        TopicRecencyRecord loaded = TopicRecencyRecord.load(legacy);
        assertTrue(loaded.save().getCompound("scene_mentions").isEmpty(), "nothing was invented");
        assertEquals(1, loaded.mentionsInWindow(SCENE, 700));
        assertEquals(1, loaded.mentionsInWindow(SCENE, 706));
        assertEquals(0, loaded.mentionsInWindow(SCENE, 707));

        // And it carries on counting correctly from there.
        assertEquals(2, play(loaded, 701).mentionsInWindow(SCENE, 701));
    }

    @Test
    void aRingWithoutItsLastSeenDayIsDropped() {
        // The ring is anchored on the scene stamp, so one cannot outlive the other; the scene level's
        // own eviction is what bounds both.
        TopicRecencyRecord orphaned = new TopicRecencyRecord(
                Map.of(), Map.of(), Map.of(), Map.of(), Map.of(SCENE, 0b010101),
                Long.MIN_VALUE, 0, Long.MIN_VALUE);
        assertTrue(orphaned.sceneMentions().isEmpty());
        assertEquals(0, orphaned.mentionsInWindow(SCENE, 5));
    }

    @Test
    void ringsAreEvictedWithTheSceneStampsThatAnchorThem() {
        TopicRecencyRecord record = TopicRecencyRecord.EMPTY;
        for (int i = 0; i < TopicRecencyRecord.MAX_ENTRIES_PER_LEVEL + 8; i++) {
            record = record.played("scene." + i, "subject", "observe", "work", 1_000L + i);
        }
        assertEquals(TopicRecencyRecord.MAX_ENTRIES_PER_LEVEL, record.scenes().size());
        assertTrue(record.sceneMentions().size() <= record.scenes().size());
        assertTrue(record.scenes().keySet().containsAll(record.sceneMentions().keySet()),
                "a ring survived the stamp that anchored it");
    }

    private static TopicRecencyRecord play(TopicRecencyRecord record, long day) {
        return record.played(SCENE, "the_late_frost", "problem_solve", "work", day);
    }
}
