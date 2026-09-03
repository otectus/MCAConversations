package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * The typed predicate a villager line puts under discussion (spec §10.1).
 *
 * <p>Version 1 contracts already record what a line <em>does</em> — {@code NpcSpeechAct} — and how it
 * feels. What they cannot say is what it is <em>about</em>, in a form a reply can target. "Tell me
 * more" is a legal answer to a status change and a wrong answer to a request; without a frame, the
 * routing lint has no way to know which it is looking at.
 *
 * <p>Optional. A v1 beat with no frame keeps its v1 behaviour exactly; the frame is only required of
 * a beat a dynamic scene routes to (spec §10).
 */
public enum DiscourseFrame {

    /** Something in the villager's work is wrong or stuck. */
    WORK_PROBLEM("work_problem"),

    /** A view the villager holds, which the player may share, question or contest. */
    OPINION("opinion"),

    /** Something that already happened, told as recollection. */
    MEMORY("memory"),

    /** Something intended, not yet begun. */
    PLAN("plan"),

    /** The villager wants something from the player. */
    REQUEST("request"),

    /** Something has changed since the last conversation. */
    STATUS_CHANGE("status_change"),

    /** A question the villager is asking the player about the player (spec §11.3). */
    ASK_BACK("ask_back"),

    /** Something about a named third person. */
    SOCIAL_REPORT("social_report"),

    /** Something the villager is being careful about saying. */
    DISCLOSURE("disclosure"),

    /** Repairing something that went wrong between the two of them. */
    REPAIR("repair"),

    /** Ordinary observation with no stake — weather, the hour, a passing remark. */
    OBSERVATION("observation");

    private final String key;

    DiscourseFrame(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /**
     * True when a line in this frame makes a response <em>relevant</em> rather than merely possible.
     *
     * <p>Used by the obligation lint: a frame that demands an answer must have a reply page containing
     * one, and three comments that ignore the question do not count (spec §20.4).
     */
    public boolean demandsResponse() {
        return this == REQUEST || this == ASK_BACK || this == REPAIR;
    }

    public static Optional<DiscourseFrame> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (DiscourseFrame frame : values()) {
            if (frame.key.equals(normalized)) {
                return Optional.of(frame);
            }
        }
        return Optional.empty();
    }
}
