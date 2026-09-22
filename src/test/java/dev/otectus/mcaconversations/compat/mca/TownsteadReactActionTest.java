package dev.otectus.mcaconversations.compat.mca;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** {@code conversations_townstead_react}: a shipped semantic, or an explicit reaction id. */
class TownsteadReactActionTest {

    @Test
    @DisplayName("a semantic names its shipped reaction; an explicit id keeps its own; junk is refused")
    void parses() {
        var warm = ConversationsMcaRegistrar.reactionRequest(JsonParser.parseString("\"warm\""));
        assertEquals("mcaconversations:conversation_warm", warm.reaction().toString());
        assertEquals("warm", warm.semantic());

        var object = ConversationsMcaRegistrar.reactionRequest(JsonParser.parseString("{\"semantic\": \"repair\"}"));
        assertEquals("mcaconversations:conversation_repair", object.reaction().toString());

        var custom = ConversationsMcaRegistrar.reactionRequest(
                JsonParser.parseString("{\"reaction\": \"mypack:bow\", \"semantic\": \"respect\"}"));
        assertEquals("mypack:bow", custom.reaction().toString());
        assertEquals("respect", custom.semantic());

        assertThrows(IllegalArgumentException.class,
                () -> ConversationsMcaRegistrar.reactionRequest(JsonParser.parseString("\"swoon\"")));
        assertThrows(IllegalArgumentException.class,
                () -> ConversationsMcaRegistrar.reactionRequest(JsonParser.parseString("{\"reaction\": \"bow\"}")));
        assertThrows(IllegalArgumentException.class,
                () -> ConversationsMcaRegistrar.reactionRequest(JsonParser.parseString("{\"semantic\": \"warm\", \"x\": 1}")));
    }
}
