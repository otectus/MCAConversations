package dev.otectus.mcaconversations.scene;

import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.history.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/** Runs the shipped advice family's episode states through selection and continuation. */
class SceneEpisodesTest {
    private static final long TODAY = 10L;

    private static SceneDefinition scene(String name) throws Exception {
        return scene(name, true);
    }

    private static SceneDefinition scene(String name, boolean opensThread) throws Exception {
        var scenes = JsonParser.parseString(Files.readString(Path.of(
                "src/main/resources/data/mcaconversations/conversation_scenes/generated.json")))
                .getAsJsonObject().getAsJsonObject("scenes");
        String id = "topic.shared_history." + name;
        var definition = scenes.getAsJsonObject(id);
        if (!opensThread) {
            definition.getAsJsonObject("episode").remove("thread");
        }
        return SceneDefinition.fromJson(id, definition);
    }

    private static ThreadTemplate template() throws Exception {
        var threads = JsonParser.parseString(Files.readString(Path.of(
                "src/main/resources/data/mcaconversations/thread_templates/generated.json")))
                .getAsJsonObject().getAsJsonObject("threads");
        return ThreadTemplate.fromJson("shared_history.advice", threads.getAsJsonObject("shared_history.advice"));
    }

    private static EpisodeRecord episode() {
        return EpisodeRecord.opened(UUID.randomUUID(), "shared.advice", "shared_history.advice",
                EpisodeState.ACTIVE, UUID.randomUUID(), Map.of(), PrivacyLevel.ORDINARY, 48, 0L);
    }

    private static Optional<EpisodeRecord> resolve(SceneDefinition scene, PairHistory pair,
                                                  EpisodeRecord bound, EpisodeRecord other) {
        return SceneEpisodes.resolve(scene, pair,
                id -> id.equals(bound.id()) ? Optional.of(bound) : Optional.empty(),
                () -> Optional.ofNullable(other), TODAY);
    }

    @Test
    void theShippedAdviceFamilyCanReachItsFollowupAndRememberedScenes() throws Exception {
        EpisodeRecord active = episode();
        PairHistory pair = new PairHistory();
        pair.putThread(template().open(Optional.of(active.id()), 0L));
        SceneDefinition starter = scene("the_view_i_wanted");
        SceneDefinition followup = scene("what_i_did_with_it");
        SceneDefinition repair = scene("i_took_it_the_wrong_way");
        SceneDefinition memory = scene("the_one_we_both_remember");
        assertEquals(active.id(), resolve(starter, pair, active, active).orElseThrow().id());
        assertTrue(resolve(followup, pair, active, active).isEmpty());

        EpisodeRecord succeeded = active.transitioned(EpisodeState.SUCCEEDED, 1L);
        assertFalse(succeeded.isLive(TODAY), "the old live-only lookup lost every follow-up here");
        assertEquals(succeeded.id(), resolve(followup, pair, succeeded, null).orElseThrow().id());
        assertTrue(resolve(starter, pair, succeeded, episode()).isEmpty(),
                "a new active copy must not replay the opening of this pair's completed situation");

        var catalog = SceneCatalog.build(List.of(starter, followup, repair, memory));
        var narrative = NarrativeCatalog.build(List.of(), List.of(template()), List.of());
        var continuation = ContinuationResolver.resolve(pair, narrative, catalog,
                id -> Optional.of(succeeded), TODAY);
        assertEquals(1, continuation.size());
        assertEquals(List.of(followup.id(), repair.id()), continuation.get(0).resumeScenes());

        EpisodeRecord remembered = succeeded.transitioned(EpisodeState.REMEMBERED, 2L);
        pair.putThread(pair.thread("shared_history.advice").orElseThrow()
                .withStatus(ThreadStatus.RESOLVED, 2L));
        assertEquals(remembered.id(), resolve(memory, pair, remembered, null).orElseThrow().id());
        assertTrue(ContinuationResolver.resolve(pair, narrative, catalog,
                id -> Optional.of(remembered), TODAY).isEmpty(), "settled threads are not reopened");
    }

    @Test
    void aResumeSceneKeepsTheBoundEpisodeWithoutReopeningItsThread() throws Exception {
        EpisodeRecord completed = episode().transitioned(EpisodeState.SUCCEEDED, 1L);
        PairHistory pair = new PairHistory();
        pair.putThread(template().open(Optional.of(completed.id()), 0L));
        SceneDefinition followup = scene("what_i_did_with_it", false);
        assertFalse(followup.opensThread());
        var continuations = ContinuationResolver.resolve(pair,
                NarrativeCatalog.build(List.of(), List.of(template()), List.of()),
                SceneCatalog.build(List.of(followup)), id -> Optional.of(completed), TODAY);
        assertEquals(1, continuations.size());
        var resolved = SceneEpisodes.resolve(followup, pair, continuations,
                id -> id.equals(completed.id()) ? Optional.of(completed) : Optional.empty(),
                Optional::empty, TODAY);
        assertEquals(completed.id(), resolved.orElseThrow().id());
    }

    @Test
    void anotherPlayerCannotInheritTheAdviceCallback() throws Exception {
        EpisodeRecord completed = episode().transitioned(EpisodeState.SUCCEEDED, 1L);
        assertTrue(resolve(scene("what_i_did_with_it"), new PairHistory(), completed, completed).isEmpty());
    }

    @Test
    void aMissingBoundEpisodeCannotBeReplacedByAnotherPlayersCompletedEpisode() throws Exception {
        PairHistory pair = new PairHistory();
        pair.putThread(template().open(Optional.of(UUID.randomUUID()), 0L));
        EpisodeRecord completed = episode().transitioned(EpisodeState.SUCCEEDED, 1L);
        assertTrue(resolve(scene("what_i_did_with_it"), pair, completed, completed).isEmpty());
    }
}
