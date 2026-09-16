package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

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
 * <p>The lease reads these timestamps and nothing else, which is what makes this payload's contract
 * the whole of its security: the timestamp is honest and unspoofable, and a heartbeat that cannot be
 * attributed to the sender's own live discussion is simply not recorded.
 */
public record ConversationPresenceC2S(ConversationRef handle) implements CustomPacketPayload {

    public ConversationPresenceC2S {
        handle = handle == null ? ConversationRef.NONE : handle;
    }

    public static final CustomPacketPayload.Type<ConversationPresenceC2S> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "conversation_presence"));
    public static final StreamCodec<FriendlyByteBuf, ConversationPresenceC2S> STREAM_CODEC =
            StreamCodec.of(ConversationPresenceC2S::encode, ConversationPresenceC2S::decode);

    static void encode(FriendlyByteBuf buffer, ConversationPresenceC2S message) {
        ConversationRef.write(buffer, message.handle());
    }

    static ConversationPresenceC2S decode(FriendlyByteBuf buffer) {
        return new ConversationPresenceC2S(ConversationRef.read(buffer));
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() { return TYPE; }
}
