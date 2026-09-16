package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.NpcSpeechAct;
import dev.otectus.mcaconversations.history.PrivacyLevel;

import java.util.Set;

/**
 * Who one utterance may reach, decided once, before it is scheduled, and never revisited.
 *
 * <p>Public chat replies used to be decided at delivery time from whatever the session's
 * <em>last</em> speech act happened to be, which is not necessarily the act of the line being
 * spoken: a staggered ambient line, a bystander's interjection, or a second line from the same turn
 * could be judged by another turn's metadata. An audience is therefore computed from the utterance
 * and its speaker, frozen into this record, and carried to delivery.
 *
 * <p>The precedence is deliberately one-directional. Explicit confidentiality and personal
 * disclosure always restrict; a sensitive static line restricts; and metadata that is missing,
 * unbound, or simply unknown restricts too. Only a line that has been <em>classified</em> as safe
 * to overhear ever reaches bystanders — silence about a line's privacy is never permission.
 */
public record UtteranceAudience(Scope scope, String basis) {

    public enum Scope {
        /** The player being spoken to, and nobody else. */
        PARTICIPANT_ONLY,
        /** The player, plus eligible players inside the addressed radius. */
        NEARBY
    }

    /**
     * The mod's own procedural chat lines: greetings, confusion, deflections and farewells. They
     * carry no personal content by construction — they are what a villager says when they have
     * <em>not</em> told you anything — so they are the explicit public classification.
     */
    private static final Set<String> PUBLIC_STATIC_LINES = Set.of(
            "chatmode.hail", "chatmode.hail_cold", "chatmode.attentive", "chatmode.busy",
            "chatmode.clarify", "chatmode.confused", "chatmode.hint", "chatmode.shrug",
            "chatmode.babble", "chatmode.dropped", "chatmode.farewell", "chatmode.muted");

    public UtteranceAudience {
        scope = scope == null ? Scope.PARTICIPANT_ONLY : scope;
        basis = basis == null ? "unclassified" : basis;
    }

    public boolean bystandersMayHear() {
        return scope == Scope.NEARBY;
    }

    public static UtteranceAudience participantOnly(String basis) {
        return new UtteranceAudience(Scope.PARTICIPANT_ONLY, basis);
    }

    public static UtteranceAudience nearby(String basis) {
        return new UtteranceAudience(Scope.NEARBY, basis);
    }

    /**
     * Classifies one of the mod's own static lines by its lang key. An unlisted key is a line whose
     * content this table does not know, and an unknown line is private.
     */
    public static UtteranceAudience ofStaticLine(String key) {
        if (key != null && PUBLIC_STATIC_LINES.contains(key)) {
            return nearby("static line " + key);
        }
        return participantOnly("unclassified static line " + key);
    }

    /**
     * Classifies a line the dialogue engine produced.
     *
     * @param privacy   the privacy of the episode this exchange belongs to, or null when unknown
     * @param act       what the villager's line does, or null when unknown
     * @param boundToSpeaker whether {@code privacy} and {@code act} were read from the very exchange
     *                       this villager is speaking in. False for a bystander's interjection or any
     *                       other line whose metadata belongs to somebody else's turn.
     */
    public static UtteranceAudience ofDialogue(PrivacyLevel privacy, NpcSpeechAct act, boolean boundToSpeaker) {
        if (!boundToSpeaker) {
            return participantOnly("metadata belongs to another turn");
        }
        if (act == NpcSpeechAct.DISCLOSE || act == NpcSpeechAct.DISCLOSE_PROBLEM) {
            return participantOnly("personal disclosure");
        }
        if (privacy == null) {
            return participantOnly("unclassified dialogue");
        }
        if (privacy != PrivacyLevel.PUBLIC) {
            return participantOnly("confidence recorded as " + privacy);
        }
        return nearby("dialogue classified public");
    }
}
