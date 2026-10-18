package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import dev.otectus.mcaconversations.network.ConversationRef;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The server half of the utterance contract (feedback remediation WP-A1, WP-A2). */
class ConversationSessionUtteranceTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("utterance-player".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("utterance-villager-2".getBytes());

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

    private static ConversationHandle begin() {
        return ConversationLifecycle.begin(PLAYER, VILLAGER, "minecraft:overworld",
                ConversationSession.Frontend.GUI, 100L).orElseThrow();
    }

    @Test
    void sequenceNumbersAreMonotonicAndTheRingIsBounded() {
        ConversationSession session = ConversationSessions.get(PLAYER, 0L);
        long previous = 0L;
        for (int i = 0; i < ConversationSession.MAX_UTTERANCES * 3; i++) {
            Utterance said = session.recordUtterance(UtteranceTag.Kind.NPC_LINE,
                    ConversationSession.Frontend.GUI, "dialogue.conversations.line/" + (i % 3 + 1));
            assertTrue(said.seq() > previous);
            previous = said.seq();
        }
        assertEquals(ConversationSession.MAX_UTTERANCES, session.recentUtterances().size());
        assertEquals(previous, session.lastUtteranceSeq(ConversationSession.Frontend.GUI));
    }

    @Test
    void theIdentityIsTheHandleWhenThereIsOneAndTheNonceOtherwise() {
        ConversationSession ambient = ConversationSessions.get(PLAYER, 0L);
        assertEquals(ambient.sessionNonce(), ambient.utteranceIdentity());
        ConversationHandle handle = begin();
        ConversationSession managed = ConversationSessions.raw(PLAYER).orElseThrow();
        assertEquals(handle.sessionId(), managed.utteranceIdentity());
    }

    @Test
    void theLastLineIsPerFrontendAndIgnoresFeedback() {
        ConversationSession session = ConversationSessions.get(PLAYER, 0L);
        Utterance gui = session.recordUtterance(UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.GUI, "a");
        session.recordUtterance(UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.CHAT, "b");
        session.recordUtterance(UtteranceTag.Kind.FEEDBACK, ConversationSession.Frontend.GUI, "");
        assertEquals(gui.seq(), session.lastUtteranceSeq(ConversationSession.Frontend.GUI),
                "a chat line and an options block are not what the card shows");
    }

    @Test
    void aLineFromThePreviousDiscussionIsNeverNamed() {
        ConversationSession session = ConversationSessions.get(PLAYER, 0L);
        session.recordUtterance(UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.GUI, "before");
        begin(); // a handle is attached: the identity changes
        ConversationSession managed = ConversationSessions.raw(PLAYER).orElseThrow();
        assertEquals(-1L, managed.lastUtteranceSeq(ConversationSession.Frontend.GUI));
        Utterance now = managed.recordUtterance(UtteranceTag.Kind.SILENT_PROMPT,
                ConversationSession.Frontend.GUI, "dialogue.conversations.family");
        assertEquals(now.seq(), managed.lastUtteranceSeq(ConversationSession.Frontend.GUI));
        assertTrue(now.silent());
    }

    @Test
    void anOfferNamesTheLineOnlyForItsOwnDiscussion() {
        ConversationHandle handle = begin();
        ConversationSession session = ConversationSessions.raw(PLAYER).orElseThrow();
        Utterance said = session.recordUtterance(UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.GUI,
                "dialogue.conversations.topic/2");
        assertEquals(said.seq(), OfferDispatch.namedLine(session, ConversationRef.of(handle)));
        assertEquals(-1L, OfferDispatch.namedLine(session,
                new ConversationRef(UUID.randomUUID(), VILLAGER)), "another discussion's ref names nothing");
        assertEquals(-1L, OfferDispatch.namedLine(session, ConversationRef.NONE));
    }

    @Test
    void onlyAnIdentifiedGraphicalOfferIsHeldForTheEndOfItsTurn() {
        ConversationSession.ChoiceOffer gui = new ConversationSession.ChoiceOffer(1L, VILLAGER, "q",
                List.of("a"), ConversationSession.Frontend.GUI, 0L, false, 0L);
        ConversationSession.ChoiceOffer chat = new ConversationSession.ChoiceOffer(2L, VILLAGER, "q",
                List.of("a"), ConversationSession.Frontend.CHAT, 0L, false, 0L);
        ConversationRef identified = new ConversationRef(UUID.randomUUID(), VILLAGER);
        assertTrue(OfferDispatch.holdable(identified, gui));
        assertFalse(OfferDispatch.holdable(identified, chat), "chat has no card to gate");
        assertFalse(OfferDispatch.holdable(ConversationRef.ofVillager(VILLAGER), gui),
                "an unmanaged page keeps protocol 4 behaviour: sent at once, no claim");
    }

    @Test
    void bindingTheOfferRecordsTheLineAndIgnoresAStaleRevision() {
        ConversationHandle handle = begin();
        ConversationSession session = ConversationSessions.raw(PLAYER).orElseThrow();
        Utterance said = session.recordUtterance(UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.GUI, "x");
        ConversationSession.ChoiceOffer offer = session.setOffer("q", List.of("a"),
                ConversationSession.Frontend.GUI, handle.villagerId(), 10L);
        session.bindOfferUtterance(offer.revision() - 1L, said.seq());
        assertEquals(-1L, session.currentOffer().orElseThrow().utteranceSeq(), "a stale revision binds nothing");
        session.bindOfferUtterance(offer.revision(), said.seq());
        assertEquals(said.seq(), session.currentOffer().orElseThrow().utteranceSeq());
        assertEquals(offer.revision(), session.recentUtterances().get(0).offerRevision());
        // A re-offer under a fresh revision starts unbound, and names the current line when sent.
        ConversationSession.ChoiceOffer next = session.setOffer("q", List.of("a"),
                ConversationSession.Frontend.GUI, handle.villagerId(), 11L);
        assertNotEquals(offer.revision(), next.revision());
        assertEquals(-1L, next.utteranceSeq());
        assertEquals(said.seq(), OfferDispatch.namedLine(session, ConversationRef.of(handle)));
    }

    @Test
    void turnsNestAndOnlyTheOutermostCloses() {
        assertFalse(OfferDispatch.inTurn());
        try (OfferDispatch.Turn outer = OfferDispatch.openTurn()) {
            assertTrue(OfferDispatch.inTurn());
            try (OfferDispatch.Turn inner = OfferDispatch.openTurn()) {
                assertTrue(OfferDispatch.inTurn());
            }
            assertTrue(OfferDispatch.inTurn(), "an inner close keeps the outer turn open");
        }
        assertFalse(OfferDispatch.inTurn());
    }

    @Test
    void variantsAreReadFromTheKey() {
        assertEquals(3, Utterance.variantOf("dialogue.conversations.topic/3"));
        assertEquals(0, Utterance.variantOf("dialogue.conversations.topic"));
        assertEquals(0, Utterance.variantOf("dialogue.conversations.topic/x"));
        assertEquals(0, Utterance.variantOf(null));
    }
}
