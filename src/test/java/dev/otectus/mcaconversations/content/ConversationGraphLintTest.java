package dev.otectus.mcaconversations.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.ConversationCatalog;
import dev.otectus.mcaconversations.conversation.DepthClass;
import dev.otectus.mcaconversations.conversation.TopicEntry;
import dev.otectus.mcaconversations.progress.AffectionMath;
import dev.otectus.mcaconversations.progress.ReplayPolicy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Structural lint for the branching conversation graph (plan §13.1).
 *
 * <p>{@code ContentLintTest} already checks that individual results are well formed — known keys,
 * resolvable lang, checked answers defining every tier, one behavioural result per state. This test
 * asks the questions that only make sense once topics become graphs: does a topic actually branch,
 * can the player always leave, does anything reward merely asking, can any single path out-earn its
 * budget, and is every piece of durable state both written somewhere and read somewhere.
 *
 * <p>Rules that depend on converted content are written to be <em>vacuously true</em> until that
 * content exists, so this file lands with the foundations and starts biting the moment a topic is
 * converted — rather than being written after the content it was supposed to police.
 */
class ConversationGraphLintTest {

    private static final String SEP = System.lineSeparator();

    private static final Path DIALOGUES = Path.of("src/main/resources/data/mcaconversations/dialogues");
    private static final Path CATALOG = Path.of("src/main/resources/data/mcaconversations/conversation_catalog");
    private static final Path LANG = Path.of("src/main/resources/assets/mca_dialogue/lang/en_us.json");

    /** Question-id prefixes that hold converted branching content. */
    private static final String TOPIC_PREFIX = "conversations.topic.";
    private static final String ARC_PREFIX = "conversations.arc.";

    /** Actions that make a result consequence-bearing, and are therefore banned on navigation. */
    private static final Set<String> CONSEQUENCE_ACTIONS = Set.of(
            "positive", "negative", "conversations_affection_apply",
            "conversations_disposition_apply", "conversations_progress_apply");

    /** MCA questions we may hand control back to. */
    private static final Set<String> MCA_QUESTIONS = Set.of("main", "greet", "root");

    private static Map<String, JsonObject> questions;
    private static ConversationCatalog catalog;
    private static Map<String, String> lang;

    @BeforeAll
    static void load() throws IOException {
        questions = new TreeMap<>();
        try (var files = Files.list(DIALOGUES)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                String id = file.getFileName().toString().replace(".json", "");
                questions.put(id, JsonParser.parseString(Files.readString(file)).getAsJsonObject());
            }
        }

