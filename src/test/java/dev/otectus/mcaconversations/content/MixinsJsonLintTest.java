package dev.otectus.mcaconversations.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Guards the mixin config: both hooks registered, soft-fail default. */
class MixinsJsonLintTest {

    @Test
    void mixinConfigListsBothMixinsWithSoftFailDefault() throws IOException {
        JsonObject config = JsonParser.parseString(
                Files.readString(Path.of("src/main/resources/mcaconversations.mixins.json"))).getAsJsonObject();

        JsonArray mixins = config.getAsJsonArray("mixins");
        List<String> names = new ArrayList<>();
        mixins.forEach(e -> names.add(e.getAsString()));
        assertTrue(names.contains("BreedableRelationshipMixin"), "gift-detection mixin missing");
        assertTrue(names.contains("DialoguesMixin"), "chat-redirect mixin missing");
        assertTrue(names.contains("NetworkHandlerMixin"), "chat-mode delivery-redirect mixin missing");

        // require 0 so an MCA method rename degrades to vanilla behavior instead of crashing.
        assertEquals(0, config.getAsJsonObject("injectors").get("defaultRequire").getAsInt());
        assertEquals("dev.otectus.mcaconversations.mixin", config.get("package").getAsString());
    }
}
