package dev.otectus.mcaconversations.disposition;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.ToIntFunction;

/**
 * World-global persistence for the disposition store, pinned to the overworld's data storage
 * ({@code data/mcaconversations_dispositions.dat}) exactly like {@link
 * dev.otectus.mcaconversations.gossip.GossipSavedData}. Server-authoritative by construction: only
 * server-side dialogue conditions/actions and server events ever touch it. Every mutating helper
 * here calls {@link #setDirty()}; all logic lives in the pure {@link DispositionStore}.
 */
public final class DispositionSavedData extends SavedData {

    private static final String DATA_NAME = "mcaconversations_dispositions";

    private final DispositionStore store;

    private DispositionSavedData(DispositionStore store) {
        this.store = store;
    }

    // 1.21.1 SavedData API: computeIfAbsent takes a SavedData.Factory (constructor + loader) plus
    // the file name, and both the loader and save() receive a HolderLookup.Provider. The DATA_NAME
    // and the stored payload are deliberately unchanged, so an upgraded world keeps its .dat file
    // and every record in it. The DataFixTypes is null: no vanilla data fixer applies to this file.
    public static DispositionSavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(DispositionSavedData::create, DispositionSavedData::load, null),
                DATA_NAME);
    }

    private static DispositionSavedData create() {
        return new DispositionSavedData(new DispositionStore());
    }

    private static DispositionSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        return new DispositionSavedData(DispositionStore.load(tag));
    }

    /** Read-only decayed axis view; absent records read as the personality baseline. */
    public int readAxis(UUID villager, UUID player, DispositionAxis axis, int baseline, long now,
                        double decayMultiplier) {
        return store.readAxis(villager, player, axis, baseline, now, decayMultiplier);
    }

    public Optional<DispositionRecord> get(UUID villager, UUID player) {
        return store.get(villager, player);
    }

    /** Guarded write path; see {@link DispositionStore#apply}. */
    public Map<DispositionAxis, Integer> apply(UUID villager, UUID player, String topic,
                                               Map<DispositionAxis, Integer> deltas,
                                               ToIntFunction<DispositionAxis> baselines, long now,
                                               int dailyCap, double gainMultiplier, double decayMultiplier) {
        Map<DispositionAxis, Integer> applied = store.apply(villager, player, topic, deltas,
                baselines, now, dailyCap, gainMultiplier, decayMultiplier);
        setDirty();
        return applied;
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
