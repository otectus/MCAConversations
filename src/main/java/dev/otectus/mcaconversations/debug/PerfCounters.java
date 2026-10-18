package dev.otectus.mcaconversations.debug;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * What this mod's server tick work costs, measured where it runs (feedback remediation WP0, WP-D1).
 *
 * <p>Each section of the tick handler is timed with two {@code System.nanoTime()} calls into a
 * per-tick slot of a fixed ring of {@link #WINDOW_TICKS} ticks, so the statistics describe the last
 * minute of server time, spikes included — a 600-tick sweep that a 100-tick average would smooth away
 * shows up as the window's maximum. Nothing allocates on the tick path, and a section that does not
 * run that tick costs nothing at all.
 *
 * <p>Alongside the timings: running totals of the items the bounded sweeps examined, which is the
 * figure that proves a batch is a batch. Read by {@code /conversations perf status}; reset by
 * {@code perf reset}. Server thread only, written and read there, so plain arrays are enough.
 */
public final class PerfCounters {

    /** Ticks of history the statistics cover: one minute at 20 TPS. */
    public static final int WINDOW_TICKS = 1200;

    /** The timed sections of {@code ConversationsEvents.onServerTick}, in the order they run. */
    public enum Section {
        CHAT_DRAIN, ATTENTION, DISCUSSIONS, GREET_SCAN, TOWNSTEAD_SWEEP, COURT_POLL, GOSSIP_SCAN,
        RUMOURS, PRUNE_DISPOSITION, PRUNE_PROGRESS, PRUNE_HISTORY, SESSION_SWEEP
    }

    /** Running counts of work examined, since the last reset. */
    public enum Count {
        /** Village residents the gossip relationship diff looked at. */
        RESIDENTS_EXAMINED,
        /** Villagers whose stored records a store prune walked. */
        PRUNE_VILLAGERS_EXAMINED,
        /** Villagers whose history the history prune walked. */
        HISTORY_VILLAGERS_EXAMINED,
        /** Townstead state reads made to judge a held villager. */
        TOWNSTEAD_HOLD_READS,
        /** Held-villager judgements served from the cached Townstead read. */
        TOWNSTEAD_HOLD_CACHED
    }

    /** One section over the window. Nanoseconds. */
    public record Stats(long p50, long p95, long max, long total, int ticksRun) {
    }

    /** The server's counters. */
    public static final PerfCounters SERVER = new PerfCounters();

    private final long[][] samples = new long[Section.values().length][WINDOW_TICKS];
    private final long[] counts = new long[Count.values().length];
    private int cursor;
    private long ticks;

    /** A fresh set; production code uses {@link #SERVER}. */
    public PerfCounters() {
    }

    /** The start of a timed section. */
    public static long start() {
        return System.nanoTime();
    }

    /** Adds the time since {@code startedAt} to this tick's slot for {@code section}. */
    public void end(Section section, long startedAt) {
        record(section, System.nanoTime() - startedAt);
    }

    /** Adds {@code nanos} to this tick's slot; the seam the arithmetic tests drive. */
    public void record(Section section, long nanos) {
        if (nanos > 0) {
            samples[section.ordinal()][cursor] += nanos;
        }
    }

    /** Closes this tick and clears the slot the next one will use. */
    public void endTick() {
        cursor = (cursor + 1) % WINDOW_TICKS;
        for (long[] ring : samples) {
            ring[cursor] = 0L;
        }
        ticks++;
    }

    public void count(Count count, long amount) {
        if (amount > 0) {
            counts[count.ordinal()] += amount;
        }
    }

    public long count(Count count) {
        return counts[count.ordinal()];
    }

    /** Ticks observed since the last reset. */
    public long ticks() {
        return ticks;
    }

    /**
     * p50, p95 and max of the per-tick cost over the completed ticks in the window, the total, and how
     * many of those ticks the section ran at all. The tick in progress is not part of the window.
     */
    public Stats stats(Section section) {
        int filled = (int) Math.min(ticks, WINDOW_TICKS - 1L);
        if (filled == 0) {
            return new Stats(0L, 0L, 0L, 0L, 0);
        }
        long[] ring = samples[section.ordinal()];
        long[] window = new long[filled];
        int ran = 0;
        long total = 0L;
        for (int i = 0; i < filled; i++) {
            // The completed ticks, oldest to newest: the slots before the cursor.
            long value = ring[Math.floorMod(cursor - 1 - i, WINDOW_TICKS)];
            window[i] = value;
            total += value;
            if (value > 0) {
                ran++;
            }
        }
        Arrays.sort(window);
        return new Stats(percentile(window, 50), percentile(window, 95), window[filled - 1], total, ran);
    }

    /** Every section's statistics, in tick order. */
    public Map<Section, Stats> all() {
        Map<Section, Stats> out = new EnumMap<>(Section.class);
        for (Section section : Section.values()) {
            out.put(section, stats(section));
        }
        return out;
    }

    /** Nearest-rank percentile of a sorted, non-empty array. */
    static long percentile(long[] sorted, int percent) {
        int rank = (int) Math.ceil(percent / 100.0 * sorted.length);
        return sorted[Math.max(0, Math.min(sorted.length - 1, rank - 1))];
    }

    public void reset() {
        for (long[] ring : samples) {
            Arrays.fill(ring, 0L);
        }
        Arrays.fill(counts, 0L);
        cursor = 0;
        ticks = 0L;
    }
}
