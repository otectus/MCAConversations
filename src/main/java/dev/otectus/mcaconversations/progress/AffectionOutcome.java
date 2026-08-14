package dev.otectus.mcaconversations.progress;

/**
 * What actually happened to a requested heart change, in enough detail for debug logging, chat heart
 * feedback, and tests to tell the guards apart.
 *
 * @param authored the delta the datapack asked for
 * @param scaled   after the stronger-negatives toggle, the multiplier and the replay policy
 * @param granted  after both budgets — the number the player really receives
 * @param reason   why {@code granted} differs from {@code authored}, or {@link Reason#APPLIED}
 */
public record AffectionOutcome(int authored, int scaled, int granted, Reason reason) {

    public enum Reason {
        /** The full scaled delta went through. */
        APPLIED,
        /** Nothing was applied because this transaction had already been applied (duplicate packet). */
        DUPLICATE,
        /** The replay policy zeroed it: a same-day repeat, or a once-only decision that already fired. */
        REPEAT,
        /** The per-conversation budget for this depth class was exhausted. */
        CONVERSATION_BUDGET,
        /** The per-villager, per-player daily budget was exhausted. */
        DAILY_BUDGET,
        /** The authored delta was zero, or the multiplier scaled it to zero. */
        ZERO
    }

    public static AffectionOutcome none(int authored, Reason reason) {
        return new AffectionOutcome(authored, 0, 0, reason);
    }

    /** True when the player's hearts actually moved. */
    public boolean applied() {
        return granted != 0;
    }
}
