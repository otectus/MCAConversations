package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentProblem;
import dev.otectus.mcaconversations.conversation.DialogueResourceIndex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The model of MCA's dialogue directory this mod builds during its own preparation pass.
 *
 * <p>It exists because half of this mod's shipped content is applied by another mod's listener. The
 * rules modelled here are MCA's, read from {@code Dialogues.loadDialogue}, {@code Question.fromJson}
 * and {@code Answer.fromJson}: the key is the file's basename with namespace and directories
 * discarded, a second file for one key appends its answers, and the merged list is sorted by
 * priority. Nothing here calls {@code getQuestion}, so the index cannot be contaminated by the very
 * instance it is validating against.
 */
class DialogueResourceIndexTest {

    private static final String DAY = """
            {"answers": [{"name": "day", "priority": 5,
              "results": [{"actions": {"next": "conversations.cat.chitchat"}}]}]}""";

    private static DialogueResourceIndex index(ReloadFixture fixture) {
        return DialogueResourceIndex.read(fixture.manager());
    }

    @Test
    @DisplayName("the namespace and every directory prefix are discarded, exactly as MCA discards them")
    void namespaceAndDirectoriesAreStripped() {
        DialogueResourceIndex index = index(new ReloadFixture()
                .with("dialogues", "mcaconversations", "nested/deeper/conversations.probe", DAY));

        assertTrue(index.declares("conversations.probe"),
                "a nested file is keyed by its basename, which is why namespaces can collide at all");
        assertFalse(index.declares("nested/deeper/conversations.probe"));
    }

    @Test
    @DisplayName("two namespaces contributing one basename merge, and the ambiguity is recorded")
    void nestedBasenameCollisionsMerge() {
        DialogueResourceIndex index = index(new ReloadFixture()
                .with("dialogues", "mcaconversations", "conversations.probe", DAY)
                .with("dialogues", "othermod", "conversations.probe", """
                        {"answers": [{"name": "night", "priority": 1,
                          "results": [{"actions": {"next": "conversations.cat.chitchat"}}]}]}"""));

        DialogueResourceIndex.IndexedQuestion question = index.questions().get("conversations.probe");
        assertEquals(2, question.sources().size(), "both resources contribute to the one question");
        assertEquals(List.of("night", "day"),
                question.answers().stream().map(DialogueResourceIndex.IndexedAnswer::name).toList(),
                "answers are ordered by priority, as MCA sorts them after the merge");
        assertTrue(index.problems().stream()
                        .anyMatch(p -> p.reason().equals("owned_question_merge_order_undefined")),
                "MCA merges on a HashMap iteration order this mod does not control; that is recorded"
                        + " rather than papered over with an invented order");
    }

    @Test
    @DisplayName("a baseConditions block longer than an answer's results is caught here, not inside MCA's parse")
    void baseConditionsAreFoldedPositionally() {
        DialogueResourceIndex index = index(new ReloadFixture()
                .with("dialogues", "mcaconversations", "conversations.probe", """
                        {"answers": [{"name": "day", "results": [{"actions": {"quit": true}}]}],
                         "baseConditions": [[{"tag": "a"}], [{"tag": "b"}]]}"""));

        assertTrue(index.fatal(), "MCA would index past the end of the result list and abort the reload");
        assertTrue(index.problems().stream()
                .anyMatch(p -> p.reason().equals("owned_dialogue_entry_malformed")));
    }

    @Test
    @DisplayName("an owned file without an answers array is a refusal; MCA would silently not load it")
    void anOwnedFileWithoutAnswersIsRefused() {
        DialogueResourceIndex index = index(new ReloadFixture()
                .with("dialogues", "mcaconversations", "conversations.probe", "{\"auto\": true}"));

        assertTrue(index.problems().stream()
                .anyMatch(p -> p.reason().equals("owned_dialogue_not_a_dialogue")));
    }

    @Test
    @DisplayName("an unrelated malformed external resource does not reject owned content")
    void anExternalMalformedResourceIsNotOurs() {
        DialogueResourceIndex index = index(new ReloadFixture()
                .with("dialogues", "mcaconversations", "conversations.probe",
                        "{\"answers\": [{\"name\": \"day\", \"results\": [{\"actions\": {\"next\": \"main\"}}]}]}")
                .with("dialogues", "mca", "someone.elses.question", "{ not json at all"));

        assertFalse(index.fatal(), "another mod's broken dialogue is another mod's problem");
        assertTrue(index.declares("conversations.probe"));
    }

    @Test
    @DisplayName("an owned route out of the owned names is marked as outside the retention scope")
    void outboundExternalRoutesAreMarked() {
        DialogueResourceIndex index = index(new ReloadFixture()
                .with("dialogues", "mcaconversations", "conversations.probe", """
                        {"answers": [{"name": "back", "results": [{"actions": {"next": "main"}}]}]}"""));

        assertEquals(java.util.Set.of("main"), index.externalExits());
        assertFalse(index.fatal(), "leaving for MCA's own tree is legal, it is simply not guaranteed");
    }

    @Test
    @DisplayName("an owned route to an owned question nothing declares is a refusal")
    void ownedNextDanglingIsRefused() {
        DialogueResourceIndex index = index(new ReloadFixture()
                .with("dialogues", "mcaconversations", "conversations.probe", DAY));

        List<ContentProblem> problems = index.problems();
        assertTrue(problems.stream().anyMatch(p -> p.reason().equals("owned_next_dangling")),
                "the answer routes to conversations.cat.chitchat, which no resource here declares");
    }
}
