package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.ChatIntentLoader;
import dev.otectus.mcaconversations.scene.SceneCatalogLoader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * One operation, one bundle — including its nested helper calls.
 *
 * <p>A planning or execution operation reads the catalog, the scenes, the beats and the narrative
 * templates through a dozen nested helpers. Publication is a single reference assignment on the
 * server thread, so it can land between any two of those reads. What this pins is that it cannot be
 * observed inside one operation: the capture is taken at the entry point and every accessor below it
 * resolves against the captured bundle, not against the newest one.
 */
class ContentOperationConsistencyTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    private static ConversationContentBundle bundleWith(TopicEntry topic) {
        return ConversationContentBundle.UNAVAILABLE
                .withTopics(ConversationCatalog.build(List.of(topic)))
                .published(4L, 1L, java.util.Map.of(), true, List.of());
    }

    private static TopicEntry topic(String id) {
        return new TopicEntry(id, "conversations.cat.chitchat", "day", DepthClass.QUICK,
                "conversations.cat.chitchat", java.util.Set.of(AgeGroup.ADULT),
                java.util.Set.of(StanceFamily.EXIT), false, java.util.Optional.empty(),
                java.util.Set.of(), java.util.Map.of(), java.util.Optional.empty(), false);
    }

    @Test
    @DisplayName("a publication between two nested reads is not observed by the operation around them")
    void oneOperationSeesOneBundle() {
        ContentReloadCoordinator.setCommittedForTesting(bundleWith(topic("day")));

        try (ContentOperation operation = ContentOperation.open()) {
            ConversationCatalog first = ConversationCatalogLoader.active();
            long firstGeneration = ContentGeneration.current();

            // A reload commits, exactly as it does between two tasks on the server thread.
            ContentReloadCoordinator.setCommittedForTesting(bundleWith(topic("night")));

            assertSame(first, ConversationCatalogLoader.active(),
                    "the nested read sees the bundle the operation captured");
            assertEquals(firstGeneration, ContentGeneration.current(),
                    "and an offer minted late in the operation carries the same generation as an early one");
            assertSame(operation.captured().scenes(), SceneCatalogLoader.active(),
                    "every section comes from the one capture, not section by section");
            assertSame(operation.captured().intents(), ChatIntentLoader.active());
        }

        assertNotSame(bundleWith(topic("day")).topics(), ConversationCatalogLoader.active());
        assertEquals("night", ConversationCatalogLoader.topicIds().get(0),
                "outside the operation the newly published content is in force immediately");
    }

    @Test
    @DisplayName("a nested entry point joins the outer capture rather than taking a newer one")
    void nestedOperationsJoinTheOuterCapture() {
        ContentReloadCoordinator.setCommittedForTesting(bundleWith(topic("day")));

        try (ContentOperation outer = ContentOperation.open()) {
            ContentReloadCoordinator.setCommittedForTesting(bundleWith(topic("night")));
            try (ContentOperation inner = ContentOperation.open()) {
                assertSame(outer.captured(), inner.captured(),
                        "a helper that is itself an entry point elsewhere must not re-read newer content");
            }
            assertSame(outer.captured(), ContentOperation.bundle(),
                    "and closing the inner one does not release the outer pin");
        }
    }

    @Test
    @DisplayName("background work has no ambient bundle and reads the committed one")
    void backgroundWorkIsNotPinned() throws Exception {
        ContentReloadCoordinator.setCommittedForTesting(bundleWith(topic("day")));

        AtomicReference<ConversationContentBundle> seen = new AtomicReference<>();
        try (ContentOperation ignored = ContentOperation.open()) {
            ContentReloadCoordinator.setCommittedForTesting(bundleWith(topic("night")));
            CompletableFuture.runAsync(() -> {
                seen.set(ContentOperation.pinnedOrNull());
            }).join();
        }

        assertNull(seen.get(),
                "the pin is thread-confined and not inheritable: a background job must never consult it");
    }
}
