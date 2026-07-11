package dev.otectus.mcaconversations.disposition;

import net.minecraft.nbt.CompoundTag;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.ToIntFunction;

/**
 * Pure container for every {@link DispositionRecord}, keyed villager UUID → player UUID. This class
 * has no Minecraft-server dependencies beyond NBT so the clamp/migrate/prune/guard paths are plain
 * JUnit territory; {@link DispositionSavedData} is the thin world-storage wrapper around it.
 *
 * <p><b>Versioning:</b> {@link #save} stamps {@link #CURRENT_VERSION}. {@link #load} of a missing,
 * older-unknown, or future version yields an empty store — a pre-1.0 world simply has no data and
 * every read falls back to the personality baseline, which is the documented migration path.
 */
public final class DispositionStore {

    public static final int CURRENT_VERSION = 1;
    /** World-wide bound on tracked pairs; the least-recently-written record is evicted first. */
    private static final int DEFAULT_MAX_PAIRS = 4096;

    private final int maxPairs;
    private final Map<UUID, Map<UUID, DispositionRecord>> villagers = new HashMap<>();

    public DispositionStore() {
        this(DEFAULT_MAX_PAIRS);
    }

    DispositionStore(int maxPairs) {
        this.maxPairs = maxPairs;
    }

    public Optional<DispositionRecord> get(UUID villager, UUID player) {
        Map<UUID, DispositionRecord> players = villagers.get(villager);
        return players == null ? Optional.empty() : Optional.ofNullable(players.get(player));
    }

    public DispositionRecord getOrCreate(UUID villager, UUID player, long now) {
        Map<UUID, DispositionRecord> players = villagers.computeIfAbsent(villager, v -> new HashMap<>());
        DispositionRecord existing = players.get(player);
        if (existing != null) {
            return existing;
        }
        while (pairCount() >= maxPairs) {
            evictOldest();
        }
        DispositionRecord record = new DispositionRecord(now);
        players.put(player, record);
        return record;
    }

    /**
     * The decayed view of one axis — the value dialogue conditions and checks consume. Absent
     * records read as the personality baseline; reading never mutates stored state.
     */
    public int readAxis(UUID villager, UUID player, DispositionAxis axis, int baseline, long now,
                        double decayMultiplier) {
        return get(villager, player)
                .map(record -> DispositionMath.decayed(axis, record.axisRaw(axis), baseline,
                        now - record.lastUpdated(), decayMultiplier))
                .orElse(DispositionMath.clamp(axis, baseline));
    }

    /**
     * The single write path: folds pending decay into every axis, then applies each requested delta
     * through {@link FarmingGuard}, clamps, and advances the counters and decay anchor.
     *
     * @return the deltas actually applied after guards (for debug logging)
     */
    public Map<DispositionAxis, Integer> apply(UUID villager, UUID player, String topic,
                                               Map<DispositionAxis, Integer> deltas,
                                               ToIntFunction<DispositionAxis> baselines, long now,
                                               int dailyCap, double gainMultiplier, double decayMultiplier) {
        DispositionRecord record = getOrCreate(villager, player, now);
        long elapsed = now - record.lastUpdated();
        for (DispositionAxis axis : DispositionAxis.values()) {
            record.setAxis(axis, DispositionMath.decayed(axis, record.axisRaw(axis),
                    baselines.applyAsInt(axis), elapsed, decayMultiplier));
        }

        long day = Math.floorDiv(now, 24_000L);
        int repeat = record.repeatCountToday(topic, day);
        Map<DispositionAxis, Integer> applied = new EnumMap<>(DispositionAxis.class);
        for (Map.Entry<DispositionAxis, Integer> entry : deltas.entrySet()) {
            DispositionAxis axis = entry.getKey();
            int guarded = FarmingGuard.guardedDelta(entry.getValue(),
                    record.gainedToday(axis, day), dailyCap, repeat, gainMultiplier);
            record.setAxis(axis, record.axisRaw(axis) + guarded);
            record.addGained(axis, day, Math.abs(guarded));
            applied.put(axis, guarded);
        }
        record.recordStance(topic, day);
        record.touch(now);
        return applied;
    }

