package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.AmbientSelection.Responder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Pure responder selection + deterministic stagger for ambient broadcast (spec §12). */
class AmbientSelectionTest {

    private static final UUID A = UUID.nameUUIDFromBytes("villager-a".getBytes());
    private static final UUID B = UUID.nameUUIDFromBytes("villager-b".getBytes());

    @Test
    void ordersByScoreThenNearest() {
        List<Responder> in = List.of(
                new Responder(0, 0.80, 40.0),
                new Responder(1, 0.90, 25.0),
                new Responder(2, 0.90, 9.0)); // same score as 1 but nearer → ranks first
        List<Responder> out = AmbientSelection.select(in, 5);
        assertEquals(List.of(2, 1, 0), out.stream().map(Responder::candidateIndex).toList());
    }

    @Test
    void capsAtMaxResponders() {
        List<Responder> in = List.of(
                new Responder(0, 0.95, 1.0),
                new Responder(1, 0.90, 2.0),
                new Responder(2, 0.85, 3.0));
        assertEquals(2, AmbientSelection.select(in, 2).size());
        assertEquals(List.of(0, 1), AmbientSelection.select(in, 2).stream().map(Responder::candidateIndex).toList());
    }

    @Test
    void firstResponderHasNoStaggerAndLaterRanksAreOffsetInRange() {
        assertEquals(0, AmbientSelection.staggerOffsetTicks(A, 0));
        int r1 = AmbientSelection.staggerOffsetTicks(A, 1);
        int r2 = AmbientSelection.staggerOffsetTicks(A, 2);
        assertTrue(r1 >= 20 && r1 <= 35, "rank-1 jitter in [20,35], was " + r1);
        assertTrue(r2 > r1, "later ranks are pushed further back");
        assertEquals(2 * r1, r2, "offset is rank × per-villager jitter");
    }

    @Test
    void staggerIsDeterministicPerVillager() {
        assertEquals(AmbientSelection.staggerOffsetTicks(A, 1), AmbientSelection.staggerOffsetTicks(A, 1));
        // Different villagers may (but need not) differ; the value must be stable for each.
        assertEquals(AmbientSelection.staggerOffsetTicks(B, 3), AmbientSelection.staggerOffsetTicks(B, 3));
    }
}
