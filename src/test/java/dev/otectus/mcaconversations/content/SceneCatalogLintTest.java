package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.BeatContract;
import dev.otectus.mcaconversations.conversation.DiscourseSpec;
import dev.otectus.mcaconversations.conversation.Obligation;
import dev.otectus.mcaconversations.conversation.ReplyContract;
import dev.otectus.mcaconversations.history.CommitmentResolver;
import dev.otectus.mcaconversations.history.CommitmentTemplate;
import dev.otectus.mcaconversations.history.EpisodeTemplate;
import dev.otectus.mcaconversations.history.NarrativeCatalog;
import dev.otectus.mcaconversations.history.ThreadTemplate;
import dev.otectus.mcaconversations.scene.FallbackChain;
import dev.otectus.mcaconversations.scene.SceneCatalog;
import dev.otectus.mcaconversations.scene.SceneDefinition;
import dev.otectus.mcaconversations.scene.SlotType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The coherence lints for the dynamic layer, run over the shipped corpus (spec §10.3, §21.1).
 *
 * <p>These are the checks that make a scene safe to select. Every one of them corresponds to a way a
 * dynamic route can be wrong in a manner the v1 lints cannot see:
 *
 * <ul>
 *   <li>a scene naming a route nothing declares — it would be chosen and then say nothing;</li>
 *   <li>a scene whose required slots the episode it binds cannot supply — it could never be selected,
 *       so it is dead content that reads as shipped;</li>
 *   <li>a reply presupposing a referent no inbound beat introduced — the "how is she?" problem, where
 *       the button names something the line above it never mentioned;</li>
 *   <li>a reply that answers no obligation the page made relevant — the page of three comments after
 *       a direct question;</li>
 *   <li>a promise with no registered resolver — the untrackable-commitment failure.</li>
 * </ul>
 */
class SceneCatalogLintTest {

    private static final Path SCENES =
            Path.of("src/main/resources/data/mcaconversations/conversation_scenes");
    private static final Path EPISODES =
            Path.of("src/main/resources/data/mcaconversations/episode_templates");
    private static final Path THREADS =
            Path.of("src/main/resources/data/mcaconversations/thread_templates");
    private static final Path COMMITMENTS =
            Path.of("src/main/resources/data/mcaconversations/commitment_templates");

    private static SceneCatalog scenes;
    private static NarrativeCatalog narrative;

    @BeforeAll
    static void load() {
        List<SceneDefinition> parsed = new ArrayList<>();
        forEachEntry(SCENES, "scenes", (id, json) -> parsed.add(SceneDefinition.fromJson(id, json)));
        scenes = SceneCatalog.build(parsed);

        List<EpisodeTemplate> episodes = new ArrayList<>();
        forEachEntry(EPISODES, "episodes", (id, json) -> episodes.add(EpisodeTemplate.fromJson(id, json)));
        List<ThreadTemplate> threads = new ArrayList<>();
        forEachEntry(THREADS, "threads", (id, json) -> threads.add(ThreadTemplate.fromJson(id, json)));
        List<CommitmentTemplate> commitments = new ArrayList<>();
        forEachEntry(COMMITMENTS, "commitments",
                (id, json) -> commitments.add(CommitmentTemplate.fromJson(id, json)));
        narrative = NarrativeCatalog.build(episodes, threads, commitments);
    }

