package dev.otectus.mcaconversations.check;

/**
 * The fully-assembled, pure inputs for one check resolution. Built once per click by
 * {@code CheckContextFactory} from the disposition store, MCA state, and config; identical inputs
 * always resolve to the identical tier.
 *
 * @param axisValue      decayed disposition value of the check's axis (ignored when {@code !vectorEnabled})
 * @param hearts         MCA hearts for this (villager, player)
 * @param personalityFit interiority bias for this stance and personality (0 until 0.9.0 data lands)
 * @param moodAdjust     combined mood + conversation-state adjustment ({@link MoodModifiers})
 * @param roll           the seeded roll ({@link CheckSeed})
 * @param difficulty     the authored difficulty (0..100)
 * @param tiersEnabled   config: four tiers when on, binary success/rebuff when off
 * @param vectorEnabled  config: hearts-only fallback formula when off
 */
public record CheckInputs(int axisValue, int hearts, int personalityFit, int moodAdjust, int roll,
                          int difficulty, boolean tiersEnabled, boolean vectorEnabled) {
}
