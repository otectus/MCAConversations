package dev.otectus.mcaconversations.disposition;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DispositionQueryTest {

    private static JsonObject json(String s) {
        return new Gson().fromJson(s, JsonObject.class);
    }

    @Test
    void parsesAxisWithMinAndMax() {
        DispositionQuery query = DispositionQuery.fromJson(json("{\"axis\": \"trust\", \"min\": 35, \"max\": 60}"));
        assertEquals(DispositionAxis.TRUST, query.axis());
        assertFalse(query.matches(34));
        assertTrue(query.matches(35));
        assertTrue(query.matches(60));
        assertFalse(query.matches(61));
    }

    @Test
    void minAndMaxDefaultToTheAxisBounds() {
        DispositionQuery below = DispositionQuery.fromJson(json("{\"axis\": \"warmth\", \"max\": 29}"));
        assertTrue(below.matches(-100));
        assertTrue(below.matches(29));
        assertFalse(below.matches(30));

        DispositionQuery above = DispositionQuery.fromJson(json("{\"axis\": \"familiarity\", \"min\": 60}"));
        assertTrue(above.matches(100));
        assertFalse(above.matches(59));
    }

    @Test
    void unknownAxisAndMissingAxisThrowForSafeParseToContain() {
        assertThrows(IllegalArgumentException.class,
                () -> DispositionQuery.fromJson(json("{\"axis\": \"charisma\"}")));
        assertThrows(IllegalArgumentException.class,
                () -> DispositionQuery.fromJson(json("{\"min\": 10}")));
    }

    @Test
    void invertedRangeThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> DispositionQuery.fromJson(json("{\"axis\": \"trust\", \"min\": 50, \"max\": 10}")));
    }
}
