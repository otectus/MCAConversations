package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;

/**
 * What a dialogue style is capable of, as data rather than as a condition repeated down the renderer.
 *
 * <p>Without this, presentation policy arrives as {@code style != MINIMAL && style != MCA_ORIGINAL}
 * at a dozen call sites, and the day a fourth style appears every one of them is wrong in a different
 * way. A profile is resolved once per frame and threaded through, so a style either has a capability
 * everywhere or nowhere.
 *
 * <p>{@code numberColumnFloor} is the one layout-facing field. A style may draw less, but it may not
 * shrink the space a numeral and its control box need: a badge that is textured and a badge that is
 * bare still hold the same digit at the same font size.
 */
public record DialogueStyleProfile(boolean customRenderer, boolean portrait, boolean texturedBadges,
                                   boolean focusPopout, int numberColumnFloor) {

    /** The 1.5.1 card: everything on, and the numeral column vanilla's button face needs. */
    public static final DialogueStyleProfile RESPONSIVE =
            new DialogueStyleProfile(true, true, true, true, DialogueChoiceLayout.NUMBER_COLUMN);

    /** The same interaction system with flat graphics: no portrait, no badge art, no pop-out. */
    public static final DialogueStyleProfile MINIMAL =
            new DialogueStyleProfile(true, false, false, false, 12);

    /** MCA Reborn draws its own menu, so nothing else in this record is ever consulted. */
    public static final DialogueStyleProfile MCA_ORIGINAL =
            new DialogueStyleProfile(false, false, false, false, 0);

    /** Total over the enum: a style added without a profile is a compile error, not a blank card. */
    public static DialogueStyleProfile of(DialogueMenuStyle style) {
        return switch (style == null ? DialogueMenuStyle.RESPONSIVE : style) {
            case RESPONSIVE -> RESPONSIVE;
            case MINIMAL -> MINIMAL;
            case MCA_ORIGINAL -> MCA_ORIGINAL;
        };
    }
}
