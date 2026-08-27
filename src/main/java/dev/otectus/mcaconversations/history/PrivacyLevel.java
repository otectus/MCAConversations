package dev.otectus.mcaconversations.history;

import java.util.Locale;
import java.util.Optional;

/**
 * How freely a remembered fact may be repeated (spec §16.3, §4.5).
 *
 * <p>Distinct from conversational <em>openness</em>, which the existing beat contracts already carry.
 * Openness says whether this villager will keep talking; privacy says whether the thing itself is
 * theirs to tell. A villager can be perfectly open and still decline, and that refusal is content
 * rather than a generic "no" — which is the whole reason this is a first-class field rather than a
 * boolean on a scene.
 *
 * <p>Ordered from most to least repeatable, so {@link #permits} is a single comparison and a scene
 * can say "anything up to discreet" without enumerating levels.
 */
public enum PrivacyLevel {

    /** Everyone in the village knows. Safe to repeat, safe to name participants. */
    PUBLIC("public", 0),

    /** Ordinary life. Repeatable without much thought, but not announced. */
    ORDINARY("ordinary", 1),

    /** Told in confidence-ish. May be described without naming its subject. */
    DISCREET("discreet", 2),

    /** Told in confidence. Repeating it is a breach with an authored consequence. */
    CONFIDENTIAL("confidential", 3),

    /** The speaker's own, and not offered at all unless they choose to. */
    SPEAKER_ONLY("speaker_only", 4);

    private final String key;
    private final int rank;

    PrivacyLevel(String key, int rank) {
        this.key = key;
        this.rank = rank;
    }

    public String key() {
        return key;
    }

    public int rank() {
        return rank;
    }

    /** True when a fact at this level may be repeated by something cleared up to {@code ceiling}. */
    public boolean permits(PrivacyLevel ceiling) {
        return ceiling != null && rank <= ceiling.rank;
    }

    /** True when repeating this without permission is a breach the outcome has to handle. */
    public boolean isBreachable() {
        return rank >= CONFIDENTIAL.rank;
    }

    /** True when the fact may be described but its subject may not be named (spec §16.3). */
    public boolean requiresAnonymity() {
        return this == DISCREET;
    }

    public static Optional<PrivacyLevel> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (PrivacyLevel level : values()) {
            if (level.key.equals(normalized)) {
                return Optional.of(level);
            }
        }
        return Optional.empty();
    }

    /** The level an unlabelled record takes: ordinary life, repeatable but not announced. */
    public static PrivacyLevel defaultLevel() {
        return ORDINARY;
    }
}
