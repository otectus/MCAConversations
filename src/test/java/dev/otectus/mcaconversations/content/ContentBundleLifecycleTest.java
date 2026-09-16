package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The bundle across a server's life: first load, initial failure, recovery, and letting go.
 *
 * <p>The last of those is the one with teeth. A committed bundle holds MCA's parsed question objects
 * <em>strongly</em>, because an unanswered offer's executable inputs must not be collectable. That is
 * also why it has to be dropped at server stop: a retained executable table belongs to one world, and
 * a second integrated world in the same JVM must not be able to reach the first one's.
 */
class ContentBundleLifecycleTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
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
    @DisplayName("an initial failure leaves the mod explicitly without content, and it recovers on the next good load")
    void initialFailureThenRecovery() throws Exception {
        ContentReloadCoordinator.setCommittedForTesting(ConversationContentBundle.UNAVAILABLE);

        ReloadFixture.Outcome failed = new ReloadFixture()
                .with("conversation_catalog", "mcaconversations", "t", "{ not json")
                .run();

        assertFalse(failed.committedNow());
        assertFalse(ContentReloadCoordinator.contentAvailable());
        assertEquals(0L, ContentReloadCoordinator.committed().generation());
        assertNull(ContentReloadCoordinator.committed().ownedQuestion("conversations.cat.chitchat"));

        ReloadFixture.Outcome recovered = coherent("quick").run();

        assertTrue(recovered.committedNow());
        assertTrue(ContentReloadCoordinator.contentAvailable());
        assertEquals(1L, ContentReloadCoordinator.committed().generation(),
                "the first successful publication is generation 1, whatever came before it");
    }

    @Test
    @DisplayName("server stop releases the executable table, so the next world cannot reach the last one's")
    void serverStopReleasesTheExecutableTable() throws Exception {
        coherent("quick").run();
        Object retained = ContentReloadCoordinator.committed().ownedQuestion("conversations.cat.chitchat");
        assertTrue(retained != null);

        ContentReloadCoordinator.reset();

        assertSame(ConversationContentBundle.UNAVAILABLE, ContentReloadCoordinator.committed());
        assertNull(ContentReloadCoordinator.pending(), "and no attempt survives the world it belonged to");
        assertFalse(ContentReloadCoordinator.governsOwnedLookups(),
                "with the table gone, the boundary goes inert rather than answering 'absent' for everything");

        ReloadFixture.Outcome next = coherent("deep").run();

        assertTrue(next.committedNow());
        assertNotSame(retained, ContentReloadCoordinator.committed().ownedQuestion("conversations.cat.chitchat"),
                "the second world parses its own questions");
    }

    @Test
    @DisplayName("a reload that publishes advances exactly one generation, and repeating it advances one more")
    void generationsAreMonotonicPerCommit() throws Exception {
        ContentReloadCoordinator.setCommittedForTesting(ConversationContentBundle.UNAVAILABLE);

        coherent("quick").run();
        assertEquals(1L, ContentReloadCoordinator.committed().generation());
        coherent("deep").run();
        assertEquals(2L, ContentReloadCoordinator.committed().generation());
        coherent("deep").run();
        assertEquals(3L, ContentReloadCoordinator.committed().generation(),
                "an identical pack is still a new body of content: the objects behind it are new");
    }
}
