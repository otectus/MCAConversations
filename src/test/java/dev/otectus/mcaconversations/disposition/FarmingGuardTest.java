package dev.otectus.mcaconversations.disposition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FarmingGuardTest {

    @Test
    void dailyCapTruncatesGain() {
        // 6 already gained today against a cap of 8 leaves room for 2.
        assertEquals(2, FarmingGuard.guardedDelta(5, 6, 8, 0, 1.0));
        // Cap already reached: nothing more today.
        assertEquals(0, FarmingGuard.guardedDelta(5, 8, 8, 0, 1.0));
    }

    @Test
    void freshDayAllowsTheFullDelta() {
        assertEquals(5, FarmingGuard.guardedDelta(5, 0, 8, 0, 1.0));
    }

    @Test
    void diminishingReturnsSequenceIsFullHalfQuarterZero() {
        assertEquals(4, FarmingGuard.guardedDelta(4, 0, 20, 0, 1.0));
        assertEquals(2, FarmingGuard.guardedDelta(4, 0, 20, 1, 1.0));
        assertEquals(1, FarmingGuard.guardedDelta(4, 0, 20, 2, 1.0));
        assertEquals(0, FarmingGuard.guardedDelta(4, 0, 20, 3, 1.0));
        assertEquals(0, FarmingGuard.guardedDelta(4, 0, 20, 99, 1.0));
    }

    @Test
    void negativeDeltasAreEquallyGuarded() {
        // Tension cannot be rage-farmed: the same repeat/cap rules apply to losses.
        assertEquals(-2, FarmingGuard.guardedDelta(-4, 0, 20, 1, 1.0));
        assertEquals(-2, FarmingGuard.guardedDelta(-5, 6, 8, 0, 1.0));
        assertEquals(0, FarmingGuard.guardedDelta(-4, 0, 20, 3, 1.0));
    }

    @Test
    void gainMultiplierScalesBeforeTheCap() {
        assertEquals(10, FarmingGuard.guardedDelta(5, 0, 20, 0, 2.0));
        assertEquals(2, FarmingGuard.guardedDelta(5, 0, 20, 0, 0.5));
        assertEquals(0, FarmingGuard.guardedDelta(5, 0, 20, 0, 0.0));
    }
}
