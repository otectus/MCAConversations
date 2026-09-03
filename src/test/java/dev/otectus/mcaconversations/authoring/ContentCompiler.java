package dev.otectus.mcaconversations.authoring;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dev.otectus.mcaconversations.support.TestPaths;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Turns the authoring sources under {@code src/content/} into runtime resources (spec §19).
 *
 * <h2>Why this exists</h2>
 *
 * <p>A living profession pack is one situation, three or four states, a page of replies for each, a
 * reaction to every reply, a thread, sometimes a promise, and every one of those in two languages. As
 * runtime JSON that is roughly 400 lines per profession before a word of prose — routes, contracts,
 * scene definitions and chat intents that are entirely mechanical and entirely derivable. Copied out
 * thirty-seven times by hand they would drift apart the first time one of them needed a fix.
 *
 * <p>So the author writes the part only an author can write — which situations this trade has, what
 * she says, what you can say back — and this compiles the rest.
 *
 * <h2>What it will not do</h2>
 *
 * <p>It never shares prose between professions. §19.5 is explicit that mechanical route shapes and
 * contract skeletons may be reused and that profession-specific opening prose, concrete risks,
 * methods and callbacks may not. Every line in the output came from that profession's own source
 * file; the compiler supplies structure and nothing else. {@code ContentLintTest} independently
 * refuses byte-identical high-salience lines across unrelated professions, so this is checked rather
 * than promised.
 *
 * <h2>Ownership</h2>
 *
 * <p>Generated files carry a {@code _generated} header and live under names this compiler owns:
 * {@code conversations.scene.*} dialogues, {@code scene_*.json} contracts and intents, and the five
 * narrative-template directories. Hand-authored 1.4.0 content is never read, never rewritten, and
 * never deleted. The two lang files are shared, so the compiler removes only the keys under its own
 * prefixes before writing its own back — a hand-authored key beside them survives untouched.
 */
public final class ContentCompiler {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /** Key prefixes this compiler owns in the shared lang files. */
    static final List<String> OWNED_LANG_PREFIXES = List.of(
            "dialogue.conversations.scene.",
            "mcaconversations.slot.");

    private final Path contentRoot;
    private final Path resourceRoot;
    private final Path fixtureRoot;

    /** Everything staged during one compilation, so a failure writes nothing at all. */
    private final Map<String, JsonObject> dialogues = new TreeMap<>();
    /**
     * Beats, replies and intents are staged per owning mod, because the isolation rule says each
     * optional mod's content lives in a file named after it. "" is the base file.
     */
    private final Map<String, Map<String, JsonObject>> beatsByOwner = new TreeMap<>();
    private final Map<String, Map<String, JsonObject>> repliesByOwner = new TreeMap<>();
    private final Map<String, Map<String, JsonObject>> intentsByOwner = new TreeMap<>();
    /** The owner the current pack is writing under; set by each pack compiler as it starts. */
    private String currentOwner = "";
    private final Map<String, JsonObject> scenes = new TreeMap<>();
    private final Map<String, JsonObject> episodes = new TreeMap<>();
    private final Map<String, JsonObject> threads = new TreeMap<>();
    private final Map<String, JsonObject> commitments = new TreeMap<>();
    private final Map<String, List<String>> intentSynonyms = new TreeMap<>();
    private final Map<String, String> langEn = new TreeMap<>();
    private final Map<String, String> langPt = new TreeMap<>();
    /** Entry routes to splice into an existing hand-authored category page, by question id. */
    private final Map<String, List<JsonObject>> entryRoutes = new TreeMap<>();
    /** Topics whose funnel this run generated, and whose lang keys it therefore owns. */
    private final Set<String> funnelTopics = new TreeSet<>();

    /** Records that a topic's funnel is generated, so its lang keys are cleaned rather than orphaned. */
    void ownFunnelTopic(String topic) {
        funnelTopics.add(topic);
    }

    /** Matcher fixtures the intent test asserts, so every generated reply is typable. */
    private final List<String[]> matcherFixtures = new ArrayList<>();
    /** Answer names offered on each generated page, so a fixture is ranked the way play ranks it. */
    private final Map<String, List<String>> answersOn = new TreeMap<>();

    public ContentCompiler(Path contentRoot, Path resourceRoot) {
        this(contentRoot, resourceRoot, TestPaths.of("src/test/resources"));
    }

