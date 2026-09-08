package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The rule that stops a Capitals cache outliving the world that filled it.
 *
 * <p>{@code ReflectiveCapitalsBridge} itself cannot be exercised here — every read needs a live
 * {@code ServerLevel} for its game time, and the unit suite deliberately runs with no server and no
 * Capitals — so the rule it applies lives in {@link CacheLifetime} as two pure statics, and that is
 * what these tests pin. What the bridge does with them is checked by reading its source, which is the
 * same technique {@code CapitalsBridgeTest} uses for the no-import discipline.
 */
class CapitalsCacheLifetimeTest {

    private static final Path BRIDGE_SOURCE = Paths.get("src", "main", "java", "dev", "otectus",
            "mcaconversations", "compat", "capitals", "ReflectiveCapitalsBridge.java");

    // --- freshness ------------------------------------------------------------------------------

    @Test
    void anEntryFromThisWorldInsideItsWindowIsFresh() {
        assertTrue(CacheLifetime.isFresh(4L, 4L, 100L, 300L, 200L));
    }

    @Test
    void anEntryFromAPreviousWorldIsNeverServed() {
        long epoch = ServerEpoch.current();
        // Everything but the epoch says fresh: same tick window, plenty of time left.
        assertTrue(CacheLifetime.isFresh(epoch, epoch, 100L, 300L, 200L));

        long next = ServerEpoch.advance();
        assertFalse(CacheLifetime.isFresh(epoch, next, 100L, 300L, 200L),
                "A world change must invalidate the entry even though its ttl has not run out.");
        assertTrue(CacheLifetime.isFresh(next, next, 100L, 300L, 200L),
                "Entries written after the advance are good again.");
    }

    @Test
    void anExpiredEntryIsNotServed() {
        assertFalse(CacheLifetime.isFresh(1L, 1L, 100L, 300L, 300L),
                "The expiry tick is the first tick the entry is stale on.");
        assertFalse(CacheLifetime.isFresh(1L, 1L, 100L, 300L, 900L));
    }

    @Test
    void anEntryCreatedInTheFutureOfNowIsRejected() {
        // What /time set backwards, or a world whose game time is lower, looks like from here.
        assertFalse(CacheLifetime.isFresh(1L, 1L, 5_000L, 5_200L, 400L),
                "A backward clock must not leave an entry indefinitely fresh.");
    }

    // --- bounding -------------------------------------------------------------------------------

    @Test
    void aMapStopsGrowingAtTheLimit() {
        Map<UUID, String> map = new ConcurrentHashMap<>();
        int limit = 16;

        for (int i = 0; i < 500; i++) {
            CacheLifetime.putBounded(map, UUID.randomUUID(), "entry " + i, limit);
            assertTrue(map.size() <= limit + 1,
                    "The map must never exceed the limit by more than the entry being written.");
        }
        assertFalse(map.isEmpty(), "Dropping the map must not stop it refilling.");
    }

    @Test
    void theLastWriteSurvivesTheDrop() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 40; i++) {
            CacheLifetime.putBounded(map, "k" + i, "v" + i, 4);
        }
        assertEquals("v39", map.get("k39"),
                "The value being stored when the map overflowed must still be readable.");
    }

    // --- what the bridge does with them ---------------------------------------------------------

    @Test
    void bothBridgeCachesAreStampedBoundedAndClearable() throws IOException {
        String source = Files.readString(BRIDGE_SOURCE, StandardCharsets.UTF_8);

        assertTrue(source.contains("private record CachedRecord(long epoch, long createdTick,"),
                "The record cache must carry an epoch and a creation tick, not just an expiry.");
        assertTrue(source.contains("private record CachedResidency(long epoch, long createdTick,"),
                "The residency cache must carry the same stamp.");
        assertEquals(2, countOf(source, "CacheLifetime.putBounded("),
                "Both maps must be written through the bounded store, or one of them is a leak.");
        assertTrue(source.contains("public void clearCaches() {"),
                "The bridge must be able to drop its caches without being rebound.");
        assertTrue(source.contains("residents.clear();") && source.contains("records.clear();"),
                "clearCaches() must empty both maps.");
    }

    /**
     * The clear is only worth anything if something calls it. Checked in the event source rather than
     * by firing the event, which needs a server.
     */
    @Test
    void theServerStopHandlerClearsTheCachesAndMovesTheEpochOn() throws IOException {
        String source = Files.readString(Paths.get("src", "main", "java", "dev", "otectus",
                "mcaconversations", "event", "ConversationsEvents.java"), StandardCharsets.UTF_8);
        int stopped = source.indexOf("public static void onServerStopped(");
        assertTrue(stopped > 0);
        String body = source.substring(stopped, source.indexOf("\n    }", stopped));

        assertTrue(body.contains("CapitalsBridge.Holder.clearCaches()"),
                "A stopping server must drop the Capitals caches beside the other process statics.");
        assertTrue(body.contains("ServerEpoch.advance()"),
                "and move the epoch on, so a late write belongs to no world at all.");
        assertTrue(source.contains("public static void onServerStarted(")
                        && source.indexOf("ServerEpoch.advance()",
                                source.indexOf("public static void onServerStarted(")) > 0,
                "A starting server must claim its own epoch.");
    }

    /** A held bridge that caches nothing still answers the call; the no-op default covers it. */
    @Test
    void clearingIsSafeWithCapitalsAbsent() {
        CapitalsBridge.Holder.clearCaches();
        assertEquals(CapitalsStatus.ABSENT, CapitalsBridge.Holder.get().status(),
                "Clearing caches must not unbind or replace the bridge.");
    }

    private static int countOf(String source, String needle) {
        int count = 0;
        for (int at = source.indexOf(needle); at >= 0; at = source.indexOf(needle, at + 1)) {
            count++;
        }
        return count;
    }
}
