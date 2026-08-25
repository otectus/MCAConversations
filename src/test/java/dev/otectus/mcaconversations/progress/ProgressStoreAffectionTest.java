package dev.otectus.mcaconversations.progress;

import dev.otectus.mcaconversations.conversation.DepthClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The guard chain end to end, against a real store: duplicate packets, repeat diminishing, both
 * budgets, day rollover, and the rule that a refused payout never consumes budget (plan §13.5).
 */
class ProgressStoreAffectionTest {

    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("villager".getBytes());
    private static final UUID PLAYER = UUID.nameUUIDFromBytes("player".getBytes());

    private static AffectionApply decision(String id, int delta, ReplayPolicy policy) {
        return new AffectionApply(id, delta, Optional.empty(), policy);
    }

    private static AffectionContext at(long now, String transaction, DepthClass budget,
                                       int sessionPositive, int sessionNegative) {
        return new AffectionContext(budget, sessionPositive, sessionNegative, 8, 10, false, 1.0,
                transaction, now);
    }

    @Test
    @DisplayName("a straightforward grant applies in full and reports APPLIED")
    void appliesInFull() {
        ProgressStore store = new ProgressStore();
        AffectionOutcome outcome = store.applyAffection(VILLAGER, PLAYER,
                decision("day.rough.empathize", 2, ReplayPolicy.DAILY_REPEAT), at(100, "t1", DepthClass.QUICK, 0, 0));
        assertEquals(2, outcome.granted());
        assertEquals(AffectionOutcome.Reason.APPLIED, outcome.reason());
        assertTrue(outcome.applied());
    }

    @Test
    @DisplayName("the same transaction id applied twice grants nothing the second time")
    void duplicatePacketGrantsNothing() {
        ProgressStore store = new ProgressStore();
        AffectionApply directive = decision("day.rough.empathize", 2, ReplayPolicy.DAILY_REPEAT);
        store.applyAffection(VILLAGER, PLAYER, directive, at(100, "same", DepthClass.QUICK, 0, 0));
        AffectionOutcome second = store.applyAffection(VILLAGER, PLAYER, directive,
                at(100, "same", DepthClass.QUICK, 2, 0));
        assertEquals(0, second.granted());
        assertEquals(AffectionOutcome.Reason.DUPLICATE, second.reason());
    }

    @Test
    @DisplayName("repeating a decision the same day pays full, half, then nothing")
    void repeatDiminishesWithinTheDay() {
        ProgressStore store = new ProgressStore();
        AffectionApply directive = decision("day.good.celebrate", 2, ReplayPolicy.DAILY_REPEAT);
        assertEquals(2, store.applyAffection(VILLAGER, PLAYER, directive,
                at(100, "a", DepthClass.DEEP, 0, 0)).granted());
        assertEquals(1, store.applyAffection(VILLAGER, PLAYER, directive,
                at(120, "b", DepthClass.DEEP, 0, 0)).granted());
        AffectionOutcome third = store.applyAffection(VILLAGER, PLAYER, directive,
                at(140, "c", DepthClass.DEEP, 0, 0));
        assertEquals(0, third.granted());
        assertEquals(AffectionOutcome.Reason.REPEAT, third.reason());
    }

    @Test
    @DisplayName("the counters roll over with the MC day")
    void dayRollover() {
        ProgressStore store = new ProgressStore();
        AffectionApply directive = decision("day.good.celebrate", 2, ReplayPolicy.ONCE_PER_DAY);
        assertEquals(2, store.applyAffection(VILLAGER, PLAYER, directive,
                at(100, "a", DepthClass.DEEP, 0, 0)).granted());
        assertEquals(0, store.applyAffection(VILLAGER, PLAYER, directive,
                at(200, "b", DepthClass.DEEP, 0, 0)).granted());
        assertEquals(2, store.applyAffection(VILLAGER, PLAYER, directive,
                at(24_100, "c", DepthClass.DEEP, 0, 0)).granted());
    }

    @Test
    @DisplayName("a once-only decision never fires twice, even on a later day")
    void onceIsForever() {
        ProgressStore store = new ProgressStore();
        AffectionApply directive = decision("fears.first.revelation", 5, ReplayPolicy.ONCE);
        assertEquals(5, store.applyAffection(VILLAGER, PLAYER, directive,
                at(100, "a", DepthClass.DEEP, 0, 0)).granted());
        assertEquals(0, store.applyAffection(VILLAGER, PLAYER, directive,
                at(500_000, "b", DepthClass.DEEP, 0, 0)).granted());
    }

