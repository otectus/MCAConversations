package dev.otectus.mcaconversations.event;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The phase table: the 600-tick work no longer shares one tick (feedback remediation WP-D2). */
class MaintenanceScheduleTest {

    @Test
    void everyJobRunsOncePerIntervalOnItsOwnTick() {
        for (int interval : new int[] {100, 600, 601, 24000}) {
            Set<Integer> offsets = new HashSet<>();
            for (MaintenanceSchedule.Job job : MaintenanceSchedule.Job.values()) {
                int runs = 0;
                for (long tick = 0; tick < interval * 3L; tick++) {
                    if (MaintenanceSchedule.due(job, tick, interval)) {
                        runs++;
                    }
                }
                assertEquals(3, runs, job + " at interval " + interval);
                assertTrue(offsets.add(MaintenanceSchedule.offset(job, interval)),
                        job + " shares a tick with another job at interval " + interval);
            }
        }
    }

    @Test
    void theCourtPollNeverLandsOnTheGossipTickAtTheDefaults() {
        for (long tick = 0; tick < 12_000; tick++) {
            assertFalse(MaintenanceSchedule.courtDue(tick, 600)
                    && MaintenanceSchedule.due(MaintenanceSchedule.Job.GOSSIP, tick, 600), "tick " + tick);
        }
        assertTrue(MaintenanceSchedule.courtDue(7, 600));
        assertTrue(MaintenanceSchedule.courtDue(3, 2), "an interval below the offset still runs once per period");
    }

    @Test
    void eachPlayerIsScannedOncePerFortyTicksAndPlayersAreSpread() {
        Set<Long> slots = new HashSet<>();
        for (int i = 0; i < 200; i++) {
            UUID player = UUID.nameUUIDFromBytes(("player-" + i).getBytes());
            int turns = 0;
            for (long tick = 1000; tick < 1000 + MaintenanceSchedule.PLAYER_SLOTS; tick++) {
                if (MaintenanceSchedule.playerDue(player, tick)) {
                    turns++;
                    slots.add(tick);
                }
            }
            assertEquals(1, turns, "player " + i);
        }
        assertTrue(slots.size() > MaintenanceSchedule.PLAYER_SLOTS / 2, "200 players use most of the 40 slots");
    }

    @Test
    void aStorePassStartsAtMostOncePerInGameHour() {
        assertTrue(MaintenanceSchedule.storePassMayStart(5_000L, Long.MIN_VALUE));
        assertFalse(MaintenanceSchedule.storePassMayStart(5_600L, 5_000L));
        assertTrue(MaintenanceSchedule.storePassMayStart(6_000L, 5_000L));
    }

    @Test
    void theJobsAreTheOnesThePlanNames() {
        assertEquals(EnumSet.of(MaintenanceSchedule.Job.GOSSIP, MaintenanceSchedule.Job.SESSION_SWEEP,
                MaintenanceSchedule.Job.RUMOURS, MaintenanceSchedule.Job.STORE_PRUNE,
                MaintenanceSchedule.Job.HISTORY_PRUNE), EnumSet.allOf(MaintenanceSchedule.Job.class));
        assertEquals(0, MaintenanceSchedule.offset(MaintenanceSchedule.Job.GOSSIP, 600));
        assertEquals(75, MaintenanceSchedule.offset(MaintenanceSchedule.Job.SESSION_SWEEP, 600));
        assertEquals(150, MaintenanceSchedule.offset(MaintenanceSchedule.Job.RUMOURS, 600));
        assertEquals(300, MaintenanceSchedule.offset(MaintenanceSchedule.Job.STORE_PRUNE, 600));
        assertEquals(450, MaintenanceSchedule.offset(MaintenanceSchedule.Job.HISTORY_PRUNE, 600));
    }
}
