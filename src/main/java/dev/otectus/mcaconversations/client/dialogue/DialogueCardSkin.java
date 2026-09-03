package dev.otectus.mcaconversations.client.dialogue;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Paints every surface of the response card from Minecraft's own GUI art.
 *
 * <h2>Why the renderer does not choose</h2>
 *
 * <p>The hitboxes are computed from the layout and not from whatever was drawn, so keeping the
 * painting here means the renderer has one way to ask for a panel and cannot grow a surface the
 * layout does not know about.
 *
 * <h2>Vanilla textures, not synthetic ones</h2>
 *
 * <p>The panel is the options-screen dirt and the two things that genuinely are buttons -- the number
 * badge and the page controls -- are drawn from vanilla's button sprites. A resource pack that
 * reskins Minecraft's menus therefore reskins this card, and the card has no colour scheme of its own
 * to fall out of step with the rest of the game. Everything else is a fill, exactly as a vanilla list
 * screen draws its selection frames and scrollbar.
 *
 * <h2>Tint discipline</h2>
 *
 * <p>{@code GuiGraphics.setColor} is global state. Every tinted blit here resets it to white in a
 * {@code finally}, or MCA's screen and the entity portrait drawn over this card inherit the tint.
 */
public final class DialogueCardSkin {

    private static final ResourceLocation DIRT = Screen.MENU_BACKGROUND;

    /** Edge length of one dirt tile in GUI units, matching {@code Screen.renderMenuBackgroundTexture}. */
    private static final int DIRT_TILE = 32;

    /** The button strip in {@code widgets.png}: 200x20 faces stacked from v=46. */
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_CORNER = 3;
    private static final int BUTTON_V_DISABLED = 46;
    private static final int BUTTON_V_REST = 66;
    private static final int BUTTON_V_HOVERED = 86;

    /** The button faces those v offsets stand for, as the GUI sprites that replaced the strip. */
    private static final ResourceLocation BUTTON_SPRITE_DISABLED =
            ResourceLocation.withDefaultNamespace("widget/button_disabled");
    private static final ResourceLocation BUTTON_SPRITE_REST =
            ResourceLocation.withDefaultNamespace("widget/button");
    private static final ResourceLocation BUTTON_SPRITE_HOVERED =
            ResourceLocation.withDefaultNamespace("widget/button_highlighted");

    /** Width of a vanilla list scrollbar, and the gutter the renderer must keep clear for it. */
    public static final int SCROLLBAR_WIDTH = 6;

    private DialogueCardSkin() {
    }

    // ---------------------------------------------------------------------------------------------
    // Card surfaces
    // ---------------------------------------------------------------------------------------------

    /**
     * The panel body, its darker list body, and the one-pixel border that separates both from the
     * world behind them. {@code listBody} may be null on a card with no rows to recess.
     */
    public static void panel(GuiGraphics graphics, DialogueChoiceLayout.Rect panel,
                             DialogueChoiceLayout.Rect listBody, float alpha) {
        dirt(graphics, panel, ConversationPalette.PANEL_TINT, alpha);
        if (listBody != null && listBody.width() > 0 && listBody.height() > 0) {
            dirt(graphics, listBody, ConversationPalette.LIST_TINT, alpha);
            // The two shading bands vanilla puts at the ends of a list, standing in for the divider
            // rule and the footer rule the card used to draw.
            int shade = ConversationPalette.withAlpha(ConversationPalette.SHADE, alpha);
            int clear = ConversationPalette.SHADE_CLEAR;
            int right = listBody.x() + listBody.width();
            int bottom = listBody.y() + listBody.height();
            graphics.fillGradient(listBody.x(), listBody.y(), right, listBody.y() + 4, shade, clear);
            graphics.fillGradient(listBody.x(), bottom - 4, right, bottom, clear, shade);
        }
        outline(graphics, panel, ConversationPalette.withAlpha(ConversationPalette.PANEL_EDGE, alpha), 1);
    }

    /**
     * One choice row. A resting row paints nothing at all -- a vanilla list entry is text on the list
     * body -- and a focused or locked one gets vanilla's two-tone selection frame.
     */
    public static void row(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
                           boolean focused, boolean locked) {
        if (!focused && !locked) {
            return;
        }
        int frame = locked ? ConversationPalette.SELECTION_LOCKED : ConversationPalette.SELECTION_FOCUSED;
        fill(graphics, rect, ConversationPalette.withAlpha(frame, alpha));
        fill(graphics, rect.outset(-1, -1),
                ConversationPalette.withAlpha(ConversationPalette.SELECTION_INNER, alpha));
    }

