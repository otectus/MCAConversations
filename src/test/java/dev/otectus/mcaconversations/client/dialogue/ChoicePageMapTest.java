package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Guards the page map's partition invariant. A packing bug that drops or duplicates an index does
 * not crash and does not look wrong on the page you are looking at -- it silently makes one of the
 * server's offered answers unreachable, which players experience as a dialogue option that does not
 * exist. Nothing downstream re-checks the covering, so it has to be checked here.
 */
class ChoicePageMapTest {

    private static List<Integer> heights(int count, int each) {
        return Collections.nCopies(count, each);
    }

    private static void assertPartitions(DialogueChoiceLayout.PageMap map, int answerCount) {
        if (answerCount == 0) {
            assertTrue(map.pages().isEmpty());
            return;
        }
        assertFalse(map.pages().isEmpty(), "a non-empty offer must produce at least one page");
        int expectedNext = 0;
        for (DialogueChoiceLayout.ChoicePage page : map.pages()) {
            assertEquals(expectedNext, page.firstInclusive(),
                    "pages must be contiguous: found a gap or an overlap");
            assertTrue(page.size() >= 1, "an empty page would render a blank card");
            assertTrue(page.size() <= DialogueChoiceLayout.MAX_VISIBLE_SHORTCUTS,
                    "a page cannot hold more rows than there are number shortcuts");
            expectedNext = page.lastExclusive();
        }
        assertEquals(answerCount, expectedNext, "the last page must reach the end of the offer");

        List<Integer> covered = new ArrayList<>();
        for (DialogueChoiceLayout.ChoicePage page : map.pages()) {
            for (int i = page.firstInclusive(); i < page.lastExclusive(); i++) {
                covered.add(i);
            }
        }
        assertEquals(answerCount, covered.size(), "every answer must appear exactly once");
        assertEquals(answerCount, covered.stream().distinct().count());
    }

    @Test
    void pagesAlwaysPartitionTheOfferAcrossScreenSizesAndFonts() {
        for (int screenHeight : new int[]{180, 220, 270, 360, 480, 720, 1080}) {
            for (int fontLineHeight : new int[]{8, 9, 12, 16}) {
                for (int answers : new int[]{1, 2, 5, 9, 10, 18, 37}) {
                    for (int rowHeight : new int[]{12, 20, 44}) {
                        List<Integer> rows = heights(answers, rowHeight);
                        DialogueChoiceLayout.PageMap map = DialogueChoiceLayout.packPages(
                                screenHeight, 2, fontLineHeight, rows, rows, true);
                        assertPartitions(map, answers);
                    }
                }
            }
        }
    }

    @Test
    void emptyOfferProducesNoPages() {
        DialogueChoiceLayout.PageMap map = DialogueChoiceLayout.packPages(
                270, 2, 9, List.of(), List.of(), true);
        assertTrue(map.pages().isEmpty());
        assertFalse(map.compact());
    }

    @Test
    void aRowTallerThanTheSafeAreaOwnsItsOwnPage() {
        List<Integer> rows = List.of(20, 4000, 20);
        DialogueChoiceLayout.PageMap map = DialogueChoiceLayout.packPages(
                270, 2, 9, rows, rows, true);
        assertPartitions(map, 3);
        DialogueChoiceLayout.ChoicePage owning = map.pages().stream()
                .filter(page -> page.contains(1)).findFirst().orElseThrow();
        assertEquals(1, owning.size(), "an oversized answer must not share its page");
    }

    @Test
    void compactSpacingEngagesOnlyWhenNormalRowsDoNotFit() {
        List<Integer> normal = heights(4, 200);
        List<Integer> compact = heights(4, 14);
        assertTrue(DialogueChoiceLayout.packPages(180, 3, 9, normal, compact, true).compact(),
                "a short screen must fall back to compact row spacing");
        List<Integer> roomy = heights(4, 20);
        assertFalse(DialogueChoiceLayout.packPages(720, 2, 9, roomy, roomy, true).compact(),
                "a tall screen must not compact rows that already fit");
    }

    @Test
    void aPageCannotBeEmptyOrReversed() {
        assertThrows(IllegalArgumentException.class, () -> new DialogueChoiceLayout.ChoicePage(3, 3));
        assertThrows(IllegalArgumentException.class, () -> new DialogueChoiceLayout.ChoicePage(4, 2));
        assertThrows(IllegalArgumentException.class, () -> new DialogueChoiceLayout.ChoicePage(-1, 2));
    }

    @Test
    void nineShortcutsIsAHardCeilingEvenWithUnlimitedHeight() {
        List<Integer> rows = heights(30, 10);
        DialogueChoiceLayout.PageMap map = DialogueChoiceLayout.packPages(
                4000, 1, 9, rows, rows, false);
        assertPartitions(map, 30);
        assertEquals(DialogueChoiceLayout.MAX_VISIBLE_SHORTCUTS, map.pages().get(0).size());
    }
}
