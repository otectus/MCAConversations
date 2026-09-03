package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Clears one consumed, expired, or otherwise superseded client offer revision. */
public record ChoiceClearS2C(long revision, Reason reason) implements CustomPacketPayload {

    public enum Reason { NONE, CONSUMED, EXPIRED }

    public static final CustomPacketPayload.Type<ChoiceClearS2C> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "choice_clear"));

    public static final StreamCodec<FriendlyByteBuf, ChoiceClearS2C> STREAM_CODEC =
            StreamCodec.of(ChoiceClearS2C::encode, ChoiceClearS2C::decode);

    static void encode(FriendlyByteBuf buffer, ChoiceClearS2C message) {
        buffer.writeVarLong(message.revision());
        buffer.writeEnum(message.reason());
    }

    static ChoiceClearS2C decode(FriendlyByteBuf buffer) {
        return new ChoiceClearS2C(buffer.readVarLong(), buffer.readEnum(Reason.class));
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
