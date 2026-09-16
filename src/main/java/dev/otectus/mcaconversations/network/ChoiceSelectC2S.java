package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ChoiceSelectionService;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Revisioned index selection; it deliberately contains no question or answer id.
 *
 * <p>Since protocol 4 it also names the discussion it was clicked in. The revision was never enough
 * on its own: a client that has been closed and reopened against the same villager produces packets
 * that are indistinguishable by revision alone, and the server used to resolve them all against
 * "whatever this player is doing now". The handle is what makes a straggler refusable.
 *
 * <p>The villager inside the handle is a <em>claim</em>, not proof: the server re-resolves the live
 * entity and re-checks who MCA says is interacting with it. The player is never on the wire at all.
 */
public record ChoiceSelectC2S(ConversationRef handle, long revision, int absoluteIndex) {

    public ChoiceSelectC2S {
        handle = handle == null ? ConversationRef.NONE : handle;
    }

    /** The villager this click claims to be answering, or null when the client named none. */
    public java.util.UUID villagerId() {
        return handle.villagerId();
    }

    static void encode(ChoiceSelectC2S message, FriendlyByteBuf buffer) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeVarLong(message.revision());
        buffer.writeVarInt(message.absoluteIndex());
    }

    static ChoiceSelectC2S decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        long revision = buffer.readVarLong();
        int index = buffer.readVarInt();
        return new ChoiceSelectC2S(handle, revision, index);
    }

    static void handle(ChoiceSelectC2S message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer sender = context.getSender();
            if (sender != null) {
                ChoiceSelectionService.select(sender, message.handle().sessionId(), message.revision(),
                        message.absoluteIndex(), message.villagerId());
            }
        });
        context.setPacketHandled(true);
    }
}
