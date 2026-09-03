package dev.otectus.mcaconversations.client.dialogue;

/** Owns input modality so an unchanged pointer can never steal keyboard focus. */
public final class DialogueChoicePresenter {

    public enum InputModality { POINTER, KEYBOARD }

    private InputModality modality = InputModality.POINTER;
    private int lastMouseX;
    private int lastMouseY;
    private boolean hasMousePosition;

    public InputModality modality() {
        return modality;
    }

    public boolean updatePointer(int mouseX, int mouseY, PreparedDialogueCard card,
                                 ClientChoiceState state) {
        if (!hasMousePosition) {
            lastMouseX = mouseX;
            lastMouseY = mouseY;
            hasMousePosition = true;
            return false;
        }
        boolean moved = Math.abs(mouseX - lastMouseX) >= 1 || Math.abs(mouseY - lastMouseY) >= 1;
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        if (!moved || state.locked()) {
            return false;
        }
        modality = InputModality.POINTER;
        DialogueHitTarget target = card == null ? new DialogueHitTarget.None() : card.hit(mouseX, mouseY);
        return target instanceof DialogueHitTarget.Choice choice && state.focus(choice.absoluteIndex());
    }

    public void keyboard() {
        modality = InputModality.KEYBOARD;
    }

    public DialogueHitTarget click(double mouseX, double mouseY, PreparedDialogueCard card,
                                   ClientChoiceState state) {
        modality = InputModality.POINTER;
        lastMouseX = (int) Math.floor(mouseX);
        lastMouseY = (int) Math.floor(mouseY);
        hasMousePosition = true;
        DialogueHitTarget target = card == null ? new DialogueHitTarget.None() : card.hit(mouseX, mouseY);
        if (target instanceof DialogueHitTarget.Choice choice) {
            state.focus(choice.absoluteIndex());
        }
        return target;
    }

    public void reset() {
        modality = InputModality.POINTER;
        hasMousePosition = false;
    }
}
