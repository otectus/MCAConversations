package dev.otectus.mcaconversations.conversation;

/**
 * Why a {@link ConversationSession} was torn down. One vocabulary for every path that ends a
 * conversation, so a stuck attention lease or an orphaned queued line can be traced to the exact
 * ending that leaked it (plan §WP08).
 *
 * <p>Strictly operational: a reason states what happened to the pair, never what it means for them.
 * Nothing here feeds hearts, memories, or disposition — {@link #PLAYER_LEFT} is not a snub and
 * {@link #TIMED_OUT} is not boredom. A close reason is diagnostic vocabulary, and code that reads
 * one to decide a social consequence is reading it wrong. An attack or a danger interruption is
 * still an operational ending: the villager fled, and the player has not thereby been snubbed.
 *
 * <p>Every constant carries an explicit {@link #wireId()}. Ordinals are never used on the wire or in
 * a log the next version has to keep reading, so a reason may be inserted anywhere in this list and
 * a client one version behind still reads the rest correctly. Ids are permanent: change one and an
 * old close becomes a different ending.
 *
 * <p>Note the deliberate vocabulary clash with {@link EngagementPolicy.Verdict}: there "speaker" is
 * the <em>player</em> sending the message, here the speaker is the <em>villager</em> doing the
 * talking. {@link #of(EngagementPolicy.Verdict)} is the one place the two are translated, so no
 * caller has to remember which word means which side.
 */
public enum CloseReason {

    /** The exchange reached its natural end — a farewell, or MCA's dialogue {@code END} op. */
    COMPLETED(0),
    /** The player died, or otherwise stopped being present while still connected. */
    PLAYER_LEFT(1),
    /** The villager being talked to died or was removed. */
    SPEAKER_DEAD(2),
    /** The villager is alive but can no longer hold the conversation (asleep, panicking, gone). Reserved: nothing closes for this reason yet. */
    SPEAKER_UNAVAILABLE(3),
    /** The pair drifted outside the conversation radius. */
    OUT_OF_RANGE(4),
    /** One of the pair changed dimension. */
    DIMENSION_CHANGED(5),
    /** The player logged out or their connection dropped. */
    DISCONNECTED(6),
    /** The session sat idle well past the configured timeout and was swept. */
    TIMED_OUT(7),
    /** A datapack reload replaced the content the open offer was minted from. */
    CONTENT_RELOADED(8),
    /** The feature driving the conversation was switched off mid-exchange. Reserved: nothing closes for this reason yet. */
    FEATURE_DISABLED(9),
    /** The offer being answered did not exist, was consumed, or belonged to someone else. Reserved: the offer paths reject without closing. */
    INVALID_OFFER(10),
    /**
     * A contained failure ended the exchange rather than propagating: the dialogue engine threw, or
     * reported that it had not run the answer. Purely technical — like every reason here it carries
     * no social meaning, and a player whose action failed has not slighted anybody.
     */
    CONTAINED_ERROR(11),

    /** The player dismissed the interaction screen. Their own choice, and nothing more than that. */
    CLIENT_CLOSED(12),
    /** The player turned to a different villager; this discussion ends so the next one can own them. */
    TARGET_CHANGED(13),
    /** Another player's accepted interaction took the villager over (spec §6). */
    TAKEN_OVER(14),
    /** Somebody hit the villager, or the villager hit somebody: the exchange stops at once. */
    ATTACKED(15),
    /** The villager left the loaded world without dying — chunk unload, teleport, or a passenger ride. */
    ENTITY_UNLOADED(16),
    /** The villager is in immediate danger and must be free to flee; no reopening follows from here. */
    DANGER(17);

    private final int wireId;

    CloseReason(int wireId) {
        this.wireId = wireId;
    }

    /** The permanent identifier of this ending — never {@link #ordinal()}. */
    public int wireId() {
        return wireId;
    }

    /** The ending with this id, or empty when a peer named one this version does not know. */
    public static java.util.Optional<CloseReason> byWireId(int wireId) {
        for (CloseReason reason : values()) {
            if (reason.wireId == wireId) {
                return java.util.Optional.of(reason);
            }
        }
        return java.util.Optional.empty();
    }

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
