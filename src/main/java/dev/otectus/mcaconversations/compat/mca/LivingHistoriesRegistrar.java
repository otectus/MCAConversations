package dev.otectus.mcaconversations.compat.mca;

import dev.otectus.mcaconversations.conversation.OutcomeFamily;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.context.ContextQuery;
import dev.otectus.mcaconversations.context.ContextRequest;
import dev.otectus.mcaconversations.context.ContextSources;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.history.CommitmentRecord;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.HistoryDirective;
import dev.otectus.mcaconversations.history.HistoryQuery;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.SocialOpinionRecord;
import dev.otectus.mcaconversations.history.SocialRoleRecord;
import dev.otectus.mcaconversations.history.ThreadStatus;
import dev.otectus.mcaconversations.history.VillagerHistory;
import dev.otectus.mcaconversations.identity.Identity;
import dev.otectus.mcaconversations.identity.ProfileQuery;
import dev.otectus.mcaconversations.scene.ConversationPlan;
import dev.otectus.mcaconversations.util.SafeParse;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

/**
 * Registers the living-histories dialogue vocabulary with MCA (spec §10.6).
 *
 * <p>Split out of {@link ConversationsMcaRegistrar} because it is a self-contained vocabulary with a
 * single theme — everything here reads or writes the identity, context and history layers added in
 * 1.4.0 — and because keeping it separate makes the feature's off state easy to verify: none of these
 * conditions can match and none of these actions can write while {@code dynamic.enabled} is false,
 * since every one routes through a facade that checks it.
 *
 * <p>The vocabulary is deliberately <b>small and orthogonal</b>. Nine entries cover identity, context,
 * episodes, threads, promises, claims, opinions, recency and the preselected scene, rather than one
 * custom condition per interest or episode kind (spec §10.6). A datapack that wants a new anchor adds
 * a token; a datapack that wants a new situation adds a template.
 *
 * <p>Same containment rules as the rest of the package: every parser goes through
 * {@link SafeParse#orNull} so malformed JSON is a dead branch rather than a failed datapack reload,
 * and every adapter body catches {@link Throwable} and returns a safe default so a runtime failure can
 * never break MCA's dialogue selection loop.
 */
public final class LivingHistoriesRegistrar {

    private LivingHistoriesRegistrar() {
    }

    public static void register() {
        registerConditions();
        registerActions();
    }