    @Test
    void everySceneRoutesToADeclaredQuestionAndBeat() {
        List<String> problems = new ArrayList<>();
        Set<String> questions = ContentFixture.graph().questionIds();
        for (SceneDefinition scene : scenes.all()) {
            if (!questions.contains(scene.questionId())) {
                problems.add(scene.id() + " routes to question '" + scene.questionId()
                        + "', which no dialogue file declares");
            }
            if (ContentFixture.catalog().beat(scene.openingBeatId()).isEmpty()) {
                problems.add(scene.id() + " opens with beat '" + scene.openingBeatId()
                        + "', which no beat catalog declares");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyOpeningBeatRespondsWithTheQuestionItsSceneNames() {
        // A scene whose beat opens a different page than the scene routes to would show the player
        // one line and a page of answers to a different one.
        List<String> problems = new ArrayList<>();
        for (SceneDefinition scene : scenes.all()) {
            ContentFixture.catalog().beat(scene.openingBeatId()).ifPresent(beat -> {
                if (!beat.responseQuestion().equals(scene.questionId())) {
                    problems.add(scene.id() + " routes to '" + scene.questionId() + "' but its beat '"
                            + beat.id() + "' responds with '" + beat.responseQuestion() + "'");
                }
            });
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everySceneFallbackResolvesAndIsNotItself() {
        assertEquals(scenes.danglingReferences());
    }

    @Test
    void everyNarrativeTemplateReferenceResolves() {
        assertEquals(narrative.danglingReferences());
    }

    @Test
    void everySceneBindingAnEpisodeCanActuallyBindItsSlots() {
        List<String> problems = new ArrayList<>();
        for (SceneDefinition scene : scenes.all()) {
            if (!scene.needsEpisode()) {
                continue;
            }
            EpisodeTemplate template = narrative.episode(scene.episodeKind()).orElse(null);
            if (template == null) {
                problems.add(scene.id() + " binds episode kind '" + scene.episodeKind()
                        + "', which no template declares");
                continue;
            }
            for (Map.Entry<String, SlotType> slot : scene.requiredSlots().entrySet()) {
                boolean fromEpisode = template.requiredSlots().contains(slot.getKey())
                        || template.slotOptions().containsKey(slot.getKey());
                boolean fromContext = CONTEXT_SLOTS.contains(slot.getKey());
                if (!fromEpisode && !fromContext) {
                    problems.add(scene.id() + " requires slot '" + slot.getKey()
                            + "', which neither episode '" + template.kind()
                            + "' nor the context snapshot can supply — the scene could never be selected");
                }
            }
            for (var state : scene.episodeStates()) {
                if (!template.states().contains(state)) {
                    problems.add(scene.id() + " waits for episode state '" + state.key()
                            + "', which '" + template.kind() + "' never enters");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /** The slot names {@code SlotBinder} can answer from the context snapshot rather than an episode. */
    private static final Set<String> CONTEXT_SLOTS = Set.of(
            "worksite", "location", "village", "season", "weather", "time_band", "profession",
            "today", "chore");

    @Test
    void everyEpisodeStateAScenePlaysInIsReachable() {
        // A scene gated on a state its template can never reach is unreachable content that still
        // costs lang keys and shows up in coverage.
        List<String> problems = new ArrayList<>();
        for (SceneDefinition scene : scenes.all()) {
            if (!scene.needsEpisode()) {
                continue;
            }
            EpisodeTemplate template = narrative.episode(scene.episodeKind()).orElseThrow();
            for (var target : scene.episodeStates()) {
                if (target == template.initialState()) {
                    continue;
                }
                boolean reachable = template.states().stream()
                        .anyMatch(from -> from != target && template.permits(from, target));
                if (!reachable) {
                    problems.add(scene.id() + " needs state '" + target.key()
                            + "', which no declared transition of '" + template.kind() + "' reaches");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyReplyReferentIsIntroducedByEveryInboundBeat() {
        List<String> problems = new ArrayList<>();
        forEachV2Reply((reply, inbound) -> {
            for (String alias : reply.move().usesReferents()) {
                for (BeatContract beat : inbound) {
                    if (!beat.frame().introduces(alias)) {
                        problems.add(reply.key() + " presupposes referent '" + alias
                                + "', which beat '" + beat.id() + "' never introduces");
                    }
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyNonExitReplyAnswersSomethingThePageMadeRelevant() {
        List<String> problems = new ArrayList<>();
        forEachV2Reply((reply, inbound) -> {
            if (reply.exit() || reply.move().move().isPresent()) {
                return;
            }
            for (BeatContract beat : inbound) {
                DiscourseSpec frame = beat.frame();
                if (!frame.isDeclared()) {
                    continue;
                }
                if (!reply.isResponsiveTo(frame.obligations())) {
                    problems.add(reply.key() + " fulfils " + reply.move().answers()
                            + " but beat '" + beat.id() + "' makes " + frame.obligations()
                            + " relevant — nothing on that page answers the line above it");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyPageThatDemandsAnAnswerHasOne() {
        // The §20.4 rule: if the villager asks, at least one button must actually answer.
        List<String> problems = new ArrayList<>();
        Map<String, List<BeatContract>> byQuestion = new TreeMap<>();
        for (BeatContract beat : ContentFixture.catalog().beats()) {
            if (beat.frame().isDeclared() && beat.frame().demandsFulfilment()) {
                byQuestion.computeIfAbsent(beat.responseQuestion(), key -> new ArrayList<>()).add(beat);
            }
        }
        byQuestion.forEach((question, beats) -> {
            for (BeatContract beat : beats) {
                boolean answered = false;
                for (ReplyContract reply : ContentFixture.catalog().repliesFor(question)) {
                    if (reply.exit()) {
                        continue;
                    }
                    for (Obligation obligation : beat.frame().obligations()) {
                        if (obligation.requiresFulfilment() && reply.move().fulfils(obligation)) {
                            answered = true;
                            break;
                        }
                    }
                    if (answered) {
                        break;
                    }
                }
                if (!answered) {
                    problems.add("beat '" + beat.id() + "' makes " + beat.frame().obligations()
                            + " relevant, but no reply on '" + question + "' fulfils any of them");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void noPromiseShipsWithoutARegisteredResolver() {
        List<String> problems = new ArrayList<>();
        for (ReplyContract reply : ContentFixture.catalog().replies()) {
            if (!reply.move().hasCommitment()) {
                continue;
            }
            Optional<CommitmentTemplate> template = narrative.commitment(reply.move().commitment());
            if (template.isEmpty()) {
                problems.add(reply.key() + " promises '" + reply.move().commitment()
                        + "', which no commitment template declares");
                continue;
            }
            CommitmentResolver resolver = template.get().resolver();
            if (resolver.isJudgeable() && template.get().target().isEmpty()
                    && resolver != CommitmentResolver.VISIT_AFTER_DAY) {
                problems.add(reply.key() + " promises '" + template.get().id()
                        + "', whose resolver names nothing that could satisfy it");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void aTemporalFrameNeverContradictsTheEpisodeStatesItPlaysIn() {
        List<String> problems = new ArrayList<>();
        for (BeatContract beat : ContentFixture.catalog().beats()) {
            DiscourseSpec frame = beat.frame();
            if (!frame.isDeclared() || frame.episodeStates().isEmpty()) {
                continue;
            }
            for (var state : frame.episodeStates()) {
                if (!frame.temporal().fits(state, frame.predicate().orElse(null))) {
                    problems.add("beat '" + beat.id() + "' is " + frame.temporal().key()
                            + " tense but plays in episode state '" + state.key() + "'");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void noBundledSceneIsDroppedByTheIndexBound() {
        // §21.6 bounds an index lookup at 128 candidates, and through 1.4.0 that bound was applied to
        // a purpose/topic bucket holding 256 work scenes: half the shipped corpus was discarded before
        // eligibility ran. This asserts the *raw* leaf sizes, because the live index is exactly the
        // evidence a truncation destroys — the old assertion read the truncated buckets and passed.
        assertFalse(scenes.isEmpty(), "no scenes were loaded, so none of the above proved anything");
        assertTrue(scenes.truncations().isEmpty(), String.join("\n", scenes.truncations()));

        List<String> problems = new ArrayList<>();
        scenes.rawBucketSizes().forEach((key, size) -> {
            if (size > SceneCatalog.MAX_INDEXED) {
                problems.add(key + " holds " + size + " scenes, over the " + SceneCatalog.MAX_INDEXED
                        + " indexed bound");
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyBundledSceneSurvivesTheIndexLeafItIsFiledUnder() {
        // The reachability half of the same fix: a scene the index cannot return is content that
        // reads as shipped and can never be selected, whatever the bucket sizes say.
        List<String> problems = new ArrayList<>();
        for (SceneDefinition scene : scenes.all()) {
            for (String profession : scene.professions().isEmpty()
                    ? Set.of(SceneCatalog.ANY_PROFESSION) : scene.professions()) {
                List<SceneDefinition> candidates =
                        scenes.candidates(scene.purpose(), scene.topic(), profession);
                if (candidates.stream().noneMatch(other -> other.id().equals(scene.id()))) {
                    problems.add(scene.id() + " is unreachable: a lookup for " + scene.indexKey()
                            + " as '" + profession + "' returns " + candidates.size()
                            + " scene(s), and none of them is this one");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyProfessionThatOwnsScenesCanReachThem() {
        // The failure this is named for: eighteen professions owned work scenes and could select none
        // of them, because every one of theirs sat past the truncation boundary.
        Map<String, List<String>> owned = new TreeMap<>();
        for (SceneDefinition scene : scenes.all()) {
            for (String profession : scene.professions()) {
                owned.computeIfAbsent(profession, key -> new ArrayList<>()).add(scene.id());
            }
        }
        assertFalse(owned.isEmpty(), "no scene names a profession, so this proved nothing");

        List<String> problems = new ArrayList<>();
        owned.forEach((profession, ids) -> {
            Set<String> reachable = new java.util.TreeSet<>();
            for (SceneDefinition scene : scenes.all()) {
                scenes.candidates(scene.purpose(), scene.topic(), profession)
                        .forEach(candidate -> reachable.add(candidate.id()));
            }
            List<String> lost = ids.stream().filter(id -> !reachable.contains(id)).toList();
            if (!lost.isEmpty()) {
                problems.add(profession + " owns " + ids.size() + " scene(s) and cannot reach "
                        + lost.size() + " of them: " + lost);
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyFallbackDegradesToARouteTheSameConversationCouldTake() {
        // 1.4.1 makes `fallback` live: the director follows it when the preferred scene cannot bind.
        // That turns a previously cosmetic property into runtime correctness. The target existing,
        // matching purpose/topic and not closing a loop is checked by danglingReferences above; what
        // is checked here is that the chain the director will actually walk is not empty.
        List<String> problems = new ArrayList<>();
        for (SceneDefinition scene : scenes.all()) {
            if (!scene.hasFallback()) {
                continue;
            }
            List<SceneDefinition> chain = FallbackChain.from(scenes, scene);
            if (chain.isEmpty()) {
                problems.add(scene.id() + " declares fallback '" + scene.fallbackScene()
                        + "' but resolves to no chain at all");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    // --- helpers ----------------------------------------------------------------------------------

    private static void forEachV2Reply(java.util.function.BiConsumer<ReplyContract, List<BeatContract>> action) {
        for (ReplyContract reply : ContentFixture.catalog().replies()) {
            if (!reply.hasMove()) {
                continue;
            }
            List<BeatContract> inbound = new ArrayList<>();
            for (BeatContract beat : ContentFixture.catalog().beats()) {
                if (beat.responseQuestion().equals(reply.question()) && reply.accepts(beat)) {
                    inbound.add(beat);
                }
            }
            action.accept(reply, inbound);
        }
    }

    private static void assertEquals(List<String> problems) {
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    private interface EntryReader {
        void read(String id, JsonObject json);
    }

    private static void forEachEntry(Path directory, String section, EntryReader reader) {
        if (!Files.isDirectory(directory)) {
            return;
        }
        try (var stream = Files.list(directory)) {
            for (Path file : stream.filter(p -> p.toString().endsWith(".json")).sorted().toList()) {
                JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                if (!root.has(section) || !root.get(section).isJsonObject()) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject(section).entrySet()) {
                    if (entry.getKey().startsWith("_") || !entry.getValue().isJsonObject()) {
                        continue;
                    }
                    JsonObject body = entry.getValue().getAsJsonObject();
                    body.remove("_comment");
                    reader.read(entry.getKey(), body);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
