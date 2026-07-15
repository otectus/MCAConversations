package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.IntentMatcher.Scored;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Formatting for {@code /conversations chat debug <msg>} (Phase 4 scoring introspection). */
class DebugScoreFormatTest {

    private static Scored topic(String id, double score, boolean ctx) {
        return new Scored(id, score, "conversations.cat.chitchat", "day", null, "topics", ctx);
    }

    @Test
    void emptyRankingSaysSo() {
        assertEquals(List.of("no intent scored above zero"),
                ChatModeDispatcher.formatRanked(List.of(), Set.of(), 5));
    }

    @Test
    void ranksNumberedWithBindingAndScore() {
        List<String> out = ChatModeDispatcher.formatRanked(
                List.of(topic("chitchat.day", 0.955, false)), Set.of("chitchat.day"), 5);
        assertEquals(1, out.size());
        assertEquals("1. chitchat.day 0.955 (conversations.cat.chitchat/day)", out.get(0));
    }

    @Test
    void limitCapsTheList() {
        List<Scored> five = List.of(
                topic("a", 0.9, false), topic("b", 0.8, false), topic("c", 0.7, false),
                topic("d", 0.6, false), topic("e", 0.5, false));
        assertEquals(3, ChatModeDispatcher.formatRanked(five, Set.of("a", "b", "c", "d", "e"), 3).size());
    }

    @Test
    void contextAndGateMarkersAppear() {
        Scored ctxScoped = new Scored("fears.press", 0.80, "conversations.fears", "press", null, "topics", true);
        Scored gated = new Scored("us.happy", 0.70, "conversations.us", "happy", null, "topics", false);
        List<String> out = ChatModeDispatcher.formatRanked(List.of(ctxScoped, gated), Set.of("fears.press"), 5);
        assertTrue(out.get(0).endsWith("[ctx]"), out.get(0));
        assertTrue(out.get(1).endsWith("[gated]"), out.get(1));
    }

    @Test
    void systemIntentShowsItsRoute() {
        Scored greet = new Scored("chatmode.greeting", 0.9, null, null, "greet", null, false);
        List<String> out = ChatModeDispatcher.formatRanked(List.of(greet), Set.of("chatmode.greeting"), 5);
        assertTrue(out.get(0).contains("(system:greet)"), out.get(0));
    }
}
