package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ConversationPresence;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * "I am still reading this." One heartbeat for one open discussion (spec §4.3, §7).
 *
 * <p>A player reading a long reply is not idle, and the old fix for that was to give a GUI offer no
 * timeout at all — which meant a client that crashed mid-conversation left its villager attending
 * nobody until something else happened to notice. The heartbeat replaces both: presence is renewed
 * while the screen is genuinely open, and stops the moment the client stops.
 *
 * <p>Carries the handle and nothing else. The sender comes from the connection, so a heartbeat can
 * only ever renew a discussion the sender actually owns; one naming a retired handle is recorded
 * nowhere and can neither revive that discussion nor take a villager from its current owner.
 *
 * <p><b>Recording only.</b> What expiry does with the timestamps is the presence lease, which is
 * separate work; this packet's contract is that the timestamp is honest and unspoofable.
 */
public record ConversationPresenceC2S(ConversationRef handle) {

    public ConversationPresenceC2S {
        handle = handle == null ? ConversationRef.NONE : handle;
    }

    static void encode(ConversationPresenceC2S message, FriendlyByteBuf buffer) {
        ConversationRef.write(buffer, message.handle());
    }

    static ConversationPresenceC2S decode(FriendlyByteBuf buffer) {
        return new ConversationPresenceC2S(ConversationRef.read(buffer));
    }

    static void handle(ConversationPresenceC2S message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer sender = context.getSender();
            if (sender != null) {
                ConversationPresence.heartbeat(sender.getUUID(), message.handle().sessionId(),
                        message.handle().villagerId(), sender.level().getGameTime());
            }
        });
        context.setPacketHandled(true);
    }
}
