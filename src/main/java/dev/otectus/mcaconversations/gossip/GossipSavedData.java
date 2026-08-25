package dev.otectus.mcaconversations.gossip;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * World-global persistence for the gossip log, the relationship snapshots, and the per-village
 * residency sets, pinned to the overworld's data storage ({@code data/mcaconversations_gossip.dat}).
 * Call {@link #setDirty()} after every mutation (the mutating helpers here do).
 */
public final class GossipSavedData extends SavedData {

    private static final String DATA_NAME = "mcaconversations_gossip";

    private final GossipLog log = new GossipLog();
    private final Map<UUID, RelationshipSnapshot> snapshots = new HashMap<>();
    /** Last-seen resident UUID set per village id — drives arrival/departure diffing. */
    private final Map<Integer, Set<UUID>> residency = new HashMap<>();

    // 1.21.1 SavedData API: computeIfAbsent takes a SavedData.Factory (constructor + loader) plus
    // the file name, and both the loader and save() receive a HolderLookup.Provider. The DATA_NAME
    // and the stored payload are deliberately unchanged, so an upgraded world keeps its .dat file
    // and every record in it. The DataFixTypes is null: no vanilla data fixer applies to this file.
    public static GossipSavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(GossipSavedData::new, GossipSavedData::load, null),
                DATA_NAME);
    }

    private static GossipSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        return load(tag);
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

    /** The last-seen resident set for a village, or {@code null} if the village has never been scanned. */
    public Set<UUID> getResidency(int villageId) {
        return residency.get(villageId);
    }

    /** Records the current resident set for a village (a defensive copy is stored). */
    public void putResidency(int villageId, Set<UUID> residents) {
        residency.put(villageId, new HashSet<>(residents));
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
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
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

        CompoundTag residencyTag = new CompoundTag();
        for (Map.Entry<Integer, Set<UUID>> entry : residency.entrySet()) {
            ListTag members = new ListTag();
            for (UUID member : entry.getValue()) {
                members.add(StringTag.valueOf(member.toString()));
            }
            residencyTag.put(Integer.toString(entry.getKey()), members);
        }
        tag.put("residency", residencyTag);
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

        CompoundTag residencyTag = tag.getCompound("residency");
        for (String key : residencyTag.getAllKeys()) {
            try {
                Set<UUID> members = new HashSet<>();
                for (Tag member : residencyTag.getList(key, Tag.TAG_STRING)) {
                    members.add(UUID.fromString(member.getAsString()));
                }
                data.residency.put(Integer.parseInt(key), members);
            } catch (IllegalArgumentException ignored) {
                // Corrupt village id or member uuid — skip rather than failing the whole load.
            }
        }
        return data;
    }
}
