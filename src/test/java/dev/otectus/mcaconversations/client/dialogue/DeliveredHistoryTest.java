package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import net.minecraft.network.chat.Component;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The delivered history's three promises: only what arrived, only once, and never unbounded.
 *
 * <p>A line the server dropped or queued has no recording point on this client at all, so the case
 * asserted here is the one that could go wrong in code: two deliveries of the same utterance must
 * still be one entry, and the oldest entry must leave when the bound is reached.
 */
class DeliveredHistoryTest {

    private static DeliveredLine spoken(String identity, String text) {
        return DeliveredLine.spoken(identity, Component.literal("Anna"), Component.literal(text));
    }

    @Test
    void oneUtteranceDeliveredTwiceIsOneEntry() {
        DeliveredHistory history = new DeliveredHistory(8);
        assertTrue(history.record(spoken("spoken:a:1", "Good morning.")));
        assertFalse(history.record(spoken("spoken:a:1", "Good morning.")),
                "the same delivery reaching the card and the chat log is one event");
        assertEquals(1, history.size());
    }

    @Test
    void theOldestEntryLeavesWhenTheBoundIsReached() {
        DeliveredHistory history = new DeliveredHistory(3);
        for (int i = 0; i < 5; i++) {
            history.record(spoken("spoken:a:" + i, "line " + i));
        }
        List<DeliveredLine> entries = history.entries();
        assertEquals(3, entries.size());
        assertEquals("spoken:a:2", entries.get(0).identity());
        assertEquals("spoken:a:4", entries.get(2).identity());
    }

    @Test
    void aCapOfZeroRecordsNothingAtAll() {
        DeliveredHistory history = new DeliveredHistory(0);
        assertFalse(history.enabled());
        assertFalse(history.record(spoken("spoken:a:1", "Good morning.")));
        assertTrue(history.isEmpty());
    }

    @Test
    void aRefusedReplyKeepsTheReasonItWasRefusedFor() {
        DeliveredHistory history = new DeliveredHistory(8);
        history.record(DeliveredLine.submitted("response:7:2", Component.literal("Tell me more.")));
        assertTrue(history.resolveAll("response:7:", DeliveredLine.Status.REFUSED,
                ChoiceClearS2C.Reason.SPEAKER_UNAVAILABLE));
        DeliveredLine entry = history.entries().get(0);
        assertEquals(DeliveredLine.Status.REFUSED, entry.status());
        assertEquals(ChoiceClearS2C.Reason.SPEAKER_UNAVAILABLE, entry.refusal());
    }

    @Test
    void anOutcomeIsAppliedOnceAndNeverRewritten() {
        DeliveredHistory history = new DeliveredHistory(8);
        history.record(DeliveredLine.submitted("response:7:2", Component.literal("Tell me more.")));
        assertTrue(history.resolveAll("response:7:", DeliveredLine.Status.ACCEPTED, null));
        assertFalse(history.resolveAll("response:7:", DeliveredLine.Status.REFUSED,
                ChoiceClearS2C.Reason.EXPIRED),
                "a decided reply is not re-decided by a later, unrelated clear");
        assertEquals(DeliveredLine.Status.ACCEPTED, history.entries().get(0).status());
    }

    @Test
    void aReceivedLineNeverCarriesAReplyStatus() {
        DeliveredLine line = new DeliveredLine(DeliveredLine.Kind.SPOKEN, "spoken:a:1",
                Component.literal("Anna"), Component.literal("Hello."),
                DeliveredLine.Status.REFUSED, ChoiceClearS2C.Reason.EXPIRED);
        assertEquals(DeliveredLine.Status.DELIVERED, line.status());
        assertNull(line.refusal());
        assertNull(line.statusLabel());
    }

    @Test
    void clearingLeavesNothingBehind() {
        DeliveredHistory history = new DeliveredHistory(8);
        history.record(spoken("spoken:a:1", "Good morning."));
        history.clear();
        assertTrue(history.isEmpty());
        assertTrue(history.record(spoken("spoken:a:1", "Good morning.")),
                "a cleared identity is free again on the next connection");
    }
}
