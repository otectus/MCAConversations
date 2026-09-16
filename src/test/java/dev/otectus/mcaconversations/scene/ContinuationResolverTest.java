package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.conversation.SceneShape;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.EpisodeState;
import dev.otectus.mcaconversations.history.NarrativeCatalog;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.PrivacyLevel;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.ThreadStatus;
import dev.otectus.mcaconversations.history.ThreadTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Picking a subject back up safely (spec §8.4, §11.5).
 *
 * <p>The failure these pin is the one a resume engine invites: offering to continue something the
 * content no longer supports, and then having to invent the middle of it. Every case here either
 * lands on an authored scene or declines — there is no third answer, and nothing infers that a check
 * was passed, a promise was kept, or an event was witnessed.
 */
class ContinuationResolverTest {

    private static final long TODAY = 100L;
    private static final UUID EPISODE = UUID.nameUUIDFromBytes("episode.ledger".getBytes());

    @Test
    @DisplayName("a ready thread with an authored way back is continuable")
    void aReadyThreadIsContinuable() {
        PairHistory pair = pairWith(thread("thread.ledger", "ledgers", "", 0L));

        List<ContinuationResolver.Continuation> found = ContinuationResolver.resolve(pair,
                narrative(template("thread.ledger", "ledgers", List.of("work.the_ledger_again"), "")),
                scenes("work.the_ledger_again"), ContinuationResolver.NO_EPISODES, TODAY);

        assertEquals(1, found.size());
        assertEquals("work", found.get(0).topic());
        assertEquals(List.of("work.the_ledger_again"), found.get(0).resumeScenes());
        assertEquals(List.of("thread.ledger"),
                ContinuationResolver.continuable(pair,
                        narrative(template("thread.ledger", "ledgers",
                                List.of("work.the_ledger_again"), "")),
                        scenes("work.the_ledger_again"), ContinuationResolver.NO_EPISODES, TODAY)
                        .stream().map(SharedThreadRecord::templateId).toList());
    }

    @Test
    @DisplayName("a thread whose template left with a datapack is not continued")
    void aMissingTemplateDeclines() {
        PairHistory pair = pairWith(thread("thread.ledger", "ledgers", "", 0L));

        assertTrue(ContinuationResolver.resolve(pair, NarrativeCatalog.EMPTY,
                scenes("work.the_ledger_again"), ContinuationResolver.NO_EPISODES, TODAY).isEmpty());
    }

    @Test
    @DisplayName("a thread whose authored way back is no longer in the catalog is not continued")
    void aMissingResumeSceneDeclines() {
        PairHistory pair = pairWith(thread("thread.ledger", "ledgers", "", 0L));

        assertTrue(ContinuationResolver.resolve(pair,
                narrative(template("thread.ledger", "ledgers", List.of("work.the_ledger_again"), "")),
                scenes("work.something_else"), ContinuationResolver.NO_EPISODES, TODAY).isEmpty(),
                "an offer with nowhere to land is not an offer");
    }

    @Test
    @DisplayName("a template that now describes another subject invalidates the continuation")
    void aRedescribedTemplateDeclines() {
        PairHistory pair = pairWith(thread("thread.ledger", "ledgers", "", 0L));

        assertTrue(ContinuationResolver.resolve(pair,
                narrative(template("thread.ledger", "the_roof", List.of("work.the_ledger_again"), "")),
                scenes("work.the_ledger_again"), ContinuationResolver.NO_EPISODES, TODAY).isEmpty(),
                "the frame it was saved under has stopped meaning what it meant");
    }

    @Test
    @DisplayName("a cooldown, a lapse, or a spent resume budget all decline plainly")
    void changedCircumstancesDecline() {
        NarrativeCatalog narrative =
                narrative(template("thread.ledger", "ledgers", List.of("work.the_ledger_again"), ""));
        SceneCatalog catalog = scenes("work.the_ledger_again");

        assertTrue(ContinuationResolver.resolve(pairWith(thread("thread.ledger", "ledgers", "",
                        TODAY + 3L)), narrative, catalog, ContinuationResolver.NO_EPISODES, TODAY).isEmpty(),
                "not before the cooldown the author asked for");
        SharedThreadRecord lapsed = thread("thread.ledger", "ledgers", "", 0L)
                .withSchedule(0L, OptionalLong.of(TODAY - 1L));
        assertTrue(ContinuationResolver.resolve(pairWith(lapsed), narrative, catalog,
                ContinuationResolver.NO_EPISODES, TODAY).isEmpty());
        SharedThreadRecord exhausted = new SharedThreadRecord("thread.ledger", "work", "ledgers",
                Optional.empty(), ThreadStatus.OPEN, "", "", "", "", PrivacyLevel.ORDINARY, 0L,
                OptionalLong.empty(), SharedThreadRecord.MAX_RESUMES, 90L);
        assertTrue(ContinuationResolver.resolve(pairWith(exhausted), narrative, catalog,
                ContinuationResolver.NO_EPISODES, TODAY).isEmpty(),
                "past its resume budget a thread is repeating itself, not continuing");
    }

    @Test
    @DisplayName("a thread bound to an episode the villager no longer has is not continued")
    void aGoneEpisodeDeclines() {
        SharedThreadRecord bound = thread("thread.ledger", "ledgers", "", 0L).withEpisode(EPISODE);
        NarrativeCatalog narrative = narrative(template("thread.ledger", "ledgers",
                List.of("work.the_ledger_again"), "work.ledger"));
        SceneCatalog catalog = scenes("work.the_ledger_again");

        assertTrue(ContinuationResolver.resolve(pairWith(bound), narrative, catalog,
                        ContinuationResolver.NO_EPISODES, TODAY).isEmpty(),
                "the villager cannot pick up what they can no longer observe");
        assertFalse(ContinuationResolver.resolve(pairWith(bound), narrative, catalog,
                id -> Optional.of(episode()), TODAY).isEmpty(),
                "and continues it while the episode is still live");
    }

