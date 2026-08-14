package dev.otectus.mcaconversations.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.check.CheckTier;
import dev.otectus.mcaconversations.conversation.DepthClass;
import dev.otectus.mcaconversations.personality.Personalities;
import dev.otectus.mcaconversations.progress.AffectionApply;
import dev.otectus.mcaconversations.progress.AffectionContext;
import dev.otectus.mcaconversations.progress.AffectionOutcome;
import dev.otectus.mcaconversations.progress.ProgressApply;
import dev.otectus.mcaconversations.progress.ProgressRecord;
import dev.otectus.mcaconversations.progress.ProgressStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Walks the two pilot topics end to end the way MCA would (plan §13.6), against the real guard
 * chain: the shipped JSON decides which result fires, and a real {@link ProgressStore} decides what
 * the player actually receives.
 *
 * <p>This is the test that would have caught "the numbers look right in the file but the player can
 * farm it anyway". It is not a substitute for playing the mod — §14 production verification is a
 * separate, human job — but every claim about what a path is worth is checked here rather than
 * asserted in a design document.
 *
 * <p>The simulator refuses to guess: an unmodelled condition key inside pilot content fails the
 * test rather than being silently treated as neutral, so new content cannot quietly escape it.
 */
class PilotPathSimulationTest {

    private static final Path DIALOGUES = Path.of("src/main/resources/data/mcaconversations/dialogues");
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("sim-villager".getBytes());
    private static final UUID PLAYER = UUID.nameUUIDFromBytes("sim-player".getBytes());

    private static Map<String, JsonObject> questions;

