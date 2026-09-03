package dev.otectus.mcaconversations.village;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * What one village is like, as its residents would all agree it is (spec §17.3).
 *
 * <p>Six tokens, one per family, plus the day the village acquired them and the ids of any villages
 * that have since been absorbed into it. The absorbed list is not bookkeeping for its own sake: when
 * two villages merge, MCA keeps one id and drops the other, and without a record of the old id every
 * resident who came across would silently be handed a culture they had never lived in. Keeping the
 * old id lets the store answer for it and lets a scene know the difference.
 *
 * @param villageId   the stable village id this culture belongs to
 * @param tokens      one token id per family
 * @param createdDay  the day the culture was first generated
 * @param absorbed    village ids that have been merged into this one
 */
public record VillageCultureRecord(int villageId,
                                   Map<CultureFamily, String> tokens,
                                   long createdDay,
                                   Set<Integer> absorbed) {

    /** Bound on how many merges one village records, so a long-lived world cannot grow this forever. */
    public static final int MAX_ABSORBED = 16;

    private static final String KEY_VILLAGE = "village";
    private static final String KEY_TOKENS = "tokens";
    private static final String KEY_CREATED = "created";
    private static final String KEY_ABSORBED = "absorbed";

    public VillageCultureRecord {
        Map<CultureFamily, String> copy = new EnumMap<>(CultureFamily.class);
        if (tokens != null) {
            tokens.forEach((family, id) -> {
                if (family != null && id != null && !id.isBlank()) {
                    copy.put(family, id.trim().toLowerCase(java.util.Locale.ROOT));
                }
            });
        }
        tokens = Map.copyOf(copy);
        Set<Integer> ids = new TreeSet<>();
        if (absorbed != null) {
            for (Integer id : absorbed) {
                if (id != null && ids.size() < MAX_ABSORBED) {
                    ids.add(id);
                }
            }
        }
        absorbed = Set.copyOf(ids);
    }

    /** True when every family is filled, which is the only state worth speaking from. */
    public boolean isComplete() {
        return tokens.size() == CultureFamily.values().length;
    }

    public Optional<String> token(CultureFamily family) {
        return family == null ? Optional.empty() : Optional.ofNullable(tokens.get(family));
    }

    /** Every token id, for the context snapshot and for {@code conversations_culture}. */
    public Set<String> tokenIds() {
        return Set.copyOf(new LinkedHashSet<>(tokens.values()));
    }

    /** True when this record answers for {@code id}, either as itself or as a village it absorbed. */
    public boolean answersFor(int id) {
        return villageId == id || absorbed.contains(id);
    }

    /**
     * This culture, having taken in another village.
     *
     * <p>The surviving culture is this one, unchanged. Merging two cultures into a blend would mean
     * every resident of both villages waking up in a place that had never existed, which reads worse
     * than the honest version: the smaller village's people now live somewhere with a different
     * festival, and the record remembers where they came from.
     */
    public VillageCultureRecord absorbing(VillageCultureRecord other) {
        if (other == null || other.villageId == villageId) {
            return this;
        }
        Set<Integer> ids = new TreeSet<>(absorbed);
        ids.add(other.villageId);
        ids.addAll(other.absorbed);
        return new VillageCultureRecord(villageId, tokens, createdDay, ids);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(KEY_VILLAGE, villageId);
        CompoundTag list = new CompoundTag();
        tokens.forEach((family, id) -> list.putString(family.key(), id));
        tag.put(KEY_TOKENS, list);
        tag.putLong(KEY_CREATED, createdDay);
        if (!absorbed.isEmpty()) {
            ListTag ids = new ListTag();
            for (Integer id : absorbed) {
                ids.add(StringTag.valueOf(Integer.toString(id)));
            }
            tag.put(KEY_ABSORBED, ids);
        }
        return tag;
    }

    public static Optional<VillageCultureRecord> load(CompoundTag tag) {
        if (tag == null || !tag.contains(KEY_VILLAGE)) {
            return Optional.empty();
        }
        Map<CultureFamily, String> tokens = new EnumMap<>(CultureFamily.class);
        if (tag.contains(KEY_TOKENS, Tag.TAG_COMPOUND)) {
            CompoundTag stored = tag.getCompound(KEY_TOKENS);
            for (String key : stored.getAllKeys()) {
                CultureFamily.byKey(key).ifPresent(family -> tokens.put(family, stored.getString(key)));
            }
        }
        Set<Integer> absorbed = new TreeSet<>();
        if (tag.contains(KEY_ABSORBED, Tag.TAG_LIST)) {
            ListTag ids = tag.getList(KEY_ABSORBED, Tag.TAG_STRING);
            for (int i = 0; i < ids.size(); i++) {
                try {
                    absorbed.add(Integer.parseInt(ids.getString(i)));
                } catch (NumberFormatException ignored) {
                    // A row we cannot read is one village's merge history, not a reason to drop a culture.
                }
            }
        }
        return Optional.of(new VillageCultureRecord(tag.getInt(KEY_VILLAGE), tokens,
                tag.getLong(KEY_CREATED), absorbed));
    }
}
