package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The six packet-ordering cases the spec requires (§7), asserted against the lifecycle and the pure
 * decision logic the packet handlers use.
 *
 * <p>All six are the same shape: something that was true when a packet was sent is no longer true
 * when it arrives. Before handles, none of them could even be detected — a click, a close and a
 * heartbeat all named a player, and a player only ever had one "current" conversation. The point of
 * every assertion below is not that the straggler is <em>handled</em>, but that it changes nothing:
 * the discussion the player is actually in survives every one of them untouched.
 */
class ConversationPacketOrderingTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("ordering-player".getBytes());
    private static final UUID OTHER_PLAYER = UUID.nameUUIDFromBytes("ordering-player-2".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("ordering-villager".getBytes());
    private static final String DIM = "minecraft:overworld";

    @BeforeEach
    @AfterEach
    void reset() {
        ConversationLifecycle.clearTeardownHooks();
        ConversationSessions.clearAllForTesting();
        ConversationPresence.clear();
        VillagerAttention.reset();
        ChatModeScheduler.reset();
    }

    private static ConversationHandle begin(UUID player, UUID villager) {
        return ConversationLifecycle
                .begin(player, villager, DIM, ConversationSession.Frontend.GUI, 100L).orElseThrow();
    }

    private static HandleAuthority.Decision judge(ConversationHandle named) {
        return HandleAuthority.judgeFor(named.playerId(), named.sessionId(), named.villagerId());
    }

    // 1 --------------------------------------------------------------------------------------

    @Test
    @DisplayName("close before a delayed offer: the offer's discussion no longer exists, so nothing publishes")
    void closeBeforeDelayedOffer() {
        ConversationHandle handle = begin(PLAYER, VILLAGER);
        ConversationSessions.recordOffer(PLAYER, VILLAGER, "q", java.util.List.of("a"),
                ConversationSession.Frontend.GUI, 100L);

        ConversationLifecycle.terminate(handle, CloseReason.CLIENT_CLOSED);

        // The offer publication was queued before the close and runs after it. Its handle is the one
        // that just ended, and the server has none at all for this player now.
        assertEquals(HandleAuthority.Decision.NO_MANAGED_HANDLE, judge(handle));
        assertTrue(HandleAuthority.judgeFor(PLAYER, handle.sessionId(), VILLAGER).stale());
        assertTrue(ConversationSessions.raw(PLAYER).isEmpty(),
                "the teardown must not leave a session for a delayed offer to attach to");
    }

    // 2 --------------------------------------------------------------------------------------

    @Test
    @DisplayName("old clear after a new offer: the clear names the previous discussion and is refused")
    void oldClearAfterNewOffer() {
        ConversationHandle first = begin(PLAYER, VILLAGER);
        ConversationLifecycle.terminate(first, CloseReason.CLIENT_CLOSED);
        ConversationHandle second = begin(PLAYER, VILLAGER);
        ConversationSession.ChoiceOffer live = ConversationSessions.recordOffer(PLAYER, VILLAGER,
                "q", java.util.List.of("a", "b"), ConversationSession.Frontend.GUI, 200L);

        assertEquals(HandleAuthority.Decision.OBSOLETE, judge(first));
        assertEquals(HandleAuthority.Decision.ACCEPT, judge(second));
        // And the live card is still the live card: a refusal is never allowed to retire it.
        assertEquals(live.revision(), ConversationSessions.raw(PLAYER).orElseThrow()
                .currentOffer().orElseThrow().revision());
    }

    // 3 --------------------------------------------------------------------------------------

    @Test
    @DisplayName("old heartbeat after a takeover: it neither revives the old handle nor moves the villager")
    void oldHeartbeatAfterTakeover() {
        ConversationHandle mine = begin(PLAYER, VILLAGER);
        ConversationHandle theirs = ConversationLifecycle.handoff(mine, OTHER_PLAYER, DIM,
                ConversationSession.Frontend.GUI, 300L);

        assertFalse(ConversationPresence.heartbeat(PLAYER, mine.sessionId(), VILLAGER, 310L),
                "a heartbeat for a handed-over discussion must not be recorded");
        assertTrue(ConversationPresence.lastHeartbeat(mine).isEmpty());
        assertEquals(theirs, ConversationPresence.ofVillager(VILLAGER).orElseThrow(),
                "the villager still belongs to the player who took them over");

        // Nor may the old player's heartbeat be re-aimed at the new handle by naming it.
        assertFalse(ConversationPresence.heartbeat(PLAYER, theirs.sessionId(), VILLAGER, 311L));
        assertTrue(ConversationPresence.heartbeat(OTHER_PLAYER, theirs.sessionId(), VILLAGER, 312L));
        assertEquals(312L, ConversationPresence.lastHeartbeat(theirs).orElseThrow());
    }

    // 4 --------------------------------------------------------------------------------------

    @Test
    @DisplayName("duplicate answer: the second copy is refused, and the exchange survives it")
    void duplicateAnswerIsRefusedWithoutEnding() {
        begin(PLAYER, VILLAGER);
        ConversationSession session = ConversationSessions.raw(PLAYER).orElseThrow();
        ConversationSession.ChoiceOffer offer = ConversationSessions.recordOffer(PLAYER, VILLAGER,
                "q", java.util.List.of("a", "b"), ConversationSession.Frontend.GUI, 100L);

        assertTrue(session.consumeOffer(offer.revision(), 0).isPresent());
        assertTrue(session.consumeOffer(offer.revision(), 0).isEmpty(),
                "an offer may be claimed exactly once");
        assertFalse(ChoiceOutcome.INVALID_SUBMISSION.terminal(),
                "a duplicate is a refusal, never an ending");
        assertFalse(ChoiceOutcome.OBSOLETE_HANDLE.terminal());
        assertTrue(ConversationPresence.ofPlayer(PLAYER).isPresent(),
                "the discussion is still the player's after a duplicate");
    }

    // 5 --------------------------------------------------------------------------------------

    @Test
    @DisplayName("old close after the same pair reopens: the successor keeps its session and villager")
    void oldCloseAfterSamePairReopen() {
        ConversationHandle first = begin(PLAYER, VILLAGER);
        ConversationLifecycle.terminate(first, CloseReason.CLIENT_CLOSED);
        ConversationHandle second = begin(PLAYER, VILLAGER);
        assertNotEquals(first.sessionId(), second.sessionId(),
                "the same pair talking twice is two discussions");

        // The straggler arrives now, naming the first discussion.
        assertTrue(ConversationLifecycle
                .terminateIfCurrent(PLAYER, first.sessionId(), VILLAGER, CloseReason.CLIENT_CLOSED)
                .isEmpty());
        assertEquals(second, ConversationPresence.ofPlayer(PLAYER).orElseThrow());
        assertEquals(second, ConversationPresence.ofVillager(VILLAGER).orElseThrow());
        assertTrue(ConversationSessions.raw(PLAYER).isPresent(),
                "the live discussion keeps its session");

        // And the same close, correctly named, does end it.
        assertTrue(ConversationLifecycle
                .terminateIfCurrent(PLAYER, second.sessionId(), VILLAGER, CloseReason.CLIENT_CLOSED)
                .isPresent());
    }

    // 6 --------------------------------------------------------------------------------------

    @Test
    @DisplayName("MCA's tokenless close after a transfer: refused for the old owner, allowed for the new")
    void legacyTokenlessCloseAfterTransfer() {
        ConversationHandle mine = begin(PLAYER, VILLAGER);
        ConversationHandle theirs = ConversationLifecycle.handoff(mine, OTHER_PLAYER, DIM,
                ConversationSession.Frontend.GUI, 400L);
        assertEquals(theirs, ConversationPresence.ofVillager(VILLAGER).orElseThrow());

        assertFalse(HandleAuthority.allowsNativeCloseOf(VILLAGER, PLAYER),
                "the previous participant's close must not end the new owner's conversation");
        assertTrue(HandleAuthority.allowsNativeCloseOf(VILLAGER, OTHER_PLAYER),
                "the owner's own close is MCA's to run");

        // And a villager nobody manages is none of this mod's business.
        UUID unmanaged = UUID.nameUUIDFromBytes("unmanaged-villager".getBytes());
        assertTrue(HandleAuthority.allowsNativeCloseOf(unmanaged, PLAYER));
    }

    // Supporting rules ------------------------------------------------------------------------

    @Test
    @DisplayName("a packet that names no discussion is judged the old way, not refused")
    void unidentifiedPacketsAreNotRefusals() {
        begin(PLAYER, VILLAGER);
        assertEquals(HandleAuthority.Decision.UNIDENTIFIED,
                HandleAuthority.judgeFor(PLAYER, null, VILLAGER));
        assertFalse(HandleAuthority.judgeFor(PLAYER, null, VILLAGER).stale());
        assertTrue(HandleAuthority.judgeFor(PLAYER, null, VILLAGER).allowed());
    }

    @Test
    @DisplayName("a session id that names the right discussion but the wrong villager is refused")
    void aMismatchedVillagerIsObsolete() {
        ConversationHandle handle = begin(PLAYER, VILLAGER);
        UUID somebodyElse = UUID.nameUUIDFromBytes("ordering-villager-2".getBytes());
        assertEquals(HandleAuthority.Decision.OBSOLETE,
                HandleAuthority.judgeFor(PLAYER, handle.sessionId(), somebodyElse));
    }

    @Test
    @DisplayName("an ending drops only its own queued lines, never the successor's")
    void scheduledWorkIsClearedByHandleNotByPlayer() {
        ConversationHandle first = begin(PLAYER, VILLAGER);
        ChatModeScheduler.schedule(PLAYER, 1_000L, () -> {
        });
        assertEquals(1, ChatModeScheduler.pendingFor(PLAYER));

        ConversationLifecycle.terminate(first, CloseReason.CLIENT_CLOSED);
        assertEquals(0, ChatModeScheduler.pendingFor(PLAYER),
                "the ended discussion's line must not arrive after the goodbye");

        ConversationHandle second = begin(PLAYER, VILLAGER);
        ChatModeScheduler.schedule(PLAYER, 2_000L, () -> {
        });
        // The straggling close for the first discussion changes nothing about the second's queue.
        ConversationLifecycle.terminateIfCurrent(PLAYER, first.sessionId(), VILLAGER,
                CloseReason.CLIENT_CLOSED);
        assertEquals(1, ChatModeScheduler.pendingFor(PLAYER));
        assertEquals(second, ConversationPresence.ofPlayer(PLAYER).orElseThrow());
    }
}
