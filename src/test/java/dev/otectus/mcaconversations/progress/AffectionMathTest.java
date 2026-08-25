package dev.otectus.mcaconversations.progress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The pure arithmetic of the affection guard chain (plan §5.2, §13.5). */
class AffectionMathTest {

    @Test
    @DisplayName("authored deltas are clamped into the safe range at parse")
    void clampsAuthored() {
        assertEquals(8, AffectionMath.clampAuthored(99));
        assertEquals(-8, AffectionMath.clampAuthored(-99));
        assertEquals(3, AffectionMath.clampAuthored(3));
        assertEquals(0, AffectionMath.clampAuthored(0));
    }

    @Test
    @DisplayName("the multiplier scales both directions and truncates toward zero")
    void scalesTowardZero() {
        assertEquals(1, AffectionMath.scaled(3, false, 0.5));   // 1.5 -> 1, never 2
        assertEquals(-1, AffectionMath.scaled(-3, false, 0.5)); // -1.5 -> -1, never -2
        assertEquals(0, AffectionMath.scaled(1, false, 0.5));
        assertEquals(4, AffectionMath.scaled(2, false, 2.0));
        assertEquals(0, AffectionMath.scaled(5, false, 0.0));
    }

    @Test
    @DisplayName("stronger negatives double losses only, and compose with the multiplier")
    void strongerNegativesAffectLossesOnly() {
        assertEquals(-4, AffectionMath.scaled(-2, true, 1.0));
        assertEquals(2, AffectionMath.scaled(2, true, 1.0));
        assertEquals(-2, AffectionMath.scaled(-2, true, 0.5));
    }

    @Test
    @DisplayName("daily_repeat pays full, then half, then nothing")
    void dailyRepeatDiminishes() {
        assertEquals(4, AffectionMath.diminished(4, 0, false, ReplayPolicy.DAILY_REPEAT));
        assertEquals(2, AffectionMath.diminished(4, 1, true, ReplayPolicy.DAILY_REPEAT));
        assertEquals(0, AffectionMath.diminished(4, 2, true, ReplayPolicy.DAILY_REPEAT));
        assertEquals(0, AffectionMath.diminished(4, 9, true, ReplayPolicy.DAILY_REPEAT));
    }

    @Test
    @DisplayName("halving a repeat rounds toward zero in both directions")
    void repeatHalvingRoundsTowardZero() {
        assertEquals(0, AffectionMath.diminished(1, 1, true, ReplayPolicy.DAILY_REPEAT));
        assertEquals(0, AffectionMath.diminished(-1, 1, true, ReplayPolicy.DAILY_REPEAT));
        assertEquals(-1, AffectionMath.diminished(-3, 1, true, ReplayPolicy.DAILY_REPEAT));
    }

    @Test
    @DisplayName("once_per_day pays the first time each day and nothing after")
    void oncePerDay() {
        assertEquals(3, AffectionMath.diminished(3, 0, true, ReplayPolicy.ONCE_PER_DAY));
        assertEquals(0, AffectionMath.diminished(3, 1, true, ReplayPolicy.ONCE_PER_DAY));
    }

    @Test
    @DisplayName("once fires exactly once ever, whatever the day")
    void onceEver() {
        assertEquals(6, AffectionMath.diminished(6, 0, false, ReplayPolicy.ONCE));
        assertEquals(0, AffectionMath.diminished(6, 0, true, ReplayPolicy.ONCE));
    }

    @Test
    @DisplayName("positive and negative budgets are tracked separately")
    void budgetsDoNotCrossSubsidise() {
        // 1 of 2 positive spent, 3 of 3 negative spent: a +2 request still gets its remaining +1...
        assertEquals(1, AffectionMath.clampToBudget(2, 1, 2, 3, 3));
        // ...and a -2 request gets nothing, because the negative budget is exhausted.
        assertEquals(0, AffectionMath.clampToBudget(-2, 1, 2, 3, 3));
        // Spending the negative budget never opens positive headroom.
        assertEquals(2, AffectionMath.clampToBudget(5, 0, 2, 10, 3));
    }

    @Test
    @DisplayName("an exhausted budget clamps to zero rather than flipping sign")
    void budgetNeverFlipsSign() {
        assertEquals(0, AffectionMath.clampToBudget(3, 5, 2, 0, 3));
        assertEquals(0, AffectionMath.clampToBudget(-3, 0, 2, 9, 3));
    }

    @Test
    @DisplayName("the MC day rolls at the 24000-tick boundary, including before day zero")
    void dayBoundaries() {
        assertEquals(0, AffectionMath.dayOf(0));
        assertEquals(0, AffectionMath.dayOf(23_999));
        assertEquals(1, AffectionMath.dayOf(24_000));
        assertEquals(-1, AffectionMath.dayOf(-1));
    }
}
