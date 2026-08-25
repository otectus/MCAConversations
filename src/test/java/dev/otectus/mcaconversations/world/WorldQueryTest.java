package dev.otectus.mcaconversations.world;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorldQueryTest {

    private static JsonObject obj(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    @Test
    void matchesTheRequestedBucket() {
        WorldQuery q = WorldQuery.fromJson(obj("{\"is\": \"rain\"}"));
        assertTrue(q.matches("rain"));
        assertFalse(q.matches("clear"));
        assertFalse(q.matches("storm"));
    }

    @Test
    void parsingIsCaseInsensitive() {
        assertTrue(WorldQuery.fromJson(obj("{\"is\": \"STORM\"}")).matches("storm"));
    }

    @Test
    void emptyOrMissingTargetMatchesNothing() {
        assertFalse(WorldQuery.fromJson(obj("{}")).matches("clear"));
        assertFalse(WorldQuery.fromJson(obj("{\"is\": \"\"}")).matches(""));
    }
}
