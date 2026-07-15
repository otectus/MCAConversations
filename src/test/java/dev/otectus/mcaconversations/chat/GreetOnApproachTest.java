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

    // --- Randomized greet roll: deterministic per (villager, player, day) ----------

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("player".getBytes());

    @Test
    void rollIsDeterministicWithinADay() {
        boolean first = GreetOnApproach.rollGreet(A, PLAYER, 100, 0.5);
        for (int i = 0; i < 5; i++) {
            assertEquals(first, GreetOnApproach.rollGreet(A, PLAYER, 100, 0.5),
                    "re-entering the radius must not re-roll");
        }
    }

    @Test
    void chanceExtremesAreAbsolute() {
        for (long day = 0; day < 50; day++) {
            assertTrue(GreetOnApproach.rollGreet(A, PLAYER, day, 1.0), "chance 1 always greets");
            assertTrue(!GreetOnApproach.rollGreet(A, PLAYER, day, 0.0), "chance 0 never greets");
        }
    }

    @Test
    void rollVariesAcrossVillagersAndDays() {
        // With chance 0.5 over 200 samples, both outcomes must occur (deterministic, not random flake).
        int greetsByDay = 0;
        for (long day = 0; day < 200; day++) {
            if (GreetOnApproach.rollGreet(A, PLAYER, day, 0.5)) {
                greetsByDay++;
            }
        }
        assertTrue(greetsByDay > 0 && greetsByDay < 200, "outcome must vary by day, got " + greetsByDay);

        int greetsByVillager = 0;
        for (int i = 0; i < 200; i++) {
            UUID v = UUID.nameUUIDFromBytes(("villager-" + i).getBytes());
            if (GreetOnApproach.rollGreet(v, PLAYER, 100, 0.5)) {
                greetsByVillager++;
            }
        }
        assertTrue(greetsByVillager > 0 && greetsByVillager < 200,
                "outcome must vary by villager, got " + greetsByVillager);
    }

    @Test
    void rollRateRoughlyTracksChance() {
        // Sanity: at 0.35 over 1000 (villager, day) samples, the greet rate lands in a broad band.
        int greets = 0;
        for (int i = 0; i < 1000; i++) {
            UUID v = UUID.nameUUIDFromBytes(("v" + i).getBytes());
            if (GreetOnApproach.rollGreet(v, PLAYER, i, 0.35)) {
                greets++;
            }
        }
        assertTrue(greets > 250 && greets < 450, "expected ~350/1000, got " + greets);
    }

    @Test
    void personalityWeightsFavorOutgoingVillagers() {
        assertTrue(GreetOnApproach.personalityWeight("peppy") > 1.0);
        assertTrue(GreetOnApproach.personalityWeight("friendly") > 1.0);
        assertTrue(GreetOnApproach.personalityWeight("shy") < 1.0);
        assertTrue(GreetOnApproach.personalityWeight("grumpy") < 1.0);
        assertEquals(1.0, GreetOnApproach.personalityWeight("witty"), 1e-9);
        assertEquals(1.0, GreetOnApproach.personalityWeight(""), 1e-9, "unknown/absent personality is neutral");
    }
}
