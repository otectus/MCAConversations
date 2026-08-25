package dev.otectus.mcaconversations.progress;

import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Pure container for every {@link ProgressRecord}, keyed villager UUID → player UUID, plus the
 * guarded affection write path. No Minecraft-server dependencies beyond NBT, so every guard is plain
 * JUnit territory; {@link ProgressSavedData} is the thin world-storage wrapper.
 *
 * <p><b>Versioning (plan §11.3).</b> Unlike {@code DispositionStore}, this store must never throw a
 * player's history away. A missing version means a pre-1.1 world that simply had no progress data.
 * A <em>newer</em> version — a world opened once with a later build and then rolled back — is read
 * with the current reader, which skips fields it does not recognise and clamps everything it does.
 * Future schema changes add a migration branch here rather than a wipe.
 */
public final class ProgressStore {

    public static final int CURRENT_VERSION = 1;
    /** World-wide bound on tracked pairs; the least-recently-written record is evicted first. */
    private static final int DEFAULT_MAX_PAIRS = 4096;

    private final int maxPairs;
    private final Map<UUID, Map<UUID, ProgressRecord>> villagers = new HashMap<>();

    public ProgressStore() {
        this(DEFAULT_MAX_PAIRS);
    }

    ProgressStore(int maxPairs) {
        this.maxPairs = maxPairs;
    }

    public Optional<ProgressRecord> get(UUID villager, UUID player) {
        Map<UUID, ProgressRecord> players = villagers.get(villager);
        return players == null ? Optional.empty() : Optional.ofNullable(players.get(player));
    }

    public ProgressRecord getOrCreate(UUID villager, UUID player, long now) {
        Map<UUID, ProgressRecord> players = villagers.computeIfAbsent(villager, v -> new HashMap<>());
        ProgressRecord existing = players.get(player);
        if (existing != null) {
            return existing;
        }
        while (pairCount() >= maxPairs) {
            evictOldest();
        }
        ProgressRecord record = new ProgressRecord(now);
        players.put(player, record);
        return record;
    }

    // --- Guarded affection -----------------------------------------------------

    /**
     * Runs the full guard chain for one authored heart change and books the result. The caller is
     * responsible for actually moving MCA's hearts by {@link AffectionOutcome#granted()} — this
     * method only decides how much is allowed and records that it happened.
     *
     * <p>Order matters and is fixed: idempotency, then replay policy, then the per-conversation
     * budget, then the per-day budget. A delta stopped by an earlier guard never touches a later
     * counter, so a refused payout cannot consume budget.
     */
    public AffectionOutcome applyAffection(UUID villager, UUID player, AffectionApply directive,
                                           AffectionContext context) {
        int authored = directive.delta();
        if (authored == 0) {
            return AffectionOutcome.none(0, AffectionOutcome.Reason.ZERO);
        }
        ProgressRecord record = getOrCreate(villager, player, context.now());
        long day = AffectionMath.dayOf(context.now());
        record.rollTo(day);

        if (!record.claimTransaction(context.transactionId())) {
            return AffectionOutcome.none(authored, AffectionOutcome.Reason.DUPLICATE);
        }

        int scaled = AffectionMath.scaled(authored, context.strongerNegatives(), context.multiplier());
        if (scaled == 0) {
            record.touch(context.now());
            return new AffectionOutcome(authored, 0, 0, AffectionOutcome.Reason.ZERO);
        }

        int afterPolicy = AffectionMath.diminished(scaled, record.repeatsToday(directive.decision(), day),
                record.everApplied(directive.decision()), directive.policy());
        if (afterPolicy == 0) {
            record.touch(context.now());
            return new AffectionOutcome(authored, scaled, 0, AffectionOutcome.Reason.REPEAT);
        }

        int afterConversation = AffectionMath.clampToBudget(afterPolicy,
                context.sessionPositive(), context.budget().positiveBudget(),
                context.sessionNegative(), context.budget().negativeBudget());
        if (afterConversation == 0) {
            record.touch(context.now());
            return new AffectionOutcome(authored, scaled, 0, AffectionOutcome.Reason.CONVERSATION_BUDGET);
        }

        int granted = AffectionMath.clampToBudget(afterConversation,
                record.positiveToday(day), context.dailyPositiveCap(),
                record.negativeToday(day), context.dailyNegativeCap());
        if (granted == 0) {
            record.touch(context.now());
            return new AffectionOutcome(authored, scaled, 0, AffectionOutcome.Reason.DAILY_BUDGET);
        }

        record.recordApplied(directive.decision(), granted, day);
        record.touch(context.now());
        return new AffectionOutcome(authored, scaled, granted, AffectionOutcome.Reason.APPLIED);
    }

    // --- Maintenance -----------------------------------------------------------

    /** Drops records not written for {@code staleTicks}; returns how many were removed. */
    public int prune(long now, long staleTicks) {
        int removed = 0;
        Iterator<Map<UUID, ProgressRecord>> perVillager = villagers.values().iterator();
        while (perVillager.hasNext()) {
            Map<UUID, ProgressRecord> players = perVillager.next();
            Iterator<ProgressRecord> records = players.values().iterator();
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
        for (Map<UUID, ProgressRecord> players : villagers.values()) {
            count += players.size();
        }
        return count;
    }

    private void evictOldest() {
        UUID oldestVillager = null;
        UUID oldestPlayer = null;
        long oldestTime = Long.MAX_VALUE;
        for (Map.Entry<UUID, Map<UUID, ProgressRecord>> villagerEntry : villagers.entrySet()) {
            for (Map.Entry<UUID, ProgressRecord> playerEntry : villagerEntry.getValue().entrySet()) {
                if (playerEntry.getValue().lastUpdated() < oldestTime) {
                    oldestTime = playerEntry.getValue().lastUpdated();
                    oldestVillager = villagerEntry.getKey();
                    oldestPlayer = playerEntry.getKey();
                }
            }
        }
        if (oldestVillager != null) {
            Map<UUID, ProgressRecord> players = villagers.get(oldestVillager);
            players.remove(oldestPlayer);
            if (players.isEmpty()) {
                villagers.remove(oldestVillager);
            }
        }
    }

    // --- Serialization ---------------------------------------------------------

    public CompoundTag save(CompoundTag tag) {
        tag.putInt("version", CURRENT_VERSION);
        CompoundTag villagersTag = new CompoundTag();
        for (Map.Entry<UUID, Map<UUID, ProgressRecord>> villagerEntry : villagers.entrySet()) {
            CompoundTag playersTag = new CompoundTag();
            for (Map.Entry<UUID, ProgressRecord> playerEntry : villagerEntry.getValue().entrySet()) {
                playersTag.put(playerEntry.getKey().toString(), playerEntry.getValue().toNbt());
            }
            villagersTag.put(villagerEntry.getKey().toString(), playersTag);
        }
        tag.put("villagers", villagersTag);
        return tag;
    }

    public static ProgressStore load(CompoundTag tag) {
        ProgressStore store = new ProgressStore();
        int version = tag.contains("version") ? tag.getInt("version") : 0;
        if (version == 0) {
            // Pre-1.1 world: there was no progress ledger, so there is nothing to migrate.
            return store;
        }
        // v1 is the origin schema. A newer version is read with this reader on purpose: ProgressRecord
        // skips fields it does not know and clamps the ones it does, so a downgrade keeps arcs,
        // milestones and promises instead of silently erasing a player's history.
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
                ProgressRecord.fromNbt(playersTag.getCompound(playerKey))
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
