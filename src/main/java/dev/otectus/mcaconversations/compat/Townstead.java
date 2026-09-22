package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.season.CalendarSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/**
 * The one question every Townstead consumer asks — is it live, and may this feature use it — and the
 * per-villager snapshot they all read from (Townstead spec §5, §7).
 *
 * <p>Always loaded and free of any Townstead type: it only ever talks to {@link TownsteadBridge},
 * whose no-op holder answers empty views when Townstead is absent. Every config read here is
 * never-throw, because conditions are scored from datapack reloads and entity ticks that can run
 * before the common config has loaded.
 */
public final class Townstead {

    /** Snapshots live this many ticks unless a caller asks for longer (chat scans do). */
    private static final int EVALUATION_TICKS = 1;
    /** A bound on the cache, so a village of thousands cannot grow it without limit. */
    private static final int MAX_CACHED = 512;

    private static final Map<UUID, Cached> CACHE = new ConcurrentHashMap<>();
    private static final AtomicLong HITS = new AtomicLong();
    private static final AtomicLong MISSES = new AtomicLong();

    /** Snapshot cache counters since the last {@link #clearCaches()}, for the status command. */
    public record CacheStats(long hits, long misses, int size) {
    }

    private record Cached(long gameTime, int ttl, TownsteadSnapshot snapshot) {
    }

    private Townstead() {
    }

    public static TownsteadBridge bridge() {
        return TownsteadBridge.Holder.get();
    }

