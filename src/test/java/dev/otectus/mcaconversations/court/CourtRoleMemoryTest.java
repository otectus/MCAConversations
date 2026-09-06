package dev.otectus.mcaconversations.court;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The rules that decide whether a villager has anything to say about their own office.
 *
 * <p>The one that matters is the first sighting. Every other rule here is a window or a flag; that
 * one is the difference between "the Hand mentions his new office" and "every titled villager in the
 * world announces a promotion on the day the integration was installed".
 */
class CourtRoleMemoryTest {

    private static final UUID VILLAGER = UUID.randomUUID();
    private static final UUID CAPITAL = UUID.randomUUID();

    @Test
    void theFirstSightingIsNotAChange() {
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(VILLAGER, "hand", 10L);
        assertEquals(Optional.empty(), memory.freshChange(VILLAGER, 10L, 7));
        assertEquals(Optional.of("hand"), memory.title(VILLAGER));
    }

    @Test
    void aDifferentTitleIsAChangeAndCarriesTheOldOne() {
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(VILLAGER, "knight", 10L);
        memory.observe(VILLAGER, "hand", 12L);
        assertEquals(Optional.of("knight"), memory.freshChange(VILLAGER, 12L, 7));
        assertEquals(Optional.of("hand"), memory.title(VILLAGER));
    }

    @Test
    void seeingTheSameTitleAgainWritesNothing() {
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(VILLAGER, "knight", 10L);
        assertFalse(memory.observe(VILLAGER, "knight", 11L));
        assertEquals(Optional.empty(), memory.freshChange(VILLAGER, 11L, 7));
    }

    @Test
    void aChangeGoesStaleWhenTheWindowPasses() {
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(VILLAGER, "knight", 10L);
        memory.observe(VILLAGER, "hand", 12L);
        assertEquals(Optional.of("knight"), memory.freshChange(VILLAGER, 19L, 7));
        assertEquals(Optional.empty(), memory.freshChange(VILLAGER, 20L, 7));
    }

    @Test
    void aClockMovedBackwardsIsNotAFreshChange() {
        // A negative age would otherwise pass every window test and remark on a promotion forever.
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(VILLAGER, "knight", 100L);
        memory.observe(VILLAGER, "hand", 100L);
        assertEquals(Optional.empty(), memory.freshChange(VILLAGER, 90L, 7));
    }

    @Test
    void onePromotionIsRemarkedOnOnce() {
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(VILLAGER, "knight", 10L);
        memory.observe(VILLAGER, "hand", 12L);
        assertTrue(memory.markRemarked(VILLAGER));
        assertEquals(Optional.empty(), memory.freshChange(VILLAGER, 12L, 7));
        assertFalse(memory.markRemarked(VILLAGER), "a second mark has nothing left to do");
    }

    @Test
    void aLaterPromotionIsRemarkableAgain() {
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(VILLAGER, "knight", 10L);
        memory.observe(VILLAGER, "hand", 12L);
        memory.markRemarked(VILLAGER);
        memory.observe(VILLAGER, "sovereign", 30L);
        assertEquals(Optional.of("hand"), memory.freshChange(VILLAGER, 30L, 7));
    }

    @Test
    void aVillagerNobodyHasSeenHasNothingToSay() {
        CourtRoleMemory memory = new CourtRoleMemory();
        assertEquals(Optional.empty(), memory.freshChange(UUID.randomUUID(), 5L, 7));
        assertEquals(Optional.empty(), memory.title(UUID.randomUUID()));
        assertTrue(memory.isEmpty());
    }

    @Test
    void aBlankTitleIsNotObserved() {
        // An unbound title handle answers "" rather than throwing; recording that as a title would
        // make the next real read look like a promotion.
        CourtRoleMemory memory = new CourtRoleMemory();
        assertFalse(memory.observe(VILLAGER, "", 1L));
        assertFalse(memory.observe(null, "hand", 1L));
        assertTrue(memory.isEmpty());
    }

    @Test
    void anUnpolledCapitalHasNoCursorAndNoSnapshot() {
        CourtRoleMemory memory = new CourtRoleMemory();
        assertEquals(CourtRoleMemory.CHRONICLE_UNSEEN, memory.chronicleSeen(CAPITAL));
        assertEquals(CourtRoleMemory.CapitalSnapshot.EMPTY, memory.snapshot(CAPITAL));
    }

    @Test
    void cursorAndSnapshotAreRememberedIndependently() {
        CourtRoleMemory memory = new CourtRoleMemory();
        assertTrue(memory.setChronicleSeen(CAPITAL, 12));
        UUID sovereign = UUID.randomUUID();
        CourtRoleMemory.CapitalSnapshot snapshot = new CourtRoleMemory.CapitalSnapshot(
                Optional.of(sovereign), Optional.empty(), "active", true,
                Map.of(UUID.randomUUID(), "war"));
        assertTrue(memory.setSnapshot(CAPITAL, snapshot));

        assertEquals(12, memory.chronicleSeen(CAPITAL));
        assertEquals(snapshot, memory.snapshot(CAPITAL));
        assertFalse(memory.setChronicleSeen(CAPITAL, 12), "an unchanged cursor is not a write");
        assertFalse(memory.setSnapshot(CAPITAL, snapshot), "an unchanged snapshot is not a write");
    }
}
