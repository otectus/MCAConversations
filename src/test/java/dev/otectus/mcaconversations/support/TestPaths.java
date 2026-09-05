package dev.otectus.mcaconversations.support;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Resolves repository paths for the tests that read the mod's own source and resources — the content
 * lints, locale parity, the mixin-config lint, the topic-path simulation, and the interiority profile.
 *
 * <p>Gradle's {@code test} task runs from the project directory here, so bare relative paths happen
 * to resolve; a runner with any other working directory would break them silently. The root is taken
 * from {@code -Dmcaconversations.projectRoot} when a build sets it, and otherwise by walking up from
 * the working directory to the first directory containing {@code settings.gradle}.
 */
public final class TestPaths {

    private static final Path ROOT = locate();

    private TestPaths() {
    }

    /** The repository root. */
    public static Path projectRoot() {
        return ROOT;
    }

    /** {@code of("src/main/resources")} → an absolute path under the project root. */
    public static Path of(String relative) {
        return ROOT.resolve(relative);
    }

    private static Path locate() {
        String prop = System.getProperty("mcaconversations.projectRoot");
        if (prop != null && !prop.isBlank()) {
            return Path.of(prop);
        }
        Path cwd = Path.of("").toAbsolutePath();
        for (Path p = cwd; p != null; p = p.getParent()) {
            if (Files.exists(p.resolve("settings.gradle"))) {
                return p;
            }
        }
        return cwd;
    }
}
