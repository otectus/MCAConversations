package dev.otectus.mcaconversations.court;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Optional;
import java.util.UUID;

/**
 * World-global persistence for the court memory, pinned to the overworld's data storage
 * ({@code data/mcaconversations_court.dat}).
 *
 * <p>Its own file rather than a section of the gossip data, because it must survive MCA Capitals
 * being uninstalled and reinstalled: a chronicle cursor thrown away is a whole court's history
 * re-announced as news, and a forgotten title is a promotion remarked on twice.
 *
 * <p>All logic lives in the pure {@link CourtRoleMemory}; this class is the storage wrapper and the
 * only thing that calls {@link #setDirty()}.
 */
public final class CourtMemorySavedData extends SavedData {

    private static final String DATA_NAME = "mcaconversations_court";

    private final CourtRoleMemory memory;

    private CourtMemorySavedData(CourtRoleMemory memory) {
        this.memory = memory;
    }

    // 1.21.1 SavedData API: computeIfAbsent takes a SavedData.Factory (constructor + loader) plus
    // the file name, and both the loader and save() receive a HolderLookup.Provider. The DATA_NAME
    // and the stored payload are deliberately unchanged. The DataFixTypes is null: no vanilla data
    // fixer applies to this file.
    public static CourtMemorySavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        () -> new CourtMemorySavedData(new CourtRoleMemory()),
                        CourtMemorySavedData::load,
                        null),
                DATA_NAME);
    }

    private static CourtMemorySavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        return new CourtMemorySavedData(CourtRoleMemory.load(tag));
    }

    /** Read-only view for callers that only ask questions. Mutate through the methods below. */
    public CourtRoleMemory memory() {
        return memory;
    }

    // --- Villager titles -----------------------------------------------------------------------

    public void observe(UUID villager, String titleId, long today) {
        if (memory.observe(villager, titleId, today)) {
            setDirty();
        }
    }

    public Optional<String> freshChange(UUID villager, long today, int windowDays) {
        return memory.freshChange(villager, today, windowDays);
    }

    public void markRemarked(UUID villager) {
        if (memory.markRemarked(villager)) {
            setDirty();
        }
    }

    // --- Capital cursors and snapshots ---------------------------------------------------------

    public int chronicleSeen(UUID capitalId) {
        return memory.chronicleSeen(capitalId);
    }

    public void setChronicleSeen(UUID capitalId, int index) {
        if (memory.setChronicleSeen(capitalId, index)) {
            setDirty();
        }
    }

    public CourtRoleMemory.CapitalSnapshot snapshot(UUID capitalId) {
        return memory.snapshot(capitalId);
    }

    public void setSnapshot(UUID capitalId, CourtRoleMemory.CapitalSnapshot snapshot) {
        if (memory.setSnapshot(capitalId, snapshot)) {
            setDirty();
        }
    }

    /**
     * Marks the memory dirty after a caller has written through {@link #memory()} directly.
     *
     * <p>{@link CourtNewsPoller} writes in bulk — a poll touches every capital's cursor, snapshot and
     * office holders at once — and reports whether anything changed, so it saves once per poll rather
     * than routing dozens of writes through the wrappers above.
     */
    public void markChanged() {
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        return memory.save(tag);
    }
}
