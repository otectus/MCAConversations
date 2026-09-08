package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.BeatCatalog;
import dev.otectus.mcaconversations.conversation.BeatContractLoader;
import dev.otectus.mcaconversations.conversation.ConversationCatalog;
import dev.otectus.mcaconversations.conversation.ConversationCatalogLoader;
import dev.otectus.mcaconversations.conversation.DepthClass;
import dev.otectus.mcaconversations.conversation.TopicEntry;

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
        files.put(ResourceLocation.fromNamespaceAndPath("mcaconversations", fileName), root);
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

    // --- Deterministic topic-id resolution (audit F12) --------------------------

    private static final String TOPIC = """
            {
              "entry": {"question": "conversations.cat.chitchat", "answer": "day"},
              "depth": "%s",
              "return_question": "conversations.cat.chitchat",
              "ages": ["adult"],
              "required_stance_families": ["empathy", "exit"]
            }""";

    private static JsonObject topicFile(String depth) {
        JsonObject topics = new JsonObject();
        topics.add("day", JsonParser.parseString(TOPIC.formatted(depth)).getAsJsonObject());
        JsonObject root = new JsonObject();
        root.add("topics", topics);
        return root;
    }

    private static void reloadCatalog(Map<ResourceLocation, JsonElement> files) throws Exception {
        Method apply = ConversationCatalogLoader.class.getDeclaredMethod(
                "apply", Map.class, ResourceManager.class, ProfilerFiller.class);
        apply.setAccessible(true);
        apply.invoke(new ConversationCatalogLoader(), files, null, null);
    }

    /**
     * The collision itself is logged, not returned, and the test source set has no appender
     * infrastructure to capture it — so what is pinned here is the part that matters to a player:
     * the same file wins on every load, whatever order the pack stack handed the files over in.
     */
    @Test
    @DisplayName("one topic id in two files resolves to the sorted-last file, whatever the input order")
    void collidingTopicIdResolvesDeterministically() throws Exception {
        ConversationCatalog before = ConversationCatalogLoader.active();
        try {
            ResourceLocation first = ResourceLocation.fromNamespaceAndPath("mcaconversations", "aaa");
            ResourceLocation last = ResourceLocation.fromNamespaceAndPath("mcaconversations", "zzz");

            Map<ResourceLocation, JsonElement> ascending = new LinkedHashMap<>();
            ascending.put(first, topicFile("quick"));
            ascending.put(last, topicFile("deep"));
            reloadCatalog(ascending);
            TopicEntry fromAscending = ConversationCatalogLoader.topic("day").orElseThrow();
            assertEquals(DepthClass.DEEP, fromAscending.depth(),
                    "the sorted-last file must win, not the first one seen");

            Map<ResourceLocation, JsonElement> descending = new LinkedHashMap<>();
            descending.put(last, topicFile("deep"));
            descending.put(first, topicFile("quick"));
            reloadCatalog(descending);
            assertEquals(DepthClass.DEEP, ConversationCatalogLoader.topic("day").orElseThrow().depth(),
                    "iteration order of the incoming map must not decide the winner");

            assertEquals(1, ConversationCatalogLoader.active().size(),
                    "a collision merges to one topic, it does not duplicate it");
        } finally {
            ConversationCatalogLoader.setActiveForTesting(before);
        }
    }
}
