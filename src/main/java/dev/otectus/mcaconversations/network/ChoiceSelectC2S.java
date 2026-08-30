package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ChoiceSelectionService;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

/** Revisioned index selection; it deliberately contains no question or answer id. */
public record ChoiceSelectC2S(long revision, int absoluteIndex, UUID villagerId) {

    static void encode(ChoiceSelectC2S message, FriendlyByteBuf buffer) {
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

    static void handle(ChoiceSelectC2S message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer sender = context.getSender();
            if (sender != null) {
                ChoiceSelectionService.select(sender, message.revision(),
                        message.absoluteIndex(), message.villagerId());
            }
        });
        context.setPacketHandled(true);
    }
}
