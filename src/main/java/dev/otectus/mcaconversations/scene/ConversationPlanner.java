package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.FeatureId;
import dev.otectus.mcaconversations.context.ContextRequest;
import dev.otectus.mcaconversations.context.ContextFingerprint;
import dev.otectus.mcaconversations.context.ContextKey;
import dev.otectus.mcaconversations.context.ContextKeys;
import dev.otectus.mcaconversations.context.ContextValue;
import dev.otectus.mcaconversations.context.ContextSources;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.conversation.BeatContractLoader;
import dev.otectus.mcaconversations.conversation.ConversationCatalogLoader;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.conversation.TopicEntry;
import dev.otectus.mcaconversations.history.History;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The one place the director is invoked, and the reason it runs exactly once per exchange
 * (spec §9.3, §7.4).
 *
 * <h2>Where this sits</h2>
 *
 * <p>MCA's dialogue engine scores a question's results and then runs the winner's actions. Conditions
 * therefore run <em>before</em> any action of ours could have planned anything — so planning inside an
 * action would always be one click too late, and planning inside a condition would re-plan once per
 * candidate result.
 *
 * <p>The seam that works is the submission packet: when the player clicks the answer that opens a
 * topic, this runs first, captures the world once, freezes a plan onto the shared session, and
 * <em>then</em> lets MCA score the results. By the time {@code conversations_scene} is evaluated the
 * decision already exists, and every candidate result reads the same one.
 *
 * <h2>Why that makes rerolling impossible</h2>
 *
 * <p>Because the plan lives on the session both frontends share, closing the screen and reopening it during the same active exchange
 * reuses it while its pinned facts remain valid, switching between the GUI and chat reuses it, and changing language cannot touch it. A
 * player who does not like the subject the librarian raised cannot shop for a different one.
 */
public final class ConversationPlanner {

    private ConversationPlanner() {
    }

    /**
     * Called from the submission mixin before MCA resolves the answer.
     *
     * <p>Silent and total in its failure handling: anything that goes wrong here leaves the session
     * without a plan, which means every {@code conversations_scene} condition scores zero and the
     * exchange takes the static 1.4.0 route it always did. That is the whole safety story for this
     * layer — it can only ever add a route, never remove one.
     */
    public static void onAnswerSubmitted(Entity villager, ServerPlayer player,
                                         String question, String answer) {
        if (!McaConversationsConfig.dynamicFeature(FeatureId.DYNAMIC, false)
                || villager == null || player == null) {
            return;
        }
        try {
            ConversationSession session = ConversationSessions.get(player.getUUID(),
                    player.level().getGameTime());
            session.setVillagerId(villager.getUUID());

            // The two of them are talking, which is the moment a promise between them is observed: a
            // "come back and see me" promise is kept by this very visit, and one long past its day
            // that nothing kept is finally read as broken. Cheap for the common case — a pair with no
            // promises is one map lookup that finds nothing — and it writes only on an actual change.
            dev.otectus.mcaconversations.history.CommitmentObserver.onMet(villager, player,
                    player.level().getDayTime() / 24000L);

            // What the button the player just pressed actually meant. This is the only place both
            // frontends funnel through with the question and answer still in hand, and it has to
            // happen before MCA scores the results: the villager's half of the exchange arrives
            // moments later through the session's turn op, and the pair of them is what a later
            // callback names.
            BeatContractLoader.active().reply(question, answer).ifPresent(reply ->
                    session.recordPlayerStance(reply.stance(), reply.introducesFacts()));

            Optional<TopicEntry> topic = ConversationCatalogLoader.active().byStarter(question, answer);
            if (topic.isEmpty()) {
                // Not a topic opener: this is a reply inside an exchange that already has its plan.
                // Refresh only the volatile half, so "it started raining while we talked" is available
                // without any pinned referent moving underneath the scene.
                session.snapshot().ifPresent(pinned -> session.refreshSnapshot(
                        ContextSources.capture(
                                ContextRequest.of(villager, player, ContextRequest.PURPOSE_REFRESH)
                                        .asRefresh())));
                return;
            }

            // A new topic: capture the world once and plan against it.
            ConversationContextSnapshot snapshot = ContextSources.capture(
                    ContextRequest.of(villager, player, ContextRequest.PURPOSE_TOPIC));
            if (canReusePlan(session, topic.get().id(), snapshot)
                    && boundPlanStillValid(session.plan().orElseThrow(), snapshot, villager, player)) {
                session.refreshSnapshot(snapshot);
                return;
            }
            session.setSnapshot(snapshot);
            session.setPlan(null);

            // Work is the one topic that bootstraps its own state: a villager asked about their trade
            // for the first time acquires the situation they are currently in, so the scenes that speak
            // about it have something to bind. Idempotent and resume-first (spec §12.2).
            if ("work".equals(topic.get().id())) {
                WorkEpisodeGenerator.ensure(villager,
                        snapshot.value(dev.otectus.mcaconversations.context.ContextKeys.WORK_PROFESSION_ID)
                                .orElse(null),
                        snapshot.capturedDay());
            } else {
                // The same bootstrap for the domains that are not somebody's trade. It opens nothing
                // unless the world already supports it, so a villager with no family, no unhealed
                // rupture and no village news simply has nothing going on.
                LifeEpisodeGenerator.ensure(villager, player, topic.get().id(), snapshot.capturedDay());
            }

            ConversationDirector.select(villager, player, ScenePurpose.TOPIC, topic.get().id(), snapshot)
                    .ifPresent(plan -> {
                        session.setPlan(plan);
                        if (McaConversationsConfig.COMMON.debugDirector.get()) {
                            plan.explanation().lines()
                                    .forEach(line -> McaConversations.LOGGER.info("[director] {}", line));
                        }
                    });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("scene planning failed; falling back to static routing", t);
        }
    }

