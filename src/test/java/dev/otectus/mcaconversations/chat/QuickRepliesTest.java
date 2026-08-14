package dev.otectus.mcaconversations.chat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Numbered quick-replies: the second, unambiguous way to name a stance in chat. */
class QuickRepliesTest {

    @Test
    @DisplayName("a bare number picks that choice, with or without the punctuation people type")
    void parsesBareNumbers() {
        assertEquals(OptionalInt.of(1), QuickReplies.parse("1", 3));
        assertEquals(OptionalInt.of(2), QuickReplies.parse("2.", 3));
        assertEquals(OptionalInt.of(3), QuickReplies.parse(" (3) ", 3));
        assertEquals(OptionalInt.of(2), QuickReplies.parse("#2", 3));
        assertEquals(OptionalInt.of(1), QuickReplies.parse("1)", 3));
    }

    @Test
    @DisplayName("a number outside the offered range is not a choice")
    void rejectsOutOfRange() {
        assertTrue(QuickReplies.parse("4", 3).isEmpty());
        assertTrue(QuickReplies.parse("0", 3).isEmpty());
        assertTrue(QuickReplies.parse("2", 0).isEmpty());
        assertTrue(QuickReplies.parse("99", 5).isEmpty());
    }

    @Test
    @DisplayName("ordinary speech containing a number is speech, not a choice")
    void doesNotHijackSpeech() {
        assertTrue(QuickReplies.parse("give me 2 minutes", 3).isEmpty());
        assertTrue(QuickReplies.parse("i have 3 sheep", 3).isEmpty());
        assertTrue(QuickReplies.parse("2 of them", 3).isEmpty());
        assertTrue(QuickReplies.parse("no", 3).isEmpty());
        assertTrue(QuickReplies.parse("", 3).isEmpty());
        assertTrue(QuickReplies.parse(null, 3).isEmpty());
    }

    @Test
    @DisplayName("the number maps to MCA's own offered order, so chat and the GUI agree")
    void mapsToOfferedOrder() {
        List<String> offered = List.of("empathize", "ask", "brush_off", "leave");
        assertEquals("empathize", QuickReplies.answerFor(offered, 1).orElseThrow());
        assertEquals("brush_off", QuickReplies.answerFor(offered, 3).orElseThrow());
        assertEquals("leave", QuickReplies.answerFor(offered, 4).orElseThrow());
        assertTrue(QuickReplies.answerFor(offered, 5).isEmpty());
        assertTrue(QuickReplies.answerFor(offered, 0).isEmpty());
        assertTrue(QuickReplies.answerFor(null, 1).isEmpty());
    }

    @Test
    @DisplayName("the options line lists the same button labels the GUI would draw")
    void rendersTheOfferedLabels() {
        var line = QuickReplies.optionsLine("conversations.topic.day.rough.respond",
                List.of("empathize", "ask", "leave")).orElseThrow();
        String flat = line.getString();
        assertTrue(flat.contains("[1]"), flat);
        assertTrue(flat.contains("[2]"), flat);
        assertTrue(flat.contains("[3]"), flat);
        assertTrue(flat.contains("dialogue.conversations.topic.day.rough.respond.empathize"),
                "labels are translatable keys the client resolves in its own locale: " + flat);
    }

    @Test
    @DisplayName("nothing worth numbering is not numbered")
    void staysQuietWhenThereIsNoChoice() {
        assertTrue(QuickReplies.optionsLine("conversations.topic.day.rough.respond", List.of()).isEmpty());
        assertTrue(QuickReplies.optionsLine("conversations.topic.day.rough.respond", List.of("leave")).isEmpty());
        assertTrue(QuickReplies.optionsLine(null, List.of("a", "b")).isEmpty());
    }

    @Test
    @DisplayName("a page never numbers more choices than MCA will show")
    void capsAtThePageSize() {
        var line = QuickReplies.optionsLine("q", List.of("a", "b", "c", "d", "e", "f", "g")).orElseThrow();
        assertTrue(line.getString().contains("[" + QuickReplies.MAX_OPTIONS + "]"));
        assertTrue(!line.getString().contains("[" + (QuickReplies.MAX_OPTIONS + 1) + "]"));
    }
}
