package dev.otectus.mcaconversations.disposition;

import dev.otectus.mcaconversations.progress.ProgressStore;
import dev.otectus.mcaconversations.util.ResumableSweep;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A batched prune pass removes exactly what one unbatched prune removes, in ceil(n / batch) calls
 * (feedback remediation WP-D2). Durable records are never discarded by the pacing.
 */
class StorePruneBatchTest {

    private static final int VILLAGERS = 200;
    private static final int BATCH = 64;
    private static final long NOW = 1_000_000L;
    private static final long STALE = 100_000L;

    private static long lastWritten(int villager, int player) {
        // A mix of fresh and stale pairs, and some villagers whose every pair is stale.
        return (villager % 3 == 0 || player == 1) ? NOW - STALE - 1 : NOW - 10;
    }

    private static DispositionStore dispositions() {
        DispositionStore store = new DispositionStore();
        for (int v = 0; v < VILLAGERS; v++) {
            for (int p = 0; p < 3; p++) {
                store.getOrCreate(new UUID(7L, v), new UUID(9L, p), lastWritten(v, p));
            }
        }
        return store;
    }

    private static ProgressStore progress() {
        ProgressStore store = new ProgressStore();
        for (int v = 0; v < VILLAGERS; v++) {
            for (int p = 0; p < 3; p++) {
                store.getOrCreate(new UUID(7L, v), new UUID(9L, p), lastWritten(v, p));
            }
        }
        return store;
    }

    @Test
    void dispositionsPrunedInBatchesMatchTheUnbatchedPrune() {
        DispositionStore whole = dispositions();
        DispositionStore batched = dispositions();
        int expected = whole.prune(NOW, STALE);
        assertTrue(expected > 0);
        ResumableSweep<UUID> sweep = new ResumableSweep<>();
        int removed = 0;
        int calls = 0;
        do {
            removed += batched.pruneVillagers(sweep.next(batched::villagerIds, BATCH), NOW, STALE);
            calls++;
        } while (sweep.inPass());
        assertEquals((VILLAGERS + BATCH - 1) / BATCH, calls);
        assertEquals(expected, removed);
        assertEquals(whole.pairCount(), batched.pairCount());
        assertEquals(whole.villagerIds().size(), batched.villagerIds().size(), "emptied villagers go too");
    }

    @Test
    void progressPrunedInBatchesMatchesTheUnbatchedPrune() {
        ProgressStore whole = progress();
        ProgressStore batched = progress();
        int expected = whole.prune(NOW, STALE);
        ResumableSweep<UUID> sweep = new ResumableSweep<>();
        int removed = 0;
        int calls = 0;
        do {
            removed += batched.pruneVillagers(sweep.next(batched::villagerIds, BATCH), NOW, STALE);
            calls++;
        } while (sweep.inPass());
        assertEquals((VILLAGERS + BATCH - 1) / BATCH, calls);
        assertEquals(expected, removed);
        assertEquals(whole.pairCount(), batched.pairCount());
    }

    @Test
    void aVillagerRemovedMidPassIsSkipped() {
        DispositionStore store = dispositions();
        ResumableSweep<UUID> sweep = new ResumableSweep<>();
        store.pruneVillagers(sweep.next(store::villagerIds, BATCH), NOW, STALE);
        UUID gone = store.villagerIds().get(store.villagerIds().size() - 1);
        store.removeVillager(gone);
        while (sweep.inPass()) {
            store.pruneVillagers(sweep.next(store::villagerIds, BATCH), NOW, STALE);
        }
        assertTrue(store.get(gone, new UUID(9L, 0)).isEmpty());
    }
}
