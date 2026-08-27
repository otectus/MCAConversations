package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Everything one villager and one player share (spec §8.1).
 *
 * <p>Threads, commitments, claims and recency are all keyed by the <em>pair</em> rather than by the
 * villager, because they are all things that exist only between two people: a promise made to one
 * player is not owed to another, and a secret told to one is not known by the village. Episodes are
 * deliberately <b>not</b> here — an episode belongs to the villager and can be discussed by anyone
 * who has heard of it, which is what makes shared knowledge and privacy separable.
 *
 * <p>Mutating methods return {@code true} when something actually changed, so the saved-data wrapper
 * marks the world dirty on a real mutation and not on a read (spec §21.6).
 */
public final class PairHistory {

    private final Map<String, SharedThreadRecord> threads = new LinkedHashMap<>();
    private final Map<String, CommitmentRecord> commitments = new LinkedHashMap<>();
    private final Map<String, PlayerClaimRecord> claims = new LinkedHashMap<>();
    private TopicRecencyRecord recency = TopicRecencyRecord.EMPTY;
    private long firstMetDay = Long.MIN_VALUE;
    private long lastTalkedDay = Long.MIN_VALUE;

    // --- Threads ------------------------------------------------------------------------------------

    public Optional<SharedThreadRecord> thread(String templateId) {
        return Optional.ofNullable(threads.get(normalize(templateId)));
    }

    public List<SharedThreadRecord> threads() {
        return List.copyOf(threads.values());
    }

    /** Threads that may be offered today, most recently active first. */
    public List<SharedThreadRecord> resumable(long today) {
        List<SharedThreadRecord> out = new ArrayList<>();
        for (SharedThreadRecord thread : threads.values()) {
            if (thread.isReady(today)) {
                out.add(thread);
            }
        }
        out.sort(Comparator.comparingLong(SharedThreadRecord::lastMentionedDay).reversed());
        return List.copyOf(out);
    }

    /** The unrepaired rupture between this pair, if there is one. */
    public Optional<SharedThreadRecord> rupture() {
        for (SharedThreadRecord thread : threads.values()) {
            if (thread.status() == ThreadStatus.RUPTURED) {
                return Optional.of(thread);
            }
        }
        return Optional.empty();
    }

    public boolean putThread(SharedThreadRecord thread) {
        if (thread == null || thread.templateId().isEmpty()) {
            return false;
        }
        SharedThreadRecord existing = threads.get(thread.key());
        if (thread.equals(existing)) {
            return false;
        }
        if (existing == null && threads.size() >= HistoryCaps.threadsPerPair() && !pruneOneThread()) {
            return false;
        }
        threads.put(thread.key(), thread);
        return true;
    }

    /**
     * Drops one closed or lapsed thread to make room.
     *
     * <p>Never a live one, and never a rupture: a pair at the thread cap with nothing closed simply
     * does not open a new thread, which is the correct outcome — losing an unresolved obligation to
     * make room for a new subject would be exactly the untracked-promise failure (spec §8.8).
     */
    private boolean pruneOneThread() {
        String victim = null;
        long oldest = Long.MAX_VALUE;
        for (Map.Entry<String, SharedThreadRecord> entry : threads.entrySet()) {
            SharedThreadRecord thread = entry.getValue();
            if (!thread.status().isClosed() || thread.hasObligation()) {
                continue;
            }
            if (thread.lastMentionedDay() < oldest) {
                oldest = thread.lastMentionedDay();
                victim = entry.getKey();
            }
        }
        if (victim == null) {
            return false;
        }
        threads.remove(victim);
        return true;
    }

    // --- Commitments --------------------------------------------------------------------------------

    public Optional<CommitmentRecord> commitment(String id) {
        return Optional.ofNullable(commitments.get(normalize(id)));
    }

    public List<CommitmentRecord> commitments() {
        return List.copyOf(commitments.values());
    }

    /** Outstanding promises whose due day has arrived, oldest first. */
    public List<CommitmentRecord> due(long today) {
        List<CommitmentRecord> out = new ArrayList<>();
        for (CommitmentRecord commitment : commitments.values()) {
            if (commitment.isDue(today) && commitment.resolver().isAvailable()) {
                out.add(commitment);
            }
        }
        out.sort(Comparator.comparingLong(c -> c.dueDay().orElse(c.createdDay())));
        return List.copyOf(out);
    }

