package dev.otectus.mcarealtalk.state;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoryIdsTest {

    @Test
    void idsAreNamespaced() {
        assertEquals("mcarealtalk.topic.fears", MemoryIds.topicEver("fears"));
        assertEquals("mcarealtalk.cooldown.fears", MemoryIds.topicCooldown("fears"));
        assertEquals("mcarealtalk.state.grateful", MemoryIds.state("grateful"));
        assertEquals("mcarealtalk.unlock.opened_up", MemoryIds.unlock("opened_up"));
        assertTrue(MemoryIds.isOurs(MemoryIds.state("grateful")));
        assertFalse(MemoryIds.isOurs("seen"));
    }

    @Test
    void playerScopingMatchesMcaParseIdFormat() {
        // Pinned against MCA 7.6.23 LongTermMemory.parseId: id + "." + player UUID.
        UUID player = UUID.fromString("11111111-2222-3333-4444-555555555555");
        assertEquals("mcarealtalk.state.grateful.11111111-2222-3333-4444-555555555555",
                MemoryIds.playerScoped(MemoryIds.state("grateful"), player));
    }

    @Test
    void gossipToldIdsIncludeEventId() {
        UUID event = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee");
        assertEquals("mcarealtalk.gossip.aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee",
                MemoryIds.gossipTold(event));
    }
}
