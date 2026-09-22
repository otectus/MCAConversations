package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Set;

/**
 * {@code conversations_townstead_tags}: Townstead's own resolved context tags, rather than a partial
 * copy of its resolver (Townstead spec §8.3).
 *
 * <pre>{"all": ["on_shift:work"], "any": ["hungry", "thirsty", "tired"], "none": ["raid_active"]}</pre>
 *
 * <p>Every non-empty clause must hold: all of {@code all}, at least one of {@code any}, none of
 * {@code none}. At least one clause is required, and an empty tag set (Townstead absent) matches
 * nothing, even a {@code none}-only query — "no tags known" is not "none of these tags".
 */
public record TownsteadTagQuery(Set<String> all, Set<String> any, Set<String> none) {

    public static TownsteadTagQuery fromJson(JsonElement element) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException("conversations_townstead_tags must be an object");
        }
        JsonObject json = element.getAsJsonObject();
        for (String key : json.keySet()) {
            if (!Set.of("all", "any", "none").contains(key)) {
                throw new IllegalArgumentException("unknown conversations_townstead_tags field: " + key);
            }
        }
        TownsteadTagQuery query = new TownsteadTagQuery(
                Set.copyOf(TownsteadQuery.strings(json.get("all"), "all")),
                Set.copyOf(TownsteadQuery.strings(json.get("any"), "any")),
                Set.copyOf(TownsteadQuery.strings(json.get("none"), "none")));
        if (query.all.isEmpty() && query.any.isEmpty() && query.none.isEmpty()) {
            throw new IllegalArgumentException("conversations_townstead_tags needs all, any or none");
        }
        return query;
    }

    public boolean matches(Set<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return false;
        }
        if (!tags.containsAll(all)) {
            return false;
        }
        if (!any.isEmpty() && any.stream().noneMatch(tags::contains)) {
            return false;
        }
        return none.stream().noneMatch(tags::contains);
    }
}
