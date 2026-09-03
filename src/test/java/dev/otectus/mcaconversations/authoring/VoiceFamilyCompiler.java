package dev.otectus.mcaconversations.authoring;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dev.otectus.mcaconversations.support.TestPaths;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Expands six voices into twenty-one personality overlays.
 *
 * <h2>The problem this solves</h2>
 *
 * <p>{@code VoiceFamily}'s javadoc has always said "the build expands it into every member's lang
 * namespace". The build did no such thing — the expander was never written, so the overlays were
 * expanded by hand. The evidence is in the shipped files: within every voice family, 999 of the
 * 1,084 keys are byte-identical across its members. Six voices, copied outward into twenty-one
 * namespaces, twice over for two locales.
 *
 * <p>That copying is the reason personality is only audible on 5.9% of lines. Adding one line to the
 * conversational middle — an appraisal, a disagreement, a boundary — means pasting it into
 * twenty-one files in two languages and keeping all forty-two copies in step forever. The cost is
 * per-line and it never goes down, so the sensible thing to do is always to not add the line.
 *
 * <p>With this, a voice is authored once. A family file holds what its members say identically; a
 * specials file holds the lines where one member genuinely differs. Forty-two output files are
 * generated, and {@code VoiceFamilyCompilerTest} fails the build if the committed ones drift from
 * what the sources compile to — the same contract {@code ContentCompiler} has.
 *
 * <h2>Why the prefix matters</h2>
 *
 * <p>Minecraft translation keys are global across asset namespaces, so an overlay key must carry its
 * personality prefix ({@code anxious.dialogue.x}). An unprefixed key in an overlay pack collides with
 * the base pool, and whichever pack loads last becomes every villager's voice. The prefix is applied
 * here rather than authored, so it cannot be forgotten.
 *
 * <p>Run with {@code ./gradlew generateVoiceOverlays}; verify with {@code verifyVoiceOverlays}.
 */
public final class VoiceFamilyCompiler {

    /** Where a voice is authored. */
    public static final String SOURCE_DIR = "src/content/voices";

    /** Where the overlays it expands into live. */
    public static final String ASSETS_DIR = "src/main/resources/assets";

    static final List<String> LOCALES = List.of("en_us", "pt_br");

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private VoiceFamilyCompiler() {
    }

    /** One personality's overlay, per locale: {@code locale -> (prefixed key -> line)}. */
    public record Overlay(String personality, Map<String, Map<String, String>> byLocale) {
    }

    public static void main(String[] args) {
        Path source = args.length > 0 ? Path.of(args[0]) : TestPaths.of(SOURCE_DIR);
        Path assets = args.length > 1 ? Path.of(args[1]) : TestPaths.of(ASSETS_DIR);
        List<Overlay> overlays = compile(source);
        for (Overlay overlay : overlays) {
            for (String locale : LOCALES) {
                write(assets.resolve("mca_dialogue_" + overlay.personality())
                                .resolve("lang").resolve(locale + ".json"),
                        overlay.byLocale().get(locale));
            }
        }
        System.out.println("[voices] " + overlays.size() + " overlays from "
                + families(source).size() + " voices, "
                + overlays.stream().mapToInt(o -> o.byLocale().get("en_us").size()).sum()
                + " keys per locale");
    }

    /** Every overlay the sources describe, personality order following the family files. */
    public static List<Overlay> compile(Path source) {
        List<Overlay> out = new ArrayList<>();
        for (Path familyFile : families(source)) {
            JsonObject family = read(familyFile);
            JsonObject shared = family.getAsJsonObject("lines");
            for (JsonElement member : family.getAsJsonArray("members")) {
                String personality = member.getAsString();
                out.add(expand(personality, shared, specialsFor(source, personality)));
            }
        }
        return out;
    }

    /**
     * One personality's overlay: the family's shared lines, then its own where it differs.
     *
     * <p>Specials are applied second and win, which is what makes a special a special. A personality
     * may also carry a key the family has no line for at all — flirty and gloomy each do — and those
     * simply appear alongside the shared ones.
     */
    static Overlay expand(String personality, JsonObject shared, JsonObject specials) {
        Map<String, Map<String, String>> byLocale = new LinkedHashMap<>();
        for (String locale : LOCALES) {
            // Sorted, because that is how the overlays have always been written and a stable order is
            // what makes a regenerated file diffable against the one it replaces.
            byLocale.put(locale, new TreeMap<>());
        }
        putAll(personality, shared, byLocale);
        putAll(personality, specials, byLocale);
        return new Overlay(personality, byLocale);
    }

    private static void putAll(String personality, JsonObject lines,
                               Map<String, Map<String, String>> byLocale) {
        if (lines == null) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : lines.entrySet()) {
            JsonObject pair = entry.getValue().getAsJsonObject();
            for (String locale : LOCALES) {
                if (!pair.has(locale)) {
                    throw new IllegalStateException(personality + " line '" + entry.getKey()
                            + "' has no " + locale + "; both locales are authored together");
                }
                byLocale.get(locale).put(personality + "." + entry.getKey(),
                        pair.get(locale).getAsString());
            }
        }
    }

    /** The family files, in name order so the output is the same on every machine. */
    static List<Path> families(Path source) {
        try (var paths = Files.list(source)) {
            return paths.filter(p -> p.getFileName().toString().endsWith(".json")).sorted().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static JsonObject specialsFor(Path source, String personality) {
        Path path = source.resolve("specials").resolve(personality + ".json");
        return Files.exists(path) ? read(path).getAsJsonObject("lines") : null;
    }

    static JsonObject read(Path path) {
        try {
            return JsonParser.parseString(Files.readString(path, StandardCharsets.UTF_8))
                    .getAsJsonObject();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** The exact text a lang file holds for these lines: two-space pretty JSON, trailing newline. */
    static String render(Map<String, String> lines) {
        JsonObject json = new JsonObject();
        lines.forEach(json::addProperty);
        return GSON.toJson(json) + "\n";
    }

    private static void write(Path path, Map<String, String> lines) {
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, render(lines), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
