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

import static org.junit.jupiter.api.Assertions.assertTrue;

/** The checked coverage manifest of social surfaces (Stability spec §9.4). */
class SocialSurfaceManifestTest {

    private static final Path MANIFEST = Path.of("src/test/resources/social_surface_manifest.json");

    @Test
    @DisplayName("every surface names a real producer, real pools in both locales, and a real test")
    void manifestIsTrue() throws IOException {
        JsonObject root = JsonParser.parseString(Files.readString(MANIFEST)).getAsJsonObject();
        JsonObject en = JsonParser.parseString(Files.readString(
                Path.of("src/main/resources/assets/mca_dialogue/lang/en_us.json"))).getAsJsonObject();
        JsonObject pt = JsonParser.parseString(Files.readString(
                Path.of("src/main/resources/assets/mca_dialogue/lang/pt_br.json"))).getAsJsonObject();
        List<String> problems = new ArrayList<>();
        for (JsonElement element : root.getAsJsonArray("surfaces")) {
            JsonObject surface = element.getAsJsonObject();
            String name = surface.get("surface").getAsString();
            String producer = surface.get("producer").getAsString();
            if (!producer.equals("external") && !exists(producer)) {
                problems.add(name + ": producer " + producer + " does not exist");
            }
            for (JsonElement pool : surface.getAsJsonArray("pools")) {
                String key = "dialogue." + pool.getAsString();
                for (JsonObject lang : List.of(en, pt)) {
                    // A pool this mod only extends (MCA's greet pools) starts past /1, so any variant counts.
                    if (!lang.has(key) && lang.keySet().stream().noneMatch(k -> k.startsWith(key + "/"))) {
                        problems.add(name + ": pool " + pool.getAsString() + " is missing in a locale");
                    }
                }
            }
            String fixture = surface.get("fixture").getAsString();
            if (!fixture.isEmpty() && !exists(fixture)) {
                problems.add(name + ": fixture " + fixture + " does not exist");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    private static boolean exists(String ref) {
        if (ref.contains("/")) {
            return Files.exists(Path.of(ref)) || Files.exists(Path.of("src/main/resources").resolve(ref));
        }
        try {
            Class.forName(ref, false, SocialSurfaceManifestTest.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
