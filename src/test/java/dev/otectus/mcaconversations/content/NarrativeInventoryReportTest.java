package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.support.TestPaths;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Exhaustive mechanical index and explicitly non-authoritative prose review queue. */
class NarrativeInventoryReportTest {
    private static final Path DATA = TestPaths.of("src/main/resources/data/mcaconversations");
    private static final Path SOURCE = TestPaths.of("src/content");
    private static final Path ASSETS = TestPaths.of("src/main/resources/assets");
    private static final Path OUT = TestPaths.of("build/reports/conversations");
    private static final Pattern CLAIM_CANDIDATE = Pattern.compile(
            "(?i)\\b(?:yesterday|last (?:week|month|winter|summer)|(?:four|six|nine|eleven|twenty|thirty) years|"
                    + "when I was|I (?:saw|watched|remember)|you (?:promised|told|helped)|everybody (?:knows|thinks)|"
                    + "ask me (?:again|on)|come (?:by|back) (?:on|at)|ontem|semana passada|eu vi|você prometeu)\\b");

    @Test
    void writesFullInventoryAndAnHonestSemanticReviewQueue() throws IOException {
        Files.createDirectories(OUT);
        JsonObject report = new JsonObject();
        report.addProperty("coverage", "Every shipped data file, dialogue result, authored source, locale namespace, and voice key is indexed."
                + " Adjacency reports contain the complete English/Portuguese exchanges."
                + " The heuristic claim queue is a review aid, not proof of a bug or proof that unflagged text is sound.");
        JsonObject dataIndex = new JsonObject();
        JsonArray routes = new JsonArray();
        Map<String, Integer> categoryCounts = new TreeMap<>();
        int questionCount = 0;
        int answerCount = 0;
        for (Path file : jsonFiles(DATA)) {
            String relative = relative(DATA, file);
            JsonObject json = read(file);
            JsonObject metadata = new JsonObject();
            JsonArray roots = new JsonArray();
            new TreeSet<>(json.keySet()).forEach(roots::add);
            metadata.add("root_fields", roots);
            metadata.addProperty("bytes", Files.size(file));
            dataIndex.add(relative, metadata);
            categoryCounts.merge(relative.split("/")[0], 1, Integer::sum);
            if (!relative.startsWith("dialogues/") || !json.has("answers")) continue;
            questionCount++;
            String question = file.getFileName().toString().replace(".json", "");
            for (JsonElement entry : json.getAsJsonArray("answers")) {
                JsonObject answer = entry.getAsJsonObject();
                answerCount++;
                String name = answer.has("name") ? answer.get("name").getAsString() : "(auto)";
                if (!answer.has("results")) continue;
                int index = 0;
                for (JsonElement value : answer.getAsJsonArray("results")) {
                    JsonObject row = new JsonObject();
                    row.addProperty("route", question + "/" + name + "/" + index++);
                    row.addProperty("question_auto", json.has("auto") && json.get("auto").getAsBoolean());
                    row.addProperty("label", "dialogue." + question + "." + name);
                    row.add("result", value.deepCopy());
                    routes.add(row);
                }
            }
        }
        report.add("data_files", dataIndex);
        report.add("dialogue_routes", routes);

        JsonObject authored = new JsonObject();
        List<String> table = new ArrayList<>();
        table.add("| Source | Scenes | Replies (all depths) | Ages | Professions | Context fields |\n|---|---:|---:|---|---|---|");
        int topicCount = 0, professionCount = 0, voiceFiles = 0;
        for (Path file : jsonFiles(SOURCE)) {
            String relative = relative(SOURCE, file);
            JsonObject source = read(file);
            JsonObject metadata = new JsonObject();
            JsonArray ids = new JsonArray();
            if (source.has("scenes")) {
                for (JsonElement scene : source.getAsJsonArray("scenes")) {
                    JsonObject s = scene.getAsJsonObject();
                    JsonObject entry = new JsonObject();
                    for (String key : List.of("name", "subject", "ages", "professions", "archetypes", "relationships", "conditions",
                            "identity", "identity_values", "identity_styles", "identity_interests", "episode_kind", "episode_state",
                            "thread", "cooldown_days", "max_mentions_per_7_days", "privacy", "epistemic", "min_variants")) {
                        if (s.has(key)) entry.add(key, s.get(key).deepCopy());
                    }
                    entry.addProperty("opening_variants", s.has("lines") ? s.getAsJsonObject("lines").getAsJsonArray("en").size() : 0);
                    entry.addProperty("nested_reply_count", countReplies(s));
                    ids.add(entry);
                }
            }
            metadata.add("scenes", ids);
            metadata.addProperty("reply_count", countReplies(source));
            metadata.addProperty("funnel", source.has("funnel"));
            for (String key : List.of("topic", "profession", "ages", "family", "members", "episodes")) {
                if (source.has(key)) metadata.add(key, source.get(key).deepCopy());
            }
            if (source.has("lines")) {
                JsonArray keys = new JsonArray();
                new TreeSet<>(source.getAsJsonObject("lines").keySet()).forEach(keys::add);
                metadata.add("voice_keys", keys);
            }
            TreeSet<String> fields = new TreeSet<>();
            collectFieldIds(source, fields);
            if (!relative.startsWith("voices/")) table.add("| " + relative + " | " + ids.size() + " | "
                    + countReplies(source) + " | " + (source.has("ages") ? source.get("ages") : "inherited")
                    + " | " + (source.has("profession") ? source.get("profession") : "context/profile")
                    + " | " + String.join(", ", fields) + " |");
            if (relative.startsWith("topics/")) topicCount++;
            if (relative.startsWith("professions/")) professionCount++;
            if (relative.startsWith("voices/")) voiceFiles++;
            authored.add(relative, metadata);
        }
        report.add("authored_sources", authored);

        JsonObject locales = new JsonObject();
        List<String> candidates = new ArrayList<>();
        candidates.add("namespace\tlocale\tkey\ttext\treview_status");
        for (Path file : jsonFiles(ASSETS)) {
            String relative = relative(ASSETS, file);
            if (!relative.contains("/lang/")) continue;
            JsonObject lang = read(file);
            JsonObject indexed = new JsonObject();
            indexed.addProperty("key_count", lang.size());
            JsonArray keys = new JsonArray();
            for (String key : new TreeSet<>(lang.keySet())) {
                keys.add(key);
                if (!lang.get(key).isJsonPrimitive()) continue;
                String line = lang.get(key).getAsString();
                if (CLAIM_CANDIDATE.matcher(line).find()) {
                    candidates.add(relative.split("/")[0] + "\t" + file.getFileName() + "\t" + key + "\t"
                            + line.replace('\t', ' ').replace('\n', ' ') + "\tneeds_context_review_not_a_proven_defect");
                }
            }
            indexed.add("keys", keys);
            locales.add(relative, indexed);
        }
        report.add("locale_inventory", locales);
        String summary = "# Narrative inventory and review coverage\n\n"
                + "This report enumerates every shipped dialogue result, source scene and nested reply, data file, "
                + "context field used by a source, profession source, voice key, and locale key. "
                + "The full English and Portuguese text adjacency lives in `adjacency.md` and `adjacency.pt_br.md`.\n\n"
                + "The stabilization edit manually reviewed all 60 ordinary dynamic topic openings and their response pools, "
                + "all seven generated generic funnels, the village-change episode branches, the 15 group variants, and the newly added depth/voice content. "
                + "Capitals has a separate supported-capability review. Existing profession and legacy content was indexed and linted; "
                + "this report does not claim a complete human semantic review of every legacy or profession sentence.\n\n"
                + "`narrative-claim-review.tsv` flags wording that may assert a past event, precise history, or future appointment. "
                + "Each flag needs its actual gates, episode and knowledge provenance checked; many flags are legitimate. "
                + "No absence of a flag proves that prose is correct.\n\n"
                + "- Dialogue questions: " + questionCount + "\n- Answers: " + answerCount + "\n- Results: " + routes.size()
                + "\n- Topic authoring sources: " + topicCount + "\n- Profession authoring sources: " + professionCount
                + "\n- Voice family/special source files: " + voiceFiles + "\n- Locale files: " + locales.size()
                + "\n- Heuristic claim-review candidates: " + (candidates.size()-1) + "\n\n"
                + "## Data files by system\n\n" + categoryCounts + "\n\n## Authored scenes and gates\n\n"
                + String.join("\n", table) + "\n";
        Files.writeString(OUT.resolve("narrative-inventory.json"), new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(report) + "\n");
        Files.writeString(OUT.resolve("narrative-inventory.md"), summary);
        Files.writeString(OUT.resolve("narrative-claim-review.tsv"), String.join("\n", candidates) + "\n");
        assertTrue(questionCount > 100 && routes.size() > 500 && professionCount >= 30 && topicCount >= 30,
                "Inventory must find the complete authored/runtime trees rather than silently scanning an empty directory");
    }

