package dev.otectus.mcaconversations.chat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Opening the chat box turns a few heads, not the whole square (Stability spec §10.2). */
class TypingGlanceTest {

    @Test
    @DisplayName("at most the three nearest villagers glance at a player who is typing")
    void capped() {
        List<VillagerFinder.VillagerCandidate> square = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            square.add(new VillagerFinder.VillagerCandidate(null, "v" + i, i * i, 0.0));
        }
        List<VillagerFinder.VillagerCandidate> glances = VillagerAttention.typingGlances(square);
        assertEquals(VillagerAttention.MAX_TYPING_GLANCES, glances.size());
        assertEquals(List.of("v0", "v1", "v2"), glances.stream().map(VillagerFinder.VillagerCandidate::name).toList());
        assertEquals(1, VillagerAttention.typingGlances(square.subList(0, 1)).size());
    }
}