        List<TopicEntry> topics = new ArrayList<>();
        if (Files.isDirectory(CATALOG)) {
            try (var files = Files.list(CATALOG)) {
                for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                    JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                    if (!root.has("topics")) {
                        continue;
                    }
                    for (Map.Entry<String, JsonElement> e : root.getAsJsonObject("topics").entrySet()) {
                        topics.add(TopicEntry.fromJson(e.getKey(), e.getValue().getAsJsonObject()));
                    }
                }
            }
        }
        catalog = ConversationCatalog.build(topics);

        lang = new TreeMap<>();
        JsonObject langJson = JsonParser.parseString(Files.readString(LANG)).getAsJsonObject();
        langJson.entrySet().forEach(e -> lang.put(e.getKey(), e.getValue().getAsString()));
    }

    // ------------------------------------------------------------------
    // Catalog integrity
    // ------------------------------------------------------------------

    @Test
    @DisplayName("every catalog topic points at a starter answer that really exists")
    void catalogStartersExist() {
        List<String> problems = new ArrayList<>();
        for (TopicEntry topic : catalog.topics()) {
            JsonObject question = questions.get(topic.entryQuestion());
            if (question == null) {
                problems.add(topic.id() + ": entry question '" + topic.entryQuestion() + "' does not exist");
                continue;
            }
            if (answer(question, topic.entryAnswer()).isEmpty()) {
                problems.add(topic.id() + ": entry question '" + topic.entryQuestion()
                        + "' has no answer '" + topic.entryAnswer() + "'");
            }
            if (!questions.containsKey(topic.returnQuestion())
                    && !MCA_QUESTIONS.contains(topic.returnQuestion())) {
                problems.add(topic.id() + ": return_question '" + topic.returnQuestion() + "' does not exist");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("arc, milestone and exclusive ids used in content are declared in the catalog")
    void progressIdsAreDeclared() {
        List<String> problems = new ArrayList<>();
        forEachResult((question, answerName, result) -> {
            String where = question + "/" + answerName;
            for (JsonObject entry : progressEntries(result)) {
                if (entry.has("arc") && catalog.byArc(entry.get("arc").getAsString()).isEmpty()) {
                    problems.add(where + ": arc '" + entry.get("arc").getAsString()
                            + "' is not declared by any catalog topic");
                }
                if (entry.has("milestone")
                        && catalog.byMilestone(entry.get("milestone").getAsString()).isEmpty()) {
                    problems.add(where + ": milestone '" + entry.get("milestone").getAsString()
                            + "' is not declared by any catalog topic");
                }
                if (entry.has("exclusive")) {
                    String group = entry.get("exclusive").getAsString();
                    if (catalog.byExclusiveGroup(group).isEmpty()) {
                        problems.add(where + ": exclusive group '" + group + "' is not declared");
                    } else if (entry.has("member")
                            && !catalog.isExclusiveMember(group, entry.get("member").getAsString())) {
                        problems.add(where + ": '" + entry.get("member").getAsString()
                                + "' is not a declared member of exclusive group '" + group + "'");
                    }
                }
            }
            for (JsonObject condition : conditions(result)) {
                if (!condition.has("conversations_progress")) {
                    continue;
                }
                JsonObject query = condition.getAsJsonObject("conversations_progress");
                if (query.has("arc") && catalog.byArc(query.get("arc").getAsString()).isEmpty()) {
                    problems.add(where + ": condition reads undeclared arc '"
                            + query.get("arc").getAsString() + "'");
                }
                if (query.has("milestone")
                        && catalog.byMilestone(query.get("milestone").getAsString()).isEmpty()) {
                    problems.add(where + ": condition reads undeclared milestone '"
                            + query.get("milestone").getAsString() + "'");
                }
                if (query.has("exclusive")
                        && catalog.byExclusiveGroup(query.get("exclusive").getAsString()).isEmpty()) {
                    problems.add(where + ": condition reads undeclared exclusive group '"
                            + query.get("exclusive").getAsString() + "'");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("no durable state is write-only or read-only: every writer has a reader and vice versa")
    void milestonesAndArcsAreBothWrittenAndRead() {
        Set<String> milestonesWritten = new LinkedHashSet<>();
        Set<String> milestonesRead = new LinkedHashSet<>();
        Set<String> arcsWritten = new LinkedHashSet<>();
        Set<String> arcsRead = new LinkedHashSet<>();
        Set<String> groupsWritten = new LinkedHashSet<>();
        Set<String> groupsRead = new LinkedHashSet<>();

        forEachResult((question, answerName, result) -> {
            for (JsonObject entry : progressEntries(result)) {
                if (entry.has("milestone")) {
                    milestonesWritten.add(entry.get("milestone").getAsString());
                }
                if (entry.has("arc")) {
                    arcsWritten.add(entry.get("arc").getAsString());
                }
                if (entry.has("exclusive")) {
                    groupsWritten.add(entry.get("exclusive").getAsString());
                }
            }
            for (JsonObject condition : conditions(result)) {
                if (!condition.has("conversations_progress")) {
                    continue;
                }
                JsonObject query = condition.getAsJsonObject("conversations_progress");
                if (query.has("milestone")) {
                    milestonesRead.add(query.get("milestone").getAsString());
                }
                if (query.has("arc")) {
                    arcsRead.add(query.get("arc").getAsString());
                }
                if (query.has("exclusive")) {
                    groupsRead.add(query.get("exclusive").getAsString());
                }
            }
            // A check seeded on an arc counts as reading it: the stage genuinely changes the outcome.
            for (JsonObject condition : conditions(result)) {
                if (condition.has("conversations_check")) {
                    JsonObject check = condition.getAsJsonObject("conversations_check");
                    if (check.has("arc")) {
                        arcsRead.add(check.get("arc").getAsString());
                    }
                }
            }
        });

        List<String> problems = new ArrayList<>();
        milestonesWritten.stream().filter(id -> !milestonesRead.contains(id)).forEach(id ->
                problems.add("milestone '" + id + "' is set but never read back — dead state"));
        milestonesRead.stream().filter(id -> !milestonesWritten.contains(id)).forEach(id ->
                problems.add("milestone '" + id + "' is read but nothing can ever set it — dangling callback"));
        arcsWritten.stream().filter(id -> !arcsRead.contains(id)).forEach(id ->
                problems.add("arc '" + id + "' advances but nothing reads its stage — invisible progress"));
        arcsRead.stream().filter(id -> !arcsWritten.contains(id)).forEach(id ->
                problems.add("arc '" + id + "' is read but nothing advances it — unreachable branch"));
        groupsWritten.stream().filter(id -> !groupsRead.contains(id)).forEach(id ->
                problems.add("exclusive group '" + id + "' is decided but never referenced again"));
        groupsRead.stream().filter(id -> !groupsWritten.contains(id)).forEach(id ->
                problems.add("exclusive group '" + id + "' is read but nothing can decide it"));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    // ------------------------------------------------------------------
    // MCA engine invariants this mod's content must respect
    // ------------------------------------------------------------------

    @Test
    @DisplayName("an auto question has exactly one answer, because MCA picks one at random")
    void autoQuestionsAreDeterministic() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> {
            if (!json.has("auto") || !json.get("auto").getAsBoolean()) {
                return;
            }
            int answers = json.getAsJsonArray("answers").size();
            if (answers != 1) {
                problems.add(name + ": auto question has " + answers + " answers; MCA calls"
                        + " getRandomAnswer() on an auto question, so more than one is a coin flip");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("a branching-disabled fallback is the LAST result, because MCA falls back to the last")
    void legacyFallbacksComeLast() {
        List<String> problems = new ArrayList<>();
        forEachAnswer((question, answerName, results) -> {
            int fallbackIndex = -1;
            for (int i = 0; i < results.size(); i++) {
                JsonObject result = results.get(i).getAsJsonObject();
                for (JsonObject condition : conditions(result)) {
                    if (condition.has("conversations_enabled")
                            && "branching".equals(condition.get("conversations_enabled").getAsString())
                            && condition.get("chance").getAsInt() < 0) {
                        fallbackIndex = i;
                    }
                }
            }
            if (fallbackIndex >= 0 && fallbackIndex != results.size() - 1) {
                problems.add(question + "/" + answerName + ": the branching-disabled fallback is at index "
                        + fallbackIndex + " of " + results.size()
                        + "; MCA selects the LAST result when everything scores <= 0, so it must be last");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    // ------------------------------------------------------------------
    // Reward hygiene
    // ------------------------------------------------------------------

    @Test
    @DisplayName("pure navigation rewards nothing at all")
    void navigationIsNeverRewarded() {
        List<String> problems = new ArrayList<>();
        forEachResult((question, answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            // A pure hop: it moves the player somewhere and says nothing. Back, category buttons,
            // "never mind", "something else". These may never carry a consequence, converted or not.
            boolean pureHop = actions.has("next") && !actions.has("say")
                    && !actions.has("conversations_say") && !actions.has("conversations_gossip_say");
            if (!pureHop) {
                return;
            }
            for (String action : CONSEQUENCE_ACTIONS) {
                if (actions.has(action)) {
                    problems.add(question + "/" + answerName + ": '" + action
                            + "' on a navigation hop — moving around a menu is not a relationship event");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("a converted topic's opener rewards nothing — the reply does")
    void convertedOpenersAreRewardFree() {
        List<String> problems = new ArrayList<>();
        for (TopicEntry topic : catalog.topics()) {
            if (!isConverted(topic)) {
                continue;
            }
            JsonObject question = questions.get(topic.entryQuestion());
            if (question == null) {
                continue;
            }
            answer(question, topic.entryAnswer()).ifPresent(answer -> {
                for (JsonElement r : answer.getAsJsonArray("results")) {
                    JsonObject actions = r.getAsJsonObject().getAsJsonObject("actions");
                    // The branching-disabled fallback keeps its legacy payout on purpose: with the
                    // feature off, the topic is the 1.0.0 one-liner and must behave like it.
                    if (isLegacyFallback(r.getAsJsonObject())) {
                        continue;
                    }
                    for (String action : CONSEQUENCE_ACTIONS) {
                        if (actions.has(action)) {
                            problems.add(topic.entryQuestion() + "/" + topic.entryAnswer() + ": '" + action
                                    + "' on the opener of converted topic '" + topic.id()
                                    + "' — asking a question is not kindness, answering one is");
                        }
                    }
                }
            });
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * The migration ledger. Every starter that still pays out for merely being clicked is listed here
     * by name, and the list must match reality exactly: convert a topic without removing its row and
     * this fails, add a new rewarded starter without listing it and this fails too.
     *
     * <p>It shrinks to empty as the phases land. That is the point — the debt is a visible number in
     * a test rather than a paragraph in a design document.
     */
    private static final Set<String> LEGACY_REWARDED_STARTERS = Set.of(
            "conversations.cat.chitchat/food",
            "conversations.cat.chitchat/season",
            "conversations.cat.chitchat/weather",
            "conversations.cat.events/news",
            "conversations.cat.events/noticed",
            "conversations.cat.personal/dreams",
            "conversations.cat.personal/feelings",
            "conversations.cat.personal/hopes",
            "conversations.cat.personal/life",
            "conversations.cat.personal/regrets",
            "conversations.cat.personal/secret",
            "conversations.cat.profession/work",
            "conversations.cat.village/people",
            "conversations.cat.village/rumors",
            "conversations.cat.village/village",
            "conversations.dreams/ask_more",
            "conversations.dreams/encourage",
            "conversations.family/ask_parent",
            "conversations.family/checkin_child",
            "conversations.family/memories",
            "conversations.fears/challenge",
            "conversations.fears/comfort",
            "conversations.fears/press",
            "conversations.fears/share",
            "conversations.feelings/same",
            "conversations.feelings/unsure",
            "conversations.us/firstmet",
            "conversations.us/future",
            "conversations.us/happy",
            "conversations.us/worries",
            "conversations.work/(auto)",
            "greet/checkin");

    @Test
    @DisplayName("the list of not-yet-converted rewarded starters matches reality exactly")
    void legacyRewardLedgerIsAccurate() {
        Set<String> actual = new LinkedHashSet<>();
        forEachResult((question, answerName, result) -> {
            // A converted topic keeps its 1.0.0 payout on the branching-disabled fallback on purpose,
            // so that off-state really is the old experience. That is not unconverted debt.
            if (isLegacyFallback(result)) {
                return;
            }
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("positive") || actions.has("negative")) {
                actual.add(question + "/" + answerName);
            }
        });

        List<String> problems = new ArrayList<>();
        actual.stream().filter(id -> !LEGACY_REWARDED_STARTERS.contains(id)).forEach(id ->
                problems.add("'" + id + "' pays out for being clicked but is not on the migration ledger."
                        + " Either convert it to a branching tree or add it to LEGACY_REWARDED_STARTERS"));
        LEGACY_REWARDED_STARTERS.stream().filter(id -> !actual.contains(id)).forEach(id ->
                problems.add("'" + id + "' is on the migration ledger but no longer pays out for being"
                        + " clicked — delete its row, the debt is paid"));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /** True for the result that keeps the 1.0.0 behaviour when the branching feature is switched off. */
    private static boolean isLegacyFallback(JsonObject result) {
        for (JsonObject condition : conditions(result)) {
            if (condition.has("conversations_enabled")
                    && "branching".equals(condition.get("conversations_enabled").getAsString())
                    && condition.get("chance").getAsInt() < 0) {
                return true;
            }
        }
        return false;
    }

    @Test
    @DisplayName("converted branching content never writes hearts through MCA's unguarded actions")
    void branchingContentUsesTheGuardedAffectionAction() {
        List<String> problems = new ArrayList<>();
        forEachResult((question, answerName, result) -> {
            if (!isBranchingNode(question)) {
                return;
            }
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("positive") || actions.has("negative")) {
                problems.add(question + "/" + answerName + ": native positive/negative inside branching"
                        + " content bypasses every budget and repeat guard; use conversations_affection_apply");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every affection action declares a stable decision id and an explicit replay policy")
    void affectionActionsDeclareTheirContract() {
        List<String> problems = new ArrayList<>();
        Map<String, Set<String>> decisionSites = new HashMap<>();
        forEachResult((question, answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            if (!actions.has("conversations_affection_apply")) {
                return;
            }
            String where = question + "/" + answerName;
            JsonObject affection = actions.getAsJsonObject("conversations_affection_apply");
            if (!affection.has("decision")) {
                problems.add(where + ": affection action without a stable decision id");
                return;
            }
            String decision = affection.get("decision").getAsString();
            decisionSites.computeIfAbsent(decision, k -> new LinkedHashSet<>()).add(where);
            if (!affection.has("policy")) {
                problems.add(where + ": affection action '" + decision + "' does not declare a replay policy");
            } else if (ReplayPolicy.byKey(affection.get("policy").getAsString()).isEmpty()) {
                problems.add(where + ": unknown replay policy '" + affection.get("policy").getAsString() + "'");
            }
            if (!affection.has("delta")) {
                problems.add(where + ": affection action '" + decision + "' has no delta");
            } else {
                int delta = affection.get("delta").getAsInt();
                if (Math.abs(delta) > AffectionMath.MAX_AUTHORED_DELTA) {
                    problems.add(where + ": delta " + delta + " exceeds the authored bound of ±"
                            + AffectionMath.MAX_AUTHORED_DELTA);
                }
                if (delta == 0) {
                    problems.add(where + ": a zero-delta affection action is noise; drop it instead");
                }
            }
        });
        // A decision id keys anti-farming and tests, so the same id must not mean two different things.
        decisionSites.forEach((decision, sites) -> {
            long distinctAnswers = sites.stream().map(s -> s.substring(0, s.lastIndexOf('/'))).distinct().count();
            if (distinctAnswers > 1) {
                problems.add("decision id '" + decision + "' is used by unrelated answers " + sites
                        + "; ids key anti-farming and must mean one thing");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    // ------------------------------------------------------------------
    // Shape of a real conversation
    // ------------------------------------------------------------------

    @Test
    @DisplayName("every decision node offers 2-5 answers and always a way out")
    void decisionNodesAreWellShaped() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> {
            if (!isBranchingNode(name)) {
                return;
            }
            JsonArray answers = json.getAsJsonArray("answers");
            if (answers.size() < 2) {
                problems.add(name + ": a decision node with " + answers.size()
                        + " answer(s) is not a decision");
            }
            if (answers.size() > DepthClass.MAX_DECISIONS) {
                problems.add(name + ": " + answers.size() + " answers exceeds the "
                        + DepthClass.MAX_DECISIONS + " that fit at the smallest GUI scale");
            }
            boolean hasExit = false;
            for (JsonElement a : answers) {
                JsonObject answer = a.getAsJsonObject();
                boolean consequenceFree = true;
                for (JsonElement r : answer.getAsJsonArray("results")) {
                    JsonObject actions = r.getAsJsonObject().getAsJsonObject("actions");
                    for (String action : CONSEQUENCE_ACTIONS) {
                        if (actions.has(action)) {
                            consequenceFree = false;
                        }
                    }
                }
                if (consequenceFree) {
                    hasExit = true;
                }
            }
            if (!hasExit) {
                problems.add(name + ": no consequence-free way out — every node needs a graceful exit");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("no branching node is orphaned and no next hop dangles")
    void graphIsConnected() {
        Set<String> targets = new HashSet<>();
        forEachResult((question, answerName, result) -> {
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("next")) {
                targets.add(actions.get("next").getAsString());
            }
        });

        List<String> problems = new ArrayList<>();
        for (String target : targets) {
            if (!questions.containsKey(target) && !MCA_QUESTIONS.contains(target)) {
                problems.add("next target '" + target + "' does not exist");
            }
        }
        questions.keySet().stream().filter(ConversationGraphLintTest::isBranchingNode)
                .filter(name -> !targets.contains(name))
                .forEach(name -> problems.add("branching node '" + name + "' is unreachable — nothing nexts to it"));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("each catalog topic reaches its declared minimum number of player decisions")
    void topicsMeetTheirDepthFloor() {
        List<String> problems = new ArrayList<>();
        for (TopicEntry topic : catalog.topics()) {
            if (!isConverted(topic)) {
                continue; // not converted yet; §16 tracks the remaining topics, not this rule
            }
            int deepest = 0;
            for (String entry : openerTargets(topic)) {
                deepest = Math.max(deepest, longestDecisionChain(entry, new ArrayDeque<>()));
            }
            if (deepest < topic.depth().minDecisions()) {
                problems.add(topic.id() + ": deepest path offers " + deepest + " player decision(s) but "
                        + topic.depth().key() + " requires " + topic.depth().minDecisions());
            }
            if (deepest > DepthClass.MAX_DECISIONS) {
                problems.add(topic.id() + ": " + deepest + " chained decisions exceeds the cap of "
                        + DepthClass.MAX_DECISIONS);
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("no single path through a topic can out-earn or out-lose its depth budget")
    void pathTotalsStayInsideTheBudget() {
        List<String> problems = new ArrayList<>();
        for (TopicEntry topic : catalog.topics()) {
            if (!isConverted(topic)) {
                continue;
            }
            for (String entry : openerTargets(topic)) {
                int[] worst = worstCaseTotals(entry, new ArrayDeque<>());
                if (worst[0] > topic.depth().positiveBudget()) {
                    problems.add(topic.id() + ": a path can gain " + worst[0] + " hearts but "
                            + topic.depth().key() + " allows " + topic.depth().positiveBudget());
                }
                if (worst[1] > topic.depth().negativeBudget()) {
                    problems.add(topic.id() + ": a path can lose " + worst[1] + " hearts but "
                            + topic.depth().key() + " allows " + topic.depth().negativeBudget());
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every converted topic can be played well and can be played badly")
    void everyTopicHasABetterAndAWorseRoute() {
        List<String> problems = new ArrayList<>();
        for (TopicEntry topic : catalog.topics()) {
            if (!isConverted(topic)) {
                continue;
            }
            boolean positive = false;
            boolean negative = false;
            for (String entry : openerTargets(topic)) {
                int[] worst = worstCaseTotals(entry, new ArrayDeque<>());
                positive |= worst[0] > 0;
                negative |= worst[1] > 0;
            }
            if (!positive) {
                problems.add(topic.id() + ": no path can gain affection — nothing is at stake");
            }
            if (!negative) {
                problems.add(topic.id() + ": no path can lose affection — there is no wrong thing to say");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every branching node has a prompt and every answer has a label")
    void branchingContentIsLocalized() {
        List<String> problems = new ArrayList<>();
        questions.forEach((name, json) -> {
            if (!isBranchingNode(name)) {
                return;
            }
            if (!lang.containsKey("dialogue." + name)) {
                problems.add(name + ": no prompt text (dialogue." + name + ")");
            }
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                if (!answer.has("name")) {
                    continue;
                }
                String key = "dialogue." + name + "." + answer.get("name").getAsString();
                if (!lang.containsKey(key)) {
                    problems.add(name + ": no button label for '" + answer.get("name").getAsString()
                            + "' (" + key + ")");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    // ------------------------------------------------------------------
    // Graph walking helpers
    // ------------------------------------------------------------------

    /** The nodes a topic's starter answer routes into (its opener branches). */
    private static Set<String> openerTargets(TopicEntry topic) {
        Set<String> targets = new LinkedHashSet<>();
        JsonObject question = questions.get(topic.entryQuestion());
        if (question == null) {
            return targets;
        }
        answer(question, topic.entryAnswer()).ifPresent(answer -> {
            for (JsonElement r : answer.getAsJsonArray("results")) {
                JsonObject actions = r.getAsJsonObject().getAsJsonObject("actions");
                if (actions.has("next") && isBranchingNode(actions.get("next").getAsString())) {
                    targets.add(actions.get("next").getAsString());
                }
            }
        });
        return targets;
    }

    /** True once a topic actually routes into a branching node — i.e. has been converted. */
    private static boolean isConverted(TopicEntry topic) {
        return !openerTargets(topic).isEmpty();
    }

    /** How many player decisions the longest cycle-free path from {@code node} offers. */
    private static int longestDecisionChain(String node, Deque<String> visiting) {
        JsonObject question = questions.get(node);
        if (question == null || visiting.contains(node) || visiting.size() > DepthClass.MAX_DECISIONS + 2) {
            return 0;
        }
        visiting.push(node);
        int deepest = 0;
        for (JsonElement a : question.getAsJsonArray("answers")) {
            for (JsonElement r : a.getAsJsonObject().getAsJsonArray("results")) {
                JsonObject actions = r.getAsJsonObject().getAsJsonObject("actions");
                if (actions.has("next") && isBranchingNode(actions.get("next").getAsString())) {
                    deepest = Math.max(deepest, longestDecisionChain(actions.get("next").getAsString(), visiting));
                }
            }
        }
        visiting.pop();
        return 1 + deepest;
    }

    /**
     * The worst case a single cycle-free path can reach: {@code [maxGain, maxLoss]}, both as positive
     * numbers. Deliberately pessimistic — it takes the best-paying result of each answer, because the
     * budget must hold for the luckiest possible traversal, not the average one.
     */
    private static int[] worstCaseTotals(String node, Deque<String> visiting) {
        JsonObject question = questions.get(node);
        if (question == null || visiting.contains(node) || visiting.size() > DepthClass.MAX_DECISIONS + 2) {
            return new int[]{0, 0};
        }
        visiting.push(node);
        int bestGain = 0;
        int worstLoss = 0;
        for (JsonElement a : question.getAsJsonArray("answers")) {
            for (JsonElement r : a.getAsJsonObject().getAsJsonArray("results")) {
                JsonObject result = r.getAsJsonObject();
                JsonObject actions = result.getAsJsonObject("actions");
                int delta = 0;
                if (actions.has("conversations_affection_apply")) {
                    delta = actions.getAsJsonObject("conversations_affection_apply").get("delta").getAsInt();
                }
                int[] downstream = {0, 0};
                if (actions.has("next") && isBranchingNode(actions.get("next").getAsString())) {
                    downstream = worstCaseTotals(actions.get("next").getAsString(), visiting);
                }
                bestGain = Math.max(bestGain, Math.max(0, delta) + downstream[0]);
                worstLoss = Math.max(worstLoss, Math.max(0, -delta) + downstream[1]);
            }
        }
        visiting.pop();
        return new int[]{bestGain, worstLoss};
    }

    private static boolean isBranchingNode(String question) {
        return question.startsWith(TOPIC_PREFIX) || question.startsWith(ARC_PREFIX);
    }

    private static Optional<JsonObject> answer(JsonObject question, String name) {
        for (JsonElement a : question.getAsJsonArray("answers")) {
            JsonObject answer = a.getAsJsonObject();
            if (answer.has("name") && answer.get("name").getAsString().equals(name)) {
                return Optional.of(answer);
            }
        }
        return Optional.empty();
    }

    private static List<JsonObject> conditions(JsonObject result) {
        List<JsonObject> out = new ArrayList<>();
        if (result.has("conditions")) {
            for (JsonElement c : result.getAsJsonArray("conditions")) {
                out.add(c.getAsJsonObject());
            }
        }
        return out;
    }

    /** Flattens a {@code conversations_progress_apply} action, which may be an object or an array. */
    private static List<JsonObject> progressEntries(JsonObject result) {
        List<JsonObject> out = new ArrayList<>();
        JsonObject actions = result.getAsJsonObject("actions");
        if (!actions.has("conversations_progress_apply")) {
            return out;
        }
        JsonElement element = actions.get("conversations_progress_apply");
        if (element.isJsonArray()) {
            element.getAsJsonArray().forEach(e -> out.add(e.getAsJsonObject()));
        } else if (element.isJsonObject()) {
            out.add(element.getAsJsonObject());
        }
        return out;
    }

    private interface ResultVisitor {
        void visit(String question, String answerName, JsonObject result);
    }

    private interface AnswerVisitor {
        void visit(String question, String answerName, JsonArray results);
    }

    private static void forEachResult(ResultVisitor visitor) {
        forEachAnswer((question, answerName, results) -> {
            for (JsonElement r : results) {
                visitor.visit(question, answerName, r.getAsJsonObject());
            }
        });
    }

    private static void forEachAnswer(AnswerVisitor visitor) {
        questions.forEach((name, json) -> {
            for (JsonElement a : json.getAsJsonArray("answers")) {
                JsonObject answer = a.getAsJsonObject();
                String answerName = answer.has("name") ? answer.get("name").getAsString() : "(auto)";
                visitor.visit(name, answerName, answer.getAsJsonArray("results"));
            }
        });
    }
}
