package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.context.ContextKeys;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.TopicRecencyRecord;
import dev.otectus.mcaconversations.identity.Identity;
import dev.otectus.mcaconversations.identity.VillagerIdentityRecord;
import dev.otectus.mcaconversations.profession.ProfessionProfile;
import dev.otectus.mcaconversations.profession.ProfessionProfileLoader;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

/**
 * Decides which authored scene fits this villager, on this day, after this history (spec §9).
 *
 * <p>It writes no prose, applies no consequence and touches no persisted state. Its whole output is a
 * frozen {@link ConversationPlan} naming a scene that was already authored and already contracted.
 * That boundary is deliberate: everything the director could get wrong is a <em>selection</em> error,
 * visible in a trace and fixable by changing a weight, rather than a coherence error that ships a line
 * nothing can answer.
 *
 * <p>The pipeline is the plan's, in order: index lookup, hard eligibility, semantic eligibility
 * (slot binding), continuity priority, transparent scoring, four-level repetition suppression,
 * deterministic choice within a near-top band, and freeze.
 *
 * <h2>Why the choice is deterministic</h2>
 *
 * <p>The seed comes from world seed, villager, player, day, purpose and the fingerprint of the
 * eligible set — never from a clock or a random source. So reopening the screen, switching frontend or
 * changing language cannot produce a different conversation, and a player cannot reroll an
 * unappealing subject by closing the window (spec §9.3). Within a small band below the top score the
 * seed picks; outside it, score decides.
 */
public final class ConversationDirector {

    /** How far below the leader a candidate may be and still be in the random band. */
    private static final int NEAR_TOP_BAND = 4;

    /** Scoring weights, gathered so tuning is one place rather than scattered through the method. */
    private static final int DUE_OBLIGATION_WEIGHT = 25;
    private static final int RUPTURE_WEIGHT = 30;
    private static final int READY_THREAD_WEIGHT = 15;
    private static final int IDENTITY_TOKEN_WEIGHT = 5;
    private static final int ACUTE_FIT_WEIGHT = 12;
    private static final int NOVELTY_WEIGHT = 6;
    private static final int SCENE_RECENCY_WEIGHT = 10;
    private static final int SUBJECT_RECENCY_WEIGHT = 6;
    private static final int SHAPE_RECENCY_WEIGHT = 5;
    private static final int TOPIC_RECENCY_WEIGHT = 2;
    private static final int FATIGUE_WEIGHT = 3;

    private ConversationDirector() {
    }

    /**
     * Chooses a scene for one purpose and topic.
     *
     * @return the frozen plan, or empty when nothing was eligible — in which case the caller keeps its
     *         existing 1.4.0 static route, which is what makes this layer additive
     */
    public static Optional<ConversationPlan> select(Entity villager, ServerPlayer player,
                                                    ScenePurpose purpose, String topic,
                                                    ConversationContextSnapshot snapshot) {
        if (!McaConversationsConfig.dynamicFeature("dynamic", false)
                || villager == null || player == null || purpose == null || snapshot == null) {
            return Optional.empty();
        }
        try {
            return selectUnguarded(villager, player, purpose, topic, snapshot);
        } catch (Throwable t) {
            // A director failure must cost the dynamic scene and nothing else: the caller falls back
            // to the static route it would have taken in 1.4.0.
            McaConversations.LOGGER.debug("scene selection failed for purpose {}; falling back to static",
                    purpose.key(), t);
            return Optional.empty();
        }
    }

