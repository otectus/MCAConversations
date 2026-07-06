package dev.otectus.mcarealtalk.gossip;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * World-global persistence for the gossip log and the relationship snapshots, pinned to the
 * overworld's data storage ({@code data/mcarealtalk_gossip.dat}). Call {@link #setDirty()} after
 * every mutation (the mutating helpers here do).
 */
public final class GossipSavedData extends SavedData {

    private static final String DATA_NAME = "mcarealtalk_gossip";

    private final GossipLog log = new GossipLog();
    private final Map<UUID, RelationshipSnapshot> snapshots = new HashMap<>();

    public static GossipSavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage()
                .computeIfAbsent(GossipSavedData::load, GossipSavedData::new, DATA_NAME);
    }

    public GossipLog log() {
        return log;
    }

    public Map<UUID, RelationshipSnapshot> snapshots() {
        return snapshots;
    }

    public boolean addEvent(GossipEvent event, int maxPerVillage) {
        boolean added = log.add(event, maxPerVillage);
        if (added) {
            setDirty();
        }
        return added;
    }

    public void putSnapshot(UUID villager, RelationshipSnapshot snapshot) {
        snapshots.put(villager, snapshot);
        setDirty();
    }

    public int prune(long now, long retentionTicks) {
        int removed = log.pruneOlderThan(now, retentionTicks);
        if (removed > 0) {
            setDirty();
        }
        return removed;
    }

    public void clearEvents() {
        log.clear();
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag eventsTag = new ListTag();
        for (GossipEvent event : log.events()) {
            eventsTag.add(event.toNbt());
        }
        tag.put("events", eventsTag);

        CompoundTag snapshotsTag = new CompoundTag();
        for (Map.Entry<UUID, RelationshipSnapshot> entry : snapshots.entrySet()) {
            snapshotsTag.put(entry.getKey().toString(), entry.getValue().toNbt());
        }
        tag.put("snapshots", snapshotsTag);
        return tag;
    }

    public static GossipSavedData load(CompoundTag tag) {
        GossipSavedData data = new GossipSavedData();
        for (Tag t : tag.getList("events", Tag.TAG_COMPOUND)) {
            GossipEvent.fromNbt((CompoundTag) t).ifPresent(data.log::addRaw);
        }
        CompoundTag snapshotsTag = tag.getCompound("snapshots");
        for (String key : snapshotsTag.getAllKeys()) {
            try {
                data.snapshots.put(UUID.fromString(key), RelationshipSnapshot.fromNbt(snapshotsTag.getCompound(key)));
            } catch (IllegalArgumentException ignored) {
                // Corrupt key — skip the entry rather than failing the whole load.
            }
        }
        return data;
    }
}
