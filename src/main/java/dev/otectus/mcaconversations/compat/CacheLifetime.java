package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The two rules every process-static compat cache in this mod obeys, kept as pure statics so they
 * can be reasoned about, and tested, without a server.
 *
 * <p>A cache entry is a claim about one world at one tick. {@link #isFresh} says when that claim
 * still holds, and {@link #putBounded} keeps the map from becoming a leak on a server that never
 * restarts.
 */
public final class CacheLifetime {

    /** The epoch whose overflow has already been reported, so the diagnostic fires once per world. */
    private static final AtomicLong REPORTED_EPOCH = new AtomicLong(-1L);

    private CacheLifetime() {
    }

    /**
     * Whether an entry may still be served.
     *
     * <p>Three conditions, and all three are needed:
     * <ul>
     *   <li>the entry was written by the server run that is asking, so another world's data can
     *       never answer;</li>
     *   <li>its time-to-live has not run out;</li>
     *   <li>the tick it was written on is not in the future of the tick asking, which is what a
     *       {@code /time set} backwards, or an older world with a lower game time, looks like. Without
     *       it such an entry would sit there apparently fresh for as long as the clock stayed behind.
     * </ul>
     */
    public static boolean isFresh(long entryEpoch, long currentEpoch, long createdTick, long expiryTick,
                                  long now) {
        return entryEpoch == currentEpoch && expiryTick > now && now >= createdTick;
    }

    /**
     * Stores an entry, dropping the whole map first when it has outgrown {@code limit}.
     *
     * <p>Dropping everything rather than sweeping is deliberate: at this size the map is a scan the
     * caller pays for on every read, the entries are cheap to rebuild, and a sweep needs an ordering
     * these maps do not keep. Overflow is routine on a large, long-lived server, so it is DEBUG
     * rather than a warning, and it is reported at most once per {@link ServerEpoch} so a map that
     * sits at the limit cannot fill a log.
     */
    public static <K, V> void putBounded(Map<K, V> map, K key, V value, int limit) {
        if (map.size() > limit) {
            map.clear();
            reportOverflow(limit);
        }
        map.put(key, value);
    }

    private static void reportOverflow(int limit) {
        long epoch = ServerEpoch.current();
        if (REPORTED_EPOCH.getAndSet(epoch) != epoch) {
            McaConversations.LOGGER.debug(
                    "A compat cache passed {} entries and was dropped whole; it will refill on demand.",
                    limit);
        }
    }
}
