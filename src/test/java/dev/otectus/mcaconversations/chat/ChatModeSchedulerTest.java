package dev.otectus.mcaconversations.chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Delay math + deadline-ordered draining for the deferred-reply queue. */
class ChatModeSchedulerTest {

    @BeforeEach
    void reset() {
        ChatModeScheduler.reset();
    }

    @Test
    void computeDelayTicks_addsOneTickPerFourChars() {
        assertEquals(15, ChatModeScheduler.computeDelayTicks(15, 0));
        assertEquals(15, ChatModeScheduler.computeDelayTicks(15, 3));   // 3/4 = 0
        assertEquals(16, ChatModeScheduler.computeDelayTicks(15, 4));   // +1
        assertEquals(25, ChatModeScheduler.computeDelayTicks(15, 40));  // +10
    }

    @Test
    void computeDelayTicks_capsAtSixty() {
        assertEquals(60, ChatModeScheduler.computeDelayTicks(15, 1000));
        assertEquals(60, ChatModeScheduler.computeDelayTicks(100, 0));
    }

    @Test
    void computeDelayTicks_clampsNegativeInputs() {
        assertEquals(0, ChatModeScheduler.computeDelayTicks(-5, -20));
    }

    @Test
    void drain_runsDueTasksInDeadlineOrder() {
        List<String> fired = new ArrayList<>();
        ChatModeScheduler.schedule(30, () -> fired.add("c"));
        ChatModeScheduler.schedule(10, () -> fired.add("a"));
        ChatModeScheduler.schedule(20, () -> fired.add("b"));

        ChatModeScheduler.drain(15);
        assertEquals(List.of("a"), fired);

        ChatModeScheduler.drain(25);
        assertEquals(List.of("a", "b"), fired);

        ChatModeScheduler.drain(100);
        assertEquals(List.of("a", "b", "c"), fired);
    }

    @Test
    void drain_preservesInsertionOrderWithinSameTick() {
        List<String> fired = new ArrayList<>();
        ChatModeScheduler.schedule(10, () -> fired.add("first"));
        ChatModeScheduler.schedule(10, () -> fired.add("second"));

        ChatModeScheduler.drain(10);
        assertEquals(List.of("first", "second"), fired);
    }

    @Test
    void drain_isolatesAThrowingTask() {
        List<String> fired = new ArrayList<>();
        ChatModeScheduler.schedule(10, () -> {
            throw new RuntimeException("boom");
        });
        ChatModeScheduler.schedule(10, () -> fired.add("survivor"));

        ChatModeScheduler.drain(10);
        assertEquals(List.of("survivor"), fired);
    }

    @Test
    void clearPlayer_dropsOnlyThatPlayersEntriesAndKeepsDeadlineOrder() {
        List<String> fired = new ArrayList<>();
        var leaving = java.util.UUID.randomUUID();
        var staying = java.util.UUID.randomUUID();
        ChatModeScheduler.scheduleOrdered(staying, 10, () -> fired.add("stay-early"));
        ChatModeScheduler.scheduleOrdered(leaving, 20, () -> fired.add("gone"));
        ChatModeScheduler.scheduleOrdered(staying, 30, () -> fired.add("stay-late"));
        ChatModeScheduler.schedule(15, () -> fired.add("unowned"));

        ChatModeScheduler.clearPlayer(leaving);
        assertEquals(0, ChatModeScheduler.pendingFor(leaving));
        assertEquals(2, ChatModeScheduler.pendingFor(staying));

        ChatModeScheduler.drain(100);
        assertEquals(List.of("stay-early", "unowned", "stay-late"), fired);
    }

    @Test
    void clearPlayer_alsoForgetsTheOrderingAnchor() {
        List<String> fired = new ArrayList<>();
        var player = java.util.UUID.randomUUID();
        ChatModeScheduler.scheduleOrdered(player, 60, () -> fired.add("before"));
        ChatModeScheduler.clearPlayer(player);
        // Without clearing the anchor the next conversation's first line would inherit tick 61.
        ChatModeScheduler.scheduleOrdered(player, 10, () -> fired.add("after"));
        ChatModeScheduler.drain(10);
        assertEquals(List.of("after"), fired);
    }

    @Test
    void aShortInterjectionCannotOvertakeItsLongOpeningLine() {
        List<String> fired = new ArrayList<>();
        var player = java.util.UUID.randomUUID();
        ChatModeScheduler.scheduleOrdered(player, 60, () -> fired.add("opening"));
        ChatModeScheduler.scheduleOrdered(player, 30, () -> fired.add("interjection"));
        ChatModeScheduler.scheduleOrdered(player, 40, () -> fired.add("followup"));
        ChatModeScheduler.drain(59);
        assertEquals(List.of(), fired);
        ChatModeScheduler.drain(62);
        assertEquals(List.of("opening", "interjection", "followup"), fired);
    }

    @Test
    void anotherPlayersConversationDoesNotWaitForTheFirst() {
        List<String> fired = new ArrayList<>();
        ChatModeScheduler.scheduleOrdered(java.util.UUID.randomUUID(), 60, () -> fired.add("slow"));
        ChatModeScheduler.scheduleOrdered(java.util.UUID.randomUUID(), 10, () -> fired.add("quick"));
        ChatModeScheduler.drain(10);
        assertEquals(List.of("quick"), fired);
        ChatModeScheduler.drain(60);
        assertEquals(List.of("quick", "slow"), fired);
    }

}
