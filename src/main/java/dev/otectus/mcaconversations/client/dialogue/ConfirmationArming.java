package dev.otectus.mcaconversations.client.dialogue;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;

/**
 * Release-based arming for the controls that confirm an answer.
 *
 * <p>A conversation can offer the next question in the same frame the previous answer was sent. A
 * confirmation key that is still held from the previous node then arrives as an auto-repeat against
 * a brand new offer and picks whatever happens to be focused. Requiring a release between two
 * confirmations removes that entirely, without a timer: a control is disarmed from the moment it is
 * pressed until it is seen released, whether or not an offer exists in between.
 */
public final class ConfirmationArming {

    /** Control id for Conversations' own pointer confirmation; key codes are never negative. */
    public static final int POINTER = -1;

    private final Set<Integer> held = new HashSet<>();

    /** Records a press. True only when the control had been released since it was last pressed. */
    public boolean press(int control) {
        return held.add(control);
    }

    public void release(int control) {
        held.remove(control);
    }

    public boolean held(int control) {
        return held.contains(control);
    }

    /**
     * Reconciles with the real device. Anything the window no longer reports as down is released,
     * which is also how a control held across a focus change or a screen swap is recovered: the
     * player does not have to press it twice because we missed the release event.
     */
    public void sync(IntPredicate stillDown) {
        held.removeIf(control -> !stillDown.test(control));
    }

    /** Nothing to do on a new offer: a control held across it stays disarmed until it is released. */
    public void offerChanged() {
    }

    public void reset() {
        held.clear();
    }
}
