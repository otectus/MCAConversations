package dev.otectus.mcaconversations.client.dialogue;

import java.util.UUID;

/**
 * Whether the answers on the card belong to the line the card is showing (feedback remediation §2.1).
 *
 * <p>The offer names the utterance it belongs to; the client ledger knows which utterance the screen
 * drew last. The card is answerable when the two agree. Every way the comparison can fail to apply
 * resolves towards answerable, because a correlation bug must never freeze a conversation:
 *
 * <ul>
 *   <li>an offer that makes no claim ({@code -1}) — MCA's own native pages, the screen opening —
 *       is answerable at once, and never superseded;</li>
 *   <li>an untagged line on screen — a third-party packet, an older server — is answerable;</li>
 *   <li>an offer whose line has not been drawn yet waits, and after the grace it is answerable anyway
 *       ({@link Verdict#LAPSED}, which the caller logs once);</li>
 *   <li>only a line drawn <em>after</em> the offer's own, in the same discussion, retires the offer —
 *       the server said something newer, so these answers are not for what is on screen.</li>
 * </ul>
 *
 * <p>Pure: the screen mixin asks it every frame, and the tests ask it every case.
 */
public final class UtteranceAgreement {

    /** Ticks an offer may wait for its line before the card becomes answerable regardless. */
    public static final int GRACE_TICKS = 40;

    public enum Verdict {
        /** The card shows the offer's own line, or there is nothing to compare: answerable. */
        ACTIONABLE,
        /** The offer's line has not been drawn yet: shown, not answerable. */
        WAITING,
        /** The line never came within the grace: answerable, and worth one debug line. */
        LAPSED,
        /** A later line of the same discussion is on screen: the offer is retired. */
        SUPERSEDED;

        public boolean answerable() {
            return this == ACTIONABLE || this == LAPSED;
        }
    }

    /**
     * The line the screen drew last.
     *
     * @param identity null for an untagged line
     * @param seq      0 for an untagged line
     */
    public record Displayed(UUID identity, long seq, boolean tagged) {
        public static Displayed untagged() {
            return new Displayed(null, 0L, false);
        }
    }

    private UtteranceAgreement() {
    }

    /**
     * @param offerSeq      the line the offer names, or -1 for none
     * @param offerIdentity the discussion the offer belongs to, or null when it names none
     * @param displayed     the line on screen, or null when this screen has drawn nothing yet
     * @param waitedTicks   how long the offer has been held
     */
    public static Verdict decide(long offerSeq, UUID offerIdentity, Displayed displayed, long waitedTicks,
                                 int graceTicks) {
        if (offerSeq < 0) {
            return Verdict.ACTIONABLE;
        }
        if (displayed != null && !displayed.tagged()) {
            return Verdict.ACTIONABLE;
        }
        boolean sameDiscussion = displayed != null && offerIdentity != null
                && offerIdentity.equals(displayed.identity());
        if (sameDiscussion && displayed.seq() == offerSeq) {
            return Verdict.ACTIONABLE;
        }
        if (sameDiscussion && displayed.seq() > offerSeq) {
            return Verdict.SUPERSEDED;
        }
        // Nothing drawn yet, the line still on its way, or the screen still showing the discussion
        // before this one: hold, then give in.
        return waitedTicks >= graceTicks ? Verdict.LAPSED : Verdict.WAITING;
    }
}
