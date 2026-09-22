package dev.otectus.mcaconversations.season;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.SeasonsBridge;
import dev.otectus.mcaconversations.compat.Townstead;
import dev.otectus.mcaconversations.compat.TownsteadCalendarView;
import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.template.WorldContext;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

/**
 * Resolves the live season/holiday buckets for a villager, bridging the pure calendar math
 * ({@link WorldContext#seasonFromDay}, {@link HolidayCalendar#holidayFor}) with the optional Townstead
 * calendar and Serene Seasons override. Shared by the {@code season}/{@code holiday} template variables
 * and the {@code conversations_season}/{@code conversations_holiday} conditions so both agree. Every
 * method fails safe to a calendar value — it runs during MCA dialogue evaluation.
 *
 * <p>Exactly one source answers ({@link CalendarSource}): in {@code AUTO}, Townstead's calendar when it
 * reports a season, then Serene Seasons, then the built-in quarter-split. Townstead may itself be
 * bridging a physical season mod, so asking both would let two lines disagree about the same day.
 */
public final class SeasonContext {

    private SeasonContext() {
    }

    /** Which source answered, and what it said: {@code townstead}, {@code serene_seasons} or {@code builtin}. */
    public record Resolved(String source, String season) {
    }

    /** Current season bucket for the villager, from whichever source {@code calendarSource} selects. */
    public static String seasonBucket(Entity villager) {
        return resolveSeason(villager).season();
    }

    /** The season and the source that supplied it, for {@link #seasonBucket} and the status command. */
    public static Resolved resolveSeason(Entity villager) {
        CalendarSource source = Townstead.calendarSource();
        if (source == CalendarSource.AUTO || source == CalendarSource.TOWNSTEAD) {
            Optional<String> season = seasonBucket(townsteadCalendar(villager));
            if (season.isPresent()) {
                return new Resolved("townstead", season.get());
            }
        }
        if (source == CalendarSource.AUTO || source == CalendarSource.SERENE_SEASONS) {
            Optional<String> season = sereneSeason(villager);
            if (season.isPresent()) {
                return new Resolved("serene_seasons", season.get());
            }
        }
        return new Resolved("builtin", WorldContext.seasonFromDay(McaCompat.getWorldDay(villager), yearLength()));
    }

    /**
     * Current festival bucket for the villager, or {@code none}. With a Townstead calendar in charge
     * the festival comes from the {@code townstead_holidays} mapping for its profile and date; an
     * unmapped date is {@code none} unless the operator opted into the legacy fixed-cycle festivals.
     */
    public static String holidayBucket(Entity villager) {
        CalendarSource source = Townstead.calendarSource();
        if (source == CalendarSource.AUTO || source == CalendarSource.TOWNSTEAD) {
            TownsteadCalendarView calendar = townsteadCalendar(villager);
            if (!calendar.isEmpty()) {
                return holidayFor(calendar, holidays(), Townstead.legacyHolidayFallback()
                        ? HolidayCalendar.holidayFor(McaCompat.getWorldDay(villager), yearLength()) : "none");
            }
        }
        return HolidayCalendar.holidayFor(McaCompat.getWorldDay(villager), yearLength());
    }

    /** The mapped festival for a Townstead date, or {@code unmapped}. Pure, for tests. */
    static String holidayFor(TownsteadCalendarView calendar, TownsteadHolidays holidays, String unmapped) {
        return holidays.holidayFor(calendar.profileId(), calendar.month(), calendar.day(), calendar.dayOfYear())
                .orElse(unmapped);
    }

    /**
     * Townstead's season folded onto this mod's four buckets. Profiles name their seasons freely
     * ({@code early_spring}, {@code fall}); anything that is not recognisably one of the four, or an
     * empty season, lets the fallback chain continue.
     */
    static Optional<String> seasonBucket(TownsteadCalendarView calendar) {
        if (calendar == null || calendar.isEmpty() || calendar.season().isEmpty()) {
            return Optional.empty();
        }
        String season = calendar.season();
        if (season.contains("spring")) {
            return Optional.of("spring");
        }
        if (season.contains("summer")) {
            return Optional.of("summer");
        }
        if (season.contains("autumn") || season.contains("fall")) {
            return Optional.of("autumn");
        }
        if (season.contains("winter")) {
            return Optional.of("winter");
        }
        return Optional.empty();
    }

    private static TownsteadCalendarView townsteadCalendar(Entity villager) {
        try {
            return Townstead.snapshot(villager).calendar();
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead calendar query failed; using the next calendar source", t);
            return TownsteadCalendarView.EMPTY;
        }
    }

    private static Optional<String> sereneSeason(Entity villager) {
        if (villager == null || !SeasonsBridge.isAvailable()) {
            return Optional.empty();
        }
        SeasonsBridge.SeasonQueries q = SeasonsBridge.queries();
        if (q == null) {
            return Optional.empty();
        }
        try {
            return q.seasonBucket(villager.level());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Serene Seasons season query failed; using calendar", t);
            return Optional.empty();
        }
    }

    private static TownsteadHolidays holidays() {
        try {
            return ContentOperation.bundle().townsteadHolidays();
        } catch (Throwable t) {
            return TownsteadHolidays.EMPTY;
        }
    }

    private static int yearLength() {
        return McaConversationsConfig.COMMON.seasonYearLengthDays.get();
    }
}
