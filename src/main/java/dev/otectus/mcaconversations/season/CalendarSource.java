package dev.otectus.mcaconversations.season;

import java.util.Locale;
import java.util.Optional;

/**
 * Which mod decides what season it is, and what today's date is called (Townstead spec 11.2).
 *
 * <p>Conversations has had two season sources for a while: Serene Seasons when it is installed, and
 * a built-in quarter-split of the world day otherwise. Townstead adds a third, and it is different
 * in kind: Townstead has a real calendar with months, weekdays and a year, and it may itself already
 * be bridging a physical season mod. Asking both would produce lines that contradict each other, so
 * exactly one source answers and the rest are not consulted.
 */
public enum CalendarSource {

    /** Townstead when its integration is healthy, then Serene Seasons, then the built-in fallback. */
    AUTO,

    /** Townstead only. Falls back to the built-in calendar when Townstead is absent. */
    TOWNSTEAD,

    /** Serene Seasons only. Falls back to the built-in calendar when it is absent. */
    SERENE_SEASONS,

    /** Always the built-in quarter-split of the world day, whatever else is installed. */
    BUILTIN;

    /** Accepts the constant name in either case; anything unparseable degrades to {@link #AUTO}. */
    public static CalendarSource byKeyOrAuto(String raw) {
        return byKey(raw).orElse(AUTO);
    }

    public static Optional<CalendarSource> byKey(String raw) {
        if (raw == null) {
            return Optional.empty();
        }
        for (CalendarSource source : values()) {
            if (source.name().equalsIgnoreCase(raw.trim())) {
                return Optional.of(source);
            }
        }
        return Optional.empty();
    }

    public String key() {
        return name().toLowerCase(Locale.ROOT);
    }
}
