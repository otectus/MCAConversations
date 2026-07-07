package dev.otectus.mcaconversations.gossip;

import java.util.Locale;
import java.util.Optional;

/** The village events villagers gossip about. */
public enum GossipEventType {
    MARRIAGE,
    DIVORCE,
    DEATH,
    BIRTH,
    /** A player completed an MCA: Quests quest for a villager (0.4.0 — only seeded when Quests is present). */
    QUEST;

    /** JSON/lang name, e.g. {@code marriage}. */
    public String jsonName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static Optional<GossipEventType> byJsonName(String name) {
        for (GossipEventType t : values()) {
            if (t.jsonName().equalsIgnoreCase(name)) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }
}
