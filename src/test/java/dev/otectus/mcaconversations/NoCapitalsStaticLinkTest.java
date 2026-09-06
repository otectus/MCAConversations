package dev.otectus.mcaconversations;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Standing tripwire: <b>no compiled class may reference an MCA Capitals type, and nothing outside
 * {@code dev/otectus/mcaconversations/compat/capitals/} may reference that package.</b>
 *
 * <p>The first scan has <b>no exemption list at all</b>, not even for the guarded package itself.
 * The Capitals integration is reflection-only end to end: {@code CapitalsBinding} matches members by
 * name and arity and adapts every handle to an all-{@code Object} shape, so not one Capitals class is
 * named anywhere in our bytecode. This test is what keeps it that way.
 *
 * <p>That strictness matters here for the same reason it does for Townstead. Capitals is itself
 * compiled against MCA, so its classes carry MCA descriptors in their own constant pools; a single
 * import would drag a relocated MCA type into ours through a third mod. Naming a Capitals type would
 * also make the class unloadable without Capitals, which is the ordinary case for most installs.
 *
 * <p>Both scans byte-search the raw constant pool of every {@code .class} under
 * {@code build/classes/java/main} for the modified-UTF8 encoding of an <em>internal (slash)</em>
 * name, which is the form the JVM uses for a real class, method or field reference.
 *
 * <p><b>Neither scan needs a whitelist.</b> The sanctioned entry point, {@code CapitalsCompat}, names
 * the implementation class as a <em>dotted</em> string literal for {@code Class.forName}, and
 * {@code CapitalsBinding} stores Capitals' package root dotted for the same reason. The
 * always-loaded seam types ({@code compat/CapitalsBridge}, the {@code Capital*View} records) sit in
 * {@code compat} with a capital {@code C}, which differs from the second needle's lowercase
 * {@code c} at the first byte after the package separator, so they never match either.
 *
 * @see dev.otectus.mcaconversations.compat.CapitalsCompat the one place the guarded package is named
 */
class NoCapitalsStaticLinkTest {

    private static final String EXEMPT_PACKAGE_PREFIX = "dev/otectus/mcaconversations/compat/capitals/";

    private static final byte[] CAPITALS_NEEDLE =
            "com/majesttyx/mcacapitals".getBytes(StandardCharsets.UTF_8);

    /**
     * Trailing slash on purpose: it is what separates the guarded package {@code compat/capitals/}
     * from the always-loaded seam types {@code compat/Capital*}.
     */
    private static final byte[] GUARDED_PACKAGE_NEEDLE =
            "dev/otectus/mcaconversations/compat/capitals/".getBytes(StandardCharsets.UTF_8);

    @Test
    void noCompiledClassReferencesACapitalsType() throws IOException {
        List<String> violations = scan(CAPITALS_NEEDLE, false);

        assertTrue(violations.isEmpty(),
                "Class(es) statically reference com.majesttyx.mcacapitals. Every Capitals access must "
                        + "resolve by name through CapitalsBinding, so the mod keeps loading when "
                        + "Capitals is absent and Capitals' own MCA descriptors never reach our "
                        + "constant pool. Offenders: " + violations);
    }

    @Test
    void noAlwaysLoadedClassReferencesTheGuardedPackage() throws IOException {
        List<String> violations = scan(GUARDED_PACKAGE_NEEDLE, true);

        assertTrue(violations.isEmpty(),
                "Class(es) outside " + EXEMPT_PACKAGE_PREFIX + " reference it directly. The only "
                        + "sanctioned entry point is CapitalsCompat's Class.forName on a dotted class "
                        + "name, which is invisible to this scan by design. Offenders: " + violations);
    }

    private static List<String> scan(byte[] needle, boolean exemptGuardedPackage) throws IOException {
        Path classesDir = Paths.get("build", "classes", "java", "main");
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
