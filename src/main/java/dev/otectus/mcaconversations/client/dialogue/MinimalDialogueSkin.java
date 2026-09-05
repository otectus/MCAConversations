package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.client.gui.GuiGraphics;

/**
 * Paints the same card out of flat rectangles and nothing else.
 *
 * <h2>Why texture-free rather than recoloured</h2>
 *
 * <p>MINIMAL is not a palette of the responsive card. It exists for the players who find the
 * illustrated card too loud, too large or too expensive: no dirt tiling, no nine-sliced button per
 * row, no entity in a scissor, no gradients. What survives is the part that is actually the feature
 * -- the numbering, the paging, the focus model and the hitboxes, all of which come from the layout
 * and are therefore identical to the responsive card's. A lint test keeps this file free of
 * {@code blit}, sprites and {@code ResourceLocation} so the promise cannot quietly lapse.
 *
 * <h2>Colour is never the only signal</h2>
 *
 * <p>A focused row gets a fill, and a locked row gets that fill plus a white outline. A player who
 * cannot separate the two greys still sees a border appear when the selection is committed, which is
 * the distinction the accessibility requirements ask for.
 */
public final class MinimalDialogueSkin implements DialogueSkin {

    /** The one instance. Stateless, like the responsive skin; an object only so a style can pick it. */
    public static final DialogueSkin INSTANCE = new MinimalDialogueSkin();

    /** Dark neutral backing at roughly three-quarters opacity, and one muted grey border pixel. */
    private static final int BACKING = 0xC0101010;
    private static final int BORDER = 0xFF6E6E6E;

    /** Focus: a subtle fill. Lock: the same fill plus a white outline, so the two never share a cue. */
    private static final int FOCUS_FILL = 0x30C8C8C8;
    private static final int FOCUS_OUTLINE = 0xFFA0A0A0;
    private static final int LOCK_FILL = 0x40FFFFFF;
    private static final int LOCK_OUTLINE = 0xFFFFFFFF;

    /** A flat scrollbar. Overflow support is not a decoration, so it stays. */
    private static final int SCROLL_TRACK = 0x40000000;
    private static final int SCROLL_THUMB = 0xFFA0A0A0;

    /** Page controls: the border grey at rest, the focus grey hovered, half alpha when unavailable. */
    private static final int CONTROL_EDGE = BORDER;
    private static final int CONTROL_EDGE_HOVERED = 0xFFA0A0A0;
    private static final float DISABLED_ALPHA = 0.5F;

    private MinimalDialogueSkin() {
    }

    /**
     * One fill and one border. {@code listBody} is deliberately ignored: a recessed list area is the
     * decorative depth this style is here to remove, and the rows are legible on the flat backing.
     */
    @Override
    public void panel(GuiGraphics graphics, DialogueChoiceLayout.Rect panel,
                      DialogueChoiceLayout.Rect listBody, float alpha) {
        DialogueCardSkin.fill(graphics, panel, ConversationPalette.withAlpha(BACKING, alpha));
        DialogueCardSkin.outline(graphics, panel, ConversationPalette.withAlpha(BORDER, alpha), 1);
    }

    /** Nothing at rest; a fill when focused; a fill and a white outline when locked. */
    @Override
    public void row(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
                    boolean focused, boolean locked) {
        if (!focused && !locked) {
            return;
        }
        DialogueCardSkin.fill(graphics, rect,
                ConversationPalette.withAlpha(locked ? LOCK_FILL : FOCUS_FILL, alpha));
        DialogueCardSkin.outline(graphics, rect,
                ConversationPalette.withAlpha(locked ? LOCK_OUTLINE : FOCUS_OUTLINE, alpha), 1);
    }

    /**
     * No badge art at all: the numeral is drawn by the renderer and stands on its own, which is what
     * lets the gutter shrink to the width of the digit rather than the width of a button face.
     */
    @Override
    public void badge(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
                      boolean highlighted) {
    }

    /**
     * A one-pixel box on exactly the rect the layout reserved. A plainer control must not be a
     * smaller one, so the geometry is untouched and only the edge colour changes; an unavailable
     * direction is muted rather than removed, and stays where the player last clicked it.
     */
    @Override
    public void control(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
                        boolean enabled, boolean hovered) {
        int edge = hovered ? CONTROL_EDGE_HOVERED : CONTROL_EDGE;
        DialogueCardSkin.outline(graphics, rect,
                ConversationPalette.withAlpha(edge, enabled ? alpha : alpha * DISABLED_ALPHA), 1);
    }

    /** MINIMAL shows no portrait, so there is no well to recess. */
    @Override
    public void portrait(GuiGraphics graphics, DialogueChoiceLayout.Rect frame, float alpha) {
    }

    /**
     * The same track and thumb arithmetic the responsive skin uses, painted as two rectangles. A
     * clipped answer must still be scrollable here; simplicity is about the graphics, not the
     * reading of long text.
     */
    @Override
    public void scrollbar(GuiGraphics graphics, DialogueChoiceLayout.Rect row,
                          int firstLine, int visibleLines, int totalLines, float alpha) {
        if (visibleLines <= 0 || totalLines <= visibleLines) {
            return;
        }
        int trackX = row.x() + row.width() - DialogueCardSkin.SCROLLBAR_WIDTH - 1;
        int top = row.y() + 1;
        int bottom = row.y() + row.height() - 1;
        int height = bottom - top;
        if (height <= 0) {
            return;
        }
        graphics.fill(trackX, top, trackX + DialogueCardSkin.SCROLLBAR_WIDTH, bottom,
                ConversationPalette.withAlpha(SCROLL_TRACK, alpha));
        int thumbHeight = Math.max(4, Math.min(height, height * visibleLines / totalLines));
        int maxScroll = totalLines - visibleLines;
        int travel = height - thumbHeight;
        int scrolled = Math.max(0, Math.min(maxScroll, firstLine));
        int thumbY = top + Math.round(travel * (scrolled / (float) maxScroll));
        graphics.fill(trackX, thumbY, trackX + DialogueCardSkin.SCROLLBAR_WIDTH, thumbY + thumbHeight,
                ConversationPalette.withAlpha(SCROLL_THUMB, alpha));
    }

    /** The nine numerals, cached per skin so no frame concatenates one. */
    private static final String[] NUMERALS =
            {"1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9."};

    /**
     * The same {@code "N."} the responsive card draws. The recommended minimal form is the numeral
     * and a full stop, which is what the card already used; there is no second typography to learn.
     */
    @Override
    public String badgeLabel(int visibleNumber) {
        return visibleNumber >= 1 && visibleNumber <= NUMERALS.length
                ? NUMERALS[visibleNumber - 1] : visibleNumber + ".";
    }
}
