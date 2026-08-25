package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonArray;
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
 * <p>Parsing is total: a malformed field is dropped rather than throwing, because these run inside MCA
 * dialogue evaluation where an exception would abandon the conversation the player is having.
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
        return new ReputationBridge.StandingQuery(
                optionalInt(json, "min"),
                optionalInt(json, "max"),
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
                json.has("known_to_speaker") && json.get("known_to_speaker").getAsBoolean(),
                json.has("max_age") ? Math.max(0L, json.get("max_age").getAsLong()) : 0L);
    }

    private static Integer optionalInt(JsonObject json, String key) {
        try {
            return json.has(key) ? json.get(key).getAsInt() : null;
        } catch (RuntimeException e) {
            return null;
        }
    }

    private static String optionalString(JsonObject json, String key) {
        try {
            if (!json.has(key)) {
                return null;
            }
            String value = json.get(key).getAsString();
            return value == null || value.isBlank() ? null : value;
        } catch (RuntimeException e) {
            return null;
        }
    }

    private static List<String> stringList(JsonObject json, String key) {
        List<String> out = new ArrayList<>();
        if (!json.has(key)) {
            return out;
        }
        JsonElement element = json.get(key);
        try {
            if (element.isJsonArray()) {
                JsonArray array = element.getAsJsonArray();
                for (JsonElement entry : array) {
                    String value = entry.getAsString();
                    if (value != null && !value.isBlank()) {
                        out.add(value);
                    }
                }
            } else {
                // A bare string where a list is expected is a common and harmless authoring habit.
                String value = element.getAsString();
                if (value != null && !value.isBlank()) {
                    out.add(value);
                }
            }
        } catch (RuntimeException ignored) {
            // one malformed entry never costs the whole condition
        }
        return out;
    }

    private static List<String> lowerList(JsonObject json, String key) {
        return stringList(json, key).stream().map(value -> value.toLowerCase(Locale.ROOT)).toList();
    }
}
