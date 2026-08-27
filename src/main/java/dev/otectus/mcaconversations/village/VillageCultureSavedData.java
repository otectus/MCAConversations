package dev.otectus.mcaconversations.village;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * World-global persistence for village cultures ({@code data/mcaconversations_culture.dat}).
 *
 * <p>Its own file, on the same reasoning as the identity store: a culture belongs to a place and
 * outlives every villager in it, so tying it to a record with a different lifetime would mean a
 * schema change on one risking the other.
 *
 * <p>Reads never mark the world dirty. A culture is looked up on essentially every conversation in a
 * village, and a lookup that scheduled a save would be a write amplifier for a value that has not
 * changed since the village was founded.
 */
public final class VillageCultureSavedData extends SavedData {

    private static final String DATA_NAME = "mcaconversations_culture";
    private static final String KEY_VILLAGES = "villages";

    /** Bound on tracked villages, matching the order of magnitude of the other world-global stores. */
    public static final int MAX_VILLAGES = 1024;

    private final Map<Integer, VillageCultureRecord> cultures;

    private VillageCultureSavedData(Map<Integer, VillageCultureRecord> cultures) {
        this.cultures = cultures;
    }

    public static VillageCultureSavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                VillageCultureSavedData::load,
                () -> new VillageCultureSavedData(new LinkedHashMap<>()),
                DATA_NAME);
    }

    /**
     * The culture in force for {@code villageId}, following merges.
     *
     * <p>A village that was absorbed answers with the culture it was absorbed into, which is what
     * keeps a resident who came across from being handed a culture nobody there lives in any more.
     */
    public Optional<VillageCultureRecord> peek(int villageId) {
        VillageCultureRecord direct = cultures.get(villageId);
        if (direct != null) {
            return Optional.of(direct);
        }
        for (VillageCultureRecord record : cultures.values()) {
            if (record.answersFor(villageId)) {
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }

    /**
     * Stores a freshly generated culture unless the village already has one.
     *
     * @return the culture now in force, which is the pre-existing one on a race
     */
    public VillageCultureRecord putIfAbsent(VillageCultureRecord record) {
        if (record == null || !record.isComplete()) {
            return record;
        }
        Optional<VillageCultureRecord> existing = peek(record.villageId());
        if (existing.isPresent()) {
            return existing.get();
        }
        if (cultures.size() >= MAX_VILLAGES) {
            return record;
        }
        cultures.put(record.villageId(), record);
        setDirty();
        return record;
    }

    /**
     * Folds {@code absorbedId}'s culture into {@code survivingId}'s (spec §17.3 "migrated on merge").
     *
     * <p>The surviving village keeps its own six tokens and gains a note that it took the other one
     * in. Blending them would produce a place neither set of residents had ever lived in; recording
     * the merge means the old id keeps resolving, so a villager who moved across is answered rather
     * than treated as a wanderer.
     *
     * @return true when something actually changed
     */
    public boolean merge(int absorbedId, int survivingId) {
        if (absorbedId == survivingId) {
            return false;
        }
        VillageCultureRecord surviving = cultures.get(survivingId);
        if (surviving == null) {
            return false;
        }
        VillageCultureRecord absorbed = cultures.remove(absorbedId);
        VillageCultureRecord merged = absorbed == null
                ? surviving.absorbing(new VillageCultureRecord(
                        absorbedId, surviving.tokens(), surviving.createdDay(), java.util.Set.of()))
                : surviving.absorbing(absorbed);
        if (merged.equals(surviving) && absorbed == null) {
            return false;
        }
        cultures.put(survivingId, merged);
        setDirty();
        return true;
    }

    /** Forgets a village entirely, for a settlement that is genuinely gone. */
    public boolean forget(int villageId) {
        if (cultures.remove(villageId) == null) {
            return false;
        }
        setDirty();
        return true;
    }

    public int size() {
        return cultures.size();
    }

    private static VillageCultureSavedData load(CompoundTag tag) {
        Map<Integer, VillageCultureRecord> cultures = new LinkedHashMap<>();
        if (tag != null && tag.contains(KEY_VILLAGES, Tag.TAG_LIST)) {
            ListTag list = tag.getList(KEY_VILLAGES, Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                VillageCultureRecord.load(list.getCompound(i))
                        .filter(VillageCultureRecord::isComplete)
                        .ifPresent(record -> cultures.put(record.villageId(), record));
            }
        }
        return new VillageCultureSavedData(cultures);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (VillageCultureRecord record : new java.util.TreeMap<>(cultures).values()) {
            list.add(record.save());
        }
        tag.put(KEY_VILLAGES, list);
        return tag;
    }
}
