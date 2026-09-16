package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.conversation.ConversationLifecycle;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * The player dismissed their conversation window (spec §7).
 *
 * <p>Sent by the client's own screen teardown so the server learns about a
 * {@link CloseReason#CLIENT_CLOSED} ending promptly instead of waiting for a distance sweep or a
 * heartbeat to lapse. It is a <em>request</em>, not an instruction: the server ends the discussion
 * only when the handle named is the one it currently has for this sender.
 *
 * <p>No reason travels. A client may say that it closed its window; it may not say that the villager
 * was attacked, that the pair drifted apart, or that somebody else took over — those are the
 * server's findings, and putting them on the wire would let a crafted packet manufacture an ending
 * it has no evidence for. The server stamps {@link CloseReason#CLIENT_CLOSED} itself.
 *
 * <p>Losing this packet costs nothing durable: presence expiry and the distance policy end an
 * abandoned discussion anyway. Nothing waits for an acknowledgement, and no villager stays pinned
 * because a client failed to send one.
 */
public record ConversationCloseC2S(ConversationRef handle) {

    public ConversationCloseC2S {
        handle = handle == null ? ConversationRef.NONE : handle;
    }

    static void encode(ConversationCloseC2S message, FriendlyByteBuf buffer) {
        ConversationRef.write(buffer, message.handle());
    }

    static ConversationCloseC2S decode(FriendlyByteBuf buffer) {
        return new ConversationCloseC2S(ConversationRef.read(buffer));
    }

    static void handle(ConversationCloseC2S message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer sender = context.getSender();
            if (sender != null) {
                ConversationLifecycle.terminateIfCurrent(sender.getUUID(), message.handle().sessionId(),
                        message.handle().villagerId(), CloseReason.CLIENT_CLOSED);
            }
        });
        context.setPacketHandled(true);
    }
}
