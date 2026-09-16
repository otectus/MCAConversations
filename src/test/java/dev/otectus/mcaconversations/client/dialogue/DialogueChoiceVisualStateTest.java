package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Guards the motion-profile contract. The failure this catches is invisible in a screenshot: a
 * duration left hard-coded in the visual state keeps animating under {@code motionMode = OFF}, which
 * the config documents as "changes visual state immediately". Players who select OFF are the ones
 * least able to tolerate the animation still being there, and nothing else in the build notices.
 */
class DialogueChoiceVisualStateTest {

    /** One villager for every offer in this class: identity is not what it is testing. */
    private static final java.util.UUID VILLAGER = java.util.UUID.randomUUID();

    private static final ConversationMotionSpec FULL = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.FULL,
            4.0F, 3.0F, 2.5F, 2.0F, 1.5F, 2.0F, 3.0F, 2.0F,
            4, 4, 1, 3, 4, 0.35F, 3.0F, 2.0F);

    private static final ConversationMotionSpec REDUCED = ConversationMotionSpec.of(
            McaConversationsConfig.MotionMode.REDUCED,
            McaConversationsConfig.DialogueMenuStyle.MINIMAL);

    private static final ConversationMotionSpec OFF = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.OFF,
            0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
            0, 0, 0, 0, 0, 0.0F, 0.0F, 0.0F);

    private static final ConversationMotionSpec MINIMAL_FULL = ConversationMotionSpec.of(
            McaConversationsConfig.MotionMode.FULL,
            McaConversationsConfig.DialogueMenuStyle.MINIMAL);

    private static DialogueChoiceVisualState observing(ClientChoiceState state) {
        DialogueChoiceVisualState visual = new DialogueChoiceVisualState();
        visual.observe(state, 0);
        return visual;
    }

    private static ClientChoiceState offered(String... answers) {
        ClientChoiceState state = new ClientChoiceState();
        offer(state, 1, answers);
        return state;
    }

    /** The next turn of the same conversation: a newer revision on the same open screen. */
    private static void offer(ClientChoiceState state, long revision, String... answers) {
        state.accept(new ClientChoiceState.ClientChoiceOffer(revision, VILLAGER, "q" + revision,
                List.of(answers), ConversationSession.Frontend.GUI, 0));
    }

    private static void settle(DialogueChoiceVisualState visual, int ticks) {
        for (int i = 0; i < ticks; i++) {
            visual.tick();
        }
    }

    @Test
    void enterAndFocusProgressClampAndSettle() {
        ClientChoiceState state = offered("a", "b");
        DialogueChoiceVisualState visual = observing(state);
        assertEquals(0.0F, visual.cardProgress(0, FULL));
        visual.tick();
        visual.tick();
        assertEquals(0.5F, visual.cardProgress(0, FULL), 0.001F);
        state.moveFocus(1);
        visual.observe(state, 0);
        assertEquals(0.0F, visual.focusProgress(1, 0, FULL), 0.001F);
        for (int i = 0; i < 8; i++) visual.tick();
        assertEquals(1.0F, visual.cardProgress(0, FULL), 0.001F);
        assertEquals(1.0F, visual.focusProgress(1, 0, FULL), 0.001F);
    }

    @Test
    void rowsCascadeUnderFullMotion() {
        DialogueChoiceVisualState visual = observing(offered("a", "b", "c"));
        visual.tick();
        assertTrue(visual.rowEntryProgress(0, 0, FULL) > visual.rowEntryProgress(2, 0, FULL),
                "a later row must still be behind the first while the cascade runs");
        for (int i = 0; i < 6; i++) visual.tick();
        assertEquals(1.0F, visual.rowEntryProgress(2, 0, FULL), 0.001F);
    }

    @Test
    void offModeIsImmediateForEveryAnimatedQuantity() {
        ClientChoiceState state = offered("a", "b");
        DialogueChoiceVisualState visual = observing(state);

        assertEquals(1.0F, visual.cardProgress(0, OFF), "card enter");
        assertEquals(1.0F, visual.pageProgress(0, OFF), "page shift");
        assertEquals(1.0F, visual.exitProgress(0, 0, OFF), "outgoing fade clears at once");
        for (int row = 0; row < 2; row++) {
            assertEquals(1.0F, visual.rowEntryProgress(row, 0, OFF), 0.0001F,
                    "row " + row + " must not cascade");
        }
        assertEquals(1.0F, visual.focusProgress(0, 0, OFF), "focused row");
        assertEquals(0.0F, visual.focusProgress(1, 0, OFF), "unfocused row");

        state.lock(0);
        visual.observe(state, 0);
        assertEquals(OFF.focusOutset(), visual.lockedOutset(0, OFF), 0.0001F,
                "a zeroed profile must not animate the selection press");
        assertTrue(OFF.instant());
    }

    @Test
    void aNewQuestionDoesNotReplayTheEntrance() {
        // The failure this prevents: every answer clears the offer and the next one arrives a
        // moment later, so an entrance keyed to the revision fades the whole card in again on each
        // turn of the conversation. On a slow server that reads as the menu closing and reopening.
        ClientChoiceState state = offered("a", "b");
        DialogueChoiceVisualState visual = observing(state);
        settle(visual, 8);
        assertEquals(1.0F, visual.cardProgress(0, FULL), 0.001F, "the card has finished entering");

        offer(state, 2, "c", "d");
        visual.observe(state, 0);
        assertEquals(1.0F, visual.cardProgress(0, FULL), 0.001F,
                "the next question replaces the text; the card is already open");
        assertEquals(1.0F, visual.cardProgress(0, REDUCED), 0.001F,
                "and under reduced motion it must not fade again either");
    }

    @Test
    void rowsStillArriveWithEachQuestionUnderFullMotion() {
        // The other half: keying the card to the presentation must not freeze the content. Rows are
        // content, so the cascade the responsive card has always had still runs per question.
        ClientChoiceState state = offered("a", "b", "c");
        DialogueChoiceVisualState visual = observing(state);
        settle(visual, 8);
        assertEquals(1.0F, visual.rowEntryProgress(2, 0, FULL), 0.001F);

        offer(state, 2, "d", "e", "f");
        visual.observe(state, 0);
        assertEquals(0.0F, visual.rowEntryProgress(2, 0, FULL), 0.001F,
                "new answers arrive as new answers");
        assertEquals(1.0F, visual.rowEntryProgress(2, 0, REDUCED), 0.001F,
                "reduced motion still shows them at once");
        assertEquals(0.0F, visual.questionRevealProgress(0, 4.0F), 0.001F,
                "the reveal is per question, not per screen");
    }

    @Test
    void closingTheScreenIsWhatMakesTheNextOpenAnEntrance() {
        ClientChoiceState state = offered("a", "b");
        DialogueChoiceVisualState visual = observing(state);
        settle(visual, 8);
        visual.reset();

        ClientChoiceState reopened = offered("a", "b");
        visual.observe(reopened, 0);
        assertEquals(0.0F, visual.cardProgress(0, FULL), 0.001F,
                "a genuine open still enters; only the turns in between do not");
    }

    @Test
    void reducedMotionChangesPagesAndSelectionWithoutMotion() {
        ClientChoiceState state = offered("a", "b", "c");
        DialogueChoiceVisualState visual = observing(state);
        settle(visual, 8);
        state.moveFocus(1);
        visual.observe(state, 0);
        assertEquals(1.0F, visual.focusProgress(1, 0, REDUCED), 0.001F, "focus is immediate");
        assertEquals(0.0F, visual.focusProgress(0, 0, REDUCED), 0.001F);
        assertEquals(1.0F, visual.pageProgress(0, REDUCED), 0.001F, "paging is immediate");
        state.lock(1);
        visual.observe(state, 0);
        assertEquals(0.0F, visual.lockedOutset(0, REDUCED), 0.001F, "selection moves nothing");
        assertTrue(REDUCED.exitTicks() > 0.0F, "the one thing left is the fade on a real close");
    }

    @Test
    void reducedMotionFadesWithoutTranslatingOrCascading() {
        DialogueChoiceVisualState visual = observing(offered("a", "b"));
        assertEquals(0, REDUCED.enterDistance());
        assertEquals(0, REDUCED.rowEntryDistance());
        assertEquals(0, REDUCED.pageDistance());
        assertEquals(0, REDUCED.focusOutset());
        assertEquals(1.0F, visual.rowEntryProgress(1, 0, REDUCED), 0.0001F,
                "reduced motion fades the whole card, it does not stagger rows");
        assertTrue(visual.cardProgress(0, REDUCED) < 1.0F, "the card itself still fades in");
        assertFalse(REDUCED.instant());
    }

    @Test
    void minimalFullNeverExpandsFocus() {
        // The whole point of the flat card is that a row stays where the layout put it. A focused
        // row that grows would move its own hitbox out from under the pointer that is hovering it.
        ClientChoiceState state = offered("a", "b");
        DialogueChoiceVisualState visual = observing(state);
        assertEquals(0, MINIMAL_FULL.focusOutset(), "no pop-out");
        assertEquals(0, MINIMAL_FULL.focusLift(), "no lift");
        state.lock(1);
        visual.observe(state, 0);
        assertEquals(0.0F, visual.lockedOutset(0, MINIMAL_FULL), 0.0001F,
                "a locked row must not push out either");
    }

    @Test
    void minimalFullDoesNotCascadeRows() {
        DialogueChoiceVisualState visual = observing(offered("a", "b", "c"));
        assertEquals(0.0F, MINIMAL_FULL.rowStagger(), 0.0001F);
        assertEquals(0, MINIMAL_FULL.rowEntryDistance());
        assertEquals(1.0F, visual.rowEntryProgress(2, 0, MINIMAL_FULL), 0.0001F,
                "rows arrive with the card rather than one after another");
        assertTrue(visual.cardProgress(0, MINIMAL_FULL) < 1.0F, "the card itself still fades in");
    }

    @Test
    void minimalFullHasNoPressOrSettleMovement() {
        ClientChoiceState state = offered("a", "b");
        DialogueChoiceVisualState visual = observing(state);
        state.lock(0);
        visual.observe(state, 0);
        assertEquals(0.0F, MINIMAL_FULL.selectionPressDepth(), 0.0001F);
        assertEquals(0.0F, MINIMAL_FULL.selectionSettleRise(), 0.0001F);
        for (int i = 0; i < 6; i++) {
            assertEquals(0.0F, visual.lockedOutset(0, MINIMAL_FULL), 0.0001F,
                    "confirmation is a colour change here, not a button press");
            visual.tick();
        }
        assertFalse(MINIMAL_FULL.instant(), "restrained is not the same as switched off");
    }
}
