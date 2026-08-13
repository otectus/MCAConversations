package dev.otectus.mcaconversations.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.chat.IntentMatcher.Decision;
import dev.otectus.mcaconversations.chat.IntentMatcher.Outcome;
import dev.otectus.mcaconversations.chat.IntentMatcher.Scored;
import dev.otectus.mcaconversations.chat.Normalizer.NormalizedMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The scoring heart of the suite (§6.3–6.6, spec §14). Table-driven over the <b>shipped</b>
 * {@code chat_intents}: ≥100 addressed utterances (paraphrases, misspellings within fuzz tolerance)
 * resolve to their intent, and player-to-player chatter stays below the ambient threshold against
 * every intent. Context scoping and negation are covered with focused synthetic cases.
 */
class IntentMatcherTest {

    private static final double MIN = 0.55;
    private static final double AMBIENT = 0.75;

    private static IntentIndex index;

    @BeforeAll
    static void load() {
        index = ChatIntentTestData.index();
    }

    private static Decision addressed(String message, String currentQuestion) {
        NormalizedMessage n = Normalizer.normalize(message, index.synonyms());
        List<Scored> ranked = IntentMatcher.rank(index, n, currentQuestion);
        return IntentMatcher.decide(ranked, true, MIN, AMBIENT);
    }

    private static double topAmbientScore(String message) {
        NormalizedMessage n = Normalizer.normalize(message, index.synonyms());
        List<Scored> ranked = IntentMatcher.rank(index, n, null);
        return ranked.isEmpty() ? 0.0 : ranked.get(0).score();
    }

    /** Utterance → expected intent id. Ordered by topic; every shipped topic intent appears ≥ 3×. */
    private static Map<String, String> cases() {
        Map<String, String> t = new LinkedHashMap<>();
        // chit-chat: day
        t.put("what are you doing", "chitchat.day");
        t.put("what are you up to", "chitchat.day");
        t.put("what's up", "chitchat.day");
        t.put("how is your day", "chitchat.day");
        t.put("how are you doing today", "chitchat.day");
        t.put("how's it going", "chitchat.day");
        t.put("how has your day been", "chitchat.day");
        t.put("how was your day today", "chitchat.day");
        // chit-chat: food
        t.put("what do you like to eat", "chitchat.food");
        t.put("what is your favorite food", "chitchat.food");
        t.put("do you like to cook", "chitchat.food");
        t.put("what food do you like", "chitchat.food");
        t.put("are you hungry", "chitchat.food");
        // chit-chat: weather
        t.put("what is the weather like", "chitchat.weather");
        t.put("is it going to rain", "chitchat.weather");
        t.put("how is the weather", "chitchat.weather");
        t.put("nice weather we are having", "chitchat.weather");
        // chit-chat: season
        t.put("what season is it", "chitchat.season");
        t.put("what season is it right now", "chitchat.season");
        t.put("is it winter or spring", "chitchat.season");
        // profession: work
        t.put("what do you do", "profession.work");
        t.put("what do you do for a living", "profession.work");
        t.put("what is your job", "profession.work");
        t.put("do you like your work", "profession.work");
        t.put("tell me about your work", "profession.work");
        t.put("what is your trade", "profession.work");
        t.put("how is work going", "profession.work");
        // profession: work_offer
        t.put("do you need anything", "profession.work_offer");
        t.put("can i help you", "profession.work_offer");
        t.put("do you need any help", "profession.work_offer");
        t.put("got any tasks for me", "profession.work_offer");
        // village: village
        t.put("tell me about the village", "village.village");
        t.put("about the village", "village.village");
        t.put("tell me about this town", "village.village");
        // village: people
        t.put("how is everyone doing", "village.people");
        t.put("tell me about the people", "village.people");
        t.put("tell me about the neighbors", "village.people");
        t.put("what are the other villagers like", "village.people");
        // village: rumors
        t.put("heard any rumors", "village.rumors");
        t.put("any gossip", "village.rumors");
        t.put("what are people saying", "village.rumors");
        t.put("heard any good rumors lately", "village.rumors");
        // events: news
        t.put("any news", "events.news");
        t.put("anything happening", "events.news");
        t.put("what is the latest news", "events.news");
        t.put("anything new to report", "events.news");
        // events: noticed
        t.put("noticed anything", "events.noticed");
        t.put("seen anything strange", "events.noticed");
        t.put("anything unusual lately", "events.noticed");
        t.put("have you noticed anything different", "events.noticed");
        // personal: life
        t.put("tell me about yourself", "personal.life");
        t.put("tell me about your life", "personal.life");
        t.put("what is your story", "personal.life");
        // personal: dreams
        t.put("what are your dreams", "personal.dreams");
        t.put("do you dream", "personal.dreams");
        t.put("what is your greatest ambition", "personal.dreams");
        // personal: fears
        t.put("what are you afraid of", "personal.fears");
        t.put("what scares you", "personal.fears");
        t.put("what do you fear", "personal.fears");
        t.put("what are your fears", "personal.fears");
        // personal: hopes
        t.put("what do you hope for", "personal.hopes");
        t.put("what are your hopes", "personal.hopes");
        t.put("what do you wish for", "personal.hopes");
        // personal: feelings
        t.put("how do you feel", "personal.feelings");
        t.put("how are you feeling", "personal.feelings");
        t.put("what are you feeling", "personal.feelings");
        // personal: regrets
        t.put("any regrets", "personal.regrets");
        t.put("do you regret anything", "personal.regrets");
        t.put("what is your biggest regret", "personal.regrets");
        // personal: secret
        t.put("tell me a secret", "personal.secret");
        t.put("any secrets", "personal.secret");
        t.put("do you have any secrets", "personal.secret");
        // greeting (full-scoring path; the ≤3-token "hi"/"hello" short-circuit is dispatcher-level)
        t.put("good morning", "chatmode.greeting");
        t.put("good evening", "chatmode.greeting");
        t.put("good afternoon to you", "chatmode.greeting");
        // check-in ("how have you been" is a real question — it gets the checkin content, not a hail)
        t.put("how have you been", "greeting.checkin");
        t.put("how are you holding up", "greeting.checkin");
        t.put("how have things been lately", "greeting.checkin");
        return t;
    }

