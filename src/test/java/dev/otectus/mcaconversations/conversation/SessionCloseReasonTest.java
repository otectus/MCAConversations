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
    @DisplayName("a topic ending is not a teardown: the player is still standing there")
    void endTopicKeepsTheSession() {
        engage();
        ConversationSessions.endTopic(PLAYER, 100, CloseReason.COMPLETED);
        assertEquals(1, ConversationSessions.size());
        assertEquals(CloseReason.COMPLETED,
                ConversationSessions.raw(PLAYER).orElseThrow().lastCloseReason().orElse(null));
        assertEquals(1, ChatModeScheduler.pendingFor(PLAYER), "a farewell line still in flight is kept");
    }
}
