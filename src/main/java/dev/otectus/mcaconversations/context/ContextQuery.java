package dev.otectus.mcaconversations.context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;

/**
 * The parsed form of the {@code conversations_context} dialogue condition (spec §10.6).
 *
 * <pre>{@code
 * {"conversations_context": {"field": "weather.state", "is": "rain", "unknown": "fail"}}
 * {"conversations_context": {"field": "player.held_tags", "has": "forge:ingots/iron"}}
 * {"conversations_context": {"field": "player.hearts", "min": 60}}
 * }</pre>
 *
 * <p>One orthogonal condition over a closed field vocabulary, rather than one custom condition per
 * fact — which is what stops the datapack surface growing a new verb every time a scene needs to know
 * something (spec §10.6).
 *
 * <p>An unparseable query is {@link #isValid() invalid} and never matches, so a datapack typo is a
 * dead branch rather than a crashed reload. An <em>unknown field name</em> is treated the same way,
 * because a field the running build does not declare cannot be evaluated honestly.
 */
public record ContextQuery(ContextKey<?> field,
                           Set<String> anyOf,
                           Set<String> hasAll,
                           OptionalDouble min,
                           OptionalDouble max,
                           boolean negate,
                           UnknownPolicy unknown) {

    /** A query that can never match: an unknown field, or JSON that did not parse. */
    public static final ContextQuery INVALID = new ContextQuery(
            null, Set.of(), Set.of(), OptionalDouble.empty(), OptionalDouble.empty(), false, UnknownPolicy.FAIL);

    public ContextQuery {
        anyOf = Set.copyOf(anyOf);
        hasAll = Set.copyOf(hasAll);
        unknown = unknown == null ? UnknownPolicy.FAIL : unknown;
    }

    public boolean isValid() {
        return field != null;
    }

    public static ContextQuery fromJson(JsonObject json) {
        if (json == null || !json.has("field")) {
            return INVALID;
        }
        ContextKey<?> key = ContextKey.byId(json.get("field").getAsString()).orElse(null);
        if (key == null) {
            return INVALID;
        }
        Set<String> anyOf = tokens(json, "is");
        anyOf.addAll(tokens(json, "any_of"));
        Set<String> hasAll = tokens(json, "has");
        hasAll.addAll(tokens(json, "has_all"));

        OptionalDouble min = json.has("min")
                ? OptionalDouble.of(json.get("min").getAsDouble()) : OptionalDouble.empty();
        OptionalDouble max = json.has("max")
                ? OptionalDouble.of(json.get("max").getAsDouble()) : OptionalDouble.empty();
        boolean negate = json.has("not") && json.get("not").getAsBoolean();
        UnknownPolicy policy = json.has("unknown")
                ? UnknownPolicy.byKey(json.get("unknown").getAsString()).orElse(UnknownPolicy.FAIL)
                : UnknownPolicy.FAIL;
        return new ContextQuery(key, anyOf, hasAll, min, max, negate, policy);
    }

    /**
     * Scores this query against a snapshot.
     *
     * <p>{@code negate} inverts a <em>match</em>, never an unknown: "not raining" and "no idea whether
     * it is raining" must stay different answers, so an unknown field always takes its declared policy
     * regardless of the negation flag.
     */
    public boolean matches(ConversationContextSnapshot snapshot) {
        if (!isValid() || snapshot == null) {
            return false;
        }
        ContextValue<?> value = snapshot.get(castKey());
        if (!value.isKnown()) {
            if (unknown == UnknownPolicy.ERROR) {
                throw new IllegalStateException("context field '" + field.id()
                        + "' is " + value.status() + " and its query declares unknown:error");
            }
            return unknown.matchesWhenUnknown();
        }
        boolean matched = test(value.opt().orElse(null));
        return negate != matched;
    }

    @SuppressWarnings("unchecked")
    private ContextKey<Object> castKey() {
        return (ContextKey<Object>) field;
    }

    private boolean test(Object raw) {
        if (raw == null) {
            return false;
        }
        if (!hasAll.isEmpty()) {
            if (!(raw instanceof Collection<?> collection)) {
                return false;
            }
            Set<String> present = new LinkedHashSet<>();
            for (Object item : collection) {
                present.add(String.valueOf(item).toLowerCase(Locale.ROOT));
            }
            if (!present.containsAll(hasAll)) {
                return false;
            }
        }
        if (!anyOf.isEmpty()) {
            if (raw instanceof Collection<?> collection) {
                boolean any = false;
                for (Object item : collection) {
                    if (anyOf.contains(String.valueOf(item).toLowerCase(Locale.ROOT))) {
                        any = true;
                        break;
                    }
                }
                if (!any) {
                    return false;
                }
            } else if (!anyOf.contains(String.valueOf(raw).toLowerCase(Locale.ROOT))) {
                return false;
            }
        }
        if (min.isPresent() || max.isPresent()) {
            Double number = asNumber(raw);
            if (number == null) {
                return false;
            }
            if (min.isPresent() && number < min.getAsDouble()) {
                return false;
            }
            if (max.isPresent() && number > max.getAsDouble()) {
                return false;
            }
        }
        // A query with no clause at all asks only "is this field known?", which is a real question:
        // it is how a scene says "only when we actually observed a workplace".
        return true;
    }

    private static Double asNumber(Object raw) {
        if (raw instanceof Number number) {
            return number.doubleValue();
        }
        if (raw instanceof Boolean bool) {
            return bool ? 1.0 : 0.0;
        }
        if (raw instanceof Collection<?> collection) {
            return (double) collection.size();
        }
        return null;
    }

    private static Set<String> tokens(JsonObject json, String member) {
        Set<String> out = new LinkedHashSet<>();
        if (json == null || !json.has(member)) {
            return out;
        }
        JsonElement element = json.get(member);
        if (element.isJsonPrimitive()) {
            out.add(element.getAsString().trim().toLowerCase(Locale.ROOT));
            return out;
        }
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (JsonElement item : array) {
                out.add(item.getAsString().trim().toLowerCase(Locale.ROOT));
            }
        }
        return out;
    }
}
