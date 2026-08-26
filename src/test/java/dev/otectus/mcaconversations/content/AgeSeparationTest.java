package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.debug.DialogueGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A toddler, a child and a teenager are three different conversations (spec phase 6).
 *
 * <p>They are not one conversation with the words made smaller. A four-year-old reports that there
 * was cake; a sixteen-year-old reports what the marriage means for that household and watches to see
 * whether you take them seriously. Sharing a page between two age groups is how the second one ends
 * up speaking in the first one's voice, which is exactly what had happened to the news topic — child
 * and teen both routed to a page written for a child, and both drew from a gossip pool that says
 * "I had two bits".
 *
 * <p>So: within one topic, no two age groups may open the same page or draw from the same pool.
 */
class AgeSeparationTest {

    private static final String SEP = System.lineSeparator();

    /** The hubs whose results choose a branch by age. */
    private static final String HUB_PREFIX = "conversations.cat.";

    /** MCA's age groups, youngest first. Adults are the unconditioned default. */
    private static final List<String> AGE_GROUPS = List.of("baby", "toddler", "child", "teen");

    /** topic -> age group -> the page and pool that age opens. */
    private static Map<String, Map<String, String>> routes;

    @BeforeAll
    static void load() {
        routes = new TreeMap<>();
        for (String id : ContentFixture.graph().questionIds()) {
            if (!id.startsWith(HUB_PREFIX) && !id.equals("conversations.family")
                    && !id.equals("conversations.us")) {
                continue;
            }
            DialogueGraph.Question hub = ContentFixture.graph().question(id).orElseThrow();
            for (DialogueGraph.Answer answer : hub.answers()) {
                for (DialogueGraph.Result result : answer.results()) {
                    String age = ageOf(result);
                    if (age == null) {
                        continue;
                    }
                    routes.computeIfAbsent(answer.name(), t -> new LinkedHashMap<>())
                            .put(age, signature(result));
                }
            }
        }
    }

    /** The age group a result is reserved for, ignoring negative-chance exclusions. */
    private static String ageOf(DialogueGraph.Result result) {
        for (JsonObject condition : result.conditions()) {
            boolean positive = !condition.has("chance") || condition.get("chance").getAsInt() > 0;
            if (positive && condition.has("age_group")) {
                String age = condition.get("age_group").getAsString();
                if (AGE_GROUPS.contains(age)) {
                    return age;
                }
            }
        }
        return null;
    }

    /** What this result actually opens: the page, plus whichever pool supplies the villager's line. */
    private static String signature(DialogueGraph.Result result) {
        JsonObject actions = result.actions();
        StringBuilder out = new StringBuilder();
        out.append(actions.has("next") ? actions.get("next").getAsString() : "(no page)");
        if (actions.has("say")) {
            out.append(" + ").append(actions.get("say").getAsString());
        }
        JsonElement gossip = actions.get("conversations_gossip_say");
        if (gossip != null && gossip.isJsonObject() && gossip.getAsJsonObject().has("phrase_prefix")) {
            out.append(" + ").append(gossip.getAsJsonObject().get("phrase_prefix").getAsString());
        }
        return out.toString();
    }

    @Test
    @DisplayName("some topic actually branches by age — the walk found the routes it needs")
    void thereAreAgeRoutesToCheck() {
        assertTrue(routes.size() >= 10, "only " + routes.size() + " topic(s) branch by age, which"
                + " means this suite is looking in the wrong place rather than that the corpus is fine");
    }

    @Test
    @DisplayName("no two age groups share a page or a pool within one topic")
    void everyAgeGetsItsOwnBranch() {
        List<String> problems = new ArrayList<>();
        routes.forEach((topic, byAge) -> {
            Map<String, List<String>> byRoute = new LinkedHashMap<>();
            byAge.forEach((age, route) ->
                    byRoute.computeIfAbsent(route, r -> new ArrayList<>()).add(age));
            byRoute.forEach((route, ages) -> {
                if (ages.size() > 1) {
                    problems.add(topic + ": " + ages + " all open '" + route
                            + "' — they are three different conversations, not one with smaller words");
                }
            });
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * A branch reserved for an age group has to say so in its own contracts too, or the coherence
     * lints reason about a child's line as though an adult had said it.
     */
    @Test
    @DisplayName("an age-specific page's beats declare the age they belong to")
    void ageBranchesDeclareTheirAge() {
        List<String> problems = new ArrayList<>();
        Set<String> agePages = new TreeSet<>();
        routes.forEach((topic, byAge) -> byAge.forEach((age, route) -> {
            String page = route.split(" [+] ")[0];
            // A branch that returns straight to its category hub has no page of its own to be
            // age-specific about: the hub is shared with every other age and with adults.
            if (page.startsWith("conversations.") && !page.startsWith(HUB_PREFIX)) {
                agePages.add(page + "|" + age);
            }
        }));

        for (String entry : agePages) {
            String[] parts = entry.split("\\|");
            String page = parts[0];
            String age = parts[1];
            ContentFixture.catalog().beats().stream()
                    .filter(beat -> beat.responseQuestion().equals(page))
                    .forEach(beat -> {
                        Set<String> declared = beat.context().ages();
                        if (!declared.isEmpty() && !declared.contains(age)) {
                            problems.add(beat.id() + ": opens " + page + ", which is the " + age
                                    + " branch, but declares ages " + declared);
                        }
                    });
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }
}
