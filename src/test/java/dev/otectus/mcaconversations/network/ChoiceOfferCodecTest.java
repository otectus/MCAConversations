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

    /** Protocol 5: the offer names the line it belongs to, and "no claim" stays one byte. */
    @Test
    void roundTripsTheUtteranceItNames() {
        ConversationRef ref = new ConversationRef(UUID.randomUUID(), UUID.randomUUID());
        ChoiceOfferS2C named = new ChoiceOfferS2C(ref, 5L, ConversationSession.Frontend.GUI, "q",
                List.of("a", "b"), 17L);
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceOfferS2C.STREAM_CODEC.encode(buffer, named);
        ChoiceOfferS2C decoded = ChoiceOfferS2C.STREAM_CODEC.decode(buffer);
        assertEquals(named, decoded);
        assertEquals(17L, decoded.utteranceSeq());
        assertEquals(0, buffer.readableBytes());

        ChoiceOfferS2C unclaimed = new ChoiceOfferS2C(ref, 5L, ConversationSession.Frontend.GUI, "q",
                List.of("a", "b"));
        assertEquals(-1L, unclaimed.utteranceSeq());
        FriendlyByteBuf a = new FriendlyByteBuf(Unpooled.buffer());
        FriendlyByteBuf b = new FriendlyByteBuf(Unpooled.buffer());
        ChoiceOfferS2C.STREAM_CODEC.encode(a, unclaimed);
        ChoiceOfferS2C.STREAM_CODEC.encode(b, new ChoiceOfferS2C(ref, 5L, ConversationSession.Frontend.GUI, "q",
                List.of("a", "b"), 0L));
        assertEquals(a.readableBytes(), b.readableBytes(), "-1 and 0 both cost a single varint byte");
        assertEquals(unclaimed, ChoiceOfferS2C.STREAM_CODEC.decode(a));
    }

    @Test
    void rejectsAnUtteranceBelowMinusOne() {
        assertThrows(IllegalArgumentException.class, () -> new ChoiceOfferS2C(ConversationRef.NONE, 1L,
                ConversationSession.Frontend.GUI, "q", List.of("a"), -2L));
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ConversationRef.write(buffer, ConversationRef.NONE);
        buffer.writeVarLong(1L);
        buffer.writeEnum(ConversationSession.Frontend.GUI);
        buffer.writeUtf("q", ChoiceOfferS2C.MAX_ID_LENGTH);
        buffer.writeVarInt(1);
        buffer.writeUtf("a", ChoiceOfferS2C.MAX_ID_LENGTH);
        buffer.writeVarLong(-5L);
        assertThrows(DecoderException.class, () -> ChoiceOfferS2C.STREAM_CODEC.decode(buffer));
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