    @Test
    @DisplayName("the per-conversation budget clamps a grant and reports why")
    void conversationBudgetClamps() {
        ProgressStore store = new ProgressStore();
        // Quick allows +2 per conversation; 2 are already spent in this exchange.
        AffectionOutcome outcome = store.applyAffection(VILLAGER, PLAYER,
                decision("day.rough.help", 2, ReplayPolicy.DAILY_REPEAT),
                at(100, "t", DepthClass.QUICK, 2, 0));
        assertEquals(0, outcome.granted());
        assertEquals(AffectionOutcome.Reason.CONVERSATION_BUDGET, outcome.reason());
    }

    @Test
    @DisplayName("the daily budget clamps across separate conversations")
    void dailyBudgetClamps() {
        ProgressStore store = new ProgressStore();
        // Eight distinct decisions worth +2 each against a daily cap of 8.
        int total = 0;
        for (int i = 0; i < 8; i++) {
            total += store.applyAffection(VILLAGER, PLAYER,
                    decision("day.topic" + i + ".warm", 2, ReplayPolicy.DAILY_REPEAT),
                    at(100 + i, "t" + i, DepthClass.DEEP, 0, 0)).granted();
        }
        assertEquals(8, total);
        AffectionOutcome overflow = store.applyAffection(VILLAGER, PLAYER,
                decision("day.topic99.warm", 2, ReplayPolicy.DAILY_REPEAT),
                at(200, "t99", DepthClass.DEEP, 0, 0));
        assertEquals(0, overflow.granted());
        assertEquals(AffectionOutcome.Reason.DAILY_BUDGET, overflow.reason());
    }

    @Test
    @DisplayName("spending the negative budget never unlocks extra positive capacity")
    void antagonisingDoesNotBuyGoodwill() {
        ProgressStore store = new ProgressStore();
        for (int i = 0; i < 5; i++) {
            store.applyAffection(VILLAGER, PLAYER,
                    decision("day.rough.mock" + i, -2, ReplayPolicy.DAILY_REPEAT),
                    at(100 + i, "n" + i, DepthClass.DEEP, 0, 0));
        }
        int gained = 0;
        for (int i = 0; i < 6; i++) {
            gained += store.applyAffection(VILLAGER, PLAYER,
                    decision("day.warm" + i, 2, ReplayPolicy.DAILY_REPEAT),
                    at(300 + i, "p" + i, DepthClass.DEEP, 0, 0)).granted();
        }
        assertEquals(8, gained, "the daily positive cap is 8 no matter how much was lost first");
    }

    @Test
    @DisplayName("a payout refused by the replay policy does not consume any budget")
    void refusedPayoutsDoNotSpendBudget() {
        ProgressStore store = new ProgressStore();
        AffectionApply once = decision("fears.milestone", 4, ReplayPolicy.ONCE);
        store.applyAffection(VILLAGER, PLAYER, once, at(100, "a", DepthClass.DEEP, 0, 0));
        store.applyAffection(VILLAGER, PLAYER, once, at(200, "b", DepthClass.DEEP, 0, 0));
        store.applyAffection(VILLAGER, PLAYER, once, at(300, "c", DepthClass.DEEP, 0, 0));
        // 4 of the 8 daily points are spent; the rest are still available to a different decision.
        assertEquals(4, store.applyAffection(VILLAGER, PLAYER,
                decision("fears.other", 4, ReplayPolicy.DAILY_REPEAT),
                at(400, "d", DepthClass.DEEP, 0, 0)).granted());
    }

    @Test
    @DisplayName("a zero multiplier makes conversation heart-neutral without breaking anything")
    void zeroMultiplier() {
        ProgressStore store = new ProgressStore();
        AffectionOutcome outcome = store.applyAffection(VILLAGER, PLAYER,
                decision("day.rough.empathize", 2, ReplayPolicy.DAILY_REPEAT),
                new AffectionContext(DepthClass.QUICK, 0, 0, 8, 10, false, 0.0, "t", 100));
        assertEquals(0, outcome.granted());
        assertEquals(AffectionOutcome.Reason.ZERO, outcome.reason());
    }

    @Test
    @DisplayName("two players build independent ledgers with the same villager")
    void ledgersArePerPair() {
        ProgressStore store = new ProgressStore();
        UUID other = UUID.nameUUIDFromBytes("other".getBytes());
        AffectionApply directive = decision("day.good.celebrate", 2, ReplayPolicy.ONCE_PER_DAY);
        assertEquals(2, store.applyAffection(VILLAGER, PLAYER, directive,
                at(100, "a", DepthClass.QUICK, 0, 0)).granted());
        assertEquals(2, store.applyAffection(VILLAGER, other, directive,
                at(100, "a", DepthClass.QUICK, 0, 0)).granted());
    }
}
