package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Round trips for the four lifecycle payloads protocol 4 adds, their stable type ids, and the two
 * decisions they must never get wrong: an ending this build has never heard of must decode safely,
 * and no payload may carry a player identity for the server to trust.
 */
class ConversationLifecycleCodecTest {

    private static FriendlyByteBuf buffer() {
        return new FriendlyByteBuf(Unpooled.buffer());
    }

    @Test
    void theTypeIdsAreStable() {
        assertEquals(ResourceLocation.fromNamespaceAndPath("mcaconversations", "conversation_opened"),
                ConversationOpenedS2C.TYPE.id());
        assertEquals(ResourceLocation.fromNamespaceAndPath("mcaconversations", "conversation_closed"),
                ConversationClosedS2C.TYPE.id());
        assertEquals(ResourceLocation.fromNamespaceAndPath("mcaconversations", "conversation_presence"),
                ConversationPresenceC2S.TYPE.id());
        assertEquals(ResourceLocation.fromNamespaceAndPath("mcaconversations", "conversation_close"),
                ConversationCloseC2S.TYPE.id());
    }

    @Test
    void openedRoundTripsTheHandleAndFrontend() {
        ConversationOpenedS2C message = new ConversationOpenedS2C(
                new ConversationRef(UUID.randomUUID(), UUID.randomUUID()),
                ConversationSession.Frontend.CHAT);
        FriendlyByteBuf buffer = buffer();
        ConversationOpenedS2C.STREAM_CODEC.encode(buffer, message);
        assertEquals(message, ConversationOpenedS2C.STREAM_CODEC.decode(buffer));
        assertEquals(0, buffer.readableBytes());
    }

    @Test
    void closedRoundTripsEveryReason() {
        for (CloseReason reason : CloseReason.values()) {
            ConversationClosedS2C message = new ConversationClosedS2C(
                    new ConversationRef(UUID.randomUUID(), UUID.randomUUID()), reason);
            FriendlyByteBuf buffer = buffer();
            ConversationClosedS2C.STREAM_CODEC.encode(buffer, message);
            ConversationClosedS2C decoded = ConversationClosedS2C.STREAM_CODEC.decode(buffer);
            assertEquals(reason, decoded.reason(), "wire id " + reason.wireId());
            assertEquals(message.handle(), decoded.handle());
            assertEquals(0, buffer.readableBytes());
        }
    }

    @Test
    void anUnknownEndingDecodesAsCompletedRatherThanThrowing() {
        // A server that learns a new ending must not disconnect a client that has not.
        FriendlyByteBuf buffer = buffer();
        ConversationRef.write(buffer, ConversationRef.NONE);
        buffer.writeVarInt(4242);
        assertEquals(CloseReason.COMPLETED, ConversationClosedS2C.STREAM_CODEC.decode(buffer).reason());
    }

    @Test
    void presenceCarriesTheHandleAndNothingElse() {
        ConversationRef handle = new ConversationRef(UUID.randomUUID(), UUID.randomUUID());
        FriendlyByteBuf buffer = buffer();
        ConversationPresenceC2S.STREAM_CODEC.encode(buffer, new ConversationPresenceC2S(handle));
        // Two presence flags plus two UUIDs. Anything more would be something the server must not trust.
        assertEquals(2 + 32, buffer.readableBytes());
        assertEquals(handle, ConversationPresenceC2S.STREAM_CODEC.decode(buffer).handle());
    }

    @Test
    void closeRequestCarriesTheHandleAndNoReason() {
        ConversationRef handle = new ConversationRef(UUID.randomUUID(), UUID.randomUUID());
        FriendlyByteBuf buffer = buffer();
        ConversationCloseC2S.STREAM_CODEC.encode(buffer, new ConversationCloseC2S(handle));
        assertEquals(2 + 32, buffer.readableBytes());
        assertEquals(handle, ConversationCloseC2S.STREAM_CODEC.decode(buffer).handle());
        assertEquals(0, buffer.readableBytes());
    }

    @Test
    void anUnnamedRefIsTwoBytesAndStaysUnidentified() {
        FriendlyByteBuf buffer = buffer();
        ConversationRef.write(buffer, null);
        assertEquals(2, buffer.readableBytes());
        ConversationRef decoded = ConversationRef.read(buffer);
        assertEquals(ConversationRef.NONE, decoded);
        assertFalse(decoded.identified());
    }

    @Test
    void everyCloseReasonHasItsOwnPermanentWireId() {
        java.util.Set<Integer> ids = new java.util.HashSet<>();
        for (CloseReason reason : CloseReason.values()) {
            assertTrue(ids.add(reason.wireId()), "duplicate wire id on " + reason);
            assertEquals(reason, CloseReason.byWireId(reason.wireId()).orElseThrow());
        }
        assertTrue(CloseReason.byWireId(-1).isEmpty());
    }
}
