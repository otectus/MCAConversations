package dev.otectus.mcaconversations.conversation;

import java.util.function.Supplier;

/**
 * The bundle one planning or execution operation runs against, pinned for its whole duration.
 *
 * <p>A reload commits between two server-thread tasks, and a single operation — choosing a scene,
 * building a hub, executing an answer — reads the catalogs, the scenes, the beats and the narrative
 * templates through a dozen nested helpers. Without a pin, a commit landing halfway through means
 * that operation planned against one body of content and executed against another. Pinning the
 * bundle on entry makes "one operation, one bundle" true for the nested calls as well, without
 * threading a parameter through every helper signature in the mod.
 *
 * <p>The pin is deliberately thread-confined and not inheritable: a scheduled reply or any other
 * background work has no ambient bundle and reads the committed one, which is what it should do. It
 * is opened on the server thread, around work that finishes on the server thread.
 *
 * <p>Nesting is a no-op by design. An inner {@code open} inside an operation keeps the outer pin, so
 * a helper that is itself an entry point elsewhere cannot silently re-read newer content.
 */
public final class ContentOperation implements AutoCloseable {

    private static final ThreadLocal<ContentOperation> CURRENT = new ThreadLocal<>();

    private final ConversationContentBundle bundle;
    private final ContentOperation parent;
    private final boolean owns;
    private boolean closed;

    private ContentOperation(ConversationContentBundle bundle, ContentOperation parent, boolean owns) {
        this.bundle = bundle;
        this.parent = parent;
        this.owns = owns;
    }

    /**
     * Captures the committed bundle for this operation, or joins the one already pinned on this
     * thread. Always close it, in a {@code finally} or a try-with-resources.
     */
    public static ContentOperation open() {
        ContentOperation existing = CURRENT.get();
        if (existing != null) {
            return new ContentOperation(existing.bundle, existing, false);
        }
        ContentOperation opened = new ContentOperation(ContentReloadCoordinator.committed(), null, true);
        CURRENT.set(opened);
        return opened;
    }

    /** Captures a specific bundle — the one a session or a deferred job was stamped with. */
    public static ContentOperation pin(ConversationContentBundle pinned) {
        if (pinned == null) {
            return open();
        }
        ContentOperation existing = CURRENT.get();
        ContentOperation opened = new ContentOperation(pinned, existing, true);
        CURRENT.set(opened);
        return opened;
    }

    /** The bundle this thread is operating against: the pinned one, or the committed one. */
    public static ConversationContentBundle bundle() {
        ContentOperation current = CURRENT.get();
        return current == null ? ContentReloadCoordinator.committed() : current.bundle;
    }

    /** The pinned bundle, or null when this thread is not inside an operation. */
    public static ConversationContentBundle pinnedOrNull() {
        ContentOperation current = CURRENT.get();
        return current == null ? null : current.bundle;
    }

    /** Runs {@code body} against one captured bundle. */
    public static <T> T in(Supplier<T> body) {
        try (ContentOperation ignored = open()) {
            return body.get();
        }
    }

    /** Runs {@code body} against one captured bundle. */
    public static void run(Runnable body) {
        try (ContentOperation ignored = open()) {
            body.run();
        }
    }

    /** Runs {@code body} against a bundle captured earlier — a session's, or a queued job's. */
    public static <T> T in(ConversationContentBundle pinned, Supplier<T> body) {
        try (ContentOperation ignored = pin(pinned)) {
            return body.get();
        }
    }

    /** Runs {@code body} against a bundle captured earlier — a session's, or a queued job's. */
    public static void run(ConversationContentBundle pinned, Runnable body) {
        try (ContentOperation ignored = pin(pinned)) {
            body.run();
        }
    }

    /** The bundle this operation captured. */
    public ConversationContentBundle captured() {
        return bundle;
    }

    @Override
    public void close() {
        if (closed) {
            return;
        }
        closed = true;
        if (!owns) {
            return;
        }
        if (parent == null) {
            CURRENT.remove();
        } else {
            CURRENT.set(parent);
        }
    }
}
