package dev.otectus.mcaconversations.context;

import java.util.Locale;
import java.util.Optional;

/**
 * What a condition does when the field it reads is not known (spec §10.7).
 *
 * <p>Every optional context read must declare one of these, because there is no safe default.
 * Treating unknown as false is sometimes exactly right — and sometimes it is the difference between
 * "she is not pregnant" and "I could not tell", which is a claim the villager has no business making.
 * Forcing the author to say which they meant is the whole mechanism.
 */
public enum UnknownPolicy {

    /** The candidate is ineligible. Use when the scene's wording depends on the fact being true. */
    FAIL("fail"),

    /** Contribute no preference either way. Use for a field that only tunes weighting. */
    NEUTRAL("neutral"),

    /**
     * Take the scene's declared fallback route. Use when there is an honest, less specific thing to
     * say — "I don't remember the first words" rather than an invented first meeting (spec §22.2).
     */
    FALLBACK("fallback"),

    /**
     * Fail loudly. Authoring and tests only; never shipped for optional data, because a missing
     * optional field on a player's machine must degrade, not throw.
     */
    ERROR("error");

    private final String key;

    UnknownPolicy(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static Optional<UnknownPolicy> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (UnknownPolicy policy : values()) {
            if (policy.key.equals(normalized)) {
                return Optional.of(policy);
            }
        }
        return Optional.empty();
    }

    /**
     * How a condition scores against this policy when its field is unknown or unavailable.
     *
     * <p>{@link #FALLBACK} scores as a non-match here: taking the fallback <em>route</em> is the
     * scene catalog's job, and a condition's only job is to stop claiming the specific thing.
     */
    public boolean matchesWhenUnknown() {
        return this == NEUTRAL;
    }
}
