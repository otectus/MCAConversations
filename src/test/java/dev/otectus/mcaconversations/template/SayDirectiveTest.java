package dev.otectus.mcaconversations.template;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SayDirectiveTest {

    private static JsonObject json(String s) {
        return JsonParser.parseString(s).getAsJsonObject();
    }

    @Test
    void parsesPhraseAndVarsInOrder() {
        SayDirective d = SayDirective.fromJson(json(
                "{\"phrase\": \"conversations.us.firstmet\", \"vars\": [\"spouse_name\", \"village_name\"]}"));
        assertEquals("conversations.us.firstmet", d.phrase());
        assertEquals(List.of(TemplateVariable.SPOUSE_NAME, TemplateVariable.VILLAGE_NAME), d.vars());
    }

    @Test
    void professionNameRoundTrips() {
        SayDirective d = SayDirective.fromJson(json(
                "{\"phrase\": \"conversations.work.generic\", \"vars\": [\"profession_name\"]}"));
        assertEquals(List.of(TemplateVariable.PROFESSION_NAME), d.vars());
        assertEquals("mcaconversations.fallback.profession", TemplateVariable.PROFESSION_NAME.fallbackKey());
    }

    @Test
    void varsOptional() {
        SayDirective d = SayDirective.fromJson(json("{\"phrase\": \"conversations.day.good\"}"));
        assertTrue(d.vars().isEmpty());
    }

    @Test
    void rejectsMissingPhraseAndUnknownVars() {
        assertThrows(IllegalArgumentException.class, () -> SayDirective.fromJson(json("{}")));
        assertThrows(IllegalArgumentException.class, () -> SayDirective.fromJson(json(
                "{\"phrase\": \"x\", \"vars\": [\"player_hat\"]}")));
    }

    @Test
    void allEnumVarsResolveByJsonName() {
        for (TemplateVariable v : TemplateVariable.values()) {
            assertEquals(v, TemplateVariable.byJsonName(v.jsonName()).orElseThrow());
        }
    }
}
