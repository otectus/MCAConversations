package dev.otectus.mcaconversations.history;

import java.util.Locale;
import java.util.Optional;

/**
 * How sure the holder of a fact is, and on what footing (spec §16.3).
 *
 * <p>Confidence is what keeps a rumour from being spoken as an observation. The plan's rule is that a
 * claim marked {@code observed} must have a provider and a claim marked {@code rumoured} must have a
 * source, and this enum is the runtime half of that: a beat whose frame is {@code observed} may not
 * be selected for an episode the villager only heard about (spec §10.3).
 *
 * <p>Ordered from firmest to weakest so propagation can decrement it by one step per hop without a
 * lookup table (spec §16.4).
 */
public enum Confidence {

    /** The villager was there. The strongest footing there is. */
    WITNESSED("witnessed", 0),

    /** Not witnessed, but not in doubt — a birth everyone attended, a building that is simply there. */
    CERTAIN("certain", 1),

    /** Told by someone with reason to know. */
    LIKELY("likely", 2),

    /** The player said so about themselves. Believed, but never treated as observed. */
    SELF_REPORTED("self_reported", 3),

    /** Heard, without a chain worth naming. */
    UNCERTAIN("uncertain", 4),

    /** Heard and disbelieved. Still worth discussing; not worth repeating as fact. */
    DOUBTED("doubted", 5);

    private final String key;
    private final int rank;

    Confidence(String key, int rank) {
        this.key = key;
        this.rank = rank;
    }

    public String key() {
        return key;
    }

    public int rank() {
        return rank;
    }

    /** True when this footing is firm enough to be stated as an observation. */
    public boolean isObserved() {
        return this == WITNESSED;
    }

    /** True when the villager should hedge — "she says", "I heard", "I don't know that it's true". */
    public boolean needsHedging() {
        return rank >= UNCERTAIN.rank;
    }

    /**
     * One propagation hop weaker.
     *
     * <p>Never below {@link #UNCERTAIN}: a fact that has travelled far becomes vague, and having it
     * decay all the way to {@link #DOUBTED} on distance alone would make every villager the sceptic
     * of every story they did not personally see. Doubt is authored, not accumulated (spec §16.4).
     */
    public Confidence weakened() {
        // Written as a switch rather than "one rank down", because SELF_REPORTED sits in the middle of
        // the ordering but is not a rung on this ladder: a story that travelled one hop must not come
        // out as "the player told me about themselves".
        return switch (this) {
            case WITNESSED, CERTAIN -> LIKELY;
            case LIKELY -> UNCERTAIN;
            case SELF_REPORTED, UNCERTAIN -> UNCERTAIN;
            case DOUBTED -> DOUBTED;
        };
    }

    public static Optional<Confidence> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (Confidence confidence : values()) {
            if (confidence.key.equals(normalized)) {
                return Optional.of(confidence);
            }
        }
        return Optional.empty();
    }
}
