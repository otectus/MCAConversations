package dev.otectus.mcaconversations.history;

import java.util.Locale;
import java.util.Optional;

/**
 * The one way a villager's account of an event is allowed to be wrong (spec §16.3, §16.5).
 *
 * <p>The plan is emphatic that this is <b>authored only</b>. Nothing in the runtime introduces a
 * distortion on its own, and propagation does not invent one: a rumour that travels loses confidence
 * and gains hedging, it does not silently acquire false detail. That rule is what keeps §16.5's line
 * intact — two villagers may read the same public event differently, but neither may contradict the
 * event log about what happened, unless an author has explicitly marked one account as mistaken.
 *
 * <p>{@link #NONE} is therefore the only value the runtime ever assigns.
 */
public enum Distortion {

    /** The account matches the record. Everything the runtime produces is this. */
    NONE("none"),

    /** True as far as it goes, and something was left out. Authored. */
    OMITTED_DETAIL("omitted_detail"),

    /** The facts are right and the reading of them is wrong. Authored. */
    MISTAKEN_INTERPRETATION("mistaken_interpretation");

    private final String key;

    Distortion(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when the account may be spoken as the plain truth of the matter. */
    public boolean isFaithful() {
        return this == NONE;
    }

    /**
     * True when a correction scene can meaningfully be offered against this account.
     *
     * <p>There is nothing to correct in a faithful account, and correcting an omission is a
     * different conversation from correcting a misreading — which is why the two are separate values
     * rather than one {@code distorted} flag.
     */
    public boolean isCorrectable() {
        return this != NONE;
    }

    public static Optional<Distortion> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (Distortion distortion : values()) {
            if (distortion.key.equals(normalized)) {
                return Optional.of(distortion);
            }
        }
        return Optional.empty();
    }
}
