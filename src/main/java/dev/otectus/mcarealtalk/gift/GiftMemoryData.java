package dev.otectus.mcarealtalk.gift;

import dev.otectus.mcarealtalk.state.LastGift;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Per-player capability data: the most recent accepted gift per villager, capped to the newest N
 * villager entries. Feeds the {@code last_gift_item} template variable.
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

    public void copyFrom(GiftMemoryData other) {
        byVillager.clear();
        byVillager.putAll(other.byVillager);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        for (Map.Entry<UUID, LastGift> entry : byVillager.entrySet()) {
            tag.put(entry.getKey().toString(), entry.getValue().toNbt());
        }
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
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
