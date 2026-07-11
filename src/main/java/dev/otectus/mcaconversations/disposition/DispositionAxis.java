package dev.otectus.mcaconversations.disposition;

import java.util.Locale;
import java.util.Optional;

/**
 * The six axes of the per-(villager, player) disposition vector (spec §4a). The vector is internal
 * and modulatory only: it decides which dialogue results open and how lines are voiced — MCA hearts
 * remain the sole authoritative, player-visible relationship economy.
 *
 * <p>Bipolar axes run −100..+100 around a per-personality resting baseline; {@code TENSION} and
 * {@code FAMILIARITY} are unipolar (0..100). All axes except {@code FAMILIARITY} drift back toward
 * the personality baseline with a per-axis half-life, so neither grudges nor banked goodwill from a
 * single conversation are permanent. Enum order is the NBT serialization order — never reorder.
 */
public enum DispositionAxis {

    /** Belief the player has their back. Slow to build, slow to fade. */
    TRUST(-100, 100, true, 168_000L),
    /** Regard for the player's competence and principles. */
    RESPECT(-100, 100, true, 120_000L),
    /** Enjoyment of the player's company (platonic). The most fluid warm axis. */
    WARMTH(-100, 100, true, 96_000L),
    /** Romantic regard. Adults + romance-eligible targets only; hard-gated everywhere it is touched. */
    ATTRACTION(-100, 100, true, 120_000L),
    /** Recent friction/wariness. Fades fast so one bad conversation is not a scar (milestones are). */
    TENSION(0, 100, true, 48_000L),
    /** Accumulated shared history. Never decays; earned by time, not grindable. */
    FAMILIARITY(0, 100, false, 0L);

    private final int min;
    private final int max;
    private final boolean decays;
    private final long defaultHalfLifeTicks;

    DispositionAxis(int min, int max, boolean decays, long defaultHalfLifeTicks) {
        this.min = min;
        this.max = max;
        this.decays = decays;
        this.defaultHalfLifeTicks = defaultHalfLifeTicks;
    }

    /** Stable lowercase key used in dialogue JSON, interiority data, and debug logging. */
    public String key() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static Optional<DispositionAxis> byKey(String key) {
        for (DispositionAxis axis : values()) {
            if (axis.key().equals(key)) {
                return Optional.of(axis);
            }
        }
        return Optional.empty();
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public boolean decays() {
        return decays;
    }

    public long defaultHalfLifeTicks() {
        return defaultHalfLifeTicks;
    }
}
