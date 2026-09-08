package dev.otectus.mcaconversations.history;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * A verbatim copy of {@code mcaconversations_history.dat} as it was found on disk, written only when
 * loading it had to discard records to fit the caps ({@code data/mcaconversations_history_backup.dat}).
 *
 * <p><b>The mod never reads this file.</b> It exists so that a history which was over its bounds —
 * a hand-edited save, a corrupted one, or a world grown under a build with looser caps — can be
 * inspected or recovered by hand instead of being quietly cut down with no trace of what went. Nothing
 * depends on it, and <b>it is safe for a server owner to delete at any time</b>.
 *
 * <p>Written at most once per load, and only by a load that actually discarded something: a world
 * whose history fits its caps — which is every world the mod itself produced — never grows this file
 * at all. A later load that discards again overwrites it, so what is kept is always the most recent
 * state that lost data rather than the first.
 *
 * <p>Deliberately dumb: one tag in, one tag out, no schema of its own and no version key beyond the
 * one already inside the copied data. A backup that needed migrating would be a second thing to get
 * wrong at exactly the moment the first one already went wrong.
 */
public final class ConversationHistoryBackupSavedData extends SavedData {

    /** The data file's name, without the {@code .dat}; quoted in the log line that points at it. */
    public static final String DATA_NAME = "mcaconversations_history_backup";

    private CompoundTag copy;

    private ConversationHistoryBackupSavedData(CompoundTag copy) {
        this.copy = copy;
    }

    // 1.21.1 SavedData API: computeIfAbsent takes a SavedData.Factory (constructor + loader) plus
    // the file name, and both the loader and save() receive a HolderLookup.Provider. The DataFixTypes
    // is null: this file is a verbatim copy of another one and no vanilla data fixer applies to it.
    /** Replaces the stored copy with {@code original} and schedules it to be written. */
    public static void write(MinecraftServer server, CompoundTag original) {
        ConversationHistoryBackupSavedData data = server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        () -> new ConversationHistoryBackupSavedData(new CompoundTag()),
                        ConversationHistoryBackupSavedData::load,
                        null),
                DATA_NAME);
        data.copy = original.copy();
        data.setDirty();
    }

    private static ConversationHistoryBackupSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        return new ConversationHistoryBackupSavedData(tag);
    }

    /** The copy held, for tests and the inspect command. */
    public CompoundTag copy() {
        return copy.copy();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        return copy.copy();
    }
}
