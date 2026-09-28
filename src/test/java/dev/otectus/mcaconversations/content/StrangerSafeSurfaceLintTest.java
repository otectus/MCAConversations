package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.support.TestPaths;
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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Surfaces a villager may use with somebody they have never met must not use that person's name
 * (Stability spec §9.2): MCA hands every line the player's name, but that does not mean the villager
 * knows it. Checked in the base lines and in every personality overlay, both locales.
 *
 * <p>The guarded and hostile greetings and goodbyes are here too. A pair can be at odds without ever having been
 * introduced — the player struck a villager they never spoke to — so a grudge is no evidence of a name.
 * So is the pre-1.8.0 greeting, which no band chooses any more and is kept only as a safe answer for
 * whatever still asks for it. The acquaintance, friend, confidant, spouse and family greetings and the
 * known-person farewell are deliberately absent: the policy only chooses them for somebody the villager
 * has actually met, or is related to.
 */
class StrangerSafeSurfaceLintTest {

    private static final Path ASSETS = TestPaths.of("src/main/resources/assets");
    /** Pools reachable by a never-met pair; each also covers its {@code .toddler} variant. */
    private static final Set<String> STRANGER_REACHABLE = Set.of(
            "chatmode.hail.stranger", "chatmode.hail.recognized", "chatmode.hail.respected_stranger",
            "chatmode.hail.guarded", "chatmode.hail_cold", "chatmode.hail", "chatmode.farewell.stranger",
            "chatmode.farewell.guarded", "chatmode.farewell.hostile", "chatmode.attentive", "chatmode.busy", "chatmode.clarify",
            "chatmode.confused", "chatmode.dropped", "chatmode.hint", "chatmode.insult", "chatmode.muted",
            "chatmode.shrug", "conversations.checkin.stranger", "greet.success", "greet.fail");

    private static boolean reachable(String pool) {
        String bare = pool.endsWith(".toddler") ? pool.substring(0, pool.length() - ".toddler".length()) : pool;
        // The hub and every category page's own prompt and labels are shown to anyone who opens them.
        boolean categoryPage = bare.matches("conversations(\\.cat\\.[a-z_]+(\\.[a-z_]+)?)?");
        return STRANGER_REACHABLE.contains(bare) || categoryPage;
    }

    @Test
    @DisplayName("nothing a stranger can hear addresses them by a name the villager was never told")
    void strangerReachableLinesNeverUseTheName() throws IOException {
        List<String> problems = new ArrayList<>();
        try (Stream<Path> namespaces = Files.list(ASSETS)) {
            for (Path namespace : namespaces.filter(p -> p.getFileName().toString().startsWith("mca_dialogue")).toList()) {
                String ns = namespace.getFileName().toString();
                String prefix = ns.equals("mca_dialogue") ? "" : ns.substring("mca_dialogue_".length()) + ".";
                for (String locale : List.of("en_us", "pt_br")) {
                    Path file = namespace.resolve("lang/" + locale + ".json");
                    if (!Files.exists(file)) {
                        continue;
                    }
                    JsonObject lang = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                    for (String key : lang.keySet()) {
                        String bare = key.startsWith(prefix) ? key.substring(prefix.length()) : key;
                        if (!bare.startsWith("dialogue.")) {
                            continue;
                        }
                        String pool = bare.substring("dialogue.".length()).split("/")[0];
                        if (reachable(pool) && lang.get(key).getAsString().contains("%1$s")) {
                            problems.add(ns + "/" + locale + " " + key + ": " + lang.get(key).getAsString());
                        }
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), "stranger-reachable lines using the player's name:\n" + String.join("\n", problems));
    }
}
