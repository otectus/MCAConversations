package dev.otectus.mcaconversations.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CivicContentContractTest {
    private static final Path SOURCE = Path.of("src/content/topics/guild_contact.json");

    @Test
    void civicTopicIsAdultDiscreetAndHasNoOpinionOrHeartMutation() throws Exception {
        JsonObject topic = JsonParser.parseString(Files.readString(SOURCE)).getAsJsonObject();
        assertTrue(topic.get("civic_contact").getAsBoolean());
        assertEquals(Set.of("adult"), strings(topic.getAsJsonArray("ages")));
        Set<String> actions = new HashSet<>();
        for (JsonElement sceneElement : topic.getAsJsonArray("scenes")) {
            JsonObject scene = sceneElement.getAsJsonObject();
            assertEquals("discreet", scene.get("privacy").getAsString());
            assertTrue(strings(scene.getAsJsonArray("integrations")).contains("ultima_kingdoms"));
            for (JsonElement replyElement : scene.getAsJsonArray("replies")) {
                JsonObject reply = replyElement.getAsJsonObject();
                assertFalse(reply.has("affection"));
                assertFalse(reply.has("disposition"));
                JsonObject reaction = reply.getAsJsonObject("reaction");
                assertEquals("discreet", reaction.get("privacy").getAsString());
                if (reply.has("civic_action")) actions.add(reply.get("civic_action").getAsString());
            }
        }
        assertEquals(Set.of("introduction", "commissions"), actions);
    }

    @Test
    void generatedTopicKeepsGateAndRequesterOnlyActionVocabulary() throws Exception {
        JsonObject catalog = JsonParser.parseString(Files.readString(Path.of(
                "src/main/resources/data/mcaconversations/conversation_catalog/topics.json")))
                .getAsJsonObject().getAsJsonObject("topics").getAsJsonObject("guild_contact");
        assertTrue(catalog.get("civic_contact").getAsBoolean());
        assertEquals(Set.of("adult"), strings(catalog.getAsJsonArray("ages")));

        String generated = Files.readString(Path.of(
                "src/main/resources/data/mcaconversations/dialogues/conversations.scene.guild_contact.introduction_ready.respond.json"))
                + Files.readString(Path.of(
                "src/main/resources/data/mcaconversations/dialogues/conversations.scene.guild_contact.commissions_ready.respond.json"));
        assertTrue(generated.contains("\"conversations_civic\""));
        assertFalse(generated.contains("\"positive\""));
        assertFalse(generated.contains("\"negative\""));
        assertFalse(generated.contains("conversations_affection_apply"));
        assertFalse(generated.contains("conversations_disposition_apply"));

        String registrar = Files.readString(Path.of(
                "src/main/java/dev/otectus/mcaconversations/compat/mca/ConversationsMcaRegistrar.java"));
        assertTrue(registrar.contains("CivicBridge.request(action, player, villager)"));
        assertTrue(registrar.contains("player.sendSystemMessage"));
    }

    private static Set<String> strings(JsonArray array) {
        Set<String> values = new HashSet<>();
        for (JsonElement value : array) values.add(value.getAsString());
        return Set.copyOf(values);
    }
}
