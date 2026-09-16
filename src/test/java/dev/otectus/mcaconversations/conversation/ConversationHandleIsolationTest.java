package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.AttentionLedger;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The guarantee the whole lifecycle rests on: a discussion that has ended cannot reach into the one
 * that replaced it (spec §3.2.6, §4.5).
 *
 * <p>Every case here is a close that arrives too late — the player reopened the same villager, a
 * second player took them over, the packet simply arrived twice — and in every one the answer must
 * be the same: the stale handle changes nothing, and the discussion that is actually live keeps its
 * session, its villager and its attention.
 */
class ConversationHandleIsolationTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("handle-player".getBytes());
    private static final UUID OTHER_PLAYER = UUID.nameUUIDFromBytes("handle-player-2".getBytes());
    private static final UUID THIRD_PLAYER = UUID.nameUUIDFromBytes("handle-player-3".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("handle-villager".getBytes());
    private static final UUID OTHER_VILLAGER = UUID.nameUUIDFromBytes("handle-villager-2".getBytes());
    private static final String DIM = "minecraft:overworld";

    @BeforeEach
    @AfterEach
    void reset() {
        ConversationLifecycle.clearTeardownHooks();
        ConversationSessions.clearAllForTesting();
        ConversationPresence.clear();
        OpenRateLimiter.clear();
        VillagerAttention.reset();
        ChatModeScheduler.reset();
    }

    private ConversationHandle begin(UUID player, UUID villager) {
        ConversationHandle handle = ConversationLifecycle
                .begin(player, villager, DIM, ConversationSession.Frontend.GUI, 100).orElseThrow();
        VillagerAttention.hold(villager, player, 1_000, AttentionLedger.Source.CONVERSATION, handle);
        return handle;
    }

    @Test
    @DisplayName("a reopened discussion is a different handle, and the old one cannot close it")
    void staleHandleCannotTerminateItsSuccessor() {
        ConversationHandle first = begin(PLAYER, VILLAGER);
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);
        assertTrue(ConversationLifecycle.terminate(first, CloseReason.CLIENT_CLOSED).isPresent());

        // The same player walks back up to the same villager: same pair, new discussion.
        ConversationHandle second = begin(PLAYER, VILLAGER);
        assertNotEquals(first, second, "the same pair talking twice is not the same discussion");
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 200);
        ChatModeScheduler.scheduleOrdered(PLAYER, 400, () -> { });

        // The first screen's delayed close finally arrives.
        assertTrue(ConversationLifecycle.terminate(first, CloseReason.CLIENT_CLOSED).isEmpty(),
                "a retired handle has nothing left to close");

        assertTrue(ConversationPresence.ownsPlayer(second), "the live discussion is untouched");
        assertTrue(ConversationPresence.ownsVillager(second));
        assertEquals(1, ConversationSessions.size());
        assertEquals(Optional.of(second), ConversationSessions.raw(PLAYER).orElseThrow().handle());
        assertTrue(VillagerAttention.activeHolds().containsKey(VILLAGER), "the villager still attends");
        assertEquals(1, ChatModeScheduler.pendingFor(PLAYER), "the new discussion's line still arrives");
    }

    @Test
    @DisplayName("terminating twice is a no-op the second time, not a second teardown")
    void terminateIsIdempotent() {
        ConversationHandle handle = begin(PLAYER, VILLAGER);
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);

        ConversationSession removed = ConversationLifecycle
                .terminate(handle, CloseReason.OUT_OF_RANGE).orElseThrow();
        assertEquals(CloseReason.OUT_OF_RANGE, removed.lastCloseReason().orElse(null));
        assertEquals(0, ConversationPresence.size());
        assertFalse(VillagerAttention.activeHolds().containsKey(VILLAGER));

        assertTrue(ConversationLifecycle.terminate(handle, CloseReason.TIMED_OUT).isEmpty());
        assertEquals(CloseReason.OUT_OF_RANGE, removed.lastCloseReason().orElse(null),
                "the second close does not restamp an ending that already happened");
        assertEquals(0, ConversationSessions.size());
    }

    @Test
    @DisplayName("a teardown stage that throws still releases the ownership indexes")
    void aFailedStageStillReleasesTheIndexes() {
        ConversationLifecycle.addTeardownHook((handle, reason) -> {
            throw new IllegalStateException("the native close adapter is unavailable");
        });
        ConversationHandle handle = begin(PLAYER, VILLAGER);
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);

        ConversationLifecycle.terminate(handle, CloseReason.CONTAINED_ERROR);

        assertEquals(0, ConversationPresence.size(), "a broken hook cannot strand the villager");
        assertTrue(ConversationPresence.ofVillager(VILLAGER).isEmpty());
        assertEquals(0, ConversationSessions.size());
        assertFalse(VillagerAttention.activeHolds().containsKey(VILLAGER));

        // And the pair can immediately start over, which is the point of releasing at all.
        ConversationHandle next = begin(PLAYER, VILLAGER);
        assertTrue(ConversationPresence.ownsVillager(next));
    }

    @Test
    @DisplayName("player A's ending never releases the villager player B has taken over")
    void aRetiredOwnerCannotReleaseTheNewOwnersHold() {
        ConversationHandle first = begin(PLAYER, VILLAGER);
        ConversationHandle second = ConversationLifecycle.handoff(
                first, OTHER_PLAYER, DIM, ConversationSession.Frontend.GUI, 200);
        VillagerAttention.hold(VILLAGER, OTHER_PLAYER, 2_000, AttentionLedger.Source.CONVERSATION, second);

        assertTrue(ConversationLifecycle.terminate(first, CloseReason.CLIENT_CLOSED).isEmpty(),
                "the taken-over handle was already retired by the handoff");

        assertTrue(ConversationPresence.ownsVillager(second));
        AttentionLedger.Hold hold = VillagerAttention.activeHolds().get(VILLAGER);
        assertEquals(OTHER_PLAYER, hold.playerId(), "the villager keeps facing whoever owns it now");
        assertEquals(second, hold.owner());
    }

    @Test
    @DisplayName("turning to another villager ends the first discussion and starts a second")
    void switchingTargetRetiresThePreviousHandle() {
        ConversationHandle first = begin(PLAYER, VILLAGER);
        ConversationHandle second = begin(PLAYER, OTHER_VILLAGER);

        assertFalse(ConversationPresence.isCurrent(first));
        assertTrue(ConversationPresence.ownsPlayer(second));
        assertTrue(ConversationPresence.ofVillager(VILLAGER).isEmpty(), "the first villager is free");
        assertFalse(VillagerAttention.activeHolds().containsKey(VILLAGER));
        assertTrue(VillagerAttention.activeHolds().containsKey(OTHER_VILLAGER));
    }

    @Test
    @DisplayName("reopening the exchange already in progress keeps the same handle and offer")
    void beginningTheSameExchangeTwiceIsOneDiscussion() {
        ConversationHandle handle = begin(PLAYER, VILLAGER);
        ConversationSession.ChoiceOffer offer = ConversationSessions.recordOffer(
                PLAYER, VILLAGER, "conversations.q", java.util.List.of("yes", "no"),
                ConversationSession.Frontend.GUI, 100);

        ConversationHandle again = ConversationLifecycle
                .begin(PLAYER, VILLAGER, DIM, ConversationSession.Frontend.GUI, 110).orElseThrow();

        assertSame(handle, again, "a say-only reply or a reopened screen is the same discussion");
        assertEquals(offer.revision(),
                ConversationSessions.raw(PLAYER).orElseThrow().currentOffer().orElseThrow().revision(),
                "re-minting would have retired the offer the player is looking at");
    }

    @Test
    @DisplayName("a handle from a previous server lifetime is never equal to a new one")
    void anEpochChangeInvalidatesEveryOldHandle() {
        ConversationHandle before = begin(PLAYER, VILLAGER);
        ConversationLifecycle.reset();

        assertEquals(0, ConversationPresence.size());
        ConversationHandle after = begin(PLAYER, VILLAGER);
        assertNotEquals(before, after);
        assertTrue(after.epoch() > before.epoch(), "the epoch advances with the server lifetime");
        assertTrue(ConversationLifecycle.terminate(before, CloseReason.DISCONNECTED).isEmpty(),
                "last world's close cannot end this world's discussion");
        assertTrue(ConversationPresence.ownsPlayer(after));
    }

    @Test
    @DisplayName("closing a player's session goes through their handle")
    void closeDelegatesToTheCoordinator() {
        ConversationHandle handle = begin(PLAYER, VILLAGER);
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);

        ConversationSession closed = ConversationSessions
                .close(PLAYER, CloseReason.DISCONNECTED).orElseThrow();

        assertEquals(CloseReason.DISCONNECTED, closed.lastCloseReason().orElse(null));
        assertFalse(ConversationPresence.isCurrent(handle));
        assertEquals(0, ConversationSessions.size());
        assertFalse(VillagerAttention.activeHolds().containsKey(VILLAGER));
    }

    // Takeover ---------------------------------------------------------------------------------

    @Test
    @DisplayName("a second player's interaction takes the villager over, and the hold never lapses")
    void anAcceptedInteractionTakesTheVillagerOver() {
        ConversationHandle first = begin(PLAYER, VILLAGER);
        ConversationSessions.beginTopic(PLAYER, VILLAGER, "day", DepthClass.QUICK, 100);
        ChatModeScheduler.scheduleOrdered(PLAYER, 400, () -> { });

        // What the first player's teardown sees while it is running. A gap here — one tick with the
        // villager unheld — is the whole failure this ordering exists to prevent, and it is the only
        // moment it could ever appear, so it is observed from inside the teardown itself.
        AttentionLedger.Hold[] duringTeardown = new AttentionLedger.Hold[1];
        CloseReason[] toldWhy = new CloseReason[1];
        ConversationLifecycle.addTeardownHook((handle, reason) -> {
            duringTeardown[0] = VillagerAttention.activeHolds().get(VILLAGER);
            toldWhy[0] = reason;
        });

        ConversationHandle second = ConversationLifecycle
                .begin(OTHER_PLAYER, VILLAGER, DIM, ConversationSession.Frontend.GUI, 200).orElseThrow();

        assertNotEquals(first, second);
        assertEquals(CloseReason.TAKEN_OVER, toldWhy[0],
                "the first player's window is told why it closed");
        assertNotNull(duringTeardown[0], "the villager is never unheld between the two owners");
        assertEquals(second, duringTeardown[0].owner(),
                "and the hold already belongs to the player taking over");
        assertTrue(duringTeardown[0].ownsMovement(), "which is the hold that stops them walking");

        assertTrue(ConversationPresence.ownsVillager(second));
        assertTrue(ConversationPresence.ownsPlayer(second));
        assertTrue(ConversationPresence.ofPlayer(PLAYER).isEmpty(), "the first discussion is over");
        assertEquals(OTHER_PLAYER, VillagerAttention.activeHolds().get(VILLAGER).playerId(),
                "the villager turns to whoever owns them now");
        assertEquals(0, ChatModeScheduler.pendingFor(PLAYER),
                "and nothing the first discussion queued survives it");
        assertEquals(Optional.of(second), ConversationSessions.raw(OTHER_PLAYER).orElseThrow().handle());
        assertTrue(ConversationSessions.raw(PLAYER).isEmpty(),
                "the second player inherits no thread of the first player's");
    }

    @Test
    @DisplayName("three players in sequence: only the last one owns the villager, its hold and its session")
    void threePlayersInSequenceLeaveOneOwner() {
        ConversationHandle a = begin(PLAYER, VILLAGER);
        ConversationHandle b = ConversationLifecycle
                .begin(OTHER_PLAYER, VILLAGER, DIM, ConversationSession.Frontend.GUI, 200).orElseThrow();
        ConversationHandle c = ConversationLifecycle
                .begin(THIRD_PLAYER, VILLAGER, DIM, ConversationSession.Frontend.GUI, 300).orElseThrow();

        assertEquals(c, ConversationPresence.ofVillager(VILLAGER).orElseThrow());
        assertTrue(ConversationPresence.ofPlayer(PLAYER).isEmpty());
        assertTrue(ConversationPresence.ofPlayer(OTHER_PLAYER).isEmpty());
        assertEquals(1, ConversationPresence.size(), "one owner per villager, never three");

        AttentionLedger.Hold hold = VillagerAttention.activeHolds().get(VILLAGER);
        assertEquals(c, hold.owner());
        assertEquals(THIRD_PLAYER, hold.playerId());

        // Neither predecessor can reach the survivor, through any door.
        assertTrue(ConversationLifecycle.terminate(a, CloseReason.CLIENT_CLOSED).isEmpty());
        assertTrue(ConversationLifecycle.terminate(b, CloseReason.CLIENT_CLOSED).isEmpty());
        assertEquals(c, ConversationPresence.ofVillager(VILLAGER).orElseThrow());
        assertEquals(c, VillagerAttention.activeHolds().get(VILLAGER).owner(),
                "a predecessor's close never hands back the current owner's villager");
    }

    @Test
    @DisplayName("interacting again as the current owner does not mint a second discussion")
    void aDuplicateInteractFromTheOwnerChangesNothing() {
        ConversationHandle owner = begin(PLAYER, VILLAGER);
        int allowanceAfterTheOpen = OpenRateLimiter.remaining(PLAYER, VILLAGER, 100);

        ConversationHandle again = ConversationLifecycle
                .begin(PLAYER, VILLAGER, DIM, ConversationSession.Frontend.GUI, 101).orElseThrow();

        assertSame(owner, again, "a second right-click on the villager you are talking to is one discussion");
        assertEquals(allowanceAfterTheOpen, OpenRateLimiter.remaining(PLAYER, VILLAGER, 101),
                "and it is not charged as an open, because it opened nothing");
        assertEquals(owner, VillagerAttention.activeHolds().get(VILLAGER).owner());
    }

    @Test
    @DisplayName("closing and immediately walking back up mints a new discussion, and the old close is a no-op")
    void aRapidReopenIsANewDiscussionNotAHandoff() {
        ConversationHandle first = begin(PLAYER, VILLAGER);
        assertTrue(ConversationLifecycle
                .terminateIfCurrent(PLAYER, first.sessionId(), VILLAGER, CloseReason.CLIENT_CLOSED)
                .isPresent());

        // Same tick, same pair: a reopen, never a takeover — there is nobody to take it over from.
        ConversationHandle second = begin(PLAYER, VILLAGER);
        assertNotEquals(first, second);
        assertEquals(PLAYER, second.playerId(), "the same player is still the owner");
        assertEquals(second, VillagerAttention.activeHolds().get(VILLAGER).owner());

        // The window that was dismissed a moment ago finally reports itself closed.
        assertTrue(ConversationLifecycle
                .terminateIfCurrent(PLAYER, first.sessionId(), VILLAGER, CloseReason.CLIENT_CLOSED)
                .isEmpty(), "a close naming the dismissed discussion never closes its replacement");
        assertTrue(ConversationPresence.ownsPlayer(second));
        assertTrue(ConversationPresence.ownsVillager(second));
    }

    @Test
    @DisplayName("repeated opens of one pair are refused for the rest of the window, and only that pair")
    void repeatedOpensOfOnePairAreRateLimited() {
        for (int open = 0; open < OpenRateLimiter.BURST; open++) {
            ConversationHandle handle = ConversationLifecycle
                    .begin(PLAYER, VILLAGER, DIM, ConversationSession.Frontend.GUI, 100).orElseThrow();
            ConversationLifecycle.terminate(handle, CloseReason.CLIENT_CLOSED);
        }

        // A discussion with somebody else is a different pair and is unaffected by the flicker.
        ConversationHandle elsewhere = ConversationLifecycle
                .begin(PLAYER, OTHER_VILLAGER, DIM, ConversationSession.Frontend.GUI, 101).orElseThrow();

        assertTrue(ConversationLifecycle
                .begin(PLAYER, VILLAGER, DIM, ConversationSession.Frontend.GUI, 101).isEmpty(),
                "the pair has had its opens for this window");
        assertTrue(ConversationPresence.ownsPlayer(elsewhere),
                "and a refused open tears nothing down: the live discussion is exactly as it was");
        assertTrue(ConversationPresence.ownsVillager(elsewhere));

        // Once the window has rolled over the same interaction is ordinary again.
        ConversationHandle later = ConversationLifecycle
                .begin(PLAYER, VILLAGER, DIM, ConversationSession.Frontend.GUI,
                        100 + OpenRateLimiter.WINDOW_TICKS).orElseThrow();
        assertTrue(ConversationPresence.ownsVillager(later));
        assertFalse(ConversationPresence.isCurrent(elsewhere),
                "and it ends the discussion it replaced, the ordinary way");
    }
}
