package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The nine-slice tiling covers its destination exactly, at every size the card asks for.
 *
 * <p>A seam is the classic nine-slice bug: one patch a pixel short leaves a hairline of whatever was
 * behind it, and one patch a pixel long double-draws a translucent edge into a darker line. On a dirt
 * panel over a Minecraft world both are almost impossible to see deliberately and obvious once
 * someone points at a screenshot. Checking the geometry is cheap; checking it by eye is not.
 */
class DialogueCardSkinTest {

    /** The button strip the badges and page controls are cut from: 200x20 at v=66, corner 3. */
    private static final int BUTTON_U = 0;
    private static final int BUTTON_V = 66;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_CORNER = 3;

    private static DialogueChoiceLayout.Rect rect(int width, int height) {
        return new DialogueChoiceLayout.Rect(7, 11, width, height);
    }

    private static void assertExactlyCovers(int u0, int v0, int sourceWidth, int sourceHeight,
                                            int corner, DialogueChoiceLayout.Rect target) {
        List<DialogueCardSkin.Patch> patches =
                DialogueCardSkin.patches(u0, v0, sourceWidth, sourceHeight, corner, target);
        assertFalse(patches.isEmpty(), "no patches for " + target.width() + "x" + target.height());

        boolean[][] painted = new boolean[target.height()][target.width()];
        for (DialogueCardSkin.Patch patch : patches) {
            for (int y = 0; y < patch.height(); y++) {
                for (int x = 0; x < patch.width(); x++) {
                    int localX = patch.x() - target.x() + x;
                    int localY = patch.y() - target.y() + y;
                    assertTrue(localX >= 0 && localX < target.width()
                                    && localY >= 0 && localY < target.height(),
                            "patch escapes the destination at " + localX + "," + localY);
                    assertFalse(painted[localY][localX],
                            "patches overlap at " + localX + "," + localY
                                    + " for " + target.width() + "x" + target.height());
                    painted[localY][localX] = true;
                }
            }
        }
        for (int y = 0; y < target.height(); y++) {
            for (int x = 0; x < target.width(); x++) {
                assertTrue(painted[y][x], "seam left unpainted at " + x + "," + y
                        + " for " + target.width() + "x" + target.height());
            }
        }
    }

    @Test
    void patchesTileEveryDestinationExactlyWithNoSeamOrOverlap() {
        for (int corner : new int[]{3, 4}) {
            for (int width = 1; width <= 64; width++) {
                for (int height = 1; height <= 64; height++) {
                    assertExactlyCovers(0, 0, 16, 16, corner, rect(width, height));
                }
            }
            // Sizes a real card reaches: a full-width panel and a tall multi-line row.
            assertExactlyCovers(0, 0, 16, 16, corner, rect(420, 226));
            assertExactlyCovers(0, 0, 16, 16, corner, rect(220, 18));
        }
    }

    /**
     * The button strip is far wider than anything the card draws from it, so opposite corners are
     * always at risk of meeting in the middle. An 18x18 page control and a 15x11 number badge are the
     * two real sizes, and both are narrower than two 200-pixel halves would ever be.
     */
    @Test
    void theButtonStripTilesShortTargetsWithoutOverlappingCorners() {
        assertExactlyCovers(BUTTON_U, BUTTON_V, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_CORNER,
                rect(18, 18));
        assertExactlyCovers(BUTTON_U, BUTTON_V, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_CORNER,
                rect(15, 11));
        for (int width = 1; width <= 40; width++) {
            for (int height = 1; height <= 24; height++) {
                assertExactlyCovers(BUTTON_U, BUTTON_V, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_CORNER,
                        rect(width, height));
            }
        }
    }

    @Test
    void everyPatchReadsFromInsideItsOwnSourceRect() {
        List<String> problems = new ArrayList<>();
        for (int v0 : new int[]{0, 46, 66, 86}) {
            for (int corner : new int[]{3, 4}) {
                for (DialogueCardSkin.Patch patch : DialogueCardSkin.patches(
                        BUTTON_U, v0, BUTTON_WIDTH, BUTTON_HEIGHT, corner, rect(120, 40))) {
                    if (patch.u() < BUTTON_U || patch.u() + patch.uWidth() > BUTTON_U + BUTTON_WIDTH
                            || patch.v() < v0 || patch.v() + patch.vHeight() > v0 + BUTTON_HEIGHT) {
                        problems.add("v " + v0 + " corner " + corner
                                + " samples outside its own source rect");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void cornersAreDrawnUnscaledWhenThereIsRoomForThem() {
        int corner = 4;
        List<DialogueCardSkin.Patch> patches =
                DialogueCardSkin.patches(0, 0, 16, 16, corner, rect(120, 40));
        List<DialogueCardSkin.Patch> corners = patches.stream()
                .filter(patch -> patch.uWidth() == corner && patch.vHeight() == corner).toList();
        assertEquals(4, corners.size(), "a large rect must have four unscaled corner patches");
        for (DialogueCardSkin.Patch patch : corners) {
            assertEquals(corner, patch.width(), "a corner must not stretch horizontally");
            assertEquals(corner, patch.height(), "a corner must not stretch vertically");
        }
    }

    @Test
    void aDegenerateRectProducesNothingRatherThanThrowing() {
        assertTrue(DialogueCardSkin.patches(0, 0, 16, 16, 4, rect(0, 20)).isEmpty());
        assertTrue(DialogueCardSkin.patches(0, 0, 16, 16, 4, rect(20, 0)).isEmpty());
        assertTrue(DialogueCardSkin.patches(0, 0, 16, 16, 4, rect(-3, -3)).isEmpty());
    }
}
