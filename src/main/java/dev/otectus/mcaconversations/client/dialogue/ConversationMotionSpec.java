package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig;

/**
 * Durations and geometry derived from the selected motion-accessibility profile.
 *
 * <p>Every animated duration on the response card lives here, so the configured profile is the
 * single authority over how long anything takes. A profile that sets a duration to zero must be
 * genuinely instantaneous: {@code motionMode = OFF} is documented as "changes visual state
 * immediately", and a duration left hard-coded in a caller silently breaks that promise for the
 * players most likely to depend on it.
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
        float rowStagger
) {
    private static final ConversationMotionSpec FULL = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.FULL,
            4.0F, 3.0F, 2.5F, 2.0F, 1.5F, 2.0F, 3.0F, 2.0F,
            4, 4, 1, 3, 4, 0.35F);

    /** Fades only: the card still resolves over a few ticks, but nothing translates or cascades. */
    private static final ConversationMotionSpec REDUCED = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.REDUCED,
            3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F,
            0, 0, 0, 0, 0, 0.0F);

    private static final ConversationMotionSpec OFF = new ConversationMotionSpec(
            McaConversationsConfig.MotionMode.OFF,
            0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
            0, 0, 0, 0, 0, 0.0F);

    public static ConversationMotionSpec current() {
        return switch (ClientChoiceController.motionMode()) {
            case FULL -> FULL;
            case REDUCED -> REDUCED;
            case OFF -> OFF;
        };
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
