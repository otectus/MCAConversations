package dev.otectus.mcaconversations.court;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The court memory has to survive a restart, because everything it is for is a comparison with a
 * previous session: a chronicle cursor lost is a court's whole history re-announced as news, and a
 * forgotten title is a promotion remarked on twice.
 *
 * <p>Written against {@link CourtRoleMemory}'s own NBT rather than through
 * {@link CourtMemorySavedData}, which needs a live server; the SavedData is only the {@code setDirty}
 * wrapper around exactly these two methods.
 */
class CourtMemorySavedDataNbtTest {

    @Test
    void everythingRemembersItselfAcrossASave() {
        UUID capital = UUID.randomUUID();
        UUID sovereign = UUID.randomUUID();
        UUID heir = UUID.randomUUID();
        UUID rival = UUID.randomUUID();
        UUID villager = UUID.randomUUID();

        CourtRoleMemory memory = new CourtRoleMemory();
        memory.setChronicleSeen(capital, 41);
        Map<UUID, String> relations = new LinkedHashMap<>();
        relations.put(rival, "war");
        memory.setSnapshot(capital, new CourtRoleMemory.CapitalSnapshot(
                Optional.of(sovereign), Optional.of(heir), "active", true, relations));
        memory.observe(villager, "knight", 10L);
        memory.observe(villager, "hand", 12L);

        CourtRoleMemory loaded = CourtRoleMemory.load(memory.save(new CompoundTag()));

        assertEquals(41, loaded.chronicleSeen(capital));
        assertEquals(memory.snapshot(capital), loaded.snapshot(capital));
        assertEquals(Optional.of("hand"), loaded.title(villager));
        assertEquals(Optional.of("knight"), loaded.freshChange(villager, 12L, 7));
    }

    @Test
    void aRemarkedChangeStaysRemarked() {
        // Otherwise a restart is a way to hear about the same promotion again.
        UUID villager = UUID.randomUUID();
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(villager, "knight", 10L);
        memory.observe(villager, "hand", 12L);
        memory.markRemarked(villager);

        CourtRoleMemory loaded = CourtRoleMemory.load(memory.save(new CompoundTag()));
        assertEquals(Optional.empty(), loaded.freshChange(villager, 12L, 7));
    }

    @Test
    void theSavedTagCarriesItsVersion() {
        CompoundTag tag = new CourtRoleMemory().save(new CompoundTag());
        assertEquals(CourtRoleMemory.CURRENT_VERSION, tag.getInt("version"));
        assertTrue(tag.contains("capitals"));
        assertTrue(tag.contains("villagers"));
    }

    @Test
    void anEmptyTagLoadsAsAnEmptyMemory() {
        assertTrue(CourtRoleMemory.load(new CompoundTag()).isEmpty());
        assertTrue(CourtRoleMemory.load(null).isEmpty());
    }

    @Test
    void aCorruptEntryIsSkippedRatherThanFatal() {
        UUID villager = UUID.randomUUID();
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(villager, "lord", 5L);
        CompoundTag tag = memory.save(new CompoundTag());

        CompoundTag broken = new CompoundTag();
        broken.putString("uuid", "not-a-uuid");
        broken.putString("titleId", "hand");
        broken.putString("previousTitleId", "");
        broken.putLong("changedDay", 0L);
        broken.putBoolean("remarked", false);
        ListTag villagers = tag.getList("villagers", Tag.TAG_COMPOUND);
        villagers.add(broken);

        CourtRoleMemory loaded = CourtRoleMemory.load(tag);
        assertEquals(Optional.of("lord"), loaded.title(villager));
    }
}
