package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.CloseReason;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * The terminal packet for one discussion: this handle is over, and here is why (spec §4.5 step 7).
 *
 * <p>Named by handle, never by player. A close for a discussion the client has already replaced is
 * therefore something the client can recognise and ignore, which is the whole point — the ending of
 * a conversation must not be able to close the one after it.
 *
 * <p>The reason travels as {@link CloseReason#wireId()}, an explicit identifier rather than an
 * ordinal, and a reason this build does not know decodes as {@link CloseReason#COMPLETED}: a server
 * that learns a new ending must not break a client that has not.
 */
public record ConversationClosedS2C(ConversationRef handle, CloseReason reason) {

    public ConversationClosedS2C {
        handle = handle == null ? ConversationRef.NONE : handle;
        reason = reason == null ? CloseReason.COMPLETED : reason;
    }

    static void encode(ConversationClosedS2C message, FriendlyByteBuf buffer) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeVarInt(message.reason().wireId());
    }

    static ConversationClosedS2C decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        int wireId = buffer.readVarInt();
        return new ConversationClosedS2C(handle,
                CloseReason.byWireId(wireId).orElse(CloseReason.COMPLETED));
    }

    static void handle(ConversationClosedS2C message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> ConversationsNetwork.sink().closed(message)));
        context.setPacketHandled(true);
    }
}