    /** The number badge behind a row's numeral: a small vanilla button face, on every row. */
    public static void badge(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
                             boolean highlighted) {
        button(graphics, rect, alpha, highlighted ? BUTTON_V_HOVERED : BUTTON_V_REST);
    }

    /** A previous/next page button face, disabled when the direction is unavailable. */
    public static void control(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
                               boolean enabled, boolean hovered) {
        button(graphics, rect, alpha,
                !enabled ? BUTTON_V_DISABLED : hovered ? BUTTON_V_HOVERED : BUTTON_V_REST);
    }

    /** The recessed well the speaking villager is drawn into. */
    public static void portrait(GuiGraphics graphics, DialogueChoiceLayout.Rect frame, float alpha) {
        fill(graphics, frame, ConversationPalette.withAlpha(ConversationPalette.WELL, alpha));
        outline(graphics, frame,
                ConversationPalette.withAlpha(ConversationPalette.WELL_EDGE, alpha), 1);
    }

    /**
     * A vanilla list scrollbar down the right edge of a row whose answer does not fit.
     *
     * <p>Drawn only when there is something to scroll, so a row that happens to fit keeps its full
     * reading width.
     */
    public static void scrollbar(GuiGraphics graphics, DialogueChoiceLayout.Rect row,
                                 int firstLine, int visibleLines, int totalLines, float alpha) {
        if (visibleLines <= 0 || totalLines <= visibleLines) {
            return;
        }
        int trackX = row.x() + row.width() - SCROLLBAR_WIDTH - 1;
        int top = row.y() + 1;
        int bottom = row.y() + row.height() - 1;
        int height = bottom - top;
        if (height <= 0) {
            return;
        }
        graphics.fill(trackX, top, trackX + SCROLLBAR_WIDTH, bottom,
                ConversationPalette.withAlpha(ConversationPalette.SCROLL_TRACK, alpha));
        int thumbHeight = Math.max(4, Math.min(height, height * visibleLines / totalLines));
        int maxScroll = totalLines - visibleLines;
        int travel = height - thumbHeight;
        int scrolled = Math.max(0, Math.min(maxScroll, firstLine));
        int thumbY = top + Math.round(travel * (scrolled / (float) maxScroll));
        graphics.fill(trackX, thumbY, trackX + SCROLLBAR_WIDTH, thumbY + thumbHeight,
                ConversationPalette.withAlpha(ConversationPalette.SCROLL_THUMB, alpha));
        graphics.fill(trackX, thumbY, trackX + SCROLLBAR_WIDTH - 1, thumbY + thumbHeight - 1,
                ConversationPalette.withAlpha(ConversationPalette.SCROLL_THUMB_FACE, alpha));
    }

    // ---------------------------------------------------------------------------------------------
    // Primitives
    // ---------------------------------------------------------------------------------------------

    public static void fill(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, int color) {
        graphics.fill(rect.x(), rect.y(), rect.x() + rect.width(), rect.y() + rect.height(), color);
    }

    public static void outline(GuiGraphics graphics, DialogueChoiceLayout.Rect rect,
                               int color, int thickness) {
        graphics.fill(rect.x(), rect.y(), rect.x() + rect.width(), rect.y() + thickness, color);
        graphics.fill(rect.x(), rect.y() + rect.height() - thickness,
                rect.x() + rect.width(), rect.y() + rect.height(), color);
        graphics.fill(rect.x(), rect.y(), rect.x() + thickness, rect.y() + rect.height(), color);
        graphics.fill(rect.x() + rect.width() - thickness, rect.y(),
                rect.x() + rect.width(), rect.y() + rect.height(), color);
    }

