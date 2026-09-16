package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import dev.otectus.mcaconversations.network.ChoiceOfferS2C;
import dev.otectus.mcaconversations.network.ChoicePacketSink;
import dev.otectus.mcaconversations.network.ConversationClosedS2C;
import dev.otectus.mcaconversations.network.ConversationOpenedS2C;
import dev.otectus.mcaconversations.network.ConversationRef;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Physical-client packet sink, installed by client setup and never named by common code.
 *
 * <p>It also keeps the one thing an incoming offer cannot say for itself: which villager this client
 * is looking at. The offer packet carries no identity, so the open interaction screen supplies it,
 * and everything the offer leaves behind is named after that villager rather than after nobody.
 *
 * <p>Since protocol 4 it additionally keeps the <em>handle</em> the server named when it accepted the
 * discussion. That is what every outgoing packet quotes back, and what lets an incoming one be
 * recognised as belonging to a conversation this client has already left. The small ring of
 * {@linkplain #CLOSED dismissed handles} completes it: a delayed open or offer for a window the
 * player has already closed is dropped rather than resurrecting it (spec §7).
 */
public final class ClientChoiceMessages implements ChoicePacketSink {

    public static final ClientChoiceMessages INSTANCE = new ClientChoiceMessages();

    private static final ClientChoiceState STATE = new ClientChoiceState();
    private static Object connection;
    private static Object screen;
    private static UUID screenVillagerId;
    private static boolean screenShown;

    /**
     * How many dismissed discussions to remember. Bounded on purpose: this only has to outlive the
     * packets already in flight for a window that has just closed, and an unbounded set would grow
     * for as long as a connection lasts.
     */
    private static final int REMEMBERED_CLOSES = 32;
    private static final Set<UUID> CLOSED = new LinkedHashSet<>();

    private static UUID handleSessionId;
    private static UUID handleVillagerId;
    private static boolean retiredByServer;

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

    /**
     * The name to put on an outgoing packet about {@code villagerId}.
     *
     * <p>The session travels only when the handle this client holds is actually for that villager.
     * Quoting a handle at the wrong villager would be a claim the server has to refuse, and the
     * honest alternative — naming the villager alone — is exactly what an unmanaged frontend sends.
     */
    public static ConversationRef refFor(UUID villagerId) {
        UUID session = handleSessionId;
        if (session != null && (villagerId == null || villagerId.equals(handleVillagerId))) {
            return new ConversationRef(session, handleVillagerId != null ? handleVillagerId : villagerId);
        }
        return ConversationRef.ofVillager(villagerId);
    }

    /** The discussion this client is in, or {@link ConversationRef#NONE} when it is in none. */
    public static ConversationRef currentRef() {
        UUID session = handleSessionId;
        return session == null ? ConversationRef.NONE : new ConversationRef(session, handleVillagerId);
    }

    /**
     * Forgets the current handle because this client has just asked for it to end.
     *
     * <p>Called from the screen teardown that sends the close, so the heartbeat stops with the
     * window rather than one round trip later, and so a delayed offer for the dismissed discussion
     * has something to be recognised against before the server's own terminal packet arrives.
     */
    public static void forgetHandle() {
        remember(handleSessionId);
        handleSessionId = null;
        handleVillagerId = null;
    }

    private static void remember(UUID sessionId) {
        if (sessionId == null) {
            return;
        }
        CLOSED.add(sessionId);
        while (CLOSED.size() > REMEMBERED_CLOSES) {
            java.util.Iterator<UUID> oldest = CLOSED.iterator();
            oldest.next();
            oldest.remove();
        }
    }

    /**
     * Whether a packet naming {@code ref} is still this client's business.
     *
     * <p>Three answers collapse into one: a packet that names no discussion is judged the way it was
     * before handles existed; one that names a dismissed discussion is dropped; one that names a
     * discussion other than the live one is dropped. A client that somehow has no handle yet accepts
     * the packet and adopts what it says, because the alternative is a screen that never updates.
     */
    private static boolean addressedToUs(ConversationRef ref) {
        if (ref == null || !ref.identified()) {
            return true;
        }
        if (CLOSED.contains(ref.sessionId())) {
            return false;
        }
        return handleSessionId == null || handleSessionId.equals(ref.sessionId());
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
            handleSessionId = null;
            handleVillagerId = null;
            // Handles are minted per server lifetime, so a new connection's cannot collide with the
            // old one's and there is nothing left to recognise.
            CLOSED.clear();
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

    @Override
    public void opened(ConversationOpenedS2C message) {
        synchronizeConnection();
        if (!message.handle().identified() || CLOSED.contains(message.handle().sessionId())) {
            // A delayed acceptance of a window the player already dismissed. Adopting it would give
            // the client a live handle for a conversation that is over.
            return;
        }
        handleSessionId = message.handle().sessionId();
        handleVillagerId = message.handle().villagerId();
    }

    @Override
    public void closed(ConversationClosedS2C message) {
        synchronizeConnection();
        UUID session = message.handle().sessionId();
        remember(session);
        if (session == null || !session.equals(handleSessionId)) {
            // A close for a discussion this client has already replaced. Retiring anything here
            // would close the window the player is looking at because the previous one ended.
            return;
        }
        UUID villager = handleVillagerId;
        handleSessionId = null;
        handleVillagerId = null;
        if (villager == null || villager.equals(screenVillagerId())) {
            STATE.clearLocal(ConversationSession.Frontend.GUI);
            explain(message.reason());
            closeHostScreen();
        }
        STATE.clearLocal(ConversationSession.Frontend.CHAT);
    }

    /**
     * Says why the conversation ended, where a refused answer would have said why it lapsed.
     *
     * <p>The same action-bar line and the same sentences: an ending the player can see the cause of —
     * they walked too far, the villager cannot carry on — reads better than a window that simply
     * vanishes. Endings the player caused themselves say nothing at all; being told "you closed this"
     * for closing it is noise.
     */
    private static void explain(CloseReason reason) {
        ChoiceClearS2C.Reason spoken = explanationOf(reason);
        Minecraft minecraft = Minecraft.getInstance();
        if (spoken != null && minecraft.player != null) {
            minecraft.player.displayClientMessage(explanation(spoken), true);
        }
    }

    /**
     * The existing refusal sentence an ending is explained with, or null for one said in silence.
     *
     * <p>Deliberately a reuse rather than a new set of strings: the player does not need one wording
     * for "you are too far apart to go on" when an answer is refused and another when the window
     * closes a second later. Reasons with nothing useful to say — the player closed it, they turned
     * to somebody else, the conversation simply finished — map to null.
     */
    static ChoiceClearS2C.Reason explanationOf(CloseReason reason) {
        if (reason == null) {
            return null;
        }
        return switch (reason) {
            case OUT_OF_RANGE, DIMENSION_CHANGED -> ChoiceClearS2C.Reason.OUT_OF_RANGE;
            case SPEAKER_DEAD, SPEAKER_UNAVAILABLE, ENTITY_UNLOADED, ATTACKED, DANGER, TAKEN_OVER ->
                    ChoiceClearS2C.Reason.SPEAKER_UNAVAILABLE;
            case CONTENT_RELOADED -> ChoiceClearS2C.Reason.CONTENT_RELOADED;
            case CONTAINED_ERROR -> ChoiceClearS2C.Reason.EXECUTION_FAILED;
            default -> null;
        };
    }

    /**
     * Closes the window the ended discussion was being read in (spec §4.6).
     *
     * <p>Through the screen's own close path, not {@code setScreen(null)}: MCA's interaction screen
     * ends its own interaction from there, and skipping it would leave MCA believing the player is
     * still standing in a conversation that no longer exists. The re-entrant teardown that follows is
     * exactly the ordinary one, minus the outbound close — {@link #retiredByServer()} is what tells
     * it not to send the server a close for a discussion the server has just ended.
     *
     * <p>Only the claimed interaction screen, and only while it is the window actually on top. A
     * terminal packet must never be able to dismiss a screen this mod does not own.
     */
    private static void closeHostScreen() {
        Minecraft minecraft = Minecraft.getInstance();
        if (!(screen instanceof net.minecraft.client.gui.screens.Screen host) || minecraft.screen != host) {
            return;
        }
        retiredByServer = true;
        try {
            host.onClose();
        } catch (Throwable t) {
            dev.otectus.mcaconversations.McaConversations.LOGGER
                    .debug("could not close the window of an ended conversation", t);
        } finally {
            retiredByServer = false;
        }
    }

    /**
     * True while a window is closing because the server said the discussion is over.
     *
     * <p>The teardown in that window then skips the close it would otherwise send. Not merely
     * redundant: that close names a handle the server has already retired, and the honest client
     * behaviour for an ending it was told about is to acknowledge nothing.
     */
    public static boolean retiredByServer() {
        return retiredByServer;
    }

    public static void accept(ChoiceOfferS2C message) {
        synchronizeConnection();
        if (!addressedToUs(message.handle())) {
            return;
        }
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
        if (!addressedToUs(message.handle())) {
            // A refusal addressed to a conversation this client has left. The revision watermark
            // alone could not tell it from a refusal of the card on screen.
            return;
        }
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
