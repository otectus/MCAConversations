package dev.otectus.mcaconversations.conversation;

import java.util.Optional;

/**
 * The published view of {@code data/<namespace>/conversation_beats/*.json} (spec §6.8).
 *
 * <p>Beats and replies were always two sections of one directory; they are now two sections of one
 * staged attempt as well, so a route collision among the beats can no longer leave the replies half
 * updated. Parsing lives in {@code ContentStaging.beatContracts}.
 */
public final class BeatContractLoader {

    private BeatContractLoader() {
    }

    /** The catalog of the bundle this operation is running against; empty before the first load. */
    public static BeatCatalog active() {
        return ContentOperation.bundle().beats();
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(BeatCatalog catalog) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withBeats(catalog == null ? BeatCatalog.EMPTY : catalog));
    }

    /** Runtime lookup that must never throw, whatever a datapack did. */
    public static Optional<BeatContract> beatForRoute(String say, String next) {
        if (say == null || next == null) {
            return Optional.empty();
        }
        try {
            return active().forRoute(say, next);
        } catch (Throwable t) {
            return Optional.empty();
        }
    }
}
