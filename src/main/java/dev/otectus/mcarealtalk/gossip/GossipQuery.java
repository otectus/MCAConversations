package dev.otectus.mcarealtalk.gossip;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.EnumSet;
import java.util.Set;

/**
 * Parsed form of the {@code realtalk_gossip} condition / {@code realtalk_gossip_say} action JSON:
 * {@code {"types": ["marriage", "death"], "max_age": 72000}}. Both keys optional — defaults are all
 * types, 3 MC days.
 */
public record GossipQuery(Set<GossipEventType> types, long maxAgeTicks) {

    public static final long DEFAULT_MAX_AGE = 72000L;

    /** @throws IllegalArgumentException on an unknown type name (fails at datapack parse). */
    public static GossipQuery fromJson(JsonObject json) {
        Set<GossipEventType> types = EnumSet.noneOf(GossipEventType.class);
        long maxAge = DEFAULT_MAX_AGE;
        if (json != null) {
            if (json.has("types")) {
                for (JsonElement e : json.getAsJsonArray("types")) {
                    String name = e.getAsString();
                    types.add(GossipEventType.byJsonName(name).orElseThrow(
                            () -> new IllegalArgumentException("realtalk_gossip unknown type \"" + name + "\"")));
                }
            }
            if (json.has("max_age")) {
                maxAge = json.get("max_age").getAsLong();
            }
        }
        return new GossipQuery(types, maxAge);
    }
}
