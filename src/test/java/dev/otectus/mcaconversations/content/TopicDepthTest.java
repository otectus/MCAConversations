package dev.otectus.mcaconversations.content;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.BeatContract;
import dev.otectus.mcaconversations.conversation.Openness;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Every topic is held to the depth its own catalog entry claims (spec §8.1).
 *
 * <p>The graph lint already counts <em>decisions</em> along a branch. That is the easy half. §8.1
 * also asks for breadth: a standard topic needs semantic beat families rather than one subject
 * dressed four ways, a deep topic needs several disclosure levels and explicit boundaries, and a
 * relationship topic needs more than one relationship state to enter from. Those are the numbers
 * that decide whether a topic is a conversation or a corridor, and nothing was counting them.
 *
 * <p>Where a topic does not meet its target, it is named in
 * {@code src/test/resources/topic_depth_debt.txt} with the number it is short by. The ledger can
 * only shrink: a topic that meets its target and is still listed fails, exactly like the route and
 * overlay ledgers before it.
 */
class TopicDepthTest {

    private static final String SEP = System.lineSeparator();

    private static final Path CATALOG =
            Path.of("src/main/resources/data/mcaconversations/conversation_catalog/topics.json");
    private static final Path DEBT = Path.of("src/test/resources/topic_depth_debt.txt");

    /** §8.1's breadth floors, by the depth class a topic declares for itself. */
    private static final Map<String, Integer> SUBJECT_FLOOR = Map.of(
            "quick", 2,
            "standard", 10,
            "deep", 4,
            "relationship", 4,
            "service", 2);

    /** Deep topics need several disclosure levels; the others need at least two. */
    private static final Map<String, Integer> OPENNESS_FLOOR = Map.of(
            "quick", 2,
            "standard", 3,
            "deep", 4,
            "relationship", 2,
            "service", 2);

    private record Measured(String depth, int subjects, int openness, int callbacks, int beats) {
    }

    private static Map<String, Measured> topics;
    private static Map<String, Integer> debt;

    @BeforeAll
    static void load() throws IOException {
        JsonObject catalog = JsonParser.parseString(Files.readString(CATALOG))
                .getAsJsonObject().getAsJsonObject("topics");

        Map<String, Set<String>> subjects = new TreeMap<>();
        Map<String, Set<Openness>> openness = new TreeMap<>();
        Map<String, Integer> callbacks = new TreeMap<>();
        Map<String, Integer> counts = new TreeMap<>();
        for (BeatContract beat : ContentFixture.catalog().beats()) {
            subjects.computeIfAbsent(beat.topic(), t -> new TreeSet<>()).add(beat.subject());
            openness.computeIfAbsent(beat.topic(), t -> new TreeSet<>()).add(beat.openness());
            counts.merge(beat.topic(), 1, Integer::sum);
            if (beat.callback().isPresent()) {
                callbacks.merge(beat.topic(), 1, Integer::sum);
            }
        }

        topics = new TreeMap<>();
        for (String name : catalog.keySet()) {
            String depth = catalog.getAsJsonObject(name).has("depth")
                    ? catalog.getAsJsonObject(name).get("depth").getAsString() : "standard";
            topics.put(name, new Measured(depth,
                    subjects.getOrDefault(name, Set.of()).size(),
                    openness.getOrDefault(name, Set.of()).size(),
                    callbacks.getOrDefault(name, 0),
                    counts.getOrDefault(name, 0)));
        }

        debt = new LinkedHashMap<>();
        if (Files.exists(DEBT)) {
            for (String line : Files.readAllLines(DEBT)) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                String[] parts = trimmed.split("\\s+");
                debt.put(parts[0], parts.length > 1 ? Integer.parseInt(parts[1]) : 0);
            }
        }
    }

    @Test
    @DisplayName("every catalog topic has beats, and the catalog is the roster")
    void everyTopicIsRepresented() {
        List<String> problems = new ArrayList<>();
        topics.forEach((name, measured) -> {
            if (measured.beats() == 0) {
                problems.add(name + ": the catalog lists it and no beat declares it");
            }
            if (!SUBJECT_FLOOR.containsKey(measured.depth())) {
                problems.add(name + ": unknown depth class '" + measured.depth() + "'");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * The breadth half of §8.1. A subject is what the villager is actually talking about; a topic
     * with two of them is one conversation with a costume change, however many beats it has.
     */
    @Test
    @DisplayName("every topic has as many subject families as its depth class asks for")
    void subjectBreadthMeetsTheTarget() {
        List<String> shortfall = new ArrayList<>();
        List<String> retired = new ArrayList<>();

        topics.forEach((name, measured) -> {
            int floor = SUBJECT_FLOOR.getOrDefault(measured.depth(), 2);
            int missing = floor - measured.subjects();
            Integer allowed = debt.get(name);
            if (missing <= 0) {
                if (allowed != null) {
                    retired.add(name + ": now has " + measured.subjects() + " subject families and the "
                            + measured.depth() + " floor is " + floor + " — delete it from the ledger");
                }
                return;
            }
            if (allowed == null) {
                shortfall.add(name + " (" + measured.depth() + "): " + measured.subjects()
                        + " subject families, " + floor + " wanted — short by " + missing);
            } else if (missing > allowed) {
                shortfall.add(name + " (" + measured.depth() + "): short by " + missing
                        + ", and the ledger allows only " + allowed + " — it has gone backwards");
            }
        });

        List<String> problems = new ArrayList<>(shortfall);
        problems.addAll(retired);
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * Disclosure levels. A topic where every line invites a follow-up has no boundaries in it, and a
     * topic where none does is a corridor. §8.1 asks deep topics in particular for several.
     */
    @Test
    @DisplayName("every topic offers more than one disclosure level")
    void opennessBreadthMeetsTheTarget() {
        List<String> problems = new ArrayList<>();
        topics.forEach((name, measured) -> {
            int floor = OPENNESS_FLOOR.getOrDefault(measured.depth(), 2);
            if (measured.openness() < floor) {
                problems.add(name + " (" + measured.depth() + "): " + measured.openness()
                        + " disclosure level(s), " + floor + " wanted — the villager is equally open"
                        + " on every line, which is not a boundary, it is a corridor");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * §8.1 asks standard topics for "at least 3 callbacks/state-sensitive revisits" and deep topics
     * for durable callbacks. Two declared callbacks plus the resume entry that reads them is three
     * revisits, so two is the floor here — and a topic with exactly one callback is a scripted
     * sequel rather than something the player did, which {@code CallbackContractTest} says as well.
     */
    @Test
    @DisplayName("the standard and deep topics remember something between conversations")
    void deepTopicsHaveCallbacks() {
        List<String> problems = new ArrayList<>();
        topics.forEach((name, measured) -> {
            if (!"deep".equals(measured.depth()) && !"standard".equals(measured.depth())) {
                return;
            }
            if (measured.callbacks() < 2) {
                problems.add(name + " (" + measured.depth() + "): " + measured.callbacks()
                        + " durable callback(s) — spec section 8.1 asks this depth class for"
                        + " something that comes back, and one line is a sequel, not a callback");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("the ledger names only topics that exist")
    void ledgerIsNotStale() {
        List<String> problems = new ArrayList<>();
        debt.keySet().forEach(name -> {
            if (!topics.containsKey(name)) {
                problems.add(name + " is in the depth ledger and is not a catalog topic");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }
}