    // Playing the opening itself changes these values. They are selection inputs for a NEW
    // exchange, not identities that can invalidate the one currently on screen. In particular,
    // first-talk bookkeeping and episode bootstrap must not make a plan reroll its own opening.
    private static final Set<ContextKey<?>> EXCHANGE_EFFECTS = Set.of(
            ContextKeys.PLAYER_HEARTS, ContextKeys.PLAYER_RELATIONSHIP_BAND,
            ContextKeys.TIME_DAYS_SINCE_LAST_TALK, ContextKeys.TIME_DAYS_SINCE_FIRST_MET,
            ContextKeys.TIME_ABSENCE_BAND, ContextKeys.NARRATIVE_ACTIVE_EPISODES,
            ContextKeys.NARRATIVE_READY_THREADS, ContextKeys.NARRATIVE_DUE_COMMITMENTS,
            ContextKeys.NARRATIVE_RECENT_SUBJECTS);

    /** Reopening preserves the scene while the people, profession and external circumstances match. */
    static boolean canReusePlan(ConversationSession session, String topic,
                                ConversationContextSnapshot current) {
        return session != null && current != null && topic != null
                && session.topicId().filter(topic::equals).isPresent()
                && session.plan().isPresent()
                && session.snapshot().filter(pinned -> referents(pinned).equals(referents(current))).isPresent();
    }

    private static ContextFingerprint referents(ConversationContextSnapshot snapshot) {
        Map<ContextKey<?>, ContextValue<?>> fields = new HashMap<>(snapshot.all());
        EXCHANGE_EFFECTS.forEach(fields::remove);
        return ContextFingerprint.of(fields);
    }

    private static boolean boundPlanStillValid(ConversationPlan plan,
                                               ConversationContextSnapshot snapshot,
                                               Entity villager, ServerPlayer player) {
        SceneDefinition scene = SceneCatalogLoader.active().scene(plan.sceneId()).orElse(null);
        if (scene == null || scene.contextConditions().stream()
                .filter(query -> query.field().isVolatile() || query.field().equals(ContextKeys.PLAYER_HEARTS)
                        || query.field().equals(ContextKeys.PLAYER_RELATIONSHIP_BAND))
                .anyMatch(query -> !query.matches(snapshot))) {
            return false;
        }
        if (!scene.relationships().isEmpty() && scene.relationships().stream().noneMatch(band ->
                snapshot.value(ContextKeys.PLAYER_RELATIONSHIP_BAND).filter(band.key()::equals).isPresent())) {
            return false;
        }
        var episode = plan.episodeId().flatMap(id -> History.of(villager).flatMap(history -> history.episode(id)));
        if (scene.needsEpisode() && (episode.isEmpty()
                || !episode.get().kind().equals(scene.episodeKind())
                || episode.get().hasExpired(snapshot.capturedDay())
                || (!scene.episodeStates().isEmpty() && !scene.episodeStates().contains(episode.get().state())))) {
            return false;
        }
        // Revalidate named people and episode payload without replacing any pinned binding.
        SlotBinder.Result bound = SlotBinder.bind(scene, episode, snapshot, player.serverLevel());
        return bound.bound() && bound.slots().entrySet().stream()
                .allMatch(entry -> entry.getValue().equals(plan.slots().get(entry.getKey())));
    }

    /**
     * Records that a planned scene was actually played, stamping all four recency levels.
     *
     * <p>Called from the session's {@code turn} op rather than from planning, because a plan that was
     * made and then lost a scoring contest inside MCA must not count as having been played — that
     * would suppress a scene the player never saw.
     */
    public static void onScenePlayed(Entity villager, ServerPlayer player, String beatId) {
        if (villager == null || player == null) {
            return;
        }
        try {
            Optional<ConversationPlan> plan = ConversationSessions.raw(player.getUUID())
                    .flatMap(ConversationSession::plan);
            if (plan.isEmpty() || !plan.get().openingBeatId().equals(beatId)) {
                return;
            }
            SceneDefinition scene = SceneCatalogLoader.active().scene(plan.get().sceneId()).orElse(null);
            if (scene == null) {
                return;
            }
            long today = villager.level().getDayTime() / 24000L;
            History.recordPlayed(villager, player, scene.id(),
                    scene.subjectsAny().isEmpty() ? scene.topic() : scene.subjectsAny().iterator().next(),
                    scene.shape().key(), scene.topic(), today);
            plan.get().episodeId().ifPresent(episodeId ->
                    History.witness(villager, episodeId, player, today));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("scene play bookkeeping failed; ignoring", t);
        }
    }
}
