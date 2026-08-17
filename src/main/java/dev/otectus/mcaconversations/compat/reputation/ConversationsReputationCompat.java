package dev.otectus.mcaconversations.compat.reputation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.ReputationBridge;
import dev.otectus.mcareputation.api.ExternalGossipCandidate;
import dev.otectus.mcareputation.api.IncidentQuery;
import dev.otectus.mcareputation.api.McaReputationApi;
import dev.otectus.mcareputation.api.ReputationIncidentView;
import dev.otectus.mcareputation.api.ReputationQuery;
import dev.otectus.mcareputation.api.ReputationRequest;
import dev.otectus.mcareputation.api.ReputationSnapshot;
import dev.otectus.mcareputation.community.CommunityKey;
import dev.otectus.mcareputation.incident.IncidentStatus;
import dev.otectus.mcareputation.incident.IncidentVisibility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    private ConversationsReputationCompat() {
    }

    /** Installs the façade. Invoked reflectively by the bridge after the mod-present check. */
    public static void register() {
        if (McaReputationApi.getApiVersion() != 1) {
            McaConversations.LOGGER.error("[MCA: Conversations] MCA: Reputation reports API v{} but this "
                    + "build was written against v1; the integration stays disabled.",
                    McaReputationApi.getApiVersion());
            return;
        }
        ReputationBridge.setQueries(new ConversationsReputationCompat());
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
            if (parsed != null) {
                builder.type(parsed);
            }
        }
        for (String status : query.statuses()) {
            IncidentStatus.byName(status).ifPresent(builder::status);
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
                if (parsed != null) {
                    builder.type(parsed);
                }
            }
        }
        List<ReputationIncidentView> views =
                McaReputationApi.selectIncidents(player.server, player.getUUID(), key.get(), builder.build());

        // selectIncidents already returns newest-first with a stable tiebreak, so taking the first
        // {@code limit} this villager actually knows keeps the §30.4 determinism.
        List<ReputationBridge.GossipCandidate> out = new java.util.ArrayList<>();
        for (ReputationIncidentView view : views) {
            if (out.size() >= limit) {
                break;
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

    // ------------------------------------------------------------------
    // Writes
    // ------------------------------------------------------------------

    @Override
    public boolean recordSignal(ServerPlayer player, Entity villager, String incidentId, String visibility,
                                String decisionId) {
        Optional<CommunityKey> key = community(villager);
        ResourceLocation incident = ResourceLocation.tryParse(incidentId);
        if (key.isEmpty() || incident == null) {
            return false;
        }
        ReputationRequest.Builder request = ReputationRequest
                .builder(player.server, player.getUUID(), key.get(), incident,
                        new ResourceLocation("mcaconversations", "conversations"))
                // §14.2's conversation shape. The decision id is what makes a second click on the same
                // apology a no-op while a genuinely different authored choice still records.
                .dedupeKey("conversation:" + villager.getUUID() + ":" + player.getUUID() + ":"
                        + decisionId)
                .subject(dev.otectus.mcareputation.incident.IncidentSubject.villager(villager.getUUID(),
                        villager.getDisplayName().getString(), "subject"))
                .context("decision", decisionId);
        if (visibility != null) {
            IncidentVisibility.byName(visibility).ifPresent(request::visibility);
        }
        return McaReputationApi.record(request.build()).applied();
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
