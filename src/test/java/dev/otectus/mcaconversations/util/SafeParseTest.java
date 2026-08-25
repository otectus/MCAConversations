package dev.otectus.mcaconversations.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.gossip.GossipQuery;
import dev.otectus.mcaconversations.template.SayDirective;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Regression guard for the world-creation crash class: MCA's dialogue loader has no error
 * containment, so our registered parsers must swallow malformed JSON (→ null → no-op adapter)
 * instead of throwing out of the datapack reload.
 */
class SafeParseTest {

    private static JsonObject json(String s) {
        return JsonParser.parseString(s).getAsJsonObject();
    }

    @Test
    void badSayDirectiveParsesToNullInsteadOfThrowing() {
        assertNull(SafeParse.orNull("conversations_say", "{}",
                () -> SayDirective.fromJson(json("{}"))));
        assertNull(SafeParse.orNull("conversations_say", "bad var",
                () -> SayDirective.fromJson(json("{\"phrase\": \"x\", \"vars\": [\"player_hat\"]}"))));
    }

    @Test
    void badGossipQueryParsesToNullInsteadOfThrowing() {
        assertNull(SafeParse.orNull("conversations_gossip", "bad type",
                () -> GossipQuery.fromJson(json("{\"types\": [\"scandal\"]}"))));
    }

    @Test
    void validJsonPassesThrough() {
        SayDirective directive = SafeParse.orNull("conversations_say", "ok",
                () -> SayDirective.fromJson(json("{\"phrase\": \"conversations.day.good\"}")));
        assertEquals("conversations.day.good", directive.phrase());
    }
}
