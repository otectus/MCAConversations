package dev.otectus.mcaconversations.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Port-only: pins the clear payload's id, byte order and, through the ordinal on the wire, the
 * declaration order of {@link ChoiceClearS2C.Reason}.
 */
class ChoiceClearPayloadTest {

    @Test
    void theTypeIdIsStable() {
        assertEquals(
                ResourceLocation.fromNamespaceAndPath("mcaconversations", "choice_clear"),
                ChoiceClearS2C.TYPE.id());
    }

    @Test
    void theByteOrderMatchesTheForgeEncoder() {
        // varLong 5 | enum CONSUMED (varInt ordinal 1)
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceClearS2C.STREAM_CODEC.encode(buffer, new ChoiceClearS2C(5L, ChoiceClearS2C.Reason.CONSUMED));
        byte[] actual = new byte[buffer.readableBytes()];
        buffer.getBytes(0, actual);
        assertArrayEquals(new byte[]{0x05, 0x01}, actual);

        assertEquals(new ChoiceClearS2C(5L, ChoiceClearS2C.Reason.CONSUMED),
                ChoiceClearS2C.STREAM_CODEC.decode(buffer));
        assertFalse(buffer.isReadable(), "the decoder must consume exactly what the encoder wrote");
    }

    @Test
    void everyReasonRoundTrips() {
        for (ChoiceClearS2C.Reason reason : ChoiceClearS2C.Reason.values()) {
            FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
            ChoiceClearS2C.STREAM_CODEC.encode(buffer, new ChoiceClearS2C(1L, reason));
            assertEquals(reason, ChoiceClearS2C.STREAM_CODEC.decode(buffer).reason());
        }
    }
}
