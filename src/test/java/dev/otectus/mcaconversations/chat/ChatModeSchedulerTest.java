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
}
