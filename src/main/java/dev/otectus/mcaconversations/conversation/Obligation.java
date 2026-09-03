package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * What kind of response a villager line makes relevant (spec §10.1, §20.4).
 *
 * <p>The v1 contracts prove a reply's <em>stance</em> is permitted after a beat. That catches
 * "you don't sound like you enjoy it" under a line about being proud. It does not catch a reply page
 * where the villager asked a direct question and none of the three buttons answers it — every one of
 * them can be a permitted stance and the page can still be a non-sequitur.
 *
 * <p>Obligations close that gap. A beat declares what it makes relevant; every non-exit reply declares
 * which obligation it fulfils, or performs an explicitly permitted topic move; and the build refuses a
 * page where nothing answers.
 */
public enum Obligation {

    /** A question was asked. At least one reply must actually answer it. */
    ANSWER_QUESTION("answer_question"),

    /** Something was told. Receiving it is enough; agreement is not required. */
    ACKNOWLEDGE("acknowledge"),

    /** A choice was put to the player. At least one reply must take a side. */
    DECIDE("decide"),

    /** Something was ambiguous. At least one reply must be able to ask which was meant. */
    CLARIFY("clarify"),

    /** Help was asked for. At least one reply must offer, and one must be able to decline. */
    PROMISE("promise"),

    /** Something is broken between them. At least one reply must address it. */
    REPAIR("repair"),

    /**
     * Nothing is owed.
     *
     * <p>A real option, not a cop-out: a passing remark about the rain genuinely obliges nothing, and
     * pretending otherwise would force an answer page onto every line in the corpus.
     */
    NONE("none");

    private final String key;

    Obligation(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when a reply page must contain something that fulfils this. */
    public boolean requiresFulfilment() {
        return this != NONE && this != ACKNOWLEDGE;
    }

    public static Optional<Obligation> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (Obligation obligation : values()) {
            if (obligation.key.equals(normalized)) {
                return Optional.of(obligation);
            }
        }
        return Optional.empty();
    }

    /** The topic moves a reply may perform instead of fulfilling an obligation (spec §10.3). */
    public enum Move {
        /** Take the conversation somewhere adjacent, through a declared bridge (spec §11.4). */
        BRIDGE("bridge"),
        /** Decline to go further, without ending the conversation. */
        BOUNDARY("boundary"),
        /** Ask something back rather than answering. Legal; ignoring the question is not. */
        RECIPROCATE("reciprocate"),
        /** Leave. Always available on every page. */
        EXIT("exit");

        private final String key;

        Move(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        public static Optional<Move> byKey(String key) {
            if (key == null) {
                return Optional.empty();
            }
            String normalized = key.trim().toLowerCase(Locale.ROOT);
            for (Move move : values()) {
                if (move.key.equals(normalized)) {
                    return Optional.of(move);
                }
            }
            return Optional.empty();
        }
    }
}
