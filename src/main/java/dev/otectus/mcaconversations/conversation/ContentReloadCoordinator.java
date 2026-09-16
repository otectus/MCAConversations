package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.history.NarrativeCatalog;
import dev.otectus.mcaconversations.identity.IdentityCatalog;
import dev.otectus.mcaconversations.interiority.InteriorityProfile;
import dev.otectus.mcaconversations.profession.ProfessionProfiles;
import dev.otectus.mcaconversations.scene.SceneCatalog;
import dev.otectus.mcaconversations.village.VillageCultureCatalog;
import dev.otectus.mcaconversations.chat.IntentIndex;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The single owner of published content.
 *
 * <p>One listener replaces the eleven that used to publish independently. It stages every owned
 * section and the dialogue validation index on the reload's background executor, cross-validates
 * them, and then publishes — once, with one reference assignment, on the server apply executor —
 * only when the executable half MCA parses has also materialised. A rejected reload changes nothing:
 * the previous bundle, the previous generation and the previous executable questions all stay in
 * force, so an offer a player is still looking at can still be answered.
 *
 * <p><b>Why the publication point is inside MCA's apply tail.</b> Half of this mod's content is
 * dialogue JSON that MCA's own listener parses. Publishing before that would mean publishing metadata
 * validated against resources whose executable form had not been built yet; publishing after the
 * whole reload would leave a window where the metadata is new and the questions are old. The tail of
 * {@code Dialogues.apply} is the one moment where both halves exist and neither has been observed.
 * {@code docs/RELOAD-TRANSACTION-BOUNDARY.md} §4.1 is the design; §4.2 lists what it still cannot do.
 *
 * <p>All of it is guarded: nothing thrown here may escape into the reload future, because an
 * exception there aborts world loading.
 */
public final class ContentReloadCoordinator implements PreparableReloadListener {

    private static final AtomicLong ATTEMPTS = new AtomicLong();

    private static volatile ConversationContentBundle committed = ConversationContentBundle.UNAVAILABLE;
    private static volatile ContentReloadAttempt pending;
    private static volatile boolean hookEverObserved;
    /**
     * Whether an MCA apply is expected at all. Production reads {@link McaBridge}; the fixtures drive
     * the boundary without MCA on the classpath, which the unit JVM cannot supply.
     */
    private static volatile java.util.function.BooleanSupplier mcaExpected = McaBridge::isAvailable;

    private final ContentReloadAttempt attempt;

    private ContentReloadCoordinator(ContentReloadAttempt attempt) {
        this.attempt = attempt;
    }

    // --- The published view --------------------------------------------------------------------

    /** The content in force. Never null; {@link ConversationContentBundle#UNAVAILABLE} before a commit. */
    public static ConversationContentBundle committed() {
        return committed;
    }

    /**
     * True once a reload has successfully published. Before that the mod has no validated content and
     * says so, rather than offering entries built from an empty catalog.
     */
    public static boolean contentAvailable() {
        return committed.available();
    }

    /** The attempt currently between this mod's apply and MCA's, or null. */
    public static ContentReloadAttempt pending() {
        return pending;
    }

    /** True when the apply-tail hook has ever run in this process — the retention health signal. */
    public static boolean hookObserved() {
        return hookEverObserved;
    }

    // --- Lifecycle -----------------------------------------------------------------------------

    /**
     * Opens an attempt and returns the listener to register. Called from this mod's
     * {@code AddReloadListenerEvent} handler at {@code HIGH}, so it runs before MCA's handler has
     * constructed the replacement {@code Dialogues} the attempt will be measured against.
     */
    public static ContentReloadCoordinator begin() {
        ContentReloadAttempt opened = new ContentReloadAttempt(ATTEMPTS.incrementAndGet(), committed);
        pending = opened;
        return new ContentReloadCoordinator(opened);
    }

    /** Releases the strong executable references at server stop and forgets any pending attempt. */
    public static void reset() {
        pending = null;
        committed = ConversationContentBundle.UNAVAILABLE;
    }

    @Override
    public String getName() {
        return "mcaconversations:content";
    }

