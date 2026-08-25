package dev.otectus.mcaconversations.disposition;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DispositionApplyTest {

    private static JsonObject json(String s) {
        return new Gson().fromJson(s, JsonObject.class);
    }

    @Test
    void parsesTopicAndDeltas() {
        DispositionApply apply = DispositionApply.fromJson(json(
                "{\"topic\": \"fears.challenge\", \"deltas\": {\"respect\": 6, \"tension\": -2}}"));
        assertEquals("fears.challenge", apply.topic());
        assertEquals(6, apply.deltas().get(DispositionAxis.RESPECT));
        assertEquals(-2, apply.deltas().get(DispositionAxis.TENSION));
        assertEquals(2, apply.deltas().size());
    }

    @Test
    void missingTopicOrEmptyDeltasThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> DispositionApply.fromJson(json("{\"deltas\": {\"trust\": 1}}")));
        assertThrows(IllegalArgumentException.class,
                () -> DispositionApply.fromJson(json("{\"topic\": \"t\", \"deltas\": {}}")));
        assertThrows(IllegalArgumentException.class,
                () -> DispositionApply.fromJson(json("{\"topic\": \"t\"}")));
    }

    @Test
    void unknownAxisThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> DispositionApply.fromJson(json("{\"topic\": \"t\", \"deltas\": {\"luck\": 1}}")));
    }

    @Test
    void oversizedDeltaThrows() {
        // Content discipline: single-conversation swings stay small; the lint pins this too.
        assertThrows(IllegalArgumentException.class,
                () -> DispositionApply.fromJson(json("{\"topic\": \"t\", \"deltas\": {\"trust\": 11}}")));
        assertThrows(IllegalArgumentException.class,
                () -> DispositionApply.fromJson(json("{\"topic\": \"t\", \"deltas\": {\"trust\": -11}}")));
    }
}
