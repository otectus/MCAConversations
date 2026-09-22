package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Set;

/**
 * {@code conversations_townstead_skill}: whether the villager has learned a profession skill
 * (Townstead spec §8.5). Read-only, always — nothing here learns, forgets or retrains anything.
 *
 * <pre>{"has": "townstead:artisan_baking"}  {"any": ["a:x", "b:y"]}  {"all": ["a:x", "b:y"]}</pre>
 */
public record TownsteadSkillQuery(Set<String> all, Set<String> any) {

    public static TownsteadSkillQuery fromJson(JsonElement element) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException("conversations_townstead_skill must be an object");
        }
        JsonObject json = element.getAsJsonObject();
        for (String key : json.keySet()) {
            if (!Set.of("has", "any", "all").contains(key)) {
                throw new IllegalArgumentException("unknown conversations_townstead_skill field: " + key);
            }
        }
        java.util.Set<String> all = new java.util.LinkedHashSet<>(TownsteadQuery.strings(json.get("all"), "all"));
        all.addAll(TownsteadQuery.strings(json.get("has"), "has"));
        Set<String> any = Set.copyOf(TownsteadQuery.strings(json.get("any"), "any"));
        if (all.isEmpty() && any.isEmpty()) {
            throw new IllegalArgumentException("conversations_townstead_skill needs has, any or all");
        }
        return new TownsteadSkillQuery(Set.copyOf(all), any);
    }

    public boolean matches(Set<String> learned) {
        if (learned == null || learned.isEmpty()) {
            return false;
        }
        java.util.Set<String> lower = new java.util.HashSet<>();
        learned.forEach(s -> lower.add(s.toLowerCase(java.util.Locale.ROOT)));
        return lower.containsAll(all) && (any.isEmpty() || any.stream().anyMatch(lower::contains));
    }
}