    /**
     * The form the drift check uses: every output root is a parameter, so a verification run can
     * compile into a copy of the tree and compare, without writing a byte into the repository.
     */
    public ContentCompiler(Path contentRoot, Path resourceRoot, Path fixtureRoot) {
        this.contentRoot = contentRoot;
        this.resourceRoot = resourceRoot;
        this.fixtureRoot = fixtureRoot;
    }

    public static void main(String[] args) throws IOException {
        Path content = args.length > 0 ? Path.of(args[0]) : TestPaths.of("src/content");
        Path resources = args.length > 1 ? Path.of(args[1]) : TestPaths.of("src/main/resources");
        ContentCompiler compiler = new ContentCompiler(content, resources);
        compiler.compile();
        compiler.write();
        System.out.println("[content] " + compiler.summary());
    }

    // ---------------------------------------------------------------------------------------------
    // Compilation
    // ---------------------------------------------------------------------------------------------

    /** Reads every authoring source and stages the runtime output. Never touches the filesystem. */
    public void compile() {
        for (Path file : sourceFiles("professions")) {
            new ProfessionPackCompiler(this, read(file), file).compile();
        }
        for (Path file : sourceFiles("topics")) {
            new TopicPackCompiler(this, read(file), file).compile();
        }
    }

    List<Path> sourceFiles(String subdirectory) {
        Path directory = contentRoot.resolve(subdirectory);
        if (!Files.isDirectory(directory)) {
            return List.of();
        }
        try (var stream = Files.walk(directory)) {
            return stream.filter(path -> path.toString().endsWith(".json")).sorted().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static JsonObject read(Path file) {
        try {
            return JsonParser.parseString(Files.readString(file)).getAsJsonObject();
        } catch (IOException e) {
            throw new UncheckedIOException("reading " + file, e);
        }
    }

    // --- staging -----------------------------------------------------------------------------

    void addDialogue(String questionId, JsonObject page) {
        if (dialogues.put(questionId, page) != null) {
            throw new IllegalStateException("two sources generated the dialogue page '" + questionId + "'");
        }
        List<String> answers = new ArrayList<>();
        page.getAsJsonArray("answers").forEach(element ->
                answers.add(element.getAsJsonObject().get("name").getAsString()));
        answersOn.put(questionId, answers);
    }

    /** Called by each pack compiler before it stages anything, so output lands in the right file. */
    void beginOwner(String owner) {
        this.currentOwner = owner == null ? "" : owner;
    }

    void addBeat(String id, JsonObject beat) {
        if (owned(beatsByOwner).put(id, beat) != null) {
            throw new IllegalStateException("two sources generated the beat '" + id + "'");
        }
    }

    void addReply(String key, JsonObject reply) {
        if (owned(repliesByOwner).put(key, reply) != null) {
            throw new IllegalStateException("two sources generated the reply '" + key + "'");
        }
    }

    private Map<String, JsonObject> owned(Map<String, Map<String, JsonObject>> byOwner) {
        return byOwner.computeIfAbsent(currentOwner, key -> new TreeMap<>());
    }

    void addScene(String id, JsonObject scene) {
        if (scenes.put(id, scene) != null) {
            throw new IllegalStateException("two sources generated the scene '" + id + "'");
        }
    }

    void addEpisode(String kind, JsonObject template) {
        if (episodes.put(kind, template) != null) {
            throw new IllegalStateException("two sources generated the episode kind '" + kind + "'");
        }
    }

    void addThread(String id, JsonObject template) {
        threads.putIfAbsent(id, template);
    }

    void addCommitment(String id, JsonObject template) {
        commitments.putIfAbsent(id, template);
    }

    void addIntent(String id, JsonObject intent) {
        if (owned(intentsByOwner).put(id, intent) != null) {
            throw new IllegalStateException("two sources generated the chat intent '" + id + "'");
        }
    }

    void addSynonym(String word, List<String> alternatives) {
        intentSynonyms.computeIfAbsent(word, key -> new ArrayList<>()).addAll(alternatives);
    }

    void addLang(String key, String english, String portuguese) {
        if (english == null || english.isBlank()) {
            throw new IllegalStateException("lang key '" + key + "' has no English");
        }
        if (portuguese == null || portuguese.isBlank()) {
            throw new IllegalStateException("lang key '" + key + "' has no Portuguese; both locales are"
                    + " authored together, never one and then the other");
        }
        langEn.put(key, english);
        langPt.put(key, portuguese);
    }

    void addEntryRoute(String questionId, JsonObject route) {
        entryRoutes.computeIfAbsent(questionId, key -> new ArrayList<>()).add(route);
    }

    void addMatcherFixture(String phrase, String question, String intentId) {
        matcherFixtures.add(new String[] {phrase, question, intentId});
    }

    /**
     * Words the system controls own, which a generated keyword may never be.
     *
     * <p>A system intent scores as a ratio over its own keyword weights, so putting "stop" on eighty
     * other intents lowers that stem's idf across the corpus and drags "stop talking" below its
     * threshold. One anchor choice, one unrelated broken control, and nothing in the diff to see.
     */
    static final Set<String> RESERVED_WORDS = Set.of(
            "stop", "quiet", "silence", "hush", "alone", "bye", "goodbye", "farewell", "later",
            "nevermind", "mind", "yes", "no", "maybe", "hello", "hey", "greet", "talk", "talking",
            "shut", "enough");

    /**
     * Identity tokens the shipped catalog actually defines, loaded once from the catalog itself.
     *
     * <p>A selection favour naming a token nobody has is not an error anywhere at runtime — it simply
     * never fires. The scene still ships, still costs lang keys, and is quietly a little less likely
     * to be chosen than its author believed, which is the sort of thing that survives forever.
     */
    private Set<String> identityTokens;

    /** Refuses a selection favour that names a token no villager can ever hold. */
    void checkIdentityTokens(List<String> tokens, String where) {
        if (identityTokens == null) {
            identityTokens = new TreeSet<>();
            Path catalog = resourceRoot.resolve("data/mcaconversations/identity_tokens/base.json");
            try {
                JsonObject root = JsonParser.parseString(Files.readString(catalog)).getAsJsonObject();
                JsonObject tokenSection = root.getAsJsonObject("tokens");
                for (Map.Entry<String, JsonElement> entry : tokenSection.entrySet()) {
                    if (entry.getValue().isJsonObject()
                            && entry.getValue().getAsJsonObject().has("family")) {
                        identityTokens.add(entry.getKey());
                    }
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        for (String token : tokens) {
            if (!identityTokens.contains(token)) {
                throw new IllegalStateException(where + " favours identity token '" + token
                        + "', which the shipped catalog does not define, so no villager can ever hold"
                        + " it and the favour can never apply");
            }
        }
    }

    /** Refuses an anchor that belongs to the system controls. */
    static void checkAnchorsAreNotReserved(List<String> anchors, String where) {
        for (String anchor : anchors) {
            if (RESERVED_WORDS.contains(anchor)) {
                throw new IllegalStateException(where + " anchors on '" + anchor + "', which belongs"
                        + " to the chat-mode system controls. Adding it here lowers that stem's"
                        + " weight across the whole corpus and quietly breaks the control.");
            }
        }
    }

    public List<String[]> matcherFixtures() {
        return List.copyOf(matcherFixtures);
    }

    public String summary() {
        return scenes.size() + " scenes, " + count(beatsByOwner) + " beats, "
                + count(repliesByOwner) + " replies, " + dialogues.size() + " pages, "
                + episodes.size() + " episodes, " + threads.size() + " threads, "
                + commitments.size() + " promises, " + count(intentsByOwner) + " intents, "
                + langEn.size() + " lang keys per locale";
    }

    private static int count(Map<String, Map<String, JsonObject>> byOwner) {
        return byOwner.values().stream().mapToInt(Map::size).sum();
    }

    // ---------------------------------------------------------------------------------------------
    // Output
    // ---------------------------------------------------------------------------------------------

    /**
     * Drops non-anchor keywords that two buttons on the same page share.
     *
     * <p>Derived keywords come from the content words of a button's own phrases, which is what keeps
     * them in step with the wording — but two replies to the same line naturally talk about the same
     * things, so left alone they end up sharing most of their evidence and the matcher cannot tell
     * them apart. On one page each button has to be distinguishable by something, so a shared word
     * carries no weight there.
     *
     * <p>Anchors are never dropped: they are the gate, and a gate the author chose deliberately.
     */
    private void pruneSharedKeywords() {
        Map<String, List<JsonObject>> byQuestion = new LinkedHashMap<>();
        for (Map<String, JsonObject> owned : intentsByOwner.values()) {
            for (JsonObject intent : owned.values()) {
                byQuestion.computeIfAbsent(intent.get("question").getAsString(),
                        key -> new ArrayList<>()).add(intent);
            }
        }
        for (List<JsonObject> page : byQuestion.values()) {
            if (page.size() < 2) {
                continue;
            }
            Map<String, Integer> seen = new LinkedHashMap<>();
            for (JsonObject intent : page) {
                for (String word : intent.getAsJsonObject("keywords").keySet()) {
                    seen.merge(word, 1, Integer::sum);
                }
            }
            for (JsonObject intent : page) {
                Set<String> anchors = new LinkedHashSet<>();
                intent.getAsJsonArray("requiresAny").forEach(a -> anchors.add(a.getAsString()));
                JsonObject keywords = intent.getAsJsonObject("keywords");
                List<String> drop = new ArrayList<>();
                for (String word : keywords.keySet()) {
                    if (!anchors.contains(word) && seen.getOrDefault(word, 0) > 1) {
                        drop.add(word);
                    }
                }
                drop.forEach(keywords::remove);
            }
        }
    }

    /**
     * Refuses two generated intents whose evidence is word for word the same.
     *
     * <p>Keywords are derived from the button's own wording, and synonyms are folded in on the way,
     * so two buttons phrased differently can still compile to the same set — "the hardest part of the
     * craft" and "the hardest part of the trade" both reduce to the hardest part of the work. The
     * matcher then has nothing to choose between, and whichever one it picks is arbitrary. The fix is
     * always in the wording, so the message points at the wording.
     */
    private void checkNoTwoIntentsShareEvidence() {
        Map<String, String> byEvidence = new LinkedHashMap<>();
        for (Map<String, JsonObject> owned : intentsByOwner.values()) {
            for (Map.Entry<String, JsonObject> entry : owned.entrySet()) {
                Set<String> words = new TreeSet<>(entry.getValue().getAsJsonObject("keywords").keySet());
                String evidence = String.join(",", words);
                String other = byEvidence.put(evidence, entry.getKey());
                if (other != null) {
                    throw new IllegalStateException("'" + entry.getKey() + "' and '" + other
                            + "' compile to the same keyword set [" + evidence + "], so the matcher"
                            + " cannot tell them apart. Word one of them around a different noun —"
                            + " and note that trade, craft and job all fold into work.");
                }
            }
        }
    }

    /**
     * Deletes generated dialogue pages this run did not produce.
     *
     * <p>The compiler owns every {@code conversations.scene.*} page, so anything left over from a
     * previous run is a page no source describes any more — which is what happens when a scene is
     * renamed. Left on disk it still loads, still claims lang keys, and routes to beats that no
     * longer exist, and the failure surfaces four lints away from the rename that caused it.
     */
    private void pruneOrphanedPages(Path directory) throws IOException {
        if (!Files.isDirectory(directory)) {
            return;
        }
        try (Stream<Path> files = Files.list(directory)) {
            List<Path> stale = files
                    .filter(path -> path.getFileName().toString().startsWith("conversations.scene."))
                    .filter(path -> {
                        String name = path.getFileName().toString();
                        return !dialogues.containsKey(name.substring(0, name.length() - ".json".length()));
                    })
                    .toList();
            for (Path path : stale) {
                Files.delete(path);
            }
            if (!stale.isEmpty()) {
                System.out.println("[content] pruned " + stale.size() + " page(s) no source describes");
            }
        }
    }

    /** Writes every staged file. Called only after {@link #compile} has succeeded outright. */
    public void write() throws IOException {
        pruneSharedKeywords();
        checkNoTwoIntentsShareEvidence();
        Path data = resourceRoot.resolve("data/mcaconversations");

        for (Map.Entry<String, JsonObject> entry : dialogues.entrySet()) {
            writeJson(data.resolve("dialogues").resolve(entry.getKey() + ".json"), entry.getValue());
        }
        pruneOrphanedPages(data.resolve("dialogues"));
        // One contract file per owner. An optional mod's trades live in a file named after it, so
        // that when that mod renames a profession there is one place to look (isolation rule).
        Set<String> owners = new TreeSet<>(beatsByOwner.keySet());
        owners.addAll(repliesByOwner.keySet());
        for (String owner : owners) {
            writeSectioned(data.resolve("conversation_beats/scene_generated"
                            + (owner.isEmpty() ? "" : "_" + owner) + ".json"),
                    Map.of("beats", beatsByOwner.getOrDefault(owner, Map.of()),
                            "replies", repliesByOwner.getOrDefault(owner, Map.of())),
                    "Semantic contracts for every generated scene (spec 10.1, 10.2).",
                    "Each beat carries a v2 frame - predicate, tense, footing, privacy, the obligations it",
                    "makes relevant - and each non-exit reply names the obligation it fulfils. That pairing",
                    "is what lets the build refuse a page where the villager asks and nothing answers.");
        }
        writeSectioned(data.resolve("conversation_scenes/generated.json"), Map.of("scenes", scenes),
                "When each authored route is the right one (spec 10.4).",
                "A scene is not dialogue: it names a question and an opening beat that already exist,",
                "and everything else is the rule for when the director should choose them.");
        writeSectioned(data.resolve("episode_templates/generated.json"), Map.of("episodes", episodes),
                "The situations villagers are actually in, and what may happen to them.",
                "slot_options are pools picked by a seed made of the world and the villager - never the",
                "day - so the thing somebody is worrying about is theirs and stays theirs.");
        writeSectioned(data.resolve("thread_templates/generated.json"), Map.of("threads", threads),
                "What a villager and a player are in the middle of, and what each is waiting for.");
        writeSectioned(data.resolve("commitment_templates/generated.json"), Map.of("commitments", commitments),
                "Promises the running game can actually observe (spec 8.5).",
                "Every one names a registered resolver. A promise nothing can watch has to be worded as",
                "willingness instead, or declared manual_neutral and never judged.");

        for (Map.Entry<String, Map<String, JsonObject>> entry : intentsByOwner.entrySet()) {
            String owner = entry.getKey();
            JsonObject intentFile = new JsonObject();
            intentFile.add("_generated", header(
                    "Chat intents for every generated reply (spec 18.3).",
                    "Every reply a player can press, a player can also type. Each is bound to its exact page",
                    "through 'context', so a phrase means something only while that page is on screen."));
            if (owner.isEmpty() && !intentSynonyms.isEmpty()) {
                JsonObject synonyms = new JsonObject();
                intentSynonyms.forEach((word, alternatives) -> {
                    JsonArray array = new JsonArray();
                    new TreeSet<>(alternatives).forEach(array::add);
                    synonyms.add(word, array);
                });
                intentFile.add("synonyms", synonyms);
            }
            JsonObject intentBody = new JsonObject();
            entry.getValue().forEach(intentBody::add);
            intentFile.add("intents", intentBody);
            writeJson(data.resolve("chat_intents/scene_generated"
                    + (owner.isEmpty() ? "" : "_" + owner) + ".json"), intentFile);
        }

        // Two namespaces, because the two kinds of key belong in different places. Dialogue lines
        // extend MCA's own pooled dialogue namespace, which is what makes /1 /2 /3 variants work at
        // all. Slot nouns are this mod's vocabulary and go in this mod's namespace, where the
        // dead-key lint over MCA's file will not look for something referencing them.
        // "mcaconversations.slot." is listed as owned here too, so slot nouns written into MCA's
        // dialogue file before the namespaces were split are cleaned out rather than left as orphans.
        // A generated funnel writes under "dialogue.conversations.topic.<id>." and
        // "dialogue.conversations.<id>.", which the hand-written topics also live in. Claiming those
        // prefixes wholesale would delete somebody else's lines, so the compiler claims exactly the
        // topics it generated a funnel for this run — and nothing else.
        List<String> dialogueOwned = new ArrayList<>(
                List.of("dialogue.conversations.scene.", "mcaconversations.slot."));
        for (String owned : funnelTopics) {
            dialogueOwned.add("dialogue.conversations.topic." + owned + ".");
            dialogueOwned.add("dialogue.conversations." + owned + ".");
        }
        mergeLang(resourceRoot.resolve("assets/mca_dialogue/lang/en_us.json"),
                withPrefix(langEn, "dialogue."), dialogueOwned);
        mergeLang(resourceRoot.resolve("assets/mca_dialogue/lang/pt_br.json"),
                withPrefix(langPt, "dialogue."), dialogueOwned);
        mergeLang(resourceRoot.resolve("assets/mcaconversations/lang/en_us.json"),
                withoutPrefix(langEn, "dialogue."), List.of("mcaconversations.slot."));
        mergeLang(resourceRoot.resolve("assets/mcaconversations/lang/pt_br.json"),
                withoutPrefix(langPt, "dialogue."), List.of("mcaconversations.slot."));
        spliceEntryRoutes(data.resolve("dialogues"));
        writeMatcherFixtures(fixtureRoot.resolve("generated_matcher_fixtures.tsv"));
    }

    /**
     * Writes the fixtures that prove every generated button is typable.
     *
     * <p>Generated rather than hand-maintained because they are derived from the same authored phrases
     * the intents are: a hand-written list would be wrong the first time somebody rewrote a button, and
     * silently so — the test would still pass, against the old wording.
     */
    private void writeMatcherFixtures(Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("# GENERATED by ContentCompiler — do not edit by hand.");
        lines.add("# phrase<TAB>question<TAB>intent-id");
        List<String[]> sorted = new ArrayList<>(matcherFixtures);
        sorted.sort((a, b) -> {
            int byIntent = a[2].compareTo(b[2]);
            return byIntent != 0 ? byIntent : a[0].compareTo(b[0]);
        });
        for (String[] fixture : sorted) {
            // The page's answers ride along, because these replies are only ever matched while their
            // decision page is open — and that is the ranking they have to survive.
            String answers = String.join(",", answersOn.getOrDefault(fixture[1], List.of()));
            lines.add(fixture[0] + "\t" + fixture[1] + "\t" + fixture[2] + "\t" + answers);
        }
        Files.createDirectories(file.getParent());
        Files.writeString(file, String.join("\n", lines) + "\n");
    }

    /**
     * Splices generated entry routes into hand-authored category pages.
     *
     * <p>The one place the compiler touches a file it does not own, and it is surgical: it removes
     * exactly the routes it added last time — recognised by their {@code conversations_scene}
     * condition — and adds the current set back. Hand-authored routes in the same answer are read,
     * preserved and rewritten untouched.
     */
    private void spliceEntryRoutes(Path dialogueDir) throws IOException {
        // Every generated route comes out first, everywhere, before any goes back in. Cleaning only
        // the pages this run targets leaves a route behind whenever a topic is renamed or moved —
        // pointing at a scene that no longer exists, on a page nobody thought to look at.
        stripGeneratedRoutes(dialogueDir);

        Map<String, Map<String, List<JsonObject>>> byPage = new TreeMap<>();
        entryRoutes.forEach((target, routes) -> {
            int slash = target.lastIndexOf('/');
            String page = target.substring(0, slash);
            String answer = target.substring(slash + 1);
            byPage.computeIfAbsent(page, key -> new TreeMap<>()).put(answer, routes);
        });

        for (Map.Entry<String, Map<String, List<JsonObject>>> page : byPage.entrySet()) {
            Path file = dialogueDir.resolve(page.getKey() + ".json");
            if (!Files.exists(file)) {
                throw new IllegalStateException("entry routes target '" + page.getKey()
                        + "', which is not a shipped dialogue page");
            }
            JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
            Set<String> spliced = new TreeSet<>();
            for (JsonElement answerElement : root.getAsJsonArray("answers")) {
                JsonObject answer = answerElement.getAsJsonObject();
                List<JsonObject> generated = page.getValue().get(answer.get("name").getAsString());
                if (generated == null) {
                    continue;
                }
                spliced.add(answer.get("name").getAsString());
                JsonArray kept = answer.getAsJsonArray("results");
                JsonArray merged = new JsonArray();
                generated.forEach(merged::add);
                kept.forEach(merged::add);
                answer.add("results", merged);
            }
            // An answer that is not on the page is a scene nobody can ever reach: the routes are
            // written, the lang keys are claimed, and no button leads there. Silently skipping it
            // is the one failure mode of this splice that would never show up in a test.
            Set<String> missing = new TreeSet<>(page.getValue().keySet());
            missing.removeAll(spliced);
            if (!missing.isEmpty()) {
                throw new IllegalStateException("entry routes name answers " + missing + " on '"
                        + page.getKey() + "', which that page does not offer — the scenes behind"
                        + " them would be unreachable");
            }
            Files.writeString(file, GSON.toJson(root) + "\n");
        }
    }

    /** Removes every route this compiler owns from every shipped dialogue page. */
    private void stripGeneratedRoutes(Path dialogueDir) throws IOException {
        if (!Files.isDirectory(dialogueDir)) {
            return;
        }
        List<Path> pages;
        try (Stream<Path> files = Files.list(dialogueDir)) {
            pages = files.filter(path -> path.getFileName().toString().endsWith(".json")).toList();
        }
        for (Path page : pages) {
            JsonObject root = JsonParser.parseString(Files.readString(page)).getAsJsonObject();
            if (!root.has("answers") || !root.get("answers").isJsonArray()) {
                continue;
            }
            boolean changed = false;
            for (JsonElement answerElement : root.getAsJsonArray("answers")) {
                JsonObject answer = answerElement.getAsJsonObject();
                if (!answer.has("results") || !answer.get("results").isJsonArray()) {
                    continue;
                }
                JsonArray kept = new JsonArray();
                for (JsonElement resultElement : answer.getAsJsonArray("results")) {
                    if (isGeneratedRoute(resultElement.getAsJsonObject())) {
                        changed = true;
                    } else {
                        kept.add(resultElement);
                    }
                }
                answer.add("results", kept);
            }
            if (changed) {
                Files.writeString(page, GSON.toJson(root) + System.lineSeparator());
            }
        }
    }

    /** A route this compiler owns: one whose conditions test a preselected scene. */
    static boolean isGeneratedRoute(JsonObject result) {
        // A generated route says so in the branch it opens the session on. Recognising them by a
        // scene condition alone missed the funnel route, which has none — so every run added another
        // copy of it to the button and nothing ever took one away.
        if (result.has("actions") && result.get("actions").isJsonObject()) {
            JsonObject actions = result.getAsJsonObject("actions");
            if (actions.has("conversations_session")
                    && actions.get("conversations_session").isJsonObject()) {
                JsonObject session = actions.getAsJsonObject("conversations_session");
                if (session.has("branch")
                        && GENERATED_BRANCHES.contains(session.get("branch").getAsString())) {
                    return true;
                }
            }
        }
        if (!result.has("conditions") || !result.get("conditions").isJsonArray()) {
            return false;
        }
        for (JsonElement condition : result.getAsJsonArray("conditions")) {
            if (condition.isJsonObject() && condition.getAsJsonObject().has("conversations_scene")) {
                return true;
            }
        }
        return false;
    }

    /** Session branches this compiler owns; a route opening one of them is its to remove. */
    private static final Set<String> GENERATED_BRANCHES = Set.of("scene", "funnel");

    /**
     * Merges generated keys into a shared lang file.
     *
     * <p>Removes the compiler's own prefixes first, so a scene that was deleted from the sources takes
     * its lang keys with it rather than leaving orphans that the parity test would then demand in both
     * locales forever.
     */
    private void mergeLang(Path file, Map<String, String> generated, List<String> ownedPrefixes)
            throws IOException {
        Map<String, String> merged = new TreeMap<>();
        boolean wasSorted = true;
        if (Files.exists(file)) {
            JsonObject existing = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
            List<String> order = new ArrayList<>(existing.keySet());
            wasSorted = order.equals(order.stream().sorted().toList());
            for (Map.Entry<String, JsonElement> entry : existing.entrySet()) {
                if (ownedPrefixes.stream().noneMatch(prefix -> entry.getKey().startsWith(prefix))) {
                    merged.put(entry.getKey(), entry.getValue().getAsString());
                }
            }
        }
        merged.putAll(generated);
        JsonObject out = new JsonObject();
        // Sorted output for a file that was already sorted; the mod's own lang file is hand-ordered
        // and re-sorting it would be a diff of forty moved lines for nothing.
        if (wasSorted) {
            merged.forEach(out::addProperty);
        } else {
            new LinkedHashMap<>(merged).forEach(out::addProperty);
        }
        Files.createDirectories(file.getParent());
        Files.writeString(file, GSON.toJson(out) + "\n");
    }

    private static Map<String, String> withPrefix(Map<String, String> source, String prefix) {
        Map<String, String> out = new TreeMap<>();
        source.forEach((key, value) -> {
            if (key.startsWith(prefix)) {
                out.put(key, value);
            }
        });
        return out;
    }

    private static Map<String, String> withoutPrefix(Map<String, String> source, String prefix) {
        Map<String, String> out = new TreeMap<>();
        source.forEach((key, value) -> {
            if (!key.startsWith(prefix)) {
                out.put(key, value);
            }
        });
        return out;
    }

    private void writeSectioned(Path file, Map<String, Map<String, JsonObject>> sections,
                                String... notes) throws IOException {
        JsonObject root = new JsonObject();
        root.add("_generated", header(notes));
        // Sorted section names so the file shape does not depend on map iteration order.
        for (String name : new TreeSet<>(sections.keySet())) {
            JsonObject body = new JsonObject();
            sections.get(name).forEach(body::add);
            root.add(name, body);
        }
        writeJson(file, root);
    }

    private static JsonArray header(String... notes) {
        JsonArray array = new JsonArray();
        array.add("GENERATED by ContentCompiler from src/content/ — do not edit by hand.");
        array.add("Run ./gradlew generateConversationContent after changing an authoring source.");
        for (String note : notes) {
            array.add(note);
        }
        return array;
    }

    private static void writeJson(Path file, JsonObject json) throws IOException {
        Files.createDirectories(file.getParent());
        Files.writeString(file, GSON.toJson(json) + "\n");
    }

    // ---------------------------------------------------------------------------------------------
    // Shared helpers used by the per-kind compilers
    // ---------------------------------------------------------------------------------------------

    static String require(JsonObject json, String field, String where) {
        if (json == null || !json.has(field) || !json.get(field).isJsonPrimitive()) {
            throw new IllegalStateException(where + " requires a \"" + field + "\"");
        }
        return json.get(field).getAsString().trim();
    }

    static List<String> strings(JsonObject json, String field) {
        List<String> out = new ArrayList<>();
        if (json == null || !json.has(field)) {
            return out;
        }
        JsonElement element = json.get(field);
        if (element.isJsonPrimitive()) {
            out.add(element.getAsString().trim());
            return out;
        }
        for (JsonElement item : element.getAsJsonArray()) {
            out.add(item.getAsString().trim());
        }
        return out;
    }

    static JsonArray array(Iterable<String> values) {
        JsonArray out = new JsonArray();
        values.forEach(out::add);
        return out;
    }

    static JsonObject object(JsonObject json, String field) {
        return json != null && json.has(field) && json.get(field).isJsonObject()
                ? json.getAsJsonObject(field) : null;
    }

    /** Normalises a phrase the way the chat matcher does, so a fixture matches what a player types. */
    static String normalizePhrase(String phrase) {
        return phrase.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9 ]", "").replaceAll("\\s+", " ").trim();
    }

    /**
     * Refuses a phrase that has no live anchor left in it.
     *
     * <p>The matcher opens a three-token negation window: everything inside it is filed as negated
     * evidence rather than positive, so an anchor in there never fires. What a phrase needs, then, is
     * at least one {@code requires_any} word standing outside any such window — and each phrase needs
     * its own, because each phrase is a separate thing a player might type.
     *
     * <p>Without this the failure is silent: the intent parses, the coverage test passes, and the
     * button simply cannot be said. The fix in content is almost always to word the button as
     * something it asserts rather than something it denies, which §18.2 prefers anyway.
     */
    static void checkAnchorsAreNotNegated(List<String> anchors, List<String> phrases, String where) {
        Set<String> negators = Set.of("not", "no", "never", "dont", "cant", "couldnt", "wont",
                "wouldnt", "doesnt", "didnt", "isnt", "arent", "havent", "hasnt", "nothing", "nobody");
        Set<String> stops = Set.of("a", "an", "the", "to", "of", "in", "on", "at", "is", "are", "be",
                "was", "were", "do", "does", "did", "i", "you", "it", "that", "this", "about", "for",
                "and", "or", "my", "your", "me", "we", "us", "so", "as", "with", "if", "would",
                "could", "will", "can", "have", "has", "had", "am");
        for (String phrase : phrases) {
            boolean live = false;
            int window = 0;
            for (String word : phrase.split(" ")) {
                if (negators.contains(word)) {
                    window = 3;
                    continue;
                }
                boolean negated = window > 0;
                if (negated && !stops.contains(word)) {
                    window--;
                }
                if (!negated && anchors.contains(word)) {
                    live = true;
                    break;
                }
            }
            if (!live) {
                throw new IllegalStateException(where + " phrase \"" + phrase + "\" contains no"
                        + " un-negated anchor from " + anchors + ". The matcher files everything"
                        + " within three words of a negator as negated evidence, so this phrase could"
                        + " never match. Word it as something it asserts, or anchor on a word outside"
                        + " the negation.");
            }
        }
    }

    /** The distinct content words of a phrase, for deriving a keyword set. */
    static Set<String> contentWords(String phrase) {
        Set<String> stop = Set.of("i", "you", "the", "a", "an", "is", "it", "to", "of", "and", "in",
                "that", "this", "for", "on", "at", "be", "was", "are", "do", "did", "have", "has",
                "not", "no", "my", "your", "me", "we", "us", "if", "so", "as", "with", "what", "how",
                "will", "would", "can", "could", "just", "about", "them", "they", "there", "then");
        Set<String> out = new LinkedHashSet<>();
        for (String word : normalizePhrase(phrase).split(" ")) {
            if (word.length() > 2 && !stop.contains(word)) {
                out.add(word);
            }
        }
        return out;
    }

    Map<String, JsonObject> scenesForTesting() {
        return new LinkedHashMap<>(scenes);
    }
}
