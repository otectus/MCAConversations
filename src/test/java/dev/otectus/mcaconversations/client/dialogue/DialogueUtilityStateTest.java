package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Opening a utility must not lose the player's place, and Back must give it straight back. */
class DialogueUtilityStateTest {

    @Test
    void backReturnsTheKeyboardToTheRegionItCameFrom() {
        DialogueUtilityState state = new DialogueUtilityState();
        assertTrue(state.open(DialogueUtilityState.Utility.HISTORY,
                DialogueReadingState.Region.QUESTION));
        assertEquals(DialogueReadingState.Region.QUESTION, state.returnRegion());
        assertTrue(state.back());
        assertFalse(state.isOpen());
        assertEquals(DialogueReadingState.Region.QUESTION, state.returnRegion());
    }

    @Test
    void switchingUtilitiesKeepsTheOriginalReturnRegion() {
        DialogueUtilityState state = new DialogueUtilityState();
        state.open(DialogueUtilityState.Utility.HISTORY, DialogueReadingState.Region.FOOTER);
        state.open(DialogueUtilityState.Utility.SETTINGS, DialogueReadingState.Region.RESPONSES);
        assertEquals(DialogueReadingState.Region.FOOTER, state.returnRegion(),
                "the second utility was opened from inside the first, not from the card");
    }

    @Test
    void toggleClosesTheUtilityItIsAlreadyShowing() {
        DialogueUtilityState state = new DialogueUtilityState();
        assertTrue(state.toggle(DialogueUtilityState.Utility.SETTINGS,
                DialogueReadingState.Region.RESPONSES));
        assertTrue(state.toggle(DialogueUtilityState.Utility.SETTINGS,
                DialogueReadingState.Region.RESPONSES));
        assertFalse(state.isOpen());
    }

    @Test
    void selectionAndScrollStayInsideTheirBounds() {
        DialogueUtilityState state = new DialogueUtilityState();
        state.open(DialogueUtilityState.Utility.SETTINGS, DialogueReadingState.Region.RESPONSES);
        assertFalse(state.move(-1, 3));
        assertTrue(state.move(9, 3));
        assertEquals(2, state.selected());
        assertFalse(state.scroll(-40, 20));
        assertTrue(state.scroll(999, 20));
        assertEquals(20, state.scroll());
    }

    @Test
    void aNewOfferDoesNotCloseAnOpenUtility() {
        DialogueUtilityState state = new DialogueUtilityState();
        state.open(DialogueUtilityState.Utility.HISTORY, DialogueReadingState.Region.RESPONSES);
        state.offerChanged();
        assertTrue(state.is(DialogueUtilityState.Utility.HISTORY));
    }

    @Test
    void theResetActionHasToBeReadBeforeItCanBeApplied() {
        DialogueUtilityState state = new DialogueUtilityState();
        state.open(DialogueUtilityState.Utility.SETTINGS, DialogueReadingState.Region.RESPONSES);
        assertFalse(state.resetListed());
        state.listReset();
        assertTrue(state.resetListed());
        state.back();
        assertFalse(state.resetListed(), "closing the pane forgets a listed reset");
    }
}
