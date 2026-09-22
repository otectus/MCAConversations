package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * {@code conversations_townstead}: one typed comparison against one allow-listed field of a
 * villager's Townstead snapshot (Townstead spec §8.2).
 *
 * <pre>{"source": "villager", "path": "needs.collapsed", "op": "eq", "value": true}</pre>
 *
 * <p>Paths are a closed table, each with a declared type, and the operator is checked against that
 * type when the pack loads: an unknown source, path or operator, or a comparison the field's type
 * cannot support, is a parse error rather than a condition that quietly never matches. There are no
 * regular expressions. At runtime a source Townstead cannot supply scores {@code 0} for every
 * operator; a field that is supplied but empty answers {@code exists} false and {@code missing} true.
 */
public record TownsteadQuery(Source source, Field field, Op op, List<JsonPrimitive> values, boolean allowBare) {

    public enum Source {
        VILLAGER, CALENDAR, BUILDING, ORIGIN, SPIRIT;

        static Source parse(String raw) {
            for (Source source : values()) {
                if (source.name().equalsIgnoreCase(raw)) {
                    return source;
                }
            }
            throw new IllegalArgumentException("unknown conversations_townstead source '" + raw
                    + "' (villager, calendar, building, origin, spirit)");
        }
    }

    public enum Type { INT, NUMBER, BOOL, STRING, SET, MAP }

    public enum Op {
        EXISTS, MISSING, EQ, NE, LT, LTE, GT, GTE, CONTAINS, IN, NOT_IN, MATCHES_ID;

        static Op parse(String raw) {
            for (Op op : values()) {
                if (op.name().equalsIgnoreCase(raw)) {
                    return op;
                }
            }
            throw new IllegalArgumentException("unknown conversations_townstead op '" + raw + "'");
        }
    }

    /** One allow-listed field: its source, dotted path, type, and how it is read from a snapshot. */
    public record Field(Source source, String path, Type type, Function<TownsteadSnapshot, Object> read) {
    }

    private static final Map<String, Field> FIELDS = new LinkedHashMap<>();

