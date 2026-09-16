package dev.otectus.mcaconversations.profession;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;

/**
 * The published view of {@code data/<namespace>/profession_profiles/*.json} (spec §6.8).
 *
 * <p>Parsing lives in {@code ContentStaging.professionProfiles}. A profile whose owning mod is absent
 * stays valid and stays loaded — its registry id simply never matches a villager — and the generic
 * runtime fallback below is unchanged, because a profession the catalog has nothing to say about must
 * still be able to hold a conversation.
 */
public final class ProfessionProfileLoader {

    private ProfessionProfileLoader() {
    }

    /** The index of the bundle this operation is running against; empty before the first load. */
    public static ProfessionProfiles active() {
        return ContentOperation.bundle().professions();
    }

    /** Test seam: publish an index without a resource reload. */
    public static void setActiveForTesting(ProfessionProfiles profiles) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withProfessions(profiles == null ? ProfessionProfiles.EMPTY : profiles));
    }

    /** Runtime lookup that must never throw, whatever a datapack did. */
    public static ProfessionProfile profile(String id, String displayFallback) {
        try {
            return active().forId(id, displayFallback);
        } catch (Throwable t) {
            return ProfessionProfile.generic(id == null ? "unknown:unknown" : id,
                    displayFallback == null ? "villager" : displayFallback);
        }
    }
}
