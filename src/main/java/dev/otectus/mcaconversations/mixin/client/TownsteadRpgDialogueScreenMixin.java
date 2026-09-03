package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.client.dialogue.ClientChoiceController;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceInput;
import dev.otectus.mcaconversations.client.townstead.TownsteadChoiceAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Delegates digit selection back through Townstead's native hub/submenu/back routine. */
@Pseudo
@Mixin(targets = "com.aetherianartificer.townstead.client.gui.dialogue.RpgDialogueScreen", remap = false)
public abstract class TownsteadRpgDialogueScreenMixin {

    @Inject(method = "keyPressed", at = @At("HEAD"),
            cancellable = true, require = 0, remap = false)
    private void mcaconversations$numberedChoice(int keyCode, int scanCode, int modifiers,
                                                CallbackInfoReturnable<Boolean> cir) {
        if (!ClientChoiceController.numericShortcutsEnabled()) {
            return;
        }
        java.util.OptionalInt digit = DialogueChoiceInput.digit(keyCode, modifiers);
        if (digit.isPresent() && TownsteadChoiceAccess.selectVisibleDigit(this, digit.getAsInt())) {
            cir.setReturnValue(true);
        }
    }
}
