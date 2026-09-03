package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.BeatCatalog;
import dev.otectus.mcaconversations.conversation.BeatContract;
import dev.otectus.mcaconversations.conversation.ReplyContract;
import dev.otectus.mcaconversations.debug.DialogueGraph;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * The shipped content, loaded once, in the form the coherence lints need.
 *
 * <p>Every semantic lint asks its question of the same three things — the dialogue graph, the English
 * corpus, and the beat catalog — so they are loaded here rather than four times over. Loading happens
 * lazily and is cached for the JVM, because six suites reading a 400 KB lang file each is a
 * measurable share of the build.
 */
final class ContentFixture {

    static final Path DIALOGUES = TestPaths.of("src/main/resources/data/mcaconversations/dialogues");
    static final Path BEATS = TestPaths.of("src/main/resources/data/mcaconversations/conversation_beats");
    static final Path LANG_EN = TestPaths.of("src/main/resources/assets/mca_dialogue/lang/en_us.json");
    static final Path LANG_PT = TestPaths.of("src/main/resources/assets/mca_dialogue/lang/pt_br.json");
    static final Path TOPICS = TestPaths.of("src/main/resources/data/mcaconversations/conversation_catalog/topics.json");

    private static DialogueGraph graph;
    private static Map<String, String> english;
    private static BeatCatalog catalog;
    private static List<String> beatProblems;
    private static Set<String> hubs;

    private ContentFixture() {
    }

    /** Every shipped question node, parsed. */
    static synchronized DialogueGraph graph() {
        if (graph == null) {
            Map<String, JsonObject> files = new TreeMap<>();
            try (var stream = Files.list(DIALOGUES)) {
                for (Path file : stream.filter(p -> p.toString().endsWith(".json")).toList()) {
                    String id = file.getFileName().toString().replace(".json", "");
                    files.put(id, JsonParser.parseString(Files.readString(file)).getAsJsonObject());
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            graph = DialogueGraph.of(files);
        }
        return graph;
    }

    /** The base English corpus, keys exactly as shipped (variant suffixes included). */
    static synchronized Map<String, String> english() {
        if (english == null) {
            english = readLang(LANG_EN);
        }
        return english;
    }

    static Map<String, String> readLang(Path path) {
        Map<String, String> out = new TreeMap<>();
        try {
            JsonObject json = JsonParser.parseString(Files.readString(path)).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                out.put(entry.getKey(), entry.getValue().getAsString());
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return out;
    }

    /**
     * The shipped beat contracts. Parse failures are collected rather than thrown so
     * {@code BeatContractLintTest} can report all of them at once instead of the first.
     */
    static synchronized BeatCatalog catalog() {
        if (catalog == null) {
            load();
        }
        return catalog;
    }

    /** Parse problems found while loading the beat catalog; empty when everything parsed. */
    static synchronized List<String> beatProblems() {
        if (beatProblems == null) {
            load();
        }
        return beatProblems;
    }

    private static void load() {
        List<String> problems = new ArrayList<>();
        Map<String, BeatContract> beats = new LinkedHashMap<>();
        Map<String, ReplyContract> replies = new LinkedHashMap<>();

        if (Files.isDirectory(BEATS)) {
            try (var stream = Files.list(BEATS)) {
                for (Path file : stream.filter(p -> p.toString().endsWith(".json")).sorted().toList()) {
                    JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                    readSection(root, "beats", file, problems,
                            (id, json) -> beats.put(id, BeatContract.fromJson(id, json)));
                    readSection(root, "replies", file, problems,
                            (id, json) -> replies.put(id, ReplyContract.fromJson(id, json)));
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        BeatCatalog built;
        try {
            built = BeatCatalog.build(new ArrayList<>(beats.values()), new ArrayList<>(replies.values()));
        } catch (RuntimeException e) {
            problems.add("catalog: " + e.getMessage());
            built = BeatCatalog.EMPTY;
        }
        catalog = built;
        beatProblems = List.copyOf(problems);
    }

    private interface EntryReader {
        void read(String id, JsonObject json);
    }

    private static void readSection(JsonObject root, String section, Path file,
                                    List<String> problems, EntryReader reader) {
        if (!root.has(section) || !root.get(section).isJsonObject()) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject(section).entrySet()) {
            if (!entry.getValue().isJsonObject()) {
                problems.add(file.getFileName() + ": " + section + " entry '" + entry.getKey() + "' is not an object");
                continue;
            }
            try {
                reader.read(entry.getKey(), entry.getValue().getAsJsonObject());
            } catch (RuntimeException e) {
                problems.add(file.getFileName() + ": " + e.getMessage());
            }
        }
    }

    /** Variant-aware line lookup, shared with the trace exporter. */
    /**
     * The dialogue key a result speaks, whichever action carries it.
     *
     * <p>Since 1.5.0 this mod's own lines all go through {@code conversations_say}, so the variant can
     * be named on the server instead of drawn at random on each client. MCA's native {@code say} is
     * still legal in a datapack and still read here, because a lint that stopped seeing it would stop
     * being able to say anything true about a third-party pack.
     *
     * @return the key without its {@code dialogue.} prefix, or null when the result says nothing
     */
    static String spokenPhrase(JsonObject actions) {
        if (actions == null) {
            return null;
        }
        JsonElement ours = actions.get("conversations_say");
        if (ours != null && ours.isJsonObject() && ours.getAsJsonObject().has("phrase")) {
            return ours.getAsJsonObject().get("phrase").getAsString();
        }
        JsonElement native_ = actions.get("say");
        return native_ != null && native_.isJsonPrimitive() ? native_.getAsString() : null;
    }

    static List<String> lines(String langKey) {
        return LangKeys.linesOf(english(), langKey);
    }

    /**
     * The navigation nodes: MCA's own entry points and this mod's category hubs.
     *
     * <p>Derived from the topic catalog rather than hard-coded, so adding a topic that opens a new hub
     * needs no change here. A hub's buttons are the subjects on offer, not replies to what was just
     * said, which is why the adjacency rules do not apply to them.
     */
    static synchronized Set<String> hubQuestions() {
        if (hubs == null) {
            Set<String> out = new TreeSet<>(Set.of("main", "greet", "root"));
            try {
                JsonObject root = JsonParser.parseString(Files.readString(TOPICS)).getAsJsonObject()
                        .getAsJsonObject("topics");
                for (Map.Entry<String, JsonElement> entry : root.entrySet()) {
                    JsonObject topic = entry.getValue().getAsJsonObject();
                    if (topic.has("entry") && topic.getAsJsonObject("entry").has("question")) {
                        out.add(topic.getAsJsonObject("entry").get("question").getAsString());
                    }
                    if (topic.has("return_question")) {
                        out.add(topic.get("return_question").getAsString());
                    }
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            hubs = Set.copyOf(out);
        }
        return hubs;
    }
}
