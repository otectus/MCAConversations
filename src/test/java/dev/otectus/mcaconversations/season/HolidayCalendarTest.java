package dev.otectus.mcaconversations.season;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HolidayCalendarTest {

    private static final int YEAR = 96; // default seasonYearLengthDays; quarter = 24 days.

    @Test
    void springBloomOpensTheYear() {
        assertEquals("spring_bloom", HolidayCalendar.holidayFor(0, YEAR));
        assertEquals("spring_bloom", HolidayCalendar.holidayFor(1, YEAR));
        assertEquals("none", HolidayCalendar.holidayFor(2, YEAR));
    }

    @Test
    void midsummerSitsMidSummerQuarter() {
        assertEquals("midsummer", HolidayCalendar.holidayFor(36, YEAR));
        assertEquals("midsummer", HolidayCalendar.holidayFor(35, YEAR));
        assertEquals("midsummer", HolidayCalendar.holidayFor(37, YEAR));
        assertEquals("none", HolidayCalendar.holidayFor(34, YEAR));
    }

    @Test
    void harvestClosesAutumn() {
        assertEquals("harvest_festival", HolidayCalendar.holidayFor(70, YEAR));
        assertEquals("harvest_festival", HolidayCalendar.holidayFor(71, YEAR));
        assertEquals("none", HolidayCalendar.holidayFor(72, YEAR)); // winter begins
        assertEquals("none", HolidayCalendar.holidayFor(69, YEAR));
    }

    @Test
    void midwinterSitsMidWinterQuarter() {
        assertEquals("midwinter", HolidayCalendar.holidayFor(84, YEAR));
        assertEquals("midwinter", HolidayCalendar.holidayFor(83, YEAR));
        assertEquals("midwinter", HolidayCalendar.holidayFor(85, YEAR));
    }

    @Test
    void ordinaryDaysAreNone() {
        assertEquals("none", HolidayCalendar.holidayFor(10, YEAR));
        assertEquals("none", HolidayCalendar.holidayFor(50, YEAR));
    }

    @Test
    void holidaysRepeatEachYear() {
        // Day 0 of the second year is spring bloom again; day 36 + a year is midsummer again.
        assertEquals("spring_bloom", HolidayCalendar.holidayFor(YEAR, YEAR));
        assertEquals("midsummer", HolidayCalendar.holidayFor(YEAR + 36L, YEAR));
    }

    @Test
    void negativeWorldDayWrapsCleanly() {
        // floorMod keeps a pre-epoch / rewound day positive; day -1 is the last day of the prior year.
        assertEquals(HolidayCalendar.holidayFor(YEAR - 1L, YEAR), HolidayCalendar.holidayFor(-1L, YEAR));
    }

    @Test
    void degenerateYearLengthIsNoneNotCrash() {
        assertEquals("none", HolidayCalendar.holidayFor(5, 0));
        assertEquals("none", HolidayCalendar.holidayFor(5, -12));
    }
}
