package dev.otectus.mcaconversations.progress;

import java.util.Locale;
import java.util.Optional;

/**
 * How often a decision is allowed to pay out. Every consequence-bearing answer must declare one
 * (plan §3.3), and lint rejects an affection action without it.
 */
public enum ReplayPolicy {

    /**
     * The default. Saying the same thing again to the same villager on the same day is worth less
     * each time: full, then half, then nothing. Resets with the MC day.
     */
    DAILY_REPEAT("daily_repeat"),
    /** Pays out once per MC day; every later repeat that day is worth nothing at all. */
    ONCE_PER_DAY("once_per_day"),
    /**
     * Fires exactly once, ever, for this villager and player. Reserved for milestone outcomes —
     * a secret told, a boundary crossed, a promise made.
     */
    ONCE("once");

    private final String key;

    ReplayPolicy(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static Optional<ReplayPolicy> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (ReplayPolicy policy : values()) {
            if (policy.key.equals(normalized)) {
                return Optional.of(policy);
            }
        }
        return Optional.empty();
    }
}
