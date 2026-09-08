package dev.otectus.mcaconversations.chat;

import net.minecraft.world.phys.Vec3;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The snapshot is taken on the chat event thread and read on the server thread a tick or more later.
 * By then the player has moved and the event is over, so the record has to be a copy by value —
 * nothing it holds may still be a live view of the sender.
 *
 * <p>The dimension is carried as {@code null} here: a real {@code ResourceKey<Level>} is a registry
 * key and cannot be constructed outside a bootstrapped game. It is also the one field with nothing
 * to copy — registry keys are immutable and interned — so it is not what these tests are about.
 */
class AcceptedChatSnapshotTest {

    /** Stands in for the sender: the mutable state the snapshot reads off a live player. */
    private static final class MutableSpeaker {
        String name = "Steve";
        Vec3 position = new Vec3(1.0D, 64.0D, 2.0D);
    }

    @Test
    void snapshotDoesNotFollowTheSpeaker() {
        UUID id = UUID.randomUUID();
        MutableSpeaker speaker = new MutableSpeaker();
        StringBuilder typed = new StringBuilder("hello Agnes");

        AcceptedChat accepted = new AcceptedChat(id, speaker.name, typed.toString(),
                null, speaker.position, 100L);

        speaker.name = "Alex";
        speaker.position = new Vec3(900.0D, 12.0D, -40.0D);
        typed.append(" and everyone else");

        assertEquals(id, accepted.playerId());
        assertEquals("Steve", accepted.playerName());
        assertEquals("hello Agnes", accepted.text());
        assertEquals(new Vec3(1.0D, 64.0D, 2.0D), accepted.position());
        assertEquals(100L, accepted.gameTime());
        assertNotEquals(speaker.position, accepted.position());
    }

    @Test
    void twoSnapshotsOfTheSameMessageAreEqual() {
        UUID id = UUID.randomUUID();
        AcceptedChat a = new AcceptedChat(id, "Steve", "hi", null, new Vec3(1, 2, 3), 7L);
        AcceptedChat b = new AcceptedChat(id, "Steve", "hi", null, new Vec3(1, 2, 3), 7L);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void aLaterMessageIsADifferentSnapshot() {
        UUID id = UUID.randomUUID();
        AcceptedChat first = new AcceptedChat(id, "Steve", "hi", null, new Vec3(1, 2, 3), 7L);
        AcceptedChat second = new AcceptedChat(id, "Steve", "hi", null, new Vec3(1, 2, 3), 8L);
        assertNotEquals(first, second);
    }
}
