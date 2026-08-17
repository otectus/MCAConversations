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
        // village: standing (§30.5)
        t.put("what do people think of me", "village.standing");
        t.put("what do people think of me around here", "village.standing");
        t.put("what is my reputation", "village.standing");
        t.put("how am i regarded", "village.standing");
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

            // --- Phase 3: the mundane topics, converted to real exchanges.
            {"i am listening", "conversations.topic.checkin.rough.respond", "checkin.rough.listen"},
            {"i will listen", "conversations.topic.checkin.rough.respond", "checkin.rough.listen"},
            {"say more if you want", "conversations.topic.checkin.rough.respond", "checkin.rough.listen"},
            {"what is weighing on you", "conversations.topic.checkin.rough.respond", "checkin.rough.ask"},
            {"what is bothering you", "conversations.topic.checkin.rough.respond", "checkin.rough.ask"},
            {"what is troubling you", "conversations.topic.checkin.rough.respond", "checkin.rough.ask"},
            {"you will be fine", "conversations.topic.checkin.rough.respond", "checkin.rough.dismiss"},
            {"you will live", "conversations.topic.checkin.rough.respond", "checkin.rough.dismiss"},
            {"you always are", "conversations.topic.checkin.rough.respond", "checkin.rough.dismiss"},
            {"tell me one thing i can take off you", "conversations.topic.checkin.rough.followup", "checkin.rough.offer_help"},
            {"let me take something", "conversations.topic.checkin.rough.followup", "checkin.rough.offer_help"},
            {"name one thing", "conversations.topic.checkin.rough.followup", "checkin.rough.offer_help"},
            {"i will stop asking", "conversations.topic.checkin.rough.followup", "checkin.rough.give_space"},
            {"i am around though", "conversations.topic.checkin.rough.followup", "checkin.rough.give_space"},
            {"i will give you space", "conversations.topic.checkin.rough.followup", "checkin.rough.give_space"},
            {"i have had a stretch like that", "conversations.topic.checkin.rough.followup", "checkin.rough.share_own"},
            {"i went through that myself", "conversations.topic.checkin.rough.followup", "checkin.rough.share_own"},
            {"been through that myself", "conversations.topic.checkin.rough.followup", "checkin.rough.share_own"},
            {"i am glad", "conversations.topic.checkin.good.respond", "checkin.good.glad"},
            {"genuinely glad", "conversations.topic.checkin.good.respond", "checkin.good.glad"},
            {"that pleases me", "conversations.topic.checkin.good.respond", "checkin.good.glad"},
            {"what has been going right", "conversations.topic.checkin.good.respond", "checkin.good.ask_more"},
            {"what is going well", "conversations.topic.checkin.good.respond", "checkin.good.ask_more"},
            {"what has gone right", "conversations.topic.checkin.good.respond", "checkin.good.ask_more"},
            {"give it a week", "conversations.topic.checkin.good.respond", "checkin.good.deflate"},
            {"wait a week", "conversations.topic.checkin.good.respond", "checkin.good.deflate"},
            {"it will not last", "conversations.topic.checkin.good.respond", "checkin.good.deflate"},
            {"keep at it", "conversations.topic.checkin.good.followup", "checkin.good.keep_it_up"},
            {"keep it up", "conversations.topic.checkin.good.followup", "checkin.good.keep_it_up"},
            {"carry on with that", "conversations.topic.checkin.good.followup", "checkin.good.keep_it_up"},
            {"careful, you will ruin your reputation", "conversations.topic.checkin.good.followup", "checkin.good.tease"},
            {"mind your reputation", "conversations.topic.checkin.good.followup", "checkin.good.tease"},
            {"you will ruin it", "conversations.topic.checkin.good.followup", "checkin.good.tease"},
            {"things have been alright with me too", "conversations.topic.checkin.good.followup", "checkin.good.share_own"},
            {"same with me", "conversations.topic.checkin.good.followup", "checkin.good.share_own"},
            {"mine has been alright too", "conversations.topic.checkin.good.followup", "checkin.good.share_own"},
            {"tell me the whole thing", "conversations.topic.checkin.young.respond", "checkin.young.interested"},
            {"tell me everything", "conversations.topic.checkin.young.respond", "checkin.young.interested"},
            {"go on then tell me", "conversations.topic.checkin.young.respond", "checkin.young.interested"},
            {"you are doing alright", "conversations.topic.checkin.young.respond", "checkin.young.encourage"},
            {"i am proud of you", "conversations.topic.checkin.young.respond", "checkin.young.encourage"},
            {"you are doing well", "conversations.topic.checkin.young.respond", "checkin.young.encourage"},
            {"that is not much of an answer", "conversations.topic.checkin.young.respond", "checkin.young.dismiss"},
            {"hardly an answer", "conversations.topic.checkin.young.respond", "checkin.young.dismiss"},
            {"that is not much", "conversations.topic.checkin.young.respond", "checkin.young.dismiss"},
            {"sorry, i have asked already", "conversations.topic.checkin.again.respond", "checkin.again.apologize"},
            {"i already asked", "conversations.topic.checkin.again.respond", "checkin.again.apologize"},
            {"sorry, my mistake", "conversations.topic.checkin.again.respond", "checkin.again.apologize"},
            {"humour me", "conversations.topic.checkin.again.respond", "checkin.again.press"},
            {"humour me anyway", "conversations.topic.checkin.again.respond", "checkin.again.press"},
            {"tell me anyway", "conversations.topic.checkin.again.respond", "checkin.again.press"},
            {"no fuss from me", "conversations.topic.food.trait.respond", "food.trait.respect"},
            {"understood", "conversations.topic.food.trait.respond", "food.trait.respect"},
            {"fine by me", "conversations.topic.food.trait.respond", "food.trait.respect"},
            {"how do you manage", "conversations.topic.food.trait.respond", "food.trait.ask_about"},
            {"how do you cope with it", "conversations.topic.food.trait.respond", "food.trait.ask_about"},
            {"how do you manage day to day", "conversations.topic.food.trait.respond", "food.trait.ask_about"},
            {"sounds like an excuse", "conversations.topic.food.trait.respond", "food.trait.mock"},
            {"you are being difficult", "conversations.topic.food.trait.respond", "food.trait.mock"},
            {"that is just fussy", "conversations.topic.food.trait.respond", "food.trait.mock"},
            {"i will find you something you can eat", "conversations.topic.food.trait.followup", "food.trait.offer_alternative"},
            {"i will bring you something else", "conversations.topic.food.trait.followup", "food.trait.offer_alternative"},
            {"i will find something", "conversations.topic.food.trait.followup", "food.trait.offer_alternative"},
            {"there is food i cannot touch either", "conversations.topic.food.trait.followup", "food.trait.share_own"},
            {"same for me", "conversations.topic.food.trait.followup", "food.trait.share_own"},
            {"me too actually", "conversations.topic.food.trait.followup", "food.trait.share_own"},
            {"you do not have to explain", "conversations.topic.food.trait.followup", "food.trait.let_be"},
            {"no need to justify it", "conversations.topic.food.trait.followup", "food.trait.let_be"},
            {"you owe me no explanation", "conversations.topic.food.trait.followup", "food.trait.let_be"},
            {"mine is plainer than that", "conversations.topic.food.normal.respond", "food.normal.share_pref"},
            {"my taste is simple", "conversations.topic.food.normal.respond", "food.normal.share_pref"},
            {"mine is simpler", "conversations.topic.food.normal.respond", "food.normal.share_pref"},
            {"how do you make it", "conversations.topic.food.normal.respond", "food.normal.ask_recipe"},
            {"how do you cook it", "conversations.topic.food.normal.respond", "food.normal.ask_recipe"},
            {"what is the recipe", "conversations.topic.food.normal.respond", "food.normal.ask_recipe"},
            {"i would argue with that", "conversations.topic.food.normal.respond", "food.normal.disagree"},
            {"i disagree", "conversations.topic.food.normal.respond", "food.normal.disagree"},
            {"you are wrong about that", "conversations.topic.food.normal.respond", "food.normal.disagree"},
            {"you have good taste", "conversations.topic.food.normal.followup", "food.normal.praise"},
            {"excellent taste", "conversations.topic.food.normal.followup", "food.normal.praise"},
            {"that is good taste", "conversations.topic.food.normal.followup", "food.normal.praise"},
            {"i will bring you some", "conversations.topic.food.normal.followup", "food.normal.promise_bring"},
            {"i will bring some over", "conversations.topic.food.normal.followup", "food.normal.promise_bring"},
            {"i will get you some", "conversations.topic.food.normal.followup", "food.normal.promise_bring"},
            {"where do you get it", "conversations.topic.food.normal.followup", "food.normal.ask_where"},
            {"where do you buy it", "conversations.topic.food.normal.followup", "food.normal.ask_where"},
            {"where does it come from", "conversations.topic.food.normal.followup", "food.normal.ask_where"},
            {"that is an excellent choice", "conversations.topic.food.young.respond", "food.young.delight"},
            {"excellent choice", "conversations.topic.food.young.respond", "food.young.delight"},
            {"i agree completely", "conversations.topic.food.young.respond", "food.young.delight"},
            {"what else do you like", "conversations.topic.food.young.respond", "food.young.ask"},
            {"what other food", "conversations.topic.food.young.respond", "food.young.ask"},
            {"anything else you like", "conversations.topic.food.young.respond", "food.young.ask"},
            {"that is not proper food", "conversations.topic.food.young.respond", "food.young.dismiss"},
            {"that is not real food", "conversations.topic.food.young.respond", "food.young.dismiss"},
            {"hardly proper food", "conversations.topic.food.young.respond", "food.young.dismiss"},
            {"sorry, we covered this", "conversations.topic.food.again.respond", "food.again.apologize"},
            {"sorry, asked already", "conversations.topic.food.again.respond", "food.again.apologize"},
            {"my mistake, we did this", "conversations.topic.food.again.respond", "food.again.apologize"},
            {"what are you craving", "conversations.topic.food.again.respond", "food.again.press"},
            {"humour me", "conversations.topic.food.again.respond", "food.again.press"},
            {"tell me anyway", "conversations.topic.food.again.respond", "food.again.press"},
            {"are you alright out in this", "conversations.topic.weather.storm.respond", "weather.storm.concern"},
            {"are you okay in this", "conversations.topic.weather.storm.respond", "weather.storm.concern"},
            {"are you safe out here", "conversations.topic.weather.storm.respond", "weather.storm.concern"},
            {"anything need bringing in", "conversations.topic.weather.storm.respond", "weather.storm.practical"},
            {"anything to bring inside", "conversations.topic.weather.storm.respond", "weather.storm.practical"},
            {"need anything secured", "conversations.topic.weather.storm.respond", "weather.storm.practical"},
            {"it is only thunder", "conversations.topic.weather.storm.respond", "weather.storm.dismiss"},
            {"just a bit of thunder", "conversations.topic.weather.storm.respond", "weather.storm.dismiss"},
            {"only a bit of weather", "conversations.topic.weather.storm.respond", "weather.storm.dismiss"},
            {"come sit it out with me", "conversations.topic.weather.storm.followup", "weather.storm.offer_shelter"},
            {"come inside with me", "conversations.topic.weather.storm.followup", "weather.storm.offer_shelter"},
            {"take shelter with me", "conversations.topic.weather.storm.followup", "weather.storm.offer_shelter"},
            {"i will wait it out here with you", "conversations.topic.weather.storm.followup", "weather.storm.stay_awhile"},
            {"i will stay with you", "conversations.topic.weather.storm.followup", "weather.storm.stay_awhile"},
            {"i will wait here", "conversations.topic.weather.storm.followup", "weather.storm.stay_awhile"},
            {"good weather for staying in bed", "conversations.topic.weather.storm.followup", "weather.storm.joke"},
            {"perfect sleeping weather", "conversations.topic.weather.storm.followup", "weather.storm.joke"},
            {"weather for bed", "conversations.topic.weather.storm.followup", "weather.storm.joke"},
            {"it is a good one", "conversations.topic.weather.mild.respond", "weather.mild.enjoy"},
            {"lovely day", "conversations.topic.weather.mild.respond", "weather.mild.enjoy"},
            {"it is nice out", "conversations.topic.weather.mild.respond", "weather.mild.enjoy"},
            {"is it any use to the fields", "conversations.topic.weather.mild.respond", "weather.mild.talk_crops"},
            {"good for the crops", "conversations.topic.weather.mild.respond", "weather.mild.talk_crops"},
            {"will it help the harvest", "conversations.topic.weather.mild.respond", "weather.mild.talk_crops"},
            {"i could do with less of it", "conversations.topic.weather.mild.respond", "weather.mild.grumble"},
            {"i have had enough of it", "conversations.topic.weather.mild.respond", "weather.mild.grumble"},
            {"i am tired of it", "conversations.topic.weather.mild.respond", "weather.mild.grumble"},
            {"you are right about that", "conversations.topic.weather.mild.followup", "weather.mild.agree"},
            {"that is true", "conversations.topic.weather.mild.followup", "weather.mild.agree"},
            {"quite right", "conversations.topic.weather.mild.followup", "weather.mild.agree"},
            {"doing anything with the day", "conversations.topic.weather.mild.followup", "weather.mild.ask_plans"},
            {"any plans today", "conversations.topic.weather.mild.followup", "weather.mild.ask_plans"},
            {"what are your plans", "conversations.topic.weather.mild.followup", "weather.mild.ask_plans"},
            {"it is only weather", "conversations.topic.weather.mild.followup", "weather.mild.cut_short"},
            {"anyway, moving on", "conversations.topic.weather.mild.followup", "weather.mild.cut_short"},
            {"this is boring", "conversations.topic.weather.mild.followup", "weather.mild.cut_short"},
            {"big sky sheep definitely", "conversations.topic.weather.young.respond", "weather.young.play_along"},
            {"i see them too", "conversations.topic.weather.young.respond", "weather.young.play_along"},
            {"definitely sheep", "conversations.topic.weather.young.respond", "weather.young.play_along"},
            {"what is your favourite kind of sky", "conversations.topic.weather.young.respond", "weather.young.ask"},
            {"which sky is best", "conversations.topic.weather.young.respond", "weather.young.ask"},
            {"favourite weather", "conversations.topic.weather.young.respond", "weather.young.ask"},
            {"they are just clouds", "conversations.topic.weather.young.respond", "weather.young.dismiss"},
            {"it is only clouds", "conversations.topic.weather.young.respond", "weather.young.dismiss"},
            {"just clouds", "conversations.topic.weather.young.respond", "weather.young.dismiss"},
            {"how do you mark it", "conversations.topic.season.holiday.respond", "season.holiday.ask_tradition"},
            {"how do you celebrate", "conversations.topic.season.holiday.respond", "season.holiday.ask_tradition"},
            {"what is the tradition", "conversations.topic.season.holiday.respond", "season.holiday.ask_tradition"},
            {"save me a place", "conversations.topic.season.holiday.respond", "season.holiday.accept"},
            {"count me in", "conversations.topic.season.holiday.respond", "season.holiday.accept"},
            {"i will join you", "conversations.topic.season.holiday.respond", "season.holiday.accept"},
            {"i cannot make it", "conversations.topic.season.holiday.respond", "season.holiday.decline"},
            {"i am busy that day", "conversations.topic.season.holiday.respond", "season.holiday.decline"},
            {"i cannot come but enjoy it", "conversations.topic.season.holiday.respond", "season.holiday.decline"},
            {"we did something similar where i am from", "conversations.topic.season.holiday.followup", "season.holiday.share_tradition"},
            {"we had something similar back home", "conversations.topic.season.holiday.followup", "season.holiday.share_tradition"},
            {"similar where i grew up", "conversations.topic.season.holiday.followup", "season.holiday.share_tradition"},
            {"the village does this well", "conversations.topic.season.holiday.followup", "season.holiday.compliment"},
            {"you do this beautifully", "conversations.topic.season.holiday.followup", "season.holiday.compliment"},
            {"it is lovely here today", "conversations.topic.season.holiday.followup", "season.holiday.compliment"},
            {"it is a lot of fuss", "conversations.topic.season.holiday.followup", "season.holiday.grumble"},
            {"too much fuss", "conversations.topic.season.holiday.followup", "season.holiday.grumble"},
            {"a lot of bother for one day", "conversations.topic.season.holiday.followup", "season.holiday.grumble"},
            {"it suits you", "conversations.topic.season.turn.respond", "season.turn.agree"},
            {"this season fits you", "conversations.topic.season.turn.respond", "season.turn.agree"},
            {"it suits you, this time of year", "conversations.topic.season.turn.respond", "season.turn.agree"},
            {"which season is yours", "conversations.topic.season.turn.respond", "season.turn.ask_favourite"},
            {"what is your favourite season", "conversations.topic.season.turn.respond", "season.turn.ask_favourite"},
            {"which do you prefer", "conversations.topic.season.turn.respond", "season.turn.ask_favourite"},
            {"i will be glad when it is over", "conversations.topic.season.turn.respond", "season.turn.complain"},
            {"i am sick of this season", "conversations.topic.season.turn.respond", "season.turn.complain"},
            {"i am done with it", "conversations.topic.season.turn.respond", "season.turn.complain"},
            {"here is what i am doing with it", "conversations.topic.season.turn.followup", "season.turn.share_plan"},
            {"my plan for it", "conversations.topic.season.turn.followup", "season.turn.share_plan"},
            {"this is what i am doing", "conversations.topic.season.turn.followup", "season.turn.share_plan"},
            {"a lot to look forward to", "conversations.topic.season.turn.followup", "season.turn.look_forward"},
            {"good things ahead", "conversations.topic.season.turn.followup", "season.turn.look_forward"},
            {"much to look forward to", "conversations.topic.season.turn.followup", "season.turn.look_forward"},
            {"it is all the same to me", "conversations.topic.season.turn.followup", "season.turn.dismiss"},
            {"they are all the same", "conversations.topic.season.turn.followup", "season.turn.dismiss"},
            {"does not matter to me", "conversations.topic.season.turn.followup", "season.turn.dismiss"},
            {"it is the best one", "conversations.topic.season.young.respond", "season.young.play_along"},
            {"you are right, it is the best", "conversations.topic.season.young.respond", "season.young.play_along"},
            {"i agree it is the best", "conversations.topic.season.young.respond", "season.young.play_along"},
            {"what do you like about it", "conversations.topic.season.young.respond", "season.young.ask"},
            {"why do you like it", "conversations.topic.season.young.respond", "season.young.ask"},
            {"what is good about it", "conversations.topic.season.young.respond", "season.young.ask"},
            {"they are all much the same", "conversations.topic.season.young.respond", "season.young.dismiss"},
            {"they are all alike", "conversations.topic.season.young.respond", "season.young.dismiss"},
            {"all the same really", "conversations.topic.season.young.respond", "season.young.dismiss"},

            // --- Phase 4: work, the village, its people, and the village's news.
            {"you are good at what you do", "conversations.topic.work.respond", "work.respond.praise"},
            {"you know your craft", "conversations.topic.work.respond", "work.respond.praise"},
            {"you are skilled at it", "conversations.topic.work.respond", "work.respond.praise"},
            {"why does it matter to you", "conversations.topic.work.respond", "work.respond.ask_why"},
            {"what does it mean to you", "conversations.topic.work.respond", "work.respond.ask_why"},
            {"why do you do it", "conversations.topic.work.respond", "work.respond.ask_why"},
            {"you do not sound like you enjoy it", "conversations.topic.work.respond", "work.respond.challenge"},
            {"do you even like it", "conversations.topic.work.respond", "work.respond.challenge"},
            {"sounds like you hate it", "conversations.topic.work.respond", "work.respond.challenge"},
            {"have you tried it the other way", "conversations.topic.work.followup", "work.followup.offer_idea"},
            {"have you tried something different", "conversations.topic.work.followup", "work.followup.offer_idea"},
            {"try it another way", "conversations.topic.work.followup", "work.followup.offer_idea"},
            {"it sounds like it is wearing you down", "conversations.topic.work.followup", "work.followup.hear_burnout"},
            {"that sounds tiring", "conversations.topic.work.followup", "work.followup.hear_burnout"},
            {"you sound worn out", "conversations.topic.work.followup", "work.followup.hear_burnout"},
            {"anyone could do that", "conversations.topic.work.followup", "work.followup.belittle"},
            {"that looks easy", "conversations.topic.work.followup", "work.followup.belittle"},
            {"that is simple work", "conversations.topic.work.followup", "work.followup.belittle"},
            {"that is real work", "conversations.topic.work.young.respond", "work.young.encourage"},
            {"that is proper work", "conversations.topic.work.young.respond", "work.young.encourage"},
            {"that counts as work", "conversations.topic.work.young.respond", "work.young.encourage"},
            {"who taught you", "conversations.topic.work.young.respond", "work.young.ask"},
            {"how did you learn", "conversations.topic.work.young.respond", "work.young.ask"},
            {"who showed you", "conversations.topic.work.young.respond", "work.young.ask"},
            {"you are just playing", "conversations.topic.work.young.respond", "work.young.dismiss"},
            {"that is pretend work", "conversations.topic.work.young.respond", "work.young.dismiss"},
            {"those are only chores", "conversations.topic.work.young.respond", "work.young.dismiss"},
            {"sorry, you have told me", "conversations.topic.work.again.respond", "work.again.apologize"},
            {"sorry, i already asked", "conversations.topic.work.again.respond", "work.again.apologize"},
            {"my mistake, you told me", "conversations.topic.work.again.respond", "work.again.apologize"},
            {"tell me again anyway", "conversations.topic.work.again.respond", "work.again.press"},
            {"once more", "conversations.topic.work.again.respond", "work.again.press"},
            {"go on, again", "conversations.topic.work.again.respond", "work.again.press"},
            {"what needs doing", "conversations.topic.work_offer.respond", "work_offer.ask_needed"},
            {"what is the task", "conversations.topic.work_offer.respond", "work_offer.ask_needed"},
            {"what needs done", "conversations.topic.work_offer.respond", "work_offer.ask_needed"},
            {"what is in it for me", "conversations.topic.work_offer.respond", "work_offer.ask_terms"},
            {"what does it pay", "conversations.topic.work_offer.respond", "work_offer.ask_terms"},
            {"what are the terms", "conversations.topic.work_offer.respond", "work_offer.ask_terms"},
            {"i will do it", "conversations.topic.work_offer.respond", "work_offer.accept"},
            {"i will take it", "conversations.topic.work_offer.respond", "work_offer.accept"},
            {"yes, i am in", "conversations.topic.work_offer.respond", "work_offer.accept"},
            {"alright, i will take it on", "conversations.topic.work_offer.followup", "work_offer.accept_after"},
            {"agreed", "conversations.topic.work_offer.followup", "work_offer.accept_after"},
            {"i will take it on", "conversations.topic.work_offer.followup", "work_offer.accept_after"},
            {"not this time i am afraid", "conversations.topic.work_offer.followup", "work_offer.decline"},
            {"i will pass", "conversations.topic.work_offer.followup", "work_offer.decline"},
            {"not this time", "conversations.topic.work_offer.followup", "work_offer.decline"},
            {"find someone else", "conversations.topic.work_offer.followup", "work_offer.refuse_rudely"},
            {"do it yourself", "conversations.topic.work_offer.followup", "work_offer.refuse_rudely"},
            {"ask someone else", "conversations.topic.work_offer.followup", "work_offer.refuse_rudely"},
            {"anything i can help with regardless", "conversations.topic.work_offer.none.respond", "work_offer.none.offer_anyway"},
            {"can i help anyway", "conversations.topic.work_offer.none.respond", "work_offer.none.offer_anyway"},
            {"anything at all i can help with", "conversations.topic.work_offer.none.respond", "work_offer.none.offer_anyway"},
            {"i will check back", "conversations.topic.work_offer.none.respond", "work_offer.none.ask_later"},
            {"i will ask later", "conversations.topic.work_offer.none.respond", "work_offer.none.ask_later"},
            {"i will come back later", "conversations.topic.work_offer.none.respond", "work_offer.none.ask_later"},
            {"it is a good place to live", "conversations.topic.village.respond", "village.respond.affirm"},
            {"lovely place", "conversations.topic.village.respond", "village.respond.affirm"},
            {"good place this", "conversations.topic.village.respond", "village.respond.affirm"},
            {"what would you change about it", "conversations.topic.village.respond", "village.respond.ask_improve"},
            {"how could it be better", "conversations.topic.village.respond", "village.respond.ask_improve"},
            {"what would you improve", "conversations.topic.village.respond", "village.respond.ask_improve"},
            {"it is a miserable little place", "conversations.topic.village.respond", "village.respond.insult"},
            {"this place is a dump", "conversations.topic.village.respond", "village.respond.insult"},
            {"awful place", "conversations.topic.village.respond", "village.respond.insult"},
            {"tell me and i will help fix it", "conversations.topic.village.followup", "village.followup.offer_help"},
            {"i will help fix it", "conversations.topic.village.followup", "village.followup.offer_help"},
            {"i can lend a hand with that", "conversations.topic.village.followup", "village.followup.offer_help"},
            {"it could be run better", "conversations.topic.village.followup", "village.followup.criticise_fairly"},
            {"it is badly managed", "conversations.topic.village.followup", "village.followup.criticise_fairly"},
            {"it could be better run", "conversations.topic.village.followup", "village.followup.criticise_fairly"},
            {"you are right about that", "conversations.topic.village.followup", "village.followup.agree"},
            {"that is true", "conversations.topic.village.followup", "village.followup.agree"},
            {"i agree with that", "conversations.topic.village.followup", "village.followup.agree"},
            {"that is a hard way to live", "conversations.topic.village.none.respond", "village.none.sympathise"},
            {"that sounds rough", "conversations.topic.village.none.respond", "village.none.sympathise"},
            {"that must be difficult", "conversations.topic.village.none.respond", "village.none.sympathise"},
            {"where do you stay", "conversations.topic.village.none.respond", "village.none.ask_where"},
            {"where do you sleep", "conversations.topic.village.none.respond", "village.none.ask_where"},
            {"where are you living", "conversations.topic.village.none.respond", "village.none.ask_where"},
            {"your own fault surely", "conversations.topic.village.none.respond", "village.none.dismiss"},
            {"that is your own doing", "conversations.topic.village.none.respond", "village.none.dismiss"},
            {"you are to blame", "conversations.topic.village.none.respond", "village.none.dismiss"},
            {"it is a good village", "conversations.topic.village.young.respond", "village.young.play_along"},
            {"nice village", "conversations.topic.village.young.respond", "village.young.play_along"},
            {"good village this", "conversations.topic.village.young.respond", "village.young.play_along"},
            {"what is the best bit", "conversations.topic.village.young.respond", "village.young.ask"},
            {"what is your favourite bit", "conversations.topic.village.young.respond", "village.young.ask"},
            {"best part of it", "conversations.topic.village.young.respond", "village.young.ask"},
            {"it is a bit dull", "conversations.topic.village.young.respond", "village.young.dismiss"},
            {"it is boring here", "conversations.topic.village.young.respond", "village.young.dismiss"},
            {"bit dull, this place", "conversations.topic.village.young.respond", "village.young.dismiss"},
            {"sorry, you have told me", "conversations.topic.village.again.respond", "village.again.apologize"},
            {"sorry, i already asked", "conversations.topic.village.again.respond", "village.again.apologize"},
            {"my mistake", "conversations.topic.village.again.respond", "village.again.apologize"},
            {"tell me again anyway", "conversations.topic.village.again.respond", "village.again.press"},
            {"go on, again", "conversations.topic.village.again.respond", "village.again.press"},
            {"again, please", "conversations.topic.village.again.respond", "village.again.press"},
            {"i have noticed the same", "conversations.topic.people.respond", "people.respond.agree"},
            {"i have seen that too", "conversations.topic.people.respond", "people.respond.agree"},
            {"same here", "conversations.topic.people.respond", "people.respond.agree"},
            {"they are not so bad", "conversations.topic.people.respond", "people.respond.defend"},
            {"that is a bit harsh", "conversations.topic.people.respond", "people.respond.defend"},
            {"that seems unfair to them", "conversations.topic.people.respond", "people.respond.defend"},
            {"give me an example", "conversations.topic.people.respond", "people.respond.ask_example"},
            {"for instance", "conversations.topic.people.respond", "people.respond.ask_example"},
            {"be specific", "conversations.topic.people.respond", "people.respond.ask_example"},
            {"that sounds exhausting", "conversations.topic.people.followup", "people.followup.empathise"},
            {"that sounds draining", "conversations.topic.people.followup", "people.followup.empathise"},
            {"that must be tiring", "conversations.topic.people.followup", "people.followup.empathise"},
            {"have you tried talking to them", "conversations.topic.people.followup", "people.followup.encourage_repair"},
            {"could you mend it", "conversations.topic.people.followup", "people.followup.encourage_repair"},
            {"have you talked to them", "conversations.topic.people.followup", "people.followup.encourage_repair"},
            {"who is the worst", "conversations.topic.people.followup", "people.followup.push_gossip"},
            {"go on, name them", "conversations.topic.people.followup", "people.followup.push_gossip"},
            {"who is the worst of them", "conversations.topic.people.followup", "people.followup.push_gossip"},
            {"who do you get on with", "conversations.topic.people.young.respond", "people.young.listen"},
            {"who are your friends", "conversations.topic.people.young.respond", "people.young.listen"},
            {"who do you like here", "conversations.topic.people.young.respond", "people.young.listen"},
            {"is anyone unkind to you", "conversations.topic.people.young.respond", "people.young.ask"},
            {"is anyone mean to you", "conversations.topic.people.young.respond", "people.young.ask"},
            {"does anyone bully you", "conversations.topic.people.young.respond", "people.young.ask"},
            {"you will grow out of caring", "conversations.topic.people.young.respond", "people.young.dismiss"},
            {"it will not matter when you are older", "conversations.topic.people.young.respond", "people.young.dismiss"},
            {"you will stop caring", "conversations.topic.people.young.respond", "people.young.dismiss"},
            {"sorry, asked already", "conversations.topic.people.again.respond", "people.again.apologize"},
            {"sorry, i already asked", "conversations.topic.people.again.respond", "people.again.apologize"},
            {"my mistake", "conversations.topic.people.again.respond", "people.again.apologize"},
            {"tell me anyway", "conversations.topic.people.again.respond", "people.again.press"},
            {"go on, again", "conversations.topic.people.again.respond", "people.again.press"},
            {"again, please", "conversations.topic.people.again.respond", "people.again.press"},
            {"who told you that", "conversations.topic.rumors.respond", "rumors.respond.ask_source"},
            {"where did you hear it", "conversations.topic.rumors.respond", "rumors.respond.ask_source"},
            {"who did you hear that from", "conversations.topic.rumors.respond", "rumors.respond.ask_source"},
            {"that does not sound reliable", "conversations.topic.rumors.respond", "rumors.respond.challenge"},
            {"i doubt that", "conversations.topic.rumors.respond", "rumors.respond.challenge"},
            {"is that even true", "conversations.topic.rumors.respond", "rumors.respond.challenge"},
            {"go on", "conversations.topic.rumors.respond", "rumors.respond.listen"},
            {"tell me more", "conversations.topic.rumors.respond", "rumors.respond.listen"},
            {"continue", "conversations.topic.rumors.respond", "rumors.respond.listen"},
            {"it stops with me", "conversations.topic.rumors.followup", "rumors.followup.promise_discretion"},
            {"i will not repeat it", "conversations.topic.rumors.followup", "rumors.followup.promise_discretion"},
            {"your secret is safe", "conversations.topic.rumors.followup", "rumors.followup.promise_discretion"},
            {"best forgotten", "conversations.topic.rumors.followup", "rumors.followup.let_it_go"},
            {"let us forget it", "conversations.topic.rumors.followup", "rumors.followup.let_it_go"},
            {"we should drop it", "conversations.topic.rumors.followup", "rumors.followup.let_it_go"},
            {"who else knows", "conversations.topic.rumors.followup", "rumors.followup.encourage_spread"},
            {"who else have you told", "conversations.topic.rumors.followup", "rumors.followup.encourage_spread"},
            {"does anyone else know", "conversations.topic.rumors.followup", "rumors.followup.encourage_spread"},
            {"a quiet week suits me", "conversations.topic.rumors.none.respond", "rumors.none.relieved"},
            {"good, quiet is fine", "conversations.topic.rumors.none.respond", "rumors.none.relieved"},
            {"quiet suits me", "conversations.topic.rumors.none.respond", "rumors.none.relieved"},
            {"nothing at all", "conversations.topic.rumors.none.respond", "rumors.none.ask_anyway"},
            {"really, nothing", "conversations.topic.rumors.none.respond", "rumors.none.ask_anyway"},
            {"nothing whatsoever", "conversations.topic.rumors.none.respond", "rumors.none.ask_anyway"},
            {"that is awful, how is everyone", "conversations.topic.news.sad.respond", "news.sad.compassion"},
            {"that is terrible", "conversations.topic.news.sad.respond", "news.sad.compassion"},
            {"that is awful news", "conversations.topic.news.sad.respond", "news.sad.compassion"},
            {"what happened exactly", "conversations.topic.news.sad.respond", "news.sad.ask_more"},
            {"what happened", "conversations.topic.news.sad.respond", "news.sad.ask_more"},
            {"how did it happen", "conversations.topic.news.sad.respond", "news.sad.ask_more"},
            {"serves them right", "conversations.topic.news.sad.respond", "news.sad.amused"},
            {"they deserved it", "conversations.topic.news.sad.respond", "news.sad.amused"},
            {"that is funny", "conversations.topic.news.sad.respond", "news.sad.amused"},
            {"that is wonderful news", "conversations.topic.news.glad.respond", "news.glad.celebrate"},
            {"brilliant", "conversations.topic.news.glad.respond", "news.glad.celebrate"},
            {"that is great news", "conversations.topic.news.glad.respond", "news.glad.celebrate"},
            {"tell me the whole story", "conversations.topic.news.glad.respond", "news.glad.ask_more"},
            {"tell me all of it", "conversations.topic.news.glad.respond", "news.glad.ask_more"},
            {"i want the whole story", "conversations.topic.news.glad.respond", "news.glad.ask_more"},
            {"it will not last", "conversations.topic.news.glad.respond", "news.glad.sour"},
            {"these things never last", "conversations.topic.news.glad.respond", "news.glad.sour"},
            {"that will not last", "conversations.topic.news.glad.respond", "news.glad.sour"},
            {"what brought that on", "conversations.topic.news.mixed.respond", "news.mixed.curious"},
            {"what caused it", "conversations.topic.news.mixed.respond", "news.mixed.curious"},
            {"why did that happen", "conversations.topic.news.mixed.respond", "news.mixed.curious"},
            {"i hope it works out for them", "conversations.topic.news.mixed.respond", "news.mixed.wish_well"},
            {"i hope they do well", "conversations.topic.news.mixed.respond", "news.mixed.wish_well"},
            {"hope it works out", "conversations.topic.news.mixed.respond", "news.mixed.wish_well"},
            {"there is more to that story", "conversations.topic.news.mixed.respond", "news.mixed.skeptical"},
            {"something behind that", "conversations.topic.news.mixed.respond", "news.mixed.skeptical"},
            {"there is more to it", "conversations.topic.news.mixed.respond", "news.mixed.skeptical"},
            {"i will not repeat any of it", "conversations.topic.news.followup", "news.followup.keep_quiet"},
            {"i will keep quiet", "conversations.topic.news.followup", "news.followup.keep_quiet"},
            {"nobody will hear it from me", "conversations.topic.news.followup", "news.followup.keep_quiet"},
            {"are you coping with it", "conversations.topic.news.followup", "news.followup.ask_how_they_are"},
            {"how are you coping", "conversations.topic.news.followup", "news.followup.ask_how_they_are"},
            {"and yourself", "conversations.topic.news.followup", "news.followup.ask_how_they_are"},
            {"everyone should hear about this", "conversations.topic.news.followup", "news.followup.spread"},
            {"everyone should know", "conversations.topic.news.followup", "news.followup.spread"},
            {"people should hear this", "conversations.topic.news.followup", "news.followup.spread"},
            {"quiet suits a village", "conversations.topic.news.none.respond", "news.none.chat_anyway"},
            {"quiet is good", "conversations.topic.news.none.respond", "news.none.chat_anyway"},
            {"quiet suits this place", "conversations.topic.news.none.respond", "news.none.chat_anyway"},
            {"i have had a week of it myself", "conversations.topic.news.none.respond", "news.none.share_own"},
            {"same week for me", "conversations.topic.news.none.respond", "news.none.share_own"},
            {"mine has been quiet too", "conversations.topic.news.none.respond", "news.none.share_own"},
            {"you are allowed to feel that", "conversations.topic.noticed.grieving.respond", "noticed.grieving.validate"},
            {"you are allowed to grieve", "conversations.topic.noticed.grieving.respond", "noticed.grieving.validate"},
            {"it is alright to feel that", "conversations.topic.noticed.grieving.respond", "noticed.grieving.validate"},
            {"i will not make you talk about it", "conversations.topic.noticed.grieving.respond", "noticed.grieving.give_space"},
            {"i will not push", "conversations.topic.noticed.grieving.respond", "noticed.grieving.give_space"},
            {"i will not force it", "conversations.topic.noticed.grieving.respond", "noticed.grieving.give_space"},
            {"people die, that is life", "conversations.topic.noticed.grieving.respond", "noticed.grieving.dismiss"},
            {"it happens", "conversations.topic.noticed.grieving.respond", "noticed.grieving.dismiss"},
            {"that is life", "conversations.topic.noticed.grieving.respond", "noticed.grieving.dismiss"},
            {"i am sorry for what i did", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.apologize"},
            {"sorry, that was wrong of me", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.apologize"},
            {"i am sorry", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.apologize"},
            {"let me explain myself", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.explain"},
            {"let me explain", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.explain"},
            {"there was a reason", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.explain"},
            {"you are still on about that", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.brush_off"},
            {"get over it", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.brush_off"},
            {"still holding a grudge", "conversations.topic.noticed.annoyed.respond", "noticed.annoyed.brush_off"},
            {"it is good to see you like this", "conversations.topic.noticed.elated.respond", "noticed.elated.share_joy"},
            {"good to see you happy", "conversations.topic.noticed.elated.respond", "noticed.elated.share_joy"},
            {"nice to see you like this", "conversations.topic.noticed.elated.respond", "noticed.elated.share_joy"},
            {"what has brought this on", "conversations.topic.noticed.elated.respond", "noticed.elated.ask_more"},
            {"what caused this", "conversations.topic.noticed.elated.respond", "noticed.elated.ask_more"},
            {"what brought that on", "conversations.topic.noticed.elated.respond", "noticed.elated.ask_more"},
            {"do not get carried away", "conversations.topic.noticed.elated.respond", "noticed.elated.deflate"},
            {"do not get ahead of yourself", "conversations.topic.noticed.elated.respond", "noticed.elated.deflate"},
            {"steady on", "conversations.topic.noticed.elated.respond", "noticed.elated.deflate"},
            {"steady is not nothing", "conversations.topic.noticed.fine.respond", "noticed.fine.glad"},
            {"steady is something", "conversations.topic.noticed.fine.respond", "noticed.fine.glad"},
            {"steady will do", "conversations.topic.noticed.fine.respond", "noticed.fine.glad"},
            {"really though", "conversations.topic.noticed.fine.respond", "noticed.fine.dig"},
            {"are you sure", "conversations.topic.noticed.fine.respond", "noticed.fine.dig"},
            {"really, though?", "conversations.topic.noticed.fine.respond", "noticed.fine.dig"},
            {"not much of an answer", "conversations.topic.noticed.fine.respond", "noticed.fine.dismiss"},
            {"that is hardly an answer", "conversations.topic.noticed.fine.respond", "noticed.fine.dismiss"},
            {"not much to go on", "conversations.topic.noticed.fine.respond", "noticed.fine.dismiss"},
            {"tell me what would help", "conversations.topic.noticed.followup", "noticed.followup.offer_help"},
            {"what do you need", "conversations.topic.noticed.followup", "noticed.followup.offer_help"},
            {"what would help", "conversations.topic.noticed.followup", "noticed.followup.offer_help"},
            {"i will be around", "conversations.topic.noticed.followup", "noticed.followup.just_be_there"},
            {"i am here", "conversations.topic.noticed.followup", "noticed.followup.just_be_there"},
            {"i will be nearby", "conversations.topic.noticed.followup", "noticed.followup.just_be_there"},
            {"let us talk about something else", "conversations.topic.noticed.followup", "noticed.followup.change_subject"},
            {"change the subject", "conversations.topic.noticed.followup", "noticed.followup.change_subject"},
            {"something else then", "conversations.topic.noticed.followup", "noticed.followup.change_subject"},

            // --- Phase 5: the personal topics and their arcs.
            {"that is yours to keep", "conversations.topic.life.guarded.respond", "life.guarded.respect"},
            {"keep it to yourself", "conversations.topic.life.guarded.respond", "life.guarded.respect"},
            {"that story is yours", "conversations.topic.life.guarded.respond", "life.guarded.respect"},
            {"tell me something lighter", "conversations.topic.life.guarded.respond", "life.guarded.ask_safer"},
            {"something easier then", "conversations.topic.life.guarded.respond", "life.guarded.ask_safer"},
            {"let us keep it light", "conversations.topic.life.guarded.respond", "life.guarded.ask_safer"},
            {"come on, you can tell me", "conversations.topic.life.guarded.respond", "life.guarded.press"},
            {"tell me the story", "conversations.topic.life.guarded.respond", "life.guarded.press"},
            {"go on, tell me", "conversations.topic.life.guarded.respond", "life.guarded.press"},
            {"sorry, i have asked already", "conversations.topic.life.again.respond", "life.again.apologize"},
            {"sorry about the story", "conversations.topic.life.again.respond", "life.again.apologize"},
            {"my mistake, asked already", "conversations.topic.life.again.respond", "life.again.apologize"},
            {"tell me again anyway", "conversations.topic.life.again.respond", "life.again.press"},
            {"the story again, please", "conversations.topic.life.again.respond", "life.again.press"},
            {"go on, again", "conversations.topic.life.again.respond", "life.again.press"},
            {"thank you for telling me", "conversations.topic.life.close", "life.close.thank"},
            {"thank you for the story", "conversations.topic.life.close", "life.close.thank"},
            {"i am grateful you told me", "conversations.topic.life.close", "life.close.thank"},
            {"that took something to say", "conversations.topic.life.close", "life.close.say_means"},
            {"that was brave of you", "conversations.topic.life.close", "life.close.say_means"},
            {"that took courage", "conversations.topic.life.close", "life.close.say_means"},
            {"that is yours to keep", "conversations.topic.dreams.guarded.respond", "dreams.guarded.respect"},
            {"keep it to yourself", "conversations.topic.dreams.guarded.respond", "dreams.guarded.respect"},
            {"that dream is yours", "conversations.topic.dreams.guarded.respond", "dreams.guarded.respect"},
            {"tell me something lighter", "conversations.topic.dreams.guarded.respond", "dreams.guarded.ask_safer"},
            {"something easier then", "conversations.topic.dreams.guarded.respond", "dreams.guarded.ask_safer"},
            {"let us keep it light", "conversations.topic.dreams.guarded.respond", "dreams.guarded.ask_safer"},
            {"come on, you can tell me", "conversations.topic.dreams.guarded.respond", "dreams.guarded.press"},
            {"tell me the dream", "conversations.topic.dreams.guarded.respond", "dreams.guarded.press"},
            {"go on, tell me", "conversations.topic.dreams.guarded.respond", "dreams.guarded.press"},
            {"sorry, i have asked already", "conversations.topic.dreams.again.respond", "dreams.again.apologize"},
            {"sorry about the dream", "conversations.topic.dreams.again.respond", "dreams.again.apologize"},
            {"my mistake, asked already", "conversations.topic.dreams.again.respond", "dreams.again.apologize"},
            {"tell me again anyway", "conversations.topic.dreams.again.respond", "dreams.again.press"},
            {"the dream again, please", "conversations.topic.dreams.again.respond", "dreams.again.press"},
            {"go on, again", "conversations.topic.dreams.again.respond", "dreams.again.press"},
            {"thank you for telling me", "conversations.topic.dreams.close", "dreams.close.thank"},
            {"thank you for the dream", "conversations.topic.dreams.close", "dreams.close.thank"},
            {"i am grateful you told me", "conversations.topic.dreams.close", "dreams.close.thank"},
            {"that took something to say", "conversations.topic.dreams.close", "dreams.close.say_means"},
            {"that was brave of you", "conversations.topic.dreams.close", "dreams.close.say_means"},
            {"that took courage", "conversations.topic.dreams.close", "dreams.close.say_means"},
            {"that is yours to keep", "conversations.topic.hopes.guarded.respond", "hopes.guarded.respect"},
            {"keep it to yourself", "conversations.topic.hopes.guarded.respond", "hopes.guarded.respect"},
            {"that hope is yours", "conversations.topic.hopes.guarded.respond", "hopes.guarded.respect"},
            {"tell me something lighter", "conversations.topic.hopes.guarded.respond", "hopes.guarded.ask_safer"},
            {"something easier then", "conversations.topic.hopes.guarded.respond", "hopes.guarded.ask_safer"},
            {"let us keep it light", "conversations.topic.hopes.guarded.respond", "hopes.guarded.ask_safer"},
            {"come on, you can tell me", "conversations.topic.hopes.guarded.respond", "hopes.guarded.press"},
            {"tell me the hope", "conversations.topic.hopes.guarded.respond", "hopes.guarded.press"},
            {"go on, tell me", "conversations.topic.hopes.guarded.respond", "hopes.guarded.press"},
            {"sorry, i have asked already", "conversations.topic.hopes.again.respond", "hopes.again.apologize"},
            {"sorry about the hope", "conversations.topic.hopes.again.respond", "hopes.again.apologize"},
            {"my mistake, asked already", "conversations.topic.hopes.again.respond", "hopes.again.apologize"},
            {"tell me again anyway", "conversations.topic.hopes.again.respond", "hopes.again.press"},
            {"the hope again, please", "conversations.topic.hopes.again.respond", "hopes.again.press"},
            {"go on, again", "conversations.topic.hopes.again.respond", "hopes.again.press"},
            {"thank you for telling me", "conversations.topic.hopes.close", "hopes.close.thank"},
            {"thank you for the hope", "conversations.topic.hopes.close", "hopes.close.thank"},
            {"i am grateful you told me", "conversations.topic.hopes.close", "hopes.close.thank"},
            {"that took something to say", "conversations.topic.hopes.close", "hopes.close.say_means"},
            {"that was brave of you", "conversations.topic.hopes.close", "hopes.close.say_means"},
            {"that took courage", "conversations.topic.hopes.close", "hopes.close.say_means"},
            {"that is yours to keep", "conversations.topic.regrets.guarded.respond", "regrets.guarded.respect"},
            {"keep it to yourself", "conversations.topic.regrets.guarded.respond", "regrets.guarded.respect"},
            {"that regret is yours", "conversations.topic.regrets.guarded.respond", "regrets.guarded.respect"},
            {"tell me something lighter", "conversations.topic.regrets.guarded.respond", "regrets.guarded.ask_safer"},
            {"something easier then", "conversations.topic.regrets.guarded.respond", "regrets.guarded.ask_safer"},
            {"let us keep it light", "conversations.topic.regrets.guarded.respond", "regrets.guarded.ask_safer"},
            {"come on, you can tell me", "conversations.topic.regrets.guarded.respond", "regrets.guarded.press"},
            {"tell me the regret", "conversations.topic.regrets.guarded.respond", "regrets.guarded.press"},
            {"go on, tell me", "conversations.topic.regrets.guarded.respond", "regrets.guarded.press"},
            {"sorry, i have asked already", "conversations.topic.regrets.again.respond", "regrets.again.apologize"},
            {"sorry about the regret", "conversations.topic.regrets.again.respond", "regrets.again.apologize"},
            {"my mistake, asked already", "conversations.topic.regrets.again.respond", "regrets.again.apologize"},
            {"tell me again anyway", "conversations.topic.regrets.again.respond", "regrets.again.press"},
            {"the regret again, please", "conversations.topic.regrets.again.respond", "regrets.again.press"},
            {"go on, again", "conversations.topic.regrets.again.respond", "regrets.again.press"},
            {"thank you for telling me", "conversations.topic.regrets.close", "regrets.close.thank"},
            {"thank you for the regret", "conversations.topic.regrets.close", "regrets.close.thank"},
            {"i am grateful you told me", "conversations.topic.regrets.close", "regrets.close.thank"},
            {"that took something to say", "conversations.topic.regrets.close", "regrets.close.say_means"},
            {"that was brave of you", "conversations.topic.regrets.close", "regrets.close.say_means"},
            {"that took courage", "conversations.topic.regrets.close", "regrets.close.say_means"},
            {"that is yours to keep", "conversations.topic.secret.guarded.respond", "secret.guarded.respect"},
            {"keep it to yourself", "conversations.topic.secret.guarded.respond", "secret.guarded.respect"},
            {"that secret is yours", "conversations.topic.secret.guarded.respond", "secret.guarded.respect"},
            {"tell me something lighter", "conversations.topic.secret.guarded.respond", "secret.guarded.ask_safer"},
            {"something easier then", "conversations.topic.secret.guarded.respond", "secret.guarded.ask_safer"},
            {"let us keep it light", "conversations.topic.secret.guarded.respond", "secret.guarded.ask_safer"},
            {"come on, you can tell me", "conversations.topic.secret.guarded.respond", "secret.guarded.press"},
            {"tell me the secret", "conversations.topic.secret.guarded.respond", "secret.guarded.press"},
            {"go on, tell me", "conversations.topic.secret.guarded.respond", "secret.guarded.press"},
            {"sorry, i have asked already", "conversations.topic.secret.again.respond", "secret.again.apologize"},
            {"sorry about the secret", "conversations.topic.secret.again.respond", "secret.again.apologize"},
            {"my mistake, asked already", "conversations.topic.secret.again.respond", "secret.again.apologize"},
            {"tell me again anyway", "conversations.topic.secret.again.respond", "secret.again.press"},
            {"the secret again, please", "conversations.topic.secret.again.respond", "secret.again.press"},
            {"go on, again", "conversations.topic.secret.again.respond", "secret.again.press"},
            {"thank you for telling me", "conversations.topic.secret.close", "secret.close.thank"},
            {"thank you for the secret", "conversations.topic.secret.close", "secret.close.thank"},
            {"i am grateful you told me", "conversations.topic.secret.close", "secret.close.thank"},
            {"that took something to say", "conversations.topic.secret.close", "secret.close.say_means"},
            {"that was brave of you", "conversations.topic.secret.close", "secret.close.say_means"},
            {"that took courage", "conversations.topic.secret.close", "secret.close.say_means"},
            {"tell me properly", "conversations.topic.life.young.respond", "life.young.interested"},
            {"tell me the whole story", "conversations.topic.life.young.respond", "life.young.interested"},
            {"go on, properly", "conversations.topic.life.young.respond", "life.young.interested"},
            {"that is worth having", "conversations.topic.life.young.respond", "life.young.encourage"},
            {"that story is worth having", "conversations.topic.life.young.respond", "life.young.encourage"},
            {"worth holding onto", "conversations.topic.life.young.respond", "life.young.encourage"},
            {"you will change your mind", "conversations.topic.life.young.respond", "life.young.dismiss"},
            {"you will grow out of it", "conversations.topic.life.young.respond", "life.young.dismiss"},
            {"you will think differently", "conversations.topic.life.young.respond", "life.young.dismiss"},
            {"tell me properly", "conversations.topic.dreams.young.respond", "dreams.young.interested"},
            {"tell me the whole dream", "conversations.topic.dreams.young.respond", "dreams.young.interested"},
            {"go on, properly", "conversations.topic.dreams.young.respond", "dreams.young.interested"},
            {"that is worth having", "conversations.topic.dreams.young.respond", "dreams.young.encourage"},
            {"that dream is worth having", "conversations.topic.dreams.young.respond", "dreams.young.encourage"},
            {"worth holding onto", "conversations.topic.dreams.young.respond", "dreams.young.encourage"},
            {"you will change your mind", "conversations.topic.dreams.young.respond", "dreams.young.dismiss"},
            {"you will grow out of it", "conversations.topic.dreams.young.respond", "dreams.young.dismiss"},
            {"you will think differently", "conversations.topic.dreams.young.respond", "dreams.young.dismiss"},
            {"tell me properly", "conversations.topic.hopes.young.respond", "hopes.young.interested"},
            {"tell me the whole hope", "conversations.topic.hopes.young.respond", "hopes.young.interested"},
            {"go on, properly", "conversations.topic.hopes.young.respond", "hopes.young.interested"},
            {"that is worth having", "conversations.topic.hopes.young.respond", "hopes.young.encourage"},
            {"that hope is worth having", "conversations.topic.hopes.young.respond", "hopes.young.encourage"},
            {"worth holding onto", "conversations.topic.hopes.young.respond", "hopes.young.encourage"},
            {"you will change your mind", "conversations.topic.hopes.young.respond", "hopes.young.dismiss"},
            {"you will grow out of it", "conversations.topic.hopes.young.respond", "hopes.young.dismiss"},
            {"you will think differently", "conversations.topic.hopes.young.respond", "hopes.young.dismiss"},
            {"which part matters most", "conversations.topic.life.respond", "life.respond.ask_which"},
            {"which part matters to you", "conversations.topic.life.respond", "life.respond.ask_which"},
            {"what part matters", "conversations.topic.life.respond", "life.respond.ask_which"},
            {"that cannot have been easy", "conversations.topic.life.respond", "life.respond.empathise"},
            {"that sounds hard", "conversations.topic.life.respond", "life.respond.empathise"},
            {"that must have been rough", "conversations.topic.life.respond", "life.respond.empathise"},
            {"you could have done better", "conversations.topic.life.respond", "life.respond.judge"},
            {"you should have known better", "conversations.topic.life.respond", "life.respond.judge"},
            {"that was wrong of you", "conversations.topic.life.respond", "life.respond.judge"},
            {"tell me more about that part", "conversations.topic.life.followup", "life.followup.follow_thread"},
            {"continue that part", "conversations.topic.life.followup", "life.followup.follow_thread"},
            {"more about that bit", "conversations.topic.life.followup", "life.followup.follow_thread"},
            {"something like that happened to me", "conversations.topic.life.followup", "life.followup.compare_own"},
            {"something similar happened to me", "conversations.topic.life.followup", "life.followup.compare_own"},
            {"same happened to me", "conversations.topic.life.followup", "life.followup.compare_own"},
            {"let us leave it there", "conversations.topic.life.followup", "life.followup.change_subject"},
            {"that is enough of that", "conversations.topic.life.followup", "life.followup.change_subject"},
            {"leave it there", "conversations.topic.life.followup", "life.followup.change_subject"},
            {"you never finished that story", "conversations.arc.life.resume.respond", "life.resume.ask_chapter"},
            {"how did it end", "conversations.arc.life.resume.respond", "life.resume.ask_chapter"},
            {"tell me the rest of it", "conversations.arc.life.resume.respond", "life.resume.ask_chapter"},
            {"where does that leave you now", "conversations.arc.life.resume.respond", "life.resume.ask_now"},
            {"and now", "conversations.arc.life.resume.respond", "life.resume.ask_now"},
            {"how are you with it today", "conversations.arc.life.resume.respond", "life.resume.ask_now"},
            {"it was a long time ago", "conversations.arc.life.resume.respond", "life.resume.brush_off"},
            {"that is in the past", "conversations.arc.life.resume.respond", "life.resume.brush_off"},
            {"ancient history", "conversations.arc.life.resume.respond", "life.resume.brush_off"},
            {"you should chase that", "conversations.topic.dreams.respond", "dreams.respond.encourage"},
            {"go and pursue it", "conversations.topic.dreams.respond", "dreams.respond.encourage"},
            {"chase it", "conversations.topic.dreams.respond", "dreams.respond.encourage"},
            {"tell me more about it", "conversations.topic.dreams.respond", "dreams.respond.ask_more"},
            {"give me the detail", "conversations.topic.dreams.respond", "dreams.respond.ask_more"},
            {"say more about it", "conversations.topic.dreams.respond", "dreams.respond.ask_more"},
            {"that is a long way from here", "conversations.topic.dreams.respond", "dreams.respond.realism"},
            {"that is far off", "conversations.topic.dreams.respond", "dreams.respond.realism"},
            {"a long way to go", "conversations.topic.dreams.respond", "dreams.respond.realism"},
            {"i will help you get there", "conversations.topic.dreams.followup", "dreams.followup.pledge_help"},
            {"we will get there together", "conversations.topic.dreams.followup", "dreams.followup.pledge_help"},
            {"let me help you with it", "conversations.topic.dreams.followup", "dreams.followup.pledge_help"},
            {"i cannot promise help", "conversations.topic.dreams.followup", "dreams.followup.be_honest"},
            {"i will not promise anything", "conversations.topic.dreams.followup", "dreams.followup.be_honest"},
            {"glad you told me, but i cannot promise", "conversations.topic.dreams.followup", "dreams.followup.be_honest"},
            {"that is ridiculous", "conversations.topic.dreams.followup", "dreams.followup.mock"},
            {"do not make me laugh", "conversations.topic.dreams.followup", "dreams.followup.mock"},
            {"you, doing that? ridiculous", "conversations.topic.dreams.followup", "dreams.followup.mock"},
            {"any closer to it", "conversations.arc.dreams.resume.respond", "dreams.resume.ask_progress"},
            {"any progress", "conversations.arc.dreams.resume.respond", "dreams.resume.ask_progress"},
            {"are you any closer", "conversations.arc.dreams.resume.respond", "dreams.resume.ask_progress"},
            {"what is the first step", "conversations.arc.dreams.resume.respond", "dreams.resume.offer_step"},
            {"let us start with one step", "conversations.arc.dreams.resume.respond", "dreams.resume.offer_step"},
            {"the first step, i will help", "conversations.arc.dreams.resume.respond", "dreams.resume.offer_step"},
            {"still on about that", "conversations.arc.dreams.resume.respond", "dreams.resume.doubt"},
            {"are you still on that", "conversations.arc.dreams.resume.respond", "dreams.resume.doubt"},
            {"that again", "conversations.arc.dreams.resume.respond", "dreams.resume.doubt"},
            {"i am listening", "conversations.topic.hopes.respond", "hopes.respond.listen"},
            {"i will listen", "conversations.topic.hopes.respond", "hopes.respond.listen"},
            {"go on, i am listening", "conversations.topic.hopes.respond", "hopes.respond.listen"},
            {"i hope you get it", "conversations.topic.hopes.respond", "hopes.respond.encourage"},
            {"you deserve it", "conversations.topic.hopes.respond", "hopes.respond.encourage"},
            {"i hope it comes", "conversations.topic.hopes.respond", "hopes.respond.encourage"},
            {"that is a bit much to hope for", "conversations.topic.hopes.respond", "hopes.respond.mock"},
            {"rather ambitious", "conversations.topic.hopes.respond", "hopes.respond.mock"},
            {"keep dreaming", "conversations.topic.hopes.respond", "hopes.respond.mock"},
            {"what would the first step be", "conversations.topic.hopes.followup", "hopes.followup.ask_first_step"},
            {"where would you start", "conversations.topic.hopes.followup", "hopes.followup.ask_first_step"},
            {"what is step one", "conversations.topic.hopes.followup", "hopes.followup.ask_first_step"},
            {"i hope for something like that too", "conversations.topic.hopes.followup", "hopes.followup.share_own"},
            {"i want the same myself", "conversations.topic.hopes.followup", "hopes.followup.share_own"},
            {"me too", "conversations.topic.hopes.followup", "hopes.followup.share_own"},
            {"is that really what you want", "conversations.topic.hopes.followup", "hopes.followup.question_it"},
            {"are you sure that is what you want", "conversations.topic.hopes.followup", "hopes.followup.question_it"},
            {"do you really want that", "conversations.topic.hopes.followup", "hopes.followup.question_it"},
            {"still hoping for it", "conversations.arc.hopes.resume.respond", "hopes.resume.still_hoping"},
            {"are you still hoping", "conversations.arc.hopes.resume.respond", "hopes.resume.still_hoping"},
            {"still hoping", "conversations.arc.hopes.resume.respond", "hopes.resume.still_hoping"},
            {"let me help with it", "conversations.arc.hopes.resume.respond", "hopes.resume.offer_help"},
            {"i can help with that", "conversations.arc.hopes.resume.respond", "hopes.resume.offer_help"},
            {"give me a hand in it", "conversations.arc.hopes.resume.respond", "hopes.resume.offer_help"},
            {"you are still on that", "conversations.arc.hopes.resume.respond", "hopes.resume.dismiss"},
            {"that again", "conversations.arc.hopes.resume.respond", "hopes.resume.dismiss"},
            {"give it up", "conversations.arc.hopes.resume.respond", "hopes.resume.dismiss"},
            {"i am not going anywhere", "conversations.topic.regrets.respond", "regrets.respond.listen"},
            {"go on, i am listening", "conversations.topic.regrets.respond", "regrets.respond.listen"},
            {"i am staying right here", "conversations.topic.regrets.respond", "regrets.respond.listen"},
            {"is there anything left to put right", "conversations.topic.regrets.respond", "regrets.respond.ask_repair"},
            {"can it be fixed", "conversations.topic.regrets.respond", "regrets.respond.ask_repair"},
            {"anything left to make right", "conversations.topic.regrets.respond", "regrets.respond.ask_repair"},
            {"what exactly did you do", "conversations.topic.regrets.respond", "regrets.respond.pry"},
            {"give me the details", "conversations.topic.regrets.respond", "regrets.respond.pry"},
            {"what did you do exactly", "conversations.topic.regrets.respond", "regrets.respond.pry"},
            {"i do not think you are a bad person", "conversations.topic.regrets.followup", "regrets.followup.forgive"},
            {"i forgive you", "conversations.topic.regrets.followup", "regrets.followup.forgive"},
            {"you are not a bad person", "conversations.topic.regrets.followup", "regrets.followup.forgive"},
            {"i will not tell you it was fine", "conversations.topic.regrets.followup", "regrets.followup.sit_with_it"},
            {"i am here anyway", "conversations.topic.regrets.followup", "regrets.followup.sit_with_it"},
            {"i will not pretend it was fine", "conversations.topic.regrets.followup", "regrets.followup.sit_with_it"},
            {"you could still make it right", "conversations.topic.regrets.followup", "regrets.followup.challenge"},
            {"it is not too late to make it right", "conversations.topic.regrets.followup", "regrets.followup.challenge"},
            {"you can still put it right", "conversations.topic.regrets.followup", "regrets.followup.challenge"},
            {"has it sat any easier since", "conversations.arc.regrets.resume.respond", "regrets.resume.ask_since"},
            {"is it easier now", "conversations.arc.regrets.resume.respond", "regrets.resume.ask_since"},
            {"any easier since we spoke", "conversations.arc.regrets.resume.respond", "regrets.resume.ask_since"},
            {"have you thought any more about putting it right", "conversations.arc.regrets.resume.respond", "regrets.resume.encourage_repair"},
            {"thought more about it", "conversations.arc.regrets.resume.respond", "regrets.resume.encourage_repair"},
            {"any more thought about fixing it", "conversations.arc.regrets.resume.respond", "regrets.resume.encourage_repair"},
            {"about that thing you did", "conversations.arc.regrets.resume.respond", "regrets.resume.bring_it_up"},
            {"remember that thing you did", "conversations.arc.regrets.resume.respond", "regrets.resume.bring_it_up"},
            {"that thing you did", "conversations.arc.regrets.resume.respond", "regrets.resume.bring_it_up"},
            {"i will hear it", "conversations.topic.secret.respond", "secret.respond.accept"},
            {"i am listening", "conversations.topic.secret.respond", "secret.respond.accept"},
            {"go on, i will hear it", "conversations.topic.secret.respond", "secret.respond.accept"},
            {"why me", "conversations.topic.secret.respond", "secret.respond.ask_why_me"},
            {"why choose me", "conversations.topic.secret.respond", "secret.respond.ask_why_me"},
            {"why tell me", "conversations.topic.secret.respond", "secret.respond.ask_why_me"},
            {"do not tell me something you will regret", "conversations.topic.secret.respond", "secret.respond.decline"},
            {"are you sure you want to tell me", "conversations.topic.secret.respond", "secret.respond.decline"},
            {"you might regret telling me", "conversations.topic.secret.respond", "secret.respond.decline"},
            {"tell me when you are ready", "conversations.topic.secret.declined", "secret.declined.offer_later"},
            {"whenever you are ready", "conversations.topic.secret.declined", "secret.declined.offer_later"},
            {"another day then, when you want to", "conversations.topic.secret.declined", "secret.declined.offer_later"},
            {"talk to me about something easier", "conversations.topic.secret.declined", "secret.declined.change_subject"},
            {"let us talk about something else", "conversations.topic.secret.declined", "secret.declined.change_subject"},
            {"something lighter instead", "conversations.topic.secret.declined", "secret.declined.change_subject"},
            {"it stays with me, you have my word", "conversations.topic.secret.followup", "secret.followup.promise"},
            {"i promise", "conversations.topic.secret.followup", "secret.followup.promise"},
            {"you have my word", "conversations.topic.secret.followup", "secret.followup.promise"},
            {"i will not promise, but i will try", "conversations.topic.secret.followup", "secret.followup.no_promise"},
            {"i can only try", "conversations.topic.secret.followup", "secret.followup.no_promise"},
            {"no promises, but i will try", "conversations.topic.secret.followup", "secret.followup.no_promise"},
            {"is that all it was", "conversations.topic.secret.followup", "secret.followup.trivialise"},
            {"hardly worth the secrecy", "conversations.topic.secret.followup", "secret.followup.trivialise"},
            {"that is nothing", "conversations.topic.secret.followup", "secret.followup.trivialise"},
            {"i have told nobody", "conversations.arc.secret.resume.respond", "secret.resume.reference"},
            {"i kept it", "conversations.arc.secret.resume.respond", "secret.resume.reference"},
            {"nobody has heard it from me", "conversations.arc.secret.resume.respond", "secret.resume.reference"},
            {"how are you carrying it", "conversations.arc.secret.resume.respond", "secret.resume.check_in"},
            {"how are you bearing it", "conversations.arc.secret.resume.respond", "secret.resume.check_in"},
            {"how is it sitting with you", "conversations.arc.secret.resume.respond", "secret.resume.check_in"},
            {"does anyone else know", "conversations.arc.secret.resume.respond", "secret.resume.ask_who_knows"},
            {"who else knows", "conversations.arc.secret.resume.respond", "secret.resume.ask_who_knows"},
            {"has anyone else been told", "conversations.arc.secret.resume.respond", "secret.resume.ask_who_knows"},
            {"i have been thinking of letting it slip", "conversations.arc.secret.resume.respond", "secret.resume.hint_publicly"},
            {"i might mention it to the others", "conversations.arc.secret.resume.respond", "secret.resume.hint_publicly"},
            {"maybe the others should know", "conversations.arc.secret.resume.respond", "secret.resume.hint_publicly"},
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
