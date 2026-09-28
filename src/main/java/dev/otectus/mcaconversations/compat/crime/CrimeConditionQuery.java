package dev.otectus.mcaconversations.compat.crime;

import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * Parsed args for the {@code conversations_crime_*} dialogue conditions (1.8.0):
 * {@code conversations_crime_wanted: {"wanted": true}}, {@code conversations_crime_band: {"band": "outlaw"}},
 * {@code conversations_crime_jailed: {"jailed": true}}, {@code conversations_crime_heat: {"min": 25}}.
 *
 * <p>No {@code dev.otectus.mcacrime.*} imports, so it is safe to reference from the MCA-importing
 * registrar on an install without Crime. An unknown band or a negative minimum throws, so
 * {@code SafeParse.orNull} degrades the condition to never-matching rather than crashing MCA's
 * containment-free {@code Dialogues} loader.
 */
public record CrimeConditionQuery(Optional<Boolean> flag, Optional<String> band, long min) {

    public static final Set<String> BANDS = Set.of("lawful", "neutral", "outlaw");

    public static CrimeConditionQuery flag(JsonObject json, String field) {
        boolean value = !json.has(field) || json.get(field).getAsBoolean();
        return new CrimeConditionQuery(Optional.of(value), Optional.empty(), 0L);
    }

    public static CrimeConditionQuery band(JsonObject json) {
        if (!json.has("band")) {
            throw new IllegalArgumentException("conversations_crime_band: 'band' is required");
        }
        String raw = json.get("band").getAsString().toLowerCase(Locale.ROOT);
        if (!BANDS.contains(raw)) {
            throw new IllegalArgumentException("conversations_crime_band: unknown band '" + raw + "', expected one of " + BANDS);
        }
        return new CrimeConditionQuery(Optional.empty(), Optional.of(raw), 0L);
    }

    public static CrimeConditionQuery heat(JsonObject json) {
        long min = json.has("min") ? json.get("min").getAsLong() : 1L;
        if (min < 0L) {
            throw new IllegalArgumentException("conversations_crime_heat: min must be >= 0, was " + min);
        }
        return new CrimeConditionQuery(Optional.empty(), Optional.empty(), min);
    }
}
