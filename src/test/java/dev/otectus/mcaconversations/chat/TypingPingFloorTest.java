package dev.otectus.mcaconversations.chat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Each typing ping the server acts on is an entity query on the server thread, and nothing but the
 * client's own one-a-second cadence used to bound them. A client that sends more is now acted on at
 * most once per floor, and that floor is far below the legitimate cadence.
 */
class TypingPingFloorTest {

    private static final UUID PLAYER = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final UUID OTHER = UUID.fromString("00000000-0000-0000-0000-000000000002");

    @Test
    @DisplayName("a flood inside the floor is acted on once; the client's own cadence always is")
    void floodIsFloored() {
        Map<UUID, Long> last = new HashMap<>();
        assertTrue(VillagerAttention.acceptTypingPing(last, PLAYER, 100));
        for (int i = 0; i < 50; i++) {
            assertFalse(VillagerAttention.acceptTypingPing(last, PLAYER, 100 + i % VillagerAttention.TYPING_PING_FLOOR_TICKS));
        }
        assertTrue(VillagerAttention.acceptTypingPing(last, PLAYER, 100 + VillagerAttention.TYPING_PING_FLOOR_TICKS));
        assertTrue(VillagerAttention.acceptTypingPing(last, PLAYER, 125), "one a second is never refused");
    }

    @Test
    @DisplayName("players are floored independently, and a clock that went backwards never locks one out")
    void independentAndClockSafe() {
        Map<UUID, Long> last = new HashMap<>();
        assertTrue(VillagerAttention.acceptTypingPing(last, PLAYER, 100));
        assertTrue(VillagerAttention.acceptTypingPing(last, OTHER, 101));
        assertTrue(VillagerAttention.acceptTypingPing(last, PLAYER, 40), "a new world's game time restarts low");
    }
}