    static {
        // --- villager ---------------------------------------------------------------------------
        field(Source.VILLAGER, "needs.hunger", Type.INT, s -> s.villager().needs().hunger());
        field(Source.VILLAGER, "needs.thirst", Type.INT, s -> s.villager().needs().thirst());
        field(Source.VILLAGER, "needs.fatigue", Type.INT, s -> s.villager().needs().fatigue());
        field(Source.VILLAGER, "needs.energy", Type.INT, s -> s.villager().needs().energy());
        field(Source.VILLAGER, "needs.collapsed", Type.BOOL, s -> s.villager().needs().collapsed());
        field(Source.VILLAGER, "needs.in_crisis", Type.BOOL, s -> s.villager().needs().inCrisis());
        field(Source.VILLAGER, "needs.thirst_active", Type.BOOL, s -> s.villager().needs().thirstActive());
        field(Source.VILLAGER, "needs.hunger_state", Type.STRING, s -> s.villager().needs().hungerBucket());
        field(Source.VILLAGER, "needs.thirst_state", Type.STRING, s -> s.villager().needs().thirstBucket());
        field(Source.VILLAGER, "needs.fatigue_state", Type.STRING, s -> s.villager().needs().fatigueBucket());
        field(Source.VILLAGER, "needs.primary", Type.STRING, s -> s.villager().needs().primaryNeed());
        field(Source.VILLAGER, "schedule.activity", Type.STRING, s -> s.villager().schedule().currentActivity());
        field(Source.VILLAGER, "schedule.planned", Type.STRING, s -> s.villager().schedule().plannedActivity());
        field(Source.VILLAGER, "schedule.on_schedule", Type.BOOL, s -> s.villager().schedule().onSchedule());
        field(Source.VILLAGER, "schedule.template", Type.STRING, s -> s.villager().schedule().currentTemplateId());
        field(Source.VILLAGER, "schedule.custom", Type.BOOL, s -> s.villager().schedule().customShifts());
        field(Source.VILLAGER, "schedule.hour", Type.INT, s -> s.villager().schedule().currentDisplayHour());
        field(Source.VILLAGER, "life.root", Type.STRING, s -> s.villager().life().rootId());
        field(Source.VILLAGER, "life.stage", Type.STRING, s -> s.villager().life().lifeStage());
        field(Source.VILLAGER, "life.age_days", Type.INT, s -> s.villager().life().biologicalAgeDays());
        field(Source.VILLAGER, "life.apparent_age", Type.INT, s -> s.villager().life().apparentAgeYears());
        field(Source.VILLAGER, "life.age_description", Type.STRING, s -> s.villager().life().ageDescription());
        field(Source.VILLAGER, "life.senior", Type.BOOL, s -> s.villager().life().senior());
        field(Source.VILLAGER, "life.ageless", Type.BOOL, s -> s.villager().life().ageless());
        field(Source.VILLAGER, "life.immortal", Type.BOOL, s -> s.villager().life().immortal());
        field(Source.VILLAGER, "profession.id", Type.STRING, s -> s.villager().profession().professionId());
        field(Source.VILLAGER, "profession.employed", Type.BOOL, s -> s.villager().profession().employed());
        field(Source.VILLAGER, "profession.level", Type.INT, s -> s.villager().profession().level());
        field(Source.VILLAGER, "profession.xp", Type.INT, s -> s.villager().profession().xp());
        field(Source.VILLAGER, "profession.skills", Type.SET, s -> s.villager().profession().skills());
        field(Source.VILLAGER, "personality.id", Type.STRING, s -> s.villager().personality().id());
        field(Source.VILLAGER, "personality.custom", Type.BOOL, s -> s.villager().personality().custom());
        field(Source.VILLAGER, "personality.base", Type.STRING, s -> s.villager().personality().baseId());
        // Queryable for pack authors, never used by shipped gossip (spec §8.2, §15.2).
        field(Source.VILLAGER, "heritage", Type.MAP, s -> s.villager().heritage());
        field(Source.VILLAGER, "heritage.dominant", Type.STRING,
                s -> s.villager().dominantHeritage().orElse(""));
        field(Source.VILLAGER, "genes.carried", Type.MAP, s -> s.villager().carriedVariants());
        field(Source.VILLAGER, "genes.expressed", Type.SET, s -> Set.copyOf(s.villager().expressedAlleles()));
        // --- calendar ---------------------------------------------------------------------------
        field(Source.CALENDAR, "profile", Type.STRING, s -> s.calendar().profileId());
        field(Source.CALENDAR, "year", Type.INT, s -> s.calendar().year());
        field(Source.CALENDAR, "month", Type.INT, s -> s.calendar().month());
        field(Source.CALENDAR, "day", Type.INT, s -> s.calendar().day());
        field(Source.CALENDAR, "day_of_year", Type.INT, s -> s.calendar().dayOfYear());
        field(Source.CALENDAR, "weekday", Type.INT, s -> s.calendar().dayOfWeek());
        field(Source.CALENDAR, "season", Type.STRING, s -> s.calendar().season());
        field(Source.CALENDAR, "world_day", Type.INT, s -> s.calendar().worldDay());
        // --- building ---------------------------------------------------------------------------
        field(Source.BUILDING, "present", Type.BOOL, s -> s.building().present());
        field(Source.BUILDING, "type", Type.STRING, s -> s.building().present() ? s.building().type() : "");
        field(Source.BUILDING, "family", Type.STRING, s -> s.building().present() ? s.building().family() : "");
        field(Source.BUILDING, "level", Type.INT, s -> s.building().present() ? s.building().level() : 0);
        field(Source.BUILDING, "size", Type.INT, s -> s.building().size());
        // --- origin -----------------------------------------------------------------------------
        field(Source.ORIGIN, "id", Type.STRING, s -> s.origin().id());
        field(Source.ORIGIN, "species", Type.STRING, s -> s.origin().effectiveSpecies());
        field(Source.ORIGIN, "ancestry", Type.STRING, s -> s.origin().ancestry());
        field(Source.ORIGIN, "lineage", Type.STRING, s -> s.origin().lineage());
        // --- spirit -----------------------------------------------------------------------------
        field(Source.SPIRIT, "tier", Type.INT, s -> s.spirit().tier());
        field(Source.SPIRIT, "total", Type.INT, s -> s.spirit().total());
        field(Source.SPIRIT, "buildings", Type.INT, s -> s.spirit().contributingBuildings());
        field(Source.SPIRIT, "classification", Type.STRING, s -> s.spirit().classification());
        field(Source.SPIRIT, "primary", Type.STRING, s -> s.spirit().primaryId());
        field(Source.SPIRIT, "secondary", Type.STRING, s -> s.spirit().secondaryId());
        field(Source.SPIRIT, "points", Type.MAP, s -> s.spirit().perSpirit());
    }

