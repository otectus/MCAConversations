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

    // --- capability strings (MCA: Reputation's own ReputationCapabilities.FEATURE_* values) -------
    //
    // Spelled out here as plain strings because this class must not name a Reputation type, and
    // negotiated rather than assumed: since 0.4.1 every optional operation is additive to API
    // version 1, so the version handshake alone cannot say whether one exists. The guarded compat
    // class checks each of these against the constant it mirrors at registration time
    // (ConversationsReputationCompat#featureStringsAgree), so a renamed capability disables the
    // integration instead of silently reading as "unsupported" forever.

    /** {@code deliver(IncidentDelivery)} exists: a request plus a producer-owned operation identity. */
    public static final String FEATURE_DELIVERY = "delivery";

    /** Keyed deliveries leave a replayable receipt, so an apology cannot be paid for twice. */
    public static final String FEATURE_RECEIPTS = "receipts";

    /** One deed can absorb an earlier one, so two apology stages total one figure. */
    public static final String FEATURE_SUPERSEDE = "supersede";

    /** Selectors can be evaluated against a named speaker, and fail closed without one. */
    public static final String FEATURE_SPEAKER_QUERY = "speaker_query";

    /** Gossip carries a semantic revision and reports corrections instead of repeating them. */
    public static final String FEATURE_GOSSIP_STORY = "gossip_story";

    /** A community's public profile — recognition, facets, coverage — can be queried (0.6.0). */
    public static final String FEATURE_PROFILE_SNAPSHOT = "profile_snapshot_v1";

    /** A profile filtered through one villager's own knowledge can be queried (0.6.0). */
    public static final String FEATURE_SPEAKER_PROFILE = "speaker_profile_v1";

    /** A keyed delivery may carry a profile-aware supersession in one canonical commit (0.6.0). */
    public static final String FEATURE_PROFILED_DELIVERY = "profiled_delivery_v1";

    /** A profile-only change is published, so a cached profile-dependent answer can be dropped. */
    public static final String FEATURE_PROFILE_CHANGE = "profile_change_v1";

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
         * <p>Carries a {@link SignalRequest} rather than a loose incident/visibility/decision triple
         * because the identity of the deed is the interesting part: a repeated menu decision is not an
         * identity for several unrelated incidents, and the same apology must not pay again because a
         * different resident was standing there or because the screen was reopened. The request says
         * which incident the deed is bound to and which earlier decision it supersedes; the adapter
         * turns that into one operation key and one delivery.
         *
         * @return true when the delivery reached a terminal accepted outcome — applied, accepted with
         *         no public incident, or a replay of one that already was. False for every refusal.
         */
        boolean recordSignal(ServerPlayer player, Entity villager, SignalRequest request);

        /** A recent notable deed this villager knows, for the standing topic's flavour line. */
        Optional<Component> recentKnownDeed(ServerPlayer player, Entity villager);

        /** True when the player has an unresolved negative deed here that amends could address. */
        boolean hasUnresolvedNegativeIncident(ServerPlayer player, Entity villager);

        /**
         * Whether the installed MCA: Reputation answers the per-villager opinion question at all.
         *
         * <p>Defaults to {@code false}, which is the answer for a build that cannot report its
         * capabilities and for an operator who switched villager opinion off: either way the
         * village-level bias is used, rather than a zero that would quietly flatten every check.
         *
         * <p>Takes the player because the question is answered from the running server's capability
         * snapshot, not from a static probe — the feature can be switched off while the world is up.
         */
        default boolean supportsOpinionBias(ServerPlayer player) {
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

        /**
         * The capability strings the installed MCA: Reputation currently advertises (§14.4).
         *
         * <p>Empty for a build too old to answer the question at all, which reads as "none of the
         * optional operations exist" — the safe assumption, and the one that keeps the authored
         * fallback in play. A profile feature appears here <b>only while it is live</b>, because a
         * query that cannot answer must not look like a negative answer about the player.
         */
        default Set<String> features(ServerPlayer player) {
            return Set.of();
        }

        /**
         * Whether a player's public profile satisfies an authored predicate.
         *
         * <p>Three answers, never two: a speaker-scoped query with no resolvable speaker is
         * {@link ProfileAnswer#UNAVAILABLE} and must never be answered from the community's view of
         * the player, which is exactly how a stranger ends up greeted as a friend.
         */
        default ProfileAnswer matchesProfile(ServerPlayer player, Entity villager,
                                             ProfileQuerySpec query) {
            return ProfileAnswer.UNAVAILABLE;
        }

        /**
         * What this villager personally knows the player for, or empty when nobody could say.
         *
         * <p>A villager who genuinely knows nothing answers with a present, empty view
         * ({@link SpeakerProfileView#knowsPlayer()} false) rather than an empty optional: that valid
         * zero is a real answer and must not be replaced by the village's.
         */
        default Optional<SpeakerProfileView> speakerProfile(ServerPlayer player, Entity villager) {
            return Optional.empty();
        }

        /**
         * Drops server-scoped state — capability snapshots and the like — when the world goes away.
         *
         * <p>The registration itself survives: the façade is installed once per JVM from common
         * setup, so releasing it here would leave a second world loaded in the same process with no
         * integration at all.
         */
        default void clearServerState() {
        }
    }

    /** Whether an authored profile predicate held, failed, or could not be evaluated at all. */
    public enum ProfileAnswer {

        /** The profile was evaluated and every clause held. */
        MATCH,

        /** The profile was evaluated and a clause did not hold. A real answer about the player. */
        NO_MATCH,

        /**
         * Nobody could answer: Reputation absent, profiles off or unpublished, an invalid query, a
         * save whose history is incomplete, or — for a speaker-scoped query — no resolvable speaker.
         * The authored fallback is the only correct response.
         */
        UNAVAILABLE;

        public boolean matched() {
            return this == MATCH;
        }
    }

    /**
     * An authored public-profile test (§14.5, §16.2). All clauses are ANDed.
     *
     * @param scope              whose knowledge answers it: the whole community, or this speaker
     * @param minRecognition     inclusive lower bound on how widely known the player is
     * @param maxRecognition     inclusive upper bound; {@code 0} asks for a complete-history stranger
     * @param minRecognitionTier the lowest acceptable recognition tier id
     * @param facets             facet clauses, at most {@link #MAX_FACETS}
     * @param allowPartialHistory whether a clause that needs complete history may answer on a save
     *                            that cannot prove it; false by default, deliberately
     */
    public record ProfileQuerySpec(Scope scope, Integer minRecognition, Integer maxRecognition,
                                   String minRecognitionTier, List<FacetSpec> facets,
                                   boolean allowPartialHistory) {

        /** Reputation's own bound on one predicate; stated here so a pack fails at parse, not at use. */
        public static final int MAX_FACETS = 16;

        /** The largest evidence requirement a facet clause may state. */
        public static final int MAX_MIN_EVIDENCE = 64;

        public ProfileQuerySpec {
            scope = scope == null ? Scope.SPEAKER : scope;
            facets = facets == null ? List.of() : List.copyOf(facets);
        }

        /** Whose knowledge the question is asked of. */
        public enum Scope {

            /** What the village as a whole can say about the player. */
            COMMUNITY,

            /** What this villager personally knows, and nothing they have not heard. */
            SPEAKER;

            public static Optional<Scope> byKey(String key) {
                if (key == null) {
                    return Optional.empty();
                }
                return switch (key.trim().toLowerCase(java.util.Locale.ROOT)) {
                    case "community", "village" -> Optional.of(COMMUNITY);
                    case "speaker", "villager" -> Optional.of(SPEAKER);
                    default -> Optional.empty();
                };
            }
        }

        /**
         * One facet clause.
         *
         * @param facet           the facet id, as Reputation spells it ({@code mcareputation:bravery})
         * @param min             inclusive lower bound on the facet value
         * @param max             inclusive upper bound
         * @param minEvidence     how many live deeds must stand behind it; 1 unless authored otherwise
         * @param allowUnobserved whether a facet with no evidence may satisfy the clause — the named
         *                        escape hatch for "no contrary evidence is known", never a default
         */
        public record FacetSpec(String facet, Integer min, Integer max, int minEvidence,
                                boolean allowUnobserved) {
        }

        /** Whether this query states no clause at all, and so asks nothing of the profile. */
        public boolean isEmpty() {
            return minRecognition == null && maxRecognition == null && minRecognitionTier == null
                    && facets.isEmpty();
        }
    }

    /**
     * What one villager knows the player for, bounded to what a line may honestly draw on (§13.3).
     *
     * <p>Recognition is not warmth: a revered hero and an infamous murderer can carry the same number.
     * Nothing here authorises familiarity, hearts, or a greeting — Conversations' own relationship
     * rules keep that job.
     *
     * @param knowsPlayer      whether this villager knows any public evidence about the player at all
     * @param recognition      how widely known they are, in this villager's knowledge
     * @param recognitionTierId the recognition tier that value falls in, or {@code ""}
     * @param dominantFacets   at most three facet ids this villager would describe them by, strongest
     *                         first
     * @param knownIncidents   how many deeds this villager knows of, however they came to know
     * @param completeHistory  whether the save can prove the history behind it is complete
     */
    public record SpeakerProfileView(boolean knowsPlayer, int recognition, String recognitionTierId,
                                     List<String> dominantFacets, int knownIncidents,
                                     boolean completeHistory) {

        /** §16.2 keeps the descriptor list short: three traits is a remark, ten is a dossier. */
        public static final int MAX_DOMINANT_FACETS = 3;

        public SpeakerProfileView {
            recognitionTierId = recognitionTierId == null ? "" : recognitionTierId;
            dominantFacets = dominantFacets == null ? List.of()
                    : List.copyOf(dominantFacets.subList(0,
                            Math.min(dominantFacets.size(), MAX_DOMINANT_FACETS)));
        }
    }

    /**
     * One authored {@code conversations_reputation_signal} (§30.6, §16.2 "existing amends repair").
     *
     * @param incidentId          the incident definition to record; never a raw score delta
     * @param visibility          Reputation's visibility name, or null for the definition's own
     * @param decisionId          the authored decision, which names the apology <em>stage</em>
     * @param bindKnownIncident   whether the deed must be bound to an exact incident this villager
     *                            knows of — the identity that stops one apology paying twice while
     *                            leaving a second, unrelated incident independently addressable
     * @param bindTypes           incident types eligible to be bound, or empty for any
     * @param bindMaxAgeTicks     how far back an eligible incident may lie; 0 for no limit
     * @param supersedesDecisionId an earlier decision whose deed this one replaces, so a fuller
     *                            apology after a partial one totals one figure rather than two
     * @param supersedeWindowTicks how far back the superseded deed may lie
     */
    public record SignalRequest(String incidentId, String visibility, String decisionId,
                                boolean bindKnownIncident, List<String> bindTypes,
                                long bindMaxAgeTicks, String supersedesDecisionId,
                                long supersedeWindowTicks) {

        /** A week of game time: long enough for a real apology, short enough not to fold history. */
        public static final long DEFAULT_SUPERSEDE_WINDOW_TICKS = 168_000L;

        public SignalRequest {
            bindTypes = bindTypes == null ? List.of() : List.copyOf(bindTypes);
            bindMaxAgeTicks = Math.max(0L, bindMaxAgeTicks);
            supersedeWindowTicks = supersedeWindowTicks <= 0L
                    ? DEFAULT_SUPERSEDE_WINDOW_TICKS : supersedeWindowTicks;
            supersedesDecisionId = supersedesDecisionId == null || supersedesDecisionId.isBlank()
                    ? "" : supersedesDecisionId.trim();
        }

        /** The plain shape: an incident, a visibility and the decision that authored it. */
        public static SignalRequest of(String incidentId, String visibility, String decisionId) {
            return new SignalRequest(incidentId, visibility, decisionId, false, List.of(), 0L, "", 0L);
        }

        /** Whether this signal asks to replace an earlier decision's deed. */
        public boolean supersedes() {
            return !supersedesDecisionId.isEmpty();
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
        long now = gameTimeOf(player, villager);
        BiasKey key = new BiasKey(player.getUUID(), villager.getUUID(), normalized);
        CachedBias cached = BIAS_CACHE.get(key);
        if (cached != null && cached.isFresh(now)) {
            return cached.value();
        }
        try {
            // A check always names a villager (the guard above), so whenever Reputation can answer the
            // per-villager question that is the better answer: what the blacksmith who watched it
            // happen makes of you, rather than what the village at large does. An older Reputation
            // has no such method, reports false, and the village-level bias is used unchanged.
            //
            // Exactly one term, either way. Since 0.6.0 the per-villager answer is itself the
            // facet-aware one — the interpretation is inside that rung's bias, not a bonus beside it —
            // so adding the village term to it would be counting the same standing twice (§13.2).
            int raw = queries.supportsOpinionBias(player)
                    ? queries.opinionBias(player, villager, normalized)
                    : queries.checkBias(player, villager, normalized);
            int term = clampStandingFit(raw, normalized);
            cacheBias(key, term, now);
            return term;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] reputation check bias failed; using 0", t);
            return 0;
        }
    }

    // ------------------------------------------------------------------
    // The opinion term's short-lived memo
    // ------------------------------------------------------------------
    //
    // One dialogue page asks for this term once per check condition, and all four tier results of a
    // stance share one set of inputs — so a single page can ask the same question a dozen times. Since
    // 0.6.0 the answer behind it aggregates the evidence this villager knows rather than reading one
    // stored integer, which is cheap once and wasteful twelve times in the same tick.
    //
    // Twenty ticks, invalidated the moment Reputation says the player's standing or profile moved, and
    // cleared with the world. Deliberately not a longer cache: a memo that outlives its invalidation
    // signal is a wrong answer waiting for a reload.

    private record BiasKey(UUID player, UUID villager, String axis) {
    }

    private record CachedBias(int value, long tick) {

        boolean isFresh(long now) {
            long age = now - tick;
            return age >= 0 && age < BIAS_CACHE_TICKS;
        }
    }

    /** How long a memoized opinion term stays usable. One second of game time. */
    public static final long BIAS_CACHE_TICKS = 20L;

    /** Bound on the memo, so a busy server cannot accumulate pairs forever. */
    public static final int MAX_CACHED_BIASES = 512;

    private static final java.util.Map<BiasKey, CachedBias> BIAS_CACHE = new ConcurrentHashMap<>();

    private static void cacheBias(BiasKey key, int value, long now) {
        if (BIAS_CACHE.size() >= MAX_CACHED_BIASES) {
            BIAS_CACHE.clear();
        }
        BIAS_CACHE.put(key, new CachedBias(value, now));
    }

    private static long gameTimeOf(ServerPlayer player, Entity villager) {
        if (villager != null && villager.level() != null) {
            return villager.level().getGameTime();
        }
        return player == null || player.level() == null ? 0L : player.level().getGameTime();
    }

    /**
     * Drops what this mod remembers about one player's standing, because Reputation says it moved.
     *
     * <p>Called for a tier crossing and for a profile change — including the profile-only change no
     * standing event can describe, which is the whole reason Reputation publishes one (§15). The
     * revision is logged, not stored: this is an invalidation, and treating the numbers on an event as
     * a profile is how a cache starts disagreeing with the store it came from.
     */
    public static void invalidateStandingCache(UUID playerId) {
        if (playerId == null) {
            BIAS_CACHE.clear();
            return;
        }
        BIAS_CACHE.keySet().removeIf(key -> playerId.equals(key.player()));
    }

    // ------------------------------------------------------------------
    // Capability negotiation
    // ------------------------------------------------------------------

    /**
     * Every capability the installed Reputation currently advertises for this player's server.
     *
     * <p>Empty with the mod absent, and empty for a build too old to answer the question — both of
     * which read as "none of the optional operations exist", the assumption that keeps the authored
     * fallback in play. The player is required because capabilities are a property of a running
     * world: profiles can be switched off, or a pack's profile content unpublished, while the server
     * is up.
     */
    public static Set<String> features(ServerPlayer player) {
        if (!isAvailable() || player == null) {
            return Set.of();
        }
        try {
            Set<String> features = queries.features(player);
            return features == null ? Set.of() : features;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] reputation capability read failed; "
                    + "assuming none", t);
            return Set.of();
        }
    }

    /** Whether one named capability is live right now. */
    public static boolean hasFeature(ServerPlayer player, String feature) {
        return feature != null && features(player).contains(feature);
    }

    /**
     * Whether a profile question in this scope can be asked at all.
     *
     * <p>Asked before the query rather than inferred from its answer, so an authored fallback fires on
     * a build without profiles instead of on a player who has simply done nothing yet.
     */
    public static boolean supportsProfileScope(ServerPlayer player, ProfileQuerySpec.Scope scope) {
        Set<String> features = features(player);
        return scope == ProfileQuerySpec.Scope.COMMUNITY
                ? features.contains(FEATURE_PROFILE_SNAPSHOT)
                : features.contains(FEATURE_SPEAKER_PROFILE);
    }

    // ------------------------------------------------------------------
    // Public profiles
    // ------------------------------------------------------------------

    /**
     * Scores an authored profile predicate, three-valued.
     *
     * <p>{@link ProfileAnswer#UNAVAILABLE} for an absent mod, an unsupported scope, a query Reputation
     * refused, and a speaker it could not resolve. Never the community's answer to a speaker's
     * question (§13.3).
     */
    public static ProfileAnswer matchesProfile(ServerPlayer player, Entity villager,
                                               ProfileQuerySpec query) {
        if (!isAvailable() || player == null || villager == null || query == null) {
            return ProfileAnswer.UNAVAILABLE;
        }
        if (!supportsProfileScope(player, query.scope())) {
            return ProfileAnswer.UNAVAILABLE;
        }
        try {
            ProfileAnswer answer = queries.matchesProfile(player, villager, query);
            return answer == null ? ProfileAnswer.UNAVAILABLE : answer;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] reputation profile query failed; "
                    + "answering unavailable", t);
            return ProfileAnswer.UNAVAILABLE;
        }
    }

    /** What this villager knows the player for, or empty when nobody could say. */
    public static Optional<SpeakerProfileView> speakerProfile(ServerPlayer player, Entity villager) {
        if (!isAvailable() || player == null || villager == null
                || !features(player).contains(FEATURE_SPEAKER_PROFILE)) {
            return Optional.empty();
        }
        try {
            Optional<SpeakerProfileView> view = queries.speakerProfile(player, villager);
            return view == null ? Optional.empty() : view;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] speaker profile read failed; "
                    + "answering unavailable", t);
            return Optional.empty();
        }
    }

    /**
     * Records an authored conversation outcome as a public deed. False when nothing was recorded.
     *
     * <p>Safe to call with Reputation absent, where it is a no-op returning false — which is what
     * makes {@code conversations_reputation_signal} authorable in a pack that also has to load
     * without the mod.
     */
    public static boolean recordSignal(ServerPlayer player, Entity villager, SignalRequest request) {
        if (!isAvailable() || player == null || villager == null || request == null) {
            return false;
        }
        try {
            return queries.recordSignal(player, villager, request);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("[MCA: Conversations] reputation signal failed; nothing was "
                    + "recorded", t);
            return false;
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
        // The village has changed its mind, so whatever this mod memoized about how one of its
        // residents reads the player is now the old answer.
        invalidateStandingCache(playerId);
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

    /** Clears world-specific, transient remarks and memos when the server stops. */
    public static void clearPendingRemarks() {
        PENDING_REMARKS.clear();
        BIAS_CACHE.clear();
        ReputationQueries impl = queries;
        if (impl != null) {
            try {
                // The façade stays registered — it is installed once per JVM — but everything it
                // cached about the world that just closed goes with the world.
                impl.clearServerState();
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("[MCA: Conversations] reputation server-state clear "
                        + "failed; the next world re-reads it anyway", t);
            }
        }
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