    private static int countReplies(JsonElement value) {
        int count = 0;
        if (value.isJsonObject()) {
            JsonObject object = value.getAsJsonObject();
            if (object.has("replies") && object.get("replies").isJsonArray()) count += object.getAsJsonArray("replies").size();
            for (JsonElement child : object.asMap().values()) count += countReplies(child);
        } else if (value.isJsonArray()) {
            for (JsonElement child : value.getAsJsonArray()) count += countReplies(child);
        }
        return count;
    }
    private static void collectFieldIds(JsonElement value, TreeSet<String> fields) {
        if (value.isJsonObject()) {
            JsonObject object = value.getAsJsonObject();
            if (object.has("field") && object.get("field").isJsonPrimitive()) fields.add(object.get("field").getAsString());
            object.entrySet().forEach(e -> collectFieldIds(e.getValue(), fields));
        } else if (value.isJsonArray()) value.getAsJsonArray().forEach(e -> collectFieldIds(e, fields));
    }
    private static List<Path> jsonFiles(Path root) throws IOException {
        try (var files = Files.walk(root)) { return files.filter(p -> p.toString().endsWith(".json")).sorted().toList(); }
    }
    private static JsonObject read(Path path) throws IOException { return JsonParser.parseString(Files.readString(path)).getAsJsonObject(); }
    private static String relative(Path root, Path path) { return root.relativize(path).toString().replace('\\', '/'); }
}
