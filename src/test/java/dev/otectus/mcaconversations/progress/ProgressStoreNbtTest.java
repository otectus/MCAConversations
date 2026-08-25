package dev.otectus.mcaconversations.progress;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Persistence for the progress ledger: round trip, bounds, malformed input, and the migration rule
 * that separates this store from the disposition store — a player's arcs, milestones and promises are
 * never thrown away because a version number changed (plan §11.3, §13.5).
 */
class ProgressStoreNbtTest {

    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("v".getBytes());
    private static final UUID PLAYER = UUID.nameUUIDFromBytes("p".getBytes());

    private static ProgressStore populated() {
        ProgressStore store = new ProgressStore();
        ProgressRecord record = store.getOrCreate(VILLAGER, PLAYER, 1_000);
        record.setArcStage("fears", 2, 3);
        record.setMilestone("fears.revelation");
        record.setExclusiveChoice("fears.support", "pledged");
        record.recordApplied("fears.open.comfort", 3, 0);
        return store;
    }

    @Test
    @DisplayName("a populated store survives a save/load round trip intact")
    void roundTrip() {
        ProgressStore loaded = ProgressStore.load(populated().save(new CompoundTag()));
        ProgressRecord record = loaded.get(VILLAGER, PLAYER).orElseThrow();
        assertEquals(2, record.arcStage("fears"));
        assertTrue(record.hasMilestone("fears.revelation"));
        assertEquals(Optional.of("pledged"), record.exclusiveChoice("fears.support"));
        assertEquals(3, record.positiveToday(0));
        assertEquals(1, record.repeatsToday("fears.open.comfort", 0));
        assertTrue(record.everApplied("fears.open.comfort"));
    }

    @Test
    @DisplayName("an unversioned tag is a pre-1.1 world with no ledger, not corruption")
    void unversionedLoadsEmpty() {
        assertEquals(0, ProgressStore.load(new CompoundTag()).pairCount());
    }

    @Test
    @DisplayName("data written by a NEWER version is still read, not discarded")
    void futureVersionKeepsWhatItCanRead() {
        CompoundTag tag = populated().save(new CompoundTag());
        tag.putInt("version", ProgressStore.CURRENT_VERSION + 5);
        tag.getCompound("villagers").getCompound(VILLAGER.toString())
                .getCompound(PLAYER.toString()).putString("someFutureField", "ignored");

        ProgressRecord record = ProgressStore.load(tag).get(VILLAGER, PLAYER).orElseThrow();
        assertTrue(record.hasMilestone("fears.revelation"),
                "a rolled-back world must not lose a milestone it already earned");
        assertEquals(2, record.arcStage("fears"));
    }

    @Test
    @DisplayName("malformed UUID keys are skipped without failing the load")
    void malformedUuidsAreSkipped() {
        CompoundTag tag = populated().save(new CompoundTag());
        tag.getCompound("villagers").put("not-a-uuid", new CompoundTag());
        ProgressStore loaded = ProgressStore.load(tag);
        assertEquals(1, loaded.pairCount());
    }

    @Test
    @DisplayName("out-of-range stored values are clamped on load")
    void clampsOnLoad() {
        CompoundTag tag = populated().save(new CompoundTag());
        CompoundTag record = tag.getCompound("villagers").getCompound(VILLAGER.toString())
                .getCompound(PLAYER.toString());
        record.getCompound("arcs").putInt("fears", -7);
        record.putInt("pos", -50);
        ProgressRecord loaded = ProgressStore.load(tag).get(VILLAGER, PLAYER).orElseThrow();
        assertEquals(0, loaded.arcStage("fears"));
        assertEquals(0, loaded.positiveToday(0));
    }

    @Test
    @DisplayName("over-long stored collections are truncated rather than trusted")
    void truncatesOversizedCollections() {
        CompoundTag tag = populated().save(new CompoundTag());
        CompoundTag record = tag.getCompound("villagers").getCompound(VILLAGER.toString())
                .getCompound(PLAYER.toString());
        ListTag milestones = new ListTag();
        for (int i = 0; i < 500; i++) {
            milestones.add(StringTag.valueOf("spam." + i));
        }
        record.put("milestones", milestones);
        ProgressRecord loaded = ProgressStore.load(tag).get(VILLAGER, PLAYER).orElseThrow();
        assertEquals(ProgressRecord.MAX_MILESTONES, loaded.milestonesView().size());
    }

    @Test
    @DisplayName("an unreadable record yields no record instead of an exception")
    void unreadableRecordIsSkipped() {
        assertTrue(ProgressRecord.fromNbt(new CompoundTag()).isEmpty());
        assertTrue(ProgressRecord.fromNbt(null).isEmpty());
    }

    @Test
    @DisplayName("stale pairs prune and a dead villager's rows are dropped")
    void pruneAndRemove() {
        ProgressStore store = populated();
        assertEquals(0, store.prune(1_500, 1_000), "a fresh record is not stale");
        assertEquals(1, store.prune(50_000, 1_000));
        assertEquals(0, store.pairCount());

        ProgressStore other = populated();
        other.removeVillager(VILLAGER);
        assertEquals(0, other.pairCount());
    }

    @Test
    @DisplayName("a milestone fires once and an exclusive choice cannot be taken twice")
    void oneShotSemantics() {
        ProgressRecord record = new ProgressRecord(0);
        assertTrue(record.setMilestone("fears.scar"));
        assertFalse(record.setMilestone("fears.scar"));

        assertTrue(record.setExclusiveChoice("fears.support", "pledged"));
        assertFalse(record.setExclusiveChoice("fears.support", "stepped_back"),
                "the first side taken decides the group for good");
        assertEquals(Optional.of("pledged"), record.exclusiveChoice("fears.support"));
    }

    @Test
    @DisplayName("arc stages clamp to the declared bound and never go below zero")
    void arcStageClamping() {
        ProgressRecord record = new ProgressRecord(0);
        assertEquals(3, record.setArcStage("fears", 9, 3));
        assertEquals(0, record.setArcStage("fears", -4, 3));
    }

    @Test
    @DisplayName("the transaction ring refuses a repeat and forgets old entries")
    void transactionRing() {
        ProgressRecord record = new ProgressRecord(0);
        assertTrue(record.claimTransaction("tx-1"));
        assertFalse(record.claimTransaction("tx-1"));
        for (int i = 0; i < ProgressRecord.MAX_TRANSACTIONS; i++) {
            record.claimTransaction("filler-" + i);
        }
        assertTrue(record.claimTransaction("tx-1"), "the ring is bounded, so ancient ids are forgotten");
    }
}
