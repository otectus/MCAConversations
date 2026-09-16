package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.AttentionLedger;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What an attack does to a discussion (spec §1.1 item 4, §5.3).
 *
 * <p>Three promises, and the third is the one the old code broke: one blow ends the discussion
 * <em>once</em>, the villager is left alone for a few seconds afterwards, and the ending is a real
 * ending — there is no paused hold anywhere that a finished hurt animation could bring back to life.
 */
class ConversationDangerTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("danger-player".getBytes());
    private static final UUID OTHER = UUID.nameUUIDFromBytes("danger-player-2".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("danger-villager".getBytes());
    private static final String DIM = "minecraft:overworld";

    private final List<CloseReason> closed = new ArrayList<>();

    @BeforeEach
    @AfterEach
    void reset() {
        closed.clear();
        ConversationLifecycle.clearTeardownHooks();
        ConversationSessions.clearAllForTesting();
        ConversationPresence.clear();
        DangerLockout.clear();
        VillagerAttention.reset();
        ChatModeScheduler.reset();
    }

    private ConversationHandle begin(UUID player, long now) {
        ConversationHandle handle = ConversationLifecycle
                .begin(player, VILLAGER, DIM, ConversationSession.Frontend.GUI, now).orElseThrow();
        VillagerAttention.hold(VILLAGER, player, now + 40, AttentionLedger.Source.CONVERSATION, handle);
        return handle;
    }

    private void watchClosures() {
        ConversationLifecycle.addTeardownHook((handle, reason) -> closed.add(reason));
    }

    @Test
    @DisplayName("the attack event and the damage event of one blow end the discussion once")
    void oneIncidentEndsTheDiscussionOnce() {
        begin(PLAYER, 100);
        watchClosures();

        // The blow: the loader raises an attack, then the damage that followed it, on the same tick.
        assertTrue(ConversationDanger.onAttacked(null, VILLAGER, 100),
                "the first half of the blow interrupts the discussion");
        assertFalse(ConversationDanger.onAttacked(null, VILLAGER, 100),
                "the second half is the same incident and must not be a second interruption");

        assertEquals(List.of(CloseReason.ATTACKED), closed, "exactly one teardown, reported as ATTACKED");
        assertEquals(0, ConversationPresence.size());
        assertFalse(VillagerAttention.activeHolds().containsKey(VILLAGER), "the villager is free");
    }

    @Test
    @DisplayName("a later blow is a new incident, even though there is nothing left to interrupt")
    void alaterBlowIsANewIncident() {
        begin(PLAYER, 100);
        assertTrue(ConversationDanger.onAttacked(null, VILLAGER, 100));
        assertFalse(ConversationDanger.onAttacked(null, VILLAGER, 140),
                "nobody is talking to them now, so nothing is interrupted");
        assertTrue(DangerLockout.locked(VILLAGER, 200),
                "but the second blow still pushed the re-open delay out");
    }

    @Test
    @DisplayName("the villager refuses a new discussion until the re-open delay has run")
    void theReopenDelayIsEnforcedAtBegin() {
        begin(PLAYER, 100);
        ConversationDanger.onAttacked(null, VILLAGER, 100);

        int delay = McaConversationsConfig.attackReopenDelayTicks();
        assertEquals(100, delay, "the shipped default the rest of this case assumes");

        assertTrue(ConversationLifecycle.begin(PLAYER, VILLAGER, DIM,
                ConversationSession.Frontend.GUI, 100 + delay - 1).isEmpty(),
                "the player who just swung cannot immediately pin the villager again");
        assertTrue(ConversationLifecycle.begin(OTHER, VILLAGER, DIM,
                ConversationSession.Frontend.GUI, 100 + delay - 1).isEmpty(),
                "and neither can a bystander: the lockout belongs to the villager");
        assertEquals(0, ConversationPresence.size());

        assertTrue(ConversationLifecycle.begin(PLAYER, VILLAGER, DIM,
                ConversationSession.Frontend.GUI, 100 + delay).isPresent(),
                "once the delay has run the villager talks again");
    }

    @Test
    @DisplayName("an interrupted discussion cannot come back when the hurt animation ends")
    void thereIsNothingLeftToResume() {
        ConversationHandle attacked = begin(PLAYER, 100);
        ConversationDanger.onAttacked(null, VILLAGER, 100);

        // Whatever the villager's hurtTime does from here, no state remains that names this handle.
        assertFalse(ConversationPresence.isCurrent(attacked));
        assertTrue(ConversationLifecycle.terminate(attacked, CloseReason.CLIENT_CLOSED).isEmpty());
        assertFalse(VillagerAttention.activeHolds().containsKey(VILLAGER));

        ConversationHandle fresh = begin(PLAYER, 400);
        assertNotEquals(attacked, fresh, "talking again after an attack is a new discussion");
        assertTrue(ConversationPresence.isCurrent(fresh));
        AttentionLedger.Hold hold = VillagerAttention.activeHolds().get(VILLAGER);
        assertEquals(fresh, hold.owner(), "and the new hold belongs to the new discussion");
    }

    @Test
    @DisplayName("interrupting a villager nobody is talking to still starts the re-open delay")
    void hittingAnUnengagedVillagerOnlyLocksThemOut() {
        watchClosures();
        assertFalse(ConversationDanger.onAttacked(null, VILLAGER, 100),
                "no discussion existed, so none was interrupted");
        assertTrue(closed.isEmpty(), "and nothing was torn down");
        assertTrue(DangerLockout.locked(VILLAGER, 150));
    }

    @Test
    @DisplayName("ongoing danger ends the discussion as DANGER rather than as an attack")
    void panicClosesAsDanger() {
        begin(PLAYER, 100);
        watchClosures();
        assertTrue(ConversationDanger.onDanger(null, VILLAGER, 100));
        assertEquals(List.of(CloseReason.DANGER), closed);
        assertTrue(DangerLockout.locked(VILLAGER, 150), "no automatic reopening follows danger either");
    }

    @Test
    @DisplayName("the shipped attacked-behaviour default leaves MCA's own reaction alone")
    void nativeCombatIsTheDefault() {
        assertEquals(McaConversationsConfig.AttackedBehavior.NATIVE_COMBAT,
                McaConversationsConfig.attackedBehavior(),
                "a guard fights back unless the server asks for RETREAT");
    }
}
