package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Catalog parsing and the reverse lookups lint and the runtime both depend on (plan §4.5). */
class ConversationCatalogTest {

    private static JsonObject json(String raw) {
        return JsonParser.parseString(raw).getAsJsonObject();
    }

    private static final String DAY = """
            {
              "entry": {"question": "conversations.cat.chitchat", "answer": "day"},
              "depth": "quick",
              "return_question": "conversations.cat.chitchat",
              "ages": ["toddler", "child", "teen", "adult"],
              "required_stance_families": ["empathy", "curiosity", "exit"]
            }
            """;

    private static final String FEARS = """
            {
              "entry": {"question": "conversations.cat.personal", "answer": "fears"},
              "depth": "deep",
              "return_question": "conversations.cat.personal",
              "ages": ["teen", "adult"],
              "required_stance_families": ["empathy", "restraint", "exit"],
              "arc": {"id": "fears", "max_stage": 3},
              "milestones": ["fears.revelation"],
              "exclusive_groups": {"fears.support": ["pledged", "stepped_back"]}
            }
            """;

    @Test
    @DisplayName("a well-formed topic parses with its declared shape")
    void parsesTopic() {
        TopicEntry day = TopicEntry.fromJson("day", json(DAY));
        assertEquals(DepthClass.QUICK, day.depth());
        assertEquals("conversations.cat.chitchat", day.entryQuestion());
        assertEquals("day", day.entryAnswer());
        assertTrue(day.chatRequired(), "chat parity is required unless a topic opts out");
        assertTrue(day.allowsAge("toddler"));
        assertFalse(day.allowsAge("baby"));
        assertTrue(day.arc().isEmpty());
    }

    @Test
    @DisplayName("arc, milestone and exclusive declarations are read and bounded")
    void parsesDurableState() {
        TopicEntry fears = TopicEntry.fromJson("fears", json(FEARS));
        assertEquals(3, fears.arc().orElseThrow().maxStage());
        assertEquals("fears", fears.arc().orElseThrow().id());
        assertTrue(fears.milestones().contains("fears.revelation"));
        assertEquals(2, fears.exclusiveGroups().get("fears.support").size());
    }

    @Test
    @DisplayName("a topic without a graceful exit is rejected outright")
    void requiresAnExitStance() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                TopicEntry.fromJson("day", json(DAY.replace("\"exit\"", "\"humor\""))));
        assertTrue(e.getMessage().contains("exit"));
    }

    @Test
    @DisplayName("unknown depth, age group and stance family are all refused")
    void rejectsUnknownVocabulary() {
        assertThrows(IllegalArgumentException.class, () ->
                TopicEntry.fromJson("day", json(DAY.replace("\"quick\"", "\"epic\""))));
        assertThrows(IllegalArgumentException.class, () ->
                TopicEntry.fromJson("day", json(DAY.replace("\"toddler\"", "\"baby\""))));
        assertThrows(IllegalArgumentException.class, () ->
                TopicEntry.fromJson("day", json(DAY.replace("\"curiosity\"", "\"vibes\""))));
    }

    @Test
    @DisplayName("an arc must declare a bound inside the global stage ceiling")
    void rejectsUnboundedArcs() {
        assertThrows(IllegalArgumentException.class, () ->
                TopicEntry.fromJson("fears", json(FEARS.replace("\"max_stage\": 3", "\"max_stage\": 0"))));
        assertThrows(IllegalArgumentException.class, () ->
                TopicEntry.fromJson("fears", json(FEARS.replace("\"max_stage\": 3", "\"max_stage\": 99"))));
    }

    @Test
    @DisplayName("an exclusive group with fewer than two members is not exclusive")
    void rejectsDegenerateExclusiveGroups() {
        assertThrows(IllegalArgumentException.class, () -> TopicEntry.fromJson("fears",
                json(FEARS.replace("[\"pledged\", \"stepped_back\"]", "[\"pledged\"]"))));
    }

    @Test
    @DisplayName("ids that are not bare dotted lowercase are refused")
    void rejectsMalformedIds() {
        assertThrows(IllegalArgumentException.class, () ->
                TopicEntry.fromJson("fears", json(FEARS.replace("\"fears.revelation\"", "\"Fears Revelation\""))));
        assertThrows(IllegalArgumentException.class, () ->
                TopicEntry.fromJson("Day Topic", json(DAY)));
    }

    @Test
    @DisplayName("the catalog indexes topics by starter, arc, milestone and exclusive group")
    void reverseLookups() {
        ConversationCatalog catalog = ConversationCatalog.build(List.of(
                TopicEntry.fromJson("day", json(DAY)),
                TopicEntry.fromJson("fears", json(FEARS))));

        assertEquals(2, catalog.size());
        assertEquals("day", catalog.byStarter("conversations.cat.chitchat", "day").orElseThrow().id());
        assertTrue(catalog.byStarter("conversations.cat.chitchat", "weather").isEmpty());
        assertEquals("fears", catalog.byArc("fears").orElseThrow().id());
        assertEquals(3, catalog.arcMaxStage("fears").orElseThrow());
        assertTrue(catalog.arcMaxStage("secrets").isEmpty());
        assertEquals("fears", catalog.byMilestone("fears.revelation").orElseThrow().id());
        assertTrue(catalog.isExclusiveMember("fears.support", "pledged"));
        assertFalse(catalog.isExclusiveMember("fears.support", "betrayed"));
    }

    @Test
    @DisplayName("depth classes carry the budgets and depth floors the content must respect")
    void depthClassInvariants() {
        assertEquals(2, DepthClass.QUICK.positiveBudget());
        assertEquals(3, DepthClass.QUICK.negativeBudget());
        assertEquals(2, DepthClass.QUICK.minDecisions());
        assertEquals(3, DepthClass.DEEP.minDecisions());
        for (DepthClass depth : DepthClass.values()) {
            assertTrue(depth.negativeBudget() >= depth.positiveBudget(),
                    depth + ": losing must never be harder to reach than gaining");
            assertTrue(depth.minDecisions() <= DepthClass.MAX_DECISIONS);
            assertEquals(depth, DepthClass.byKey(depth.key()).orElseThrow());
        }
    }
}
