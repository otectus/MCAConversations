package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

/**
 * The facade every dialogue adapter goes through to read or move living-history state (spec §8).
 *
 * <p>Modelled on the existing {@code Progress} facade, and for the same reasons. Everything fails
 * safe: an unreachable server, a disabled feature, an unknown template or any throw at all leaves
 * state untouched and reads as "nothing has happened yet", so a compat break or a datapack typo
 * degrades a conversation rather than ending it.
 *
 * <p>Two policies live here rather than in the store:
 *
 * <ul>
 *   <li><b>Templates are authoritative.</b> An episode may only be opened for a declared kind, a
 *       thread only for a declared template, a commitment only for a declared template with a
 *       registered resolver. Runtime code cannot invent a shape.</li>
 *   <li><b>Off means inert.</b> With {@code history.enabled=false} every read returns empty and every
 *       write is a no-op, so the 1.4.0 arcs, milestones, affection budgets and disposition vectors
 *       carry on exactly as they did (spec §22.5).</li>
 * </ul>
 */
public final class History {

    private History() {
    }

    public static boolean enabled() {
        return McaConversationsConfig.dynamicFeature("history", false);
    }

    /** True when episodes may be created and advanced at all. */
    public static boolean episodesEnabled() {
        return McaConversationsConfig.dynamicFeature("episodes", false);
    }

    // --- Reads ---------------------------------------------------------------------------------------

    /** One villager's whole history, read-only. Empty when history is off or nothing is stored. */
    public static Optional<VillagerHistory> of(Entity villager) {
        if (!enabled() || villager == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return Optional.empty();
        }
        try {
            return ConversationHistorySavedData.get(server).peek(villager.getUUID());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("history read failed; treating as empty", t);
            return Optional.empty();
        }
    }

    /** What this villager and this player share, read-only. */
    public static Optional<PairHistory> pair(Entity villager, ServerPlayer player) {
        if (player == null) {
            return Optional.empty();
        }
        return of(villager).flatMap(history -> history.peekPair(player.getUUID()));
    }

    /** Live episodes for this villager, most salient first. */
    public static List<EpisodeRecord> liveEpisodes(Entity villager, long today) {
        return of(villager).map(history -> history.liveEpisodes(today)).orElse(List.of());
    }

    /** The live episode of one kind, when there is one. */
    public static Optional<EpisodeRecord> liveEpisode(Entity villager, String kind, long today) {
        return of(villager).flatMap(history -> history.liveEpisodeOfKind(kind, today));
    }

    // --- Episodes ------------------------------------------------------------------------------------

