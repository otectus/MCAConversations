package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Every refusal has to be able to explain itself, and only one of them ends the conversation. */
class ChoiceOutcomeTest {

    @Test
    void everyRejectionExplainsItselfAndOnlySuccessIsSuccess() {
        for (ChoiceOutcome outcome : ChoiceOutcome.values()) {
            if (outcome.ok()) {
                assertEquals(ChoiceClearS2C.Reason.CONSUMED, outcome.wireReason());
            } else {
                assertNotEquals(ChoiceClearS2C.Reason.CONSUMED, outcome.wireReason(), outcome.name());
                assertNotEquals(ChoiceClearS2C.Reason.NONE, outcome.wireReason(), outcome.name());
                assertTrue(outcome.wireReason().explained(), outcome.name());
            }
        }
    }

    @Test
    void onlyAContainedFailureTearsTheSessionDown() {
        for (ChoiceOutcome outcome : ChoiceOutcome.values()) {
            if (outcome == ChoiceOutcome.EXECUTION_FAILED) {
                assertTrue(outcome.terminal());
                assertEquals(CloseReason.CONTAINED_ERROR, outcome.closeReason().orElseThrow());
            } else {
                assertFalse(outcome.terminal(), outcome.name());
                assertTrue(outcome.closeReason().isEmpty(), outcome.name());
            }
        }
    }

    @Test
    void internalDistinctionsStayDistinctWhileSharingAnExplanation() {
        // The server can tell a replay from an ownership mismatch; the player is told the same true
        // thing about both, because the difference is a diagnosis and not their problem.
        assertEquals(ChoiceClearS2C.Reason.EXPIRED, ChoiceOutcome.TIMED_OUT.wireReason());
        assertEquals(ChoiceClearS2C.Reason.EXPIRED, ChoiceOutcome.INVALID_SUBMISSION.wireReason());
        assertEquals(ChoiceClearS2C.Reason.EXPIRED, ChoiceOutcome.OWNERSHIP_MISMATCH.wireReason());
        assertEquals(ChoiceClearS2C.Reason.SPEAKER_UNAVAILABLE, ChoiceOutcome.FEATURE_DISABLED.wireReason());
        assertNotEquals(ChoiceOutcome.TIMED_OUT, ChoiceOutcome.INVALID_SUBMISSION);
    }

    @Test
    void engagementRefusalsKeepTheirOwnNames() {
        assertEquals(ChoiceOutcome.OUT_OF_RANGE,
                ChoiceOutcome.of(EngagementPolicy.Verdict.OUT_OF_RANGE));
        assertEquals(ChoiceOutcome.SPEAKER_UNAVAILABLE,
                ChoiceOutcome.of(EngagementPolicy.Verdict.VILLAGER_DEAD));
        assertEquals(ChoiceOutcome.SPEAKER_UNAVAILABLE,
                ChoiceOutcome.of(EngagementPolicy.Verdict.DIMENSION_CHANGED));
        assertTrue(ChoiceOutcome.of(EngagementPolicy.Verdict.OK).ok());
        assertTrue(ChoiceOutcome.of(null).ok());
    }
}
