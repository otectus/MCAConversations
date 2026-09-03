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
    private void enforceEpisodeCaps(long today) {
        int liveCap = HistoryCaps.activeEpisodes();
        List<EpisodeRecord> live = liveEpisodes(today);
        for (int i = liveCap; i < live.size(); i++) {
            EpisodeRecord victim = live.get(i);
            episodes.put(victim.id(), victim.transitioned(EpisodeState.ABANDONED, today));
        }

        int resolvedCap = HistoryCaps.resolvedEpisodes();
        List<EpisodeRecord> past = new ArrayList<>();
        for (EpisodeRecord episode : episodes.values()) {
            if (!episode.state().isLive()) {
                past.add(episode);
            }
        }
        if (past.size() <= resolvedCap) {
            return;
        }
        // Lowest salience first, then oldest update: exactly the plan's pruning order, so two servers
        // with the same history prune the same records.
        past.sort(Comparator.comparingInt(EpisodeRecord::salience)
                .thenComparing(Comparator.comparingLong(EpisodeRecord::updatedDay)));
        for (int i = 0; i < past.size() - resolvedCap; i++) {
            episodes.remove(past.get(i).id());
        }
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
        if (existing == null && opinions.size() >= HistoryCaps.opinionsPerVillager()) {
            String victim = opinions.entrySet().stream()
                    .min(Comparator
                            .<Map.Entry<String, SocialOpinionRecord>>comparingInt(
                                    entry -> Math.abs(entry.getValue().value()))
                            .thenComparingLong(entry -> entry.getValue().createdDay()))
                    .map(Map.Entry::getKey)
                    .orElse(null);
            if (victim == null) {
                return false;
            }
            opinions.remove(victim);
        }
        opinions.put(opinion.key(), opinion);
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
        if (existing == null && roles.size() >= HistoryCaps.rolesPerVillager()) {
            String victim = roles.entrySet().stream()
                    .min(Comparator
                            .<Map.Entry<String, SocialRoleRecord>>comparingInt(
                                    entry -> entry.getValue().role().persistsUntilWithdrawn() ? 1 : 0)
                            .thenComparingLong(entry -> entry.getValue().createdDay()))
                    .map(Map.Entry::getKey)
                    .orElse(null);
            if (victim == null) {
                return false;
            }
            roles.remove(victim);
        }
        roles.put(record.key(), record);
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
        while (pairs.size() >= HistoryCaps.HARD_PAIRS_PER_VILLAGER) {
            String unused = null;
            UUID victim = pairs.entrySet().stream()
                    .filter(entry -> entry.getValue().isEmpty())
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseGet(() -> pairs.entrySet().stream()
                            .min(Comparator.comparingLong(entry ->
                                    entry.getValue().lastTalkedDay().orElse(Long.MIN_VALUE)))
                            .map(Map.Entry::getKey)
                            .orElse(null));
            if (victim == null) {
                break;
            }
            pairs.remove(victim);
        }
        PairHistory created = new PairHistory();
        pairs.put(player, created);
        return created;
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
        enforceEpisodeCaps(today);
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
                    history.pairs.put(row.getUUID("player"), PairHistory.load(row));
                }
            }
        }
        return history;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
