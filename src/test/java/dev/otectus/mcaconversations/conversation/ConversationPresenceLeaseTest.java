package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The presence lease: what a graphical conversation has instead of a reading timeout (spec §4.3).
 *
 * <p>The two failures it stands between are opposites. A player reading a long reply for three
 * minutes must not lose the villager — nothing about the passage of time is held against them. A
 * client that crashed, disconnected, or whose window simply vanished must not keep that villager
 * pinned to a conversation nobody is in. The lease tells them apart by asking the only question that
 * distinguishes them: is the window still there to say so.
 *
 * <p>The tick that drives it lives in the server event handler and needs a world, so what is asserted
 * here is everything underneath it: the timestamps, who may refresh them, and the idle sweep that
 * must now keep its hands off a discussion the lease is already governing.
 */
class ConversationPresenceLeaseTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("lease-player".getBytes());
    private static final UUID OTHER_PLAYER = UUID.nameUUIDFromBytes("lease-player-2".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("lease-villager".getBytes());
    private static final String DIM = "minecraft:overworld";
    private static final int LEASE = 100;

    @BeforeEach
    @AfterEach
    void reset() {
        ConversationLifecycle.clearTeardownHooks();
        ConversationSessions.clearAllForTesting();
        ConversationPresence.clear();
        VillagerAttention.reset();
        ChatModeScheduler.reset();
    }

    private static ConversationHandle begin(UUID player, UUID villager, long now) {
        return ConversationLifecycle
                .begin(player, villager, DIM, ConversationSession.Frontend.GUI, now).orElseThrow();
    }

    @Test
    @DisplayName("an accepted discussion is leased from the moment it is accepted")
    void acceptanceSeedsTheLease() {
        ConversationHandle handle = begin(PLAYER, VILLAGER, 1_000L);

        assertEquals(1_000L, ConversationPresence.lastHeartbeat(handle).orElseThrow(),
                "a window that never attaches must still be expirable");
        assertFalse(ConversationPresence.leaseExpired(handle, 1_100L, LEASE),
                "the lease itself is not yet over");
        assertTrue(ConversationPresence.leaseExpired(handle, 1_101L, LEASE));
    }

    @Test
    @DisplayName("a heartbeat keeps the discussion alive, however long the player reads")
    void heartbeatsKeepTheHandleAlive() {
        ConversationHandle handle = begin(PLAYER, VILLAGER, 1_000L);

        // Three minutes of reading, reported on the client's twenty-tick cadence.
        for (long tick = 1_020L; tick <= 4_600L; tick += 20L) {
            assertTrue(ConversationPresence.heartbeat(PLAYER, handle.sessionId(), VILLAGER, tick));
            assertFalse(ConversationPresence.leaseExpired(handle, tick, LEASE),
                    "reading is not idleness");
        }
        assertFalse(ConversationPresence.leaseExpired(handle, 4_700L, LEASE));
        // And the moment the window stops reporting, the villager is released.
        assertTrue(ConversationPresence.leaseExpired(handle, 4_701L, LEASE));
    }

    @Test
    @DisplayName("a heartbeat from the wrong player or the wrong discussion extends nothing")
    void spoofedHeartbeatsDoNotExtendTheLease() {
        ConversationHandle handle = begin(PLAYER, VILLAGER, 1_000L);

        assertFalse(ConversationPresence.heartbeat(OTHER_PLAYER, handle.sessionId(), VILLAGER, 1_090L),
                "a heartbeat may only ever renew the sender's own discussion");
        UUID notThisDiscussion = UUID.nameUUIDFromBytes("some-other-session".getBytes());
        assertFalse(ConversationPresence.heartbeat(PLAYER, notThisDiscussion, VILLAGER, 1_090L));
        UUID notThisVillager = UUID.nameUUIDFromBytes("lease-villager-2".getBytes());
        assertFalse(ConversationPresence.heartbeat(PLAYER, handle.sessionId(), notThisVillager, 1_090L));

        assertEquals(1_000L, ConversationPresence.lastHeartbeat(handle).orElseThrow(),
                "a refused heartbeat must not have moved the timestamp");
        assertTrue(ConversationPresence.leaseExpired(handle, 1_101L, LEASE),
                "and so the lease still expires on time");
    }

    @Test
    @DisplayName("an ended discussion has no lease left to expire")
    void aRetiredHandleIsNobodysToExpire() {
        ConversationHandle handle = begin(PLAYER, VILLAGER, 1_000L);
        ConversationLifecycle.terminate(handle, CloseReason.CLIENT_CLOSED);

        assertTrue(ConversationPresence.lastHeartbeat(handle).isEmpty());
        assertFalse(ConversationPresence.leaseExpired(handle, 9_999L, LEASE),
                "the timestamps go with the handle; nothing is left to sweep twice");
    }

    @Test
    @DisplayName("a lease of zero switches the rule off rather than expiring everything at once")
    void zeroDisablesTheLease() {
        ConversationHandle handle = begin(PLAYER, VILLAGER, 1_000L);
        assertFalse(ConversationPresence.leaseExpired(handle, 1_000_000L, 0));
    }

    @Test
    @DisplayName("the out-of-range marker is remembered per discussion and cleared with it")
    void theGraceMarkerBelongsToTheDiscussion() {
        ConversationHandle handle = begin(PLAYER, VILLAGER, 1_000L);
        assertEquals(ConversationDistancePolicy.NOT_OUTSIDE, ConversationPresence.outsideSince(handle));

        ConversationPresence.noteOutside(handle, 1_050L);
        assertEquals(1_050L, ConversationPresence.outsideSince(handle));
        ConversationPresence.noteOutside(handle, ConversationDistancePolicy.NOT_OUTSIDE);
        assertEquals(ConversationDistancePolicy.NOT_OUTSIDE, ConversationPresence.outsideSince(handle),
                "walking back inside forgets the lapse entirely");

        ConversationPresence.noteOutside(handle, 1_060L);
        ConversationLifecycle.terminate(handle, CloseReason.OUT_OF_RANGE);
        assertEquals(ConversationDistancePolicy.NOT_OUTSIDE, ConversationPresence.outsideSince(handle));
    }

    @Test
    @DisplayName("a player reading for minutes is not swept away from under the conversation")
    void theIdleSweepLeavesALeasedDiscussionAlone() {
        ConversationHandle handle = begin(PLAYER, VILLAGER, 100L);
        ConversationSession session = ConversationSessions.raw(PLAYER).orElseThrow();
        ConversationSession.ChoiceOffer offer = ConversationSessions.recordOffer(PLAYER, VILLAGER,
                "q", List.of("a", "b"), ConversationSession.Frontend.GUI, 100L);
        // Answered, so nothing is pending: the old guard no longer applies and only the lease does.
        assertTrue(session.consumeOffer(offer.revision(), 0).isPresent());

        assertEquals(0, ConversationSessions.sweep(1_000_000L),
                "the lease decides when a graphical discussion ends, not a stopwatch");
        assertTrue(ConversationPresence.ofPlayer(PLAYER).isPresent());
        assertTrue(ConversationSessions.raw(PLAYER).isPresent());

        // A session with no discussion behind it is swept exactly as it always was.
        ConversationSessions.get(OTHER_PLAYER, 100L);
        assertEquals(1, ConversationSessions.sweep(1_000_000L));
        assertTrue(ConversationSessions.raw(OTHER_PLAYER).isEmpty());
        assertTrue(ConversationPresence.isCurrent(handle), "and the leased one is still standing");
    }
}
