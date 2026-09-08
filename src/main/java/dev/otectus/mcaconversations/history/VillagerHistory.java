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
import java.util.UUID;

/**
 * One villager's own narrative state: their episodes, what they take their neighbours to be, their
 * opinions of them, and the pair records for each player they know (spec §8.1).
 *
 * <p>The split between what lives here and what lives in {@link PairHistory} is the mod's whole
 * knowledge model in one decision. An <b>episode</b> is the villager's — the book really is damp,
 * whether or not anyone has been told — so it lives here and carries its own witness list. A
 * <b>thread</b> is shared, so it lives in the pair record. Getting this the other way round is how a
 * villager ends up discussing a private disclosure with the player who never heard it.
 */
public final class VillagerHistory {

    private final Map<UUID, EpisodeRecord> episodes = new LinkedHashMap<>();
    private final Map<String, SocialOpinionRecord> opinions = new LinkedHashMap<>();
    private final Map<String, SocialRoleRecord> roles = new LinkedHashMap<>();
    private final Map<UUID, PairHistory> pairs = new LinkedHashMap<>();

    /**
     * The last day this villager spoke to anybody, or {@link Long#MIN_VALUE} when they never have.
     *
     * <p>Optional in the file: written only when it is known, and derived from the pairs when it is
     * missing, so a save from an earlier build reads back with the same number it would have had. It
     * exists because eviction needs one comparable number per villager - walking every pair of every
     * villager to find the least active one is the sort of thing that gets done in insertion order
     * instead, which is how a world forgets whoever it happened to meet first.
     */
    private long lastActivityDay = Long.MIN_VALUE;

    /** Records this load discarded to fit the caps. Transient: it describes one read, not the state. */
    private int discardedOnLoad;

    // --- Episodes ------------------------------------------------------------------------------------

    public Optional<EpisodeRecord> episode(UUID id) {
        return id == null ? Optional.empty() : Optional.ofNullable(episodes.get(id));
    }

    public List<EpisodeRecord> episodes() {
        return List.copyOf(episodes.values());
    }

    /** Live episodes, most salient first, then most recently updated. */
    public List<EpisodeRecord> liveEpisodes(long today) {
        List<EpisodeRecord> out = new ArrayList<>();
        for (EpisodeRecord episode : episodes.values()) {
            if (episode.isLive(today)) {
                out.add(episode);
            }
        }
        out.sort(Comparator.comparingInt(EpisodeRecord::salience).reversed()
                .thenComparing(Comparator.comparingLong(EpisodeRecord::updatedDay).reversed()));
        return List.copyOf(out);
    }

    /** The live episode of one kind, when the villager has one. */
    public Optional<EpisodeRecord> liveEpisodeOfKind(String kind, long today) {
        String needle = normalize(kind);
        for (EpisodeRecord episode : liveEpisodes(today)) {
            if (episode.kind().equals(needle)) {
                return Optional.of(episode);
            }
        }
        return Optional.empty();
    }

    /**
     * Stores an episode, enforcing the live and resolved caps.
     *
     * @return true when something changed
     */
    public boolean putEpisode(EpisodeRecord episode, long today) {
        if (episode == null || episode.kind().isEmpty()) {
            return false;
        }
        EpisodeRecord existing = episodes.get(episode.id());
        if (episode.equals(existing)) {
            return false;
        }
        episodes.put(episode.id(), episode);
        enforceEpisodeCaps(today);
        return true;
    }

