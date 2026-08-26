package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.debug.DialogueGraph;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Six of the thirty-seven trades belong to mods this one does not require (spec §7.1, phase 5).
 *
 * <p>Two things have to stay true about them, and neither is visible by reading a single file. The
 * first is that no optional mod is a dependency: this mod must install, load and hold every one of
 * its conversations with all six absent. The second is that each optional mod's content lives in
 * files named after it — the loaders merge a directory, so which file a beat sits in changes nothing
 * at runtime, and changes everything when Vampirism renames a profession and somebody has to find
 * every line this mod says about it.
 *
 * <p>The roster is read from the shipped work router, so a seventh optional mod is covered the day
 * it is added rather than the day somebody remembers to update this test.
 */
class OptionalProfessionIsolationTest {

    private static final String SEP = System.lineSeparator();

    private static final Path BEATS = ContentFixture.BEATS;
    private static final Path INTENTS = Path.of("src/main/resources/data/mcaconversations/chat_intents");
    private static final Path PROFILES = Path.of("src/main/resources/data/mcaconversations/profession_profiles");
    private static final Path MODS_TOML = Path.of("src/main/resources/META-INF/mods.toml");

    /** Namespaces whose professions exist with no optional mod installed. */
    private static final Set<String> BASE_NAMESPACES = Set.of("minecraft", "mca");

    /** Mods this one really does declare, none of which owns a profession. */
    private static final Set<String> DECLARED_MODS =
            Set.of("forge", "minecraft", "mca", "mcaquests", "mcareputation", "townstead");

    private static final Pattern WORK_ID =
            Pattern.compile("(?:conversations\\.topic\\.)?work\\.(?:prof\\.)?([a-z_]+)[.$]");

    /** profession path -> owning mod, for the optional ones only. */
    private static Map<String, String> owners;

    @BeforeAll
    static void load() {
        owners = new TreeMap<>();
        DialogueGraph.Question router =
                ContentFixture.graph().question("conversations.work").orElseThrow();
        for (DialogueGraph.Answer answer : router.answers()) {
            for (DialogueGraph.Result result : answer.results()) {
                for (JsonObject condition : result.conditions()) {
                    if (!condition.has("profession")) {
                        continue;
                    }
                    String id = condition.get("profession").getAsString();
                    int colon = id.indexOf(':');
                    String namespace = id.substring(0, colon);
                    if (!BASE_NAMESPACES.contains(namespace)) {
                        owners.put(id.substring(colon + 1), namespace);
                    }
                }
            }
        }
    }

    /** The owning mod of a beat id, reply key or question node, or null if it is a base trade's. */
    private static String ownerOf(String id) {
        Matcher matcher = WORK_ID.matcher(id + ".");
        return matcher.lookingAt() ? owners.get(matcher.group(1)) : null;
    }

