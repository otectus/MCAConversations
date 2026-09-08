package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.McaConversations;
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
 *
 * <p><b>Never dirty at all when degraded.</b> A file written by a newer build is read but never
 * written back in this build's shape, so {@link #setDirty()} is swallowed outright rather than
 * trusted to every call site — including the ones Minecraft itself might add. The store owns the
 * decision; this class only enforces it.
 */
public final class ConversationHistorySavedData extends SavedData {

    private static final String DATA_NAME = "mcaconversations_history";

    private final ConversationHistoryStore store;
    private boolean backedUp;
    private boolean degradedAnnounced;

    private ConversationHistorySavedData(ConversationHistoryStore store) {
        this.store = store;
    }

    // 1.21.1 SavedData API: computeIfAbsent takes a SavedData.Factory (constructor + loader) plus
    // the file name, and both the loader and save() receive a HolderLookup.Provider. The DATA_NAME
    // and the stored payload are deliberately unchanged, so an upgraded world keeps its .dat file
    // and every history in it. The DataFixTypes is null: no vanilla data fixer applies to this file.
    public static ConversationHistorySavedData get(MinecraftServer server) {
        ConversationHistorySavedData data = server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(ConversationHistorySavedData::create, ConversationHistorySavedData::load, null),
                DATA_NAME);
        data.backUpIfTruncated(server);
        return data;
    }

    /**
     * Copies the file aside, once, when loading it had to discard records to fit the caps.
     *
     * <p>Only ever written when something was actually lost, so an ordinary world never grows a second
     * file. See {@link ConversationHistoryBackupSavedData} for what it is and when to delete it.
     */
    private void backUpIfTruncated(MinecraftServer server) {
        if (backedUp) {
            return;
        }
        backedUp = true;
        store.originalTagIfTruncated().ifPresent(original -> {
            ConversationHistoryBackupSavedData.write(server, original);
            McaConversations.LOGGER.warn(
                    "Conversation history held more than the caps allow; {} records were dropped while "
                            + "loading it. The file as it was found has been copied to {}.dat, which is "
                            + "safe to delete.",
                    store.discardedOnLoad(), ConversationHistoryBackupSavedData.DATA_NAME);
        });
    }

    /**
     * The already-loaded history for this server, or empty when nothing has asked for it yet.
     *
     * <p>Unlike {@link #get} this never creates a store from nothing. It exists for shutdown, where
     * forcing a load of a store nobody used would be a pointless disk read at the one moment the world
     * is closing.
     */
    public static Optional<ConversationHistorySavedData> peek(MinecraftServer server) {
        if (server == null || server.overworld() == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(server.overworld().getDataStorage().get(
                new SavedData.Factory<>(ConversationHistorySavedData::create, ConversationHistorySavedData::load, null),
                DATA_NAME));
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

    /**
     * Marks the world dirty, unless this world's history came from a newer build.
     *
     * <p>Swallowed rather than merely skipped at the call sites above: a degraded store would write
     * back only the fields this build knows about, and one forgotten call site is all it would take to
     * delete a later version's data. Announced once, at WARN, because the player is losing something
     * real — this session's conversations are not being remembered — and silence would make that look
     * like a bug in the mod rather than a rollback.
     */
    @Override
    public void setDirty() {
        if (store.isDegraded()) {
            if (!degradedAnnounced) {
                degradedAnnounced = true;
                McaConversations.LOGGER.warn(
                        "Conversation history is read-only because the world was last opened with a newer "
                                + "build of this mod; changes made this session will not be saved.");
            }
            return;
        }
        super.setDirty();
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
