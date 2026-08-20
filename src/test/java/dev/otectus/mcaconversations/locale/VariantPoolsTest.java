package dev.otectus.mcaconversations.locale;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.personality.Personalities;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Guards the index chat mode picks variants from. It is read off the classpath rather than generated,
 * so these read the same shipped lang files the index does and check the answers agree.
 */
class VariantPoolsTest {

    private static Map<String, String> lang(String resource) {
        try (InputStream in = VariantPools.class.getResourceAsStream(resource)) {
            assertTrue(in != null, "missing resource " + resource);
            JsonObject json = JsonParser.parseReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
            Map<String, String> out = new LinkedHashMap<>();
            json.entrySet().forEach(e -> out.put(e.getKey(), e.getValue().getAsString()));
            return out;
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Test
    void poolSizeMatchesTheShippedFile() {
        Map<String, String> base = lang("/assets/mca_dialogue/lang/en_us.json");
        String key = "dialogue.conversations.work_offer.ask_terms";
        int expected = 0;
        while (base.containsKey(key + "/" + (expected + 1))) {
            expected++;
        }
        assertTrue(expected >= 2, "test fixture key stopped being a pool");
        assertEquals(expected, VariantPools.poolSize(key));
    }

    @Test
    void unpooledAndUnknownKeysAnswerZero() {
        assertEquals(0, VariantPools.poolSize("dialogue.conversations.cat.village.neighbour"));
        assertEquals(0, VariantPools.poolSize("dialogue.nothing.of.the.sort"));
        assertEquals(0, VariantPools.poolSize(""));
    }

    /**
     * The five pools that extend MCA's own start at {@code /6}+ and we ship only our half, so naming an
     * index inside them could collide with an MCA line. They must stay out of the index.
     */
    @Test
    void poolsWeOnlyPartlyOwnAreExcluded() {
        for (String base : List.of("dialogue.main", "dialogue.greet.success", "dialogue.greet.fail",
                "dialogue.story.success", "dialogue.shake_hand.success")) {
            assertEquals(0, VariantPools.poolSize(base), base + " must not be pickable server-side");
        }
    }

    @Test
    void variantLengthMatchesTheSentenceAndRejectsOutOfRange() {
        Map<String, String> base = lang("/assets/mca_dialogue/lang/en_us.json");
        String key = "dialogue.conversations.work_offer.ask_terms";
        int size = VariantPools.poolSize(key);
        for (int n = 1; n <= size; n++) {
            assertEquals(base.get(key + "/" + n).length(), VariantPools.variantLength(key, n),
                    key + "/" + n);
        }
        assertEquals(0, VariantPools.variantLength(key, 0));
        assertEquals(0, VariantPools.variantLength(key, size + 1));
    }

    @Test
    void withoutAnOverlayTheWholeBasePoolIsDeliverable() {
        String key = "dialogue.conversations.work_offer.ask_terms";
        assertEquals(0, VariantPools.overlayPoolSize(key, "crabby"), "fixture gained an overlay");
        assertEquals(VariantPools.poolSize(key), VariantPools.deliverablePoolSize(key, "crabby"));
        assertEquals(VariantPools.poolSize(key), VariantPools.deliverablePoolSize(key, ""));
        assertEquals(VariantPools.poolSize(key), VariantPools.deliverablePoolSize(key, null));
    }

    /**
     * The property the whole design rests on: an index we hand a client must exist in the pool that
     * client will actually read. MCA prefers {@code <personality>.<key>} and silently falls back to the
     * generic line when that lookup misses, so naming a variant past the end of a shorter overlay would
     * cost the villager their voice — quietly, and only for some personalities.
     */
    @Test
    void noDeliverableIndexOverrunsAPersonalityOverlay() {
        List<String> problems = new ArrayList<>();
        Map<String, String> base = lang("/assets/mca_dialogue/lang/en_us.json");
        for (String personality : Personalities.overlayPrefixes()) {
            for (String key : base.keySet()) {
                int slash = key.lastIndexOf('/');
                if (slash < 0) {
                    continue;
                }
                String root = key.substring(0, slash);
                int overlay = VariantPools.overlayPoolSize(root, personality);
                if (overlay == 0) {
                    continue; // no overlay for this line: the base pool is what everyone reads
                }
                int deliverable = VariantPools.deliverablePoolSize(root, personality);
                if (deliverable > overlay) {
                    problems.add(personality + "/" + root + ": may name /" + deliverable
                            + " but the overlay stops at /" + overlay);
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void aColdIndexRebuildsItself() {
        String key = "dialogue.conversations.work_offer.ask_terms";
        int before = VariantPools.poolSize(key);
        VariantPools.resetForTesting();
        assertEquals(before, VariantPools.poolSize(key));
    }
}
