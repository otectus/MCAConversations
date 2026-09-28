package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The "fully discussed" rule behind {@code topics.hideExhaustedTopics}, exercised without a server:
 * one answered reply followed by an authored exit counts; a first-page bail-out, a closed screen and a
 * guarded opening do not; a repeatable topic never hides.
 */
class TopicExhaustionTest {

    private static final TopicEntry LIFE = TopicEntry.fromJson("life", JsonParser.parseString("""
            {"entry":{"question":"conversations.cat.personal","answer":"life"},"depth":"deep",
             "return_question":"conversations.cat.personal","ages":["adult"],
             "required_stance_families":["curiosity","exit"]}
            """).getAsJsonObject());

    private static final TopicEntry NEWS = TopicEntry.fromJson("news", JsonParser.parseString("""
            {"entry":{"question":"conversations.cat.events","answer":"news"},"depth":"quick",
             "return_question":"conversations.cat.events","ages":["adult"],
             "required_stance_families":["curiosity","exit"],"repeatable":true}
            """).getAsJsonObject());

    private static BeatContract beat(String id, String topic, String act, String openness) {
        return BeatContract.fromJson(id, JsonParser.parseString("""
                {"topic":"%s","say":"conversations.scene.life.x","response_question":"conversations.scene.life.x.respond",
                 "npc_act":"%s","subject":"life.now","polarity":"mixed","openness":"%s",
                 "allowed_stances":["curiosity","exit"]}
                """.formatted(topic, act, openness)).getAsJsonObject());
    }

    private static Optional<ReplyContract> reply(String question, String answer, String stance, boolean exit) {
        return Optional.of(ReplyContract.fromJson(question + "/" + answer, JsonParser.parseString("""
                {"stance":"%s","exit":%s}
                """.formatted(stance, exit)).getAsJsonObject()));
    }

    @Test
    @DisplayName("hidden only when the switch is on, the topic is finished and it is not repeatable")
    void hidden() {
        assertTrue(TopicExhaustion.hidden(LIFE, true, true));
        assertFalse(TopicExhaustion.hidden(LIFE, false, true), "switched off: shown, the record still stands");
        assertFalse(TopicExhaustion.hidden(LIFE, true, false));
        assertFalse(TopicExhaustion.hidden(NEWS, true, true), "repeatable is never hidden");
        assertFalse(TopicExhaustion.hidden(null, true, true));
    }

    @Test
    @DisplayName("the topic's own pages are inside; the hub, main and its entry/return page are outside")
    void insideAndOutside() {
        assertFalse(TopicExhaustion.outsideTopic(LIFE, "conversations.scene.life.the_chapter_im_in.respond"));
        assertFalse(TopicExhaustion.outsideTopic(LIFE, "conversations.topic.life.close"));
        assertFalse(TopicExhaustion.outsideTopic(LIFE, "pastimes.life.page"), "an unknown page is inside");
        assertTrue(TopicExhaustion.outsideTopic(LIFE, "conversations.cat.personal"));
        assertTrue(TopicExhaustion.outsideTopic(LIFE, "conversations"));
        assertTrue(TopicExhaustion.outsideTopic(LIFE, "main"));
        assertTrue(TopicExhaustion.outsideTopic(LIFE, null));
    }

    @Test
    @DisplayName("a reply is substantive unless its contract, or failing that its name, is an exit")
    void substantive() {
        assertTrue(TopicExhaustion.substantive(reply("q", "ask_which", "curiosity", false), "ask_which"));
        assertFalse(TopicExhaustion.substantive(reply("q", "leave", "exit", true), "leave"));
        assertFalse(TopicExhaustion.substantive(Optional.empty(), "leave"));
        assertFalse(TopicExhaustion.substantive(Optional.empty(), "back"));
        assertTrue(TopicExhaustion.substantive(Optional.empty(), "interested"), "an uncontracted reply counts");
        assertFalse(TopicExhaustion.substantive(Optional.empty(), ""));
    }

    @Test
    @DisplayName("a guarded, deflected, refused or off-topic opening never opens the topic")
    void openings() {
        assertTrue(TopicExhaustion.openingCounts(beat("life.open", "life", "report", "invites_followup"), "life"));
        assertTrue(TopicExhaustion.openingCounts(null, "life"), "a route with no beat is a real tree");
        assertFalse(TopicExhaustion.openingCounts(beat("deflect.life", "life", "deflect", "guarded"), "life"));
        assertFalse(TopicExhaustion.openingCounts(beat("life.qualified", "life", "qualify", "guarded"), "life"));
        assertFalse(TopicExhaustion.openingCounts(beat("life.rebuked", "life", "refuse", "closes_subject"), "life"));
        assertFalse(TopicExhaustion.openingCounts(beat("life.again", "deep", "accept", "permits_followup"), "life"),
                "the 'we talked already' route opens 'deep', not the topic asked for");
    }

    @Test
    @DisplayName("the worked cases: what concludes a topic and what does not")
    void concluded() {
        // Leave on the opening page: the end op ran, but nothing was answered.
        assertFalse(TopicExhaustion.concluded(false, 0, true, "conversations.cat.personal", LIFE));
        // Reply, reaction, then leave: the end op ran after one answered reply.
        assertTrue(TopicExhaustion.concluded(false, 1, true, "conversations.cat.personal", LIFE));
        // Reply on a single-page legacy tree that returns to the category with no end op.
        assertTrue(TopicExhaustion.concluded(true, 1, true, "conversations.cat.personal", LIFE));
        // Reply answered, but the player is still inside (followup page on offer): not yet.
        assertFalse(TopicExhaustion.concluded(true, 1, true, "conversations.scene.life.followup", LIFE));
        // Screen closed after the reaction: the topic stays open and the offer never left the tree.
        assertFalse(TopicExhaustion.concluded(true, 1, true, "conversations.scene.life.followup", LIFE));
        // Guarded opening, however many replies.
        assertFalse(TopicExhaustion.concluded(false, 3, false, "conversations.cat.personal", LIFE));
    }
}
