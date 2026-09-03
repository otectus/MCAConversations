package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.FriendlyByteBuf;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceOfferCodecTest {

    @Test
    void roundTripsBoundedOffer() {
        ChoiceOfferS2C original = new ChoiceOfferS2C(42L, ConversationSession.Frontend.CHAT,
                "conversations.question", List.of("first", "second"));
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceOfferS2C.STREAM_CODEC.encode(buffer, original);
        assertEquals(original, ChoiceOfferS2C.STREAM_CODEC.decode(buffer));
    }

    @Test
    void rejectsOversizedCountsBeforeAllocating() {
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        buffer.writeVarLong(1L);
        buffer.writeEnum(ConversationSession.Frontend.GUI);
        buffer.writeUtf("q", ChoiceOfferS2C.MAX_ID_LENGTH);
        buffer.writeVarInt(ChoiceOfferS2C.MAX_CHOICES + 1);
        assertThrows(DecoderException.class, () -> ChoiceOfferS2C.STREAM_CODEC.decode(buffer));

        List<String> tooMany = java.util.stream.IntStream
                .rangeClosed(0, ChoiceOfferS2C.MAX_CHOICES).mapToObj(Integer::toString).toList();
        assertThrows(IllegalArgumentException.class,
                () -> new ChoiceOfferS2C(1L, ConversationSession.Frontend.GUI, "q", tooMany));
    }
}