    private static Optional<ConversationPlan> selectUnguarded(Entity villager, ServerPlayer player,
                                                              ScenePurpose purpose, String topic,
                                                              ConversationContextSnapshot snapshot) {
        SceneCatalog catalog = SceneCatalogLoader.active();
        SelectionExplanation explanation = new SelectionExplanation(
                purpose.key() + (topic == null || topic.isEmpty() ? "" : ":" + topic));
        explanation.context(snapshot.fingerprint().hex());

        List<SceneDefinition> indexed = catalog.candidates(purpose, topic);
        explanation.indexed(indexed.size());
        if (indexed.isEmpty()) {
            return Optional.empty();
        }

        long today = snapshot.capturedDay();
        ProfessionProfile profile = ProfessionProfileLoader.profile(
                snapshot.value(ContextKeys.WORK_PROFESSION_ID).orElse(null),
                snapshot.value(ContextKeys.WORK_PROFESSION_NAME).orElse("villager"));
        Optional<VillagerIdentityRecord> identity = Identity.of(villager);
        Optional<PairHistory> pair = History.pair(villager, player);
        TopicRecencyRecord recency = pair.map(PairHistory::recency).orElse(TopicRecencyRecord.EMPTY);
        ServerLevel level = villager.level() instanceof ServerLevel serverLevel ? serverLevel : null;

        // Stage 2-3: hard eligibility, then semantic eligibility through slot binding.
        List<Candidate> eligible = new ArrayList<>();
        for (SceneDefinition scene : indexed) {
            if (eligible.size() >= SceneCatalog.MAX_SCORED) {
                explanation.note("scored set capped at " + SceneCatalog.MAX_SCORED
                        + "; " + (indexed.size() - eligible.size()) + " candidate(s) not evaluated");
                break;
            }
            Optional<EpisodeRecord> episode = scene.needsEpisode()
                    ? History.liveEpisode(villager, scene.episodeKind(), today)
                    : Optional.empty();

            String reason = SceneEligibility.check(scene, snapshot, profile, identity, episode,
                    ConversationDirector::modPresent, today);
            if (!reason.isEmpty()) {
                explanation.reject(scene.id(), reason);
                continue;
            }
            long daysSinceScene = recency.daysSince(TopicRecencyRecord.Level.SCENE, scene.id(), today);
            reason = SceneEligibility.checkRecency(scene, daysSinceScene,
                    mentionsThisWeek(recency, scene, today));
            if (!reason.isEmpty()) {
                explanation.reject(scene.id(), reason);
                continue;
            }
            SlotBinder.Result binding = SlotBinder.bind(scene, episode, snapshot, level);
            if (!binding.bound()) {
                explanation.reject(scene.id(), "slot '" + binding.failedSlot() + "' could not bind");
                continue;
            }
            eligible.add(new Candidate(scene, episode, binding));
        }
        explanation.afterHardFilters(eligible.size());
        if (eligible.isEmpty()) {
            return Optional.empty();
        }

        // Stage 4-6: continuity priority and transparent scoring with recency suppression.
        int best = Integer.MIN_VALUE;
        for (Candidate candidate : eligible) {
            candidate.score = score(candidate, purpose, snapshot, identity, pair, recency, today);
            explanation.finalist(candidate.scene.id(), candidate.score);
            best = Math.max(best, candidate.score.total());
        }

        // Stage 7: deterministic choice inside a small band below the leader.
        final int threshold = best - NEAR_TOP_BAND;
        List<Candidate> band = new ArrayList<>();
        for (Candidate candidate : eligible) {
            if (candidate.score.total() >= threshold) {
                band.add(candidate);
            }
        }
        band.sort(Comparator.comparing((Candidate candidate) -> candidate.scene.id()));

        String seedBasis = seedBasis(villager, player, purpose, today, band);
        explanation.seed(seedBasis);
        Candidate chosen = band.get((int) Math.floorMod(hash(seedBasis), band.size()));
        explanation.selected(chosen.scene.id());
        chosen.binding.provenance().forEach(explanation::slot);

        return Optional.of(new ConversationPlan(chosen.scene.id(), chosen.scene.questionId(),
                chosen.scene.openingBeatId(), chosen.binding.slots(),
                chosen.episode.map(EpisodeRecord::id),
                chosen.scene.opensThread() ? Optional.of(chosen.scene.threadTemplate()) : Optional.empty(),
                snapshot.fingerprint(), Long.toHexString(hash(seedBasis)), explanation));
    }