    public boolean putCommitment(CommitmentRecord commitment) {
        if (commitment == null || commitment.id().isEmpty()) {
            return false;
        }
        CommitmentRecord existing = commitments.get(commitment.id());
        if (commitment.equals(existing)) {
            return false;
        }
        if (existing == null && commitments.size() >= HistoryCaps.commitmentsPerPair()) {
            // Settled promises are the only ones that may be forgotten to make room.
            String victim = null;
            long oldest = Long.MAX_VALUE;
            for (Map.Entry<String, CommitmentRecord> entry : commitments.entrySet()) {
                CommitmentRecord candidate = entry.getValue();
                if (candidate.state().isSettled()
                        && candidate.resolvedDay().orElse(candidate.createdDay()) < oldest) {
                    oldest = candidate.resolvedDay().orElse(candidate.createdDay());
                    victim = entry.getKey();
                }
            }
            if (victim == null) {
                return false;
            }
            commitments.remove(victim);
        }
        commitments.put(commitment.id(), commitment);
        return true;
    }

    // --- Player claims --------------------------------------------------------------------------------

    public Optional<PlayerClaimRecord> claim(String type) {
        return Optional.ofNullable(claims.get(normalize(type)));
    }

    public List<PlayerClaimRecord> claims() {
        return List.copyOf(claims.values());
    }

    /** Claims the player has contradicted and not yet clarified. */
    public List<PlayerClaimRecord> disputedClaims() {
        List<PlayerClaimRecord> out = new ArrayList<>();
        for (PlayerClaimRecord claim : claims.values()) {
            if (claim.disputed()) {
                out.add(claim);
            }
        }
        return List.copyOf(out);
    }

    /**
     * Records a claim.
     *
     * <p>A second, different answer for the same type does not overwrite: it produces a disputed
     * record that still carries the old value, so a clarification scene can refer to both
     * (spec §8.6).
     */
    public boolean recordClaim(PlayerClaimRecord claim) {
        if (claim == null || !claim.isAttributable()) {
            return false;
        }
        PlayerClaimRecord existing = claims.get(claim.type());
        if (existing != null) {
            PlayerClaimRecord updated = existing.contradictedBy(claim.value(), claim.sourceReply(), claim.day());
            if (updated.equals(existing)) {
                return false;
            }
            claims.put(claim.type(), updated);
            return true;
        }
        if (claims.size() >= HistoryCaps.claimsPerPair()) {
            String victim = claims.entrySet().stream()
                    .filter(entry -> !entry.getValue().disputed())
                    .min(Comparator.comparingLong(entry -> entry.getValue().day()))
                    .map(Map.Entry::getKey)
                    .orElse(null);
            if (victim == null) {
                return false;
            }
            claims.remove(victim);
        }
        claims.put(claim.type(), claim);
        return true;
    }

    public boolean clarifyClaim(String type, long day) {
        PlayerClaimRecord existing = claims.get(normalize(type));
        if (existing == null) {
            return false;
        }
        PlayerClaimRecord updated = existing.clarified(day);
        if (updated.equals(existing)) {
            return false;
        }
        claims.put(existing.type(), updated);
        return true;
    }

    // --- Recency and the shared clock -----------------------------------------------------------------

    public TopicRecencyRecord recency() {
        return recency;
    }

    public boolean recordPlayed(String scene, String subject, String shape, String topic, long day) {
        TopicRecencyRecord updated = recency.played(scene, subject, shape, topic, day);
        boolean changed = !updated.equals(recency) || lastTalkedDay != day;
        recency = updated;
        if (firstMetDay == Long.MIN_VALUE) {
            firstMetDay = day;
        }
        lastTalkedDay = day;
        return changed;
    }

    public boolean recordInitiative(long day) {
        recency = recency.initiated(day);
        return true;
    }

    /** Marks that the pair spoke today at all, whether or not a contracted scene was played. */
    public boolean touch(long day) {
        boolean changed = false;
        if (firstMetDay == Long.MIN_VALUE) {
            firstMetDay = day;
            changed = true;
        }
        if (lastTalkedDay != day) {
            lastTalkedDay = day;
            changed = true;
        }
        return changed;
    }

    /** The day this pair first spoke; empty on a save that predates the history store (spec §22.2). */
    public java.util.OptionalLong firstMetDay() {
        return firstMetDay == Long.MIN_VALUE ? java.util.OptionalLong.empty()
                : java.util.OptionalLong.of(firstMetDay);
    }

