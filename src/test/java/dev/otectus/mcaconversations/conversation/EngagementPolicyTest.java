package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.conversation.EngagementPolicy.Verdict;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The one place the "may this player still engage this villager" rules are stated, and their order. */
class EngagementPolicyTest {

    private static final double MAX = 100.0D;

    private static Verdict evaluate(boolean connected, boolean speakerAlive, boolean villagerAlive,
                                    boolean sameLevel, double distSqr) {
        return EngagementPolicy.evaluate(connected, speakerAlive, villagerAlive, sameLevel, distSqr, MAX);
    }

    @Test
    void everythingInOrderIsOk() {
        assertEquals(Verdict.OK, evaluate(true, true, true, true, 1.0D));
        assertTrue(Verdict.OK.ok());
    }

    @Test
    void disconnectedSpeaker() {
        assertEquals(Verdict.SPEAKER_GONE, evaluate(false, true, true, true, 1.0D));
    }

    @Test
    void deadSpeaker() {
        assertEquals(Verdict.SPEAKER_DEAD, evaluate(true, false, true, true, 1.0D));
    }

    @Test
    void deadVillager() {
        assertEquals(Verdict.VILLAGER_DEAD, evaluate(true, true, false, true, 1.0D));
    }

    @Test
    void differentDimension() {
        assertEquals(Verdict.DIMENSION_CHANGED, evaluate(true, true, true, false, 1.0D));
    }

    @Test
    void outOfRange() {
        Verdict verdict = evaluate(true, true, true, true, MAX + 0.01D);
        assertEquals(Verdict.OUT_OF_RANGE, verdict);
        assertFalse(verdict.ok());
    }

    @Test
    void theRadiusItselfIsInside() {
        assertEquals(Verdict.OK, evaluate(true, true, true, true, MAX));
    }

    @Test
    void aGoneSpeakerOutranksEveryOtherFailure() {
        assertEquals(Verdict.SPEAKER_GONE, evaluate(false, false, false, false, MAX * 10));
    }

    @Test
    void aDeadSpeakerOutranksADeadVillager() {
        assertEquals(Verdict.SPEAKER_DEAD, evaluate(true, false, false, false, MAX * 10));
    }

    @Test
    void aDeadVillagerOutranksADimensionChange() {
        assertEquals(Verdict.VILLAGER_DEAD, evaluate(true, true, false, false, MAX * 10));
    }

    @Test
    void aDimensionChangeOutranksRange() {
        // Cross-dimension distance is meaningless, so it must never be the reported reason.
        assertEquals(Verdict.DIMENSION_CHANGED, evaluate(true, true, true, false, MAX * 10));
    }
}
