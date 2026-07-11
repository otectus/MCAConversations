package dev.otectus.mcaconversations.disposition;

import net.minecraft.nbt.CompoundTag;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The disposition state for one (villager, player) pair: the six raw axis values, the decay anchor,
 * and the per-day farming counters. Raw values are stored undecayed — {@link DispositionStore} folds
 * decay in lazily using {@link #lastUpdated()}.
 *
 * <p>NBT layout (compact, all fields optional-with-defaults on load):
 * {@code ax} byte[6] axis values in {@link DispositionAxis} enum order · {@code lu} long decay
 * anchor · {@code gday} long world day of the gain counters · {@code gain} byte[6] |movement| per
 * axis on that day · {@code st} compound of stance topic → int[2]{count, day}.
 */
public final class DispositionRecord {

    /** Bound on the per-record stance-repeat map; oldest entries are evicted first. */
    public static final int MAX_TRACKED_STANCES = 16;

    private static final int AXES = DispositionAxis.values().length;

    private final int[] axes = new int[AXES];
    private long lastUpdated;
    private long gainDay = Long.MIN_VALUE;
    private final int[] gainedToday = new int[AXES];
    /** Insertion-ordered stance topic → {count, day}; bounded by {@link #MAX_TRACKED_STANCES}. */
    private final LinkedHashMap<String, int[]> stances = new LinkedHashMap<>();

    public DispositionRecord(long now) {
        this.lastUpdated = now;
    }

    public int axisRaw(DispositionAxis axis) {
        return axes[axis.ordinal()];
    }

    public void setAxis(DispositionAxis axis, int value) {
        axes[axis.ordinal()] = DispositionMath.clamp(axis, value);
    }

    /** Game time of the last write — the anchor decay is computed from. Reads never move it. */
    public long lastUpdated() {
        return lastUpdated;
    }

    public void touch(long now) {
        this.lastUpdated = now;
    }

    /** How often this stance already fired today (0 when the tracked entry is from another day). */
    public int repeatCountToday(String topic, long day) {
        int[] entry = stances.get(topic);
        return entry != null && entry[1] == day ? entry[0] : 0;
    }

    public void recordStance(String topic, long day) {
        int[] entry = stances.remove(topic);
        if (entry != null && entry[1] == day) {
            entry[0]++;
        } else {
            entry = new int[]{1, (int) day};
        }
        entry[1] = (int) day;
        stances.put(topic, entry);
        while (stances.size() > MAX_TRACKED_STANCES) {
            stances.remove(stances.keySet().iterator().next());
        }
    }

    /** Total |movement| already applied to this axis today (0 when the counters are from another day). */
    public int gainedToday(DispositionAxis axis, long day) {
        return day == gainDay ? gainedToday[axis.ordinal()] : 0;
    }

    public void addGained(DispositionAxis axis, long day, int absAmount) {
        if (day != gainDay) {
            gainDay = day;
            java.util.Arrays.fill(gainedToday, 0);
        }
        gainedToday[axis.ordinal()] += Math.abs(absAmount);
    }

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        byte[] ax = new byte[AXES];
        byte[] gain = new byte[AXES];
        for (int i = 0; i < AXES; i++) {
            ax[i] = (byte) axes[i];
            gain[i] = (byte) Math.min(gainedToday[i], Byte.MAX_VALUE);
        }
        tag.putByteArray("ax", ax);
        tag.putLong("lu", lastUpdated);
        tag.putLong("gday", gainDay);
        tag.putByteArray("gain", gain);
        CompoundTag stancesTag = new CompoundTag();
        for (Map.Entry<String, int[]> entry : stances.entrySet()) {
            stancesTag.putIntArray(entry.getKey(), entry.getValue().clone());
        }
        tag.put("st", stancesTag);
        return tag;
    }

    /** Empty when the tag is structurally unusable (the caller skips the entry, never fails the load). */
    public static Optional<DispositionRecord> fromNbt(CompoundTag tag) {
        byte[] ax = tag.getByteArray("ax");
        if (ax.length != AXES) {
            return Optional.empty();
        }
        DispositionRecord record = new DispositionRecord(tag.getLong("lu"));
        for (DispositionAxis axis : DispositionAxis.values()) {
            record.setAxis(axis, ax[axis.ordinal()]);
        }
        record.gainDay = tag.getLong("gday");
        byte[] gain = tag.getByteArray("gain");
        if (gain.length == AXES) {
            for (int i = 0; i < AXES; i++) {
                record.gainedToday[i] = Math.max(0, gain[i]);
            }
        }
        CompoundTag stancesTag = tag.getCompound("st");
        for (String topic : stancesTag.getAllKeys()) {
            int[] entry = stancesTag.getIntArray(topic);
            if (entry.length == 2 && record.stances.size() < MAX_TRACKED_STANCES) {
                record.stances.put(topic, entry.clone());
            }
        }
        return Optional.of(record);
    }
}
