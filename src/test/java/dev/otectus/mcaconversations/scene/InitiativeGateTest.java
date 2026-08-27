package dev.otectus.mcaconversations.scene;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The anti-spam policy, as rules rather than as a description (spec §11.2).
 *
 * <p>{@link InitiativeGate#decide} itself needs a live server — a mute session, a villager entity and
 * a history store — so what is checked here is the part of the policy that is pure: which busy states
 * refuse which purposes, which purposes spend the daily budget, and which single item gets surfaced
 * when several are outstanding. Those are the decisions that go wrong silently; the plumbing around
 * them fails loudly.
 */
class InitiativeGateTest {

    @Test
    @DisplayName("a sleeping villager is not available for anything but an emergency")
    void hardStatesRefuseEverythingButAcute() {
        for (BusyState state : List.of(BusyState.SLEEPING, BusyState.PANICKING,
                BusyState.FIGHTING, BusyState.WITH_ANOTHER_PLAYER)) {
            assertTrue(state.isHard(), state + " should be a hard state");
            assertTrue(state.suppresses(ScenePurpose.GREETING), state + " should refuse a greeting");
            assertTrue(state.suppresses(ScenePurpose.DUE_COMMITMENT),
                    state + " should refuse even a due promise");
            assertFalse(state.suppresses(ScenePurpose.ACUTE),
                    state + " must still allow \"you're bleeding\"");
        }
    }

    @Test
    @DisplayName("a soft state refuses small talk and still permits what matters")
    void softStatesRefuseOnlyTheExpensiveInitiatives() {
        for (BusyState state : List.of(BusyState.GRIEVING, BusyState.ON_A_CHORE)) {
            assertFalse(state.isHard());
            assertTrue(state.suppresses(ScenePurpose.LOW_STAKES),
                    "a remark about the rain can wait");
            assertTrue(state.suppresses(ScenePurpose.OPINION_REQUEST),
                    "so can being asked about a door handle");
            assertFalse(state.suppresses(ScenePurpose.DUE_COMMITMENT),
                    "a promise coming due cannot");
            assertFalse(state.suppresses(ScenePurpose.REPAIR));
            assertFalse(state.suppresses(ScenePurpose.SHARED_EVENT),
                    "news worth telling can be told to somebody weeding");
        }
    }

    @Test
    @DisplayName("a player-opened topic is never suppressed by anything here")
    void playerOpenedConversationsAreNotInitiative() {
        assertFalse(ScenePurpose.TOPIC.isInitiative());
        for (BusyState state : BusyState.values()) {
            assertFalse(state.suppresses(ScenePurpose.TOPIC),
                    "the player pressed a button; that is not an interruption");
        }
    }

    @Test
    @DisplayName("nothing is suppressed when there is nothing in the way")
    void idleVillagersAreAvailable() {
        for (ScenePurpose purpose : ScenePurpose.values()) {
            assertFalse(BusyState.NONE.suppresses(purpose));
        }
        assertFalse(BusyState.NONE.isBusy());
    }

    @Test
    @DisplayName("only the two bypassing purposes escape the daily budget")
    void dailyCapAppliesToOrdinaryInitiative() {
        assertFalse(ScenePurpose.ACUTE.countsAgainstDailyCap(),
                "an emergency is not rationed");
        assertFalse(ScenePurpose.STATE_CHANGE.countsAgainstDailyCap(),
                "nor is a genuine change in something the player already knows about");
        assertFalse(ScenePurpose.TOPIC.countsAgainstDailyCap());

        for (ScenePurpose purpose : List.of(ScenePurpose.GREETING, ScenePurpose.DUE_COMMITMENT,
                ScenePurpose.SHARED_EVENT, ScenePurpose.OPINION_REQUEST, ScenePurpose.REPAIR,
                ScenePurpose.LOW_STAKES, ScenePurpose.RESUME)) {
            assertTrue(purpose.countsAgainstDailyCap(), purpose + " should be rationed");
        }
    }

    @Test
    @DisplayName("with several things outstanding, exactly one is surfaced, and it is the urgent one")
    void onlyOneItemIsSurfaced() {
        Optional<ScenePurpose> chosen = InitiativeGate.mostImportant(List.of(
                ScenePurpose.LOW_STAKES, ScenePurpose.OPINION_REQUEST,
                ScenePurpose.DUE_COMMITMENT, ScenePurpose.ACUTE));

        assertEquals(Optional.of(ScenePurpose.ACUTE), chosen);
    }

    @Test
    @DisplayName("the ordering follows the plan's own list of initiative classes")
    void orderingIsByInterruptionCost() {
        assertEquals(Optional.of(ScenePurpose.RESUME), InitiativeGate.mostImportant(
                List.of(ScenePurpose.LOW_STAKES, ScenePurpose.RESUME, ScenePurpose.SHARED_EVENT)));
        assertEquals(Optional.of(ScenePurpose.REPAIR), InitiativeGate.mostImportant(
                List.of(ScenePurpose.OPINION_REQUEST, ScenePurpose.REPAIR)));
    }

    @Test
    @DisplayName("a player-opened topic is not an outstanding item to surface")
    void topicIsNeverSurfacedAsAnInitiative() {
        assertEquals(Optional.empty(), InitiativeGate.mostImportant(List.of(ScenePurpose.TOPIC)));
        assertEquals(Optional.empty(), InitiativeGate.mostImportant(List.of()));
        assertEquals(Optional.empty(), InitiativeGate.mostImportant(null));
    }

    @Test
    @DisplayName("a bark and a full initiative are different amounts of a player's attention")
    void weightsAreDistinct() {
        assertEquals(2, InitiativeGate.Weight.values().length,
                "a third weight would need its own rule in the plan before it has one here");
    }
}
