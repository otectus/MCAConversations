package dev.otectus.mcaconversations.conversation;

/**
 * The one number that says which body of loaded content is in force (audit finding F12).
 *
 * <p>It is no longer a counter of its own. A generation is now a property of the published
 * {@link ConversationContentBundle}, so it advances if and only if one complete, validated,
 * materialised bundle replaced another. That is the difference the reload coordinator makes: the
 * generation used to be a marker that a reload had happened, and is now evidence that the content
 * behind it is coherent.
 *
 * <p>Offers record the generation they were minted under, and a submission carrying a stale one is
 * refused rather than executed against content that no longer exists. The generation is purely
 * server-side bookkeeping: it is never written to a packet, so the client never sees it and no
 * protocol version turns on it.
 *
 * <p>{@code 0} is the bootstrap value and means "nothing has been published yet"; the first
 * successful publication is {@code 1}.
 */
public final class ContentGeneration {

    private ContentGeneration() {
    }

    /**
     * The generation of the content currently in force — or, inside an operation, of the bundle that
     * operation pinned, so an offer minted halfway through a hub build carries the generation the
     * rest of that build used.
     */
    public static long current() {
        return ContentOperation.bundle().generation();
    }
}
