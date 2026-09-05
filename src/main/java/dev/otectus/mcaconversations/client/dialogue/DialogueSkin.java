package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import net.minecraft.client.gui.GuiGraphics;

/**
 * The visual primitives of a dialogue card, so that the renderer never learns whether the panel it
 * asked for is dirt-textured or a flat fill.
 *
 * <p>Hitboxes come from {@link DialogueChoiceLayout} and not from whatever was painted, so a skin can
 * only change how a surface looks, never where it is or how large it is. That is what keeps two
 * presentations honest about being the same interaction system.
 */
public interface DialogueSkin {

    /**
     * The panel body, its list body, and the border between both and the world. {@code listBody} may
     * be null on a card with no rows to recess, and a skin may ignore it entirely.
     */
    void panel(GuiGraphics graphics, DialogueChoiceLayout.Rect panel,
               DialogueChoiceLayout.Rect listBody, float alpha);

    /** One choice row, in its resting, focused or locked state. */
    void row(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
             boolean focused, boolean locked);

    /** The surface behind a row's numeral. A skin with no badge art draws nothing. */
    void badge(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha, boolean highlighted);

    /** A previous/next page control, disabled when the direction is unavailable. */
    void control(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, float alpha,
                 boolean enabled, boolean hovered);

    /** The well the speaking villager is drawn into, on the styles that show one. */
    void portrait(GuiGraphics graphics, DialogueChoiceLayout.Rect frame, float alpha);

    /** The scrollbar of a row whose answer does not fit, drawn only when there is travel. */
    void scrollbar(GuiGraphics graphics, DialogueChoiceLayout.Rect row,
                   int firstLine, int visibleLines, int totalLines, float alpha);

    /**
     * The numeral drawn on row {@code visibleNumber}. It is the skin's business because the numeral
     * and the surface behind it are one design decision, and because the strings are then cached per
     * skin instead of being concatenated once per row per frame.
     */
    String badgeLabel(int visibleNumber);

    /**
     * The skin for a style. {@code MCA_ORIGINAL} answers the responsive skin rather than throwing:
     * the renderer's {@code customRenderer} gate means it is never consulted, and a lookup on a
     * render path is the wrong place to discover that.
     */
    static DialogueSkin of(DialogueMenuStyle style) {
        return switch (style == null ? DialogueMenuStyle.RESPONSIVE : style) {
            case MINIMAL -> MinimalDialogueSkin.INSTANCE;
            case RESPONSIVE, MCA_ORIGINAL -> DialogueCardSkin.INSTANCE;
        };
    }
}
