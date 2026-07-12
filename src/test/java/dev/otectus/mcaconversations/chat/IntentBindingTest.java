package dev.otectus.mcaconversations.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Structural validation of the §7.1 intent schema; malformed entries throw so SafeParse can skip them. */
class IntentBindingTest {

    private static JsonObject json(String s) {
        return new Gson().fromJson(s, JsonObject.class);
    }

    private static IntentBinding parse(String id, String s) {
        return IntentBinding.fromJson(id, json(s));
    }

    @Test
    void parsesTopicIntent() {
        IntentBinding b = parse("chitchat.day", """
                {"question":"conversations.cat.chitchat","answer":"day",
                 "keywords":{"day":1.5,"today":1.2},"requiresAny":["day","today"],
                 "phrases":["how * day"],"antiKeywords":["birthday"],"bigrams":["your day"],
                 "category":"topics"}""");
        assertFalse(b.isSystem());
        assertEquals("conversations.cat.chitchat", b.question());
        assertEquals("day", b.answer());
        assertEquals(2, b.keywords().size());
        assertEquals(1.5, b.keywords().get("day"));
        assertEquals("topics", b.category());
        assertTrue(b.antiKeywords().contains("birthday"));
    }

    @Test
    void parsesSystemIntent() {
        IntentBinding b = parse("chatmode.greeting",
                "{\"system\":\"greet\",\"keywords\":{\"hello\":1.5},\"phrases\":[\"good morning\"]}");
        assertTrue(b.isSystem());
        assertEquals("greet", b.system());
        assertEquals(null, b.question());
    }

    @Test
    void parsesContextScopedIntent() {
        IntentBinding b = parse("fears.press", """
                {"context":"conversations.fears","question":"conversations.fears","answer":"press",
                 "keywords":{"face":1.2,"stand":1.2},"phrases":["stand with you"]}""");
        assertEquals("conversations.fears", b.context());
    }

    @Test
    void questionAndSystemAreMutuallyExclusive() {
        assertThrows(IllegalArgumentException.class, () -> parse("bad",
                "{\"question\":\"q\",\"answer\":\"a\",\"system\":\"greet\",\"keywords\":{\"x\":1}}"));
    }

    @Test
    void neitherQuestionNorSystemThrows() {
        assertThrows(IllegalArgumentException.class, () -> parse("bad", "{\"keywords\":{\"x\":1}}"));
    }

    @Test
    void topicWithoutAnswerThrows() {
        assertThrows(IllegalArgumentException.class, () -> parse("bad",
                "{\"question\":\"q\",\"keywords\":{\"x\":1}}"));
    }

    @Test
    void noEvidenceThrows() {
        assertThrows(IllegalArgumentException.class, () -> parse("bad",
                "{\"question\":\"q\",\"answer\":\"a\"}"));
    }

    @Test
    void keywordsMustBeObject() {
        assertThrows(IllegalArgumentException.class, () -> parse("bad",
                "{\"question\":\"q\",\"answer\":\"a\",\"keywords\":[\"x\"]}"));
    }

    @Test
    void keywordWeightRangeIsEnforced() {
        assertThrows(IllegalArgumentException.class, () -> parse("bad",
                "{\"question\":\"q\",\"answer\":\"a\",\"keywords\":{\"x\":0}}"));
        assertThrows(IllegalArgumentException.class, () -> parse("bad",
                "{\"question\":\"q\",\"answer\":\"a\",\"keywords\":{\"x\":99}}"));
    }

    @Test
    void arrayFieldsMustBeArrays() {
        assertThrows(IllegalArgumentException.class, () -> parse("bad",
                "{\"question\":\"q\",\"answer\":\"a\",\"keywords\":{\"x\":1},\"phrases\":\"nope\"}"));
    }

    @Test
    void blankIdThrows() {
        assertThrows(IllegalArgumentException.class, () -> parse("  ",
                "{\"question\":\"q\",\"answer\":\"a\",\"keywords\":{\"x\":1}}"));
    }
}
