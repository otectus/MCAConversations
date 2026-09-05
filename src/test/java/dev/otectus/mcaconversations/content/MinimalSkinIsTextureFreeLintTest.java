package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Keeps the minimal skin flat.
 *
 * <p>"Texture-free" is a promise to a particular set of players -- the ones on low-end machines,
 * unusual resource packs, or simply too much visual noise -- and it is the sort of promise that
 * erodes one convenient blit at a time, each of which compiles and looks fine on the author's
 * machine. Reading the source is the only check that can tell the difference between a flat skin and
 * a nearly flat one, so the rule is mechanical rather than a review convention.
 */
class MinimalSkinIsTextureFreeLintTest {

    private static final Path MINIMAL_SKIN = TestPaths.of(
            "src/main/java/dev/otectus/mcaconversations/client/dialogue/MinimalDialogueSkin.java");

    /** Banned token to why. Each has a flat equivalent the file already uses. */
    private static final List<String[]> BANNED = List.of(
            new String[]{"blit", "a flat skin paints rectangles, not textures"},
            new String[]{"blitSprite", "GUI sprites are the responsive card's business"},
            new String[]{"ResourceLocation", "naming a texture at all is the thing being avoided"},
            new String[]{"BACKGROUND_LOCATION", "the tiled dirt is exactly what MINIMAL removes"},
            new String[]{"WIDGETS", "widgets.png button art is the responsive card's business"},
            new String[]{"fillGradient", "gradients are decoration, not information"},
            new String[]{"renderEntityInInventory", "no entity rendering inside a GUI scissor"});

    @Test
    void theMinimalSkinNamesNoTextureOrEntityRender() throws IOException {
        String source = stripComments(Files.readString(MINIMAL_SKIN, StandardCharsets.UTF_8));
        List<String> offenders = new ArrayList<>();
        for (String[] rule : BANNED) {
            if (source.contains(rule[0])) {
                offenders.add(rule[0] + " (" + rule[1] + ")");
            }
        }
        assertTrue(offenders.isEmpty(),
                () -> "MinimalDialogueSkin is no longer texture-free:\n  "
                        + String.join("\n  ", offenders));
    }

    @Test
    void theMinimalSkinDrawsOnlyThroughFillsAndOutlines() throws IOException {
        String source = stripComments(Files.readString(MINIMAL_SKIN, StandardCharsets.UTF_8));
        List<String> offenders = new ArrayList<>();
        for (String line : source.split("\n")) {
            String trimmed = line.trim();
            if (!trimmed.contains("graphics.")) {
                continue;
            }
            if (!trimmed.contains("graphics.fill(")) {
                offenders.add(trimmed);
            }
        }
        assertTrue(offenders.isEmpty(),
                () -> "the only GuiGraphics call a flat skin needs is fill:\n  "
                        + String.join("\n  ", offenders));
    }

    /** Drops comments, so a rule fires on code and not on the prose explaining what is forbidden. */
    private static String stripComments(String source) {
        return source
                .replaceAll("(?s)/\\*.*?\\*/", " ")
                .replaceAll("(?m)//.*$", " ");
    }
}
