package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A GUI offer lives as long as MCA's screen does, not as long as the session timeout. It used to age
 * out with the session, so the first click after a minute of reading bounced as EXPIRED, the card
 * faded, and MCA's own still-populated answer list took the screen back.
 */
class GuiOfferLifetimeTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("player".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("villager".getBytes());
    private static final UUID OTHER_VILLAGER = UUID.nameUUIDFromBytes("other".getBytes());
    /** Past the largest configurable session timeout, so the expiry branch runs under any config. */
    private static final long LONG_AFTER = 100L + 30_000L;
    private static final int TIMEOUT = 1200;

    @BeforeEach
    void reset() {
        ConversationSessions.clearAllForTesting();
    }

    @Test
    @DisplayName("a GUI offer survives the session's inactivity expiry")
    void guiOfferSurvivesInactivityExpiry() {
        ConversationSession.ChoiceOffer offered =
                ConversationSessions.recordOffer(PLAYER, "main", List.of("chat", "joke"), 100);

        ConversationSession session = ConversationSessions.get(PLAYER, LONG_AFTER);
        ConversationSession.ChoiceOffer after = session.currentOffer().orElseThrow();

        assertEquals(offered.revision(), after.revision(), "the same offer, not a re-offer");
        assertEquals(offered.answerIds(), after.answerIds());
        assertFalse(after.consumed());
        assertTrue(session.wasOffered("main", "joke"), "the submission guard still knows the answers");
    }

    @Test
    @DisplayName("a chat offer still expires with the topic")
    void chatOfferExpiresWithTheTopic() {
        ConversationSessions.recordOffer(PLAYER, VILLAGER, "main", List.of("chat"),
                ConversationSession.Frontend.CHAT, 100);

        ConversationSession session = ConversationSessions.get(PLAYER, LONG_AFTER);

        assertTrue(session.currentOffer().isEmpty());
    }

    @Test
    @DisplayName("only chat offers age out by time")
    void onlyChatOffersAgeOut() {
        ConversationSession.ChoiceOffer gui = new ConversationSession.ChoiceOffer(
                1, null, "main", List.of("chat"), ConversationSession.Frontend.GUI, 0, false);
        ConversationSession.ChoiceOffer chat = new ConversationSession.ChoiceOffer(
                1, VILLAGER, "main", List.of("chat"), ConversationSession.Frontend.CHAT, 0, false);

        assertFalse(ChoiceSelectionService.offerTimedOut(gui, TIMEOUT * 100L, TIMEOUT));
        assertFalse(ChoiceSelectionService.offerTimedOut(chat, TIMEOUT, TIMEOUT));
        assertTrue(ChoiceSelectionService.offerTimedOut(chat, TIMEOUT + 1L, TIMEOUT));
    }

    @Test
    @DisplayName("a consumed GUI offer goes with the topic")
    void consumedGuiOfferExpiresWithTheTopic() {
        ConversationSession.ChoiceOffer offered =
                ConversationSessions.recordOffer(PLAYER, "main", List.of("chat"), 100);
        ConversationSessions.get(PLAYER, 100).consumeOffer(offered.revision(), 0);

        ConversationSession session = ConversationSessions.get(PLAYER, LONG_AFTER);

        assertTrue(session.currentOffer().isEmpty(), "nothing is on screen for an answered offer");
    }

    @Test
    @DisplayName("switching villagers still drops the old offer")
    void switchingVillagersDropsTheOffer() {
        ConversationSessions.recordOffer(PLAYER, "main", List.of("chat"), 100);
        ConversationSession session = ConversationSessions.get(PLAYER, 100);
        session.setVillagerId(VILLAGER);

        session.setVillagerId(OTHER_VILLAGER);

        assertTrue(session.currentOffer().isEmpty());
    }
}
