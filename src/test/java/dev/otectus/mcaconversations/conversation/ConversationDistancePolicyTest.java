package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The continued-distance rule, at its boundaries (spec §5.4).
 *
 * <p>The old rule was one constant — eight blocks, squared — and the only thing it could say was
 * "no". These cases pin the three things that replaced it: where the line is, that standing exactly
 * on it is inside, and that the second between the two distances is genuinely a second in which the
 * player can walk back.
 *
 * <p>Squared distances throughout, deliberately. A policy that took a linear distance in one method
 * and a squared one in another would compare 16 against 256 somewhere and silently allow a
 * conversation across a quarter of a chunk; every number below is written as {@code d * d} so that a
 * mistake of that kind would show up here as an arithmetic error rather than as a bug in play.
 */
class ConversationDistancePolicyTest {

    private static final ConversationDistancePolicy POLICY =
            ConversationDistancePolicy.of(16.0, 24.0, 20);

    private static double sqr(double distance) {
        return distance * distance;
    }

    @Test
    @DisplayName("exactly the continue distance is inside, and everything is allowed there")
    void theBoundaryItselfIsInside() {
        assertEquals(ConversationDistancePolicy.Band.INSIDE, POLICY.bandOf(sqr(16.0)));
        assertTrue(POLICY.allowsEffects(sqr(16.0)));
        ConversationDistancePolicy.Verdict verdict =
                POLICY.judge(sqr(16.0), ConversationDistancePolicy.NOT_OUTSIDE, 1_000L);
        assertTrue(verdict.effectsAllowed());
        assertFalse(verdict.terminate());
        assertEquals(ConversationDistancePolicy.NOT_OUTSIDE, verdict.outsideSince());
    }

    @Test
    @DisplayName("a step past it refuses effects and starts the grace, without ending anything")
    void justOutsideRefusesEffectsAndStartsTheClock() {
        ConversationDistancePolicy.Verdict verdict =
                POLICY.judge(sqr(16.01), ConversationDistancePolicy.NOT_OUTSIDE, 1_000L);

        assertEquals(ConversationDistancePolicy.Band.GRACE, verdict.band());
        assertFalse(verdict.effectsAllowed(), "grace buys time to walk back, not the right to act");
        assertFalse(verdict.terminate());
        assertEquals(1_000L, verdict.outsideSince(), "the clock starts at the tick they went outside");
        assertFalse(POLICY.allowsEffects(sqr(16.01)));
        assertFalse(POLICY.beyondImmediate(sqr(16.01)));
    }

    @Test
    @DisplayName("coming back inside during the grace clears the timer and the conversation goes on")
    void returningWithinTheGraceRecovers() {
        ConversationDistancePolicy.Verdict left =
                POLICY.judge(sqr(20.0), ConversationDistancePolicy.NOT_OUTSIDE, 1_000L);
        ConversationDistancePolicy.Verdict stillOut = POLICY.judge(sqr(20.0), left.outsideSince(), 1_010L);
        assertFalse(stillOut.terminate(), "half a second outside is not an ending");

        ConversationDistancePolicy.Verdict back = POLICY.judge(sqr(4.0), stillOut.outsideSince(), 1_015L);
        assertEquals(ConversationDistancePolicy.Band.INSIDE, back.band());
        assertTrue(back.effectsAllowed());
        assertEquals(ConversationDistancePolicy.NOT_OUTSIDE, back.outsideSince());

        // And the timer is cleared, not paused: leaving again gets a whole fresh grace.
        ConversationDistancePolicy.Verdict again = POLICY.judge(sqr(20.0), back.outsideSince(), 1_016L);
        assertFalse(again.terminate());
        assertEquals(1_016L, again.outsideSince());
    }

    @Test
    @DisplayName("staying outside for the whole grace ends the conversation")
    void graceExpiryTerminates() {
        ConversationDistancePolicy.Verdict left =
                POLICY.judge(sqr(20.0), ConversationDistancePolicy.NOT_OUTSIDE, 1_000L);

        assertFalse(POLICY.judge(sqr(20.0), left.outsideSince(), 1_019L).terminate(),
                "one tick short of the grace is still inside it");
        assertTrue(POLICY.judge(sqr(20.0), left.outsideSince(), 1_020L).terminate(),
                "twenty ticks outside is the configured second");
        assertTrue(POLICY.judge(sqr(20.0), left.outsideSince(), 5_000L).terminate());
    }

    @Test
    @DisplayName("past the immediate distance there is no grace to wait for")
    void beyondTheHardLimitEndsAtOnce() {
        assertEquals(ConversationDistancePolicy.Band.BEYOND, POLICY.bandOf(sqr(24.01)));
        assertTrue(POLICY.beyondImmediate(sqr(24.01)));
        assertFalse(POLICY.beyondImmediate(sqr(24.0)), "the hard limit itself is still the grace band");

        ConversationDistancePolicy.Verdict verdict =
                POLICY.judge(sqr(40.0), ConversationDistancePolicy.NOT_OUTSIDE, 1_000L);
        assertTrue(verdict.terminate());
        assertFalse(verdict.effectsAllowed());
    }

    @Test
    @DisplayName("the radii are squared exactly once, where they are configured")
    void squaredAndLinearNeverDisagree() {
        assertEquals(16.0, POLICY.continueDistance());
        assertEquals(24.0, POLICY.immediateCloseDistance());
        assertEquals(256.0, POLICY.continueDistanceSqr());
        assertEquals(576.0, POLICY.immediateCloseDistanceSqr());

        // The mistake this shape exists to prevent: 16 read as a squared distance would put four
        // blocks on the boundary and call twelve blocks "too far".
        assertTrue(POLICY.allowsEffects(sqr(12.0)));
        assertEquals(ConversationDistancePolicy.Band.INSIDE, POLICY.bandOf(sqr(15.9)));
    }

    @Test
    @DisplayName("no grace at all ends the conversation the moment the pair separate")
    void zeroGraceEndsImmediately() {
        ConversationDistancePolicy strict = ConversationDistancePolicy.of(16.0, 24.0, 0);
        assertTrue(strict.judge(sqr(17.0), ConversationDistancePolicy.NOT_OUTSIDE, 1_000L).terminate());
        assertFalse(strict.judge(sqr(16.0), ConversationDistancePolicy.NOT_OUTSIDE, 1_000L).terminate());
    }

    @Test
    @DisplayName("a config that puts the hard limit nearer than the continue distance keeps the stricter rule")
    void impossibleCombinationsAreNormalised() {
        ConversationDistancePolicy muddled = ConversationDistancePolicy.of(16.0, 8.0, -5);
        assertEquals(16.0, muddled.immediateCloseDistance(), "the hard limit is raised, never lowered");
        assertEquals(0, muddled.graceTicks());
        assertTrue(muddled.allowsEffects(sqr(16.0)));
        assertTrue(muddled.beyondImmediate(sqr(16.01)), "with no band between them there is no grace");
    }

    @Test
    @DisplayName("the configured policy answers the documented defaults with no world loaded")
    void theConfiguredPolicyIsSixteenAndTwentyFour() {
        ConversationDistancePolicy configured = ConversationDistancePolicy.configured();
        assertEquals(16.0, configured.continueDistance());
        assertEquals(24.0, configured.immediateCloseDistance());
        assertEquals(20, configured.graceTicks());
    }
}
