package dev.otectus.mcaconversations.identity;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Optional;
import java.util.UUID;

/**
 * World-global persistence for villager identity profiles, pinned to the overworld's data storage
 * ({@code data/mcaconversations_identity.dat}).
 *
 * <p>Its own file rather than a section of the progress ledger, for the same reason the progress
 * ledger is its own file: the two have different lifetimes and different owners. A profile belongs to
 * a villager and survives every player; a progress record belongs to a villager/player pair. Merging
 * them would mean a schema change on one risking the other.
 *
 * <p>Server-authoritative by construction, and dirty only on mutation — a read never marks the world
 * for saving, which matters because a profile is read on essentially every interaction (spec §21.6).
 */
public final class VillagerIdentitySavedData extends SavedData {

    private static final String DATA_NAME = "mcaconversations_identity";

    private final VillagerIdentityStore store;

    private VillagerIdentitySavedData(VillagerIdentityStore store) {
        this.store = store;
    }

    // 1.21.1 SavedData API: computeIfAbsent takes a SavedData.Factory (constructor + loader) plus
    // the file name, and both the loader and save() receive a HolderLookup.Provider. The DATA_NAME
    // and the stored payload are deliberately unchanged, so an upgraded world keeps its .dat file
    // and every profile in it. The DataFixTypes is null: no vanilla data fixer applies to this file.
    public static VillagerIdentitySavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(VillagerIdentitySavedData::create, VillagerIdentitySavedData::load, null),
                DATA_NAME);
    }

    private static VillagerIdentitySavedData create() {
        return new VillagerIdentitySavedData(new VillagerIdentityStore());
    }

    private static VillagerIdentitySavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        return new VillagerIdentitySavedData(VillagerIdentityStore.load(tag));
    }

    /** Read-only lookup; never generates and never marks dirty. */
    public Optional<VillagerIdentityRecord> peek(UUID villager) {
        return store.get(villager);
    }

    /**
     * Stores a freshly generated profile unless one already exists.
     *
     * @return the profile now in force, which is the pre-existing one on a race
     */
    public VillagerIdentityRecord putIfAbsent(UUID villager, VillagerIdentityRecord record) {
        VillagerIdentityRecord inForce = store.putIfAbsent(villager, record);
        if (inForce == record) {
            setDirty();
        }
        return inForce;
    }

    /** Replaces a profile after an explicit life transition. */
    public void replace(UUID villager, VillagerIdentityRecord record) {
        store.put(villager, record);
        setDirty();
    }

    public void removeVillager(UUID villager) {
        store.removeVillager(villager);
        setDirty();
    }

    /** Applies the catalog's alias table to every stored profile; returns how many changed. */
    public int applyAliases(IdentityCatalog catalog) {
        int changed = store.applyAliases(catalog);
        if (changed > 0) {
            setDirty();
        }
        return changed;
    }

    public int size() {
        return store.size();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        return store.save(tag);
    }
}
