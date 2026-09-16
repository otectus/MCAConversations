package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentProblem;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ContentSection;
import dev.otectus.mcaconversations.conversation.ContentSeverity;
import dev.otectus.mcaconversations.conversation.ContentSources;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The structured diagnostics specified in {@code docs/RELOAD-TRANSACTION-BOUNDARY.md} §6.
 *
 * <p>Two fields were named there as the ones the old architecture could not supply everywhere — the
 * pack id and the JSON line/column — and both were specified as explicitly unknown rather than
 * optional, so a reader can tell "not available on this path" from "no problem here". That is what is
 * pinned here: the pack id is now real, a syntax error carries Gson's position, and a semantic
 * failure records unknown rather than inventing one.
 */
class ContentReloadDiagnosticsTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    @Test
    @DisplayName("a syntax error carries the pack, the resource and Gson's reported position")
    void aSyntaxErrorIsFullyAttributed() {
        ContentSources.Staged staged = ContentSources.read(new ReloadFixture()
                        .with("conversation_catalog", "mcaconversations", "broken",
                                "{\n  \"topics\": {\n    \"day\": {,\n  }\n}", "somepack")
                        .manager(),
                ContentSection.CONVERSATION_CATALOG);

        ContentProblem problem = staged.problems().get(0);
        assertEquals("resource_malformed", problem.reason());
        assertEquals("somepack", problem.origin().pack(),
                "the pack id was unobtainable from the inherited prepare; reading the manager supplies it");
        assertEquals("conversation_catalog", problem.directory());
        assertNotEquals(ContentProblem.UNKNOWN_POSITION, problem.line(),
                "Gson reports a line for a syntax error, so it is recorded rather than discarded");
        assertTrue(problem.format().contains("line="));
    }

    @Test
    @DisplayName("a semantic failure records an unknown position rather than fabricating one")
    void aSemanticFailureRecordsUnknownPosition() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", """
                        {"topics": {"day": {"depth": "quick", "ages": ["adult"]}}}""")
                .run();

        ContentProblem problem = outcome.attempt().problems().stream()
                .filter(p -> p.severity() == ContentSeverity.REFUSED)
                .findFirst()
                .orElseThrow();
        assertEquals(ContentProblem.UNKNOWN_POSITION, problem.line());
        assertEquals(ContentProblem.UNKNOWN_POSITION, problem.column());
        assertTrue(problem.format().contains("line=<unknown>"));
        assertTrue(problem.format().contains("column=<unknown>"));
        assertEquals("day", problem.entry(), "the entry being parsed is named, as it always was");
    }

    @Test
    @DisplayName("every problem is stamped with the attempt and the generation the reload was aiming at")
    void problemsCarryTheAttemptAndTargetGeneration() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t",
                        "{\"topics\": {\"day\": 7}}")
                .run();

        assertTrue(outcome.attempt().problems().stream()
                        .allMatch(p -> p.attempt() == outcome.attempt().id()
                                && p.generation() == outcome.attempt().targetGeneration()),
                "a diagnostic names the generation the reload was attempting, not the one in force");
    }

    @Test
    void aCrossSectionFailureStillNamesTheWinningResourceAndPack() throws Exception {
        var outcome = new ReloadFixture().with("conversation_catalog", "example", "broken", """
                {"topics":{"day":{"entry":{"question":"conversations.missing","answer":"day"},
                "depth":"quick","return_question":"conversations.missing","ages":["adult"],
                "required_stance_families":["empathy","exit"]}}}""", "custom-pack").run();
        ContentProblem problem = outcome.attempt().problems().stream()
                .filter(p -> p.reason().equals("topic_entry_question_missing")).findFirst().orElseThrow();
        assertEquals("example:broken", problem.origin().resource().toString());
        assertEquals("custom-pack", problem.origin().pack());
        assertEquals("/topics/day/entry/question", problem.path());
    }

    @Test
    @DisplayName("a semantic failure points at the offending member with a JSON pointer")
    void semanticFailuresUseJsonPointers() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t",
                        "{\"topics\": {\"day\": 7}}")
                .run();

        assertTrue(outcome.attempt().problems().stream()
                        .anyMatch(p -> p.path().equals("/topics/day")),
                "the pointer is the one thing SafeParse.orNull never threaded through the parse");
    }

    @Test
    @DisplayName("one primary failure is not repeated as every reference derived from it")
    void duplicateDiagnosticsAreCoalesced() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", """
                        {"topics": {
                          "day": {"entry": {"question": "conversations.missing", "answer": "day"},
                           "depth": "quick", "return_question": "conversations.missing",
                           "ages": ["adult"], "required_stance_families": ["empathy", "exit"]}}}""")
                .with("dialogues", "mcaconversations", "conversations.elsewhere",
                        "{\"answers\": [{\"name\": \"a\", \"results\": [{\"actions\": {\"next\": \"main\"}}]}]}")
                .run();

        long distinct = outcome.attempt().problems().stream()
                .map(ContentProblem::coalescingKey)
                .distinct()
                .count();
        assertEquals(distinct, outcome.attempt().problems().size(),
                "the primary resource error must not be buried under a flood of derived ones");
    }
}
