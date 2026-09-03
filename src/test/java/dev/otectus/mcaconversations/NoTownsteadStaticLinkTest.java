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
 * Standing tripwire (Townstead spec 4.2): <b>no compiled class may reference a Townstead type, and
 * nothing outside {@code dev/otectus/mcaconversations/compat/townstead/} may reference that
 * package.</b>
 *
 * <p>The first scan has <b>no exemption list at all</b>, not even for the guarded package itself.
 * Unlike the MCA: Quests and MCA: Reputation seams, where one package is permitted to link against
 * the other mod, the Townstead integration is reflection-only end to end: {@code TownsteadBinding}
 * matches members by name and arity and adapts every handle to an all-{@code Object} shape, so not
 * one Townstead class is named anywhere in our bytecode. This test is what keeps it that way.
 *
 * <p>That strictness matters more here than the mod count suggests. Townstead is itself compiled
 * against MCA, so its classes carry MCA descriptors in their own constant pools; a single import
 * would drag a <em>relocated</em> MCA type into ours through a third mod. Naming a Townstead type
 * would also make the class unloadable without Townstead, which is the ordinary case for most
 * installs.
 *
 * <p>Both scans byte-search the raw constant pool of every {@code .class} under
 * {@code build/classes/java/main} for the modified-UTF8 encoding of an <em>internal (slash)</em>
 * name, which is the form the JVM uses for a real class, method or field reference. A plain byte
 * search is enough, since this only has to prove a string is absent rather than parse bytecode, so
 * the test stays dependency-free and runs on any JDK.
 *
 * <p><b>Neither scan needs a whitelist.</b> The sanctioned entry point, {@code TownsteadCompat},
 * names the implementation class as a <em>dotted</em> string literal for {@code Class.forName}, and
 * {@code TownsteadBinding} stores Townstead's package root dotted for the same reason; a dotted
 * literal can never collide with the slash form the needles look for. The always-loaded seam types
 * ({@code compat/TownsteadBridge}, the {@code Townstead*View} records) sit in {@code compat} with a
 * capital {@code T}, which differs from the second needle's lowercase {@code t} at the first byte
 * after the package separator, so they never match either. The same reasoning covers the
 * {@code @Mixin(targets = "...")} literal on the optional client mixin, which is dotted too.
 *
 * @see dev.otectus.mcaconversations.compat.TownsteadCompat the one place the guarded package is named
 */
class NoTownsteadStaticLinkTest {

    private static final String EXEMPT_PACKAGE_PREFIX = "dev/otectus/mcaconversations/compat/townstead/";

    private static final byte[] TOWNSTEAD_NEEDLE =
            "com/aetherianartificer/townstead".getBytes(StandardCharsets.UTF_8);

    /**
     * Trailing slash on purpose: it is what separates the guarded package {@code compat/townstead/}
     * from the always-loaded seam types {@code compat/Townstead*}.
     */
    private static final byte[] GUARDED_PACKAGE_NEEDLE =
            "dev/otectus/mcaconversations/compat/townstead/".getBytes(StandardCharsets.UTF_8);

    @Test
    void noCompiledClassReferencesATownsteadType() throws IOException {
        List<String> violations = scan(TOWNSTEAD_NEEDLE, false);

        assertTrue(violations.isEmpty(),
                "Class(es) statically reference com.aetherianartificer.townstead. Every Townstead "
                        + "access must resolve by name through TownsteadBinding, so the mod keeps "
                        + "loading when Townstead is absent and Townstead's own relocated-MCA "
                        + "descriptors never reach our constant pool. Offenders: " + violations);
    }

    @Test
    void noAlwaysLoadedClassReferencesTheGuardedPackage() throws IOException {
        List<String> violations = scan(GUARDED_PACKAGE_NEEDLE, true);

        assertTrue(violations.isEmpty(),
                "Class(es) outside " + EXEMPT_PACKAGE_PREFIX + " reference it directly. The only "
                        + "sanctioned entry point is TownsteadCompat's Class.forName on a dotted class "
                        + "name, which is invisible to this scan by design. Offenders: " + violations);
    }

    private static List<String> scan(byte[] needle, boolean exemptGuardedPackage) throws IOException {
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
                if (exemptGuardedPackage && relative.startsWith(EXEMPT_PACKAGE_PREFIX)) {
                    return;
                }
                try {
                    if (containsNeedle(Files.readAllBytes(p), needle)) {
                        violations.add(relative);
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
        return violations;
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
