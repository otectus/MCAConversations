package dev.otectus.mcaconversations.debug;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

/**
 * A read-only view over MCA dialogue JSON, in the shape the coherence work actually needs (spec §15
 * Phase 0).
 *
 * <p>MCA's own loader turns this JSON into its runtime objects and then answers only the questions
 * the runtime asks. The question this overhaul has to answer is the opposite one, and cannot be asked
 * of a graph in flight: <em>given every line that can lead into this page, is every button on it a
 * sensible thing to say?</em> That needs the whole corpus at rest — all 173 files, every result,
 * every route — which is what this class provides to the exporter, the lint suites and the debug
 * command alike.
 *
 * <p>Deliberately tolerant: unknown fields are ignored and a missing {@code answers} array yields an
 * empty question, because this model is also pointed at third-party datapacks whose contents are not
 * ours to validate.
 */
public final class DialogueGraph {

    /** The action key MCA uses for a plain speech line. */
    public static final String SAY = "say";
    /** This mod's templated speech action; its {@code phrase} is the equivalent of a say key. */
    public static final String CONVERSATIONS_SAY = "conversations_say";
    /** The action naming the question a result hands control to. */
    public static final String NEXT = "next";

    /** Actions that write something durable, listed in the trace so a reviewer sees consequences. */
    public static final List<String> CONSEQUENCE_ACTIONS = List.of(
            "positive", "negative", "remember", "conversations_record",
            "conversations_affection_apply", "conversations_disposition_apply",
            "conversations_progress_apply", "conversations_reputation_signal", "conversations_quest_open");

    private final Map<String, Question> questions;

    private DialogueGraph(Map<String, Question> questions) {
        this.questions = questions;
    }

    /** Builds a graph from question id → parsed file contents. */
    public static DialogueGraph of(Map<String, JsonObject> files) {
        Map<String, Question> parsed = new TreeMap<>();
        files.forEach((id, json) -> parsed.put(id, Question.parse(id, json)));
        return new DialogueGraph(Map.copyOf(parsed));
    }

    public Optional<Question> question(String id) {
        return Optional.ofNullable(questions.get(id));
    }

    public Set<String> questionIds() {
        return new LinkedHashSet<>(new TreeMap<>(questions).keySet());
    }

    public List<Question> allQuestions() {
        return List.copyOf(new TreeMap<>(questions).values());
    }

    /** Every speaking result in the corpus, in a stable order. */
    public List<Route> routes() {
        List<Route> out = new ArrayList<>();
        for (Question question : allQuestions()) {
            for (Answer answer : question.answers()) {
                for (Result result : answer.results()) {
                    out.add(new Route(question, answer, result));
                }
            }
        }
        return List.copyOf(out);
    }

    /** Every route that both speaks and navigates — the pairs a beat contract must cover. */
    public List<Route> speakingRoutes() {
        return routes().stream().filter(r -> r.result().isSpeaking() && r.result().next().isPresent()).toList();
    }

    /** One question node. */
    public record Question(String id, boolean auto, boolean silent, List<Answer> answers) {

        static Question parse(String id, JsonObject json) {
            List<Answer> answers = new ArrayList<>();
            if (json.has("answers") && json.get("answers").isJsonArray()) {
                JsonArray array = json.getAsJsonArray("answers");
                for (JsonElement element : array) {
                    if (element.isJsonObject()) {
                        answers.add(Answer.parse(element.getAsJsonObject()));
                    }
                }
            }
            boolean auto = json.has("auto") && json.get("auto").getAsBoolean();
            boolean silent = json.has("silent") && json.get("silent").getAsBoolean();
            return new Question(id, auto, silent, List.copyOf(answers));
        }

        public Optional<Answer> answer(String name) {
            return answers.stream().filter(a -> name.equals(a.name())).findFirst();
        }

        /** The lang key MCA reads for this node's villager prompt. */
        public String promptKey() {
            return "dialogue." + id;
        }
    }

    /** One player button, with the constraints MCA uses to decide whether to show it. */
    public record Answer(String name, List<JsonObject> constraints, List<Result> results) {

