package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * How the villager took what the player just said (spec §5.4).
 *
 * <p>An outcome family is attached to the villager's <em>reaction</em>, and its whole purpose is to
 * constrain what comes next. The rule the old graph broke is simple and absolute: a reaction that
 * rejects, wounds or shuts the player out may not open a page written for a villager who is enjoying
 * the conversation. It may only open apology, clarification, respect for the boundary, a change of
 * subject, or the door.
 */
public enum OutcomeFamily {

    /** Took the point and moved on with it. */
    ACCEPTED("accepted", false),
    /** Was glad of it — thanks, warmth, a compliment returned. */
    APPRECIATED("appreciated", false),
    /** Ran with it: told more, asked back, opened up further. */
    ENGAGED("engaged", false),
    /** Agreed with a correction attached. Invites the player to adjust. */
    QUALIFIED("qualified", false),
    /** Took it the wrong way, or the player's meaning did not land. Invites clarification. */
    MISUNDERSTOOD("misunderstood", false),
    /** Disagreed and held their ground. The subject continues, the stance does not. */
    RESISTED("resisted", false),

    // --- Ruptures ------------------------------------------------------------------

    /** Rejected the stance outright. Warmth is not available on the next page. */
    REBUFFED("rebuffed", true),
    /** The player caused real hurt. Repair or space; nothing else. */
    HURT("hurt", true),
    /** The subject is closed by the villager's explicit choice. */
    BOUNDARY_CLOSED("boundary_closed", true),
    /** They are done talking. The only continuation is leaving. */
    CONVERSATION_ENDED("conversation_ended", true);

    private final String key;
    private final boolean rupture;

    OutcomeFamily(String key, boolean rupture) {
        this.key = key;
        this.rupture = rupture;
    }

    public String key() {
        return key;
    }

    /**
     * True when the next node must be a repair page. The outcome-routing lint refuses to let a
     * rupture reach a node that offers pressure, intimacy, humour at the villager's expense, or more
     * probing without an intervening repair (spec §5.5 rules 6 and 7).
     */
    public boolean isRupture() {
        return rupture;
    }

    /**
     * The stance families a rupture page may offer: say sorry, say what you meant, back off, leave —
     * or press anyway.
     *
     * <p>That last one is deliberate. Spec §10.3 asks that a hard boundary be met with respect
     * <em>or with explicit violation carrying an honest consequence</em>, and the corpus already
     * models exactly that: pushing a villager who has just refused earns a scar that later
     * conversations read back. Removing the option would not make the mod kinder, only shallower.
     * What stays forbidden is the warm continuation — humour, encouragement, an offer of help, a
     * confidence of one's own — which is what makes a page assume the rupture did not happen.
     */
    public static boolean mayFollowRupture(StanceFamily stance) {
        return stance == StanceFamily.EMPATHY
                || stance == StanceFamily.CANDOR
                || stance == StanceFamily.RESTRAINT
                || stance == StanceFamily.CURIOSITY
                || stance == StanceFamily.BOUNDARY_PUSH
                || stance == StanceFamily.EXIT;
    }

    public static Optional<OutcomeFamily> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (OutcomeFamily family : values()) {
            if (family.key.equals(normalized)) {
                return Optional.of(family);
            }
        }
        return Optional.empty();
    }
}
