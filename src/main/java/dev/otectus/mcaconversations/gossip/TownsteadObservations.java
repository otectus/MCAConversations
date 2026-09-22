package dev.otectus.mcaconversations.gossip;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;

/**
 * What the gossip sweep last saw of Townstead, so the next sweep can tell what changed (Townstead
 * spec §15.1, §21.1). A versioned section of {@link GossipSavedData}.
 *
 * <p>Primitives only: strings, booleans, bounded integers, and bounded string sets. No Townstead
 * record, registry object or entity is ever written, so a world saved with Townstead loads without
 * it, and loads again when it comes back.
 */
public final class TownsteadObservations {

    public static final int FORMAT = 1;
    static final int MAX_RESIDENTS = 4096;
    static final int MAX_VILLAGES = 512;
    static final int MAX_SKILLS = 64;
    static final int MAX_BUILDINGS = 512;
    static final int MAX_STRING = 128;

    /**
     * One resident as last seen.
     *
     * @param crisis        in a need crisis, with hysteresis: set on entering one, cleared only once stable
     * @param collapsed     collapsed from exhaustion
     * @param crisisTold    a crisis or collapse was reported, so a recovery is news
     * @param lastCrisisDay the game day a crisis was last reported, for the cooldown
     * @param profession    Townstead profession id
     * @param tier          profession level
     * @param skills        learned skill ids
     * @param stage         life-stage id
     * @param age           apparent age in years, whose step is a birthday
     * @param birthdayYear  the Townstead year a birthday was last reported, so one per year; 0 until one is
     */
    public record Resident(boolean crisis, boolean collapsed, boolean crisisTold, long lastCrisisDay,
                           String profession, int tier, Set<String> skills, String stage, int age,
                           int birthdayYear, long observed) {

        public Resident {
            profession = clip(profession);
            stage = clip(stage);
            skills = bounded(skills, MAX_SKILLS);
        }
    }

    /**
     * One village as last seen.
     *
     * @param buildings      registered building id to type
     * @param missing        building id to consecutive sweeps it has been absent, before it is news
     * @param spiritTier     Townstead's own spirit tier
     */
    public record Village(Map<Integer, String> buildings, Map<Integer, Integer> missing, int spiritTier,
                          String classification, String primary, String secondary, long observed) {

        public Village {
            buildings = boundedMap(buildings);
            missing = missing == null ? Map.of() : Map.copyOf(missing);
            classification = clip(classification);
            primary = clip(primary);
            secondary = clip(secondary);
        }
    }

    private final Map<UUID, Resident> residents = new HashMap<>();
    private final Map<Integer, Village> villages = new HashMap<>();

    public Resident resident(UUID uuid) {
        return residents.get(uuid);
    }

    public void putResident(UUID uuid, Resident resident) {
        if (residents.size() < MAX_RESIDENTS || residents.containsKey(uuid)) {
            residents.put(uuid, resident);
        }
    }

    /** Forgets residents no longer anywhere in the given villages' full residency, so the section cannot grow forever. */
    public void retainResidents(Set<UUID> keep) {
        residents.keySet().retainAll(keep);
    }

    public Village village(int villageId) {
        return villages.get(villageId);
    }

    public void putVillage(int villageId, Village village) {
        if (villages.size() < MAX_VILLAGES || villages.containsKey(villageId)) {
            villages.put(villageId, village);
        }
    }

    /** Drops residents and villages not seen since {@code cutoff}; the next sight of them re-seeds quietly. */
    public boolean prune(long cutoff) {
        boolean r = residents.values().removeIf(resident -> resident.observed() < cutoff);
        boolean v = villages.values().removeIf(village -> village.observed() < cutoff);
        return r || v;
    }

