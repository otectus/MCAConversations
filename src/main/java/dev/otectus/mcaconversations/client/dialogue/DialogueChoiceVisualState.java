package dev.otectus.mcaconversations.client.dialogue;

/** Screen-local, client-clocked animation state; it never decides offer or selection truth. */
public final class DialogueChoiceVisualState {

    private long tick;
    private long revision = -1L;
    private float enterAt;
    private int focusedIndex = -1;
    private int previousFocus = -1;
    private float focusAt;
    private int lockedIndex = -1;
    private float lockAt;
    private int page = -1;
    private float pageAt;

    public void tick() {
        tick++;
    }

    public float time(float partialTick) {
        return tick + Math.max(0.0F, Math.min(1.0F, partialTick));
    }

    public void observe(ClientChoiceState state, float partialTick) {
        float now = time(partialTick);
        long incoming = state.offer().map(ClientChoiceState.ClientChoiceOffer::revision).orElse(-1L);
        if (incoming != revision) {
            revision = incoming;
            enterAt = now;
            focusedIndex = -1;
            previousFocus = -1;
            lockedIndex = -1;
            page = state.page();
            pageAt = now;
        }
        if (state.focusedIndex() != focusedIndex) {
            previousFocus = focusedIndex;
            focusedIndex = state.focusedIndex();
            focusAt = now;
        }
        if (state.lockedIndex() != lockedIndex) {
            lockedIndex = state.lockedIndex();
            lockAt = now;
        }
        if (state.page() != page) {
            page = state.page();
            pageAt = now;
        }
    }

    public float cardProgress(float partialTick, ConversationMotionSpec spec) {
        return durationProgress(time(partialTick) - enterAt, spec.enterTicks());
    }

    public float rowEntryProgress(int visibleIndex, float partialTick, ConversationMotionSpec spec) {
        float elapsed = time(partialTick) - enterAt - visibleIndex * spec.rowStagger();
        return ConversationMotionSpec.easeOutCubic(durationProgress(elapsed, spec.rowEntryTicks()));
    }

    public float focusProgress(int absoluteIndex, float partialTick, ConversationMotionSpec spec) {
        if (spec.focusTicks() <= 0.0F) {
            return absoluteIndex == focusedIndex || absoluteIndex == lockedIndex ? 1.0F : 0.0F;
        }
        float progress = ConversationMotionSpec.easeOutCubic(
                durationProgress(time(partialTick) - focusAt, spec.focusTicks()));
        if (absoluteIndex == focusedIndex || absoluteIndex == lockedIndex) {
            return progress;
        }
        if (absoluteIndex == previousFocus) {
            return 1.0F - ConversationMotionSpec.smoothStep(
                    durationProgress(time(partialTick) - focusAt, spec.focusExitTicks()));
        }
        return 0.0F;
    }

    /** How far the question has appeared, measured from the moment the card entered. */
    public float questionRevealProgress(float partialTick, float ticks) {
        return durationProgress(time(partialTick) - enterAt, ticks);
    }

    public float pageProgress(float partialTick, ConversationMotionSpec spec) {
        return ConversationMotionSpec.easeOutCubic(
                durationProgress(time(partialTick) - pageAt, spec.pageTicks()));
    }

    /** Progress of the outgoing card's fade, one at rest so a zeroed profile clears immediately. */
    public float exitProgress(float exitAt, float partialTick, ConversationMotionSpec spec) {
        return ConversationMotionSpec.smoothStep(
                durationProgress(time(partialTick) - exitAt, spec.exitTicks()));
    }

    /** Full-motion locked outset: press inward, then settle just proud of the resting row. */
    public float lockedOutset(float partialTick, ConversationMotionSpec spec) {
        float press = spec.selectionPressTicks();
        float settle = spec.selectionSettleTicks();
        if (lockedIndex < 0 || spec.focusOutset() <= 0 || (press <= 0.0F && settle <= 0.0F)) {
            return spec.focusOutset();
        }
        float elapsed = time(partialTick) - lockAt;
        if (elapsed <= press) {
            return spec.focusOutset()
                    - ConversationMotionSpec.smoothStep(durationProgress(elapsed, press))
                            * spec.selectionPressDepth();
        }
        return Math.max(0.0F, spec.focusOutset() - spec.selectionPressDepth())
                + ConversationMotionSpec.easeOutCubic(
                        durationProgress(elapsed - press, settle)) * spec.selectionSettleRise();
    }

    public void reset() {
        revision = -1L;
        focusedIndex = -1;
        previousFocus = -1;
        lockedIndex = -1;
        page = -1;
    }

    private static float durationProgress(float elapsed, float duration) {
        return duration <= 0.0F ? 1.0F : ConversationMotionSpec.clamp01(elapsed / duration);
    }
}
