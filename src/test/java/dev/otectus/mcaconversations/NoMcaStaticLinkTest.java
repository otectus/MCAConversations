package dev.otectus.mcaconversations;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import dev.otectus.mcaconversations.support.TestPaths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Standing tripwire: <b>no compiled class may reference a Minecraft Comes Alive type</b>.
 *
 * <p>This is the enforcement mechanism for the runtime binding layer, and it is the test that would
 * have caught the original bug. MCA repackaged mid-7.7-line — from a Forgix-merged jar whose Forge
 * classes sit at {@code forge.net.mca.*} to a single-root jar at {@code net.conczin.mca.*} — and
 * because {@code McaCompat} imported the former directly, the first MCA reference on a renamed build
 * threw {@code NoClassDefFoundError} from a {@code PlayerInteractEvent.EntityInteract} handler,
 * killing a dedicated server the moment any player right-clicked any entity. Every MCA class and
 * member is now resolved by name at runtime by {@code compat.mca.McaBinding}, and this test makes
 * sure a stray {@code import} can never quietly reintroduce the linkage.
 *
 * <p>It walks every {@code .class} file under {@code build/classes/java/main} and byte-searches the
 * raw constant pool for the modified-UTF8 encoding of each MCA package in <em>internal (slash)</em>
 * form — which is what the JVM uses for a real class, method, or field reference. The binding layer
 * stores its candidate roots as <em>dotted</em> strings ({@code "forge.net.mca."}), which never
 * appear in slash form, so the distinction is exact and free and the binding layer needs no
 * exemption. <b>There is no exemption list at all</b>: unlike the optional-sibling seams, where one package
 * is permitted to link, nothing anywhere may name an MCA type.
 *
 * <p>A plain byte search is sufficient — this only needs to prove the string is absent, not parse
 * bytecode — which keeps the test dependency-free and runnable on any JDK.
 *
 * @see NoTownsteadStaticLinkTest the same technique, applied to the optional Townstead integration
 */
class NoMcaStaticLinkTest {

    /**
     * Every MCA package root this mod has ever seen, in internal form. {@code net/mca/} keeps its
     * trailing slash so it cannot collide with an unrelated {@code net/mcaSomething} package.
     *
     * <p>The Townstead compatibility spec also names a {@code forge.net.conczin.mca} root. It needs no
     * entry of its own: {@code net/conczin/mca} is a substring of it, so any class referencing that
     * root already trips the second needle. Adding it would be dead weight that reads like coverage.
     */
    private static final String[] MCA_ROOTS = {"forge/net/mca", "net/conczin/mca", "net/mca/"};

    @Test
    void noCompiledClassReferencesAnMcaType() throws IOException {
        // Resolved against the project root: ModDevGradle's unitTest runs from build/minecraft-junit,
        // not the project directory, so a relative path would miss (see support.TestPaths).
        Path classesDir = TestPaths.of("build/classes/java/main");
        assertTrue(Files.isDirectory(classesDir),
                "build/classes/java/main does not exist; run `./gradlew compileJava` (or `test`, "
                        + "which depends on it) before running this test directly.");

        List<String> violations = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(classesDir)) {
            paths.filter(p -> p.toString().endsWith(".class")).forEach(p -> {
                String relative = classesDir.relativize(p).toString().replace('\\', '/');
                try {
                    byte[] bytes = Files.readAllBytes(p);
                    for (String root : MCA_ROOTS) {
                        if (containsNeedle(bytes, root.getBytes(StandardCharsets.UTF_8))) {
                            violations.add(relative + " -> " + root);
                        }
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }

        assertTrue(violations.isEmpty(),
                "Class(es) statically reference an MCA package. Every MCA access must go through "
                        + "dev.otectus.mcaconversations.compat.mca.McaBinding/McaHandles so the mod keeps working "
                        + "across MCA's package renames. Offenders: " + violations);
    }

    private static boolean containsNeedle(byte[] haystack, byte[] needle) {
        outer:
        for (int i = 0; i <= haystack.length - needle.length; i++) {
            for (int j = 0; j < needle.length; j++) {
                if (haystack[i + j] != needle[j]) {
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }
}
