package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;

/**
 * The published view of the three narrative template directories (spec Appendix A).
 *
 * <p>Three directories, one catalog — and now genuinely one publication. They used to be three
 * listeners writing into one process-static staging area, each calling {@code publish()} over
 * whatever the other two happened to hold at that moment, so one reload could publish three times and
 * a failure in the last listener left the first two's contents live. The three are sections of one
 * staged attempt in {@code ContentStaging.narrative} and build once.
 */
public final class NarrativeCatalogLoader {

    private NarrativeCatalogLoader() {
    }

    /** The catalog of the bundle this operation is running against; empty before the first load. */
    public static NarrativeCatalog active() {
        return ContentOperation.bundle().narrative();
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(NarrativeCatalog catalog) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withNarrative(catalog == null ? NarrativeCatalog.EMPTY : catalog));
    }
}
