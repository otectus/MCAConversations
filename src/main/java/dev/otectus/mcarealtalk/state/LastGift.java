package dev.otectus.mcarealtalk.state;

import net.minecraft.nbt.CompoundTag;

/**
 * A remembered gift: what item (registry id), how many, and when (game time). Pure data —
 * resolution of the item id to a display name happens at template time.
 */
public record LastGift(String itemId, int count, long gameTime) {

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        tag.putString("item", itemId);
        tag.putInt("count", count);
        tag.putLong("time", gameTime);
        return tag;
    }

    public static LastGift fromNbt(CompoundTag tag) {
        return new LastGift(tag.getString("item"), tag.getInt("count"), tag.getLong("time"));
    }
}
