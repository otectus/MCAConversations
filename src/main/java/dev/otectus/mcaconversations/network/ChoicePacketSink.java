package dev.otectus.mcaconversations.network;

/**
 * Where a decoded choice packet goes once it has crossed the wire.
 *
 * <p>Decoding and registration are common work — a dedicated server has to be able to read its own
 * protocol — but handling one is not: the only thing that can act on an offer is a screen. Both S2C
 * packets used to name {@code client.dialogue.ClientChoiceMessages} directly, which put a client
 * class in the constant pool of common code and left {@code DistExecutor} as the only thing between
 * a dedicated server and a class it must never load.
 *
 * <p>So the packets talk to this interface instead. The default implementation does nothing, which
 * is exactly right for a server; the physical client installs its own during client setup.
 */
public interface ChoicePacketSink {

    /** The sink a dedicated server keeps for the life of the process. */
    ChoicePacketSink NONE = new ChoicePacketSink() {
    };

    default void offer(ChoiceOfferS2C message) {
    }

    default void clear(ChoiceClearS2C message) {
    }

    /** The server accepted a discussion; the client may now name it on everything it sends. */
    default void opened(ConversationOpenedS2C message) {
    }

    /** That discussion has ended. Only the matching UI is retired; a successor's is untouched. */
    default void closed(ConversationClosedS2C message) {
    }
}
