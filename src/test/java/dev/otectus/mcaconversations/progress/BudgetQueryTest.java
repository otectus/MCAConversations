package dev.otectus.mcaconversations.progress;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code conversations_budget} condition, which exposes counters that were tracked per villager
 * and player since 1.1.0 and readable by nothing — so the daily cap could silently clamp a heart
 * gain to zero with no way for the villager to say so.
 */
class BudgetQueryTest {

    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("v".getBytes());
    private static final UUID PLAYER = UUID.nameUUIDFromBytes("p".getBytes());

    private static JsonObject json(String raw) {
        return JsonParser.parseString(raw).getAsJsonObject();
    }

    @Test
    void parsesTheThreeAxes() {
        assertEquals(BudgetQuery.Axis.POSITIVE,
                BudgetQuery.fromJson(json("{\"axis\":\"positive\"}")).axis());
        assertEquals(BudgetQuery.Axis.NEGATIVE,
                BudgetQuery.fromJson(json("{\"axis\":\"NEGATIVE\"}")).axis());
        assertEquals(BudgetQuery.Axis.REPEATS,
                BudgetQuery.fromJson(json("{\"axis\":\"repeats\",\"decision\":\"day.rough.empathize\"}")).axis());
    }

    @Test
    void repeatsCountsOneDecisionAndSaysSoWhenItIsNotGivenOne() {
        assertThrows(IllegalArgumentException.class,
                () -> BudgetQuery.fromJson(json("{\"axis\":\"repeats\"}")));
        assertThrows(IllegalArgumentException.class,
                () -> BudgetQuery.fromJson(json("{\"axis\":\"positive\",\"decision\":\"x\"}")),
                "a daily total is not about one decision, and pretending otherwise would read as working");
    }

    @Test
    void rejectsAnUnknownAxisAndAnInvertedRange() {
        assertThrows(IllegalArgumentException.class,
                () -> BudgetQuery.fromJson(json("{\"axis\":\"warmth\"}")));
        assertThrows(IllegalArgumentException.class,
                () -> BudgetQuery.fromJson(json("{\"axis\":\"positive\",\"min\":5,\"max\":2}")));
    }

    @Test
    void readsTodaysLedgerAndNotYesterdays() {
        ProgressStore store = new ProgressStore();
        ProgressRecord record = store.getOrCreate(VILLAGER, PLAYER, 1_000L);
        AffectionApply apply = AffectionApply.fromJson(json(
                "{\"decision\":\"day.rough.empathize\",\"delta\":2,\"budget\":\"quick\",\"policy\":\"daily_repeat\"}"));
        AffectionContext context = new AffectionContext(
                dev.otectus.mcaconversations.conversation.DepthClass.QUICK,
                0, 0, 8, 10, false, 1.0, "t1", 1_000L);
        store.applyAffection(VILLAGER, PLAYER, apply, context);

        long today = 0L;
        BudgetQuery atLeastOne = BudgetQuery.fromJson(json("{\"axis\":\"positive\",\"min\":1}"));
        assertTrue(atLeastOne.matches(atLeastOne.valueOf(record, today)));

        BudgetQuery none = BudgetQuery.fromJson(json("{\"axis\":\"negative\",\"min\":1}"));
        assertFalse(none.matches(none.valueOf(record, today)),
                "the two directions are counted separately, so kindness must not fill the negative budget");

        // A new day rolls the counters over, which is what makes a "we've talked enough" branch
        // stop firing tomorrow.
        assertEquals(0, atLeastOne.valueOf(record, today + 1));
    }

    @Test
    void aMissingRecordIsZeroRatherThanAFailure() {
        BudgetQuery query = BudgetQuery.fromJson(json("{\"axis\":\"positive\",\"max\":0}"));
        assertEquals(0, query.valueOf(null, 0L));
        assertTrue(query.matches(query.valueOf(null, 0L)),
                "a player the villager has never spoken to has spent none of the budget");
    }
}
