package dev.otectus.mcaconversations.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Port-only: pins the selection payload's id and byte order, including the optional-villager flag
 * that keeps the absent case three bytes wide.
 */
class ChoiceSelectPayloadTest {

    @Test
    void theTypeIdIsStable() {
        assertEquals(
                ResourceLocation.fromNamespaceAndPath("mcaconversations", "choice_select"),
                ChoiceSelectC2S.TYPE.id());
    }

    @Test
    void theByteOrderMatchesTheForgeEncoder() {
        // varLong 7 | varInt 3 | boolean false (no villager)
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceSelectC2S.STREAM_CODEC.encode(buffer, new ChoiceSelectC2S(7L, 3, null));
        byte[] actual = new byte[buffer.readableBytes()];
        buffer.getBytes(0, actual);
        assertArrayEquals(new byte[]{0x07, 0x03, 0x00}, actual);

        ChoiceSelectC2S decoded = ChoiceSelectC2S.STREAM_CODEC.decode(buffer);
        assertEquals(7L, decoded.revision());
        assertEquals(3, decoded.absoluteIndex());
        assertNull(decoded.villagerId());
        assertFalse(buffer.isReadable(), "the decoder must consume exactly what the encoder wrote");
    }

    @Test
    void aPresentVillagerAddsTheFlagAndSixteenBytes() {
        UUID villager = UUID.fromString("00000000-0000-0001-0000-000000000002");
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceSelectC2S.STREAM_CODEC.encode(buffer, new ChoiceSelectC2S(7L, 3, villager));
        assertEquals(3 + 16, buffer.readableBytes());
        assertEquals(new ChoiceSelectC2S(7L, 3, villager), ChoiceSelectC2S.STREAM_CODEC.decode(buffer));
    }
}
