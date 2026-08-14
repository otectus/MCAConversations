package dev.otectus.mcaconversations.progress;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Everything one villager durably remembers about one player's conversations: the daily affection
 * budget counters, which decisions have already paid out, where each arc stands, which one-shot
 * milestones have fired, which side of an exclusive choice was taken, and a short ring of recently
 * applied transaction ids for packet idempotency.
 *
 * <p>Deliberately separate from {@code DispositionRecord}. The vector is a mood-like thing that
 * decays and may safely be dropped on a schema change; this is the ledger that makes "the game
 * remembered" true, so it is bounded, clamped, and migrated field-by-field rather than discarded
 * (plan §5.3, §11.3).
 *
 * <p>Every collection is hard-bounded. A datapack cannot make a villager's record grow without limit
 * by inventing ids.
 */
public final class ProgressRecord {

    static final int MAX_DECISIONS_TRACKED = 64;
    static final int MAX_ONCE_DECISIONS = 64;
    static final int MAX_ARCS = 32;
    static final int MAX_MILESTONES = 128;
    static final int MAX_EXCLUSIVE_GROUPS = 32;
    /**
     * How many recent transaction ids to remember. A duplicated packet arrives within the same tick
     * or two; anything older cannot be a duplicate of a decision the player is still making.
     */
    static final int MAX_TRANSACTIONS = 8;

    private long lastUpdated;
    private long counterDay = Long.MIN_VALUE;
    private int positiveToday;
    private int negativeToday;

    private final Map<String, Integer> decisionCountsToday = new LinkedHashMap<>();
    private final Set<String> decisionsEver = new LinkedHashSet<>();
    private final Map<String, Integer> arcStages = new LinkedHashMap<>();
    private final Set<String> milestones = new LinkedHashSet<>();
    private final Map<String, String> exclusives = new LinkedHashMap<>();
    private final Deque<String> recentTransactions = new ArrayDeque<>();

    public ProgressRecord(long now) {
        this.lastUpdated = now;
    }

    public long lastUpdated() {
        return lastUpdated;
    }

    public void touch(long now) {
        this.lastUpdated = now;
    }

    // --- Daily affection counters --------------------------------------------

    /** Rolls the daily counters over when the MC day has changed. Safe to call before every read. */
    public void rollTo(long day) {
        if (day != counterDay) {
            counterDay = day;
            positiveToday = 0;
            negativeToday = 0;
            decisionCountsToday.clear();
        }
    }

    public int positiveToday(long day) {
        rollTo(day);
        return positiveToday;
    }

    public int negativeToday(long day) {
        rollTo(day);
        return negativeToday;
    }

    /** How many times {@code decisionId} has already paid out today. */
    public int repeatsToday(String decisionId, long day) {
        rollTo(day);
        return decisionCountsToday.getOrDefault(decisionId, 0);
    }

    /** True when {@code decisionId} has paid out at least once, on any day. */
    public boolean everApplied(String decisionId) {
        return decisionsEver.contains(decisionId);
    }

    /** Books an applied delta against the daily counters and the per-decision repeat counts. */
    public void recordApplied(String decisionId, int applied, long day) {
        rollTo(day);
        if (applied > 0) {
            positiveToday += applied;
        } else if (applied < 0) {
            negativeToday += -applied;
        }
        if (decisionCountsToday.size() < MAX_DECISIONS_TRACKED
                || decisionCountsToday.containsKey(decisionId)) {
            decisionCountsToday.merge(decisionId, 1, Integer::sum);
        }
        if (decisionsEver.size() < MAX_ONCE_DECISIONS) {
            decisionsEver.add(decisionId);
        }
    }

    // --- Idempotency -----------------------------------------------------------

    /**
     * Claims a transaction id. Returns false when this exact transaction was already applied — the
     * signature of a duplicated or replayed packet.
     */
    public boolean claimTransaction(String transactionId) {
        if (recentTransactions.contains(transactionId)) {
            return false;
        }
        recentTransactions.addLast(transactionId);
        while (recentTransactions.size() > MAX_TRANSACTIONS) {
            recentTransactions.removeFirst();
        }
        return true;
    }

    // --- Arcs ------------------------------------------------------------------

    public int arcStage(String arcId) {
        return arcStages.getOrDefault(arcId, 0);
    }

    /**
     * Sets an arc stage, clamped into {@code [0, maxStage]}. Returns the stage actually stored.
     * Regression is allowed — a mishandled stage may move an arc backwards — but never below 0.
     */
    public int setArcStage(String arcId, int stage, int maxStage) {
        int clamped = Math.max(0, Math.min(Math.max(0, maxStage), stage));
        if (!arcStages.containsKey(arcId) && arcStages.size() >= MAX_ARCS) {
            return arcStage(arcId);
        }
        arcStages.put(arcId, clamped);
        return clamped;
    }

    // --- Milestones ------------------------------------------------------------

