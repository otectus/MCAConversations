package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * One <em>successful</em> reload, one generation (audit F12).
 *
 * <p>The generation used to be an independent counter bumped by a terminal listener that loaded
 * nothing and inspected nothing, so it advanced over a reload in which half the loaders had refused
 * their pack — a marker that a reload happened, not evidence the content behind it was coherent. It
 * is a property of the committed {@link ConversationContentBundle} now, so the invariants worth
 * pinning are about commits: it moves only forward, only on a commit, and never twice for one.
 */
class ContentGenerationTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    @Test
    @DisplayName("the generation is the committed bundle's, and advances only when one is published")
    void generationIsThePublishedBundles() {
        ContentReloadCoordinator.setCommittedForTesting(ConversationContentBundle.UNAVAILABLE);

        assertEquals(0L, ContentGeneration.current(),
                "nothing has been published, and the generation says so rather than claiming 1");
        assertTrue(ConversationContentBundle.UNAVAILABLE.available() == false,
                "the bootstrap bundle is explicitly unavailable, not an empty success");

        long first = ContentReloadCoordinator.advanceGenerationForTesting();
        long second = ContentReloadCoordinator.advanceGenerationForTesting();

        assertEquals(1L, first, "the first successful publication is generation 1");
        assertEquals(first + 1, second);
        assertEquals(second, ContentGeneration.current());
    }

    @Test
    @DisplayName("an operation reads the generation of the bundle it pinned, not of a later commit")
    void generationInsideAnOperationIsPinned() {
        ContentReloadCoordinator.setCommittedForTesting(ConversationContentBundle.UNAVAILABLE);
        ContentReloadCoordinator.advanceGenerationForTesting();

        try (ContentOperation operation = ContentOperation.open()) {
            long pinned = ContentGeneration.current();
            ConversationContentBundle captured = operation.captured();

            ContentReloadCoordinator.advanceGenerationForTesting();

            assertEquals(pinned, ContentGeneration.current(),
                    "a commit landing mid-operation must not change the generation that operation mints with");
            assertSame(captured, ContentOperation.bundle(),
                    "and must not change the content either");
        }

        assertEquals(2L, ContentGeneration.current(),
                "outside the operation the new generation is in force immediately");
    }
}
