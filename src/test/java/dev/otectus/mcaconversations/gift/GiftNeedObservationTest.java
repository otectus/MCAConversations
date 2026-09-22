package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.compat.TownsteadNeedsView;
import dev.otectus.mcaconversations.gift.GiftNeedObservation.Relief;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** A gift is only said to have helped when Townstead's own numbers moved (Townstead spec §14). */
class GiftNeedObservationTest {

    private static TownsteadNeedsView needs(int hunger, int thirst, int fatigue, boolean collapsed, boolean gated) {
        return new TownsteadNeedsView(hunger, 0f, 0f, thirst, thirst, 0f, fatigue, collapsed, gated);
    }

    @Test
    @DisplayName("an ordinary gift, or one Townstead did not consume, claims nothing")
    void nothingChanged() {
        TownsteadNeedsView same = needs(40, 10, 5, false, false);
        assertEquals(Set.of(), GiftNeedObservation.compare(same, same));
        assertEquals(Set.of(), GiftNeedObservation.compare(same, needs(38, 10, 6, false, false)),
                "needs that fell while the gift was handed over were not relieved by it");
        assertEquals(Set.of(), GiftNeedObservation.compare(null, same), "Townstead absent");
    }

    @Test
    @DisplayName("food that Townstead consumed relieves hunger; a drink only where thirst is simulated")
    void foodAndDrink() {
        assertEquals(Set.of(Relief.HUNGER),
                GiftNeedObservation.compare(needs(20, 10, 5, false, false), needs(45, 10, 5, false, false)));
        assertEquals(Set.of(Relief.THIRST),
                GiftNeedObservation.compare(needs(60, 3, 5, false, false), needs(60, 9, 5, false, false)));
        assertEquals(Set.of(),
                GiftNeedObservation.compare(needs(60, 3, 5, false, true), needs(60, 9, 5, false, true)),
                "no thirst mod: a drink cannot have relieved a thirst that does not exist");
    }

    @Test
    @DisplayName("waking from a collapse, or less fatigue, is recovery")
    void recovery() {
        assertTrue(GiftNeedObservation.compare(needs(10, 10, 20, true, false), needs(30, 10, 20, false, false))
                .containsAll(Set.of(Relief.HUNGER, Relief.RECOVERY)));
        assertEquals(Set.of(Relief.RECOVERY),
                GiftNeedObservation.compare(needs(60, 10, 14, false, false), needs(60, 10, 12, false, false)));
        assertEquals("mcaconversations.gift.relieved_hunger", Relief.HUNGER.memoryId());
    }

    @Test
    @DisplayName("with Townstead absent nothing is queued")
    void absentQueuesNothing() {
        GiftNeedObservation.onAccepted(null, null);
        assertFalse(GiftNeedObservation.pending(UUID.randomUUID(), UUID.randomUUID()));
    }
}
