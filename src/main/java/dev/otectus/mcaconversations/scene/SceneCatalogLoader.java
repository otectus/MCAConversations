package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;

/**
 * The published view of {@code data/<namespace>/conversation_scenes/*.json} (spec §10.4, §22.3).
 *
 * <p>Parsing lives in {@code ContentStaging.scenes}. An index overflow used to be logged after the
 * catalog had already been published, which meant a shipped scene could quietly lose its place; it is
 * a staging refusal now, so the previous catalog stays in force instead.
 */
public final class SceneCatalogLoader {

    private SceneCatalogLoader() {
    }

    /** The catalog of the bundle this operation is running against; empty before the first load. */
    public static SceneCatalog active() {
        return ContentOperation.bundle().scenes();
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(SceneCatalog catalog) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withScenes(catalog == null ? SceneCatalog.EMPTY : catalog));
    }
}
