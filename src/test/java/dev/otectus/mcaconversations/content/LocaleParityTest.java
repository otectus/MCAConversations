package dev.otectus.mcaconversations.content;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.otectus.mcaconversations.locale.OverlayLocales;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Holds every shipped locale to the same shape as {@code en_us}.
 *
 * <p>A missing key renders in game as the raw translation key; a placeholder that changed index or
 * count renders as a broken substitution or throws while formatting; a gap in a {@code /N} variant
 * run silently shrinks the anti-repetition pool. None of those are visible until a player hits the
 * exact line, so they are enforced here instead.
 */
class LocaleParityTest {

    private static final Path ASSETS = Path.of("src/main/resources/assets");
    private static final Pattern PLACEHOLDER = Pattern.compile("%(\\d+)\\$s");
    private static final Pattern BARE_PLACEHOLDER = Pattern.compile("%s");
    private static final Gson GSON = new Gson();

    private static Map<String, String> read(Path p) throws IOException {
        return GSON.fromJson(Files.readString(p),
                TypeToken.getParameterized(Map.class, String.class, String.class).getType());
    }

    /** Every namespace directory under assets/ that ships a lang folder. */
    private static List<Path> langDirs() throws IOException {
        try (Stream<Path> dirs = Files.list(ASSETS)) {
            return dirs.map(d -> d.resolve("lang")).filter(Files::isDirectory).sorted().toList();
        }
    }

    private static String placeholderSignature(String value) {
        TreeSet<String> found = new TreeSet<>();
        Matcher m = PLACEHOLDER.matcher(value);
        while (m.find()) {
            found.add(m.group());
        }
        return found.toString();
    }

