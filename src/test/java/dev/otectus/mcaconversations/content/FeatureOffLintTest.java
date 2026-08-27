package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Switching a feature off returns the conversation to what it was, not to nothing (spec §21.1).
 *
 * <p>Every switch in this mod has an off state that is supposed to reproduce the previous release
 * exactly. That promise lives in the content rather than in the code: a generated route sinks itself
 * when its feature is disabled, and the hand-written route underneath it is always available. If a
 * generated route ever forgets one of its sinks, the failure is not an error message — it is a
 * villager answering a question nobody can turn off, and it would only be found by somebody playing
 * with the layer disabled.
 *
 * <p>So the sinks are checked as a property of the corpus. Every route the compiler generates must
 * stand down for {@code branching} and {@code topics}, and a preselected-scene route must stand down
 * for {@code dynamic} as well, because a scene is the living-histories layer and the other two are
 * not.
 */
class FeatureOffLintTest {

    private static final String SEP = System.lineSeparator();

    /** Branches the compiler opens a session on; the mark of a generated route. */
    private static final Set<String> GENERATED_BRANCHES = Set.of("scene", "funnel");

    /** Every generated route stands down for these. */
    private static final List<String> ALWAYS_REQUIRED = List.of("branching", "topics");

    @Test
    @DisplayName("every generated route stands down when its feature is switched off")
    void generatedRoutesCarryTheirSinks() throws IOException {
        List<String> problems = new ArrayList<>();
        int routes = 0;
        for (Map.Entry<String, JsonObject> page : questions().entrySet()) {
            for (JsonElement element : page.getValue().getAsJsonArray("answers")) {
                JsonObject answer = element.getAsJsonObject();
                if (!answer.has("name") || !answer.has("results")) {
                    continue;
                }
                String route = page.getKey() + "/" + answer.get("name").getAsString();
                for (JsonElement resultElement : answer.getAsJsonArray("results")) {
                    JsonObject result = resultElement.getAsJsonObject();
                    String branch = branchOf(result);
                    if (branch == null) {
                        continue;
                    }
                    routes++;
                    Set<String> sinks = disabledSinks(result);
                    List<String> required = new ArrayList<>(ALWAYS_REQUIRED);
                    if ("scene".equals(branch)) {
                        required.add("dynamic");
                    }
                    for (String feature : required) {
                        if (!sinks.contains(feature)) {
                            problems.add(route + " (" + branch + " route): no sink for '"
                                    + feature + "'");
                        }
                    }
                }
            }
        }
        assertTrue(routes > 0, "no generated routes found; this lint is measuring nothing");
        assertTrue(problems.isEmpty(),
                "With the layer switched off these routes would still fire:" + SEP
                        + String.join(SEP, problems));
    }

    @Test
    @DisplayName("every button carrying a generated route also carries one that never sinks")
    void everyButtonKeepsAnUnconditionalFallback() throws IOException {
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, JsonObject> page : questions().entrySet()) {
            for (JsonElement element : page.getValue().getAsJsonArray("answers")) {
                JsonObject answer = element.getAsJsonObject();
                if (!answer.has("name") || !answer.has("results")) {
                    continue;
                }
                boolean hasGenerated = false;
                boolean hasFallback = false;
                for (JsonElement resultElement : answer.getAsJsonArray("results")) {
                    JsonObject result = resultElement.getAsJsonObject();
                    if (branchOf(result) != null) {
                        hasGenerated = true;
                    } else if (survivesFeaturesOff(result)) {
                        hasFallback = true;
                    }
                }
                if (hasGenerated && !hasFallback) {
                    problems.add(page.getKey() + "/" + answer.get("name").getAsString());
                }
            }
        }
        assertTrue(problems.isEmpty(),
                "These buttons would be empty with the layer switched off, which is worse than the"
                        + " release they are supposed to fall back to:" + SEP
                        + String.join(SEP, problems));
    }

    private static String branchOf(JsonObject result) {
        if (!result.has("actions") || !result.get("actions").isJsonObject()) {
            return null;
        }
        JsonObject actions = result.getAsJsonObject("actions");
        if (!actions.has("conversations_session")
                || !actions.get("conversations_session").isJsonObject()) {
            return null;
        }
        JsonObject session = actions.getAsJsonObject("conversations_session");
        if (!session.has("branch")) {
            return null;
        }
        String branch = session.get("branch").getAsString();
        return GENERATED_BRANCHES.contains(branch) ? branch : null;
    }

    /**
     * Features this result <em>stands down</em> for.
     *
     * <p>The sign is the whole meaning. {@code conversations_disabled} is true while a feature is
     * off, so a negative chance on it is a route removing itself from the off state, and a positive
     * one is a route that exists <em>because</em> the feature is off. Reading both as "a sink" was
     * the first version of this lint and it called every correct fallback a hole.
     */
    private static Set<String> disabledSinks(JsonObject result) {
        Set<String> sinks = new LinkedHashSet<>();
        if (!result.has("conditions")) {
            return sinks;
        }
        for (JsonElement element : result.getAsJsonArray("conditions")) {
            JsonObject condition = element.getAsJsonObject();
            if (condition.has("conversations_disabled") && chanceOf(condition) < 0) {
                sinks.add(condition.get("conversations_disabled").getAsString());
            }
        }
        return sinks;
    }

    /**
     * True when this result is still on the table with the whole layer switched off.
     *
     * <p>Either it never mentions a feature switch, or it is the route that only exists in the off
     * state. Both are fallbacks; a result that sinks itself when a feature is off is not.
     */
    private static boolean survivesFeaturesOff(JsonObject result) {
        return disabledSinks(result).isEmpty();
    }

    private static int chanceOf(JsonObject condition) {
        return condition.has("chance") ? condition.get("chance").getAsInt() : 0;
    }

    private static Map<String, JsonObject> questions() throws IOException {
        Map<String, JsonObject> pages = new HashMap<>();
        try (Stream<Path> files = Files.list(ContentFixture.DIALOGUES)) {
            for (Path file : files.filter(path -> path.toString().endsWith(".json")).toList()) {
                String name = file.getFileName().toString();
                pages.put(name.substring(0, name.length() - ".json".length()),
                        JsonParser.parseString(Files.readString(file)).getAsJsonObject());
            }
        }
        return pages;
    }
}
