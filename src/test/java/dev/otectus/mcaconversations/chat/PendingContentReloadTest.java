package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.conversation.DepthClass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Work that is already queued when a reload lands.
 *
 * <p>A chat reply is chosen and rendered at match time and only its <em>delivery</em> is deferred by
 * the humanized delay. That delay is where a reload can land, so the queued task carries the bundle
 * it was made under: it finishes the line it was already going to say, out of the content that chose
 * it, rather than re-reading a catalog published in the meantime.
 */
class PendingContentReloadTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("player".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("villager".getBytes());

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @BeforeEach
    void reset() {
        ChatModeScheduler.reset();
        ConversationSessions.clearAllForTesting();
    }

    @AfterEach
    void restore() {
        ChatModeScheduler.reset();
        ConversationSessions.clearAllForTesting();
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    @Test
    @DisplayName("a queued delivery runs against the bundle it was queued under")
    void queuedDeliveryKeepsItsBundle() {
        ConversationContentBundle atQueueTime = ContentReloadCoordinator.committed();
        AtomicReference<ConversationContentBundle> seen = new AtomicReference<>();
        ChatModeScheduler.schedule(PLAYER, 10L, () -> seen.set(ContentOperation.bundle()));

        // A reload commits during the humanized delay.
        ContentReloadCoordinator.advanceGenerationForTesting();
        ChatModeScheduler.drain(20L);

        assertSame(atQueueTime, seen.get(),
                "the line was chosen out of that content; delivering it out of another would be a"
                        + " different answer to the same message");
    }

    @Test
    @DisplayName("a successful publication cancels pending content-dependent work through close(), by name")
    void publicationCancelsPendingWorkThroughClose() {
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);
        ChatModeScheduler.schedule(PLAYER, 10L, () -> {
            throw new AssertionError("a cancelled reply must never surface later");
        });

        int cancelled = ConversationSessions.closeForContentReload();

        assertEquals(1, cancelled);
        assertTrue(ConversationSessions.raw(PLAYER).isEmpty(),
                "close() is the teardown; endTopic would have left the session and its attention lease");
        // close() is also what drops the queued reply, so draining past its deadline must be silent.
        ChatModeScheduler.drain(20L);
    }

    @Test
    @DisplayName("a session with a live offer is left standing, so the click is refused truthfully")
    void anOpenOfferSurvivesTheCancellationSweep() {
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);
        ConversationSessions.recordOffer(PLAYER, VILLAGER, "conversations.cat.chitchat",
                List.of("yes", "no"), ConversationSession.Frontend.GUI, 100);

        int cancelled = ConversationSessions.closeForContentReload();

        assertEquals(0, cancelled);
        assertFalse(ConversationSessions.raw(PLAYER).isEmpty(),
                "dropping the offer would make the next click 'there is no live offer' rather than"
                        + " 'the content was reloaded', which is the wrong thing to tell a player");
    }
}
