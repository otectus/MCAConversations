package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentReloadAttempt;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * One publication, at one moment, or none.
 *
 * <p>Publication is the only thing that changes what a player is talking to, so the cases worth
 * pinning are the ones where it must <em>not</em> happen: a section accepted on its own, a late
 * failure, an executable question that did not materialise, a duplicate or wrong-instance callback,
 * and an MCA apply that never finished.
 */
class ContentReloadCoordinatorTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
        ContentReloadCoordinator.clearMcaExpectedForTesting();
    }

    private static final String TOPIC = """
            {"topics": {"day": {"entry": {"question": "conversations.cat.chitchat", "answer": "day"},
             "depth": "%s", "return_question": "conversations.cat.chitchat",
             "ages": ["adult"], "required_stance_families": ["empathy", "exit"]}}}""";

    private static final String DIALOGUE = """
            {"answers": [{"name": "day", "results": [{"actions": {"next": "conversations.cat.chitchat"}}]}]}""";

    private static ReloadFixture coherent(String depth) {
        return new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", TOPIC.formatted(depth))
                .with("dialogues", "mcaconversations", "conversations.cat.chitchat", DIALOGUE);
    }

    @Test
    @DisplayName("a complete success publishes exactly one bundle and one generation")
    void aCompleteSuccessPublishesOnce() throws Exception {
        ContentReloadCoordinator.setCommittedForTesting(ConversationContentBundle.UNAVAILABLE);

        ReloadFixture.Outcome outcome = coherent("quick").run();

        assertTrue(outcome.committedNow());
        assertEquals(1L, ContentReloadCoordinator.committed().generation());
        assertTrue(ContentReloadCoordinator.contentAvailable());
        assertEquals(1, ContentReloadCoordinator.committed().retainedQuestionCount(),
                "and the bundle is holding the executable question, not merely its name");
        assertSame(ContentReloadCoordinator.committed(), ContentReloadCoordinator.committed(),
                "one reference assignment: there is no half-published state to observe");
    }

    @Test
    @DisplayName("a section accepting on its own publishes nothing")
    void catalogAcceptanceAloneIsNotPublication() throws Exception {
        ReloadFixture.Outcome good = coherent("quick").run();
        ConversationContentBundle published = ContentReloadCoordinator.committed();
        assertTrue(good.committedNow());

        // The catalog of this pack is perfectly valid. The scenes are not.
        ReloadFixture.Outcome outcome = coherent("deep")
                .with("conversation_scenes", "mcaconversations", "s", "{\"scenes\": {\"broken\": 7}}")
                .run();

        assertEquals(ContentReloadAttempt.Verdict.REJECTED, outcome.attempt().verdict());
        assertSame(published, ContentReloadCoordinator.committed());
    }

    @Test
    @DisplayName("an executable question that did not materialise is a rejection, not a partial commit")
    void aMissingExecutableQuestionRejects() throws Exception {
        ReloadFixture.Outcome good = coherent("quick").run();
        ConversationContentBundle published = ContentReloadCoordinator.committed();
        Object retained = published.ownedQuestion("conversations.cat.chitchat");

        ReloadFixture fixture = coherent("deep");
        ReloadFixture.Outcome outcome = fixture.reload(fixture.manager(), Map.of(),
                Set.of("conversations.cat.chitchat"));

        assertEquals(ContentReloadAttempt.Verdict.REJECTED, outcome.attempt().verdict());
        assertTrue(outcome.attempt().problems().stream()
                .anyMatch(p -> p.reason().equals("owned_question_not_materialised")));
        assertSame(published, ContentReloadCoordinator.committed());
        assertSame(retained, outcome.liveQuestions().get("conversations.cat.chitchat"),
                "and the question an open offer would run is put back into MCA's own map");
    }

    @Test
    @DisplayName("no MCA apply tail means no publication, however good the content was")
    void aMissingApplyTailPublishesNothing() throws Exception {
        ReloadFixture.Outcome good = coherent("quick").run();
        ConversationContentBundle published = ContentReloadCoordinator.committed();

        ReloadFixture.Outcome outcome = coherent("deep").reloadWithoutMcaApply(coherent("deep").manager());

        assertEquals(ContentReloadAttempt.Verdict.REJECTED, outcome.attempt().verdict());
        assertFalse(outcome.attempt().tailObserved());
        assertTrue(outcome.attempt().problems().stream()
                        .anyMatch(p -> p.reason().equals("mca_apply_tail_not_observed")),
                "require = 0 must not be read as 'assume the hook ran'");
        assertSame(published, ContentReloadCoordinator.committed());
    }

    @Test
    @DisplayName("a callback from another Dialogues instance cannot commit")
    void aWrongInstanceCallbackCannotCommit() throws Exception {
        coherent("quick").run();
        ConversationContentBundle published = ContentReloadCoordinator.committed();

        ContentReloadCoordinator.setMcaExpectedForTesting(true);
        ContentReloadCoordinator.begin();
        ContentReloadCoordinator.onDialoguesApplyHead(new Object());
        ContentReloadCoordinator.onDialoguesApplyTail(new Object(), new LinkedHashMap<>());

        assertSame(published, ContentReloadCoordinator.committed(),
                "a late callback from a superseded attempt or another server lifecycle publishes nothing");
    }

    @Test
    @DisplayName("a duplicate apply tail cannot publish a second time")
    void aDuplicateCallbackIsIgnored() throws Exception {
        ReloadFixture fixture = coherent("quick");
        ReloadFixture.Outcome outcome = fixture.run();
        ConversationContentBundle published = ContentReloadCoordinator.committed();

        ContentReloadCoordinator.onDialoguesApplyTail(new Object(), outcome.liveQuestions());

        assertSame(published, ContentReloadCoordinator.committed());
    }

    @Test
    @DisplayName("an initial rejection leaves content explicitly unavailable rather than empty-but-fine")
    void anInitialRejectionLeavesContentUnavailable() throws Exception {
        ContentReloadCoordinator.setCommittedForTesting(ConversationContentBundle.UNAVAILABLE);

        ReloadFixture.Outcome outcome = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", "{\"topics\": {\"day\": 7}}")
                .run();

        assertFalse(outcome.committedNow());
        assertFalse(ContentReloadCoordinator.contentAvailable(),
                "nothing was validated, and the mod says so instead of offering entries built from nothing");
        assertEquals(0L, ContentReloadCoordinator.committed().generation());
        assertFalse(ContentReloadCoordinator.committed().ownedQuestionsKnown(),
                "with no bundle ever committed, owned lookups must pass straight through to MCA");
    }

    @Test
    @DisplayName("a rejected attempt aims at the generation it never reached; the next good one takes it")
    void rejectedAttemptsShareTheirTargetGeneration() throws Exception {
        ContentReloadCoordinator.setCommittedForTesting(ConversationContentBundle.UNAVAILABLE);
        coherent("quick").run();

        ReloadFixture.Outcome first = coherent("deep")
                .with("conversation_scenes", "mcaconversations", "s", "{\"scenes\": {\"broken\": 7}}")
                .run();
        ReloadFixture.Outcome second = coherent("deep")
                .with("conversation_scenes", "mcaconversations", "s", "{\"scenes\": {\"broken\": 7}}")
                .run();

        assertNotSame(first.attempt(), second.attempt());
        assertEquals(first.attempt().targetGeneration(), second.attempt().targetGeneration(),
                "attempt ids advance separately, so several rejected attempts all aim at the same generation");
        assertEquals(1L, ContentReloadCoordinator.committed().generation());

        assertTrue(coherent("deep").run().committedNow());
        assertEquals(2L, ContentReloadCoordinator.committed().generation());
    }
}
