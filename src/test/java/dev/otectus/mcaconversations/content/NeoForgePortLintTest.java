package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Stops the 1.20.1 Forge idioms from creeping back in.
 *
 * <p>Most of what this checks would fail to compile anyway. The value is in the ones that would
 * not: {@code ForgeCaps} outside the migration, a {@code refmap} key that will never resolve, a
 * re-added {@code mods.toml} shadowing the real metadata, or {@code VillagerMessageMixin} coming
 * back to hook constructors that no longer exist. Those all build cleanly and fail at runtime, or
 * silently do nothing, which is exactly the failure mode a port introduces most easily.
 *
 * <p>Each rule names the file it allows, rather than pattern-matching a directory, so adding an
 * exemption is a deliberate edit here and not a side effect of where a file happens to live.
 */
class NeoForgePortLintTest {

    private static final Path SOURCE_ROOT = TestPaths.of("src/main/java/dev/otectus/mcaconversations");
    private static final Path TEST_ROOT = TestPaths.of("src/test/java/dev/otectus/mcaconversations");
    private static final Path RESOURCES = TestPaths.of("src/main/resources");

    /**
     * Banned token to why it is banned. Every one of these has a 1.21.1 replacement that is already
     * used everywhere else in the tree.
     */
    private static final List<String[]> BANNED_IN_MAIN = List.of(
            new String[]{"net.minecraftforge", "Forge package root; NeoForge is net.neoforged.*"},
            new String[]{"forge.net.mca", "the Forgix-relocated MCA root; 1.21.1 ships net.conczin.mca"},
            new String[]{"new ResourceLocation(", "removed in 1.21; use ResourceLocation.fromNamespaceAndPath"},
            new String[]{"ForgeRegistries", "use BuiltInRegistries"},
            new String[]{"SimpleChannel", "use CustomPacketPayload and PayloadRegistrar"},
            new String[]{"NetworkRegistry", "use RegisterPayloadHandlersEvent"},
            new String[]{"LazyOptional", "capabilities are gone; use data attachments"},
            new String[]{"ICapabilityProvider", "capabilities are gone; use data attachments"},
            new String[]{"AttachCapabilitiesEvent", "attachments need no attach step"},
            new String[]{"ForgeConfigSpec", "use ModConfigSpec"},
            new String[]{"MinecraftForge.EVENT_BUS", "use NeoForge.EVENT_BUS"},
            new String[]{"TickEvent.Phase", "use ServerTickEvent.Post / ClientTickEvent.Post"},
            new String[]{"LivingHurtEvent", "use LivingIncomingDamageEvent"},
            new String[]{"@Mod.EventBusSubscriber", "EventBusSubscriber is a top-level annotation now"});

    @Test
    void noForgeEraApiSurvivesInMainSources() throws IOException {
        List<String> offenders = new ArrayList<>();
        for (Path file : javaFiles(SOURCE_ROOT)) {
            String relative = relativize(SOURCE_ROOT, file);
            String source = stripComments(Files.readString(file, StandardCharsets.UTF_8));
            for (String[] rule : BANNED_IN_MAIN) {
                if (containsToken(source, rule[0])) {
                    offenders.add(relative + ": " + rule[0] + " (" + rule[1] + ")");
                }
            }
        }
        assertTrue(offenders.isEmpty(),
                () -> "Forge-era API still present:\n  " + String.join("\n  ", offenders));
    }

    @Test
    void theForgeCapsKeyAppearsOnlyWhereTheMigrationNeedsIt() throws IOException {
        // The one legitimate use: reading the root compound a Forge world wrote. Anywhere else it
        // would mean something is still trying to write or resolve the old shape.
        List<String> allowed = List.of("gift/ForgeCapsMigration.java", "mixin/PlayerLegacyDataMixin.java");
        List<String> offenders = new ArrayList<>();
        for (Path file : javaFiles(SOURCE_ROOT)) {
            String relative = relativize(SOURCE_ROOT, file);
            if (allowed.contains(relative)) {
                continue;
            }
            if (containsToken(stripComments(Files.readString(file, StandardCharsets.UTF_8)), "ForgeCaps")) {
                offenders.add(relative);
            }
        }
        assertTrue(offenders.isEmpty(),
                () -> "ForgeCaps named outside the migration:\n  " + String.join("\n  ", offenders));
    }

    @Test
    void theObsoleteVillagerMessageMixinIsGoneForGood() throws IOException {
        assertFalse(Files.exists(SOURCE_ROOT.resolve("mixin/client/VillagerMessageMixin.java")),
                "MCA 1.21.1 passes Component objects straight through; this mixin has nothing to fix "
                        + "and its target constructors no longer exist");
        String config = Files.readString(RESOURCES.resolve("mcaconversations.mixins.json"),
                StandardCharsets.UTF_8);
        assertFalse(config.contains("VillagerMessageMixin"));
    }

