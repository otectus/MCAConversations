package dev.otectus.mcaconversations.compat.reputation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.ReputationBridge;
import dev.otectus.mcaconversations.compat.ReputationSignalIdentity;
import dev.otectus.mcareputation.api.DeliveryOutcome;
import dev.otectus.mcareputation.api.GossipStory;
import dev.otectus.mcareputation.api.IncidentDelivery;
import dev.otectus.mcareputation.api.IncidentQuery;
import dev.otectus.mcareputation.api.McaReputationApi;
import dev.otectus.mcareputation.api.ReceiptOutcome;
import dev.otectus.mcareputation.api.ReceiptView;
import dev.otectus.mcareputation.api.ReputationCapabilities;
import dev.otectus.mcareputation.api.ReputationIncidentView;
import dev.otectus.mcareputation.api.ReputationQuery;
import dev.otectus.mcareputation.api.ReputationRequest;
import dev.otectus.mcareputation.api.ReputationSnapshot;
import dev.otectus.mcareputation.api.SpeakerContext;
import dev.otectus.mcareputation.api.SupersedeSpec;
import dev.otectus.mcareputation.api.profile.ProfileQuery;
import dev.otectus.mcareputation.api.profile.ProfileQueryResult;
import dev.otectus.mcareputation.api.profile.ProfiledDelivery;
import dev.otectus.mcareputation.api.profile.VillagerProfileSnapshot;
import dev.otectus.mcareputation.community.CommunityKey;
import dev.otectus.mcareputation.incident.IncidentStatus;
import dev.otectus.mcareputation.incident.IncidentVisibility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.NeoForge;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * The MCA: Reputation-backed implementation of {@link ReputationBridge.ReputationQueries} (spec §30).
 *
 * <p><b>Only ever loaded after {@code ModList.get().isLoaded("mcareputation")}.</b>
 * {@link ReputationBridge#tryRegister()} reaches it by name for exactly that reason; a direct
 * reference would drag every {@code mcareputation} type named below into the bridge's constant pool,
 * and the bridge loads on every installation.
 *
 * <p>Reputation supplies facts; Conversations supplies voice. Nothing here writes a disposition axis,
 * grants hearts, or decides what a villager says — those belong to Conversations and MCA respectively
 * (§4). What it does is answer "what does the village think of this player", "does this villager know
 * about that", and "record that the player just apologised in public".
 */
public final class ConversationsReputationCompat implements ReputationBridge.ReputationQueries {

    /** The namespace and source every Conversations-authored deed is filed under. */
    private static final ResourceLocation SOURCE =
            ResourceLocation.fromNamespaceAndPath(ReputationSignalIdentity.NAMESPACE, "conversations");

    /**
     * How long a capability snapshot is reused. Five seconds of game time.
     *
     * <p>Capabilities are not static: the profile rows appear only while profiles are enabled and a
     * pack has published content, so a datapack reload or a config change can add or withdraw them
     * mid-session. Short enough that a reload is picked up within a conversation or two, long enough
     * that a page full of check conditions does not re-inspect the authority table a dozen times.
     */
    private static final long CAPABILITY_TTL_TICKS = 100L;

    /** The last capability snapshot, the server it came from, and when it was taken. */
    private static volatile CapabilitySnapshot capabilities = CapabilitySnapshot.EMPTY;

    /**
     * One capability reading.
     *
     * <p>The server is held weakly and compared by identity: a snapshot must not outlive the world it
     * describes, and a strong reference to a stopped server is a leak nobody would look for here.
     */
    private record CapabilitySnapshot(java.lang.ref.WeakReference<MinecraftServer> server, long tick,
                                      boolean opinionEnabled, Set<String> features) {

        static final CapabilitySnapshot EMPTY =
                new CapabilitySnapshot(null, Long.MIN_VALUE, false, Set.of());

        boolean isFresh(MinecraftServer current, long now) {
            if (server == null || server.get() != current) {
                return false;
            }
            long age = now - tick;
            return age >= 0 && age < CAPABILITY_TTL_TICKS;
        }
    }

    private ConversationsReputationCompat() {
    }

    /** Installs the façade. Invoked reflectively by the bridge after the mod-present check. */
    public static void register() {
        // v2 is the NeoForge 1.21.1 generation of the Reputation API: its public event types extend
        // NeoForge's event base, so anything compiled against v1 no longer links.
        if (McaReputationApi.getApiVersion() != 2) {
            McaConversations.LOGGER.error("[MCA: Conversations] MCA: Reputation reports API v{} but this "
                    + "build was written against v2; the integration stays disabled.",
                    McaReputationApi.getApiVersion());
            return;
        }
        if (!featureStringsAgree()) {
            McaConversations.LOGGER.error("[MCA: Conversations] MCA: Reputation has renamed a capability "
                    + "string this build negotiates against; the integration stays disabled rather than "
                    + "reading every optional operation as absent.");
            return;
        }
        // Not an @EventBusSubscriber, for the same reason ConversationsQuestsEvents is not one:
        // the annotation would put a Reputation event type on the classpath of an install that has no
        // Reputation. Registered here instead, where the mod is already known to be present.
        NeoForge.EVENT_BUS.register(new ConversationsReputationEvents());
        ReputationBridge.setQueries(new ConversationsReputationCompat());
    }

    /**
     * That the capability strings the always-loaded bridge spells out still match Reputation's own.
     *
     * <p>The bridge cannot name {@code ReputationCapabilities} — it loads on installs that have no
     * Reputation — so the two lists are checked against each other here, once, where both are on the
     * classpath. A rename would otherwise make every optional operation read as permanently absent,
     * which is a silent degradation rather than a fault anybody would notice.
     */
    private static boolean featureStringsAgree() {
        return ReputationBridge.FEATURE_DELIVERY.equals(ReputationCapabilities.FEATURE_DELIVERY)
                && ReputationBridge.FEATURE_RECEIPTS.equals(ReputationCapabilities.FEATURE_RECEIPTS)
                && ReputationBridge.FEATURE_SUPERSEDE.equals(ReputationCapabilities.FEATURE_SUPERSEDE)
                && ReputationBridge.FEATURE_SPEAKER_QUERY
                        .equals(ReputationCapabilities.FEATURE_SPEAKER_QUERY)
                && ReputationBridge.FEATURE_GOSSIP_STORY
                        .equals(ReputationCapabilities.FEATURE_GOSSIP_STORY)
                && ReputationBridge.FEATURE_PROFILE_SNAPSHOT
                        .equals(ReputationCapabilities.FEATURE_PROFILE_SNAPSHOT)
                && ReputationBridge.FEATURE_SPEAKER_PROFILE
                        .equals(ReputationCapabilities.FEATURE_SPEAKER_PROFILE)
                && ReputationBridge.FEATURE_PROFILED_DELIVERY
                        .equals(ReputationCapabilities.FEATURE_PROFILED_DELIVERY)
                && ReputationBridge.FEATURE_PROFILE_CHANGE
                        .equals(ReputationCapabilities.FEATURE_PROFILE_CHANGE);
    }

    /**
     * What the installed build can do right now (§14.4).
     *
     * <p>This replaces 1.7.1's single reflective method probe for the per-villager opinion bias.
     * Reflection was the right answer when one additive method was in question; it is the wrong
     * answer to nine, and
     * Reputation's own guidance since 0.5.0 is to negotiate through {@code capabilities(server)}
     * rather than to keep reflecting over methods. {@code capabilities} is itself additive, so the one
     * remaining unknown — a build too old to have it — is absorbed here: no capabilities means no
     * optional operations, which is exactly the pre-0.4.1 behaviour.
     */
    private static CapabilitySnapshot capabilities(MinecraftServer server) {
        if (server == null) {
            return CapabilitySnapshot.EMPTY;
        }
        long now = server.overworld() == null ? 0L : server.overworld().getGameTime();
        CapabilitySnapshot cached = capabilities;
        if (cached.isFresh(server, now)) {
            return cached;
        }
        try {
            ReputationCapabilities live = McaReputationApi.capabilities(server);
            CapabilitySnapshot snapshot = new CapabilitySnapshot(
                    new java.lang.ref.WeakReference<>(server), now, live.opinionEnabled(),
                    Set.copyOf(live.features()));
            capabilities = snapshot;
            return snapshot;
        } catch (Throwable t) {
            // A build without capabilities(), or one that threw inspecting itself. Either way the safe
            // answer is "none of the optional operations", which keeps every authored fallback in play.
            McaConversations.LOGGER.debug("[MCA: Conversations] MCA: Reputation could not report its "
                    + "capabilities; optional operations stay off and village-level standing is used.", t);
            CapabilitySnapshot snapshot = new CapabilitySnapshot(
                    new java.lang.ref.WeakReference<>(server), now, false, Set.of());
            capabilities = snapshot;
            return snapshot;
        }
    }

    /** Forgets the capability snapshot; called when the world goes away. */
    static void clearCapabilities() {
        capabilities = CapabilitySnapshot.EMPTY;
    }

    /**
     * The check bias from one villager's own opinion (§30.3 read per villager).
     *
     * <p>Since 0.6.0 this single call <b>is</b> the facet-aware answer: where a profile is available
     * Reputation picks the rung from the villager's final, facet-adjusted opinion rather than from
     * their scalar one, inside the same ±8. That is why nothing here adds a facet term beside it —
     * the interpretation is already inside the number, and adding a second one would count the same
     * evidence twice (§13.2, §16.2).
     */
    static int opinionBias(MinecraftServer server, UUID villager, UUID player, CommunityKey community,
                           String axis) {
        if (server == null || villager == null || player == null || community == null
                || !capabilities(server).opinionEnabled()) {
            return 0;
        }
        return McaReputationApi.getOpinionBias(server, player, villager, community, axis);
    }

    /** The community a villager belongs to, or empty. */
    private static Optional<CommunityKey> community(Entity villager) {
        return McaReputationApi.resolveCommunity(villager);
    }

    private static Optional<ReputationSnapshot> snapshot(ServerPlayer player, Entity villager) {
        return community(villager)
                .flatMap(key -> McaReputationApi.getSnapshot(player.server, player.getUUID(), key));
    }

    // ------------------------------------------------------------------
    // Reads
    // ------------------------------------------------------------------

    @Override
    public int score(ServerPlayer player, Entity villager) {
        return community(villager)
                .map(key -> McaReputationApi.getScoreOrZero(player.server, player.getUUID(), key))
                .orElse(0);
    }

    @Override
    public OptionalInt localScore(ServerPlayer player, Entity villager) {
        Optional<CommunityKey> key = community(villager);
        if (key.isEmpty()) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(McaReputationApi.getScoreOrZero(player.server, player.getUUID(), key.get()));
    }

    @Override
    public String tierId(ServerPlayer player, Entity villager) {
        return community(villager)
                .map(key -> McaReputationApi.getTierId(player.server, player.getUUID(), key))
                .orElse("");
    }

    @Override
    public int checkBias(ServerPlayer player, Entity villager, String axis) {
        return community(villager)
                .map(key -> McaReputationApi.getCheckBias(player.server, player.getUUID(), key, axis))
                .orElse(0);
    }

    @Override
    public boolean supportsOpinionBias(ServerPlayer player) {
        return player != null && capabilities(player.server).opinionEnabled();
    }

    @Override
    public Set<String> features(ServerPlayer player) {
        return player == null ? Set.of() : capabilities(player.server).features();
    }

    @Override
    public void clearServerState() {
        clearCapabilities();
    }

    @Override
    public int opinionBias(ServerPlayer player, Entity villager, String axis) {
        return community(villager)
                .map(key -> opinionBias(player.server, villager.getUUID(), player.getUUID(), key, axis))
                .orElse(0);
    }

    @Override
    public String communityId(Entity villager) {
        return community(villager).map(CommunityKey::asString).orElse("");
    }

    @Override
    public boolean matchesStanding(ServerPlayer player, Entity villager,
                                   ReputationBridge.StandingQuery query) {
        Optional<CommunityKey> key = community(villager);
        if (key.isEmpty()) {
            return false; // no village: no public to have an opinion, so nothing matches
        }
        ReputationQuery.Builder builder = ReputationQuery.builder();
        if (query.min() != null) {
            builder.min(query.min());
        }
        if (query.max() != null) {
            builder.max(query.max());
        }
        builder.minTier(query.minTier()).maxTier(query.maxTier());
        if (query.hasTitle() != null) {
            ResourceLocation title = ResourceLocation.tryParse(query.hasTitle());
            if (title == null) {
                // An unparseable title id cannot be satisfied. §30.2 asks for a safe zero rather than
                // an error, so the authored fallback branch fires instead of the conversation dying.
                return false;
            }
            builder.hasTitle(title);
        }
        return McaReputationApi.matches(player.server, player.getUUID(), key.get(), builder.build());
    }

    @Override
    public boolean matchesIncident(ServerPlayer player, Entity villager,
                                   ReputationBridge.IncidentQuery query) {
        Optional<CommunityKey> key = community(villager);
        if (key.isEmpty()) {
            return false;
        }
        IncidentQuery.Builder builder = IncidentQuery.builder()
                .tags(query.tags())
                .maxAgeTicks(query.maxAgeTicks())
                .newestOnly(false);
        for (String type : query.types()) {
            ResourceLocation parsed = ResourceLocation.tryParse(type);
            if (parsed == null) {
                return false;
            }
            builder.type(parsed);
        }
        for (String status : query.statuses()) {
            Optional<IncidentStatus> parsed = IncidentStatus.byName(status);
            if (parsed.isEmpty()) {
                return false;
            }
            builder.status(parsed.get());
        }
        List<ReputationIncidentView> matches =
                McaReputationApi.selectIncidents(player.server, player.getUUID(), key.get(), builder.build());
        if (matches.isEmpty()) {
            return false;
        }
        if (!query.knownToSpeaker()) {
            return true;
        }
        // "Known to the speaker" is the difference between a villager who saw it and one who has not
        // heard yet. Asking Reputation per incident is what keeps the rumour delay meaningful (§19.3).
        return matches.stream().anyMatch(view ->
                McaReputationApi.villagerKnows(player.server, villager, player.getUUID(), view.id()));
    }

    // ------------------------------------------------------------------
    // Public profiles (0.6.0)
    // ------------------------------------------------------------------

    /**
     * Scores an authored profile predicate against what the village, or this villager, can say.
     *
     * <p>Reads the <b>detailed</b> result, never a convenience zero: "no recognition here" and "we
     * cannot say" are different facts, and a condition that conflated them would eventually let an
     * authored gate open on the strength of a switched-off feature. A speaker-scoped question with no
     * resolvable speaker comes back {@code UNRESOLVED} from Reputation and stays
     * {@link ReputationBridge.ProfileAnswer#UNAVAILABLE} here — it is never re-asked of the community,
     * because a villager who has heard nothing is not a village that has (§13.1, §13.3).
     */
    @Override
    public ReputationBridge.ProfileAnswer matchesProfile(ServerPlayer player, Entity villager,
                                                         ReputationBridge.ProfileQuerySpec spec) {
        Optional<CommunityKey> key = community(villager);
        if (key.isEmpty()) {
            // No village: there is no public here to hold a profile, and no honest answer to give.
            return ReputationBridge.ProfileAnswer.UNAVAILABLE;
        }
        ProfileQuery query = toProfileQuery(spec);
        if (query == null) {
            return ReputationBridge.ProfileAnswer.UNAVAILABLE;
        }
        ProfileQueryResult<Boolean> result =
                spec.scope() == ReputationBridge.ProfileQuerySpec.Scope.COMMUNITY
                        ? McaReputationApi.matchesProfile(player.server, player.getUUID(), key.get(),
                                query)
                        : McaReputationApi.matchesSpeakerProfile(player.server, player.getUUID(),
                                villager, query);
        if (!result.isAvailable()) {
            if (McaConversations.LOGGER.isDebugEnabled()) {
                McaConversations.LOGGER.debug("[MCA: Conversations] profile condition unavailable ({} {});"
                        + " the authored fallback runs", result.availability(),
                        result.reason().orElse("no reason given"));
            }
            return ReputationBridge.ProfileAnswer.UNAVAILABLE;
        }
        return result.value().orElse(false)
                ? ReputationBridge.ProfileAnswer.MATCH
                : ReputationBridge.ProfileAnswer.NO_MATCH;
    }

    /** Translates the authored predicate into Reputation's own, or null when it cannot be built. */
    private static ProfileQuery toProfileQuery(ReputationBridge.ProfileQuerySpec spec) {
        ProfileQuery.Builder builder = ProfileQuery.builder()
                .allowPartialHistory(spec.allowPartialHistory());
        if (spec.minRecognition() != null) {
            builder.minRecognition(spec.minRecognition());
        }
        if (spec.maxRecognition() != null) {
            builder.maxRecognition(spec.maxRecognition());
        }
        if (spec.minRecognitionTier() != null && !spec.minRecognitionTier().isBlank()) {
            builder.minRecognitionTier(spec.minRecognitionTier().trim().toLowerCase(Locale.ROOT));
        }
        for (ReputationBridge.ProfileQuerySpec.FacetSpec facet : spec.facets()) {
            ResourceLocation id = ResourceLocation.tryParse(facet.facet());
            if (id == null) {
                // An unparseable facet id cannot be satisfied and must not be silently dropped: a
                // query missing one of its clauses is a broader query than the pack authored.
                return null;
            }
            builder.facet(new ProfileQuery.FacetPredicate(id,
                    facet.min() == null ? OptionalInt.empty() : OptionalInt.of(facet.min()),
                    facet.max() == null ? OptionalInt.empty() : OptionalInt.of(facet.max()),
                    facet.minEvidence(), facet.allowUnobserved()));
        }
        ProfileQuery query = builder.build();
        // Reputation answers an invalid query UNRESOLVED rather than false, but building one at all is
        // a pack error worth refusing here, where the authored ids are still in hand.
        return query.valid() ? query : null;
    }

    /**
     * What this villager knows the player for (§16.2's bounded template values).
     *
     * <p>A villager who genuinely knows nothing is a present, empty view rather than an absent one:
     * that valid zero is a real answer, and falling back from it to the community's profile is
     * precisely the stranger-treated-as-a-friend defect §13.3 forbids. Nothing here is warmth —
     * recognition can authorise "I have heard your name" and never "my dear friend".
     */
    @Override
    public Optional<ReputationBridge.SpeakerProfileView> speakerProfile(ServerPlayer player,
                                                                        Entity villager) {
        ProfileQueryResult<VillagerProfileSnapshot> result =
                McaReputationApi.getVillagerProfileDetailed(player.server, player.getUUID(), villager);
        if (!result.isAvailable()) {
            return Optional.empty();
        }
        VillagerProfileSnapshot snapshot = result.value().orElseThrow();
        List<String> dominant = new ArrayList<>();
        snapshot.knownProfile().dominantFacets()
                .forEach(facet -> dominant.add(facet.toString()));
        return Optional.of(new ReputationBridge.SpeakerProfileView(
                snapshot.knowsAnything(),
                snapshot.knownProfile().recognition().value(),
                snapshot.knownProfile().recognition().tierId(),
                dominant,
                snapshot.knownIncidents(),
                snapshot.knownProfile().coverage()
                        == dev.otectus.mcareputation.api.profile.ProfileCoverage
                                .COMPLETE_SINCE_RECORD_START));
    }

    // ------------------------------------------------------------------
    // Gossip
    // ------------------------------------------------------------------

    @Override
    public Optional<ReputationBridge.GossipCandidate> nextGossip(ServerPlayer player, Entity teller,
                                                                 Set<String> types, long maxAgeTicks) {
        List<ReputationBridge.GossipCandidate> candidates =
                gossipCandidates(player, teller, types, maxAgeTicks, 1);
        return candidates.isEmpty() ? Optional.empty() : Optional.of(candidates.get(0));
    }

    @Override
    public List<ReputationBridge.GossipCandidate> gossipCandidates(ServerPlayer player, Entity teller,
                                                                   Set<String> types, long maxAgeTicks,
                                                                   int limit) {
        Optional<CommunityKey> key = community(teller);
        if (key.isEmpty() || limit <= 0) {
            return List.of();
        }
        IncidentQuery.Builder builder = IncidentQuery.builder()
                .maxAgeTicks(maxAgeTicks)
                .newestOnly(false);
        if (types != null) {
            for (String type : types) {
                ResourceLocation parsed = ResourceLocation.tryParse(type);
                if (parsed == null) {
                    return List.of();
                }
                builder.type(parsed);
            }
        }
        List<ReputationIncidentView> views =
                McaReputationApi.selectIncidents(player.server, player.getUUID(), key.get(), builder.build());

        // Corrections are not stories. A deed a later one absorbed, or one the village has decided did
        // not happen, still has a gossip candidate on the older call — and telling it in its original
        // accusatory form is exactly what §16.2 forbids. gossipStory is the only surface that knows,
        // so where it exists the incident it reports as a correction is dropped from the list.
        Set<UUID> corrections = corrections(player, teller, key.get());

        // selectIncidents already returns newest-first with a stable tiebreak, so taking the first
        // {@code limit} this villager actually knows keeps the §30.4 determinism.
        List<ReputationBridge.GossipCandidate> out = new java.util.ArrayList<>();
        for (ReputationIncidentView view : views) {
            if (out.size() >= limit) {
                break;
            }
            if (corrections.contains(view.id())) {
                continue;
            }
            if (!McaReputationApi.villagerKnows(player.server, teller, player.getUUID(), view.id())) {
                continue;
            }
            McaReputationApi.gossipCandidate(player.server, player.getUUID(), key.get(), view.id(),
                            player.getGameProfile().getName())
                    .ifPresent(candidate -> out.add(new ReputationBridge.GossipCandidate(
                            candidate.incidentId(),
                            candidate.typeId().toString(),
                            candidate.createdGameTime(),
                            candidate.effectiveTone(),
                            candidate.phraseKey(),
                            candidate.arguments(),
                            candidate.contribution())));
        }
        return List.copyOf(out);
    }

    /**
     * Incidents this villager should acknowledge rather than retell.
     *
     * <p>{@code gossipStory} answers for the newest story only, which is the one a villager would
     * actually raise, so this set holds at most one id. {@code gossipCandidate} stays the source of
     * the candidate list itself: 0.6.0 leaves it and {@code ExternalGossipCandidate} untouched
     * precisely so an adapter keeps its baseline behaviour, and nothing in the story surface offers a
     * filtered <em>list</em>. Empty when the capability is absent, which is the 1.7.1 behaviour.
     */
    private static Set<UUID> corrections(ServerPlayer player, Entity teller, CommunityKey community) {
        if (!capabilities(player.server).features().contains(ReputationBridge.FEATURE_GOSSIP_STORY)) {
            return Set.of();
        }
        Optional<GossipStory> story =
                McaReputationApi.gossipStory(player.server, teller, player.getUUID(), community);
        if (story.isEmpty() || !story.get().correction()) {
            return Set.of();
        }
        Set<UUID> out = new LinkedHashSet<>();
        out.add(story.get().incidentId());
        return Set.copyOf(out);
    }

    // ------------------------------------------------------------------
    // Writes
    // ------------------------------------------------------------------

    /**
     * Records an authored conversation outcome as a public deed, exactly once (§30.6, §16.2).
     *
     * <p>Three things changed in 1.7.2, and all three are identity:
     *
     * <ul>
     *   <li><b>The key no longer names the villager.</b> It named one until now, which made the same
     *       apology payable again by repeating it to a different resident. Reputation's receipt
     *       identity is already {@code namespace + player + community + key}, so the villager added
     *       nothing to it but a way to pay twice.</li>
     *   <li><b>A bound deed carries the incident it answers.</b> An apology stage alone cannot be the
     *       identity of every grievance a village holds, so a signal that declares {@code binds}
     *       resolves the exact eligible incident <em>this villager knows about</em> and puts it in the
     *       key. One apology per incident, and a second, unrelated incident stays independently
     *       addressable. With nothing eligible to amend, nothing is recorded at all — an apology for
     *       a grievance the speaker has never heard of is not a deed.</li>
     *   <li><b>An amending decision supersedes its precursor instead of stacking.</b> The earlier
     *       decision's own receipt names the deed it produced, so a fuller apology after a partial one
     *       folds it rather than adding a second payment for the same repair.</li>
     * </ul>
     *
     * <p>The villager is supplied as a real {@link SpeakerContext} for the knowledge-filtered
     * selection, and as a real witness on the request: a deed the speaker was present for may honestly
     * be recorded as witnessed, which is not the same as labelling a private exchange public.
     */
    @Override
    public boolean recordSignal(ServerPlayer player, Entity villager,
                                ReputationBridge.SignalRequest signal) {
        Optional<CommunityKey> key = community(villager);
        ResourceLocation incident = ResourceLocation.tryParse(signal.incidentId());
        if (key.isEmpty() || incident == null || signal.decisionId() == null
                || signal.decisionId().isBlank()) {
            return false;
        }
        MinecraftServer server = player.server;
        CommunityKey community = key.get();
        Set<String> features = capabilities(server).features();

        Optional<UUID> bound = Optional.empty();
        if (signal.bindKnownIncident()) {
            bound = boundIncident(player, villager, community, signal, features);
            if (bound.isEmpty()) {
                // Nothing this villager knows of is open to amends. Recording an unbound apology here
                // would pay for a grievance nobody has, which is the farm this binding exists to close.
                return false;
            }
        }

        String operationKey =
                ReputationSignalIdentity.operationKey(signal.decisionId(),
                        bound.map(UUID::toString).orElse(null));
        ReputationRequest.Builder request = ReputationRequest
                .builder(server, player.getUUID(), community, incident, SOURCE)
                // The wrapper key and the request's own dedupe key must agree after normalisation
                // (Reputation §11.2), so there is exactly one of them and it is this one.
                .dedupeKey(operationKey)
                .subject(dev.otectus.mcareputation.incident.IncidentSubject.villager(villager.getUUID(),
                        villager.getDisplayName().getString(), "subject"))
                .witness(villager.getUUID())
                .context("decision", signal.decisionId());
        bound.ifPresent(id -> request.context("amends_incident", id.toString()));
        if (signal.visibility() != null) {
            Optional<IncidentVisibility> parsed = IncidentVisibility.byName(signal.visibility());
            if (parsed.isEmpty()) {
                return false; // A misspelled private visibility must never inherit the public default.
            }
            request.visibility(parsed.get());
        }

        IncidentDelivery delivery = IncidentDelivery.of(request.build(),
                ReputationSignalIdentity.NAMESPACE, operationKey);
        Optional<SupersedeSpec> supersede = supersedeSpec(player, community, signal, bound, features);

        if (supersede.isPresent() && features.contains(ReputationBridge.FEATURE_PROFILED_DELIVERY)) {
            // One canonical commit: the receipt is filed inside the same mutation that folds the
            // precursor, so a replay cannot fold it twice.
            return accepted(McaReputationApi.deliverProfiled(
                    ProfiledDelivery.superseding(delivery, supersede.get())).outcome(), operationKey);
        }
        if (supersede.isPresent() && features.contains(ReputationBridge.FEATURE_SUPERSEDE)) {
            // No receipt on this path, but the request carries the same dedupe key, so a second click
            // is still a no-op.
            return McaReputationApi.recordSuperseding(delivery.request(), supersede.get()).applied();
        }
        if (features.contains(ReputationBridge.FEATURE_DELIVERY)) {
            return accepted(McaReputationApi.deliver(delivery), operationKey);
        }
        // A build older than deliveries: the plain write, with the same identity as its dedupe key.
        return McaReputationApi.record(delivery.request()).applied();
    }

    /**
     * Reads a typed delivery outcome honestly.
     *
     * <p>Applied, accepted-with-no-public-incident and a duplicate of either are all "this operation
     * is done" — a duplicate in particular must not be retried or reported as a failure, because the
     * first delivery's answer is the answer. A refusal is false, and the two refusals an operator can
     * fix say so at debug: the caller has no retry queue to put them in, so the honest thing is to
     * record nothing rather than to half-apply a deed.
     */
    private static boolean accepted(DeliveryOutcome outcome, String operationKey) {
        ReceiptOutcome receipt = outcome.outcome();
        boolean done = receipt == ReceiptOutcome.APPLIED
                || receipt == ReceiptOutcome.ACCEPTED_NO_PUBLIC_INCIDENT
                || receipt == ReceiptOutcome.DUPLICATE;
        if (!done) {
            McaConversations.LOGGER.debug("[MCA: Conversations] reputation refused '{}' ({}{}); the "
                    + "conversation continues and nothing was recorded", operationKey, receipt,
                    outcome.retryable() ? ", retryable" : "");
        }
        return done;
    }

    /**
     * The exact incident an authored amends answers, as this villager knows it.
     *
     * <p>Knowledge-filtered on purpose, through a real {@link SpeakerContext}: the speaker-less
     * selector overload fails closed on {@code knownToSpeaker} since 0.4.1, and an apology offered to
     * somebody who never heard about the deed is not repair. Newest first, still open, and actually
     * negative — a deed that cost the player nothing has nothing to amend.
     */
    private static Optional<UUID> boundIncident(ServerPlayer player, Entity villager,
                                                CommunityKey community,
                                                ReputationBridge.SignalRequest signal,
                                                Set<String> features) {
        if (!features.contains(ReputationBridge.FEATURE_SPEAKER_QUERY)) {
            // Without a speaker-aware selector there is no way to tell a grievance this villager
            // knows of from one they have never heard about, and a bound deed is exactly the deed
            // that must not be recorded on a guess.
            McaConversations.LOGGER.debug("[MCA: Conversations] MCA: Reputation cannot evaluate a "
                    + "speaker-aware selector, so the authored amends records nothing rather than "
                    + "binding to a deed this villager may not know about");
            return Optional.empty();
        }
        Optional<SpeakerContext> speaker = McaReputationApi.speakerContext(player.server, villager);
        if (speaker.isEmpty()) {
            return Optional.empty();
        }
        IncidentQuery.Builder query = IncidentQuery.builder()
                .status(IncidentStatus.ACTIVE)
                .knownToSpeaker(true)
                .maxAgeTicks(signal.bindMaxAgeTicks())
                .newestOnly(false);
        for (String type : signal.bindTypes()) {
            ResourceLocation parsed = ResourceLocation.tryParse(type);
            if (parsed == null) {
                return Optional.empty();
            }
            query.type(parsed);
        }
        return McaReputationApi
                .selectIncidents(player.server, player.getUUID(), community, query.build(),
                        speaker.get())
                .stream()
                .filter(view -> view.currentContribution() < 0 || view.baseDelta() < 0)
                .map(ReputationIncidentView::id)
                .findFirst();
    }

    /**
     * The precursor an amending decision replaces, found through its own receipt.
     *
     * <p>A read-only lookup by the earlier decision's operation key: no scan, no newest-incident
     * guess, and no chance of folding an unrelated deed that happens to be recent. Empty when the
     * earlier stage was never delivered here, when receipts are unavailable, or when its receipt has
     * aged past the replay horizon — in which case the successor is simply recorded on its own terms,
     * which is what Reputation does with a failed supersede validation anyway.
     */
    private static Optional<SupersedeSpec> supersedeSpec(ServerPlayer player, CommunityKey community,
                                                         ReputationBridge.SignalRequest signal,
                                                         Optional<UUID> bound,
                                                         Set<String> features) {
        if (!signal.supersedes() || !features.contains(ReputationBridge.FEATURE_RECEIPTS)) {
            return Optional.empty();
        }
        String precursorKey = ReputationSignalIdentity.operationKey(signal.supersedesDecisionId(),
                bound.map(UUID::toString).orElse(null));
        return McaReputationApi
                .findReceipt(player.server, ReputationSignalIdentity.NAMESPACE, player.getUUID(),
                        community, precursorKey)
                .flatMap(ReceiptView::incidentId)
                .map(precursor -> SupersedeSpec.of(precursor, signal.supersedeWindowTicks(), false));
    }

    // ------------------------------------------------------------------
    // Standing topic support (§30.5)
    // ------------------------------------------------------------------

    @Override
    public Optional<Component> recentKnownDeed(ServerPlayer player, Entity villager) {
        Optional<CommunityKey> key = community(villager);
        if (key.isEmpty()) {
            return Optional.empty();
        }
        return McaReputationApi.recentIncidents(player.server, player.getUUID(), key.get(), 16).stream()
                .filter(view -> view.severity().isNotable() || view.currentContribution() != 0)
                .filter(view -> McaReputationApi.villagerKnows(player.server, villager, player.getUUID(),
                        view.id()))
                .findFirst()
                .map(ReputationIncidentView::display);
    }

    @Override
    public boolean hasUnresolvedNegativeIncident(ServerPlayer player, Entity villager) {
        return snapshot(player, villager)
                .map(snapshot -> !snapshot.unresolvedNegativeIncidents().isEmpty())
                .orElse(false);
    }
}
