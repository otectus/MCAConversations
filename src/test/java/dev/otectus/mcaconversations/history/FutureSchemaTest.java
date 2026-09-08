package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What happens when this build opens a world that a later build wrote (spec §22.1, finding F07).
 *
 * <p>The failure this guards against is quiet and total: read a version-3 file with a version-1
 * reader, keep playing, and the first autosave writes back only the fields version 1 knows about —
 * every later addition deleted, with no error and nothing to recover from. Rolling a mod back is an
 * ordinary thing for a player to do, and it must cost them a session's memories at worst, never their
 * villagers.
 *
 * <p>So the contract has two halves, and both are tested here: everything recognisable is still
 * <em>read</em>, so villagers keep talking; and nothing at all is <em>written</em>, so the file on
 * disk survives the session byte for byte.
 */
class FutureSchemaTest {

    private static final UUID VILLAGER = UUID.fromString("00000000-0000-4000-8000-0000000000a1");
    private static final UUID PLAYER = UUID.fromString("00000000-0000-4000-8000-0000000000b1");

    @Test
    void aNewerSchemaLoadsDegradedAndStillServesWhatItRecognises() {
        ConversationHistoryStore loaded = ConversationHistoryStore.load(futureTag());

        assertTrue(loaded.isDegraded(), "a version-3 file did not put the store into degraded mode");
        assertEquals(3, loaded.loadedVersion());
        assertEquals(1, loaded.villagerCount(), "the villagers this build understands were not read");
        VillagerHistory history = loaded.peek(VILLAGER).orElseThrow();
        assertEquals(1, history.opinions().size(), "a recognised record was dropped with the unknown ones");
        assertTrue(history.peekPair(PLAYER).isPresent(), "a recognised pair was dropped");
    }

    @Test
    void aCurrentSchemaIsNotDegraded() {
        ConversationHistoryStore store = new ConversationHistoryStore();
        store.getOrCreate(VILLAGER).putOpinion(opinion());
        ConversationHistoryStore reloaded = ConversationHistoryStore.load(store.save(new CompoundTag()));

        assertFalse(reloaded.isDegraded(), "an ordinary world was treated as a rollback");
        assertEquals(ConversationHistoryStore.CURRENT_VERSION, reloaded.loadedVersion());
    }

    @Test
    void aDegradedStoreReEmitsTheOriginalTagByteForByte() {
        CompoundTag original = futureTag();
        ConversationHistoryStore loaded = ConversationHistoryStore.load(original);

        // An ordinary mutation first: the store still accepts one, because refusing would break the
        // conversation. What must not happen is that mutation reaching the file.
        loaded.getOrCreate(VILLAGER).pair(PLAYER).touch(500);
        loaded.getOrCreate(UUID.randomUUID()).putOpinion(opinion());

        assertArrayEquals(bytes(original), bytes(loaded.save(new CompoundTag())),
                "a degraded save rewrote the file instead of re-emitting it unchanged");
    }

    @Test
    void unknownKeysAndUnknownRecordVariantsSurviveInTheReEmittedTag() {
        CompoundTag original = futureTag();
        CompoundTag written = ConversationHistoryStore.load(original).save(new CompoundTag());

        assertTrue(written.contains("dreams"), "an unknown top-level key was dropped");
        assertEquals(3, written.getInt("version"), "the newer version number was overwritten");
        CompoundTag row = written.getList("villagers", net.minecraft.nbt.Tag.TAG_COMPOUND).getCompound(0);
        assertTrue(row.contains("grudges"), "an unknown nested key was dropped");
        assertEquals("a_state_this_build_has_never_heard_of",
                row.getList("episodes", net.minecraft.nbt.Tag.TAG_COMPOUND).getCompound(0).getString("state"),
                "an unknown record variant was rewritten");
    }

    /**
     * A version-3 file: unknown top-level key, unknown nested key, an episode in a state this build
     * has no name for, and one opinion and one pair it does understand.
     */
    private static CompoundTag futureTag() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("version", 3);
        tag.putString("dreams", "a whole record family that does not exist yet");

        CompoundTag row = new CompoundTag();
        row.putUUID("uuid", VILLAGER);
        row.putString("grudges", "nested, and equally unknown");

        ListTag episodes = new ListTag();
        CompoundTag episode = new CompoundTag();
        episode.putUUID("id", new UUID(4L, 4L));
        episode.putString("kind", "work.damaged_volume");
        episode.putString("state", "a_state_this_build_has_never_heard_of");
        episodes.add(episode);
        row.put("episodes", episodes);

        ListTag opinions = new ListTag();
        opinions.add(opinion().save());
        row.put("opinions", opinions);

        ListTag pairs = new ListTag();
        CompoundTag pair = new CompoundTag();
        pair.putUUID("player", PLAYER);
        pair.putLong("first_met", 3);
        pair.putLong("last_talked", 9);
        pairs.add(pair);
        row.put("pairs", pairs);

        ListTag villagers = new ListTag();
        villagers.add(row);
        tag.put("villagers", villagers);
        return tag;
    }

    private static SocialOpinionRecord opinion() {
        return new SocialOpinionRecord(new UUID(5L, 5L), "reliability", -2, "episode.harvest_help.late",
                Confidence.WITNESSED, PrivacyLevel.DISCREET, 8, java.util.OptionalLong.empty());
    }

    private static byte[] bytes(CompoundTag tag) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try (DataOutputStream stream = new DataOutputStream(out)) {
                NbtIo.write(tag, stream);
            }
            return out.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
