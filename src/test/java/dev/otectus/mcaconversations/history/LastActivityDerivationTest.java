package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.junit.jupiter.api.Test;

import java.util.OptionalLong;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The optional {@code last_activity} key: written when known, derived when absent (finding F09).
 *
 * <p>Eviction needs one comparable number per villager, and adding one to the file is only safe if it
 * is optional in both directions. A world written by the build before this one has no such key and
 * must still sort correctly, and a world written by this build must still open under a build that
 * ignores the key — which is why it is an addition rather than a schema change (the version stays 1).
 */
class LastActivityDerivationTest {

    private static final UUID FIRST = UUID.fromString("00000000-0000-4000-8000-0000000000d2");
    private static final UUID SECOND = UUID.fromString("00000000-0000-4000-8000-0000000000d3");

    @Test
    void anAbsentKeyIsDerivedFromTheLatestPair() {
        CompoundTag row = new CompoundTag();
        ListTag pairs = new ListTag();
        pairs.add(pairTag(FIRST, 12));
        pairs.add(pairTag(SECOND, 40));
        row.put("pairs", pairs);
        assertFalse(row.contains("last_activity"));

        VillagerHistory loaded = VillagerHistory.load(row);

        assertEquals(OptionalLong.of(40), loaded.lastActivityDay(),
                "a save from before the key existed did not derive an activity day from its pairs");
    }

    @Test
    void aVillagerWhoHasNeverSpokenHasNoActivityDay() {
        assertEquals(OptionalLong.empty(), new VillagerHistory().lastActivityDay());
        assertEquals(OptionalLong.empty(), VillagerHistory.load(new CompoundTag()).lastActivityDay());
    }

    @Test
    void theKeyIsWrittenAndReadBack() {
        VillagerHistory history = new VillagerHistory();
        history.pair(FIRST).touch(31);

        CompoundTag saved = history.save();
        assertTrue(saved.contains("last_activity"), "a known activity day was not written");
        assertEquals(31, saved.getLong("last_activity"));
        assertEquals(OptionalLong.of(31), VillagerHistory.load(saved).lastActivityDay());
    }

    @Test
    void aTagWithTheKeyStrippedStillLoads() {
        VillagerHistory history = new VillagerHistory();
        history.pair(FIRST).touch(12);
        history.pair(SECOND).touch(40);
        CompoundTag saved = history.save();

        // Exactly what a build that does not know the key would hand back.
        saved.remove("last_activity");
        VillagerHistory reloaded = VillagerHistory.load(saved);

        assertEquals(OptionalLong.of(40), reloaded.lastActivityDay(),
                "stripping the optional key cost the villager their place in the eviction order");
        assertEquals(2, reloaded.pairs().size(), "stripping the optional key cost a pair record");
    }

    @Test
    void aConversationAfterLoadingMovesTheDayForward() {
        VillagerHistory history = VillagerHistory.load(rowWithPair(40));
        assertEquals(OptionalLong.of(40), history.lastActivityDay());

        history.peekPair(FIRST).orElseThrow()
                .recordPlayed("scene.one", "subject.one", "problem_solve", "work", 55);

        assertEquals(OptionalLong.of(55), history.lastActivityDay(),
                "a pair read back from disk stopped reporting its conversations to the villager");
    }

    @Test
    void aStoredDayLaterThanAnyPairIsKept() {
        // The store and the pairs can honestly disagree: a pair may have been pruned away since.
        CompoundTag row = rowWithPair(12);
        row.putLong("last_activity", 300);

        assertEquals(OptionalLong.of(300), VillagerHistory.load(row).lastActivityDay());
    }

    @Test
    void theKeyIsAbsentFromAStoreThatHasSaidNothing() {
        CompoundTag saved = new VillagerHistory().save();
        assertFalse(saved.contains("last_activity"),
                "an unwritten day would make two identical saves differ");
        assertEquals(0, saved.getList("pairs", Tag.TAG_COMPOUND).size());
    }

    private static CompoundTag rowWithPair(long day) {
        CompoundTag row = new CompoundTag();
        ListTag pairs = new ListTag();
        pairs.add(pairTag(FIRST, day));
        row.put("pairs", pairs);
        return row;
    }

    private static CompoundTag pairTag(UUID player, long day) {
        CompoundTag pair = new CompoundTag();
        pair.putUUID("player", player);
        pair.putLong("first_met", 1);
        pair.putLong("last_talked", day);
        return pair;
    }
}
