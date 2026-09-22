package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.scene.SceneDefinition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Social metadata on scenes (Stability spec §9.3): declared, validated, and enforced as eligibility. */
class SocialContractTest {

    private static SocialContract parse(String raw) {
        return SocialContract.fromJson(JsonParser.parseString(raw));
    }

    @Test
    @DisplayName("each claim becomes the server-side fact it needs")
    void claimsBecomeConditions() {
        List<JsonObject> conditions = parse("""
                {"contact": ["recognized"], "attitudes": ["warm"],
                 "claims": ["personal_friendship", "romantic_relationship", "family_tie", "unresolved_rupture"],
                 "requires_known_player_name": true}""").conditions();
        String text = conditions.toString();
        assertTrue(text.contains("\"field\":\"social.contact\""), text);
        assertTrue(text.contains("\"field\":\"player.is_spouse\""), text);
        assertTrue(text.contains("\"field\":\"player.is_family\""), text);
        assertTrue(text.contains("\"field\":\"narrative.rupture\""), text);
        assertTrue(conditions.stream().allMatch(c -> "fail".equals(c.get("unknown").getAsString())),
                "an unknown fact never passes a social requirement");
        assertEquals(List.of(), parse("{}").conditions(), "no contract, no restriction");
    }

    @Test
    @DisplayName("a misspelled requirement is refused rather than silently ignored")
    void strict() {
        assertThrows(IllegalArgumentException.class, () -> parse("{\"claims\": [\"saved_the_village\"]}"));
        assertThrows(IllegalArgumentException.class, () -> parse("{\"contact\": [\"friend\"]}"));
        assertThrows(IllegalArgumentException.class, () -> parse("{\"attitudes\": [\"smitten\"]}"));
        assertThrows(IllegalArgumentException.class, () -> parse("{\"requires_known_player_name\": \"yes\"}"));
        assertThrows(IllegalArgumentException.class, () -> parse("{\"privacy\": \"public\"}"));
    }

    @Test
    @DisplayName("a scene's social block is hard eligibility, and a shared episode needs an episode behind it")
    void scenesEnforceIt() {
        SceneDefinition scene = SceneDefinition.fromJson("topic.test.named", JsonParser.parseString("""
                {"purpose": "topic:test", "shape": "observe",
                 "context": {"social": {"requires_known_player_name": true}},
                 "route": {"question": "q", "opening_beat": "b"}}""").getAsJsonObject());
        assertEquals(1, scene.contextConditions().size());
        assertThrows(IllegalArgumentException.class, () -> SceneDefinition.fromJson("topic.test.claim",
                JsonParser.parseString("""
                {"purpose": "topic:test", "shape": "observe",
                 "context": {"social": {"claims": ["shared_episode"]}},
                 "route": {"question": "q", "opening_beat": "b"}}""").getAsJsonObject()));
    }
}
