package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * What a villager line <em>does</em>, as opposed to what it is about (spec §5.2).
 *
 * <p>Topic labels — "work", "life", "people" — are the reason the old graph could route a proud
 * craft explanation and a burnt-out complaint into the same page of replies: both were "about work".
 * The speech act is the axis that actually decides which replies are sensible. You may commiserate
 * with a {@link #COMPLAIN} and you may not commiserate with a {@link #CELEBRATE}; you may ask a
 * follow-up after a {@link #DISCLOSE} and you may not after a {@link #SET_BOUNDARY}.
 *
 * <p>Every act declares whether it is a <em>rupture</em> — a line whose whole point is that the
 * exchange has gone wrong. Rupture acts are what the outcome-routing lint uses to prove that being
 * rebuffed never lands the player on a page that assumes warmth.
 */
public enum NpcSpeechAct {

    /** States a plain fact about the world or the villager's day. Neutral, answerable, low stakes. */
    REPORT("report", false),
    /** Explains a craft, a method, a belief, or how something works. Invites curiosity. */
    EXPLAIN("explain", false),
    /** Says something personal they would not tell everyone. Invites care, not analysis. */
    DISCLOSE("disclose", false),
    /** Names a difficulty that is currently theirs to carry. Invites help, sympathy or perspective. */
    DISCLOSE_PROBLEM("disclose_problem", false),
    /** Grumbles. Wants acknowledgement more often than solutions. */
    COMPLAIN("complain", false),
    /** Shares good news or pride. A help offer here is a non-sequitur. */
    CELEBRATE("celebrate", false),
    /** Puts a question to the player and expects it answered. */
    ASK("ask", false),
    /** Offers the player something: company, a favour, a place, a gift. */
    INVITE("invite", false),
    /** Looks back at something that already happened. */
    REMINISCE("reminisce", false),
    /** Asks the player for practical help with a named thing. */
    REQUEST_HELP("request_help", false),
    /** Describes work that needs doing, without promising the player a task. */
    OFFER_WORK("offer_work", false),
    /** Accepts what the player just said and moves with it. */
    ACCEPT("accept", false),
    /** Agrees in part, corrects in part. Invites clarification. */
    QUALIFY("qualify", false),
    /** Takes the point back: disagrees, pushes back, holds their ground. Not yet a rupture. */
    RESIST("resist", false),
    /** Declines to answer, without hostility. Guarded, still willing to talk about something else. */
    DEFLECT("deflect", false),

    // --- Ruptures ------------------------------------------------------------------

    /** Rejects the player's line outright. The subject is not going to continue as it was. */
    REBUFF("refuse", true),
    /** Says, in some form, that the player has hurt them. */
    HURT("hurt", true),
    /** Closes the subject explicitly: do not ask again. */
    SET_BOUNDARY("set_boundary", true),
    /** Ends the exchange. Nothing follows but leaving. */
    DISMISS("dismiss", true);

    private final String key;
    private final boolean rupture;

    NpcSpeechAct(String key, boolean rupture) {
        this.key = key;
        this.rupture = rupture;
    }

    public String key() {
        return key;
    }

    /**
     * True when the line's meaning is that the exchange has gone wrong. Rupture acts may only open a
     * response page built for repair, respect or leaving (spec §5.4).
     */
    public boolean isRupture() {
        return rupture;
    }

    public static Optional<NpcSpeechAct> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (NpcSpeechAct act : values()) {
            if (act.key.equals(normalized)) {
                return Optional.of(act);
            }
        }
        return Optional.empty();
    }
}
