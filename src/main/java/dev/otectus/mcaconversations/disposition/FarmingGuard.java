package dev.otectus.mcaconversations.disposition;

/**
 * Farming guards for disposition writes (spec §6a): repeating the same stance the same day yields
 * progressively less (full → half → quarter → nothing), and each axis has a per-day movement budget.
 * Guards apply to losses as well as gains — Tension content cannot be rage-farmed and then reset.
 */
public final class FarmingGuard {

    private FarmingGuard() {
    }

    /**
     * @param requestedDelta   the authored axis delta (signed)
     * @param gainedTodayAbs   total |movement| already applied to this axis today
     * @param dailyCap         per-axis per-day movement budget
     * @param repeatCountToday times this same stance already fired today
     * @param gainMultiplier   config scale on all deltas
     * @return the delta actually allowed (signed, truncated toward zero)
     */
    public static int guardedDelta(int requestedDelta, int gainedTodayAbs, int dailyCap,
                                   int repeatCountToday, double gainMultiplier) {
        int scaled = (int) (requestedDelta * gainMultiplier * repeatFactor(repeatCountToday));
        int remaining = Math.max(0, dailyCap - gainedTodayAbs);
        if (Math.abs(scaled) > remaining) {
            scaled = scaled < 0 ? -remaining : remaining;
        }
        return scaled;
    }

    private static double repeatFactor(int repeatCountToday) {
        return switch (repeatCountToday) {
            case 0 -> 1.0;
            case 1 -> 0.5;
            case 2 -> 0.25;
            default -> 0.0;
        };
    }
}
