package dev.otectus.mcaconversations.season;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.SeasonsBridge;
import dev.otectus.mcaconversations.template.WorldContext;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

/**
 * Resolves the live season/holiday buckets for a villager, bridging the pure calendar math
 * ({@link WorldContext#seasonFromDay}, {@link HolidayCalendar#holidayFor}) with the optional Serene
 * Seasons override. Shared by the {@code season}/{@code holiday} template variables and the
 * {@code conversations_season}/{@code conversations_holiday} conditions so both agree. Every method fails
 * safe to a calendar value — it runs during MCA dialogue evaluation.
 */
public final class SeasonContext {

    private SeasonContext() {
    }

    /** Current season bucket for the villager: Serene Seasons when it's driving, else the calendar season. */
    public static String seasonBucket(Entity villager) {
        if (villager != null && SeasonsBridge.isAvailable()) {
            SeasonsBridge.SeasonQueries q = SeasonsBridge.queries();
            if (q != null) {
                try {
                    Optional<String> s = q.seasonBucket(villager.level());
                    if (s.isPresent()) {
                        return s.get();
                    }
                } catch (Throwable t) {
                    McaConversations.LOGGER.debug("Serene Seasons season query failed; using calendar", t);
                }
            }
        }
        return WorldContext.seasonFromDay(McaCompat.getWorldDay(villager), yearLength());
    }

    /** Current festival bucket for the villager (always calendar-based), or {@code none}. */
    public static String holidayBucket(Entity villager) {
        return HolidayCalendar.holidayFor(McaCompat.getWorldDay(villager), yearLength());
    }

    private static int yearLength() {
        return McaConversationsConfig.COMMON.seasonYearLengthDays.get();
    }
}
