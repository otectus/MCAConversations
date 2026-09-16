package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.AttentionLedger;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Every way a conversation can end has to leave the same nothing behind: no session, no attention
 * lease pinning the villager in place, and no reply still queued for a player who has stopped
 * listening (plan §WP08). One test per ending, all four asserting the same three things plus the
 * reason that was stamped.
 */
class SessionCloseReasonTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("close-player".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("close-villager".getBytes());

    @BeforeEach
    void reset() {
        ConversationSessions.clearAllForTesting();
        ConversationPresence.clear();
        OpenRateLimiter.clear();
        VillagerAttention.reset();
        ChatModeScheduler.reset();
    }

    /** A player mid-conversation: a session on a villager, a lease on it, a line still queued. */
    private ConversationSession engage() {
        ConversationSession session = ConversationSessions.beginTopic(
                PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);
        VillagerAttention.hold(VILLAGER, PLAYER, 1_000, AttentionLedger.Source.CONVERSATION);
        ChatModeScheduler.scheduleOrdered(PLAYER, 200, () -> { });
        assertEquals(1, VillagerAttention.activeHolds().size());
        assertEquals(1, ChatModeScheduler.pendingFor(PLAYER));
        return session;
    }

    private void assertTornDown(ConversationSession session, CloseReason expected) {
        assertEquals(expected, session.lastCloseReason().orElse(null));
        assertEquals(0, ConversationSessions.size());
        assertTrue(ConversationSessions.raw(PLAYER).isEmpty());
        assertFalse(VillagerAttention.activeHolds().containsKey(VILLAGER));
        assertEquals(0, ChatModeScheduler.pendingFor(PLAYER));
    }

    @Test
    @DisplayName("logout closes the session as DISCONNECTED and leaves nothing behind")
    void logout() {
        ConversationSession session = engage();
        ConversationSessions.close(PLAYER, CloseReason.DISCONNECTED);
        assertTornDown(session, CloseReason.DISCONNECTED);
    }

    @Test
    @DisplayName("the villager's death closes every session pointed at it as SPEAKER_DEAD")
    void villagerDeath() {
        ConversationSession session = engage();
        ConversationSessions.clearVillager(VILLAGER, CloseReason.SPEAKER_DEAD);
        assertTornDown(session, CloseReason.SPEAKER_DEAD);
    }

    @Test
    @DisplayName("the idle sweep closes as TIMED_OUT")
    void sweepTimeout() {
        ConversationSession session = engage();
        assertEquals(1, ConversationSessions.sweep(500_000));
        assertTornDown(session, CloseReason.TIMED_OUT);
    }

    @Test
    @DisplayName("a server stop closes every session with the reason it was given")
    void clearAll() {
        ConversationSession session = engage();
        ConversationSessions.clearAll(CloseReason.DISCONNECTED);
        assertTornDown(session, CloseReason.DISCONNECTED);
    }

    @Test
    @DisplayName("closing a player who never had a session is still a clean teardown")
    void closeWithoutSession() {
        VillagerAttention.hold(VILLAGER, PLAYER, 1_000, AttentionLedger.Source.TYPING);
        ChatModeScheduler.scheduleOrdered(PLAYER, 200, () -> { });
        assertTrue(ConversationSessions.close(PLAYER, CloseReason.PLAYER_LEFT).isEmpty());
        assertFalse(VillagerAttention.activeHolds().containsKey(VILLAGER));
        assertEquals(0, ChatModeScheduler.pendingFor(PLAYER));
    }

    @Test
    @DisplayName("an engagement refusal names the same ending in the session's vocabulary")
    void verdictMapping() {
        assertEquals(CloseReason.DISCONNECTED, CloseReason.of(EngagementPolicy.Verdict.SPEAKER_GONE));
        assertEquals(CloseReason.PLAYER_LEFT, CloseReason.of(EngagementPolicy.Verdict.SPEAKER_DEAD));
        assertEquals(CloseReason.SPEAKER_DEAD, CloseReason.of(EngagementPolicy.Verdict.VILLAGER_DEAD));
        assertEquals(CloseReason.DIMENSION_CHANGED, CloseReason.of(EngagementPolicy.Verdict.DIMENSION_CHANGED));
        assertEquals(CloseReason.OUT_OF_RANGE, CloseReason.of(EngagementPolicy.Verdict.OUT_OF_RANGE));
        assertEquals(CloseReason.COMPLETED, CloseReason.of(EngagementPolicy.Verdict.OK));
    }

    @Test
    @DisplayName("a technical interruption leaves no stance or outcome for a later thread write to read")
    void interruptionRecordsNothingSocial() {
        // The thread directive writes `playerStance` and `lastOutcome` from exactly these two reads
        // (LivingHistoriesRegistrar, thread op PLAYED). If an interruption left them standing, the
        // next scene this pair played would inherit the stance of an exchange that never finished,
        // and a thread would record a walked-away ending for a dropped connection.
        for (CloseReason reason : new CloseReason[] {CloseReason.DISCONNECTED, CloseReason.PLAYER_LEFT,
                CloseReason.SPEAKER_DEAD, CloseReason.SPEAKER_UNAVAILABLE, CloseReason.OUT_OF_RANGE,
                CloseReason.DIMENSION_CHANGED, CloseReason.TIMED_OUT, CloseReason.CONTAINED_ERROR}) {
            reset();
            ConversationSession session = engage();
            session.recordPlayerStance(StanceFamily.EMPATHY, java.util.List.of());
            assertTrue(session.lastPlayerStance().isPresent());

            ConversationSessions.close(PLAYER, reason);

            assertEquals(reason, session.lastCloseReason().orElse(null));
            assertTrue(session.lastPlayerStance().isEmpty(),
                    "an interruption is not a stance: " + reason);
            assertTrue(session.lastOutcome().isEmpty(),
                    "an interruption is not an outcome: " + reason);
        }
    }

    @Test
    @DisplayName("a topic ending is not a teardown: the player is still standing there")
    void endTopicKeepsTheSession() {
        engage();
        ConversationSessions.endTopic(PLAYER, 100, CloseReason.COMPLETED);
        assertEquals(1, ConversationSessions.size());
        assertEquals(CloseReason.COMPLETED,
                ConversationSessions.raw(PLAYER).orElseThrow().lastCloseReason().orElse(null));
        assertEquals(1, ChatModeScheduler.pendingFor(PLAYER), "a farewell line still in flight is kept");
    }

    @Test
    @DisplayName("every close reason has a permanent id of its own, and no ordinal is one of them")
    void closeReasonWireIdsArePinnedAndUnique() {
        // Pinned on purpose: these numbers go on the wire and into logs that a later version still
        // has to read. Changing one silently turns an old close into a different ending, so a test
        // that fails here is telling the truth — add a new constant instead of renumbering.
        java.util.Map<CloseReason, Integer> pinned = new java.util.LinkedHashMap<>();
        pinned.put(CloseReason.COMPLETED, 0);
        pinned.put(CloseReason.PLAYER_LEFT, 1);
        pinned.put(CloseReason.SPEAKER_DEAD, 2);
        pinned.put(CloseReason.SPEAKER_UNAVAILABLE, 3);
        pinned.put(CloseReason.OUT_OF_RANGE, 4);
        pinned.put(CloseReason.DIMENSION_CHANGED, 5);
        pinned.put(CloseReason.DISCONNECTED, 6);
        pinned.put(CloseReason.TIMED_OUT, 7);
        pinned.put(CloseReason.CONTENT_RELOADED, 8);
        pinned.put(CloseReason.FEATURE_DISABLED, 9);
        pinned.put(CloseReason.INVALID_OFFER, 10);
        pinned.put(CloseReason.CONTAINED_ERROR, 11);
        pinned.put(CloseReason.CLIENT_CLOSED, 12);
        pinned.put(CloseReason.TARGET_CHANGED, 13);
        pinned.put(CloseReason.TAKEN_OVER, 14);
        pinned.put(CloseReason.ATTACKED, 15);
        pinned.put(CloseReason.ENTITY_UNLOADED, 16);
        pinned.put(CloseReason.DANGER, 17);

        assertEquals(pinned.size(), CloseReason.values().length,
                "a new ending needs a pinned id here too");
        java.util.Set<Integer> seen = new java.util.HashSet<>();
        for (CloseReason reason : CloseReason.values()) {
            assertEquals(pinned.get(reason), reason.wireId(), "stable id for " + reason);
            assertTrue(seen.add(reason.wireId()), "two endings share an id: " + reason);
            assertEquals(reason, CloseReason.byWireId(reason.wireId()).orElse(null));
        }
        assertTrue(CloseReason.byWireId(9999).isEmpty(), "an unknown id is not guessed at");
    }

    @Test
    @DisplayName("an accepted discussion tears down through its handle, leaving the same nothing")
    void closingAnAcceptedDiscussion() {
        ConversationHandle handle = ConversationLifecycle.begin(PLAYER, VILLAGER, "minecraft:overworld",
                ConversationSession.Frontend.GUI, 100).orElseThrow();
        ConversationSession session = engage();
        ConversationSessions.close(PLAYER, CloseReason.CLIENT_CLOSED);

        assertTornDown(session, CloseReason.CLIENT_CLOSED);
        assertFalse(ConversationPresence.isCurrent(handle), "the villager is free for the next player");
        assertTrue(ConversationPresence.ofVillager(VILLAGER).isEmpty());
    }
}
