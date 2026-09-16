package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The reading layer is what makes a long question and a long answer readable without moving the
 * card. The cases that matter are the boundaries: at the end of the question the wheel must still
 * belong to the question, and a new offer must not leave the previous answer's scroll behind.
 */
class DialogueReadingStateTest {

    @Test
    void theQuestionScrollsWithinItsOwnBoundsAndStopsThere() {
        DialogueReadingState reading = new DialogueReadingState();
        reading.questionBounds(10, 3);
        assertEquals(7, reading.questionMaxScroll());
        assertTrue(reading.questionOverflows());
        assertTrue(reading.scrollQuestion(3));
        assertEquals(3, reading.questionScroll());
        assertFalse(reading.scrollQuestion(-9) && reading.questionScroll() != 0,
                "scrolling past the top lands on the top");
        assertEquals(0, reading.questionScroll());
        assertTrue(reading.questionBoundary(true));
        assertEquals(7, reading.questionScroll());
        // At the limit the offset stops changing. The renderer still consumes the event: that is
        // what stops a wheel at the end of the question from turning the answer page underneath.
        assertFalse(reading.scrollQuestion(1));
        assertEquals(7, reading.questionScroll());
    }

    @Test
    void aShorterQuestionCannotStayScrolledOffItsOwnEnd() {
        DialogueReadingState reading = new DialogueReadingState();
        reading.questionBounds(20, 3);
        reading.questionBoundary(true);
        assertEquals(17, reading.questionScroll());
        reading.questionBounds(4, 3);
        assertEquals(1, reading.questionScroll(), "the offset is re-clamped to the new text");
    }

    @Test
    void tabMovesBetweenTheRegionsAndSkipsTheFooterWhenThereIsNoPaging() {
        DialogueReadingState reading = new DialogueReadingState();
        assertEquals(DialogueReadingState.Region.RESPONSES, reading.region());
        assertTrue(reading.cycleRegion(false, true));
        assertEquals(DialogueReadingState.Region.FOOTER, reading.region());
        assertTrue(reading.cycleRegion(false, true));
        assertEquals(DialogueReadingState.Region.QUESTION, reading.region());
        assertTrue(reading.cycleRegion(true, true));
        assertEquals(DialogueReadingState.Region.FOOTER, reading.region());

        DialogueReadingState unpaged = new DialogueReadingState();
        assertTrue(unpaged.cycleRegion(false, false));
        assertEquals(DialogueReadingState.Region.QUESTION, unpaged.region());
        assertTrue(unpaged.cycleRegion(false, false));
        assertEquals(DialogueReadingState.Region.RESPONSES, unpaged.region());
    }

    @Test
    void readingAResponseIsATextRegionAndLeavingItIsNot() {
        DialogueReadingState reading = new DialogueReadingState();
        assertFalse(reading.readingText());
        assertTrue(reading.expand(4));
        assertTrue(reading.readingText());
        assertEquals(4, reading.expandedIndex());
        assertTrue(reading.expanded(4));
        assertFalse(reading.expand(4), "expanding the same response twice changes nothing");
        assertTrue(reading.collapse());
        assertFalse(reading.readingText());
        assertFalse(reading.collapse());
    }

    @Test
    void theViewportScrollsAsOneDocumentAndRevealsAFocusedRow() {
        DialogueReadingState reading = new DialogueReadingState();
        reading.responseBounds(300, 100);
        assertEquals(200, reading.responseMaxScroll());
        assertTrue(reading.responsesOverflow());
        // A row below the viewport is brought to its bottom edge, not to the top: the rows above it
        // stay where the player last saw them.
        assertTrue(reading.revealRow(140, 20, 40, 100));
        assertEquals(20, reading.responseScroll());
        assertFalse(reading.revealRow(60, 20, 40, 100), "a row already inside does not scroll");
        assertTrue(reading.revealRow(20, 20, 40, 100));
        assertEquals(0, reading.responseScroll());
    }

    @Test
    void aNewOfferDropsTheReadingPositionButKeepsTheRegion() {
        DialogueReadingState reading = new DialogueReadingState();
        reading.responseBounds(300, 100);
        reading.scrollResponses(120);
        reading.expand(2);
        // Reading a response puts the keyboard on the list; the player then tabbed to the question.
        reading.region(DialogueReadingState.Region.QUESTION);
        reading.questionBounds(10, 2);
        reading.questionBoundary(true);

        reading.offerChanged();
        assertEquals(DialogueReadingState.Region.QUESTION, reading.region(),
                "a keyboard reader is not thrown back to the list every turn");
        assertEquals(0, reading.questionScroll());
        assertEquals(0, reading.responseScroll());
        assertEquals(-1, reading.expandedIndex());

        reading.reset();
        assertEquals(DialogueReadingState.Region.RESPONSES, reading.region());
    }
}
