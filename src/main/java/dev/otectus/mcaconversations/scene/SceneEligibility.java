package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.context.ContextKeys;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.context.ContextQuery;
import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.identity.ProfileQuery;
import dev.otectus.mcaconversations.identity.VillagerIdentityRecord;
import dev.otectus.mcaconversations.profession.ProfessionProfile;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Stage two and three of the candidate pipeline: the hard gates (spec §9.1).
 *
 * <p>Every check here returns a <b>reason</b> rather than a boolean, because the trace has to name the
 * first decisive one. And every check here is a gate rather than a weight, which is the rule that
 * makes the whole scoring model trustworthy: §9.2 is explicit that a hard ineligibility is never
 * represented by a large negative number, since a large enough bonus elsewhere would then put an
 * ineligible scene on screen.
 *
 * <p>The order is chosen so the cheapest and most selective checks run first — profession before
 * context queries, context queries before slot binding — so the expensive work happens only for
 * candidates that could actually win.
 */
public final class SceneEligibility {

    /** Passed as the reason when a candidate survives every gate. */
    public static final String ELIGIBLE = "";

    private SceneEligibility() {
    }

    /**
     * Runs every gate except slot binding, which the director does last because it needs the level.
     *
     * @return {@link #ELIGIBLE} when the candidate survives, or the first decisive reason it did not
     */
    public static String check(SceneDefinition scene,
                               ConversationContextSnapshot snapshot,
                               ProfessionProfile profile,
                               Optional<VillagerIdentityRecord> identity,
                               Optional<EpisodeRecord> episode,
                               Predicate<String> integrationPresent,
                               long today) {
        if (scene == null) {
            return "no scene";
        }
        // --- Profession, archetype, subject -------------------------------------------------------
        if (!scene.professions().isEmpty()) {
            String professionId = snapshot.value(ContextKeys.WORK_PROFESSION_ID).orElse(null);
            if (professionId == null || !scene.professions().contains(professionId)) {
                return "profession " + (professionId == null ? "unknown" : professionId)
                        + " not in " + scene.professions();
            }
        }
        if (!scene.archetypes().isEmpty()
                && (profile == null || !scene.archetypes().contains(profile.archetype().key()))) {
            return "archetype not in " + scene.archetypes();
        }
        if (!scene.subjectsAny().isEmpty()) {
            boolean any = false;
            if (profile != null) {
                for (String subject : scene.subjectsAny()) {
                    if (profile.subjects().contains(subject)) {
                        any = true;
                        break;
                    }
                }
            }
            if (!any) {
                return "profession declares none of the subjects " + scene.subjectsAny();
            }
        }

        // --- Age and relationship ------------------------------------------------------------------
        if (!scene.ages().isEmpty()) {
            String age = snapshot.value(ContextKeys.SPEAKER_AGE).orElse(null);
            if (age == null || !scene.ages().contains(age)) {
                return "age " + (age == null ? "unknown" : age) + " not in " + scene.ages();
            }
        }
        if (!scene.relationships().isEmpty()) {
            String bandKey = snapshot.value(ContextKeys.PLAYER_RELATIONSHIP_BAND).orElse(null);
            RelationshipBand band = bandKey == null ? null : RelationshipBand.byKey(bandKey).orElse(null);
            if (band == null || !scene.relationships().contains(band)) {
                return "relationship " + (bandKey == null ? "unknown" : bandKey)
                        + " not in " + scene.relationships();
            }
        }

        // --- Optional integrations -----------------------------------------------------------------
        for (String integration : scene.integrations()) {
            if (integrationPresent == null || !integrationPresent.test(integration)) {
                return "integration '" + integration + "' absent";
            }
        }

        // --- Episode ---------------------------------------------------------------------------------
        if (scene.needsEpisode()) {
            if (episode.isEmpty()) {
                return "no live episode of kind '" + scene.episodeKind() + "'";
            }
            EpisodeRecord record = episode.get();
            if (!record.kind().equals(scene.episodeKind())) {
                return "episode kind '" + record.kind() + "' is not '" + scene.episodeKind() + "'";
            }
            if (!scene.episodeStates().isEmpty() && !scene.episodeStates().contains(record.state())) {
                return "episode state '" + record.state().key() + "' not in " + scene.episodeStates();
            }
            if (record.hasExpired(today)) {
                return "episode expired on day " + record.expiresDay().orElse(today);
            }
        }

        // --- Context and identity conditions --------------------------------------------------------
        for (ContextQuery query : scene.contextConditions()) {
            if (!query.matches(snapshot)) {
                return "context condition on '" + query.field().id() + "' not satisfied";
            }
        }
        for (ProfileQuery query : scene.profileConditions()) {
            if (!query.matches(identity.orElse(null))) {
                return "identity condition not satisfied"
                        + query.family().map(family -> " (" + family.key() + ")").orElse("");
            }
        }
        return ELIGIBLE;
    }

    /**
     * The recency gate, kept separate because it is the one hard check derived from history rather
     * than from the world.
     *
     * <p>A scene inside its authored cooldown, or past its weekly mention cap, is <b>ineligible</b>
     * rather than merely penalised — those two numbers are the author saying "not again yet", and a
     * score is the wrong instrument for a statement that absolute (spec §24.5).
     */
    public static String checkRecency(SceneDefinition scene, long daysSinceScene, int mentionsThisWeek) {
        if (scene == null) {
            return "no scene";
        }
        if (daysSinceScene < scene.cooldownDays()) {
            return "scene cooldown: " + daysSinceScene + " of " + scene.cooldownDays() + " days";
        }
        if (mentionsThisWeek >= scene.maxMentionsPerWeek()) {
            return "weekly mention cap reached (" + scene.maxMentionsPerWeek() + ")";
        }
        return ELIGIBLE;
    }
}
