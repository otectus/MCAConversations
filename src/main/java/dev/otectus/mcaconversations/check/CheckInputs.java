package dev.otectus.mcaconversations.check;

/**
 * The fully-assembled, pure inputs for one check resolution. Built once per click by
 * {@code CheckContextFactory} from the disposition store, MCA state, and config; identical inputs
 * always resolve to the identical tier.
 *
 * @param axisValue      decayed disposition value of the check's axis (ignored when {@code !vectorEnabled})
 * @param hearts         MCA hearts for this (villager, player)
 * @param personalityFit interiority bias for this stance and personality (0 until 0.9.0 data lands)
 * @param publicStandingFit the player's public standing with this villager's village, as a small
 *                       additive term (spec 30.3). Non-zero only for TRUST and RESPECT checks, hard
 *                       clamped to ±8, and exactly 0 when MCA: Reputation is absent or disabled — so
 *                       every existing seeded outcome is unchanged. Deliberately smaller than the
 *                       tier margins, so standing colours a check but can never decide one alone.
 * @param moodAdjust     combined mood + conversation-state adjustment ({@link MoodModifiers})
 * @param roll           the seeded roll ({@link CheckSeed})
 * @param difficulty     the authored difficulty (0..100)
 * @param tiersEnabled   config: four tiers when on, binary success/rebuff when off
 * @param vectorEnabled  config: hearts-only fallback formula when off
 */
public record CheckInputs(int axisValue, int hearts, int personalityFit, int publicStandingFit,
                          int moodAdjust, int roll, int difficulty, boolean tiersEnabled,
                          boolean vectorEnabled) {

    /**
     * A copy with no public-standing term, for asserting that the rest of the formula is untouched by
     * the integration.
     */
    public CheckInputs withoutPublicStanding() {
        return new CheckInputs(axisValue, hearts, personalityFit, 0, moodAdjust, roll, difficulty,
                tiersEnabled, vectorEnabled);
    }
}
