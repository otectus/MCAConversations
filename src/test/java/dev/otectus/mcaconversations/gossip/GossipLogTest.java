package dev.otectus.mcaconversations.gossip;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GossipLogTest {

    private static final UUID A = UUID.fromString("00000000-0000-0000-0000-00000000000a");
    private static final UUID B = UUID.fromString("00000000-0000-0000-0000-00000000000b");
    private static final UUID C = UUID.fromString("00000000-0000-0000-0000-00000000000c");
    private static final UUID TELLER = UUID.fromString("00000000-0000-0000-0000-0000000000ff");

    private static GossipEvent event(UUID id, GossipEventType type, int village, long created, UUID a, UUID b) {
        return new GossipEvent(id, type, village, created, a, "A-name",
                Optional.ofNullable(b), b == null ? "" : "B-name");
    }

    @Test
    void addRejectsSameSubjectsSameTypeSameVillage() {
        GossipLog log = new GossipLog();
        assertTrue(log.add(event(UUID.randomUUID(), GossipEventType.MARRIAGE, 1, 100, A, B), 32));
        // Same couple observed from the other side.
        assertFalse(log.add(event(UUID.randomUUID(), GossipEventType.MARRIAGE, 1, 200, B, A), 32));
        // Different type is a new event.
        assertTrue(log.add(event(UUID.randomUUID(), GossipEventType.DIVORCE, 1, 300, A, B), 32));
        // Same couple, different village: separate.
        assertTrue(log.add(event(UUID.randomUUID(), GossipEventType.MARRIAGE, 2, 400, A, B), 32));
        assertEquals(3, log.size());
    }

    @Test
    void addEnforcesPerVillageCapOldestFirst() {
        GossipLog log = new GossipLog();
        GossipEvent oldest = event(UUID.randomUUID(), GossipEventType.DEATH, 1, 10, A, null);
        log.add(oldest, 2);
        log.add(event(UUID.randomUUID(), GossipEventType.DEATH, 1, 20, B, null), 2);
        log.add(event(UUID.randomUUID(), GossipEventType.DEATH, 1, 30, C, null), 2);
        assertEquals(2, log.size());
        assertFalse(log.events().contains(oldest));
    }

    @Test
    void pruneDropsExpired() {
        GossipLog log = new GossipLog();
        log.add(event(UUID.randomUUID(), GossipEventType.DEATH, 1, 0, A, null), 32);
        log.add(event(UUID.randomUUID(), GossipEventType.DEATH, 1, 5000, B, null), 32);
        assertEquals(1, log.pruneOlderThan(10000, 6000));
        assertEquals(1, log.size());
    }

    @Test
    void queryIsNewestFirstAndFilters() {
        GossipLog log = new GossipLog();
        GossipEvent older = event(UUID.randomUUID(), GossipEventType.MARRIAGE, 1, 100, A, B);
        GossipEvent newer = event(UUID.randomUUID(), GossipEventType.DEATH, 1, 200, C, null);
        log.add(older, 32);
        log.add(newer, 32);

        // Newest first.
        assertEquals(newer, log.query(1, Set.of(), 300, 10000, TELLER, e -> false).orElseThrow());
        // Type filter.
        assertEquals(older, log.query(1, EnumSet.of(GossipEventType.MARRIAGE), 300, 10000, TELLER, e -> false).orElseThrow());
        // Age filter (newer is 100 ticks old at now=300; older is 200).
        assertEquals(newer, log.query(1, Set.of(), 300, 150, TELLER, e -> false).orElseThrow());
        // Told filter skips to the next one.
        assertEquals(older, log.query(1, Set.of(), 300, 10000, TELLER, e -> e.equals(newer)).orElseThrow());
        // Wrong village: nothing.
        assertTrue(log.query(2, Set.of(), 300, 10000, TELLER, e -> false).isEmpty());
    }

    @Test
    void queryExcludesEventsInvolvingTheTeller() {
        GossipLog log = new GossipLog();
        log.add(event(UUID.randomUUID(), GossipEventType.MARRIAGE, 1, 100, TELLER, B), 32);
        assertTrue(log.query(1, Set.of(), 200, 10000, TELLER, e -> false).isEmpty());
        // A third party can tell it.
        assertTrue(log.query(1, Set.of(), 200, 10000, C, e -> false).isPresent());
    }
}
