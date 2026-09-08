package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Every villager's narrative history, and the schema version it was written under (spec §8, §22.1).
 *
 * <p>Pure container: no Minecraft-server dependency beyond NBT, so every cap, every prune order and
 * the whole round trip are plain JUnit territory. {@link ConversationHistorySavedData} is the thin
 * world-storage wrapper.
 *
 * <p><b>Versioning policy, stated once.</b> A missing version means a world that predates the history
 * store and simply had none. A <em>newer</em> version — a world opened once under a later build and
 * rolled back — is read with the current reader, which skips what it does not recognise and clamps
 * what it does. Nothing here ever wipes a player's history because a field moved; that is what
 * {@link HistoryMigration} exists to avoid.
 *
 * <p><b>Degraded mode.</b> Reading a newer file is safe; <em>writing</em> one is not, because this
 * build would write back only the keys it understands and silently delete the rest. So a store loaded
 * from a newer schema keeps the verbatim tag it was given, still serves everything it
 * recognised — the conversations keep working — and writes that copy back unchanged if it is ever
 * asked to save at all. {@link ConversationHistorySavedData} refuses to mark such a world dirty, so in
 * practice it is never asked. This is the single owner of that decision: the migration does not
 * warn about it and the wrapper does not decide it.
 */
public final class ConversationHistoryStore {

    /** Bumped when a field changes meaning. {@link HistoryMigration} keys its upgrades off this. */
    public static final int CURRENT_VERSION = 1;

    private static final String KEY_VERSION = "version";
    private static final String KEY_VILLAGERS = "villagers";
    private static final String KEY_UUID = "uuid";

    /** Diagnostics spent on a store with nothing evictable before the counter speaks for the rest. */
    private static final int CROWDED_REPORTS = 4;

    private final Map<UUID, VillagerHistory> byVillager = new LinkedHashMap<>();
    private int loadedVersion = CURRENT_VERSION;
    private boolean degraded;
    private CompoundTag retainedTag;
    private int discardedOnLoad;
    private CompoundTag originalTag;
    private int crowdedReports;

    /**
     * Whether a villager is in a live conversation right now, injected by the server-start handler.
     *
     * <p>A default of "nobody is" keeps the store a pure object: unit tests, reports and the datagen
     * pass all build one without a server, and the seam is a {@link Predicate} rather than a call into
     * {@code conversation/} so this package keeps depending on nothing but NBT.
     */
    private Predicate<UUID> liveSessions = id -> false;

    /** Read-only lookup; never creates. */
    public Optional<VillagerHistory> peek(UUID villager) {
        return villager == null ? Optional.empty() : Optional.ofNullable(byVillager.get(villager));
    }

    /**
     * The history for one villager, creating it if absent.
     *
     * <p>Evicts when the world-wide bound is reached, in this order:
     *
     * <ol>
     *   <li><b>Protected villagers are skipped entirely</b> — anyone holding an unresolved commitment
     *       or a live thread with any player, and anyone a player is talking to at this moment. An
     *       obligation is the one thing a cap may never buy room with;</li>
     *   <li>then an <b>empty</b> history, which costs nothing to forget;</li>
     *   <li>then the <b>lowest last-activity day</b>, so the villager forgotten is the one nobody has
     *       spoken to for longest rather than the one this world happened to meet first;</li>
     *   <li>then the <b>smallest UUID</b>, so two servers handed the same world forget the same
     *       villager.</li>
     * </ol>
     *
     * <p>When every candidate is protected the store refuses to evict and hands back a detached
     * history: the new villager is simply not remembered, which is a smaller loss than dropping
     * somebody's promise to make room for them. A world would need four thousand villagers each owed
     * something to reach that, so it is a diagnostic rather than a warning.
     */
    public VillagerHistory getOrCreate(UUID villager) {
        if (villager == null) {
            return new VillagerHistory();
        }
        VillagerHistory existing = byVillager.get(villager);
        if (existing != null) {
            return existing;
        }
        while (byVillager.size() >= HistoryCaps.HARD_VILLAGERS) {
            UUID victim = evictionCandidate();
            if (victim == null) {
                if (crowdedReports++ < CROWDED_REPORTS) {
                    McaConversations.LOGGER.debug(
                            "History is at its {} villager bound and every entry is protected by an "
                                    + "obligation or a live conversation; {} is not being remembered.",
                            HistoryCaps.HARD_VILLAGERS, villager);
                }
                return new VillagerHistory();
            }
            byVillager.remove(victim);
        }
        VillagerHistory created = new VillagerHistory();
        byVillager.put(villager, created);
        return created;
    }

