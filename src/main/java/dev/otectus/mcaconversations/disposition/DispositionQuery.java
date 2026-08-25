package dev.otectus.mcaconversations.disposition;

import com.google.gson.JsonObject;

/**
 * Parsed form of the {@code conversations_disposition} dialogue condition:
 * {@code {"axis": "trust", "min"?: 35, "max"?: 60}} — matches while the decayed axis value lies in
 * the inclusive range. Bounds default to the axis limits. Parse problems throw so
 * {@link dev.otectus.mcaconversations.util.SafeParse} contains them into a never-matching condition.
 */
public record DispositionQuery(DispositionAxis axis, int min, int max) {

    public static DispositionQuery fromJson(JsonObject json) {
        if (!json.has("axis")) {
            throw new IllegalArgumentException("conversations_disposition requires an \"axis\"");
        }
        String key = json.get("axis").getAsString();
        DispositionAxis axis = DispositionAxis.byKey(key)
                .orElseThrow(() -> new IllegalArgumentException("unknown disposition axis: " + key));
        int min = json.has("min") ? json.get("min").getAsInt() : axis.min();
        int max = json.has("max") ? json.get("max").getAsInt() : axis.max();
        if (min > max) {
            throw new IllegalArgumentException("conversations_disposition min " + min + " > max " + max);
        }
        return new DispositionQuery(axis, min, max);
    }

    public boolean matches(int value) {
        return value >= min && value <= max;
    }
}
