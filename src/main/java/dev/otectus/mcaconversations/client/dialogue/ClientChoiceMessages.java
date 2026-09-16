package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import dev.otectus.mcaconversations.network.ChoiceOfferS2C;
import dev.otectus.mcaconversations.network.ChoicePacketSink;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

/** Physical-client packet sink, installed by client setup and never named by common code. */
public final class ClientChoiceMessages implements ChoicePacketSink {

    public static final ClientChoiceMessages INSTANCE = new ClientChoiceMessages();

    private static final ClientChoiceState STATE = new ClientChoiceState();
    private static Object connection;

    private ClientChoiceMessages() {
    }

    public static ClientChoiceState state() {
        synchronizeConnection();
        return STATE;
    }

    private static void synchronizeConnection() {
        Object current = Minecraft.getInstance().getConnection();
        if (connection != current) {
            STATE.resetConnection();
            // A new connection is a new sequence of revisions and a new set of conversations, so the
            // delivered history goes with the old one. This is also the disconnect and world-change
            // path: leaving a world leaves nothing behind in memory to be re-read later.
            ClientDialogueHistory.clear();
            connection = current;
        }
    }

    @Override
    public void offer(ChoiceOfferS2C message) {
        accept(message);
    }

    @Override
    public void clear(ChoiceClearS2C message) {
        applyClear(message);
    }

    public static void accept(ChoiceOfferS2C message) {
        synchronizeConnection();
        Minecraft minecraft = Minecraft.getInstance();
        long tick = minecraft.player == null ? 0L : minecraft.player.tickCount;
        STATE.accept(new ClientChoiceState.ClientChoiceOffer(message.revision(), message.questionId(),
                message.answerIds(), message.frontend(), tick));
    }

    public static void applyClear(ChoiceClearS2C message) {
        synchronizeConnection();
        // Read before the clear, because the state that knows which frontend lapsed is the state
        // about to be emptied.
        ConversationSession.Frontend frontend = STATE.offer()
                .map(ClientChoiceState.ClientChoiceOffer::frontend)
                .orElseGet(() -> STATE.lapse().map(ClientChoiceState.Lapse::frontend).orElse(null));
        boolean changed = STATE.clear(message.revision(), message.reason());
        // The outcome the player was told, recorded against the reply it answers, before anything
        // else acts on the clear.
        ClientDialogueHistory.resolved(message.revision(), message.reason());
        Minecraft minecraft = Minecraft.getInstance();
        if (changed && message.reason().explained() && minecraft.player != null) {
            minecraft.player.displayClientMessage(explanation(message.reason()), true);
            // Under MCA_ORIGINAL the graphical menu is MCA's, and narrating over it would announce
            // the same lapse twice. A chat offer is a separate frontend that Conversations owns
            // whatever the dialogue style is, so it still narrates.
            if (ClientChoiceController.conversationsDialogueEnabled()
                    || frontend == ConversationSession.Frontend.CHAT) {
                DialogueChoiceNarrator.lapsed(message.reason());
            }
        }
    }

    /** The sentence a refused answer is explained with. One per reason; never a generic lapse. */
    public static Component explanation(ChoiceClearS2C.Reason reason) {
        return Component.translatable(switch (reason) {
            case CONTENT_RELOADED -> "gui.mcaconversations.responses.content_reloaded";
            case SPEAKER_UNAVAILABLE -> "gui.mcaconversations.responses.speaker_unavailable";
            case OUT_OF_RANGE -> "gui.mcaconversations.responses.out_of_range";
            case REQUIREMENTS_CHANGED -> "gui.mcaconversations.responses.requirements_changed";
            case EXECUTION_FAILED -> "gui.mcaconversations.responses.execution_failed";
            default -> "gui.mcaconversations.responses.expired";
        });
    }

    /** The one action a lapse shell offers: a revalidated way back, or simply out. */
    public static Component action(ClientChoiceState.Lapse lapse) {
        return Component.translatable(lapse.backToTopics()
                ? "gui.mcaconversations.responses.back_to_topics"
                : "gui.mcaconversations.responses.close");
    }
}
