package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingdomGateSpecTest {

    @Test
    void inlineGateAndEveryStandingScopeRoundTrip() {
        for (String scope : List.of("local", "faction", "effective", "either", "both")) {
            JsonObject json = JsonParser.parseString("""
                    {"subject":"giver_origin","include":["ultima_kingdoms:madera"],
                     "exclude":["ultima_kingdoms:yew"],"when_unknown":"allow",
                     "standing":{"scope":"%s","min":-25,"max":100}}
                    """.formatted(scope)).getAsJsonObject();
            KingdomGateSpec parsed = KingdomGateSpec.fromJson(json);
            assertEquals(parsed, KingdomGateSpec.fromJson(parsed.toJson()));
            assertEquals(scope, parsed.standing().orElseThrow().scope().serializedName());
        }
    }

    @Test
    void absentUltimaOnlyHonorsExplicitInlineUnknownAllow() {
        assertFalse(KingdomGateSpec.fromJson(JsonParser.parseString("{}"))
                .allowsWhenUnavailable());
        assertTrue(KingdomGateSpec.fromJson(JsonParser.parseString("{\"when_unknown\":\"allow\"}"))
                .allowsWhenUnavailable());
        assertFalse(KingdomGateSpec.fromJson(JsonParser.parseString(
                        "{\"gate\":\"my_pack:northern_scholars\"}"))
                .allowsWhenUnavailable());
        assertFalse(KingdomGateSpec.fromJson(JsonParser.parseString(
                        "{\"when_unknown\":\"allow\",\"standing\":{\"scope\":\"local\",\"min\":1}}"))
                .allowsWhenUnavailable());
    }

    @Test
    void malformedRestrictionsFailClosedAtParse() {
        assertThrows(IllegalArgumentException.class, () -> KingdomGateSpec.fromJson(
                JsonParser.parseString("{\"include\":[\"Not Canonical\"]}")));
        assertThrows(IllegalArgumentException.class, () -> KingdomGateSpec.fromJson(
                JsonParser.parseString("{\"include\":[\"a:b\",\"a:b\"]}")));
        assertThrows(IllegalArgumentException.class, () -> KingdomGateSpec.fromJson(
                JsonParser.parseString("{\"unknown_field\":true}")));
        assertThrows(IllegalArgumentException.class, () -> KingdomGateSpec.fromJson(
                JsonParser.parseString("{\"gate\":\"a:b\",\"include\":[\"a:b\"]}")));
        assertThrows(IllegalArgumentException.class, () -> KingdomGateSpec.fromJson(
                JsonParser.parseString("{\"gate\":\"a:b\",\"standing\":{\"min\":1}}")));
    }
}