    @Override
    public CompletableFuture<Void> reload(PreparationBarrier barrier, ResourceManager manager,
                                          ProfilerFiller prepareProfiler, ProfilerFiller applyProfiler,
                                          Executor prepareExecutor, Executor applyExecutor) {
        return CompletableFuture
                .supplyAsync(() -> stage(manager), prepareExecutor)
                .thenCompose(barrier::wait)
                .thenAcceptAsync(this::apply, applyExecutor);
    }

    /**
     * Registered after MCA's listener. Minecraft releases each listener's barrier only when the
     * preceding listener future completes; queueing a task from our own apply runs it too early.
     * This listener loads nothing and observes the outcome even if an earlier apply failed.
     */
    public static PreparableReloadListener completionListener() {
        ContentReloadAttempt observed = pending;
        return new PreparableReloadListener() {
            @Override
            public String getName() {
                return "mcaconversations:content_completion";
            }

            @Override
            public CompletableFuture<Void> reload(PreparationBarrier barrier, ResourceManager manager,
                    ProfilerFiller prepareProfiler, ProfilerFiller applyProfiler,
                    Executor prepareExecutor, Executor applyExecutor) {
                return barrier.wait((Void) null).whenCompleteAsync((unused, failure) -> {
                    if (observed != null) {
                        observe(observed);
                    }
                }, applyExecutor);
            }
        };
    }

    // --- Preparation ---------------------------------------------------------------------------

    /**
     * Reads and builds every owned section plus the dialogue validation index. Pure: it publishes
     * nothing, touches no live entity and mutates no active field, so it is safe on the background
     * pool. An ordinary resource or build failure completes as a rejected staging result rather than
     * as a thrown exception, so the attempt still participates in Forge's preparation barrier instead
     * of aborting the reload.
     */
    private ConversationContentBundle stage(ResourceManager manager) {
        try {
            Map<ContentSection, ContentSources.Staged> documents = new LinkedHashMap<>();
            for (ContentSection section : ContentSection.values()) {
                ContentSources.Staged read = ContentSources.read(manager, section);
                documents.put(section, read);
                attempt.record(read.problems());
            }

            StagingResult<IntentIndex> intents =
                    ContentStaging.chatIntents(docs(documents, ContentSection.CHAT_INTENTS));
            StagingResult<ConversationCatalog> topics =
                    ContentStaging.conversationCatalog(docs(documents, ContentSection.CONVERSATION_CATALOG));
            StagingResult<BeatCatalog> beats =
                    ContentStaging.beatContracts(docs(documents, ContentSection.CONVERSATION_BEATS));
            StagingResult<ProfessionProfiles> professions =
                    ContentStaging.professionProfiles(docs(documents, ContentSection.PROFESSION_PROFILES));
            StagingResult<Map<String, InteriorityProfile>> interiority =
                    ContentStaging.interiority(docs(documents, ContentSection.INTERIORITY));
            StagingResult<IdentityCatalog> identity =
                    ContentStaging.identityTokens(docs(documents, ContentSection.IDENTITY_TOKENS));
            StagingResult<SceneCatalog> scenes =
                    ContentStaging.scenes(docs(documents, ContentSection.CONVERSATION_SCENES));
            StagingResult<VillageCultureCatalog> culture =
                    ContentStaging.villageCulture(docs(documents, ContentSection.VILLAGE_CULTURE));
            StagingResult<NarrativeCatalog> narrative = ContentStaging.narrative(
                    docs(documents, ContentSection.EPISODE_TEMPLATES),
                    docs(documents, ContentSection.THREAD_TEMPLATES),
                    docs(documents, ContentSection.COMMITMENT_TEMPLATES));

            List<StagingResult<?>> sections = List.of(intents, topics, beats, professions, interiority,
                    identity, scenes, culture, narrative);
            sections.forEach(section -> attempt.record(section.problems()));

            DialogueResourceIndex dialogues = DialogueResourceIndex.read(manager);
            attempt.record(dialogues.problems());

            if (sections.stream().anyMatch(StagingResult::fatal) || dialogues.fatal()) {
                return null;
            }

            ConversationContentBundle staged = new ConversationContentBundle(
                    attempt.targetGeneration(), true, attempt.id(),
                    intents.value(), topics.value(), beats.value(), professions.value(),
                    interiority.value(), identity.value(), scenes.value(), culture.value(),
                    narrative.value(), dialogues, Map.of(), false, List.of());

            attempt.record(ContentValidation.validate(staged, dialogues, documents));
            return attempt.fatal() ? null : staged;
        } catch (Throwable t) {
            attempt.record(ContentProblem.of(null, ResourceOrigin.unknownPack(null), "", "",
                    ContentSeverity.REFUSED, "preparation_failed", String.valueOf(t)));
            return null;
        }
    }

