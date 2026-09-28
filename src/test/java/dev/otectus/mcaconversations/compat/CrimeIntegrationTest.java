package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.compat.crime.CrimeConditionQuery;
import dev.otectus.mcaconversations.compat.crime.CrimeVoiceKeys;
import dev.otectus.mcaconversations.support.TestPaths;
import net.minecraft.resources.ResourceLocation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The MCA: Crime seam (1.8.0): its classloading discipline, its condition parsing and its voice mapping. */
class CrimeIntegrationTest {

    private static final Path SOURCE_ROOT = TestPaths.of("src/main/java/dev/otectus/mcaconversations");

    @Test
    void onlyTheGuardedPackageNamesCrimeTypes() throws IOException {
        List<String> offenders = new ArrayList<>();
        try (Stream<Path> files = Files.walk(SOURCE_ROOT)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".java")).toList()) {
                String relative = SOURCE_ROOT.relativize(file).toString().replace('\\', '/');
                if (relative.startsWith("compat/crime/")) {
                    continue;
                }
                String source = Files.readString(file, StandardCharsets.UTF_8);
                if (source.contains("import dev.otectus.mcacrime.")) {
                    offenders.add(relative);
                }
            }
        }
        assertTrue(offenders.isEmpty(), () -> "MCA: Crime imports outside compat/crime:\n  "
                + String.join("\n  ", offenders));
    }

    private static JsonObject json(String text) {
        return JsonParser.parseString(text).getAsJsonObject();
    }

    @Test
    void conditionArgsParseAndReject() {
        assertEquals(Optional.of(true), CrimeConditionQuery.flag(json("{}"), "wanted").flag());
        assertEquals(Optional.of(false), CrimeConditionQuery.flag(json("{\"wanted\": false}"), "wanted").flag());
        assertEquals(Optional.of("outlaw"), CrimeConditionQuery.band(json("{\"band\": \"Outlaw\"}")).band());
        assertEquals(25L, CrimeConditionQuery.heat(json("{\"min\": 25}")).min());
        assertThrows(IllegalArgumentException.class, () -> CrimeConditionQuery.band(json("{\"band\": \"red\"}")),
                "bands are the legal words, not Crime's colours");
        assertThrows(IllegalArgumentException.class, () -> CrimeConditionQuery.band(json("{}")));
        assertThrows(IllegalArgumentException.class, () -> CrimeConditionQuery.heat(json("{\"min\": -1}")));
    }

    @Test
    void onlyVoicedCrimeEventsMapToAPool() {
        assertEquals("conversations.crime.guard_challenge",
                CrimeVoiceKeys.phraseFor(ResourceLocation.fromNamespaceAndPath("mcacrime", "guard_challenge")));
        assertEquals("conversations.crime.apology_accepted",
                CrimeVoiceKeys.phraseFor(ResourceLocation.fromNamespaceAndPath("mcacrime", "apology_accepted")));
        assertNull(CrimeVoiceKeys.phraseFor(ResourceLocation.fromNamespaceAndPath("mcacrime", "mug_opening")),
                "a mugging line stays Crime's own");
        assertNull(CrimeVoiceKeys.phraseFor(ResourceLocation.fromNamespaceAndPath("othermod", "guard_challenge")));
    }
}
