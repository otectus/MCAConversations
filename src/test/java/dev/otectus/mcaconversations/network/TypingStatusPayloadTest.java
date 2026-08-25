package dev.otectus.mcaconversations.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The mod's only packet: its identity on the wire, and that it carries nothing else. */
class TypingStatusPayloadTest {

    @Test
    void theTypeIdIsStable() {
        // The id is half the protocol contract. Renaming it makes every client silently fail to
        // register the payload against a server that still uses the old name.
        assertEquals(
                ResourceLocation.fromNamespaceAndPath("mcaconversations", "typing_status"),
                TypingStatusC2S.TYPE.id());
    }

    @Test
    void bothValuesRoundTripThroughTheStreamCodec() {
        for (boolean typing : new boolean[]{true, false}) {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            TypingStatusC2S.STREAM_CODEC.encode(buf, new TypingStatusC2S(typing));
            TypingStatusC2S decoded = TypingStatusC2S.STREAM_CODEC.decode(buf);
            assertEquals(typing, decoded.typing());
            assertFalse(buf.isReadable(), "the decoder must consume exactly what the encoder wrote");
        }
    }

    @Test
    void thePayloadIsOneByteAndNothingElse() {
        // Deliberately no UUID, position, radius or target: the server derives the player from the
        // connection and re-validates every gate itself, so a crafted packet can at most make
        // villagers glance at its own sender. A wider payload would hand that choice to the client.
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        TypingStatusC2S.STREAM_CODEC.encode(buf, new TypingStatusC2S(true));
        assertEquals(1, buf.readableBytes());
    }

    @Test
    void theRecordExposesTheFlagAndTheType() {
        TypingStatusC2S payload = new TypingStatusC2S(true);
        assertTrue(payload.typing());
        assertEquals(TypingStatusC2S.TYPE, payload.type());
    }
}
