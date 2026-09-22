package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.ConversationMovementController.Situation;
import dev.otectus.mcaconversations.chat.ConversationMovementController.Stance;
import dev.otectus.mcaconversations.chat.TownsteadChatPolicy.Facts;
import dev.otectus.mcaconversations.chat.TownsteadChatPolicy.Greeting;
import dev.otectus.mcaconversations.conversation.DepthClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Chat mode inside a Townstead day (Townstead spec §13). */
class TownsteadChatPolicyTest {

    private static final Facts WORKING = new Facts(false, false, false, false, false, true, false, false);
    private static final Facts TIRED = new Facts(false, false, false, false, false, false, true, false);
    private static final Facts COLLAPSED = new Facts(false, false, false, true, true, false, false, false);
    private static final Facts LOCKED = new Facts(false, false, false, false, false, false, false, true);

    @Test
    @DisplayName("a greeting is suppressed for the asleep, the collapsed, the desperate and the busy; brief at work")
    void greetings() {
        assertEquals(Greeting.NORMAL, TownsteadChatPolicy.greeting(Facts.NONE), "Townstead absent: as before");
        assertEquals(Greeting.BRIEF, TownsteadChatPolicy.greeting(WORKING));
        assertEquals(Greeting.SUPPRESS, TownsteadChatPolicy.greeting(COLLAPSED));
        assertEquals(Greeting.SUPPRESS, TownsteadChatPolicy.greeting(
                new Facts(true, false, false, false, false, false, false, false)));
        assertEquals(Greeting.SUPPRESS, TownsteadChatPolicy.greeting(
                new Facts(false, false, true, false, false, false, false, false)), "another player's dialogue");
    }

    @Test
    @DisplayName("at work or worn out, deep topics wait; collapsed, only small talk; quick topics always answer")
    void addressedTopics() {
        for (Facts f : List.of(Facts.NONE, WORKING, TIRED, COLLAPSED)) {
            assertFalse(TownsteadChatPolicy.defersTopic(f, DepthClass.QUICK));
            assertFalse(TownsteadChatPolicy.defersTopic(f, DepthClass.SERVICE));
        }
        assertFalse(TownsteadChatPolicy.defersTopic(Facts.NONE, DepthClass.DEEP));
        assertTrue(TownsteadChatPolicy.defersTopic(WORKING, DepthClass.DEEP));
        assertTrue(TownsteadChatPolicy.defersTopic(TIRED, DepthClass.RELATIONSHIP));
        assertFalse(TownsteadChatPolicy.defersTopic(WORKING, DepthClass.STANDARD));
        assertTrue(TownsteadChatPolicy.defersTopic(COLLAPSED, DepthClass.STANDARD));
    }

    @Test
    @DisplayName("the collapsed and the mid-reaction never shout back; workers answer less, and at most one")
    void ambientResponders() {
        assertFalse(TownsteadChatPolicy.ambientEligible(COLLAPSED));
        assertFalse(TownsteadChatPolicy.ambientEligible(LOCKED));
        assertTrue(TownsteadChatPolicy.ambientEligible(WORKING));
        assertEquals(0.5, TownsteadChatPolicy.ambientWeight(WORKING));
        assertEquals(1.0, TownsteadChatPolicy.ambientWeight(Facts.NONE));

        List<AmbientSelection.Responder> picked = AmbientSelection.select(List.of(
                new AmbientSelection.Responder(0, 0.9, 4, true),
                new AmbientSelection.Responder(1, 0.8, 4, true),
                new AmbientSelection.Responder(2, 0.7, 4, false),
                new AmbientSelection.Responder(3, 0.6, 4, true)), 3, TownsteadChatPolicy.MAX_WORKING_RESPONDERS);
        assertEquals(List.of(0, 2), picked.stream().map(AmbientSelection.Responder::candidateIndex).toList());
    }

    @Test
    @DisplayName("attention never fights a collapse or a reaction lock, and only looks at a villager at work")
    void attention() {
        Situation locked = new Situation(true, true, false, false, true, true, true, true, false);
        assertEquals(Stance.LEAVE, ConversationMovementController.decide(locked));
        Situation working = new Situation(true, true, false, false, true, true, true, false, true);
        assertEquals(Stance.FACE, ConversationMovementController.decide(working));
        Situation hurtAndLocked = new Situation(true, true, true, false, true, true, true, true, false);
        assertEquals(Stance.REVOKE_ATTACKED, ConversationMovementController.decide(hurtAndLocked),
                "damage still outranks everything");
        assertEquals(Stance.HOLD, ConversationMovementController.decide(
                new Situation(true, true, false, false, true, true, true)), "Townstead absent: as before");
    }
}
