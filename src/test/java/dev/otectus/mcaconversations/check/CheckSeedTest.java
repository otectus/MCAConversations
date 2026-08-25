package dev.otectus.mcaconversations.check;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckSeedTest {

    private static final UUID VILLAGER = UUID.fromString("11111111-1111-1111-1111-111111111111");
    private static final UUID PLAYER = UUID.fromString("22222222-2222-2222-2222-222222222222");

    @Test
    void identicalInputsGiveIdenticalRolls() {
        int first = CheckSeed.roll(VILLAGER, PLAYER, "fears.challenge", 0, 3000L);
        for (int i = 0; i < 10; i++) {
            assertEquals(first, CheckSeed.roll(VILLAGER, PLAYER, "fears.challenge", 0, 3000L));
        }
    }

    @Test
    void reopeningWithinTheSameHalfDayBucketNeverRerolls() {
        // Ticks 0..11999 share bucket 0: closing and re-opening the screen cannot re-roll.
        int atDawn = CheckSeed.roll(VILLAGER, PLAYER, "fears.challenge", 0, 0L);
        assertEquals(atDawn, CheckSeed.roll(VILLAGER, PLAYER, "fears.challenge", 0, 5_000L));
        assertEquals(atDawn, CheckSeed.roll(VILLAGER, PLAYER, "fears.challenge", 0, 11_999L));
    }

    @Test
    void rollsStayWithinMinusTenToPlusTenAndSpreadAcrossTheRange() {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 500; i++) {
            int roll = CheckSeed.roll(VILLAGER, PLAYER, "topic." + i, 0, 0L);
            assertTrue(roll >= -10 && roll <= 10, "roll out of range: " + roll);
            seen.add(roll);
        }
        assertTrue(seen.size() >= 15, "seeded rolls should spread across the range, saw " + seen);
    }

    @Test
    void playerTopicStageAndBucketAllFeedTheSeed() {
        // With 21 outcomes single pairs can collide, so assert variation across a sample instead.
        Set<String> distinctStreams = new HashSet<>();
        for (int i = 0; i < 40; i++) {
            UUID player = new UUID(0, i);
            StringBuilder stream = new StringBuilder();
            for (int stage = 0; stage < 4; stage++) {
                stream.append(CheckSeed.roll(VILLAGER, player, "fears.challenge", stage, 0L)).append(',');
                stream.append(CheckSeed.roll(VILLAGER, player, "fears.challenge", stage, 12_000L)).append(',');
            }
            distinctStreams.add(stream.toString());
        }
        assertTrue(distinctStreams.size() >= 35,
                "different players must get effectively independent roll streams");
    }
}
