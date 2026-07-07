package dev.otectus.mcaconversations.compat.quests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestOpenDirectiveTest {

    private static JsonObject obj(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    @Test
    void defaultsToMenuMode() {
        QuestOpenDirective d = QuestOpenDirective.fromJson(obj("{}"));
        assertEquals(QuestOpenDirective.Mode.MENU, d.mode());
        assertTrue(d.quest().isEmpty());
    }

    @Test
    void parsesAcceptModeWithQuestId() {
        QuestOpenDirective d = QuestOpenDirective.fromJson(obj("{\"mode\":\"accept\",\"quest\":\"mcaquests:x\"}"));
        assertEquals(QuestOpenDirective.Mode.ACCEPT, d.mode());
        assertEquals("mcaquests:x", d.quest().orElseThrow());
    }

    @Test
    void acceptWithoutQuestIdIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> QuestOpenDirective.fromJson(obj("{\"mode\":\"accept\"}")));
    }

    @Test
    void unknownModeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> QuestOpenDirective.fromJson(obj("{\"mode\":\"close\"}")));
    }
}
