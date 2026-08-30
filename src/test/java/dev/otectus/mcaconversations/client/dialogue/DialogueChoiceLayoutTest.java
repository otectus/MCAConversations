package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DialogueChoiceLayoutTest {

    @Test
    void responsiveRowsDoNotOverlap() {
        DialogueChoiceLayout.Layout layout = DialogueChoiceLayout.create(
                320, 300, 2, List.of(18, 27, 18, 36), true);
        assertTrue(layout.panel().width() <= 320 - DialogueChoiceLayout.OUTER_MARGIN * 2);
        assertEquals(4, layout.rows().size());
        for (int i = 1; i < layout.rows().size(); i++) {
            DialogueChoiceLayout.Rect previous = layout.rows().get(i - 1);
            DialogueChoiceLayout.Rect current = layout.rows().get(i);
            assertTrue(current.y() >= previous.y() + previous.height() + DialogueChoiceLayout.ROW_GAP);
        }
        assertTrue(layout.rows().get(1).contains(
                layout.rows().get(1).x() + 1, layout.rows().get(1).y() + 1));
    }

    @Test
    void narrowScreensKeepUsableMarginsAndTextWidth() {
        assertTrue(DialogueChoiceLayout.panelWidth(180) <= 164);
        assertTrue(DialogueChoiceLayout.answerTextWidth(180) >= 40);
        assertEquals(DialogueChoiceLayout.panelWidth(180) - DialogueChoiceLayout.INNER_PADDING * 2,
                DialogueChoiceLayout.questionTextWidth(180));
        assertTrue(DialogueChoiceLayout.questionTextWidth(180)
                > DialogueChoiceLayout.answerTextWidth(180));
    }
}
