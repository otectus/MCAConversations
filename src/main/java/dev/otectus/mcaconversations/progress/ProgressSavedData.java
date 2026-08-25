package dev.otectus.mcaconversations.progress;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Optional;
import java.util.UUID;

/**
 * World-global persistence for the conversation progress ledger, pinned to the overworld's data
 * storage ({@code data/mcaconversations_progress.dat}).
 *
 * <p>Deliberately its own file rather than a section of the disposition data: the affection guards
 * and milestone flags must keep working when {@code enableDispositions} is off (plan §5.3), and they
 * must survive a schema change that the vector is allowed to discard.
 *
 * <p>Server-authoritative by construction — only server-side dialogue actions and server events ever
 * reach it. Every mutating helper calls {@link #setDirty()}; all logic lives in the pure
 * {@link ProgressStore}.
 */
public final class ProgressSavedData extends SavedData {

    private static final String DATA_NAME = "mcaconversations_progress";

    private final ProgressStore store;

    private ProgressSavedData(ProgressStore store) {
        this.store = store;
    }

    // 1.21.1 SavedData API: computeIfAbsent takes a SavedData.Factory (constructor + loader) plus
    // the file name, and both the loader and save() receive a HolderLookup.Provider. The DATA_NAME
    // and the stored payload are deliberately unchanged, so an upgraded world keeps its .dat file
    // and every record in it. The DataFixTypes is null: no vanilla data fixer applies to this file.
    public static ProgressSavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(ProgressSavedData::create, ProgressSavedData::load, null),
                DATA_NAME);
    }

    private static ProgressSavedData create() {
        return new ProgressSavedData(new ProgressStore());
    }

    private static ProgressSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        return new ProgressSavedData(ProgressStore.load(tag));
    }

    public Optional<ProgressRecord> peek(UUID villager, UUID player) {
        return store.get(villager, player);
    }

    /** Guarded heart write path; see {@link ProgressStore#applyAffection}. */
    public AffectionOutcome applyAffection(UUID villager, UUID player, AffectionApply directive,
                                           AffectionContext context) {
        AffectionOutcome outcome = store.applyAffection(villager, player, directive, context);
        setDirty();
        return outcome;
    }

    /** Read-only arc stage; 0 when the arc has never started. */
    public int arcStage(UUID villager, UUID player, String arcId) {
        return store.get(villager, player).map(record -> record.arcStage(arcId)).orElse(0);
    }

    public boolean hasMilestone(UUID villager, UUID player, String milestoneId) {
        return store.get(villager, player).map(record -> record.hasMilestone(milestoneId)).orElse(false);
    }

    public Optional<String> exclusiveChoice(UUID villager, UUID player, String group) {
        return store.get(villager, player).flatMap(record -> record.exclusiveChoice(group));
    }

    public int setArcStage(UUID villager, UUID player, String arcId, int stage, int maxStage, long now) {
        ProgressRecord record = store.getOrCreate(villager, player, now);
        int applied = record.setArcStage(arcId, stage, maxStage);
        record.touch(now);
        setDirty();
        return applied;
    }

    public boolean setMilestone(UUID villager, UUID player, String milestoneId, long now) {
        ProgressRecord record = store.getOrCreate(villager, player, now);
        boolean fired = record.setMilestone(milestoneId);
        record.touch(now);
        setDirty();
        return fired;
    }

    public boolean setExclusiveChoice(UUID villager, UUID player, String group, String member, long now) {
        ProgressRecord record = store.getOrCreate(villager, player, now);
        boolean decided = record.setExclusiveChoice(group, member);
        record.touch(now);
        setDirty();
        return decided;
    }

    public int prune(long now, long staleTicks) {
        int removed = store.prune(now, staleTicks);
        if (removed > 0) {
            setDirty();
        }
        return removed;
    }

    public void removeVillager(UUID villager) {
        store.removeVillager(villager);
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        return store.save(tag);
    }
}
