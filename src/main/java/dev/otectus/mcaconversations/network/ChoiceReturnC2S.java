package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ChoiceSelectionService;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

/** Requests a fresh topic menu for a refused GUI revision. Carries no executable answer. */
public record ChoiceReturnC2S(long revision, UUID villagerId) {
    static void encode(ChoiceReturnC2S message, FriendlyByteBuf buffer) {
        buffer.writeVarLong(message.revision());
        buffer.writeUUID(message.villagerId());
    }

    static ChoiceReturnC2S decode(FriendlyByteBuf buffer) {
        return new ChoiceReturnC2S(buffer.readVarLong(), buffer.readUUID());
    }

    static void handle(ChoiceReturnC2S message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> ChoiceSelectionService.returnToTopics(context.getSender(),
                message.revision(), message.villagerId()));
        context.setPacketHandled(true);
    }
}
