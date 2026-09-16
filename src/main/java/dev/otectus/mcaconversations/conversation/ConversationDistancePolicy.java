package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversationsConfig;

/**
 * How far apart a discussion may be held, and what happens when it is not (spec §5.4).
 *
 * <p>Before this, a graphical conversation was governed by one hard-coded number — eight blocks,
 * squared once at the top of {@link ChoiceSelectionService} — and there was nothing in between
 * "fine" and "over". Standing a step too far made the next click fail with no warning, and the
 * window stayed open afterwards as if nothing had happened. Three distances replace it:
 *
 * <ul>
 *   <li><b>At or within the continue distance</b> (16 blocks) everything is allowed. Equality is
 *       inside, matching the convention {@link EngagementPolicy} already uses: the radius is the
 *       boundary of the sphere, not the first point outside it.</li>
 *   <li><b>Between continue and immediate</b> the discussion is in its grace band. The window stays
 *       readable, but nothing may act — no answer, no topic change — and a counter runs. Coming back
 *       within the continue distance before it expires clears the counter and the discussion carries
 *       on; letting it expire ends the discussion.</li>
 *   <li><b>Beyond the immediate-close distance</b> (24 blocks) there is no grace at all.</li>
 * </ul>
 *
 * <p><b>Squared throughout.</b> Every method takes and compares a <em>squared</em> distance, and the
 * two configured radii are squared exactly once, here, when the policy is built. A caller never has
 * the chance to compare a linear distance against a squared limit, which is the mistake this shape
 * exists to make impossible.
 *
 * <p>Pure: no entities, no levels, no server. {@link #judge} is given the distance, the tick the pair
 * first went outside, and the current tick, and answers with a verdict and the marker to remember —
 * so the grace band is a property of this class and its caller owns nothing but a {@code long}.
 *
 * <p>This is the <em>continued</em> distance, and only that. It does not widen MCA's reach for
 * opening an interaction, and the chat frontend keeps its own ambient and addressed radii, which are
 * a different question about a different frontend and are deliberately not merged into it.
 */
public final class ConversationDistancePolicy {

    /** The marker for a pair that is not outside the continue distance. */
    public static final long NOT_OUTSIDE = Long.MIN_VALUE;

    /** Where a pair stands relative to the two distances. */
    public enum Band {
        /** At or within the continue distance: the discussion is unrestricted. */
        INSIDE,
        /** Past the continue distance but not the immediate one: readable, but nothing may act. */
        GRACE,
        /** Past the immediate-close distance: the discussion ends now. */
        BEYOND
    }

    /**
     * One answer about one pair at one moment.
     *
     * @param band            where the pair stands
     * @param effectsAllowed  whether an answer or a topic change may run
     * @param terminate       whether the discussion must end now
     * @param outsideSince    the marker to remember for the next call, {@link #NOT_OUTSIDE} when the
     *                        pair is inside again
     */
    public record Verdict(Band band, boolean effectsAllowed, boolean terminate, long outsideSince) {
    }

    private final double continueDistance;
    private final double immediateCloseDistance;
    private final int graceTicks;
    private final double continueDistanceSqr;
    private final double immediateCloseDistanceSqr;

    private ConversationDistancePolicy(double continueDistance, double immediateCloseDistance, int graceTicks) {
        this.continueDistance = continueDistance;
        this.immediateCloseDistance = immediateCloseDistance;
        this.graceTicks = graceTicks;
        this.continueDistanceSqr = continueDistance * continueDistance;
        this.immediateCloseDistanceSqr = immediateCloseDistance * immediateCloseDistance;
    }

    /**
     * Builds a policy, normalising a combination that cannot mean anything.
     *
     * <p>A hand-edited config can say that the immediate-close distance is nearer than the continue
     * distance. Rather than refusing to work, the immediate distance is raised to the continue
     * distance — which collapses the grace band to nothing and leaves the stricter of the two rules
     * standing, the safe direction to fail in. Negative grace is no grace.
     */
    public static ConversationDistancePolicy of(double continueDistance, double immediateCloseDistance,
                                                int graceTicks) {
        double keep = Math.max(0.0D, continueDistance);
        double hard = Math.max(keep, immediateCloseDistance);
        return new ConversationDistancePolicy(keep, hard, Math.max(0, graceTicks));
    }

    /** The policy the server is configured with; the documented defaults while no world is loaded. */
    public static ConversationDistancePolicy configured() {
        return of(McaConversationsConfig.continueDistance(),
                McaConversationsConfig.immediateCloseDistance(),
                McaConversationsConfig.distanceGraceTicks());
    }

    public double continueDistance() {
        return continueDistance;
    }

    public double immediateCloseDistance() {
        return immediateCloseDistance;
    }

    public int graceTicks() {
        return graceTicks;
    }

    /** The continue distance as the squared value every comparison actually uses. */
    public double continueDistanceSqr() {
        return continueDistanceSqr;
    }

    /** The immediate-close distance as the squared value every comparison actually uses. */
    public double immediateCloseDistanceSqr() {
        return immediateCloseDistanceSqr;
    }

    /** Which band a squared distance falls in. */
    public Band bandOf(double distSqr) {
        if (distSqr <= continueDistanceSqr) {
            return Band.INSIDE;
        }
        return distSqr <= immediateCloseDistanceSqr ? Band.GRACE : Band.BEYOND;
    }

    /**
     * Whether an answer or a topic return may run from this distance.
     *
     * <p>Only from inside. Grace buys the player time to walk back, not the right to act at a
     * distance: a reply that ran from the grace band would be an effect the villager could not
     * possibly be part of.
     */
    public boolean allowsEffects(double distSqr) {
        return bandOf(distSqr) == Band.INSIDE;
    }

    /** True past the hard limit, where nothing is waited for. */
    public boolean beyondImmediate(double distSqr) {
        return distSqr > immediateCloseDistanceSqr;
    }

    /**
     * Judges one pair at one tick.
     *
     * @param distSqr      the squared distance between the pair
     * @param outsideSince the tick they first went outside, or {@link #NOT_OUTSIDE}
     * @param now          the current game tick
     */
    public Verdict judge(double distSqr, long outsideSince, long now) {
        Band band = bandOf(distSqr);
        if (band == Band.INSIDE) {
            // Back inside: the timer is cleared, not paused. Stepping in and out repeatedly buys a
            // fresh grace each time, which is the same thing as being in range the whole while.
            return new Verdict(Band.INSIDE, true, false, NOT_OUTSIDE);
        }
        if (band == Band.BEYOND) {
            return new Verdict(Band.BEYOND, false, true, outsideSince == NOT_OUTSIDE ? now : outsideSince);
        }
        long since = outsideSince == NOT_OUTSIDE ? now : outsideSince;
        boolean expired = now - since >= graceTicks;
        return new Verdict(Band.GRACE, false, expired, since);
    }
}
