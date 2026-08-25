package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * The mod's only packet: {@code typing=true} while the client's chat screen is open (re-pinged about
 * once a second), {@code false} when it closes.
 *
 * <p>Deliberately carries nothing but the flag. No UUID, position, radius or target — the server
 * derives the player from the connection and re-validates every feature flag, opt-in and liveness
 * check itself, so a stray or hostile packet can at most make villagers glance at its own sender.
 */
public record TypingStatusC2S(boolean typing) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<TypingStatusC2S> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "typing_status"));

    public static final StreamCodec<ByteBuf, TypingStatusC2S> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.BOOL, TypingStatusC2S::typing,
                    TypingStatusC2S::new);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
