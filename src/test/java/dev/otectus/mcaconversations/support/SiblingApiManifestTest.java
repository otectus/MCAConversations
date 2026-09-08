package dev.otectus.mcaconversations.support;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Guards {@code gradle/sibling-apis.properties}, the pin that decides which build of MCA: Quests and
 * MCA: Reputation the optional integrations compile against.
 *
 * <p>The manifest is only as good as its own well-formedness: a truncated hash, a jar path that no
 * longer exists, or a block copied across from the other loader's repository all produce a build that
 * either fails obscurely inside Gradle or — worse — compiles against the wrong game version. The build
 * itself checks the bytes ({@code verifySiblingApis}); this checks that there is something coherent to
 * check, and that the pin belongs to <em>this</em> repository's loader and Minecraft version.
 */
class SiblingApiManifestTest {

    private static final Pattern COMMIT = Pattern.compile("[0-9a-f]{40}");
    private static final Pattern SHA256 = Pattern.compile("[0-9a-f]{64}");

    private static Properties manifest;
    private static Properties gradleProperties;

    @BeforeAll
    static void load() throws IOException {
        manifest = read(TestPaths.of("gradle/sibling-apis.properties"));
        gradleProperties = read(TestPaths.of("gradle.properties"));
    }

    private static Properties read(Path path) throws IOException {
        assertTrue(Files.isRegularFile(path), path + " is missing");
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(path)) {
            properties.load(in);
        }
        return properties;
    }

    @Test
    @DisplayName("the manifest names exactly the providers the build resolves")
    void providersArePresent() {
        assertNotNull(manifest.getProperty("mcaquests.jar"), "no mcaquests block");
        assertNotNull(manifest.getProperty("mcareputation.jar"), "no mcareputation block");
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"mcaquests", "mcareputation"})
    @DisplayName("every provider block is complete")
    void blockIsComplete(String provider) {
        for (String key : new String[]{"version", "minecraft", "loader", "commit", "jar", "sha256"}) {
            String value = manifest.getProperty(provider + "." + key);
            assertTrue(value != null && !value.isBlank(), provider + "." + key + " is missing or blank");
        }
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"mcaquests", "mcareputation"})
    @DisplayName("the provider commit is a full 40-character SHA-1")
    void commitIsFullLength(String provider) {
        String commit = manifest.getProperty(provider + ".commit");
        assertTrue(COMMIT.matcher(commit).matches(),
                provider + ".commit is '" + commit + "'; a full 40-hex-character commit is required "
                        + "so the artifact can be rebuilt from source");
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"mcaquests", "mcareputation"})
    @DisplayName("the pinned hash is a full SHA-256")
    void hashIsFullLength(String provider) {
        String sha256 = manifest.getProperty(provider + ".sha256");
        assertTrue(SHA256.matcher(sha256).matches(),
                provider + ".sha256 is '" + sha256 + "'; 64 hex characters are required");
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"mcaquests", "mcareputation"})
    @DisplayName("the vendored jar exists and is named for the pinned version")
    void jarIsVendored(String provider) {
        String relative = manifest.getProperty(provider + ".jar");
        Path jar = TestPaths.of(relative);
        assertTrue(Files.isRegularFile(jar), relative + " is not a file; the vendored API jar must be "
                + "committed, not fetched at build time");
        String name = jar.getFileName().toString();
        String version = manifest.getProperty(provider + ".version");
        assertTrue(name.contains(version),
                name + " does not carry the pinned version " + version);
        assertTrue(name.endsWith("-api.jar"),
                name + " is not an -api.jar; the full mod jar must never be a compile dependency");
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"mcaquests", "mcareputation"})
    @DisplayName("the pin belongs to this repository's loader and Minecraft version")
    void pinMatchesThisBuild(String provider) {
        String expectedMinecraft = gradleProperties.getProperty("minecraft_version");
        assertTrue(expectedMinecraft.equals(manifest.getProperty(provider + ".minecraft")),
                provider + ".minecraft is '" + manifest.getProperty(provider + ".minecraft")
                        + "' but this repository builds for " + expectedMinecraft
                        + "; the jar was probably copied from the other loader's repository");
        String expectedLoader = expectedLoader();
        assertTrue(expectedLoader.equals(manifest.getProperty(provider + ".loader")),
                provider + ".loader is '" + manifest.getProperty(provider + ".loader")
                        + "' but this repository builds for " + expectedLoader);
    }

    /** The loader this repository builds for, read from whichever loader version gradle.properties pins. */
    private static String expectedLoader() {
        return gradleProperties.getProperty("neo_version") != null
                || gradleProperties.getProperty("neoforge_version") != null ? "neoforge" : "forge";
    }
}