    /** The villager eviction would take next, or null when every one of them is protected. */
    private UUID evictionCandidate() {
        UUID emptiest = null;
        UUID stalest = null;
        long stalestDay = Long.MAX_VALUE;
        for (Map.Entry<UUID, VillagerHistory> entry : byVillager.entrySet()) {
            UUID id = entry.getKey();
            VillagerHistory history = entry.getValue();
            if (liveSessions.test(id) || history.isProtected()) {
                continue;
            }
            if (history.isEmpty()) {
                if (emptiest == null || id.compareTo(emptiest) < 0) {
                    emptiest = id;
                }
                continue;
            }
            long day = history.lastActivityDay().orElse(Long.MIN_VALUE);
            if (day < stalestDay || (day == stalestDay && stalest != null && id.compareTo(stalest) < 0)) {
                stalestDay = day;
                stalest = id;
            }
        }
        return emptiest != null ? emptiest : stalest;
    }

    /**
     * Tells the store which villagers are mid-conversation, so eviction never pulls the history out
     * from under a screen that is open. Set at server start, cleared at server stop.
     */
    public void setLiveSessionPredicate(Predicate<UUID> predicate) {
        liveSessions = predicate == null ? id -> false : predicate;
    }

    public void removeVillager(UUID villager) {
        if (villager != null) {
            byVillager.remove(villager);
        }
    }

    public int villagerCount() {
        return byVillager.size();
    }

    /**
     * Total records across every collection — the number the save-size test asserts against.
     *
     * <p>Counts the observed roles and the four recency levels too. They are as much of the file as
     * anything else, and a count that omitted them made the growth budget read low by exactly the
     * collections nothing else was watching.
     */
    public int recordCount() {
        int total = 0;
        for (VillagerHistory history : byVillager.values()) {
            total += history.episodes().size() + history.opinions().size() + history.roles().size();
            for (PairHistory pair : history.pairs().values()) {
                total += pair.threads().size() + pair.commitments().size() + pair.claims().size();
                TopicRecencyRecord recency = pair.recency();
                total += recency.scenes().size() + recency.subjects().size()
                        + recency.shapes().size() + recency.topics().size();
            }
        }
        return total;
    }

    /**
     * True when this store was written by a newer build and is therefore read-only.
     *
     * <p>Everything recognised is still served — a degraded world holds ordinary conversations — but
     * nothing is ever written back except the bytes that were read.
     */
    public boolean isDegraded() {
        return degraded;
    }

    /**
     * How many records the load discarded to fit the caps, truncated strings included.
     *
     * <p>Non-zero means the file on disk held more than the caps allow and this store is not a
     * complete copy of it, which is what the world-storage wrapper takes as its cue to keep a backup.
     */
    public int discardedOnLoad() {
        return discardedOnLoad;
    }

    /**
     * The tag this store was loaded from, when the load had to discard something; empty otherwise.
     *
     * <p>Only ever a read of what was already on disk. The wrapper writes it to a sibling file so the
     * discarded records are recoverable by hand; nothing in the mod reads it back.
     */
    public Optional<CompoundTag> originalTagIfTruncated() {
        return Optional.ofNullable(originalTag);
    }

