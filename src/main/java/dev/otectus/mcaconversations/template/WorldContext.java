package dev.otectus.mcaconversations.template;

/**
 * Pure buckets for time-varying world state, keeping the branching logic unit-testable and out of the
 * MCA-facing code. Weather now; season/holiday buckets will join here in a later release. Callers turn
 * a bucket into a lang key ({@code mcaconversations.weather.<bucket>}) or compare it against a
 * {@code conversations_weather} condition target.
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
}
