package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.BeatContract;
import dev.otectus.mcaconversations.conversation.PlayerRole;
import dev.otectus.mcaconversations.debug.DialogueGraph;
import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A family topic is written from one side, all the way down (feedback remediation §2.3, WP-C1).
 *
 * <p>Until 1.8.0, {@code checkin_child} — the player talking to their own child — answered "how are
 * you?" with a parent talking about their own children, and {@code ask_parent} had a child's lines
 * two pages deep. {@link AgeSeparationTest} and the constraint probe were both green the whole time,
 * because neither was ever about perspective. This one is.
 *
 * <p>For every catalog topic that states a {@code player_role}, the walk follows every {@code next}
 * page reachable from the topic's starter — stopping at the hubs and return pages every topic shares —
 * and asserts:
 *
 * <ol>
 *   <li>every beat a reached line is filed under declares the topic's {@code player_role};</li>
 *   <li>no line ({@code say} key) and no topic page is reached from two different perspectives — a
 *       topic with no role counts as a perspective of its own, so a parent's line cannot quietly be
 *       reused by a topic about somebody else's children either.</li>
 * </ol>
 *
 * <p>The corpus report the plan asks for (§4) is written alongside, to
 * {@code build/reports/conversations/family-perspective.md}.
 */
class FamilyPerspectiveTest {

    private static final String SEP = System.lineSeparator();
    private static final Path REPORT = TestPaths.of("build/reports/conversations/family-perspective.md");
    private static final String NO_ROLE = "(none)";

    /** One topic as the walk saw it. */
    private record Walk(String topic, String role, Set<String> pages, Map<String, Set<String>> lines,
                        List<String> problems) {
    }

    private static Map<String, Walk> walks;

    @BeforeAll
    static void walkEveryTopic() throws IOException {
        JsonObject topics = JsonParser.parseString(Files.readString(ContentFixture.TOPICS)).getAsJsonObject()
                .getAsJsonObject("topics");
        walks = new TreeMap<>();
        for (Map.Entry<String, JsonElement> entry : topics.entrySet()) {
            JsonObject row = entry.getValue().getAsJsonObject();
            String role = row.has("player_role") ? row.get("player_role").getAsString() : NO_ROLE;
            JsonObject start = row.getAsJsonObject("entry");
            walks.put(entry.getKey(), walk(entry.getKey(), role, start.get("question").getAsString(),
                    start.get("answer").getAsString()));
        }
        writeReport();
    }

    private static Walk walk(String topic, String role, String entryQuestion, String entryAnswer) {
        Set<String> hubs = ContentFixture.hubQuestions();
        DialogueGraph graph = ContentFixture.graph();
        Set<String> pages = new LinkedHashSet<>();
        Map<String, Set<String>> lines = new TreeMap<>();
        List<String> problems = new ArrayList<>();
        Deque<List<DialogueGraph.Result>> pending = new ArrayDeque<>();
        graph.question(entryQuestion).flatMap(q -> q.answers().stream()
                        .filter(a -> a.name().equals(entryAnswer)).findFirst())
                .ifPresent(answer -> pending.add(answer.results()));
        while (!pending.isEmpty()) {
            for (DialogueGraph.Result result : pending.removeFirst()) {
                JsonObject actions = result.actions();
                String next = actions.has("next") ? actions.get("next").getAsString() : null;
                String phrase = ContentFixture.spokenPhrase(actions);
                if (phrase != null) {
                    Set<String> beats = lines.computeIfAbsent(phrase, p -> new TreeSet<>());
                    for (BeatContract beat : beatsFor(phrase, next)) {
                        beats.add(beat.id());
                        String declared = beat.context().playerRole().map(PlayerRole::key).orElse(NO_ROLE);
                        if (!NO_ROLE.equals(role) && !role.equals(declared)) {
                            problems.add(topic + " (player is the speaker's " + role + ") reaches beat "
                                    + beat.id() + " saying '" + phrase + "', which declares player_role "
                                    + declared);
                        }
                    }
                }
                if (next == null || hubs.contains(next) || next.startsWith("conversations.cat.")
                        || !pages.add(next)) {
                    continue;
                }
                graph.question(next).ifPresent(question -> {
                    for (DialogueGraph.Answer answer : question.answers()) {
                        pending.add(answer.results());
                    }
                });
            }
        }
        return new Walk(topic, role, pages, lines, problems);
    }

