package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.UtteranceTag;
import net.minecraft.network.chat.Component;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The client ledger: bounded, keyed by identity, cleared with the connection (WP-A3). */
class ClientUtteranceLedgerTest {

    private static final UUID CONVERSATION = UUID.nameUUIDFromBytes("ledger-conversation".getBytes());

    private static ClientUtteranceLedger ledger() {
        return new ClientUtteranceLedger(Component::getString);
    }

    private static UtteranceTag.Id id(long seq) {
        return new UtteranceTag.Id(CONVERSATION, seq, UtteranceTag.Kind.NPC_LINE, null);
    }

    @Test
    void oneUtteranceOnThreeSurfacesIsOneEntry() {
        ClientUtteranceLedger ledger = ledger();
        Component line = Component.literal("Pretty good!");
        ledger.record(id(1L), line, ClientUtteranceLedger.Surface.SCREEN, 10L);
        ledger.record(id(1L), Component.literal("ignored second instance"), ClientUtteranceLedger.Surface.CHAT, 10L);
        ledger.record(id(1L), line, ClientUtteranceLedger.Surface.SPEECH, 11L);
        assertEquals(1, ledger.size());
        ClientUtteranceLedger.Entry entry = ledger.entry(id(1L)).orElseThrow();
        assertEquals("Pretty good!", entry.speechText(), "the first instance is the one kept");
        assertTrue(entry.seenBy(ClientUtteranceLedger.Surface.SCREEN));
        assertTrue(entry.seenBy(ClientUtteranceLedger.Surface.CHAT));
        assertTrue(entry.seenBy(ClientUtteranceLedger.Surface.SPEECH));
    }

    @Test
    void twoIdenticalSentencesAreTwoEntries() {
        ClientUtteranceLedger ledger = ledger();
        ledger.record(id(1L), Component.literal("Again?"), ClientUtteranceLedger.Surface.CHAT, 1L);
        ledger.record(id(2L), Component.literal("Again?"), ClientUtteranceLedger.Surface.CHAT, 2L);
        assertEquals(2, ledger.size());
    }

    @Test
    void theLedgerIsBounded() {
        ClientUtteranceLedger ledger = ledger();
        for (long seq = 1; seq <= ClientUtteranceLedger.CAPACITY + 5; seq++) {
            ledger.record(id(seq), Component.literal("line " + seq), ClientUtteranceLedger.Surface.CHAT, seq);
        }
        assertEquals(ClientUtteranceLedger.CAPACITY, ledger.size());
        assertTrue(ledger.entry(id(1L)).isEmpty(), "the oldest went first");
        assertEquals(ClientUtteranceLedger.CAPACITY + 5L, ledger.recent(1).get(0).id().seq());
    }

    @Test
    void speechIsClaimedOncePerUtteranceNeverPerText() {
        ClientUtteranceLedger ledger = ledger();
        assertTrue(ledger.claimSpeech(id(3L), Component.literal("Hello."), 1L));
        assertFalse(ledger.claimSpeech(id(3L), Component.literal("Hello."), 2L), "a repeat of one utterance");
        assertTrue(ledger.claimSpeech(id(4L), Component.literal("Hello."), 3L), "the same words, said again");
    }

    @Test
    void theScreenSightingIsWhatTheAgreementReads() {
        ClientUtteranceLedger ledger = ledger();
        assertTrue(ledger.lastDisplayed().isEmpty());
        ledger.record(id(5L), Component.literal("x"), ClientUtteranceLedger.Surface.CHAT, 1L);
        assertTrue(ledger.lastDisplayed().isEmpty(), "a chat sighting is not what the card shows");
        ledger.record(id(6L), Component.literal("y"), ClientUtteranceLedger.Surface.SCREEN, 2L);
        assertEquals(6L, ledger.lastDisplayed().orElseThrow().seq());
        ledger.displayedUntagged();
        assertFalse(ledger.lastDisplayed().orElseThrow().tagged());
        ledger.screenOpened();
        assertTrue(ledger.lastDisplayed().isEmpty(), "a new screen inherits nothing");
        ledger.clear();
        assertEquals(0, ledger.size());
    }
}
