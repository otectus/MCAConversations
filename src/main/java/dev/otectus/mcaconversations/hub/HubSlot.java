package dev.otectus.mcaconversations.hub;

import java.util.Locale;
import java.util.Optional;

/**
 * One contextually surfaced entry above the six fixed categories (spec §14.2).
 *
 * <p>The plan allows three kinds and no more, and each answers a different question about the
 * villager standing in front of you: what were we in the middle of, what would they raise if I let
 * them, and what is there to ask about at all.
 *
 * @param kind   which of the three entries this is
 * @param domain how much the label is allowed to say
 * @param topic  the topic this entry opens, which the label never names
 */
public record HubSlot(HubSlot.Kind kind, HubDomain domain, String topic) {

    public HubSlot {
        topic = topic == null ? "" : topic.trim().toLowerCase(Locale.ROOT);
    }

    public enum Kind {

        /** The highest-priority ready thread: something the two of them are already in the middle of. */
        CONTINUE("continue"),

        /**
         * The director's own pick, phrased so the menu gives nothing away.
         *
         * <p>This is the one entry whose label is fixed and domain-free by design. It exists so a
         * villager can raise a thing the player has no way of knowing about — which means the button
         * that offers it must not be able to hint at what it is.
         */
        MIND("mind"),

        /** A subject the player has already met, offered again. */
        ASK("ask");

        private final String key;

        Kind(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        /** True when this kind's label names its domain. */
        public boolean namesDomain() {
            return this != MIND;
        }

        public static Optional<Kind> byKey(String key) {
            if (key == null) {
                return Optional.empty();
            }
            String normalized = key.trim().toLowerCase(Locale.ROOT);
            for (Kind kind : values()) {
                if (kind.key.equals(normalized)) {
                    return Optional.of(kind);
                }
            }
            return Optional.empty();
        }
    }

    /**
     * The answer name this slot lights up on the hub page.
     *
     * <p>Domain-free for {@link Kind#MIND}, so there is exactly one such button and it cannot be
     * read for a hint; per-domain for the other two, so their labels can say something useful without
     * ever saying something private.
     */
    public String answerName() {
        return kind.namesDomain()
                ? "dynamic_" + kind.key() + "_" + domain.key()
                : "dynamic_" + kind.key();
    }

    public boolean isWellFormed() {
        return kind != null && domain != null && !topic.isEmpty();
    }

    /** The answer name a kind and domain would use, for the compiler and the mixin. */
    public static String answerNameFor(Kind kind, HubDomain domain) {
        return new HubSlot(kind, domain, "x").answerName();
    }
}
