package dev.otectus.mcaconversations.village;

import com.google.gson.JsonObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * Village culture: the same place every time, shared by its residents, and not agreed with by all of
 * them (spec §17.3).
 */
class VillageCultureTest {

    private static VillageCultureCatalog catalog() {
        Map<String, CultureToken> tokens = new LinkedHashMap<>();
        for (CultureFamily family : CultureFamily.values()) {
            for (int i = 0; i < 4; i++) {
                String id = family.key() + "_" + i;
                tokens.put(id, new CultureToken(id, family, 10,
                        Set.of("tradition"), Set.of("independence"), Set.of()));
            }
        }
        return new VillageCultureCatalog(tokens, Map.of());
    }

    @Test
    @DisplayName("the same world and village generate the same place every time")
    void generationIsDeterministic() {
        long seed = VillageCultureGenerator.seedFor(1234L, 7);
        Optional<VillageCultureRecord> first =
                VillageCultureGenerator.generate(catalog(), seed, 7, 3L, id -> true);
        Optional<VillageCultureRecord> again =
                VillageCultureGenerator.generate(catalog(), seed, 7, 900L, id -> true);

        assertTrue(first.isPresent());
        assertEquals(first.get().tokens(), again.orElseThrow().tokens(),
                "a village must not become a different village because the day moved on");
    }

    @Test
    @DisplayName("two villages in one world are not the same village")
    void villagesDiffer() {
        VillageCultureCatalog catalog = catalog();
        Map<CultureFamily, String> one = VillageCultureGenerator
                .generate(catalog, VillageCultureGenerator.seedFor(99L, 1), 1, 0L, id -> true)
                .orElseThrow().tokens();
        Map<CultureFamily, String> two = VillageCultureGenerator
                .generate(catalog, VillageCultureGenerator.seedFor(99L, 2), 2, 0L, id -> true)
                .orElseThrow().tokens();

        assertNotEquals(one, two);
    }

    @Test
    @DisplayName("a culture is all six families or none at all")
    void partialCulturesAreRefused() {
        Map<String, CultureToken> incomplete = new LinkedHashMap<>();
        incomplete.put("only", new CultureToken("only", CultureFamily.FESTIVAL, 10,
                Set.of(), Set.of(), Set.of()));

        assertTrue(VillageCultureGenerator.generate(
                        new VillageCultureCatalog(incomplete, Map.of()), 1L, 1, 0L, id -> true)
                .isEmpty(), "a village with a festival and no landmark leaves every scene a hole");
    }

    @Test
    @DisplayName("a token an integration owns is skipped when that mod is absent")
    void integrationsGateCandidates() {
        Map<String, CultureToken> tokens = new LinkedHashMap<>();
        tokens.put("base", new CultureToken("base", CultureFamily.DEBATE, 10,
                Set.of(), Set.of(), Set.of()));
        tokens.put("modded", new CultureToken("modded", CultureFamily.DEBATE, 10,
                Set.of(), Set.of(), Set.of("townstead")));
        VillageCultureCatalog catalog = new VillageCultureCatalog(tokens, Map.of());

        assertEquals(1, catalog.candidates(CultureFamily.DEBATE, id -> false).size());
        assertEquals(2, catalog.candidates(CultureFamily.DEBATE, id -> true).size());
    }

    @Test
    @DisplayName("residents reach the same token from different directions")
    void stanceComesFromIdentity() {
        CultureToken token = new CultureToken("first_frost_supper", CultureFamily.FESTIVAL, 10,
                Set.of("hospitality", "crowded_table"), Set.of("crowds", "privacy"), Set.of());

        assertEquals(CultureStance.ENDORSE, token.stanceFor(Set.of("hospitality", "duty")));
        assertEquals(CultureStance.QUESTION, token.stanceFor(Set.of("privacy", "duty")));
        assertEquals(CultureStance.IGNORE, token.stanceFor(Set.of("duty")),
                "most people have no view about most of what their village believes");
        assertEquals(CultureStance.IGNORE, token.stanceFor(Set.of()),
                "and without an identity there is no basis for saying they hold one");
    }

