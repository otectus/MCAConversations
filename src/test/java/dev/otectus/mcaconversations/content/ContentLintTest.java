package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.scene.InitiativePlanner;
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
import dev.otectus.mcaconversations.personality.Personalities;
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
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * Build-time lint over the shipped dialogue datapack and lang files: every condition/action key is
 * one MCA supports (verified against MCA 7.6.23) or one this mod registers; every memory id is
 * namespaced; every say/button/prompt key resolves in our {@code mca_dialogue} lang file. A typo
 * fails CI, not a play session.
 */
class ContentLintTest {

    /** Join separator for problem lists. */
    private static final String SEP = System.lineSeparator();

    private static final Path DIALOGUES = TestPaths.of("src/main/resources/data/mcaconversations/dialogues");
    private static final Path LANG = TestPaths.of("src/main/resources/assets/mca_dialogue/lang/en_us.json");

    /** MCA 7.6.23 condition vocabulary (from GiftPredicate registrations) + ours. */
    private static final Set<String> CONDITION_KEYS = Set.of(
            "chance", "personality", "mood", "age_group", "profession", "rank", "constraints",
            "time_min", "time_max", "advancement", "has_item", "item", "tag", "biome", "emeralds",
            "gender", "has_home", "has_village", "hearts", "hearts_min", "hearts_max", "is_married",
            "is_pregnant", "trait", "village_has_building", "current_chore", "min_health",
            "min_infection_progress", "min_pregnancy_progress", "pregnancy_child_gender", "memory",
            "conversations_enabled", "conversations_disabled", "conversations_gossip", "conversations_weather",
            "conversations_season", "conversations_holiday", "conversations_personality",
            "conversations_disposition", "conversations_check", "conversations_progress",
            "conversations_relationship",
            "conversations_quest_available", "conversations_quest_active", "conversations_quest_ready",
            "conversations_quest_completed",
            "conversations_reputation", "conversations_reputation_incident",
            "conversations_session", "conversations_budget",
            // Living histories, registered by LivingHistoriesRegistrar.
            "conversations_profile", "conversations_context", "conversations_episode",
            "conversations_thread", "conversations_commitment", "conversations_claim",
            "conversations_opinion", "conversations_recent", "conversations_scene",
            "conversations_exchange");

    /** MCA 7.6.23 action vocabulary (from Actions registrations) + ours. */
    private static final Set<String> ACTION_KEYS = Set.of(
            "next", "say", "positive", "negative", "command", "quit", "remember",
            "conversations_record", "conversations_say", "conversations_gossip_say",
            "conversations_disposition_apply", "conversations_quest_open",
            "conversations_session", "conversations_affection_apply", "conversations_progress_apply",
            "conversations_reputation_signal",
            // Living histories. Every one instantiates an authored template rather than a
            // shape, so a result can never invent an episode kind or an unregistered promise.
            "conversations_episode", "conversations_thread", "conversations_commitment",
            "conversations_claim", "conversations_opinion");

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
    /**
     * Values accepted by our own {@code conversations_personality} condition. MCA's native
     * {@code personality} condition is unusable across versions (it throws on an unknown id and
     * takes the datapack reload down with it), so every use of it was replaced — see
     * {@link #contentNeverUsesMcasCrashProneNativePersonalityCondition}.
     *
     * <p>Deliberately the full overlay roster, not just {@link Personalities#CANONICAL}. Three
     * tiers are legitimate here and they are not the same thing:
     *
     * <ul>
     *   <li><b>canonical</b> — the 14 personalities the target MCA actually rolls. These are the
     *       only ids a fresh villager can have, and {@code OverlayLintTest} requires a complete
     *       lang overlay for each.</li>
     *   <li><b>renamed aliases</b> ({@code witty}, {@code shy}, {@code lazy}, {@code grumpy}) —
     *       resolve to a canonical successor, so authoring either spelling matches the same
     *       villager.</li>
     *   <li><b>retained legacy</b> ({@code athletic}, {@code confident}, {@code peppy}) — no longer
     *       registered by MCA, so no new villager rolls one, but an upgraded save or a third-party
     *       pack can still present one. The condition simply never matches on a stock install; it
     *       cannot crash, because ours parses defensively.</li>
     * </ul>
     *
     * <p>What this still catches is the thing that matters: a typo or an invented id, which would
     * silently never match and quietly disable a line.
     */
    private static final Set<String> PERSONALITIES = Personalities.overlayPrefixes();
    private static final Set<String> AGE_GROUPS = Set.of("unassigned", "baby", "toddler", "child", "teen", "adult");
    private static final Set<String> RANKS = Set.of("outlaw", "peasant", "merchant", "noble", "mayor", "monarch");
    private static final Set<String> CONSTRAINTS = Set.of(
            "family", "spouse", "kids", "parent", "adult", "teen", "toddler", "baby", "engaged",
            "promised", "cleric", "adventurer", "mercenary", "outlawed", "trader", "orphan",
            "has_village", "following", "hit_by", "mayor", "monarch", "noble", "peasant");
    private static final Set<String> FEATURES = Set.of(
            "topics", "states", "templates", "gossip", "quests", "world", "dispositions", "checks",
            // "world" gates weather only; seasons and holidays have their own flags, and until
            // McaConversationsConfig.isFeatureEnabled learned them they fell through its default
            // and scored as permanently enabled, so a sink on either could never fire.
            "seasons", "holidays",
            "branching", "chat",
            // Living-histories switches. Each is gated by dynamic.enabled as well as its own, so
            // a sink on "dynamic" silences the whole layer and one on "episodes" silences a part.
            "dynamic", "identity", "episodes", "history", "social_opinions", "village_culture",
            "group");

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

