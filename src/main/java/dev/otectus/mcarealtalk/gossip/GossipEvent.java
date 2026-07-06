package dev.otectus.mcarealtalk.gossip;

import net.minecraft.nbt.CompoundTag;

import java.util.Optional;
import java.util.UUID;

/**
 * One tellable village event. Subject names are cached at detection time so gossip survives the
 * subject entity unloading or dying (a death event must still name the deceased).
 *
 * @param id        stable identity, also used for per-(villager,player) "already told" memory flags
 * @param type      what happened
 * @param villageId MCA village the event belongs to (gossip is village-scoped in 0.1.0)
 * @param created   game time the event was detected
 * @param aUuid     primary subject
 * @param aName     primary subject's cached display name
 * @param bUuid     secondary subject (partner/parent), empty for single-subject events
 * @param bName     secondary subject's cached name, empty string when absent
 */
public record GossipEvent(UUID id, GossipEventType type, int villageId, long created,
                          UUID aUuid, String aName, Optional<UUID> bUuid, String bName) {

    /** True when {@code uuid} is one of the event's subjects (subjects never gossip about themselves). */
    public boolean involves(UUID uuid) {
        return aUuid.equals(uuid) || bUuid.map(uuid::equals).orElse(false);
    }

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        tag.putUUID("id", id);
        tag.putString("type", type.jsonName());
        tag.putInt("village", villageId);
        tag.putLong("created", created);
        tag.putUUID("aUuid", aUuid);
        tag.putString("aName", aName);
        bUuid.ifPresent(b -> tag.putUUID("bUuid", b));
        tag.putString("bName", bName);
        return tag;
    }

    /** Empty when the tag is malformed (unknown type, missing uuids) — corrupt entries are skipped, not fatal. */
    public static Optional<GossipEvent> fromNbt(CompoundTag tag) {
        Optional<GossipEventType> type = GossipEventType.byJsonName(tag.getString("type"));
        if (type.isEmpty() || !tag.hasUUID("id") || !tag.hasUUID("aUuid")) {
            return Optional.empty();
        }
        Optional<UUID> bUuid = tag.hasUUID("bUuid") ? Optional.of(tag.getUUID("bUuid")) : Optional.empty();
        return Optional.of(new GossipEvent(
                tag.getUUID("id"), type.get(), tag.getInt("village"), tag.getLong("created"),
                tag.getUUID("aUuid"), tag.getString("aName"), bUuid, tag.getString("bName")));
    }
}
