package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;

/**
 * Durations and geometry derived from the selected motion-accessibility profile.
 *
 * <p>Every animated duration on the response card lives here, so the configured profile is the
 * single authority over how long anything takes. A profile that sets a duration to zero must be
 * genuinely instantaneous: {@code motionMode = OFF} is documented as "changes visual state
 * immediately", and a duration left hard-coded in a caller silently breaks that promise for the
 * players most likely to depend on it.
 *
 * <p>The profile depends on the dialogue style as well as the mode, because "full motion" means
 * something different on a card built out of vanilla button faces and on a flat one. MINIMAL under
 * FULL keeps only what tells the player something -- a short entrance, a focus transition, a short
 * page move -- and drops every effect whose purpose is decoration: no row cascade, no focus pop-out,
 * no lift, and no selection press. A minimal interface that bounces is not a minimal interface.
 */
public record ConversationMotionSpec(
        McaConversationsConfig.MotionMode mode,
        float enterTicks,
        float rowEntryTicks,
        float focusTicks,
        float focusExitTicks,
        float selectionPressTicks,
        float selectionSettleTicks,
        float pageTicks,
        float exitTicks,
        int enterDistance,
        int focusOutset,
        int focusLift,
        int rowEntryDistance,
        int pageDistance,
        float rowStagger,
        float selectionPressDepth,
        float selectionSettleRise
) {
    /** The 1.5.1 responsive card, unchanged: these numbers are what players already have. */
    private static final ConversationMotionSpec FULL = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.FULL,
            4.0F, 3.0F, 2.5F, 2.0F, 1.5F, 2.0F, 3.0F, 2.0F,
            4, 4, 1, 3, 4, 0.35F, 3.0F, 2.0F);

    /** Restrained full motion for the flat card: transitions, but no movement worth watching. */
    private static final ConversationMotionSpec MINIMAL_FULL = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.FULL,
            3.0F, 0.0F, 2.0F, 1.5F, 0.0F, 0.0F, 2.0F, 2.0F,
            2, 0, 0, 0, 2, 0.0F, 0.0F, 0.0F);

    /** Fades only: the card still resolves over a few ticks, but nothing translates or cascades. */
    private static final ConversationMotionSpec REDUCED = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.REDUCED,
            3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F,
            0, 0, 0, 0, 0, 0.0F, 0.0F, 0.0F);

    private static final ConversationMotionSpec OFF = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.OFF,
            0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
            0, 0, 0, 0, 0, 0.0F, 0.0F, 0.0F);

    /**
     * The profile for one mode and one style. REDUCED and OFF are shared: a player who asked for
     * fades only, or for nothing at all, asked the same question of both cards.
     */
    public static ConversationMotionSpec of(McaConversationsConfig.MotionMode mode,
                                            DialogueMenuStyle style) {
        return switch (mode) {
            case FULL -> style == DialogueMenuStyle.MINIMAL ? MINIMAL_FULL : FULL;
            case REDUCED -> REDUCED;
            case OFF -> OFF;
        };
    }

    /** The configured profile for {@code style}, resolved once per frame by the renderer. */
    public static ConversationMotionSpec current(DialogueMenuStyle style) {
        return of(ClientChoiceController.motionMode(), style);
    }

    /** True when this profile animates nothing at all, so callers can skip interpolation entirely. */
    public boolean instant() {
        return enterTicks <= 0.0F && rowEntryTicks <= 0.0F && focusTicks <= 0.0F
                && focusExitTicks <= 0.0F && selectionPressTicks <= 0.0F
                && selectionSettleTicks <= 0.0F && pageTicks <= 0.0F && exitTicks <= 0.0F;
    }

    public static float clamp01(float value) {
        return Math.max(0.0F, Math.min(1.0F, value));
    }

    public static float easeOutCubic(float value) {
        float u = 1.0F - clamp01(value);
        return 1.0F - u * u * u;
    }

    public static float smoothStep(float value) {
        float t = clamp01(value);
        return t * t * (3.0F - 2.0F * t);
    }
}