    private static void field(Source source, String path, Type type, Function<TownsteadSnapshot, Object> read) {
        FIELDS.put(key(source, path), new Field(source, path, type, read));
    }

    private static String key(Source source, String path) {
        return source.name() + "|" + path;
    }

    /** Every allow-listed path for a source, for documentation and lint. */
    public static List<Field> fields() {
        return List.copyOf(FIELDS.values());
    }

    public static TownsteadQuery fromJson(JsonElement element) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException("conversations_townstead must be an object");
        }
        JsonObject json = element.getAsJsonObject();
        for (String key : json.keySet()) {
            if (!Set.of("source", "path", "op", "value", "allow_bare").contains(key)) {
                throw new IllegalArgumentException("unknown conversations_townstead field: " + key);
            }
        }
        Source source = Source.parse(string(json, "source"));
        String path = string(json, "path").trim().toLowerCase(Locale.ROOT);
        Field field = FIELDS.get(key(source, path));
        if (field == null) {
            throw new IllegalArgumentException("unknown conversations_townstead path '" + path
                    + "' for source " + source.name().toLowerCase(Locale.ROOT));
        }
        Op op = Op.parse(string(json, "op"));
        List<JsonPrimitive> values = List.of();
        switch (op) {
            case EXISTS, MISSING -> {
                if (json.has("value")) {
                    throw new IllegalArgumentException(op + " takes no value");
                }
            }
            case EQ, NE -> {
                requireType(op, field, Type.INT, Type.NUMBER, Type.BOOL, Type.STRING);
                values = List.of(scalar(json, field.type()));
            }
            case LT, LTE, GT, GTE -> {
                requireType(op, field, Type.INT, Type.NUMBER);
                values = List.of(scalar(json, field.type()));
            }
            case CONTAINS -> {
                requireType(op, field, Type.STRING, Type.SET, Type.MAP);
                values = List.of(scalar(json, Type.STRING));
            }
            case IN, NOT_IN -> {
                requireType(op, field, Type.INT, Type.NUMBER, Type.BOOL, Type.STRING);
                if (!json.has("value") || !json.get("value").isJsonArray() || json.getAsJsonArray("value").isEmpty()) {
                    throw new IllegalArgumentException(op + " needs a non-empty array value");
                }
                values = new java.util.ArrayList<>();
                for (JsonElement item : json.getAsJsonArray("value")) {
                    values.add(coerce(item, field.type()));
                }
                values = List.copyOf(values);
            }
            case MATCHES_ID -> {
                requireType(op, field, Type.STRING);
                values = List.of(scalar(json, Type.STRING));
            }
        }
        boolean allowBare = json.has("allow_bare") && json.get("allow_bare").isJsonPrimitive()
                && json.get("allow_bare").getAsJsonPrimitive().isBoolean() && json.get("allow_bare").getAsBoolean();
        return new TownsteadQuery(source, field, op, values, allowBare);
    }

    /** Evaluates against a snapshot; {@code false} whenever the source cannot be supplied. */
    public boolean matches(TownsteadSnapshot snapshot) {
        if (snapshot == null || !snapshot.live() || !sourcePresent(snapshot)) {
            return false;
        }
        Object value;
        try {
            value = field.read().apply(snapshot);
        } catch (Throwable t) {
            return false;
        }
        boolean present = present(value);
        return switch (op) {
            case EXISTS -> present;
            case MISSING -> !present;
            case EQ -> present && equal(value, values.get(0));
            case NE -> present && !equal(value, values.get(0));
            case LT -> present && compare(value, values.get(0)) < 0;
            case LTE -> present && compare(value, values.get(0)) <= 0;
            case GT -> present && compare(value, values.get(0)) > 0;
            case GTE -> present && compare(value, values.get(0)) >= 0;
            case CONTAINS -> present && contains(value, values.get(0).getAsString());
            case IN -> present && values.stream().anyMatch(v -> equal(value, v));
            case NOT_IN -> present && values.stream().noneMatch(v -> equal(value, v));
            case MATCHES_ID -> present && matchesId(value.toString(), values.get(0).getAsString());
        };
    }

    private boolean sourcePresent(TownsteadSnapshot snapshot) {
        return switch (source) {
            case VILLAGER -> !snapshot.villager().isEmpty();
            case CALENDAR -> !snapshot.calendar().isEmpty();
            // Standing outside any building is still an answer about buildings.
            case BUILDING -> !snapshot.villager().isEmpty() || snapshot.building().present();
            case ORIGIN -> !snapshot.origin().isEmpty();
            case SPIRIT -> !snapshot.spirit().isEmpty();
        };
    }

    private static boolean present(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof String s) {
            return !s.isBlank();
        }
        if (value instanceof Collection<?> c) {
            return !c.isEmpty();
        }
        if (value instanceof Map<?, ?> m) {
            return !m.isEmpty();
        }
        return true;
    }

    private static boolean equal(Object actual, JsonPrimitive expected) {
        if (actual instanceof Boolean b) {
            return expected.isBoolean() && b == expected.getAsBoolean();
        }
        if (actual instanceof Number n) {
            return expected.isNumber() && Double.compare(n.doubleValue(), expected.getAsDouble()) == 0;
        }
        return expected.isString() && normalize(actual.toString()).equals(normalize(expected.getAsString()));
    }

    private static int compare(Object actual, JsonPrimitive expected) {
        return actual instanceof Number n ? Double.compare(n.doubleValue(), expected.getAsDouble()) : 0;
    }

    private static boolean contains(Object actual, String needle) {
        String n = normalize(needle);
        if (actual instanceof Map<?, ?> map) {
            return map.keySet().stream().anyMatch(k -> normalize(String.valueOf(k)).equals(n));
        }
        if (actual instanceof Collection<?> c) {
            return c.stream().anyMatch(item -> normalize(String.valueOf(item)).equals(n));
        }
        return normalize(actual.toString()).contains(n);
    }

    /** Namespace-aware exact match; a bare path matches only when the author allowed it. */
    private boolean matchesId(String actual, String expected) {
        ResourceLocation a = ResourceLocation.tryParse(normalize(actual));
        String e = normalize(expected);
        if (a == null) {
            return false;
        }
        if (e.indexOf(':') < 0) {
            return allowBare && a.getPath().equals(e);
        }
        ResourceLocation b = ResourceLocation.tryParse(e);
        return a.equals(b);
    }

    private static String normalize(String raw) {
        return raw == null ? "" : raw.trim().toLowerCase(Locale.ROOT);
    }

    private static void requireType(Op op, Field field, Type... allowed) {
        for (Type type : allowed) {
            if (field.type() == type) {
                return;
            }
        }
        throw new IllegalArgumentException("op " + op.name().toLowerCase(Locale.ROOT) + " cannot compare "
                + field.path() + " (" + field.type().name().toLowerCase(Locale.ROOT) + ")");
    }

    private static JsonPrimitive scalar(JsonObject json, Type type) {
        if (!json.has("value")) {
            throw new IllegalArgumentException("conversations_townstead needs a value");
        }
        return coerce(json.get("value"), type);
    }

    private static JsonPrimitive coerce(JsonElement element, Type type) {
        if (element == null || !element.isJsonPrimitive()) {
            throw new IllegalArgumentException("conversations_townstead value must be a scalar");
        }
        JsonPrimitive p = element.getAsJsonPrimitive();
        switch (type) {
            case INT, NUMBER -> {
                if (!p.isNumber() || !Double.isFinite(p.getAsDouble())) {
                    throw new IllegalArgumentException("value must be a finite number");
                }
            }
            case BOOL -> {
                if (!p.isBoolean()) {
                    throw new IllegalArgumentException("value must be true or false");
                }
            }
            default -> {
                if (!p.isString()) {
                    throw new IllegalArgumentException("value must be a string");
                }
            }
        }
        return p;
    }

    private static String string(JsonObject json, String key) {
        JsonElement e = json.get(key);
        if (e == null || !e.isJsonPrimitive() || !e.getAsJsonPrimitive().isString() || e.getAsString().isBlank()) {
            throw new IllegalArgumentException("conversations_townstead requires a \"" + key + "\" string");
        }
        return e.getAsString();
    }

    /** Parses a string or array of strings, lower-cased and de-duplicated. */
    static Set<String> strings(JsonElement element, String what) {
        Set<String> out = new LinkedHashSet<>();
        if (element == null) {
            return out;
        }
        if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
            out.add(normalize(element.getAsString()));
            return out;
        }
        if (!element.isJsonArray()) {
            throw new IllegalArgumentException(what + " must be a string or an array of strings");
        }
        for (JsonElement item : (JsonArray) element) {
            if (!item.isJsonPrimitive() || !item.getAsJsonPrimitive().isString() || item.getAsString().isBlank()) {
                throw new IllegalArgumentException(what + " entries must be non-blank strings");
            }
            out.add(normalize(item.getAsString()));
        }
        return out;
    }
}
