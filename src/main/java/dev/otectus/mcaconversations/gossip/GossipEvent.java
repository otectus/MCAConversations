package dev.otectus.mcaconversations.gossip;

import net.minecraft.nbt.CompoundTag;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * One tellable village event. Subject names are cached at detection time so gossip survives the
 * subject entity unloading or dying (a death event must still name the deceased).
 *
 * @param id         stable identity, also used for per-(villager,player) "already told" memory flags
 * @param type       what happened
 * @param villageId  MCA village the event belongs to (gossip is village-scoped in 0.1.0)
 * @param created    game time the event was detected
 * @param aUuid      primary subject
 * @param aName      primary subject's cached display name
 * @param bUuid      secondary subject (partner/parent), empty for single-subject events
 * @param bName      secondary subject's cached name, empty string when absent
 * @param attributes bounded, validated extra facts (1.8.0): a Townstead building type, a profession
 *                   level, a spirit id. {@code a_key}/{@code b_key} name a translation for a subject
 *                   that is a thing rather than a person, so a building is told in the listener's
 *                   language; the cached name is its fallback. Old saves load with none.
 */
public record GossipEvent(UUID id, GossipEventType type, int villageId, long created,
                          UUID aUuid, String aName, Optional<UUID> bUuid, String bName,
                          Map<String, String> attributes) {

    /** At most this many attributes, keys of this shape, values this long: a save never grows unbounded. */
    public static final int MAX_ATTRIBUTES = 8;
    public static final int MAX_ATTRIBUTE_LENGTH = 128;
    private static final Pattern ATTRIBUTE_KEY = Pattern.compile("[a-z_]{1,32}");

    public GossipEvent {
        attributes = sanitize(attributes);
    }

    /** An event with no attributes, as every event before 1.8.0 was. */
    public GossipEvent(UUID id, GossipEventType type, int villageId, long created,
                       UUID aUuid, String aName, Optional<UUID> bUuid, String bName) {
        this(id, type, villageId, created, aUuid, aName, bUuid, bName, Map.of());
    }

    /** True when {@code uuid} is one of the event's subjects (subjects never gossip about themselves). */
    public boolean involves(UUID uuid) {
        return aUuid.equals(uuid) || bUuid.map(uuid::equals).orElse(false);
    }

    public Optional<String> attribute(String key) {
        return Optional.ofNullable(attributes.get(key));
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
        if (!attributes.isEmpty()) {
            CompoundTag attrs = new CompoundTag();
            attributes.forEach(attrs::putString);
            tag.put("attrs", attrs);
        }
        return tag;
    }

    /** Empty when the tag is malformed (unknown type, missing uuids) — corrupt entries are skipped, not fatal. */
    public static Optional<GossipEvent> fromNbt(CompoundTag tag) {
        Optional<GossipEventType> type = GossipEventType.byJsonName(tag.getString("type"));
        if (type.isEmpty() || !tag.hasUUID("id") || !tag.hasUUID("aUuid")) {
            return Optional.empty();
        }
        Optional<UUID> bUuid = tag.hasUUID("bUuid") ? Optional.of(tag.getUUID("bUuid")) : Optional.empty();
        Map<String, String> attributes = new TreeMap<>();
        CompoundTag attrs = tag.getCompound("attrs");
        for (String key : attrs.getAllKeys()) {
            attributes.put(key, attrs.getString(key));
        }
        return Optional.of(new GossipEvent(
                tag.getUUID("id"), type.get(), tag.getInt("village"), tag.getLong("created"),
                tag.getUUID("aUuid"), tag.getString("aName"), bUuid, tag.getString("bName"), attributes));
    }

    /** Drops malformed keys, clips long values, and keeps at most {@link #MAX_ATTRIBUTES}, in key order. */
    static Map<String, String> sanitize(Map<String, String> raw) {
        if (raw == null || raw.isEmpty()) {
            return Map.of();
        }
        Map<String, String> clean = new TreeMap<>();
        for (Map.Entry<String, String> entry : new TreeMap<>(raw).entrySet()) {
            if (clean.size() >= MAX_ATTRIBUTES) {
                break;
            }
            if (entry.getKey() == null || !ATTRIBUTE_KEY.matcher(entry.getKey()).matches() || entry.getValue() == null) {
                continue;
            }
            String value = entry.getValue();
            clean.put(entry.getKey(), value.length() > MAX_ATTRIBUTE_LENGTH ? value.substring(0, MAX_ATTRIBUTE_LENGTH) : value);
        }
        return Map.copyOf(clean);
    }
}