    /**
     * Applies the live and resolved caps, in the plan's deterministic order (spec §8.8).
     *
     * <p>Over the live cap, the least salient live episode is <em>abandoned</em> rather than deleted:
     * a villager who has taken on more than they can hold has given something up, and that is a state
     * a scene can honestly speak from. Only past the resolved cap is anything actually forgotten.
     */
    private int enforceEpisodeCaps(long today) {
        int changed = 0;
        int liveCap = HistoryCaps.activeEpisodes();
        List<EpisodeRecord> live = liveEpisodes(today);
        for (int i = liveCap; i < live.size(); i++) {
            EpisodeRecord victim = live.get(i);
            episodes.put(victim.id(), victim.transitioned(EpisodeState.ABANDONED, today));
            changed++;
        }

        int resolvedCap = HistoryCaps.resolvedEpisodes();
        List<EpisodeRecord> past = new ArrayList<>();
        for (EpisodeRecord episode : episodes.values()) {
            if (!episode.state().isLive()) {
                past.add(episode);
            }
        }
        if (past.size() <= resolvedCap) {
            return changed;
        }
        // Lowest salience first, then oldest update: exactly the plan's pruning order, so two servers
        // with the same history prune the same records.
        past.sort(Comparator.comparingInt(EpisodeRecord::salience)
                .thenComparing(Comparator.comparingLong(EpisodeRecord::updatedDay)));
        for (int i = 0; i < past.size() - resolvedCap; i++) {
            episodes.remove(past.get(i).id());
            changed++;
        }
        return changed;
    }

    public boolean removeEpisode(UUID id) {
        return id != null && episodes.remove(id) != null;
    }

    // --- Opinions -------------------------------------------------------------------------------------