    private static List<StagedResource> docs(Map<ContentSection, ContentSources.Staged> all,
                                             ContentSection section) {
        ContentSources.Staged staged = all.get(section);
        return staged == null ? List.of() : staged.documents();
    }

    // --- Apply, without publishing -------------------------------------------------------------

    /**
     * Records the verdict. Deliberately publishes nothing: no active catalog, generation, session or
     * cache changes here, because MCA has not parsed the executable half yet.
     */
    private void apply(ConversationContentBundle staged) {
        try {
            if (staged == null || attempt.fatal()) {
                attempt.verdict(ContentReloadAttempt.Verdict.REJECTED);
                return;
            }
            attempt.stage(staged);
            attempt.verdict(ContentReloadAttempt.Verdict.READY_FOR_MCA);
            if (!mcaExpected.getAsBoolean()) {
                // No MCA, so no executable half will ever arrive and no owned question can exist.
                // The owned catalogs are still this mod's own data and are still worth publishing:
                // every feature that would execute one is already gated on MCA being present.
                publish(attempt, Map.of(), false, "MCA absent; no executable questions to retain");
                return;
            }
        } catch (Throwable t) {
            attempt.verdict(ContentReloadAttempt.Verdict.REJECTED);
            McaConversations.LOGGER.error("Content reload apply failed; the previous bundle stays in force", t);
        }
    }

    private static void observe(ContentReloadAttempt observed) {
        try {
            if (pending != observed) {
                return;
            }
            if (observed.verdict() == ContentReloadAttempt.Verdict.COMMITTED) {
                return;
            }
            if (!observed.tailObserved()) {
                observed.verdict(ContentReloadAttempt.Verdict.REJECTED);
                observed.record(ContentProblem.of(null, ResourceOrigin.unknownPack(null), "", "",
                        ContentSeverity.REFUSED, "mca_apply_tail_not_observed",
                        observed.applyStarted()
                                ? "MCA's dialogue apply started but never finished"
                                : "MCA's dialogue apply never ran, or the retention hook is not installed"));
                summarise(observed, "no MCA apply tail; nothing published");
            }
            pending = null;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("reload observation failed", t);
        }
    }

    // --- The MCA boundary ----------------------------------------------------------------------

    /** Called from {@code Dialogues.apply} HEAD. Binds the attempt to the exact incoming instance. */
    public static void onDialoguesApplyHead(Object instance) {
        ContentReloadAttempt open = pending;
        if (open == null) {
            return;
        }
        open.mcaInstance(instance);
        open.applyStarted(true);
    }

