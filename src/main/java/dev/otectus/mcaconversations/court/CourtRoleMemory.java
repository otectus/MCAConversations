package dev.otectus.mcaconversations.court;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * What Conversations remembers about a court between two readings of it.
 *
 * <p>MCA Capitals is authoritative about who holds which title <em>now</em>, and that is all it will
 * say. A villager who was made Hand yesterday looks exactly like one who has been Hand for a year, so
 * "I was only just given the office" is a line no single read of Capitals can ever justify. This class
 * is the missing half: the previous answer, and the day it stopped being the answer.
 *
 * <p>Everything here is pure — plain maps and NBT, no server and no Capitals type — so the change
 * rules are ordinary JUnit territory. {@link CourtMemorySavedData} is the thin world-storage wrapper
 * that owns the {@code setDirty()} calls.
 *
 * <p><b>A first sighting is never a change.</b> A villager this mod has never observed has not just
 * been promoted; they have just been noticed. Recording that as a title change would have every
 * villager in a world announce a new office on the day the integration was installed.
 */
public final class CourtRoleMemory {

    /** Bumped only when the layout below changes shape; {@link #load} migrates from here. */
    public static final int CURRENT_VERSION = 1;

    /** A capital whose chronicle has never been read, so the poller seeds rather than announces. */
    public static final int CHRONICLE_UNSEEN = -1;

    private final Map<UUID, RoleRecord> villagers = new HashMap<>();
    private final Map<UUID, CapitalMemory> capitals = new LinkedHashMap<>();

    /** One villager's title, the title before it, and whether the change has been spoken about. */
    private record RoleRecord(String titleId, String previousTitleId, long changedDay, boolean remarked) {
    }

    /**
     * The state a capital was last seen in.
     *
     * <p>Kept as a snapshot rather than as live reads because the news poller's whole job is to diff
     * two readings, and a value it can only read now tells it nothing about what changed.
     */
    public record CapitalSnapshot(Optional<UUID> sovereign,
                                  Optional<UUID> heir,
                                  String state,
                                  boolean mourning,
                                  Map<UUID, String> relations) {

        public static final CapitalSnapshot EMPTY =
                new CapitalSnapshot(Optional.empty(), Optional.empty(), "", false, Map.of());

        public CapitalSnapshot {
            sovereign = sovereign == null ? Optional.empty() : sovereign;
            heir = heir == null ? Optional.empty() : heir;
            state = state == null ? "" : state;
            relations = relations == null ? Map.of() : Map.copyOf(relations);
        }
    }

    /** Everything remembered about one capital: its chronicle cursor and its last snapshot. */
    private record CapitalMemory(int chronicleSeen, CapitalSnapshot snapshot) {
    }

    // --- Villager titles -----------------------------------------------------------------------

    /**
     * Records this villager's current title, and a change only when it differs from the last one seen.
     *
     * @return true when something was written, so the caller knows whether to mark storage dirty
     */
    public boolean observe(UUID villager, String titleId, long today) {
        if (villager == null || titleId == null || titleId.isBlank()) {
            return false;
        }
        RoleRecord existing = villagers.get(villager);
        if (existing == null) {
            // First sighting: remembered, but not as a change. See the class javadoc.
            villagers.put(villager, new RoleRecord(titleId, "", Long.MIN_VALUE, true));
            return true;
        }
        if (existing.titleId().equals(titleId)) {
            return false;
        }
        villagers.put(villager, new RoleRecord(titleId, existing.titleId(), today, false));
        return true;
    }

    /**
     * The title this villager held before their current one, when the change is recent enough to be
     * worth mentioning and nobody has mentioned it yet.
     */
    public Optional<String> freshChange(UUID villager, long today, int windowDays) {
        RoleRecord record = villagers.get(villager);
        if (record == null || record.previousTitleId().isEmpty() || record.remarked()) {
            return Optional.empty();
        }
        long age = today - record.changedDay();
        if (age < 0L || age > windowDays) {
            return Optional.empty();
        }
        return Optional.of(record.previousTitleId());
    }

    /** The title this villager was last seen holding, if this mod has ever seen them. */
    public Optional<String> title(UUID villager) {
        RoleRecord record = villagers.get(villager);
        return record == null ? Optional.empty() : Optional.of(record.titleId());
    }

    /** Marks the change spoken about, so one promotion is never remarked on twice. */
    public boolean markRemarked(UUID villager) {
        RoleRecord record = villagers.get(villager);
        if (record == null || record.remarked()) {
            return false;
        }
        villagers.put(villager, new RoleRecord(record.titleId(), record.previousTitleId(),
                record.changedDay(), true));
        return true;
    }

    // --- Capital cursors and snapshots ---------------------------------------------------------

    /** How far this capital's chronicle has been read, or {@link #CHRONICLE_UNSEEN}. */
    public int chronicleSeen(UUID capitalId) {
        CapitalMemory memory = capitals.get(capitalId);
        return memory == null ? CHRONICLE_UNSEEN : memory.chronicleSeen();
    }

    public boolean setChronicleSeen(UUID capitalId, int index) {
        if (capitalId == null) {
            return false;
        }
        CapitalMemory existing = capitals.get(capitalId);
        if (existing != null && existing.chronicleSeen() == index) {
            return false;
        }
        capitals.put(capitalId, new CapitalMemory(index,
                existing == null ? CapitalSnapshot.EMPTY : existing.snapshot()));
        return true;
    }

