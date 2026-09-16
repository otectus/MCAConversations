package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.ReputationBridge;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * What this villager personally knows the player for, from MCA: Reputation's public profiles.
 *
 * <p>Everything here comes through {@link ReputationBridge}, so no Reputation type is named and an
 * install without the mod — or with its profile layer switched off — sees exactly one difference:
 * this source's capability line. Every field it declares reads {@code UNAVAILABLE} then, which is
 * what makes a profile-gated scene unselectable rather than selectable-on-a-false.
 *
 * <p><b>Speaker-scoped, and it never widens.</b> The answer is filtered through this villager's own
 * awareness before anything is aggregated, so a deed they have not heard about cannot reach a line
 * they say. A villager who genuinely knows nothing is a real answer — {@code knows_player} false with
 * an unknown tier — and must never be replaced by what the village at large thinks, which is how a
 * stranger ends up greeted as an old friend.
 *
 * <p><b>None of this is warmth.</b> Recognition says how widely a player is known, not how well they
 * are liked: a revered hero and an infamous murderer can carry the same number. Hearts, familiarity
 * and relationship eligibility stay exactly where they were, in MCA and in this mod's own
 * disposition rules.
 */
public final class ReputationContextSource implements ConversationContextSource {

    public static final String ID = "reputation";

    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.STANDING_SPEAKER_KNOWS_PLAYER,
            ContextKeys.STANDING_SPEAKER_RECOGNITION_TIER,
            ContextKeys.STANDING_SPEAKER_KNOWN_FOR);

    @Override
    public String id() {
        return ID;
    }

    @Override
    public List<ContextKey<?>> declares() {
        return DECLARES;
    }

    @Override
    public boolean isAvailable(ContextRequest request) {
        return request.isComplete()
                && McaCompat.isMcaVillager(request.villager())
                && ReputationBridge.isAvailable();
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        if (!request.isComplete() || !McaCompat.isMcaVillager(request.villager())) {
            markUnavailable(builder, "no MCA villager and player pair");
            return;
        }
        if (!ReputationBridge.isAvailable()) {
            markUnavailable(builder, "MCA: Reputation is not installed");
            return;
        }
        try {
            Optional<ReputationBridge.SpeakerProfileView> resolved =
                    ReputationBridge.speakerProfile(request.player(), request.villager());
            if (resolved.isEmpty()) {
                // Profiles off, unpublished, migrating, or a villager whose community cannot be named.
                // "We cannot say" is not "this player is nobody", so nothing is written as a value.
                markUnavailable(builder, "no profile answer for this speaker");
                return;
            }
            ReputationBridge.SpeakerProfileView profile = resolved.get();
            builder.put(ContextKeys.STANDING_SPEAKER_KNOWS_PLAYER, profile.knowsPlayer());
            if (profile.recognitionTierId().isBlank()) {
                // A real answer with no tier named: the villager knows of nothing that would place the
                // player on the ladder. UNKNOWN rather than UNAVAILABLE — the question was answered.
                builder.unknown(ContextKeys.STANDING_SPEAKER_RECOGNITION_TIER);
            } else {
                builder.put(ContextKeys.STANDING_SPEAKER_RECOGNITION_TIER,
                        profile.recognitionTierId());
            }
            builder.put(ContextKeys.STANDING_SPEAKER_KNOWN_FOR, knownFor(profile));
            builder.reportCapability(ContextCapabilities.Status.READY, "");
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("reputation profile context unavailable; those fields go dark", t);
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.FAILED, "profile read failed");
        }
    }

    /**
     * The descriptors a line may draw on, bounded and stable.
     *
     * <p>A set rather than a list because authored conditions ask {@code has}: "does this villager
     * know them for bravery" is the question content wants, and the exact ordering of three ids is
     * not a fact worth invalidating a pinned snapshot over.
     */
    private static Set<String> knownFor(ReputationBridge.SpeakerProfileView profile) {
        Set<String> facets = new LinkedHashSet<>(profile.dominantFacets());
        return Set.copyOf(facets);
    }

    private static void markUnavailable(ContextSnapshotBuilder builder, String why) {
        builder.allUnavailable(DECLARES);
        builder.reportCapability(ContextCapabilities.Status.ABSENT, why);
    }
}
