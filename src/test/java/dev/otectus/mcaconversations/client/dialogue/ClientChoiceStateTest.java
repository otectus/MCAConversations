package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientChoiceStateTest {

    private static ClientChoiceState.ClientChoiceOffer offer(long revision, int count) {
        List<String> answers = java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> "answer_" + i).toList();
        return new ClientChoiceState.ClientChoiceOffer(revision, "question", answers,
                ConversationSession.Frontend.GUI, 20L);
    }

    @Test
    void newerOfferResetsFocusPageAndLock() {
        ClientChoiceState state = new ClientChoiceState();
        assertTrue(state.accept(offer(1, 10)));
        assertEquals(2, state.pageCount());
        assertTrue(state.changePage(1));
        assertEquals(9, state.focusedIndex());
        assertTrue(state.lock(9));

        assertTrue(state.accept(offer(2, 3)));
        assertEquals(0, state.page());
        assertEquals(0, state.focusedIndex());
        assertFalse(state.locked());
        assertFalse(state.accept(offer(1, 4)));
    }

    @Test
    void staleClearCannotEraseNewOfferAndLockIsOneShot() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(4, 5));
        assertFalse(state.clear(3));
        assertTrue(state.offer().isPresent());
        assertTrue(state.lock(2));
        assertFalse(state.lock(2));
        assertTrue(state.clear(4));
        assertTrue(state.offer().isEmpty());
    }
}