    public java.util.OptionalLong lastTalkedDay() {
        return lastTalkedDay == Long.MIN_VALUE ? java.util.OptionalLong.empty()
                : java.util.OptionalLong.of(lastTalkedDay);
    }

    public boolean isEmpty() {
        return threads.isEmpty() && commitments.isEmpty() && claims.isEmpty()
                && recency.equals(TopicRecencyRecord.EMPTY) && firstMetDay == Long.MIN_VALUE;
    }

    // --- Pruning ---------------------------------------------------------------------------------------

    /**
     * Drops what has expired, in the plan's declared order: expired low-salience, then resolved and
     * already consumed, then oldest neutral (spec §8.8).
     *
     * @return how many records were removed
     */
    public int prune(long today) {
        int removed = 0;
        List<String> lapsed = new ArrayList<>();
        for (Map.Entry<String, SharedThreadRecord> entry : threads.entrySet()) {
            SharedThreadRecord thread = entry.getValue();
            if (thread.status() == ThreadStatus.RUPTURED || thread.hasObligation()) {
                continue;
            }
            if (thread.hasLapsed(today)) {
                lapsed.add(entry.getKey());
            }
        }
        for (String key : lapsed) {
            SharedThreadRecord thread = threads.get(key);
            // Lapsing is a status change, not a deletion: "we never finished that" is content.
            threads.put(key, thread.withStatus(ThreadStatus.LAPSED, today));
        }
        List<String> settled = new ArrayList<>();
        for (Map.Entry<String, CommitmentRecord> entry : commitments.entrySet()) {
            CommitmentRecord commitment = entry.getValue();
            if (commitment.state().isSettled()
                    && today - commitment.resolvedDay().orElse(commitment.createdDay())
                            > HistoryCaps.episodeRetentionDays()) {
                settled.add(entry.getKey());
            }
        }
        for (String key : settled) {
            commitments.remove(key);
            removed++;
        }
        return removed;
    }

    // --- Persistence -----------------------------------------------------------------------------------

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.put("threads", saveList(new TreeMap<>(threads).values().stream()
                .map(SharedThreadRecord::save).toList()));
        tag.put("commitments", saveList(new TreeMap<>(commitments).values().stream()
                .map(CommitmentRecord::save).toList()));
        tag.put("claims", saveList(new TreeMap<>(claims).values().stream()
                .map(PlayerClaimRecord::save).toList()));
        CompoundTag recencyTag = recency.save();
        if (!recencyTag.isEmpty()) {
            tag.put("recency", recencyTag);
        }
        if (firstMetDay != Long.MIN_VALUE) {
            tag.putLong("first_met", firstMetDay);
        }
        if (lastTalkedDay != Long.MIN_VALUE) {
            tag.putLong("last_talked", lastTalkedDay);
        }
        return tag;
    }

    public static PairHistory load(CompoundTag tag) {
        PairHistory history = new PairHistory();
        if (tag == null) {
            return history;
        }
        forEachCompound(tag, "threads", row ->
                SharedThreadRecord.load(row).ifPresent(t -> history.threads.put(t.key(), t)));
        forEachCompound(tag, "commitments", row ->
                CommitmentRecord.load(row).ifPresent(c -> history.commitments.put(c.id(), c)));
        forEachCompound(tag, "claims", row ->
                PlayerClaimRecord.load(row).ifPresent(c -> history.claims.put(c.type(), c)));
        if (tag.contains("recency", Tag.TAG_COMPOUND)) {
            history.recency = TopicRecencyRecord.load(tag.getCompound("recency"));
        }
        history.firstMetDay = tag.contains("first_met") ? tag.getLong("first_met") : Long.MIN_VALUE;
        history.lastTalkedDay = tag.contains("last_talked") ? tag.getLong("last_talked") : Long.MIN_VALUE;
        return history;
    }

    private static ListTag saveList(List<CompoundTag> rows) {
        ListTag list = new ListTag();
        list.addAll(rows);
        return list;
    }

    private static void forEachCompound(CompoundTag tag, String key, java.util.function.Consumer<CompoundTag> action) {
        if (!tag.contains(key, Tag.TAG_LIST)) {
            return;
        }
        ListTag list = tag.getList(key, Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            action.accept(list.getCompound(i));
        }
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
