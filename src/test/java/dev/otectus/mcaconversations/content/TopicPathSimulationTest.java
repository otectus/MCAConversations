package dev.otectus.mcaconversations.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.RelationshipQuery;
import dev.otectus.mcaconversations.check.CheckTier;
import dev.otectus.mcaconversations.conversation.ConversationCatalog;
import dev.otectus.mcaconversations.conversation.DepthClass;
import dev.otectus.mcaconversations.conversation.TopicEntry;
import dev.otectus.mcaconversations.personality.Personalities;
import dev.otectus.mcaconversations.progress.AffectionApply;
import dev.otectus.mcaconversations.progress.BudgetQuery;
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
 * Walks the shipped topics end to end the way MCA would (plan §13.6), against the real guard chain:
 * the shipped JSON decides which result fires, and a real {@link ProgressStore} decides what the
 * player actually receives.
 *
 * <p>Two halves. The hand-written scenarios pin down specific beats — a rough day handled well, a
 * promise that is exclusive, a rebuff that scars — and read as documentation of what the content is
 * supposed to do. {@code everyTopicWalksEndToEndInsideItsBudget} is the coverage half, and drives
 * every catalogued topic rather than the two pilots this file was originally written for.
 *
 * <p>This is the test that would have caught "the numbers look right in the file but the player can
 * farm it anyway". It is not a substitute for playing the mod — §14 production verification is a
 * separate, human job — but every claim about what a path is worth is checked here rather than
 * asserted in a design document.
 *
 * <p>The simulator refuses to guess: an unmodelled condition key inside pilot content fails the
 * test rather than being silently treated as neutral, so new content cannot quietly escape it.
 */
class TopicPathSimulationTest {

    private static final Path DIALOGUES = Path.of("src/main/resources/data/mcaconversations/dialogues");
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("sim-villager".getBytes());
    private static final UUID PLAYER = UUID.nameUUIDFromBytes("sim-player".getBytes());

    private static Map<String, JsonObject> questions;
    private static ConversationCatalog CATALOG;

    @BeforeAll
    static void load() throws IOException {
        questions = new TreeMap<>();
        try (var files = Files.list(DIALOGUES)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                questions.put(file.getFileName().toString().replace(".json", ""),
                        JsonParser.parseString(Files.readString(file)).getAsJsonObject());
            }
        }

