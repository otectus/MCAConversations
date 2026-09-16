package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.IntentBinding;
import dev.otectus.mcaconversations.chat.IntentIndex;
import dev.otectus.mcaconversations.scene.SceneCatalog;
import dev.otectus.mcaconversations.scene.SceneDefinition;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Cross-validates a staged bundle against the dialogue resources it will execute through.
 *
 * <p>The structural checks inside each {@code fromJson} answer "is this entry well formed". This
 * answers the question no single loader could: does the catalog agree with the beats, do the beats
 * agree with the scenes, and does any of it still point at a dialogue question that exists. Those
 * are precisely the failures a datapack introduces and the ones that used to surface as a dead
 * button several minutes into a conversation.
 *
 * <p><b>Feature-aware.</b> A reference into optional content is not a failure merely because the
 * owning mod is absent — a profession profile for a mod nobody installed is supported today and stays
 * supported, because its registry id simply never matches a villager. What is rejected is a reference
 * that can never resolve for anyone: an owned question name no resource declares.
 *
 * <p>Editorial and localisation checks stay in the test source set. This runs on a server with no
 * client language manager, so it may only judge things a datapack can be wrong about.
 */
public final class ContentValidation {

    private ContentValidation() {
    }

    /**
     * Every cross-section problem in the staged bundle.
     *
     * @param dialogues the prospective dialogue resources, never MCA's live map
     */
    public static List<ContentProblem> validate(ConversationContentBundle staged,
                                                DialogueResourceIndex dialogues) {
        List<ContentProblem> problems = new ArrayList<>();
        if (staged == null) {
            return problems;
        }
        DialogueResourceIndex index = dialogues == null ? DialogueResourceIndex.empty() : dialogues;
        validateTopics(staged, index, problems);
        validateBeats(staged, index, problems);
        validateIntents(staged, index, problems);
        validateScenes(staged, index, problems);
        return coalesce(problems);
    }

    /** Keeps the winning source attached to failures discovered after the individual parsers ran. */
    public static List<ContentProblem> validate(ConversationContentBundle staged, DialogueResourceIndex dialogues,
                                               Map<ContentSection, ContentSources.Staged> sources) {
        return validate(staged, dialogues).stream().map(problem -> {
            String[] path = problem.path().split("/", 3);
            if (problem.entry().isEmpty() || path.length < 2) {
                return problem;
            }
            ResourceOrigin origin = problem.origin();
            for (var section : sources.entrySet()) {
                if (!section.getKey().directory().equals(problem.directory())) {
                    continue;
                }
                for (StagedResource document : section.getValue().documents()) {
                    if (!document.json().isJsonObject()) {
                        continue;
                    }
                    var entries = document.json().getAsJsonObject().get(path[1]);
                    if (entries != null && entries.isJsonObject()
                            && entries.getAsJsonObject().has(problem.entry())) {
                        origin = document.origin(); // same sorted-last precedence as staging
                    }
                }
            }
            return new ContentProblem(problem.attempt(), problem.generation(), problem.listener(),
                    problem.directory(), origin, problem.path(), problem.line(), problem.column(),
                    problem.entry(), problem.severity(), problem.reason(), problem.message(), problem.contributors());
        }).toList();
    }

    private static void validateTopics(ConversationContentBundle staged, DialogueResourceIndex index,
                                       List<ContentProblem> problems) {
        for (TopicEntry topic : staged.topics().topics()) {
            requireQuestion(problems, index, ContentSection.CONVERSATION_CATALOG, topic.id(),
                    "/topics/" + topic.id() + "/entry/question", topic.entryQuestion(), "topic_entry_question_missing");
            requireAnswer(problems, index, ContentSection.CONVERSATION_CATALOG, topic.id(),
                    "/topics/" + topic.id() + "/entry/answer", topic.entryQuestion(), topic.entryAnswer(),
                    "topic_entry_answer_missing");
            requireQuestion(problems, index, ContentSection.CONVERSATION_CATALOG, topic.id(),
                    "/topics/" + topic.id() + "/return_question", topic.returnQuestion(),
                    "topic_return_question_missing");
        }
    }

    private static void validateBeats(ConversationContentBundle staged, DialogueResourceIndex index,
                                      List<ContentProblem> problems) {
        Map<String, String> routes = new LinkedHashMap<>();
        for (BeatContract beat : staged.beats().beats()) {
            requireQuestion(problems, index, ContentSection.CONVERSATION_BEATS, beat.id(),
                    "/beats/" + beat.id() + "/response_question", beat.responseQuestion(),
                    "beat_response_question_missing");
            // A beat's "topic" is the conversational domain it belongs to, not a catalog topic id —
            // "family" is a domain that several catalog entries sit inside. It is deliberately not
            // cross-checked against the catalog here.
            String route = beat.say() + " -> " + beat.responseQuestion();
            String previous = routes.put(route, beat.id());
            if (previous != null) {
                problems.add(ContentProblem.of(ContentSection.CONVERSATION_BEATS,
                        ResourceOrigin.unknownPack(null), "/beats/" + beat.id(), beat.id(),
                        ContentSeverity.REFUSED, "beat_route_collision",
                        "beats '" + previous + "' and '" + beat.id() + "' claim the same route " + route));
            }
        }
    }

