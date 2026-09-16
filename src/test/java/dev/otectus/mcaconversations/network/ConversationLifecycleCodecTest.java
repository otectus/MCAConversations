package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Round trips for the four lifecycle packets protocol 4 adds, and for the two decisions they must
 * never get wrong: an ending this build has never heard of must decode safely, and no packet may
 * carry a player identity for the server to trust.
 */
class ConversationLifecycleCodecTest {

    private static FriendlyByteBuf buffer() {
        return new FriendlyByteBuf(Unpooled.buffer());
    }

    @Test
    void openedRoundTripsTheHandleAndFrontend() {
        ConversationOpenedS2C message = new ConversationOpenedS2C(
                new ConversationRef(UUID.randomUUID(), UUID.randomUUID()),
                ConversationSession.Frontend.CHAT);
        FriendlyByteBuf buffer = buffer();
        ConversationOpenedS2C.encode(message, buffer);
        assertEquals(message, ConversationOpenedS2C.decode(buffer));
        assertEquals(0, buffer.readableBytes());
    }

    @Test
    void closedRoundTripsEveryReason() {
        for (CloseReason reason : CloseReason.values()) {
            ConversationClosedS2C message = new ConversationClosedS2C(
                    new ConversationRef(UUID.randomUUID(), UUID.randomUUID()), reason);
            FriendlyByteBuf buffer = buffer();
            ConversationClosedS2C.encode(message, buffer);
            ConversationClosedS2C decoded = ConversationClosedS2C.decode(buffer);
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
        assertEquals(CloseReason.COMPLETED, ConversationClosedS2C.decode(buffer).reason());
    }

    @Test
    void presenceCarriesTheHandleAndNothingElse() {
        ConversationRef handle = new ConversationRef(UUID.randomUUID(), UUID.randomUUID());
        FriendlyByteBuf buffer = buffer();
        ConversationPresenceC2S.encode(new ConversationPresenceC2S(handle), buffer);
        // Two presence flags plus two UUIDs. Anything more would be something the server must not trust.
        assertEquals(2 + 32, buffer.readableBytes());
        assertEquals(handle, ConversationPresenceC2S.decode(buffer).handle());
    }

    @Test
    void closeRequestCarriesTheHandleAndNoReason() {
        ConversationRef handle = new ConversationRef(UUID.randomUUID(), UUID.randomUUID());
        FriendlyByteBuf buffer = buffer();
        ConversationCloseC2S.encode(new ConversationCloseC2S(handle), buffer);
        assertEquals(2 + 32, buffer.readableBytes());
        assertEquals(handle, ConversationCloseC2S.decode(buffer).handle());
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
