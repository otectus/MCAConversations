package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.McaConversations;

/**
 * Field-by-field upgrades between history schema versions (spec §22.1).
 *
 * <p>Exists as its own class from version 1, with nothing to do, because the shape of the thing
 * matters more than its current contents: the first schema change must have somewhere obvious to go
 * and a fixture to prove it, rather than being bolted into a loader that already works.
 *
 * <p>Three rules govern everything added here.
 *
 * <ol>
 *   <li><b>Idempotent.</b> Running a migration twice must leave the same data, because a world can be
 *       opened, closed without saving, and opened again.</li>
 *   <li><b>Never destructive.</b> A record the migration does not understand is left alone, not
 *       dropped. The existing {@code mcaconversations_progress} and disposition files are not touched
 *       at all — a new store is not a reason to delete an old one.</li>
 *   <li><b>Forward-tolerant.</b> A version <em>newer</em> than this build is read as-is. A player who
 *       tried a later build and rolled back keeps their villagers.</li>
 * </ol>
 */
public final class HistoryMigration {

    private HistoryMigration() {
    }

    /**
     * Upgrades {@code store} in place from {@code fromVersion} to
     * {@link ConversationHistoryStore#CURRENT_VERSION}.
     *
     * @param fromVersion the version the data was written under; 0 for a world that had no history
     */
    public static void migrate(ConversationHistoryStore store, int fromVersion) {
        if (store == null) {
            return;
        }
        if (fromVersion > ConversationHistoryStore.CURRENT_VERSION) {
            McaConversations.LOGGER.warn(
                    "Conversation history was written by a newer build (schema {} > {}). Reading it with the "
                            + "current reader: unknown fields are ignored and nothing is discarded.",
                    fromVersion, ConversationHistoryStore.CURRENT_VERSION);
            return;
        }
        if (fromVersion == 0) {
            // A world that predates the history store. Nothing to convert: the 1.4.0 arcs, milestones
            // and exclusive choices stay authoritative in their own file and are read through as they
            // always were. Backfilling a fabricated first meeting from a familiarity number is exactly
            // what §22.2 forbids, so a long-known pair simply starts with no shared-event record and
            // the honest "I don't remember the first words" route.
            return;
        }
        // Future upgrades chain here, each guarded by the version it starts from:
        //     if (fromVersion < 2) { upgradeOneToTwo(store); }
    }
}
