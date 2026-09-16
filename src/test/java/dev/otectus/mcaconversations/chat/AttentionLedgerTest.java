package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.AttentionLedger.Hold;
import dev.otectus.mcaconversations.chat.AttentionLedger.Source;
import dev.otectus.mcaconversations.conversation.ConversationHandle;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Attention bookkeeping: precedence, expiry, and scoped releases — by source (typing vs
 * conversation) and by owner (which discussion booked the hold).
 */
class AttentionLedgerTest {

    private static final UUID V1 = UUID.nameUUIDFromBytes("villager-1".getBytes());
    private static final UUID V2 = UUID.nameUUIDFromBytes("villager-2".getBytes());
    private static final UUID P1 = UUID.nameUUIDFromBytes("player-1".getBytes());
    private static final UUID P2 = UUID.nameUUIDFromBytes("player-2".getBytes());

    @Test
    void typingNeverDowngradesAConversation() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P1, 1000, Source.CONVERSATION);
        ledger.hold(V1, P2, 2000, Source.TYPING); // later deadline, lower rank — must lose
        Hold h = ledger.activeHolds().get(V1);
        assertEquals(Source.CONVERSATION, h.source());
        assertEquals(P1, h.playerId());
        assertEquals(1000, h.untilTick());
    }

    @Test
    void conversationTakesOverATypingGlance() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P1, 500, Source.TYPING);
        ledger.hold(V1, P2, 400, Source.CONVERSATION); // earlier deadline but higher rank — wins
        Hold h = ledger.activeHolds().get(V1);
        assertEquals(Source.CONVERSATION, h.source());
        assertEquals(P2, h.playerId());
    }

    @Test
    void sameSourceOnlyExtendsNeverShortens() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P1, 1000, Source.CONVERSATION);
        ledger.hold(V1, P1, 800, Source.CONVERSATION); // earlier — ignored
        assertEquals(1000, ledger.activeHolds().get(V1).untilTick());
        ledger.hold(V1, P1, 1500, Source.CONVERSATION); // later — extends
        assertEquals(1500, ledger.activeHolds().get(V1).untilTick());
    }

    @Test
    void sweepDropsExpiredHoldsOnly() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P1, 100, Source.TYPING);
        ledger.hold(V2, P1, 200, Source.CONVERSATION);
        ledger.sweep(150);
        assertNull(ledger.activeHolds().get(V1));
        assertTrue(ledger.activeHolds().containsKey(V2));
    }

    @Test
    void releaseTypingLeavesConversationsAlone() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P1, 1000, Source.TYPING);
        ledger.hold(V2, P1, 1000, Source.CONVERSATION);
        ledger.releaseTyping(P1);
        assertFalse(ledger.activeHolds().containsKey(V1), "typing glance released on chat close");
        assertTrue(ledger.activeHolds().containsKey(V2), "conversation partner keeps attending");
    }

    @Test
    void releaseTypingIsPerPlayer() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P1, 1000, Source.TYPING);
        ledger.hold(V2, P2, 1000, Source.TYPING);
        ledger.releaseTyping(P1);
        assertFalse(ledger.activeHolds().containsKey(V1));
        assertTrue(ledger.activeHolds().containsKey(V2), "other players' typing holds survive");
    }

    @Test
    void releasePlayerDropsEverythingForThatPlayer() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P1, 1000, Source.CONVERSATION);
        ledger.hold(V2, P2, 1000, Source.CONVERSATION);
        ledger.releasePlayer(P1);
        assertFalse(ledger.activeHolds().containsKey(V1));
        assertTrue(ledger.activeHolds().containsKey(V2));
    }

    private static ConversationHandle handleFor(UUID player, UUID villager) {
        return ConversationHandle.mint(player, villager, "minecraft:overworld",
                ConversationSession.Frontend.GUI);
    }

    @Test
    void onlyTheDiscussionThatBookedAHoldMayReleaseIt() {
        AttentionLedger ledger = new AttentionLedger();
        ConversationHandle owner = handleFor(P1, V1);
        ConversationHandle stale = handleFor(P1, V1); // same pair, earlier discussion
        ledger.hold(V1, P1, 1000, Source.CONVERSATION, owner);

        assertFalse(ledger.releaseIfOwned(V1, stale), "a retired discussion releases nothing");
        assertTrue(ledger.activeHolds().containsKey(V1));
        assertTrue(ledger.releaseIfOwned(V1, owner));
        assertFalse(ledger.activeHolds().containsKey(V1));
    }

    @Test
    void anotherPlayersHoldSurvivesThisPlayersEnding() {
        AttentionLedger ledger = new AttentionLedger();
        ConversationHandle mine = handleFor(P1, V1);
        ledger.hold(V1, P2, 1000, Source.CONVERSATION, handleFor(P2, V1)); // P2 took the villager over

        assertFalse(ledger.releaseIfOwned(V1, mine), "a villager somebody else owns is not mine to free");
        assertFalse(ledger.releaseIfOwned(V1, P1));
        assertEquals(P2, ledger.activeHolds().get(V1).playerId());
    }

    @Test
    void anUnownedHoldIsReleasedByTheSamePlayersDiscussion() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P1, 1000, Source.CONVERSATION); // a chat exchange, no handle minted
        assertNull(ledger.activeHolds().get(V1).owner());
        assertTrue(ledger.releaseIfOwned(V1, handleFor(P1, V1)),
                "the player's own ending still ends the hold it booked without a handle");
        assertFalse(ledger.activeHolds().containsKey(V1));
    }

    @Test
    void unconditionalReleaseRemainsForTheVillagerItselfGoingAway() {
        AttentionLedger ledger = new AttentionLedger();
        ledger.hold(V1, P2, 1000, Source.CONVERSATION, handleFor(P2, V1));
        ledger.release(V1); // death / removal / server stop: nobody is talking to a gone villager
        assertFalse(ledger.activeHolds().containsKey(V1));
    }
}
