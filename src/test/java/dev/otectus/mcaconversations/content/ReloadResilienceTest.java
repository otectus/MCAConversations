package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.BeatCatalog;
import dev.otectus.mcaconversations.conversation.BeatContractLoader;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A broken datapack does not take the conversation with it (spec §16, "datapack reload failure
 * retains the previous good semantic/profile snapshot").
 *
 * <p>The failure this guards against is specific and easy to reintroduce. Beat contracts are keyed
 * twice — by id, and by the {@code say} + {@code next} route they contract — and two packs that both
 * claim one route are a genuine conflict the catalog refuses to build. If that refusal were allowed
 * to leave the catalog empty, every villager in the world would fall back to uncontracted lines
 * because somebody's third-party pack had a typo in it. The loader keeps the last good catalog
 * instead, and this pins that.
 *
 * <p>The loader's {@code apply} is protected and the class is final, so it is invoked reflectively.
 * That is the honest shape of the test: the behaviour under test is what the reload listener does,
 * not what some extracted helper does.
 */
class ReloadResilienceTest {

    private static final String BEAT = """
            {
              "topic": "day",
              "say": "conversations.reload.probe",
              "response_question": "conversations.cat.chitchat",
              "npc_act": "report",
              "subject": "day.probe",
              "polarity": "neutral",
              "openness": "permits_followup",
              "allowed_stances": ["exit"]
            }""";

    private static Map<ResourceLocation, JsonElement> pack(String fileName, String... beatIds) {
        JsonObject beats = new JsonObject();
        for (String id : beatIds) {
            beats.add(id, JsonParser.parseString(BEAT).getAsJsonObject());
        }
        JsonObject root = new JsonObject();
        root.add("beats", beats);
        Map<ResourceLocation, JsonElement> files = new LinkedHashMap<>();
        files.put(new ResourceLocation("mcaconversations", fileName), root);
        return files;
    }

    private static void reload(Map<ResourceLocation, JsonElement> files) throws Exception {
        Method apply = BeatContractLoader.class.getDeclaredMethod(
                "apply", Map.class, ResourceManager.class, ProfilerFiller.class);
        apply.setAccessible(true);
        apply.invoke(new BeatContractLoader(), files, null, null);
    }

    @Test
    @DisplayName("a pack that contracts one route twice leaves the previous catalog standing")
    void collidingPackKeepsThePreviousCatalog() throws Exception {
        BeatCatalog before = BeatContractLoader.active();
        try {
            reload(pack("good", "day.probe.one"));
            BeatCatalog good = BeatContractLoader.active();
            assertEquals(1, good.size(), "the good pack should have loaded");

            // Two ids, one route: BeatCatalog.build refuses, and the loader must keep what it had.
            reload(pack("broken", "day.probe.two", "day.probe.three"));
            assertSame(good, BeatContractLoader.active(),
                    "a colliding reload must leave the previous catalog in place, not empty it");
            assertEquals(1, BeatContractLoader.active().size());
        } finally {
            BeatContractLoader.setActiveForTesting(before);
        }
    }

    @Test
    @DisplayName("an empty pack is a choice, not a failure")
    void anEmptyPackIsHonoured() throws Exception {
        BeatCatalog before = BeatContractLoader.active();
        try {
            reload(pack("good", "day.probe.one"));
            assertEquals(1, BeatContractLoader.active().size());

            reload(new LinkedHashMap<>());
            assertTrue(BeatContractLoader.active().size() == 0,
                    "a pack that removes every beat has removed every beat — that is not a failure"
                            + " and must not be treated as one");
        } finally {
            BeatContractLoader.setActiveForTesting(before);
        }
    }
}
