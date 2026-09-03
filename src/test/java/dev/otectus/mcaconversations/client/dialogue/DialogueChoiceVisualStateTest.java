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

    private static final ConversationMotionSpec FULL = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.FULL,
            4.0F, 3.0F, 2.5F, 2.0F, 1.5F, 2.0F, 3.0F, 2.0F,
            4, 4, 1, 3, 4, 0.35F);

    private static final ConversationMotionSpec REDUCED = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.REDUCED,
            3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F,
            0, 0, 0, 0, 0, 0.0F);

    private static final ConversationMotionSpec OFF = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.OFF,
            0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
            0, 0, 0, 0, 0, 0.0F);

    private static DialogueChoiceVisualState observing(ClientChoiceState state) {
        DialogueChoiceVisualState visual = new DialogueChoiceVisualState();
        visual.observe(state, 0);
        return visual;
    }

    private static ClientChoiceState offered(String... answers) {
        ClientChoiceState state = new ClientChoiceState();
        state.accept(new ClientChoiceState.ClientChoiceOffer(1, "q", List.of(answers),
                ConversationSession.Frontend.GUI, 0));
        return state;
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
}
