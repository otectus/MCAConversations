package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The shared conversation session and the guard that reads it (plan §7.1, §7.2, §13.5). */
class ConversationSessionTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("player".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("villager".getBytes());
    private static final UUID OTHER_VILLAGER = UUID.nameUUIDFromBytes("other".getBytes());

    @BeforeEach
    void reset() {
        ConversationSessions.clearAllForTesting();
    }

    @Test
    @DisplayName("the offered set is what the guard compares a submission against")
    void recordsTheOfferedSet() {
        ConversationSessions.recordOffer(PLAYER, "conversations.topic.day.rough.respond",
                List.of("empathize", "ask", "leave"), 100);
        ConversationSession session = ConversationSessions.get(PLAYER, 100);

        assertTrue(session.wasOffered("conversations.topic.day.rough.respond", "empathize"));
        assertFalse(session.wasOffered("conversations.topic.day.rough.respond", "mock"),
                "an answer that was never on screen was never offered");
        assertFalse(session.wasOffered("conversations.topic.day.good.respond", "empathize"),
                "the right answer to a different question is still not offered");
    }

    @Test
    @DisplayName("offer revisions advance, preserve order immutably, and consume exactly once")
    void revisionedOfferIsAtomic() {
        List<String> source = new java.util.ArrayList<>(List.of("first", "second"));
        ConversationSession.ChoiceOffer first = ConversationSessions.recordOffer(
                PLAYER, "conversations.q", source, 100);
        source.set(0, "mutated");
        ConversationSession.ChoiceOffer second = ConversationSessions.recordOffer(
                PLAYER, "conversations.q", List.of("first", "second"), 101);

        assertEquals(first.revision() + 1, second.revision());
        assertEquals(List.of("first", "second"), first.answerIds());
        assertThrows(UnsupportedOperationException.class, () -> second.answerIds().add("third"));
        assertEquals("second", ConversationSessions.consumeOffer(
                PLAYER, second.revision(), 1, 101).orElseThrow());
        assertTrue(ConversationSessions.consumeOffer(PLAYER, second.revision(), 1, 101).isEmpty());
        assertTrue(ConversationSessions.consumeOffer(PLAYER, first.revision(), 0, 101).isEmpty());
    }

    @Test
    @DisplayName("a GUI offer never inherits a stale villager from the broader session")
    void guiOfferKeepsUnboundVillagerExplicit() {
        ConversationSession session = ConversationSessions.get(PLAYER, 100);
        session.setVillagerId(VILLAGER);

        ConversationSession.ChoiceOffer gui = session.setOffer("conversations.q", List.of("answer"),
                ConversationSession.Frontend.GUI, null, 101);
        assertNull(gui.villagerId());
        assertEquals(VILLAGER, session.villagerId(), "recording the offer must not end the topic");

        ConversationSession.ChoiceOffer chat = session.setOffer("conversations.q", List.of("answer"),
                ConversationSession.Frontend.CHAT, OTHER_VILLAGER, 102);
        assertEquals(OTHER_VILLAGER, chat.villagerId());
        assertEquals(OTHER_VILLAGER, session.villagerId());
    }

    @Test
    @DisplayName("beginning a topic resets the per-conversation heart budget")
    void topicResetsBudget() {
        ConversationSession session = ConversationSessions.beginTopic(PLAYER, VILLAGER, "day",
                DepthClass.QUICK, 100);
        session.recordApplied(2);
        session.recordApplied(-1);
        assertEquals(2, session.positiveApplied());
        assertEquals(1, session.negativeApplied());

        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 200);
        assertEquals(0, session.positiveApplied());
        assertEquals(0, session.negativeApplied());
    }

    @Test
    @DisplayName("switching villager ends the old topic so its budget cannot carry over")
    void switchingTargetEndsTheTopic() {
        ConversationSession session = ConversationSessions.beginTopic(PLAYER, VILLAGER, "day",
                DepthClass.QUICK, 100);
        session.recordApplied(2);
        session.setVillagerId(OTHER_VILLAGER);

        assertTrue(session.topicId().isEmpty());
        assertEquals(0, session.positiveApplied());
        assertEquals(DepthClass.QUICK, session.budget());
    }

    @Test
    @DisplayName("an idle session's topic ends on next contact, and the sweep eventually drops it")
    void expiry() {
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);
        assertTrue(ConversationSessions.get(PLAYER, 100).topicId().isPresent());

        // Well past the 1200-tick default timeout.
        assertTrue(ConversationSessions.get(PLAYER, 100_000).topicId().isEmpty());
        ConversationSessions.raw(PLAYER).orElseThrow().touch(100_000);
        assertEquals(0, ConversationSessions.sweep(101_000), "still fresh enough to keep");
        assertEquals(1, ConversationSessions.sweep(500_000));
        assertEquals(0, ConversationSessions.size());
    }

    @Test
    @DisplayName("a villager's death ends any session pointed at it")
    void villagerDeathClearsSessions() {
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);
        ConversationSessions.clearVillager(VILLAGER);
        ConversationSession session = ConversationSessions.get(PLAYER, 100);
        assertTrue(session.topicId().isEmpty());
        assertEquals(null, session.villagerId());
    }

    // --- ConversationGuard -----------------------------------------------------

    @Test
    @DisplayName("native MCA questions are never judged")
    void nativeQuestionsPassThrough() {
        assertFalse(ConversationGuard.isOurQuestion("main"));
        assertFalse(ConversationGuard.isOurQuestion("chat.topic"));
        assertTrue(ConversationGuard.isOurQuestion("conversations"));
        assertTrue(ConversationGuard.isOurQuestion("conversations.topic.day.rough.respond"));
        assertFalse(ConversationGuard.rejectSubmission(PLAYER, VILLAGER, "story", "yes", false, 100));
    }

    @Test
    @DisplayName("with no recorded offer the guard fails open rather than breaking a click")
    void failsOpenWithoutAnOffer() {
        assertFalse(ConversationGuard.rejectSubmission(PLAYER, VILLAGER,
                "conversations.cat.chitchat", "day", false, 100));
    }

    @Test
    @DisplayName("an answer that was never offered is rejected")
    void rejectsUnofferedAnswers() {
        ConversationSessions.recordOffer(PLAYER, "conversations.topic.day.rough.respond",
                List.of("empathize", "leave"), 100);
        assertTrue(ConversationGuard.rejectSubmission(PLAYER, VILLAGER,
                "conversations.topic.day.rough.respond", "mock", false, 100));
        assertFalse(ConversationGuard.rejectSubmission(PLAYER, VILLAGER,
                "conversations.topic.day.rough.respond", "empathize", false, 100));
    }

    @Test
    @DisplayName("the same submission twice in one tick is a duplicated packet")
    void rejectsDuplicateSubmissions() {
        ConversationSessions.recordOffer(PLAYER, "conversations.topic.day.rough.respond",
                List.of("empathize"), 100);
        assertFalse(ConversationGuard.rejectSubmission(PLAYER, VILLAGER,
                "conversations.topic.day.rough.respond", "empathize", false, 100));
        assertTrue(ConversationGuard.rejectSubmission(PLAYER, VILLAGER,
                "conversations.topic.day.rough.respond", "empathize", false, 100));
    }

    @Test
    @DisplayName("a villager mid-conversation with someone else cannot be driven")
    void rejectsWhenAnotherPlayerIsInteracting() {
        ConversationSessions.recordOffer(PLAYER, "conversations.topic.day.rough.respond",
                List.of("empathize"), 100);
        assertTrue(ConversationGuard.rejectSubmission(PLAYER, VILLAGER,
                "conversations.topic.day.rough.respond", "empathize", true, 100));
    }
}
