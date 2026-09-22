package dev.otectus.mcaconversations.season;

import dev.otectus.mcaconversations.support.TestPaths;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.compat.TownsteadCalendarView;
import dev.otectus.mcaconversations.compat.TownsteadFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The Townstead holiday mapping and calendar precedence (Townstead spec §11.2, §11.3). */
class TownsteadHolidaysTest {

    private static final Path SHIPPED =
            TestPaths.of("src/main/resources/data/mcaconversations/townstead_holidays/townstead_calendar.json");

    private static TownsteadHolidays.Entry entry(String id, String raw) {
        return TownsteadHolidays.fromJson(id, JsonParser.parseString(raw).getAsJsonObject());
    }

    private static TownsteadHolidays shipped() throws Exception {
        JsonObject root = JsonParser.parseString(Files.readString(SHIPPED)).getAsJsonObject();
        JsonObject holidays = root.getAsJsonObject("holidays");
        List<TownsteadHolidays.Entry> entries = new ArrayList<>();
        for (String id : holidays.keySet()) {
            entries.add(TownsteadHolidays.fromJson(id, holidays.getAsJsonObject(id)));
        }
        return new TownsteadHolidays(entries);
    }

    @Test
    @DisplayName("a date maps to its festival for its own profile, over a span of days")
    void mapsDates() {
        TownsteadHolidays holidays = new TownsteadHolidays(List.of(
                entry("a", "{\"profile\": \"townstead_calendar:default\", \"month\": 6, \"day\": 21, \"span\": 2, \"holiday\": \"midsummer\"}"),
                entry("b", "{\"profile\": \"townstead_calendar:ecliptic\", \"day_of_year\": 46, \"holiday\": \"spring_bloom\"}")));
        assertEquals(Optional.of("midsummer"), holidays.holidayFor("townstead_calendar:default", 6, 21, 172));
        assertEquals(Optional.of("midsummer"), holidays.holidayFor("townstead_calendar:default", 6, 22, 173));
        assertEquals(Optional.empty(), holidays.holidayFor("townstead_calendar:default", 6, 23, 174));
        assertEquals(Optional.empty(), holidays.holidayFor("townstead_calendar:serene", 6, 21, 45),
                "a mapping for one calendar never lands on another");
        assertEquals(Optional.of("spring_bloom"), holidays.holidayFor("townstead_calendar:ecliptic", 4, 1, 46));
    }

    @Test
    @DisplayName("an exact profile outranks a wildcard, whatever order the packs loaded in")
    void exactBeatsWildcard() {
        TownsteadHolidays holidays = new TownsteadHolidays(List.of(
                entry("z_any", "{\"profile\": \"*\", \"month\": 1, \"day\": 1, \"holiday\": \"midwinter\"}"),
                entry("a_exact", "{\"profile\": \"mypack:moons\", \"month\": 1, \"day\": 1, \"holiday\": \"spring_bloom\"}")));
        assertEquals(Optional.of("spring_bloom"), holidays.holidayFor("mypack:moons", 1, 1, 1));
        assertEquals(Optional.of("midwinter"), holidays.holidayFor("other:calendar", 1, 1, 1));
    }

    @Test
    @DisplayName("an unknown festival, a missing profile, or both date forms at once is refused")
    void strictParsing() {
        assertThrows(IllegalArgumentException.class,
                () -> entry("x", "{\"profile\": \"*\", \"month\": 1, \"day\": 1, \"holiday\": \"new_year\"}"));
        assertThrows(IllegalArgumentException.class,
                () -> entry("x", "{\"month\": 1, \"day\": 1, \"holiday\": \"midwinter\"}"));
        assertThrows(IllegalArgumentException.class,
                () -> entry("x", "{\"profile\": \"*\", \"month\": 1, \"day\": 1, \"day_of_year\": 1, \"holiday\": \"midwinter\"}"));
        assertThrows(IllegalArgumentException.class,
                () -> entry("x", "{\"profile\": \"*\", \"month\": 0, \"day\": 1, \"holiday\": \"midwinter\"}"));
        assertThrows(IllegalArgumentException.class,
                () -> entry("x", "{\"profile\": \"*\", \"month\": 1, \"day\": 1, \"holiday\": \"midwinter\", \"when\": 3}"));
    }

    @Test
    @DisplayName("every shipped Townstead calendar profile has all four festivals")
    void shippedMappingsCoverEveryProfile() throws Exception {
        TownsteadHolidays holidays = shipped();
        for (String profile : List.of("default", "tfc", "serene", "ecliptic")) {
            List<String> found = new ArrayList<>();
            for (TownsteadHolidays.Entry entry : holidays.entries()) {
                if (entry.profile().equals("townstead_calendar:" + profile)) {
                    found.add(entry.holiday());
                }
            }
            assertEquals(TownsteadHolidays.HOLIDAYS.size(), found.size(), profile + ": " + found);
            assertTrue(found.containsAll(TownsteadHolidays.HOLIDAYS), profile + ": " + found);
        }
        assertEquals(Optional.of("midwinter"), holidays.holidayFor("townstead_calendar:default", 12, 21, 355));
        assertEquals(Optional.of("harvest_festival"), holidays.holidayFor("townstead_calendar:serene", 9, 7, 71));
    }

    @Test
    @DisplayName("an unmapped Townstead date is 'none', not the old fixed-cycle festival")
    void unmappedIsNone() {
        TownsteadCalendarView day = TownsteadFixtures.calendar("mypack:moons", 3, 3, 33, "");
        assertEquals("none", SeasonContext.holidayFor(day, TownsteadHolidays.EMPTY, "none"));
        assertEquals("spring_bloom", SeasonContext.holidayFor(day, TownsteadHolidays.EMPTY, "spring_bloom"),
                "the legacy fallback answers only when the operator opted into it");
    }

    @Test
    @DisplayName("Townstead's season folds onto the four buckets, and an empty season lets the chain continue")
    void seasonBuckets() {
        assertEquals(Optional.of("autumn"),
                SeasonContext.seasonBucket(TownsteadFixtures.calendar("townstead_calendar:serene", 8, 1, 57, "autumn")));
        assertEquals(Optional.of("spring"),
                SeasonContext.seasonBucket(TownsteadFixtures.calendar("x:y", 1, 1, 1, "early_spring")));
        assertEquals(Optional.empty(),
                SeasonContext.seasonBucket(TownsteadFixtures.calendar("townstead_calendar:default", 6, 21, 172, "")),
                "the Gregorian profile has no season of its own unless a season mod supplies one");
        assertEquals(Optional.empty(), SeasonContext.seasonBucket(TownsteadCalendarView.EMPTY));
    }
}
