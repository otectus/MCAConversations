package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A conversation can offer its next question in the same frame the last answer was sent. Held Enter
 * then arrives as an auto-repeat against a brand new offer and picks whatever happens to be focused,
 * which is how a player walks through three nodes they never read.
 */
class ConfirmationArmingTest {

    @Test
    void holdingConfirmationAcrossOffersConfirmsExactlyOnce() {
        ConfirmationArming arming = new ConfirmationArming();
        assertTrue(arming.press(GLFW.GLFW_KEY_ENTER), "the first press confirms");
        assertFalse(arming.press(GLFW.GLFW_KEY_ENTER), "an auto-repeat does not");
        arming.offerChanged();
        assertFalse(arming.press(GLFW.GLFW_KEY_ENTER),
                "a new offer does not re-arm a key that was never released");
        arming.release(GLFW.GLFW_KEY_ENTER);
        assertTrue(arming.press(GLFW.GLFW_KEY_ENTER),
                "release then press works immediately, with no waiting interval");
    }

    @Test
    void everyConfirmationControlIsArmedSeparately() {
        ConfirmationArming arming = new ConfirmationArming();
        assertTrue(arming.press(GLFW.GLFW_KEY_SPACE));
        assertTrue(arming.press(GLFW.GLFW_KEY_KP_ENTER));
        assertTrue(arming.press(GLFW.GLFW_KEY_1));
        assertTrue(arming.press(ConfirmationArming.POINTER));
        assertFalse(arming.press(GLFW.GLFW_KEY_SPACE));
        assertFalse(arming.press(ConfirmationArming.POINTER));
        assertTrue(arming.held(GLFW.GLFW_KEY_1));
        arming.release(GLFW.GLFW_KEY_1);
        assertFalse(arming.held(GLFW.GLFW_KEY_1));
    }

    @Test
    void reconcilingWithTheDeviceRecoversAReleaseTheScreenNeverDelivered() {
        // Focus loss and screen changes are where a release event goes missing. Nothing here is a
        // timer: the window is simply asked whether the key is still down.
        ConfirmationArming arming = new ConfirmationArming();
        arming.press(GLFW.GLFW_KEY_ENTER);
        arming.press(ConfirmationArming.POINTER);
        arming.sync(control -> control == ConfirmationArming.POINTER);
        assertFalse(arming.held(GLFW.GLFW_KEY_ENTER));
        assertTrue(arming.held(ConfirmationArming.POINTER));
        assertTrue(arming.press(GLFW.GLFW_KEY_ENTER));

        arming.reset();
        assertFalse(arming.held(ConfirmationArming.POINTER));
    }
}
