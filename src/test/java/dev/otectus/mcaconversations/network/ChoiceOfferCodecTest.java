package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.FriendlyByteBuf;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceOfferCodecTest {

    @Test
    void roundTripsBoundedOffer() {
        ChoiceOfferS2C original = new ChoiceOfferS2C(
                new ConversationRef(UUID.randomUUID(), UUID.randomUUID()), 42L,
                ConversationSession.Frontend.CHAT, "conversations.question", List.of("first", "second"));
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceOfferS2C.STREAM_CODEC.encode(buffer, original);
        assertEquals(original, ChoiceOfferS2C.STREAM_CODEC.decode(buffer));
        assertEquals(0, buffer.readableBytes());
    }

    /** An offer for an ambient exchange names no discussion, and that has to survive the wire too. */
    @Test
    void roundTripsAnUnidentifiedOffer() {
        ChoiceOfferS2C original = new ChoiceOfferS2C(null, 1L, ConversationSession.Frontend.CHAT,
                "q", List.of("a"));
        assertEquals(ConversationRef.NONE, original.handle());
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceOfferS2C.STREAM_CODEC.encode(buffer, original);
        ChoiceOfferS2C decoded = ChoiceOfferS2C.STREAM_CODEC.decode(buffer);
        assertEquals(original, decoded);
        assertFalse(decoded.handle().identified());
    }

    /** A villager alone is what a frontend that never acquired a handle legitimately sends. */
    @Test
    void roundTripsAVillagerWithoutASession() {
        UUID villager = UUID.randomUUID();
        ChoiceOfferS2C original = new ChoiceOfferS2C(ConversationRef.ofVillager(villager), 2L,
                ConversationSession.Frontend.GUI, "q", List.of("a", "b"));
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceOfferS2C.STREAM_CODEC.encode(buffer, original);
        ChoiceOfferS2C decoded = ChoiceOfferS2C.STREAM_CODEC.decode(buffer);
        assertEquals(original, decoded);
        assertNull(decoded.handle().sessionId());
        assertEquals(villager, decoded.handle().villagerId());
    }

    @Test
    void rejectsOversizedCountsBeforeAllocating() {
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ConversationRef.write(buffer, ConversationRef.NONE);
        buffer.writeVarLong(1L);
        buffer.writeEnum(ConversationSession.Frontend.GUI);
        buffer.writeUtf("q", ChoiceOfferS2C.MAX_ID_LENGTH);
        buffer.writeVarInt(ChoiceOfferS2C.MAX_CHOICES + 1);
        assertThrows(DecoderException.class, () -> ChoiceOfferS2C.STREAM_CODEC.decode(buffer));

        List<String> tooMany = java.util.stream.IntStream
                .rangeClosed(0, ChoiceOfferS2C.MAX_CHOICES).mapToObj(Integer::toString).toList();
        assertThrows(IllegalArgumentException.class,
                () -> new ChoiceOfferS2C(ConversationRef.NONE, 1L,
                        ConversationSession.Frontend.GUI, "q", tooMany));
    }
}
