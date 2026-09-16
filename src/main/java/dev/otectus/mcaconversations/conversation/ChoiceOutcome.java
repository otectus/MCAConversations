package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.network.ChoiceClearS2C;

/**
 * What happened to one attempted choice — the single vocabulary every frontend answers a numbered
 * or clicked response with.
 *
 * <p>Every rejection used to collapse into one {@code EXPIRED} clear, so a player whose answer was
 * refused because the villager had walked off, because a reload replaced the catalog, or because the
 * action itself threw, was told the same untrue thing. The outcomes below keep those apart, and each
 * one names the explanation the client is allowed to show ({@link #wireReason()}).
 *
 * <p>The last four are <em>internal</em> distinctions: they are worth logging and testing separately
 * but deliberately reach the client as an existing explanation, because "the offer you answered is
 * no longer the live one" is the truthful thing to say about all of them and the difference between
 * them is a server-side diagnosis, not a player-facing one.
 */
public enum ChoiceOutcome {

    /** The answer was claimed and MCA's engine ran it. */
    CONSUMED(ChoiceClearS2C.Reason.CONSUMED, false),
    /** A datapack reload replaced the content the offer was minted from. */
    CONTENT_RELOADED(ChoiceClearS2C.Reason.CONTENT_RELOADED, false),
    /** The villager is gone, dead, asleep, or busy with somebody else. */
    SPEAKER_UNAVAILABLE(ChoiceClearS2C.Reason.SPEAKER_UNAVAILABLE, false),
    /** The pair drifted apart between the offer and the reply. */
    OUT_OF_RANGE(ChoiceClearS2C.Reason.OUT_OF_RANGE, false),
    /** The answer's constraints or age gate no longer accept this pair. */
    REQUIREMENTS_CHANGED(ChoiceClearS2C.Reason.REQUIREMENTS_CHANGED, false),
    /** Execution threw, or the reflective call reported failure. Terminal. */
    EXECUTION_FAILED(ChoiceClearS2C.Reason.EXECUTION_FAILED, true),

    /** Internal: a chat offer aged out with its session. */
    TIMED_OUT(ChoiceClearS2C.Reason.EXPIRED, false),
    /** Internal: the feature driving this frontend was switched off mid-exchange. */
    FEATURE_DISABLED(ChoiceClearS2C.Reason.SPEAKER_UNAVAILABLE, false),
    /** Internal: no live offer, a stale revision, an out-of-range index, or a replayed packet. */
    INVALID_SUBMISSION(ChoiceClearS2C.Reason.EXPIRED, false),
    /** Internal: the offer belongs to another villager, or the villager to another player. */
    OWNERSHIP_MISMATCH(ChoiceClearS2C.Reason.EXPIRED, false),
    /**
     * Internal: the packet named a discussion that is not the live one — a click from a window the
     * player has already closed, or from the exchange before the one they are in now.
     *
     * <p>Emphatically not terminal. The straggler is refused and its own card retired; the
     * conversation the player is actually having is left exactly as it was (spec §4.4).
     */
    OBSOLETE_HANDLE(ChoiceClearS2C.Reason.EXPIRED, false);

    private final ChoiceClearS2C.Reason wireReason;
    private final boolean terminal;

    ChoiceOutcome(ChoiceClearS2C.Reason wireReason, boolean terminal) {
        this.wireReason = wireReason;
        this.terminal = terminal;
    }

    /** The explanation this outcome is allowed to put on screen. */
    public ChoiceClearS2C.Reason wireReason() {
        return wireReason;
    }

    public boolean ok() {
        return this == CONSUMED;
    }

    /**
     * True when this outcome ends the exchange rather than merely refusing one reply. Only a
     * contained failure does: a duplicate, stale or ineligible submission leaves a perfectly valid
     * session standing, and closing it would punish the player for a packet they did not send.
     */
    public boolean terminal() {
        return terminal;
    }

    /** The reason the session closes for, or empty when nothing should be torn down. */
    public java.util.Optional<CloseReason> closeReason() {
        return terminal ? java.util.Optional.of(CloseReason.CONTAINED_ERROR) : java.util.Optional.empty();
    }

    /** Translates an engagement refusal into the outcome its reply is rejected with. */
    public static ChoiceOutcome of(EngagementPolicy.Verdict verdict) {
        if (verdict == null || verdict.ok()) {
            return CONSUMED;
        }
        return verdict == EngagementPolicy.Verdict.OUT_OF_RANGE ? OUT_OF_RANGE : SPEAKER_UNAVAILABLE;
    }
}
