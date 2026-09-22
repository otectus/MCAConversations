package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;

/**
 * {@code conversations_townstead_spirit}: what kind of place the villager's village is becoming
 * (Townstead spec §8.4).
 *
 * <pre>{"classification": ["single", "blend"], "min_tier": 2, "primary": "nautical", "min_share": 0.4}</pre>
 *
 * <p>Tiers are Townstead's own computed tier, never re-derived from its point thresholds here.
 * {@code min_points}, {@code max_points}, {@code min_share} and {@code max_share} are about one named
 * spirit: {@code spirit} when given, otherwise {@code primary}; naming neither with one of them is a
 * parse error. Every clause is ANDed, and a village Townstead has no spirit for matches nothing.
 */
public record TownsteadSpiritQuery(Set<String> classification, OptionalInt minTier, OptionalInt maxTier,
                                   Optional<String> primary, Optional<String> secondary,
                                   Optional<String> spirit, OptionalInt minPoints, OptionalInt maxPoints,
                                   OptionalDouble minShare, OptionalDouble maxShare, OptionalInt minBuildings) {

    private static final Set<String> FIELDS = Set.of("classification", "min_tier", "max_tier", "primary",
            "secondary", "spirit", "min_points", "max_points", "min_share", "max_share", "min_buildings");
    private static final Set<String> CLASSIFICATIONS = Set.of("settlement", "single", "blend", "mixed");

    public static TownsteadSpiritQuery fromJson(JsonElement element) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException("conversations_townstead_spirit must be an object");
        }
        JsonObject json = element.getAsJsonObject();
        if (json.size() == 0) {
            throw new IllegalArgumentException("conversations_townstead_spirit needs at least one clause");
        }
        for (String key : json.keySet()) {
            if (!FIELDS.contains(key)) {
                throw new IllegalArgumentException("unknown conversations_townstead_spirit field: " + key);
            }
        }
        Set<String> classification = Set.copyOf(TownsteadQuery.strings(json.get("classification"), "classification"));
        for (String c : classification) {
            if (!CLASSIFICATIONS.contains(c)) {
                throw new IllegalArgumentException("unknown spirit classification: " + c);
            }
        }
        Optional<String> primary = id(json, "primary");
        Optional<String> named = id(json, "spirit").or(() -> primary);
        OptionalInt minPoints = integer(json, "min_points");
        OptionalInt maxPoints = integer(json, "max_points");
        OptionalDouble minShare = share(json, "min_share");
        OptionalDouble maxShare = share(json, "max_share");
        if (named.isEmpty() && (minPoints.isPresent() || maxPoints.isPresent()
                || minShare.isPresent() || maxShare.isPresent())) {
            throw new IllegalArgumentException("points and share clauses need a spirit or a primary");
        }
        return new TownsteadSpiritQuery(classification, integer(json, "min_tier"), integer(json, "max_tier"),
                primary, id(json, "secondary"), named, minPoints, maxPoints, minShare, maxShare,
                integer(json, "min_buildings"));
    }

    public boolean matches(TownsteadSpiritView view) {
        if (view == null || view.isEmpty()) {
            return false;
        }
        if (!classification.isEmpty() && !classification.contains(lower(view.classification()))) {
            return false;
        }
        if (minTier.isPresent() && view.tier() < minTier.getAsInt()
                || maxTier.isPresent() && view.tier() > maxTier.getAsInt()) {
            return false;
        }
        if (primary.isPresent() && !primary.get().equals(lower(view.primaryId()))
                || secondary.isPresent() && !secondary.get().equals(lower(view.secondaryId()))) {
            return false;
        }
        if (spirit.isPresent()) {
            int points = view.pointsFor(spirit.get());
            double share = view.shareOf(spirit.get());
            if (minPoints.isPresent() && points < minPoints.getAsInt()
                    || maxPoints.isPresent() && points > maxPoints.getAsInt()
                    || minShare.isPresent() && share < minShare.getAsDouble()
                    || maxShare.isPresent() && share > maxShare.getAsDouble()) {
                return false;
            }
        }
        return minBuildings.isEmpty() || view.contributingBuildings() >= minBuildings.getAsInt();
    }

    private static String lower(String s) {
        return s == null ? "" : s.toLowerCase(Locale.ROOT);
    }

    private static Optional<String> id(JsonObject json, String key) {
        if (!json.has(key)) {
            return Optional.empty();
        }
        JsonElement e = json.get(key);
        if (!e.isJsonPrimitive() || !e.getAsJsonPrimitive().isString() || e.getAsString().isBlank()) {
            throw new IllegalArgumentException(key + " must be a spirit id");
        }
        return Optional.of(e.getAsString().trim().toLowerCase(Locale.ROOT));
    }

    private static OptionalInt integer(JsonObject json, String key) {
        if (!json.has(key)) {
            return OptionalInt.empty();
        }
        JsonElement e = json.get(key);
        if (!e.isJsonPrimitive() || !e.getAsJsonPrimitive().isNumber()) {
            throw new IllegalArgumentException(key + " must be an integer");
        }
        return OptionalInt.of(e.getAsInt());
    }

    private static OptionalDouble share(JsonObject json, String key) {
        if (!json.has(key)) {
            return OptionalDouble.empty();
        }
        JsonElement e = json.get(key);
        if (!e.isJsonPrimitive() || !e.getAsJsonPrimitive().isNumber()) {
            throw new IllegalArgumentException(key + " must be a number");
        }
        double v = e.getAsDouble();
        if (!Double.isFinite(v) || v < 0.0 || v > 1.0) {
            throw new IllegalArgumentException(key + " must be between 0 and 1");
        }
        return OptionalDouble.of(v);
    }
}
