package dev.otectus.mcaconversations.chat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.chat.IntentMatcher.Decision;
import dev.otectus.mcaconversations.chat.IntentMatcher.Outcome;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Datapack "synonym packs" (spec §15 Phase 4): a third-party file that carries <em>only</em> a
 * {@code synonyms} block — no intents — must merge into the shared table (ChatIntentLoader pass 1
 * runs over every file before any intent parses), so vocabulary added by one pack routes into intents
 * defined by another. This mirrors the loader's two-pass build over in-memory fixtures.
 */
class SynonymPackTest {

    private static final Gson GSON = new Gson();

    /** The shipped-style intent file: defines an intent keyed on the canonical word "work". */
    private static final String INTENT_FILE = """
            {"intents": {"profession.work": {
                "question": "conversations.cat.profession", "answer": "work",
                "keywords": {"work": 1.5}, "requiresAny": ["work"]}}}""";

    /** A synonyms-only pack (valid: no "intents" block) broadening the vocabulary. */
    private static final String SYNONYM_PACK = """
            {"synonyms": {"work": ["gig", "hustle"]}}""";

    private static IntentIndex buildLikeTheLoader(String... files) {
        // Pass 1: synonyms from every file.
        SynonymTable.Builder synonyms = SynonymTable.builder();
        for (String file : files) {
            JsonObject obj = GSON.fromJson(file, JsonObject.class);
            if (obj.has("synonyms")) {
                for (Map.Entry<String, JsonElement> e : obj.getAsJsonObject("synonyms").entrySet()) {
                    List<String> aliases = new ArrayList<>();
                    e.getValue().getAsJsonArray().forEach(a -> aliases.add(a.getAsString()));
                    synonyms.addClass(e.getKey(), aliases);
                }
            }
        }
        SynonymTable syn = synonyms.build();
        assertTrue(synonyms.conflicts().isEmpty(), "merge must be conflict-free");

        // Pass 2: intents from every file; files without an "intents" block are skipped, not errors.
        List<IntentBinding> bindings = new ArrayList<>();
        for (String file : files) {
            JsonObject obj = GSON.fromJson(file, JsonObject.class);
            if (!obj.has("intents")) {
                continue;
            }
            for (Map.Entry<String, JsonElement> e : obj.getAsJsonObject("intents").entrySet()) {
                bindings.add(IntentBinding.fromJson(e.getKey(), e.getValue().getAsJsonObject()));
            }
        }
        return IntentIndex.build(bindings, syn);
    }

    @Test
    void synonymOnlyPackRoutesItsAliasesIntoAnotherFilesIntent() {
        IntentIndex index = buildLikeTheLoader(INTENT_FILE, SYNONYM_PACK);
        Decision d = IntentMatcher.decide(
                IntentMatcher.rank(index, Normalizer.normalize("how is the hustle going", index.synonyms()), null),
                true, 0.55, 0.75);
        assertEquals(Outcome.MATCH, d.outcome(), "pack alias 'hustle' must canonicalize to 'work'");
        assertEquals("profession.work", d.chosen().id());
    }

    @Test
    void withoutThePackTheAliasDoesNotMatch() {
        IntentIndex index = buildLikeTheLoader(INTENT_FILE);
        Decision d = IntentMatcher.decide(
                IntentMatcher.rank(index, Normalizer.normalize("how is the hustle going", index.synonyms()), null),
                true, 0.55, 0.75);
        assertEquals(Outcome.NONE, d.outcome());
    }
}
