package dev.otectus.mcaconversations.check;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckDefinitionTest {

    private static JsonObject json(String s) {
        return new Gson().fromJson(s, JsonObject.class);
    }

    @Test
    void parsesTheFullShape() {
        CheckDefinition check = CheckDefinition.fromJson(json(
                "{\"id\": \"fears.challenge\", \"tier\": \"crit\", \"axis\": \"trust\", \"difficulty\": 45}"));
        assertEquals("fears.challenge", check.id());
        assertEquals(CheckTier.CRIT, check.tier());
        assertEquals(DispositionAxis.TRUST, check.axis());
        assertEquals(45, check.difficulty());
    }

    @Test
    void everyTierKeyParses() {
        for (CheckTier tier : CheckTier.values()) {
            assertEquals(tier, CheckDefinition.fromJson(json(
                    "{\"id\": \"t\", \"tier\": \"" + tier.key() + "\", \"axis\": \"warmth\", \"difficulty\": 10}")).tier());
        }
    }

    @Test
    void missingOrUnknownFieldsThrowForSafeParseToContain() {
        assertThrows(IllegalArgumentException.class, () -> CheckDefinition.fromJson(json(
                "{\"tier\": \"crit\", \"axis\": \"trust\", \"difficulty\": 45}")));
        assertThrows(IllegalArgumentException.class, () -> CheckDefinition.fromJson(json(
                "{\"id\": \"t\", \"axis\": \"trust\", \"difficulty\": 45}")));
        assertThrows(IllegalArgumentException.class, () -> CheckDefinition.fromJson(json(
                "{\"id\": \"t\", \"tier\": \"epic\", \"axis\": \"trust\", \"difficulty\": 45}")));
        assertThrows(IllegalArgumentException.class, () -> CheckDefinition.fromJson(json(
                "{\"id\": \"t\", \"tier\": \"crit\", \"difficulty\": 45}")));
        assertThrows(IllegalArgumentException.class, () -> CheckDefinition.fromJson(json(
                "{\"id\": \"t\", \"tier\": \"crit\", \"axis\": \"charisma\", \"difficulty\": 45}")));
    }

    @Test
    void difficultyMustBeSane() {
        assertThrows(IllegalArgumentException.class, () -> CheckDefinition.fromJson(json(
                "{\"id\": \"t\", \"tier\": \"crit\", \"axis\": \"trust\", \"difficulty\": -1}")));
        assertThrows(IllegalArgumentException.class, () -> CheckDefinition.fromJson(json(
                "{\"id\": \"t\", \"tier\": \"crit\", \"axis\": \"trust\", \"difficulty\": 101}")));
        assertThrows(IllegalArgumentException.class, () -> CheckDefinition.fromJson(json(
                "{\"id\": \"t\", \"tier\": \"crit\", \"axis\": \"trust\"}")));
    }
}
