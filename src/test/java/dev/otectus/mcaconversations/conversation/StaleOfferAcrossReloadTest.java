package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What happens to an offer a player is looking at when a reload lands underneath it.
 *
 * <p>Two outcomes, and they are opposites on purpose. A <b>rejected</b> reload changed nothing, so
 * the buttons on screen still describe content that exists and the click must go through — flushing
 * offers on every failed reload would punish the player for somebody else's typo. A <b>successful</b>
 * one replaced the content the buttons were written from, so the click must be refused as
 * {@code CONTENT_RELOADED} and execute nothing.
 */
class StaleOfferAcrossReloadTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("player".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("villager".getBytes());
    private static final String QUESTION = "conversations.cat.chitchat";

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @BeforeEach
    void reset() {
        ConversationSessions.clearAllForTesting();
    }

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
        ConversationSessions.clearAllForTesting();
    }

    private static ConversationSession.ChoiceOffer offer() {
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);
        return ConversationSessions.recordOffer(PLAYER, VILLAGER, QUESTION, List.of("yes", "no"),
                ConversationSession.Frontend.GUI, 100);
    }

    @Test
    @DisplayName("a rejected reload leaves an unanswered offer usable")
    void aRejectedReloadLeavesTheOfferUsable() {
        ConversationSession.ChoiceOffer made = offer();
        long generation = ContentGeneration.current();

        // A rejected reload publishes nothing, so the generation does not move — which is exactly
        // what makes the offer still valid.
        assertEquals(generation, ContentGeneration.current());

        assertEquals(ChoiceOutcome.CONSUMED,
                ConversationGuard.evaluate(PLAYER, VILLAGER, QUESTION, "yes", false, 101),
                "nothing changed underneath the player, so nothing is refused");
        assertEquals(ChoiceOutcome.CONSUMED,
                ConversationGuard.preflight(PLAYER, QUESTION, 101),
                "and the preflight lets it through too");
        assertEquals(made.generation(), generation);
    }

    @Test
    @DisplayName("a successful replacement refuses the native submission and closes the topic safely")
    void aSuccessfulReplacementRefusesTheNativeSubmission() {
        offer();

        ContentReloadCoordinator.advanceGenerationForTesting();

        assertEquals(ChoiceOutcome.CONTENT_RELOADED,
                ConversationGuard.evaluate(PLAYER, VILLAGER, QUESTION, "yes", false, 101));
        ConversationSession session = ConversationSessions.raw(PLAYER).orElseThrow();
        assertTrue(session.currentOffer().isEmpty(), "the refused offer is gone, not re-answerable");
        assertTrue(session.topicId().isEmpty(), "the topic is closed safely, not left half-open");
    }

    @Test
    @DisplayName("the stale-offer preflight refuses before any content lookup can run")
    void thePreflightRefusesBeforeAnyContentLookup() {
        offer();

        ContentReloadCoordinator.advanceGenerationForTesting();

        assertEquals(ChoiceOutcome.CONTENT_RELOADED,
                ConversationGuard.preflight(PLAYER, QUESTION, 101),
                "the constraint check and the age gate both resolve a question name against live content;"
                        + " asking them about an offer already known to be stale is the bug");
        ConversationSession session = ConversationSessions.raw(PLAYER).orElseThrow();
        assertTrue(session.currentOffer().isEmpty());
    }

    @Test
    @DisplayName("the preflight has nothing to say about a native MCA question")
    void thePreflightIgnoresNativeQuestions() {
        offer();
        ContentReloadCoordinator.advanceGenerationForTesting();

        assertEquals(ChoiceOutcome.CONSUMED, ConversationGuard.preflight(PLAYER, "main", 101),
                "MCA's own names are not this guard's to judge, before or after a reload");
        assertFalse(ConversationSessions.raw(PLAYER).orElseThrow().currentOffer().isEmpty(),
                "and the owned offer is untouched by a submission that was never ours");
    }

    @Test
    @DisplayName("an offer minted inside an operation carries that operation's generation, not a newer one")
    void offersMintedInAnOperationCarryItsGeneration() {
        try (ContentOperation ignored = ContentOperation.open()) {
            long pinned = ContentGeneration.current();
            ContentReloadCoordinator.advanceGenerationForTesting();

            ConversationSession.ChoiceOffer made = offer();

            assertEquals(pinned, made.generation(),
                    "minting must use the operation's generation, not a fresh global read");
        }
    }
}