    /** The beats a result's line is filed under: its exact route when contracted, else its pool. */
    private static List<BeatContract> beatsFor(String phrase, String next) {
        if (next != null) {
            Optional<BeatContract> routed = ContentFixture.catalog().forRoute(phrase, next);
            if (routed.isPresent()) {
                return List.of(routed.get());
            }
        }
        return ContentFixture.catalog().forSay(phrase);
    }

    @Test
    @DisplayName("the family topics state their perspective — the walk has something to check")
    void theFamilyTopicsDeclareARole() {
        assertTrue(walks.values().stream().filter(w -> !NO_ROLE.equals(w.role())).count() >= 2,
                "checkin_child and ask_parent must state player_role in the catalog");
        for (Walk walk : walks.values()) {
            if (!NO_ROLE.equals(walk.role())) {
                assertFalse(walk.lines().isEmpty(), walk.topic() + " reached no lines at all");
            }
        }
    }

    @Test
    @DisplayName("every line a perspective topic reaches is filed under that perspective")
    void everyReachedBeatDeclaresTheTopicsRole() {
        List<String> problems = new ArrayList<>();
        walks.values().forEach(walk -> problems.addAll(walk.problems()));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("no line and no page is reached from two different perspectives")
    void noLineOrPageIsSharedBetweenPerspectives() {
        Map<String, Set<String>> rolesByLine = new TreeMap<>();
        Map<String, Set<String>> rolesByPage = new TreeMap<>();
        Map<String, Set<String>> topicsByLine = new TreeMap<>();
        for (Walk walk : walks.values()) {
            for (String line : walk.lines().keySet()) {
                rolesByLine.computeIfAbsent(line, l -> new TreeSet<>()).add(walk.role());
                topicsByLine.computeIfAbsent(line, l -> new TreeSet<>()).add(walk.topic());
            }
            for (String page : walk.pages()) {
                rolesByPage.computeIfAbsent(page, p -> new TreeSet<>()).add(walk.role());
            }
        }
        boolean anyRole = walks.values().stream().anyMatch(w -> !NO_ROLE.equals(w.role()));
        List<String> problems = new ArrayList<>();
        rolesByLine.forEach((line, roles) -> {
            if (roles.size() > 1 && anyRole && roles.stream().anyMatch(r -> !NO_ROLE.equals(r))) {
                problems.add("line '" + line + "' is reached as " + roles + " by " + topicsByLine.get(line));
            }
        });
        rolesByPage.forEach((page, roles) -> {
            if (roles.size() > 1 && roles.stream().anyMatch(r -> !NO_ROLE.equals(r))) {
                problems.add("page '" + page + "' is reached as " + roles);
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    private static void writeReport() throws IOException {
        StringBuilder out = new StringBuilder("# Family perspective report\n\n");
        out.append("Generated by FamilyPerspectiveTest. For every topic that states a `player_role` (what the ")
                .append("player is to the speaker), the pages and lines its starter reaches, and the beats each ")
                .append("line is filed under.\n");
        for (Walk walk : walks.values()) {
            if (NO_ROLE.equals(walk.role()) && !walk.topic().equals("their_children")) {
                continue;
            }
            out.append("\n## ").append(walk.topic()).append(NO_ROLE.equals(walk.role())
                            ? " — no family role (the speaker's own family is what it is about)"
                            : " — player is the speaker's " + walk.role())
                    .append("\n\nPages: ").append(walk.pages().isEmpty() ? "(none)" : String.join(", ", walk.pages()))
                    .append("\n\n| Line | Beats |\n|---|---|\n");
            walk.lines().forEach((line, beats) -> out.append("| `").append(line).append("` | ")
                    .append(beats.isEmpty() ? "(no contract)" : String.join(", ", beats)).append(" |\n"));
            if (!walk.problems().isEmpty()) {
                out.append("\nProblems:\n");
                walk.problems().forEach(p -> out.append("- ").append(p).append('\n'));
            }
        }
        Files.createDirectories(REPORT.getParent());
        Files.writeString(REPORT, out.toString());
    }

    /** Test seam for the walk counts, so a report reader knows what was covered. */
    static Map<String, Integer> coverage() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        walks.forEach((topic, walk) -> counts.put(topic, walk.lines().size()));
        return counts;
    }
}