    private static void registerConditions() {

        // Stable identity: "does this villager value precision?" Never a gate on a required subject —
        // an unprofiled villager reads as a non-match, so a profile condition can only add a route.
        McaHandles.registerCondition("conversations_profile",
                (json, name) -> SafeParse.orNull("conversations_profile", json,
                        () -> ProfileQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        return query != null && Identity.matches(villager, query) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_profile failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // The world, read once and shared. Every optional field must declare what an unknown answer
        // means, because "not raining" and "nothing could tell me" are different facts (spec §10.7).
        McaHandles.registerCondition("conversations_context",
                (json, name) -> SafeParse.orNull("conversations_context", json,
                        () -> ContextQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !query.isValid() || player == null) {
                            return 0.0f;
                        }
                        return query.matches(snapshotFor(villager, player)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_context failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // A live situation, and what state it is in. This is the condition that lets one authored page
        // say "still stuck" only while it is true.
        McaHandles.registerCondition("conversations_episode",
                (json, name) -> SafeParse.orNull("conversations_episode", json,
                        () -> HistoryQuery.Episode.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !query.isValid()) {
                            return 0.0f;
                        }
                        Optional<VillagerHistory> history = History.of(villager);
                        return query.matches(history, dayOf(villager)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_episode failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        McaHandles.registerCondition("conversations_thread",
                (json, name) -> SafeParse.orNull("conversations_thread", json,
                        () -> HistoryQuery.Thread.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !query.isValid() || player == null) {
                            return 0.0f;
                        }
                        return query.matches(History.pair(villager, player), dayOf(villager)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_thread failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        McaHandles.registerCondition("conversations_commitment",
                (json, name) -> SafeParse.orNull("conversations_commitment", json,
                        () -> HistoryQuery.Commitment.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !query.isValid() || player == null) {
                            return 0.0f;
                        }
                        return query.matches(History.pair(villager, player), dayOf(villager)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_commitment failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        McaHandles.registerCondition("conversations_claim",
                (json, name) -> SafeParse.orNull("conversations_claim", json,
                        () -> HistoryQuery.Claim.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !query.isValid() || player == null) {
                            return 0.0f;
                        }
                        return query.matches(History.pair(villager, player)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_claim failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        McaHandles.registerCondition("conversations_opinion",
                (json, name) -> SafeParse.orNull("conversations_opinion", json,
                        () -> HistoryQuery.Opinion.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        return query != null && query.isValid() && query.matches(History.of(villager))
                                ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_opinion failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        McaHandles.registerCondition("conversations_role",
                (json, name) -> SafeParse.orNull("conversations_role", json,
                        () -> HistoryQuery.Role.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        return query != null && query.isValid()
                                && query.matches(History.of(villager), dayOf(villager))
                                ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_role failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        McaHandles.registerCondition("conversations_culture",
                (json, name) -> SafeParse.orNull("conversations_culture", json,
                        () -> dev.otectus.mcaconversations.village.CultureQuery
                                .fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        return query != null && query.isValid() && query.matches(villager) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_culture failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // Four-level repetition suppression, exposed so content can also avoid repeating itself in
        // ways the director's scoring cannot see — a shared joke, a running observation.
        McaHandles.registerCondition("conversations_recent",
                (json, name) -> SafeParse.orNull("conversations_recent", json,
                        () -> HistoryQuery.Recent.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !query.isValid() || player == null) {
                            return 0.0f;
                        }
                        return query.matches(History.pair(villager, player), dayOf(villager)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_recent failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // What was actually decided, so a callback can name it. conversations_recent above answers
        // "how long since this came up"; this answers "and what did we settle on", which is the
        // difference between "as I was saying" and "you told me to save the ink".
        McaHandles.registerCondition("conversations_exchange",
                (json, name) -> SafeParse.orNull("conversations_exchange", json,
                        () -> HistoryQuery.Exchange.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !query.isValid() || player == null) {
                            return 0.0f;
                        }
                        return query.matches(History.pair(villager, player), dayOf(villager)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_exchange failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // The scene the director already chose. Reads the frozen plan; it never reruns selection, which
        // is what makes reopening the screen unable to change the subject (spec §9.3).
        McaHandles.registerCondition("conversations_scene",
                (json, name) -> SafeParse.orNull("conversations_scene", json,
                        () -> dev.otectus.mcaconversations.scene.SceneQuery.fromJson(
                                json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !query.isValid() || player == null) {
                            return 0.0f;
                        }
                        return query.matches(planOf(player).orElse(null)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_scene failed; defaulting 0", t);
                        return 0.0f;
                    }
                });
    }

    private static void registerActions() {

        // Opens, advances or witnesses a typed situation. Every op is idempotent: opening a live
        // episode resumes it, and an undeclared transition leaves the old state intact.
        McaHandles.registerAction("conversations_episode",
                (json, name) -> SafeParse.orNull("conversations_episode", json,
                        () -> HistoryDirective.Episode.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive == null || !directive.isValid()
                                || player == null) {
                            return;
                        }
                        applyEpisode(directive, villager, player);
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_episode action failed; ignoring", t);
                    }
                });

        McaHandles.registerAction("conversations_thread",
                (json, name) -> SafeParse.orNull("conversations_thread", json,
                        () -> HistoryDirective.Thread.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive == null || !directive.isValid()
                                || player == null) {
                            return;
                        }
                        applyThread(directive, villager, player);
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_thread action failed; ignoring", t);
                    }
                });

        McaHandles.registerAction("conversations_commitment",
                (json, name) -> SafeParse.orNull("conversations_commitment", json,
                        () -> HistoryDirective.Commitment.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive == null || !directive.isValid()
                                || player == null) {
                            return;
                        }
                        long today = dayOf(villager);
                        Optional<UUID> episodeId = planOf(player).flatMap(ConversationPlan::episodeId);
                        if (directive.op() == HistoryDirective.Commitment.Op.MAKE) {
                            History.promise(villager, player, directive.id(), episodeId, today);
                        } else {
                            History.settle(villager, player, directive.id(),
                                    directive.outcome().orElse(CommitmentRecord.State.NOTED), today);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_commitment action failed; ignoring", t);
                    }
                });

        McaHandles.registerAction("conversations_claim",
                (json, name) -> SafeParse.orNull("conversations_claim", json,
                        () -> HistoryDirective.Claim.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive == null || !directive.isValid()
                                || player == null) {
                            return;
                        }
                        long today = dayOf(villager);
                        if (directive.op() == HistoryDirective.Claim.Op.RECORD) {
                            History.recordClaim(villager, player, directive.type(), directive.value(),
                                    directive.source(), today);
                        } else {
                            clarifyClaim(villager, player, directive.type(), today);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_claim action failed; ignoring", t);
                    }
                });

        McaHandles.registerAction("conversations_opinion",
                (json, name) -> SafeParse.orNull("conversations_opinion", json,
                        () -> HistoryDirective.Opinion.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive == null || !directive.isValid()
                                || player == null) {
                            return;
                        }
                        applyOpinion(directive, villager, player);
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_opinion action failed; ignoring", t);
                    }
                });

        McaHandles.registerAction("conversations_role",
                (json, name) -> SafeParse.orNull("conversations_role", json,
                        () -> HistoryDirective.Role.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive == null || !directive.isValid() || player == null) {
                            return;
                        }
                        applyRole(directive, villager, player);
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_role action failed; ignoring", t);
                    }
                });
    }

    // --- Directive application -------------------------------------------------------------------

    private static void applyEpisode(HistoryDirective.Episode directive, Entity villager,
                                     ServerPlayer player) {
        long today = dayOf(villager);
        switch (directive.op()) {
            case OPEN -> History.openEpisode(villager, directive.kind(), directive.slots(), today)
                    .ifPresent(episode -> History.witness(villager, episode.id(), player, today));
            case ADVANCE -> History.liveEpisode(villager, directive.kind(), today)
                    .ifPresent(episode -> History.transition(villager, episode.id(),
                            directive.state().orElse(episode.state()), today));
            case WITNESS -> History.liveEpisode(villager, directive.kind(), today)
                    .ifPresent(episode -> History.witness(villager, episode.id(), player, today));
            case CORRECT -> History.liveEpisode(villager, directive.kind(), today)
                    .ifPresent(episode -> History.correctEpisode(villager, episode.id()));
            default -> {
                // INVALID never reaches here: isValid() is checked by the caller.
            }
        }
    }

    private static void applyThread(HistoryDirective.Thread directive, Entity villager,
                                    ServerPlayer player) {
        long today = dayOf(villager);
        if (directive.op() == HistoryDirective.Thread.Op.OPEN) {
            Optional<UUID> episodeId = planOf(player).flatMap(ConversationPlan::episodeId);
            History.openThread(villager, player, directive.templateId(), episodeId, today)
                    .filter(thread -> !directive.obligation().isEmpty())
                    .ifPresent(thread -> History.updateThread(villager, player, directive.templateId(),
                            record -> record.withObligation(directive.obligation(), today)));
            return;
        }
        if (directive.op() == HistoryDirective.Thread.Op.PLAYED) {
            // The stance and the outcome come off the live session, not off the record. Until 1.5.0
            // this line passed the thread's own values straight back into it, so `playerStance` and
            // `lastOutcome` were written empty when the thread was opened and stayed empty for the
            // life of the save: two schema fields that could never hold a value, and two callbacks
            // that could never be written.
            Optional<ConversationSession> session = ConversationSessions.raw(player.getUUID());
            String stance = session.flatMap(ConversationSession::lastPlayerStance)
                    .map(StanceFamily::key).orElse("");
            String outcome = session.flatMap(ConversationSession::lastOutcome)
                    .map(OutcomeFamily::key).orElse("");
            History.updateThread(villager, player, directive.templateId(), record -> record.played(
                    planOf(player).map(ConversationPlan::sceneId).orElse(record.lastScene()),
                    outcome.isEmpty() ? record.lastOutcome() : outcome,
                    stance.isEmpty() ? record.playerStance() : stance,
                    today, directive.cooldownDays()));
            return;
        }
        Optional<ThreadStatus> target = directive.targetStatus();
        History.updateThread(villager, player, directive.templateId(), record -> {
            SharedThreadRecord updated = target.map(status -> record.withStatus(status, today))
                    .orElse(record);
            return directive.obligation().isEmpty()
                    ? updated
                    : updated.withObligation(directive.obligation(), today);
        });
    }

    private static void clarifyClaim(Entity villager, ServerPlayer player, String type, long today) {
        if (villager == null || villager.getServer() == null) {
            return;
        }
        UUID playerId = player.getUUID();
        dev.otectus.mcaconversations.history.ConversationHistorySavedData
                .get(villager.getServer())
                .mutate(villager.getUUID(),
                        history -> history.pair(playerId).clarifyClaim(type, today), true);
    }

    /**
     * Applies one caused opinion, resolving its target from the frozen plan's slots.
     *
     * <p>The target comes from a bound {@code person} slot rather than from the JSON, because a
     * directive that named a UUID directly could not have been authored — the villager it refers to did
     * not exist when the datapack was written. Resolving through the plan also means the opinion is
     * about the same person the line just named (spec §7.4).
     */
    private static void applyOpinion(HistoryDirective.Opinion directive, Entity villager,
                                     ServerPlayer player) {
        Optional<UUID> target = planOf(player)
                .flatMap(plan -> plan.slot(directive.targetSlot()))
                .flatMap(dev.otectus.mcaconversations.history.NarrativeValue::asUuid);
        if (target.isEmpty()) {
            return;
        }
        long today = dayOf(villager);
        Optional<VillagerHistory> history = History.of(villager);
        Optional<SocialOpinionRecord> existing = history
                .flatMap(record -> record.opinion(target.get(), directive.axis()));
        OptionalLong expiry = directive.expiresDays().isPresent()
                ? OptionalLong.of(today + directive.expiresDays().getAsLong())
                : OptionalLong.empty();

        SocialOpinionRecord record = existing
                .map(current -> current.adjusted(directive.delta(), directive.cause(), today, expiry))
                .orElseGet(() -> new SocialOpinionRecord(target.get(), directive.axis(),
                        directive.delta(), directive.cause(),
                        dev.otectus.mcaconversations.history.Confidence.WITNESSED,
                        directive.privacy(), today, expiry));
        History.recordOpinion(villager, record);
    }

    /**
     * Applies one observed role, resolving its target from the frozen plan's slots.
     *
     * <p>Same rule as an opinion: the person comes from a bound slot, never from the JSON, so the
     * role is about whoever the line just named and cannot refer to a villager who did not exist
     * when the pack was written.
     *
     * <p>Seeing a role again refreshes it rather than replacing it, so {@code createdDay} keeps
     * meaning "since when" — which is what lets a scene say "for years now" and be right.
     */
    private static void applyRole(HistoryDirective.Role directive, Entity villager,
                                  ServerPlayer player) {
        Optional<UUID> target = planOf(player)
                .flatMap(plan -> plan.slot(directive.targetSlot()))
                .flatMap(dev.otectus.mcaconversations.history.NarrativeValue::asUuid);
        if (target.isEmpty()) {
            return;
        }
        if (directive.withdraw()) {
            History.withdrawRole(villager, target.get(), directive.role());
            return;
        }
        long today = dayOf(villager);
        Optional<SocialRoleRecord> existing = History.of(villager)
                .flatMap(record -> record.role(target.get(), directive.role()));
        SocialRoleRecord record = existing
                .map(current -> current.renewed(directive.cause(), today))
                .orElseGet(() -> SocialRoleRecord.observed(
                        target.get(), directive.role(), directive.cause(), today));
        if (directive.expiresDays().isPresent()) {
            record = new SocialRoleRecord(record.target(), record.role(), record.cause(),
                    record.confidence(), record.createdDay(),
                    OptionalLong.of(today + directive.expiresDays().getAsLong()));
        }
        History.recordRole(villager, record);
    }

    // --- Shared helpers ---------------------------------------------------------------------------

    /**
     * The snapshot this exchange is using, capturing one if the session has none.
     *
     * <p>Reading the session's snapshot rather than capturing per condition is the entire point of the
     * context layer: two conditions in one click must see the same world (spec §7.4). The capture
     * fallback exists for conditions evaluated outside a live session — MCA scores results in a few
     * places the mod does not own — and is cached onto the session so the next condition reuses it.
     */
    private static ConversationContextSnapshot snapshotFor(Entity villager, ServerPlayer player) {
        Optional<ConversationSession> session = ConversationSessions.raw(player.getUUID());
        Optional<ConversationContextSnapshot> existing =
                session.flatMap(ConversationSession::snapshot);
        if (existing.isPresent()) {
            return existing.get();
        }
        ConversationContextSnapshot captured = ContextSources.capture(
                ContextRequest.of(villager, player, ContextRequest.PURPOSE_TOPIC));
        session.ifPresent(live -> live.setSnapshot(captured));
        return captured;
    }

    private static Optional<ConversationPlan> planOf(ServerPlayer player) {
        return ConversationSessions.raw(player.getUUID()).flatMap(ConversationSession::plan);
    }

    private static long dayOf(Entity villager) {
        return villager == null || villager.level() == null
                ? 0L : villager.level().getDayTime() / 24000L;
    }

    /** Exposed for the trace: the pair record behind a condition, if any. */
    public static Optional<PairHistory> pairFor(Entity villager, ServerPlayer player) {
        return History.pair(villager, player);
    }

    /** Exposed for the trace: the live episode a scene is bound to, if any. */
    public static Optional<EpisodeRecord> episodeFor(Entity villager, ServerPlayer player) {
        return planOf(player)
                .flatMap(ConversationPlan::episodeId)
                .flatMap(id -> History.of(villager).flatMap(history -> history.episode(id)));
    }
}
