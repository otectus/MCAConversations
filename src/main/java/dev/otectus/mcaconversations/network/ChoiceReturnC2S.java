package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

/**
 * Requests a fresh topic menu for a refused GUI revision. Carries no executable answer.
 *
 * <p>Since protocol 4 it names its discussion for the same reason a selection does: going back to
 * the topic list is a server-revalidated request, and a request from a conversation that has ended
 * must not open a menu on the one that replaced it.
 */
public record ChoiceReturnC2S(ConversationRef handle, long revision) implements CustomPacketPayload {

    public ChoiceReturnC2S {
        handle = handle == null ? ConversationRef.NONE : handle;
    }

    /** The villager this request claims to be returning to, or null when the client named none. */
    public UUID villagerId() {
        return handle.villagerId();
    }

    static void encode(FriendlyByteBuf buffer, ChoiceReturnC2S message) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeVarLong(message.revision());
    }

    static ChoiceReturnC2S decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        return new ChoiceReturnC2S(handle, buffer.readVarLong());
    }

    public static final CustomPacketPayload.Type<ChoiceReturnC2S> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "choice_return"));
    public static final StreamCodec<FriendlyByteBuf, ChoiceReturnC2S> STREAM_CODEC =
            StreamCodec.of(ChoiceReturnC2S::encode, ChoiceReturnC2S::decode);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() { return TYPE; }
}
