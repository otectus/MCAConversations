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

            // --- the branching Day tree (1.1.0). Three utterances per stance: the plain phrasing,
            // a natural paraphrase, and a shorter form a player would actually type mid-chat.
            {"that sounds hard", "conversations.topic.day.rough.respond", "day.rough.empathize"},
            {"im sorry, that sounds rough", "conversations.topic.day.rough.respond", "day.rough.empathize"},
            {"that is a lot to carry", "conversations.topic.day.rough.respond", "day.rough.empathize"},
            {"what happened", "conversations.topic.day.rough.respond", "day.rough.ask"},
            {"what went wrong", "conversations.topic.day.rough.respond", "day.rough.ask"},
            {"tell me what happened", "conversations.topic.day.rough.respond", "day.rough.ask"},
            {"everyone has bad days", "conversations.topic.day.rough.respond", "day.rough.brush_off"},
            {"could be worse", "conversations.topic.day.rough.respond", "day.rough.brush_off"},
            {"that is just life", "conversations.topic.day.rough.respond", "day.rough.brush_off"},
            {"let me help", "conversations.topic.day.rough.followup", "day.rough.offer_help"},
            {"can i give you a hand", "conversations.topic.day.rough.followup", "day.rough.offer_help"},
            {"let me take that", "conversations.topic.day.rough.followup", "day.rough.offer_help"},
            {"the cat won", "conversations.topic.day.rough.followup", "day.rough.lighten"},
            {"you have to laugh", "conversations.topic.day.rough.followup", "day.rough.lighten"},
            {"at least it is funny", "conversations.topic.day.rough.followup", "day.rough.lighten"},
            {"it is okay to have a bad day", "conversations.topic.day.rough.followup", "day.rough.let_be"},
            {"you do not have to explain", "conversations.topic.day.rough.followup", "day.rough.let_be"},
            {"it can just be a bad day", "conversations.topic.day.rough.followup", "day.rough.let_be"},
            {"that came out wrong", "conversations.topic.day.rough.repair", "day.rough.apologize"},
            {"im sorry, that was harsh", "conversations.topic.day.rough.repair", "day.rough.apologize"},
            {"i did not mean that", "conversations.topic.day.rough.repair", "day.rough.apologize"},
            {"it is not a tragedy", "conversations.topic.day.rough.repair", "day.rough.double_down"},
            {"you are overreacting", "conversations.topic.day.rough.repair", "day.rough.double_down"},
            {"stop being dramatic", "conversations.topic.day.rough.repair", "day.rough.double_down"},
            {"you have earned it", "conversations.topic.day.good.respond", "day.good.celebrate"},
            {"you deserve that", "conversations.topic.day.good.respond", "day.good.celebrate"},
            {"good for you", "conversations.topic.day.good.respond", "day.good.celebrate"},
            {"what made it good", "conversations.topic.day.good.respond", "day.good.ask_more"},
            {"why is that", "conversations.topic.day.good.respond", "day.good.ask_more"},
            {"tell me more about it", "conversations.topic.day.good.respond", "day.good.ask_more"},
            {"must be nice", "conversations.topic.day.good.respond", "day.good.deflate"},
            {"alright for some", "conversations.topic.day.good.respond", "day.good.deflate"},
            {"some of us work", "conversations.topic.day.good.respond", "day.good.deflate"},
            {"mine has been good too", "conversations.topic.day.good.followup", "day.good.share_own"},
            {"same here", "conversations.topic.day.good.followup", "day.good.share_own"},
            {"mine as well", "conversations.topic.day.good.followup", "day.good.share_own"},
            {"careful, people will think you are happy", "conversations.topic.day.good.followup", "day.good.tease"},
            {"mind your reputation", "conversations.topic.day.good.followup", "day.good.tease"},
            {"you are smiling", "conversations.topic.day.good.followup", "day.good.tease"},
            {"hope it holds", "conversations.topic.day.good.followup", "day.good.wish_well"},
            {"hope it lasts", "conversations.topic.day.good.followup", "day.good.wish_well"},
            {"long may it continue", "conversations.topic.day.good.followup", "day.good.wish_well"},
            {"what are you working on", "conversations.topic.day.ordinary.respond", "day.ordinary.ask_work"},
            {"what is that you are making", "conversations.topic.day.ordinary.respond", "day.ordinary.ask_work"},
            {"what are you making", "conversations.topic.day.ordinary.respond", "day.ordinary.ask_work"},
            {"mine has been the same", "conversations.topic.day.ordinary.respond", "day.ordinary.share_own_day"},
            {"much the same", "conversations.topic.day.ordinary.respond", "day.ordinary.share_own_day"},
            {"mine too", "conversations.topic.day.ordinary.respond", "day.ordinary.share_own_day"},
            {"do not let me slow you down", "conversations.topic.day.ordinary.respond", "day.ordinary.hurry"},
            {"hurry up", "conversations.topic.day.ordinary.respond", "day.ordinary.hurry"},
            {"you are busy, clearly", "conversations.topic.day.ordinary.respond", "day.ordinary.hurry"},
            {"you are good at that", "conversations.topic.day.ordinary.followup", "day.ordinary.praise"},
            {"that is impressive", "conversations.topic.day.ordinary.followup", "day.ordinary.praise"},
            {"you do that well", "conversations.topic.day.ordinary.followup", "day.ordinary.praise"},
            {"you could do that faster", "conversations.topic.day.ordinary.followup", "day.ordinary.advise"},
            {"there is a quicker way", "conversations.topic.day.ordinary.followup", "day.ordinary.advise"},
            {"you should try it this way", "conversations.topic.day.ordinary.followup", "day.ordinary.advise"},
            {"i will stop talking", "conversations.topic.day.ordinary.followup", "day.ordinary.let_them_work"},
            {"let you finish", "conversations.topic.day.ordinary.followup", "day.ordinary.let_them_work"},
            {"i will be quiet", "conversations.topic.day.ordinary.followup", "day.ordinary.let_them_work"},
            {"tell me properly", "conversations.topic.day.young.respond", "day.young.interested"},
            {"i want to hear it", "conversations.topic.day.young.respond", "day.young.interested"},
            {"tell me more", "conversations.topic.day.young.respond", "day.young.interested"},
            {"that is a good day of work", "conversations.topic.day.young.respond", "day.young.encourage"},
            {"well done", "conversations.topic.day.young.respond", "day.young.encourage"},
            {"i would be proud of that", "conversations.topic.day.young.respond", "day.young.encourage"},
            {"that is not really news", "conversations.topic.day.young.respond", "day.young.dismiss"},
            {"that is boring", "conversations.topic.day.young.respond", "day.young.dismiss"},
            {"who cares", "conversations.topic.day.young.respond", "day.young.dismiss"},
            {"and then what", "conversations.topic.day.young.followup", "day.young.play_along"},
            {"what happened next", "conversations.topic.day.young.followup", "day.young.play_along"},
            {"go on, what next", "conversations.topic.day.young.followup", "day.young.play_along"},
            {"is it always like this", "conversations.topic.day.young.followup", "day.young.ask_more"},
            {"is that normal", "conversations.topic.day.young.followup", "day.young.ask_more"},
            {"is that normally how it goes", "conversations.topic.day.young.followup", "day.young.ask_more"},
            {"that is the best news", "conversations.topic.day.toddler.respond", "day.toddler.delighted"},
            {"wow", "conversations.topic.day.toddler.respond", "day.toddler.delighted"},
            {"that is amazing", "conversations.topic.day.toddler.respond", "day.toddler.delighted"},
            {"how much", "conversations.topic.day.toddler.respond", "day.toddler.ask"},
            {"how many", "conversations.topic.day.toddler.respond", "day.toddler.ask"},
            {"tell me more", "conversations.topic.day.toddler.respond", "day.toddler.ask"},
            {"sorry, i forgot i asked", "conversations.topic.day.again.respond", "day.again.apologize"},
            {"my mistake", "conversations.topic.day.again.respond", "day.again.apologize"},
            {"sorry, asked already", "conversations.topic.day.again.respond", "day.again.apologize"},
            {"humour me", "conversations.topic.day.again.respond", "day.again.press"},
            {"tell me anyway", "conversations.topic.day.again.respond", "day.again.press"},
            {"go on, again", "conversations.topic.day.again.respond", "day.again.press"},

            // --- the branching Fears tree (1.1.0): the deep pilot, including the boundary node,
            // the guarded refusal, the scarred repair route and both cross-session arc stages.
            {"that sounds hard to carry", "conversations.topic.fears.open.respond", "fears.open.comfort"},
            {"that is heavy", "conversations.topic.fears.open.respond", "fears.open.comfort"},
            {"i am sorry", "conversations.topic.fears.open.respond", "fears.open.comfort"},
            {"tell me the rest", "conversations.topic.fears.open.respond", "fears.open.press"},
            {"tell me everything", "conversations.topic.fears.open.respond", "fears.open.press"},
            {"there is more, is there not", "conversations.topic.fears.open.respond", "fears.open.press"},
            {"me too", "conversations.topic.fears.open.respond", "fears.open.share"},
            {"i am scared of that too", "conversations.topic.fears.open.respond", "fears.open.share"},
            {"i fear that myself", "conversations.topic.fears.open.respond", "fears.open.share"},
            {"you will not face it alone", "conversations.topic.fears.open.followup", "fears.open.pledge"},
            {"we face it together", "conversations.topic.fears.open.followup", "fears.open.pledge"},
            {"i promise", "conversations.topic.fears.open.followup", "fears.open.pledge"},
            {"i cannot promise that", "conversations.topic.fears.open.followup", "fears.open.step_back"},
            {"but i am listening", "conversations.topic.fears.open.followup", "fears.open.step_back"},
            {"i would rather be honest", "conversations.topic.fears.open.followup", "fears.open.step_back"},
            {"you could face it", "conversations.topic.fears.open.followup", "fears.open.challenge"},
            {"stand up to it", "conversations.topic.fears.open.followup", "fears.open.challenge"},
            {"you can overcome it", "conversations.topic.fears.open.followup", "fears.open.challenge"},
            {"thank you for telling me", "conversations.topic.fears.open.close", "fears.open.thank"},
            {"thank you for trusting me", "conversations.topic.fears.open.close", "fears.open.thank"},
            {"i am grateful", "conversations.topic.fears.open.close", "fears.open.thank"},
            {"that took something to say", "conversations.topic.fears.open.close", "fears.open.means"},
            {"that was brave", "conversations.topic.fears.open.close", "fears.open.means"},
            {"that took courage", "conversations.topic.fears.open.close", "fears.open.means"},
            {"forget i asked", "conversations.topic.fears.pressed", "fears.pressed.back_off"},
            {"you are right", "conversations.topic.fears.pressed", "fears.pressed.back_off"},
            {"sorry i asked", "conversations.topic.fears.pressed", "fears.pressed.back_off"},
            {"i want the rest now", "conversations.topic.fears.pressed", "fears.pressed.push"},
            {"i insist", "conversations.topic.fears.pressed", "fears.pressed.push"},
            {"tell me now", "conversations.topic.fears.pressed", "fears.pressed.push"},
            {"that is yours to keep", "conversations.topic.fears.guarded.respond", "fears.guarded.respect"},
            {"fair enough", "conversations.topic.fears.guarded.respond", "fears.guarded.respect"},
            {"keep it private", "conversations.topic.fears.guarded.respond", "fears.guarded.respect"},
            {"tell me something easier", "conversations.topic.fears.guarded.respond", "fears.guarded.ask_safer"},
            {"something lighter then", "conversations.topic.fears.guarded.respond", "fears.guarded.ask_safer"},
            {"ask something else", "conversations.topic.fears.guarded.respond", "fears.guarded.ask_safer"},
            {"come on", "conversations.topic.fears.guarded.respond", "fears.guarded.press"},
            {"out with it", "conversations.topic.fears.guarded.respond", "fears.guarded.press"},
            {"just tell me", "conversations.topic.fears.guarded.respond", "fears.guarded.press"},
            {"i pushed you and i was wrong", "conversations.topic.fears.scarred.respond", "fears.scarred.apologize"},
            {"i should not have pushed", "conversations.topic.fears.scarred.respond", "fears.scarred.apologize"},
            {"that was wrong of me", "conversations.topic.fears.scarred.respond", "fears.scarred.apologize"},
            {"i will not ask again", "conversations.topic.fears.scarred.respond", "fears.scarred.give_space"},
            {"i will give you space", "conversations.topic.fears.scarred.respond", "fears.scarred.give_space"},
            {"never again", "conversations.topic.fears.scarred.respond", "fears.scarred.give_space"},
            {"sorry, i asked twice", "conversations.topic.fears.again.respond", "fears.again.apologize"},
            {"i should not have asked again", "conversations.topic.fears.again.respond", "fears.again.apologize"},
            {"my mistake", "conversations.topic.fears.again.respond", "fears.again.apologize"},
            {"humour me", "conversations.topic.fears.again.respond", "fears.again.press"},
            {"tell me anyway", "conversations.topic.fears.again.respond", "fears.again.press"},
            {"go on, again", "conversations.topic.fears.again.respond", "fears.again.press"},
            {"you are safe here", "conversations.topic.fears.toddler.respond", "fears.toddler.reassure"},
            {"that does sound scary", "conversations.topic.fears.toddler.respond", "fears.toddler.reassure"},
            {"nothing will get you", "conversations.topic.fears.toddler.respond", "fears.toddler.reassure"},
            {"what does it look like", "conversations.topic.fears.toddler.respond", "fears.toddler.ask"},
            {"describe it to me", "conversations.topic.fears.toddler.respond", "fears.toddler.ask"},
            {"what is it like", "conversations.topic.fears.toddler.respond", "fears.toddler.ask"},
            {"that is a real fear", "conversations.topic.fears.young.respond", "fears.young.reassure"},
            {"that is valid", "conversations.topic.fears.young.respond", "fears.young.reassure"},
            {"that is fair", "conversations.topic.fears.young.respond", "fears.young.reassure"},
            {"everyone is scared of something", "conversations.topic.fears.young.respond", "fears.young.normalize"},
            {"everybody has fears", "conversations.topic.fears.young.respond", "fears.young.normalize"},
            {"that is normal", "conversations.topic.fears.young.respond", "fears.young.normalize"},
            {"you will grow out of it", "conversations.topic.fears.young.respond", "fears.young.dismiss"},
            {"do not be silly", "conversations.topic.fears.young.respond", "fears.young.dismiss"},
            {"that is childish", "conversations.topic.fears.young.respond", "fears.young.dismiss"},
            {"what would help", "conversations.arc.fears.plan.respond", "fears.plan.ask_what_helps"},
            {"what do you need", "conversations.arc.fears.plan.respond", "fears.plan.ask_what_helps"},
            {"what actually helps", "conversations.arc.fears.plan.respond", "fears.plan.ask_what_helps"},
            {"let us do something about it", "conversations.arc.fears.plan.respond", "fears.plan.offer_plan"},
            {"we need a plan", "conversations.arc.fears.plan.respond", "fears.plan.offer_plan"},
            {"let us try something", "conversations.arc.fears.plan.respond", "fears.plan.offer_plan"},
            {"you do not need a plan", "conversations.arc.fears.plan.respond", "fears.plan.just_listen"},
            {"i am just listening", "conversations.arc.fears.plan.respond", "fears.plan.just_listen"},
            {"i am here", "conversations.arc.fears.plan.respond", "fears.plan.just_listen"},
            {"how is it going", "conversations.arc.fears.followthrough.respond", "fears.followthrough.ask_how"},
            {"how have you been getting on", "conversations.arc.fears.followthrough.respond", "fears.followthrough.ask_how"},
            {"how are you doing with it", "conversations.arc.fears.followthrough.respond", "fears.followthrough.ask_how"},
            {"look at you", "conversations.arc.fears.followthrough.respond", "fears.followthrough.celebrate"},
            {"that is not nothing", "conversations.arc.fears.followthrough.respond", "fears.followthrough.celebrate"},
            {"i am proud of you", "conversations.arc.fears.followthrough.respond", "fears.followthrough.celebrate"},
            {"i meant what i said", "conversations.arc.fears.followthrough.respond", "fears.followthrough.recall_promise"},
            {"i meant it back then", "conversations.arc.fears.followthrough.respond", "fears.followthrough.recall_promise"},
            {"i stand by what i said", "conversations.arc.fears.followthrough.respond", "fears.followthrough.recall_promise"},
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

    // --- Live-decision filtering (plan §7.3) ----------------------------------

    private static final String DAY_ROUGH = "conversations.topic.day.rough.respond";
    private static final List<String> DAY_ROUGH_OFFER = List.of("empathize", "ask", "brush_off", "leave");

    private static List<Scored> liveRank(String message, String question, List<String> offered) {
        NormalizedMessage n = Normalizer.normalize(message, index.synonyms());
        return IntentMatcher.rank(index, n, question, offered);
    }

    @Test
    void liveDecisionKeepsTheChoicesOnTheTable() {
        List<Scored> ranked = liveRank("that sounds hard", DAY_ROUGH, DAY_ROUGH_OFFER);
        assertTrue(ranked.stream().anyMatch(s -> s.id().equals("day.rough.empathize")),
                "a genuine reply to the open question must survive: " + ids(ranked));
    }

    @Test
    void liveDecisionDropsAnswersThatWereNotOffered() {
        // The same question, but MCA only offered two of its answers for this villager.
        List<Scored> ranked = liveRank("what happened", DAY_ROUGH, List.of("empathize", "leave"));
        assertTrue(ranked.stream().noneMatch(s -> s.id().equals("day.rough.ask")),
                "an answer the player was never shown must not be reachable by typing: " + ids(ranked));
    }

    @Test
    void liveDecisionSilencesWeakGlobalTopicMatches() {
        // "how is your day" is a perfectly good global topic starter, and exactly the kind of thing a
        // player might type mid-decision without meaning to abandon the question they were asked.
        List<Scored> ranked = liveRank("how is your day", DAY_ROUGH, DAY_ROUGH_OFFER);
        for (Scored s : ranked) {
            if (s.isSystem()) {
                continue;
            }
            assertTrue(s.contextScoped() || s.score() >= IntentMatcher.SUBJECT_CHANGE_FLOOR,
                    "a global topic below the subject-change floor leaked through: " + s.id() + "=" + s.score());
        }
    }

    @Test
    void liveDecisionAlwaysLeavesAWayOut() {
        List<Scored> ranked = liveRank("goodbye", DAY_ROUGH, DAY_ROUGH_OFFER);
        assertTrue(ranked.stream().anyMatch(Scored::isSystem),
                "system controls must survive a live decision so the player can always leave: " + ids(ranked));
    }

    @Test
    void withoutALiveDecisionRankingIsUnchanged() {
        List<Scored> open = liveRank("how is your day", null, List.of());
        List<Scored> plain = IntentMatcher.rank(index,
                Normalizer.normalize("how is your day", index.synonyms()), null);
        assertEquals(ids(plain), ids(open));
    }

    private static String ids(List<Scored> scored) {
        return scored.stream().map(Scored::id).toList().toString();
    }
}
