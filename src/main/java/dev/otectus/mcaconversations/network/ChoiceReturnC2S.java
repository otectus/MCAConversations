package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ChoiceSelectionService;
import net.minecraft.network.FriendlyByteBuf;
import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

/** Requests a fresh topic menu for a refused GUI revision. Carries no executable answer. */
public record ChoiceReturnC2S(long revision, UUID villagerId) implements CustomPacketPayload {
    static void encode(FriendlyByteBuf buffer, ChoiceReturnC2S message) {
        buffer.writeVarLong(message.revision());
        buffer.writeUUID(message.villagerId());
    }

    static ChoiceReturnC2S decode(FriendlyByteBuf buffer) {
        return new ChoiceReturnC2S(buffer.readVarLong(), buffer.readUUID());
    }


    public static final CustomPacketPayload.Type<ChoiceReturnC2S> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "choice_return"));
    public static final StreamCodec<FriendlyByteBuf, ChoiceReturnC2S> STREAM_CODEC =
            StreamCodec.of(ChoiceReturnC2S::encode, ChoiceReturnC2S::decode);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() { return TYPE; }
}
