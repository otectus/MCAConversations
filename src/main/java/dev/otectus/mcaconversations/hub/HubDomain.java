package dev.otectus.mcaconversations.hub;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * The five things a dynamic hub entry is allowed to be about (spec §14.2, §14.3).
 *
 * <p>A dynamic label may not expose a secret, diagnose an emotion, or name a person the player has
 * not heard about. The simplest way to guarantee all three is to never let a label carry anything
 * more specific than a domain: "your work" cannot leak a confidence, and "the village" cannot name
 * anybody. Everything specific stays inside the scene, where it is gated by the scene's own
 * conditions and by the privacy the fact was recorded under.
 *
 * <p>{@link #PERSONAL} is deliberately the vaguest of the five. It covers the topics where a
 * specific label would be exactly the leak the plan forbids — fears, regrets, a secret — so its
 * wording says only that there is something, never what.
 */
public enum HubDomain {

    /** The trade, and everything that happens because of it. */
    WORK("work"),

    /** The place and the people in it. */
    VILLAGE("village"),

    /** The villager's inner life. Labelled without ever saying which part. */
    PERSONAL("personal"),

    /** The weather, the food, the day. Nothing at stake. */
    EVERYDAY("everyday"),

    /** The people they are related to, or married to. */
    FAMILY("family");

    private final String key;

    HubDomain(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /**
     * Which domain a topic belongs to.
     *
     * <p>An explicit table rather than a rule derived from the category page, because the two answer
     * different questions: a category is where a player goes looking for something, and a domain is
     * how little a label may say about it. {@code origin} sits in the Personal category and is a
     * village-domain subject; {@code standing} is the reverse.
     */
    public static Optional<HubDomain> ofTopic(String topic) {
        if (topic == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(BY_TOPIC.get(topic.trim().toLowerCase(Locale.ROOT)));
    }

    public static Optional<HubDomain> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (HubDomain domain : values()) {
            if (domain.key.equals(normalized)) {
                return Optional.of(domain);
            }
        }
        return Optional.empty();
    }

    private static final Map<String, HubDomain> BY_TOPIC = Map.ofEntries(
            Map.entry("work", WORK),
            Map.entry("work_offer", WORK),

            Map.entry("village", VILLAGE),
            Map.entry("people", VILLAGE),
            Map.entry("neighbour", VILLAGE),
            Map.entry("rumors", VILLAGE),
            Map.entry("standing", VILLAGE),
            Map.entry("place", VILLAGE),
            Map.entry("origin", VILLAGE),

            Map.entry("life", PERSONAL),
            Map.entry("dreams", PERSONAL),
            Map.entry("fears", PERSONAL),
            Map.entry("hopes", PERSONAL),
            Map.entry("regrets", PERSONAL),
            Map.entry("feelings", PERSONAL),
            Map.entry("secret", PERSONAL),
            Map.entry("worries", PERSONAL),
            Map.entry("interests", PERSONAL),
            Map.entry("values", PERSONAL),
            Map.entry("memories", PERSONAL),
            Map.entry("firstmet", PERSONAL),
            Map.entry("future", PERSONAL),
            Map.entry("happy", PERSONAL),
            Map.entry("player", PERSONAL),
            Map.entry("shared_history", PERSONAL),

            Map.entry("day", EVERYDAY),
            Map.entry("food", EVERYDAY),
            Map.entry("weather", EVERYDAY),
            Map.entry("season", EVERYDAY),
            Map.entry("routine", EVERYDAY),
            Map.entry("news", EVERYDAY),
            Map.entry("noticed", EVERYDAY),
            Map.entry("checkin", EVERYDAY),

            Map.entry("us", FAMILY),
            Map.entry("family", FAMILY),
            Map.entry("ask_parent", FAMILY),
            Map.entry("checkin_child", FAMILY));
}
