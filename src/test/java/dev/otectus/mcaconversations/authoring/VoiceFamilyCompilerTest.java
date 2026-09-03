package dev.otectus.mcaconversations.authoring;

import org.junit.jupiter.api.Test;

import dev.otectus.mcaconversations.support.TestPaths;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The committed personality overlays must be exactly what the six voices compile to.
 *
 * <p>Same contract as {@code ContentCompilerTest}, and for the same reason: the moment a generated
 * file can be edited by hand without the build noticing, the source stops being the source. For
 * overlays that matters more than usual, because the failure mode is silent — a villager keeps
 * speaking in a voice nobody can find the words for any more.
 *
 * <p>This test is also the proof that extracting the voices from the shipped overlays lost nothing.
 * The 21 namespaces were hand-copied from 6 voices before 1.5.0; if the extraction had dropped or
 * merged a single line, the byte comparison below would say so.
 */
class VoiceFamilyCompilerTest {

    private static final Path SOURCE = TestPaths.of(VoiceFamilyCompiler.SOURCE_DIR);
    private static final Path ASSETS = TestPaths.of(VoiceFamilyCompiler.ASSETS_DIR);

    private static String read(Path path) {
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Path overlayPath(String personality, String locale) {
        return ASSETS.resolve("mca_dialogue_" + personality).resolve("lang").resolve(locale + ".json");
    }

    @Test
    void committedOverlaysMatchWhatTheVoicesCompileTo() {
        List<String> stale = new ArrayList<>();
        for (VoiceFamilyCompiler.Overlay overlay : VoiceFamilyCompiler.compile(SOURCE)) {
            for (String locale : VoiceFamilyCompiler.LOCALES) {
                Path path = overlayPath(overlay.personality(), locale);
                assertTrue(Files.exists(path), "missing overlay: " + path);
                String expected = VoiceFamilyCompiler.render(overlay.byLocale().get(locale));
                if (!expected.equals(read(path))) {
                    stale.add(overlay.personality() + "/" + locale);
                }
            }
        }
        assertTrue(stale.isEmpty(), stale.size() + " overlay file(s) differ from what "
                + "src/content/voices compiles to: " + stale
                + " — run ./gradlew generateVoiceOverlays and commit the result.");
    }

    @Test
    void everyPersonalityWithAnOverlayIsAuthoredBySomeVoice() {
        // The other direction: an overlay namespace no voice claims would be a file the generator
        // never rewrites, quietly drifting away from every other personality in its family.
        Set<String> compiled = new TreeSet<>();
        for (VoiceFamilyCompiler.Overlay overlay : VoiceFamilyCompiler.compile(SOURCE)) {
            compiled.add(overlay.personality());
        }
        Set<String> shipped = new TreeSet<>();
        try (var paths = Files.list(ASSETS)) {
            paths.map(p -> p.getFileName().toString())
                    .filter(name -> name.startsWith("mca_dialogue_"))
                    .map(name -> name.substring("mca_dialogue_".length()))
                    .forEach(shipped::add);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        assertEquals(shipped, compiled,
                "every mca_dialogue_* namespace must be owned by exactly one voice");
    }

    @Test
    void noPersonalityIsClaimedByTwoVoices() {
        List<String> seen = new ArrayList<>();
        for (VoiceFamilyCompiler.Overlay overlay : VoiceFamilyCompiler.compile(SOURCE)) {
            seen.add(overlay.personality());
        }
        assertEquals(new TreeSet<>(seen).size(), seen.size(),
                "a personality appears in more than one family file: " + seen);
    }

    @Test
    void everyLineIsAuthoredInBothLocalesAndPrefixed() {
        for (VoiceFamilyCompiler.Overlay overlay : VoiceFamilyCompiler.compile(SOURCE)) {
            Map<String, String> english = overlay.byLocale().get("en_us");
            Map<String, String> portuguese = overlay.byLocale().get("pt_br");
            assertEquals(english.keySet(), portuguese.keySet(),
                    overlay.personality() + " has a line in one locale and not the other");
            String prefix = overlay.personality() + ".";
            for (String key : english.keySet()) {
                // An unprefixed overlay key collides with the base pool across asset namespaces, and
                // whichever pack loads last becomes every villager's voice.
                assertTrue(key.startsWith(prefix),
                        overlay.personality() + " emitted an unprefixed key: " + key);
            }
        }
    }

    @Test
    void theSixVoicesReallyDoCoverTwentyOnePersonalities() {
        // A guard on the guards: if the source directory emptied, every test above would pass by
        // vacuum while the shipped overlays sat unowned.
        List<VoiceFamilyCompiler.Overlay> overlays = VoiceFamilyCompiler.compile(SOURCE);
        assertEquals(6, VoiceFamilyCompiler.families(SOURCE).size(), "six voices");
        assertEquals(21, overlays.size(), "twenty-one overlay namespaces");
        for (VoiceFamilyCompiler.Overlay overlay : overlays) {
            assertTrue(overlay.byLocale().get("en_us").size() > 900,
                    overlay.personality() + " compiled to suspiciously few lines");
        }
    }
}
