package dev.otectus.mcaconversations.history;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

/**
 * World-global persistence for the living-history store, pinned to the overworld
 * ({@code data/mcaconversations_history.dat}).
 *
 * <p>Its own file, beside the progress ledger and the disposition vector rather than inside either.
 * The plan is explicit that a new history store must not be a reason to delete or reshape the
 * existing ones (spec §22.1), and separate files are the only version of that promise that survives a
 * schema change.
 *
 * <p><b>Dirty only on mutation.</b> Every method that can change something routes through
 * {@link #mutate}, which marks the world dirty exactly when the pure store reports a real change. That
 * matters more here than in the other stores: the director reads history during selection, on every
 * interaction, and a read that marked the world dirty would turn conversation into disk traffic
 * (spec §21.6).
 */
public final class ConversationHistorySavedData extends SavedData {

    private static final String DATA_NAME = "mcaconversations_history";

    private final ConversationHistoryStore store;

    private ConversationHistorySavedData(ConversationHistoryStore store) {
        this.store = store;
    }

    // 1.21.1 SavedData API: computeIfAbsent takes a SavedData.Factory (constructor + loader) plus
    // the file name, and both the loader and save() receive a HolderLookup.Provider. The DATA_NAME
    // and the stored payload are deliberately unchanged, so an upgraded world keeps its .dat file
    // and every history in it. The DataFixTypes is null: no vanilla data fixer applies to this file.
    public static ConversationHistorySavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(ConversationHistorySavedData::create, ConversationHistorySavedData::load, null),
                DATA_NAME);
    }

    private static ConversationHistorySavedData create() {
        return new ConversationHistorySavedData(new ConversationHistoryStore());
    }

    private static ConversationHistorySavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        return new ConversationHistorySavedData(ConversationHistoryStore.load(tag));
    }

    /** Read-only view of one villager's history. Never creates, never marks dirty. */
    public Optional<VillagerHistory> peek(UUID villager) {
        return store.peek(villager);
    }

    /**
     * Runs {@code action} against one villager's history and marks the world dirty if it changed.
     *
     * @return whatever the action returned
     */
    public <T> T mutate(UUID villager, Function<VillagerHistory, T> action, boolean expectChange) {
        VillagerHistory history = store.getOrCreate(villager);
        T result = action.apply(history);
        if (expectChange || Boolean.TRUE.equals(result)) {
            setDirty();
        }
        return result;
    }

    /** Convenience for the common boolean-returning mutation. */
    public boolean change(UUID villager, Function<VillagerHistory, Boolean> action) {
        boolean changed = Boolean.TRUE.equals(mutate(villager, action, false));
        return changed;
    }

    public void removeVillager(UUID villager) {
        store.removeVillager(villager);
        setDirty();
    }

    public int prune(long today) {
        int removed = store.prune(today);
        if (removed > 0) {
            setDirty();
        }
        return removed;
    }

    public ConversationHistoryStore store() {
        return store;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        return store.save(tag);
    }
}
