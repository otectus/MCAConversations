package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.CloseReason;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * The terminal payload for one discussion: this handle is over, and here is why (spec §4.5 step 7).
 *
 * <p>Named by handle, never by player. A close for a discussion the client has already replaced is
 * therefore something the client can recognise and ignore, which is the whole point — the ending of
 * a conversation must not be able to close the one after it.
 *
 * <p>The reason travels as {@link CloseReason#wireId()}, an explicit identifier rather than an
 * ordinal, and a reason this build does not know decodes as {@link CloseReason#COMPLETED}: a server
 * that learns a new ending must not break a client that has not.
 */
public record ConversationClosedS2C(ConversationRef handle, CloseReason reason)
        implements CustomPacketPayload {

    public ConversationClosedS2C {
        handle = handle == null ? ConversationRef.NONE : handle;
        reason = reason == null ? CloseReason.COMPLETED : reason;
    }

    public static final CustomPacketPayload.Type<ConversationClosedS2C> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "conversation_closed"));
    public static final StreamCodec<FriendlyByteBuf, ConversationClosedS2C> STREAM_CODEC =
            StreamCodec.of(ConversationClosedS2C::encode, ConversationClosedS2C::decode);

    static void encode(FriendlyByteBuf buffer, ConversationClosedS2C message) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeVarInt(message.reason().wireId());
    }

    static ConversationClosedS2C decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        int wireId = buffer.readVarInt();
        return new ConversationClosedS2C(handle,
                CloseReason.byWireId(wireId).orElse(CloseReason.COMPLETED));
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() { return TYPE; }
}
