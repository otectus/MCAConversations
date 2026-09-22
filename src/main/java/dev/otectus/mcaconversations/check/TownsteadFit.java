package dev.otectus.mcaconversations.check;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * An authored, Townstead-aware nudge on one dialogue check (Townstead spec §9.1).
 *
 * <pre>"townstead_fit": {"good_if_any": ["hungry", "tired"], "bad_if_any": ["raid_active"], "good": 4, "bad": -8}</pre>
 *
 * <p>Read from Townstead's own context tags. It is <b>exactly 0</b> without Townstead, with the fit
 * switched off, or on a check that does not declare one, and it is clamped to
 * {@code townstead.maxCheckFit} — below the fifteen-point tier margin — so the villager's situation
 * can colour a borderline exchange without ever deciding one. It never touches the seeded roll.
 */
public record TownsteadFit(Set<String> goodIfAny, Set<String> badIfAny, int good, int bad) {

    public static TownsteadFit fromJson(JsonElement element) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException("townstead_fit must be an object");
        }
        JsonObject json = element.getAsJsonObject();
        for (String key : json.keySet()) {
            if (!Set.of("good_if_any", "bad_if_any", "good", "bad").contains(key)) {
                throw new IllegalArgumentException("unknown townstead_fit field: " + key);
            }
        }
        Set<String> good = tags(json.get("good_if_any"), "good_if_any");
        Set<String> bad = tags(json.get("bad_if_any"), "bad_if_any");
        if (good.isEmpty() && bad.isEmpty()) {
            throw new IllegalArgumentException("townstead_fit needs good_if_any or bad_if_any");
        }
        int goodPoints = json.has("good") ? json.get("good").getAsInt() : 0;
        int badPoints = json.has("bad") ? json.get("bad").getAsInt() : 0;
        if (goodPoints < 0 || badPoints > 0) {
            throw new IllegalArgumentException("townstead_fit good must be >= 0 and bad <= 0");
        }
        if (!good.isEmpty() && goodPoints == 0 || !bad.isEmpty() && badPoints == 0) {
            throw new IllegalArgumentException("townstead_fit names tags without the points they are worth");
        }
        return new TownsteadFit(Set.copyOf(good), Set.copyOf(bad), goodPoints, badPoints);
    }

    /** The term for these tags, clamped to {@code ±cap}; 0 for an empty tag set. */
    public int score(Set<String> tags, int cap) {
        if (tags == null || tags.isEmpty() || cap <= 0) {
            return 0;
        }
        int total = 0;
        if (goodIfAny.stream().anyMatch(tags::contains)) {
            total += good;
        }
        if (badIfAny.stream().anyMatch(tags::contains)) {
            total += bad;
        }
        return Math.max(-cap, Math.min(cap, total));
    }

    private static Set<String> tags(JsonElement element, String what) {
        Set<String> out = new LinkedHashSet<>();
        if (element == null) {
            return out;
        }
        if (!element.isJsonArray()) {
            throw new IllegalArgumentException(what + " must be an array of tags");
        }
        for (JsonElement item : element.getAsJsonArray()) {
            if (!item.isJsonPrimitive() || item.getAsString().isBlank()) {
                throw new IllegalArgumentException(what + " entries must be non-blank tags");
            }
            out.add(item.getAsString().trim().toLowerCase(Locale.ROOT));
        }
        return out;
    }
}
