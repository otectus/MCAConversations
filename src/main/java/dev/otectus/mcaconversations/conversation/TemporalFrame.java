package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.history.EpisodeState;

import java.util.Locale;
import java.util.Optional;

/**
 * When the thing a line is about takes place (spec §10.1, §10.3).
 *
 * <p>Exists so that "still", "again", "yet", "finally", "before" and "after" can be checked rather
 * than trusted. Those six words are the whole of failure mode 5: "tomorrow's harvest" after the
 * deadline, or "again" on a first encounter, reads as a bug to a player even though every individual
 * line is perfectly written.
 *
 * <p>{@link #fits} is the check the lint runs. It is deliberately conservative — a frame and a state
 * that <em>could</em> disagree are treated as disagreeing, because a false positive costs an author
 * one annotation and a false negative ships a non-sequitur.
 */
public enum TemporalFrame {

    /** Over and done with. Past-tense prose; "still" and "yet" are wrong here. */
    PAST("past"),

    /** Happening now. "Still" is legal; "finally" needs a resolution. */
    CURRENT("current"),

    /** Not yet. "Tomorrow" is legal only while a deadline is still ahead. */
    FUTURE("future"),

    /** How things generally are. Tenseless; compatible with any episode state. */
    HABITUAL("habitual");

    private final String key;

    TemporalFrame(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /**
     * True when this frame can truthfully describe an episode in {@code state}.
     *
     * <p>{@link #HABITUAL} fits everything: "I always dread the damp season" is true whether this
     * year's damage is ongoing or long repaired.
     */
    public boolean fits(EpisodeState state) {
        if (state == null || this == HABITUAL) {
            return true;
        }
        return switch (this) {
            case PAST -> state.isPast();
            case CURRENT -> state.isLive();
            case FUTURE -> state == EpisodeState.PLANNED || state.isLive();
            case HABITUAL -> true;
        };
    }

    /**
     * True when this frame can truthfully describe an episode in {@code state}, given what the line is
     * <em>about</em>.
     *
     * <p>One case needs the predicate to decide. "Will you plant it again next year?" is future tense
     * asked about a finished harvest, and it is perfectly true: the plan is about the successor, not
     * about the episode that ended. What must stay forbidden is a {@code status_change} in future
     * tense on a finished episode, which would claim a thing that has already happened is still
     * ahead — failure mode 5 exactly. So a plan may look forward from a terminal state and nothing
     * else may.
     */
    public boolean fits(EpisodeState state, DiscourseFrame predicate) {
        if (state == null) {
            return true;
        }
        if (this == FUTURE && state.isPast()) {
            return predicate == DiscourseFrame.PLAN;
        }
        if (this == PAST && state.isLive()) {
            // Past-tense narration inside a live situation is ordinary: "I have been telling myself
            // it was the weather since spring" is about how an ongoing problem began. The one thing
            // it must not be is a status_change, which in past tense would claim the situation has
            // ended while it is still going on.
            return predicate != DiscourseFrame.STATUS_CHANGE;
        }
        return fits(state);
    }

    /** True when a deadline in the future is required for this frame to make sense. */
    public boolean needsFutureDeadline() {
        return this == FUTURE;
    }

    public static Optional<TemporalFrame> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (TemporalFrame frame : values()) {
            if (frame.key.equals(normalized)) {
                return Optional.of(frame);
            }
        }
        return Optional.empty();
    }
}
