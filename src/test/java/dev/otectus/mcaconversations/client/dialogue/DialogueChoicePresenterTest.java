package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DialogueChoicePresenterTest {

    @Test
    void stationaryPointerDoesNotStealKeyboardFocusButMovementDoes() {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(new ClientChoiceState.ClientChoiceOffer(1, "q", List.of("a", "b"),
                ConversationSession.Frontend.GUI, 0));
        PreparedDialogueCard card = card();
        DialogueChoicePresenter presenter = new DialogueChoicePresenter();

        assertFalse(presenter.updatePointer(20, 20, card, state));
        presenter.keyboard();
        assertTrue(state.moveFocus(1));
        assertEquals(1, state.focusedIndex());
        assertFalse(presenter.updatePointer(20, 20, card, state));
        assertEquals(1, state.focusedIndex());
        assertTrue(presenter.updatePointer(21, 20, card, state));
        assertEquals(0, state.focusedIndex());
        assertEquals(DialogueChoicePresenter.InputModality.POINTER, presenter.modality());
    }

    @Test
    void controlsHaveTypedTargets() {
        PreparedDialogueCard card = card();
        assertInstanceOf(DialogueHitTarget.Choice.class, card.hit(20, 20));
        assertInstanceOf(DialogueHitTarget.PreviousPage.class, card.hit(110, 85));
        assertInstanceOf(DialogueHitTarget.NextPage.class, card.hit(140, 85));
        assertInstanceOf(DialogueHitTarget.None.class, card.hit(2, 2));
    }

    @Test
    void theQuestionAndTheListAreSeparatelyAddressableRegions() {
        // The wheel is routed by what it is over, so the two reading regions have to be
        // distinguishable from each other and from a miss outside the card.
        PreparedDialogueCard card = card();
        assertInstanceOf(DialogueHitTarget.Question.class, card.hit(20, 13),
                "the question's own region is a target");
        assertInstanceOf(DialogueHitTarget.Responses.class, card.hit(20, 70),
                "empty space inside the viewport still belongs to the list");
        assertInstanceOf(DialogueHitTarget.None.class, card.hit(20, 100),
                "below the viewport the card claims nothing");
    }

    @Test
    void aRowScrolledOutOfTheViewportIsNotClickableThroughTheQuestion() {
        DialogueChoiceLayout.Rect panel = new DialogueChoiceLayout.Rect(10, 10, 160, 100);
        DialogueChoiceLayout.Rect above = new DialogueChoiceLayout.Rect(15, 0, 140, 20);
        DialogueChoiceLayout.Layout layout = new DialogueChoiceLayout.Layout(panel, panel, null,
                15, 15, 1, 35, List.of(above), 80, null, null,
                new DialogueChoiceLayout.Rect(15, 12, 140, 20),
                new DialogueChoiceLayout.Rect(15, 40, 140, 40), 120);
        PreparedDialogueCard card = new PreparedDialogueCard(1, layout,
                List.of(FormattedCharSequence.EMPTY),
                List.of(new PreparedChoiceRow(0, 1, above, above, Component.literal("A"),
                        List.of(FormattedCharSequence.EMPTY), true, false)),
                10, DialogueChoiceLayout.NUMBER_COLUMN, false);
        assertInstanceOf(DialogueHitTarget.Question.class, card.hit(20, 15),
                "a row scrolled above the viewport must not be clickable over the question");
        assertTrue(card.responsesOverflow());
    }

    private static PreparedDialogueCard card() {
        DialogueChoiceLayout.Rect panel = new DialogueChoiceLayout.Rect(10, 10, 160, 100);
        DialogueChoiceLayout.Rect first = new DialogueChoiceLayout.Rect(15, 15, 140, 20);
        DialogueChoiceLayout.Rect second = new DialogueChoiceLayout.Rect(15, 40, 140, 20);
        DialogueChoiceLayout.Layout layout = new DialogueChoiceLayout.Layout(panel, panel, null,
                15, 15, 1, 35, List.of(first, second), 80,
                new DialogueChoiceLayout.Rect(100, 80, 18, 18),
                new DialogueChoiceLayout.Rect(130, 80, 18, 18),
                new DialogueChoiceLayout.Rect(15, 12, 140, 20),
                new DialogueChoiceLayout.Rect(15, 15, 140, 60), 45);
        return new PreparedDialogueCard(1, layout, List.of(FormattedCharSequence.EMPTY), List.of(
                new PreparedChoiceRow(0, 1, first, first, Component.literal("A"),
                        List.of(FormattedCharSequence.EMPTY), false),
                new PreparedChoiceRow(1, 2, second, second, Component.literal("B"),
                        List.of(FormattedCharSequence.EMPTY), false)),
                10, DialogueChoiceLayout.NUMBER_COLUMN, false);
    }
}
