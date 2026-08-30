package dev.otectus.mcaconversations.scene;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What a scene degrades to when the one the author preferred cannot be told (spec §10.4).
 *
 * <p>Through 1.4.0 {@code fallback} was parsed, validated and never followed: 219 declarations in the
 * shipped corpus described behaviour the runtime did not have. These tests pin the contract that
 * makes them real — and the guards that keep a degrade from becoming a search.
 */
class FallbackTraversalTest {

    @Test
    void followsAChainNearestHopFirst() {
        SceneCatalog catalog = catalog(
                scene("work.mason.cracked_lintel", "minecraft:mason", "work.mason.evergreen"),
                scene("work.mason.evergreen", "minecraft:mason", "work.generic"),
                scene("work.generic", "", ""));

        List<String> chain = ids(FallbackChain.from(catalog,
                catalog.scene("work.mason.cracked_lintel").orElseThrow()));
        assertEquals(List.of("work.mason.evergreen", "work.generic"), chain);
    }

    @Test
    void stopsAtTheDepthBound() {
        List<SceneDefinition> scenes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            scenes.add(scene("work.hop_" + i, "", i == 9 ? "" : "work.hop_" + (i + 1)));
        }
        SceneCatalog catalog = catalog(scenes.toArray(new SceneDefinition[0]));

        List<SceneDefinition> chain = FallbackChain.from(catalog, catalog.scene("work.hop_0").orElseThrow());
        assertEquals(FallbackChain.MAX_DEPTH, chain.size(),
                "a degrade is a degrade, not a walk to the far end of the catalog");
        assertEquals("work.hop_1", chain.get(0).id());
    }

    @Test
    void aCycleTerminatesAndIsReportedOnce() {
        SceneCatalog catalog = catalog(
                scene("work.loop_a", "", "work.loop_b"),
                scene("work.loop_b", "", "work.loop_a"));

        List<String> chain = ids(FallbackChain.from(catalog, catalog.scene("work.loop_a").orElseThrow()));
        assertEquals(List.of("work.loop_b"), chain, "the hop back to the start is not taken");

        List<String> cycles = FallbackChain.cycles(catalog);
        assertEquals(1, cycles.size(), cycles.toString());
        assertTrue(cycles.get(0).contains("work.loop_a"), cycles.get(0));
    }

    @Test
    void willNotDegradeAcrossTopics() {
        // A fallback that changed the subject would answer a question about work with a line about
        // the weather, and the player would have no way to tell that is what happened.
        SceneCatalog catalog = catalog(
                sceneOn("topic:work", "work.smith.quenching", "", "weather.the_wet_forge"),
                sceneOn("topic:weather", "weather.the_wet_forge", "", ""));

        assertTrue(FallbackChain.from(catalog, catalog.scene("work.smith.quenching").orElseThrow())
                .isEmpty());
        assertFalse(catalog.danglingReferences().isEmpty(),
                "a cross-topic fallback should be reported at load, not merely ignored at selection");
    }

    @Test
    void anUnknownOrSelfReferentialTargetIsReportedAndYieldsNoChain() {
        SceneCatalog catalog = catalog(
                scene("work.missing_target", "", "work.nobody_declares_this"),
                scene("work.self", "", "work.self"));

        assertTrue(FallbackChain.from(catalog, catalog.scene("work.missing_target").orElseThrow())
                .isEmpty());
        assertTrue(FallbackChain.from(catalog, catalog.scene("work.self").orElseThrow()).isEmpty());
        assertEquals(2, catalog.danglingReferences().size(), catalog.danglingReferences().toString());
    }

    @Test
    void aSceneWithNoFallbackHasNoChain() {
        SceneCatalog catalog = catalog(scene("work.terminal", "", ""));
        assertTrue(FallbackChain.from(catalog, catalog.scene("work.terminal").orElseThrow()).isEmpty());
        assertTrue(catalog.danglingReferences().isEmpty());
    }

    // --- helpers ----------------------------------------------------------------------------------

    private static SceneCatalog catalog(SceneDefinition... scenes) {
        return SceneCatalog.build(List.of(scenes));
    }

    private static List<String> ids(List<SceneDefinition> scenes) {
        return scenes.stream().map(SceneDefinition::id).toList();
    }

    private static SceneDefinition scene(String id, String profession, String fallback) {
        return sceneOn("topic:work", id, profession, fallback);
    }

    private static SceneDefinition sceneOn(String purpose, String id, String profession,
                                           String fallback) {
        JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
        json.addProperty("purpose", purpose);
        if (!profession.isEmpty()) {
            JsonObject profile = new JsonObject();
            com.google.gson.JsonArray professions = new com.google.gson.JsonArray();
            professions.add(profession);
            profile.add("profession", professions);
            json.add("profile", profile);
        }
        JsonObject route = new JsonObject();
        route.addProperty("question", "conversations.scene." + id + ".respond");
        route.addProperty("opening_beat", id + ".open");
        json.add("route", route);
        if (!fallback.isEmpty()) {
            json.addProperty("fallback", fallback);
        }
        return SceneDefinition.fromJson(id, json);
    }
}