        List<TopicEntry> topics = new ArrayList<>();
        Path catalog = Path.of("src/main/resources/data/mcaconversations/conversation_catalog");
        try (var files = Files.list(catalog)) {
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
        CATALOG = ConversationCatalog.build(topics);
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
        /** conversations.work fans out over 40 professions and needs exactly one of them to match. */
        String profession = "minecraft:farmer";
        int hearts = 60;
        /** Whether "is it raining / is there a festival / is a quest waiting" answers yes. */
        boolean worldFacts;
        boolean topics = true;
        boolean dispositionsEnabled = true;
        /** Midday: the ordinary hour, so dawn and late-night branches are colourings, not defaults. */
        long timeOfDay = 6000L;
        String rank = "peasant";
        int health = 20;
        /** Constraint tokens this villager satisfies (spouse, family, kids, has_village, …). */
        final Set<String> traits = new HashSet<>();
        /** Where the disposition vector sits; absent axes rest at zero, as a stranger's would. */
        final Map<String, Integer> dispositions = new HashMap<>();
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
        /** What conversations_session has framed this exchange as, which content can now read. */
        String sessionTopic;
        String sessionBranch;
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
            if (weight(r.getAsJsonObject(), question + "/" + answerName, run) > 0) {
                positive.add(r.getAsJsonObject());
            }
        }
        assertTrue(positive.size() <= 1, question + "/" + answerName
                + ": " + positive.size() + " results have positive weight in this state — MCA would"
                + " pick between them at random, so the player's consequence would be a lottery");
        // MCA's documented fallback: when nothing scores, the LAST result fires.
        return positive.isEmpty() ? results.get(results.size() - 1).getAsJsonObject() : positive.get(0);
    }

    private static int weight(JsonObject result, String where, Run run) {
        int total = result.has("baseChance") ? result.get("baseChance").getAsInt() : 0;
        if (!result.has("conditions")) {
            return total;
        }
        for (JsonElement c : result.getAsJsonArray("conditions")) {
            JsonObject condition = c.getAsJsonObject();
            int chance = condition.get("chance").getAsInt();
            total += chance * conditionValue(condition, where, run);
        }
        return total;
    }

    private static int conditionValue(JsonObject condition, String where, Run run) {
        World world = run.world;
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
                // Named bands rather than heart numbers (spec section 9.4). The simulated pair is
                // unmarried and not relatives, so the band is whatever the heart total says it is.
                case "conversations_relationship":
                    return RelationshipQuery.fromJson(condition.get(key))
                            .matches(RelationshipBand.of(world.hearts, false, false, false)) ? 1 : 0;
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
                // The vector rests at its personality baseline in this world, so a "you have earned
                // this" threshold is not met by a stranger — which is the point of the threshold.
                case "conversations_disposition": {
                    JsonObject query = condition.getAsJsonObject(key);
                    int value = world.dispositions.getOrDefault(query.get("axis").getAsString(), 0);
                    boolean inRange = (!query.has("min") || value >= query.get("min").getAsInt())
                            && (!query.has("max") || value <= query.get("max").getAsInt());
                    return inRange ? 1 : 0;
                }
                // Read against the run's real ledger, so a budget-aware branch is simulated rather
                // than assumed. This is the only condition whose answer changes as the walk proceeds.
                case "conversations_budget": {
                    BudgetQuery query = BudgetQuery.fromJson(condition.getAsJsonObject(key));
                    ProgressRecord record = run.store.get(VILLAGER, PLAYER).orElse(null);
                    return query.matches(query.valueOf(record, run.now / 24000L)) ? 1 : 0;
                }
                // An unhurt villager of no particular standing: the ordinary case, so rank- and
                // health-gated colourings are colourings rather than the default walk.
                // The read half of conversations_session, which lets sibling branches share a node
                // instead of duplicating the branch into the node's name.
                case "conversations_session": {
                    JsonObject query = condition.getAsJsonObject(key);
                    if (query.has("topic")
                            && !query.get("topic").getAsString().equalsIgnoreCase(String.valueOf(run.sessionTopic))) {
                        return 0;
                    }
                    if (query.has("branch")
                            && !query.get("branch").getAsString().equalsIgnoreCase(String.valueOf(run.sessionBranch))) {
                        return 0;
                    }
                    return run.sessionTopic == null ? 0 : 1;
                }
                case "rank":
                    return condition.get(key).getAsString().equals(world.rank) ? 1 : 0;
                case "min_health":
                    return world.health >= condition.get(key).getAsInt() ? 1 : 0;
                case "time_min":
                    return world.timeOfDay >= condition.get(key).getAsLong() ? 1 : 0;
                case "time_max":
                    return world.timeOfDay <= condition.get(key).getAsLong() ? 1 : 0;
                // The world facts below are all "is the world currently like this?" questions with
                // no bearing on the guard chain. The simulated world answers no to every one, which
                // makes the walk deterministic and lands it on each topic's ordinary branch — the
                // one an adult with no weather, no festival and no gossip waiting actually gets.
                case "conversations_weather":
                case "conversations_season":
                case "conversations_holiday":
                case "conversations_gossip":
                case "conversations_reputation":
                case "conversations_reputation_incident":
                case "conversations_quest_available":
                case "conversations_quest_active":
                case "conversations_quest_ready":
                case "conversations_quest_completed":
                case "trait":
                case "is_pregnant":
                case "min_infection_progress":
                    return world.worldFacts ? 1 : 0;
                // Living histories. The simulated villager has no generated profile, no
                // episodes, no threads and no promises, which is exactly the state a brand-new world
                // is in - so every one of these answers no and the walk lands on the static 1.4.0
                // route. That is the property worth simulating: with the dynamic layer producing
                // nothing, every topic must still reach its ordinary branch inside its budget.
                case "conversations_profile":
                case "conversations_episode":
                case "conversations_thread":
                case "conversations_commitment":
                case "conversations_claim":
                case "conversations_opinion":
                case "conversations_recent":
                    return 0;
                case "conversations_context":
                    // Unknown context takes the declared unknown policy; only "neutral" matches, and
                    // the simulated world knows nothing.
                    return condition.getAsJsonObject(key).has("unknown")
                            && "neutral".equalsIgnoreCase(
                                    condition.getAsJsonObject(key).get("unknown").getAsString())
                            ? 1 : 0;
                case "conversations_scene": {
                    // No plan is ever frozen in the simulator, so a plain scene test never matches and
                    // its negated twin always does. That is what drives the dynamic routes to their
                    // -5000 sink and leaves the static route standing.
                    JsonObject query = condition.getAsJsonObject(key);
                    boolean negate = query.has("not") && query.get("not").getAsBoolean();
                    return negate ? 1 : 0;
                }
                case "profession":
                    return condition.get(key).getAsString().equals(world.profession) ? 1 : 0;
                case "constraints":
                    return constraintsValue(condition.get(key).getAsString(), world);
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
            case "topics" -> world.topics;
            case "dispositions" -> world.dispositionsEnabled;
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
    /**
     * MCA's constraint string, evaluated against the villager the walk is talking to. A bare token
     * must hold, a {@code !}-prefixed one must not. Age tokens read the world's age group; everything
     * else reads the trait set.
     */
    private static int constraintsValue(String constraints, World world) {
        for (String raw : constraints.split(",")) {
            String token = raw.trim();
            if (token.isEmpty()) {
                continue;
            }
            boolean negated = token.startsWith("!");
            String name = negated ? token.substring(1) : token;
            boolean holds = Set.of("baby", "toddler", "child", "teen", "adult").contains(name)
                    ? name.equals(world.ageGroup)
                    : world.traits.contains(name);
            if (holds == negated) {
                return 0;
            }
        }
        return 1;
    }

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
        if (actions.has("conversations_session")) {
            JsonObject session = actions.getAsJsonObject("conversations_session");
            String op = session.has("op") ? session.get("op").getAsString() : "";
            if ("begin".equals(op)) {
                run.sessionTopic = session.has("topic") ? session.get("topic").getAsString() : null;
                run.sessionBranch = session.has("branch") ? session.get("branch").getAsString() : null;
            } else if ("branch".equals(op) && session.has("branch")) {
                run.sessionBranch = session.get("branch").getAsString();
            } else if ("end".equals(op)) {
                run.sessionTopic = null;
                run.sessionBranch = null;
            }
        }
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
        // The joke used to be decided by two hardcoded personality lists. It is now a real check on
        // the humour family, so the tier decides the outcome and personality reaches it the way
        // every other stance does — through the interiority bias the resolver adds to the roll.
        for (Map.Entry<CheckTier, Integer> expected : Map.of(
                CheckTier.CRIT, 1, CheckTier.SUCCESS, 1, CheckTier.PARTIAL, 1, CheckTier.REBUFF, -1).entrySet()) {
            Run run = new Run(new World().mood("sad").tier(expected.getKey()));
            click("conversations.cat.chitchat", "day", run);
            click("conversations.topic.day.rough.respond", "ask", run);
            click("conversations.topic.day.rough.followup", "lighten", run);
            assertEquals(expected.getValue(), run.heartsMoved,
                    expected.getKey() + ": " + run.trace);
        }

        // And the thing that tips the roll is the shipped profile, which is what makes the
        // CHANGELOG's long-standing claim true rather than decorative: a joke lands for a playful
        // villager and falls flat on a gloomy one. Read from the datapack, because the runtime
        // loader only populates on a resource reload.
        assertTrue(shippedHumourBias("playful") > 0,
                "a playful villager has to be more receptive to a joke than average");
        assertTrue(shippedHumourBias("gloomy") < 0,
                "and a gloomy one less so — otherwise the headline claim is decoration");
    }

    /** The humour stance bias the shipped interiority datapack gives a personality. */
    private static int shippedHumourBias(String personality) {
        try {
            JsonObject profiles = JsonParser.parseString(Files.readString(
                            Path.of("src/main/resources/data/mcaconversations/interiority/personalities.json")))
                    .getAsJsonObject().getAsJsonObject("profiles");
            JsonObject bias = profiles.getAsJsonObject(personality).getAsJsonObject("stance_bias");
            return bias.has("humor") ? bias.get("humor").getAsInt() : 0;
        } catch (IOException e) {
            throw new AssertionError("could not read the shipped interiority profiles", e);
        }
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
        String repaired = click(opener, "apologize", repair);
        assertEquals("conversations.topic.fears.repaired", repaired,
                "the scar is permanent; the conversation is not");

        // Apologising used to route to the guarded node, which offers "Come on, you can tell me."
        // — handing back the exact button that caused the scar. The repair route must not.
        Set<String> offered = new HashSet<>();
        for (JsonElement a : questions.get(repaired).getAsJsonArray("answers")) {
            offered.add(a.getAsJsonObject().get("name").getAsString());
        }
        assertFalse(offered.contains("press"),
                "a repair node must not re-offer the boundary push; got " + offered);
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

        // Both sides are read back later, differently — and so is having taken neither.
        for (String side : List.of("pledged", "stepped_back")) {
            World world = new World();
            world.memories.add("arc:fears=2");
            world.memories.add("exclusive:fears.support=" + side);
            // A pledge that is still standing: the stamp it wrote has not yet lapsed.
            world.memories.add("mcaconversations.pledge.fears");
            JsonObject chosen = select("conversations.arc.fears.followthrough.respond",
                    "recall_promise", new Run(world));
            assertTrue(chosen.getAsJsonObject("actions").get("say").getAsString().endsWith(side),
                    side + " must have its own callback line");
        }

        World stranger = new World();
        stranger.memories.add("arc:fears=2");
        JsonObject shrug = select("conversations.arc.fears.followthrough.respond",
                "recall_promise", new Run(stranger));
        assertEquals("conversations.fears.followthrough.recall.plain",
                shrug.getAsJsonObject("actions").get("say").getAsString(),
                "never having been asked must not read back as having stepped back");
    }

    @Test
    @DisplayName("a pledge you did not come back for is noticed, and can be repaired")
    void aPledgeCanBeBrokenAndMended() {
        World lapsed = new World();
        lapsed.memories.add("arc:fears=2");
        lapsed.memories.add("exclusive:fears.support=pledged");
        // No mcaconversations.pledge.fears stamp: the window it was written with has run out.
        Run run = new Run(lapsed);

        JsonObject chosen = select("conversations.arc.fears.followthrough.respond", "recall_promise", run);
        assertEquals("conversations.fears.followthrough.recall.lapsed",
                chosen.getAsJsonObject("actions").get("say").getAsString(),
                "a promise that can only ever be kept is not a promise");
        apply(chosen, run);
        assertEquals(0, run.heartsMoved, "a first lapse costs trust and tension, never hearts");
        assertEquals("conversations.topic.fears.lapsed",
                chosen.getAsJsonObject("actions").get("next").getAsString(),
                "there has to be a way back from it");

        for (String answer : List.of("apologize", "make_good", "leave")) {
            Run repair = new Run(lapsed);
            assertEquals("conversations.cat.personal",
                    click("conversations.topic.fears.lapsed", answer, repair));
            assertTrue(repair.heartsMoved >= 0, answer + " must not punish the player twice");
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

    // ------------------------------------------------------------------
    // Coverage: every catalog topic, not just the two pilots
    // ------------------------------------------------------------------

    /**
     * Walk every converted topic from its opener to the category it returns to, taking the most
     * generous reply available at each step, and drive it through the real {@link ProgressStore}.
     *
     * <p>The hand-written scenarios above pin down specific beats but covered two topics of
     * twenty-seven. This is the coverage half, and it asks the questions only execution can answer:
     * does every state resolve to exactly one result (rather than leaving MCA a lottery), does asking
     * ever pay, does the walk terminate, and does the guard chain hold the total inside the topic's
     * budget once the store — not the JSON — has had its say.
     *
     * <p>It deliberately does not assert that a bad path exists: {@code everyTopicHasABetterAndAWorseRoute}
     * already proves that over the whole graph, which is a stronger statement than one greedy walk.
     */
    /**
     * {@code enableTopics} does what CONFIG.md has always said it does.
     *
     * <p>Until 1.2.0 the flag had no effect at all: it was read only through the {@code "topics"}
     * feature key and no shipped dialogue used that key, so turning it off changed nothing. Every
     * converted opener now sinks its branching results on it, which leaves the legacy fallback
     * standing — the documented behaviour, finally true.
     */
    @Test
    @DisplayName("with topics disabled every opener falls back to its legacy line")
    void disablingTopicsFallsBackEverywhere() {
        List<String> problems = new ArrayList<>();
        for (TopicEntry topic : CATALOG.topics()) {
            World world = worldFor(topic);
            world.topics = false;
            Run run = new Run(world);
            JsonObject chosen = select(topic.entryQuestion(), topic.entryAnswer(), run);
            JsonObject actions = chosen.getAsJsonObject("actions");
            String next = actions.has("next") ? actions.get("next").getAsString() : null;
            if (next != null && (next.startsWith("conversations.topic.") || next.startsWith("conversations.arc."))) {
                problems.add(topic.id() + ": topics disabled still routed into " + next);
            }
            JsonObject destination = next == null ? null : questions.get(next);
            boolean spokenByAuto = destination != null && destination.has("auto")
                    && destination.get("auto").getAsBoolean();
            if (!actions.has("say") && !actions.has("conversations_gossip_say")
                    && !actions.has("conversations_say") && !spokenByAuto) {
                problems.add(topic.id() + ": topics disabled left the villager with nothing to say");
            }
            apply(chosen, run);
            if (run.heartsMoved < 0) {
                problems.add(topic.id() + ": the off-state punished the player for asking");
            }
        }
        problems.sort(null);
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    /**
     * The disposition vector is finally readable, and reads differently at the thresholds.
     *
     * <p>Before 1.2.0 {@code tension} had 105 writes and 0 reads, {@code familiarity} 95 and 0, and
     * no gate anywhere used a {@code min} bound — so there was not one "you have earned this"
     * threshold in the whole mod, and every axis was a number the player could never detect.
     */
    /**
     * One node, five topics, five different answers — read from the live session.
     *
     * <p>The five deep topics each had their own "we were just here" node, identical in every way
     * except which lang key it said, because nothing could ask the session what topic was open.
     * 114 results had been writing a branch into that session since 1.1.0 with no reader at all.
     */
    @Test
    @DisplayName("the shared repeat node says the right topic's line, chosen from the session")
    void oneNodeSpeaksForFiveTopics() {
        for (String topic : List.of("life", "dreams", "hopes", "regrets", "secret")) {
            Run run = new Run(new World());
            run.sessionTopic = topic;
            assertEquals("conversations." + topic + ".again.press",
                    select("conversations.topic.deep.again.respond", "press", run)
                            .getAsJsonObject("actions").get("say").getAsString(),
                    topic + " must speak for itself even though the node is shared");
        }

        // And a session that has lapsed still gets a sensible line rather than a raw key.
        Run lapsed = new Run(new World());
        assertNotNull(select("conversations.topic.deep.again.respond", "press", lapsed)
                .getAsJsonObject("actions").get("say"));
    }

    @Test
    @DisplayName("the disposition thresholds change what the villager says")
    void earnedThresholdsAreAudible() {
        // A stranger gets the ordinary line...
        Run stranger = new Run(new World().mood("sad"));
        assertEquals("conversations.day.rough.empathize",
                select("conversations.topic.day.rough.respond", "empathize", stranger)
                        .getAsJsonObject("actions").get("say").getAsString());

        // ...and somebody the villager is still cross with gets a cooler one. This is the missing
        // half of the apology mechanic: something finally reads whether the air is unsettled.
        World unsettled = new World().mood("sad");
        unsettled.dispositions.put("tension", 40);
        assertEquals("conversations.day.rough.tense",
                select("conversations.topic.day.rough.respond", "empathize", new Run(unsettled))
                        .getAsJsonObject("actions").get("say").getAsString());

        // With the vector switched off entirely, the gate cannot fire and the plain line returns.
        World vectorOff = new World().mood("sad");
        vectorOff.dispositions.put("tension", 40);
        vectorOff.dispositionsEnabled = false;
        assertEquals("conversations.day.rough.empathize",
                select("conversations.topic.day.rough.respond", "empathize", new Run(vectorOff))
                        .getAsJsonObject("actions").get("say").getAsString(),
                "a disposition-gated result must degrade to its authored fallback, not vanish");
    }

    /**
     * At the daily cap the villager says so, instead of the player quietly receiving nothing.
     *
     * <p>{@code positiveToday} was tracked per villager and player since 1.1.0 and exposed by no
     * condition, so the guard chain would clamp a kindness to zero in silence.
     */
    @Test
    @DisplayName("at the daily cap the villager winds the conversation down instead of going quiet")
    void theBudgetIsAudibleOnceItIsSpent() {
        Run run = new Run(new World().mood("sad"));
        assertEquals("conversations.day.rough.offer_help",
                select("conversations.topic.day.rough.followup", "offer_help", run)
                        .getAsJsonObject("actions").get("say").getAsString());

        // Spend the day's positive budget the way a player actually would — across different
        // topics. Repeating one kindness cannot do it: daily_repeat diminishes the same decision to
        // nothing, which is the anti-farming guard working exactly as intended.
        for (String node : List.of("conversations.topic.life.respond", "conversations.topic.hopes.respond",
                "conversations.topic.fears.open.respond", "conversations.topic.village.respond",
                "conversations.topic.people.respond", "conversations.topic.work.respond",
                "conversations.topic.life.followup", "conversations.topic.hopes.followup")) {
            if (run.heartsMoved >= 8 || !questions.containsKey(node)) {
                continue;
            }
            run.newConversation(DepthClass.DEEP);
            click(node, mostGenerousAnswer(node), run);
        }
        assertEquals(8, run.heartsMoved,
                "the daily cap is 8 and distinct kindnesses reach it; trace: " + run.trace);

        run.newConversation(DepthClass.QUICK);
        assertEquals("conversations.day.rough.offer_help.spent",
                select("conversations.topic.day.rough.followup", "offer_help", run)
                        .getAsJsonObject("actions").get("say").getAsString(),
                "at the cap the offer is turned down warmly rather than accepted for nothing");
    }

    @Test
    @DisplayName("every catalog topic walks end to end, deterministically and inside its budget")
    void everyTopicWalksEndToEndInsideItsBudget() {
        List<String> problems = new ArrayList<>();
        Set<String> walked = new HashSet<>();
        for (TopicEntry topic : CATALOG.topics()) {
            if (NOT_SIMULATABLE.contains(topic.id())) {
                continue;
            }
            walked.add(topic.id());
            try {
                Run run = new Run(worldFor(topic));
                run.newConversation(topic.depth());
                String node = click(topic.entryQuestion(), topic.entryAnswer(), run);
                if (run.heartsMoved != 0) {
                    problems.add(topic.id() + ": asking the question moved " + run.heartsMoved
                            + " hearts — the act of asking must never pay");
                }
                if (node == null || !isBranchingNode(node)) {
                    // Without this the test would pass vacuously for any topic whose opener falls
                    // through to its off-state fallback: nothing is walked, so nothing can fail.
                    problems.add(topic.id() + ": the opener landed on '" + node + "' instead of a"
                            + " branching node, so this topic is not actually being simulated");
                    continue;
                }
                int steps = 0;
                Set<String> visited = new HashSet<>();
                while (node != null && isBranchingNode(node) && steps++ <= DepthClass.MAX_DECISIONS) {
                    if (!visited.add(node)) {
                        break; // an answer looped back to a node we already answered; stop walking
                    }
                    JsonObject page = questions.get(node);
                    if (page.has("auto") && page.get("auto").getAsBoolean()) {
                        // MCA resolves an auto question itself and recurses straight into whatever it
                        // routes to; the player is never asked, so this is not a decision.
                        steps--;
                        node = apply(soleResult(node, run), run);
                        continue;
                    }
                    String answer = mostGenerousAnswer(node);
                    if (answer == null) {
                        problems.add(topic.id() + ": node '" + node + "' offers nothing to click");
                        break;
                    }
                    node = click(node, answer, run);
                }
                if (node != null && isBranchingNode(node)) {
                    problems.add(topic.id() + ": still inside the tree after " + steps
                            + " decisions — every topic has to end up back at a category page");
                }
                if (run.heartsMoved > topic.depth().positiveBudget()) {
                    problems.add(topic.id() + ": the most generous walk gained " + run.heartsMoved
                            + " hearts against a " + topic.depth().key() + " budget of "
                            + topic.depth().positiveBudget() + " — " + run.trace);
                }
            } catch (AssertionError | RuntimeException e) {
                problems.add(topic.id() + ": " + e.getClass().getSimpleName() + ": " + e.getMessage()
                        + (e.getStackTrace().length > 0 ? " @ " + e.getStackTrace()[0] : ""));
            }
        }
        problems.sort(null);
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
        assertEquals(CATALOG.topics().size() - NOT_SIMULATABLE.size(), walked.size(),
                "every topic except the documented exclusions has to be walked");
    }

    /**
     * Topics the simulator deliberately does not walk, and why.
     *
     * <p>{@code standing} branches on MCA: Reputation's tier ordering ({@code min_tier} /
     * {@code max_tier}), and that ordering lives in Reputation, not here — Conversations passes the
     * tier names through as opaque strings. Modelling it would mean inventing a ranking this
     * codebase does not own, which is exactly the guessing the simulator refuses to do elsewhere.
     * Its branches are covered by {@code ReputationIntegrationTest} instead.
     */
    private static final Set<String> NOT_SIMULATABLE = Set.of("standing");

    /**
     * The world a topic's ordinary adult branch expects.
     *
     * <p>Hearts are set well above every gate, and the unlock memories the deep topics check for are
     * granted, so the walk lands on the real tree rather than on a "not yet, we barely know each
     * other" deflect. Everything else is left at its default.
     */
    private static World worldFor(TopicEntry topic) {
        World world = new World().hearts(120);
        world.memories.add("mcaconversations.unlock.confided");
        world.memories.add("mcaconversations.unlock.intimate");
        world.traits.addAll(Set.of("spouse", "family", "kids", "parent", "has_village"));
        // standing only exists when MCA: Reputation is answering, so the walk has to say yes to the
        // world questions for that one topic or there is nothing to simulate.
        world.worldFacts = topic.id().equals("standing");
        return world;
    }

    /**
     * The result an {@code auto} question resolves to in the current world.
     *
     * <p>Auto questions carry a single unnamed answer — {@code autoQuestionsAreDeterministic}
     * enforces that — so there is no name to select by. Unlike a player decision, several results
     * being positive at once is <em>correct</em> here: {@code conversations.work} is a weighted
     * flavour lottery over forty professions plus mood and personality colour, and any of its
     * outcomes is a legitimate line. The walk takes the heaviest so it stays reproducible.
     */
    private static JsonObject soleResult(String question, Run run) {
        JsonArray answers = questions.get(question).getAsJsonArray("answers");
        assertEquals(1, answers.size(), question + ": an auto question must have exactly one answer");
        JsonArray results = answers.get(0).getAsJsonObject().getAsJsonArray("results");
        JsonObject heaviest = null;
        int best = 0;
        for (JsonElement r : results) {
            int w = weight(r.getAsJsonObject(), question + "/(auto)", run);
            if (w > best) {
                best = w;
                heaviest = r.getAsJsonObject();
            }
        }
        return heaviest != null ? heaviest : results.get(results.size() - 1).getAsJsonObject();
    }

    /** The answer on this node whose best authored outcome is worth the most hearts. */
    private static String mostGenerousAnswer(String node) {
        String best = null;
        int bestDelta = Integer.MIN_VALUE;
        for (JsonElement a : questions.get(node).getAsJsonArray("answers")) {
            JsonObject answer = a.getAsJsonObject();
            if (!answer.has("name")) {
                continue;
            }
            int delta = 0;
            for (JsonElement r : answer.getAsJsonArray("results")) {
                JsonObject actions = r.getAsJsonObject().getAsJsonObject("actions");
                if (actions.has("conversations_affection_apply")) {
                    delta = Math.max(delta,
                            actions.getAsJsonObject("conversations_affection_apply").get("delta").getAsInt());
                }
            }
            if (delta > bestDelta) {
                best = answer.get("name").getAsString();
                bestDelta = delta;
            }
        }
        return best;
    }

    /**
     * Nodes the walk should keep walking through. {@code conversations.work} is included because it
     * is the {@code auto} profession node: MCA resolves it without asking the player anything and
     * immediately recurses into the tree behind it, so it is part of the topic, not a way out of it.
     */
    private static boolean isBranchingNode(String question) {
        return question.startsWith("conversations.topic.") || question.startsWith("conversations.arc.")
                || question.equals("conversations.work");
    }

    @Test
    @DisplayName("refusing to hear a secret ends its own way, and is never treated as having heard it")
    void decliningASecretNeverReachesTheNodeThatAssumesYouHeardIt() {
        Run run = new Run(new World());
        String node = click("conversations.topic.secret.respond", "decline", run);

        assertEquals("conversations.topic.secret.declined", node,
                "declining used to land on the follow-up, which then offered to promise to keep a"
                        + " secret the player had never been told");
        assertFalse(run.milestone("secret.entrusted"), "you cannot be entrusted with what you refused");
        assertEquals(0, run.arcStage("secret"), "refusing does not advance the arc");
        assertEquals(0, run.heartsMoved, "refusing to hear it is neither rewarded nor punished");

        // Nothing on the declined node can reach the follow-up either: its whole premise is
        // "so now you're carrying it too".
        for (String answer : List.of("offer_later", "change_subject", "leave")) {
            Run branch = new Run(new World());
            click("conversations.topic.secret.respond", "decline", branch);
            assertEquals("conversations.cat.personal",
                    click("conversations.topic.secret.declined", answer, branch),
                    answer + " must return to the category, not continue into the disclosure");
            assertFalse(branch.milestone("secret.entrusted"), answer + " must not entrust anything");
        }
    }

    @Test
    @DisplayName("agreeing to hear a secret is what delivers it — the opener only offers")
    void acceptingASecretIsWhatDeliversIt() {
        Run run = new Run(new World());
        JsonObject chosen = select("conversations.topic.secret.respond", "accept", run);
        assertEquals("conversations.secret.respond.accept",
                chosen.getAsJsonObject("actions").get("say").getAsString(),
                "the payload belongs to the answer that agreed to hear it, not to the opener");
        apply(chosen, run);
        assertTrue(run.milestone("secret.entrusted"), "hearing it is what entrusts you");
        assertEquals(1, run.arcStage("secret"));
    }
}