    private static Map<String, JsonObject> filesIn(Path directory) {
        Map<String, JsonObject> out = new TreeMap<>();
        try (var stream = Files.list(directory)) {
            for (Path file : stream.filter(p -> p.toString().endsWith(".json")).sorted().toList()) {
                out.put(file.getFileName().toString(),
                        JsonParser.parseString(Files.readString(file)).getAsJsonObject());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    @Test
    @DisplayName("the optional roster is what the release claims it is")
    void rosterIsTheExpectedSix() {
        Set<String> mods = new TreeSet<>(owners.values());
        assertFalse(mods.isEmpty(), "the work router branches on no optional professions at all");
        assertTrue(mods.equals(Set.of("ars_nouveau", "chefsdelight", "iceandfire",
                        "morevillagers", "vampirism", "werewolves")),
                "the optional profession roster has changed to " + mods
                        + " — update the release notes and the profile files to match");
    }

    /**
     * The one that matters at load time: an absent mod must be absent, not missing. Nothing about a
     * profession from another mod may appear in {@code mods.toml}, because a declared dependency —
     * even an optional one — is a claim about a mod this one has never needed.
     */
    @Test
    @DisplayName("no profession-owning mod is declared as a dependency, optional or otherwise")
    void noOptionalHardDependency() throws IOException {
        String toml = Files.readString(MODS_TOML);
        List<String> problems = new ArrayList<>();

        Matcher declared = Pattern.compile("modId\\s*=\\s*\"([^\"]+)\"").matcher(toml);
        while (declared.find()) {
            String modId = declared.group(1);
            if (modId.startsWith("${")) {
                continue;  // the [[mods]] block naming this mod itself, expanded at build time
            }
            if (owners.containsValue(modId)) {
                problems.add(modId + " owns a profession and is declared in mods.toml — this mod must"
                        + " neither require nor announce it");
            }
            if (!DECLARED_MODS.contains(modId)) {
                problems.add(modId + " is declared in mods.toml and is not one of the mods this"
                        + " release integrates with");
            }
        }

        Matcher mandatory = Pattern.compile(
                "modId\\s*=\\s*\"([^\"]+)\"\\s*\\R\\s*mandatory\\s*=\\s*true").matcher(toml);
        Set<String> required = new TreeSet<>();
        while (mandatory.find()) {
            required.add(mandatory.group(1));
        }
        if (!required.equals(Set.of("forge", "minecraft", "mca"))) {
            problems.add("the mandatory dependencies are " + required
                    + ", and only forge, minecraft and mca may ever be mandatory");
        }

        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every optional profession's beats and replies live in a file named after its mod")
    void beatsAreOwnedByFile() {
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, JsonObject> file : filesIn(BEATS).entrySet()) {
            String name = file.getKey().replace(".json", "");
            for (String section : List.of("beats", "replies")) {
                if (!file.getValue().has(section)) {
                    continue;
                }
                for (String key : file.getValue().getAsJsonObject(section).keySet()) {
                    String owner = ownerOf(section.equals("replies")
                            ? key.substring(0, Math.max(key.lastIndexOf('/'), 0)) : key);
                    if (owner == null) {
                        continue;
                    }
                    if (!name.endsWith("_" + owner)) {
                        problems.add(file.getKey() + ": " + key + " belongs to " + owner
                                + " and should be in a file whose name ends '_" + owner + "'");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("an owner's file holds nothing but that owner's professions")
    void ownerFilesAreNotDumpingGrounds() {
        List<String> problems = new ArrayList<>();
        Map<String, JsonObject> all = new LinkedHashMap<>(filesIn(BEATS));
        all.putAll(filesIn(INTENTS));
        for (Map.Entry<String, JsonObject> file : all.entrySet()) {
            String name = file.getKey().replace(".json", "");
            String claimed = null;
            for (String mod : new TreeSet<>(owners.values())) {
                if (name.endsWith("_" + mod)) {
                    claimed = mod;
                }
            }
            if (claimed == null) {
                continue;
            }
            for (Map.Entry<String, JsonElement> section : file.getValue().entrySet()) {
                for (Map.Entry<String, JsonElement> entry
                        : section.getValue().getAsJsonObject().entrySet()) {
                    String subject = section.getKey().equals("intents")
                            ? entry.getValue().getAsJsonObject().get("question").getAsString()
                            : entry.getKey();
                    String owner = ownerOf(subject.contains("/")
                            ? subject.substring(0, subject.lastIndexOf('/')) : subject);
                    if (!claimed.equals(owner)) {
                        problems.add(file.getKey() + ": " + entry.getKey() + " is not one of "
                                + claimed + "'s professions");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * A chat intent bound to an optional profession's page must be scoped to that page. A global
     * intent would compete for every typed line whether the mod is installed or not, so an absent
     * mod would still cost the player a wrong match.
     */
    @Test
    @DisplayName("optional professions' chat intents are scoped to their own pages")
    void optionalIntentsAreContextScoped() {
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, JsonObject> file : filesIn(INTENTS).entrySet()) {
            if (!file.getValue().has("intents")) {
                continue;
            }
            for (Map.Entry<String, JsonElement> entry
                    : file.getValue().getAsJsonObject("intents").entrySet()) {
                JsonObject intent = entry.getValue().getAsJsonObject();
                String question = intent.has("question") ? intent.get("question").getAsString() : "";
                String owner = ownerOf(question);
                if (owner == null) {
                    continue;
                }
                if (!intent.has("context")) {
                    problems.add(file.getKey() + ": " + entry.getKey() + " is bound to " + owner
                            + " and would compete globally with " + owner + " absent");
                } else if (!question.equals(intent.get("context").getAsString())) {
                    problems.add(file.getKey() + ": " + entry.getKey() + " is scoped to '"
                            + intent.get("context").getAsString() + "' but answers '" + question + "'");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every optional profession's profile is in its own mod's profile file")
    void profilesAreOwnedByFile() {
        List<String> problems = new ArrayList<>();
        Set<String> covered = new TreeSet<>();
        for (Map.Entry<String, JsonObject> file : filesIn(PROFILES).entrySet()) {
            String name = file.getKey().replace(".json", "");
            for (String id : file.getValue().getAsJsonObject("profiles").keySet()) {
                String namespace = id.substring(0, id.indexOf(':'));
                boolean base = BASE_NAMESPACES.contains(namespace);
                if (base && !name.equals("base")) {
                    problems.add(file.getKey() + ": " + id + " is a base profession and belongs in"
                            + " base.json");
                } else if (!base && !name.equals(namespace)) {
                    problems.add(file.getKey() + ": " + id + " belongs in " + namespace + ".json");
                } else if (!base) {
                    covered.add(namespace);
                }
            }
        }
        Set<String> missing = new TreeSet<>(owners.values());
        missing.removeAll(covered);
        if (!missing.isEmpty()) {
            problems.add("optional mods whose professions have no profile file of their own: " + missing);
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * The whole point of the split: removing one mod's files must remove exactly that mod's
     * conversations and nothing else. Every page an optional profession opens has to be named after
     * it, so a maintainer can list them without reading any of them.
     */
    @Test
    @DisplayName("each optional profession's pages are named after it and nothing else names them")
    void pagesAreNamedAfterTheirProfession() {
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, String> entry : owners.entrySet()) {
            String prefix = "conversations.topic.work." + entry.getKey() + ".";
            List<String> pages = ContentFixture.graph().questionIds().stream()
                    .filter(q -> q.startsWith(prefix)).sorted().toList();
            if (pages.size() < 6) {
                problems.add(entry.getValue() + ":" + entry.getKey() + " has " + pages.size()
                        + " pages of its own, and six subjects is the floor");
            }
            for (String page : pages) {
                if (ContentFixture.catalog().inbound(page).isEmpty()) {
                    problems.add(page + ": nothing declares what opens it, so removing "
                            + entry.getValue() + " would leave an unreachable page behind");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }
}
