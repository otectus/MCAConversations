package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentProblem;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ContentSection;
import dev.otectus.mcaconversations.conversation.ContentSeverity;
import dev.otectus.mcaconversations.conversation.ContentSources;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;
import dev.otectus.mcaconversations.conversation.DepthClass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pack overrides, and the difference between one resource hiding another and two resources merging.
 *
 * <p>These are two things a datapack author does on purpose and one of them used to be impossible to
 * tell apart from a mistake. A higher pack replacing {@code the same resource path} hides the lower
 * file completely — the lower file is never opened, so whatever is wrong with it cannot reject
 * anything. Two <em>different</em> resources declaring one id is a merge, the sorted-last one wins,
 * and both origins go in the record.
 */
class ContentResourceOverrideTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    private static String topicFile(String depth) {
        return """
                {"topics": {"day": {"entry": {"question": "conversations.cat.chitchat", "answer": "day"},
                 "depth": "%s", "return_question": "conversations.cat.chitchat",
                 "ages": ["adult"], "required_stance_families": ["empathy", "exit"]}}}""".formatted(depth);
    }

    @Test
    @DisplayName("a higher pack replacing the same resource wins, and the hidden file is never parsed")
    void anIdenticalResourceOverrideWins() throws Exception {
        ReloadFixture fixture = new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "topics", topicFile("quick"), "base")
                .with("conversation_catalog", "mcaconversations", "topics", topicFile("deep"), "overlay");

        ReloadFixture.Outcome outcome = fixture.run();

        assertTrue(outcome.committedNow());
        assertEquals(DepthClass.DEEP,
                ContentReloadCoordinator.committed().topics().topic("day").orElseThrow().depth(),
                "the top of the pack stack is the effective resource");
        ContentProblem override = outcome.attempt().problems().stream()
                .filter(p -> p.reason().equals("resource_overridden"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("an override is worth attributing"));
        assertEquals(ContentSeverity.INFO, override.severity(),
                "a normal override is informational; it is not a failure of any kind");
        assertEquals("overlay", override.origin().pack());
    }

    @Test
    @DisplayName("a malformed file hidden underneath a valid override cannot reject anything")
    void aHiddenMalformedFileIsNeverOpened() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "topics", "{ this is not json", "base")
                .with("conversation_catalog", "mcaconversations", "topics", topicFile("deep"), "overlay")
                .run();

        assertTrue(outcome.committedNow(),
                "the broken file is not the effective resource, so it is not this reload's problem");
        assertTrue(outcome.attempt().problems().stream()
                        .noneMatch(p -> p.reason().equals("resource_malformed")));
    }

    @Test
    @DisplayName("reversing the input order does not change which distinct resource wins")
    void distinctResourceWinnersAreOrderIndependent() {
        ContentSources.Staged ascending = ContentSources.read(new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "aaa", topicFile("quick"))
                .with("conversation_catalog", "mcaconversations", "zzz", topicFile("deep"))
                .manager(), ContentSection.CONVERSATION_CATALOG);
        ContentSources.Staged descending = ContentSources.read(new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "zzz", topicFile("deep"))
                .with("conversation_catalog", "mcaconversations", "aaa", topicFile("quick"))
                .manager(), ContentSection.CONVERSATION_CATALOG);

        assertEquals(ascending.documents().stream().map(d -> d.origin().resource().toString()).toList(),
                descending.documents().stream().map(d -> d.origin().resource().toString()).toList(),
                "staging orders by resource id, so four sections that used to iterate the incoming map"
                        + " directly now mean the same thing as the seven that sorted it");
    }

    @Test
    @DisplayName("a fatal route collision keeps both contributing origins in the record")
    void aFatalCollisionNamesBothOrigins() throws Exception {
        String beat = """
                {"topic": "day", "say": "conversations.reload.probe",
                 "response_question": "conversations.cat.chitchat", "npc_act": "report",
                 "subject": "day.probe", "polarity": "neutral", "openness": "permits_followup",
                 "allowed_stances": ["exit"]}""";

        ReloadFixture.Outcome outcome = new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "t", topicFile("quick"))
                .with("conversation_beats", "mcaconversations", "a",
                        "{\"beats\": {\"day.probe.one\": " + beat + "}}")
                .with("conversation_beats", "othermod", "b",
                        "{\"beats\": {\"day.probe.two\": " + beat + "}}")
                .run();

        ContentProblem collision = outcome.attempt().problems().stream()
                .filter(p -> p.severity() == ContentSeverity.REFUSED)
                .findFirst()
                .orElseThrow(() -> new AssertionError("two packs claiming one route is the collision"));
        assertEquals("section_build_refused", collision.reason(),
                "the catalog builder refuses the route, and the refusal is what the attempt records");
        assertTrue(collision.message().contains("day.probe.one") && collision.message().contains("day.probe.two"),
                "both contracts are named, so the pack author knows which two to reconcile");
        assertTrue(!outcome.committedNow(), "and the whole attempt is rejected, not only the beats");
    }
}