    @Test
    void modMetadataIsTheNeoForgeFileOnly() throws IOException {
        assertTrue(Files.exists(RESOURCES.resolve("META-INF/neoforge.mods.toml")));
        assertFalse(Files.exists(RESOURCES.resolve("META-INF/mods.toml")),
                "a leftover mods.toml is ignored by NeoForge and only misleads whoever reads it next");
    }

    @Test
    void theMixinConfigTargetsJava21AndNamesNoRefmap() throws IOException {
        String config = Files.readString(RESOURCES.resolve("mcaconversations.mixins.json"),
                StandardCharsets.UTF_8);
        assertTrue(config.contains("\"JAVA_21\""), "Minecraft 1.21.1 runs on Java 21");
        assertFalse(config.contains("JAVA_17"));
        assertFalse(config.contains("refmap"),
                "dev and production both run official Mojang names, so no refmap is ever generated");
    }

    @Test
    void generatedMetadataIsValidUtf8() throws IOException {
        // gradle.properties is read with the Java Properties charset, and processResources writes
        // with the platform charset. Get either wrong and neoforge.mods.toml comes out as invalid
        // UTF-8, which NightConfig refuses and FML reports as "not a valid mod file" -- a failure
        // that points at the mod folder rather than at the character that caused it.
        Path properties = TestPaths.of("gradle.properties");
        byte[] raw = Files.readAllBytes(properties);
        List<String> offenders = new ArrayList<>();
        for (int i = 0; i < raw.length; i++) {
            if ((raw[i] & 0xFF) > 0x7F) {
                offenders.add("byte " + i);
            }
        }
        assertTrue(offenders.isEmpty(),
                () -> "gradle.properties must stay pure ASCII and spell non-ASCII as a \\uXXXX "
                        + "escape; found non-ASCII at " + String.join(", ", offenders));
    }

    @Test
    void everyMixinListedInTheConfigExists() throws IOException {
        String config = Files.readString(RESOURCES.resolve("mcaconversations.mixins.json"),
                StandardCharsets.UTF_8);
        List<String> missing = new ArrayList<>();
        for (String line : config.split("\n")) {
            String trimmed = line.trim().replace("\"", "").replace(",", "");
            if (!trimmed.endsWith("Mixin")) {
                continue;
            }
            Path expected = SOURCE_ROOT.resolve("mixin/" + trimmed.replace('.', '/') + ".java");
            if (!Files.exists(expected)) {
                missing.add(trimmed);
            }
        }
        assertTrue(missing.isEmpty(),
                () -> "mixin config names classes that do not exist: " + String.join(", ", missing));
    }

    @Test
    void testsResolveRepositoryPathsThroughTestPaths() throws IOException {
        // ModDevGradle runs the suite from build/minecraft-junit, so a bare relative path silently
        // resolves to the wrong place. This is the rule that keeps the content lints honest.
        List<String> offenders = new ArrayList<>();
        for (Path file : javaFiles(TEST_ROOT)) {
            String relative = relativize(TEST_ROOT, file);
            if (relative.equals("support/TestPaths.java")) {
                continue;
            }
            String source = Files.readString(file, StandardCharsets.UTF_8);
            if (source.contains("Path.of(\"src/") || source.contains("Paths.get(\"src/")) {
                offenders.add(relative);
            }
        }
        assertTrue(offenders.isEmpty(),
                () -> "tests using a bare relative repository path:\n  " + String.join("\n  ", offenders));
    }

    /**
     * Drops comments before scanning, so a rule fires on code and not on the prose explaining why
     * that code is gone. Half this file's job is documenting the Forge idioms it forbids, and a
     * lint that cannot tell the difference makes those explanations undeletable.
     */
    private static String stripComments(String source) {
        return source
                .replaceAll("(?s)/\\*.*?\\*/", " ")
                .replaceAll("(?m)//.*$", " ");
    }

    /**
     * Matches only where the needle is not part of a longer identifier. Without this,
     * {@code ForgeRegistries} flags every use of {@code NeoForgeRegistries} and
     * {@code TickEvent} flags {@code ClientTickEvent} -- both correct 1.21.1 API.
     */
    private static boolean containsToken(String source, String needle) {
        int from = 0;
        while (true) {
            int at = source.indexOf(needle, from);
            if (at < 0) {
                return false;
            }
            boolean identifierBefore = at > 0 && isIdentifierPart(source.charAt(at - 1));
            int end = at + needle.length();
            boolean identifierAfter = end < source.length() && isIdentifierPart(source.charAt(end));
            if (!identifierBefore && !identifierAfter) {
                return true;
            }
            from = at + 1;
        }
    }

    private static boolean isIdentifierPart(char c) {
        return Character.isLetterOrDigit(c) || c == '_' || c == '$';
    }

    private static List<Path> javaFiles(Path root) throws IOException {
        try (Stream<Path> files = Files.walk(root)) {
            return files.filter(p -> p.toString().endsWith(".java")).sorted().toList();
        }
    }

    private static String relativize(Path root, Path file) {
        return root.relativize(file).toString().replace('\\', '/');
    }
}
