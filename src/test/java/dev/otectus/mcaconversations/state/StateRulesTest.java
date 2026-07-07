package dev.otectus.mcaconversations.state;

import dev.otectus.mcaconversations.gossip.GossipEventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StateRulesTest {

    @Test
    void gossipEventsMapToAmbientMoods() {
        assertEquals(ConversationState.GRIEVING, StateRules.forGossip(GossipEventType.DEATH).orElseThrow());
        assertEquals(ConversationState.ELATED, StateRules.forGossip(GossipEventType.BIRTH).orElseThrow());
        assertEquals(ConversationState.ELATED, StateRules.forGossip(GossipEventType.MARRIAGE).orElseThrow());
    }

    @Test
    void neutralGossipEventsInduceNoMood() {
        assertTrue(StateRules.forGossip(GossipEventType.DIVORCE).isEmpty());
        assertTrue(StateRules.forGossip(GossipEventType.QUEST).isEmpty());
        assertTrue(StateRules.forGossip(null).isEmpty());
    }

    @Test
    void ambientGossipMoodsAreUnscoped() {
        // applyAmbient writes an unscoped id, so any gossip-induced state must be ambient.
        for (GossipEventType type : GossipEventType.values()) {
            StateRules.forGossip(type).ifPresent(s ->
                    assertTrue(!s.playerScoped(), type + " -> " + s + " must be ambient (unscoped)"));
        }
    }

    @Test
    void questOutcomeMapsToPrideOrAnnoyance() {
        assertEquals(ConversationState.PROUD, StateRules.forQuest(true));
        assertEquals(ConversationState.ANNOYED, StateRules.forQuest(false));
    }
}
