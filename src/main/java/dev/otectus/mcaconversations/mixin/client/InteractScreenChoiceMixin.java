package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.client.dialogue.ClientChoiceController;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceState;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceInput;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceRenderer;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.Entity;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.UUID;

/** Narrow responsive-choice adapter for MCA's existing interaction screen. */
@Pseudo
@Mixin(targets = {
        "forge.net.mca.client.gui.InteractScreen",
        "forge.net.conczin.mca.client.gui.InteractScreen",
}, remap = false)
public abstract class InteractScreenChoiceMixin {

    @Shadow(remap = false) private List<String> dialogAnswers;
    @Shadow(remap = false) private String dialogAnswerHover;
    @Shadow(remap = false) private List<FormattedCharSequence> dialogQuestionText;
    @Shadow(remap = false) private String dialogQuestionId;

    @Unique private final DialogueChoiceRenderer mcaconversations$renderer = new DialogueChoiceRenderer();
    @Unique private List<FormattedCharSequence> mcaconversations$savedQuestion;
    @Unique private UUID mcaconversations$villagerId;
    @Unique private boolean mcaconversations$suppressed;

    @Inject(method = "<init>", at = @At("RETURN"), require = 0, remap = false)
    private void mcaconversations$captureVillager(@Coerce Object villager, CallbackInfo ci) {
        if (villager instanceof Entity entity) {
            mcaconversations$villagerId = entity.getUUID();
        }
    }

    @Inject(method = {"render", "m_88315_"}, at = @At("HEAD"), require = 0, remap = false)
    private void mcaconversations$hideLegacyChoices(GuiGraphics graphics, int mouseX, int mouseY,
                                                    float partialTick, CallbackInfo ci) {
        if (mcaconversations$active()) {
            mcaconversations$savedQuestion = dialogQuestionText;
            dialogQuestionText = null;
            mcaconversations$suppressed = true;
        }
    }

    @Inject(method = {"render", "m_88315_"}, at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$renderChoiceCard(GuiGraphics graphics, int mouseX, int mouseY,
                                                   float partialTick, CallbackInfo ci) {
        if (!mcaconversations$suppressed) {
            return;
        }
        dialogQuestionText = mcaconversations$savedQuestion;
        mcaconversations$suppressed = false;
        mcaconversations$renderer.render(graphics, mouseX, mouseY, dialogQuestionText);
    }

    @Inject(method = {"keyPressed", "m_7933_"}, at = @At("HEAD"),
            cancellable = true, require = 0, remap = false)
    private void mcaconversations$keyPressed(int keyCode, int scanCode, int modifiers,
                                             CallbackInfoReturnable<Boolean> cir) {
        if (!mcaconversations$active()) {
            return;
        }
        ClientChoiceState state = ClientChoiceMessages.state();
        if (ClientChoiceController.numericShortcutsEnabled()) {
            java.util.OptionalInt digit = DialogueChoiceInput.digit(keyCode, modifiers);
            if (digit.isPresent()) {
                int index = state.firstOnPage() + digit.getAsInt() - 1;
                if (index < state.firstOnPage() + state.visibleCount()) {
                    ClientChoiceController.select(index, mcaconversations$villagerId);
                }
                cir.setReturnValue(true);
                return;
            }
        }
        switch (keyCode) {
            case GLFW.GLFW_KEY_UP -> state.moveFocus(-1);
            case GLFW.GLFW_KEY_DOWN -> state.moveFocus(1);
            case GLFW.GLFW_KEY_HOME -> state.focusBoundary(false);
            case GLFW.GLFW_KEY_END -> state.focusBoundary(true);
            case GLFW.GLFW_KEY_PAGE_UP -> state.changePage(-1);
            case GLFW.GLFW_KEY_PAGE_DOWN -> state.changePage(1);
            case GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_KP_ENTER, GLFW.GLFW_KEY_SPACE ->
                    ClientChoiceController.select(state.focusedIndex(), mcaconversations$villagerId);
            default -> { return; }
        }
        cir.setReturnValue(true);
    }

    @Inject(method = {"mouseClicked", "m_6375_"}, at = @At("HEAD"),
            cancellable = true, require = 0, remap = false)
    private void mcaconversations$mouseClicked(double mouseX, double mouseY, int button,
                                               CallbackInfoReturnable<Boolean> cir) {
        if (!mcaconversations$active() || button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return;
        }
        int index = mcaconversations$renderer.hitIndex(mouseX, mouseY);
        if (index >= 0) {
            ClientChoiceController.select(index, mcaconversations$villagerId);
            cir.setReturnValue(true);
        } else {
            // Preserve MCA's icon/button handling without letting a stale legacy hover submit.
            dialogAnswerHover = null;
        }
    }

    @Inject(method = {"mouseScrolled", "m_6050_"}, at = @At("HEAD"),
            cancellable = true, require = 0, remap = false)
    private void mcaconversations$mouseScrolled(double mouseX, double mouseY, double delta,
                                                CallbackInfoReturnable<Boolean> cir) {
        if (mcaconversations$active()) {
            ClientChoiceMessages.state().changePage(delta < 0.0D ? 1 : -1);
            cir.setReturnValue(true);
        }
    }

    @Inject(method = {"onClose", "m_7379_"}, at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$onClose(CallbackInfo ci) {
        if (ClientChoiceMessages.state().activeFor(ConversationSession.Frontend.GUI)) {
            ClientChoiceMessages.state().clearLocal();
        }
    }

    @Unique
    private boolean mcaconversations$active() {
        if (!ClientChoiceController.numberingEnabled() || dialogQuestionText == null
                || dialogQuestionId == null || dialogAnswers == null) {
            return false;
        }
        ClientChoiceState.ClientChoiceOffer offer = ClientChoiceMessages.state().offer().orElse(null);
        return offer != null && offer.frontend() == ConversationSession.Frontend.GUI
                && offer.questionId().equals(dialogQuestionId)
                && offer.answerIds().equals(dialogAnswers);
    }
}
