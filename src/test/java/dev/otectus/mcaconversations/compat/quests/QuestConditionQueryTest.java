package dev.otectus.mcaconversations.compat.quests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.util.SafeParse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestConditionQueryTest {

    private static JsonObject obj(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    @Test
    void defaultsToThisVillagerAndMinOne() {
        QuestConditionQuery q = QuestConditionQuery.fromJson(obj("{}"));
        assertTrue(q.thisVillagerOnly());
        assertEquals(1, q.min());
    }

    @Test
    void parsesScopeAndMin() {
        QuestConditionQuery q = QuestConditionQuery.fromJson(obj("{\"scope\":\"any\",\"min\":3}"));
        assertTrue(!q.thisVillagerOnly());
        assertEquals(3, q.min());
    }

    @Test
    void rejectsUnknownScopeAndNegativeMin() {
        assertThrows(IllegalArgumentException.class, () -> QuestConditionQuery.fromJson(obj("{\"scope\":\"nearby\"}")));
        assertThrows(IllegalArgumentException.class, () -> QuestConditionQuery.fromJson(obj("{\"min\":-1}")));
    }

    @Test
    void safeParseSwallowsBadScopeToNull() {
        // Mirrors the registrar: a bad value must degrade to null (never-matching), not throw into MCA's
        // containment-free Dialogues loader.
        JsonObject bad = obj("{\"scope\":\"nearby\"}");
        QuestConditionQuery q = SafeParse.orNull("conversations_quest_available", bad,
                () -> QuestConditionQuery.fromJson(bad));
        assertNull(q);
    }
}
