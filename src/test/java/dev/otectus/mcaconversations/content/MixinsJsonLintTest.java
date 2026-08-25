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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/** Guards the mixin config: every hook registered on the right side, soft-fail default. */
class MixinsJsonLintTest {

    @Test
    void mixinConfigListsEveryHookWithSoftFailDefault() throws IOException {
        JsonObject config = JsonParser.parseString(
                Files.readString(TestPaths.of("src/main/resources/mcaconversations.mixins.json"))).getAsJsonObject();

        JsonArray mixins = config.getAsJsonArray("mixins");
        List<String> names = new ArrayList<>();
        mixins.forEach(e -> names.add(e.getAsString()));
        assertTrue(names.contains("BreedableRelationshipMixin"), "gift-detection mixin missing");
        assertTrue(names.contains("DialoguesMixin"), "chat-redirect mixin missing");
        assertTrue(names.contains("InteractionDialogueMessageMixin"), "submission-guard mixin missing");
        // Renamed from NetworkHandlerMixin: MCA 1.21.1 replaced cobalt NetworkHandler with Network.
        assertTrue(names.contains("McaNetworkMixin"), "chat-mode delivery-redirect mixin missing");
        assertTrue(names.contains("PlayerLegacyDataMixin"), "ForgeCaps migration mixin missing");
        assertTrue(names.contains("QuestionMixin"), "hub-button visibility mixin missing");

        JsonArray clientMixins = config.getAsJsonArray("client");
        List<String> clientNames = new ArrayList<>();
        clientMixins.forEach(e -> clientNames.add(e.getAsString()));
        assertTrue(clientNames.contains("client.MCAClientMixin"), "overlay-locale mixin missing");

        // VillagerMessageMixin is gone for good. On 1.20.1 MCA's VillagerMessage carried the line as
        // JSON and re-parsed it per read, so the UI and the chat copy drew different random variants;
        // the 1.21.1 payload is a record of Component objects and MCA passes the same instance to
        // both, so the bug it worked around cannot occur and the constructors it hooked no longer
        // exist. Re-adding it would fail to apply.
        assertFalse(clientNames.contains("client.VillagerMessageMixin"),
                "VillagerMessageMixin is obsolete on 1.21.1 and must not be re-listed");
        assertFalse(names.contains("client.VillagerMessageMixin"));

        // require 0 so an MCA method rename degrades to vanilla behavior instead of crashing.
        // (BreedableRelationshipMixin overrides this with require = 1 in its own annotation: a
        // silently missing gift hook reads as a data bug rather than a missing injection.)
        assertEquals(0, config.getAsJsonObject("injectors").get("defaultRequire").getAsInt());
        assertEquals("dev.otectus.mcaconversations.mixin", config.get("package").getAsString());
    }

    @Test
    void mixinConfigTargetsJava21AndShipsNoRefmap() throws IOException {
        JsonObject config = JsonParser.parseString(
                Files.readString(TestPaths.of("src/main/resources/mcaconversations.mixins.json"))).getAsJsonObject();

        assertEquals("JAVA_21", config.get("compatibilityLevel").getAsString(),
                "Minecraft 1.21.1 runs on Java 21");
        assertTrue(config.get("required").getAsBoolean());
        // NeoForge 1.21.1 runs official Mojang names in both dev and production, so no refmap is
        // generated. Naming one that will never exist makes Mixin log a missing-resource warning.
        assertFalse(config.has("refmap"), "1.21.1 needs no refmap; the key must stay absent");
    }
}