    public Optional<SocialOpinionRecord> opinion(UUID target, String axis) {
        if (target == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(opinions.get(target + "/" + normalize(axis)));
    }

    public List<SocialOpinionRecord> opinions() {
        return List.copyOf(opinions.values());
    }

    /** Opinions about one neighbour, across every axis. */
    public List<SocialOpinionRecord> opinionsOf(UUID target) {
        List<SocialOpinionRecord> out = new ArrayList<>();
        for (SocialOpinionRecord opinion : opinions.values()) {
            if (opinion.target().equals(target)) {
                out.add(opinion);
            }
        }
        return List.copyOf(out);
    }

    /**
     * Records or adjusts a caused opinion.
     *
     * <p>Refuses a malformed edge outright. An opinion with no cause could only ever produce "I don't
     * like them", which is the generic drama the plan rules out (spec §16.2).
     */
    public boolean putOpinion(SocialOpinionRecord opinion) {
        if (opinion == null || !opinion.isWellFormed()) {
            return false;
        }
        SocialOpinionRecord existing = opinions.get(opinion.key());
        if (opinion.equals(existing)) {
            return false;
        }
        if (existing == null && opinions.size() >= HistoryCaps.opinionsPerVillager()
                && !pruneOneOpinion()) {
            return false;
        }
        opinions.put(opinion.key(), opinion);
        return true;
    }

    /**
     * Drops the blandest opinion to make room: nearest to neutral first, then oldest.
     *
     * <p>A neutral edge is the one whose loss changes no line the villager could have said, which is
     * why strength outranks age here. Ties break on the key so two servers reading the same file drop
     * the same edge.
     */
    private boolean pruneOneOpinion() {
        String victim = opinions.entrySet().stream()
                .min(Comparator
                        .<Map.Entry<String, SocialOpinionRecord>>comparingInt(
                                entry -> Math.abs(entry.getValue().value()))
                        .thenComparingLong(entry -> entry.getValue().createdDay())
                        .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .orElse(null);
        if (victim == null) {
            return false;
        }
        opinions.remove(victim);
        return true;
    }

    // --- Roles ------------------------------------------------------------------------------------------

    /** What this villager takes {@code target} to be, on one particular footing. */
    public Optional<SocialRoleRecord> role(UUID target, SocialRole role) {
        if (target == null || role == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(roles.get(target + "/" + role.key()));
    }

    public List<SocialRoleRecord> roles() {
        return List.copyOf(roles.values());
    }

    /** Every role this villager holds towards one neighbour. */
    public List<SocialRoleRecord> rolesOf(UUID target) {
        List<SocialRoleRecord> out = new ArrayList<>();
        for (SocialRoleRecord record : roles.values()) {
            if (record.target().equals(target)) {
                out.add(record);
            }
        }
        return List.copyOf(out);
    }

    /** Everyone this villager holds a given role towards. */
    public List<SocialRoleRecord> rolesOfKind(SocialRole role) {
        List<SocialRoleRecord> out = new ArrayList<>();
        for (SocialRoleRecord record : roles.values()) {
            if (record.role() == role) {
                out.add(record);
            }
        }
        return List.copyOf(out);
    }

    /**
     * Records an observed role, or refreshes one already held.
     *
     * <p>Refuses a malformed edge outright, for the same reason opinions do: a role with no cause
     * could only produce a villager who avoids somebody without being able to say why, and the plan
     * rules that out by name (spec §16.2).
     *
     * <p>When the cap is reached, the edge given up is the one that has least to say — an expiring
     * role before a structural one, and the oldest of those. A mentorship is not evicted to make
     * room for having been at the same fire.
     */
    public boolean putRole(SocialRoleRecord record) {
        if (record == null || !record.isWellFormed()) {
            return false;
        }
        SocialRoleRecord existing = roles.get(record.key());
        if (record.equals(existing)) {
            return false;
        }
        if (existing == null && roles.size() >= HistoryCaps.rolesPerVillager() && !pruneOneRole()) {
            return false;
        }
        roles.put(record.key(), record);
        return true;
    }

    /** Drops the role with least to say: an expiring one before a structural one, oldest first. */
    private boolean pruneOneRole() {
        String victim = roles.entrySet().stream()
                .min(Comparator
                        .<Map.Entry<String, SocialRoleRecord>>comparingInt(
                                entry -> entry.getValue().role().persistsUntilWithdrawn() ? 1 : 0)
                        .thenComparingLong(entry -> entry.getValue().createdDay())
                        .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .orElse(null);
        if (victim == null) {
            return false;
        }
        roles.remove(victim);
        return true;
    }

    /**
     * Withdraws a role because the arrangement behind it has ended.
     *
     * <p>This is the other half of the persistence policy. A role that never expires has to be
     * removable, or a villager keeps calling somebody their apprentice a decade after they left.
     */
    public boolean withdrawRole(UUID target, SocialRole role) {
        if (target == null || role == null) {
            return false;
        }
        return roles.remove(target + "/" + role.key()) != null;
    }

    // --- Pairs ------------------------------------------------------------------------------------------

    /** Read-only lookup; does not create a pair record for a player who has never spoken. */
    public Optional<PairHistory> peekPair(UUID player) {
        return player == null ? Optional.empty() : Optional.ofNullable(pairs.get(player));
    }

    public PairHistory pair(UUID player) {
        if (player == null) {
            return new PairHistory();
        }
        PairHistory existing = pairs.get(player);
        if (existing != null) {
            return existing;
        }
        while (pairs.size() >= HistoryCaps.HARD_PAIRS_PER_VILLAGER && pruneOnePair()) {
            // Room for the newcomer.
        }
        PairHistory created = new PairHistory();
        adopt(player, created);
        return created;
    }

    /** Drops one pair: an empty record first, otherwise the one that spoke longest ago. */
    private boolean pruneOnePair() {
        UUID victim = pairs.entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .min(Comparator.naturalOrder())
                .orElseGet(() -> pairs.entrySet().stream()
                        .min(Comparator
                                .<Map.Entry<UUID, PairHistory>>comparingLong(entry ->
                                        entry.getValue().lastTalkedDay().orElse(Long.MIN_VALUE))
                                .thenComparing(Map.Entry.comparingByKey()))
                        .map(Map.Entry::getKey)
                        .orElse(null));
        if (victim == null) {
            return false;
        }
        pairs.remove(victim);
        return true;
    }

    /**
     * Takes ownership of a pair record, so the day that pair last spoke also becomes this villager's.
     *
     * <p>The one seam where {@link #lastActivityDay} is maintained. Every pair this villager holds
     * arrives through here - freshly created above, or read back from disk - and reports its
     * conversations from then on, which is what keeps the number correct without a second bookkeeping
     * path that could be forgotten at a call site.
     */
    private void adopt(UUID player, PairHistory pair) {
        pairs.put(player, pair);
        pair.listenForTalk(this::talkedOn);
    }

    private void talkedOn(long day) {
        if (day > lastActivityDay) {
            lastActivityDay = day;
        }
    }

    /**
     * The last day this villager spoke to anybody, or empty when they never have.
     *
     * <p>The eviction order reads this; see {@link ConversationHistoryStore#getOrCreate}.
     */
    public java.util.OptionalLong lastActivityDay() {
        return lastActivityDay == Long.MIN_VALUE ? java.util.OptionalLong.empty()
                : java.util.OptionalLong.of(lastActivityDay);
    }

    /**
     * True when forgetting this villager would drop something somebody is owed.
     *
     * <p>An unresolved promise or a thread still open is an obligation the player can hold the mod to,
     * and no bound on a map is worth breaking one. A protected villager is skipped by eviction
     * entirely (spec §8.8).
     */
    public boolean isProtected() {
        for (PairHistory pair : pairs.values()) {
            for (CommitmentRecord commitment : pair.commitments()) {
                if (commitment.isOutstanding()) {
                    return true;
                }
            }
            for (SharedThreadRecord thread : pair.threads()) {
                if (thread.status().isLive() || thread.hasObligation()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Map<UUID, PairHistory> pairs() {
        return Map.copyOf(pairs);
    }

    public boolean isEmpty() {
        return episodes.isEmpty() && opinions.isEmpty() && roles.isEmpty() && pairs.isEmpty();
    }

    // --- Pruning ------------------------------------------------------------------------------------------

    /** Runs every collection's pruning pass; returns how many records were removed. */
    public int prune(long today) {
        int removed = 0;
        List<UUID> expiredEpisodes = new ArrayList<>();
        for (EpisodeRecord episode : episodes.values()) {
            if (episode.state() == EpisodeState.REMEMBERED
                    && today - episode.updatedDay() > HistoryCaps.episodeRetentionDays()) {
                expiredEpisodes.add(episode.id());
            } else if (episode.hasExpired(today) && episode.state().isLive()) {
                episodes.put(episode.id(), episode.transitioned(EpisodeState.ABANDONED, today));
                removed++;
            }
        }
        for (UUID id : expiredEpisodes) {
            episodes.remove(id);
            removed++;
        }
        List<String> expiredOpinions = new ArrayList<>();
        for (Map.Entry<String, SocialOpinionRecord> entry : opinions.entrySet()) {
            if (entry.getValue().hasExpired(today)) {
                expiredOpinions.add(entry.getKey());
            }
        }
        for (String key : expiredOpinions) {
            opinions.remove(key);
            removed++;
        }
        List<String> expiredRoles = new ArrayList<>();
        for (Map.Entry<String, SocialRoleRecord> entry : roles.entrySet()) {
            if (entry.getValue().hasExpired(today)) {
                expiredRoles.add(entry.getKey());
            }
        }
        for (String key : expiredRoles) {
            roles.remove(key);
            removed++;
        }
        List<UUID> emptyPairs = new ArrayList<>();
        for (Map.Entry<UUID, PairHistory> entry : pairs.entrySet()) {
            removed += entry.getValue().prune(today);
            if (entry.getValue().isEmpty()) {
                emptyPairs.add(entry.getKey());
            }
        }
        for (UUID player : emptyPairs) {
            pairs.remove(player);
        }
        removed += enforceEpisodeCaps(today);
        return removed;
    }

    // --- Persistence ----------------------------------------------------------------------------------------

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        ListTag episodeList = new ListTag();
        for (EpisodeRecord episode : new TreeMap<>(episodes).values()) {
            episodeList.add(episode.save());
        }
        tag.put("episodes", episodeList);

        ListTag opinionList = new ListTag();
        for (SocialOpinionRecord opinion : new TreeMap<>(opinions).values()) {
            opinionList.add(opinion.save());
        }
        tag.put("opinions", opinionList);

        ListTag roleList = new ListTag();
        for (SocialRoleRecord record : new TreeMap<>(roles).values()) {
            roleList.add(record.save());
        }
        tag.put("roles", roleList);

        ListTag pairList = new ListTag();
        for (Map.Entry<UUID, PairHistory> entry : new TreeMap<>(pairs).entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            CompoundTag row = entry.getValue().save();
            row.putUUID("player", entry.getKey());
            pairList.add(row);
        }
        tag.put("pairs", pairList);
        if (lastActivityDay != Long.MIN_VALUE) {
            // Optional both ways: absent when nothing has been said, and derived on load when a file
            // written by an older build has no such key. No schema bump buys anything here.
            tag.putLong("last_activity", lastActivityDay);
        }
        return tag;
    }

    public static VillagerHistory load(CompoundTag tag) {
        VillagerHistory history = new VillagerHistory();
        if (tag == null) {
            return history;
        }
        if (tag.contains("episodes", Tag.TAG_LIST)) {
            ListTag list = tag.getList("episodes", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                EpisodeRecord.load(list.getCompound(i))
                        .ifPresent(episode -> history.episodes.put(episode.id(), episode));
            }
        }
        if (tag.contains("opinions", Tag.TAG_LIST)) {
            ListTag list = tag.getList("opinions", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                SocialOpinionRecord.load(list.getCompound(i))
                        .ifPresent(opinion -> history.opinions.put(opinion.key(), opinion));
            }
        }
        if (tag.contains("roles", Tag.TAG_LIST)) {
            ListTag list = tag.getList("roles", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                SocialRoleRecord.load(list.getCompound(i))
                        .ifPresent(record -> history.roles.put(record.key(), record));
            }
        }
        if (tag.contains("pairs", Tag.TAG_LIST)) {
            ListTag list = tag.getList("pairs", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag row = list.getCompound(i);
                if (row.hasUUID("player")) {
                    PairHistory pair = PairHistory.load(row);
                    history.discardedOnLoad += pair.discardedOnLoad();
                    // adopt(), not put(): the pair reports the day it last spoke on the way in, which
                    // is where a file with no last_activity key gets its number from.
                    history.adopt(row.getUUID("player"), pair);
                }
            }
        }
        if (tag.contains("last_activity")) {
            // The stored day wins over the derived one only when it is later: a file written before
            // this key existed, then written again by a build that has it, must not go backwards.
            history.talkedOn(tag.getLong("last_activity"));
        }
        history.discardedOnLoad += history.enforceLoadedCaps();
        return history;
    }

    /**
     * Applies every declared cap to what has just been read, and returns how many records that cost.
     *
     * <p>Each collection is reduced by the same private eviction the mutation path uses, so a file
     * that was over its caps keeps exactly the records it would have kept had it filled up one
     * conversation at a time:
     *
     * <ul>
     *   <li><b>episodes</b> - the live and resolved caps as {@link #enforceEpisodeCaps} applies them,
     *       against the latest day any episode in the file was updated. Over the live cap the least
     *       salient is abandoned rather than deleted; only past the resolved cap is anything
     *       forgotten, lowest salience then oldest update first;</li>
     *   <li><b>opinions</b> - nearest to neutral first, then oldest, then by key;</li>
     *   <li><b>roles</b> - expiring before structural, then oldest, then by key;</li>
     *   <li><b>pairs</b> - an empty record first, otherwise the pair that spoke longest ago, then by
     *       player UUID.</li>
     * </ul>
     */
    private int enforceLoadedCaps() {
        int discarded = 0;
        long latest = 0;
        for (EpisodeRecord episode : episodes.values()) {
            latest = Math.max(latest, episode.updatedDay());
        }
        int episodesBefore = episodes.size();
        enforceEpisodeCaps(latest);
        discarded += episodesBefore - episodes.size();

        while (opinions.size() > HistoryCaps.opinionsPerVillager() && pruneOneOpinion()) {
            discarded++;
        }
        while (roles.size() > HistoryCaps.rolesPerVillager() && pruneOneRole()) {
            discarded++;
        }
        while (pairs.size() > HistoryCaps.HARD_PAIRS_PER_VILLAGER && pruneOnePair()) {
            discarded++;
        }
        return discarded;
    }

    /** How many records the load that built this history had to discard to fit the caps. */
    int discardedOnLoad() {
        return discardedOnLoad;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
