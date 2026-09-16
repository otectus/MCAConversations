package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TopicReturnTest {
    @Test
    void returnSurvivesClearingTheRejectedOfferButCanOnlyRunOnce() {
        var session = new ConversationSession(UUID.randomUUID(), 0L);
        var offer = session.setOffer("conversations.test", List.of("answer"),
                ConversationSession.Frontend.GUI, UUID.randomUUID(), 0L);
        session.clearOffer();
        session.allowTopicReturn(offer.revision());
        assertTrue(session.claimTopicReturn(offer.revision()));
        assertFalse(session.claimTopicReturn(offer.revision()));
    }

    @Test
    void anOldRefusalCannotAuthorizeReplacingTheNewMenu() {
        var session = new ConversationSession(UUID.randomUUID(), 0L);
        var old = session.setOffer("conversations.test", List.of("answer"),
                ConversationSession.Frontend.GUI, null, 0L);
        session.allowTopicReturn(old.revision());
        var next = session.setOffer("conversations.next", List.of("new"),
                ConversationSession.Frontend.GUI, null, 1L);
        session.allowTopicReturn(old.revision());
        assertFalse(session.claimTopicReturn(old.revision()));
        assertFalse(session.claimTopicReturn(next.revision()), "the new offer was never refused");
        assertEquals(next.revision(), session.currentOffer().orElseThrow().revision());
    }

    @Test
    void aChatRefusalCannotOpenAGuiMenu() {
        var session = new ConversationSession(UUID.randomUUID(), 0L);
        var offer = session.setOffer("conversations.test", List.of("answer"),
                ConversationSession.Frontend.CHAT, UUID.randomUUID(), 0L);
        session.allowTopicReturn(offer.revision());
        assertFalse(session.claimTopicReturn(offer.revision()));
    }
}
