package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import dev.otectus.mcaconversations.network.ChoiceOfferS2C;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

/** Physical-client packet sink, kept out of common initialization paths. */
public final class ClientChoiceMessages {

    private static final ClientChoiceState STATE = new ClientChoiceState();

    private ClientChoiceMessages() {
    }

    public static ClientChoiceState state() {
        return STATE;
    }

    public static void accept(ChoiceOfferS2C message) {
        Minecraft minecraft = Minecraft.getInstance();
        long tick = minecraft.player == null ? 0L : minecraft.player.tickCount;
        STATE.accept(new ClientChoiceState.ClientChoiceOffer(message.revision(), message.questionId(),
                message.answerIds(), message.frontend(), tick));
    }

    public static void clear(ChoiceClearS2C message) {
        // Read before the clear, because the state that knows which frontend lapsed is the state
        // about to be emptied.
        ConversationSession.Frontend frontend = STATE.offer()
                .map(ClientChoiceState.ClientChoiceOffer::frontend).orElse(null);
        boolean changed = STATE.clear(message.revision());
        Minecraft minecraft = Minecraft.getInstance();
        if (changed && message.reason() == ChoiceClearS2C.Reason.EXPIRED && minecraft.player != null) {
            minecraft.player.displayClientMessage(
                    Component.translatable("gui.mcaconversations.responses.expired"), true);
            // Under MCA_ORIGINAL the graphical menu is MCA's, and narrating over it would announce
            // the same lapse twice. A chat offer is a separate frontend that Conversations owns
            // whatever the dialogue style is, so it still narrates.
            if (ClientChoiceController.conversationsDialogueEnabled()
                    || frontend == ConversationSession.Frontend.CHAT) {
                DialogueChoiceNarrator.expired();
            }
        }
    }
}
