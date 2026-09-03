package dev.otectus.mcaconversations.history;

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
 */
public final class ConversationHistoryStore {

    /** Bumped when a field changes meaning. {@link HistoryMigration} keys its upgrades off this. */
    public static final int CURRENT_VERSION = 1;

    private static final String KEY_VERSION = "version";
    private static final String KEY_VILLAGERS = "villagers";
    private static final String KEY_UUID = "uuid";

    private final Map<UUID, VillagerHistory> byVillager = new LinkedHashMap<>();
    private int loadedVersion = CURRENT_VERSION;

    /** Read-only lookup; never creates. */
    public Optional<VillagerHistory> peek(UUID villager) {
        return villager == null ? Optional.empty() : Optional.ofNullable(byVillager.get(villager));
    }

    /**
     * The history for one villager, creating it if absent.
     *
     * <p>Evicts the least-recently-active villager when the world-wide bound is reached, the same way
     * the progress ledger does. Eviction here is genuinely lossy, which is why the bound is generous:
     * a world would need four thousand individually-conversed-with villagers to reach it.
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
            UUID victim = byVillager.entrySet().stream()
                    .filter(entry -> entry.getValue().isEmpty())
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseGet(() -> byVillager.keySet().stream().findFirst().orElse(null));
            if (victim == null) {
                break;
            }
            byVillager.remove(victim);
        }
        VillagerHistory created = new VillagerHistory();
        byVillager.put(villager, created);
        return created;
    }

    public void removeVillager(UUID villager) {
        if (villager != null) {
            byVillager.remove(villager);
        }
    }

    public int villagerCount() {
        return byVillager.size();
    }

    /** Total records across every collection — the number the save-size test asserts against. */
    public int recordCount() {
        int total = 0;
        for (VillagerHistory history : byVillager.values()) {
            total += history.episodes().size() + history.opinions().size();
            for (PairHistory pair : history.pairs().values()) {
                total += pair.threads().size() + pair.commitments().size() + pair.claims().size();
            }
        }
        return total;
    }

    /** The schema version the loaded data was written under. */
    public int loadedVersion() {
        return loadedVersion;
    }

    /** Runs every villager's pruning pass. Returns how many records were removed. */
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
        if (!tag.contains(KEY_VILLAGERS, Tag.TAG_LIST)) {
            return store;
        }
        ListTag list = tag.getList(KEY_VILLAGERS, Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag row = list.getCompound(i);
            if (!row.hasUUID(KEY_UUID)) {
                // One malformed row must never cost the rest of the world its history.
                continue;
            }
            store.byVillager.put(row.getUUID(KEY_UUID), VillagerHistory.load(row));
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
