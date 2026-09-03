package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.history.Confidence;

import java.util.Locale;
import java.util.Optional;

/**
 * On what footing a villager line asserts what it asserts (spec §10.1, §10.3).
 *
 * <p>The rule this enforces is short: <b>a claim marked {@link #OBSERVED} must have a provider, and a
 * claim marked {@link #RUMOURED} must have a source.</b> Without it, the same authored line can be
 * selected for something the villager saw and for something they half-heard, and the difference — the
 * one a player actually notices — disappears.
 */
public enum Epistemic {

    /** The villager saw it. Requires a context provider or an episode they own. */
    OBSERVED("observed"),

    /** Someone told them. Requires a source, even if the source is "someone". */
    REPORTED("reported"),

    /** They worked it out. Legal without a provider, but must be worded as inference. */
    INFERRED("inferred"),

    /** Village talk. Requires a source chain or an explicit anonymous-source token. */
    RUMOURED("rumoured"),

    /** They do not know. The honest frame for a missing or unavailable fact. */
    UNCERTAIN("uncertain"),

    /**
     * Deliberately not a claim about the world at all — a story, a joke, a hypothetical, a game with
     * a child. Exempt from the provider rule precisely because it asserts nothing (spec §10.3).
     */
    FICTIONAL_PLAY("fictional_play");

    private final String key;

    Epistemic(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when a line on this footing needs something in the world to back it. */
    public boolean requiresProvider() {
        return this == OBSERVED;
    }

    /** True when a line on this footing needs a named or explicitly anonymous source. */
    public boolean requiresSource() {
        return this == REPORTED || this == RUMOURED;
    }

    /** True when the wording must hedge — "she says", "I heard", "I don't know that it's true". */
    public boolean requiresHedging() {
        return this == RUMOURED || this == UNCERTAIN;
    }

    /** True when a fact held at {@code confidence} may honestly be spoken on this footing. */
    public boolean permits(Confidence confidence) {
        if (confidence == null) {
            return this != OBSERVED;
        }
        return switch (this) {
            case OBSERVED -> confidence.isObserved();
            case REPORTED -> confidence != Confidence.WITNESSED;
            case RUMOURED, UNCERTAIN -> confidence.needsHedging()
                    || confidence == Confidence.LIKELY || confidence == Confidence.SELF_REPORTED;
            case INFERRED, FICTIONAL_PLAY -> true;
        };
    }

    public static Optional<Epistemic> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (Epistemic epistemic : values()) {
            if (epistemic.key.equals(normalized)) {
                return Optional.of(epistemic);
            }
        }
        return Optional.empty();
    }
}
