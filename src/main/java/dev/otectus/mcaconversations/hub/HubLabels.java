package dev.otectus.mcaconversations.hub;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * What a dynamic hub entry is called, and what a player can say to pick it (spec §14.2).
 *
 * <p>Eleven labels, all authored: five ways of saying "about what we were discussing", five of saying
 * "may I ask about that", and one neutral "what's on your mind?". None of them can be more specific
 * than a domain, which is what makes the privacy rule structural rather than a thing content has to
 * remember — there is no wording available to a label that could name a person or a secret.
 *
 * <p>The continuation wording is deliberately flat. A villager picking a subject back up has no way
 * of knowing whether the player left it four days ago or was interrupted mid-sentence, so the label
 * says only that there was something, and the authored resume scene says what.
 *
 * <p>The phrases are the matching side of the same eleven entries. They are checked before the
 * ordinary intent matcher, because a live hub entry is an exact offer the player has just been shown
 * and should not have to compete with the general corpus for it.
 */
public final class HubLabels {

    private HubLabels() {
    }

    /** The lang key for one entry's label. */
    public static String langKey(HubSlot slot) {
        return slot == null ? "" : "mcaconversations.hub." + keyOf(slot);
    }

    /** The lang key for a kind and domain, for the locale tests and the compiler. */
    public static String langKey(HubSlot.Kind kind, HubDomain domain) {
        return "mcaconversations.hub." + keyOf(kind, domain);
    }

    /** Every label key this build can show, so a locale test can check them all exist. */
    public static List<String> allLangKeys() {
        List<String> keys = new ArrayList<>();
        keys.add(langKey(HubSlot.Kind.MIND, HubDomain.WORK));
        for (HubSlot.Kind kind : List.of(HubSlot.Kind.CONTINUE, HubSlot.Kind.ASK)) {
            for (HubDomain domain : HubDomain.values()) {
                keys.add(langKey(kind, domain));
            }
        }
        return List.copyOf(keys);
    }

    /**
     * The phrases that pick this entry in chat.
     *
     * <p>Short and unmistakable on purpose. These are checked ahead of the general matcher, so a
     * phrase that could plausibly mean something else would quietly shadow a real topic — which is
     * why none of them is a bare word.
     */
    public static List<String> phrases(HubSlot slot) {
        if (slot == null) {
            return List.of();
        }
        if (slot.kind() == HubSlot.Kind.MIND) {
            return MIND_PHRASES;
        }
        String domain = slot.domain().key();
        if (slot.kind() != HubSlot.Kind.CONTINUE) {
            return List.of("ask about your " + domain, "can i ask about the " + domain,
                    "tell me about your " + domain);
        }
        // The label's own wording first, so a player who types back what the villager offered is
        // taken at their word, then the shorter ways of saying the same thing.
        String noun = continuationNoun(slot.domain());
        return noun.isEmpty()
                ? List.of("about what we were discussing", "where were we",
                        "go on about your " + domain)
                : List.of("about what we were discussing " + noun, "about what we were discussing",
                        "where were we", "go on about your " + domain,
                        "carry on about the " + domain);
    }

    /**
     * How a continuation label names its domain, or empty when it names nothing.
     *
     * <p>Restrained on purpose. "About what we were discussing" is the whole of the personal entry,
     * because a label that said which personal thing would be saying it to a player who has not been
     * told — and the other four say no more than the domain the hub already allows them.
     */
    static String continuationNoun(HubDomain domain) {
        return switch (domain) {
            case WORK -> "your work";
            case FAMILY -> "your family";
            case VILLAGE -> "the village";
            case EVERYDAY -> "your day";
            case PERSONAL -> "";
        };
    }

    private static final List<String> MIND_PHRASES = List.of(
            "what is on your mind", "whats on your mind", "anything on your mind");

    /**
     * The slot a typed line picks out of the ones currently offered, if any.
     *
     * <p>Deliberately an exact-ish match rather than a scored one: a hub entry is an offer the player
     * has this moment been shown, and something that merely resembles it should fall through to the
     * ordinary matcher rather than be treated as a selection.
     */
    public static Optional<HubSlot> resolve(HubPlan plan, String typed) {
        if (plan == null || plan.isEmpty() || typed == null) {
            return Optional.empty();
        }
        String needle = normalize(typed);
        if (needle.isEmpty()) {
            return Optional.empty();
        }
        for (HubSlot slot : plan.slots()) {
            for (String phrase : phrases(slot)) {
                if (needle.equals(normalize(phrase))) {
                    return Optional.of(slot);
                }
            }
        }
        return Optional.empty();
    }

    private static String keyOf(HubSlot slot) {
        return keyOf(slot.kind(), slot.domain());
    }

    private static String keyOf(HubSlot.Kind kind, HubDomain domain) {
        return kind == HubSlot.Kind.MIND ? kind.key() : kind.key() + "." + domain.key();
    }

    private static String normalize(String text) {
        StringBuilder out = new StringBuilder(text.length());
        for (char c : text.toLowerCase(Locale.ROOT).toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                out.append(c);
            } else if (Character.isWhitespace(c) && out.length() > 0 && out.charAt(out.length() - 1) != ' ') {
                out.append(' ');
            }
        }
        return out.toString().trim();
    }
}
