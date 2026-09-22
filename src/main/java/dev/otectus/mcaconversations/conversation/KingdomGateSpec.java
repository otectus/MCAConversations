package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Conversations-owned, strict representation of an optional Ultima kingdom gate.
 *
 * <p>This type deliberately does not name an Ultima class. Catalogs must remain parseable when
 * Ultima Kingdoms is not installed, and malformed restrictions must be refused during reload rather
 * than becoming global content. Runtime evaluation is delegated to the optional reflective bridge.
 */
public record KingdomGateSpec(Optional<ResourceLocation> namedGate,
                              String subject,
                              Set<ResourceLocation> include,
                              Set<ResourceLocation> exclude,
                              UnknownPolicy whenUnknown,
                              Optional<UUID> explicitSettlementId,
                              Optional<StandingGate> standing) {

    private static final Set<String> FIELDS = Set.of(
            "gate", "settlement_id", "subject", "include", "exclude", "when_unknown", "standing");
    private static final Set<String> SUBJECTS = Set.of(
            "giver_residence", "giver_origin", "giver_location", "player_location", "explicit_settlement");

    public enum UnknownPolicy {
        DENY,
        ALLOW;

        static UnknownPolicy parse(String value) {
            return switch (value) {
                case "deny" -> DENY;
                case "allow" -> ALLOW;
                default -> throw new IllegalArgumentException("when_unknown must be 'deny' or 'allow'");
            };
        }

        String serializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    public enum StandingScope {
        LOCAL,
        FACTION,
        EFFECTIVE,
        EITHER,
        BOTH;

        static StandingScope parse(String value) {
            try {
                return valueOf(value.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException(
                        "standing.scope must be local, faction, effective, either, or both");
            }
        }

        String serializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    /** An inclusive standing range, evaluated by Ultima Factions in the requested scope. */
    public record StandingGate(StandingScope scope, Optional<ResourceLocation> kingdom,
                               int minimum, int maximum) {
        private static final Set<String> FIELDS = Set.of("scope", "kingdom", "min", "max");

        public StandingGate {
            if (minimum > maximum) {
                throw new IllegalArgumentException("standing.min must not exceed standing.max");
            }
        }

        static StandingGate fromJson(JsonElement element) {
            if (element == null || !element.isJsonObject()) {
                throw new IllegalArgumentException("standing must be an object");
            }
            JsonObject json = element.getAsJsonObject();
            rejectUnknown(json, FIELDS, "standing");
            StandingScope scope = json.has("scope")
                    ? StandingScope.parse(requireString(json, "scope")) : StandingScope.EFFECTIVE;
            Optional<ResourceLocation> kingdom = json.has("kingdom")
                    ? Optional.of(resourceId(requireString(json, "kingdom"), "standing.kingdom"))
                    : Optional.empty();
            int minimum = json.has("min") ? requireInt(json, "min", "standing") : Integer.MIN_VALUE;
            int maximum = json.has("max") ? requireInt(json, "max", "standing") : Integer.MAX_VALUE;
            if (!json.has("min") && !json.has("max")) {
                throw new IllegalArgumentException("standing requires at least one of min or max");
            }
            return new StandingGate(scope, kingdom, minimum, maximum);
        }

        JsonObject toJson() {
            JsonObject json = new JsonObject();
            json.addProperty("scope", scope.serializedName());
            kingdom.ifPresent(id -> json.addProperty("kingdom", id.toString()));
            if (minimum != Integer.MIN_VALUE) json.addProperty("min", minimum);
            if (maximum != Integer.MAX_VALUE) json.addProperty("max", maximum);
            return json;
        }
    }

    public KingdomGateSpec {
        namedGate = namedGate == null ? Optional.empty() : namedGate;
        subject = subject == null ? "giver_residence" : subject;
        include = include == null ? Set.of() : Set.copyOf(include);
        exclude = exclude == null ? Set.of() : Set.copyOf(exclude);
        whenUnknown = whenUnknown == null ? UnknownPolicy.DENY : whenUnknown;
        explicitSettlementId = explicitSettlementId == null ? Optional.empty() : explicitSettlementId;
        standing = standing == null ? Optional.empty() : standing;
        if (!SUBJECTS.contains(subject)) {
            throw new IllegalArgumentException("unknown kingdom subject: " + subject);
        }
        if (namedGate.isPresent() && (!include.isEmpty() || !exclude.isEmpty()
                || !"giver_residence".equals(subject) || whenUnknown != UnknownPolicy.DENY)) {
            throw new IllegalArgumentException("named gate cannot be mixed with inline kingdom predicate fields");
        }
        if (namedGate.isPresent() && standing.isPresent() && standing.get().kingdom().isEmpty()) {
            throw new IllegalArgumentException(
                    "standing.kingdom is required when standing is combined with a named gate");
        }
        if (namedGate.isEmpty()
                && ("explicit_settlement".equals(subject) != explicitSettlementId.isPresent())) {
            throw new IllegalArgumentException(
                    "settlement_id is required exactly when subject is explicit_settlement");
        }
    }

    public static KingdomGateSpec fromJson(JsonElement element) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException("kingdom_gate must be an object");
        }
        JsonObject json = element.getAsJsonObject();
        rejectUnknown(json, FIELDS, "kingdom_gate");
        Optional<ResourceLocation> named = json.has("gate")
                ? Optional.of(resourceId(requireString(json, "gate"), "gate")) : Optional.empty();
        boolean hasSubject = json.has("subject");
        boolean hasInclude = json.has("include");
        boolean hasExclude = json.has("exclude");
        boolean hasUnknown = json.has("when_unknown");
        if (named.isPresent() && (hasSubject || hasInclude || hasExclude || hasUnknown)) {
            throw new IllegalArgumentException("named gate cannot be mixed with inline kingdom predicate fields");
        }
        String subject = hasSubject ? requireString(json, "subject") : "giver_residence";
        Set<ResourceLocation> include = ids(json, "include");
        Set<ResourceLocation> exclude = ids(json, "exclude");
        UnknownPolicy unknown = hasUnknown
                ? UnknownPolicy.parse(requireString(json, "when_unknown")) : UnknownPolicy.DENY;
        Optional<UUID> settlement = json.has("settlement_id")
                ? Optional.of(uuid(requireString(json, "settlement_id"))) : Optional.empty();
        Optional<StandingGate> standing = json.has("standing")
                ? Optional.of(StandingGate.fromJson(json.get("standing"))) : Optional.empty();
        return new KingdomGateSpec(named, subject, include, exclude, unknown, settlement, standing);
    }

    /** The inline predicate shape accepted by KingdomGateApi.parse/testJson. */
    public JsonObject predicateJson() {
        JsonObject json = new JsonObject();
        json.addProperty("subject", subject);
        json.add("include", ids(include));
        json.add("exclude", ids(exclude));
        json.addProperty("when_unknown", whenUnknown.serializedName());
        return json;
    }

    /** Stable catalog/dialogue representation used by generator round trips and reflection. */
    public JsonObject toJson() {
        JsonObject json = namedGate.isPresent() ? new JsonObject() : predicateJson();
        namedGate.ifPresent(id -> json.addProperty("gate", id.toString()));
        explicitSettlementId.ifPresent(id -> json.addProperty("settlement_id", id.toString()));
        standing.ifPresent(value -> json.add("standing", value.toJson()));
        return json;
    }

    /** Fallback when Ultima is absent: only an explicitly authored inline allow may open. */
    public boolean allowsWhenUnavailable() {
        return namedGate.isEmpty() && standing.isEmpty() && whenUnknown == UnknownPolicy.ALLOW;
    }

    private static Set<ResourceLocation> ids(JsonObject json, String field) {
        if (!json.has(field)) return Set.of();
        JsonElement element = json.get(field);
        if (!element.isJsonArray()) throw new IllegalArgumentException(field + " must be an array");
        LinkedHashSet<ResourceLocation> ids = new LinkedHashSet<>();
        int index = 0;
        for (JsonElement value : element.getAsJsonArray()) {
            if (!value.isJsonPrimitive() || !value.getAsJsonPrimitive().isString()) {
                throw new IllegalArgumentException(field + "[" + index + "] must be a resource id string");
            }
            ResourceLocation id = resourceId(value.getAsString(), field + "[" + index + "]");
            if (!ids.add(id)) throw new IllegalArgumentException(field + " contains duplicate id: " + id);
            index++;
        }
        return Set.copyOf(ids);
    }

    private static JsonArray ids(Set<ResourceLocation> ids) {
        JsonArray json = new JsonArray();
        ids.stream().sorted().forEach(id -> json.add(id.toString()));
        return json;
    }

    private static ResourceLocation resourceId(String value, String field) {
        ResourceLocation id = ResourceLocation.tryParse(value);
        if (id == null || !id.toString().equals(value)) {
            throw new IllegalArgumentException(field + " must be a canonical resource id");
        }
        return id;
    }

    private static UUID uuid(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("settlement_id must be a UUID: " + value);
        }
    }

    private static String requireString(JsonObject json, String field) {
        JsonElement element = json.get(field);
        if (element == null || !element.isJsonPrimitive() || !element.getAsJsonPrimitive().isString()) {
            throw new IllegalArgumentException(field + " must be a string");
        }
        return element.getAsString();
    }

    private static int requireInt(JsonObject json, String field, String owner) {
        JsonElement element = json.get(field);
        if (element == null || !element.isJsonPrimitive() || !element.getAsJsonPrimitive().isNumber()) {
            throw new IllegalArgumentException(owner + "." + field + " must be an integer");
        }
        try {
            return Integer.parseInt(element.getAsString());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(owner + "." + field + " must be an integer");
        }
    }

    private static void rejectUnknown(JsonObject json, Set<String> allowed, String owner) {
        for (String field : json.keySet()) {
            if (!allowed.contains(field)) {
                throw new IllegalArgumentException("unknown " + owner + " field: " + field);
            }
        }
    }
}
