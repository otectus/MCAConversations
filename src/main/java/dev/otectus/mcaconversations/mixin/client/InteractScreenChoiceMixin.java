package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.client.dialogue.ClientChoiceController;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceState;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceInput;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceRenderer;
import dev.otectus.mcaconversations.client.dialogue.DialogueHitTarget;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.UUID;

/** Narrow responsive-choice adapter for MCA's existing interaction screen. */
@Pseudo
@Mixin(targets = "net.conczin.mca.client.gui.InteractScreen", remap = false)
public abstract class InteractScreenChoiceMixin {

    @Shadow(remap = false) private List<String> dialogAnswers;
    @Shadow(remap = false) private String dialogAnswerHover;
    @Shadow(remap = false) private List<FormattedCharSequence> dialogQuestionText;
    @Shadow(remap = false) private String dialogQuestionId;

    @Unique private final DialogueChoiceRenderer mcaconversations$renderer = new DialogueChoiceRenderer();
    @Unique private List<FormattedCharSequence> mcaconversations$savedQuestion;
    @Unique private UUID mcaconversations$villagerId;
    @Unique private Component mcaconversations$speakerName;
    @Unique private LivingEntity mcaconversations$speaker;
    @Unique private FormattedText mcaconversations$questionComponent;
    @Unique private long mcaconversations$questionRevision;
    @Unique private boolean mcaconversations$silentQuestion;
    @Unique private boolean mcaconversations$suppressed;

    @Inject(method = "<init>", at = @At("RETURN"), require = 0, remap = false)
    private void mcaconversations$captureVillager(@Coerce Object villager, CallbackInfo ci) {
        if (villager instanceof Entity entity) {
            mcaconversations$villagerId = entity.getUUID();
            mcaconversations$speakerName = entity.getDisplayName().copy();
            // Held for the screen's lifetime only, which is the villager's own: MCA's screen already
            // owns this reference, and it is released in onClose alongside everything else.
            if (entity instanceof LivingEntity living) {
                mcaconversations$speaker = living;
            }
        }
    }

    @Inject(method = "setLastPhrase", at = @At("HEAD"), require = 0, remap = false)
    private void mcaconversations$captureSilentQuestion(Component phrase, boolean silent,
                                                        CallbackInfo ci) {
        mcaconversations$silentQuestion = silent;
    }

