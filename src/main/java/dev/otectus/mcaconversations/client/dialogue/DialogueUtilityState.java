package dev.otectus.mcaconversations.client.dialogue;

/**
 * Which of the two card utilities is open, where it is scrolled, and where the keyboard came from.
 *
 * <p>Both utilities are overlays on the response viewport, never a second screen and never a resize:
 * the outer {@link DialogueChoiceLayout.Geometry} the card was built with is exactly the geometry it
 * keeps while one is open, so opening the history does not move the panel the player was reading.
 *
 * <p>An open utility owns the keyboard and the pointer. That is the whole safeguard behind "opening
 * a utility cannot select a response": while one is open the card's selection state is not reachable
 * at all, rather than reachable and filtered.
 */
public final class DialogueUtilityState {

    public enum Utility {
        NONE,
        /** What this client has received and sent. */
        HISTORY,
        /** The presentation choices. */
        SETTINGS
    }

    private Utility open = Utility.NONE;
    private DialogueReadingState.Region returnRegion = DialogueReadingState.Region.RESPONSES;
    private int selected;
    private int scroll;
    private boolean listedReset;

    public Utility open() {
        return open;
    }

    public boolean isOpen() {
        return open != Utility.NONE;
    }

    public boolean is(Utility utility) {
        return open == utility;
    }

    /** Opens a utility, remembering the region the keyboard is to be given back. */
    public boolean open(Utility utility, DialogueReadingState.Region from) {
        if (utility == null || utility == Utility.NONE || open == utility) {
            return false;
        }
        if (open == Utility.NONE && from != null) {
            returnRegion = from;
        }
        open = utility;
        selected = 0;
        scroll = 0;
        listedReset = false;
        return true;
    }

    /** Toggles a utility from a region; closing returns the keyboard where it came from. */
    public boolean toggle(Utility utility, DialogueReadingState.Region from) {
        return open == utility ? back() : open(utility, from);
    }

    /** The Back action. True when a utility was actually closed. */
    public boolean back() {
        if (open == Utility.NONE) {
            return false;
        }
        open = Utility.NONE;
        selected = 0;
        scroll = 0;
        listedReset = false;
        return true;
    }

    /** Where the keyboard goes when the utility closes. */
    public DialogueReadingState.Region returnRegion() {
        return returnRegion;
    }

    public int selected() {
        return selected;
    }

    public boolean select(int index, int rowCount) {
        int wanted = Math.max(0, Math.min(Math.max(0, rowCount - 1), index));
        if (wanted == selected) {
            return false;
        }
        selected = wanted;
        return true;
    }

    public boolean move(int delta, int rowCount) {
        return select(selected + delta, rowCount);
    }

    public int scroll() {
        return scroll;
    }

    public boolean scroll(int delta, int maxScroll) {
        int wanted = Math.max(0, Math.min(Math.max(0, maxScroll), scroll + delta));
        if (wanted == scroll) {
            return false;
        }
        scroll = wanted;
        return true;
    }

    /**
     * Whether the reset action has already said what it would change.
     *
     * <p>The first activation lists the changes and the second applies them, so nothing about the
     * player's presentation is replaced by a control they pressed once without reading.
     */
    public boolean resetListed() {
        return listedReset;
    }

    public void listReset() {
        listedReset = true;
    }

    public void resetListingCleared() {
        listedReset = false;
    }

    /** A new offer does not close a utility: the player is reading, and the card is still theirs. */
    public void offerChanged() {
        // Intentionally empty; see the class comment.
    }

    public void reset() {
        open = Utility.NONE;
        returnRegion = DialogueReadingState.Region.RESPONSES;
        selected = 0;
        scroll = 0;
        listedReset = false;
    }
}
