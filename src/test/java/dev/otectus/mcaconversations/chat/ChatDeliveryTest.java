package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.locale.LineVoice;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The chat line template substitution must keep the villager-line {@link Component} intact (never
 * flatten it to a string) so client-side lang/personality styling survives. These tests assert the
 * rendered plain text; the point is that {@code %2$s} is spliced as a live component, not formatted.
 */
class ChatDeliveryTest {

    @Test
    void defaultTemplate_wrapsNameInAngleBrackets() {
        MutableComponent out = ChatDelivery.applyFormat("<%1$s> %2$s",
                Component.literal("Agnes"), Component.literal("Good morning."));
        assertEquals("<Agnes> Good morning.", out.getString());
    }

    @Test
    void colonTemplate_isSupported() {
        MutableComponent out = ChatDelivery.applyFormat("%1$s: %2$s",
                Component.literal("Ilsa"), Component.literal("Hm."));
        assertEquals("Ilsa: Hm.", out.getString());
    }

    @Test
    void reversedPlaceholders_areHonored() {
        MutableComponent out = ChatDelivery.applyFormat("%2$s — %1$s",
                Component.literal("Agnes"), Component.literal("Leave me be"));
        assertEquals("Leave me be — Agnes", out.getString());
    }

    @Test
    void templateWithoutPlaceholders_isLiteral() {
        MutableComponent out = ChatDelivery.applyFormat("static text",
                Component.literal("Agnes"), Component.literal("hi"));
        assertEquals("static text", out.getString());
    }

    @Test
    void lineComponentIsPreserved_notFlattened() {
        Component line = Component.literal("styled");
        MutableComponent out = ChatDelivery.applyFormat("<%1$s> %2$s", Component.literal("Agnes"), line);
        // The exact line component instance is appended as a sibling, not stringified.
        assertEquals(line, out.getSiblings().get(out.getSiblings().size() - 1));
    }

    // --- Heart-change feedback suffix (Phase 4, chatModeShowHeartChanges) ---------

    @Test
    void zeroHeartsDeltaIsSilent() {
        assertEquals("", ChatDelivery.heartsSuffix(0));
    }

    @Test
    void positiveHeartsDeltaGetsPlusHeart() {
        assertEquals(" (+2 ♥)", ChatDelivery.heartsSuffix(2));
        assertEquals(" (+1 ♥)", ChatDelivery.heartsSuffix(1));
    }

    @Test
    void negativeHeartsDeltaGetsMinusHeart() {
        assertEquals(" (−3 ♥)", ChatDelivery.heartsSuffix(-3));
    }

    // --- Marker stripping (server-side variant pinning) --------------------------

    @Test
    void allFourMarkersAreStripped() {
        assertEquals("dialogue.conversations.work_offer.ask_terms",
                LineVoice.stripMarkers(
                        "#Gmale.#Ecrabby.#Pfarmer.#Tadult.dialogue.conversations.work_offer.ask_terms"));
    }

    @Test
    void aPartialMarkerSetIsStripped() {
        // gender, profession and personality are all optional in MCA's getTranslatable.
        assertEquals("dialogue.chatmode.confused",
                LineVoice.stripMarkers("#Tadult.dialogue.chatmode.confused"));
    }

    @Test
    void anUnmarkedKeyIsUnchanged() {
        // ChatModeDispatcher's fallback builds a bare translatable when MCA has no line.
        assertEquals("dialogue.chatmode.hint", LineVoice.stripMarkers("dialogue.chatmode.hint"));
    }

    @Test
    void aMalformedMarkerLeavesTheKeyAlone() {
        assertEquals("#Tadult", LineVoice.stripMarkers("#Tadult"));
    }

    @Test
    void keyBodyKeepsItsOwnDots() {
        assertEquals("dialogue.a.b.c", LineVoice.stripMarkers("#Eodd.dialogue.a.b.c"));
    }

    // --- Reply-delay length ------------------------------------------------------

    @Test
    void aResolvedSentenceIsMeasuredAsItself() {
        assertEquals(16, ChatDelivery.typedLength("Good morning, %s"));
    }

