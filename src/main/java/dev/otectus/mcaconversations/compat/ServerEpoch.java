package dev.otectus.mcaconversations.compat;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A monotonic counter that increments each time a server starts in this JVM; caches stamp entries
 * with it so data from a previous world can never be served.
 *
 * <p>The problem it solves is singleplayer: leaving a world and opening another reuses the process,
 * so a process-static cache keyed by uuid and validated against the game time survives the world
 * that filled it, and the second world's first read can answer from the first world's data. Clearing
 * on stop is the main defence; the epoch is what makes the defence total, because an entry written
 * after the clear but before the next start matches neither the epoch it was stamped with nor the
 * one that follows.
 */
public final class ServerEpoch {

    private static final AtomicLong CURRENT = new AtomicLong();

    private ServerEpoch() {
    }

    /** The epoch entries written now must be stamped with, and later read back under. */
    public static long current() {
        return CURRENT.get();
    }

    /** Moves to the next epoch, invalidating every entry stamped with the previous one. */
    public static long advance() {
        return CURRENT.incrementAndGet();
    }
}
