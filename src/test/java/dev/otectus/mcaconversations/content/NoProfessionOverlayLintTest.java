package dev.otectus.mcaconversations.content;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * No lang key this mod ships gives its own lines a profession variant (feedback remediation WP-B1).
 *
 * <p>The speech association repair computes the key the screen rendered by calling MCA's
 * {@code DialogueType.applyFallback} a second time. That is deterministic for every marker but one:
 * the profession branch flips a coin per call, and it only matters when a
 * {@code <profession>.dialogue.…} key exists. So for this mod's own namespaces — every key that
 * contains {@code dialogue.conversations.} or {@code dialogue.chatmode.} — the only prefix allowed is
 * the personality of the overlay namespace the key lives in, and the base namespace allows none.
 */
class NoProfessionOverlayLintTest {

    private static final Path ASSETS = Paths.get("src", "main", "resources", "assets");
    private static final String[] OWNED = {"dialogue.conversations.", "dialogue.chatmode."};

    @Test
    void ownedKeysCarryNoPrefixButTheirOwnPersonality() throws IOException {
        List<String> problems = new ArrayList<>();
        int checked = 0;
        try (Stream<Path> namespaces = Files.list(ASSETS)) {
            for (Path namespace : namespaces.filter(p -> p.getFileName().toString().startsWith("mca_dialogue")).toList()) {
                String name = namespace.getFileName().toString();
                String personality = name.equals("mca_dialogue") ? "" : name.substring("mca_dialogue_".length()) + ".";
                Path lang = namespace.resolve("lang");
                if (!Files.isDirectory(lang)) {
                    continue;
                }
                try (Stream<Path> files = Files.list(lang)) {
                    for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                        JsonObject json;
                        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
                            json = JsonParser.parseReader(reader).getAsJsonObject();
                        }
                        for (String key : json.keySet()) {
                            for (String marker : OWNED) {
                                int at = key.indexOf(marker);
                                if (at < 0) {
                                    continue;
                                }
                                checked++;
                                String prefix = key.substring(0, at);
                                if (!prefix.equals(personality)) {
                                    problems.add(name + "/" + file.getFileName() + ": " + key);
                                }
                            }
                        }
                    }
                }
            }
        }
        assertTrue(checked > 0, "no owned keys found under " + ASSETS + " — wrong working directory?");
        assertFalse(problems.size() > 0, "owned lang keys with a prefix other than their namespace's personality "
                + "(a profession variant would make the speech repair guess): " + problems.subList(0,
                Math.min(20, problems.size())));
    }
}
