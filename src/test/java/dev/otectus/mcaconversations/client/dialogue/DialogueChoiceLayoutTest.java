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

    @Test
    void heightAwarePagesRespectFontAndSafeScreenBounds() {
        List<Integer> normal = List.of(30, 30, 30, 30, 30, 30);
        List<Integer> compact = List.of(24, 24, 24, 24, 24, 24);
        DialogueChoiceLayout.PageMap pages = DialogueChoiceLayout.packPages(
                180, 3, 16, normal, compact, true);
        assertTrue(pages.pages().size() > 1);
        assertEquals(0, pages.pages().get(0).firstInclusive());
        assertEquals(6, pages.pages().get(pages.pages().size() - 1).lastExclusive());
        assertTrue(pages.pages().stream().allMatch(page ->
                page.size() <= DialogueChoiceLayout.MAX_VISIBLE_SHORTCUTS));

        DialogueChoiceLayout.ChoicePage first = pages.pages().get(0);
        DialogueChoiceLayout.Layout layout = DialogueChoiceLayout.create(
                320, 180, 3, 16, normal.subList(first.firstInclusive(), first.lastExclusive()),
                true, pages.compact(), false, true);
        assertTrue(layout.panel().y() >= DialogueChoiceLayout.SAFE_TOP);
        assertTrue(layout.panel().y() + layout.panel().height()
                <= DialogueChoiceLayout.SAFE_TOP + DialogueChoiceLayout.maxPanelHeight(180));
    }

    @Test
    void atMostNineShortcutsArePackedPerPage() {
        List<Integer> heights = java.util.Collections.nCopies(18, 12);
        DialogueChoiceLayout.PageMap pages = DialogueChoiceLayout.packPages(
                800, 1, 9, heights, heights, false);
        assertEquals(2, pages.pages().size());
        assertEquals(9, pages.pages().get(0).size());
        assertEquals(9, pages.pages().get(1).size());
    }
}
