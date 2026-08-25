package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * The shared vocabulary for <em>what kind of thing the player just said</em> (plan §5.4).
 *
 * <p>Stance families exist so three separate systems can agree without hard-coding topic names:
 * the catalog declares which families a topic must offer, interiority profiles declare which
 * families a personality warms to or bristles at, and lint checks that no topic has become a row of
 * five ways to be nice.
 *
 * <p>A stance family is not a mechanic the player ever sees. Buttons carry the words the player
 * says; the family is metadata behind them.
 */
public enum StanceFamily {

    EMPATHY("empathy"),
    CURIOSITY("curiosity"),
    CANDOR("candor"),
    ENCOURAGEMENT("encouragement"),
    PRACTICAL_HELP("practical_help"),
    HUMOR("humor"),
    RESPECTFUL_DISAGREEMENT("respectful_disagreement"),
    SELF_DISCLOSURE("self_disclosure"),
    RESTRAINT("restraint"),
    CHALLENGE("challenge"),
    FLIRTATION("flirtation"),
    DISMISSAL("dismissal"),
    BOUNDARY_PUSH("boundary_push"),
    /**
     * Leaving, changing the subject, or "never mind". Always available, always free of consequence —
     * every decision node must offer one (plan §3.5).
     */
    EXIT("exit");

    private final String key;

    StanceFamily(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True for the stances a well-adjusted villager may dislike hearing — never rewarded by default. */
    public boolean isAdversarial() {
        return this == DISMISSAL || this == BOUNDARY_PUSH;
    }

    public static Optional<StanceFamily> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (StanceFamily family : values()) {
            if (family.key.equals(normalized)) {
                return Optional.of(family);
            }
        }
        return Optional.empty();
    }
}
