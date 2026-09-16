package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

/**
 * MCA: Conversations' strict client/server channel. Choice packets carry only a revision and an
 * index; question and answer ids remain server-owned.
 *
 * <p>This is chat mode's one deviation from the "no new client code/packets" posture: typing state
 * simply does not exist server-side. The mod is already required on both sides (MCA dependency), and
 * a client that never sends pings just gets no typing-attention — everything else works unchanged.
 */
public final class ConversationsNetwork {

    /**
     * The channel protocol, kept out of Java prose: it is a build property like every other version
     * string in this project, expanded into {@code mcaconversations-network.properties} by
     * {@code processResources}. The literal below is only the fallback for a classpath that somehow
     * has the classes without the resource, and it must stay in step with {@code gradle.properties}.
     */
    public static final String PROTOCOL = NetworkProtocol.version();
    private static final java.util.Set<String> WARNED_OVERSIZED_OFFERS =
            java.util.concurrent.ConcurrentHashMap.newKeySet();

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(McaConversations.MOD_ID, "main"),
            () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);

    private static volatile ChoicePacketSink sink = ChoicePacketSink.NONE;

    private ConversationsNetwork() {
    }

    /**
     * Installs the physical client's packet sink. Called from client setup only; a dedicated server
     * never calls it and keeps {@link ChoicePacketSink#NONE}.
     */
    public static void installSink(ChoicePacketSink incoming) {
        sink = incoming == null ? ChoicePacketSink.NONE : incoming;
    }

    static ChoicePacketSink sink() {
        return sink;
    }

    public static void register() {
        CHANNEL.registerMessage(0, TypingStatusC2S.class,
                TypingStatusC2S::encode, TypingStatusC2S::decode, TypingStatusC2S::handle,
                java.util.Optional.of(NetworkDirection.PLAY_TO_SERVER));
        CHANNEL.registerMessage(1, ChoiceOfferS2C.class,
                ChoiceOfferS2C::encode, ChoiceOfferS2C::decode, ChoiceOfferS2C::handle,
                java.util.Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        CHANNEL.registerMessage(2, ChoiceClearS2C.class,
                ChoiceClearS2C::encode, ChoiceClearS2C::decode, ChoiceClearS2C::handle,
                java.util.Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        CHANNEL.registerMessage(3, ChoiceSelectC2S.class,
                ChoiceSelectC2S::encode, ChoiceSelectC2S::decode, ChoiceSelectC2S::handle,
                java.util.Optional.of(NetworkDirection.PLAY_TO_SERVER));
        CHANNEL.registerMessage(4, ChoiceReturnC2S.class,
                ChoiceReturnC2S::encode, ChoiceReturnC2S::decode, ChoiceReturnC2S::handle,
                java.util.Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    public static void sendOffer(ServerPlayer player, ChoiceOfferS2C offer) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), offer);
    }

    public static void clearOffer(ServerPlayer player, long revision, ChoiceClearS2C.Reason reason) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new ChoiceClearS2C(revision, reason));
    }

    public static void warnOversizedOffer(String question, int count) {
        if (WARNED_OVERSIZED_OFFERS.size() < 128 && WARNED_OVERSIZED_OFFERS.add(question)) {
            McaConversations.LOGGER.warn(
                    "Question '{}' offered {} answers; numbered synchronization is disabled above {}",
                    question, count, ChoiceOfferS2C.MAX_CHOICES);
        }
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
