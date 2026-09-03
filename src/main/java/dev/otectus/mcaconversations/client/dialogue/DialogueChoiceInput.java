package dev.otectus.mcaconversations.client.dialogue;

import org.lwjgl.glfw.GLFW;

import java.util.OptionalInt;

/** Pure GLFW-to-choice mapping shared by the MCA and chat screen adapters. */
public final class DialogueChoiceInput {

    private DialogueChoiceInput() {
    }

    public static OptionalInt digit(int keyCode, int modifiers) {
        int disallowed = GLFW.GLFW_MOD_SHIFT | GLFW.GLFW_MOD_CONTROL
                | GLFW.GLFW_MOD_ALT | GLFW.GLFW_MOD_SUPER;
        if ((modifiers & disallowed) != 0) {
            return OptionalInt.empty();
        }
        if (keyCode >= GLFW.GLFW_KEY_1 && keyCode <= GLFW.GLFW_KEY_9) {
            return OptionalInt.of(keyCode - GLFW.GLFW_KEY_0);
        }
        if (keyCode >= GLFW.GLFW_KEY_KP_1 && keyCode <= GLFW.GLFW_KEY_KP_9) {
            return OptionalInt.of(keyCode - GLFW.GLFW_KEY_KP_0);
        }
        return OptionalInt.empty();
    }
}