        static Answer parse(JsonObject json) {
            String name = json.has("name") ? json.get("name").getAsString() : null;
            List<JsonObject> constraints = new ArrayList<>();
            if (json.has("constraints") && json.get("constraints").isJsonArray()) {
                for (JsonElement element : json.getAsJsonArray("constraints")) {
                    if (element.isJsonObject()) {
                        constraints.add(element.getAsJsonObject());
                    }
                }
            }
            List<Result> results = new ArrayList<>();
            if (json.has("results") && json.get("results").isJsonArray()) {
                for (JsonElement element : json.getAsJsonArray("results")) {
                    if (element.isJsonObject()) {
                        results.add(Result.parse(element.getAsJsonObject()));
                    }
                }
            }
            return new Answer(name, List.copyOf(constraints), List.copyOf(results));
        }

        /** True for MCA's unnamed auto-answer, which the player never sees as a button. */
        public boolean isAuto() {
            return name == null;
        }

        /** The lang key for this button's wording. */
        public String labelKey(String questionId) {
            return isAuto() ? "dialogue." + questionId : "dialogue." + questionId + "." + name;
        }
    }

    /** One villager reaction: the conditions that score it, and the actions it runs if chosen. */
    public record Result(int baseChance, List<JsonObject> conditions, JsonObject actions) {

        static Result parse(JsonObject json) {
            int baseChance = json.has("baseChance") ? json.get("baseChance").getAsInt() : 0;
            List<JsonObject> conditions = new ArrayList<>();
            if (json.has("conditions") && json.get("conditions").isJsonArray()) {
                for (JsonElement element : json.getAsJsonArray("conditions")) {
                    if (element.isJsonObject()) {
                        conditions.add(element.getAsJsonObject());
                    }
                }
            }
            JsonObject actions = json.has("actions") && json.get("actions").isJsonObject()
                    ? json.getAsJsonObject("actions")
                    : new JsonObject();
            return new Result(baseChance, List.copyOf(conditions), actions);
        }

        /** The base speech key this result delivers, from either speech action. */
        public Optional<String> say() {
            if (actions.has(SAY)) {
                return Optional.of(actions.get(SAY).getAsString());
            }
            if (actions.has(CONVERSATIONS_SAY) && actions.get(CONVERSATIONS_SAY).isJsonObject()) {
                JsonObject directive = actions.getAsJsonObject(CONVERSATIONS_SAY);
                if (directive.has("phrase")) {
                    return Optional.of(directive.get("phrase").getAsString());
                }
            }
            return Optional.empty();
        }

        public Optional<String> next() {
            return actions.has(NEXT) ? Optional.of(actions.get(NEXT).getAsString()) : Optional.empty();
        }

        public boolean isSpeaking() {
            return say().isPresent();
        }

        /** The beat id this result declares through {@code conversations_session}, if any. */
        public Optional<String> declaredBeat() {
            if (!actions.has("conversations_session") || !actions.get("conversations_session").isJsonObject()) {
                return Optional.empty();
            }
            JsonObject session = actions.getAsJsonObject("conversations_session");
            return session.has("beat") ? Optional.of(session.get("beat").getAsString()) : Optional.empty();
        }

        /** The durable-consequence action names this result runs, for the review report. */
        public List<String> consequences() {
            List<String> out = new ArrayList<>();
            for (String action : CONSEQUENCE_ACTIONS) {
                if (actions.has(action)) {
                    out.add(action);
                }
            }
            return List.copyOf(out);
        }

        /** A compact, deterministic rendering of the conditions, for the review report. */
        public String conditionSummary() {
            if (conditions.isEmpty()) {
                return "(always eligible)";
            }
            List<String> parts = new ArrayList<>();
            for (JsonObject condition : conditions) {
                int chance = condition.has("chance") ? condition.get("chance").getAsInt() : 0;
                List<String> terms = new ArrayList<>();
                for (Map.Entry<String, JsonElement> entry : new TreeMap<>(condition.asMap()).entrySet()) {
                    if ("chance".equals(entry.getKey())) {
                        continue;
                    }
                    terms.add(entry.getKey() + "=" + entry.getValue());
                }
                parts.add((chance < 0 ? "NOT " : "") + String.join(" & ", terms));
            }
            return String.join("; ", parts);
        }
    }

    /** A question/answer/result triple — one authored edge of the graph. */
    public record Route(Question question, Answer answer, Result result) {

        public String id() {
            return question.id() + "/" + (answer.isAuto() ? "(auto)" : answer.name());
        }

        /** The {@code say -> next} pair a beat contract is filed under. */
        public Optional<String> routeKey() {
            return result.say().flatMap(say -> result.next().map(next -> say + " -> " + next));
        }
    }
}
