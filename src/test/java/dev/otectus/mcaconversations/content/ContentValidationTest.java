package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentProblem;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Cross-validation: the checks no single loader could make, and the shipped corpus passing them.
 *
 * <p>The rules are deliberately feature-aware. A profile for a mod nobody installed stays valid,
 * because its registry id simply never matches a villager and that is a supported way to ship
 * optional content. What is refused is a reference that can never resolve for anybody — an owned
 * question name no dialogue resource declares, a topic no catalog entry declares, a fallback scene
 * that does not exist or comes back round to itself.
 */
class ContentValidationTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    private static final String TOPIC = """
            {"topics": {"day": {"entry": {"question": "conversations.cat.chitchat", "answer": "day"},
             "depth": "quick", "return_question": "conversations.cat.chitchat",
             "ages": ["adult"], "required_stance_families": ["empathy", "exit"]}}}""";

    private static final String DIALOGUE = """
            {"answers": [{"name": "day", "results": [{"actions": {"next": "conversations.cat.chitchat"}}]}]}""";

    @Test
    @DisplayName("the shipped corpus validates with no authored-content change")
    void theShippedCorpusValidates() throws Exception {
        ReloadFixture.Outcome outcome = ReloadFixture.shipped().run();

        List<ContentProblem> fatal = outcome.attempt().problems().stream()
                .filter(problem -> problem.severity().fatal())
                .toList();
        assertTrue(fatal.isEmpty(), () -> "the corpus this mod ships must load as one transaction:\n"
                + fatal.stream().map(ContentProblem::format).limit(20).toList());
        assertTrue(outcome.committedNow(), "and therefore publish");
    }

    @Test
    @DisplayName("a topic entering a question no dialogue resource declares is refused, naming the topic")
    void aTopicIntoAMissingQuestionIsRefused() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", TOPIC)
                .with("dialogues", "mcaconversations", "conversations.somewhere.else",
                        "{\"answers\": [{\"name\": \"out\", \"results\": [{\"actions\": {\"next\": \"main\"}}]}]}")
                .run();

        assertRefusedFor(outcome, "topic_entry_question_missing");
    }

    @Test
    void removingEveryDialogueDoesNotBypassReferenceValidation() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", TOPIC).run();
        assertRefusedFor(outcome, "topic_entry_question_missing");
    }

    @Test
    @DisplayName("a topic entering an answer its question does not declare is refused")
    void aTopicIntoAMissingAnswerIsRefused() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", TOPIC)
                .with("dialogues", "mcaconversations", "conversations.cat.chitchat",
                        """
                        {"answers": [{"name": "night", "results": [{"actions": {"quit": true}}]}]}""")
                .run();

        assertRefusedFor(outcome, "topic_entry_answer_missing");
    }

    @Test
    @DisplayName("a scene whose fallback chain comes back to itself is refused")
    void aFallbackCycleIsRefused() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", TOPIC)
                .with("dialogues", "mcaconversations", "conversations.cat.chitchat", DIALOGUE)
                .with("conversation_scenes", "mcaconversations", "s", """
                        {"scenes": {
                          "a.scene": {"purpose": "topic:day",
                           "fallback": "b.scene",
                           "route": {"question": "conversations.cat.chitchat",
                                     "opening_beat": "day.probe"}},
                          "b.scene": {"purpose": "topic:day",
                           "fallback": "a.scene",
                           "route": {"question": "conversations.cat.chitchat",
                                     "opening_beat": "day.probe"}}}}""")
                .run();

        assertRefusedFor(outcome, "scene_fallback_cycle");
    }

    @Test
    @DisplayName("a profession profile for an absent mod is valid; that is how optional content ships")
    void anAbsentModsProfileIsStillValid() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", TOPIC)
                .with("dialogues", "mcaconversations", "conversations.cat.chitchat", DIALOGUE)
                .with("profession_profiles", "mcaconversations", "p",
                        "{\"profiles\": {\"nosuchmod:nosuchjob\": {\"archetype\": \"craft\", \"subjects\": [\"day.probe\"]}}}")
                .run();

        assertTrue(outcome.committedNow(), () -> String.valueOf(outcome.attempt().problems()));
    }

    @Test
    @DisplayName("an owned route out to an external MCA question is a recorded boundary crossing, not a failure")
    void anExternalExitIsRecordedRatherThanRefused() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", TOPIC)
                .with("dialogues", "mcaconversations", "conversations.cat.chitchat",
                        """
                        {"answers": [{"name": "day", "results": [{"actions": {"next": "main"}}]}]}""")
                .run();

        assertTrue(outcome.committedNow(), "the exit is legal; MCA owns 'main' and always did");
        assertTrue(ContentReloadCoordinator.committed().dialogues().externalExits().contains("main"),
                "but it is recorded, because retention does not cover what happens on the other side");
    }

    private static void assertRefusedFor(ReloadFixture.Outcome outcome, String reason) {
        assertTrue(outcome.attempt().problems().stream()
                        .anyMatch(problem -> problem.reason().equals(reason)),
                () -> "expected a " + reason + " refusal, got "
                        + outcome.attempt().problems().stream().map(ContentProblem::format).toList());
        assertTrue(!outcome.committedNow(), "and nothing publishes");
    }
}
