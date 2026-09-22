package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.EnumSet;
import java.util.Set;

/**
 * {@code conversations_townstead_available}: a capability gate (Townstead spec §8.1).
 *
 * <pre>"read_needs"   {"all": ["read_needs", "read_schedule"]}   {"any": ["read_spirit", "read_building"]}</pre>
 *
 * <p>An unknown capability id makes the gate one that never matches — a gate that quietly matched
 * everything would be worse than none — and the parse records that it did, so lint can find it.
 */
public record TownsteadAvailableQuery(Set<TownsteadCapability> all, Set<TownsteadCapability> any,
                                      boolean unknownId) {

    public static TownsteadAvailableQuery fromJson(JsonElement element) {
        if (element == null) {
            throw new IllegalArgumentException("conversations_townstead_available needs a capability");
        }
        Set<String> allIds;
        Set<String> anyIds = Set.of();
        if (element.isJsonObject()) {
            JsonObject json = element.getAsJsonObject();
            for (String key : json.keySet()) {
                if (!Set.of("all", "any").contains(key)) {
                    throw new IllegalArgumentException("unknown conversations_townstead_available field: " + key);
                }
            }
            allIds = TownsteadQuery.strings(json.get("all"), "all");
            anyIds = TownsteadQuery.strings(json.get("any"), "any");
        } else {
            allIds = TownsteadQuery.strings(element, "conversations_townstead_available");
        }
        if (allIds.isEmpty() && anyIds.isEmpty()) {
            throw new IllegalArgumentException("conversations_townstead_available needs a capability");
        }
        boolean unknown = false;
        EnumSet<TownsteadCapability> all = EnumSet.noneOf(TownsteadCapability.class);
        for (String id : allIds) {
            var cap = TownsteadCapability.byKey(id);
            unknown |= cap.isEmpty();
            cap.ifPresent(all::add);
        }
        EnumSet<TownsteadCapability> any = EnumSet.noneOf(TownsteadCapability.class);
        for (String id : anyIds) {
            var cap = TownsteadCapability.byKey(id);
            unknown |= cap.isEmpty();
            cap.ifPresent(any::add);
        }
        return new TownsteadAvailableQuery(Set.copyOf(all), Set.copyOf(any), unknown);
    }

    public boolean matches(Set<TownsteadCapability> bound) {
        if (unknownId || bound == null) {
            return false;
        }
        return bound.containsAll(all) && (any.isEmpty() || any.stream().anyMatch(bound::contains));
    }
}
