package dev.otectus.mcaconversations.disposition;

/**
 * Pure disposition arithmetic. Decay is computed lazily from elapsed game time on read/write — there
 * is deliberately no per-tick processing anywhere in the disposition system.
 */
public final class DispositionMath {

    private DispositionMath() {
    }

    public static int clamp(DispositionAxis axis, int value) {
        return Math.max(axis.min(), Math.min(axis.max(), value));
    }

    /**
     * Exponential drift toward the personality baseline: after one half-life, half the distance to
     * baseline remains. The decayed distance is truncated toward the baseline so values converge
     * exactly instead of hovering one point away forever. {@code FAMILIARITY} (and any elapsed or
     * multiplier that is not positive) is identity.
     *
     * @param decayMultiplier config scale on decay speed; 0 freezes values in place
     */
    public static int decayed(DispositionAxis axis, int raw, int baseline, long elapsedTicks,
                              double decayMultiplier) {
        if (!axis.decays() || elapsedTicks <= 0 || decayMultiplier <= 0) {
            return raw;
        }
        double halfLives = elapsedTicks * decayMultiplier / axis.defaultHalfLifeTicks();
        int delta = raw - baseline;
        int remaining = (int) (delta * Math.pow(0.5, halfLives));
        return clamp(axis, baseline + remaining);
    }
}
