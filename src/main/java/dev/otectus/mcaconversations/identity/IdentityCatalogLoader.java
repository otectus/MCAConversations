package dev.otectus.mcaconversations.identity;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;

/**
 * The published view of {@code data/<namespace>/identity_tokens/*.json} (spec §22.3).
 *
 * <p>Parsing lives in {@code ContentStaging.identityTokens}. Aliases are how a token is renamed
 * without rerolling anybody: an existing profile keeps the string it was generated with, and the
 * catalog resolves it forward (spec §6.4). An incomplete catalog is still not fatal — identity simply
 * stays off rather than producing half a profile — so it stays a warning rather than a refusal.
 */
public final class IdentityCatalogLoader {

    private IdentityCatalogLoader() {
    }

    /** The catalog of the bundle this operation is running against; empty before the first load. */
    public static IdentityCatalog active() {
        return ContentOperation.bundle().identity();
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(IdentityCatalog catalog) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withIdentity(catalog == null ? IdentityCatalog.EMPTY : catalog));
    }
}
