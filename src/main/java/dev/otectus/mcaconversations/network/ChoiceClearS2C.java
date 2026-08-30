package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/** Clears one consumed, expired, or otherwise superseded client offer revision. */
public record ChoiceClearS2C(long revision, Reason reason) {

    public enum Reason { NONE, CONSUMED, EXPIRED }

    static void encode(ChoiceClearS2C message, FriendlyByteBuf buffer) {
        buffer.writeVarLong(message.revision());
        buffer.writeEnum(message.reason());
    }

    static ChoiceClearS2C decode(FriendlyByteBuf buffer) {
        return new ChoiceClearS2C(buffer.readVarLong(), buffer.readEnum(Reason.class));
    }

    static void handle(ChoiceClearS2C message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> ClientChoiceMessages.clear(message)));
        context.setPacketHandled(true);
    }
}
