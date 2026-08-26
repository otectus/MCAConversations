package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The semantic need bands, which are what wellbeing content is authored against.
 *
 * <p>The numbers here are the boundaries of Townstead's own thresholds, so these cases double as the
 * readable statement of what each band means. The constants themselves are pinned against the real
 * jar by {@code TownsteadBindingProbeTest}; this pins the banding built on them.
 */
class TownsteadNeedsViewTest {

    private static TownsteadNeedsView hunger(int value) {
        return new TownsteadNeedsView(value, 0f, 0f, TownsteadNeedsView.MAX_THIRST,
                TownsteadNeedsView.MAX_THIRST, 0f, 0, false, false);
    }

    private static TownsteadNeedsView thirst(int value, boolean gated) {
        return new TownsteadNeedsView(TownsteadNeedsView.MAX_HUNGER, 0f, 0f, value, value, 0f, 0,
                false, gated);
    }

    private static TownsteadNeedsView fatigue(int value) {
        return new TownsteadNeedsView(TownsteadNeedsView.MAX_HUNGER, 0f, 0f,
                TownsteadNeedsView.MAX_THIRST, TownsteadNeedsView.MAX_THIRST, 0f, value, false, false);
    }

    @ParameterizedTest
    @CsvSource({
            "0, starving", "25, starving", "26, famished", "49, famished",
            "50, hungry", "59, hungry", "60, adequate", "79, adequate",
            "80, well_fed", "100, well_fed"
    })
    void hungerBands(int value, String expected) {
        assertEquals(expected, hunger(value).hungerBucket());
    }

    @ParameterizedTest
    @CsvSource({
            "0, dehydrated", "4, dehydrated", "5, parched", "11, parched",
            "12, thirsty", "15, thirsty", "16, hydrated", "17, hydrated",
            "18, quenched", "20, quenched"
    })
    void thirstBands(int value, String expected) {
        assertEquals(expected, thirst(value, false).thirstBucket());
    }

    @ParameterizedTest
    @CsvSource({
            "0, rested", "7, rested", "8, tired", "11, tired",
            "12, drowsy", "15, drowsy", "16, exhausted", "20, exhausted"
    })
    void fatigueBands(int value, String expected) {
        assertEquals(expected, fatigue(value).fatigueBucket());
    }

    @Test
    void gatedThirstAlwaysReadsQuenched() {
        // Townstead gates thirst behind a thirst mod. Without one the value is meaningless, and a
        // villager must never be pitied for a need this install does not simulate.
        assertEquals("quenched", thirst(0, true).thirstBucket());
        assertFalse(thirst(0, true).inCrisis());
        assertEquals("none", thirst(0, true).primaryNeed());
        assertFalse(thirst(0, true).thirstActive());
    }

    @Test
    void energyRisesAsFatigueFalls() {
        assertEquals(TownsteadNeedsView.MAX_FATIGUE, fatigue(0).energy());
        assertEquals(0, fatigue(TownsteadNeedsView.MAX_FATIGUE).energy());
    }

    @Test
    void primaryNeedComparesSharesRatherThanRawNumbers() {
        // 30/100 hunger is a larger share than 15/20 thirst is not: 0.30 against 0.75. Comparing the
        // raw ints would pick thirst, which is the bug this method exists to prevent.
        TownsteadNeedsView mixed = new TownsteadNeedsView(30, 0f, 0f, 15, 15, 0f, 0, false, false);
        assertEquals("hunger", mixed.primaryNeed());

        // And the other way round: 80/100 hunger against 4/20 thirst is 0.80 against 0.20.
        TownsteadNeedsView thirsty = new TownsteadNeedsView(80, 0f, 0f, 4, 4, 0f, 0, false, false);
        assertEquals("thirst", thirsty.primaryNeed());
    }

    @Test
    void collapseOutranksEverything() {
        TownsteadNeedsView collapsed = new TownsteadNeedsView(TownsteadNeedsView.MAX_HUNGER, 0f, 0f,
                TownsteadNeedsView.MAX_THIRST, TownsteadNeedsView.MAX_THIRST, 0f, 0, true, false);
        assertEquals("collapsed", collapsed.primaryNeed());
        assertTrue(collapsed.inCrisis());
    }

    @Test
    void nothingIsPressingWhenEveryNeedIsAtLeastHalf() {
        TownsteadNeedsView comfortable = new TownsteadNeedsView(50, 0f, 0f, 10, 10, 0f, 10, false, false);
        assertEquals("none", comfortable.primaryNeed());
    }

    @Test
    void crisisMatchesTownsteadEmergencyThresholds() {
        assertTrue(hunger(TownsteadNeedsView.HUNGER_EMERGENCY).inCrisis());
        assertFalse(hunger(TownsteadNeedsView.HUNGER_EMERGENCY + 1).inCrisis());
        assertTrue(thirst(TownsteadNeedsView.THIRST_EMERGENCY, false).inCrisis());
        assertFalse(thirst(TownsteadNeedsView.THIRST_EMERGENCY + 1, false).inCrisis());
        assertTrue(fatigue(TownsteadNeedsView.FATIGUE_EXHAUSTED).inCrisis());
        assertFalse(fatigue(TownsteadNeedsView.FATIGUE_EXHAUSTED - 1).inCrisis());
    }

    @Test
    void theEmptyViewIsTheNeutralOne() {
        assertEquals("well_fed", TownsteadNeedsView.EMPTY.hungerBucket());
        assertEquals("quenched", TownsteadNeedsView.EMPTY.thirstBucket());
        assertEquals("rested", TownsteadNeedsView.EMPTY.fatigueBucket());
        assertEquals("none", TownsteadNeedsView.EMPTY.primaryNeed());
        assertFalse(TownsteadNeedsView.EMPTY.inCrisis());
    }
}