    @BeforeAll
    static void load() throws IOException {
        questions = new TreeMap<>();
        try (var files = Files.list(DIALOGUES)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                questions.put(file.getFileName().toString().replace(".json", ""),
                        JsonParser.parseString(Files.readString(file)).getAsJsonObject());
            }
        }
    }

    // ------------------------------------------------------------------
    // The modelled world
    // ------------------------------------------------------------------

    /** Everything the pilot content is allowed to branch on. */
    private static final class World {
        boolean branching = true;
        boolean checks = true;
        String personality = "friendly";
        String ageGroup = "adult";
        String mood = "fine";
        String chore = "none";
        int hearts = 60;
        final Set<String> memories = new HashSet<>();
        CheckTier tier = CheckTier.SUCCESS;

        World mood(String m) {
            this.mood = m;
            return this;
        }

        World age(String a) {
            this.ageGroup = a;
            return this;
        }

        World hearts(int h) {
            this.hearts = h;
            return this;
        }

        World personality(String p) {
            this.personality = p;
            return this;
        }

        World tier(CheckTier t) {
            this.tier = t;
            return this;
        }

        World checksOff() {
            this.checks = false;
            return this;
        }

        World branchingOff() {
            this.branching = false;
            return this;
        }
    }

    /** One traversal: the ledger it writes into, and the running totals it produces. */
    private static final class Run {
        final ProgressStore store = new ProgressStore();
        final World world;
        long now = 1_000;
        int sessionPositive;
        int sessionNegative;
        int heartsMoved;
        DepthClass budget = DepthClass.QUICK;
        final List<String> trace = new ArrayList<>();

        Run(World world) {
            this.world = world;
            // A world that starts mid-arc must start with a ledger that agrees: the opener reads the
            // world, every node after it reads the store, and the two must not disagree.
            for (String memory : world.memories) {
                if (memory.startsWith("arc:")) {
                    String[] parts = memory.substring(4).split("=");
                    record().setArcStage(parts[0], Integer.parseInt(parts[1]), 3);
                } else if (memory.startsWith("milestone:")) {
                    record().setMilestone(memory.substring("milestone:".length()));
                } else if (memory.startsWith("exclusive:")) {
                    String[] parts = memory.substring("exclusive:".length()).split("=");
                    record().setExclusiveChoice(parts[0], parts[1]);
                }
            }
        }

        ProgressRecord record() {
            return store.getOrCreate(VILLAGER, PLAYER, now);
        }

        int arcStage(String arc) {
            return store.get(VILLAGER, PLAYER).map(r -> r.arcStage(arc)).orElse(0);
        }

        boolean milestone(String id) {
            return store.get(VILLAGER, PLAYER).map(r -> r.hasMilestone(id)).orElse(false);
        }

        Optional<String> exclusive(String group) {
            return store.get(VILLAGER, PLAYER).flatMap(r -> r.exclusiveChoice(group));
        }

        /** A new conversation on the same day: the per-conversation budget resets, the daily one does not. */
        Run newConversation(DepthClass depth) {
            sessionPositive = 0;
            sessionNegative = 0;
            budget = depth;
            now += 200;
            return this;
        }

        /** A new MC day: every daily counter rolls over. */
        Run nextDay() {
            newConversation(budget);
            now += 24_000;
            return this;
        }
    }

    // ------------------------------------------------------------------
    // The engine, as MCA implements it
    // ------------------------------------------------------------------

    /** Picks the result MCA would fire, asserting the determinism invariant on the way. */
    private static JsonObject select(String question, String answerName, Run run) {
        JsonObject q = questions.get(question);
        assertNotNull(q, "no such question: " + question);
        JsonArray results = null;
        for (JsonElement a : q.getAsJsonArray("answers")) {
            JsonObject answer = a.getAsJsonObject();
            if (answer.has("name") && answer.get("name").getAsString().equals(answerName)) {
                results = answer.getAsJsonArray("results");
            }
        }
        assertNotNull(results, question + " has no answer '" + answerName + "'");

        List<JsonObject> positive = new ArrayList<>();
        for (JsonElement r : results) {
            if (weight(r.getAsJsonObject(), question + "/" + answerName, run.world) > 0) {
                positive.add(r.getAsJsonObject());
            }
        }
        assertTrue(positive.size() <= 1, question + "/" + answerName
                + ": " + positive.size() + " results have positive weight in this state — MCA would"
                + " pick between them at random, so the player's consequence would be a lottery");
        // MCA's documented fallback: when nothing scores, the LAST result fires.
        return positive.isEmpty() ? results.get(results.size() - 1).getAsJsonObject() : positive.get(0);
    }

    private static int weight(JsonObject result, String where, World world) {
        int total = result.has("baseChance") ? result.get("baseChance").getAsInt() : 0;
        if (!result.has("conditions")) {
            return total;
        }
        for (JsonElement c : result.getAsJsonArray("conditions")) {
            JsonObject condition = c.getAsJsonObject();
            int chance = condition.get("chance").getAsInt();
            total += chance * conditionValue(condition, where, world);
        }
        return total;
    }

    private static int conditionValue(JsonObject condition, String where, World world) {
        for (String key : condition.keySet()) {
            switch (key) {
                case "chance":
                    continue;
                case "conversations_enabled":
                    return featureOn(condition.get(key).getAsString(), world) ? 1 : 0;
                case "conversations_disabled":
                    return featureOn(condition.get(key).getAsString(), world) ? 0 : 1;
                case "age_group":
                    return condition.get(key).getAsString().equals(world.ageGroup) ? 1 : 0;
                case "mood":
                    return condition.get(key).getAsString().equals(world.mood) ? 1 : 0;
                case "current_chore":
                    return condition.get(key).getAsString().equals(world.chore) ? 1 : 0;
                case "hearts_max":
                    return world.hearts <= condition.get(key).getAsInt() ? 1 : 0;
                case "hearts_min":
                    return world.hearts >= condition.get(key).getAsInt() ? 1 : 0;
                case "conversations_personality":
                    return personalityMatches(condition.get(key), world.personality) ? 1 : 0;
                case "memory":
                    return memoryValue(condition.getAsJsonObject(key), world);
                case "conversations_check":
                    return world.checks
                            && condition.getAsJsonObject(key).get("tier").getAsString()
                            .equals(world.tier.key()) ? 1 : 0;
                case "conversations_progress":
                    return progressValue(condition.getAsJsonObject(key), world);
                default:
                    throw new AssertionError(where + ": the path simulator does not model condition '"
                            + key + "'. Add it here rather than letting pilot content escape the"
                            + " determinism and budget checks.");
            }
        }
        return 1;
    }

    private static boolean featureOn(String feature, World world) {
        return switch (feature) {
            case "branching" -> world.branching;
            case "checks" -> world.checks;
            default -> true;
        };
    }

    private static boolean personalityMatches(JsonElement value, String personality) {
        if (value.isJsonArray()) {
            for (JsonElement e : value.getAsJsonArray()) {
                if (Personalities.matches(e.getAsString(), personality)) {
                    return true;
                }
            }
            return false;
        }
        return Personalities.matches(value.getAsString(), personality);
    }

    /** MCA's memory condition: {@code clamp(remaining / dividend + add, 0, max)} with 1/0/1 defaults. */
    private static int memoryValue(JsonObject memory, World world) {
        boolean has = world.memories.contains(memory.get("id").getAsString());
        boolean inverted = memory.has("dividend") && memory.get("dividend").getAsDouble() < 0;
        return (has != inverted) ? 1 : 0;
    }

    /** The simulator reads progress out of the world's memories view for openers, before any run state. */
    private static int progressValue(JsonObject query, World world) {
        if (query.has("milestone")) {
            boolean expected = !query.has("has") || query.get("has").getAsBoolean();
            return world.memories.contains("milestone:" + query.get("milestone").getAsString()) == expected ? 1 : 0;
        }
        if (query.has("arc")) {
            int stage = world.memories.stream()
                    .filter(m -> m.startsWith("arc:" + query.get("arc").getAsString() + "="))
                    .mapToInt(m -> Integer.parseInt(m.substring(m.indexOf('=') + 1)))
                    .findFirst().orElse(0);
            int min = query.has("min") ? query.get("min").getAsInt() : 0;
            int max = query.has("max") ? query.get("max").getAsInt() : Integer.MAX_VALUE;
            return stage >= min && stage <= max ? 1 : 0;
        }
        String group = query.get("exclusive").getAsString();
        String want = query.get("is").getAsString();
        Optional<String> chosen = world.memories.stream()
                .filter(m -> m.startsWith("exclusive:" + group + "="))
                .map(m -> m.substring(m.indexOf('=') + 1)).findFirst();
        return ("none".equals(want) ? chosen.isEmpty() : chosen.filter(want::equals).isPresent()) ? 1 : 0;
    }

    /** Applies a chosen result's actions through the real guards, and returns where it routes next. */
    private static String apply(JsonObject result, Run run) {
        JsonObject actions = result.getAsJsonObject("actions");
        if (actions.has("conversations_progress_apply")) {
            JsonElement element = actions.get("conversations_progress_apply");
            List<JsonObject> entries = new ArrayList<>();
            if (element.isJsonArray()) {
                element.getAsJsonArray().forEach(e -> entries.add(e.getAsJsonObject()));
            } else {
                entries.add(element.getAsJsonObject());
            }
            for (JsonObject entry : entries) {
                ProgressApply directive = ProgressApply.fromJson(entry);
                ProgressRecord record = run.record();
                if (directive instanceof ProgressApply.Arc arc) {
                    // The pilot's only arc declares max_stage 3 in the catalog.
                    record.setArcStage(arc.arcId(),
                            ProgressApply.resolveStage(arc, record.arcStage(arc.arcId()), 3), 3);
                } else if (directive instanceof ProgressApply.Milestone milestone) {
                    record.setMilestone(milestone.milestoneId());
                } else if (directive instanceof ProgressApply.Exclusive exclusive) {
                    record.setExclusiveChoice(exclusive.group(), exclusive.member());
                }
            }
        }
        if (actions.has("conversations_affection_apply")) {
            AffectionApply directive =
                    AffectionApply.fromJson(actions.getAsJsonObject("conversations_affection_apply"));
            AffectionContext context = new AffectionContext(
                    directive.budget().orElse(run.budget),
                    run.sessionPositive, run.sessionNegative, 8, 10, false, 1.0,
                    directive.decision() + "@" + run.now, run.now);
            AffectionOutcome outcome = run.store.applyAffection(VILLAGER, PLAYER, directive, context);
            run.heartsMoved += outcome.granted();
            if (outcome.granted() > 0) {
                run.sessionPositive += outcome.granted();
            } else {
                run.sessionNegative += -outcome.granted();
            }
            run.trace.add(directive.decision() + "=" + outcome.granted() + "(" + outcome.reason() + ")");
        } else {
            run.trace.add("(no hearts)");
        }
        run.now += 20;
        return actions.has("next") ? actions.get("next").getAsString() : null;
    }

    /** Clicks one answer at a node and returns the destination question. */
    private static String click(String question, String answer, Run run) {
        return apply(select(question, answer, run), run);
    }

    // ------------------------------------------------------------------
    // §13.6 — the representative paths
    // ------------------------------------------------------------------

    @Test
    @DisplayName("1. rough day → empathise → offer help → a small, capped positive outcome")
    void roughDayHandledWell() {
        Run run = new Run(new World().mood("sad"));
        String node = click("conversations.cat.chitchat", "day", run);
        assertEquals("conversations.topic.day.rough.respond", node);
        assertEquals(0, run.heartsMoved, "asking must never pay");

        node = click(node, "empathize", run);
        assertEquals("conversations.topic.day.rough.followup", node);
        node = click(node, "offer_help", run);
        assertEquals("conversations.cat.chitchat", node);

        assertEquals(2, run.heartsMoved, "trace: " + run.trace);
        assertEquals(DepthClass.QUICK.positiveBudget(), run.heartsMoved,
                "the best mundane path lands exactly on the Quick budget, never past it");
    }

    @Test
    @DisplayName("2. rough day → brush off → apologise = a repair that is not a refund")
    void apologisingIsNotAHeartRefund() {
        Run run = new Run(new World().mood("sad"));
        String node = click("conversations.cat.chitchat", "day", run);
        node = click(node, "brush_off", run);
        assertEquals("conversations.topic.day.rough.repair", node);
        assertEquals(-1, run.heartsMoved);

        click(node, "apologize", run);
        assertEquals(-1, run.heartsMoved,
                "apologising settles the vector, not the hearts: a slight cannot be bought back");
    }

    @Test
    @DisplayName("3. the same dismissal twice in a day cannot be farmed, and cannot be out-run")
    void repeatingADismissalDiminishes() {
        Run run = new Run(new World().mood("sad"));
        click("conversations.cat.chitchat", "day", run);
        click("conversations.topic.day.rough.respond", "brush_off", run);
        assertEquals(-1, run.heartsMoved);

        run.newConversation(DepthClass.QUICK);
        click("conversations.topic.day.rough.respond", "brush_off", run);
        assertEquals(-1, run.heartsMoved, "a same-day repeat halves to nothing: " + run.trace);

        run.newConversation(DepthClass.QUICK);
        click("conversations.topic.day.rough.respond", "brush_off", run);
        assertEquals(-1, run.heartsMoved, "and stays at nothing: " + run.trace);
    }

    @Test
    @DisplayName("5. the same joke lands for one personality and misfires for another")
    void personalityDecidesTheJoke() {
        Run playful = new Run(new World().mood("sad").personality("playful"));
        click("conversations.cat.chitchat", "day", playful);
        click("conversations.topic.day.rough.respond", "ask", playful);
        click("conversations.topic.day.rough.followup", "lighten", playful);
        assertEquals(1, playful.heartsMoved, "playful: " + playful.trace);

        Run gloomy = new Run(new World().mood("sad").personality("gloomy"));
        click("conversations.cat.chitchat", "day", gloomy);
        click("conversations.topic.day.rough.respond", "ask", gloomy);
        click("conversations.topic.day.rough.followup", "lighten", gloomy);
        assertEquals(-1, gloomy.heartsMoved, "gloomy: " + gloomy.trace);

        Run peaceful = new Run(new World().mood("sad").personality("peaceful"));
        click("conversations.cat.chitchat", "day", peaceful);
        click("conversations.topic.day.rough.respond", "ask", peaceful);
        click("conversations.topic.day.rough.followup", "lighten", peaceful);
        assertEquals(0, peaceful.heartsMoved, "everyone else: politely received, no movement");
    }

    @Test
    @DisplayName("7. below the trust gate the fear is withheld, and the exit is safe")
    void fearBelowTheGateDeflectsSafely() {
        Run run = new Run(new World().hearts(10));
        String node = click("conversations.cat.personal", "fears", run);
        assertEquals("conversations.topic.fears.guarded.respond", node);
        assertEquals(0, run.heartsMoved, "being refused is not a punishment");

        String after = click(node, "respect", run);
        assertEquals("conversations.cat.personal", after);
        assertEquals(1, run.heartsMoved, "hearing 'no' the first time is worth something");
    }

    @Test
    @DisplayName("8. a fear crit fires the revelation once ever, and a later day reads it back")
    void revelationFiresOnceAndIsCalledBack() {
        Run run = new Run(new World().tier(CheckTier.CRIT));
        String node = click("conversations.cat.personal", "fears", run);
        assertEquals("conversations.topic.fears.open.respond", node);

        node = click(node, "comfort", run);
        assertTrue(run.milestone("fears.revelation"), "a crit is what unlocks it");
        assertEquals(1, run.arcStage("fears"), "the arc moves exactly one stage");
        int afterCrit = run.heartsMoved;

        // The same crit on a later day pays nothing more: the milestone outcome is once, ever.
        run.nextDay();
        click("conversations.topic.fears.open.respond", "comfort", run);
        assertEquals(afterCrit, run.heartsMoved, "a once-only outcome cannot fire twice: " + run.trace);

        // …and a later conversation reads the milestone back out loud.
        World later = new World();
        later.memories.add("milestone:fears.revelation");
        later.memories.add("arc:fears=1");
        Run callback = new Run(later);
        JsonObject chosen = select("conversations.arc.fears.plan.respond", "ask_what_helps", callback);
        assertEquals("conversations.fears.plan.ask_what_helps.remembered",
                chosen.getAsJsonObject("actions").get("say").getAsString(),
                "the villager must say something different because it remembers");
    }

    @Test
    @DisplayName("9. pushing after a refusal scars the boundary, and the repair route survives")
    void pushingAfterRefusalScarsButNeverLocksOut() {
        Run run = new Run(new World().tier(CheckTier.REBUFF));
        String node = click("conversations.cat.personal", "fears", run);
        node = click(node, "press", run);
        assertEquals("conversations.topic.fears.pressed", node, "a rebuff hands the player the choice");
        assertFalse(run.milestone("fears.scar"), "the first attempt never scars — being told no does");

        click(node, "push", run);
        assertTrue(run.milestone("fears.scar"), "choosing to push after a refusal is what scars");
        assertEquals(-7, run.heartsMoved, "trace: " + run.trace);
        assertTrue(-run.heartsMoved <= DepthClass.DEEP.negativeBudget(),
                "the worst path stays inside the Deep budget");

        // Backing off instead is the same node's other side, and costs nothing extra.
        Run gentle = new Run(new World().tier(CheckTier.REBUFF));
        click("conversations.cat.personal", "fears", gentle);
        click("conversations.topic.fears.open.respond", "press", gentle);
        click("conversations.topic.fears.pressed", "back_off", gentle);
        assertFalse(gentle.milestone("fears.scar"));
        assertEquals(-2, gentle.heartsMoved);

        // A scarred villager still talks, and apologising reopens the guarded route.
        World scarred = new World();
        scarred.memories.add("milestone:fears.scar");
        Run repair = new Run(scarred);
        String opener = click("conversations.cat.personal", "fears", repair);
        assertEquals("conversations.topic.fears.scarred.respond", opener);
        assertEquals("conversations.topic.fears.guarded.respond",
                click(opener, "apologize", repair),
                "the scar is permanent; the conversation is not");
    }

    @Test
    @DisplayName("10. the support promise is exclusive — the other side can never also be taken")
    void thePromiseIsExclusive() {
        Run pledged = new Run(new World().tier(CheckTier.SUCCESS));
        click("conversations.cat.personal", "fears", pledged);
        click("conversations.topic.fears.open.respond", "comfort", pledged);
        click("conversations.topic.fears.open.followup", "pledge", pledged);
        assertEquals(Optional.of("pledged"), pledged.exclusive("fears.support"));

        // Reaching the node again and taking the other side changes nothing: first choice wins.
        pledged.nextDay();
        click("conversations.topic.fears.open.followup", "step_back", pledged);
        assertEquals(Optional.of("pledged"), pledged.exclusive("fears.support"));

        Run steppedBack = new Run(new World().tier(CheckTier.SUCCESS));
        click("conversations.cat.personal", "fears", steppedBack);
        click("conversations.topic.fears.open.respond", "comfort", steppedBack);
        click("conversations.topic.fears.open.followup", "step_back", steppedBack);
        assertEquals(Optional.of("stepped_back"), steppedBack.exclusive("fears.support"));

        // Both sides are read back later, differently.
        for (String side : List.of("pledged", "stepped_back")) {
            World world = new World();
            world.memories.add("arc:fears=2");
            world.memories.add("exclusive:fears.support=" + side);
            JsonObject chosen = select("conversations.arc.fears.followthrough.respond",
                    "recall_promise", new Run(world));
            assertTrue(chosen.getAsJsonObject("actions").get("say").getAsString().endsWith(side),
                    side + " must have its own callback line");
        }
    }

    @Test
    @DisplayName("11. the arc advances one stage per conversation and cannot be rushed")
    void theArcCannotBeRushed() {
        Run run = new Run(new World().tier(CheckTier.CRIT));
        click("conversations.cat.personal", "fears", run);
        click("conversations.topic.fears.open.respond", "comfort", run);
        assertEquals(1, run.arcStage("fears"));

        // The rest of the same conversation deliberately holds.
        click("conversations.topic.fears.open.followup", "pledge", run);
        click("conversations.topic.fears.open.close", "thank", run);
        assertEquals(1, run.arcStage("fears"), "one major stage per conversation (§6.3)");

        // Another day, the resume node moves it on.
        World day2 = new World();
        day2.memories.add("arc:fears=1");
        Run second = new Run(day2);
        String node = click("conversations.cat.personal", "fears", second);
        assertEquals("conversations.arc.fears.plan.respond", node);
        click(node, "ask_what_helps", second);
        assertEquals(2, second.arcStage("fears"));
    }

    @Test
    @DisplayName("12. children and teens never reach a checked or romantic path")
    void youngVillagersGetTheirOwnTree() {
        for (String age : List.of("toddler", "child", "teen")) {
            Run run = new Run(new World().age(age));
            String node = click("conversations.cat.personal", "fears", run);
            assertTrue(node.equals("conversations.topic.fears.toddler.respond")
                            || node.equals("conversations.topic.fears.young.respond"),
                    age + " routed to " + node);
            assertNoAttractionOrChecks(node, age);

            Run day = new Run(new World().age(age));
            String dayNode = click("conversations.cat.chitchat", "day", day);
            assertNoAttractionOrChecks(dayNode, age);
        }
    }

    private static void assertNoAttractionOrChecks(String node, String age) {
        String json = questions.get(node).toString();
        assertFalse(json.contains("attraction"), age + ": " + node + " touches the Attraction axis");
        assertFalse(json.contains("conversations_check"), age + ": " + node + " puts a child behind a check");
        assertFalse(json.contains("flirt"), age + ": " + node + " contains a romantic stance");
    }

    @Test
    @DisplayName("14. a duplicated packet applies the consequence exactly once")
    void duplicatePacketsAreIdempotent() {
        Run run = new Run(new World().mood("sad"));
        click("conversations.cat.chitchat", "day", run);

        JsonObject result = select("conversations.topic.day.rough.respond", "empathize", run);
        long frozen = run.now;
        apply(result, run);
        run.now = frozen;            // the duplicate arrives in the same tick
        apply(result, run);

        assertEquals(1, run.heartsMoved, "the second application must be refused: " + run.trace);
        assertTrue(run.trace.get(run.trace.size() - 1).contains("DUPLICATE"), run.trace.toString());
    }

    @Test
    @DisplayName("15. every feature off-state still resolves to a playable line")
    void everyToggleCombinationStaysPlayable() {
        List<String> problems = new ArrayList<>();
        for (boolean branching : List.of(true, false)) {
            for (boolean checks : List.of(true, false)) {
                World world = new World();
                world.branching = branching;
                world.checks = checks;

                for (String topic : List.of("conversations.cat.chitchat|day", "conversations.cat.personal|fears")) {
                    String[] parts = topic.split("\\|");
                    Run run = new Run(world);
                    JsonObject opener = select(parts[0], parts[1], run);
                    JsonObject actions = opener.getAsJsonObject("actions");
                    if (!actions.has("say")) {
                        problems.add(topic + " branching=" + branching + " checks=" + checks
                                + ": opener has no line");
                    }
                    String next = actions.has("next") ? actions.get("next").getAsString() : null;
                    if (next == null) {
                        problems.add(topic + " branching=" + branching + ": opener goes nowhere");
                        continue;
                    }
                    if (!branching) {
                        boolean legacy = next.startsWith("conversations.cat.") || next.equals("conversations.fears");
                        if (!legacy) {
                            problems.add(topic + ": branching off still routed into " + next);
                        }
                        continue;
                    }
                    // With branching on, the destination must be a real node offering real choices.
                    JsonObject node = questions.get(next);
                    if (node == null || node.getAsJsonArray("answers").size() < 2) {
                        problems.add(topic + " checks=" + checks + ": " + next + " is not a decision");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    @DisplayName("with checks disabled a checked stance still resolves to one authored outcome")
    void checksDisabledFallsBackCleanly() {
        Run run = new Run(new World().checksOff());
        String node = click("conversations.cat.personal", "fears", run);
        assertEquals("conversations.topic.fears.open.respond", node);

        JsonObject chosen = select(node, "comfort", run);
        assertEquals("conversations.fears.open.comfort.plain",
                chosen.getAsJsonObject("actions").get("say").getAsString());
        apply(chosen, run);
        assertEquals(1, run.heartsMoved);
        assertEquals(1, run.arcStage("fears"), "the arc still moves without the dice");
    }

    @Test
    @DisplayName("the daily budget holds across separate conversations with the same villager")
    void dailyBudgetHoldsAcrossConversations() {
        Run run = new Run(new World().mood("sad"));
        int conversations = 0;
        while (conversations++ < 8) {
            run.newConversation(DepthClass.QUICK);
            click("conversations.cat.chitchat", "day", run);
            click("conversations.topic.day.rough.respond", "empathize", run);
            click("conversations.topic.day.rough.followup", "offer_help", run);
        }
        assertTrue(run.heartsMoved <= 8,
                "a whole day of the same two kindnesses must not exceed the daily cap; got "
                        + run.heartsMoved + " — " + run.trace);
    }
}
