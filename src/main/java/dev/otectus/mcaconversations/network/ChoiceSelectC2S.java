package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

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
public record ChoiceSelectC2S(ConversationRef handle, long revision, int absoluteIndex)
        implements CustomPacketPayload {

    public ChoiceSelectC2S {
        handle = handle == null ? ConversationRef.NONE : handle;
    }

    public static final CustomPacketPayload.Type<ChoiceSelectC2S> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "choice_select"));

    public static final StreamCodec<FriendlyByteBuf, ChoiceSelectC2S> STREAM_CODEC =
            StreamCodec.of(ChoiceSelectC2S::encode, ChoiceSelectC2S::decode);

    /** The villager this click claims to be answering, or null when the client named none. */
    public UUID villagerId() {
        return handle.villagerId();
    }

    static void encode(FriendlyByteBuf buffer, ChoiceSelectC2S message) {
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

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
