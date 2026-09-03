package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.history.NarrativeValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Every typed claim can be traced to the button that produced it (spec §11.3, §8.6).
 *
 * <p>Storing what a player said about themselves is the one place this mod keeps a record whose
 * origin is a person rather than the world, and the whole safety argument for doing it at all is
 * that the record can always be pointed back at a click. Free-form text may <em>select</em> a claim;
 * it may never become one. These are the checks that keep that true as content grows, and they are
 * worth having even while the corpus holds only a couple of claims — the moment to write the rule is
 * before the content that would break it.
 */
class ClaimBindingLintTest {

    private static final String SEP = System.lineSeparator();

    /**
     * The shipped dialogue pages, read straight off disk.
     *
     * <p>Read here rather than through the graph fixture because these checks are about the JSON as
     * authored — a claim's {@code source} is a string in a file, and normalising it through a loader
     * first would hide exactly the mistakes this is looking for.
     */
    private static Map<String, JsonObject> questions() {
        Map<String, JsonObject> pages = new HashMap<>();
        try (Stream<Path> files = Files.list(ContentFixture.DIALOGUES)) {
            files.filter(file -> file.getFileName().toString().endsWith(".json")).forEach(file -> {
                String name = file.getFileName().toString();
                try {
                    pages.put(name.substring(0, name.length() - ".json".length()),
                            JsonParser.parseString(Files.readString(file)).getAsJsonObject());
                } catch (Exception e) {
                    throw new IllegalStateException("unreadable dialogue page " + file, e);
                }
            });
        } catch (Exception e) {
            throw new IllegalStateException("could not list the dialogue pages", e);
        }
        return pages;
    }

    /** Every {@code conversations_claim} action in the shipped dialogues, with where it was found. */
    private static List<Binding> bindings() {
        List<Binding> found = new ArrayList<>();
        for (Map.Entry<String, JsonObject> page : questions().entrySet()) {
            for (JsonElement element : page.getValue().getAsJsonArray("answers")) {
                JsonObject answer = element.getAsJsonObject();
                if (!answer.has("name") || !answer.has("results")) {
                    continue;
                }
                String route = page.getKey() + "/" + answer.get("name").getAsString();
                for (JsonElement result : answer.getAsJsonArray("results")) {
                    JsonObject actions = result.getAsJsonObject().has("actions")
                            ? result.getAsJsonObject().getAsJsonObject("actions") : null;
                    if (actions != null && actions.has("conversations_claim")
                            && actions.get("conversations_claim").isJsonObject()) {
                        found.add(new Binding(route, actions.getAsJsonObject("conversations_claim")));
                    }
                }
            }
        }
        return found;
    }

    private record Binding(String route, JsonObject claim) {
        String op() {
            return claim.has("op") ? claim.get("op").getAsString() : "record";
        }

        String type() {
            return claim.has("type") ? claim.get("type").getAsString() : "";
        }

        String source() {
            return claim.has("source") ? claim.get("source").getAsString() : "";
        }
    }

    @Test
    @DisplayName("a recorded claim names the button the player actually pressed")
    void everyClaimPointsAtItsOwnButton() {
        List<String> problems = new ArrayList<>();
        for (Binding binding : bindings()) {
            if (!"record".equals(binding.op())) {
                continue;
            }
            if (binding.source().isBlank()) {
                problems.add(binding.route() + ": records a claim with no source");
            } else if (!binding.source().equals(binding.route())) {
                problems.add(binding.route() + ": claims to come from '" + binding.source()
                        + "', which is a different button");
            }
        }
        assertTrue(problems.isEmpty(),
                "Without provenance there is no claim. These records could not be traced to a click:"
                        + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("a recorded claim carries a value the store can actually hold")
    void everyClaimHasATypedValue() {
        List<String> problems = new ArrayList<>();
        for (Binding binding : bindings()) {
            if (!"record".equals(binding.op())) {
                continue;
            }
            if (binding.type().isBlank()) {
                problems.add(binding.route() + ": records a claim of no type");
            }
            String raw = binding.claim().has("value")
                    ? binding.claim().get("value").getAsString() : "";
            if (NarrativeValue.parse(raw).isEmpty()) {
                problems.add(binding.route() + ": value '" + raw
                        + "' is not a typed narrative value, so nothing would be stored");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("nothing waits on a claim no button can record")
    void everyClaimConditionHasAWriter() {
        Set<String> written = new TreeSet<>();
        for (Binding binding : bindings()) {
            if ("record".equals(binding.op()) && !binding.type().isBlank()) {
                written.add(binding.type());
            }
        }

        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, JsonObject> page : questions().entrySet()) {
            for (JsonElement element : page.getValue().getAsJsonArray("answers")) {
                JsonObject answer = element.getAsJsonObject();
                if (!answer.has("name") || !answer.has("results")) {
                    continue;
                }
                String route = page.getKey() + "/" + answer.get("name").getAsString();
                for (JsonElement result : answer.getAsJsonArray("results")) {
                    JsonObject row = result.getAsJsonObject();
                    if (!row.has("conditions")) {
                        continue;
                    }
                    for (JsonElement condition : row.getAsJsonArray("conditions")) {
                        JsonObject test = condition.getAsJsonObject();
                        if (!test.has("conversations_claim")
                                || !test.get("conversations_claim").isJsonObject()) {
                            continue;
                        }
                        JsonObject query = test.getAsJsonObject("conversations_claim");
                        String type = query.has("type") ? query.get("type").getAsString() : "";
                        if (!type.isBlank() && !written.contains(type)) {
                            problems.add(route + ": waits on claim '" + type
                                    + "', which no button records");
                        }
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(),
                "A condition on a claim nothing writes is a branch that can never be taken:"
                        + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("the claim mechanism is exercised by the corpus, not merely available to it")
    void claimsAreActuallyUsed() {
        long records = bindings().stream().filter(binding -> "record".equals(binding.op())).count();

        assertTrue(records > 0,
                "no button records a typed claim, so §11.3's self-disclosure has machinery and no"
                        + " content behind it");
    }
}