    /** The schema version the loaded data was written under. */
    public int loadedVersion() {
        return loadedVersion;
    }

    /** Runs every villager's pruning pass. Returns the number of removals and state transitions. */
    public int prune(long today) {
        int removed = 0;
        List<UUID> empties = new ArrayList<>();
        for (Map.Entry<UUID, VillagerHistory> entry : byVillager.entrySet()) {
            removed += entry.getValue().prune(today);
            if (entry.getValue().isEmpty()) {
                empties.add(entry.getKey());
            }
        }
        for (UUID villager : empties) {
            byVillager.remove(villager);
        }
        return removed;
    }

    public CompoundTag save(CompoundTag tag) {
        if (degraded) {
            // The very tag that was read, so what is written back is byte-for-byte what was found.
            // Writing the recognised keys instead would delete the newer build's fields, which is
            // precisely the loss degraded mode exists to prevent. Nothing mutates it: the loader is
            // the only holder of the tag read from disk, and this store only ever hands it on.
            return retainedTag;
        }
        tag.putInt(KEY_VERSION, CURRENT_VERSION);
        ListTag list = new ListTag();
        // Sorted so an unchanged store serialises byte-identically twice running; an unstable order
        // would rewrite the world's data file on every save for no reason.
        for (Map.Entry<UUID, VillagerHistory> entry : new TreeMap<>(byVillager).entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            CompoundTag row = entry.getValue().save();
            row.putUUID(KEY_UUID, entry.getKey());
            list.add(row);
        }
        tag.put(KEY_VILLAGERS, list);
        return tag;
    }

    public static ConversationHistoryStore load(CompoundTag tag) {
        ConversationHistoryStore store = new ConversationHistoryStore();
        if (tag == null) {
            return store;
        }
        store.loadedVersion = tag.contains(KEY_VERSION) ? tag.getInt(KEY_VERSION) : 0;
        if (store.loadedVersion > CURRENT_VERSION) {
            store.degraded = true;
            store.retainedTag = tag;
            McaConversations.LOGGER.warn(
                    "Conversation history was written by a newer build (schema {} > {}). Everything this "
                            + "build understands is loaded and villagers will talk normally, but the file "
                            + "is read-only until the mod is upgraded, so nothing new is remembered.",
                    store.loadedVersion, CURRENT_VERSION);
        }
        if (!tag.contains(KEY_VILLAGERS, Tag.TAG_LIST)) {
            return store;
        }
        long truncationsBefore = HistoryCaps.truncations();
        ListTag list = tag.getList(KEY_VILLAGERS, Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag row = list.getCompound(i);
            if (!row.hasUUID(KEY_UUID)) {
                // One malformed row must never cost the rest of the world its history.
                continue;
            }
            VillagerHistory history = VillagerHistory.load(row);
            store.discardedOnLoad += history.discardedOnLoad();
            store.byVillager.put(row.getUUID(KEY_UUID), history);
        }
        // The world-wide bound, applied through the very method the live path evicts with, so a file
        // over the bound keeps the villagers that path would have kept: never a protected one, empty
        // records first, then the least recently active.
        while (store.byVillager.size() > HistoryCaps.HARD_VILLAGERS) {
            UUID victim = store.evictionCandidate();
            if (victim == null) {
                break;
            }
            store.byVillager.remove(victim);
            store.discardedOnLoad++;
        }
        store.discardedOnLoad += (int) Math.min(Integer.MAX_VALUE,
                HistoryCaps.truncations() - truncationsBefore);
        if (store.discardedOnLoad > 0) {
            store.originalTag = tag;
        }
        HistoryMigration.migrate(store, store.loadedVersion);
        return store;
    }

    /** Every tracked villager, for reports and the inspect command. */
    public List<UUID> villagers() {
        List<UUID> out = new ArrayList<>(byVillager.keySet());
        out.sort(Comparator.naturalOrder());
        return List.copyOf(out);
    }
}
