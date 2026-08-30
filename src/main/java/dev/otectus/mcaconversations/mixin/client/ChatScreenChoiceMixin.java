package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.client.dialogue.ClientChoiceController;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceState;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceInput;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Empty-chat-field digit shortcut; normal gameplay and non-empty numeric typing remain untouched. */
@Mixin(ChatScreen.class)
public abstract class ChatScreenChoiceMixin {

    @Shadow protected EditBox input;

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void mcaconversations$selectChatChoice(int keyCode, int scanCode, int modifiers,
                                                   CallbackInfoReturnable<Boolean> cir) {
        ClientChoiceState state = ClientChoiceMessages.state();
        if (!ClientChoiceController.chatShortcutsEnabled()
                || !state.activeFor(ConversationSession.Frontend.CHAT)
                || input == null || !input.getValue().isBlank()) {
            return;
        }
        java.util.OptionalInt digit = DialogueChoiceInput.digit(keyCode, modifiers);
        if (digit.isEmpty()) {
            return;
        }
        int index = digit.getAsInt() - 1;
        if (index < state.visibleCount() && ClientChoiceController.select(index, null)) {
            Minecraft.getInstance().setScreen(null);
        }
        cir.setReturnValue(true);
    }
}