    /** Tiles the options-screen dirt over {@code rect}, tinted and faded, then restores the tint. */
    private static void dirt(GuiGraphics graphics, DialogueChoiceLayout.Rect rect,
                             float tint, float alpha) {
        if (rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        try {
            graphics.setColor(tint, tint, tint, Math.max(0.0F, Math.min(1.0F, alpha)));
            // Sampled one texel per GUI unit so the tiling matches vanilla's dirt at every GUI scale.
            graphics.blit(DIRT, rect.x(), rect.y(), rect.width(), rect.height(), 0.0F, 0.0F,
                    rect.width(), rect.height(), DIRT_TILE, DIRT_TILE);
        } finally {
            graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
        }
    }

    private static void button(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
                               int v) {
        blitNineSliced(graphics, buttonSprite(v), rect, alpha);
    }

    /** The sprite standing in for one of the strip's v offsets. */
    private static ResourceLocation buttonSprite(int v) {
        if (v == BUTTON_V_DISABLED) {
            return BUTTON_SPRITE_DISABLED;
        }
        return v == BUTTON_V_HOVERED ? BUTTON_SPRITE_HOVERED : BUTTON_SPRITE_REST;
    }

    // ---------------------------------------------------------------------------------------------
    // Nine-slice
    // ---------------------------------------------------------------------------------------------

    /** One blit of a nine-slice: where it lands, and which part of the source it comes from. */
    public record Patch(int x, int y, int width, int height,
                        int u, int v, int uWidth, int vHeight) {
    }

    /**
     * The blits that stretch the source rect {@code (u0, v0, sourceWidth, sourceHeight)} over
     * {@code rect}, corners unscaled.
     *
     * <p>Pure, so the tiling can be checked without a render context. A nine-slice that is one pixel
     * out leaves a seam or a doubled edge, which is close to invisible on a dark panel and is exactly
     * the sort of thing that survives review.
     */
    public static List<Patch> patches(int u0, int v0, int sourceWidth, int sourceHeight, int corner,
                                      DialogueChoiceLayout.Rect rect) {
        List<Patch> patches = new ArrayList<>(9);
        int width = rect.width();
        int height = rect.height();
        if (width <= 0 || height <= 0) {
            return patches;
        }
        int sourceMiddleWidth = sourceWidth - corner * 2;
        int sourceMiddleHeight = sourceHeight - corner * 2;
        // Shrink the drawn corner on a rect too small to hold two of them, so opposite corners never
        // overlap; the source corner stays put so the artwork is cropped rather than squashed.
        int c = Math.min(corner, Math.min(width, height) / 2);
        if (c <= 0) {
            // Smaller than a single corner in one axis: stretch the source's centre over the whole
            // rect. Corner artwork cannot be shown here, but the surface is still painted.
            patches.add(new Patch(rect.x(), rect.y(), width, height,
                    u0 + corner, v0 + corner, sourceMiddleWidth, sourceMiddleHeight));
            return patches;
        }
        int middleWidth = width - c * 2;
        int middleHeight = height - c * 2;
        int x = rect.x();
        int y = rect.y();
        int right = x + width - c;
        int bottom = y + height - c;
        int farU = u0 + sourceWidth - corner;
        int farV = v0 + sourceHeight - corner;

        patches.add(new Patch(x, y, c, c, u0, v0, corner, corner));
        patches.add(new Patch(right, y, c, c, farU, v0, corner, corner));
        patches.add(new Patch(x, bottom, c, c, u0, farV, corner, corner));
        patches.add(new Patch(right, bottom, c, c, farU, farV, corner, corner));
        if (middleWidth > 0) {
            patches.add(new Patch(x + c, y, middleWidth, c,
                    u0 + corner, v0, sourceMiddleWidth, corner));
            patches.add(new Patch(x + c, bottom, middleWidth, c,
                    u0 + corner, farV, sourceMiddleWidth, corner));
        }
        if (middleHeight > 0) {
            patches.add(new Patch(x, y + c, c, middleHeight,
                    u0, v0 + corner, corner, sourceMiddleHeight));
            patches.add(new Patch(right, y + c, c, middleHeight,
                    farU, v0 + corner, corner, sourceMiddleHeight));
        }
        if (middleWidth > 0 && middleHeight > 0) {
            patches.add(new Patch(x + c, y + c, middleWidth, middleHeight,
                    u0 + corner, v0 + corner, sourceMiddleWidth, sourceMiddleHeight));
        }
        return patches;
    }

    /**
     * Draws a source rect stretched to {@code rect}, keeping its corners unscaled.
     *
     * <p>The button strip the source rect used to name is a GUI sprite here, and a sprite carries its
     * own nine-slice in its mcmeta, so the nine explicit blits collapse to one. {@link #patches} is
     * kept and still tested: it is the description of the tiling this draws, and a nine-slice that is
     * one pixel out leaves a seam that is close to invisible on a dark panel.
     */
    private static void blitNineSliced(GuiGraphics graphics, ResourceLocation sprite,
                                       DialogueChoiceLayout.Rect rect, float alpha) {
        if (rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        try {
            graphics.setColor(1.0F, 1.0F, 1.0F, Math.max(0.0F, Math.min(1.0F, alpha)));
            graphics.blitSprite(sprite, rect.x(), rect.y(), rect.width(), rect.height());
        } finally {
            graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
        }
    }
}