    /** Townstead is installed, bound at least partly, and the integration is switched on. */
    public static boolean active() {
        try {
            return bool(() -> McaConversationsConfig.COMMON.townsteadEnabled.get(), true)
                    && bridge().isAvailable();
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean has(TownsteadCapability capability) {
        return active() && bridge().has(capability);
    }

    // --- Feature switches -----------------------------------------------------------------------

    public static boolean contentEnabled() {
        return active() && bool(() -> McaConversationsConfig.COMMON.townsteadContentEnabled.get(), true);
    }

    public static boolean conditionsEnabled() {
        return active() && bool(() -> McaConversationsConfig.COMMON.townsteadContextConditionsEnabled.get(), true);
    }

    public static boolean checkFitEnabled() {
        return active() && bool(() -> McaConversationsConfig.COMMON.townsteadContextCheckFitEnabled.get(), true);
    }

    public static boolean reactionsEnabled() {
        return active() && bool(() -> McaConversationsConfig.COMMON.townsteadReactionsEnabled.get(), true);
    }

    public static boolean emotionEffectsEnabled() {
        return bool(() -> McaConversationsConfig.COMMON.townsteadEmotionEffectsEnabled.get(), true);
    }

    public static boolean scheduleRespectEnabled() {
        return active() && bool(() -> McaConversationsConfig.COMMON.townsteadScheduleRespectEnabled.get(), true);
    }

    public static boolean dialogueTrackingEnabled() {
        return active()
                && bool(() -> McaConversationsConfig.COMMON.townsteadTypedChatDialogueTrackingEnabled.get(), true);
    }

    public static boolean giftNeedObservationEnabled() {
        return active() && bool(() -> McaConversationsConfig.COMMON.townsteadGiftNeedObservationEnabled.get(), true);
    }

    public static boolean gossipEnabled() {
        return active() && bool(() -> McaConversationsConfig.COMMON.townsteadGossipEnabled.get(), true);
    }

    public static boolean customPersonalityProfilesEnabled() {
        return active()
                && bool(() -> McaConversationsConfig.COMMON.townsteadCustomPersonalityProfilesEnabled.get(), true);
    }

    public static CalendarSource calendarSource() {
        try {
            CalendarSource source = McaConversationsConfig.COMMON.calendarSource.get();
            return source == null ? CalendarSource.AUTO : source;
        } catch (Throwable t) {
            return CalendarSource.AUTO;
        }
    }

    public static boolean legacyHolidayFallback() {
        return bool(() -> McaConversationsConfig.COMMON.useLegacyHolidayFallbackWithTownstead.get(), false);
    }

    public static int maxCheckFit() {
        return integer(() -> McaConversationsConfig.COMMON.townsteadMaxCheckFit.get(), 8);
    }

    public static int contextCacheTicks() {
        return Math.max(1, integer(() -> McaConversationsConfig.COMMON.townsteadContextCacheTicks.get(), 20));
    }

    public static int needCrisisCooldownDays() {
        return integer(() -> McaConversationsConfig.COMMON.townsteadNeedCrisisCooldownDays.get(), 2);
    }

    public static int buildingRemovalConfirmScans() {
        return Math.max(1, integer(() -> McaConversationsConfig.COMMON.townsteadBuildingRemovalConfirmScans.get(), 2));
    }

    public static boolean debug() {
        return bool(() -> McaConversationsConfig.COMMON.townsteadDebug.get(), false);
    }

    // --- Snapshots ------------------------------------------------------------------------------

    /** This villager's snapshot for the current evaluation (one tick). */
    public static TownsteadSnapshot snapshot(Entity villager) {
        return snapshot(villager, EVALUATION_TICKS);
    }

    /**
     * This villager's snapshot, reused for up to {@code ttlTicks}. {@link TownsteadSnapshot#EMPTY}
     * when Townstead is not active or there is no villager.
     */
    public static TownsteadSnapshot snapshot(Entity villager, int ttlTicks) {
        if (villager == null || villager.level() == null || !active()) {
            return TownsteadSnapshot.EMPTY;
        }
        long now = villager.level().getGameTime();
        int ttl = Math.max(1, ttlTicks);
        Cached cached = CACHE.get(villager.getUUID());
        if (cached != null && now >= cached.gameTime() && now - cached.gameTime() < Math.min(ttl, cached.ttl())) {
            HITS.incrementAndGet();
            return cached.snapshot();
        }
        MISSES.incrementAndGet();
        TownsteadSnapshot fresh = read(villager);
        if (CACHE.size() >= MAX_CACHED) {
            CACHE.clear();
        }
        CACHE.put(villager.getUUID(), new Cached(now, ttl, fresh));
        return fresh;
    }

    /** Drops every cached snapshot: server stop, config change, or a test. */
    public static void clearCaches() {
        CACHE.clear();
        HITS.set(0L);
        MISSES.set(0L);
    }

    public static CacheStats cacheStats() {
        return new CacheStats(HITS.get(), MISSES.get(), CACHE.size());
    }

    private static TownsteadSnapshot read(Entity villager) {
        TownsteadBridge bridge = bridge();
        Supplier<TownsteadVillagerView> view = () -> bridge.has(TownsteadCapability.READ_VILLAGER)
                ? bridge.villager(villager) : TownsteadVillagerView.EMPTY;
        TownsteadSnapshot[] self = new TownsteadSnapshot[1];
        self[0] = new TownsteadSnapshot(true,
                view,
                () -> bridge.has(TownsteadCapability.READ_CALENDAR) && villager.getServer() != null
                        ? bridge.calendar(villager.getServer()) : TownsteadCalendarView.EMPTY,
                () -> bridge.has(TownsteadCapability.READ_BUILDING) && villager.level() instanceof ServerLevel level
                        ? bridge.buildingAt(level, villager.blockPosition()) : TownsteadBuildingView.EMPTY,
                () -> {
                    if (!bridge.has(TownsteadCapability.READ_ROOT)) {
                        return TownsteadRootView.EMPTY;
                    }
                    ResourceLocation id = ResourceLocation.tryParse(self[0].villager().life().rootId());
                    return id == null ? TownsteadRootView.EMPTY : bridge.root(id);
                },
                () -> {
                    if (!bridge.has(TownsteadCapability.READ_SPIRIT)
                            || !(villager.level() instanceof ServerLevel level)) {
                        return TownsteadSpiritView.EMPTY;
                    }
                    OptionalInt village = McaCompat.getHomeVillageId(villager);
                    return village.isPresent() ? bridge.spiritForVillage(level, village.getAsInt())
                            : TownsteadSpiritView.EMPTY;
                },
                () -> bridge.has(TownsteadCapability.READ_CONTEXT_TAGS) ? bridge.contextTags(villager) : Set.of());
        return self[0];
    }

    private static boolean bool(Supplier<Boolean> read, boolean fallback) {
        try {
            Boolean value = read.get();
            return value == null ? fallback : value;
        } catch (Throwable t) {
            return fallback;
        }
    }

    private static int integer(Supplier<Integer> read, int fallback) {
        try {
            Integer value = read.get();
            return value == null ? fallback : value;
        } catch (Throwable t) {
            return fallback;
        }
    }
}
