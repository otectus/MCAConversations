package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.compat.TownsteadFixtures;
import dev.otectus.mcaconversations.compat.TownsteadSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Townstead's context fields (Townstead spec §8.4), from hand-built snapshots. */
class TownsteadContextSourceTest {

    private static ConversationContextSnapshot contribute(TownsteadSnapshot snapshot) {
        ContextSnapshotBuilder builder = new ContextSnapshotBuilder();
        builder.beginSource(TownsteadContextSource.ID);
        TownsteadContextSource.contribute(builder, snapshot);
        return builder.build();
    }

    @Test
    @DisplayName("a live villager's needs, schedule, work, place, spirit and date become context fields")
    void liveSnapshot() {
        ConversationContextSnapshot snapshot = contribute(TownsteadFixtures.snapshot(
                TownsteadFixtures.villager(), Set.of("townstead:working")));

        assertEquals(Optional.of(true), snapshot.value(ContextKeys.TOWNSTEAD_PRESENT));
        assertEquals(Optional.of("work"), snapshot.value(ContextKeys.TOWNSTEAD_ACTIVITY));
        assertEquals(Optional.of("none"), snapshot.value(ContextKeys.TOWNSTEAD_NEED));
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.TOWNSTEAD_NEED_CRISIS));
        assertEquals(Optional.of("well_fed"), snapshot.value(ContextKeys.TOWNSTEAD_HUNGER));
        assertEquals(Optional.of("adult"), snapshot.value(ContextKeys.TOWNSTEAD_AGE));
        assertEquals(Optional.of(3), snapshot.value(ContextKeys.TOWNSTEAD_PROFESSION_LEVEL));
        assertEquals(Optional.of("bakery"), snapshot.value(ContextKeys.TOWNSTEAD_BUILDING));
        assertEquals(Optional.of("human"), snapshot.value(ContextKeys.TOWNSTEAD_SPECIES));
        assertEquals(Optional.of(2), snapshot.value(ContextKeys.TOWNSTEAD_SPIRIT_TIER));
        assertEquals(Optional.of("townstead:hearth"), snapshot.value(ContextKeys.TOWNSTEAD_SPIRIT));
        assertEquals(Optional.of("single"), snapshot.value(ContextKeys.TOWNSTEAD_SPIRIT_KIND));
        assertEquals(Optional.of(6), snapshot.value(ContextKeys.TOWNSTEAD_MONTH));
        assertEquals(Optional.of(2), snapshot.value(ContextKeys.TOWNSTEAD_WEEKDAY));
    }

    @Test
    @DisplayName("a villager in crisis says so, and names the need to speak about first")
    void crisis() {
        ConversationContextSnapshot snapshot = contribute(TownsteadFixtures.snapshot(
                TownsteadFixtures.villager(TownsteadFixtures.needs(10, 18, 2, false), "idle", 1, Set.of(),
                        dev.otectus.mcaconversations.compat.TownsteadPersonalityView.EMPTY), Set.of()));
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.TOWNSTEAD_NEED_CRISIS));
        assertEquals(Optional.of("hunger"), snapshot.value(ContextKeys.TOWNSTEAD_NEED));
        assertEquals(Optional.of("starving"), snapshot.value(ContextKeys.TOWNSTEAD_HUNGER));
    }

    @Test
    @DisplayName("with no Townstead villager, presence is false and the rest is unknown, never a guess")
    void emptySnapshot() {
        ConversationContextSnapshot snapshot = contribute(TownsteadSnapshot.EMPTY);
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.TOWNSTEAD_PRESENT));
        assertTrue(snapshot.value(ContextKeys.TOWNSTEAD_ACTIVITY).isEmpty());
        assertTrue(snapshot.value(ContextKeys.TOWNSTEAD_NEED).isEmpty());
        assertTrue(snapshot.value(ContextKeys.TOWNSTEAD_MONTH).isEmpty());
        assertTrue(snapshot.value(ContextKeys.TOWNSTEAD_SPIRIT_TIER).isEmpty());
    }
}