    @Test
    void anUnresolvedLangKeyFallsBackToTheNominalLength() {
        // A dedicated server never mounts assets/, so getString() hands back the key itself; scaling
        // the delay by key length would make it track spelling rather than typing time.
        assertEquals(ChatDelivery.NOMINAL_LINE_LENGTH,
                ChatDelivery.typedLength("dialogue.conversations.work_offer.ask_terms"));
        assertEquals(ChatDelivery.NOMINAL_LINE_LENGTH,
                ChatDelivery.typedLength("#Gmale.#Tadult.dialogue.conversations.greet"));
        assertEquals(ChatDelivery.NOMINAL_LINE_LENGTH, ChatDelivery.typedLength(""));
        assertEquals(ChatDelivery.NOMINAL_LINE_LENGTH, ChatDelivery.typedLength(null));
    }

    @Test
    void publicRepliesDoNotAnnounceConfidencesOrPersonalDisclosures() {
        for (var privacy : dev.otectus.mcaconversations.history.PrivacyLevel.values()) {
            assertEquals(privacy == dev.otectus.mcaconversations.history.PrivacyLevel.PUBLIC,
                    ChatDelivery.mayBroadcast(privacy, dev.otectus.mcaconversations.conversation.NpcSpeechAct.REPORT));
        }
        assertEquals(false, ChatDelivery.mayBroadcast(null,
                dev.otectus.mcaconversations.conversation.NpcSpeechAct.DISCLOSE));
        assertEquals(false, ChatDelivery.mayBroadcast(null,
                dev.otectus.mcaconversations.conversation.NpcSpeechAct.DISCLOSE_PROBLEM));
        assertEquals(true, ChatDelivery.mayBroadcast(null,
                dev.otectus.mcaconversations.conversation.NpcSpeechAct.REPORT));
    }


    @Test
    void clarificationUsesTheActualContextualAnswerLabel() {
        var choice = new IntentMatcher.Scored("q.ask", 0.9, "conversations.q", "ask", null, "topics", true);
        assertEquals("dialogue.conversations.q.ask", ChatModeDispatcher.topicName(choice).getString());
    }

    // --- Delivery-boundary hook -----------------------------------------------
    //
    // deliver() itself needs live entities, so what is covered here is the plumbing either side of
    // it: a hook parked on the open scope runs exactly once when a delivered line fires it, and
    // never when the scope closes with a line that was scheduled but dropped.

    @Test
    void deliveryHook_firesOnceOnTheFirstDeliveredLine() {
        int[] fired = {0};
        try (ChatModeSession.Scope scope = ChatModeSession.open(null, null, 0)) {
            assertTrue(ChatModeSession.deferUntilDelivered(null, () -> fired[0]++));
            scope.linesScheduled = 2;
            scope.fireDelivered();
            scope.fireDelivered();  // the turn's second line finds the bookkeeping already done
        }
        assertEquals(1, fired[0]);
    }

    @Test
    void deliveryHook_doesNotFireWhenTheScheduledLineIsDropped() {
        int[] fired = {0};
        try (ChatModeSession.Scope scope = ChatModeSession.open(null, null, 0)) {
            assertTrue(ChatModeSession.deferUntilDelivered(null, () -> fired[0]++));
            scope.linesScheduled = 1;  // scheduled, then dropped before delivery: never fired
        }
        assertEquals(0, fired[0]);
    }

    @Test
    void deliveryHook_fallsBackToTurnTimeWhenTheTurnSaysNothing() {
        int[] fired = {0};
        try (ChatModeSession.Scope scope = ChatModeSession.open(null, null, 0)) {
            assertTrue(ChatModeSession.deferUntilDelivered(null, () -> fired[0]++));
        }
        assertEquals(1, fired[0], "no line was ever scheduled, so there is no delivery to wait for");
    }

    @Test
    void deliveryHook_isRefusedWhenNoScopeIsOpen() {
        assertFalse(ChatModeSession.deferUntilDelivered(null, () -> { }));
    }
}