    /** Drops records not written for {@code staleTicks}; returns how many were removed. */
    public int prune(long now, long staleTicks) {
        int removed = 0;
        Iterator<Map<UUID, DispositionRecord>> perVillager = villagers.values().iterator();
        while (perVillager.hasNext()) {
            Map<UUID, DispositionRecord> players = perVillager.next();
            Iterator<DispositionRecord> records = players.values().iterator();
            while (records.hasNext()) {
                if (records.next().lastUpdated() < now - staleTicks) {
                    records.remove();
                    removed++;
                }
            }
            if (players.isEmpty()) {
                perVillager.remove();
            }
        }
        return removed;
    }

    /** Drops every player's record for a villager (called when the villager entity dies). */
    public void removeVillager(UUID villager) {
        villagers.remove(villager);
    }

    public int pairCount() {
        int count = 0;
        for (Map<UUID, DispositionRecord> players : villagers.values()) {
            count += players.size();
        }
        return count;
    }

    private void evictOldest() {
        UUID oldestVillager = null;
        UUID oldestPlayer = null;
        long oldestTime = Long.MAX_VALUE;
        for (Map.Entry<UUID, Map<UUID, DispositionRecord>> villagerEntry : villagers.entrySet()) {
            for (Map.Entry<UUID, DispositionRecord> playerEntry : villagerEntry.getValue().entrySet()) {
                if (playerEntry.getValue().lastUpdated() < oldestTime) {
                    oldestTime = playerEntry.getValue().lastUpdated();
                    oldestVillager = villagerEntry.getKey();
                    oldestPlayer = playerEntry.getKey();
                }
            }
        }
        if (oldestVillager != null) {
            Map<UUID, DispositionRecord> players = villagers.get(oldestVillager);
            players.remove(oldestPlayer);
            if (players.isEmpty()) {
                villagers.remove(oldestVillager);
            }
        }
    }

    public CompoundTag save(CompoundTag tag) {
        tag.putInt("version", CURRENT_VERSION);
        CompoundTag villagersTag = new CompoundTag();
        for (Map.Entry<UUID, Map<UUID, DispositionRecord>> villagerEntry : villagers.entrySet()) {
            CompoundTag playersTag = new CompoundTag();
            for (Map.Entry<UUID, DispositionRecord> playerEntry : villagerEntry.getValue().entrySet()) {
                playersTag.put(playerEntry.getKey().toString(), playerEntry.getValue().toNbt());
            }
            villagersTag.put(villagerEntry.getKey().toString(), playersTag);
        }
        tag.put("villagers", villagersTag);
        return tag;
    }

    public static DispositionStore load(CompoundTag tag) {
        DispositionStore store = new DispositionStore();
        if (tag.getInt("version") != CURRENT_VERSION) {
            // Unversioned (foreign/pre-1.0) or future data: start empty rather than misread it.
            return store;
        }
        CompoundTag villagersTag = tag.getCompound("villagers");
        for (String villagerKey : villagersTag.getAllKeys()) {
            UUID villager = parseUuid(villagerKey);
            if (villager == null) {
                continue;
            }
            CompoundTag playersTag = villagersTag.getCompound(villagerKey);
            for (String playerKey : playersTag.getAllKeys()) {
                UUID player = parseUuid(playerKey);
                if (player == null) {
                    continue;
                }
                DispositionRecord.fromNbt(playersTag.getCompound(playerKey))
                        .ifPresent(record -> store.villagers
                                .computeIfAbsent(villager, v -> new HashMap<>())
                                .put(player, record));
            }
        }
        return store;
    }

    private static UUID parseUuid(String key) {
        try {
            return UUID.fromString(key);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
