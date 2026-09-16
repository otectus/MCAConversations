package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import dev.otectus.mcaconversations.network.ChoiceOfferS2C;
import dev.otectus.mcaconversations.network.ChoicePacketSink;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.UUID;

/**
 * Physical-client packet sink, installed by client setup and never named by common code.
 *
 * <p>It also keeps the one thing an incoming offer cannot say for itself: which villager this client
 * is looking at. The offer packet carries no identity, so the open interaction screen supplies it,
 * and everything the offer leaves behind is named after that villager rather than after nobody.
 */
public final class ClientChoiceMessages implements ChoicePacketSink {

    public static final ClientChoiceMessages INSTANCE = new ClientChoiceMessages();

    private static final ClientChoiceState STATE = new ClientChoiceState();
    private static Object connection;
    private static Object screen;
    private static UUID screenVillagerId;
    private static boolean screenShown;

    private ClientChoiceMessages() {
    }

    public static ClientChoiceState state() {
        synchronizeConnection();
        synchronizeScreen();
        return STATE;
    }

    /**
     * Claims the graphical frontend for the villager an interaction screen was opened for.
     *
     * <p>Called from the screen's own constructor, so the claim is in place before the first offer
     * for it can arrive. A new screen inherits nothing: whatever the previous one left behind
     * belonged to the conversation that ended with it, and is dropped here rather than lingering to
     * be shown to whoever is spoken to next.
     */
    public static void screenOpened(Object owner, UUID villagerId) {
        if (owner == null) {
            return;
        }
        if (screen != owner) {
            STATE.clearLocal(ConversationSession.Frontend.GUI);
        }
        screen = owner;
        screenVillagerId = villagerId;
        screenShown = false;
    }

    /** Releases the claim when that screen closes. A screen may only release its own. */
    public static void screenClosed(Object owner) {
        if (owner != null && screen != owner) {
            return;
        }
        screen = null;
        screenVillagerId = null;
        screenShown = false;
    }

    /** The villager whose interaction screen is open on this client, or null if none is. */
    public static UUID screenVillagerId() {
        synchronizeScreen();
        return screenVillagerId;
    }

    /**
     * Reconciles the claim against the window.
     *
     * <p>A screen that is swapped out rather than closed — MCA's family tree button, a disconnect
     * dialog — never runs its close path, so the claim is also checked against what is actually
     * displayed. The {@code screenShown} latch is what makes that safe during construction: a screen
     * claims before {@code setScreen} installs it, and a claim that has never been displayed is not
     * yet evidence of anything.
     */
    private static void synchronizeScreen() {
        if (screen == null) {
            return;
        }
        Object current = Minecraft.getInstance().screen;
        if (current == screen) {
            screenShown = true;
            return;
        }
        if (!screenShown) {
            return;
        }
        screen = null;
        screenVillagerId = null;
        screenShown = false;
        STATE.clearLocal(ConversationSession.Frontend.GUI);
    }

    private static void synchronizeConnection() {
        Object current = Minecraft.getInstance().getConnection();
        if (connection != current) {
            STATE.resetConnection();
            // A new connection is a new sequence of revisions and a new set of conversations, so the
            // delivered history goes with the old one. This is also the disconnect and world-change
            // path: leaving a world leaves nothing behind in memory to be re-read later.
            ClientDialogueHistory.clear();
            screen = null;
            screenVillagerId = null;
            screenShown = false;
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
        // Only the graphical frontend has a screen, and a graphical offer belongs to the villager
        // that screen was opened for. A chat offer names no villager here; nothing on this client
        // claims one.
        UUID owner = message.frontend() == ConversationSession.Frontend.GUI ? screenVillagerId() : null;
        STATE.accept(new ClientChoiceState.ClientChoiceOffer(message.revision(), owner,
                message.questionId(), message.answerIds(), message.frontend(), tick));
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