    /**
     * Called from {@code Dialogues.apply} TAIL with the map MCA just filled.
     *
     * <p>The map is read directly — the pinned {@code getQuestion} boundary is bypassed on purpose,
     * because what is needed here is exactly the newly parsed objects that boundary is hiding.
     *
     * <p>On acceptance the owned questions are materialised and the bundle is published. On rejection
     * the newly parsed owned keys are removed and the previous bundle's owned table is put back:
     * removing the new-only keys first is what stops a rejected addition leaking into execution.
     */
    public static void onDialoguesApplyTail(Object instance, Map<String, Object> questions) {
        hookEverObserved = true;
        ContentReloadAttempt open = pending;
        if (open == null || questions == null) {
            return;
        }
        if (open.mcaInstance() != null && open.mcaInstance() != instance) {
            // A callback from another instance, or another server lifecycle, cannot commit.
            return;
        }
        open.tailObserved(true);
        open.applyFinished(true);
        try {
            if (open.verdict() != ContentReloadAttempt.Verdict.READY_FOR_MCA) {
                restore(open, questions);
                return;
            }
            Map<String, Object> owned = new LinkedHashMap<>();
            List<String> missing = new ArrayList<>();
            for (String name : open.staged().dialogues().ownedNames()) {
                Object question = questions.get(name);
                if (question == null) {
                    missing.add(name);
                } else {
                    owned.put(name, question);
                }
            }
            for (Map.Entry<String, Object> entry : questions.entrySet()) {
                if (ConversationGuard.isOurQuestion(entry.getKey()) && entry.getValue() != null) {
                    owned.putIfAbsent(entry.getKey(), entry.getValue());
                }
            }
            if (!missing.isEmpty()) {
                open.record(ContentProblem.of(null, ResourceOrigin.unknownPack(null), "", missing.get(0),
                        ContentSeverity.REFUSED, "owned_question_not_materialised",
                        missing.size() + " validated owned question(s) are absent from MCA's parsed map, "
                                + "starting with '" + missing.get(0) + "'"));
                open.verdict(ContentReloadAttempt.Verdict.REJECTED);
                restore(open, questions);
                return;
            }
            publish(open, owned, true, "published");
        } catch (Throwable t) {
            open.verdict(ContentReloadAttempt.Verdict.REJECTED);
            McaConversations.LOGGER.error("Owned dialogue retention failed; the previous bundle stays in force", t);
            try {
                restore(open, questions);
            } catch (Throwable ignored) {
                McaConversations.LOGGER.error("Owned dialogue restoration also failed", ignored);
            }
        }
    }

    private static void restore(ContentReloadAttempt open, Map<String, Object> questions) {
        ConversationContentBundle previous = open.previous();
        int removed = 0;
        List<String> newKeys = new ArrayList<>();
        for (String name : questions.keySet()) {
            if (ConversationGuard.isOurQuestion(name)) {
                newKeys.add(name);
            }
        }
        for (String name : newKeys) {
            questions.remove(name);
            removed++;
        }
        questions.putAll(previous.ownedQuestions());
        pending = null;
        summarise(open, "rejected; removed " + removed + " newly parsed owned key(s) and reinstated "
                + previous.retainedQuestionCount() + " retained question(s)");
    }

    private static void publish(ContentReloadAttempt open, Map<String, Object> owned, boolean ownedKnown,
                                String why) {
        ConversationContentBundle published = open.staged().published(open.targetGeneration(), open.id(),
                owned, ownedKnown, open.problems());
        committed = published;
        open.verdict(ContentReloadAttempt.Verdict.COMMITTED);
        pending = null;
        // Only after the assignment: cancelling before it would tear a conversation down over a
        // publication that had not happened yet. An open offer is left standing on purpose, so the
        // player's next click is refused with the truthful reload outcome instead of vanishing.
        int cancelled = 0;
        try {
            cancelled = ConversationSessions.closeForContentReload();
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("content-reload session teardown failed", t);
        }
        summarise(open, cancelled == 0 ? why : why + "; cancelled " + cancelled + " pending session(s)");
    }

    // --- The owned lookup boundary -------------------------------------------------------------

    /**
     * True when this mod is answering for owned question names at all. False until a bundle with a
     * materialised executable table has been committed, so before the first commit every lookup
     * passes straight through to MCA.
     */
    public static boolean governsOwnedLookups() {
        ConversationContentBundle bundle = ContentOperation.bundle();
        return bundle.ownedQuestionsKnown();
    }

    /**
     * The executable question for an owned name under the bundle this operation is running against.
     *
     * <p>Absence is authoritative. Returning null cancels the lookup rather than falling through,
     * because falling through would hand back a newly parsed question the committed bundle never
     * validated — which is the exact mixing this whole transaction exists to prevent.
     */
    public static Object ownedQuestion(String name) {
        return ContentOperation.bundle().ownedQuestion(name);
    }