    /**
     * Opens an episode of a declared kind, or returns the live one that already exists.
     *
     * <p>Returning the existing episode rather than opening a second is what keeps a working life
     * coherent: a librarian has <em>one</em> damaged volume, and the scene that would have opened a
     * second one instead resumes the first (spec §12.2).
     */
    public static Optional<EpisodeRecord> openEpisode(Entity villager, String kind,
                                                      Map<String, NarrativeValue> payload, long today) {
        if (!episodesEnabled() || villager == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        EpisodeTemplate template = NarrativeCatalogLoader.active().episode(kind).orElse(null);
        if (server == null || template == null) {
            return Optional.empty();
        }
        try {
            ConversationHistorySavedData data = ConversationHistorySavedData.get(server);
            UUID villagerId = villager.getUUID();
            Optional<EpisodeRecord> existing = data.peek(villagerId)
                    .flatMap(history -> history.liveEpisodeOfKind(template.kind(), today));
            if (existing.isPresent()) {
                return existing;
            }
            // Fill whatever the caller did not supply from the template's own pools, using a seed made
            // of the world, this villager and this episode kind. That is what makes two librarians
            // worry about different books while sharing one authored scene (spec §12.3).
            long worldSeed = server.overworld() == null ? 0L : server.overworld().getSeed();
            Map<String, NarrativeValue> filled = template.fillSlots(payload,
                    EpisodeTemplate.seedFor(worldSeed, villagerId, template.kind()));
            if (!template.satisfiedBy(filled)) {
                // A scene cannot bind slots that were never supplied; opening the episode anyway would
                // create a situation nothing can ever talk about.
                return Optional.empty();
            }
            EpisodeRecord opened = EpisodeRecord
                    .opened(UUID.randomUUID(), template.kind(), template.subject(),
                            template.initialState(), villagerId, filled, template.privacy(),
                            template.baseSalience(), today)
                    .withProvenance(template.provenance())
                    .withDeadline(template.dueDayFrom(today), template.expiryDayFrom(today));
            data.mutate(villagerId, history -> history.putEpisode(opened, today), true);
            return Optional.of(opened);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("episode open failed for kind '{}'; ignoring", kind, t);
            return Optional.empty();
        }
    }

    /**
     * Moves an episode to a new state.
     *
     * @return the episode as it now stands, or empty when the transition was refused
     */
    public static Optional<EpisodeRecord> transition(Entity villager, UUID episodeId,
                                                     EpisodeState next, long today) {
        if (!episodesEnabled() || villager == null || episodeId == null || next == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return Optional.empty();
        }
        try {
            ConversationHistorySavedData data = ConversationHistorySavedData.get(server);
            UUID villagerId = villager.getUUID();
            EpisodeRecord current = data.peek(villagerId)
                    .flatMap(history -> history.episode(episodeId))
                    .orElse(null);
            if (current == null) {
                return Optional.empty();
            }
            EpisodeTemplate template = NarrativeCatalogLoader.active().episode(current.kind()).orElse(null);
            if (template != null && !template.permits(current.state(), next)) {
                McaConversations.LOGGER.debug("episode '{}' refused transition {} -> {}",
                        current.kind(), current.state().key(), next.key());
                return Optional.of(current);
            }
            EpisodeRecord updated = current.transitioned(next, today);
            if (updated.equals(current)) {
                return Optional.of(current);
            }
            data.mutate(villagerId, history -> history.putEpisode(updated, today), true);
            return Optional.of(updated);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("episode transition failed; leaving state intact", t);
            return Optional.empty();
        }
    }

    /** Records that a player now knows about an episode, so a later scene may refer to it. */
    public static void witness(Entity villager, UUID episodeId, ServerPlayer player, long today) {
        if (!episodesEnabled() || villager == null || episodeId == null || player == null) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        try {
            ConversationHistorySavedData data = ConversationHistorySavedData.get(server);
            UUID villagerId = villager.getUUID();
            data.peek(villagerId)
                    .flatMap(history -> history.episode(episodeId))
                    .map(episode -> episode.witnessedBy(player.getUUID()))
                    .ifPresent(updated ->
                            data.mutate(villagerId, history -> history.putEpisode(updated, today), true));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("episode witness write failed; ignoring", t);
        }
    }

    // --- Threads --------------------------------------------------------------------------------------

    /** Opens or returns the thread of a declared template for this pair. */
    public static Optional<SharedThreadRecord> openThread(Entity villager, ServerPlayer player,
                                                          String templateId, Optional<UUID> episodeId,
                                                          long today) {
        if (!enabled() || villager == null || player == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        ThreadTemplate template = NarrativeCatalogLoader.active().thread(templateId).orElse(null);
        if (server == null || template == null) {
            return Optional.empty();
        }
        try {
            ConversationHistorySavedData data = ConversationHistorySavedData.get(server);
            UUID villagerId = villager.getUUID();
            UUID playerId = player.getUUID();
            Optional<SharedThreadRecord> existing = data.peek(villagerId)
                    .flatMap(history -> history.peekPair(playerId))
                    .flatMap(pairHistory -> pairHistory.thread(template.id()))
                    .filter(thread -> !thread.status().isClosed());
            if (existing.isPresent()) {
                return existing;
            }
            SharedThreadRecord opened = template.open(episodeId, today);
            data.mutate(villagerId, history -> history.pair(playerId).putThread(opened), true);
            return Optional.of(opened);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("thread open failed for '{}'; ignoring", templateId, t);
            return Optional.empty();
        }
    }

    /** Applies a change to an existing thread. Returns the thread as it now stands. */
    public static Optional<SharedThreadRecord> updateThread(Entity villager, ServerPlayer player,
                                                            String templateId,
                                                            java.util.function.UnaryOperator<SharedThreadRecord> change) {
        if (!enabled() || villager == null || player == null || change == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return Optional.empty();
        }
        try {
            ConversationHistorySavedData data = ConversationHistorySavedData.get(server);
            UUID villagerId = villager.getUUID();
            UUID playerId = player.getUUID();
            SharedThreadRecord current = data.peek(villagerId)
                    .flatMap(history -> history.peekPair(playerId))
                    .flatMap(pairHistory -> pairHistory.thread(templateId))
                    .orElse(null);
            if (current == null) {
                return Optional.empty();
            }
            SharedThreadRecord updated = change.apply(current);
            if (updated == null || updated.equals(current)) {
                return Optional.of(current);
            }
            data.mutate(villagerId, history -> history.pair(playerId).putThread(updated), true);
            return Optional.of(updated);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("thread update failed for '{}'; ignoring", templateId, t);
            return Optional.empty();
        }
    }

    // --- Commitments -----------------------------------------------------------------------------------

    /**
     * Creates a promise from a declared template.
     *
     * <p>Refuses when the template's resolver cannot be observed on this install — the button that
     * offered it should already have been filtered out, and creating it anyway would mean judging a
     * player on something nothing can watch (spec §12.6).
     */
    public static Optional<CommitmentRecord> promise(Entity villager, ServerPlayer player,
                                                     String templateId, Optional<UUID> episodeId,
                                                     long today) {
        if (!enabled() || villager == null || player == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        CommitmentTemplate template = NarrativeCatalogLoader.active().commitment(templateId).orElse(null);
        if (server == null || template == null || !template.isObservable()) {
            return Optional.empty();
        }
        try {
            ConversationHistorySavedData data = ConversationHistorySavedData.get(server);
            UUID villagerId = villager.getUUID();
            UUID playerId = player.getUUID();
            Optional<CommitmentRecord> existing = data.peek(villagerId)
                    .flatMap(history -> history.peekPair(playerId))
                    .flatMap(pairHistory -> pairHistory.commitment(template.id()))
                    .filter(CommitmentRecord::isOutstanding);
            if (existing.isPresent()) {
                // Promising the same thing twice is one promise, not two debts.
                return existing;
            }
            CommitmentRecord made = template.make(today, episodeId);
            data.mutate(villagerId, history -> history.pair(playerId).putCommitment(made), true);
            return Optional.of(made);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("commitment creation failed for '{}'; ignoring", templateId, t);
            return Optional.empty();
        }
    }

    /** Settles a promise. A neutral-resolver promise settles as {@code NOTED} whatever is asked. */
    public static Optional<CommitmentRecord> settle(Entity villager, ServerPlayer player,
                                                    String templateId, CommitmentRecord.State outcome,
                                                    long today) {
        if (!enabled() || villager == null || player == null || outcome == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return Optional.empty();
        }
        try {
            ConversationHistorySavedData data = ConversationHistorySavedData.get(server);
            UUID villagerId = villager.getUUID();
            UUID playerId = player.getUUID();
            CommitmentRecord current = data.peek(villagerId)
                    .flatMap(history -> history.peekPair(playerId))
                    .flatMap(pairHistory -> pairHistory.commitment(templateId))
                    .orElse(null);
            if (current == null || current.state().isSettled()) {
                return Optional.ofNullable(current);
            }
            CommitmentRecord settled = current.resolved(outcome, today);
            data.mutate(villagerId, history -> history.pair(playerId).putCommitment(settled), true);
            return Optional.of(settled);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("commitment settle failed for '{}'; ignoring", templateId, t);
            return Optional.empty();
        }
    }

    /** Promises that are due today and observable, oldest first. */
    public static List<CommitmentRecord> dueCommitments(Entity villager, ServerPlayer player, long today) {
        return pair(villager, player).map(history -> history.due(today)).orElse(List.of());
    }

    // --- Player claims ---------------------------------------------------------------------------------

    /** Records something the player said about themselves, through an authored reply. */
    public static void recordClaim(Entity villager, ServerPlayer player, String type,
                                   NarrativeValue value, String sourceReply, long today) {
        if (!enabled() || villager == null || player == null) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        PlayerClaimRecord claim = PlayerClaimRecord.stated(type, value, sourceReply, today);
        if (!claim.isAttributable()) {
            // No provenance means no claim. Free-form text may select a claim; it may never become one.
            return;
        }
        try {
            ConversationHistorySavedData data = ConversationHistorySavedData.get(server);
            UUID playerId = player.getUUID();
            data.mutate(villager.getUUID(),
                    history -> history.pair(playerId).recordClaim(claim), true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("claim write failed for '{}'; ignoring", type, t);
        }
    }

    public static Optional<PlayerClaimRecord> claim(Entity villager, ServerPlayer player, String type) {
        return pair(villager, player).flatMap(history -> history.claim(type));
    }

    /**
     * Stores an episode on a villager who did not open it — how a rumour arrives (spec §16.4).
     *
     * <p>Distinct from {@link #openEpisode}, which instantiates a template. This one takes an event
     * that already exists, id and all, and gives a second villager their own footing on it. Keeping
     * the id is the whole point: a correction later on has to be able to address the same event
     * rather than one villager's copy of it.
     */
    public static boolean putEpisode(Entity villager, EpisodeRecord episode) {
        if (!McaConversationsConfig.dynamicFeature("episodes", false)
                || villager == null || episode == null) {
            return false;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return false;
        }
        try {
            long today = server.overworld().getDayTime() / 24000L;
            ConversationHistorySavedData.get(server)
                    .mutate(villager.getUUID(), history -> history.putEpisode(episode, today), true);
            return true;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("episode write failed; ignoring", t);
            return false;
        }
    }

    /**
     * Sets an account straight, keeping the event id (spec §16.4 point 8).
     *
     * <p>What a correction changes is the <em>footing</em>, not the event: the villager now holds it
     * as certain and any authored distortion is cleared. It does not become something they witnessed
     * — they still were not there — which is why the source is left alone and only the confidence
     * moves.
     */
    public static boolean correctEpisode(Entity villager, UUID episodeId) {
        if (!McaConversationsConfig.dynamicFeature("episodes", false)
                || villager == null || episodeId == null) {
            return false;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return false;
        }
        try {
            long today = server.overworld().getDayTime() / 24000L;
            return ConversationHistorySavedData.get(server).mutate(villager.getUUID(), history ->
                    history.episode(episodeId)
                            .map(episode -> history.putEpisode(
                                    episode.withProvenance(episode.provenance().corrected(null)),
                                    today))
                            .orElse(false), true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("episode correction failed; ignoring", t);
            return false;
        }
    }

    // --- Social opinions --------------------------------------------------------------------------------

    /** Records or adjusts one caused opinion of a named neighbour. */
    public static void recordOpinion(Entity villager, SocialOpinionRecord opinion) {
        if (!McaConversationsConfig.dynamicFeature("social_opinions", false)
                || villager == null || opinion == null) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        try {
            ConversationHistorySavedData.get(server)
                    .mutate(villager.getUUID(), history -> history.putOpinion(opinion), true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("opinion write failed; ignoring", t);
        }
    }

    // --- Social roles -----------------------------------------------------------------------------------

    /**
     * Records or refreshes one observed role towards a named neighbour (spec §16.2).
     *
     * <p>Shares the social-knowledge switch with opinions. They are the same layer to a server owner
     * — how much a village remembers about itself — and separating the flags would let a save exist
     * where a villager knows they dislike somebody but has forgotten that they work with them.
     */
    public static void recordRole(Entity villager, SocialRoleRecord role) {
        if (!McaConversationsConfig.dynamicFeature("social_opinions", false)
                || villager == null || role == null) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        try {
            ConversationHistorySavedData.get(server)
                    .mutate(villager.getUUID(), history -> history.putRole(role), true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("role write failed; ignoring", t);
        }
    }

    /** Ends a role because the arrangement behind it has ended. */
    public static void withdrawRole(Entity villager, UUID target, SocialRole role) {
        if (!McaConversationsConfig.dynamicFeature("social_opinions", false)
                || villager == null || target == null || role == null) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        try {
            ConversationHistorySavedData.get(server)
                    .mutate(villager.getUUID(), history -> history.withdrawRole(target, role), true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("role withdrawal failed; ignoring", t);
        }
    }

    // --- Recency and the shared clock ---------------------------------------------------------------------

    /** Stamps all four recency levels after a contracted scene was played. */
    public static void recordPlayed(Entity villager, ServerPlayer player, String scene, String subject,
                                    String shape, String topic, long today) {
        if (!enabled() || villager == null || player == null) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        try {
            UUID playerId = player.getUUID();
            ConversationHistorySavedData.get(server).mutate(villager.getUUID(),
                    history -> history.pair(playerId).recordPlayed(scene, subject, shape, topic, today),
                    true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("recency write failed; ignoring", t);
        }
    }

    /** Records that the villager opened a conversation unprompted, for the daily cap. */
    public static void recordInitiative(Entity villager, ServerPlayer player, long today) {
        if (!enabled() || villager == null || player == null) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        try {
            UUID playerId = player.getUUID();
            ConversationHistorySavedData.get(server).mutate(villager.getUUID(),
                    history -> history.pair(playerId).recordInitiative(today), true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("initiative write failed; ignoring", t);
        }
    }

    /** Days since this pair last spoke; empty on a first meeting rather than zero. */
    public static OptionalLong daysSinceLastTalk(Entity villager, ServerPlayer player, long today) {
        return pair(villager, player)
                .map(history -> history.lastTalkedDay())
                .filter(OptionalLong::isPresent)
                .map(day -> OptionalLong.of(Math.max(0L, today - day.getAsLong())))
                .orElse(OptionalLong.empty());
    }

    /** Drops a dead villager's history, the same way the progress and disposition stores do. */
    public static void forget(MinecraftServer server, UUID villager) {
        if (server == null || villager == null) {
            return;
        }
        try {
            ConversationHistorySavedData.get(server).removeVillager(villager);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("history removal failed for {}; ignoring", villager, t);
        }
    }

    /** Runs the pruning pass; called from the existing low-frequency sweep, never per tick. */
    public static int prune(MinecraftServer server, long today) {
        if (server == null || !enabled()) {
            return 0;
        }
        try {
            return ConversationHistorySavedData.get(server).prune(today);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("history prune failed; ignoring", t);
            return 0;
        }
    }
}
