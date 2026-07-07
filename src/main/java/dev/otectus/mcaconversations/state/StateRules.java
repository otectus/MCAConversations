package dev.otectus.mcaconversations.state;

import dev.otectus.mcaconversations.gossip.GossipEventType;

import java.util.Optional;

/**
 * Pure mapping from world events to the {@link ConversationState} they induce. No Minecraft types, so
 * it is unit-testable in isolation; the apply side ({@link StateTracker}) and the write-hooks
 * ({@code GiftTracker}, {@code GossipDetectors}, {@code ConversationsQuestsEvents}, the hurt handler)
 * consult these rules and then persist the result.
 */
public final class StateRules {

    private StateRules() {
    }

    /**
     * The ambient state a gossip-worthy village event induces in onlookers, if any. A death makes
     * residents {@link ConversationState#GRIEVING}; a birth or marriage makes them
     * {@link ConversationState#ELATED}. Divorce and quest events induce no ambient mood.
     */
    public static Optional<ConversationState> forGossip(GossipEventType type) {
        if (type == null) {
            return Optional.empty();
        }
        return switch (type) {
            case DEATH -> Optional.of(ConversationState.GRIEVING);
            case BIRTH, MARRIAGE -> Optional.of(ConversationState.ELATED);
            default -> Optional.empty();
        };
    }

    /** A completed quest makes the giver {@link ConversationState#PROUD}; a failed one, {@link ConversationState#ANNOYED}. */
    public static ConversationState forQuest(boolean completed) {
        return completed ? ConversationState.PROUD : ConversationState.ANNOYED;
    }
}
