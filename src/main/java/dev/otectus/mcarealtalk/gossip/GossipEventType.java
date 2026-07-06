package dev.otectus.mcarealtalk.gossip;

import java.util.Locale;
import java.util.Optional;

/** The village events villagers gossip about in 0.1.0. */
public enum GossipEventType {
    MARRIAGE,
    DIVORCE,
    DEATH,
    BIRTH;

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
