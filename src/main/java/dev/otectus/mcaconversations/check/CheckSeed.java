package dev.otectus.mcaconversations.check;

import java.util.UUID;

/**
 * The deterministic variance source for dialogue checks (spec §6a): a SplitMix64-mixed hash of the
 * stable check identity, never fresh RNG. Re-opening a conversation inside the same half-day bucket
 * reproduces the identical roll, so a rebuff cannot be save-scummed or click-spammed into a crit;
 * coming back later (a new bucket) is the legitimate way the dice move.
 */
public final class CheckSeed {

    /** Half a Minecraft day: long enough to kill re-open scumming, short enough that patience matters. */
    public static final long TIME_BUCKET_TICKS = 12_000L;

    private CheckSeed() {
    }

    /**
     * @param arcStage the current stage when the check belongs to an arc, else 0
     * @param dayTime  the world's absolute game time
     * @return a roll in −10..+10, identical for identical inputs
     */
    public static int roll(UUID villager, UUID player, String checkId, int arcStage, long dayTime) {
        long s = mix(villager.getMostSignificantBits());
        s = mix(s ^ villager.getLeastSignificantBits());
        s = mix(s ^ player.getMostSignificantBits());
        s = mix(s ^ player.getLeastSignificantBits());
        s = mix(s ^ checkId.hashCode());
        s = mix(s ^ arcStage);
        s = mix(s ^ Math.floorDiv(dayTime, TIME_BUCKET_TICKS));
        return (int) Math.floorMod(s, 21L) - 10;
    }

    /** SplitMix64 finalizer. */
    private static long mix(long z) {
        z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
        z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
        return z ^ (z >>> 31);
    }
}
