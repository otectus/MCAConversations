package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.state.LastGift;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Per-player attachment data: the most recent accepted gift per villager, capped to the newest N
 * villager entries. Feeds the {@code last_gift_item} template variable.
 *
 * <p>The NBT shape — one compound per villager, keyed by the villager's UUID string — is unchanged
 * from the 1.20.1 Forge capability, so {@link ForgeCapsMigration} can read an old player file
 * key-for-key.
 */
public final class GiftMemoryData implements INBTSerializable<CompoundTag> {

    private final Map<UUID, LastGift> byVillager = new HashMap<>();

    public void recordGift(UUID villager, LastGift gift, int cap) {
        byVillager.put(villager, gift);
        while (byVillager.size() > cap) {
            byVillager.entrySet().stream()
                    .min(Comparator.comparingLong(e -> e.getValue().gameTime()))
                    .map(Map.Entry::getKey)
                    .ifPresent(byVillager::remove);
        }
    }

    public Optional<LastGift> lastGiftTo(UUID villager) {
        return Optional.ofNullable(byVillager.get(villager));
    }

    public int size() {
        return byVillager.size();
    }

    /**
     * True when no gift has ever been recorded.
     *
     * <p>Attachments create on read, so an absent attachment and an empty one are the same state.
     * This is the replacement for the capability era's {@code Optional.isEmpty()} test.
     */
    public boolean isEmpty() {
        return byVillager.isEmpty();
    }

    public void copyFrom(GiftMemoryData other) {
        byVillager.clear();
        byVillager.putAll(other.byVillager);
    }

    /** Reads the raw capability/attachment compound. Used by {@link ForgeCapsMigration}. */
    public static GiftMemoryData fromNbt(CompoundTag tag) {
        GiftMemoryData data = new GiftMemoryData();
        data.load(tag);
        return data;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return save();
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        load(tag);
    }

    CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        for (Map.Entry<UUID, LastGift> entry : byVillager.entrySet()) {
            tag.put(entry.getKey().toString(), entry.getValue().toNbt());
        }
        return tag;
    }

    void load(CompoundTag tag) {
        byVillager.clear();
        for (String key : tag.getAllKeys()) {
            try {
                byVillager.put(UUID.fromString(key), LastGift.fromNbt(tag.getCompound(key)));
            } catch (IllegalArgumentException ignored) {
                // Corrupt key — skip.
            }
        }
    }
}
