package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static dev.otectus.mcaconversations.client.dialogue.UtteranceAgreement.Verdict.ACTIONABLE;
import static dev.otectus.mcaconversations.client.dialogue.UtteranceAgreement.Verdict.LAPSED;
import static dev.otectus.mcaconversations.client.dialogue.UtteranceAgreement.Verdict.SUPERSEDED;
import static dev.otectus.mcaconversations.client.dialogue.UtteranceAgreement.Verdict.WAITING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The agreement rule the interaction screen asks every frame (feedback remediation §2.1, WP-A3):
 * the card is answerable when it shows the offer's own line, and every way the comparison can fail to
 * apply resolves towards answerable.
 */
class InteractScreenAgreementTest {

    private static final UUID DISCUSSION = UUID.nameUUIDFromBytes("agreement-discussion".getBytes());
    private static final int GRACE = UtteranceAgreement.GRACE_TICKS;

    private static UtteranceAgreement.Displayed shown(long seq) {
        return new UtteranceAgreement.Displayed(DISCUSSION, seq, true);
    }

    @Test
    void theOffersOwnLineOnScreenIsAnswerable() {
        assertEquals(ACTIONABLE, UtteranceAgreement.decide(4L, DISCUSSION, shown(4L), 0L, GRACE));
    }

    @Test
    void anOfferAheadOfItsLineWaitsThenGivesIn() {
        assertEquals(WAITING, UtteranceAgreement.decide(5L, DISCUSSION, shown(4L), 0L, GRACE));
        assertEquals(WAITING, UtteranceAgreement.decide(5L, DISCUSSION, shown(4L), GRACE - 1L, GRACE));
        assertEquals(LAPSED, UtteranceAgreement.decide(5L, DISCUSSION, shown(4L), GRACE, GRACE));
        assertTrue(LAPSED.answerable(), "fail-open: a correlation bug must never freeze a conversation");
        assertFalse(WAITING.answerable());
    }

    @Test
    void aLaterLineOfTheSameDiscussionRetiresTheOffer() {
        assertEquals(SUPERSEDED, UtteranceAgreement.decide(4L, DISCUSSION, shown(5L), 0L, GRACE));
        assertFalse(SUPERSEDED.answerable());
    }

    @Test
    void anOfferThatMakesNoClaimIsAnswerableAndNeverSuperseded() {
        assertEquals(ACTIONABLE, UtteranceAgreement.decide(-1L, DISCUSSION, shown(9L), 0L, GRACE));
        assertEquals(ACTIONABLE, UtteranceAgreement.decide(-1L, null, null, 0L, GRACE));
    }

    @Test
    void anUntaggedLineOnScreenIsAnswerable() {
        assertEquals(ACTIONABLE, UtteranceAgreement.decide(3L, DISCUSSION,
                UtteranceAgreement.Displayed.untagged(), 0L, GRACE));
    }

    @Test
    void nothingDrawnYetOrTheLastDiscussionStillShowingWaits() {
        assertEquals(WAITING, UtteranceAgreement.decide(1L, DISCUSSION, null, 0L, GRACE));
        assertEquals(WAITING, UtteranceAgreement.decide(1L, DISCUSSION,
                new UtteranceAgreement.Displayed(UUID.randomUUID(), 9L, true), 0L, GRACE),
                "a line from another discussion is not ahead of this offer, it is unrelated");
        assertEquals(LAPSED, UtteranceAgreement.decide(1L, DISCUSSION, null, GRACE + 5L, GRACE));
    }
}
