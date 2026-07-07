package dev.otectus.mcaconversations.world;

import com.google.gson.JsonObject;

import java.util.Locale;

/**
 * Parsed form of the world-state dialogue conditions ({@code conversations_weather} now;
 * {@code conversations_season}/{@code conversations_holiday} later share this shape):
 * {@code {"is": "rain"}}. The adapter computes the current bucket and calls {@link #matches(String)}.
 * Kept trivial and pure so a datapack typo is a never-matching condition, never a crash.
 */
public record WorldQuery(String is) {

    public static WorldQuery fromJson(JsonObject json) {
        String value = json != null && json.has("is")
                ? json.get("is").getAsString().toLowerCase(Locale.ROOT)
                : "";
        return new WorldQuery(value);
    }

    /** True when {@code bucket} equals the target this query asks for. */
    public boolean matches(String bucket) {
        return is != null && !is.isEmpty() && is.equals(bucket);
    }
}