    /** The state this capital was last polled in; {@link CapitalSnapshot#EMPTY} when never polled. */
    public CapitalSnapshot snapshot(UUID capitalId) {
        CapitalMemory memory = capitals.get(capitalId);
        return memory == null ? CapitalSnapshot.EMPTY : memory.snapshot();
    }

    public boolean setSnapshot(UUID capitalId, CapitalSnapshot snapshot) {
        if (capitalId == null || snapshot == null) {
            return false;
        }
        CapitalMemory existing = capitals.get(capitalId);
        if (existing != null && existing.snapshot().equals(snapshot)) {
            return false;
        }
        capitals.put(capitalId, new CapitalMemory(
                existing == null ? CHRONICLE_UNSEEN : existing.chronicleSeen(), snapshot));
        return true;
    }

    /** True when nothing has ever been observed. Used by the tests and the debug report. */
    public boolean isEmpty() {
        return villagers.isEmpty() && capitals.isEmpty();
    }

    // --- Persistence ---------------------------------------------------------------------------

    public CompoundTag save(CompoundTag tag) {
        tag.putInt("version", CURRENT_VERSION);

        ListTag capitalsTag = new ListTag();
        for (Map.Entry<UUID, CapitalMemory> entry : capitals.entrySet()) {
            CompoundTag capitalTag = new CompoundTag();
            capitalTag.putString("id", entry.getKey().toString());
            capitalTag.putInt("chronicleSeen", entry.getValue().chronicleSeen());
            CapitalSnapshot snapshot = entry.getValue().snapshot();
            snapshot.sovereign().ifPresent(uuid -> capitalTag.putString("sovereign", uuid.toString()));
            snapshot.heir().ifPresent(uuid -> capitalTag.putString("heir", uuid.toString()));
            capitalTag.putString("state", snapshot.state());
            capitalTag.putBoolean("mourning", snapshot.mourning());
            CompoundTag relationsTag = new CompoundTag();
            snapshot.relations().forEach((other, state) -> relationsTag.putString(other.toString(), state));
            capitalTag.put("relations", relationsTag);
            capitalsTag.add(capitalTag);
        }
        tag.put("capitals", capitalsTag);

        ListTag villagersTag = new ListTag();
        for (Map.Entry<UUID, RoleRecord> entry : villagers.entrySet()) {
            CompoundTag villagerTag = new CompoundTag();
            villagerTag.putString("uuid", entry.getKey().toString());
            villagerTag.putString("titleId", entry.getValue().titleId());
            villagerTag.putString("previousTitleId", entry.getValue().previousTitleId());
            villagerTag.putLong("changedDay", entry.getValue().changedDay());
            villagerTag.putBoolean("remarked", entry.getValue().remarked());
            villagersTag.add(villagerTag);
        }
        tag.put("villagers", villagersTag);
        return tag;
    }

    /**
     * Reads a saved memory. A corrupt entry is skipped rather than failing the load: the worst this
     * data can cost is one unremarked promotion, which is not worth losing a world's court state over.
     */
    public static CourtRoleMemory load(CompoundTag tag) {
        CourtRoleMemory memory = new CourtRoleMemory();
        if (tag == null) {
            return memory;
        }
        for (Tag element : tag.getList("capitals", Tag.TAG_COMPOUND)) {
            CompoundTag capitalTag = (CompoundTag) element;
            try {
                UUID id = UUID.fromString(capitalTag.getString("id"));
                Map<UUID, String> relations = new LinkedHashMap<>();
                CompoundTag relationsTag = capitalTag.getCompound("relations");
                for (String key : relationsTag.getAllKeys()) {
                    relations.put(UUID.fromString(key), relationsTag.getString(key));
                }
                CapitalSnapshot snapshot = new CapitalSnapshot(
                        optionalUuid(capitalTag, "sovereign"),
                        optionalUuid(capitalTag, "heir"),
                        capitalTag.getString("state"),
                        capitalTag.getBoolean("mourning"),
                        relations);
                memory.capitals.put(id, new CapitalMemory(
                        capitalTag.contains("chronicleSeen")
                                ? capitalTag.getInt("chronicleSeen")
                                : CHRONICLE_UNSEEN,
                        snapshot));
            } catch (IllegalArgumentException ignored) {
                // Corrupt capital or relation id — skip this capital, keep the rest.
            }
        }
        for (Tag element : tag.getList("villagers", Tag.TAG_COMPOUND)) {
            CompoundTag villagerTag = (CompoundTag) element;
            try {
                memory.villagers.put(UUID.fromString(villagerTag.getString("uuid")),
                        new RoleRecord(villagerTag.getString("titleId"),
                                villagerTag.getString("previousTitleId"),
                                villagerTag.getLong("changedDay"),
                                villagerTag.getBoolean("remarked")));
            } catch (IllegalArgumentException ignored) {
                // Corrupt villager id — skip this villager, keep the rest.
            }
        }
        return memory;
    }

    private static Optional<UUID> optionalUuid(CompoundTag tag, String key) {
        if (!tag.contains(key)) {
            return Optional.empty();
        }
        return Optional.of(UUID.fromString(tag.getString(key)));
    }
}
