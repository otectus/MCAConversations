package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ChoiceSelectionService;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Requests a fresh topic menu for a refused GUI revision. Carries no executable answer.
 *
 * <p>Since protocol 4 it names its discussion for the same reason a selection does: going back to
 * the topic list is a server-revalidated request, and a request from a conversation that has ended
 * must not open a menu on the one that replaced it.
 */
public record ChoiceReturnC2S(ConversationRef handle, long revision) {

    public ChoiceReturnC2S {
        handle = handle == null ? ConversationRef.NONE : handle;
    }

    /** The villager this request claims to be returning to, or null when the client named none. */
    public java.util.UUID villagerId() {
        return handle.villagerId();
    }

    static void encode(ChoiceReturnC2S message, FriendlyByteBuf buffer) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeVarLong(message.revision());
    }

    static ChoiceReturnC2S decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        return new ChoiceReturnC2S(handle, buffer.readVarLong());
    }

    static void handle(ChoiceReturnC2S message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> ChoiceSelectionService.returnToTopics(context.getSender(),
                message.handle().sessionId(), message.revision(), message.villagerId()));
        context.setPacketHandled(true);
    }
}
