package dev.otectus.mcaconversations.progress;

import dev.otectus.mcaconversations.conversation.DepthClass;

/**
 * Everything outside the ledger that a heart change depends on, passed in explicitly so
 * {@link ProgressStore#applyAffection} stays a pure function of its arguments and the stored record.
 *
 * @param budget            the depth class whose per-conversation caps apply
 * @param sessionPositive   hearts already gained in this conversation (non-negative)
 * @param sessionNegative   hearts already lost in this conversation (non-negative)
 * @param dailyPositiveCap  config ceiling on hearts gained per villager, per player, per MC day
 * @param dailyNegativeCap  config floor on hearts lost, as a positive number
 * @param strongerNegatives config toggle doubling authored negatives before the multiplier
 * @param multiplier        global conversation heart multiplier
 * @param transactionId     idempotency key; a repeat of the same id is refused
 * @param now               current game time, which fixes the MC day for the daily counters
 */
public record AffectionContext(DepthClass budget,
                               int sessionPositive,
                               int sessionNegative,
                               int dailyPositiveCap,
                               int dailyNegativeCap,
                               boolean strongerNegatives,
                               double multiplier,
                               String transactionId,
                               long now) {
}
