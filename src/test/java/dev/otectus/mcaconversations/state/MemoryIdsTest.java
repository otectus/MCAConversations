package dev.otectus.mcaconversations.state;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoryIdsTest {

    @Test
    void idsAreNamespaced() {
        assertEquals("mcaconversations.topic.fears", MemoryIds.topicEver("fears"));
        assertEquals("mcaconversations.cooldown.fears", MemoryIds.topicCooldown("fears"));
        assertEquals("mcaconversations.state.grateful", MemoryIds.state("grateful"));
        assertEquals("mcaconversations.unlock.opened_up", MemoryIds.unlock("opened_up"));
        assertTrue(MemoryIds.isOurs(MemoryIds.state("grateful")));
        assertFalse(MemoryIds.isOurs("seen"));
    }

    @Test
    void playerScopingMatchesMcaParseIdFormat() {
        // Pinned against MCA 7.6.23 LongTermMemory.parseId: id + "." + player UUID.
        UUID player = UUID.fromString("11111111-2222-3333-4444-555555555555");
        assertEquals("mcaconversations.state.grateful.11111111-2222-3333-4444-555555555555",
                MemoryIds.playerScoped(MemoryIds.state("grateful"), player));
    }

    @Test
    void gossipToldIdsIncludeEventId() {
        UUID event = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee");
        assertEquals("mcaconversations.gossip.aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee",
                MemoryIds.gossipTold(event));
    }

    @Test
    void questIdsAreNamespacedAndSanitized() {
        // namespace:path and any '/' are flattened to '.' so the id stays a bare MCA memory-map key.
        assertEquals("mcaconversations.quest.done.mcaquests.leatherworker_the_tannery",
                MemoryIds.questCompleted("mcaquests:leatherworker_the_tannery"));
        assertEquals("mcaconversations.quest.failed.mcaquests.chains.guard_safety.3_militia",
                MemoryIds.questFailed("mcaquests:chains/guard_safety/3_militia"));
        assertTrue(MemoryIds.isOurs(MemoryIds.questCompleted("mcaquests:x")));
    }
}
