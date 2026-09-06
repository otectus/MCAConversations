package dev.otectus.mcaconversations.court;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The three ways "what is new in this chronicle" goes wrong, each of which ends with a village
 * announcing a dynasty's entire history at once.
 */
class ChronicleCursorTest {

    @Test
    void aFirstSightingSeedsTheCursorAndTellsNothing() {
        ChronicleCursor.Range range =
                ChronicleCursor.advance(CourtRoleMemory.CHRONICLE_UNSEEN, 40, 3);
        assertTrue(range.isEmpty(), "a capital that existed before we were installed is not news");
        assertEquals(40, range.newCursor());
    }

    @Test
    void newEntriesAreTold() {
        ChronicleCursor.Range range = ChronicleCursor.advance(10, 13, 5);
        assertEquals(10, range.from());
        assertEquals(13, range.to());
        assertEquals(3, range.count());
        assertEquals(13, range.newCursor());
    }

    @Test
    void nothingNewIsAnEmptyRangeAtTheSameCursor() {
        ChronicleCursor.Range range = ChronicleCursor.advance(12, 12, 3);
        assertTrue(range.isEmpty());
        assertEquals(12, range.newCursor());
    }

    @Test
    void aBurstIsCappedAtTheNewestEntries() {
        // Ten new entries, three allowed: the three most recent, and the older seven are skipped for
        // good. The cursor still lands at the end — skipping is a decision, not a backlog.
        ChronicleCursor.Range range = ChronicleCursor.advance(5, 15, 3);
        assertEquals(12, range.from());
        assertEquals(15, range.to());
        assertEquals(15, range.newCursor());
    }

    @Test
    void aTrimmedChronicleReseedsRatherThanRereads() {
        // Capitals trimmed the list under us. Clamping and re-reading would re-tell every entry that
        // survived the trim, so the cursor is re-seeded and nothing is told.
        ChronicleCursor.Range range = ChronicleCursor.advance(30, 4, 3);
        assertTrue(range.isEmpty());
        assertEquals(4, range.newCursor());
    }

    @Test
    void aZeroCapTellsNothingButStillAdvances() {
        ChronicleCursor.Range range = ChronicleCursor.advance(2, 9, 0);
        assertTrue(range.isEmpty());
        assertEquals(9, range.newCursor());
    }

    @Test
    void anEmptyChronicleIsNeverANegativeCursor() {
        assertEquals(0, ChronicleCursor.advance(CourtRoleMemory.CHRONICLE_UNSEEN, 0, 3).newCursor());
        assertEquals(0, ChronicleCursor.advance(0, -1, 3).newCursor());
    }
}
