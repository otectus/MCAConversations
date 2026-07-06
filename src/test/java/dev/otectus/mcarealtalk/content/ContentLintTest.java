package dev.otectus.mcarealtalk.content;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcarealtalk.gossip.GossipEventType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Build-time lint over the shipped dialogue datapack and lang files: every condition/action key is
 * one MCA supports (verified against MCA 7.6.23) or one this mod registers; every memory id is
 * namespaced; every say/button/prompt key resolves in our {@code mca_dialogue} lang file. A typo
 * fails CI, not a play session.
 */
class ContentLintTest {

    private static final Path DIALOGUES = Path.of("src/main/resources/data/mcarealtalk/dialogues");
    private static final Path LANG = Path.of("src/main/resources/assets/mca_dialogue/lang/en_us.json");

    /** MCA 7.6.23 condition vocabulary (from GiftPredicate registrations) + ours. */
    private static final Set<String> CONDITION_KEYS = Set.of(
            "chance", "personality", "mood", "age_group", "profession", "rank", "constraints",
            "time_min", "time_max", "advancement", "has_item", "item", "tag", "biome", "emeralds",
            "gender", "has_home", "has_village", "hearts", "hearts_min", "hearts_max", "is_married",
            "is_pregnant", "trait", "village_has_building", "current_chore", "min_health",
            "min_infection_progress", "min_pregnancy_progress", "pregnancy_child_gender", "memory",
            "realtalk_enabled", "realtalk_disabled", "realtalk_gossip");

    /** MCA 7.6.23 action vocabulary (from Actions registrations) + ours. */
    private static final Set<String> ACTION_KEYS = Set.of(
            "next", "say", "positive", "negative", "command", "quit", "remember",
            "realtalk_record", "realtalk_say", "realtalk_gossip_say");

    private static Map<String, JsonObject> questions;
    private static Map<String, String> lang;

    @BeforeAll
    static void load() throws IOException {
        questions = new HashMap<>();
        try (Stream<Path> files = Files.list(DIALOGUES)) {
            for (Path file : files.toList()) {
                String name = file.getFileName().toString().replace(".json", "");
                questions.put(name, JsonParser.parseString(Files.readString(file)).getAsJsonObject());
            }
        }
        lang = new Gson().fromJson(Files.readString(LANG),
                com.google.gson.reflect.TypeToken.getParameterized(Map.class, String.class, String.class).getType());
    }

    @Test
    void everyQuestionHasAnswersArray() {
        questions.forEach((name, json) -> assertTrue(
                json.has("answers") && json.get("answers").isJsonArray(),
                name + " must have an answers array (MCA's loader rejects it otherwise)"));
    }

