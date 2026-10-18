package dev.otectus.mcaconversations.event;

import java.util.UUID;

/**
 * When each piece of this mod's periodic server work runs (feedback remediation §2.4, WP-D2).
 *
 * <p>Until 1.8.0 every periodic job keyed off {@code tick % 600 == 0}: the gossip sweep, all three
 * store prunes, the rumour sweep, the session sweep and — at its default 30 s — the court poll all
 * landed on the same tick, and one tick in fifteen of the 40-tick greeting scan with them. The work
 * is unchanged; the table below spreads it across the gossip interval so no tick carries all of it.
 * {@code gossipScanIntervalTicks} still sets the period.
 *
 * <pre>
 *   offset in the interval     job
 *   0                          gossip sweep
 *   interval / 8               session sweep
 *   interval / 4               rumour propagation
 *   interval / 2               disposition and progress prunes (a new pass at most once per in-game hour)
 *   3 * interval / 4           history prune (a new pass only when the in-game day changed)
 *   7 (court interval)         court news poll
 *   every tick, by player      greeting scan and Townstead dialogue sweep: one player in forty per tick
 * </pre>
 *
 * <p>Pure arithmetic, so the table is a unit test rather than a profiler session.
 */
public final class MaintenanceSchedule {

    /** The jobs that ride the gossip interval. */
    public enum Job {
        GOSSIP, SESSION_SWEEP, RUMOURS, STORE_PRUNE, HISTORY_PRUNE
    }

    /** Ticks the court poll is offset from the gossip phase. */
    public static final int COURT_OFFSET_TICKS = 7;

    /** The greeting scan's per-player period, and so the number of player slots it is spread over. */
    public static final int PLAYER_SLOTS = 40;

    /** Store records a prune step examines, per villager batch. */
    public static final int STORE_PRUNE_BATCH = 64;

    /** Villagers a history prune step examines. */
    public static final int HISTORY_PRUNE_BATCH = 64;

    /** Loaded residents one gossip sweep observes per village, resuming at the next sweep. */
    public static final int GOSSIP_RESIDENT_BATCH = 128;

    /** Game ticks between the starts of two store prune passes: one in-game hour. */
    public static final long STORE_PASS_MIN_GAP_TICKS = 1000L;

    private MaintenanceSchedule() {
    }

    /** Where in the interval {@code job} runs. */
    public static int offset(Job job, int interval) {
        int period = Math.max(1, interval);
        return switch (job) {
            case GOSSIP -> 0;
            case SESSION_SWEEP -> period / 8;
            case RUMOURS -> period / 4;
            case STORE_PRUNE -> period / 2;
            case HISTORY_PRUNE -> (int) (3L * period / 4L);
        };
    }

    /** True on the ticks {@code job} runs. */
    public static boolean due(Job job, long tick, int interval) {
        int period = Math.max(1, interval);
        return Math.floorMod(tick, period) == offset(job, period);
    }

    /** True on the ticks the court poll runs: its own interval, seven ticks after the gossip phase. */
    public static boolean courtDue(long tick, int courtInterval) {
        int period = Math.max(1, courtInterval);
        return Math.floorMod(tick, period) == COURT_OFFSET_TICKS % period;
    }

    /** True when it is this player's turn among {@link #PLAYER_SLOTS}: each player once per period. */
    public static boolean playerDue(UUID player, long tick) {
        return player != null && Math.floorMod(player.hashCode(), PLAYER_SLOTS) == Math.floorMod(tick, PLAYER_SLOTS);
    }

    /** True when a store prune may begin a new pass, given when the last one began. */
    public static boolean storePassMayStart(long now, long lastStart) {
        return lastStart == Long.MIN_VALUE || now - lastStart >= STORE_PASS_MIN_GAP_TICKS;
    }
}
