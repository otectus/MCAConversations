package dev.otectus.mcaconversations.content;

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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Nothing a dedicated server loads may reach for a client class (spec §21.6, Phase 10).
 *
 * <p>A client class on a server path is the failure that does not show up in single-player and takes
 * the whole mod down the first time somebody runs it on a server — which is exactly the sort of thing
 * that should be a build failure rather than a bug report. The check is a source scan rather than a
 * runtime one so it holds for code that is only reached under conditions a test would not reproduce.
 *
 * <p>Two rules. Java outside the declared client packages may not import {@code net.minecraft.client}
 * at all, and a mixin listed in the common {@code mixins} block may not live in the client package or
 * name a client type. The second is the one that matters most: a common mixin is applied on every
 * side, so a client reference inside it is loaded before any of our own guards can run.
 */
class DedicatedServerSafetyTest {

    private static final String SEP = System.lineSeparator();

    private static final Path SOURCES = Path.of("src/main/java/dev/otectus/mcaconversations");
    private static final Path MIXIN_CONFIG = Path.of("src/main/resources/mcaconversations.mixins.json");

    /**
     * Packages allowed to touch client classes.
     *
     * <p>Both are declared client-side: {@code mixin.client} is listed in the mixin config's
     * {@code "client"} block, and {@code client} holds the physical-client helpers Forge only loads
     * on that side.
     */
    private static final Set<String> CLIENT_PACKAGES = Set.of("mixin/client", "client");

    @Test
    @DisplayName("no server-side class imports a client class")
    void serverCodeIsClientFree() throws IOException {
        List<String> problems = new ArrayList<>();
        for (Path file : javaFiles()) {
            String relative = SOURCES.relativize(file).toString().replace('\\', '/');
            if (isClientOwned(relative)) {
                continue;
            }
            String source = Files.readString(file);
            for (String line : source.split("\r?\n")) {
                String trimmed = line.trim();
                if (trimmed.startsWith("import net.minecraft.client")
                        || trimmed.startsWith("import net.minecraftforge.client")
                        || trimmed.startsWith("import com.mojang.blaze3d")) {
                    problems.add(relative + ": " + trimmed);
                }
            }
        }
        assertTrue(problems.isEmpty(),
                "A client class on a server path is fine in single-player and fatal on a dedicated"
                        + " server:" + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("every common mixin is genuinely common")
    void commonMixinsAreServerSafe() throws IOException {
        JsonObject config = JsonParser.parseString(Files.readString(MIXIN_CONFIG)).getAsJsonObject();
        String base = config.get("package").getAsString().replace('.', '/');

        List<String> problems = new ArrayList<>();
        for (JsonElement element : config.getAsJsonArray("mixins")) {
            String name = element.getAsString();
            if (name.startsWith("client.")) {
                problems.add(name + " is listed as common and lives in the client package");
                continue;
            }
            Path file = Path.of("src/main/java", base, name.replace('.', '/') + ".java");
            if (!Files.exists(file)) {
                problems.add(name + " is listed in the mixin config and has no source file");
                continue;
            }
            String source = Files.readString(file);
            if (source.contains("net.minecraft.client") || source.contains("com.mojang.blaze3d")) {
                problems.add(name + " is a common mixin and names a client type");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every client mixin is declared in the client block, and nowhere else")
    void clientMixinsAreDeclaredOnce() throws IOException {
        JsonObject config = JsonParser.parseString(Files.readString(MIXIN_CONFIG)).getAsJsonObject();
        Set<String> common = new TreeSet<>();
        config.getAsJsonArray("mixins").forEach(element -> common.add(element.getAsString()));
        Set<String> client = new TreeSet<>();
        if (config.has("client")) {
            config.getAsJsonArray("client").forEach(element -> client.add(element.getAsString()));
        }

        List<String> problems = new ArrayList<>();
        for (String name : client) {
            if (common.contains(name)) {
                problems.add(name + " is declared in both blocks, so a server would load it");
            }
        }

        // Every mixin source has to be declared somewhere, or it silently does nothing.
        try (Stream<Path> files = Files.walk(SOURCES.resolve("mixin"))) {
            files.filter(path -> path.toString().endsWith("Mixin.java")).forEach(path -> {
                String relative = SOURCES.resolve("mixin").relativize(path).toString()
                        .replace('\\', '.').replace('/', '.');
                String name = relative.substring(0, relative.length() - ".java".length());
                if (!common.contains(name) && !client.contains(name)) {
                    problems.add(name + " exists and is declared in neither block");
                }
            });
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    private static boolean isClientOwned(String relativePath) {
        for (String allowed : CLIENT_PACKAGES) {
            if (relativePath.startsWith(allowed + "/")) {
                return true;
            }
        }
        return false;
    }

    private static List<Path> javaFiles() throws IOException {
        try (Stream<Path> files = Files.walk(SOURCES)) {
            return files.filter(path -> path.toString().endsWith(".java")).sorted().toList();
        }
    }
}
