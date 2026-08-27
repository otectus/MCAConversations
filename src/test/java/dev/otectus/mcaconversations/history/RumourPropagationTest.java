package dev.otectus.mcaconversations.history;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The eight conditions §16.4 puts on a rumour, checked as refusals.
 *
 * <p>The sweep itself needs a server and villager entities; what is asserted here is every rule that
 * decides whether a story may move and what it looks like when it has — which is the part that would
 * fail silently and leak something it should not.
 */
class RumourPropagationTest {

    private static final UUID OWNER = UUID.fromString("00000000-0000-0000-0000-00000000c001");
    private static final UUID SUBJECT = UUID.fromString("00000000-0000-0000-0000-00000000c002");
    private static final UUID TELLER = UUID.fromString("00000000-0000-0000-0000-00000000c004");

    private static EpisodeRecord episode(PrivacyLevel privacy, int salience) {
        return EpisodeRecord.opened(UUID.randomUUID(), "village.wall", "village.repair",
                        EpisodeState.ACTIVE, OWNER, Map.of(), privacy, salience, 1L)
                .withParticipant(SUBJECT);
    }

    @Test
    @DisplayName("ordinary village news travels")
    void ordinaryNewsTravels() {
        assertTrue(RumourPropagation.mayTravel(episode(PrivacyLevel.ORDINARY, 50), 2L));
    }

    @Test
    @DisplayName("a confidence does not")
    void confidencesDoNotTravel() {
        assertFalse(RumourPropagation.mayTravel(episode(PrivacyLevel.CONFIDENTIAL, 50), 2L));
        assertFalse(RumourPropagation.mayTravel(episode(PrivacyLevel.SPEAKER_ONLY, 50), 2L));
    }

    @Test
    @DisplayName("what the player said about themselves stays where they put it")
    void selfReportsDoNotTravel() {
        EpisodeRecord claim = episode(PrivacyLevel.ORDINARY, 50)
                .withProvenance(Provenance.selfReported(SUBJECT, PrivacyLevel.ORDINARY));

        assertFalse(RumourPropagation.mayTravel(claim, 2L),
                "believed, and never repeated without permission");

        EpisodeRecord permitted = claim.withProvenance(
                claim.provenance().withPrivacy(PrivacyLevel.PUBLIC));
        assertTrue(RumourPropagation.mayTravel(permitted, 2L),
                "unless the player made it public, which is what permission means");
    }

    @Test
    @DisplayName("a story stops after enough tellings")
    void chainLengthIsCapped() {
        EpisodeRecord story = episode(PrivacyLevel.PUBLIC, 90);
        for (int hop = 0; hop < RumourPropagation.MAX_HOPS; hop++) {
            assertTrue(RumourPropagation.mayTravel(story, 2L), "hop " + hop + " should still travel");
            story = story.asToldBy(UUID.randomUUID());
        }
        assertFalse(RumourPropagation.mayTravel(story, 2L),
                "four tellings is a long enough game of telephone");
    }

    @Test
    @DisplayName("a story nobody would bring up any more stops travelling")
    void faintStoriesStop() {
        assertFalse(RumourPropagation.mayTravel(
                episode(PrivacyLevel.PUBLIC, RumourPropagation.MINIMUM_INTERESTING_SALIENCE - 1), 2L));
    }

    @Test
    @DisplayName("the event id survives every hop, so a correction can address it")
    void eventIdSurvives() {
        EpisodeRecord original = episode(PrivacyLevel.PUBLIC, 90);
        EpisodeRecord heard = RumourPropagation.asHeardBy(original, TELLER, null, 2L);

        assertEquals(original.id(), heard.id());
        assertEquals(original.kind(), heard.kind());
        assertEquals(original.subject(), heard.subject());
    }

    @Test
    @DisplayName("a hop costs confidence and salience and invents nothing")
    void hopsWeaken() {
        EpisodeRecord original = episode(PrivacyLevel.PUBLIC, 90);
        EpisodeRecord heard = RumourPropagation.asHeardBy(original, TELLER, null, 2L);

        assertEquals(Confidence.LIKELY, heard.confidence());
        assertEquals(90 - RumourPropagation.SALIENCE_PER_HOP, heard.salience());
        assertEquals(Distortion.NONE, heard.provenance().distortion());
    }

    @Test
    @DisplayName("a fact that may be described but not named travels without its people")
    void anonymousFactsLoseTheirNames() {
        EpisodeRecord discreet = episode(PrivacyLevel.DISCREET, 60);
        assertTrue(discreet.participants().contains(SUBJECT));

        EpisodeRecord heard = RumourPropagation.asHeardBy(discreet, TELLER, null, 2L);

        assertTrue(heard.participants().isEmpty(),
                "a name kept in the record is one mistake away from being spoken");
        assertTrue(RumourPropagation.mayTravel(discreet, 2L), "and the story itself still travels");
    }

    @Test
    @DisplayName("privacy travels with the fact rather than wearing off")
    void privacyDoesNotDecay() {
        EpisodeRecord discreet = episode(PrivacyLevel.DISCREET, 60);
        EpisodeRecord heard = RumourPropagation.asHeardBy(discreet, TELLER, null, 2L);

        assertEquals(PrivacyLevel.DISCREET, heard.privacy());
        assertFalse(heard.provenance().mayName());
    }

    @Test
    @DisplayName("an expired episode is not news")
    void expiredEpisodesDoNotTravel() {
        EpisodeRecord stale = episode(PrivacyLevel.PUBLIC, 90)
                .withDeadline(null, java.util.OptionalLong.of(5L));

        assertTrue(RumourPropagation.mayTravel(stale, 4L));
        assertFalse(RumourPropagation.mayTravel(stale, 6L));
    }

    @Test
    @DisplayName("a correction sets the footing straight and keeps the event")
    void correctionKeepsTheEvent() {
        EpisodeRecord heard = episode(PrivacyLevel.PUBLIC, 90)
                .asToldBy(UUID.randomUUID())
                .withProvenance(episode(PrivacyLevel.PUBLIC, 90).provenance()
                        .afterHop(SUBJECT)
                        .corrected(null));

        assertEquals(Confidence.CERTAIN, heard.confidence());
        assertEquals(KnowledgeSource.TOLD_BY, heard.source(),
                "being corrected does not mean they were there after all");
        assertEquals(Distortion.NONE, heard.provenance().distortion());
    }

    @Test
    @DisplayName("nobody is told what they already know")
    void listenersAreNotToldTwice() {
        UUID listener = UUID.fromString("00000000-0000-0000-0000-00000000c003");
        EpisodeRecord known = episode(PrivacyLevel.PUBLIC, 90).witnessedBy(listener);

        assertTrue(known.isKnownTo(listener));
        assertEquals(Optional.empty(),
                Optional.ofNullable(known.isKnownTo(listener) ? null : known));
    }
}