    /**
     * The scoring model of §9.2, term by term.
     *
     * <p>Continuity outranks novelty and novelty outranks nothing much: a callback to a real shared
     * event is worth more than a line the player has never seen, because recognition beats novelty
     * (spec §4.1). What continuity may <em>not</em> do is bypass a gate, which is why every term here
     * is a number and every gate is somewhere else.
     */
    private static SelectionScore score(Candidate candidate, ScenePurpose purpose,
                                        ConversationContextSnapshot snapshot,
                                        Optional<VillagerIdentityRecord> identity,
                                        Optional<PairHistory> pair,
                                        TopicRecencyRecord recency, long today) {
        SceneDefinition scene = candidate.scene;
        SelectionScore score = new SelectionScore();
        score.add(SelectionScore.BASE_PRIORITY, scene.basePriority());

        // Continuity.
        pair.ifPresent(history -> {
            if (!history.due(today).isEmpty() && purpose == ScenePurpose.DUE_COMMITMENT) {
                score.add(SelectionScore.DUE_OBLIGATION, DUE_OBLIGATION_WEIGHT);
            }
            if (history.rupture().isPresent() && purpose == ScenePurpose.REPAIR) {
                score.add(SelectionScore.UNRESOLVED_CONTINUITY, RUPTURE_WEIGHT);
            }
            if (scene.opensThread()) {
                Optional<SharedThreadRecord> thread = history.thread(scene.threadTemplate());
                if (thread.map(record -> record.isReady(today)).orElse(false)) {
                    score.add(SelectionScore.UNRESOLVED_CONTINUITY, READY_THREAD_WEIGHT);
                }
            }
        });
        candidate.episode.ifPresent(episode -> {
            // Salience is 0-100; divided so an episode cannot outweigh a due promise by itself.
            score.add(SelectionScore.EPISODE_SALIENCE, episode.salience() / 5);
            if (episode.isOverdue(today)) {
                score.add(SelectionScore.DUE_OBLIGATION, DUE_OBLIGATION_WEIGHT / 2);
            }
        });

        // Acute context: a scene that matches what is actually happening beats one that does not.
        if (purpose == ScenePurpose.ACUTE) {
            score.add(SelectionScore.ACUTE_CONTEXT_FIT, ACUTE_FIT_WEIGHT);
        }
        if (snapshot.is(ContextKeys.WEATHER_STATE, "storm")
                && snapshot.value(ContextKeys.WEATHER_RELEVANT).orElse(false)) {
            score.add(SelectionScore.ACUTE_CONTEXT_FIT, ACUTE_FIT_WEIGHT / 2);
        }

        // Stable identity: a thumb on the scale, never a rail. A scene that suits this villager's
        // values or interests is likelier; one that does not is still perfectly selectable.
        identity.ifPresent(profile -> {
            int fit = 0;
            for (String value : scene.identityValues()) {
                if (profile.has(dev.otectus.mcaconversations.identity.IdentityFamily.VALUE, value)) {
                    fit += IDENTITY_TOKEN_WEIGHT;
                }
            }
            for (String interest : scene.identityInterests()) {
                if (profile.has(dev.otectus.mcaconversations.identity.IdentityFamily.INTEREST, interest)) {
                    fit += IDENTITY_TOKEN_WEIGHT;
                }
            }
            for (String style : scene.identityStyles()) {
                if (profile.workStyle().equals(style) || profile.socialStyle().equals(style)
                        || profile.disclosureStyle().equals(style)) {
                    fit += IDENTITY_TOKEN_WEIGHT;
                }
            }
            score.add(SelectionScore.STABLE_IDENTITY_FIT, fit);
        });

        // Novelty: never seen beats seen, but only mildly.
        long daysSinceScene = recency.daysSince(TopicRecencyRecord.Level.SCENE, scene.id(), today);
        if (daysSinceScene == Long.MAX_VALUE) {
            score.add(SelectionScore.NOVELTY, NOVELTY_WEIGHT);
        }

        // Four-level repetition suppression (spec §9.4). Each level decays independently, so a scene
        // whose subject came up yesterday is dampened even if that exact scene never has.
        score.penalise(SelectionScore.SCENE_RECENCY,
                decayed(daysSinceScene, SCENE_RECENCY_WEIGHT, 4));
        score.penalise(SelectionScore.SUBJECT_RECENCY,
                decayed(recency.daysSince(TopicRecencyRecord.Level.SUBJECT,
                        subjectOf(scene), today), SUBJECT_RECENCY_WEIGHT, 3));
        score.penalise(SelectionScore.SHAPE_RECENCY,
                decayed(recency.daysSince(TopicRecencyRecord.Level.SHAPE,
                        scene.shape().key(), today), SHAPE_RECENCY_WEIGHT, scene.shape().cooldownDays()));
        score.penalise(SelectionScore.TOPIC_RECENCY,
                decayed(recency.daysSince(TopicRecencyRecord.Level.TOPIC, scene.topic(), today),
                        TOPIC_RECENCY_WEIGHT, 1));

        // Interruption and fatigue: expensive to open unprompted while somebody is working.
        if (purpose.isInitiative() && !purpose.overridesBusyState()) {
            String activity = snapshot.value(ContextKeys.WORK_ACTIVITY).orElse("");
            int cost = purpose.interruptionCost();
            if (activity.equals("work") || activity.equals("panic") || activity.equals("grieve")) {
                cost *= 2;
            }
            score.penalise(SelectionScore.INTERRUPTION_COST, cost);
            score.penalise(SelectionScore.INTERACTION_FATIGUE,
                    recency.initiativesOn(today) * FATIGUE_WEIGHT);
        }
        return score;
    }

