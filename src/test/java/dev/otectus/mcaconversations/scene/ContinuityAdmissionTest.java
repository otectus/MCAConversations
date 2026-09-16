package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.conversation.SceneShape;
import dev.otectus.mcaconversations.history.CommitmentRecord;
import dev.otectus.mcaconversations.history.CommitmentResolver;
import dev.otectus.mcaconversations.history.NarrativeValue;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.PrivacyLevel;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.ThreadStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Continuity-aware admission: what this pair already has open is evaluated before the ordinary
 * priority run, under the same gates and the same work bounds (spec §9.1).
 *
 * <p>The failure these pin is the quiet one. A callback the villager genuinely owes could be authored
 * at a low base priority, land behind thirty-two louder scenes, and never be evaluated at all — the
 * trace showed a perfectly ordinary selection because the scene that should have won was never a
 * candidate. Reservation fixes the ordering and nothing else: a reserved scene still has to pass every
 * gate, and it still has to score.
 */
class ContinuityAdmissionTest {

    private static final String THREAD = "thread.ledger";

    @Test
    void aLowPriorityDueCallbackBeyondTheOrdinaryCapReachesScoring() {
        List<SceneDefinition> scenes = new ArrayList<>(loud(40));
        scenes.add(scene("work.zz_the_ledger", 1, THREAD, Set.of()));
        SceneCatalog catalog = SceneCatalog.build(scenes);

        ConversationDirector.Relevance relevance = new ConversationDirector.Relevance(
                Set.of(THREAD), Set.of(), Set.of(), Set.of());
        ConversationDirector.Admission admission = ConversationDirector.admission(
                catalog, catalog.candidates(ScenePurpose.TOPIC, "work", ""), ScenePurpose.TOPIC,
                "work", "", relevance);

        assertEquals(List.of("work.zz_the_ledger"), ids(admission.due()));
        assertTrue(admission.ordinary().indexOf(catalog.scene("work.zz_the_ledger").orElseThrow())
                        >= SceneCatalog.MAX_SCORED,
                "the scene is last in ordinary priority; only reservation can reach it");

        Recorder recorder = new Recorder(scene -> true);
        ConversationDirector.AdmissionOutcome outcome = ConversationDirector.admit(admission,
                new ConversationDirector.Budget(SceneCatalog.MAX_INDEXED), recorder);

        assertTrue(recorder.admitted.contains("work.zz_the_ledger"));
        assertEquals(SceneCatalog.MAX_SCORED, outcome.scored());
        assertEquals(1, outcome.reserved());
    }

    @Test
    void aReadyThreadSceneIsFoundEvenWhenItsIndexLeafOverflowed() {
        // The merged bucket is capped at MAX_INDEXED, and the relevant scene sorts past it by id.
        List<SceneDefinition> scenes = new ArrayList<>(loud(SceneCatalog.MAX_INDEXED + 4));
        scenes.add(scene("work.zz_overflowed", 1, THREAD, Set.of()));
        SceneCatalog catalog = SceneCatalog.build(scenes);

        List<SceneDefinition> indexed = catalog.candidates(ScenePurpose.TOPIC, "work", "");
        assertEquals(SceneCatalog.MAX_INDEXED, indexed.size());
        assertFalse(ids(indexed).contains("work.zz_overflowed"), "the bucket lost it, as documented");

        ConversationDirector.Admission admission = ConversationDirector.admission(catalog, indexed,
                ScenePurpose.TOPIC, "work", "",
                new ConversationDirector.Relevance(Set.of(), Set.of(), Set.of(THREAD), Set.of()));
        assertEquals(List.of("work.zz_overflowed"), ids(admission.continuity()));
    }

    @Test
    void anIneligibleReservedCandidateNeverEntersScoring() {
        List<SceneDefinition> scenes = new ArrayList<>(loud(40));
        scenes.add(scene("work.zz_the_ledger", 1, THREAD, Set.of()));
        SceneCatalog catalog = SceneCatalog.build(scenes);
        ConversationDirector.Admission admission = ConversationDirector.admission(catalog,
                catalog.candidates(ScenePurpose.TOPIC, "work", ""), ScenePurpose.TOPIC, "work", "",
                new ConversationDirector.Relevance(Set.of(THREAD), Set.of(), Set.of(), Set.of()));

        Recorder recorder = new Recorder(scene -> !scene.id().equals("work.zz_the_ledger"));
        ConversationDirector.AdmissionOutcome outcome = ConversationDirector.admit(admission,
                new ConversationDirector.Budget(SceneCatalog.MAX_INDEXED), recorder);

        assertFalse(recorder.admitted.contains("work.zz_the_ledger"),
                "a reservation orders evaluation; it does not excuse a gate");
        assertTrue(recorder.evaluated.contains("work.zz_the_ledger"),
                "the failure costs evaluation budget");
        assertEquals(0, outcome.reserved(), "a failed candidate consumes no scored position");
        assertEquals(SceneCatalog.MAX_SCORED, outcome.scored(),
                "and the position it did not take is filled by ordinary priority");
    }