    public int residentCount() {
        return residents.size();
    }

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("format", FORMAT);
        CompoundTag residentsTag = new CompoundTag();
        residents.forEach((uuid, r) -> {
            CompoundTag t = new CompoundTag();
            t.putBoolean("crisis", r.crisis());
            t.putBoolean("collapsed", r.collapsed());
            t.putBoolean("crisisTold", r.crisisTold());
            t.putLong("lastCrisisDay", r.lastCrisisDay());
            t.putString("profession", r.profession());
            t.putInt("tier", r.tier());
            ListTag skills = new ListTag();
            r.skills().forEach(s -> skills.add(StringTag.valueOf(s)));
            t.put("skills", skills);
            t.putString("stage", r.stage());
            t.putInt("age", r.age());
            t.putInt("birthdayYear", r.birthdayYear());
            t.putLong("observed", r.observed());
            residentsTag.put(uuid.toString(), t);
        });
        tag.put("residents", residentsTag);
        CompoundTag villagesTag = new CompoundTag();
        villages.forEach((id, v) -> {
            CompoundTag t = new CompoundTag();
            CompoundTag buildings = new CompoundTag();
            v.buildings().forEach((bid, type) -> buildings.putString(Integer.toString(bid), type));
            t.put("buildings", buildings);
            CompoundTag missing = new CompoundTag();
            v.missing().forEach((bid, count) -> missing.putInt(Integer.toString(bid), count));
            t.put("missing", missing);
            t.putInt("spiritTier", v.spiritTier());
            t.putString("classification", v.classification());
            t.putString("primary", v.primary());
            t.putString("secondary", v.secondary());
            t.putLong("observed", v.observed());
            villagesTag.put(Integer.toString(id), t);
        });
        tag.put("villages", villagesTag);
        return tag;
    }

    /**
     * Reads a section, tolerating everything short of a crash: a missing section is empty, a future
     * format is ignored (the next sweeps re-seed it silently), and a corrupt entry is skipped.
     */
    public static TownsteadObservations fromNbt(CompoundTag tag) {
        TownsteadObservations out = new TownsteadObservations();
        if (tag == null || tag.isEmpty() || tag.getInt("format") > FORMAT) {
            return out;
        }
        CompoundTag residentsTag = tag.getCompound("residents");
        for (String key : residentsTag.getAllKeys()) {
            try {
                CompoundTag t = residentsTag.getCompound(key);
                Set<String> skills = new TreeSet<>();
                for (Tag s : t.getList("skills", Tag.TAG_STRING)) {
                    skills.add(s.getAsString());
                }
                out.putResident(UUID.fromString(key), new Resident(t.getBoolean("crisis"), t.getBoolean("collapsed"),
                        t.getBoolean("crisisTold"), t.getLong("lastCrisisDay"), t.getString("profession"),
                        t.getInt("tier"), skills, t.getString("stage"), t.getInt("age"), t.getInt("birthdayYear"),
                        t.getLong("observed")));
            } catch (IllegalArgumentException ignored) {
                // Corrupt key: skip the entry, never the load.
            }
        }
        CompoundTag villagesTag = tag.getCompound("villages");
        for (String key : villagesTag.getAllKeys()) {
            try {
                CompoundTag t = villagesTag.getCompound(key);
                Map<Integer, String> buildings = new TreeMap<>();
                CompoundTag b = t.getCompound("buildings");
                for (String bid : b.getAllKeys()) {
                    buildings.put(Integer.parseInt(bid), b.getString(bid));
                }
                Map<Integer, Integer> missing = new TreeMap<>();
                CompoundTag m = t.getCompound("missing");
                for (String bid : m.getAllKeys()) {
                    missing.put(Integer.parseInt(bid), m.getInt(bid));
                }
                out.putVillage(Integer.parseInt(key), new Village(buildings, missing, t.getInt("spiritTier"),
                        t.getString("classification"), t.getString("primary"), t.getString("secondary"),
                        t.getLong("observed")));
            } catch (IllegalArgumentException ignored) {
                // Corrupt village id or building id: skip it.
            }
        }
        return out;
    }

    private static String clip(String value) {
        if (value == null) {
            return "";
        }
        return value.length() > MAX_STRING ? value.substring(0, MAX_STRING) : value;
    }

    private static Set<String> bounded(Set<String> values, int max) {
        if (values == null || values.isEmpty()) {
            return Set.of();
        }
        Set<String> out = new TreeSet<>();
        for (String value : new TreeSet<>(values)) {
            if (out.size() >= max) {
                break;
            }
            out.add(clip(value));
        }
        return Set.copyOf(out);
    }

    private static Map<Integer, String> boundedMap(Map<Integer, String> values) {
        if (values == null || values.isEmpty()) {
            return Map.of();
        }
        Map<Integer, String> out = new TreeMap<>();
        for (Map.Entry<Integer, String> entry : new TreeMap<>(values).entrySet()) {
            if (out.size() >= MAX_BUILDINGS) {
                break;
            }
            out.put(entry.getKey(), clip(entry.getValue()));
        }
        return Map.copyOf(out);
    }
}
