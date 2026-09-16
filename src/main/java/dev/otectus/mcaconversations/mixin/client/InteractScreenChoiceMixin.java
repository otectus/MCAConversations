package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.client.dialogue.ClientChoiceController;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceState;
import dev.otectus.mcaconversations.client.dialogue.ClientDialogueHistory;
import dev.otectus.mcaconversations.client.dialogue.ConfirmationArming;
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
    @Unique private final UUID mcaconversations$historyConversation = UUID.randomUUID();
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
        // Claimed here, before any offer for this villager can arrive, and claiming drops whatever
        // the previous screen left behind. A screen that was replaced rather than closed — MCA's own
        // family tree button does exactly that — never runs the close path below.
        ClientChoiceMessages.screenOpened(this, mcaconversations$villagerId);
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
        // The one point where this client has the villager's line as a resolved component. Recording
        // it here is what keeps the history the conversation that happened: a pooled line asked for
        // again resolves to a different sentence, so an id kept for later would not be this line.
        if (mcaconversations$questionComponent instanceof Component spoken) {
            ClientDialogueHistory.spoken(
                    ClientDialogueHistory.spokenIdentity(mcaconversations$historyConversation,
                            mcaconversations$questionRevision),
                    mcaconversations$speakerName, spoken);
        }
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
        // Unconditional: a confirmation key released while no offer is up is still a release, and
        // the next press has to be able to act on it.
        mcaconversations$renderer.syncConfirmationInput();
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
        mcaconversations$renderer.lapseReturn(() -> ClientChoiceController.returnToTopics(
                mcaconversations$villagerId));
    }

    @Inject(method = "keyPressed", at = @At("HEAD"),
            cancellable = true, require = 0, remap = false)
    private void mcaconversations$keyPressed(int keyCode, int scanCode, int modifiers,
                                             CallbackInfoReturnable<Boolean> cir) {
        if (!mcaconversations$active()) {
            return;
        }
        if (ClientChoiceMessages.state().lapseFor(ConversationSession.Frontend.GUI,
                mcaconversations$villagerId)) {
            mcaconversations$renderer.lapseKey(keyCode);
            cir.setReturnValue(true);
            return;
        }
        // An open utility owns the keyboard outright. Asked before the digit branch and before the
        // confirmation keys, so opening the history or the settings pane cannot select an answer.
        if (mcaconversations$renderer.utilityOpen()) {
            mcaconversations$renderer.utilityKey(keyCode, (modifiers & GLFW.GLFW_MOD_SHIFT) != 0);
            cir.setReturnValue(true);
            return;
        }
        ClientChoiceState state = ClientChoiceMessages.state();
        if (ClientChoiceController.numericShortcutsEnabled()) {
            java.util.OptionalInt digit = DialogueChoiceInput.digit(keyCode, modifiers);
            if (digit.isPresent()) {
                mcaconversations$renderer.keyboardInput();
                // Consumed whether or not it confirms. An auto-repeat that we decline must not fall
                // through to whatever the host screen would have done with the same digit.
                if (mcaconversations$renderer.confirmPress(keyCode)) {
                    int index = state.firstOnPage() + digit.getAsInt() - 1;
                    if (index < state.firstOnPage() + state.visibleCount()) {
                        mcaconversations$select(index);
                    }
                }
                cir.setReturnValue(true);
                return;
            }
        }
        switch (keyCode) {
            case GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_KP_ENTER, GLFW.GLFW_KEY_SPACE -> {
                mcaconversations$renderer.keyboardInput();
                if (mcaconversations$renderer.confirmPress(keyCode)) {
                    mcaconversations$select(state.focusedIndex());
                }
                cir.setReturnValue(true);
            }
            default -> {
                boolean shift = (modifiers & GLFW.GLFW_MOD_SHIFT) != 0;
                if (mcaconversations$renderer.navigationKey(keyCode, shift)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    /**
     * Release tracking. The screen may not override this at all, which is why the renderer also
     * reconciles against the window every frame; both paths feed the same arming state.
     */
    @Inject(method = "keyReleased", at = @At("HEAD"), require = 0, remap = false)
    private void mcaconversations$keyReleased(int keyCode, int scanCode, int modifiers,
                                              CallbackInfoReturnable<Boolean> cir) {
        mcaconversations$renderer.confirmRelease(keyCode);
    }

    @Inject(method = "mouseReleased", at = @At("HEAD"), require = 0, remap = false)
    private void mcaconversations$mouseReleased(double mouseX, double mouseY, int button,
                                                CallbackInfoReturnable<Boolean> cir) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            mcaconversations$renderer.confirmRelease(ConfirmationArming.POINTER);
        }
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
            if (mcaconversations$renderer.confirmPress(ConfirmationArming.POINTER)) {
                mcaconversations$select(choice.absoluteIndex());
            }
            cir.setReturnValue(true);
        } else if (target instanceof DialogueHitTarget.PreviousPage
                || target instanceof DialogueHitTarget.NextPage
                || target instanceof DialogueHitTarget.Question
                || target instanceof DialogueHitTarget.Responses) {
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
            // The renderer routes the wheel to the region it is over. An event it does not claim is
            // consumed and dropped: it must never turn the answer page from over the question, or
            // from a reading region that has simply reached its end.
            mcaconversations$renderer.scroll(mouseX, mouseY, delta);
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "onClose", at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$onClose(CallbackInfo ci) {
        mcaconversations$releaseScreen();
    }

    /**
     * Retires everything this screen owned.
     *
     * <p>Unconditional, and both halves. The old guard asked whether a GUI offer was live, but a
     * lapse has no offer — clearing it was exactly the case the guard excluded — so the explanation
     * survived the close and was still on the state when the player opened the next villager's
     * screen. Only the graphical frontend is dropped: a chat conversation is not this screen's.
     */
    @Unique
    private void mcaconversations$releaseScreen() {
        ClientChoiceMessages.state().clearLocal(ConversationSession.Frontend.GUI);
        ClientChoiceMessages.screenClosed(this);
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

    /**
     * The sole ownership gate: every injector above asks this, and it reads the effective dialogue
     * style through the controller, so a style change takes effect atomically per frame and per
     * event. Under MCA_ORIGINAL it answers false and MCA keeps its own question, answers, clicks,
     * wheel and digits.
     *
     * <p>A lapse must also name this screen's villager. One left by another conversation, or one
     * that names nobody, is not this screen's to show: without that test the sentence explaining why
     * the last villager stopped answering took over the next villager's screen, and MCA's own
     * dialogue never appeared.
     */
    @Unique
    private boolean mcaconversations$active() {
        if (ClientChoiceController.numberingEnabled()
                && ClientChoiceMessages.state().lapseFor(ConversationSession.Frontend.GUI,
                        mcaconversations$villagerId)) {
            return true;
        }
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
        if (!ClientChoiceController.select(absoluteIndex, mcaconversations$villagerId,
                mcaconversations$renderer.answerText(absoluteIndex))) {
            return false;
        }
        dialogAnswerHover = null;
        dialogAnswers = List.of();
        return true;
    }
}
