package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.compat.capitals.CapitalsBinding;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Resolves {@link CapitalsBinding#MANIFEST} against a real MCA Capitals jar.
 *
 * <h2>Why this exists</h2>
 *
 * <p>{@code NoCapitalsStaticLinkTest} guarantees no class names a Capitals type, which means the
 * compiler cannot tell anyone when Capitals renames or removes something the manifest asks for: a
 * stale member name would surface as a silently dead capability rather than as a build error. This is
 * the replacement safety net. It walks the whole manifest against the supplied jar and fails if
 * anything is missing, so a moved method shows up here rather than as a topic that never appears.
 *
 * <h2>Why MCA rides along</h2>
 *
 * <p>Capitals is compiled against MCA 7.7.1-alpha.2 or newer. Enumerating a class's methods resolves
 * every parameter type, so a Capitals class whose descriptors name an MCA type reads as unbound with
 * MCA absent. That is the correct production behaviour, and exactly how a mismatched Capitals/MCA
 * pair degrades, but it would make this probe vacuously green.
 *
 * <h2>Running it</h2>
 *
 * <pre>./gradlew capitalsProbeTest -PcapitalsJar=/path/mcacapitals-1.3.6.jar</pre>
 *
 * <p>Skipped rather than failed when no jar is supplied, so an ordinary checkout still runs the suite.
 */
class CapitalsBindingProbeTest {

    private static final String CAPITALS_JAR_PROPERTY = "mcaconversations.capitals.probe.jar";
    private static final String MCA_JARS_PROPERTY = "mcaconversations.probe.jars";

    @Test
    void manifestResolvesAgainstTheRealCapitalsJar() throws Exception {
        List<Path> all = withMca(requireJar());

        try (URLClassLoader loader = loaderFor(all)) {
            CapitalsBinding.Resolution resolution = CapitalsBinding.resolveAgainst(loader);

            assertEquals(List.of(), resolution.unresolved(),
                    "MCA Capitals is missing member(s) the manifest asks for. Either Capitals renamed "
                            + "them (update CapitalsBinding's manifest) or removed them (drop the "
                            + "capability and give CapitalsHandles a fallback). Jars: " + all);
            assertEquals(CapitalsStatus.FULL, resolution.status(),
                    "Every declared capability must bind against a supported MCA Capitals.");
            assertEquals(CapitalsBinding.DECLARED_CAPABILITIES, resolution.capabilities());
            System.out.println("[probe] MCA Capitals bound; capabilities = "
                    + resolution.capabilities().size());
        }
    }

    /**
     * Sanity check on the probe itself: with no Capitals anywhere, resolution must report a clean
     * absence rather than throwing. That is the state the rest of the unit suite runs in, and the
     * state most servers are in. It has to be boring, not fatal.
     */
    @Test
    void resolutionWithoutCapitalsIsAbsentAndDoesNotThrow() throws Exception {
        try (URLClassLoader empty = new URLClassLoader(new URL[0], null)) {
            CapitalsBinding.Resolution resolution = CapitalsBinding.resolveAgainst(empty);

            assertEquals(CapitalsStatus.ABSENT, resolution.status());
            assertTrue(resolution.capabilities().isEmpty());
            assertTrue(resolution.unresolved().isEmpty(),
                    "An absent Capitals is not a partial binding; nothing should be reported as a miss.");
            // Every handle must still be a usable stub: CapitalsHandles invokes them with no null check.
            assertNotNull(resolution.handle(CapitalsBinding.CAPITAL_FOR_VILLAGE));
        }
    }

    // --- helpers ---------------------------------------------------------------------------------

    private static List<Path> requireJar() {
        List<Path> capitals = jars(CAPITALS_JAR_PROPERTY);
        Assumptions.assumeFalse(capitals.isEmpty(),
                "No MCA Capitals jar supplied (" + CAPITALS_JAR_PROPERTY + "); run "
                        + "`./gradlew capitalsProbeTest -PcapitalsJar=<path>` to exercise this.");
        return capitals;
    }

    private static List<Path> withMca(List<Path> capitals) {
        List<Path> all = new ArrayList<>(capitals);
        all.addAll(jars(MCA_JARS_PROPERTY));
        return all;
    }

    private static URLClassLoader loaderFor(List<Path> jars) throws Exception {
        List<URL> urls = new ArrayList<>();
        for (Path jar : jars) {
            urls.add(jar.toUri().toURL());
        }
        // The parent hides MCA so the pinned copy the test runtime carries for FML cannot answer
        // for the MCA types in Capitals' signatures; the probe jars still supply their own.
        return new URLClassLoader(urls.toArray(URL[]::new),
                new McaHidingClassLoader(CapitalsBindingProbeTest.class.getClassLoader()));
    }

    private static List<Path> jars(String property) {
        List<Path> jars = new ArrayList<>();
        for (String entry : System.getProperty(property, "").split(File.pathSeparator)) {
            if (!entry.isBlank()) {
                Path path = Paths.get(entry.trim());
                if (Files.isRegularFile(path)) {
                    jars.add(path);
                }
            }
        }
        return jars;
    }
}
