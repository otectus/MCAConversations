package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Chat-mode work is pinned to one content bundle on the thread it runs on.
 *
 * <p>Both entry points, observed chat and radius-local chat, hand their pipeline to
 * {@link ChatModeDispatcher#pinnedHop} on the server thread. Until 1.8.0 local chat opened its pin
 * around the submission instead, on the network thread; {@link ContentOperation} is thread-confined, so
 * the scheduled pipeline ran unpinned and a {@code /reload} between two of its steps could answer one
 * message from two catalogs. These drive the hop body with a real second thread standing in for the
 * server thread, since the entry points themselves need a live server and player.
 */
class ChatModeDispatcherHopTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    private static ConversationContentBundle bundle(long generation) {
        return ConversationContentBundle.UNAVAILABLE.published(generation, 1L, Map.of(), true, List.of());
    }

    @Test
    void aReloadLandingMidMessageIsNotSeenByTheRestOfIt() {
        ConversationContentBundle first = bundle(7L);
        ContentReloadCoordinator.setCommittedForTesting(first);
        AtomicReference<ConversationContentBundle> atStart = new AtomicReference<>();
        AtomicReference<ConversationContentBundle> afterReload = new AtomicReference<>();

        ChatModeDispatcher.pinnedHop(() -> {
            atStart.set(ContentOperation.pinnedOrNull());
            ContentReloadCoordinator.setCommittedForTesting(bundle(8L)); // a /reload commits mid-message
            afterReload.set(ContentOperation.bundle());
        }, "test hop");

        assertSame(first, atStart.get(), "the hop pins the committed bundle on entry");
        assertSame(first, afterReload.get(), "and the rest of the message reads that one, not the new one");
        assertNull(ContentOperation.pinnedOrNull(), "the pin is released when the hop ends");
    }

    @Test
    void aPinTakenOnTheSubmittingThreadDoesNotReachTheServerThread() throws Exception {
        ExecutorService serverThread = Executors.newSingleThreadExecutor();
        try (ContentOperation submittingThreadPin = ContentOperation.open()) {
            // What 1.8.0's local chat did: pin here, run over there. The task sees no pin at all.
            assertNull(serverThread.submit(ContentOperation::pinnedOrNull).get());

            // What both entry points do now: the task pins for itself.
            ConversationContentBundle seen = serverThread.submit(() -> {
                AtomicReference<ConversationContentBundle> inside = new AtomicReference<>();
                ChatModeDispatcher.pinnedHop(() -> inside.set(ContentOperation.pinnedOrNull()), "test hop");
                return inside.get();
            }).get();
            assertSame(ContentReloadCoordinator.committed(), seen);
        } finally {
            serverThread.shutdownNow();
        }
    }

    @Test
    void aFailingStepIsContainedAndStillReleasesThePin() {
        ChatModeDispatcher.pinnedHop(() -> {
            throw new IllegalStateException("expected by ChatModeDispatcherHopTest");
        }, "chat-mode hop failure expected by ChatModeDispatcherHopTest");
        assertNull(ContentOperation.pinnedOrNull());
    }
}