    @Test
    void everyAuthoredLocaleHasTheSameKeysAsEnglish() throws IOException {
        List<String> problems = new ArrayList<>();
        for (Path lang : langDirs()) {
            Map<String, String> en = read(lang.resolve("en_us.json"));
            for (String locale : OverlayLocales.AUTHORED_BY_THIS_MOD) {
                if (locale.equals("en_us")) {
                    continue;
                }
                Path file = lang.resolve(locale + ".json");
                if (!Files.exists(file)) {
                    problems.add(lang.getParent().getFileName() + ": no " + locale + ".json "
                            + "(this locale is declared complete in OverlayLocales)");
                    continue;
                }
                Map<String, String> other = read(file);
                for (String k : en.keySet()) {
                    if (!other.containsKey(k)) {
                        problems.add(lang.getParent().getFileName() + "/" + locale + ": missing '" + k + "'");
                    }
                }
                for (String k : other.keySet()) {
                    if (!en.containsKey(k)) {
                        problems.add(lang.getParent().getFileName() + "/" + locale
                                + ": '" + k + "' has no en_us counterpart");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void placeholderSignaturesMatchAcrossLocales() throws IOException {
        List<String> problems = new ArrayList<>();
        for (Path lang : langDirs()) {
            Map<String, String> en = read(lang.resolve("en_us.json"));
            for (String locale : OverlayLocales.AUTHORED_BY_THIS_MOD) {
                Path file = lang.resolve(locale + ".json");
                if (locale.equals("en_us") || !Files.exists(file)) {
                    continue;
                }
                Map<String, String> other = read(file);
                for (Map.Entry<String, String> e : en.entrySet()) {
                    String translated = other.get(e.getKey());
                    if (translated == null) {
                        continue; // reported by the key-set test
                    }
                    String a = placeholderSignature(e.getValue());
                    String b = placeholderSignature(translated);
                    if (!a.equals(b)) {
                        problems.add(lang.getParent().getFileName() + "/" + locale + " '" + e.getKey()
                                + "': placeholders " + a + " vs " + b);
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * MCA formats these with positional arguments only; a bare {@code %s} mixed in with
     * {@code %1$s} throws {@code MissingFormatArgumentException} at render time.
     */
    @Test
    void noBarePlaceholders() throws IOException {
        List<String> problems = new ArrayList<>();
        for (Path lang : langDirs()) {
            try (Stream<Path> files = Files.list(lang)) {
                for (Path file : files.filter(f -> f.toString().endsWith(".json")).toList()) {
                    read(file).forEach((k, v) -> {
                        if (BARE_PLACEHOLDER.matcher(v).find()) {
                            problems.add(file.getParent().getParent().getFileName() + "/"
                                    + file.getFileName() + " '" + k + "': bare %s (use %1$s)");
                        }
                    });
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * {@code /N} variants are pooled by {@code PooledTranslationStorage}, which collects
     * {@code key/1}, {@code key/2}… A gap means the author intended a variant that will never be
     * picked, and the run must be identical in every locale or the pools differ in size.
     */
    @Test
    void variantSequencesAreContiguousAndMatchAcrossLocales() throws IOException {
        List<String> problems = new ArrayList<>();
        for (Path lang : langDirs()) {
            try (Stream<Path> files = Files.list(lang)) {
                for (Path file : files.filter(f -> f.toString().endsWith(".json")).sorted().toList()) {
                    Map<String, TreeSet<Integer>> runs = new LinkedHashMap<>();
                    read(file).keySet().forEach(k -> {
                        int slash = k.lastIndexOf('/');
                        if (slash < 0) {
                            runs.computeIfAbsent(k, x -> new TreeSet<>());
                            return;
                        }
                        String base = k.substring(0, slash);
                        String tail = k.substring(slash + 1);
                        if (tail.chars().allMatch(Character::isDigit) && !tail.isEmpty()) {
                            runs.computeIfAbsent(base, x -> new TreeSet<>()).add(Integer.parseInt(tail));
                        }
                    });
                    // A run need not start at /1: several pools here deliberately EXTEND MCA's own
                    // (e.g. MCA ships dialogue.main/1../7 and we append /8../12 into the shared
                    // namespace). What must hold is that our own indices have no internal gap —
                    // a hole means an authored variant the pool builder will never pick.
                    runs.forEach((base, indices) -> {
                        int expected = indices.isEmpty() ? 1 : indices.first();
                        for (int i : indices) {
                            if (i != expected) {
                                problems.add(file.getParent().getParent().getFileName() + "/"
                                        + file.getFileName() + " '" + base + "': variant run has a gap at /"
                                        + expected + " (jumps to /" + i + ")");
                                break;
                            }
                            expected++;
                        }
                    });
                }
            }

            // Same runs in every authored locale.
            Map<String, String> en = read(lang.resolve("en_us.json"));
            for (String locale : OverlayLocales.AUTHORED_BY_THIS_MOD) {
                Path file = lang.resolve(locale + ".json");
                if (locale.equals("en_us") || !Files.exists(file)) {
                    continue;
                }
                Map<String, String> other = read(file);
                long enVariants = en.keySet().stream().filter(k -> k.matches(".*/\\d+$")).count();
                long otherVariants = other.keySet().stream().filter(k -> k.matches(".*/\\d+$")).count();
                if (enVariants != otherVariants) {
                    problems.add(lang.getParent().getFileName() + "/" + locale + ": "
                            + otherVariants + " variants vs en_us " + enVariants);
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * A locale is only listed in {@link OverlayLocales} once it actually has complete personality
     * overlays; the client hook widens MCA's gate for exactly this list, so a premature entry would
     * mean villagers speaking English mid-conversation.
     */
    @Test
    void declaredOverlayLocalesActuallyShipEveryOverlay() throws IOException {
        List<String> problems = new ArrayList<>();
        try (Stream<Path> dirs = Files.list(ASSETS)) {
            List<Path> overlayDirs = dirs
                    .filter(d -> d.getFileName().toString().startsWith("mca_dialogue_"))
                    .sorted().toList();
            for (String locale : OverlayLocales.WITH_PERSONALITY_OVERLAYS) {
                if (!OverlayLocales.AUTHORED_BY_THIS_MOD.contains(locale)) {
                    continue; // MCA's own locales are not ours to ship
                }
                for (Path dir : overlayDirs) {
                    if (!Files.exists(dir.resolve("lang/" + locale + ".json"))) {
                        problems.add(locale + " is declared complete but "
                                + dir.getFileName() + " has no " + locale + ".json");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }
}
