package dev.otectus.mcaconversations.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.chat.IntentIndex.CompiledIntent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** IDF specificity, norm, phrase compilation, context partitioning, and synonym-consistent stemming. */
class IntentIndexTest {

    private static IntentBinding topic(String id, String answer, String keywordsJson, String extra) {
        String s = "{\"question\":\"q\",\"answer\":\"" + answer + "\",\"keywords\":" + keywordsJson
                + (extra == null ? "" : "," + extra) + "}";
        return IntentBinding.fromJson(id, new Gson().fromJson(s, JsonObject.class));
    }

    private static IntentIndex index(SynonymTable syn, IntentBinding... bindings) {
        return IntentIndex.build(new ArrayList<>(List.of(bindings)), syn);
    }

    private static CompiledIntent byId(IntentIndex idx, String id) {
        return idx.all().stream().filter(c -> c.id().equals(id)).findFirst().orElseThrow();
    }

    @Test
    void rareStemHasHigherIdfThanCommonStem() {
        IntentIndex idx = index(SynonymTable.EMPTY,
                topic("a", "a", "{\"village\":1.0,\"regret\":1.0}", null),
                topic("b", "b", "{\"village\":1.0}", null),
                topic("c", "c", "{\"village\":1.0}", null));
        assertTrue(idx.idf("regret") > idx.idf("village"),
                "a stem in one intent is more specific than one in three");
    }

    @Test
    void keywordsAreStemmedAtLoad() {
        IntentIndex idx = index(SynonymTable.EMPTY,
                topic("fears", "fears", "{\"fears\":1.5,\"scares\":1.2}", null));
        CompiledIntent ci = byId(idx, "fears");
        assertTrue(ci.keywordStems.containsKey("fear"), "fears -> fear");
        assertTrue(ci.keywordStems.containsKey("scare"), "scares -> scare (plural strip)");
    }

    @Test
    void synonymsCanonicalizeKeywordsConsistently() {
        SynonymTable syn = SynonymTable.builder()
                .addClass("work", List.of("job", "trade", "profession")).build();
        IntentIndex idx = index(syn, topic("work", "work", "{\"job\":1.5,\"trade\":1.0}", null));
        CompiledIntent ci = byId(idx, "work");
        assertTrue(ci.keywordStems.containsKey("work"), "job/trade fold to canonical work");
        assertEquals(1, ci.keywordStems.size(), "both aliases collapse to one canonical stem");
    }

    @Test
    void phrasesCompileWithWildcards() {
        IntentIndex idx = index(SynonymTable.EMPTY,
                topic("day", "day", "{\"day\":1.5}", "\"phrases\":[\"how * day\"]"));
        CompiledIntent ci = byId(idx, "day");
        assertEquals(1, ci.phrases.size());
        List<IntentIndex.PhraseToken> p = ci.phrases.get(0);
        assertEquals(3, p.size());
        assertEquals("how", p.get(0).stem());
        assertTrue(p.get(1).wildcard());
        assertEquals("day", p.get(2).stem());
    }

    @Test
    void contextScopedIntentsArePartitioned() {
        IntentIndex idx = index(SynonymTable.EMPTY,
                topic("global", "a", "{\"day\":1.0}", null),
                topic("scoped", "press", "{\"face\":1.0}", "\"context\":\"conversations.fears\""));
        assertEquals(1, idx.activeIntents(null).size(), "only global intents live with no context");
        assertEquals(2, idx.activeIntents("conversations.fears").size(), "the scoped intent joins in-context");
    }

    @Test
    void invertedIndexRoutesByKeywordStem() {
        IntentIndex idx = index(SynonymTable.EMPTY,
                topic("weather", "weather", "{\"rain\":1.2,\"storm\":1.2}", null),
                topic("day", "day", "{\"day\":1.5}", null));
        assertEquals(1, idx.withKeyword("rain").size());
        assertEquals("weather", idx.withKeyword("rain").get(0).id());
        assertTrue(idx.withKeyword("nonsense").isEmpty());
    }

    @Test
    void normIsPositiveEvenForPhraseOnlyIntent() {
        IntentBinding phraseOnly = IntentBinding.fromJson("silence", new Gson().fromJson(
                "{\"system\":\"mute\",\"phrases\":[\"stop talking\"]}", JsonObject.class));
        IntentIndex idx = index(SynonymTable.EMPTY, phraseOnly);
        assertNotNull(byId(idx, "silence"));
        assertTrue(byId(idx, "silence").norm > 0);
    }
}