    @ModifyArg(method = "setLastPhrase", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Font;split(Lnet/minecraft/network/chat/FormattedText;I)Ljava/util/List;",
            remap = true), index = 0, require = 0, remap = false)
    private FormattedText mcaconversations$captureDisplayedQuestion(FormattedText question) {
        mcaconversations$questionComponent = question instanceof Component component
                ? component.copy() : question;
        mcaconversations$questionRevision++;
        return question;
    }

    @Inject(method = "tick", at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$tickChoiceCard(CallbackInfo ci) {
        mcaconversations$renderer.tick();
    }

    @Inject(method = "render", at = @At("HEAD"), require = 0, remap = false)
    private void mcaconversations$hideLegacyChoices(GuiGraphics graphics, int mouseX, int mouseY,
                                                    float partialTick, CallbackInfo ci) {
        mcaconversations$restoreReofferedChoices();
        if (mcaconversations$active() || mcaconversations$renderer.hasOutgoingPresentation()) {
            mcaconversations$savedQuestion = dialogQuestionText;
            dialogQuestionText = null;
            mcaconversations$suppressed = true;
        }
    }

    @Inject(method = "render", at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$renderChoiceCard(GuiGraphics graphics, int mouseX, int mouseY,
                                                   float partialTick, CallbackInfo ci) {
        if (!mcaconversations$suppressed) {
            return;
        }
        dialogQuestionText = mcaconversations$savedQuestion;
        mcaconversations$suppressed = false;
        mcaconversations$renderer.render(graphics, mouseX, mouseY, partialTick,
                mcaconversations$questionComponent, mcaconversations$questionRevision,
                mcaconversations$speakerName, mcaconversations$silentQuestion, dialogQuestionText,
                mcaconversations$speaker);
    }

    @Inject(method = "keyPressed", at = @At("HEAD"),
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
                mcaconversations$renderer.keyboardInput();
                int index = state.firstOnPage() + digit.getAsInt() - 1;
                if (index < state.firstOnPage() + state.visibleCount()) {
                    mcaconversations$select(index);
                }
                cir.setReturnValue(true);
                return;
            }
        }
        switch (keyCode) {
            case GLFW.GLFW_KEY_UP -> mcaconversations$renderer.moveFocus(-1);
            case GLFW.GLFW_KEY_DOWN -> mcaconversations$renderer.moveFocus(1);
            case GLFW.GLFW_KEY_HOME -> mcaconversations$renderer.focusBoundary(false);
            case GLFW.GLFW_KEY_END -> mcaconversations$renderer.focusBoundary(true);
            case GLFW.GLFW_KEY_PAGE_UP -> mcaconversations$renderer.changePage(-1);
            case GLFW.GLFW_KEY_PAGE_DOWN -> mcaconversations$renderer.changePage(1);
            case GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_KP_ENTER, GLFW.GLFW_KEY_SPACE -> {
                mcaconversations$renderer.keyboardInput();
                mcaconversations$select(state.focusedIndex());
            }
            default -> { return; }
        }
        cir.setReturnValue(true);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"),
            cancellable = true, require = 0, remap = false)
    private void mcaconversations$mouseClicked(double mouseX, double mouseY, int button,
                                               CallbackInfoReturnable<Boolean> cir) {
        if (!mcaconversations$active() || button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return;
        }
        DialogueHitTarget target = mcaconversations$renderer.click(mouseX, mouseY);
        if (target instanceof DialogueHitTarget.Choice choice) {
            mcaconversations$select(choice.absoluteIndex());
            cir.setReturnValue(true);
        } else if (target instanceof DialogueHitTarget.PreviousPage
                || target instanceof DialogueHitTarget.NextPage) {
            cir.setReturnValue(true);
        } else {
            // Preserve MCA's icon/button handling without letting a stale legacy hover submit.
            dialogAnswerHover = null;
        }
    }

    @Inject(method = "mouseScrolled", at = @At("HEAD"),
            cancellable = true, require = 0, remap = false)
    private void mcaconversations$mouseScrolled(double mouseX, double mouseY, double scrollX,
                                                double scrollY,
                                                CallbackInfoReturnable<Boolean> cir) {
        // 1.21.1 splits the old single scroll delta into two axes; the vertical one is that delta.
        double delta = scrollY;
        if (mcaconversations$active()) {
            if (!mcaconversations$renderer.scroll(mouseX, mouseY, delta)) {
                mcaconversations$renderer.changePage(delta < 0.0D ? 1 : -1);
            }
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "onClose", at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$onClose(CallbackInfo ci) {
        if (ClientChoiceMessages.state().activeFor(ConversationSession.Frontend.GUI)) {
            ClientChoiceMessages.state().clearLocal();
        }
        mcaconversations$renderer.reset();
        mcaconversations$speaker = null;
    }

    /**
     * Puts the answer list back when the server re-offered the same question after a say-only reply.
     *
     * <p>MCA sends no response packet for a terminal answer, so its own fields stay blanked by
     * {@link #mcaconversations$select}. Restoring them from the fresh offer is what makes the card
     * reappear beneath the villager's line, matching vanilla MCA, where the buttons simply remain.
     */
    @Unique
    private void mcaconversations$restoreReofferedChoices() {
        if (dialogQuestionId == null || dialogAnswers == null || !dialogAnswers.isEmpty()
                || ClientChoiceMessages.state().locked()) {
            return;
        }
        ClientChoiceState.ClientChoiceOffer offer = ClientChoiceMessages.state().offer().orElse(null);
        if (offer != null && offer.frontend() == ConversationSession.Frontend.GUI
                && offer.questionId().equals(dialogQuestionId) && !offer.answerIds().isEmpty()) {
            dialogAnswers = offer.answerIds();
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
                && (offer.answerIds().equals(dialogAnswers) || ClientChoiceMessages.state().locked());
    }

    /**
     * MCA normally leaves the submitted answer list in its screen fields until another response
     * packet replaces it. Our synchronized offer is one-shot, so retaining those answers exposes
     * the old clickable menu as soon as the consumed offer clears. Retire only the answer list; the
     * latest villager phrase remains visible and a genuine next response repopulates the fields via
     * MCA's ordinary {@code setDialogue} packet handler.
     *
     * <p>A say-only answer sends no such response, so the server re-offers the same question under a
     * new revision and {@link #mcaconversations$restoreReofferedChoices} refills the list on the next
     * frame. The blanking still matters in between: it keeps MCA's native rows unclickable while the
     * card plays its exit animation.
     */
    @Unique
    private boolean mcaconversations$select(int absoluteIndex) {
        if (!ClientChoiceController.select(absoluteIndex, mcaconversations$villagerId)) {
            return false;
        }
        dialogAnswerHover = null;
        dialogAnswers = List.of();
        return true;
    }
}