    private static void validateIntents(ConversationContentBundle staged, DialogueResourceIndex index,
                                        List<ContentProblem> problems) {
        IntentIndex intents = staged.intents();
        for (IntentIndex.CompiledIntent compiled : intents.all()) {
            IntentBinding intent = compiled.source;
            if (intent.isSystem()) {
                continue;
            }
            requireQuestion(problems, index, ContentSection.CHAT_INTENTS, intent.id(),
                    "/intents/" + intent.id() + "/question", intent.question(), "intent_question_missing");
            requireAnswer(problems, index, ContentSection.CHAT_INTENTS, intent.id(),
                    "/intents/" + intent.id() + "/answer", intent.question(), intent.answer(),
                    "intent_answer_missing");
        }
    }

    private static void validateScenes(ConversationContentBundle staged, DialogueResourceIndex index,
                                       List<ContentProblem> problems) {
        SceneCatalog scenes = staged.scenes();
        for (SceneDefinition scene : scenes.all()) {
            requireQuestion(problems, index, ContentSection.CONVERSATION_SCENES, scene.id(),
                    "/scenes/" + scene.id() + "/question", scene.questionId(), "scene_question_missing");
            // Same as the beats: a scene's topic is the domain its purpose names, not a catalog id.
            if (scene.fallbackScene() != null && !scene.fallbackScene().isBlank()) {
                if (scene.fallbackScene().equals(scene.id())) {
                    problems.add(ContentProblem.of(ContentSection.CONVERSATION_SCENES,
                            ResourceOrigin.unknownPack(null), "/scenes/" + scene.id() + "/fallback", scene.id(),
                            ContentSeverity.REFUSED, "scene_fallback_self",
                            "scene '" + scene.id() + "' falls back to itself"));
                } else if (scenes.scene(scene.fallbackScene()).isEmpty()) {
                    problems.add(ContentProblem.of(ContentSection.CONVERSATION_SCENES,
                            ResourceOrigin.unknownPack(null), "/scenes/" + scene.id() + "/fallback", scene.id(),
                            ContentSeverity.REFUSED, "scene_fallback_missing",
                            "scene '" + scene.id() + "' falls back to '" + scene.fallbackScene()
                                    + "', which no scene declares"));
                }
            }
            if (scene.threadTemplate() != null && !scene.threadTemplate().isBlank()
                    && staged.narrative().thread(scene.threadTemplate()).isEmpty()) {
                problems.add(ContentProblem.of(ContentSection.CONVERSATION_SCENES,
                        ResourceOrigin.unknownPack(null), "/scenes/" + scene.id() + "/thread_template",
                        scene.id(), ContentSeverity.REFUSED, "scene_thread_template_missing",
                        "scene '" + scene.id() + "' opens thread template '" + scene.threadTemplate()
                                + "', which no template declares"));
            }
        }
        detectFallbackCycles(scenes, problems);
    }

    private static void detectFallbackCycles(SceneCatalog scenes, List<ContentProblem> problems) {
        for (SceneDefinition scene : scenes.all()) {
            String at = scene.fallbackScene();
            int hops = 0;
            while (at != null && !at.isBlank() && hops++ <= scenes.size()) {
                if (at.equals(scene.id())) {
                    problems.add(ContentProblem.of(ContentSection.CONVERSATION_SCENES,
                            ResourceOrigin.unknownPack(null), "/scenes/" + scene.id() + "/fallback",
                            scene.id(), ContentSeverity.REFUSED, "scene_fallback_cycle",
                            "the fallback chain from '" + scene.id() + "' returns to itself"));
                    break;
                }
                SceneDefinition next = scenes.scene(at).orElse(null);
                at = next == null ? null : next.fallbackScene();
            }
        }
    }

    private static void requireQuestion(List<ContentProblem> problems, DialogueResourceIndex index,
                                        ContentSection section, String entry, String pointer,
                                        String question, String reason) {
        if (question == null || question.isBlank() || !ConversationGuard.isOurQuestion(question)) {
            // An external question is MCA's to declare; an owned reference into it is a boundary
            // crossing that this bundle records but does not guarantee.
            return;
        }
        if (!index.declares(question)) {
            problems.add(ContentProblem.of(section, ResourceOrigin.unknownPack(null), pointer, entry,
                    ContentSeverity.REFUSED, reason,
                    "'" + entry + "' references question '" + question + "', which no dialogue resource declares"));
        }
    }

    private static void requireAnswer(List<ContentProblem> problems, DialogueResourceIndex index,
                                      ContentSection section, String entry, String pointer,
                                      String question, String answer, String reason) {
        if (question == null || answer == null || answer.isBlank()
                || !ConversationGuard.isOurQuestion(question)
                || !index.declares(question)) {
            return;
        }
        if (!index.declaresAnswer(question, answer)) {
            problems.add(ContentProblem.of(section, ResourceOrigin.unknownPack(null), pointer, entry,
                    ContentSeverity.REFUSED, reason,
                    "'" + entry + "' references answer '" + answer + "' of question '" + question
                            + "', which that question does not declare"));
        }
    }

    /** One primary failure must not surface again as every reference derived from it. */
    static List<ContentProblem> coalesce(List<ContentProblem> problems) {
        Map<String, ContentProblem> unique = new LinkedHashMap<>();
        problems.forEach(p -> unique.putIfAbsent(p.coalescingKey(), p));
        return List.copyOf(unique.values());
    }
}
