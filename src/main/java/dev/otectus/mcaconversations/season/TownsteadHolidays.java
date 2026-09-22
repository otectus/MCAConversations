package dev.otectus.mcaconversations.season;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * Which festival falls on a Townstead calendar date (Townstead spec §11.3), loaded from
 * {@code data/<namespace>/townstead_holidays/*.json}.
 *
 * <pre>{"holidays": {"midsummer": {"profile": "townstead_calendar:default", "month": 6, "day": 21, "holiday": "midsummer"}}}</pre>
 *
 * <p>Townstead's calendar knows dates and seasons but has no holiday registry, and its months differ
 * per calendar profile, so a mapping is keyed by profile ({@code "*"} for any) and by either
 * {@code month} and {@code day} or {@code day_of_year}, with an optional {@code span} of days. The
 * festival must be one this mod's content already speaks about. An entry for the exact profile
 * outranks a {@code "*"} entry; with no match the answer is {@code none} — an unrelated built-in
 * festival is never laid over a Townstead calendar unless the operator asks for it.
 */
public record TownsteadHolidays(List<Entry> entries) {

    public static final TownsteadHolidays EMPTY = new TownsteadHolidays(List.of());

    /** The festivals content can speak about; the same vocabulary as {@code conversations_holiday}. */
    public static final Set<String> HOLIDAYS = Set.of("spring_bloom", "midsummer", "harvest_festival", "midwinter");

    private static final Set<String> FIELDS = Set.of("profile", "month", "day", "day_of_year", "span", "holiday");

    public record Entry(String id, String profile, int month, int day, int dayOfYear, int span, String holiday) {

        boolean matches(String activeProfile, int m, int d, int doy) {
            if (!"*".equals(profile) && !profile.equals(activeProfile)) {
                return false;
            }
            if (dayOfYear > 0) {
                return doy >= dayOfYear && doy < dayOfYear + span;
            }
            return m == month && d >= day && d < day + span;
        }
    }

    public TownsteadHolidays {
        List<Entry> sorted = new ArrayList<>(entries == null ? List.of() : entries);
        // Exact profiles first, then ids, so a lookup is deterministic whatever order packs load in.
        sorted.sort(Comparator.comparing((Entry e) -> "*".equals(e.profile())).thenComparing(Entry::id));
        entries = List.copyOf(sorted);
    }

    public static Entry fromJson(String id, JsonObject json) {
        for (String key : json.keySet()) {
            if (!FIELDS.contains(key)) {
                throw new IllegalArgumentException("unknown townstead_holidays field: " + key);
            }
        }
        if (!json.has("profile") || json.get("profile").getAsString().isBlank()) {
            throw new IllegalArgumentException("townstead holiday '" + id + "' needs a profile (or \"*\")");
        }
        String profile = json.get("profile").getAsString().trim().toLowerCase(Locale.ROOT);
        String holiday = json.has("holiday") ? json.get("holiday").getAsString().trim().toLowerCase(Locale.ROOT) : "";
        if (!HOLIDAYS.contains(holiday)) {
            throw new IllegalArgumentException("townstead holiday '" + id + "' names unknown festival '" + holiday
                    + "' (spring_bloom, midsummer, harvest_festival, midwinter)");
        }
        boolean byDate = json.has("month") || json.has("day");
        boolean byYear = json.has("day_of_year");
        if (byDate == byYear) {
            throw new IllegalArgumentException("townstead holiday '" + id
                    + "' needs either month and day, or day_of_year");
        }
        int month = byDate ? positive(json, "month", id) : 0;
        int day = byDate ? positive(json, "day", id) : 0;
        int dayOfYear = byYear ? positive(json, "day_of_year", id) : 0;
        int span = json.has("span") ? positive(json, "span", id) : 1;
        return new Entry(id, profile, month, day, dayOfYear, span, holiday);
    }

    /** The festival on this date of this calendar profile, if one is mapped. */
    public Optional<String> holidayFor(String profile, int month, int day, int dayOfYear) {
        String active = profile == null ? "" : profile.toLowerCase(Locale.ROOT);
        for (Entry entry : entries) {
            if (entry.matches(active, month, day, dayOfYear)) {
                return Optional.of(entry.holiday());
            }
        }
        return Optional.empty();
    }

    private static int positive(JsonObject json, String key, String id) {
        if (!json.has(key) || !json.get(key).isJsonPrimitive() || !json.get(key).getAsJsonPrimitive().isNumber()) {
            throw new IllegalArgumentException("townstead holiday '" + id + "' needs a numeric " + key);
        }
        int value = json.get(key).getAsInt();
        if (value < 1) {
            throw new IllegalArgumentException("townstead holiday '" + id + "' " + key + " must be at least 1");
        }
        return value;
    }
}