    /** A Minecraft positional format argument: the {@code N} of {@code %N$s}. */
    private static final java.util.regex.Pattern FORMAT_ARG = java.util.regex.Pattern.compile("%(\\d+)\\$s");

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
    void contentNeverUsesMcasCrashProneNativePersonalityCondition() {
        // MCA parses `personality` with Personality.get(id).orElseThrow() inside Dialogues.apply,
        // which has no error containment: an id the running MCA does not know aborts the datapack
        // reload and the world fails to load. 7.7 removed witty/shy/lazy/grumpy/athletic, so any
        // native use is a crash on one MCA version or the other. Ours is parse-safe and alias-aware.
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (!result.has("conditions")) {
                return;
            }
            for (JsonElement c : result.getAsJsonArray("conditions")) {
                if (c.getAsJsonObject().has("personality")) {
                    problems.add(name + "/" + answerName
                            + ": uses MCA's native `personality` condition; use `conversations_personality`");
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
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
                checkValue(condition, "conversations_personality", PERSONALITIES, where, problems);
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
            String spoken = ContentFixture.spokenPhrase(actions);
            if (spoken != null) {
                requireLang("dialogue." + spoken, name + "/" + answerName, problems);
            }
            if (actions.has("conversations_say")) {
                requireLang("dialogue." + actions.getAsJsonObject("conversations_say").get("phrase").getAsString(),
                        name + "/" + answerName, problems);
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * A line may not name an argument its call site never passes. MCA's {@code getTranslatable} supplies
     * exactly one argument of its own — the spouse-aware player name at {@code %1$s} — so a plain
     * {@code say} caps there, and a {@code conversations_say} earns one further slot per declared var
     * and one per declared scene slot. Slots fill the positions after the vars, in declaration order,
     * which is the ordering the locale files depend on (spec 18.5).
     *
     * <p>Overrunning does not crash: {@code TranslatableContents.decompose} catches the format error and
     * renders the raw template instead, so the player reads a literal <em>"%2$s? It's home."</em> on
     * screen. Four shipped openers did exactly that, one of them behind all 21 personality overlays.
     */
    @Test
    void sayLinesNeverNameAnArgumentTheCallSiteDoesNotPass() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            String where = name + "/" + answerName;
            if (actions.has("say")) {
                checkArity(actions.get("say").getAsString(), 1, where + " say", problems);
            }
            if (actions.has("conversations_say")) {
                JsonObject say = actions.getAsJsonObject("conversations_say");
                int vars = say.has("vars") ? say.getAsJsonArray("vars").size() : 0;
                int slots = say.has("slots") ? say.getAsJsonArray("slots").size() : 0;
                checkArity(say.get("phrase").getAsString(), 1 + vars + slots,
                        where + " conversations_say", problems);
            }
        }));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    private static void checkArity(String key, int supplied, String where, List<String> problems) {
        int highest = 0;
        for (String line : LangKeys.linesOf(lang, "dialogue." + key)) {
            java.util.regex.Matcher m = FORMAT_ARG.matcher(line);
            while (m.find()) {
                highest = Math.max(highest, Integer.parseInt(m.group(1)));
            }
        }
        if (highest > supplied) {
            problems.add(where + ": " + key + " names %" + highest + "$s but the call site passes only "
                    + supplied + " argument(s)");
        }
    }

    /**
     * A declared var that no line in the pool reads is a mistake, not a spare. Either the sentence meant
     * to name it and wrote {@code %1$s} — which puts the player's name in the noun slot, as in
     * <em>"In Steve you don't ask why"</em> — or the var should not be declared at all. Six pools shipped
     * that way, so the season, weather, holiday and gift hooks never actually reached the screen.
     *
     * <p>An individual variant may skip a var; the pool as a whole may not.
     */
    @Test
    void everyDeclaredTemplateVarIsReadBySomeVariant() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            if (!actions.has("conversations_say")) {
                return;
            }
            JsonObject say = actions.getAsJsonObject("conversations_say");
            if (!say.has("vars")) {
                return;
            }
            String key = say.get("phrase").getAsString();
            List<String> lines = LangKeys.linesOf(lang, "dialogue." + key);
            JsonArray vars = say.getAsJsonArray("vars");
            for (int i = 0; i < vars.size(); i++) {
                String token = "%" + (i + 2) + "$s";
                if (lines.stream().noneMatch(line -> line.contains(token))) {
                    problems.add(name + "/" + answerName + ": " + key + " declares var '"
                            + vars.get(i).getAsString() + "' but no variant reads " + token);
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * {@code say} and {@code conversations_say} both push a finished line to the player, so a result
     * carrying both sends two packets and the client keeps whichever landed last. The earlier line — and
     * any template var it resolved — is bought and thrown away.
     */
    @Test
    void noResultSpeaksTwice() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("say") && actions.has("conversations_say")) {
                problems.add(name + "/" + answerName + ": speaks both conversations_say '"
                        + actions.getAsJsonObject("conversations_say").get("phrase").getAsString()
                        + "' and say '" + actions.get("say").getAsString() + "'; only the last one shows");
            }
        }));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * Weights accumulate per condition entry, so a term listed twice counts twice. Both spellings of that
     * mistake shipped, and both silently unreach a branch:
     *
     * <ul>
     *   <li>the same entry written twice — seven results repeated their own {@code -1000} cooldown and
     *       scored {@code -2000}, losing to the fallback they were written to beat;</li>
     *   <li>one term both boosted and sunk — three repeat branches paired {@code +1000} with
     *       {@code -1000} on their own cooldown memory, netting zero, so the "you already asked me"
     *       line could never win and the opener fell through to the legacy heart-paying result.</li>
     * </ul>
     */
    @Test
    void conditionsNeitherRepeatNorCancelThemselves() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            if (!result.has("conditions")) {
                return;
            }
            String where = name + "/" + answerName;
            Set<String> seen = new HashSet<>();
            Map<String, Integer> signs = new HashMap<>();
            for (JsonElement e : result.getAsJsonArray("conditions")) {
                JsonObject condition = e.getAsJsonObject();
                if (!seen.add(condition.toString())) {
                    problems.add(where + ": condition listed twice, doubling its weight: " + condition);
                }
                JsonObject term = condition.deepCopy();
                term.remove("chance");
                int sign = Integer.signum(condition.has("chance") ? condition.get("chance").getAsInt() : 0);
                Integer previous = signs.put(term.toString(), sign);
                if (previous != null && previous * sign < 0) {
                    problems.add(where + ": condition is both boosted and sunk, cancelling itself: " + term);
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
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
        // `main` joined this set in 1.0.0: additive hub entry injects the Conversations button as
        // an extra answer merged into MCA's own main menu, so MCA owns the dialogue.main prompt.
        Set<String> extensions = Set.of("greet", "main");
        questions.entrySet().stream()
                .filter(e -> !extensions.contains(e.getKey()))
                .filter(e -> !(e.getValue().has("auto") && e.getValue().get("auto").getAsBoolean()))
                .forEach(e -> assertTrue(LangKeys.hasLine(lang, "dialogue." + e.getKey()),
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
                String spoken = ContentFixture.spokenPhrase(actions);
                if (spoken != null) {
                    referenced.add("dialogue." + spoken);
                }
                if (actions.has("conversations_say")) {
                    referenced.add("dialogue." + actions.getAsJsonObject("conversations_say").get("phrase").getAsString());
                }
            });
        });
        for (String prefix : gossipPrefixesInUse()) {
            for (GossipEventType type : GossipEventType.values()) {
                referenced.add("dialogue." + prefix + "." + type.jsonName());
            }
            referenced.add("dialogue." + prefix + ".none");
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
            // dialogue.mcareputation.gossip.* are the external gossip voices (§30.4): the phrase key
            // arrives from MCA: Reputation's incident definitions at runtime and is rendered by
            // GossipConditionLogic, never referenced from any dialogue JSON.
            if (base.startsWith("dialogue.mcareputation.gossip.")) {
                continue;
            }
            // dialogue.conversations.initiative.* are the lines a villager opens with when it has
            // something of its own to raise. Spoken from InitiativePlanner, which reaches them by
            // purpose key rather than through any dialogue file, so nothing references them here.
            if (base.startsWith("dialogue." + InitiativePlanner.PHRASE_PREFIX)) {
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
        // A say key earns the relaxed floor by being a check tier, which is a property of the RESULT
        // that speaks it, not of how the key is spelled. Keying off the name let
        // conversations.fears.{challenge,press}.success take the relaxed floor while also serving as
        // the checks-disabled fallback — the line every player with checks off hears — on two
        // variants. A key spoken from anywhere without a conversations_check pays the full floor.
        Set<String> sayKeys = new HashSet<>();
        Set<String> alwaysChecked = new HashSet<>();
        Set<String> spokenUnchecked = new HashSet<>();
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            List<String> spoken = new ArrayList<>();
            JsonObject actions = result.getAsJsonObject("actions");
            String said = ContentFixture.spokenPhrase(actions);
            if (said != null) {
                spoken.add(said);
            }
            if (actions.has("conversations_say")) {
                spoken.add(actions.getAsJsonObject("conversations_say").get("phrase").getAsString());
            }
            boolean checked = false;
            if (result.has("conditions")) {
                for (JsonElement c : result.getAsJsonArray("conditions")) {
                    if (c.getAsJsonObject().has("conversations_check")) {
                        checked = true;
                    }
                }
            }
            sayKeys.addAll(spoken);
            (checked ? alwaysChecked : spokenUnchecked).addAll(spoken);
        }));
        alwaysChecked.removeAll(spokenUnchecked);

        List<String> problems = new ArrayList<>();
        for (String key : sayKeys) {
            if (key.equals("conversations.food.trait.sirben")) {
                continue;
            }
            int floor = key.startsWith("conversations.work.prof.") || key.startsWith("conversations.food.trait.")
                    || key.endsWith(".child") || key.endsWith(".teen")
                    // Check-tier and guard lines are precision-targeted (one tier of one stance).
                    || alwaysChecked.contains(key) || key.endsWith(".guard") ? 2 : 3;
            String base = "dialogue." + key;
            // Count only the /N entries. MCA's PooledTranslationStorage indexes nothing else, and
            // mca$onGet always draws from the pool when one exists, so a plain base sentence sitting
            // beside a variant is never shown to anyone. The bare key counts as a line of its own
            // only when it has no variants at all.
            int pool = 0;
            for (int i = 1; lang.containsKey(base + "/" + i); i++) {
                pool++;
            }
            if (pool == 0 && lang.containsKey(base)) {
                pool = 1;
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
            if (!LangKeys.hasLine(lang, "dialogue.conversations.work.prof." + path)) {
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
    void gossipTypeLinesExistForEveryPrefixInUse() {
        List<String> problems = new ArrayList<>();
        for (String prefix : gossipPrefixesInUse()) {
            for (GossipEventType type : GossipEventType.values()) {
                if (!LangKeys.hasLine(lang, "dialogue." + prefix + "." + type.jsonName())) {
                    problems.add("gossip prefix '" + prefix + "' has no line for " + type
                            + " — a phrase_prefix must cover every type it can be asked to tell");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * Every prefix {@code conversations_gossip_say} can render through, including the default.
     *
     * <p>The action takes an optional {@code phrase_prefix} so a branch can tell the same events in
     * a different voice — a child hearing that someone died should not get the adult line. Whichever
     * prefixes the content actually uses have to be complete, or a villager renders a raw lang key
     * at the one moment the line matters.
     */
    private static Set<String> gossipPrefixesInUse() {
        Set<String> prefixes = new HashSet<>();
        prefixes.add("conversations.gossip");
        questions.forEach((name, json) -> forEachResult(json, (answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("conversations_gossip_say")) {
                JsonObject say = actions.getAsJsonObject("conversations_gossip_say");
                if (say.has("phrase_prefix")) {
                    prefixes.add(say.get("phrase_prefix").getAsString());
                }
            }
        }));
        return prefixes;
    }

    /**
     * The shape MCA's pool builder actually reads: a family is either a single plain key or a
     * contiguous {@code /1../N} run, never both. {@code PooledTranslationStorage} indexes only keys
     * matching {@code /[0-9]+$}, and {@code mca$onGet} always draws from that index once it is
     * non-empty — so a plain base sentence left beside a variant is dead content, and a hole in the
     * run is an authored line the builder will never pick.
     */
    @Test
    void conversationsVariantSequencesHaveNoHoles() {
        for (String key : lang.keySet()) {
            if (key.contains("/") && key.startsWith("dialogue.conversations")) {
                String base = key.substring(0, key.indexOf('/'));
                int n = Integer.parseInt(key.substring(key.indexOf('/') + 1));
                assertTrue(!lang.containsKey(base),
                        "pooled key " + base + " also carries a plain base sentence, which MCA can "
                                + "never show — its pool builder indexes only /N. Fold it in as a variant.");
                for (int i = 1; i <= n; i++) {
                    assertTrue(lang.containsKey(base + "/" + i), base + " variant sequence has a hole at /" + i);
                }
            }
        }
    }

    /**
     * Five of our pools deliberately EXTEND MCA's own rather than starting fresh: MCA ships
     * {@code dialogue.main/1../7} and we append {@code /8../12} into the same shared namespace. They
     * must keep their offsets — renumbering one down to {@code /1} would silently overwrite an MCA
     * line — and they must never grow a plain base key, which would shadow the whole shared pool.
     */
    @Test
    void mcaExtensionPoolsKeepTheirOffsets() {
        Map<String, Integer> firstIndex = Map.of(
                "dialogue.main", 8,
                "dialogue.greet.success", 6,
                "dialogue.greet.fail", 6,
                "dialogue.story.success", 10,
                "dialogue.shake_hand.success", 6);
        List<String> problems = new ArrayList<>();
        firstIndex.forEach((base, first) -> {
            if (lang.containsKey(base)) {
                problems.add(base + ": plain base key would shadow MCA's shared pool");
            }
            if (!lang.containsKey(base + "/" + first)) {
                problems.add(base + ": expected the run to start at /" + first);
            }
            if (lang.containsKey(base + "/" + (first - 1))) {
                problems.add(base + ": /" + (first - 1) + " overlaps MCA's own variants");
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
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

    /**
     * Two buttons in the same topic may read identically only if pressing them does the same thing.
     *
     * <p>Three adjacent {@code life} nodes all offered "Thank you for telling me.": on two of them it
     * was a free exit, on the third it paid a heart and moved trust. The player cannot tell those
     * apart, which makes the choice a coin flip dressed as a decision. Reusing a bare exit line
     * <em>across</em> topics is fine and deliberate — that is the mod's voice — so this only looks
     * inside one topic's family of nodes.
     */
    @Test
    void answerLabelsAreUniqueWithinATopic() {
        // topic -> label -> consequence signature -> where it was seen
        Map<String, Map<String, Map<String, List<String>>>> byTopic = new HashMap<>();
        questions.forEach((name, json) -> {
            String topic = topicOf(name);
            if (topic == null) {
                return;
            }
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                if (!answer.has("name")) {
                    continue;
                }
                String label = lang.get("dialogue." + name + "." + answer.get("name").getAsString());
                if (label == null) {
                    continue; // branchingContentIsLocalized reports the missing label
                }
                byTopic.computeIfAbsent(topic, t -> new HashMap<>())
                        .computeIfAbsent(label, l -> new HashMap<>())
                        .computeIfAbsent(consequenceSignature(answer), s -> new ArrayList<>())
                        .add(name + "/" + answer.get("name").getAsString());
            }
        });

        List<String> problems = new ArrayList<>();
        byTopic.forEach((topic, labels) -> labels.forEach((label, signatures) -> {
            if (signatures.size() > 1) {
                List<String> where = new ArrayList<>();
                signatures.values().forEach(where::addAll);
                where.sort(null);
                problems.add(topic + ": " + signatures.size() + " different consequences share the label \""
                        + label + "\" — " + String.join(", ", where));
            }
        }));
        problems.sort(null);
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /** The topic a branching node belongs to: conversations.{topic,arc}.&lt;topic&gt;.… */
    private static String topicOf(String question) {
        String[] parts = question.split("\\.");
        if (parts.length < 4 || !parts[0].equals("conversations")) {
            return null;
        }
        return parts[1].equals("topic") || parts[1].equals("arc") ? parts[2] : null;
    }

    /**
     * What pressing this button actually does, reduced to a comparable string. Only the durable,
     * player-visible effects count: hearts, the disposition vector, and progress state. Which line the
     * villager says back is deliberately excluded — two exits that differ only in flavour text are the
     * same choice.
     */
    private static String consequenceSignature(JsonObject answer) {
        Set<String> effects = new java.util.TreeSet<>();
        for (JsonElement r : answer.getAsJsonArray("results")) {
            JsonObject actions = r.getAsJsonObject().getAsJsonObject("actions");
            if (actions.has("conversations_affection_apply")) {
                effects.add("hearts=" + actions.getAsJsonObject("conversations_affection_apply").get("delta"));
            }
            if (actions.has("positive")) {
                effects.add("hearts=" + actions.get("positive"));
            }
            if (actions.has("negative")) {
                effects.add("hearts=-" + actions.get("negative"));
            }
            if (actions.has("conversations_disposition_apply")) {
                effects.add("vector=" + actions.getAsJsonObject("conversations_disposition_apply")
                        .getAsJsonObject("deltas"));
            }
            if (actions.has("conversations_progress_apply")) {
                effects.add("progress=" + actions.get("conversations_progress_apply"));
            }
        }
        return effects.toString();
    }

    /**
     * The deep topics may not speak each other's lines.
     *
     * <p>{@code life}, {@code dreams}, {@code hopes}, {@code regrets} and {@code secret} shared three
     * entire sub-trees byte-identically, so the refusal to tell you a secret and the reluctance to
     * discuss your hopes for the harvest were the same sentence: <em>"I could. I'm choosing not to.
     * There's a difference."</em> After two topics a player could predict the words as well as the
     * buttons.
     *
     * <p>Exits are exempt on purpose. A short parting line reused across topics — "Right you are." —
     * is the mod's voice, and the exemption is decided by the answer being an exit rather than by an
     * arbitrary word count, so a well-written seven-word goodbye is not punished for being long.
     */
    @Test
    void deepTopicsDoNotShareLines() {
        Set<String> deep = Set.of("life", "dreams", "hopes", "regrets", "secret", "fears");
        Map<String, Map<String, String>> byText = new HashMap<>();
        for (Map.Entry<String, String> e : lang.entrySet()) {
            String key = e.getKey();
            if (!key.startsWith("dialogue.conversations.")) {
                continue;
            }
            String rest = key.substring("dialogue.conversations.".length());
            String topic = rest.split("\\.")[0];
            if (!deep.contains(topic) || isExitLine(rest)) {
                continue;
            }
            byText.computeIfAbsent(e.getValue(), t -> new java.util.TreeMap<>()).putIfAbsent(topic, key);
        }

        List<String> problems = new ArrayList<>();
        byText.forEach((text, topics) -> {
            if (topics.size() > 1) {
                problems.add(String.join(" / ", topics.values()) + " all say \"" + text
                        + "\" — a deep topic has to sound like its own subject");
            }
        });
        problems.sort(null);
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /** True for the parting lines, which are deliberately shared across the whole mod. */
    private static boolean isExitLine(String keyWithoutPrefix) {
        String base = keyWithoutPrefix.split("/")[0];
        return base.endsWith(".leave") || base.endsWith(".back") || base.equals("back")
                || base.endsWith("close_leave") || base.endsWith(".give_space");
    }

    /**
     * The "nobody has ever done that for me" beat is rationed.
     *
     * <p>It fired at 25 distinct sites — one for essentially every kind act in the game. Each line is
     * good; collectively they establish that every villager in the valley has been ignored by
     * everyone forever until the player arrived, which is implausible, self-flattering, and cheapens
     * each individual moment by repeating it. Six sites keep it, all deepest-disclosure beats.
     *
     * <p>The pattern deliberately matches the <em>beat</em> and not the word: "Nobody's said
     * 'settled' yet" is a fact about the village and "Nobody's looked at me strangely since" is
     * evidence a secret held, and neither is a villager telling the player they are unique.
     */
    @Test
    void rewardBeatIsNotOverused() {
        java.util.regex.Pattern beat = java.util.regex.Pattern.compile(
                "Nobody(?:'s| has) (?:ever\\b|(?:said|asked|offered|given|wanted)\\s+(?:that|me|it)\\b)");
        List<String> uses = new ArrayList<>();
        List<String> mostPeople = new ArrayList<>();
        lang.forEach((key, value) -> {
            if (!key.startsWith("dialogue.conversations.")) {
                return;
            }
            if (beat.matcher(value).find()) {
                uses.add(key);
            }
            if (value.contains("Most people")) {
                mostPeople.add(key);
            }
        });
        uses.sort(null);
        mostPeople.sort(null);
        assertTrue(uses.size() <= 6, "the 'nobody has ever' reward beat is at " + uses.size()
                + " sites, and 6 is the ration — reserve it for the deepest disclosures and give the"
                + " rest another kind of acknowledgement (practical, deflecting, surprised-then-brisk,"
                + " reciprocal): " + String.join(SEP, uses));
        assertTrue(mostPeople.size() <= 10, "'Most people' is at " + mostPeople.size()
                + " sites and 10 is the ration: " + String.join(SEP, mostPeople));
    }

    /**
     * A variant pool exists so the villager does not say the same thing twice. Two members that
     * differ only in wording are one line and its editor's pass, and the pool is effectively shorter
     * than it looks.
     *
     * <p>Mostly advisory: everything above {@code ADVISORY} is logged for a human to read, because
     * "is this a different angle or the same one reworded?" is a judgement call. Above
     * {@code NEAR_DUPLICATE} it fails, because at that similarity it is not a judgement call any
     * more — the two lines are the same sentence with a synonym swapped, and the player will notice.
     * Overlay namespaces are included: their variants were measurably the worst in the mod.
     */
    @Test
    void variantPoolsAreNotParaphrases() throws IOException {
        final double ADVISORY = 0.65;
        final double NEAR_DUPLICATE = 0.80;

        Map<String, Map<String, String>> pools = new java.util.TreeMap<>();
        for (Path file : langFiles()) {
            String namespace = file.getParent().getParent().getFileName().toString();
            JsonObject json = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
            for (Map.Entry<String, JsonElement> e : json.entrySet()) {
                String key = e.getKey();
                String base = key.contains("/") ? key.substring(0, key.indexOf('/')) : key;
                pools.computeIfAbsent(namespace + ":" + base, p -> new java.util.TreeMap<>())
                        .put(key, e.getValue().getAsString());
            }
        }

        List<String> advisory = new ArrayList<>();
        List<String> failures = new ArrayList<>();
        pools.forEach((pool, members) -> {
            List<Map.Entry<String, String>> list = new ArrayList<>(members.entrySet());
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    double similarity = similarity(list.get(i).getValue(), list.get(j).getValue());
                    if (similarity <= ADVISORY) {
                        continue;
                    }
                    String note = String.format("%.2f  %s <-> %s", similarity,
                            list.get(i).getKey(), list.get(j).getKey());
                    (similarity > NEAR_DUPLICATE ? failures : advisory).add(note);
                }
            }
        });

        if (!advisory.isEmpty()) {
            System.out.println("[variantPoolsAreNotParaphrases] " + advisory.size()
                    + " pool(s) worth a second look (similarity > " + ADVISORY + "):");
            advisory.forEach(a -> System.out.println("    " + a));
        }
        assertTrue(failures.isEmpty(), failures.size() + " variant pair(s) are the same line reworded"
                + " (similarity > " + NEAR_DUPLICATE + "). Give each variant a different angle, not a"
                + " different wording:" + SEP + String.join(SEP, failures));
    }

    /** Normalized similarity in 0..1, from Levenshtein distance over case-folded text. */
    private static double similarity(String a, String b) {
        String x = a.toLowerCase(java.util.Locale.ROOT);
        String y = b.toLowerCase(java.util.Locale.ROOT);
        int longest = Math.max(x.length(), y.length());
        if (longest == 0) {
            return 1.0;
        }
        int[] previous = new int[y.length() + 1];
        int[] current = new int[y.length() + 1];
        for (int j = 0; j <= y.length(); j++) {
            previous[j] = j;
        }
        for (int i = 1; i <= x.length(); i++) {
            current[0] = i;
            for (int j = 1; j <= y.length(); j++) {
                int cost = x.charAt(i - 1) == y.charAt(j - 1) ? 0 : 1;
                current[j] = Math.min(Math.min(current[j - 1] + 1, previous[j] + 1), previous[j - 1] + cost);
            }
            int[] swap = previous;
            previous = current;
            current = swap;
        }
        return 1.0 - (double) previous[y.length()] / longest;
    }

    /** Every en_us lang file this mod ships, base pool and personality overlays alike. */
    private static List<Path> langFiles() throws IOException {
        try (var dirs = Files.list(TestPaths.of("src/main/resources/assets"))) {
            List<Path> files = new ArrayList<>();
            for (Path dir : dirs.toList()) {
                Path file = dir.resolve("lang").resolve("en_us.json");
                if (Files.exists(file)) {
                    files.add(file);
                }
            }
            return files;
        }
    }

    /**
     * A player label and its reply pool may only name detail that appears in <em>every</em> variant
     * of the line they answer.
     *
     * <p>"Well, the cat clearly won." answered a bad-day opener whose three variants were a cat
     * knocking the stew over, a sticking door and a dropped egg, so it was a non-sequitur two times
     * in three — and the payoff tripled down on it. Whether a word is "specific detail" is a
     * judgement, so the map below is curated rather than inferred: each entry names a pool and the
     * nouns that only some of its variants contain. Add a row when you write a pool whose variants
     * differ in their props.
     */
    @Test
    void labelsDoNotReferenceSingleVariantDetail() {
        // key prefix that answers a variable pool -> words only some of that pool's variants contain
        Map<String, List<String>> singleVariantDetail = Map.of(
                "dialogue.conversations.day.rough", List.of("cat", "stew", "egg", "bucket", "door"),
                // "puddle" is deliberately absent: weather.toddler.ask invites the child to name a
                // favourite sky, which is new information rather than an echo of the opener.
                "dialogue.conversations.weather.toddler", List.of("sheep", "thunder"),
                "dialogue.conversations.checkin.toddler", List.of("bug", "wiggled"),
                "dialogue.conversations.life.toddler", List.of("frog", "puddles"),
                "dialogue.conversations.day.toddler", List.of("mud", "butterfly"),
                "dialogue.conversations.fears.toddler", List.of("thunder", "mama", "bed"));

        List<String> problems = new ArrayList<>();
        singleVariantDetail.forEach((pool, nouns) -> {
            // Every key that answers this pool: same prefix, deeper path (a stance or its reply).
            for (Map.Entry<String, String> e : lang.entrySet()) {
                String key = e.getKey();
                if (!key.startsWith(pool + ".") && !key.startsWith(pool.replace(
                        "dialogue.conversations.", "dialogue.conversations.topic.") + ".")) {
                    continue;
                }
                String text = e.getValue().toLowerCase(java.util.Locale.ROOT);
                for (String noun : nouns) {
                    if (text.matches(".*\\b" + noun + "\\b.*")) {
                        problems.add(key + " says \"" + noun + "\", which only some variants of "
                                + pool + " mention — write to what every variant shares");
                    }
                }
            }
        });
        problems.sort(null);
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /** Every conversations.* question must be reachable from the hub via next edges — no orphan pages. */
    @Test
    void everyConversationsQuestionIsReachableFromHub() {
        Set<String> reached = new HashSet<>();
        // Every root this mod hangs an answer on: the hub, MCA's main menu (which carries the
        // Conversations button) and MCA's greeting menu (which carries the check-in topic).
        List<String> frontier = new ArrayList<>(List.of("conversations", "main", "greet"));
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
                String babble = ContentFixture.spokenPhrase(actions);
                if (!actions.keySet().equals(Set.of("next", "conversations_say"))
                        || !actions.get("next").getAsString().equals("main")
                        || babble == null || !babble.startsWith("conversations.babble.")) {
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
            if (ContentFixture.spokenPhrase(actions) == null) {
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
        if (!LangKeys.hasLine(lang, key)) {
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
