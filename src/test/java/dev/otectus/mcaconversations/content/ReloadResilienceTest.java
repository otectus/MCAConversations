package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentProblem;
import dev.otectus.mcaconversations.conversation.ContentReloadAttempt;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;
import dev.otectus.mcaconversations.conversation.DepthClass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
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
 * because somebody's third-party pack had a typo in it.
 *
 * <p>What changed with the coordinator is the <em>scope</em> of that retention. It used to be one
 * loader keeping one catalog; it is the whole bundle now, so a colliding beat file does not leave the
 * topics, scenes and narrative templates of the same broken pack in force alongside the old beats.
 */
class ReloadResilienceTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

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

    private static final String TOPIC = """
            {
              "entry": {"question": "conversations.cat.chitchat", "answer": "day"},
              "depth": "%s",
              "return_question": "conversations.cat.chitchat",
              "ages": ["adult"],
              "required_stance_families": ["empathy", "exit"]
            }""";

    private static String beatFile(String... ids) {
        StringBuilder body = new StringBuilder("{\"beats\": {");
        for (int i = 0; i < ids.length; i++) {
            body.append(i == 0 ? "" : ",").append('"').append(ids[i]).append("\": ").append(BEAT);
        }
        return body.append("}}").toString();
    }

    private static String topicFile(String depth) {
        return "{\"topics\": {\"day\": " + TOPIC.formatted(depth) + "}}";
    }

    private static ReloadFixture.Outcome reload(ReloadFixture fixture) throws Exception {
        return fixture.reload(fixture.manager(), Map.of(), Set.of());
    }

    @Test
    @DisplayName("a pack that contracts one route twice leaves the whole previous bundle standing")
    void collidingPackKeepsThePreviousCatalog() throws Exception {
        reload(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "good", beatFile("day.probe.one"))
                .with("conversation_catalog", "mcaconversations", "topics", topicFile("quick")));
        ConversationContentBundle good = ContentReloadCoordinator.committed();
        assertEquals(1, good.beats().size(), "the good pack should have loaded");
        assertEquals(1, good.topics().size());

        // Two ids, one route: BeatCatalog.build refuses. The topics of the same pack are perfectly
        // valid, and under the old per-listener verdicts they would have been published anyway.
        ReloadFixture.Outcome broken = reload(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "broken",
                        beatFile("day.probe.two", "day.probe.three"))
                .with("conversation_catalog", "mcaconversations", "topics", topicFile("deep")));

        assertEquals(ContentReloadAttempt.Verdict.REJECTED, broken.attempt().verdict());
        assertSame(good, ContentReloadCoordinator.committed(),
                "a colliding reload must leave the previous bundle in place, not empty or half-replace it");
        assertSame(good.beats(), ContentReloadCoordinator.committed().beats());
        assertSame(good.topics(), ContentReloadCoordinator.committed().topics());
        assertEquals(DepthClass.QUICK,
                ContentReloadCoordinator.committed().topics().topic("day").orElseThrow().depth(),
                "the valid half of a refused pack is not published either");
        assertEquals(good.generation(), ContentReloadCoordinator.committed().generation(),
                "and the generation does not move, because nothing was published");
    }

    @Test
    @DisplayName("an empty pack is a choice, not a failure")
    void anEmptyPackIsHonoured() throws Exception {
        reload(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "good", beatFile("day.probe.one"))
                .with("conversation_catalog", "mcaconversations", "topics", topicFile("quick")));
        assertEquals(1, ContentReloadCoordinator.committed().beats().size());

        ReloadFixture.Outcome emptied = reload(new ReloadFixture());

        assertTrue(emptied.committedNow(),
                "a pack that removes every beat has removed every beat — that is not a failure"
                        + " and must not be treated as one");
        assertEquals(0, ContentReloadCoordinator.committed().beats().size());
        assertEquals(0, ContentReloadCoordinator.committed().topics().size(),
                "an empty section with nothing referencing it is valid, not missing");
    }

    @Test
    @DisplayName("a wrong-shaped section is not the same as an absent one")
    void aWrongShapedSectionIsRefused() throws Exception {
        reload(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "good", beatFile("day.probe.one"))
                .with("conversation_catalog", "mcaconversations", "topics", topicFile("quick")));
        ConversationContentBundle good = ContentReloadCoordinator.committed();

        ReloadFixture.Outcome broken = reload(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "bad", "{\"beats\": [\"not an object\"]}"));

        assertEquals(ContentReloadAttempt.Verdict.REJECTED, broken.attempt().verdict());
        assertSame(good, ContentReloadCoordinator.committed());
        assertTrue(broken.attempt().problems().stream()
                        .anyMatch(p -> p.reason().equals("section_wrong_shape")),
                "the diagnostic says the section is the wrong shape, not that it was empty");
    }

    @Test
    @DisplayName("one topic id in two files resolves to the sorted-last file and names both origins")
    void collidingTopicIdResolvesDeterministically() throws Exception {
        ReloadFixture.Outcome ascending = reload(new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "aaa", topicFile("quick"))
                .with("conversation_catalog", "mcaconversations", "zzz", topicFile("deep")));

        assertTrue(ascending.committedNow());
        assertEquals(DepthClass.DEEP,
                ContentReloadCoordinator.committed().topics().topic("day").orElseThrow().depth(),
                "the sorted-last file must win, not the first one seen");
        assertEquals(1, ContentReloadCoordinator.committed().topics().size(),
                "a collision merges to one topic, it does not duplicate it");

        ContentProblem collision = ascending.attempt().problems().stream()
                .filter(p -> p.reason().equals("topic_declared_twice"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("the collision must be reported, not merely resolved"));
        assertEquals(2, collision.contributors().size(),
                "both contributing resources are named, not only the winner");
        assertEquals("zzz.json", fileName(collision),
                "and the winner is the one the record is attributed to");

        // The same two files in the other input order: the pack stack's iteration order must not be
        // what decides which one a player ends up talking to.
        ConversationContentBundle first = ContentReloadCoordinator.committed();
        reload(new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "zzz", topicFile("deep"))
                .with("conversation_catalog", "mcaconversations", "aaa", topicFile("quick")));
        assertNotSame(first, ContentReloadCoordinator.committed(), "the second reload did publish");
        assertEquals(DepthClass.DEEP,
                ContentReloadCoordinator.committed().topics().topic("day").orElseThrow().depth(),
                "iteration order of the incoming resources must not decide the winner");
    }

    private static String fileName(ContentProblem problem) {
        String path = problem.origin().path();
        return path.substring(path.lastIndexOf('/') + 1);
    }
}
