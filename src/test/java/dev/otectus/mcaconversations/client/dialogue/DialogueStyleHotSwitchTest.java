package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Changing the dialogue style mid-conversation is a presentation change and nothing else.
 *
 * <p>The style is a live client config value, so a player can change it while a screen is open, and
 * the renderer's answer is to rebuild the model -- which re-packs the pages, because a narrower
 * badge gutter wraps answers differently and therefore fits a different number of them on a page.
 * Everything else is conversation truth: the offer, its revision, the order of its answers, which
 * answer the player has focused and whether they have already committed to one. §45 asks for this
 * explicitly, because a re-pack that quietly moved the focus would submit the wrong reply.
 */
class DialogueStyleHotSwitchTest {

    private static final int ANSWERS = 18;

    private static ClientChoiceState acceptedOffer() {
        List<String> answerIds = new ArrayList<>(ANSWERS);
        for (int i = 0; i < ANSWERS; i++) {
            answerIds.add("answer" + i);
        }
        ClientChoiceState state = new ClientChoiceState();
        state.accept(new ClientChoiceState.ClientChoiceOffer(7L, "question", answerIds,
                ConversationSession.Frontend.GUI, 0L));
        return state;
    }

    /** The pages the presentation builder would produce for a style, at one font and viewport. */
    private static List<DialogueChoiceLayout.ChoicePage> pagesFor(DialogueStyleProfile profile) {
        // Answers wrap into the width the gutter leaves them, so the two styles genuinely page
        // differently; that is the re-pack this test is checking survives.
        int column = DialogueChoiceLayout.numberColumn(12, profile);
        int answerWidth = DialogueChoiceLayout.answerTextWidth(640, column);
        List<Integer> normal = new ArrayList<>(ANSWERS);
        List<Integer> compact = new ArrayList<>(ANSWERS);
        for (int i = 0; i < ANSWERS; i++) {
            // Answers of assorted lengths, wrapped into whatever reading width the gutter left.
            int textWidth = 900 + i * 137;
            int lines = Math.max(1, (textWidth + answerWidth - 1) / answerWidth);
            normal.add(DialogueChoiceLayout.rowHeight(lines, 9, false));
            compact.add(DialogueChoiceLayout.rowHeight(lines, 9, true));
        }
        return DialogueChoiceLayout.packPages(360, 2, 9, normal, compact, true).pages();
    }

    private static void assertConversationTruthSurvives(ClientChoiceState state,
                                                        List<String> answerIds,
                                                        int focused, int locked) {
        assertTrue(state.offer().isPresent(), "the offer must survive a presentation change");
        assertEquals(7L, state.offer().get().revision(), "revision is server truth");
        assertEquals(answerIds, state.offer().get().answerIds(), "answer order is server truth");
        assertEquals(focused, state.focusedIndex(), "the focused answer must not move");
        assertEquals(locked, state.lockedIndex(), "a committed selection must not move");
        DialogueChoiceLayout.ChoicePage page = state.pages().get(state.page());
        assertTrue(page.contains(state.focusedIndex()),
                "the visible page must be the one holding the focused answer");
    }

    @Test
    void switchingBetweenTheTwoDrawnStylesKeepsFocusAndOffer() {
        ClientChoiceState state = acceptedOffer();
        List<String> answerIds = state.offer().orElseThrow().answerIds();
        for (int i = 0; i < 11; i++) {
            state.moveFocus(1);
        }
        assertEquals(11, state.focusedIndex());

        state.updatePages(pagesFor(DialogueStyleProfile.RESPONSIVE));
        assertConversationTruthSurvives(state, answerIds, 11, -1);
        state.updatePages(pagesFor(DialogueStyleProfile.MINIMAL));
        assertConversationTruthSurvives(state, answerIds, 11, -1);
        state.updatePages(pagesFor(DialogueStyleProfile.RESPONSIVE));
        assertConversationTruthSurvives(state, answerIds, 11, -1);
    }

    @Test
    void switchingWithASelectionAlreadyCommittedKeepsTheLock() {
        ClientChoiceState state = acceptedOffer();
        List<String> answerIds = state.offer().orElseThrow().answerIds();
        state.updatePages(pagesFor(DialogueStyleProfile.RESPONSIVE));
        // Focused first, exactly as a player reaches an answer: locking is only ever offered for
        // one that is on the page in front of them.
        for (int i = 0; i < 11; i++) {
            state.moveFocus(1);
        }
        assertTrue(state.lock(11), "the player commits to an answer on the responsive card");

        state.updatePages(pagesFor(DialogueStyleProfile.MINIMAL));
        assertConversationTruthSurvives(state, answerIds, 11, 11);
        state.updatePages(pagesFor(DialogueStyleProfile.RESPONSIVE));
        assertConversationTruthSurvives(state, answerIds, 11, 11);
    }

    @Test
    void switchingToOriginalMcaLeavesNothingForConversationsToDraw() {
        // The style that draws no card at all: the renderer asks the profile this question once per
        // frame and returns, which is what stops both menus being clickable in the same frame.
        assertFalse(DialogueStyleProfile.of(DialogueMenuStyle.MCA_ORIGINAL).customRenderer());
        // And the offer itself is untouched, so switching back builds the same conversation.
        ClientChoiceState state = acceptedOffer();
        state.updatePages(pagesFor(DialogueStyleProfile.RESPONSIVE));
        for (int i = 0; i < 11; i++) {
            state.moveFocus(1);
        }
        assertConversationTruthSurvives(state, state.offer().orElseThrow().answerIds(), 11, -1);
    }
}
