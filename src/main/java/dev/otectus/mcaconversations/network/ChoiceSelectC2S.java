package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

/** Revisioned index selection; it deliberately contains no question or answer id. */
public record ChoiceSelectC2S(long revision, int absoluteIndex, UUID villagerId) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ChoiceSelectC2S> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "choice_select"));

    public static final StreamCodec<FriendlyByteBuf, ChoiceSelectC2S> STREAM_CODEC =
            StreamCodec.of(ChoiceSelectC2S::encode, ChoiceSelectC2S::decode);

    static void encode(FriendlyByteBuf buffer, ChoiceSelectC2S message) {
        buffer.writeVarLong(message.revision());
        buffer.writeVarInt(message.absoluteIndex());
        buffer.writeBoolean(message.villagerId() != null);
        if (message.villagerId() != null) {
            buffer.writeUUID(message.villagerId());
        }
    }

    static ChoiceSelectC2S decode(FriendlyByteBuf buffer) {
        long revision = buffer.readVarLong();
        int index = buffer.readVarInt();
        UUID villager = buffer.readBoolean() ? buffer.readUUID() : null;
        return new ChoiceSelectC2S(revision, index, villager);
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
