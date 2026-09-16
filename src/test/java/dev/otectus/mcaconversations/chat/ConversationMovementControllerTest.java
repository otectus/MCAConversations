package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.ConversationMovementController.Situation;
import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.conversation.ConversationHandle;
import dev.otectus.mcaconversations.conversation.ConversationLifecycle;
import dev.otectus.mcaconversations.conversation.ConversationPresence;
import dev.otectus.mcaconversations.conversation.OpenRateLimiter;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.chat.ConversationMovementController.Stance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The movement policy, exercised as the pure function it was extracted to be.
 *
 * <p>No test here can prove a villager stopped walking — that needs a running client, and the spec
 * says so outright (§13.1). What it can prove is every decision that leads to the brain call: that a
 * hurt villager is revoked rather than waited out, that a fleeing one is never pinned, that a glance
 * is cosmetic, and that {@code holdVillagerDuringInteraction = false} gives up movement suppression
 * without giving up facing. Those are exactly the rules the reported bugs came from.
 */
class ConversationMovementControllerTest {

    /** A live, safe pair in an ordinary discussion with the defaults. */
    private static Situation talking() {
        return new Situation(true, true, false, false, true, true, true);
    }

    @Test
    @DisplayName("an ordinary discussion holds the villager still and facing the player")
    void ordinaryDiscussionHolds() {
        assertEquals(Stance.HOLD, ConversationMovementController.decide(talking()));
    }

    @Test
    @DisplayName("holdVillagerDuringInteraction = false keeps the facing and gives up the hold")
    void configuredOffMeansFacingOnly() {
        Situation s = new Situation(true, true, false, false, true, false, true);
        assertEquals(Stance.FACE, ConversationMovementController.decide(s),
                "turning the hold off must not also turn off looking at the player");
    }

    @Test
    @DisplayName("a passing glance never stops a villager walking")
    void glancesAreCosmetic() {
        Situation glance = new Situation(true, true, false, false, false, true, true);
        assertEquals(Stance.FACE, ConversationMovementController.decide(glance));
    }

    @Test
    @DisplayName("a freshly hurt villager is revoked, not skipped for a tick")
    void damageRevokes() {
        Situation hurt = new Situation(true, true, true, false, true, true, true);
        Stance stance = ConversationMovementController.decide(hurt);
        assertEquals(Stance.REVOKE_ATTACKED, stance);
        assertTrue(stance.revokes(), "the discussion ends; the hold cannot resume when hurtTime does");
    }

    @Test
    @DisplayName("damage outranks everything, including a server that never interrupts on danger")
    void damageRevokesEvenWithDangerInterruptionOff() {
        Situation hurt = new Situation(true, true, true, true, true, true, false);
        assertEquals(Stance.REVOKE_ATTACKED, ConversationMovementController.decide(hurt));
    }

    @Test
    @DisplayName("a panicking villager ends the discussion when the server interrupts on danger")
    void panicRevokesWhenConfigured() {
        Situation panicking = new Situation(true, true, false, true, true, true, true);
        assertEquals(Stance.REVOKE_DANGER, ConversationMovementController.decide(panicking));
    }

    @Test
    @DisplayName("with danger interruption off a fleeing villager is left alone, never pinned")
    void panicIsNeverPinned() {
        Situation panicking = new Situation(true, true, false, true, true, true, false);
        Stance stance = ConversationMovementController.decide(panicking);
        assertEquals(Stance.LEAVE, stance);
        assertFalse(stance.revokes(), "the discussion stands; only the hold steps aside");
    }

    @Test
    @DisplayName("a villager or player who is not there drops the hold")
    void absenceDropsTheHold() {
        assertEquals(Stance.DROP, ConversationMovementController.decide(
                new Situation(false, true, false, false, true, true, true)));
        assertEquals(Stance.DROP, ConversationMovementController.decide(
                new Situation(true, false, false, false, true, true, true)));
    }

    // --- Who owns which hold (spec §5.1) -----------------------------------------------------

    @Test
    @DisplayName("a live graphical discussion's hold survives chat mode being switched off")
    void guiHoldsSurviveChatModeBeingOff() {
        UUID player = UUID.nameUUIDFromBytes("hold-player".getBytes());
        UUID villager = UUID.nameUUIDFromBytes("hold-villager".getBytes());
        UUID passerby = UUID.nameUUIDFromBytes("hold-villager-2".getBytes());
        try {
            ConversationHandle gui = ConversationLifecycle.begin(player, villager,
                    "minecraft:overworld", ConversationSession.Frontend.GUI, 100).orElseThrow();
            VillagerAttention.hold(villager, player, 500, AttentionLedger.Source.CONVERSATION, gui);
            VillagerAttention.hold(passerby, player, 500, AttentionLedger.Source.TYPING);

            assertTrue(VillagerAttention.managed(VillagerAttention.activeHolds().get(villager)),
                    "the lifecycle tick owns a live GUI discussion's hold");
            assertEquals(1, VillagerAttention.releaseChatHolds(), "only the glance is chat mode's");
            assertTrue(VillagerAttention.activeHolds().containsKey(villager),
                    "a GUI conversation must keep working on a server with chat mode off");
            assertFalse(VillagerAttention.activeHolds().containsKey(passerby));

            // Once the discussion is retired its hold is nobody's to keep either.
            ConversationLifecycle.terminate(gui, CloseReason.CLIENT_CLOSED);
            assertFalse(VillagerAttention.activeHolds().containsKey(villager));
        } finally {
            ConversationLifecycle.clearTeardownHooks();
            ConversationSessions.clearAllForTesting();
            ConversationPresence.clear();
            OpenRateLimiter.clear();
            VillagerAttention.reset();
        }
    }
}
