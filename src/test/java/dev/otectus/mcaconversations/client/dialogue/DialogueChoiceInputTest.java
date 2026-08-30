package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DialogueChoiceInputTest {

    @Test
    void mapsTopRowAndKeypadWithoutModifiers() {
        assertEquals(OptionalInt.of(1), DialogueChoiceInput.digit(GLFW.GLFW_KEY_1, 0));
        assertEquals(OptionalInt.of(9), DialogueChoiceInput.digit(GLFW.GLFW_KEY_9, 0));
        assertEquals(OptionalInt.of(4), DialogueChoiceInput.digit(GLFW.GLFW_KEY_KP_4, 0));
        assertTrue(DialogueChoiceInput.digit(GLFW.GLFW_KEY_0, 0).isEmpty());
        assertTrue(DialogueChoiceInput.digit(GLFW.GLFW_KEY_2, GLFW.GLFW_MOD_CONTROL).isEmpty());
    }
}
