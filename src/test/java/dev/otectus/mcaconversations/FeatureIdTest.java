package dev.otectus.mcaconversations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The feature-id registry is closed, so the two things worth checking are that everything content may
 * legitimately write resolves, and that nothing else does.
 *
 * <p>The config side is read from source rather than by calling the supplier, because a
 * {@code ForgeConfigSpec} value throws until its file is loaded and no file is loaded in a unit run.
 * What matters is that each id names a real switch: an id with no switch behind it can never be
 * turned off, which was the whole failure mode the closed registry replaced.
 */
class FeatureIdTest {

    private static final Path SOURCE =
            Path.of("src/main/java/dev/otectus/mcaconversations/FeatureId.java");

    @Test
    @DisplayName("every canonical id and alias resolves to its constant")
    void everyIdResolves() {
        for (FeatureId feature : FeatureId.values()) {
            assertEquals(Optional.of(feature), FeatureId.parse(feature.id()), feature.id());
            for (String alias : feature.aliases()) {
                assertEquals(Optional.of(feature), FeatureId.parse(alias), alias);
            }
            assertEquals(Optional.of(feature), FeatureId.parse("  " + feature.id().toUpperCase() + " "),
                    "ids are trimmed and case-insensitive");
        }
    }

    @Test
    @DisplayName("a typo of a shipped id resolves to nothing at all")
    void typosDoNotResolve() {
        for (FeatureId feature : FeatureId.values()) {
            String typo = feature.id() + "s";
            if (FeatureId.parse(typo).isPresent()) {
                typo = feature.id().substring(0, feature.id().length() - 1);
            }
            assertTrue(FeatureId.parse(typo).isEmpty(),
                    typo + " must not resolve; an unknown id is an invalid reference, not a feature");
        }
        assertTrue(FeatureId.parse(null).isEmpty());
        assertTrue(FeatureId.parse("").isEmpty());
    }

    @Test
    @DisplayName("an unknown id reads as disabled and never as enabled")
    void unknownIdsAreDisabled() {
        assertFalse(McaConversationsConfig.isFeatureEnabled("not_a_feature"));
        assertFalse(McaConversationsConfig.dynamicFeature("not_a_feature", true),
                "the never-throw wrapper must not resurrect the old always-on default");
    }

    @Test
    @DisplayName("no constant escapes its config read, loaded or not")
    void everyConstantIsReadableThroughTheGuard() {
        for (FeatureId feature : FeatureId.values()) {
            assertDoesNotThrow(() -> McaConversationsConfig.dynamicFeature(feature, false), feature.id());
        }
    }

    @Test
    @DisplayName("every constant is decided by real config keys")
    void everyConstantNamesRealConfigKeys() throws IOException {
        String source = Files.readString(SOURCE, StandardCharsets.UTF_8);
        Set<String> fields = new LinkedHashSet<>();
        for (java.lang.reflect.Field field : McaConversationsConfig.Common.class.getFields()) {
            fields.add(field.getName());
        }

        List<String> problems = new ArrayList<>();
        for (FeatureId feature : FeatureId.values()) {
            String declaration = declarationOf(source, feature);
            Matcher reads = Pattern.compile("COMMON\\.(\\w+)\\.get\\(\\)").matcher(declaration);
            int found = 0;
            while (reads.find()) {
                found++;
                if (!fields.contains(reads.group(1))) {
                    problems.add(feature.id() + " reads COMMON." + reads.group(1) + ", which does not exist");
                }
            }
            if (found == 0) {
                problems.add(feature.id() + " reads no config value, so it can never be switched off");
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    /** The source text of one enum constant: its name up to the next constant's. */
    private static String declarationOf(String source, FeatureId feature) {
        int start = indexOfConstant(source, feature);
        assertTrue(start >= 0, feature.name() + " has no declaration in " + SOURCE);
        int end = source.length();
        for (FeatureId other : FeatureId.values()) {
            int index = indexOfConstant(source, other);
            if (index > start && index < end) {
                end = index;
            }
        }
        return source.substring(start, end);
    }

    private static int indexOfConstant(String source, FeatureId feature) {
        String marker = "    " + feature.name() + "(\"";
        int index = source.indexOf(marker);
        while (index > 0 && source.charAt(index - 1) != '\n') {
            index = source.indexOf(marker, index + 1);
        }
        return index;
    }
}
