package dev.otectus.mcaconversations.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldContextTest {

    @Test
    void clearWhenNoPrecipitation() {
        assertEquals("clear", WorldContext.weatherBucket(false, false));
    }

    @Test
    void rainWhenRainingWithoutThunder() {
        assertEquals("rain", WorldContext.weatherBucket(true, false));
    }

    @Test
    void stormTakesPrecedenceOverRain() {
        // A thunderstorm always reports raining too; storm must win.
        assertEquals("storm", WorldContext.weatherBucket(true, true));
        assertEquals("storm", WorldContext.weatherBucket(false, true));
    }

    @Test
    void seasonSplitsTheYearIntoFourQuarters() {
        int year = 96; // quarter = 24 days.
        assertEquals("spring", WorldContext.seasonFromDay(0, year));
        assertEquals("spring", WorldContext.seasonFromDay(23, year));
        assertEquals("summer", WorldContext.seasonFromDay(24, year));
        assertEquals("summer", WorldContext.seasonFromDay(47, year));
        assertEquals("autumn", WorldContext.seasonFromDay(48, year));
        assertEquals("autumn", WorldContext.seasonFromDay(71, year));
        assertEquals("winter", WorldContext.seasonFromDay(72, year));
        assertEquals("winter", WorldContext.seasonFromDay(95, year));
    }

    @Test
    void seasonWrapsAcrossYears() {
        int year = 96;
        assertEquals("spring", WorldContext.seasonFromDay(96, year));   // day 0 of year 2
        assertEquals("winter", WorldContext.seasonFromDay(-1, year));   // last day of prior year
    }

    @Test
    void seasonDegradesToSpringOnBadYearLength() {
        assertEquals("spring", WorldContext.seasonFromDay(50, 0));
        assertEquals("spring", WorldContext.seasonFromDay(50, -4));
    }
}
