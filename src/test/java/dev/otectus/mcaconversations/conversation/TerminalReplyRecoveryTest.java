package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/** A say-only reply keeps the menu usable without replaying an old selection or replacing a successor. */
class TerminalReplyRecoveryTest {
    private final UUID player = UUID.randomUUID();
    private final UUID villager = UUID.randomUUID();

    @AfterEach
    void clear() {
        ConversationSessions.clearAllForTesting();
    }

    @ParameterizedTest
    @EnumSource(ConversationSession.Frontend.class)
    void aSuccessfulTerminalReplyGetsAFreshOneShotOffer(ConversationSession.Frontend frontend) {
        var session = ConversationSessions.beginTopic(player, villager, "day", DepthClass.QUICK, 10);
        session.recordApplied(2);
        var original = ConversationSessions.recordOffer(player, villager, "conversations.q",
                List.of("yes", "no"), frontend, 10);
        assertTrue(session.consumeOffer(original.revision(), 0).isPresent());
        var fresh = ConversationSessions.reofferConsumed(player, original.questionId(), original.revision(), 11)
                .orElseThrow();
        assertTrue(fresh.revision() > original.revision());
        assertEquals(original.answerIds(), fresh.answerIds());
        assertEquals(frontend, fresh.frontend());
        assertEquals(villager, fresh.villagerId());
        assertEquals(2, session.positiveApplied(), "restoring choices must not replenish the topic budget");
        assertTrue(session.consumeOffer(original.revision(), 0).isEmpty(), "old packets remain invalid");
        assertTrue(ConversationSessions.reofferConsumed(player, original.questionId(), original.revision(), 12)
                .isEmpty(), "recovery itself cannot be replayed");
        assertTrue(session.consumeOffer(fresh.revision(), 1).isPresent());
    }

    @Test
    void anUnconsumedOfferOrDifferentQuestionCannotBeRecovered() {
        var original = ConversationSessions.recordOffer(player, "conversations.q", List.of("yes"), 10);
        assertTrue(ConversationSessions.reofferConsumed(player, original.questionId(), original.revision(), 11)
                .isEmpty());
        ConversationSessions.raw(player).orElseThrow().consumeOffer(original.revision(), 0);
        assertTrue(ConversationSessions.reofferConsumed(player, "conversations.other", original.revision(), 11)
                .isEmpty());
    }

    @Test
    void aSuccessorIsNeverOverwritten() {
        var original = ConversationSessions.recordOffer(player, "conversations.q", List.of("yes"), 10);
        var session = ConversationSessions.raw(player).orElseThrow();
        session.consumeOffer(original.revision(), 0);
        var successor = ConversationSessions.recordOffer(player, "conversations.next", List.of("leave"), 11);
        assertTrue(ConversationSessions.reofferConsumed(player, original.questionId(), original.revision(), 11)
                .isEmpty());
        assertEquals(successor, session.currentOffer().orElseThrow());
    }

    @Test
    void anEndedTopicStaysEnded() {
        var original = ConversationSessions.recordOffer(player, "conversations.q", List.of("yes"), 10);
        var session = ConversationSessions.raw(player).orElseThrow();
        session.consumeOffer(original.revision(), 0);
        session.endTopic();
        assertTrue(ConversationSessions.reofferConsumed(player, original.questionId(), original.revision(), 11)
                .isEmpty());
    }
}
