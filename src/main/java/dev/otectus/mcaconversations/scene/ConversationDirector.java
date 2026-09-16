package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.FeatureId;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.context.ContextKeys;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.history.CommitmentRecord;
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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.function.Predicate;

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

    /** Scored positions held for scenes that answer a due promise before ordinary priority runs. */
    static final int RESERVED_DUE = 8;

    /** Scored positions held for an unresolved rupture or a thread that is ready to be picked up. */
    static final int RESERVED_CONTINUITY = 8;

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
     *         existing static route, which is what makes this layer additive
     */
    public static Optional<ConversationPlan> select(Entity villager, ServerPlayer player,
                                                    ScenePurpose purpose, String topic,
                                                    ConversationContextSnapshot snapshot) {
        if (!McaConversationsConfig.dynamicFeature(FeatureId.DYNAMIC, false)
                || villager == null || player == null || purpose == null || snapshot == null) {
            return Optional.empty();
        }
        try {
            return selectUnguarded(villager, player, purpose, topic, snapshot);
        } catch (Throwable t) {
            // A director failure must cost the dynamic scene and nothing else: the caller falls back
            // to the static route it would have taken with the dynamic layer switched off.
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

        String professionId = snapshot.value(ContextKeys.WORK_PROFESSION_ID).orElse("");
        List<SceneDefinition> indexed =
                new ArrayList<>(catalog.candidates(purpose, topic, professionId));
        explanation.indexed(indexed.size());
        if (indexed.isEmpty()) {
            return Optional.empty();
        }
        // Highest authored priority first, id second. The scored cap below is a bound on work, not an
        // editorial decision, and taking the first thirty-two alphabetically made it into one: a scene
        // could be dropped for its name while a less important one survived.
        indexed.sort(Comparator.comparingInt((SceneDefinition scene) -> -scene.basePriority())
                .thenComparing(SceneDefinition::id));

        long today = snapshot.capturedDay();
        ProfessionProfile profile = ProfessionProfileLoader.profile(
                professionId.isEmpty() ? null : professionId,
                snapshot.value(ContextKeys.WORK_PROFESSION_NAME).orElse("villager"));
        Optional<VillagerIdentityRecord> identity = Identity.of(villager);
        Optional<PairHistory> pair = History.pair(villager, player);
        TopicRecencyRecord recency = pair.map(PairHistory::recency).orElse(TopicRecencyRecord.EMPTY);
        ServerLevel level = villager.level() instanceof ServerLevel serverLevel ? serverLevel : null;
        Optional<dev.otectus.mcaconversations.history.VillagerHistory> villagerHistory = History.of(villager);
        List<ContinuationResolver.Continuation> continuations = ContinuationResolver.resolve(pair.orElse(null),
                dev.otectus.mcaconversations.history.NarrativeCatalogLoader.active(), catalog,
                episodeId -> villagerHistory.flatMap(records -> records.episode(episodeId)), today);
        Gate gate = new Gate(villager, snapshot, profile, identity, pair, continuations, recency, level, today);

        // Stage 2-3: hard eligibility, then semantic eligibility through slot binding — in admission
        // order, so what this pair already has open is evaluated before the ordinary priority run
        // rather than after the scored cap has already closed.
        List<Candidate> eligible = new ArrayList<>();
        Set<String> considered = new LinkedHashSet<>();
        Set<String> admitted = new LinkedHashSet<>();
        List<SceneDefinition> degraded = new ArrayList<>();
        Predicate<SceneDefinition> evaluator = scene -> {
            considered.add(scene.id());
            Candidate candidate = evaluate(scene, gate, explanation);
            if (candidate != null) {
                eligible.add(candidate);
                admitted.add(scene.id());
                return true;
            }
            if (scene.hasFallback()) {
                degraded.add(scene);
            }
            return false;
        };

        // What this pair already has open, including the authored boundary a thread would be picked
        // up at. Read through the same pinned bundle as the catalogs above, so a continuation is only
        // ever offered into content this operation is actually running against.
        Relevance relevance = pair.map(history -> relevanceOf(history, continuations, today))
                .orElse(Relevance.NONE);
        Admission admission = admission(catalog, indexed, purpose, topic, professionId, relevance);
        Budget budget = new Budget(SceneCatalog.MAX_INDEXED);
        AdmissionOutcome outcome = admit(admission, budget, evaluator);
        explanation.reserved(outcome.reserved());
        if (outcome.scored() >= SceneCatalog.MAX_SCORED) {
            explanation.note("scored set capped at " + SceneCatalog.MAX_SCORED
                    + "; " + Math.max(0, admission.ordinary().size() - considered.size())
                    + " candidate(s) not evaluated");
        }

        // Stage 3b: a scene that could not be told degrades to the route its author named for exactly
        // this case — nearest hop first, every hop re-gated (spec §10.4). Reached only after the
        // preferred scene failed, so a fallback never competes with the scene that declared it.
        List<SceneDefinition> degrading = List.copyOf(degraded);
        for (SceneDefinition scene : degrading) {
            if (eligible.size() >= SceneCatalog.MAX_SCORED) {
                break;
            }
            for (SceneDefinition next : FallbackChain.from(catalog, scene)) {
                if (admitted.contains(next.id())) {
                    break;
                }
                if (considered.contains(next.id())) {
                    continue;
                }
                // A degrade is evaluation like any other, so it is paid for out of the same budget:
                // a long chain behind every failed scene must not become an unbounded second pass.
                if (!budget.spend()) {
                    break;
                }
                if (evaluator.test(next)) {
                    explanation.note("scene '" + scene.id() + "' degraded to '" + next.id() + "'");
                    break;
                }
            }
        }
        explanation.afterHardFilters(eligible.size());
        explanation.evaluated(budget.spent());
        explanation.budgetExhausted(budget.exhausted());
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

    /** The inputs every gate needs, gathered so evaluating one scene is one call (spec §9.1). */
    private record Gate(Entity villager, ConversationContextSnapshot snapshot, ProfessionProfile profile,
                        Optional<VillagerIdentityRecord> identity, Optional<PairHistory> pair,
                        List<ContinuationResolver.Continuation> continuations, TopicRecencyRecord recency,
                        ServerLevel level, long today) {
    }

    /**
     * Stages two and three for one scene: the hard gates, the recency gate, then slot binding.
     *
     * <p>Extracted so the fallback pass runs the identical gate stack. A degrade that skipped a check
     * the preferred scene had to pass would be a hole straight through eligibility.
     *
     * @return the bound candidate, or null after recording the first decisive reason it was rejected
     */
    private static Candidate evaluate(SceneDefinition scene, Gate gate,
                                      SelectionExplanation explanation) {
        Optional<EpisodeRecord> episode = scene.needsEpisode()
                ? SceneEpisodes.resolve(scene, gate.pair().orElse(null), gate.continuations(),
                        id -> History.of(gate.villager()).flatMap(history -> history.episode(id)),
                        () -> History.liveEpisode(gate.villager(), scene.episodeKind(), gate.today()), gate.today())
                : Optional.empty();

        String reason = SceneEligibility.check(scene, gate.snapshot(), gate.profile(), gate.identity(),
                episode, ConversationDirector::modPresent, gate.today());
        if (!reason.isEmpty()) {
            explanation.reject(scene.id(), reason);
            return null;
        }
        long daysSinceScene =
                gate.recency().daysSince(TopicRecencyRecord.Level.SCENE, scene.id(), gate.today());
        reason = SceneEligibility.checkRecency(scene, daysSinceScene,
                gate.recency().mentionsInWindow(scene.id(), gate.today()));
        if (!reason.isEmpty()) {
            explanation.reject(scene.id(), reason);
            return null;
        }
        SlotBinder.Result binding = SlotBinder.bind(scene, episode, gate.snapshot(), gate.level());
        if (!binding.bound()) {
            explanation.reject(scene.id(), "slot '" + binding.failedSlot() + "' could not bind");
            return null;
        }
        return new Candidate(scene, episode, binding);
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

    // --- Continuity admission (spec §9.1) ---------------------------------------------------------

    /**
     * What this pair already has open, as scene-matching keys.
     *
     * <p>Split into two classes because they are two different claims on a scored position: a promise
     * whose day has come, and continuity that is merely unresolved. Keys are scene-specific — a thread
     * template or a subject — rather than the purpose the caller asked for, because the due-promise and
     * rupture bonuses in {@link #score} apply to every candidate of that purpose and so cannot tell one
     * scene from another. A class carries a scene only when the scene itself names the thread or the
     * subject at issue.
     */
    record Relevance(Set<String> dueThreads, Set<String> dueSubjects,
                     Set<String> openThreads, Set<String> openSubjects,
                     Set<String> resumeScenes) {

        static final Relevance NONE = new Relevance(Set.of(), Set.of(), Set.of(), Set.of());

        /** Relevance with no authored continuation resolved — the four record-derived classes only. */
        Relevance(Set<String> dueThreads, Set<String> dueSubjects,
                  Set<String> openThreads, Set<String> openSubjects) {
            this(dueThreads, dueSubjects, openThreads, openSubjects, Set.of());
        }

        boolean isEmpty() {
            return dueThreads.isEmpty() && dueSubjects.isEmpty()
                    && openThreads.isEmpty() && openSubjects.isEmpty() && resumeScenes.isEmpty();
        }
    }

    /** The evaluation order for one selection: reserved classes first, then ordinary priority. */
    record Admission(List<SceneDefinition> due, List<SceneDefinition> continuity,
                     List<SceneDefinition> ordinary) {
    }

    /** What admission actually spent, for the trace. */
    record AdmissionOutcome(int evaluated, int scored, int reserved, boolean budgetExhausted) {
    }

    /** The shared evaluation allowance, so no pass — including a fallback chain — is unbounded. */
    static final class Budget {
        private final int allowance;
        private int spent;

        Budget(int allowance) {
            this.allowance = Math.max(0, allowance);
        }

        /** Claims one evaluation; false once the allowance is gone. */
        boolean spend() {
            if (spent >= allowance) {
                return false;
            }
            spent++;
            return true;
        }

        int spent() {
            return spent;
        }

        /**
         * True once the allowance is gone.
         *
         * <p>Reported by reaching the bound rather than by being refused at it: a selection that spent
         * its last evaluation on its last candidate is still a selection whose result cannot be read
         * as "these were all the candidates there were".
         */
        boolean exhausted() {
            return spent >= allowance;
        }
    }

    /**
     * Reads the pair's own state into the two relevance classes.
     *
     * <p>A due promise reaches a scene through the thread that carries it: a commitment names no topic
     * of its own, so the thread waiting on it is what says which scene would be answering it.
     */
    static Relevance relevanceOf(PairHistory history, long today) {
        if (history == null) {
            return Relevance.NONE;
        }
        Set<String> dueCommitments = new TreeSet<>();
        for (CommitmentRecord commitment : history.due(today)) {
            dueCommitments.add(commitment.id());
        }
        Set<String> dueThreads = new TreeSet<>();
        Set<String> dueSubjects = new TreeSet<>();
        Set<String> openThreads = new TreeSet<>();
        Set<String> openSubjects = new TreeSet<>();
        for (SharedThreadRecord thread : history.threads()) {
            if (thread.outstandingCommitment().filter(dueCommitments::contains).isPresent()) {
                key(dueThreads, thread.templateId());
                key(dueSubjects, thread.subject());
            }
        }
        history.rupture().ifPresent(thread -> {
            key(openThreads, thread.templateId());
            key(openSubjects, thread.subject());
        });
        for (SharedThreadRecord thread : history.resumable(today)) {
            key(openThreads, thread.templateId());
            key(openSubjects, thread.subject());
        }
        // Deduplicated across classes: a thread that is both due and merely unresolved is one claim,
        // and the stronger class keeps it.
        openThreads.removeAll(dueThreads);
        openSubjects.removeAll(dueSubjects);
        return new Relevance(Set.copyOf(dueThreads), Set.copyOf(dueSubjects),
                Set.copyOf(openThreads), Set.copyOf(openSubjects));
    }

    /**
     * The same two classes, plus the scenes the pair's threads are authored to be picked up in.
     *
     * <p>A resume scene is not necessarily a scene that <em>opens</em> the thread, so the template and
     * subject indexes cannot find it: an author writes "we left this unfinished" as its own scene, and
     * until it is named by the thread template nothing points at it. Naming it here is what lets a
     * continuation reach scoring at all; it changes no weight and skips no gate.
     */
    static Relevance relevanceOf(PairHistory history, dev.otectus.mcaconversations.history.NarrativeCatalog narrative,
                                 SceneCatalog catalog, ContinuationResolver.Episodes episodes, long today) {
        return relevanceOf(history, ContinuationResolver.resolve(history, narrative, catalog, episodes, today), today);
    }

    private static Relevance relevanceOf(PairHistory history,
                                         List<ContinuationResolver.Continuation> continuations, long today) {
        Relevance base = relevanceOf(history, today);
        Set<String> resume = ContinuationResolver.resumeSceneIds(continuations);
        return resume.isEmpty() ? base
                : new Relevance(base.dueThreads(), base.dueSubjects(), base.openThreads(),
                        base.openSubjects(), resume);
    }

    private static void key(Set<String> into, String value) {
        if (value != null && !value.isEmpty()) {
            into.add(value);
        }
    }

    /**
     * Orders the scenes this selection may evaluate: due-promise candidates, then unresolved
     * continuity, then the ordinary priority run.
     *
     * <p>Reserved candidates are looked up through the catalog's continuity indexes rather than taken
     * from the merged bucket, which is the point of the stage: a relevant scene must not be lost
     * because {@value SceneCatalog#MAX_INDEXED} alphabetically earlier scenes share its leaf. Every
     * reserved scene still has to be one this conversation could be having, so purpose, topic and
     * profession are matched exactly as the index lookup matches them; everything else is left to the
     * ordinary gates.
     */
    static Admission admission(SceneCatalog catalog, List<SceneDefinition> indexed,
                               ScenePurpose purpose, String topic, String profession,
                               Relevance relevance) {
        List<SceneDefinition> due = List.of();
        List<SceneDefinition> continuity = List.of();
        if (catalog != null && !relevance.isEmpty()) {
            due = relevant(catalog, purpose, topic, profession, Set.of(),
                    relevance.dueThreads(), relevance.dueSubjects(), Set.of());
            Set<String> taken = new LinkedHashSet<>();
            due.forEach(scene -> taken.add(scene.id()));
            continuity = relevant(catalog, purpose, topic, profession, relevance.resumeScenes(),
                    relevance.openThreads(), relevance.openSubjects(), taken);
        }

        // The ordinary run is the indexed bucket plus any reserved scene the bucket did not hold, in
        // one priority order. A reserved scene that overflows its class is therefore not dropped: it
        // competes for an ordinary position like anything else.
        Map<String, SceneDefinition> ordinary = new LinkedHashMap<>();
        for (SceneDefinition scene : indexed) {
            ordinary.putIfAbsent(scene.id(), scene);
        }
        for (SceneDefinition scene : due) {
            ordinary.putIfAbsent(scene.id(), scene);
        }
        for (SceneDefinition scene : continuity) {
            ordinary.putIfAbsent(scene.id(), scene);
        }
        List<SceneDefinition> run = new ArrayList<>(ordinary.values());
        run.sort(Comparator.comparingInt((SceneDefinition scene) -> -scene.basePriority())
                .thenComparing(SceneDefinition::id));
        return new Admission(due, continuity, List.copyOf(run));
    }

    /**
     * The candidates one relevance class names, most precisely matched first.
     *
     * <p>Order is fixed: the scene the thread's own template names as its way back, then a scene that
     * opens the very thread at issue, then a scene that merely names its subject, then authored
     * priority, then id. Nothing here depends on map iteration or on a clock, so an overflowing
     * reservation drops the same scenes on every server.
     */
    private static List<SceneDefinition> relevant(SceneCatalog catalog, ScenePurpose purpose,
                                                  String topic, String profession,
                                                  Set<String> resumeScenes,
                                                  Set<String> threads, Set<String> subjects,
                                                  Set<String> exclude) {
        Map<String, SceneDefinition> found = new LinkedHashMap<>();
        Map<String, Integer> rank = new LinkedHashMap<>();
        for (String sceneId : new TreeSet<>(resumeScenes)) {
            catalog.scene(sceneId).ifPresent(scene ->
                    collect(List.of(scene), 0, purpose, topic, profession, exclude, found, rank));
        }
        for (String thread : new TreeSet<>(threads)) {
            collect(catalog.byThreadTemplate(thread), 1, purpose, topic, profession, exclude, found, rank);
        }
        for (String subject : new TreeSet<>(subjects)) {
            collect(catalog.bySubject(subject), 2, purpose, topic, profession, exclude, found, rank);
        }
        List<SceneDefinition> out = new ArrayList<>(found.values());
        out.sort(Comparator.comparingInt((SceneDefinition scene) -> rank.get(scene.id()))
                .thenComparingInt(scene -> -scene.basePriority())
                .thenComparing(SceneDefinition::id));
        return out.size() > SceneCatalog.MAX_INDEXED
                ? List.copyOf(out.subList(0, SceneCatalog.MAX_INDEXED)) : List.copyOf(out);
    }

    private static void collect(List<SceneDefinition> scenes, int rankValue, ScenePurpose purpose,
                                String topic, String profession, Set<String> exclude,
                                Map<String, SceneDefinition> found, Map<String, Integer> rank) {
        for (SceneDefinition scene : scenes) {
            if (exclude.contains(scene.id()) || !fits(scene, purpose, topic, profession)) {
                continue;
            }
            if (found.putIfAbsent(scene.id(), scene) == null) {
                rank.put(scene.id(), rankValue);
            }
        }
    }

    /** The index lookup's own filter, applied to a scene found outside the merged bucket. */
    private static boolean fits(SceneDefinition scene, ScenePurpose purpose, String topic,
                                String profession) {
        if (scene.purpose() != purpose) {
            return false;
        }
        String wantedTopic = topic == null ? "" : topic.trim().toLowerCase(Locale.ROOT);
        if (wantedTopic.isEmpty() ? !scene.topic().isEmpty()
                : !(scene.topic().isEmpty() || scene.topic().equals(wantedTopic))) {
            return false;
        }
        String wantedProfession = profession == null ? "" : profession.trim().toLowerCase(Locale.ROOT);
        return scene.professions().isEmpty()
                || (!wantedProfession.isEmpty() && scene.professions().contains(wantedProfession));
    }

    /**
     * Runs the admission order through one evaluator, honouring both bounds.
     *
     * <p>A reserved class holds <em>positions</em>, not outcomes: a reserved scene that fails a gate
     * costs evaluation budget and nothing else, and the position stays open for the next relevant
     * scene. Reservations are capped so at least
     * {@code MAX_SCORED - RESERVED_DUE - RESERVED_CONTINUITY} positions remain for ordinary priority,
     * and nothing is admitted twice.
     *
     * @param evaluate runs the full gate stack for one scene and reports whether it reached scoring
     */
    static AdmissionOutcome admit(Admission admission, Budget budget,
                                  Predicate<SceneDefinition> evaluate) {
        Set<String> seen = new LinkedHashSet<>();
        int[] scored = {0};
        int reserved = fill(admission.due(), RESERVED_DUE, seen, budget, evaluate, scored)
                + fill(admission.continuity(), RESERVED_CONTINUITY, seen, budget, evaluate, scored);
        fill(admission.ordinary(), SceneCatalog.MAX_SCORED, seen, budget, evaluate, scored);
        return new AdmissionOutcome(budget.spent(), scored[0], reserved, budget.exhausted());
    }

    private static int fill(List<SceneDefinition> scenes, int classLimit, Set<String> seen,
                            Budget budget, Predicate<SceneDefinition> evaluate, int[] scored) {
        int admitted = 0;
        for (SceneDefinition scene : scenes) {
            if (admitted >= classLimit || scored[0] >= SceneCatalog.MAX_SCORED) {
                break;
            }
            if (!seen.add(scene.id())) {
                continue;
            }
            if (!budget.spend()) {
                break;
            }
            if (evaluate.test(scene)) {
                admitted++;
                scored[0]++;
            }
        }
        return admitted;
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
