package dev.otectus.mcaconversations.chat;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Edge detection for proactive greeting (Phase 4): greet on radius entry, never on presence. */
class GreetOnApproachTest {

    private static final UUID A = UUID.nameUUIDFromBytes("a".getBytes());
    private static final UUID B = UUID.nameUUIDFromBytes("b".getBytes());
    private static final UUID C = UUID.nameUUIDFromBytes("c".getBytes());

    @Test
    void firstScanCountsEveryoneAsEntered() {
        assertEquals(Set.of(A, B), GreetOnApproach.newlyEntered(null, Set.of(A, B)));
        assertEquals(Set.of(A), GreetOnApproach.newlyEntered(Set.of(), Set.of(A)));
    }

    @Test
    void stayingInsideIsNotEntering() {
        assertTrue(GreetOnApproach.newlyEntered(Set.of(A, B), Set.of(A, B)).isEmpty());
    }

    @Test
    void onlyTheNewArrivalCounts() {
        assertEquals(Set.of(C), GreetOnApproach.newlyEntered(Set.of(A, B), Set.of(A, B, C)));
    }

    @Test
    void leavingAndReturningCountsAsEnteringAgain() {
        // Scan 1: A inside. Scan 2: A left. Scan 3: A back → entered again.
        Set<UUID> afterLeave = Set.of();
        assertEquals(Set.of(A), GreetOnApproach.newlyEntered(afterLeave, Set.of(A)));
    }

    @Test
    void emptyCurrentYieldsNothing() {
        assertTrue(GreetOnApproach.newlyEntered(Set.of(A), Set.of()).isEmpty());
    }
}
