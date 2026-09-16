package dev.otectus.mcaconversations.conversation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A read-only model of the effective {@code dialogues/} resources, built during this mod's own
 * preparation pass so a reload can be validated <em>before</em> MCA applies it.
 *
 * <p>Half of this mod's shipped content lives in a directory MCA's listener owns. Without this index
 * the coordinator could only validate the catalogs and hope the question names they reference
 * survived; with it, a pack that repoints an owned answer at a question that does not exist is a
 * rejected reload rather than a dead button.
 *
 * <p><b>This never consults MCA.</b> It reads the resource manager, applies MCA's documented key rule
 * — the file's basename after the last {@code /}, with the namespace and every directory prefix
 * discarded — and models the merge and priority sort. It does not call {@code getQuestion}, so it
 * cannot be contaminated by the incoming instance it is validating against.
 *
 * <p>Only names {@link ConversationGuard#isOurQuestion} accepts are fully validated. External
 * questions are indexed just far enough to resolve an outbound reference; a malformed dialogue file
 * belonging to another mod is another mod's problem and never rejects this bundle.
 */
public final class DialogueResourceIndex {

    /** MCA's own directory. This mod reads it; it never registers a listener for it. */
    public static final String DIRECTORY = "dialogues";

    private static final Gson GSON = new Gson();
    private static final DialogueResourceIndex EMPTY =
            new DialogueResourceIndex(Map.of(), Set.of(), List.of());

    private final Map<String, IndexedQuestion> questions;
    private final Set<String> externalExits;
    private final List<ContentProblem> problems;

    private DialogueResourceIndex(Map<String, IndexedQuestion> questions, Set<String> externalExits,
                                  List<ContentProblem> problems) {
        this.questions = Map.copyOf(questions);
        this.externalExits = Set.copyOf(externalExits);
        this.problems = List.copyOf(problems);
    }

    /** The index of a world with no dialogue resources at all. */
    public static DialogueResourceIndex empty() {
        return EMPTY;
    }

    /** Every question name the effective resources declare, owned and external alike. */
    public Map<String, IndexedQuestion> questions() {
        return questions;
    }

    /** Owned question names, which are the ones this bundle guarantees. */
    public List<String> ownedNames() {
        return questions.keySet().stream().filter(ConversationGuard::isOurQuestion).sorted().toList();
    }

    /**
     * Question names an owned answer routes to that are <em>not</em> owned — {@code main} and the rest
     * of MCA's tree. Recorded so the boundary is visible: retention does not cover them, and an owned
     * action entering one of them runs against whatever content is live at that moment.
     */
    public Set<String> externalExits() {
        return externalExits;
    }

    /** Everything that went wrong reading or cross-checking the directory. */
    public List<ContentProblem> problems() {
        return problems;
    }

    public boolean fatal() {
        return problems.stream().anyMatch(p -> p.severity().fatal());
    }

    /**
     * Reads every effective {@code dialogues/**.json} across namespaces.
     *
     * <p>Namespace and directory are discarded exactly as MCA discards them, so
     * {@code mcaconversations:dialogues/a/x.json} and {@code mca:dialogues/x.json} are recognised as
     * contributing to the same question {@code x} — which is the case that makes ordering matter.
     */
    public static DialogueResourceIndex read(ResourceManager manager) {
        if (manager == null) {
            return EMPTY;
        }
        List<ContentProblem> problems = new ArrayList<>();
        Map<ResourceLocation, Resource> effective = new TreeMap<>(ResourceLocation::compareTo);
        try {
            effective.putAll(manager.listResources(DIRECTORY, path -> path.getPath().endsWith(".json")));
        } catch (Throwable t) {
            problems.add(dialogueProblem(ResourceOrigin.unknownPack(null), "", ContentSeverity.REFUSED,
                    "dialogue_directory_unreadable", String.valueOf(t)));
            return new DialogueResourceIndex(Map.of(), Set.of(), problems);
        }

        Map<String, Draft> drafts = new LinkedHashMap<>();
        for (Map.Entry<ResourceLocation, Resource> entry : effective.entrySet()) {
            ResourceLocation file = entry.getKey();
            Resource resource = entry.getValue();
            String key = keyOf(file);
            boolean owned = ConversationGuard.isOurQuestion(key);
            ResourceOrigin origin = new ResourceOrigin(file, resource.sourcePackId(), file.getPath());
            JsonObject root;
            try (BufferedReader reader = resource.openAsReader()) {
                JsonElement json = GsonHelper.fromJson(GSON, reader, JsonElement.class);
                root = json != null && json.isJsonObject() ? json.getAsJsonObject() : null;
            } catch (JsonParseException e) {
                if (owned) {
                    problems.add(ContentProblem.ofSyntax(null, origin, key, ContentSeverity.REFUSED,
                            "owned_dialogue_malformed", e));
                }
                continue;
            } catch (Throwable t) {
                if (owned) {
                    problems.add(dialogueProblem(origin, key, ContentSeverity.REFUSED,
                            "owned_dialogue_unreadable", String.valueOf(t)));
                }
                continue;
            }
            if (root == null || !root.has("answers") || !root.get("answers").isJsonArray()) {
                // MCA's own gate: a file without an "answers" array is warned about and not loaded,
                // so it contributes nothing and is only a failure when we were relying on it.
                if (owned) {
                    problems.add(dialogueProblem(origin, key, ContentSeverity.REFUSED,
                            "owned_dialogue_not_a_dialogue",
                            "an owned dialogue file must carry an 'answers' array"));
                }
                continue;
            }
            Draft draft = drafts.computeIfAbsent(key, Draft::new);
            try {
                draft.absorb(origin, root);
            } catch (Throwable t) {
                if (owned) {
                    problems.add(ContentProblem.ofSyntax(null, origin, key, ContentSeverity.REFUSED,
                            "owned_dialogue_entry_malformed", t));
                }
            }
        }

        Map<String, IndexedQuestion> questions = new LinkedHashMap<>();
        drafts.forEach((key, draft) -> questions.put(key, draft.freeze()));

        Set<String> exits = new LinkedHashSet<>();
        for (IndexedQuestion question : questions.values()) {
            if (!ConversationGuard.isOurQuestion(question.name())) {
                continue;
            }
            if (question.ambiguousMerge()) {
                // MCA merges on a HashMap iteration order this mod does not control, so two distinct
                // resources contributing to one owned question have no defined answer order. Recorded
                // here; the coordinator reconciles it against MCA's actual result before it commits.
                problems.add(dialogueProblem(question.sources().get(0), question.name(),
                        ContentSeverity.INFO, "owned_question_merge_order_undefined",
                        question.sources().size() + " resources contribute to '" + question.name() + "'")
                        .withContributors(question.sources()));
            }
            for (IndexedAnswer answer : question.answers()) {
                if (!answer.hasResults()) {
                    problems.add(dialogueProblem(question.sources().get(0), question.name(),
                            ContentSeverity.REFUSED, "owned_answer_without_results",
                            "answer '" + answer.name() + "' of '" + question.name() + "' has no results"));
                }
                for (String next : answer.next()) {
                    if (!ConversationGuard.isOurQuestion(next)) {
                        exits.add(next);
                        continue;
                    }
                    if (!questions.containsKey(next)) {
                        problems.add(dialogueProblem(question.sources().get(0), question.name(),
                                ContentSeverity.REFUSED, "owned_next_dangling",
                                "answer '" + answer.name() + "' of '" + question.name()
                                        + "' routes to '" + next + "', which no resource declares"));
                    }
                }
            }
        }
        return new DialogueResourceIndex(questions, exits, coalesce(problems));
    }

    /** True when this index declares the named question. */
    public boolean declares(String name) {
        return name != null && questions.containsKey(name);
    }

    /** True when the named owned question declares the named answer. */
    public boolean declaresAnswer(String question, String answer) {
        IndexedQuestion indexed = questions.get(question);
        return indexed != null && indexed.answers().stream().anyMatch(a -> a.name().equals(answer));
    }

    // --- Internals -----------------------------------------------------------------------------

    /** MCA's key rule: the path segment after the last {@code /}, with {@code .json} removed. */
    static String keyOf(ResourceLocation file) {
        String path = file.getPath();
        if (path.endsWith(".json")) {
            path = path.substring(0, path.length() - ".json".length());
        }
        int slash = path.lastIndexOf('/');
        return slash < 0 ? path : path.substring(slash + 1);
    }

    private static ContentProblem dialogueProblem(ResourceOrigin origin, String entry,
                                                  ContentSeverity severity, String reason, String message) {
        return new ContentProblem(0L, 0L, "Dialogues (MCA)", DIRECTORY, origin, "",
                ContentProblem.UNKNOWN_POSITION, ContentProblem.UNKNOWN_POSITION, entry, severity,
                reason, message, List.of());
    }

    private static List<ContentProblem> coalesce(List<ContentProblem> problems) {
        Map<String, ContentProblem> unique = new LinkedHashMap<>();
        problems.forEach(p -> unique.putIfAbsent(p.coalescingKey(), p));
        return List.copyOf(unique.values());
    }

    private static final class Draft {

        private final String name;
        private final List<ResourceOrigin> sources = new ArrayList<>();
        private final List<IndexedAnswer> answers = new ArrayList<>();
        private boolean auto;
        private boolean silent;

        private Draft(String name) {
            this.name = name;
        }

        private void absorb(ResourceOrigin origin, JsonObject root) {
            sources.add(origin);
            // Later-loaded wins for the flags, exactly as Question.fromJson rebuilds them and merge()
            // only appends the earlier answers.
            auto = root.has("auto") && root.get("auto").getAsBoolean();
            silent = root.has("silent") && root.get("silent").getAsBoolean();
            List<IndexedAnswer> mine = new ArrayList<>();
            for (JsonElement element : root.getAsJsonArray("answers")) {
                JsonObject answer = element.getAsJsonObject();
                String answerName = answer.has("name") ? answer.get("name").getAsString() : "";
                int priority = answer.has("priority") ? answer.get("priority").getAsInt() : 0;
                int resultCount = answer.has("results") && answer.get("results").isJsonArray()
                        ? answer.getAsJsonArray("results").size() : 0;
                List<String> next = new ArrayList<>();
                if (resultCount > 0) {
                    for (JsonElement result : answer.getAsJsonArray("results")) {
                        if (!result.isJsonObject()) {
                            continue;
                        }
                        JsonObject actions = result.getAsJsonObject().has("actions")
                                && result.getAsJsonObject().get("actions").isJsonObject()
                                ? result.getAsJsonObject().getAsJsonObject("actions") : null;
                        if (actions != null && actions.has("next")) {
                            next.add(actions.get("next").getAsString());
                        }
                    }
                }
                mine.add(new IndexedAnswer(answerName, priority, resultCount, List.copyOf(next)));
            }
            if (root.has("baseConditions") && root.get("baseConditions").isJsonArray()) {
                // Folded into each answer's results positionally by MCA; a baseConditions block longer
                // than an answer's result list is an index error inside MCA's own parse, so it is
                // checked here rather than discovered as a reload-aborting throw.
                JsonArray conditions = root.getAsJsonArray("baseConditions");
                int required = conditions.size();
                for (IndexedAnswer answer : mine) {
                    if (answer.resultCount() >= required) {
                        continue;
                    }
                    throw new IllegalStateException("baseConditions declares " + required
                            + " result slot(s) but answer '" + answer.name() + "' has "
                            + answer.resultCount());
                }
            }
            // merge() appends the previously loaded answers after the new ones, then the whole list is
            // stably sorted by priority.
            List<IndexedAnswer> merged = new ArrayList<>(mine);
            merged.addAll(answers);
            merged.sort(Comparator.comparingInt(IndexedAnswer::priority));
            answers.clear();
            answers.addAll(merged);
        }

        private IndexedQuestion freeze() {
            return new IndexedQuestion(name, List.copyOf(sources), auto, silent, List.copyOf(answers),
                    sources.size() > 1);
        }
    }

    /** One question name as the effective resources declare it. */
    public record IndexedQuestion(String name, List<ResourceOrigin> sources, boolean auto, boolean silent,
                                  List<IndexedAnswer> answers, boolean ambiguousMerge) {
    }

    /** One answer of an indexed question, with the question names its results route to. */
    public record IndexedAnswer(String name, int priority, int resultCount, List<String> next) {

        /** MCA refuses to build an answer with no results; an empty list is a malformed answer. */
        public boolean hasResults() {
            return resultCount > 0;
        }
    }
}
