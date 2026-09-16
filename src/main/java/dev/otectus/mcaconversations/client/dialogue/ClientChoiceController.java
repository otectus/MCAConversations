package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.network.ChoiceSelectC2S;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

import java.util.UUID;

/** Locks locally before sending a revisioned index to the server. */
public final class ClientChoiceController {

    private ClientChoiceController() {
    }

    /**
     * The one authoritative reading of the presentation configuration. {@code numberedResponses=false}
     * is the deprecated 1.4.x/1.5.1 compatibility override and always wins.
     */
    public static McaConversationsConfig.DialogueMenuStyle resolve(
            boolean numberedResponses, McaConversationsConfig.DialogueMenuStyle configured) {
        if (!numberedResponses) {
            return McaConversationsConfig.DialogueMenuStyle.MCA_ORIGINAL;
        }
        return configured == null ? McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE : configured;
    }

    public static McaConversationsConfig.DialogueMenuStyle dialogueMenuStyle() {
        try {
            return resolve(McaConversationsConfig.CLIENT.numberedResponses.get(),
                    McaConversationsConfig.CLIENT.dialogueMenuStyle.get());
        } catch (Throwable ignored) {
            return McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE;
        }
    }

    public static boolean responsiveDialogueEnabled() {
        return dialogueMenuStyle() == McaConversationsConfig.DialogueMenuStyle.RESPONSIVE;
    }

    public static boolean minimalDialogueEnabled() {
        return dialogueMenuStyle() == McaConversationsConfig.DialogueMenuStyle.MINIMAL;
    }

    public static boolean originalMcaDialogueEnabled() {
        return dialogueMenuStyle() == McaConversationsConfig.DialogueMenuStyle.MCA_ORIGINAL;
    }

    /** True while MCA: Conversations owns the dialogue presentation, whichever card it draws. */
    public static boolean conversationsDialogueEnabled() {
        return dialogueMenuStyle() != McaConversationsConfig.DialogueMenuStyle.MCA_ORIGINAL;
    }

    /**
     * Compatibility alias of {@link #conversationsDialogueEnabled()}: MINIMAL keeps the numbered
     * list, its digit shortcuts and Townstead badges, so this is not a RESPONSIVE-only gate.
     */
    public static boolean numberingEnabled() {
        return conversationsDialogueEnabled();
    }

    public static boolean numericShortcutsEnabled() {
        try {
            return numberingEnabled() && McaConversationsConfig.CLIENT.numericResponseShortcuts.get();
        } catch (Throwable ignored) {
            return true;
        }
    }

    /** Chat replies are a separate frontend: the graphical dialogue style must not gate them. */
    public static boolean chatShortcutsEnabled() {
        try {
            return McaConversationsConfig.CLIENT.chatNumericShortcuts.get();
        } catch (Throwable ignored) {
            return true;
        }
    }

    public static boolean showHints() {
        try {
            return McaConversationsConfig.CLIENT.showResponseControlHints.get();
        } catch (Throwable ignored) {
            return true;
        }
    }

    public static McaConversationsConfig.MotionMode motionMode() {
        try {
            return McaConversationsConfig.CLIENT.motionMode.get();
        } catch (Throwable ignored) {
            return McaConversationsConfig.DEFAULT_MOTION_MODE;
        }
    }

    public static double uiSoundVolume() {
        try {
            return McaConversationsConfig.CLIENT.uiSoundVolume.get();
        } catch (Throwable ignored) {
            return 0.65D;
        }
    }

    public static boolean speakerNameAccent() {
        try {
            return McaConversationsConfig.CLIENT.speakerNameAccent.get();
        } catch (Throwable ignored) {
            return true;
        }
    }

    public static boolean showSpeakerPortrait() {
        try {
            return McaConversationsConfig.CLIENT.showSpeakerPortrait.get();
        } catch (Throwable ignored) {
            return true;
        }
    }

    public static McaConversationsConfig.QuestionReveal questionRevealMode() {
        try {
            return McaConversationsConfig.CLIENT.questionRevealMode.get();
        } catch (Throwable ignored) {
            return McaConversationsConfig.QuestionReveal.OFF;
        }
    }

    public static boolean select(int absoluteIndex, UUID villagerId) {
        return select(absoluteIndex, villagerId, null);
    }

    public static void returnToTopics(UUID villagerId) {
        ClientChoiceState.Lapse lapse = ClientChoiceMessages.state().lapse().orElse(null);
        if (villagerId != null && lapse != null && lapse.backToTopics()) {
            PacketDistributor.sendToServer(
                    new dev.otectus.mcaconversations.network.ChoiceReturnC2S(lapse.revision(), villagerId));
        }
    }

    /**
     * As above, recording what was sent.
     *
     * <p>{@code answerText} is the line the player actually read on the card. When the caller has no
     * resolved text, use the same answer label as the chat frontend's numbered list.
     */
    public static boolean select(int absoluteIndex, UUID villagerId,
                                 net.minecraft.network.chat.Component answerText) {
        ClientChoiceState state = ClientChoiceMessages.state();
        ClientChoiceState.ClientChoiceOffer offer = state.offer().orElse(null);
        if (offer == null || !state.lock(absoluteIndex)) {
            return false;
        }
        net.minecraft.network.chat.Component recorded = answerText;
        if (recorded == null && absoluteIndex >= 0 && absoluteIndex < offer.answerIds().size()) {
            recorded = net.minecraft.network.chat.Component.translatable(
                    "dialogue." + offer.questionId() + "." + offer.answerIds().get(absoluteIndex));
        }
        ClientDialogueHistory.submitted(offer.revision(), absoluteIndex, recorded);
        float volume = (float) uiSoundVolume();
        if (volume > 0.0F) {
            Minecraft.getInstance().getSoundManager().play(
                    SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK.value(), 1.0F, volume));
        }
        // NeoForge payload API: the SimpleChannel is gone; the payload goes straight to the server.
        PacketDistributor.sendToServer(
                new ChoiceSelectC2S(offer.revision(), absoluteIndex, villagerId));
        return true;
    }
}