    /**
     * A penalty that fades linearly to nothing over {@code windowDays}.
     *
     * <p>Linear rather than stepped so a scene does not become abruptly available on the morning of
     * day four; and clamped at zero so an ancient scene is not rewarded for being old, which would
     * quietly turn the recency term into a second novelty bonus.
     */
    private static int decayed(long daysSince, int weight, int windowDays) {
        if (daysSince == Long.MAX_VALUE || windowDays <= 0 || daysSince >= windowDays) {
            return 0;
        }
        long remaining = windowDays - daysSince;
        return (int) Math.max(0, (weight * remaining) / windowDays);
    }

    private static String subjectOf(SceneDefinition scene) {
        return scene.subjectsAny().isEmpty() ? scene.topic() : scene.subjectsAny().iterator().next();
    }

    private static int mentionsThisWeek(TopicRecencyRecord recency, SceneDefinition scene, long today) {
        // The store keeps last-seen days rather than a count, so "mentions this week" is 1 when the
        // scene was seen inside the window and 0 otherwise. That is enough for a cap of 1 or 2 and
        // avoids keeping a per-scene event list for a question nobody asks more precisely.
        long daysSince = recency.daysSince(TopicRecencyRecord.Level.SCENE, scene.id(), today);
        return daysSince <= 7 ? 1 : 0;
    }

    /**
     * The selection seed, as the plan specifies it (spec §9.3).
     *
     * <p>The eligible-candidate fingerprint is part of it on purpose: when the world changes enough to
     * change which scenes are available, the choice is allowed to change too. What it must never
     * depend on is a clock, a random source, or anything a player can vary by reopening a screen.
     */
    private static String seedBasis(Entity villager, ServerPlayer player, ScenePurpose purpose,
                                    long day, List<Candidate> band) {
        StringBuilder sb = new StringBuilder();
        if (villager.level() instanceof ServerLevel level) {
            sb.append(level.getSeed()).append('/');
        }
        sb.append(villager.getUUID()).append('/')
                .append(player.getUUID()).append('/')
                .append(day).append('/')
                .append(purpose.key()).append('/');
        for (Candidate candidate : band) {
            sb.append(candidate.scene.id()).append(',');
        }
        return sb.toString();
    }

    /** FNV-1a: identical on every platform, unlike {@code String.hashCode} for non-ASCII input. */
    private static long hash(String text) {
        long hash = 0xcbf29ce484222325L;
        for (byte b : text.getBytes(StandardCharsets.UTF_8)) {
            hash ^= (b & 0xffL);
            hash *= 0x100000001b3L;
        }
        return hash & Long.MAX_VALUE;
    }

    private static boolean modPresent(String modId) {
        if (modId == null || modId.isBlank()) {
            return true;
        }
        try {
            return ModList.get() != null && ModList.get().isLoaded(modId.trim().toLowerCase(Locale.ROOT));
        } catch (Throwable t) {
            return false;
        }
    }

    /** A candidate under evaluation: the scene, what it bound, and what it scored. */
    private static final class Candidate {
        private final SceneDefinition scene;
        private final Optional<EpisodeRecord> episode;
        private final SlotBinder.Result binding;
        private SelectionScore score = new SelectionScore();

        Candidate(SceneDefinition scene, Optional<EpisodeRecord> episode, SlotBinder.Result binding) {
            this.scene = scene;
            this.episode = episode;
            this.binding = binding;
        }
    }

    /** Exposed for the debug command: which villager UUID a plan was made for, if any. */
    public static Optional<UUID> villagerOf(Entity villager) {
        return villager == null ? Optional.empty() : Optional.of(villager.getUUID());
    }

    /** True when this villager is in a state that should suppress ordinary initiative (spec §11.2). */
    public static boolean isBusy(Entity villager) {
        return McaCompat.isPanicking(villager) || McaCompat.isGrieving(villager);
    }
}
