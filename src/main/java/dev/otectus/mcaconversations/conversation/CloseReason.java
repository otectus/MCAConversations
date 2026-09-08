package dev.otectus.mcaconversations.conversation;

/**
 * Why a {@link ConversationSession} was torn down. One vocabulary for every path that ends a
 * conversation, so a stuck attention lease or an orphaned queued line can be traced to the exact
 * ending that leaked it (plan §WP08).
 *
 * <p>Strictly operational: a reason states what happened to the pair, never what it means for them.
 * Nothing here feeds hearts, memories, or disposition — {@link #PLAYER_LEFT} is not a snub and
 * {@link #TIMED_OUT} is not boredom. A close reason is diagnostic vocabulary, and code that reads
 * one to decide a social consequence is reading it wrong.
 *
 * <p>Note the deliberate vocabulary clash with {@link EngagementPolicy.Verdict}: there "speaker" is
 * the <em>player</em> sending the message, here the speaker is the <em>villager</em> doing the
 * talking. {@link #of(EngagementPolicy.Verdict)} is the one place the two are translated, so no
 * caller has to remember which word means which side.
 */
public enum CloseReason {

    /** The exchange reached its natural end — a farewell, or MCA's dialogue {@code END} op. */
    COMPLETED,
    /** The player died, or otherwise stopped being present while still connected. */
    PLAYER_LEFT,
    /** The villager being talked to died or was removed. */
    SPEAKER_DEAD,
    /** The villager is alive but can no longer hold the conversation (asleep, panicking, gone). Reserved: nothing closes for this reason yet. */
    SPEAKER_UNAVAILABLE,
    /** The pair drifted outside the conversation radius. */
    OUT_OF_RANGE,
    /** One of the pair changed dimension. */
    DIMENSION_CHANGED,
    /** The player logged out or their connection dropped. */
    DISCONNECTED,
    /** The session sat idle well past the configured timeout and was swept. */
    TIMED_OUT,
    /** A datapack reload replaced the content the open offer was minted from. */
    CONTENT_RELOADED,
    /** The feature driving the conversation was switched off mid-exchange. Reserved: nothing closes for this reason yet. */
    FEATURE_DISABLED,
    /** The offer being answered did not exist, was consumed, or belonged to someone else. Reserved: the offer paths reject without closing. */
    INVALID_OFFER,
    /** A contained failure ended the exchange rather than propagating. Reserved: nothing closes for this reason yet. */
    CONTAINED_ERROR;

    /**
     * Translates an engagement refusal into the reason its session closes for.
     *
     * <p>The two sides swap names: {@link EngagementPolicy.Verdict#SPEAKER_DEAD} is the
     * <em>player</em> dying, which closes as {@link #PLAYER_LEFT}, while
     * {@link EngagementPolicy.Verdict#VILLAGER_DEAD} closes as {@link #SPEAKER_DEAD} because the
     * villager is the one who speaks. {@link EngagementPolicy.Verdict#OK} has no ending of its own;
     * it maps to {@link #COMPLETED} so a caller closing after a successful exchange needs no branch.
     */
    public static CloseReason of(EngagementPolicy.Verdict verdict) {
        if (verdict == null) {
            return COMPLETED;
        }
        return switch (verdict) {
            case OK -> COMPLETED;
            case SPEAKER_GONE -> DISCONNECTED;
            case SPEAKER_DEAD -> PLAYER_LEFT;
            case VILLAGER_DEAD -> SPEAKER_DEAD;
            case DIMENSION_CHANGED -> DIMENSION_CHANGED;
            case OUT_OF_RANGE -> OUT_OF_RANGE;
        };
    }
}
