package dev.otectus.mcaconversations.debug;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The ring statistics behind {@code /conversations perf status} (feedback remediation WP-D1). */
class PerfCountersTest {

    @Test
    void percentilesAndMaxDescribeTheCompletedTicks() {
        PerfCounters perf = new PerfCounters();
        for (int tick = 1; tick <= 100; tick++) {
            perf.record(PerfCounters.Section.GOSSIP_SCAN, tick * 1_000L);
            perf.endTick();
        }
        PerfCounters.Stats stats = perf.stats(PerfCounters.Section.GOSSIP_SCAN);
        assertEquals(50_000L, stats.p50());
        assertEquals(95_000L, stats.p95());
        assertEquals(100_000L, stats.max());
        assertEquals(5_050_000L, stats.total());
        assertEquals(100, stats.ticksRun());
    }

    @Test
    void aPeriodicSpikeIsTheMaxNotTheMedian() {
        PerfCounters perf = new PerfCounters();
        // The window is the last WINDOW_TICKS - 1 completed ticks, so both spikes have to fall inside it.
        for (int tick = 0; tick < 1_200; tick++) {
            if (tick % 600 == 599) {
                perf.record(PerfCounters.Section.PRUNE_HISTORY, 9_000_000L);
            }
            perf.endTick();
        }
        PerfCounters.Stats stats = perf.stats(PerfCounters.Section.PRUNE_HISTORY);
        assertEquals(0L, stats.p50());
        assertEquals(9_000_000L, stats.max());
        assertEquals(2, stats.ticksRun());
    }

    @Test
    void theWindowForgetsOldTicksAndResetForgetsEverything() {
        PerfCounters perf = new PerfCounters();
        perf.record(PerfCounters.Section.ATTENTION, 5_000_000L);
        perf.endTick();
        for (int tick = 0; tick < PerfCounters.WINDOW_TICKS; tick++) {
            perf.record(PerfCounters.Section.ATTENTION, 10L);
            perf.endTick();
        }
        assertEquals(10L, perf.stats(PerfCounters.Section.ATTENTION).max(), "the spike has left the window");
        perf.count(PerfCounters.Count.RESIDENTS_EXAMINED, 128);
        assertEquals(128L, perf.count(PerfCounters.Count.RESIDENTS_EXAMINED));
        perf.reset();
        assertEquals(0L, perf.count(PerfCounters.Count.RESIDENTS_EXAMINED));
        assertEquals(0L, perf.stats(PerfCounters.Section.ATTENTION).max());
        assertEquals(0L, perf.ticks());
    }

    @Test
    void nearestRankPercentile() {
        assertEquals(3L, PerfCounters.percentile(new long[] {1, 2, 3, 4}, 50) + 1L);
        assertEquals(4L, PerfCounters.percentile(new long[] {1, 2, 3, 4}, 95));
        assertEquals(7L, PerfCounters.percentile(new long[] {7}, 50));
    }
}
