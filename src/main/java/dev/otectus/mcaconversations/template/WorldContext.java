package dev.otectus.mcaconversations.template;

/**
 * Pure buckets for time-varying world state, keeping the branching logic unit-testable and out of the
 * MCA-facing code. Weather and the calendar season fall out here; callers turn a bucket into a lang key
 * ({@code mcaconversations.weather.<bucket>} / {@code mcaconversations.season.<bucket>}) or compare it
 * against a {@code conversations_weather}/{@code conversations_season} condition target. The
 * <em>resolution</em> of these buckets from live world state (and the optional Serene Seasons override)
 * lives in {@code season.SeasonContext}; only the arithmetic is here.
 */
public final class WorldContext {

    private WorldContext() {
    }

    /** Current-sky bucket, most severe first: {@code storm} (thunder) &gt; {@code rain} &gt; {@code clear}. */
    public static String weatherBucket(boolean raining, boolean thundering) {
        if (thundering) {
            return "storm";
        }
        if (raining) {
            return "rain";
        }
        return "clear";
    }

    /**
     * Calendar-season bucket for a world day, splitting the year into four equal quarters starting at
     * spring on day 0: {@code spring} → {@code summer} → {@code autumn} → {@code winter}. Used only when
     * Serene Seasons isn't driving the season (see {@code season.SeasonContext}). A non-positive year
     * length degrades to {@code spring} rather than dividing by zero.
     */
    public static String seasonFromDay(long worldDay, int yearLengthDays) {
        if (yearLengthDays <= 0) {
            return "spring";
        }
        long dayOfYear = Math.floorMod(worldDay, yearLengthDays);
        int quarter = (int) (dayOfYear * 4L / yearLengthDays);
        return switch (quarter) {
            case 0 -> "spring";
            case 1 -> "summer";
            case 2 -> "autumn";
            default -> "winter";
        };
    }
}
