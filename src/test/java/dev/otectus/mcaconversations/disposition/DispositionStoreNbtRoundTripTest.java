package dev.otectus.mcaconversations.disposition;

import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DispositionStoreNbtRoundTripTest {

    private static final UUID VILLAGER = UUID.fromString("11111111-1111-1111-1111-111111111111");
    private static final UUID PLAYER = UUID.fromString("22222222-2222-2222-2222-222222222222");

    private static DispositionStore populatedStore() {
        DispositionStore store = new DispositionStore();
        DispositionRecord record = store.getOrCreate(VILLAGER, PLAYER, 1000L);
        record.setAxis(DispositionAxis.TRUST, 40);
        record.setAxis(DispositionAxis.TENSION, 15);
        record.setAxis(DispositionAxis.FAMILIARITY, 33);
        record.recordStance("fears.challenge", 7);
        record.recordStance("fears.challenge", 7);
        record.addGained(DispositionAxis.TRUST, 7, 5);
        return store;
    }

    @Test
    void roundTripsAllFields() {
        DispositionStore store = populatedStore();
        DispositionStore loaded = DispositionStore.load(store.save(new CompoundTag()));

        DispositionRecord record = loaded.get(VILLAGER, PLAYER).orElseThrow();
        assertEquals(40, record.axisRaw(DispositionAxis.TRUST));
        assertEquals(15, record.axisRaw(DispositionAxis.TENSION));
        assertEquals(33, record.axisRaw(DispositionAxis.FAMILIARITY));
        assertEquals(0, record.axisRaw(DispositionAxis.WARMTH));
        assertEquals(1000L, record.lastUpdated());
        assertEquals(2, record.repeatCountToday("fears.challenge", 7));
        assertEquals(0, record.repeatCountToday("fears.challenge", 8));
        assertEquals(5, record.gainedToday(DispositionAxis.TRUST, 7));
        assertEquals(0, record.gainedToday(DispositionAxis.TRUST, 8));
    }

    @Test
    void savedTagCarriesTheCurrentVersion() {
        CompoundTag tag = populatedStore().save(new CompoundTag());
        assertEquals(DispositionStore.CURRENT_VERSION, tag.getInt("version"));
    }

    @Test
    void missingVersionLoadsEmpty() {
        // A pre-1.0 save has no dispositions file at all; a version-less tag is foreign data.
        CompoundTag tag = populatedStore().save(new CompoundTag());
        tag.remove("version");
        assertTrue(DispositionStore.load(tag).get(VILLAGER, PLAYER).isEmpty());
    }

    @Test
    void futureVersionLoadsEmpty() {
        CompoundTag tag = populatedStore().save(new CompoundTag());
        tag.putInt("version", DispositionStore.CURRENT_VERSION + 1);
        assertTrue(DispositionStore.load(tag).get(VILLAGER, PLAYER).isEmpty());
    }

    @Test
    void malformedVillagerOrPlayerKeyIsSkippedNotFatal() {
        CompoundTag tag = populatedStore().save(new CompoundTag());
        CompoundTag villagers = tag.getCompound("villagers");
        villagers.put("not-a-uuid", villagers.getCompound(VILLAGER.toString()).copy());
        CompoundTag players = villagers.getCompound(VILLAGER.toString());
        players.put("also-not-a-uuid", players.getCompound(PLAYER.toString()).copy());

        DispositionStore loaded = DispositionStore.load(tag);
        assertEquals(1, loaded.pairCount());
        assertTrue(loaded.get(VILLAGER, PLAYER).isPresent());
    }

    @Test
    void wrongSizeAxisArrayIsSkippedNotFatal() {
        CompoundTag tag = populatedStore().save(new CompoundTag());
        CompoundTag pair = tag.getCompound("villagers").getCompound(VILLAGER.toString())
                .getCompound(PLAYER.toString());
        pair.putByteArray("ax", new byte[]{1, 2});

        assertTrue(DispositionStore.load(tag).get(VILLAGER, PLAYER).isEmpty());
    }

    @Test
    void pruneRemovesStaleRecordsAndEmptyVillagers() {
        DispositionStore store = populatedStore();
        store.getOrCreate(VILLAGER, UUID.randomUUID(), 500_000L);

        assertEquals(1, store.prune(600_000L, 100_000L));
        assertEquals(1, store.pairCount());
        assertTrue(store.get(VILLAGER, PLAYER).isEmpty());

        assertEquals(1, store.prune(10_000_000L, 100_000L));
        assertEquals(0, store.pairCount());
    }

    @Test
    void removeVillagerDropsAllItsPlayers() {
        DispositionStore store = populatedStore();
        store.getOrCreate(VILLAGER, UUID.randomUUID(), 2000L);
        store.removeVillager(VILLAGER);
        assertEquals(0, store.pairCount());
    }

    @Test
    void pairCapDropsTheOldestRecord() {
        DispositionStore store = new DispositionStore(3);
        UUID oldest = UUID.randomUUID();
        store.getOrCreate(VILLAGER, oldest, 100L);
        store.getOrCreate(VILLAGER, UUID.randomUUID(), 200L);
        store.getOrCreate(VILLAGER, UUID.randomUUID(), 300L);
        store.getOrCreate(VILLAGER, UUID.randomUUID(), 400L);

        assertEquals(3, store.pairCount());
        assertTrue(store.get(VILLAGER, oldest).isEmpty());
    }

    @Test
    void readAxisReturnsBaselineForAbsentRecordAndDecayedValueOtherwise() {
        DispositionStore store = new DispositionStore();
        assertEquals(10, store.readAxis(VILLAGER, PLAYER, DispositionAxis.TRUST, 10, 0L, 1.0));

        DispositionRecord record = store.getOrCreate(VILLAGER, PLAYER, 0L);
        record.setAxis(DispositionAxis.TRUST, 40);
        long halfLife = DispositionAxis.TRUST.defaultHalfLifeTicks();
        assertEquals(25, store.readAxis(VILLAGER, PLAYER, DispositionAxis.TRUST, 10, halfLife, 1.0));
        // Reading never mutates: the raw value and anchor are untouched.
        assertEquals(40, record.axisRaw(DispositionAxis.TRUST));
        assertEquals(0L, record.lastUpdated());
    }

    @Test
    void applyFoldsDecayGuardsTheDeltaAndTracksCounters() {
        DispositionStore store = new DispositionStore();
        DispositionRecord record = store.getOrCreate(VILLAGER, PLAYER, 0L);
        record.setAxis(DispositionAxis.TRUST, 40);

        long now = DispositionAxis.TRUST.defaultHalfLifeTicks();
        Map<DispositionAxis, Integer> applied = store.apply(VILLAGER, PLAYER, "fears.challenge",
                Map.of(DispositionAxis.TRUST, 4), axis -> 10, now, 8, 1.0, 1.0);

        // Decay folded first (40 -> 25 toward baseline 10), then the full first-time delta.
        assertEquals(4, applied.get(DispositionAxis.TRUST));
        assertEquals(29, record.axisRaw(DispositionAxis.TRUST));
        assertEquals(now, record.lastUpdated());

        // Second identical stance the same day: diminished to half.
        Map<DispositionAxis, Integer> second = store.apply(VILLAGER, PLAYER, "fears.challenge",
                Map.of(DispositionAxis.TRUST, 4), axis -> 10, now, 8, 1.0, 1.0);
        assertEquals(2, second.get(DispositionAxis.TRUST));
        assertEquals(31, record.axisRaw(DispositionAxis.TRUST));

        // Third: quartered (4 -> 1), and now the daily cap (8) is reached: 4+2+1=7, room for 1.
        Map<DispositionAxis, Integer> third = store.apply(VILLAGER, PLAYER, "fears.challenge",
                Map.of(DispositionAxis.TRUST, 4), axis -> 10, now, 8, 1.0, 1.0);
        assertEquals(1, third.get(DispositionAxis.TRUST));

        // Fourth: repeat multiplier hits zero.
        Map<DispositionAxis, Integer> fourth = store.apply(VILLAGER, PLAYER, "fears.challenge",
                Map.of(DispositionAxis.TRUST, 4), axis -> 10, now, 8, 1.0, 1.0);
        assertEquals(0, fourth.get(DispositionAxis.TRUST));
        assertEquals(32, record.axisRaw(DispositionAxis.TRUST));
    }

    @Test
    void applyClampsAtAxisBounds() {
        DispositionStore store = new DispositionStore();
        DispositionRecord record = store.getOrCreate(VILLAGER, PLAYER, 0L);
        record.setAxis(DispositionAxis.TENSION, 1);

        store.apply(VILLAGER, PLAYER, "a", Map.of(DispositionAxis.TENSION, -10), axis -> 0, 0L, 50, 1.0, 1.0);
        assertEquals(0, record.axisRaw(DispositionAxis.TENSION));
    }

    @Test
    void stanceRepeatMapIsBounded() {
        DispositionRecord record = new DispositionRecord(0L);
        for (int i = 0; i < DispositionRecord.MAX_TRACKED_STANCES + 5; i++) {
            record.recordStance("topic." + i, 1);
        }
        // The oldest entries were evicted; the newest survives.
        assertEquals(1, record.repeatCountToday("topic." + (DispositionRecord.MAX_TRACKED_STANCES + 4), 1));
        assertEquals(0, record.repeatCountToday("topic.0", 1));
    }
}
