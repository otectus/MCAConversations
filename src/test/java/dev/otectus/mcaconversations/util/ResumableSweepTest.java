package dev.otectus.mcaconversations.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResumableSweepTest {

    @Test
    void aPassOverNKeysTakesCeilNOverBatchCallsAndVisitsEachOnce() {
        for (int n : new int[] {0, 1, 63, 64, 65, 640, 1000}) {
            Set<Integer> keys = new LinkedHashSet<>();
            for (int i = 0; i < n; i++) {
                keys.add(i);
            }
            ResumableSweep<Integer> sweep = new ResumableSweep<>();
            List<Integer> seen = new ArrayList<>();
            int calls = 0;
            do {
                seen.addAll(sweep.next(() -> keys, 64));
                calls++;
            } while (sweep.inPass());
            assertEquals(Math.max(1, (n + 63) / 64), calls, "n=" + n);
            assertEquals(new ArrayList<>(keys), seen, "n=" + n);
            assertEquals(1L, sweep.passesCompleted());
        }
    }

    @Test
    void keysChangingMidPassAreToleratedAndThePassIsTheSnapshot() {
        Set<Integer> keys = new LinkedHashSet<>(List.of(1, 2, 3, 4, 5));
        ResumableSweep<Integer> sweep = new ResumableSweep<>();
        assertEquals(List.of(1, 2), sweep.next(() -> keys, 2));
        keys.remove(3);
        keys.add(99);
        // The pass hands out its snapshot; the caller skips what is gone, and 99 waits for the next pass.
        assertEquals(List.of(3, 4), sweep.next(() -> keys, 2));
        assertEquals(List.of(5), sweep.next(() -> keys, 2));
        assertFalse(sweep.inPass());
        List<Integer> second = sweep.next(() -> keys, 10);
        assertTrue(second.contains(99));
        assertFalse(second.contains(3));
    }
}
