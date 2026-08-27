package dev.otectus.mcaconversations.history;

import java.util.Locale;
import java.util.Optional;

/**
 * How the game will find out whether a promise was kept (spec §8.5).
 *
 * <p>This enum exists to make failure mode 9 structurally impossible. A conversation may only offer
 * the player a button that says "I will bring you iron before tomorrow" if something in the running
 * game can actually observe iron arriving before tomorrow. Every commitment names one of these, the
 * build refuses a commitment reply that names none, and a promise with no observer must instead be
 * worded as willingness — "I'll try", "I'd like to help" — which is what {@link #MANUAL_NEUTRAL} is
 * for.
 *
 * <p>Each constant is a <em>capability</em>, not an implementation: {@link #isAvailable()} answers
 * whether the running install can observe it at all, so a commitment that depends on an absent
 * integration is filtered out before the button is ever offered rather than judged unfairly later
 * (spec §12.6).
 */
public enum CommitmentResolver {

    /**
     * The player gives the villager any item in a declared tag. Observed through the existing gift
     * path, which already runs on every accepted gift.
     */
    GIFT_TAG_RECEIVED("gift_tag_received", true),

    /**
     * An MCA: Quests objective completes or fails. Only available when that mod is installed; a
     * quest commitment simply never appears without it.
     */
    QUEST_STATE("quest_state", false),

    /** The player speaks to this villager again on or after a stated day. Always observable. */
    VISIT_AFTER_DAY("visit_after_day", true),

    /** The player returns and chooses a declared follow-up reply. Always observable. */
    CONVERSATION_CHOICE("conversation_choice", true),

    /** A supported event log records the relevant village change — a building, a birth, a death. */
    EVENT_OBSERVED("event_observed", true),

    /**
     * Acknowledged and never judged.
     *
     * <p>The honest escape hatch, and the only legal resolver for prose that cannot claim success or
     * failure. A {@code MANUAL_NEUTRAL} commitment is remembered as something that was said, and the
     * villager may refer to having discussed it — never to the player having kept or broken it.
     */
    MANUAL_NEUTRAL("manual_neutral", true);

    private final String key;
    private final boolean alwaysAvailable;

    CommitmentResolver(String key, boolean alwaysAvailable) {
        this.key = key;
        this.alwaysAvailable = alwaysAvailable;
    }

    public String key() {
        return key;
    }

    /**
     * True when this install can observe this resolver.
     *
     * <p>Checked at selection time rather than at load time, because a server can gain or lose an
     * optional mod between sessions and a commitment created under one install must degrade rather
     * than resolve wrongly under another.
     */
    public boolean isAvailable() {
        if (alwaysAvailable) {
            return true;
        }
        if (this == QUEST_STATE) {
            return dev.otectus.mcaconversations.compat.QuestsBridge.queries() != null;
        }
        return false;
    }

    /** True when this resolver may ever mark a commitment kept or broken. */
    public boolean isJudgeable() {
        return this != MANUAL_NEUTRAL;
    }

    public static Optional<CommitmentResolver> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (CommitmentResolver resolver : values()) {
            if (resolver.key.equals(normalized)) {
                return Optional.of(resolver);
            }
        }
        return Optional.empty();
    }
}
