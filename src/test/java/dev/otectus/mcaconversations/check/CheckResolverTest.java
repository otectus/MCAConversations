package dev.otectus.mcaconversations.check;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckResolverTest {

    private static CheckInputs inputs(int axisValue, int hearts, int fit, int mood, int roll,
                                      int difficulty, boolean tiersEnabled, boolean vectorEnabled) {
        return new CheckInputs(axisValue, hearts, fit, mood, roll, difficulty, tiersEnabled, vectorEnabled);
    }

    @Test
    void tierBandsAreExactAtTheirBoundaries() {
        // difficulty 40: crit at 55+, success at 40..54, partial at 25..39, rebuff below.
        assertEquals(CheckTier.CRIT, CheckResolver.resolve(inputs(55, 0, 0, 0, 0, 40, true, true)));
        assertEquals(CheckTier.SUCCESS, CheckResolver.resolve(inputs(54, 0, 0, 0, 0, 40, true, true)));
        assertEquals(CheckTier.SUCCESS, CheckResolver.resolve(inputs(40, 0, 0, 0, 0, 40, true, true)));
        assertEquals(CheckTier.PARTIAL, CheckResolver.resolve(inputs(39, 0, 0, 0, 0, 40, true, true)));
        assertEquals(CheckTier.PARTIAL, CheckResolver.resolve(inputs(25, 0, 0, 0, 0, 40, true, true)));
        assertEquals(CheckTier.REBUFF, CheckResolver.resolve(inputs(24, 0, 0, 0, 0, 40, true, true)));
    }

    @Test
    void allScoreComponentsAdd() {
        // axis 20 + hearts 40/4=10 + fit 5 + mood 3 + roll 2 = 40 -> exactly success at difficulty 40.
        assertEquals(CheckTier.SUCCESS, CheckResolver.resolve(inputs(20, 40, 5, 3, 2, 40, true, true)));
        assertEquals(CheckTier.PARTIAL, CheckResolver.resolve(inputs(20, 40, 5, 3, 1, 40, true, true)));
    }

    @Test
    void heartsContributionIsCappedAtPlusMinus25() {
        // hearts 200 would be 50 uncapped; capped at 25 the score is 25 -> partial at difficulty 40.
        assertEquals(CheckTier.PARTIAL, CheckResolver.resolve(inputs(0, 200, 0, 0, 0, 40, true, true)));
        assertEquals(CheckTier.REBUFF, CheckResolver.resolve(inputs(0, -200, 0, 0, 24 + 25, 40, true, true)));
    }

    @Test
    void tiersDisabledCollapsesToBinarySuccessOrRebuff() {
        // Would be a crit: collapses to plain success.
        assertEquals(CheckTier.SUCCESS, CheckResolver.resolve(inputs(80, 0, 0, 0, 0, 40, false, true)));
        // Would be a partial: collapses to rebuff.
        assertEquals(CheckTier.REBUFF, CheckResolver.resolve(inputs(30, 0, 0, 0, 0, 40, false, true)));
    }

    @Test
    void vectorDisabledSubstitutesTheDocumentedHeartsFormula() {
        // Vector off: axis term becomes clamp(hearts/2, ±50); the stored axis value is ignored.
        // hearts 40 -> axis term 20 + hearts term 10 = 30 -> partial at difficulty 40.
        assertEquals(CheckTier.PARTIAL, CheckResolver.resolve(inputs(100, 40, 0, 0, 0, 40, true, false)));
        // hearts 200 -> axis term capped 50 + hearts term capped 25 = 75 -> crit.
        assertEquals(CheckTier.CRIT, CheckResolver.resolve(inputs(0, 200, 0, 0, 0, 40, true, false)));
    }
}
