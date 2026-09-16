package dev.otectus.mcaconversations.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The clear packet carries the explanation a player is shown, so its identifiers are part of the
 * protocol rather than an accident of declaration order.
 */
class ChoiceClearCodecTest {

    @Test
    void everyReasonRoundTrips() {
        for (ChoiceClearS2C.Reason reason : ChoiceClearS2C.Reason.values()) {
            FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
            ConversationRef handle = new ConversationRef(UUID.randomUUID(), UUID.randomUUID());
            ChoiceClearS2C.encode(new ChoiceClearS2C(handle, 7L, reason), buffer);
            ChoiceClearS2C decoded = ChoiceClearS2C.decode(buffer);
            assertEquals(reason, decoded.reason());
            assertEquals(7L, decoded.revision());
            assertEquals(handle, decoded.handle());
            assertEquals(0, buffer.readableBytes());
        }
    }

    @Test
    void identifiersAreUniqueAndIndependentOfOrdinals() {
        Set<Integer> ids = new HashSet<>();
        for (ChoiceClearS2C.Reason reason : ChoiceClearS2C.Reason.values()) {
            assertTrue(ids.add(reason.id()), "duplicate identifier on " + reason);
            assertEquals(reason, ChoiceClearS2C.Reason.byId(reason.id()));
        }
    }

    @Test
    void anUnknownIdentifierClearsSilentlyRatherThanThrowing() {
        // A build that learns a new explanation must not crash the connection of one that has not.
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        ConversationRef.write(buffer, ConversationRef.NONE);
        buffer.writeVarLong(3L);
        buffer.writeVarInt(9999);
        ChoiceClearS2C decoded = ChoiceClearS2C.decode(buffer);
        assertEquals(ChoiceClearS2C.Reason.NONE, decoded.reason());
        assertFalse(decoded.reason().explained());
    }

    @Test
    void onlyReasonsWorthExplainingClaimAnExplanation() {
        assertFalse(ChoiceClearS2C.Reason.NONE.explained());
        assertFalse(ChoiceClearS2C.Reason.CONSUMED.explained());
        assertFalse(ChoiceClearS2C.Reason.SUPERSEDED.explained());
        assertTrue(ChoiceClearS2C.Reason.EXECUTION_FAILED.explained());
        assertTrue(ChoiceClearS2C.Reason.CONTENT_RELOADED.explained());
    }
}
