package dev.otectus.mcaconversations.conversation;

import java.util.UUID;

/**
 * The one rule for "may this packet act on this discussion?", written as pure functions so the
 * ordering cases the spec demands (§7) can be tested without a server.
 *
 * <p>Every C2S packet on protocol 4 names the discussion it was sent from. That name is the only
 * thing separating an answer the player just clicked from one their previous conversation queued
 * before it was torn down — the two are identical otherwise, right down to the revision, and the
 * mod used to treat both as "this player's current exchange". So each arrival is judged here first,
 * and a judgement is deliberately four-valued rather than a boolean:
 *
 * <ul>
 *   <li>{@link Decision#ACCEPT} — the packet names the discussion the server currently has.</li>
 *   <li>{@link Decision#UNIDENTIFIED} — the packet names no discussion at all. Not a refusal: chat
 *       mode's ambient exchanges and MCA's own dialogue packets legitimately have no handle, and
 *       they keep being governed by the checks that governed them before handles existed.</li>
 *   <li>{@link Decision#NO_MANAGED_HANDLE} — the sender believes it is in a discussion the server has
 *       already ended. The close-before-delayed-answer case.</li>
 *   <li>{@link Decision#OBSOLETE} — the sender names a <em>different</em> discussion from the live
 *       one, including a different discussion between the same pair. The reopen and takeover cases.</li>
 * </ul>
 *
 * <p>The last two are refusals, and a refusal is never an ending: it retires the one request, and
 * the discussion the player is actually in is left exactly as it was (spec §4.4).
 */
public final class HandleAuthority {

    public enum Decision {
        ACCEPT,
        UNIDENTIFIED,
        NO_MANAGED_HANDLE,
        OBSOLETE;

        /** True when the request may proceed — either owned, or unidentified and judged the old way. */
        public boolean allowed() {
            return this == ACCEPT || this == UNIDENTIFIED;
        }

        /** True when the request names a discussion that is not the live one. */
        public boolean stale() {
            return this == NO_MANAGED_HANDLE || this == OBSOLETE;
        }
    }

    private HandleAuthority() {
    }

    /**
     * Judges a named discussion against the one that is live.
     *
     * @param current    the handle the server currently has for the sender, or null
     * @param sessionId  the discussion the packet names, or null when it names none
     * @param villagerId the villager the packet names, or null when it names none
     */
    public static Decision judge(ConversationHandle current, UUID sessionId, UUID villagerId) {
        if (sessionId == null) {
            return Decision.UNIDENTIFIED;
        }
        if (current == null) {
            return Decision.NO_MANAGED_HANDLE;
        }
        if (!current.sessionId().equals(sessionId)) {
            return Decision.OBSOLETE;
        }
        // A session id is unique per discussion, so a villager that disagrees with it is either a
        // fabricated packet or a client one step behind. Either way it is not this discussion.
        return villagerId != null && !current.villagerId().equals(villagerId)
                ? Decision.OBSOLETE : Decision.ACCEPT;
    }

    /** As {@link #judge}, against whatever discussion this player is registered in right now. */
    public static Decision judgeFor(UUID playerId, UUID sessionId, UUID villagerId) {
        return judge(ConversationPresence.ofPlayer(playerId).orElse(null), sessionId, villagerId);
    }

    /**
     * Whether MCA's own tokenless interaction close may run.
     *
     * <p>MCA's close request carries a villager and nothing else, and its handler ends that
     * villager's interaction without asking whether the sender is the player it belongs to. Once a
     * villager can change hands, that is a player closing somebody else's conversation by closing
     * their own stale window.
     *
     * @param owner          the discussion that owns the villager, or null when none does
     * @param senderPlayerId the player whose connection the close arrived on
     * @return true when MCA may proceed: nobody manages this villager, or the sender is its owner
     */
    public static boolean allowsNativeClose(ConversationHandle owner, UUID senderPlayerId) {
        return owner == null || owner.playerId().equals(senderPlayerId);
    }

    /** As {@link #allowsNativeClose}, against the live owner of {@code villagerId}. */
    public static boolean allowsNativeCloseOf(UUID villagerId, UUID senderPlayerId) {
        return allowsNativeClose(ConversationPresence.ofVillager(villagerId).orElse(null), senderPlayerId);
    }
}