    @Test
    void reservationClassesNeitherDuplicateCandidatesNorStarveOrdinarySelection() {
        List<SceneDefinition> scenes = new ArrayList<>(loud(40));
        for (int i = 0; i < 12; i++) {
            scenes.add(scene("work.zz_due_" + i, 1, "thread.due_" + i, Set.of("ledgers")));
            scenes.add(scene("work.zz_open_" + i, 1, "thread.open_" + i, Set.of("ledgers")));
        }
        SceneCatalog catalog = SceneCatalog.build(scenes);

        Set<String> dueThreads = new LinkedHashSet<>();
        Set<String> openThreads = new LinkedHashSet<>();
        for (int i = 0; i < 12; i++) {
            dueThreads.add("thread.due_" + i);
            openThreads.add("thread.open_" + i);
        }
        ConversationDirector.Admission admission = ConversationDirector.admission(catalog,
                catalog.candidates(ScenePurpose.TOPIC, "work", ""), ScenePurpose.TOPIC, "work", "",
                // Both classes are over-subscribed: twelve threads each, and a subject naming all of
                // them, so each has more relevant scenes than it may reserve.
                new ConversationDirector.Relevance(dueThreads, Set.of(), openThreads, Set.of("ledgers")));

        assertEquals(12, admission.due().size());
        assertEquals(12, admission.continuity().size(),
                "the subject match adds no scene the due class already claimed");
        Set<String> both = new LinkedHashSet<>(ids(admission.due()));
        both.retainAll(ids(admission.continuity()));
        assertTrue(both.isEmpty(), "a scene relevant twice is one claim: " + both);

        Recorder recorder = new Recorder(scene -> true);
        ConversationDirector.AdmissionOutcome outcome = ConversationDirector.admit(admission,
                new ConversationDirector.Budget(SceneCatalog.MAX_INDEXED), recorder);

        assertEquals(ConversationDirector.RESERVED_DUE + ConversationDirector.RESERVED_CONTINUITY,
                outcome.reserved());
        assertEquals(SceneCatalog.MAX_SCORED, outcome.scored());
        long loudAdmitted = recorder.admitted.stream().filter(id -> id.startsWith("work.loud_")).count();
        assertEquals(SceneCatalog.MAX_SCORED - outcome.reserved(), loudAdmitted,
                "ordinary priority keeps every position continuity did not reserve");
        assertEquals(recorder.admitted.size(), new LinkedHashSet<>(recorder.admitted).size());
    }

    @Test
    void evaluationStaysBoundedForAnOverloadedCatalogAndItsFallbackChains() {
        List<SceneDefinition> scenes = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            String id = String.format(Locale.ROOT, "work.bulk_%03d", i);
            scenes.add(scene(id, 20, "", Set.of()));
        }
        SceneCatalog catalog = SceneCatalog.build(scenes);
        ConversationDirector.Admission admission = ConversationDirector.admission(catalog,
                catalog.candidates(ScenePurpose.TOPIC, "work", ""), ScenePurpose.TOPIC, "work", "",
                ConversationDirector.Relevance.NONE);

        ConversationDirector.Budget budget = new ConversationDirector.Budget(SceneCatalog.MAX_INDEXED);
        Recorder recorder = new Recorder(scene -> false);
        ConversationDirector.AdmissionOutcome outcome =
                ConversationDirector.admit(admission, budget, recorder);

