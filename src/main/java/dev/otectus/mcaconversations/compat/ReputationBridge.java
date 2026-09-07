package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The classloading gate in front of everything MCA: Reputation-shaped — the exact sibling of
 * {@link QuestsBridge}, built to the same discipline for the same reason (spec §30.1).
 *
 * <p>This class has <b>no</b> {@code dev.otectus.mcareputation.*} imports.
 * {@code compat.reputation.ConversationsReputationCompat} (which does) is only <em>named</em> here as a
 * string, so the JVM loads no Reputation class until after the {@link ModList#isLoaded} check, and
 * {@code catch (Throwable)} additionally absorbs the {@code NoClassDefFoundError} /
 * {@code NoSuchMethodError} that API drift would otherwise produce.
 *
 * <p>MCA: Reputation is an <b>optional</b> integration. When it is absent {@link #isAvailable()} stays
 * {@code false}, every reputation dialogue condition scores a neutral {@code 0} so authored
 * disabled-context fallbacks fire, the standing check term is exactly {@code 0} so no existing
 * deterministic outcome changes, the {@code conversations_reputation_signal} action is a no-op, and no
 * external gossip candidate is ever offered. Conversations then behaves precisely as it does today.
 */
public final class ReputationBridge {

    private static volatile boolean available;
    private static volatile ReputationQueries queries;

    private ReputationBridge() {
    }

    /**
     * Everything Conversations asks of MCA: Reputation, in pure Minecraft and Java types.
     *
     * <p>Implemented by {@code compat.reputation.ConversationsReputationCompat} and installed through
     * {@link #setQueries}. Every method must fail safe to a documented neutral value, because callers
     * invoke them in the middle of MCA dialogue evaluation, where an exception would abort the
     * conversation the player is having.
     */
    public interface ReputationQueries {

        /** The player's standing with this villager's village. Safe default: {@code 0}. */
        int score(ServerPlayer player, Entity villager);

        /** The current tier id, or {@code ""} when nothing resolves. */
        String tierId(ServerPlayer player, Entity villager);

        /**
         * The bounded check bias for an axis (§30.3). Non-zero only for {@code trust} and
         * {@code respect}; hard-clamped to ±8 whatever a datapack claims. Safe default: {@code 0}.
         */
        int checkBias(ServerPlayer player, Entity villager, String axis);

        /** Whether the player's standing satisfies an authored query. Safe default: {@code false}. */
        boolean matchesStanding(ServerPlayer player, Entity villager, StandingQuery query);

        /** Whether a matching incident exists — and, if asked, whether this villager knows about it. */
        boolean matchesIncident(ServerPlayer player, Entity villager, IncidentQuery query);

        /**
         * The newest story this villager knows and could tell, or empty (§30.4).
         *
         * <p>Reputation answers only <em>whether the teller knows the fact</em> and hands back a
         * normalized candidate. It never decides whether the story has been told before — that memory
         * stays Conversations' own, in MCA's {@code LongTermMemory} (§19.4).
         */
        Optional<GossipCandidate> nextGossip(ServerPlayer player, Entity teller, Set<String> types,
                                             long maxAgeTicks);

        /**
         * Up to {@code limit} tellable stories this villager knows, newest first (§30.4).
         *
         * <p>{@link #nextGossip} alone is not enough for the merge: Reputation cannot see the
         * told-memory, so its single newest candidate may already have been told while an older,
         * untold one still exists. The gossip logic filters this list against its own memory and
         * merges the survivor with the native log.
         */
        default List<GossipCandidate> gossipCandidates(ServerPlayer player, Entity teller,
                                                       Set<String> types, long maxAgeTicks, int limit) {
            return nextGossip(player, teller, types, maxAgeTicks).map(List::of).orElse(List.of());
        }

        /**
         * Records an authored conversation outcome as a public deed (§30.6).
         *
         * @return true when something was actually recorded
         */
        boolean recordSignal(ServerPlayer player, Entity villager, String incidentId, String visibility,
                             String decisionId);

        /** A recent notable deed this villager knows, for the standing topic's flavour line. */
        Optional<Component> recentKnownDeed(ServerPlayer player, Entity villager);

        /** True when the player has an unresolved negative deed here that amends could address. */
        boolean hasUnresolvedNegativeIncident(ServerPlayer player, Entity villager);

        /**
         * Whether the installed MCA: Reputation answers the per-villager opinion question at all.
         *
         * <p>Defaults to {@code false} because an older Reputation has no such method: the compat
         * class probes for it once and reports what it found, so an older server keeps the
         * village-level bias instead of taking a {@code NoSuchMethodError} into a conversation.
         */
        default boolean supportsOpinionBias() {
            return false;
        }

        /**
         * The bounded check bias read from <em>this villager's own</em> opinion rather than from the
         * village's standing. Same two axes and the same ceiling; safe default: {@link #checkBias}.
         */
        default int opinionBias(ServerPlayer player, Entity villager, String axis) {
            return checkBias(player, villager, axis);
        }

        /** The stable id of this villager's community, or {@code ""} when they belong to none. */
        default String communityId(Entity villager) {
            return "";
        }
    }

    /** An authored standing test (§30.2). All fields optional and ANDed. */
    public record StandingQuery(Integer min, Integer max, String minTier, String maxTier, String hasTitle) {

        public boolean isEmpty() {
            return min == null && max == null && minTier == null && maxTier == null && hasTitle == null;
        }
    }

    /** An authored incident test (§30.2). */
    public record IncidentQuery(List<String> types, List<String> statuses, List<String> tags,
                                boolean knownToSpeaker, long maxAgeTicks) {

        public IncidentQuery {
            types = types == null ? List.of() : List.copyOf(types);
            statuses = statuses == null ? List.of() : List.copyOf(statuses);
            tags = tags == null ? List.of() : List.copyOf(tags);
        }
    }

    /**
     * A story from Reputation, normalized into the same shape Conversations' own gossip log produces
     * (§30.4).
     *
     * <p>Carries a phrase key and arguments, never a finished sentence: Reputation knows <em>that</em>
     * something happened and who knows it, Conversations knows how a nervous blacksmith says it at
     * dusk. {@code alreadyToldId} is the identity Conversations hangs its per-teller/per-listener
     * memory off, keeping that flag exactly where it has always lived.
     */
    public record GossipCandidate(UUID alreadyToldId, String typeId, long createdGameTime, String tone,
                                  String phraseKey, List<Component> arguments, int contribution) {

        /** §30.4 caps external arguments at four. */
        public static final int MAX_ARGUMENTS = 4;

        public GossipCandidate {
            arguments = arguments == null ? List.of()
                    : List.copyOf(arguments.subList(0, Math.min(arguments.size(), MAX_ARGUMENTS)));
        }
    }

    /** Installs the query façade. Called by the guarded compat class once the mod is confirmed present. */
    public static void setQueries(ReputationQueries impl) {
        queries = impl;
    }

    /** The query façade, or {@code null} when MCA: Reputation is absent — callers must null-check. */
    public static ReputationQueries queries() {
        return queries;
    }

    /** True once MCA: Reputation is confirmed present and the integration registered successfully. */
    public static boolean isAvailable() {
        return available && queries != null;
    }

    /**
     * Detects MCA: Reputation and installs the integration. Called once from common setup, after every
     * mod has loaded.
     *
     * <p>A failure here is never fatal: one ERROR is logged, {@link #isAvailable()} stays false, and
     * Conversations runs exactly as it does without the mod (§35.1).
     */
    public static void tryRegister() {
        available = false;
        queries = null;
        if (!ModList.get().isLoaded("mcareputation")) {
            McaConversations.LOGGER.info("[MCA: Conversations] MCA: Reputation is not installed; "
                    + "reputation-aware dialogue, the standing check term, and external gossip are off.");
            return;
        }
        try {
            // Named as a string, never referenced: a direct reference would put every Reputation type
            // that class mentions into this class's constant pool, and this class loads everywhere.
            Class.forName("dev.otectus.mcaconversations.compat.reputation.ConversationsReputationCompat")
                    .getMethod("register")
                    .invoke(null);
            available = queries != null;
            if (available) {
                McaConversations.LOGGER.info("[MCA: Conversations] MCA: Reputation detected; villagers now "
                        + "take public standing into account and can tell stories about your deeds.");
            } else {
                McaConversations.LOGGER.error("[MCA: Conversations] MCA: Reputation is present but its "
                        + "query façade did not install; the integration stays disabled.");
            }
        } catch (Throwable t) {
            available = false;
            queries = null;
            McaConversations.LOGGER.error("[MCA: Conversations] MCA: Reputation is installed but the "
                    + "integration could not start; dialogue continues without it.", t);
        }
    }

    // ------------------------------------------------------------------
    // Safe-by-default helpers used from dialogue evaluation
    // ------------------------------------------------------------------

    /**
     * The standing term added to a TRUST or RESPECT check (§30.3).
     *
     * <p>Hard-clamped to ±8 here as well as inside Reputation, so even malformed external data cannot
     * push public standing past the margin that separates check tiers. With the mod absent or the
     * integration disabled this is exactly {@code 0}, which is what keeps every existing seeded
     * outcome bit-for-bit unchanged.
     */
    public static int publicStandingFit(ServerPlayer player, Entity villager, String axis) {
        if (!isAvailable() || player == null || villager == null || axis == null) {
            return 0;
        }
        String normalized = axis.toLowerCase(java.util.Locale.ROOT);
        if (!appliesToAxis(normalized)) {
            return 0;
        }
        try {
            // A check always names a villager (the guard above), so whenever Reputation can answer the
            // per-villager question that is the better answer: what the blacksmith who watched it
            // happen makes of you, rather than what the village at large does. An older Reputation
            // has no such method, reports false, and the village-level bias is used unchanged.
            int raw = queries.supportsOpinionBias()
                    ? queries.opinionBias(player, villager, normalized)
                    : queries.checkBias(player, villager, normalized);
            return clampStandingFit(raw, normalized);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] reputation check bias failed; using 0", t);
            return 0;
        }
    }

    /**
     * Whether public standing has any business influencing this axis.
     *
     * <p>Only {@code trust} and {@code respect} (§30.3). Warmth, attraction, tension, and familiarity
     * are private interpersonal state between one villager and one player; what the village at large
     * thinks does not belong anywhere near them.
     */
    public static boolean appliesToAxis(String axis) {
        if (axis == null) {
            return false;
        }
        String normalized = axis.toLowerCase(java.util.Locale.ROOT);
        return normalized.equals("trust") || normalized.equals("respect");
    }

    /**
     * The clamp itself, pure and separately testable.
     *
     * <p>±8 is not arbitrary: Conversations' check resolver separates tiers by a 15-point margin, so a
     * term bounded here can shift a borderline outcome but can never carry a check on its own (§30.3).
     * Applied on this side as well as inside Reputation, so even a malformed datapack that somehow
     * bypassed that mod's own validation cannot exceed it.
     */
    public static int clampStandingFit(int raw, String axis) {
        return appliesToAxis(axis) ? Math.max(-8, Math.min(8, raw)) : 0;
    }

    /** The player's standing, or 0. Safe to call from anywhere. */
    public static int score(ServerPlayer player, Entity villager) {
        if (!isAvailable() || player == null || villager == null) {
            return 0;
        }
        try {
            return queries.score(player, villager);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] reputation score failed; using 0", t);
            return 0;
        }
    }

    /** The player's tier id, or {@code ""}. */
    public static String tierId(ServerPlayer player, Entity villager) {
        if (!isAvailable() || player == null || villager == null) {
            return "";
        }
        try {
            String tier = queries.tierId(player, villager);
            return tier == null ? "" : tier;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] reputation tier failed; using \"\"", t);
            return "";
        }
    }

    // ------------------------------------------------------------------
    // Standing remarks: a tier crossing waiting to be mentioned
    // ------------------------------------------------------------------

    /**
     * A tier crossing no villager has remarked on yet.
     *
     * @param communityId     the community whose opinion moved, as Reputation spells it
     * @param tierId          the tier the player has crossed into
     * @param upward          true when the village thinks better of them than it did
     * @param noticedGameTime the tick the crossing was recorded, which the timeout is measured from
     */
    public record PendingRemark(String communityId, String tierId, boolean upward, long noticedGameTime) {
    }

    /** player -> the one crossing waiting to be mentioned. Newest wins; only ever one per player. */
    private static final java.util.Map<UUID, PendingRemark> PENDING_REMARKS = new ConcurrentHashMap<>();

    /**
     * How long a crossing stays worth mentioning. A day: older than that it is not news, and a
     * villager opening with it a week later reads as a bug rather than as memory.
     */
    public static final long REMARK_TIMEOUT_TICKS = 24_000L;

    /** Bound on the map, so a long-running server cannot accumulate crossings forever. */
    public static final int MAX_PENDING_REMARKS = 256;

    /**
     * Records that a player's standing with a community crossed a tier.
     *
     * <p>Called only from {@code compat.reputation}, the one package allowed to name the event this
     * comes from. Everything held here is a string, a flag and a tick.
     */
    public static void noteStandingChange(UUID playerId, String communityId, String tierId,
                                          boolean upward, long now) {
        if (playerId == null || communityId == null || communityId.isEmpty()
                || tierId == null || tierId.isEmpty()) {
            return;
        }
        PENDING_REMARKS.values().removeIf(remark -> !isFresh(remark, now));
        if (PENDING_REMARKS.size() >= MAX_PENDING_REMARKS) {
            PENDING_REMARKS.clear();
        }
        PENDING_REMARKS.put(playerId, new PendingRemark(communityId, tierId, upward, now));
    }

    /**
     * Whether a crossing is still recent enough to mention. Pure, and the whole of the timeout rule.
     *
     * <p>A clock that has run backwards expires the entry rather than holding it forever: a crossing
     * nobody can date is one nobody should be greeted with.
     */
    public static boolean isFresh(PendingRemark remark, long now) {
        if (remark == null) {
            return false;
        }
        long age = now - remark.noticedGameTime();
        return age >= 0 && age < REMARK_TIMEOUT_TICKS;
    }

    /** The crossing this player has waiting, dropping it once it has timed out. */
    public static Optional<PendingRemark> pendingRemark(UUID playerId, long now) {
        if (playerId == null) {
            return Optional.empty();
        }
        PendingRemark remark = PENDING_REMARKS.get(playerId);
        if (remark == null) {
            return Optional.empty();
        }
        if (!isFresh(remark, now)) {
            PENDING_REMARKS.remove(playerId, remark);
            return Optional.empty();
        }
        return Optional.of(remark);
    }

    /** Takes the crossing, so the next villager the player meets does not raise it a second time. */
    public static Optional<PendingRemark> consumeStandingRemark(UUID playerId, long now) {
        Optional<PendingRemark> remark = pendingRemark(playerId, now);
        remark.ifPresent(value -> PENDING_REMARKS.remove(playerId, value));
        return remark;
    }

    /**
     * Whether <em>this</em> villager is one to raise the player's new standing.
     *
     * <p>Three things have to hold at once: the player crossed a tier recently, this villager lives
     * in the community that changed its mind, and they personally know a deed behind it. The last is
     * what stops a stranger across the square congratulating somebody on something they never heard
     * about.
     */
    public static boolean hasStandingRemark(ServerPlayer player, Entity villager, long now) {
        if (!isAvailable() || player == null || villager == null) {
            return false;
        }
        Optional<PendingRemark> pending = pendingRemark(player.getUUID(), now);
        if (pending.isEmpty()) {
            return false;
        }
        try {
            return pending.get().communityId().equals(queries.communityId(villager))
                    && queries.recentKnownDeed(player, villager).isPresent();
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] standing remark check failed; staying quiet", t);
            return false;
        }
    }

    /** Clears world-specific, transient remarks when the server stops. */
    public static void clearPendingRemarks() {
        PENDING_REMARKS.clear();
    }

    /** Compatibility alias for existing tests. */
    public static void clearPendingRemarksForTest() {
        clearPendingRemarks();
    }

    /** Test seam: install a stub façade without the real mod present. */
    public static void setAvailableForTest(boolean value, ReputationQueries impl) {
        available = value;
        queries = impl;
    }
}
