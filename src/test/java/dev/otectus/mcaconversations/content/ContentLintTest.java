package dev.otectus.mcaconversations.content;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.gossip.GossipEventType;
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

    private static final Path DIALOGUES = Path.of("src/main/resources/data/mcaconversations/dialogues");
    private static final Path LANG = Path.of("src/main/resources/assets/mca_dialogue/lang/en_us.json");

    /** MCA 7.6.23 condition vocabulary (from GiftPredicate registrations) + ours. */
    private static final Set<String> CONDITION_KEYS = Set.of(
            "chance", "personality", "mood", "age_group", "profession", "rank", "constraints",
            "time_min", "time_max", "advancement", "has_item", "item", "tag", "biome", "emeralds",
            "gender", "has_home", "has_village", "hearts", "hearts_min", "hearts_max", "is_married",
            "is_pregnant", "trait", "village_has_building", "current_chore", "min_health",
            "min_infection_progress", "min_pregnancy_progress", "pregnancy_child_gender", "memory",
            "conversations_enabled", "conversations_disabled", "conversations_gossip", "conversations_weather",
            "conversations_quest_available", "conversations_quest_active", "conversations_quest_ready",
            "conversations_quest_completed");

    /** MCA 7.6.23 action vocabulary (from Actions registrations) + ours. */
    private static final Set<String> ACTION_KEYS = Set.of(
            "next", "say", "positive", "negative", "command", "quit", "remember",
            "conversations_record", "conversations_say", "conversations_gossip_say", "conversations_quest_open");

    /** The four quest-aware condition keys, whose values are objects ({scope,min}), not MCA enum strings. */
    private static final Set<String> QUEST_CONDITION_KEYS = Set.of(
            "conversations_quest_available", "conversations_quest_active", "conversations_quest_ready",
            "conversations_quest_completed");

    /**
     * Conversations quest lifecycle lines the MCA: Quests voice resolver references from Java (not from any
     * say/label/prompt in JSON), so noDeadLangKeys must count them as referenced.
     */
    private static final Set<String> QUEST_VOICE_KEYS = Set.of(
            "dialogue.conversations.quest.accepted", "dialogue.conversations.quest.in_progress",
            "dialogue.conversations.quest.ready", "dialogue.conversations.quest.completed",
            "dialogue.conversations.quest.failed");

    // Condition VALUE vocabularies, pinned from the MCA 7.6.26 jar. Most of these are parsed with
    // Enum.valueOf at DATAPACK LOAD TIME and MCA's Dialogues loader has no error containment — an
    // invalid value crashes the game during world creation (the Chore.CHOPPING crash of 2026-07-06).
    private static final Set<String> CHORES = Set.of("none", "prospect", "harvest", "chop", "hunt", "fish");
    private static final Set<String> MOODS = Set.of("depressed", "sad", "unhappy", "passive", "fine", "happy", "overjoyed");
    private static final Set<String> PERSONALITIES = Set.of(
            "unassigned", "athletic", "confident", "friendly", "flirty", "witty", "shy",
            "gloomy", "sensitive", "greedy", "odd", "lazy", "grumpy", "peppy");
    private static final Set<String> AGE_GROUPS = Set.of("unassigned", "baby", "toddler", "child", "teen", "adult");
    private static final Set<String> RANKS = Set.of("outlaw", "peasant", "merchant", "noble", "mayor", "monarch");
    private static final Set<String> CONSTRAINTS = Set.of(
            "family", "spouse", "kids", "parent", "adult", "teen", "toddler", "baby", "engaged",
            "promised", "cleric", "adventurer", "mercenary", "outlawed", "trader", "orphan",
            "has_village", "following", "hit_by", "mayor", "monarch", "noble", "peasant");
    private static final Set<String> FEATURES = Set.of("topics", "states", "templates", "gossip", "quests", "world");

    /** Weather buckets the {@code conversations_weather} condition matches (see {@code WorldContext}). */
    private static final Set<String> WEATHERS = Set.of("clear", "rain", "storm");

    /** MCA trait vocabulary (lowercase, as MCA's own gift JSON uses), pinned from the 7.6.26 jar. */
    private static final Set<String> TRAITS = Set.of(
            "left_handed", "weak", "tough", "color_blind", "heterochromia", "lactose_intolerance",
            "coeliac_disease", "diabetes", "dwarfism", "albinism", "vegetarian", "bisexual",
            "homosexual", "asexual", "electrified", "sirben", "rainbow", "unknown");

    /**
     * Every profession id our content references — vanilla + the registered professions of the
     * runecraft modpack (scanned 2026-07-06). A typo here means silent dead content, so JSON
     * conditions must match this roster exactly. NOTE: mca:baker/jeweler/miner/warrior/pillager
     * are lang-only ghosts in MCA 7.6.26 (never registered) and must not appear.
     */
    private static final Set<String> PROFESSIONS = Set.of(
            "minecraft:farmer", "minecraft:fisherman", "minecraft:shepherd", "minecraft:fletcher",
            "minecraft:librarian", "minecraft:cartographer", "minecraft:cleric", "minecraft:armorer",
            "minecraft:weaponsmith", "minecraft:toolsmith", "minecraft:butcher",
            "minecraft:leatherworker", "minecraft:mason", "minecraft:nitwit", "minecraft:none",
            "mca:guard", "mca:archer", "mca:adventurer", "mca:mercenary", "mca:cultist", "mca:outlaw",
            "morevillagers:enderian", "morevillagers:engineer", "morevillagers:florist",
            "morevillagers:hunter", "morevillagers:miner", "morevillagers:netherian",
            "morevillagers:oceanographer", "morevillagers:woodworker",
            "ars_nouveau:shady_wizard", "chefsdelight:delightchef", "chefsdelight:delightcook",
            "iceandfire:scribe", "vampirism:hunter_expert", "vampirism:priest",
            "vampirism:vampire_expert", "werewolves:werewolf_expert");

    private static final java.util.regex.Pattern RESOURCE_LOCATION =
            java.util.regex.Pattern.compile("^[a-z0-9_.-]+:[a-z0-9_./-]+$");

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
    void conditionValuesMatchMcaVocabularies() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (!result.has("conditions")) {
                return;
            }
            for (JsonElement c : result.getAsJsonArray("conditions")) {
                JsonObject condition = c.getAsJsonObject();
                String where = name + "/" + answerName;
                checkValue(condition, "current_chore", CHORES, where, problems);
                checkValue(condition, "mood", MOODS, where, problems);
                checkValue(condition, "personality", PERSONALITIES, where, problems);
                checkValue(condition, "age_group", AGE_GROUPS, where, problems);
                checkValue(condition, "rank", RANKS, where, problems);
                checkValue(condition, "conversations_enabled", FEATURES, where, problems);
                checkValue(condition, "conversations_disabled", FEATURES, where, problems);
                checkValue(condition, "trait", TRAITS, where, problems);
                if (condition.has("profession")) {
                    String value = condition.get("profession").getAsString();
                    if (!RESOURCE_LOCATION.matcher(value).matches()) {
                        problems.add(where + ": malformed profession id '" + value + "'");
                    } else if (!PROFESSIONS.contains(value)) {
                        problems.add(where + ": profession '" + value + "' not in the pinned roster (typo = dead content)");
                    }
                }
                if (condition.has("constraints")) {
                    for (String token : condition.get("constraints").getAsString().split(",")) {
                        String bare = token.strip().replaceFirst("^!", "");
                        if (!CONSTRAINTS.contains(bare)) {
                            problems.add(where + ": unknown constraint '" + token.strip() + "'");
                        }
                    }
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * The {@code conversations_quest_*} conditions take an object value {@code {scope,min}}, which
     * {@link #checkValue} (string-only) skips. Validate them explicitly: {@code scope} must be
     * {@code this}/{@code any} and {@code min} a non-negative integer — mirrors
     * {@code QuestConditionQuery.fromJson}, so a bad datapack value fails CI, not world creation.
     */
    @Test
    void questConditionArgsAreValid() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (!result.has("conditions")) {
                return;
            }
            for (JsonElement c : result.getAsJsonArray("conditions")) {
                JsonObject condition = c.getAsJsonObject();
                for (String key : QUEST_CONDITION_KEYS) {
                    if (!condition.has(key)) {
                        continue;
                    }
                    String where = name + "/" + answerName + " " + key;
                    JsonElement value = condition.get(key);
                    if (!value.isJsonObject()) {
                        problems.add(where + ": value must be an object {scope,min}");
                        continue;
                    }
                    JsonObject args = value.getAsJsonObject();
                    if (args.has("scope")) {
                        String scope = args.get("scope").getAsString();
                        if (!scope.equals("this") && !scope.equals("any")) {
                            problems.add(where + ": scope must be 'this' or 'any', was '" + scope + "'");
                        }
                    }
                    if (args.has("min") && args.get("min").getAsInt() < 0) {
                        problems.add(where + ": min must be >= 0");
                    }
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * The {@code conversations_weather} condition takes an object value {@code {"is": "rain"}}; validate
     * that {@code is} is a known weather bucket — mirrors {@code WorldQuery}/{@code WorldContext}, so a
     * bad datapack value fails CI, not a silent never-matching line.
     */
    @Test
    void weatherConditionArgsAreValid() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (!result.has("conditions")) {
                return;
            }
            for (JsonElement c : result.getAsJsonArray("conditions")) {
                JsonObject condition = c.getAsJsonObject();
                if (!condition.has("conversations_weather")) {
                    continue;
                }
                String where = name + "/" + answerName + " conversations_weather";
                JsonElement value = condition.get("conversations_weather");
                if (!value.isJsonObject() || !value.getAsJsonObject().has("is")) {
                    problems.add(where + ": value must be an object {is}");
                    continue;
                }
                String is = value.getAsJsonObject().get("is").getAsString();
                if (!WEATHERS.contains(is)) {
                    problems.add(where + ": is '" + is + "' not a weather bucket " + WEATHERS);
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /** Regression guard for the 2026-07-06 world-creation crash: these exact values must never return. */
    @Test
    void choreVocabularyRejectsTheOldCrashValues() {
        for (String bad : List.of("chopping", "harvesting", "fishing")) {
            assertTrue(!CHORES.contains(bad), "'" + bad + "' is not a valid MCA Chore");
        }
    }

    private static void checkValue(JsonObject condition, String key, Set<String> allowed,
                                   String where, List<String> problems) {
        if (condition.has(key) && condition.get(key).isJsonPrimitive()
                && condition.get(key).getAsJsonPrimitive().isString()) {
            String value = condition.get(key).getAsString();
            if (!allowed.contains(value)) {
                problems.add(where + ": invalid " + key + " value '" + value + "' (allowed: " + allowed + ")");
            }
        }
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
            if (actions.has("conversations_record")) {
                JsonElement record = actions.get("conversations_record");
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
        if (!id.startsWith("mcaconversations.")) {
            problems.add(where + ": memory id '" + id + "' must start with mcaconversations.");
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
            if (actions.has("conversations_say")) {
                requireLang("dialogue." + actions.getAsJsonObject("conversations_say").get("phrase").getAsString(),
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
        // Extension files (MCA questions) excluded — MCA provides their prompts. Auto questions
        // are processed server-side without ever showing a prompt.
        Set<String> extensions = Set.of("greet");
        questions.entrySet().stream()
                .filter(e -> !extensions.contains(e.getKey()))
                .filter(e -> !(e.getValue().has("auto") && e.getValue().get("auto").getAsBoolean()))
                .forEach(e -> assertTrue(lang.containsKey("dialogue." + e.getKey()),
                        "question '" + e.getKey() + "' needs prompt text dialogue." + e.getKey()));
    }

    /**
     * Every lang key must trace back to something that can display it — a say/conversations_say
     * reference, a gossip type line, a question prompt, an answer button, a follow-up button, or a
     * whitelisted extension of MCA's own line pools. Orphans are dead content rot.
     */
    @Test
    void noDeadLangKeys() {
        Set<String> referenced = new HashSet<>();
        questions.forEach((name, json) -> {
            referenced.add("dialogue." + name);
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                if (answer.has("name")) {
                    referenced.add("dialogue." + name + "." + answer.get("name").getAsString());
                }
            }
            forEachResult(json, (answerName, result) -> {
                JsonObject actions = result.getAsJsonObject("actions");
                if (actions.has("say")) {
                    referenced.add("dialogue." + actions.get("say").getAsString());
                }
                if (actions.has("conversations_say")) {
                    referenced.add("dialogue." + actions.getAsJsonObject("conversations_say").get("phrase").getAsString());
                }
            });
        });
        for (GossipEventType type : GossipEventType.values()) {
            referenced.add("dialogue.conversations.gossip." + type.jsonName());
        }
        // Quest lifecycle lines are referenced by the MCA: Quests voice resolver (Java), not by JSON.
        referenced.addAll(QUEST_VOICE_KEYS);
        // Intentional extensions of MCA's own dialogue pools (their bases live in MCA's lang),
        // plus dialogue.chat: MCA's next-action builds the header prompt from the raw next string,
        // so the Chat->Conversations redirect displays "dialogue.chat" as the hub entry header even
        // though no JSON of ours references it.
        Set<String> mcaPoolBases = Set.of("dialogue.main", "dialogue.greet.success",
                "dialogue.greet.fail", "dialogue.story.success", "dialogue.shake_hand.success",
                "dialogue.chat");

        List<String> problems = new ArrayList<>();
        for (String key : lang.keySet()) {
            String base = key.contains("/") ? key.substring(0, key.indexOf('/')) : key;
            if (!referenced.contains(base) && !mcaPoolBases.contains(base)) {
                problems.add("orphaned lang key: " + key);
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * Anti-repetition floor: every referenced say key needs a pool of ≥3 lines, except the
     * per-profession/per-trait/per-age flavor keys (≥2; they are already precision-targeted) and
     * the sirben easter egg (1 is the joke).
     */
    @Test
    void sayKeyPoolsMeetTheVariantFloor() {
        Set<String> sayKeys = new HashSet<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("say")) {
                sayKeys.add(actions.get("say").getAsString());
            }
            if (actions.has("conversations_say")) {
                sayKeys.add(actions.getAsJsonObject("conversations_say").get("phrase").getAsString());
            }
        }));
        List<String> problems = new ArrayList<>();
        for (String key : sayKeys) {
            if (key.equals("conversations.food.trait.sirben")) {
                continue;
            }
            int floor = key.startsWith("conversations.work.prof.") || key.startsWith("conversations.food.trait.")
                    || key.endsWith(".child") || key.endsWith(".teen") ? 2 : 3;
            String base = "dialogue." + key;
            int pool = (lang.containsKey(base) ? 1 : 0);
            for (int i = 1; lang.containsKey(base + "/" + i); i++) {
                pool++;
            }
            if (pool < floor) {
                problems.add(base + ": pool " + pool + " < floor " + floor);
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /** All hand-written profession say keys must correspond to roster ids and vice versa. */
    @Test
    void professionLinesMatchRoster() {
        Set<String> expectedPaths = new HashSet<>();
        for (String id : PROFESSIONS) {
            expectedPaths.add(id.substring(id.indexOf(':') + 1));
        }
        List<String> problems = new ArrayList<>();
        for (String path : expectedPaths) {
            if (!lang.containsKey("dialogue.conversations.work.prof." + path)) {
                problems.add("missing profession line: dialogue.conversations.work.prof." + path);
            }
        }
        for (String key : lang.keySet()) {
            if (key.startsWith("dialogue.conversations.work.prof.") && !key.contains("/")) {
                String path = key.substring("dialogue.conversations.work.prof.".length());
                if (!expectedPaths.contains(path)) {
                    problems.add("profession line without roster entry: " + key);
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void gossipTypeLinesExistForDefaultPrefix() {
        for (GossipEventType type : GossipEventType.values()) {
            assertTrue(lang.containsKey("dialogue.conversations.gossip." + type.jsonName()),
                    "missing gossip line for " + type);
        }
    }

    @Test
    void conversationsVariantSequencesHaveNoHoles() {
        for (String key : lang.keySet()) {
            if (key.contains("/") && key.startsWith("dialogue.conversations")) {
                String base = key.substring(0, key.indexOf('/'));
                int n = Integer.parseInt(key.substring(key.indexOf('/') + 1));
                assertTrue(lang.containsKey(base), "variant " + key + " without base key " + base);
                for (int i = 1; i <= n; i++) {
                    assertTrue(lang.containsKey(base + "/" + i), base + " variant sequence has a hole at /" + i);
                }
            }
        }
    }

    /**
     * The label of answer 'a' in question 'q' is lang key dialogue.q.a — the same key a question
     * named "q.a" would use as its prompt/header. If both exist, one string serves two masters
     * (the pre-category hub had exactly this: dialogue.conversations.family was both a button and the
     * conversations.family page header). Category answers must not resurrect that collision.
     */
    @Test
    void answerLabelKeysDontCollideWithQuestionPrompts() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> {
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                if (answer.has("name") && questions.containsKey(name + "." + answer.get("name").getAsString())) {
                    problems.add(name + "/" + answer.get("name").getAsString()
                            + ": label key collides with the prompt of question '"
                            + name + "." + answer.get("name").getAsString() + "'");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /** Every conversations.* question must be reachable from the hub via next edges — no orphan pages. */
    @Test
    void everyConversationsQuestionIsReachableFromHub() {
        Set<String> reached = new HashSet<>();
        List<String> frontier = new ArrayList<>(List.of("conversations"));
        while (!frontier.isEmpty()) {
            String current = frontier.remove(frontier.size() - 1);
            if (!reached.add(current) || !questions.containsKey(current)) {
                continue;
            }
            forEachResult(questions.get(current), (answerName, result) -> {
                JsonObject actions = result.getAsJsonObject("actions");
                if (actions.has("next")) {
                    frontier.add(actions.get("next").getAsString());
                }
            });
        }
        questions.keySet().stream()
                .filter(name -> name.startsWith("conversations"))
                .forEach(name -> assertTrue(reached.contains(name),
                        "question '" + name + "' is unreachable from the conversations hub"));
    }

    /**
     * The category layer must stay pure navigation: hub answers do nothing but hop to a
     * conversations.cat.* page (or exit via main), and every category page offers a back answer that
     * returns to the hub. Side effects (say, hearts, memories) belong on starters, not categories.
     */
    @Test
    void categoryHubShapeInvariants() {
        List<String> problems = new ArrayList<>();

        for (JsonElement a : questions.get("conversations").getAsJsonArray("answers")) {
            JsonObject answer = a.getAsJsonObject();
            String name = answer.get("name").getAsString();
            JsonArray results = answer.getAsJsonArray("results");
            if (results.size() != 1) {
                problems.add("conversations/" + name + ": hub answers must have exactly one result");
                continue;
            }
            JsonObject actions = results.get(0).getAsJsonObject().getAsJsonObject("actions");
            String expected = name.equals("back") ? "main" : "conversations.cat.";
            if (!actions.keySet().equals(Set.of("next"))
                    || !actions.get("next").getAsString().startsWith(expected)) {
                problems.add("conversations/" + name + ": must be a side-effect-free hop to "
                        + (name.equals("back") ? "'main'" : "a conversations.cat.* page") + ", got " + actions);
            }
        }

        questions.forEach((name, json) -> {
            if (!name.startsWith("conversations.cat.")) {
                return;
            }
            boolean hasBack = false;
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                if (answer.has("name") && answer.get("name").getAsString().equals("back")) {
                    hasBack = true;
                    JsonArray results = answer.getAsJsonArray("results");
                    JsonObject actions = results.get(0).getAsJsonObject().getAsJsonObject("actions");
                    if (results.size() != 1 || !actions.keySet().equals(Set.of("next"))
                            || !actions.get("next").getAsString().equals("conversations")) {
                        problems.add(name + "/back: must be a single side-effect-free hop to 'conversations'");
                    }
                }
            }
            if (!hasBack) {
                problems.add(name + ": category page has no back answer");
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
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
