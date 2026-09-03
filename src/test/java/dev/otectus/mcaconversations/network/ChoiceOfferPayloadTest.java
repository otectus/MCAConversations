package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Port-only: pins the exact wire bytes the 1.20.1 SimpleChannel encoder produced, so the payload
 * rewrite cannot silently reorder or re-widen a field.
 */
class ChoiceOfferPayloadTest {

    @Test
    void theTypeIdIsStable() {
        assertEquals(
                ResourceLocation.fromNamespaceAndPath("mcaconversations", "choice_offer"),
                ChoiceOfferS2C.TYPE.id());
    }

    @Test
    void theByteOrderMatchesTheForgeEncoder() {
        // varLong 42 | enum CHAT (varInt ordinal 1) | utf "q" | varInt count 2 | utf "a" | utf "bb"
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceOfferS2C.STREAM_CODEC.encode(buffer, new ChoiceOfferS2C(42L,
                ConversationSession.Frontend.CHAT, "q", List.of("a", "bb")));
        byte[] actual = new byte[buffer.readableBytes()];
        buffer.getBytes(0, actual);
        assertArrayEquals(new byte[]{0x2A, 0x01, 0x01, 0x71, 0x02, 0x01, 0x61, 0x02, 0x62, 0x62},
                actual);

        ChoiceOfferS2C decoded = ChoiceOfferS2C.STREAM_CODEC.decode(buffer);
        assertEquals(new ChoiceOfferS2C(42L, ConversationSession.Frontend.CHAT, "q",
                List.of("a", "bb")), decoded);
        assertFalse(buffer.isReadable(), "the decoder must consume exactly what the encoder wrote");
    }

    @Test
    void anOverlongCountIsRejectedBeforeAllocating() {
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        buffer.writeVarLong(1L);
        buffer.writeEnum(ConversationSession.Frontend.GUI);
        buffer.writeUtf("q", ChoiceOfferS2C.MAX_ID_LENGTH);
        buffer.writeVarInt(ChoiceOfferS2C.MAX_CHOICES + 1);
        assertThrows(DecoderException.class, () -> ChoiceOfferS2C.STREAM_CODEC.decode(buffer));
    }
}
