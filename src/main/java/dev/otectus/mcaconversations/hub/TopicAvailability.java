package dev.otectus.mcaconversations.hub;

import net.minecraft.network.chat.Component;

/**
 * Why a topic the player has already been offered cannot be opened right now, in words.
 *
 * <p>Three rules keep this from becoming a debug channel.
 *
 * <ol>
 *   <li><b>Only about something already on offer.</b> These sentences are produced for a topic the
 *       hub was showing this player. A topic they have not discovered, are too young for, or would
 *       never be offered stays absent: it has no reason here, because it has no presence.</li>
 *   <li><b>Social, never technical.</b> Each constant is something a person could say about another
 *       person. A network failure or a failed action is not one of these; those are lapses and the
 *       card explains them as lapses, in the villager's absence rather than in their voice.</li>
 *   <li><b>Descriptive, never authoritative.</b> A sentence from here explains a refusal that has
 *       already happened. Selecting an unavailable topic still goes through the server's ordinary
 *       gate, which is the thing that decides.</li>
 * </ol>
 *
 * <p>The wording carries no pronoun unless the client is naming the villager it is about, so a
 * villager whose gender this sentence does not know is described rather than assumed.
 */
public enum TopicAvailability {

    /** Nothing to explain: the topic may be opened. */
    AVAILABLE(null),
    /** The villager is occupied — working, or in the middle of somebody else's conversation. */
    BUSY("busy"),
    /** The pair have been over this subject already today. */
    DISCUSSED_TODAY("discussed_today"),
    /** Offered, but the conditions behind it do not hold at the moment. */
    NOT_READY("not_ready");

    private final String key;

    TopicAvailability(String key) {
        this.key = key;
    }

    public boolean explainable() {
        return key != null;
    }

    /**
     * The sentence to say, or null when there is nothing to explain.
     *
     * @param villagerName the villager's displayed name, or null when the caller does not have one
     */
    public Component sentence(Component villagerName) {
        if (!explainable()) {
            return null;
        }
        if (this == DISCUSSED_TODAY) {
            // About the two of them rather than about the villager, so it needs neither a name nor a
            // pronoun in any locale this ships.
            return Component.translatable("mcaconversations.availability.discussed_today");
        }
        return villagerName == null
                ? Component.translatable("mcaconversations.availability." + key + ".unnamed")
                : Component.translatable("mcaconversations.availability." + key + ".named",
                        villagerName);
    }

    /** Every key this enum can show, so the locale test can check they all exist. */
    public static java.util.List<String> allLangKeys() {
        java.util.List<String> keys = new java.util.ArrayList<>();
        keys.add("mcaconversations.availability.discussed_today");
        for (TopicAvailability value : values()) {
            if (value.explainable() && value != DISCUSSED_TODAY) {
                keys.add("mcaconversations.availability." + value.key + ".named");
                keys.add("mcaconversations.availability." + value.key + ".unnamed");
            }
        }
        return java.util.List.copyOf(keys);
    }
}
