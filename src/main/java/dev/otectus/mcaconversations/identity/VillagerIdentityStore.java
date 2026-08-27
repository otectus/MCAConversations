package dev.otectus.mcaconversations.identity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Every generated {@link VillagerIdentityRecord}, keyed by villager UUID (spec §6.1, §8.1).
 *
 * <p>Pure container with no Minecraft-server dependency beyond NBT, so the caps, the migration and the
 * round trip are all plain JUnit territory; {@link VillagerIdentitySavedData} is the thin world-storage
 * wrapper.
 *
 * <p><b>Never throws a profile away.</b> Unlike a cache, this store holds the only copy of who a
 * villager is. A missing version means a pre-1.5 world that simply had no identities; a newer version
 * is read with the current reader, which skips fields it does not recognise. There is deliberately no
 * eviction by count: a world with ten thousand villagers keeps ten thousand small records, and
 * {@link #removeVillager} on death is what bounds it — the same policy the progress ledger uses for
 * the same reason.
 */
public final class VillagerIdentityStore {

    public static final int CURRENT_VERSION = 1;

    private static final String KEY_VERSION = "version";
    private static final String KEY_ENTRIES = "identities";
    private static final String KEY_UUID = "uuid";
    private static final String KEY_PROFILE = "profile";

    private final Map<UUID, VillagerIdentityRecord> byVillager = new HashMap<>();

    public Optional<VillagerIdentityRecord> get(UUID villager) {
        return villager == null ? Optional.empty() : Optional.ofNullable(byVillager.get(villager));
    }

    public boolean has(UUID villager) {
        return villager != null && byVillager.containsKey(villager);
    }

    /** Stores a profile, replacing any existing one. Callers decide whether replacing is legal. */
    public void put(UUID villager, VillagerIdentityRecord record) {
        if (villager != null && record != null) {
            byVillager.put(villager, record);
        }
    }

    /**
     * Stores {@code record} only if this villager has none.
     *
     * @return the profile now in force — the existing one when there was one, so a caller that raced
     *         another thread cannot end up handing out a profile that was never persisted
     */
    public VillagerIdentityRecord putIfAbsent(UUID villager, VillagerIdentityRecord record) {
        if (villager == null || record == null) {
            return record;
        }
        VillagerIdentityRecord existing = byVillager.get(villager);
        if (existing != null) {
            return existing;
        }
        byVillager.put(villager, record);
        return record;
    }

    public void removeVillager(UUID villager) {
        if (villager != null) {
            byVillager.remove(villager);
        }
    }

    public int size() {
        return byVillager.size();
    }

    /** Rewrites every profile's token ids through the current alias table (spec §6.4). */
    public int applyAliases(IdentityCatalog catalog) {
        if (catalog == null || catalog.aliases().isEmpty()) {
            return 0;
        }
        int changed = 0;
        for (Map.Entry<UUID, VillagerIdentityRecord> entry : byVillager.entrySet()) {
            VillagerIdentityRecord updated = entry.getValue().withAliasesResolved(catalog);
            if (!updated.equals(entry.getValue())) {
                entry.setValue(updated);
                changed++;
            }
        }
        return changed;
    }

    public CompoundTag save(CompoundTag tag) {
        tag.putInt(KEY_VERSION, CURRENT_VERSION);
        ListTag entries = new ListTag();
        // Sorted so an unchanged store serialises byte-identically twice running.
        for (Map.Entry<UUID, VillagerIdentityRecord> entry : new TreeMap<>(byVillager).entrySet()) {
            CompoundTag row = new CompoundTag();
            row.putUUID(KEY_UUID, entry.getKey());
            row.put(KEY_PROFILE, entry.getValue().save());
            entries.add(row);
        }
        tag.put(KEY_ENTRIES, entries);
        return tag;
    }

    public static VillagerIdentityStore load(CompoundTag tag) {
        VillagerIdentityStore store = new VillagerIdentityStore();
        if (tag == null || !tag.contains(KEY_ENTRIES, Tag.TAG_LIST)) {
            return store;
        }
        ListTag entries = tag.getList(KEY_ENTRIES, Tag.TAG_COMPOUND);
        for (int i = 0; i < entries.size(); i++) {
            CompoundTag row = entries.getCompound(i);
            if (!row.hasUUID(KEY_UUID) || !row.contains(KEY_PROFILE, Tag.TAG_COMPOUND)) {
                // One malformed row must never cost the rest of the world its villagers.
                continue;
            }
            VillagerIdentityRecord record = VillagerIdentityRecord.load(row.getCompound(KEY_PROFILE));
            if (record != null) {
                store.byVillager.put(row.getUUID(KEY_UUID), record);
            }
        }
        return store;
    }
}