    @Test
    @DisplayName("a token that is both endorsed and questioned by one identity is refused")
    void contradictoryTokensAreMalformed() {
        CultureToken token = new CultureToken("split", CultureFamily.VALUE, 10,
                Set.of("duty"), Set.of("duty"), Set.of());

        assertFalse(token.isWellFormed(),
                "otherwise the tie is settled by whichever set was iterated first");
    }

    @Test
    @DisplayName("a merge keeps the surviving culture and keeps answering for the old id")
    void mergesMigrate() {
        Map<CultureFamily, String> tokens = new EnumMap<>(CultureFamily.class);
        for (CultureFamily family : CultureFamily.values()) {
            tokens.put(family, family.key() + "_0");
        }
        VillageCultureRecord surviving = new VillageCultureRecord(1, tokens, 4L, Set.of());
        VillageCultureRecord absorbed = new VillageCultureRecord(2, tokens, 5L, Set.of());

        VillageCultureRecord merged = surviving.absorbing(absorbed);

        assertEquals(1, merged.villageId());
        assertEquals(surviving.tokens(), merged.tokens(),
                "blending two cultures would leave everybody living somewhere that never existed");
        assertTrue(merged.answersFor(2), "and the people who came across still get an answer");
        assertTrue(merged.answersFor(1));
        assertFalse(merged.answersFor(3));
    }

    @Test
    @DisplayName("a merge chain keeps every id that has been folded in")
    void mergeChainsAccumulate() {
        Map<CultureFamily, String> tokens = new EnumMap<>(CultureFamily.class);
        for (CultureFamily family : CultureFamily.values()) {
            tokens.put(family, family.key() + "_1");
        }
        VillageCultureRecord small = new VillageCultureRecord(3, tokens, 1L, Set.of(4));
        VillageCultureRecord large = new VillageCultureRecord(1, tokens, 1L, Set.of(2));

        VillageCultureRecord merged = large.absorbing(small);

        assertTrue(merged.answersFor(2));
        assertTrue(merged.answersFor(3));
        assertTrue(merged.answersFor(4), "a village absorbed by a village that was absorbed still counts");
    }

    @Test
    @DisplayName("a culture survives a save and a load")
    void roundTrips() {
        Map<CultureFamily, String> tokens = new EnumMap<>(CultureFamily.class);
        for (CultureFamily family : CultureFamily.values()) {
            tokens.put(family, family.key() + "_2");
        }
        VillageCultureRecord original = new VillageCultureRecord(11, tokens, 40L, Set.of(12, 13));

        assertEquals(Optional.of(original), VillageCultureRecord.load(original.save()));
    }

    @Test
    @DisplayName("an alias keeps a renamed token's villages")
    void aliasesResolve() {
        Map<String, CultureToken> tokens = new LinkedHashMap<>();
        tokens.put("first_frost_supper", new CultureToken("first_frost_supper",
                CultureFamily.FESTIVAL, 10, Set.of(), Set.of(), Set.of()));
        VillageCultureCatalog catalog = new VillageCultureCatalog(
                tokens, Map.of("frost_supper", "first_frost_supper"));

        assertTrue(catalog.token("frost_supper").isPresent(),
                "a village holding the old id would otherwise hold a name nothing answers to");
        assertEquals("first_frost_supper", catalog.token("frost_supper").orElseThrow().id());
    }

    @Test
    @DisplayName("the condition needs a token or a family to mean anything")
    void queryNeedsSomethingToAsk() {
        assertFalse(CultureQuery.fromJson(new JsonObject()).isValid());

        JsonObject byToken = new JsonObject();
        byToken.addProperty("token", "the_old_well");
        assertTrue(CultureQuery.fromJson(byToken).isValid());

        JsonObject byFamily = new JsonObject();
        byFamily.addProperty("family", "festival");
        byFamily.addProperty("stance", "question");
        CultureQuery query = CultureQuery.fromJson(byFamily);
        assertTrue(query.isValid());
        assertEquals(CultureFamily.FESTIVAL, query.family());
        assertEquals(CultureStance.QUESTION, query.stance());
    }

