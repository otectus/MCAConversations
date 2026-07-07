package dev.otectus.mcaconversations.gossip;

import net.minecraft.nbt.CompoundTag;

import java.util.Optional;
import java.util.UUID;

/**
 * What the gossip scanner last saw for one villager — diffed each scan to derive
 * marriage/divorce/birth events. Name is cached so later events can reference villagers that have
 * since unloaded.
 *
 * @param partner      spouse UUID at last scan, empty when unmarried
 * @param name         cached display name
 * @param wasBaby      age state was BABY at last scan (suppresses birth re-detection)
 * @param lastSeenTime game time of the last scan that saw this villager
 */
public record RelationshipSnapshot(Optional<UUID> partner, String name, boolean wasBaby, long lastSeenTime) {

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        partner.ifPresent(p -> tag.putUUID("partner", p));
        tag.putString("name", name);
        tag.putBoolean("wasBaby", wasBaby);
        tag.putLong("lastSeen", lastSeenTime);
        return tag;
    }

    public static RelationshipSnapshot fromNbt(CompoundTag tag) {
        Optional<UUID> partner = tag.hasUUID("partner") ? Optional.of(tag.getUUID("partner")) : Optional.empty();
        return new RelationshipSnapshot(partner, tag.getString("name"), tag.getBoolean("wasBaby"), tag.getLong("lastSeen"));
    }
}
