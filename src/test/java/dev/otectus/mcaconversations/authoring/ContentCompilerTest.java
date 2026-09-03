package dev.otectus.mcaconversations.authoring;

import org.junit.jupiter.api.Test;

import dev.otectus.mcaconversations.support.TestPaths;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The committed generated content is what {@code src/content} compiles to, and still is (spec §19).
 *
 * <p>{@link ContentCompiler} writes straight into {@code src/main/resources}, and its outputs are
 * committed rather than built on demand, so that a player's jar and a datapack author's reference are
 * the same bytes the corpus lints ran against. That arrangement has exactly one failure mode: an
 * authoring source is edited and the generator is not re-run, or a generated file is edited by hand,
 * and the two drift apart silently. {@code build.gradle} has claimed since the generator landed that
 * this test closes that hole. Until 1.4.1 the test did not exist.
 *
 * <p>The check is non-mutating by construction: the whole output tree is copied to a scratch
 * directory, the compiler runs against the copy, and the copy is compared back. A drift fails here
 * with the offending paths named; it never rewrites the repository, so a failing build leaves the
 * working tree exactly as it found it. Regenerating is still an explicit author command:
 *
 * <pre>./gradlew generateConversationContent</pre>
 */
class ContentCompilerTest {

    private static final Path CONTENT = TestPaths.of("src/content");
    private static final Path RESOURCES = TestPaths.of("src/main/resources");
    private static final Path FIXTURES = TestPaths.of("src/test/resources");

    /** Fixture files the compiler owns; the rest of {@code src/test/resources} is not its business. */
    private static final List<String> OWNED_FIXTURES = List.of("generated_matcher_fixtures.tsv");

    @Test
    void committedGeneratedContentMatchesWhatTheAuthoringSourcesCompileTo() throws IOException {
        Path scratch = Files.createTempDirectory("mcaconversations-content-verify");
        try {
            Path resources = scratch.resolve("resources");
            Path fixtures = scratch.resolve("fixtures");
            copyTree(RESOURCES, resources);
            for (String owned : OWNED_FIXTURES) {
                copyFile(FIXTURES.resolve(owned), fixtures.resolve(owned));
            }

            ContentCompiler compiler = new ContentCompiler(CONTENT, resources, fixtures);
            compiler.compile();
            compiler.write();

            List<String> drift = new ArrayList<>();
            drift.addAll(compare(RESOURCES, resources, "src/main/resources"));
            for (String owned : OWNED_FIXTURES) {
                drift.addAll(compareFile(FIXTURES.resolve(owned), fixtures.resolve(owned),
                        "src/test/resources/" + owned));
            }
            assertTrue(drift.isEmpty(), drift.size()
                    + " generated file(s) differ from what src/content compiles to."
                    + " Run ./gradlew generateConversationContent and commit the result.\n"
                    + String.join("\n", drift.stream().limit(40).toList())
                    + (drift.size() > 40 ? "\n… and " + (drift.size() - 40) + " more" : ""));
        } finally {
            deleteTree(scratch);
        }
    }

    // --- helpers ----------------------------------------------------------------------------------

    /**
     * Compares two trees by content, reporting changed, missing and extra files alike.
     *
     * <p>All three matter. A changed file is stale output; a missing one is a generated file nobody
     * committed; an extra one is output for a source that was deleted and left behind — the last of
     * which is the failure a "regenerate and diff" habit is least likely to notice.
     */
    private static List<String> compare(Path committed, Path produced, String label) throws IOException {
        Map<String, Path> left = index(committed);
        Map<String, Path> right = index(produced);
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, Path> entry : left.entrySet()) {
            Path other = right.get(entry.getKey());
            if (other == null) {
                problems.add("extra (nothing compiles to it): " + label + "/" + entry.getKey());
            } else if (Files.mismatch(entry.getValue(), other) != -1L) {
                problems.add("stale: " + label + "/" + entry.getKey());
            }
        }
        for (String key : right.keySet()) {
            if (!left.containsKey(key)) {
                problems.add("missing (never committed): " + label + "/" + key);
            }
        }
        return problems;
    }

    private static List<String> compareFile(Path committed, Path produced, String label)
            throws IOException {
        if (!Files.exists(produced)) {
            return Files.exists(committed)
                    ? List.of("extra (nothing compiles to it): " + label)
                    : List.of();
        }
        if (!Files.exists(committed)) {
            return List.of("missing (never committed): " + label);
        }
        return Files.mismatch(committed, produced) != -1L ? List.of("stale: " + label) : List.of();
    }

    private static Map<String, Path> index(Path root) throws IOException {
        Map<String, Path> out = new TreeMap<>();
        if (!Files.isDirectory(root)) {
            return out;
        }
        try (Stream<Path> files = Files.walk(root)) {
            files.filter(Files::isRegularFile)
                    .forEach(path -> out.put(relative(root, path), path));
        }
        return out;
    }

    private static String relative(Path root, Path path) {
        return root.relativize(path).toString().replace('\\', '/');
    }

    private static void copyTree(Path from, Path to) throws IOException {
        try (Stream<Path> files = Files.walk(from)) {
            files.filter(Files::isRegularFile).forEach(path -> {
                try {
                    copyFile(path, to.resolve(relative(from, path)));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }

    private static void copyFile(Path from, Path to) throws IOException {
        if (!Files.exists(from)) {
            return;
        }
        Files.createDirectories(to.getParent());
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
    }

    private static void deleteTree(Path root) throws IOException {
        if (!Files.exists(root)) {
            return;
        }
        try (Stream<Path> paths = Files.walk(root)) {
            paths.sorted(java.util.Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }
}
