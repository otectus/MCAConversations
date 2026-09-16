package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** What the client keeps on screen after an answer is refused, and what it is allowed to offer. */
class ClientChoiceLapseTest {

    private static ClientChoiceState.ClientChoiceOffer offer(long revision) {
        return new ClientChoiceState.ClientChoiceOffer(revision, "conversations.q",
                List.of("a", "b"), ConversationSession.Frontend.GUI, 0L);
    }

    @Test
    void anExplainedClearLeavesTheExplanationReadable() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(5L));
        assertTrue(state.clear(5L, ChoiceClearS2C.Reason.REQUIREMENTS_CHANGED));
        assertTrue(state.offer().isEmpty(), "the answers themselves are gone");
        ClientChoiceState.Lapse lapse = state.lapse().orElseThrow();
        assertEquals(ChoiceClearS2C.Reason.REQUIREMENTS_CHANGED, lapse.reason());
        assertTrue(lapse.backToTopics());
        assertTrue(state.lapseFor(ConversationSession.Frontend.GUI),
                "GUI ownership must survive the offer being cleared and the card finishing its exit");
        assertFalse(state.lapseFor(ConversationSession.Frontend.CHAT));
    }

    @Test
    void aConsumedOrSupersededClearLeavesNothingBehind() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(1L));
        state.clear(1L, ChoiceClearS2C.Reason.CONSUMED);
        assertTrue(state.lapse().isEmpty());
        state.accept(offer(2L));
        state.clear(2L, ChoiceClearS2C.Reason.SUPERSEDED);
        assertTrue(state.lapse().isEmpty());
    }

    @Test
    void aFailedActionOffersNavigationOnly() {
        assertFalse(ClientChoiceState.offersReturnToTopics(ChoiceClearS2C.Reason.EXECUTION_FAILED));
        assertFalse(ClientChoiceState.offersReturnToTopics(ChoiceClearS2C.Reason.SPEAKER_UNAVAILABLE));
        assertFalse(ClientChoiceState.offersReturnToTopics(ChoiceClearS2C.Reason.OUT_OF_RANGE));
        assertTrue(ClientChoiceState.offersReturnToTopics(ChoiceClearS2C.Reason.EXPIRED));
        assertTrue(ClientChoiceState.offersReturnToTopics(ChoiceClearS2C.Reason.CONTENT_RELOADED));
    }

    @Test
    void aDelayedRejectionCannotDismissANewerDecision() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(9L));
        assertFalse(state.clear(8L, ChoiceClearS2C.Reason.EXECUTION_FAILED),
                "an old rejection must not touch the offer on screen");
        assertTrue(state.offer().isPresent());
        assertTrue(state.lapse().isEmpty());
    }

    @Test
    void aNewOfferReplacesTheShellEntirely() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(1L));
        state.clear(1L, ChoiceClearS2C.Reason.CONTENT_RELOADED);
        assertTrue(state.lapse().isPresent());
        state.accept(offer(2L));
        assertTrue(state.lapse().isEmpty());
        assertTrue(state.dismissLapse() == false);
    }

    @Test
    void aFailedReturnKeepsTheFrontendAndSwitchesToClose() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(1L));
        state.clear(1L, ChoiceClearS2C.Reason.CONTENT_RELOADED);
        assertTrue(state.clear(1L, ChoiceClearS2C.Reason.OUT_OF_RANGE),
                "a failed recovery must announce the new reason");
        assertFalse(state.clear(1L, ChoiceClearS2C.Reason.OUT_OF_RANGE),
                "a duplicate failure must not repeat its announcement");
        assertTrue(state.lapseFor(ConversationSession.Frontend.GUI));
        assertFalse(state.lapse().orElseThrow().backToTopics());
    }
}
