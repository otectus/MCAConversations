package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.content.NativeConstraintTokens;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks the token set the content lint enforces against the real {@code Constraint} registry in every
 * probed MCA jar.
 *
 * <p>MCA ignores a constraint token it does not recognise, so an invented one is not a load error — it
 * is an exclusion that silently never happens, which is exactly how {@code !child} survived in nine
 * shipped answers. The lint can only catch that if its token set is MCA's, so the set is re-derived
 * here from the jar rather than trusted.
 *
 * <p>Gated and loaded exactly as {@code McaBindingProbeTest} does: skipped when no probe jars are
 * supplied and one {@link URLClassLoader} per jar. The 1.21.1 NeoForge artifact only ever shipped the
 * renamed {@code net.conczin.mca} root, so that is the one root listed here, as in
 * {@code McaBinding.CANDIDATE_ROOTS}.
 */
class ConstraintVocabularyProbeTest {

    private static final String JARS_PROPERTY = "mcaconversations.probe.jars";

    private static final List<String> ROOTS = List.of("net.conczin.mca");

    @Test
    void checkedInTokensExistInEveryProbedMcaJar() throws Exception {
        List<Path> jars = probeJars();
        Assumptions.assumeFalse(jars.isEmpty(),
                "No MCA jar to probe (" + JARS_PROPERTY + "); run via Gradle to exercise this.");

        List<String> problems = new ArrayList<>();
        for (Path jar : jars) {
            try (URLClassLoader loader = new URLClassLoader(new URL[] {jar.toUri().toURL()},
                    ConstraintVocabularyProbeTest.class.getClassLoader())) {
                Set<String> registry = registryKeys(loader);
                if (registry.isEmpty()) {
                    problems.add(jar.getFileName() + ": no Constraint registry found under any known"
                            + " package root " + ROOTS);
                    continue;
                }
                Set<String> missing = new TreeSet<>(NativeConstraintTokens.ALL);
                missing.removeAll(registry);
                if (!missing.isEmpty()) {
                    problems.add(jar.getFileName() + ": token(s) the corpus is allowed to use that this"
                            + " MCA does not have: " + missing);
                }
                System.out.println("[probe] " + jar.getFileName() + " -> " + registry.size()
                        + " constraint tokens");
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    /** Every key of {@code Constraint.REGISTRY}, or empty when this jar has no such class. */
    @SuppressWarnings("unchecked")
    private static Set<String> registryKeys(ClassLoader loader) throws Exception {
        for (String root : ROOTS) {
            Class<?> constraint;
            try {
                constraint = Class.forName(root + ".entity.interaction.Constraint", false, loader);
            } catch (ClassNotFoundException absent) {
                continue;
            }
            Field registry = constraint.getDeclaredField("REGISTRY");
            registry.setAccessible(true);
            return new LinkedHashSet<>(((Map<String, ?>) registry.get(null)).keySet());
        }
        return Set.of();
    }

    private static List<Path> probeJars() {
        List<Path> jars = new ArrayList<>();
        for (String entry : System.getProperty(JARS_PROPERTY, "").split(File.pathSeparator)) {
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