    @Test
    @DisplayName("the shipped catalog can fill every family on a vanilla install")
    void shippedTokensAreComplete() {
        VillageCultureCatalog shipped = shipped();

        assertFalse(shipped.isEmpty());
        assertTrue(shipped.isComplete(id -> false),
                "a village on a plain install must still be able to have a culture");
    }

    @Test
    @DisplayName("every shipped token speaks in both locales")
    void shippedTokensAreLocalized() throws Exception {
        JsonObject en = readJson(TestPaths.of(
                "src/main/resources/assets/mcaconversations/lang/en_us.json"));
        JsonObject pt = readJson(TestPaths.of(
                "src/main/resources/assets/mcaconversations/lang/pt_br.json"));

        List<String> missing = new ArrayList<>();
        for (String id : shippedIds()) {
            String key = "mcaconversations.culture." + id;
            if (!en.has(key)) {
                missing.add(key + " (en_us)");
            }
            if (!pt.has(key)) {
                missing.add(key + " (pt_br)");
            }
        }
        assertTrue(missing.isEmpty(), String.join(", ", missing));
    }

    // --- The shipped file, read straight off disk -------------------------------------------------

    private static final Path SHIPPED =
            TestPaths.of("src/main/resources/data/mcaconversations/village_culture/base.json");
    private static final Path TOWNSTEAD =
            TestPaths.of("src/main/resources/data/mcaconversations/village_culture/townstead.json");

    private static VillageCultureCatalog shipped() {
        try {
            JsonObject root = readJson(SHIPPED);
            Map<String, CultureToken> tokens = new LinkedHashMap<>();
            for (Map.Entry<String, com.google.gson.JsonElement> entry
                    : root.getAsJsonObject("tokens").entrySet()) {
                CultureToken token = CultureToken.fromJson(entry.getKey(),
                        entry.getValue().getAsJsonObject());
                tokens.put(entry.getKey(), token);
            }
            return new VillageCultureCatalog(tokens, Map.of());
        } catch (Exception e) {
            throw new IllegalStateException("shipped village culture unreadable", e);
        }
    }

    private static List<String> shippedIds() throws Exception {
        List<String> ids = new ArrayList<>(
                readJson(SHIPPED).getAsJsonObject("tokens").keySet());
        return ids;
    }

    @Test
    @DisplayName("the Townstead slice is entirely absent on a plain install")
    void optionalSliceIsGated() {
        VillageCultureCatalog all = catalogOf(SHIPPED, TOWNSTEAD);

        assertTrue(all.isComplete(id -> false),
                "the base six families must still fill a village with the optional slice filtered out");
        for (CultureFamily family : CultureFamily.values()) {
            assertTrue(all.candidates(family, id -> true).size()
                            > all.candidates(family, id -> false).size(),
                    family + " should gain a candidate when Townstead is installed");
        }
    }

    @Test
    @DisplayName("every Townstead token declares the integration that owns it")
    void optionalSliceDeclaresItsOwner() throws Exception {
        JsonObject tokens = readJson(TOWNSTEAD).getAsJsonObject("tokens");
        for (String id : tokens.keySet()) {
            CultureToken token = CultureToken.fromJson(id, tokens.getAsJsonObject(id));
            assertTrue(token.integrations().contains("townstead"),
                    id + " would otherwise be drawn on a plain install");
        }
    }

    private static VillageCultureCatalog catalogOf(Path... files) {
        try {
            Map<String, CultureToken> tokens = new LinkedHashMap<>();
            for (Path file : files) {
                JsonObject root = readJson(file).getAsJsonObject("tokens");
                for (String id : root.keySet()) {
                    tokens.put(id, CultureToken.fromJson(id, root.getAsJsonObject(id)));
                }
            }
            return new VillageCultureCatalog(tokens, Map.of());
        } catch (Exception e) {
            throw new IllegalStateException("shipped village culture unreadable", e);
        }
    }

    private static JsonObject readJson(Path path) throws Exception {
        return com.google.gson.JsonParser.parseString(Files.readString(path)).getAsJsonObject();
    }
}
