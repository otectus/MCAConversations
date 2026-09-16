package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.ChatFormatting;

/**
 * The fixed colours the response card paints with.
 *
 * <p>Every value here is a vanilla one, taken from the screens the card is meant to be mistaken for:
 * the options list, the controls list, and the widget strip. There are deliberately no presets and no
 * opacity slider. The card's surfaces are Minecraft's own textures, so a player who wants a different
 * look installs a resource pack and gets one that matches the rest of their menus, which is a better
 * answer than three hand-tuned palettes that match nothing.
 *
 * <p>Imports nothing from the client render stack, so the values can be asserted in a plain unit test.
 */
public final class ConversationPalette {

    private ConversationPalette() {
    }

    /** Body text on the panel backing, always drawn with a shadow. */
    public static final int TEXT = 0xFFFFFFFF;

    /** Hints, page counters and disabled glyphs: vanilla's secondary label grey. */
    public static final int TEXT_MUTED = 0xFFA0A0A0;

    /** Outer frame of a pointer-focused row, matching an unfocused vanilla list selection. */
    public static final int SELECTION_FOCUSED = 0xFF808080;

    /** Outer frame of the locked row, matching a focused vanilla list selection. */
    public static final int SELECTION_LOCKED = 0xFFFFFFFF;

    /** The black interior a vanilla selection frame is drawn around. */
    public static final int SELECTION_INNER = 0xFF000000;

    /** One-pixel border around the whole panel. */
    public static final int PANEL_EDGE = 0xFF000000;

    /** The opaque end of the two shading gradients that cap the list body. */
    public static final int SHADE = 0xFF000000;

    /** The transparent end of those gradients. */
    public static final int SHADE_CLEAR = 0x00000000;

    /** Scrollbar track, thumb, and the thumb's lighter inner face, as vanilla draws them. */
    public static final int SCROLL_TRACK = 0xFF000000;
    public static final int SCROLL_THUMB = 0xFF808080;
    public static final int SCROLL_THUMB_FACE = 0xFFC0C0C0;

    /** The portrait well: a black recess with a grey rim. */
    public static final int WELL = 0xFF000000;
    public static final int WELL_EDGE = 0xFF808080;

    /**
     * The speaking villager's name. Yellow rather than an invented gold, so the label reads as the
     * same emphasis Minecraft itself uses and follows a resource pack that redefines the format.
     */
    public static final int SPEAKER_NAME = 0xFF000000
            | (ChatFormatting.YELLOW.getColor() == null ? 0xFFFF55 : ChatFormatting.YELLOW.getColor());

    /**
     * Flat backing for the responsive card body. 1.20.1 has no equivalent of the 1.21 menu texture
     * the NeoForge build tints, so the body is a translucent dark fill that lands on the same
     * darkness the tinted texture averages to, and the world stays visible through it.
     */
    public static final int PANEL_BACKING = 0xB0202020;

    /** Darker recess behind the answer list, the same step down the list tint gives the texture. */
    public static final int LIST_BACKING = 0xCC0E0E0E;

    /** Scales an ARGB colour's existing alpha by {@code alpha}, never brightening it. */
    public static int withAlpha(int color, float alpha) {
        int original = color >>> 24;
        int scaled = Math.round(Math.max(0.0F, Math.min(1.0F, alpha)) * original);
        return (scaled << 24) | (color & 0x00FFFFFF);
    }
}
