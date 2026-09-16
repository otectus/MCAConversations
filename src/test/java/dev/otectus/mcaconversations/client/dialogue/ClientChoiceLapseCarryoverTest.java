package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The reported bug: an explanation left by one villager taking over the next villager's screen.
 *
 * <p>Walking away mid-answer clears the offer with {@code OUT_OF_RANGE}, which leaves a lapse — a
 * sentence and one safe action — behind on purpose. That shell has no offer, so a teardown that only
 * looked for a live offer never removed it, and the ownership gate never asked which villager it
 * belonged to. The next villager's screen inherited it and showed the old explanation instead of
 * MCA's dialogue.
 *
 * <p>Everything below is the client state's own decision, with no Minecraft runtime: the screen
 * contributes only the villager it was opened for.
 */
class ClientChoiceLapseCarryoverTest {

    private static final UUID VILLAGER_A = UUID.randomUUID();
    private static final UUID VILLAGER_B = UUID.randomUUID();

    private static ClientChoiceState.ClientChoiceOffer offer(long revision, UUID villagerId) {
        return new ClientChoiceState.ClientChoiceOffer(revision, villagerId, "conversations.q",
                List.of("a", "b"), ConversationSession.Frontend.GUI, 0L);
    }

    @Test
    void anExplanationIsShownWhileItsOwnVillagersScreenIsOpen() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(1L, VILLAGER_A));
        assertTrue(state.lock(0), "the player committed to an answer before walking away");
        assertTrue(state.clear(1L, ChoiceClearS2C.Reason.OUT_OF_RANGE));

        ClientChoiceState.Lapse lapse = state.lapse().orElseThrow();
        assertTrue(VILLAGER_A.equals(lapse.villagerId()), "the lapse names the conversation it ended");
        assertTrue(state.lapseFor(ConversationSession.Frontend.GUI, VILLAGER_A),
                "the villager who was walked away from still owes the player the explanation");
    }

    @Test
    void closingTheScreenLeavesNothingForTheNextVillagerToInherit() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(1L, VILLAGER_A));
        state.lock(0);
        state.clear(1L, ChoiceClearS2C.Reason.OUT_OF_RANGE);
        assertTrue(state.lapse().isPresent(), "the explanation outlives the answers it replaced");

        // What the interaction screen now does on close: the graphical frontend, both halves,
        // whether or not an offer is live.
        assertTrue(state.clearLocal(ConversationSession.Frontend.GUI));

        assertTrue(state.lapse().isEmpty(), "the explanation closed with the screen that owned it");
        assertTrue(state.offer().isEmpty());
        assertFalse(state.locked(), "no lock survives into the next conversation");
        assertFalse(state.lapseFor(ConversationSession.Frontend.GUI, VILLAGER_B));
        assertFalse(state.lapseFor(ConversationSession.Frontend.GUI, VILLAGER_A));
    }

    @Test
    void aSurvivingExplanationIsStillNotTheNextVillagersToShow() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(1L, VILLAGER_A));
        state.clear(1L, ChoiceClearS2C.Reason.OUT_OF_RANGE);

        // The second gate, independent of teardown: even a lapse that somehow outlived its screen is
        // refused by a screen opened for anyone else.
        assertFalse(state.lapseFor(ConversationSession.Frontend.GUI, VILLAGER_B),
                "villager B's screen must not adopt villager A's explanation");
        assertTrue(state.lapseFor(ConversationSession.Frontend.GUI, VILLAGER_A));
    }

    @Test
    void anUnownedExplanationBelongsToNoScreen() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(offer(1L, null));
        state.clear(1L, ChoiceClearS2C.Reason.OUT_OF_RANGE);

        assertTrue(state.lapse().isPresent());
        assertFalse(state.lapseFor(ConversationSession.Frontend.GUI, VILLAGER_A),
                "a lapse naming no villager is one no screen may claim");
        assertFalse(state.lapseFor(ConversationSession.Frontend.GUI, null));
    }

    @Test
    void aClosingScreenDoesNotEndAChatConversation() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(new ClientChoiceState.ClientChoiceOffer(1L, null, "conversations.q",
                List.of("a", "b"), ConversationSession.Frontend.CHAT, 0L));

        assertFalse(state.clearLocal(ConversationSession.Frontend.GUI),
                "the graphical teardown has nothing of its own to drop");
        assertTrue(state.offer().isPresent(), "the chat offer is not the interaction screen's");
        assertTrue(state.activeFor(ConversationSession.Frontend.CHAT));
    }
}
