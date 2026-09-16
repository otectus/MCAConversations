package dev.otectus.mcaconversations.conversation;

import java.util.List;
import java.util.Optional;

/**
 * The published view of {@code data/<namespace>/conversation_catalog/*.json} (plan §4.5).
 *
 * <p>The parsing moved to {@code ContentStaging.conversationCatalog} and the publication to
 * {@code ContentReloadCoordinator}: a malformed topic is no longer dropped so the rest of the pack
 * can be published over the top of a working catalog, it rejects the reload and leaves the working
 * catalog alone. The sorted-last winner for a duplicate topic id is unchanged.
 */
public final class ConversationCatalogLoader {

    private ConversationCatalogLoader() {
    }

    /** The catalog of the bundle this operation is running against; empty before the first load. */
    public static ConversationCatalog active() {
        return ContentOperation.bundle().topics();
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(ConversationCatalog catalog) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withTopics(catalog == null ? ConversationCatalog.EMPTY : catalog));
    }

    /** Convenience for runtime lookups that must never throw. */
    public static Optional<TopicEntry> topic(String id) {
        try {
            return active().topic(id);
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    /** All topic ids currently loaded, for the debug command. */
    public static List<String> topicIds() {
        return active().topics().stream().map(TopicEntry::id).sorted().toList();
    }
}
