package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentReloadAttempt;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ContentSection;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;
import dev.otectus.mcaconversations.conversation.DialogueResourceIndex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The regression baseline for the reload transaction boundary described in
 * {@code docs/RELOAD-TRANSACTION-BOUNDARY.md}.
 *
 * <p>This test used to assert the <b>absence</b> of a transaction: one reload producing several
 * unrelated verdicts, a generation advancing over the top of them, and half the mod's content being
 * applied by another mod's listener with no validation of ours. That was the line a coordinator had
 * to be measured against; it now measures the coordinator against it.
 *
 * <p>{@link ReloadResilienceTest} covers the retention rules per section. This one covers what
 * happens when the sections disagree inside one reload, and what the mod does about the half of its
 * content that MCA parses.
 */
class ReloadTransactionBaselineTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    /** A valid topic, in the shape {@code TopicEntry.fromJson} accepts. */
    private static final String VALID_TOPIC = """
            {
              "entry": {"question": "conversations.cat.chitchat", "answer": "day"},
              "depth": "%s",
              "return_question": "conversations.cat.chitchat",
              "ages": ["adult"],
              "required_stance_families": ["empathy", "exit"]
            }""";

    /** The invalid entry of the fixture pack: no {@code entry}, so the topic cannot be built. */
    private static final String INVALID_TOPIC = """
            {
              "depth": "quick",
              "ages": ["adult"]
            }""";

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

    /** A dialogue file in MCA's format and MCA's directory, routing within this mod's own names. */
    private static final String COHERENT_DIALOGUE = """
            {
              "answers": [
                {"name": "day", "results": [{"actions": {"next": "conversations.cat.chitchat"}}]}
              ]
            }""";

    /**
     * The changed dialogue action of the fixture pack: a pack author has repointed one answer's
     * {@code next} at a question no resource declares.
     */
    private static final String CHANGED_DIALOGUE = """
            {
              "answers": [
                {"name": "day", "results": [{"actions": {"next": "conversations.probe.changed"}}]}
              ]
            }""";

    // ---------------------------------------------------------------------------------------------
    // One reload, one verdict
    // ---------------------------------------------------------------------------------------------

    @Test
    @DisplayName("one pack, one reload: a refused section keeps the whole previous bundle, not only its own")
    void oneReloadProducesOneVerdict() throws Exception {
        drive(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "fixture", beats("day.probe.one"))
                .with("conversation_catalog", "mcaconversations", "fixture", topics("quick", false)));
        ConversationContentBundle good = ContentReloadCoordinator.committed();
        assertEquals(1, good.beats().size());
        assertEquals(1, good.topics().size());

        // The fixture pack, applied the way a reload applies it — except that there is now one
        // decision rather than eleven. Two beat ids claim one route, and one topic of the catalog is
        // unbuildable; the valid topic of the same file used to be published regardless.
        ReloadFixture.Outcome outcome = drive(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "fixture",
                        beats("day.probe.two", "day.probe.three"))
                .with("conversation_catalog", "mcaconversations", "fixture", topics("deep", true)));

        assertEquals(ContentReloadAttempt.Verdict.REJECTED, outcome.attempt().verdict());
        assertSame(good, ContentReloadCoordinator.committed(),
                "the whole prior bundle stays in force, not one lucky section of it");
        assertSame(good.beats(), ContentReloadCoordinator.committed().beats());
        assertSame(good.topics(), ContentReloadCoordinator.committed().topics());
        assertEquals("quick",
                ContentReloadCoordinator.committed().topics().topic("day").orElseThrow()
                        .depth().name().toLowerCase(java.util.Locale.ROOT),
                "the old entry is the one in force — old and new are distinguishable, so this is retention"
                        + " rather than a coincidence of sizes");
        assertTrue(ContentReloadCoordinator.committed().topics().topic("day.broken").isEmpty());
    }

    @Test
    @DisplayName("the content generation gates on the verdict: a rejected reload does not advance it")
    void generationDoesNotAdvanceOverARejectedReload() throws Exception {
        drive(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "fixture", beats("day.probe.one"))
                .with("conversation_catalog", "mcaconversations", "fixture", topics("quick", false)));
        long before = ContentReloadCoordinator.committed().generation();

        ReloadFixture.Outcome rejected = drive(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "fixture",
                        beats("day.probe.two", "day.probe.three"))
                .with("conversation_catalog", "mcaconversations", "fixture", topics("deep", true)));

        assertEquals(before, ContentReloadCoordinator.committed().generation(),
                "a generation is now evidence that the content behind it is coherent, not a marker"
                        + " that a reload happened");
        assertEquals(before + 1, rejected.attempt().targetGeneration(),
                "the attempt was aiming at the next generation; it simply never reached it");

        ReloadFixture.Outcome accepted = drive(new ReloadFixture().withChitchat()
                .with("conversation_beats", "mcaconversations", "fixture", beats("day.probe.four"))
                .with("conversation_catalog", "mcaconversations", "fixture", topics("deep", false)));

        assertTrue(accepted.committedNow());
        assertEquals(before + 1, ContentReloadCoordinator.committed().generation(),
                "and the next good reload takes the generation the rejected one was aiming at");
    }

    // ---------------------------------------------------------------------------------------------
    // The half of the pack MCA parses
    // ---------------------------------------------------------------------------------------------

    @Test
    @DisplayName("all eleven owned sections are staged by one coordinator, which also reads MCA's directory as metadata")
    void everySectionParticipatesAndTheDialogueDirectoryIsReadAsMetadata() {
        List<String> directories = Arrays.stream(ContentSection.values())
                .map(ContentSection::directory)
                .toList();

        assertEquals(List.of("chat_intents", "conversation_catalog", "conversation_beats",
                        "profession_profiles", "interiority", "identity_tokens", "conversation_scenes",
                        "village_culture", "episode_templates", "thread_templates", "commitment_templates"),
                directories,
                "the owned catalog inventory in docs/RELOAD-TRANSACTION-BOUNDARY.md is this list");
        assertEquals(directories.size(), directories.stream().distinct().count(),
                "two sections reading one directory would each see the other's files");
        assertFalse(directories.contains(DialogueResourceIndex.DIRECTORY),
                "the dialogue directory is still MCA's to apply; owning it would merge two engines' data");
        assertEquals("dialogues", DialogueResourceIndex.DIRECTORY,
                "but it is read, as metadata, so a reference into it can be validated before MCA applies");
    }

    @Test
    @DisplayName("a changed dialogue action is validated here and, when it dangles, keeps the old executable questions")
    void theChangedDialogueIsValidatedAndRetained() throws Exception {
        ReloadFixture.Outcome good = drive(new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "fixture", topics("quick", false))
                .with("dialogues", "mcaconversations", "conversations.cat.chitchat", COHERENT_DIALOGUE));

        assertTrue(good.committedNow(), "a coherent pack publishes");
        Object retained = ContentReloadCoordinator.committed().ownedQuestion("conversations.cat.chitchat");
        assertTrue(retained != null, "and the bundle holds the executable question, not just its name");

        // The same pack with the answer repointed at a question nothing declares. MCA would have
        // applied that action regardless of what any loader of ours decided; it is now this mod's to
        // refuse, because the reference is into a name this mod owns.
        ReloadFixture.Outcome changed = drive(new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "fixture", topics("deep", false))
                .with("dialogues", "mcaconversations", "conversations.cat.chitchat", CHANGED_DIALOGUE));

        assertEquals(ContentReloadAttempt.Verdict.REJECTED, changed.attempt().verdict());
        assertTrue(changed.attempt().problems().stream()
                        .anyMatch(p -> p.reason().equals("owned_next_dangling")),
                "the diagnostic names the dangling route, not merely that something was wrong");
        assertSame(retained, ContentReloadCoordinator.committed().ownedQuestion("conversations.cat.chitchat"),
                "the executable question an unanswered offer would run is the same object as before");
        assertSame(retained, changed.liveQuestions().get("conversations.cat.chitchat"),
                "and MCA's own map was put back to it, so a click between the applies runs the old action");
        assertEquals(List.of("conversations.cat.chitchat"), changed.ownedKeysLeftInMca(),
                "no newly parsed owned key leaked into execution");
    }

    // ---------------------------------------------------------------------------------------------
    // Plumbing
    // ---------------------------------------------------------------------------------------------

    private static String topics(String depth, boolean withBroken) {
        StringBuilder body = new StringBuilder("{\"topics\": {\"day\": ")
                .append(VALID_TOPIC.formatted(depth));
        if (withBroken) {
            body.append(", \"day.broken\": ").append(INVALID_TOPIC);
        }
        return body.append("}}").toString();
    }

    private static String beats(String... ids) {
        StringBuilder body = new StringBuilder("{\"beats\": {");
        for (int i = 0; i < ids.length; i++) {
            body.append(i == 0 ? "" : ",").append('"').append(ids[i]).append("\": ").append(BEAT);
        }
        return body.append("}}").toString();
    }

    private static ReloadFixture.Outcome drive(ReloadFixture fixture) throws Exception {
        return fixture.reload(fixture.manager(), Map.of(), Set.of());
    }
}
