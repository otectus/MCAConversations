package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.gossip.GossipEvent;
import dev.otectus.mcaconversations.gossip.GossipEventType;
import dev.otectus.mcaconversations.gossip.GossipSavedData;
import dev.otectus.mcaconversations.gossip.RelationshipSnapshot;
import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The three world-global data files: their names, and that the gossip payload survives the move to
 * the 1.21.1 {@code SavedData.Factory} API.
 *
 * <p>The name check matters more than it looks. {@code DataStorage.computeIfAbsent} keys the file
 * purely on that string, so a rename does not fail — it silently starts a brand new, empty file and
 * leaves the old one on disk untouched. Every disposition, every gossip event and all conversation
 * progress in an existing world would appear to have been wiped, with nothing in the log.
 */
class SavedDataContractTest {

    private static final UUID ANNA = UUID.fromString("11111111-2222-3333-4444-555555555555");
    private static final UUID BRAM = UUID.fromString("66666666-7777-8888-9999-aaaaaaaaaaaa");
    private static final UUID EVENT_ID = UUID.fromString("0f0e0d0c-0b0a-0908-0706-050403020100");

    @Test
    void everyDataFileKeepsItsOriginalName() throws Exception {
        assertEquals("mcaconversations_dispositions",
                dataName(dev.otectus.mcaconversations.disposition.DispositionSavedData.class));
        assertEquals("mcaconversations_gossip",
                dataName(GossipSavedData.class));
        assertEquals("mcaconversations_progress",
                dataName(dev.otectus.mcaconversations.progress.ProgressSavedData.class));
    }

    @Test
    void gossipSurvivesASaveLoadCycleThroughTheNewApi() {
        GossipSavedData data = GossipSavedData.load(new CompoundTag());

        data.addEvent(new GossipEvent(EVENT_ID, GossipEventType.MARRIAGE, 7, 1234L,
                ANNA, "Anna", Optional.of(BRAM), "Bram"), 64);
        data.putSnapshot(ANNA, new RelationshipSnapshot(Optional.of(BRAM), "Anna", false, 1234L));
        data.putResidency(7, Set.of(ANNA, BRAM));

        // provider is null on purpose: this payload holds no registry-bound data, which is why the
        // HolderLookup.Provider the 1.21.1 signature adds is unused on both sides.
        CompoundTag tag = data.save(new CompoundTag(), null);
        GossipSavedData reloaded = GossipSavedData.load(tag);

        List<GossipEvent> events = List.copyOf(reloaded.log().events());
        assertEquals(1, events.size());
        assertEquals(GossipEventType.MARRIAGE, events.get(0).type());
        assertEquals(ANNA, events.get(0).aUuid());
        assertEquals(Optional.of(BRAM), events.get(0).bUuid());
        assertEquals(1234L, events.get(0).created());

        Map<UUID, RelationshipSnapshot> snapshots = reloaded.snapshots();
        assertTrue(snapshots.containsKey(ANNA));
        assertEquals("Anna", snapshots.get(ANNA).name());

        assertEquals(Set.of(ANNA, BRAM), reloaded.getResidency(7));
    }

    @Test
    void anEmptyFileLoadsAsAnEmptyStoreRatherThanFailing() {
        // The first launch after an upgrade hands us whatever is on disk, including nothing.
        GossipSavedData fresh = GossipSavedData.load(new CompoundTag());
        assertTrue(fresh.log().events().isEmpty());
        assertTrue(fresh.snapshots().isEmpty());
    }

    private static String dataName(Class<?> type) throws Exception {
        Field field = type.getDeclaredField("DATA_NAME");
        field.setAccessible(true);
        return (String) field.get(null);
    }
}