        assertEquals(0, outcome.scored());
        assertEquals(SceneCatalog.MAX_INDEXED, outcome.evaluated());
        assertTrue(outcome.budgetExhausted());
        // A fallback pass shares the same allowance, so a chain behind every failed scene buys nothing.
        assertFalse(budget.spend(), "the fallback pass has nothing left to spend");
        assertEquals(SceneCatalog.MAX_INDEXED, budget.spent());
    }

    @Test
    void anUnchangedPairStateProducesTheSameAdmissionOrder() {
        List<SceneDefinition> scenes = new ArrayList<>(loud(40));
        scenes.add(scene("work.zz_the_ledger", 1, THREAD, Set.of()));
        SceneCatalog catalog = SceneCatalog.build(scenes);
        PairHistory history = new PairHistory();
        history.putThread(thread(THREAD, ThreadStatus.OPEN, "", "ledgers"));

        ConversationDirector.Relevance first = ConversationDirector.relevanceOf(history, 100);
        ConversationDirector.Relevance second = ConversationDirector.relevanceOf(history, 100);
        assertEquals(first, second, "nothing changed, so nothing about the candidate set may change");

        List<SceneDefinition> indexed = catalog.candidates(ScenePurpose.TOPIC, "work", "");
        assertEquals(ids(ConversationDirector.admission(catalog, indexed, ScenePurpose.TOPIC, "work",
                        "", first).ordinary()),
                ids(ConversationDirector.admission(catalog, indexed, ScenePurpose.TOPIC, "work",
                        "", second).ordinary()));
    }

    @Test
    void relevanceSeparatesADuePromiseFromMerelyUnresolvedContinuity() {
        PairHistory history = new PairHistory();
        history.putCommitment(new CommitmentRecord("commitment.ledger",
                CommitmentResolver.CONVERSATION_CHOICE, NarrativeValue.token("reply.yes"),
                CommitmentRecord.Party.VILLAGER, CommitmentRecord.State.PENDING, 90,
                OptionalLong.of(99), OptionalLong.empty(), Optional.empty()));
        history.putThread(thread("thread.ledger", ThreadStatus.OPEN, "commitment:commitment.ledger",
                "ledgers"));
        history.putThread(thread("thread.quarrel", ThreadStatus.RUPTURED, "", "the_argument"));
        history.putThread(thread("thread.roof", ThreadStatus.OPEN, "", "the_roof"));

        ConversationDirector.Relevance relevance = ConversationDirector.relevanceOf(history, 100);

        assertEquals(Set.of("thread.ledger"), relevance.dueThreads());
        assertEquals(Set.of("ledgers"), relevance.dueSubjects());
        assertEquals(Set.of("thread.quarrel", "thread.roof"), relevance.openThreads());
        assertEquals(Set.of("the_argument", "the_roof"), relevance.openSubjects());
        assertFalse(relevance.openThreads().contains("thread.ledger"),
                "the due class keeps a thread that is both");
    }

    // --- helpers ----------------------------------------------------------------------------------

    /** An evaluator that records what it saw, standing in for the director's gate stack. */
    private static final class Recorder implements Predicate<SceneDefinition> {
        private final Predicate<SceneDefinition> eligible;
        private final List<String> evaluated = new ArrayList<>();
        private final List<String> admitted = new ArrayList<>();

        Recorder(Predicate<SceneDefinition> eligible) {
            this.eligible = eligible;
        }

        @Override
        public boolean test(SceneDefinition scene) {
            evaluated.add(scene.id());
            if (!eligible.test(scene)) {
                return false;
            }
            admitted.add(scene.id());
            return true;
        }
    }

    private static List<String> ids(List<SceneDefinition> scenes) {
        return scenes.stream().map(SceneDefinition::id).toList();
    }

    private static List<SceneDefinition> loud(int count) {
        List<SceneDefinition> out = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            out.add(scene(String.format(Locale.ROOT, "work.loud_%03d", i), 40, "", Set.of()));
        }
        return out;
    }

    private static SceneDefinition scene(String id, int basePriority, String threadTemplate,
                                         Set<String> subjects) {
        return new SceneDefinition(id, ScenePurpose.TOPIC, "work", SceneShape.OBSERVE,
                Set.of(), Set.of(), subjects, Set.of(), Set.of(), "", Set.of(), Map.of(),
                List.of(), List.of(), Set.of(), basePriority, Set.of(), Set.of(), Set.of(),
                1, 0, "conversations.scene." + id + ".respond", id + ".open", threadTemplate, "");
    }

    private static SharedThreadRecord thread(String template, ThreadStatus status, String obligation,
                                             String subject) {
        return new SharedThreadRecord(template, "work", subject, Optional.empty(), status, "", "",
                obligation, "", PrivacyLevel.ORDINARY, 0, OptionalLong.empty(), 0, 90);
    }
}
