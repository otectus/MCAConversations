package dev.otectus.mcaconversations.content;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.check.CheckDefinition;
import dev.otectus.mcaconversations.check.CheckTier;
import dev.otectus.mcaconversations.disposition.DispositionApply;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.disposition.DispositionQuery;
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
            "conversations_season", "conversations_holiday",
            "conversations_disposition", "conversations_check",
            "conversations_quest_available", "conversations_quest_active", "conversations_quest_ready",
            "conversations_quest_completed");

    /** MCA 7.6.23 action vocabulary (from Actions registrations) + ours. */
    private static final Set<String> ACTION_KEYS = Set.of(
            "next", "say", "positive", "negative", "command", "quit", "remember",
            "conversations_record", "conversations_say", "conversations_gossip_say",
            "conversations_disposition_apply", "conversations_quest_open");

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
    private static final Set<String> FEATURES = Set.of(
            "topics", "states", "templates", "gossip", "quests", "world", "dispositions", "checks");

    /** Weather buckets the {@code conversations_weather} condition matches (see {@code WorldContext}). */
    private static final Set<String> WEATHERS = Set.of("clear", "rain", "storm");

    /** Season buckets the {@code conversations_season} condition matches (see {@code WorldContext.seasonFromDay}). */
    private static final Set<String> SEASONS = Set.of("spring", "summer", "autumn", "winter");

    /** Holiday buckets the {@code conversations_holiday} condition matches (see {@code HolidayCalendar}). */
    private static final Set<String> HOLIDAYS = Set.of(
            "none", "spring_bloom", "midsummer", "harvest_festival", "midwinter");

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
        assertWorldConditionArgsValid("conversations_weather", WEATHERS);
    }

    /** {@code conversations_season: {"is": "autumn"}} — {@code is} must be a known season bucket. */
    @Test
    void seasonConditionArgsAreValid() {
        assertWorldConditionArgsValid("conversations_season", SEASONS);
    }

    /** {@code conversations_holiday: {"is": "midsummer"}} — {@code is} must be a known holiday bucket. */
    @Test
    void holidayConditionArgsAreValid() {
        assertWorldConditionArgsValid("conversations_holiday", HOLIDAYS);
    }

    /**
     * Shared validator for the object-valued world conditions ({@code {"is": "<bucket>"}}): mirrors
     * {@code WorldQuery}, so a bad datapack value fails CI rather than becoming a silent never-match.
     */
    private static void assertWorldConditionArgsValid(String key, Set<String> allowed) {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (!result.has("conditions")) {
                return;
            }
            for (JsonElement c : result.getAsJsonArray("conditions")) {
                JsonObject condition = c.getAsJsonObject();
                if (!condition.has(key)) {
                    continue;
                }
                String where = name + "/" + answerName + " " + key;
                JsonElement value = condition.get(key);
                if (!value.isJsonObject() || !value.getAsJsonObject().has("is")) {
                    problems.add(where + ": value must be an object {is}");
                    continue;
                }
                String is = value.getAsJsonObject().get("is").getAsString();
                if (!allowed.contains(is)) {
                    problems.add(where + ": is '" + is + "' not in " + allowed);
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
            // dialogue.chatmode.* are the chat-mode deflection lines, referenced from chat/ Java
            // (ChatModeDispatcher), never from a dialogue say/prompt — count them as referenced.
            if (base.startsWith("dialogue.chatmode.")) {
                continue;
            }
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
                    || key.endsWith(".child") || key.endsWith(".teen")
                    // Check-tier and guard lines are precision-targeted (one tier of one stance).
                    || key.endsWith(".crit") || key.endsWith(".success") || key.endsWith(".partial")
                    || key.endsWith(".rebuff") || key.endsWith(".guard") ? 2 : 3;
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
            if (name.equals("babble")) {
                // The one hub answer that is not navigation: a baby has no categories to offer, so
                // this leaf says its line and returns to the main menu. Shape-checked explicitly.
                if (!actions.keySet().equals(Set.of("next", "say"))
                        || !actions.get("next").getAsString().equals("main")
                        || !actions.get("say").getAsString().startsWith("conversations.babble.")) {
                    problems.add("conversations/babble: must say a conversations.babble.* line and "
                            + "return to 'main', got " + actions);
                }
                continue;
            }
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

    /**
     * Every {@code conversations_disposition}/{@code conversations_check} condition and
     * {@code conversations_disposition_apply} action must be accepted by the exact parser the runtime
     * uses (a rejected value there silently becomes a never-match/no-op — dead content), and
     * disposition ranges must lie inside the axis bounds (an out-of-bounds bound also never matches).
     */
    @Test
    void dispositionAndCheckArgsAreValid() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            String where = name + "/" + answerName;
            if (result.has("conditions")) {
                for (JsonElement c : result.getAsJsonArray("conditions")) {
                    JsonObject condition = c.getAsJsonObject();
                    if (condition.has("conversations_disposition")) {
                        try {
                            DispositionQuery query = DispositionQuery.fromJson(
                                    condition.getAsJsonObject("conversations_disposition"));
                            if (query.min() < query.axis().min() || query.max() > query.axis().max()) {
                                problems.add(where + ": disposition range [" + query.min() + "," + query.max()
                                        + "] exceeds " + query.axis().key() + " bounds");
                            }
                        } catch (RuntimeException e) {
                            problems.add(where + ": conversations_disposition rejected: " + e.getMessage());
                        }
                    }
                    if (condition.has("conversations_check")) {
                        try {
                            CheckDefinition.fromJson(condition.getAsJsonObject("conversations_check"));
                        } catch (RuntimeException e) {
                            problems.add(where + ": conversations_check rejected: " + e.getMessage());
                        }
                    }
                }
            }
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("conversations_disposition_apply")) {
                try {
                    DispositionApply.fromJson(actions.getAsJsonObject("conversations_disposition_apply"));
                } catch (RuntimeException e) {
                    problems.add(where + ": conversations_disposition_apply rejected: " + e.getMessage());
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * Check completeness (§4b): within an answer, every check id must appear with exactly the four
     * tiers, identical axis/difficulty across them (the resolver assembles inputs once per id), and
     * the answer must author a plain fallback result that fires when the check subsystem is disabled
     * (a negative {@code conversations_enabled: "checks"} sink marks it).
     */
    @Test
    void checkedAnswersDefineAllFourTiersAndADisabledFallback() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> {
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                String where = name + "/" + (answer.has("name") ? answer.get("name").getAsString() : "(auto)");
                Map<String, Set<String>> tiersById = new HashMap<>();
                Map<String, Set<String>> shapesById = new HashMap<>();
                boolean hasCheck = false;
                boolean hasDisabledFallback = false;
                for (JsonElement r : answer.getAsJsonArray("results")) {
                    JsonObject result = r.getAsJsonObject();
                    if (!result.has("conditions")) {
                        continue;
                    }
                    for (JsonElement c : result.getAsJsonArray("conditions")) {
                        JsonObject condition = c.getAsJsonObject();
                        if (condition.has("conversations_check")) {
                            hasCheck = true;
                            JsonObject check = condition.getAsJsonObject("conversations_check");
                            String id = check.get("id").getAsString();
                            tiersById.computeIfAbsent(id, k -> new HashSet<>())
                                    .add(check.get("tier").getAsString());
                            shapesById.computeIfAbsent(id, k -> new HashSet<>())
                                    .add(check.get("axis").getAsString() + "@" + check.get("difficulty").getAsInt());
                        }
                        if (condition.has("conversations_enabled")
                                && condition.get("conversations_enabled").getAsString().equals("checks")
                                && condition.get("chance").getAsInt() < 0) {
                            hasDisabledFallback = true;
                        }
                    }
                }
                if (!hasCheck) {
                    continue;
                }
                tiersById.forEach((id, tiers) -> {
                    Set<String> allTiers = new HashSet<>();
                    for (CheckTier tier : CheckTier.values()) {
                        allTiers.add(tier.key());
                    }
                    if (!tiers.equals(allTiers)) {
                        problems.add(where + ": check '" + id + "' defines tiers " + tiers
                                + " but must define exactly " + allTiers);
                    }
                });
                shapesById.forEach((id, shapes) -> {
                    if (shapes.size() != 1) {
                        problems.add(where + ": check '" + id + "' uses inconsistent axis/difficulty " + shapes);
                    }
                });
                if (!hasDisabledFallback) {
                    problems.add(where + ": checked answer has no checks-disabled fallback result"
                            + " (a result sunk by a negative conversations_enabled: \"checks\" condition)");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /** Every check-tier result must keep the conversation alive: a live next hop and a spoken line. */
    @Test
    void checkTierResultsNeverDeadEnd() {
        List<String> problems = new ArrayList<>();
        Set<String> mcaQuestions = Set.of("main", "greet", "root");
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (!result.has("conditions")) {
                return;
            }
            boolean isCheckResult = false;
            for (JsonElement c : result.getAsJsonArray("conditions")) {
                if (c.getAsJsonObject().has("conversations_check")) {
                    isCheckResult = true;
                }
            }
            if (!isCheckResult) {
                return;
            }
            String where = name + "/" + answerName;
            JsonObject actions = result.getAsJsonObject("actions");
            if (!actions.has("say")) {
                problems.add(where + ": check tier result has no say line");
            }
            if (!actions.has("next")
                    || (!questions.containsKey(actions.get("next").getAsString())
                        && !mcaQuestions.contains(actions.get("next").getAsString()))) {
                problems.add(where + ": check tier result has no live next hop (graceful exit rule)");
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * The determinism invariant that makes checks work on MCA's <b>weighted-random</b> result
     * selection (verified against Dialogues.selectAnswer bytecode: positive totals are lottery
     * weights, not priorities): in every reachable state of a checked answer, EXACTLY one result may
     * have positive weight. Enumerates the full state space — checks/dispositions feature toggles ×
     * resolver tier per check id × in/out per disposition range × has/lacks per memory id — and
     * evaluates every result's weight exactly as MCA sums it.
     */
    @Test
    void checkedAnswerStatesResolveToExactlyOneResult() {
        Set<String> modeledKeys = Set.of("chance", "conversations_check", "conversations_disposition",
                "conversations_enabled", "conversations_disabled", "memory");
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> {
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                String where = name + "/" + (answer.has("name") ? answer.get("name").getAsString() : "(auto)");
                JsonArray results = answer.getAsJsonArray("results");
                boolean hasCheck = false;
                for (JsonElement r : results) {
                    if (r.getAsJsonObject().has("conditions")) {
                        for (JsonElement c : r.getAsJsonObject().getAsJsonArray("conditions")) {
                            if (c.getAsJsonObject().has("conversations_check")) {
                                hasCheck = true;
                            }
                        }
                    }
                }
                if (!hasCheck) {
                    continue;
                }

                // Collect the state variables and reject unmodeled condition keys.
                List<String> checkIds = new ArrayList<>();
                List<String> dispRanges = new ArrayList<>();
                List<String> memoryIds = new ArrayList<>();
                Map<String, String> rangeAxis = new HashMap<>();
                boolean modelable = true;
                for (JsonElement r : results) {
                    JsonObject result = r.getAsJsonObject();
                    if (!result.has("conditions")) {
                        continue;
                    }
                    for (JsonElement c : result.getAsJsonArray("conditions")) {
                        JsonObject condition = c.getAsJsonObject();
                        for (String key : condition.keySet()) {
                            if (!modeledKeys.contains(key)) {
                                problems.add(where + ": condition key '" + key
                                        + "' is not modelable — checked answers may only use " + modeledKeys);
                                modelable = false;
                            }
                        }
                        if (condition.has("conversations_check")) {
                            String id = condition.getAsJsonObject("conversations_check").get("id").getAsString();
                            if (!checkIds.contains(id)) {
                                checkIds.add(id);
                            }
                        }
                        if (condition.has("conversations_disposition")) {
                            JsonObject q = condition.getAsJsonObject("conversations_disposition");
                            String range = q.toString();
                            if (!dispRanges.contains(range)) {
                                dispRanges.add(range);
                                String axis = q.get("axis").getAsString();
                                if (rangeAxis.containsValue(axis)) {
                                    problems.add(where + ": multiple disposition ranges on axis '" + axis
                                            + "' — ranges on one axis are correlated and cannot be simulated"
                                            + " independently; use one range per axis per answer");
                                    modelable = false;
                                }
                                rangeAxis.put(range, axis);
                            }
                        }
                        if (condition.has("memory")) {
                            String id = memoryVariable(condition.getAsJsonObject("memory"));
                            if (!memoryIds.contains(id)) {
                                memoryIds.add(id);
                            }
                        }
                    }
                }
                if (!modelable) {
                    continue;
                }
                int boolVars = 2 + dispRanges.size() + memoryIds.size();
                if (boolVars + 2 * checkIds.size() > 14) {
                    problems.add(where + ": state space too large to simulate — simplify the answer");
                    continue;
                }

                // Enumerate: bit 0 = checks on, bit 1 = dispositions on, then ranges, then memories;
                // tiers enumerated separately per check id (5 states: 4 tiers + 'none' for the
                // attraction-ineligible case, which only arises for attraction-axis checks).
                int combos = 1 << boolVars;
                int tierStates = (int) Math.pow(5, checkIds.size());
                for (int bits = 0; bits < combos; bits++) {
                    boolean checksOn = (bits & 1) != 0;
                    boolean dispOn = (bits & 2) != 0;
                    for (int t = 0; t < tierStates; t++) {
                        Map<String, String> tierById = new HashMap<>();
                        boolean anyNone = false;
                        int tt = t;
                        for (String id : checkIds) {
                            int pick = tt % 5;
                            tt /= 5;
                            String tier = pick == 4 ? "none" : CheckTier.values()[pick].key();
                            if (tier.equals("none")) {
                                anyNone = true;
                            }
                            tierById.put(id, tier);
                        }
                        // 'none' only occurs for attraction checks; skip it for other axes.
                        if (anyNone && !answerHasAttractionCheck(results)) {
                            continue;
                        }
                        int positives = 0;
                        String positiveNames = "";
                        for (JsonElement r : results) {
                            JsonObject result = r.getAsJsonObject();
                            int weight = result.has("baseChance") ? result.get("baseChance").getAsInt() : 0;
                            if (result.has("conditions")) {
                                for (JsonElement c : result.getAsJsonArray("conditions")) {
                                    JsonObject condition = c.getAsJsonObject();
                                    int chance = condition.get("chance").getAsInt();
                                    weight += chance * conditionValue(condition, checksOn, dispOn,
                                            tierById, dispRanges, bits, memoryIds);
                                }
                            }
                            if (weight > 0) {
                                positives++;
                                positiveNames += " " + result.getAsJsonObject("actions");
                            }
                        }
                        if (positives != 1) {
                            problems.add(where + ": state[checks=" + checksOn + " disp=" + dispOn
                                    + " tiers=" + tierById + " bits=" + Integer.toBinaryString(bits)
                                    + "] has " + positives + " positive-weight results (must be exactly 1)"
                                    + (positives > 1 ? ":" + positiveNames : ""));
                        }
                    }
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    private static boolean answerHasAttractionCheck(JsonArray results) {
        for (JsonElement r : results) {
            JsonObject result = r.getAsJsonObject();
            if (!result.has("conditions")) {
                continue;
            }
            for (JsonElement c : result.getAsJsonArray("conditions")) {
                JsonObject condition = c.getAsJsonObject();
                if (condition.has("conversations_check")
                        && condition.getAsJsonObject("conversations_check").get("axis").getAsString()
                                .equals(DispositionAxis.ATTRACTION.key())) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Evaluates one modeled condition (0 or 1) in a simulated state — mirrors the runtime adapters. */
    private static int conditionValue(JsonObject condition, boolean checksOn, boolean dispOn,
                                      Map<String, String> tierById, List<String> dispRanges, int bits,
                                      List<String> memoryIds) {
        if (condition.has("conversations_check")) {
            JsonObject check = condition.getAsJsonObject("conversations_check");
            if (!checksOn) {
                return 0;
            }
            return check.get("tier").getAsString()
                    .equals(tierById.get(check.get("id").getAsString())) ? 1 : 0;
        }
        if (condition.has("conversations_disposition")) {
            if (!dispOn) {
                return 0;
            }
            int index = dispRanges.indexOf(condition.getAsJsonObject("conversations_disposition").toString());
            return (bits & (1 << (2 + index))) != 0 ? 1 : 0;
        }
        if (condition.has("conversations_enabled")) {
            String feature = condition.get("conversations_enabled").getAsString();
            return featureOn(feature, checksOn, dispOn) ? 1 : 0;
        }
        if (condition.has("conversations_disabled")) {
            String feature = condition.get("conversations_disabled").getAsString();
            return featureOn(feature, checksOn, dispOn) ? 0 : 1;
        }
        if (condition.has("memory")) {
            int index = memoryIds.indexOf(memoryVariable(condition.getAsJsonObject("memory")));
            boolean has = (bits & (1 << (2 + dispRanges.size() + index))) != 0;
            boolean lacks = condition.getAsJsonObject("memory").has("dividend")
                    && condition.getAsJsonObject("memory").get("dividend").getAsDouble() < 0;
            return (lacks ? !has : has) ? 1 : 0;
        }
        return 0;
    }

    /**
     * The simulation variable behind a memory condition: the flag identity (id + player scoping),
     * NOT the whole condition object — the has-form and lacks-form of one flag must share a variable
     * or the simulation would enumerate impossible states.
     */
    private static String memoryVariable(JsonObject memory) {
        return memory.get("id").getAsString()
                + "|" + (memory.has("var") ? memory.get("var").getAsString() : "");
    }

    /** Only checks/dispositions vary in the simulation; other features are assumed on. */
    private static boolean featureOn(String feature, boolean checksOn, boolean dispOn) {
        return switch (feature) {
            case "checks" -> checksOn;
            case "dispositions" -> dispOn;
            default -> true;
        };
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
