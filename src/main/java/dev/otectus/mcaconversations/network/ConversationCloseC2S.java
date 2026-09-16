package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.CloseReason;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

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
 * server's findings, and putting them on the wire would let a crafted payload manufacture an ending
 * it has no evidence for. The server stamps {@link CloseReason#CLIENT_CLOSED} itself.
 *
 * <p>Losing this payload costs nothing durable: presence expiry and the distance policy end an
 * abandoned discussion anyway. Nothing waits for an acknowledgement, and no villager stays pinned
 * because a client failed to send one.
 */
public record ConversationCloseC2S(ConversationRef handle) implements CustomPacketPayload {

    public ConversationCloseC2S {
        handle = handle == null ? ConversationRef.NONE : handle;
    }

    public static final CustomPacketPayload.Type<ConversationCloseC2S> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "conversation_close"));
    public static final StreamCodec<FriendlyByteBuf, ConversationCloseC2S> STREAM_CODEC =
            StreamCodec.of(ConversationCloseC2S::encode, ConversationCloseC2S::decode);

    static void encode(FriendlyByteBuf buffer, ConversationCloseC2S message) {
        ConversationRef.write(buffer, message.handle());
    }

    static ConversationCloseC2S decode(FriendlyByteBuf buffer) {
        return new ConversationCloseC2S(ConversationRef.read(buffer));
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() { return TYPE; }
}