    @Test
    void conditionAndActionKeysAreKnown() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (result.has("conditions")) {
                for (JsonElement c : result.getAsJsonArray("conditions")) {
                    for (String key : c.getAsJsonObject().keySet()) {
                        if (!CONDITION_KEYS.contains(key)) {
                            problems.add(name + "/" + answerName + ": unknown condition key '" + key + "'");
                        }
                    }
                }
            }
            JsonObject actions = result.getAsJsonObject("actions");
            for (String key : actions.keySet()) {
                if (!ACTION_KEYS.contains(key)) {
                    problems.add(name + "/" + answerName + ": unknown action key '" + key + "'");
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void memoryIdsAreNamespaced() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (result.has("conditions")) {
                for (JsonElement c : result.getAsJsonArray("conditions")) {
                    JsonObject condition = c.getAsJsonObject();
                    if (condition.has("memory")) {
                        checkId(condition.getAsJsonObject("memory"), name + "/" + answerName, problems);
                    }
                }
            }
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("remember")) {
                checkId(actions.getAsJsonObject("remember"), name + "/" + answerName, problems);
            }
            if (actions.has("realtalk_record")) {
                JsonElement record = actions.get("realtalk_record");
                if (record.isJsonArray()) {
                    record.getAsJsonArray().forEach(e ->
                            checkId(e.getAsJsonObject(), name + "/" + answerName, problems));
                } else {
                    checkId(record.getAsJsonObject(), name + "/" + answerName, problems);
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    private static void checkId(JsonObject withId, String where, List<String> problems) {
        String id = withId.get("id").getAsString();
        if (!id.startsWith("mcarealtalk.")) {
            problems.add(where + ": memory id '" + id + "' must start with mcarealtalk.");
        }
    }

    @Test
    void everySayKeyResolvesInLang() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("say")) {
                requireLang("dialogue." + actions.get("say").getAsString(), name + "/" + answerName, problems);
            }
            if (actions.has("realtalk_say")) {
                requireLang("dialogue." + actions.getAsJsonObject("realtalk_say").get("phrase").getAsString(),
                        name + "/" + answerName, problems);
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyNamedAnswerHasButtonLabelAndNextTargetsExist() {
        List<String> problems = new ArrayList<>();
        // Questions our pack may route to without defining them (MCA's own).
        Set<String> mcaQuestions = Set.of("main", "greet", "root");
        questions.forEach((name, json) -> {
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                if (answer.has("name")) {
                    requireLang("dialogue." + name + "." + answer.get("name").getAsString(), name, problems);
                }
                for (JsonElement r : answer.getAsJsonArray("results")) {
                    JsonObject actions = r.getAsJsonObject().getAsJsonObject("actions");
                    if (actions.has("next")) {
                        String next = actions.get("next").getAsString();
                        if (!questions.containsKey(next) && !mcaQuestions.contains(next)) {
                            problems.add(name + ": next target '" + next + "' is not a known question");
                        }
                    }
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void newQuestionsHavePromptText() {
        // Extension files (MCA questions) excluded — MCA provides their prompts.
        Set<String> extensions = Set.of("main", "greet");
        questions.keySet().stream().filter(q -> !extensions.contains(q)).forEach(q ->
                assertTrue(lang.containsKey("dialogue." + q),
                        "question '" + q + "' needs prompt text dialogue." + q));
    }

    @Test
    void gossipTypeLinesExistForDefaultPrefix() {
        for (GossipEventType type : GossipEventType.values()) {
            assertTrue(lang.containsKey("dialogue.realtalk.gossip." + type.jsonName()),
                    "missing gossip line for " + type);
        }
    }

    @Test
    void realtalkVariantSequencesHaveNoHoles() {
        for (String key : lang.keySet()) {
            if (key.contains("/") && key.startsWith("dialogue.realtalk")) {
                String base = key.substring(0, key.indexOf('/'));
                int n = Integer.parseInt(key.substring(key.indexOf('/') + 1));
                assertTrue(lang.containsKey(base), "variant " + key + " without base key " + base);
                for (int i = 1; i <= n; i++) {
                    assertTrue(lang.containsKey(base + "/" + i), base + " variant sequence has a hole at /" + i);
                }
            }
        }
    }

    private static void requireLang(String key, String where, List<String> problems) {
        if (!lang.containsKey(key) && !lang.containsKey(key + "/1")) {
            problems.add(where + ": lang key '" + key + "' missing from mca_dialogue en_us.json");
        }
    }

    private static void forEachResult(JsonObject question, ResultVisitor visitor) {
        for (JsonElement a : question.getAsJsonArray("answers")) {
            JsonObject answer = a.getAsJsonObject();
            String answerName = answer.has("name") ? answer.get("name").getAsString() : "(auto)";
            JsonArray results = answer.getAsJsonArray("results");
            if (results == null) {
                fail(answerName + " has no results array");
            }
            for (JsonElement r : results) {
                visitor.visit(answerName, r.getAsJsonObject());
            }
        }
    }

    private interface ResultVisitor {
        void visit(String answerName, JsonObject result);
    }
}
