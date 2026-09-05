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

    @Test
    void numberColumnHonoursStyleFloor() {
        // The one-argument form is the responsive card and must keep answering exactly what it did
        // in 1.5.1; the profile form lets a style ask for less, but never less than the digit and
        // the padding around it actually occupy.
        int numeralWidth = 8;
        assertEquals(DialogueChoiceLayout.numberColumn(numeralWidth),
                DialogueChoiceLayout.numberColumn(numeralWidth, DialogueStyleProfile.RESPONSIVE),
                "the default profile is the responsive card");
        int minimal = DialogueChoiceLayout.numberColumn(numeralWidth, DialogueStyleProfile.MINIMAL);
        assertTrue(minimal < DialogueChoiceLayout.numberColumn(numeralWidth),
                "a text-only badge must actually narrow the gutter");
        assertEquals(4 + numeralWidth + 5, minimal,
                "MINIMAL is the numeral between the card's own 4px and 5px gaps");
        assertTrue(minimal >= DialogueStyleProfile.MINIMAL.numberColumnFloor(),
                "the style floor is still a floor");
    }

    @Test
    void badgeWidthFollowsTheStyleTheGutterWasMeasuredFor() {
        // The gutter and the badge box are the same decision made twice. If they disagree, the
        // numeral is drawn over the first word of the answer, which is exactly what the floor and
        // the badge minimum exist to prevent.
        DialogueChoiceLayout.Rect row = new DialogueChoiceLayout.Rect(0, 0, 200, 20);
        for (DialogueStyleProfile profile :
                new DialogueStyleProfile[]{DialogueStyleProfile.RESPONSIVE, DialogueStyleProfile.MINIMAL}) {
            for (int numeralWidth : new int[]{1, 4, 8, 12, 20, 32}) {
                DialogueChoiceLayout.Rect badge =
                        DialogueChoiceLayout.badgeRect(row, 9, numeralWidth, profile);
                assertTrue(badge.width() >= numeralWidth,
                        "the badge must hold its own numeral");
                assertTrue(badge.x() + badge.width()
                                <= row.x() + DialogueChoiceLayout.numberColumn(numeralWidth, profile),
                        "the badge must stay inside the gutter it was measured with");
            }
        }
    }
}