    /**
     * What the {@code getQuestion} boundary should do for one name, as a value the mixin merely
     * applies. Keeping the decision here rather than inside the injection is what makes it testable
     * without MCA on the classpath.
     */
    public static OwnedLookup lookup(String name) {
        if (!ConversationGuard.isOurQuestion(name)) {
            // MCA's own names, and every other mod's, are outside the guarantee and always were.
            return OwnedLookup.passThrough();
        }
        ConversationContentBundle bundle = ContentOperation.bundle();
        if (!bundle.ownedQuestionsKnown()) {
            // No bundle has ever committed an executable table, so there is nothing to answer with
            // and answering "absent" would break a mod that has not finished starting.
            return OwnedLookup.passThrough();
        }
        return OwnedLookup.answered(bundle.ownedQuestion(name));
    }

    /** The boundary's answer: either "not mine" or "mine, and here it is — possibly absent". */
    public static final class OwnedLookup {

        private static final OwnedLookup PASS_THROUGH = new OwnedLookup(false, null);

        private final boolean intercepted;
        private final Object question;

        private OwnedLookup(boolean intercepted, Object question) {
            this.intercepted = intercepted;
            this.question = question;
        }

        static OwnedLookup passThrough() {
            return PASS_THROUGH;
        }

        static OwnedLookup answered(Object question) {
            return new OwnedLookup(true, question);
        }

        /** True when MCA's own lookup must be cancelled and {@link #question()} returned instead. */
        public boolean intercepted() {
            return intercepted;
        }

        /** The retained executable question, or null when this bundle does not declare the name. */
        public Object question() {
            return question;
        }
    }

    // --- Diagnostics ---------------------------------------------------------------------------

    /**
     * The terminal summary: one record per observed attempt outcome. Staged acceptance and committed
     * acceptance are named differently on purpose — only the latter published a generation.
     */
    private static void summarise(ContentReloadAttempt open, String why) {
        try {
            List<ContentProblem> problems = open.problems();
            problems.stream().filter(p -> p.severity().fatal())
                    .forEach(p -> McaConversations.LOGGER.error(p.format()));
            problems.stream().filter(p -> p.severity() == ContentSeverity.SKIPPED
                            || p.severity() == ContentSeverity.RETAINED)
                    .forEach(p -> McaConversations.LOGGER.warn(p.format()));
            problems.stream().filter(p -> p.severity() == ContentSeverity.INFO)
                    .forEach(p -> McaConversations.LOGGER.debug(p.format()));
            boolean committedNow = open.verdict() == ContentReloadAttempt.Verdict.COMMITTED;
            McaConversations.LOGGER.info(
                    "Content reload attempt {}: {} — previous generation {}, {} generation {}, "
                            + "{} retained question(s), hook observed: {} ({})",
                    open.id(),
                    committedNow ? "committed" : "rejected",
                    open.previous().generation(),
                    committedNow ? "published" : "still",
                    committedNow ? committed.generation() : open.previous().generation(),
                    committed.retainedQuestionCount(),
                    open.tailObserved(),
                    why);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("reload summary failed", t);
        }
    }

    // --- Test seams ----------------------------------------------------------------------------

    /**
     * Replaces the committed bundle outright. The loaders' {@code setActiveForTesting} seams and the
     * reload fixtures go through here; production code never does.
     */
    public static void setCommittedForTesting(ConversationContentBundle bundle) {
        committed = bundle == null ? ConversationContentBundle.UNAVAILABLE : bundle;
    }

    /** Test seam: drive the MCA apply-tail boundary without MCA on the classpath. */
    public static void setMcaExpectedForTesting(boolean expected) {
        mcaExpected = () -> expected;
    }

    /** Restores the production answer to "is an MCA apply coming". */
    public static void clearMcaExpectedForTesting() {
        mcaExpected = McaBridge::isAvailable;
    }

    /** Publishes the committed content again under the next generation, for offer-staleness tests. */
    public static long advanceGenerationForTesting() {
        ConversationContentBundle now = committed;
        committed = now.published(now.generation() + 1L, now.attempt(), now.ownedQuestions(),
                now.ownedQuestionsKnown(), now.problems());
        return committed.generation();
    }
}
