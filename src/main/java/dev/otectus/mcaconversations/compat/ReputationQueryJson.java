package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Parses the two reputation dialogue conditions' JSON (spec §30.2), in pure Gson and Java types so it
 * stays on the always-loaded side of {@link ReputationBridge}'s classloading gate.
 *
 * <p>Both conditions are registered <b>unconditionally</b>, with or without MCA: Reputation installed.
 * That is deliberate: dialogue JSON referencing an unregistered key is an unknown-key error, so a
 * datapack written for the full suite would break an MCA-only install. Registered and scoring zero,
 * it simply never matches, and the pack's authored fallback branch fires — which is what §30.2 means
 * by "return 0 so authored disabled-context fallbacks fire".
 *
 * <p>Malformed supplied filters reject the whole query. The registrar wraps these parsers in
 * {@code SafeParse}, so an invalid condition scores zero without abandoning the conversation or
 * silently broadening the query into an unrelated standing or incident match.
 */
public final class ReputationQueryJson {

    private ReputationQueryJson() {
    }

    /**
     * <pre>{@code
     * { "min": 75, "max": 299, "min_tier": "friend", "max_tier": "honored",
     *   "has_title": "mcareputation:village_guardian" }
     * }</pre>
     */
    public static ReputationBridge.StandingQuery standing(JsonObject json) {
        Integer min = optionalInt(json, "min");
        Integer max = optionalInt(json, "max");
        if (min != null && max != null && min > max) {
            throw new IllegalArgumentException("Reputation min exceeds max");
        }
        return new ReputationBridge.StandingQuery(
                min,
                max,
                optionalString(json, "min_tier"),
                optionalString(json, "max_tier"),
                optionalString(json, "has_title"));
    }

    /**
     * <pre>{@code
     * { "types": ["mcareputation:villager_assaulted"], "statuses": ["active", "apologized"],
     *   "tags": ["crime"], "known_to_speaker": true, "max_age": 168000 }
     * }</pre>
     */
    public static ReputationBridge.IncidentQuery incident(JsonObject json) {
        return new ReputationBridge.IncidentQuery(
                stringList(json, "types"),
                lowerList(json, "statuses"),
                lowerList(json, "tags"),
                optionalBoolean(json, "known_to_speaker"),
                nonnegativeLong(json, "max_age"));
    }

    private static Integer optionalInt(JsonObject json, String key) {
        return json.has(key) ? scalar(json.get(key), key).getAsBigDecimal().intValueExact() : null;
    }

    private static long nonnegativeLong(JsonObject json, String key) {
        if (!json.has(key)) {
            return 0L;
        }
        long value = scalar(json.get(key), key).getAsBigDecimal().longValueExact();
        if (value < 0L) {
            throw new IllegalArgumentException(key + " must not be negative");
        }
        return value;
    }

    private static boolean optionalBoolean(JsonObject json, String key) {
        if (!json.has(key)) {
            return false;
        }
        var value = scalar(json.get(key), key);
        if (!value.isBoolean()) {
            throw new IllegalArgumentException(key + " must be a boolean");
        }
        return value.getAsBoolean();
    }

    private static com.google.gson.JsonPrimitive scalar(JsonElement element, String key) {
        if (element == null || !element.isJsonPrimitive()) {
            throw new IllegalArgumentException(key + " must be a scalar");
        }
        return element.getAsJsonPrimitive();
    }

    private static String text(JsonElement element, String key) {
        var value = scalar(element, key);
        if (!value.isString() || value.getAsString().isBlank()) {
            throw new IllegalArgumentException(key + " must be a nonblank string");
        }
        return value.getAsString().trim();
    }

    private static String optionalString(JsonObject json, String key) {
        return json.has(key) ? text(json.get(key), key) : null;
    }

    private static List<String> stringList(JsonObject json, String key) {
        if (!json.has(key)) {
            return List.of();
        }
        List<String> out = new ArrayList<>();
        JsonElement element = json.get(key);
        if (element.isJsonArray()) {
            for (JsonElement entry : element.getAsJsonArray()) {
                out.add(text(entry, key));
            }
        } else {
            // Keep the documented shorthand, but never discard an invalid list member.
            out.add(text(element, key));
        }
        return List.copyOf(out);
    }

    private static List<String> lowerList(JsonObject json, String key) {
        return stringList(json, key).stream().map(value -> value.toLowerCase(Locale.ROOT)).toList();
    }
}