    public boolean hasMilestone(String id) {
        return milestones.contains(id);
    }

    /** Sets a one-shot milestone. Returns true only the first time it fires. */
    public boolean setMilestone(String id) {
        if (milestones.contains(id) || milestones.size() >= MAX_MILESTONES) {
            return false;
        }
        milestones.add(id);
        return true;
    }

    // --- Exclusive choices -----------------------------------------------------

    public Optional<String> exclusiveChoice(String group) {
        return Optional.ofNullable(exclusives.get(group));
    }

    /**
     * Records which side of a mutually exclusive choice was taken. The first choice wins for good —
     * a later attempt to take the other side is refused, which is what makes the choice exclusive.
     * Returns true when this call is the one that decided it.
     */
    public boolean setExclusiveChoice(String group, String member) {
        if (exclusives.containsKey(group)) {
            return false;
        }
        if (exclusives.size() >= MAX_EXCLUSIVE_GROUPS) {
            return false;
        }
        exclusives.put(group, member);
        return true;
    }

    // --- Views for debug output and tests --------------------------------------

    public Map<String, Integer> arcStagesView() {
        return Collections.unmodifiableMap(arcStages);
    }

    public Set<String> milestonesView() {
        return Collections.unmodifiableSet(milestones);
    }

    public Map<String, String> exclusivesView() {
        return Collections.unmodifiableMap(exclusives);
    }

    // --- Serialization ---------------------------------------------------------

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        tag.putLong("lastUpdated", lastUpdated);
        tag.putLong("day", counterDay);
        tag.putInt("pos", positiveToday);
        tag.putInt("neg", negativeToday);

        CompoundTag counts = new CompoundTag();
        decisionCountsToday.forEach(counts::putInt);
        tag.put("counts", counts);

        tag.put("ever", stringList(decisionsEver));

        CompoundTag arcs = new CompoundTag();
        arcStages.forEach(arcs::putInt);
        tag.put("arcs", arcs);

        tag.put("milestones", stringList(milestones));

        CompoundTag exclusiveTag = new CompoundTag();
        exclusives.forEach(exclusiveTag::putString);
        tag.put("exclusives", exclusiveTag);

        tag.put("tx", stringList(recentTransactions));
        return tag;
    }

    /**
     * Reads a record, tolerating anything. Malformed entries are skipped rather than failing the
     * load; counters and stages are clamped; over-long collections are truncated. An entirely
     * unreadable tag yields an empty record rather than an exception.
     */
    public static Optional<ProgressRecord> fromNbt(CompoundTag tag) {
        if (tag == null || tag.isEmpty()) {
            return Optional.empty();
        }
        try {
            ProgressRecord record = new ProgressRecord(tag.getLong("lastUpdated"));
            record.counterDay = tag.contains("day") ? tag.getLong("day") : Long.MIN_VALUE;
            record.positiveToday = Math.max(0, tag.getInt("pos"));
            record.negativeToday = Math.max(0, tag.getInt("neg"));

            CompoundTag counts = tag.getCompound("counts");
            for (String key : counts.getAllKeys()) {
                if (record.decisionCountsToday.size() >= MAX_DECISIONS_TRACKED) {
                    break;
                }
                record.decisionCountsToday.put(key, Math.max(0, counts.getInt(key)));
            }

            readStrings(tag, "ever", MAX_ONCE_DECISIONS, record.decisionsEver::add);

            CompoundTag arcs = tag.getCompound("arcs");
            for (String key : arcs.getAllKeys()) {
                if (record.arcStages.size() >= MAX_ARCS) {
                    break;
                }
                // The catalog's real bound is applied on write; here we only keep it sane and non-negative.
                record.arcStages.put(key, Math.max(0, Math.min(arcs.getInt(key), Integer.MAX_VALUE)));
            }

            readStrings(tag, "milestones", MAX_MILESTONES, record.milestones::add);

            CompoundTag exclusives = tag.getCompound("exclusives");
            for (String key : exclusives.getAllKeys()) {
                if (record.exclusives.size() >= MAX_EXCLUSIVE_GROUPS) {
                    break;
                }
                String member = exclusives.getString(key);
                if (!member.isBlank()) {
                    record.exclusives.put(key, member);
                }
            }

            readStrings(tag, "tx", MAX_TRANSACTIONS, record.recentTransactions::addLast);
            return Optional.of(record);
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    private static ListTag stringList(Iterable<String> values) {
        ListTag list = new ListTag();
        for (String value : values) {
            list.add(StringTag.valueOf(value));
        }
        return list;
    }

    private static void readStrings(CompoundTag tag, String key, int limit, java.util.function.Consumer<String> sink) {
        ListTag list = tag.getList(key, Tag.TAG_STRING);
        for (int i = 0; i < list.size() && i < limit; i++) {
            String value = list.getString(i);
            if (!value.isBlank()) {
                sink.accept(value);
            }
        }
    }
}
