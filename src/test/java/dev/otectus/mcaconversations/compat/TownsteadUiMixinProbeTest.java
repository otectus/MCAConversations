package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Exact optional-member probe for the narrow Townstead number-badge and digit adapter. */
class TownsteadUiMixinProbeTest {

    private static final String TOWNSTEAD_JARS = "mcaconversations.townstead.probe.jars";
    private static final String MCA_JARS = "mcaconversations.probe.jars";

    @Test
    void supportedTownsteadJarStillExposesTheDecoratedChoiceSurface() throws Exception {
        List<Path> townstead = paths(TOWNSTEAD_JARS);
        Assumptions.assumeFalse(townstead.isEmpty(), "No Townstead jar supplied to the optional probe");
        List<URL> urls = new ArrayList<>();
        for (Path path : townstead) urls.add(path.toUri().toURL());
        for (Path path : paths(MCA_JARS)) urls.add(path.toUri().toURL());

        List<String> problems = new ArrayList<>();
        try (URLClassLoader loader = new URLClassLoader(urls.toArray(URL[]::new), getClass().getClassLoader())) {
            Class<?> panel = Class.forName(
                    "com.aetherianartificer.townstead.client.gui.dialogue.ChoicePanel", false, loader);
            for (String field : List.of("displayEntries", "visible", "hoveredIndex", "selectedIndex",
                    "scrollOffset", "entryHeights", "x", "y", "width", "height")) {
                if (!declaresField(panel, field)) problems.add("ChoicePanel field " + field + " is missing");
            }
            for (String method : List.of("render", "select", "isVisible")) {
                if (!declaresMethod(panel, method)) problems.add("ChoicePanel method " + method + " is missing");
            }

            Class<?> screen = Class.forName(
                    "com.aetherianartificer.townstead.client.gui.dialogue.RpgDialogueScreen", false, loader);
            if (!declaresField(screen, "choicePanel")) problems.add("RpgDialogueScreen.choicePanel is missing");
            for (String method : List.of("m_7933_", "handleChoiceSelection")) {
                if (!declaresMethod(screen, method)) problems.add("RpgDialogueScreen method " + method + " is missing");
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    private static boolean declaresField(Class<?> type, String name) {
        return java.util.Arrays.stream(type.getDeclaredFields()).anyMatch(field -> field.getName().equals(name));
    }

    private static boolean declaresMethod(Class<?> type, String name) {
        return java.util.Arrays.stream(type.getDeclaredMethods()).anyMatch(method -> method.getName().equals(name));
    }

    private static List<Path> paths(String property) {
        List<Path> paths = new ArrayList<>();
        for (String value : System.getProperty(property, "").split(File.pathSeparator)) {
            if (!value.isBlank()) {
                Path path = Path.of(value.trim());
                if (Files.isRegularFile(path)) paths.add(path);
            }
        }
        return paths;
    }
}
