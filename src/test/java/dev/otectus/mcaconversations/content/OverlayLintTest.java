package dev.otectus.mcaconversations.content;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Lints the personality overlay lang files ({@code assets/mca_dialogue_<personality>/lang/}):
 * every MCA personality has an overlay, every overlay key's base exists in our main lang file,
 * and each overlay covers the standard high-traffic key set.
 */
class OverlayLintTest {

    private static final Path ASSETS = Path.of("src/main/resources/assets");
    private static final Path BASE_LANG = ASSETS.resolve("mca_dialogue/lang/en_us.json");

    /** MCA 7.6.26 personalities (lowercase). */
    private static final Set<String> PERSONALITIES = Set.of(
            "athletic", "confident", "friendly", "flirty", "witty", "shy",
            "gloomy", "sensitive", "greedy", "odd", "lazy", "grumpy", "peppy");

    /** Every overlay must cover at least these high-traffic lines. */
    private static final Set<String> STANDARD_KEYS = Set.of(
            "dialogue.chat",
            "dialogue.conversations",
            "dialogue.conversations.checkin.good", "dialogue.conversations.checkin.rough",
            "dialogue.conversations.day.good", "dialogue.conversations.day.rough",
            "dialogue.conversations.work.like", "dialogue.conversations.work.hate",
            "dialogue.conversations.life.first", "dialogue.conversations.dreams.first",
            "dialogue.conversations.fears.first", "dialogue.conversations.feelings.first",
            "dialogue.conversations.deflect.personal", "dialogue.conversations.deflect.intimate",
            "dialogue.conversations.gossip.none");

    private static Map<String, Map<String, String>> overlays;
    private static Map<String, String> baseLang;

    @BeforeAll
    static void load() throws IOException {
        Gson gson = new Gson();
        var type = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
        baseLang = gson.fromJson(Files.readString(BASE_LANG), type);
        overlays = new HashMap<>();
        try (Stream<Path> dirs = Files.list(ASSETS)) {
            for (Path dir : dirs.filter(d -> d.getFileName().toString().startsWith("mca_dialogue_")).toList()) {
                String personality = dir.getFileName().toString().substring("mca_dialogue_".length());
                Map<String, String> lang = gson.fromJson(
                        Files.readString(dir.resolve("lang/en_us.json")), type);
                overlays.put(personality, lang);
            }
        }
    }

    @Test
    void everyPersonalityHasAnOverlay() {
        List<String> problems = new ArrayList<>();
        for (String p : PERSONALITIES) {
            if (!overlays.containsKey(p)) {
                problems.add("missing overlay file for personality '" + p + "'");
            }
        }
        for (String p : overlays.keySet()) {
            if (!PERSONALITIES.contains(p)) {
                problems.add("overlay for unknown personality '" + p + "' (namespace would never be read)");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    // Note: dialogue.chat has a base entry in our main lang file (added for the Chat->Conversations
    // entry header), so overlayKeysOverrideRealBaseKeys covers it like any other key.
    @Test
    void overlayKeysOverrideRealBaseKeys() {
        List<String> problems = new ArrayList<>();
        overlays.forEach((personality, lang) -> {
            for (String key : lang.keySet()) {
                String base = key.contains("/") ? key.substring(0, key.indexOf('/')) : key;
                if (!baseLang.containsKey(base)) {
                    problems.add(personality + ": overlay key '" + key + "' has no base line to override");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void overlaysCoverTheStandardKeySet() {
        List<String> problems = new ArrayList<>();
        overlays.forEach((personality, lang) -> {
            for (String key : STANDARD_KEYS) {
                if (!lang.containsKey(key)) {
                    problems.add(personality + ": missing standard overlay key '" + key + "'");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }
}