    @Test
    @DisplayName("the scene already played is a last resort, never the way back offered first")
    void thePlayedSceneIsNotReplayed() {
        SharedThreadRecord played = thread("thread.ledger", "ledgers", "", 0L)
                .played("work.the_ledger_again", "engaged", "empathy", 90L, 0L);
        NarrativeCatalog narrative = narrative(template("thread.ledger", "ledgers",
                List.of("work.the_ledger_again", "work.the_ledger_later"), ""));

        List<String> order = ContinuationResolver.resolve(pairWith(played), narrative,
                        scenes("work.the_ledger_again", "work.the_ledger_later"),
                        ContinuationResolver.NO_EPISODES, TODAY)
                .get(0).resumeScenes();

        assertEquals(List.of("work.the_ledger_later", "work.the_ledger_again"), order);
    }

    @Test
    @DisplayName("something outstanding is picked up before something merely unfinished")
    void obligationsComeFirst() {
        PairHistory pair = new PairHistory();
        pair.putThread(thread("thread.roof", "the_roof", "", 0L));
        pair.putThread(thread("thread.ledger", "ledgers", "commitment:commitment.ledger", 0L));

        List<ContinuationResolver.Continuation> found = ContinuationResolver.resolve(pair,
                narrative(template("thread.roof", "the_roof", List.of("work.the_roof_again"), ""),
                        template("thread.ledger", "ledgers", List.of("work.the_ledger_again"), "")),
                scenes("work.the_roof_again", "work.the_ledger_again"),
                ContinuationResolver.NO_EPISODES, TODAY);

        assertEquals(List.of("thread.ledger", "thread.roof"),
                found.stream().map(c -> c.thread().templateId()).toList());
        assertEquals(Set.of("work.the_ledger_again", "work.the_roof_again"),
                ContinuationResolver.resumeSceneIds(found));
    }

    @Test
    @DisplayName("the authored way back reaches scoring even though no index points at it")
    void theResumeSceneIsAdmittedByTheDirector() {
        // The resume scene neither opens the thread nor names its subject, so the template and
        // subject indexes cannot find it: without the continuation class it is never a candidate.
        SceneCatalog catalog = SceneCatalog.build(List.of(
                scene("work.the_ledger_again", 1, "", Set.of()),
                scene("work.opens_it", 40, "thread.ledger", Set.of())));
        PairHistory pair = pairWith(thread("thread.ledger", "ledgers", "", 0L));

        ConversationDirector.Relevance relevance = ConversationDirector.relevanceOf(pair,
                narrative(template("thread.ledger", "ledgers", List.of("work.the_ledger_again"), "")),
                catalog, ContinuationResolver.NO_EPISODES, TODAY);
        assertEquals(Set.of("work.the_ledger_again"), relevance.resumeScenes());

        ConversationDirector.Admission admission = ConversationDirector.admission(catalog,
                catalog.candidates(ScenePurpose.TOPIC, "work", ""), ScenePurpose.TOPIC, "work", "",
                relevance);

        assertEquals(List.of("work.the_ledger_again", "work.opens_it"),
                admission.continuity().stream().map(SceneDefinition::id).toList());
    }

    // --- helpers ----------------------------------------------------------------------------------

    private static PairHistory pairWith(SharedThreadRecord thread) {
        PairHistory pair = new PairHistory();
        pair.putThread(thread);
        return pair;
    }

    private static SharedThreadRecord thread(String template, String subject, String obligation,
                                             long nextEligibleDay) {
        return new SharedThreadRecord(template, "work", subject, Optional.empty(), ThreadStatus.OPEN,
                "", "", obligation, "", PrivacyLevel.ORDINARY, nextEligibleDay, OptionalLong.empty(),
                0, 90L);
    }

    private static ThreadTemplate template(String id, String subject, List<String> resumeScenes,
                                           String episodeKind) {
        return new ThreadTemplate(id, "work", subject, resumeScenes, 0L, OptionalLong.empty(),
                PrivacyLevel.ORDINARY, episodeKind, "");
    }

    private static NarrativeCatalog narrative(ThreadTemplate... templates) {
        return NarrativeCatalog.build(List.of(), List.of(templates), List.of());
    }

    private static EpisodeRecord episode() {
        return EpisodeRecord.opened(EPISODE, "work.ledger", "ledgers", EpisodeState.ACTIVE,
                UUID.nameUUIDFromBytes("villager".getBytes()), Map.of(), PrivacyLevel.ORDINARY, 50,
                TODAY - 4L);
    }

    private static SceneCatalog scenes(String... ids) {
        List<SceneDefinition> out = new java.util.ArrayList<>();
        for (String id : ids) {
            out.add(scene(id, 10, "", Set.of()));
        }
        return SceneCatalog.build(out);
    }

    private static SceneDefinition scene(String id, int basePriority, String threadTemplate,
                                         Set<String> subjects) {
        return new SceneDefinition(id, ScenePurpose.TOPIC, "work", SceneShape.OBSERVE,
                Set.of(), Set.of(), subjects, Set.of(), Set.of(), "", Set.of(), Map.of(),
                List.of(), List.of(), Set.of(), basePriority, Set.of(), Set.of(), Set.of(),
                1, 0, "conversations.scene." + id + ".respond", id + ".open", threadTemplate, "");
    }
}
