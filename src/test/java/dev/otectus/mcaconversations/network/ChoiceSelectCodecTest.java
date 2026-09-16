package dev.otectus.mcaconversations.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The selection payload is the one a forged or replayed copy would do the most damage with, so what
 * it carries — and what it deliberately does not — is pinned here.
 */
class ChoiceSelectCodecTest {

    @Test
    void roundTripsTheNamedDiscussion() {
        ConversationRef handle = new ConversationRef(UUID.randomUUID(), UUID.randomUUID());
        ChoiceSelectC2S message = new ChoiceSelectC2S(handle, 12L, 3);
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        try {
            ChoiceSelectC2S.encode(buffer, message);
            ChoiceSelectC2S decoded = ChoiceSelectC2S.decode(buffer);
            assertEquals(message, decoded);
            assertEquals(handle.villagerId(), decoded.villagerId());
            assertEquals(0, buffer.readableBytes());
        } finally {
            buffer.release();
        }
    }

    @Test
    void anUnidentifiedSelectionStillRoundTrips() {
        ChoiceSelectC2S message = new ChoiceSelectC2S(null, 0L, 0);
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        try {
            ChoiceSelectC2S.encode(buffer, message);
            // Two presence flags, a varLong and a varInt: the unnamed case stays four bytes wide.
            assertEquals(4, buffer.readableBytes());
            ChoiceSelectC2S decoded = ChoiceSelectC2S.decode(buffer);
            assertEquals(ConversationRef.NONE, decoded.handle());
            assertNull(decoded.villagerId());
        } finally {
            buffer.release();
        }
    }
}
