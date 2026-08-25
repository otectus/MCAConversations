package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.AttentionLedger.Hold;
import dev.otectus.mcaconversations.chat.AttentionLedger.Source;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Attention bookkeeping: precedence, expiry, and scoped releases (typing vs conversation). */
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
}
