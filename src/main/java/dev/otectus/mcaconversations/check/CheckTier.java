package dev.otectus.mcaconversations.check;

import java.util.Locale;
import java.util.Optional;

/**
 * Success tiers for dialogue checks (spec §4b). Every checked stance authors all four outcomes; the
 * resolver picks exactly one per click. The player reads how it landed from the villager's reply —
 * never from a number.
 */
public enum CheckTier {

    /** The villager opens further than asked — the payoff tier for milestones and unlocks. */
    CRIT,
    /** The intended good outcome. */
    SUCCESS,
    /** The attempt half-lands: tone shifts, little or no reward, often a second chance. */
    PARTIAL,
    /** The attempt misfires in character. May raise Tension; never farmable; always exits gracefully. */
    REBUFF;

    /** Stable lowercase key used in dialogue JSON. */
    public String key() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static Optional<CheckTier> byKey(String key) {
        for (CheckTier tier : values()) {
            if (tier.key().equals(key)) {
                return Optional.of(tier);
            }
        }
        return Optional.empty();
    }
}
