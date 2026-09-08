package dev.otectus.mcaconversations.conversation;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The one number that says which body of loaded content is in force (audit finding F12). Every
 * datapack reload publishes exactly one generation, bumped once by {@link ContentGenerationListener}
 * after the last catalog listener has applied — so nothing observes half a reload, where the topic
 * catalog is new and the scenes are still old.
 *
 * <p>Offers record the generation they were minted under, and a submission carrying a stale one is
 * refused rather than executed against content that no longer exists. The generation is purely
 * server-side bookkeeping: it is never written to a packet, so the client never sees it and no
 * protocol version turns on it.
 *
 * <p>Starts at 1, so an offer minted before any reload is still comparable against the live value.
 */
public final class ContentGeneration {

    private static final AtomicLong CURRENT = new AtomicLong(1L);

    private ContentGeneration() {
    }

    /** The generation of the content currently in force. */
    public static long current() {
        return CURRENT.get();
    }

    /** Publishes the next generation. Only the terminal reload listener calls this. */
    static long advance() {
        return CURRENT.incrementAndGet();
    }
}
