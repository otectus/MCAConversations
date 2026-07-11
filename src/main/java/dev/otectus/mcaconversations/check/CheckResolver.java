package dev.otectus.mcaconversations.check;

/**
 * Deterministic tier selection (spec §4b). Score model:
 * {@code axisTerm + heartsTerm + personalityFit + moodAdjust + roll} against the authored
 * difficulty, with crit at +{@link #CRIT_MARGIN} and partial down to −{@link #PARTIAL_MARGIN}.
 * Hearts always contribute (capped) so the check system never fights MCA's economy — it refines it;
 * with the vector subsystem disabled, hearts alone carry the axis term (the documented fallback).
 */
public final class CheckResolver {

    public static final int CRIT_MARGIN = 15;
    public static final int PARTIAL_MARGIN = 15;
    /** Hearts term: hearts/4 capped to ±25 — meaningful, never dominant. */
    private static final int HEARTS_DIVISOR = 4;
    private static final int HEARTS_CAP = 25;
    /** Vector-off substitute axis term: hearts/2 capped to ±50. */
    private static final int FALLBACK_DIVISOR = 2;
    private static final int FALLBACK_CAP = 50;

    private CheckResolver() {
    }

    public static CheckTier resolve(CheckInputs in) {
        int axisTerm = in.vectorEnabled()
                ? in.axisValue()
                : clamp(in.hearts() / FALLBACK_DIVISOR, FALLBACK_CAP);
        int heartsTerm = clamp(in.hearts() / HEARTS_DIVISOR, HEARTS_CAP);
        int score = axisTerm + heartsTerm + in.personalityFit() + in.moodAdjust() + in.roll();

        if (!in.tiersEnabled()) {
            return score >= in.difficulty() ? CheckTier.SUCCESS : CheckTier.REBUFF;
        }
        if (score >= in.difficulty() + CRIT_MARGIN) {
            return CheckTier.CRIT;
        }
        if (score >= in.difficulty()) {
            return CheckTier.SUCCESS;
        }
        if (score >= in.difficulty() - PARTIAL_MARGIN) {
            return CheckTier.PARTIAL;
        }
        return CheckTier.REBUFF;
    }

    private static int clamp(int value, int cap) {
        return Math.max(-cap, Math.min(cap, value));
    }
}
