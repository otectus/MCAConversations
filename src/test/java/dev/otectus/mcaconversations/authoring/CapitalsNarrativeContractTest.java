package dev.otectus.mcaconversations.authoring;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Public facts and missing optional state must stay distinct at the authoring boundary. */
class CapitalsNarrativeContractTest {
    private static final List<String> TOPICS = List.of("crown", "court", "realm", "house",
            "news", "rumors", "village", "standing");

    @Test
    void everyCapitalSceneFailsClosedWhenTheIntegrationIsUnavailable() throws Exception {
        for (String topic : TOPICS) {
            for (var element : source(topic).getAsJsonArray("scenes")) {
                JsonObject scene = element.getAsJsonObject();
                boolean capital = List.of("crown", "court", "realm", "house").contains(topic)
                        || hasField(scene, "capital.present");
                if (capital) {
                    assertTrue(hasGate(scene, "capital.present", true), topic + "/" + scene.get("name"));
                    for (var condition : scene.getAsJsonArray("conditions")) {
                        JsonObject gate = condition.getAsJsonObject();
                        if (gate.has("field") && gate.get("field").getAsString().startsWith("capital.")) {
                            assertTrue(gate.has("unknown") && "fail".equals(gate.get("unknown").getAsString()),
                                    "Missing Capitals state must not be used as proof: " + topic + "/" + scene.get("name"));
                        }
                    }
                }
            }
        }
    }

    @Test
    void namedPeopleAndHouseWordsRequireTheirActualPresence() throws Exception {
        assertTrue(hasGate(scene("crown", "a_woman_on_the_seat"), "capital.sovereign_female", true));
        assertTrue(hasGate(scene("crown", "a_woman_on_the_seat"), "capital.sovereign_named", true));
        assertTrue(hasGate(scene("crown", "the_one_who_stands_beside"), "capital.consort_named", true));
        assertTrue(hasGate(scene("house", "the_words_we_say"), "capital.house_words_present", true));
        assertTrue(hasGate(scene("house", "no_name_above_my_door"), "capital.house_present", false));
    }

    @Test
    void personalPoliticalDisclosuresNeedBothTrustAndPrivateDelivery() throws Exception {
        for (JsonObject scene : List.of(scene("crown", "a_question_in_private"),
                scene("court", "the_cloak_they_took"))) {
            assertTrue("confidential".equals(scene.get("privacy").getAsString()));
            assertTrue(hasField(scene, "player.relationship_band"));
        }
        assertTrue(hasField(scene("crown", "a_question_in_private"), "time.days_since_first_met"));
    }

    @Test
    void publicChangeReactionsNeedAnObservedEventInsteadOfJustAnActiveCapital() throws Exception {
        assertTrue(hasField(scene("news", "the_seat_changed_hands"), "village.recent_event"));
        assertTrue(hasField(scene("realm", "after_word_of_peace"), "village.recent_event"));
        assertTrue(hasGate(scene("news", "they_raised_me_up"), "capital.title_changed", true));
    }

    @Test
    void sensitiveConversationsHaveDistinctContinuationPages() throws Exception {
        for (JsonObject scene : List.of(scene("court", "a_petition_worth_hearing"),
                scene("court", "a_decree_changes_lives"), scene("court", "where_a_levy_would_go"),
                scene("crown", "a_question_in_private"))) {
            JsonArray replies = scene.getAsJsonArray("replies");
            JsonArray continuation = replies.get(0).getAsJsonObject().getAsJsonObject("reaction").getAsJsonArray("replies");
            assertTrue(continuation != null && continuation.size() >= 2);
            assertFalse(continuation.get(0).getAsJsonObject().getAsJsonObject("label").get("en").getAsString()
                    .equals(continuation.get(1).getAsJsonObject().getAsJsonObject("label").get("en").getAsString()));
        }
    }

    private static JsonObject source(String topic) throws Exception {
        return JsonParser.parseString(Files.readString(Path.of("src/content/topics", topic + ".json"))).getAsJsonObject();
    }

    private static JsonObject scene(String topic, String name) throws Exception {
        for (var element : source(topic).getAsJsonArray("scenes")) {
            JsonObject scene = element.getAsJsonObject();
            if (name.equals(scene.get("name").getAsString())) return scene;
        }
        throw new AssertionError("Missing scene " + topic + "/" + name);
    }

    private static boolean hasField(JsonObject scene, String field) {
        if (!scene.has("conditions")) return false;
        for (var element : scene.getAsJsonArray("conditions")) {
            JsonObject condition = element.getAsJsonObject();
            if (condition.has("field") && field.equals(condition.get("field").getAsString())) return true;
        }
        return false;
    }

    private static boolean hasGate(JsonObject scene, String field, boolean value) {
        for (var element : scene.getAsJsonArray("conditions")) {
            JsonObject condition = element.getAsJsonObject();
            if (condition.has("field") && field.equals(condition.get("field").getAsString())) {
                return condition.has("is") && condition.get("is").getAsBoolean() == value
                        && condition.has("unknown") && "fail".equals(condition.get("unknown").getAsString());
            }
        }
        return false;
    }
}
