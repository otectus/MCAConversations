package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Two rules about how this mod's own villager lines are spoken.
 *
 * <h2>Every line goes through {@code conversations_say}</h2>
 *
 * <p>MCA's native {@code say} hands the lang key to the client, which resolves the {@code /N} pool
 * with a fresh random draw and no memory of what it drew last time. Our own action names the variant
 * on the server first (see {@code LineVoice}), which is what stops a pool of three sentences reading
 * like a pool of one, and what makes one utterance the same sentence for the speaker and for every
 * bystander who overhears it.
 *
 * <p>So a bare {@code say} in our corpus is not a style preference — it is a line that quietly opts
 * out of the anti-repetition rule, and there is no way to see that by reading it. In 1.5.0 all 3,583
 * of them were rewritten; this is what stops the 3,584th appearing. Third-party datapacks may still
 * use {@code say}: it stays a legal action, and the lints that read a spoken key still accept it.
 *
 * <h2>It has to come after {@code next}</h2>
 *
 * <p>MCA runs a result's actions in JSON key order, and {@code next} overwrites the line the player is
 * looking at. A {@code say} placed before {@code next} is therefore not a line that renders oddly — it
 * is a line nobody ever sees, with nothing at runtime to say so. That rule was documented in
 * DATAPACK.md and enforced by nothing; the 1.5.0 rewrite had to preserve key order to respect it, so
 * it is worth checking that it did, and worth catching by hand the first time somebody appends an
 * action to the end of a result and pushes the line past its page.
 */
class NoBareSayLintTest {

    private static final Path DIALOGUES =
            Path.of("src/main/resources/data/mcaconversations/dialogues");

    private static List<Path> dialogueFiles() {
        try (Stream<Path> files = Files.list(DIALOGUES)) {
            return files.filter(p -> p.getFileName().toString().endsWith(".json")).sorted().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static JsonObject read(Path path) {
        try {
            return JsonParser.parseString(Files.readString(path, StandardCharsets.UTF_8))
                    .getAsJsonObject();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** Every {@code actions} object in one file, with a readable path to it. */
    private static void forEachActions(JsonElement node, String where, List<String[]> out) {
        if (node.isJsonObject()) {
            JsonObject object = node.getAsJsonObject();
            for (String key : object.keySet()) {
                if (key.equals("actions") && object.get(key).isJsonObject()) {
                    out.add(new String[]{where, key});
                }
                forEachActions(object.get(key), where + "/" + key, out);
            }
        } else if (node.isJsonArray()) {
            int i = 0;
            for (JsonElement child : node.getAsJsonArray()) {
                forEachActions(child, where + "[" + i++ + "]", out);
            }
        }
    }

    private static List<JsonObject> actionsIn(JsonElement node) {
        List<JsonObject> out = new ArrayList<>();
        collect(node, out);
        return out;
    }

    private static void collect(JsonElement node, List<JsonObject> out) {
        if (node.isJsonObject()) {
            JsonObject object = node.getAsJsonObject();
            JsonElement actions = object.get("actions");
            if (actions != null && actions.isJsonObject()) {
                out.add(actions.getAsJsonObject());
            }
            for (String key : object.keySet()) {
                collect(object.get(key), out);
            }
        } else if (node.isJsonArray()) {
            for (JsonElement child : node.getAsJsonArray()) {
                collect(child, out);
            }
        }
    }

    @Test
    void noShippedLineUsesMcaNativeSay() {
        List<String> problems = new ArrayList<>();
        for (Path file : dialogueFiles()) {
            for (JsonObject actions : actionsIn(read(file))) {
                if (actions.has("say")) {
                    problems.add(file.getFileName() + ": uses MCA's native 'say' ("
                            + actions.get("say") + "). Use conversations_say {\"phrase\": ...} so the "
                            + "pooled variant is chosen on the server.");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void theSpokenLineIsAlwaysDeclaredAfterTheHop() {
        List<String> problems = new ArrayList<>();
        for (Path file : dialogueFiles()) {
            for (JsonObject actions : actionsIn(read(file))) {
                List<String> keys = List.copyOf(actions.keySet());
                int next = keys.indexOf("next");
                if (next < 0) {
                    continue;
                }
                for (String speaking : List.of("conversations_say", "say", "conversations_gossip_say")) {
                    int at = keys.indexOf(speaking);
                    if (at >= 0 && at < next) {
                        problems.add(file.getFileName() + ": '" + speaking + "' is declared before "
                                + "'next', so the page replaces the line before anyone reads it. "
                                + "Order: " + keys);
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void theCorpusIsBigEnoughForTheseRulesToMeanSomething() {
        // A guard on the guards: if a refactor ever pointed this test at an empty directory, both
        // rules above would pass by vacuum and nobody would notice until a player did.
        int spoken = 0;
        for (Path file : dialogueFiles()) {
            for (JsonObject actions : actionsIn(read(file))) {
                if (actions.has("conversations_say")) {
                    spoken++;
                }
            }
        }
        assertTrue(spoken > 3000, "expected the shipped corpus to speak through conversations_say, "
                + "found only " + spoken + " uses");
    }
}
