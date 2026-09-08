package dev.otectus.mcaconversations.scene;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.context.ContextKeys;
import dev.otectus.mcaconversations.context.ContextSnapshotBuilder;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.history.NarrativeValue;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SlotBinderTest {

    private static SceneDefinition villageScene() {
        JsonObject json = JsonParser.parseString("""
                {"purpose": "topic:place",
                 "context": {"required_slots": {"village": "location_token"}},
                 "route": {"question": "conversations.scene.place.test.respond",
                           "opening_beat": "place.test.open"}}
                """).getAsJsonObject();
        return SceneDefinition.fromJson("topic.place.test_village_slot", json);
    }

    private static ConversationContextSnapshot snapshot(Optional<String> villageName) {
        ContextSnapshotBuilder builder = new ContextSnapshotBuilder().clock(100, 4);
        return builder.put(ContextKeys.PLACE_VILLAGE_NAME, villageName).build();
    }

    @Test
    void theVillageSlotBindsTheLiveNameAsALiteral() {
        SlotBinder.Result result = SlotBinder.bind(villageScene(), Optional.empty(),
                snapshot(Optional.of("Ash Hollow")), null);
        assertTrue(result.bound());
        NarrativeValue value = result.slots().get("village");
        assertEquals(NarrativeValue.Kind.LITERAL, value.kind());
        assertEquals("Ash Hollow", value.raw());
    }

    @Test
    void aVillageWithNoNameLeavesTheSceneIneligible() {
        // All-or-nothing binding: the scene's declared fallback route says something honest instead
        // of the line naming a village that has no name.
        SlotBinder.Result result = SlotBinder.bind(villageScene(), Optional.empty(),
                snapshot(Optional.empty()), null);
        assertFalse(result.bound());
        assertEquals("village", result.failedSlot());
    }
}
