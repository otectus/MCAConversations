package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.support.TestPaths;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** "Life here" and the Townstead-aware scenes (Townstead spec §16, §17.3). */
class TownsteadContentContractTest {

    private static final Path TOPICS = TestPaths.of("src/content/topics");
    private static final Path DATA = TestPaths.of("src/main/resources/data/mcaconversations");
    private static final Path ASSETS = TestPaths.of("src/main/resources/assets");
    private static final List<String> LIFE_HERE = List.of("wellbeing", "daily_rhythm", "mastery", "age_and_life",
            "roots", "home_and_place", "community", "calendar");
    private static final Set<String> TOWNSTEAD_TAGS = Set.of("excited", "angry", "happy", "nervous", "sad", "yell",
            "sleepy", "scared", "flirty", "whisper", "mysterious", "frozen", "burning", "drunk");

    private static JsonObject read(Path path) throws IOException {
        return JsonParser.parseString(Files.readString(path)).getAsJsonObject();
    }

    @Test
    @DisplayName("every Life here topic is a Townstead-only catalog row on its own category page, reachable from the hub")
    void lifeHereIsWiredEndToEnd() throws IOException {
        JsonObject topics = read(DATA.resolve("conversation_catalog/topics.json")).getAsJsonObject("topics");
        JsonObject page = read(DATA.resolve("dialogues/conversations.cat.townstead.json"));
        List<String> answers = new ArrayList<>();
        page.getAsJsonArray("answers").forEach(a -> answers.add(a.getAsJsonObject().get("name").getAsString()));
        for (String topic : LIFE_HERE) {
            JsonObject row = topics.getAsJsonObject(topic);
            assertTrue(row != null && row.get("townstead").getAsBoolean(), topic + " must be flagged townstead");
            assertEquals("conversations.cat.townstead", row.getAsJsonObject("entry").get("question").getAsString());
            assertTrue(answers.contains(topic), topic + " is not on the Life here page");
            assertTrue(read(TOPICS.resolve(topic + ".json")).get("townstead").getAsBoolean(),
                    topic + ": the authoring source must carry the flag the catalog row does");
        }
        assertTrue(answers.contains("back"));
        boolean hub = false;
        for (JsonElement answer : read(DATA.resolve("dialogues/conversations.json")).getAsJsonArray("answers")) {
            hub |= "townstead".equals(answer.getAsJsonObject().get("name").getAsString());
        }
        assertTrue(hub, "the hub offers the Life here category (hidden at runtime without Townstead)");
    }

    @Test
    @DisplayName("any scene that reads Townstead state declares the integration, so it never plans without the mod")
    void townsteadScenesDeclareTheIntegration() throws IOException {
        List<String> problems = new ArrayList<>();
        try (Stream<Path> files = Files.list(TOPICS)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                JsonObject pack = read(file);
                if (!pack.has("scenes")) {
                    continue;
                }
                for (JsonElement element : pack.getAsJsonArray("scenes")) {
                    JsonObject scene = element.getAsJsonObject();
                    if (!scene.toString().contains("\"townstead.")) {
                        continue;
                    }
                    boolean declared = scene.has("integrations")
                            && scene.getAsJsonArray("integrations").toString().contains("\"townstead\"");
                    if (!declared) {
                        problems.add(file.getFileName() + "/" + scene.get("name").getAsString());
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), "scenes gated on townstead.* without integrations [townstead]: " + problems);
    }

    @Test
    @DisplayName("the Townstead emotion sidecar only ever tags a line; the visible text is the lang value, exactly")
    void emotionSidecarStripsToTheShippedText() throws IOException {
        Pattern strip = Pattern.compile("<(\\w+)>(.*?)</(\\w+)>", Pattern.DOTALL);
        List<String> problems = new ArrayList<>();
        for (String locale : List.of("en_us", "pt_br")) {
            JsonObject sidecar = read(ASSETS.resolve("mcaconversations/townstead_emotions/" + locale + ".json"));
            JsonObject lang = read(ASSETS.resolve("mca_dialogue/lang/" + locale + ".json"));
            Set<String> plains = new TreeSet<>();
            for (String key : sidecar.keySet()) {
                if (key.startsWith("_")) {
                    continue;
                }
                String tagged = sidecar.get(key).getAsString();
                String plain = tagged;
                Matcher m = strip.matcher(tagged);
                while (m.find()) {
                    if (!m.group(1).equals(m.group(3)) || !TOWNSTEAD_TAGS.contains(m.group(1))) {
                        problems.add(locale + " " + key + ": tag <" + m.group(1) + "> is not a paired Townstead effect");
                    }
                }
                while (strip.matcher(plain).find()) {
                    plain = strip.matcher(plain).replaceAll("$2");
                }
                if (!lang.has(key)) {
                    problems.add(locale + " " + key + ": no such line");
                } else if (!plain.equals(lang.get(key).getAsString())) {
                    problems.add(locale + " " + key + ": strips to text that is not the shipped line");
                }
                if (tagged.contains("%")) {
                    problems.add(locale + " " + key + ": a line with a placeholder can never match resolved text");
                }
                if (!plains.add(plain)) {
                    problems.add(locale + " " + key + ": two tagged lines strip to the same text");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }
}
