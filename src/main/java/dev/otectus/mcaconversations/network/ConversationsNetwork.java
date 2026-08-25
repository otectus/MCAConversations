package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

/**
 * The mod's (first and only) network channel. One tiny C2S message: {@link TypingStatusC2S}, sent by
 * the client while the chat screen is open so nearby villagers can turn toward the typing player
 * (chat-mode attention). The server fully re-validates on receipt — feature flags, opt-in — so a
 * stray or malicious packet can at most make villagers glance at the sender.
 *
 * <p>This is chat mode's one deviation from the "no new client code/packets" posture: typing state
 * simply does not exist server-side. The mod is already required on both sides (MCA dependency), and
 * a client that never sends pings just gets no typing-attention — everything else works unchanged.
 */
public final class ConversationsNetwork {

    private static final String PROTOCOL = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(McaConversations.MOD_ID, "main"),
            () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);

    private ConversationsNetwork() {
    }

    public static void register() {
        CHANNEL.registerMessage(0, TypingStatusC2S.class,
                TypingStatusC2S::encode, TypingStatusC2S::decode, TypingStatusC2S::handle);
    }

    /** {@code typing=true} while the chat screen is open (re-pinged ~1/s); {@code false} on close. */
    public record TypingStatusC2S(boolean typing) {

        static void encode(TypingStatusC2S msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.typing);
        }

        static TypingStatusC2S decode(FriendlyByteBuf buf) {
            return new TypingStatusC2S(buf.readBoolean());
        }

        static void handle(TypingStatusC2S msg, Supplier<NetworkEvent.Context> ctx) {
            NetworkEvent.Context context = ctx.get();
            context.enqueueWork(() -> {
                ServerPlayer sender = context.getSender();
                if (sender != null) {
                    ChatModeDispatcher.onTypingStatus(sender, msg.typing());
                }
            });
            context.setPacketHandled(true);
        }
    }
}
