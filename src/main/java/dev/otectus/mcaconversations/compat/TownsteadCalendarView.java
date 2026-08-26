package dev.otectus.mcaconversations.compat;

/**
 * The server's Townstead calendar (Townstead spec 5.2). One per server rather than per villager, so
 * the evaluation cache holds a single instance for a whole pass.
 */
public record TownsteadCalendarView(
        String profileId,
        long worldDay,
        int epochYearOffset,
        String timeMode,
        int year,
        int month,
        int day,
        int dayOfYear,
        int dayOfWeek,
        String season) {

    public static final TownsteadCalendarView EMPTY =
            new TownsteadCalendarView("", 0L, 0, "", 0, 0, 0, 0, 0, "");

    /** True when Townstead is not supplying a calendar, so the season fallback chain continues. */
    public boolean isEmpty() {
        return profileId.isEmpty();
    }
}
