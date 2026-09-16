package dev.otectus.mcaconversations.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChoiceReturnCodecTest {
    @Test
    void returnRoundTripsTheRefusedRevisionAndSpeaker() {
        var message = new ChoiceReturnC2S(
                new ConversationRef(UUID.randomUUID(), UUID.randomUUID()), Long.MAX_VALUE);
        var buffer = new FriendlyByteBuf(Unpooled.buffer());
        try {
            ChoiceReturnC2S.encode(message, buffer);
            assertEquals(message, ChoiceReturnC2S.decode(buffer));
            assertEquals(0, buffer.readableBytes());
        } finally {
            buffer.release();
        }
    }
}
