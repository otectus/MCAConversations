package dev.otectus.mcaconversations.season;

/**
 * Pure calendar of villager "festival days", derived from the world day so it needs no external mod.
 * Each holiday occupies a short window anchored to a point in the four-quarter year (see
 * {@code WorldContext.seasonFromDay}); every other day is {@code none}. Kept pure and total — an
 * out-of-range or degenerate year length collapses to {@code none} rather than throwing, so a bad config
 * value can never crash dialogue evaluation. Callers turn the bucket into a lang key
 * ({@code mcaconversations.holiday.<bucket>}) or match it against a {@code conversations_holiday} target.
 *
 * <p>Holidays are always calendar-based (vanilla world day), independent of any Serene Seasons override
 * of the <em>season</em>, so a festival lands on the same in-game date regardless of installed mods.
 */
public final class HolidayCalendar {

    private HolidayCalendar() {
    }

    /**
     * The festival on {@code worldDay}, or {@code none}. Anchors, for a year split into four quarters of
     * {@code q = yearLengthDays / 4} days: {@code spring_bloom} on the first two days of the year,
     * {@code midsummer} around the middle of summer, {@code harvest_festival} on the last two days of
     * autumn, {@code midwinter} around the middle of winter.
     */
    public static String holidayFor(long worldDay, int yearLengthDays) {
        if (yearLengthDays <= 0) {
            return "none";
        }
        long dayOfYear = Math.floorMod(worldDay, yearLengthDays);
        long q = Math.max(1L, yearLengthDays / 4L);

        // Spring bloom — the opening days of the year (start of spring).
        if (dayOfYear <= 1L) {
            return "spring_bloom";
        }
        // Midsummer — middle of the summer quarter.
        if (Math.abs(dayOfYear - (q + q / 2L)) <= 1L) {
            return "midsummer";
        }
        // Harvest festival — the closing days of the autumn quarter.
        if (dayOfYear >= 3L * q - 2L && dayOfYear < 3L * q) {
            return "harvest_festival";
        }
        // Midwinter — middle of the winter quarter.
        if (Math.abs(dayOfYear - (3L * q + q / 2L)) <= 1L) {
            return "midwinter";
        }
        return "none";
    }
}
