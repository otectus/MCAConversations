package dev.otectus.mcaconversations.gossip;

import dev.otectus.mcaconversations.state.LastGift;
import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GossipNbtRoundTripTest {

    @Test
    void gossipEventRoundTripsWithAndWithoutSecondSubject() {
        GossipEvent married = new GossipEvent(UUID.randomUUID(), GossipEventType.MARRIAGE, 3, 12345L,
                UUID.randomUUID(), "Ann", Optional.of(UUID.randomUUID()), "Bob");
        assertEquals(married, GossipEvent.fromNbt(married.toNbt()).orElseThrow());

        GossipEvent death = new GossipEvent(UUID.randomUUID(), GossipEventType.DEATH, 7, 99L,
                UUID.randomUUID(), "Carl", Optional.empty(), "");
        assertEquals(death, GossipEvent.fromNbt(death.toNbt()).orElseThrow());

        // QUEST events (MCA: Quests integration) round-trip like any single-subject event.
        GossipEvent quest = new GossipEvent(UUID.randomUUID(), GossipEventType.QUEST, 4, 500L,
                UUID.randomUUID(), "Dana", Optional.empty(), "");
        assertEquals(quest, GossipEvent.fromNbt(quest.toNbt()).orElseThrow());
        assertEquals(GossipEventType.QUEST, GossipEventType.byJsonName("quest").orElseThrow());
    }

    @Test
    void malformedEventTagIsSkippedNotFatal() {
        CompoundTag bad = new CompoundTag();
        bad.putString("type", "not_a_type");
        assertTrue(GossipEvent.fromNbt(bad).isEmpty());
    }

    @Test
    void snapshotRoundTrips() {
        RelationshipSnapshot married = new RelationshipSnapshot(Optional.of(UUID.randomUUID()), "Ann", false, 42L);
        assertEquals(married, RelationshipSnapshot.fromNbt(married.toNbt()));

        RelationshipSnapshot single = new RelationshipSnapshot(Optional.empty(), "Bob", true, 7L);
        assertEquals(single, RelationshipSnapshot.fromNbt(single.toNbt()));
    }

    @Test
    void lastGiftRoundTrips() {
        LastGift gift = new LastGift("minecraft:poppy", 3, 1234567L);
        assertEquals(gift, LastGift.fromNbt(gift.toNbt()));
    }
}
