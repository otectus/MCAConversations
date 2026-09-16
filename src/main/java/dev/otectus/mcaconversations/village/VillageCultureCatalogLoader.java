package dev.otectus.mcaconversations.village;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;

/**
 * The published view of {@code data/<namespace>/village_culture/*.json} (spec §22.3).
 *
 * <p>Parsing lives in {@code ContentStaging.villageCulture}. Aliases matter more here than almost
 * anywhere else: a village's culture is stored as six token ids, so renaming a token without an alias
 * would leave every village that drew it holding a name nothing answers to. A malformed token used to
 * be dropped silently; it now refuses the reload, which is what keeps those stored ids resolvable.
 */
public final class VillageCultureCatalogLoader {

    private VillageCultureCatalogLoader() {
    }

    /** The catalog of the bundle this operation is running against; empty before the first load. */
    public static VillageCultureCatalog active() {
        return ContentOperation.bundle().culture();
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(VillageCultureCatalog catalog) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withCulture(catalog == null ? VillageCultureCatalog.EMPTY : catalog));
    }
}