    @Test
    void addressedUtterancesResolveToExpectedIntent() {
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, String> e : cases().entrySet()) {
            Decision d = addressed(e.getKey(), null);
            if (d.outcome() != Outcome.MATCH) {
                problems.add(String.format("\"%s\" expected %s but got %s%s", e.getKey(), e.getValue(),
                        d.outcome(), d.chosen() != null
                                ? " (" + d.chosen().id() + "=" + String.format("%.3f", d.chosen().score()) + ")" : ""));
            } else if (!d.chosen().id().equals(e.getValue())) {
                problems.add(String.format("\"%s\" expected %s but matched %s (%.3f)", e.getKey(),
                        e.getValue(), d.chosen().id(), d.chosen().score()));
            }
        }
        assertTrue(problems.isEmpty(), problems.size() + " mismatches:\n" + String.join("\n", problems));
    }

    /**
     * Stance utterances: {message, open sub-question, expected intent id}.
     *
     * <p>Every one of these must fire while its sub-question is open. Whether it may <em>also</em>
     * fire cold is a per-intent authoring decision, expressed by the {@code context} field in the
     * intent pack and asserted separately by {@link #globallyReachableStancesAreDeclaredSo()}.
     */
    private static final String[][] CONTEXT_CASES = {
            {"you could face it", "conversations.fears", "fears.challenge"},
            {"that sounds hard to carry", "conversations.fears", "fears.comfort"},
            {"tell me the rest of it", "conversations.fears", "fears.press"},
            {"me too, scared of that too", "conversations.fears", "fears.share"},
            {"you should chase that", "conversations.dreams", "dreams.encourage"},
            {"tell me more about it", "conversations.dreams", "dreams.ask_more"},
            {"i feel the same way", "conversations.feelings", "feelings.same"},
            {"im not sure yet, maybe", "conversations.feelings", "feelings.unsure"},
            {"are you happy with us", "conversations.us", "us.happy"},
            {"remember when we met", "conversations.us", "us.firstmet"},
            {"what about our future", "conversations.us", "us.future"},
            {"is anything weighing on you", "conversations.us", "us.worries"},
            {"how are the kids holding up", "conversations.family", "family.checkin_child"},
            {"tell me about your parents", "conversations.family", "family.ask_parent"},
            {"tell me a family story", "conversations.family", "family.memories"},
    };

    @Test
    void contextStancesFireInContextAndAreInertOutside() {
        List<String> problems = new ArrayList<>();
        for (String[] c : CONTEXT_CASES) {
            String msg = c[0];
            String ctx = c[1];
            String expected = c[2];
            Decision in = addressed(msg, ctx);
            if (in.outcome() != Outcome.MATCH || !in.chosen().id().equals(expected)) {
                problems.add(String.format("in-context \"%s\" [%s] expected %s but got %s%s", msg, ctx,
                        expected, in.outcome(), in.chosen() != null
                                ? " (" + in.chosen().id() + "=" + String.format("%.3f", in.chosen().score()) + ")" : ""));
            }
            // Out of context, a stance may only enter the ranking if its pack declares it global
            // (no "context" field). A stance that IS context-scoped must stay completely inert.
            NormalizedMessage n = Normalizer.normalize(msg, index.synonyms());
            List<Scored> global = IntentMatcher.rank(index, n, null);
            boolean leaked = global.stream().anyMatch(x -> x.id().equals(expected));
            if (leaked && isContextScoped(expected)) {
                problems.add(String.format("out-of-context \"%s\" leaked scoped intent %s", msg, expected));
            }
        }
        assertTrue(problems.isEmpty(), problems.size() + " problems:\n" + String.join("\n", problems));
    }

    /** True when the loaded intent pack scopes this intent to a sub-question. */
    private static boolean isContextScoped(String intentId) {
        return index.all().stream()
                .filter(ci -> ci.source.id().equals(intentId))
                .noneMatch(ci -> index.activeIntents(null).contains(ci));
    }

    /**
     * The relationship and family stances are reachable without first opening their topic page:
     * asking a spouse "are you happy with us" in passing should work. They are global on purpose,
     * so this pins the decision — if someone re-adds {@code "context"} to one of these, the
     * conversational reach silently shrinks and this fails rather than the behaviour quietly
     * regressing. Their questions still gate them: {@code GatePreview} checks the answer's
     * constraints, so a non-spouse cannot reach spouse-only content this way.
     */
    @Test
    void globallyReachableStancesAreDeclaredSo() {
        List<String> problems = new ArrayList<>();
        for (String id : new String[]{"us.happy", "us.firstmet", "us.future", "us.worries",
                "family.checkin_child", "family.ask_parent", "family.memories"}) {
            if (isContextScoped(id)) {
                problems.add(id + " is context-scoped; it should be globally reachable");
            }
        }
        // The fears/dreams/feelings stances are the opposite case: "tell me more about it" only
        // means anything while that topic is open, so they must stay scoped.
        for (String id : new String[]{"fears.challenge", "fears.comfort", "fears.press",
                "dreams.encourage", "dreams.ask_more", "feelings.same", "feelings.unsure"}) {
            if (!isContextScoped(id)) {
                problems.add(id + " is global; a bare stance like this must stay context-scoped");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /** System control utterances (no context): {message, expected system-intent id}. */
    private static final String[][] SYSTEM_CASES = {
            {"see you later", "chatmode.farewell"},
            {"take care", "chatmode.farewell"},
            {"goodbye friend", "chatmode.farewell"},
            {"stop talking", "chatmode.silence"},
            {"please be quiet", "chatmode.silence"},
            {"leave me alone", "chatmode.silence"},
            {"never mind", "chatmode.decline"},
            {"forget it", "chatmode.decline"},
            {"change the subject", "chatmode.decline"},
            {"shut up", "chatmode.insult"},
            {"i hate you", "chatmode.insult"},
            {"you are an idiot", "chatmode.insult"},
            {"youre so stupid", "chatmode.insult"},
    };

    @Test
    void systemControlUtterancesRouteToTheirIntent() {
        List<String> problems = new ArrayList<>();
        for (String[] c : SYSTEM_CASES) {
            Decision d = addressed(c[0], null);
            if (d.outcome() != Outcome.MATCH || !d.chosen().id().equals(c[1])) {
                problems.add(String.format("\"%s\" expected %s but got %s%s", c[0], c[1], d.outcome(),
                        d.chosen() != null
                                ? " (" + d.chosen().id() + "=" + String.format("%.3f", d.chosen().score()) + ")" : ""));
            }
        }
        assertTrue(problems.isEmpty(), problems.size() + " problems:\n" + String.join("\n", problems));
    }

    @Test
    void tableCoversEveryShippedIntentAtLeastOnce() {
        List<String> ids = new ArrayList<>(ChatIntentTestData.bindings().keySet());
        List<String> covered = new ArrayList<>(cases().values());
        for (String[] c : CONTEXT_CASES) {
            covered.add(c[2]);
        }
        for (String[] c : SYSTEM_CASES) {
            covered.add(c[1]);
        }
        List<String> missing = new ArrayList<>();
        for (String id : ids) {
            if (!covered.contains(id)) {
                missing.add(id);
            }
        }
        assertTrue(missing.isEmpty(), "intents with no matcher utterance: " + missing);
    }

    @Test
    void townSquareEveryoneQuestionClearsTheAmbientFloor() {
        // "How's everyone doing?" shouted in a square must reach responders (spec §12).
        NormalizedMessage n = Normalizer.normalize("how is everyone doing", index.synonyms());
        List<Scored> ranked = IntentMatcher.rank(index, n, null);
        Decision d = IntentMatcher.decide(ranked, false, MIN, AMBIENT);
        assertEquals(Outcome.MATCH, d.outcome(), "expected an ambient match, top: "
                + (ranked.isEmpty() ? "none" : ranked.get(0).id() + "=" + ranked.get(0).score()));
        assertEquals("village.people", d.chosen().id());
    }

    @Test
    void playerChatterStaysBelowAmbientThreshold() {
        String[] antiCases = {
                "anyone selling emeralds", "brb", "lol nice one", "where are you", "nice house lol",
                "gg wp everyone", "anyone got spare iron", "lets go mining", "afk for a bit",
                "who wants to trade", "come to my base", "look at my new sword", "xp farm this way",
                "night everyone", "sup",
        };
        List<String> problems = new ArrayList<>();
        for (String msg : antiCases) {
            double top = topAmbientScore(msg);
            if (top >= AMBIENT) {
                problems.add(String.format("\"%s\" scored %.3f (>= ambient %.2f)", msg, top, AMBIENT));
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void negatedAnchorSuppressesTheTopic() {
        // "weather" is negation-tagged, so its anchor cannot satisfy requiresAny → no weather match.
        Decision d = addressed("i do not like the weather", null);
        boolean weatherWon = d.outcome() == Outcome.MATCH && "chitchat.weather".equals(d.chosen().id());
        assertTrue(!weatherWon, "negated weather must not drive the weather topic");
    }

    // --- Context scoping (synthetic: Phase 2 ships no context intents, but the engine supports them) ---

    private static IntentIndex contextIndex() {
        List<IntentBinding> b = new ArrayList<>();
        b.add(IntentBinding.fromJson("chitchat.day", new Gson().fromJson(
                "{\"question\":\"conversations.cat.chitchat\",\"answer\":\"day\","
                        + "\"keywords\":{\"day\":1.5},\"requiresAny\":[\"day\"]}", JsonObject.class)));
        b.add(IntentBinding.fromJson("fears.press", new Gson().fromJson(
                "{\"context\":\"conversations.fears\",\"question\":\"conversations.fears\",\"answer\":\"press\","
                        + "\"keywords\":{\"face\":1.2,\"stand\":1.2,\"brave\":1.0},"
                        + "\"requiresAny\":[\"face\",\"stand\",\"brave\"],\"phrases\":[\"stand with you\",\"face it\"]}",
                JsonObject.class)));
        return IntentIndex.build(b, SynonymTable.EMPTY);
    }

    @Test
    void contextScopedIntentIsInertOutOfContextAndLiveInContext() {
        IntentIndex idx = contextIndex();
        NormalizedMessage m = Normalizer.normalize("you could face it, i would stand with you", SynonymTable.EMPTY);

        List<Scored> withoutContext = IntentMatcher.rank(idx, m, null);
        assertTrue(withoutContext.stream().noneMatch(s -> s.id().equals("fears.press")),
                "scoped intent must not score with no open sub-question");

        Decision inContext = IntentMatcher.decide(
                IntentMatcher.rank(idx, m, "conversations.fears"), true, MIN, AMBIENT);
        assertEquals(Outcome.MATCH, inContext.outcome());
        assertEquals("fears.press", inContext.chosen().id(), "in context, the stance wins with its ctx bonus");
    }

    @Test
    void ambientAmbiguityIsSilenceButAddressedAsksToClarify() {
        // Two different-question intents in a near tie: addressed → AMBIGUOUS, ambient → NONE.
        List<IntentBinding> b = new ArrayList<>();
        b.add(IntentBinding.fromJson("a.one", new Gson().fromJson(
                "{\"question\":\"qa\",\"answer\":\"one\",\"keywords\":{\"shared\":1.5},\"requiresAny\":[\"shared\"]}",
                JsonObject.class)));
        b.add(IntentBinding.fromJson("b.two", new Gson().fromJson(
                "{\"question\":\"qb\",\"answer\":\"two\",\"keywords\":{\"shared\":1.5},\"requiresAny\":[\"shared\"]}",
                JsonObject.class)));
        IntentIndex idx = IntentIndex.build(b, SynonymTable.EMPTY);
        NormalizedMessage m = Normalizer.normalize("shared", SynonymTable.EMPTY);
        assertEquals(Outcome.AMBIGUOUS, IntentMatcher.decide(IntentMatcher.rank(idx, m, null), true, MIN, AMBIENT).outcome());
        assertEquals(Outcome.NONE, IntentMatcher.decide(IntentMatcher.rank(idx, m, null), false, MIN, AMBIENT).outcome());
    }
}
