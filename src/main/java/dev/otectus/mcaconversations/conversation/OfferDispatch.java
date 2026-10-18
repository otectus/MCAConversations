package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.network.ChoiceOfferS2C;
import dev.otectus.mcaconversations.network.ConversationRef;
import dev.otectus.mcaconversations.network.ConversationsNetwork;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Sends answer offers so that each one names the line it belongs to (protocol 5).
 *
 * <h2>Why offers wait for the end of the turn</h2>
 *
 * <p>MCA's {@code next} action sends the answer list <em>before</em> the page's own prompt, and a
 * result's {@code say} runs in JSON order, often after the {@code next}. An offer sent the moment its
 * answer list passes the outgoing-packet hook would therefore name the line <em>before</em> the one
 * the player is about to read, and every page would look superseded on arrival. So inside a turn this
 * mod drives — a numbered reply, an owned click, a topic return — the graphical offer is held and sent
 * when the turn finishes, naming the last line the turn delivered: exactly the line on the card when
 * the answers become clickable. A turn is one synchronous call on the server thread, so nothing waits
 * longer than the rest of that call.
 *
 * <p>An offer minted outside any such turn — MCA opening its screen, MCA's own native answers, the
 * chat frontend — is sent at once naming {@code -1}: "no claim", which the client never holds back and
 * never treats as superseded. That keeps every path this mod does not drive byte-for-byte as it
 * behaved on protocol 4.
 *
 * <p>Server thread only. The turn is thread-confined, like {@link ContentOperation}, and nesting is a
 * no-op: only the outermost turn sends.
 */
public final class OfferDispatch {

    private static final ThreadLocal<Turn> CURRENT = new ThreadLocal<>();

    private OfferDispatch() {
    }

    /** An offer held until its turn ends. */
    private record Pending(ServerPlayer player, ConversationRef ref, long revision) {
    }

    /** One turn: the offers it minted, keyed by player, latest wins. */
    public static final class Turn implements AutoCloseable {
        private final Turn parent;
        private final Map<UUID, Pending> pending;
        private boolean closed;

        private Turn(Turn parent) {
            this.parent = parent;
            this.pending = parent == null ? new LinkedHashMap<>() : parent.pending;
        }

        @Override
        public void close() {
            if (closed) {
                return;
            }
            closed = true;
            if (CURRENT.get() == this) {
                if (parent == null) {
                    CURRENT.remove();
                } else {
                    CURRENT.set(parent);
                }
            }
            if (parent == null) {
                flush(this);
            }
        }
    }

    /** Opens a turn on this thread, or joins the one already open. Always close it. */
    public static Turn openTurn() {
        Turn turn = new Turn(CURRENT.get());
        CURRENT.set(turn);
        return turn;
    }

    /** True while a turn is open on this thread. */
    public static boolean inTurn() {
        return CURRENT.get() != null;
    }

    /**
     * Sends {@code offer}, or holds it for the end of the open turn.
     *
     * <p>Only a graphical offer of an identified discussion is ever held: that is the only case where
     * the client can match the line's identity against the offer's, so it is the only case where
     * naming a line buys anything.
     */
    public static void offer(ServerPlayer player, ConversationRef ref, ConversationSession.ChoiceOffer offer) {
        if (player == null || offer == null) {
            return;
        }
        Turn turn = CURRENT.get();
        if (turn != null && holdable(ref, offer)) {
            turn.pending.put(player.getUUID(), new Pending(player, ref, offer.revision()));
            return;
        }
        ConversationsNetwork.sendOffer(player, ChoiceOfferS2C.from(ref, offer, -1L));
    }

    /**
     * Drops a held offer whose revision has just been cleared, so a successor a failed attempt minted
     * is never sent after the clear that retired it.
     */
    public static void cancel(ServerPlayer player, long revision) {
        Turn turn = CURRENT.get();
        if (turn == null || player == null) {
            return;
        }
        Pending held = turn.pending.get(player.getUUID());
        if (held != null && held.revision() == revision) {
            turn.pending.remove(player.getUUID());
        }
    }

    static boolean holdable(ConversationRef ref, ConversationSession.ChoiceOffer offer) {
        return offer.frontend() == ConversationSession.Frontend.GUI && ref != null && ref.identified();
    }

    /**
     * The line a held offer names: the last graphical line of the discussion the ref identifies, or
     * -1 when the session now serves a different discussion or has said nothing in this one.
     */
    static long namedLine(ConversationSession session, ConversationRef ref) {
        if (session == null || ref == null || !ref.identified()
                || !ref.sessionId().equals(session.utteranceIdentity())) {
            return -1L;
        }
        return session.lastUtteranceSeq(ConversationSession.Frontend.GUI);
    }

    private static void flush(Turn turn) {
        if (turn.pending.isEmpty()) {
            return;
        }
        List<Pending> due = new ArrayList<>(turn.pending.values());
        turn.pending.clear();
        for (Pending held : due) {
            try {
                if (held.player().hasDisconnected()) {
                    continue;
                }
                ConversationSession session = ConversationSessions.raw(held.player().getUUID()).orElse(null);
                ConversationSession.ChoiceOffer live = session == null ? null
                        : session.currentOffer().orElse(null);
                if (live == null || live.revision() != held.revision() || live.consumed()) {
                    // Replaced or answered while the turn was still running; its successor, if any,
                    // was held under the same key and is the one that goes.
                    continue;
                }
                long seq = namedLine(session, held.ref());
                session.bindOfferUtterance(live.revision(), seq);
                ConversationsNetwork.sendOffer(held.player(), ChoiceOfferS2C.from(held.ref(), live, seq));
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("held offer for {} could not be sent", held.player().getUUID(), t);
            }
        }
    }

    /** Test seam: abandons any turn left open on this thread. */
    public static void resetForTesting() {
        CURRENT.remove();
    }
}
