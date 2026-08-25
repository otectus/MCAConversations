package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.chat.ChatModePlayerState;
import dev.otectus.mcaconversations.state.LastGift;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The one-time import of 1.20.1 Forge capability data into the NeoForge attachments.
 *
 * <p>Every case runs against a committed, gzip-compressed player {@code .dat} rather than a tag
 * built in memory. That is the whole point: a change to {@code GiftMemoryData.save} would keep an
 * in-memory round-trip passing while breaking every real world being upgraded, and only frozen
 * bytes catch it. See {@link ForgeCapsFixtures} for where the shape comes from.
 */
class ForgeCapsMigrationTest {

    @BeforeAll
    static void regenerateIfAsked() throws IOException {
        if (Boolean.getBoolean("mcaconversations.regenerateFixtures")) {
            ForgeCapsFixtures.writeAll();
        }
    }

    // --- The happy paths ------------------------------------------------------

    @Test
    void giftMemoryOnlyMigratesEveryRememberedVillager() throws IOException {
        FakeHolder holder = new FakeHolder();
        ForgeCapsMigration.applyTo(holder, ForgeCapsFixtures.read("forge-player-gift-only.dat"), "tester");

        GiftMemoryData memory = holder.require(ConversationsAttachments.GIFT_MEMORY.get());
        assertEquals(2, memory.size());
        assertLastGift(memory, ForgeCapsFixtures.ANNA, ForgeCapsFixtures.ANNAS_GIFT);
        assertLastGift(memory, ForgeCapsFixtures.BRAM, ForgeCapsFixtures.BRAMS_GIFT);

        assertFalse(holder.hasData(ConversationsAttachments.CHAT_MODE.get()),
                "a file with no chat-mode capability must not invent one");
    }

    @Test
    void chatModeOnlyKeepsTheExplicitOptOut() throws IOException {
        FakeHolder holder = new FakeHolder();
        ForgeCapsMigration.applyTo(holder, ForgeCapsFixtures.read("forge-player-chat-only.dat"), "tester");

        ChatModePlayerState state = holder.require(ConversationsAttachments.CHAT_MODE.get());
        assertTrue(state.isExplicit(), "the choice must stay explicit, or the config default takes over");
        assertFalse(state.storedChoice(), "the player opted out; migrating them back in is the bug");

        assertFalse(holder.hasData(ConversationsAttachments.GIFT_MEMORY.get()));
    }

    @Test
    void bothMigrateTogetherAndForeignCapabilitiesAreIgnored() throws IOException {
        FakeHolder holder = new FakeHolder();
        ForgeCapsMigration.applyTo(holder, ForgeCapsFixtures.read("forge-player-both.dat"), "tester");

        assertEquals(2, holder.require(ConversationsAttachments.GIFT_MEMORY.get()).size());
        assertTrue(holder.require(ConversationsAttachments.CHAT_MODE.get()).isExplicit());
        // The fixture also carries someothermod:state. Two attachments in, two attachments out.
        assertEquals(2, holder.stored.size(), "migrated another mod's capability");
    }

    // --- The paths that must not do anything ---------------------------------

    @Test
    void aFileWithNoForgeCapsIsLeftCompletelyAlone() throws IOException {
        FakeHolder holder = new FakeHolder();
        ForgeCapsMigration.applyTo(holder, ForgeCapsFixtures.read("fabric-player-no-forgecaps.dat"), "tester");
        assertTrue(holder.stored.isEmpty(), "nothing to migrate, so nothing may be written");
    }

    @Test
    void malformedEntriesAreSkippedWithoutThrowingOrBlockingTheOther() throws IOException {
        FakeHolder holder = new FakeHolder();
        // Gift memory is a string, chat mode is a compound of the wrong keys, and a third mod's
        // capability sits alongside. None of that may propagate out of the migration.
        ForgeCapsMigration.applyTo(holder, ForgeCapsFixtures.read("forge-player-malformed.dat"), "tester");

        assertFalse(holder.hasData(ConversationsAttachments.GIFT_MEMORY.get()),
                "a non-compound value is not readable and must be skipped, not guessed at");
        // The chat-mode compound IS a compound, just full of foreign keys. CompoundTag.getBoolean
        // returns false for a missing key, so it reads back as a player who made no explicit
        // choice — which is exactly the safe default, and is why this must not throw.
        ChatModePlayerState state = holder.require(ConversationsAttachments.CHAT_MODE.get());
        assertFalse(state.isExplicit());
    }

    @Test
    void existingAttachmentDataAlwaysWinsOverForgeCaps() throws IOException {
        FakeHolder holder = new FakeHolder();
        // A player who has already played on NeoForge: the attachment is live and the lingering
        // ForgeCaps blob is a fossil. Importing it would roll their memory back to the Forge era.
        GiftMemoryData live = new GiftMemoryData();
        live.recordGift(ForgeCapsFixtures.ANNA, new LastGift("minecraft:diamond", 1, 999_999L), 16);
        holder.setData(ConversationsAttachments.GIFT_MEMORY.get(), live);

        ForgeCapsMigration.applyTo(holder, ForgeCapsFixtures.read("forge-player-both.dat"), "tester");

        assertLastGift(holder.require(ConversationsAttachments.GIFT_MEMORY.get()),
                ForgeCapsFixtures.ANNA, new LastGift("minecraft:diamond", 1, 999_999L));
        // The untouched half still migrates: precedence is per attachment, not all-or-nothing.
        assertTrue(holder.require(ConversationsAttachments.CHAT_MODE.get()).isExplicit());
    }

    @Test
    void runningTwiceIsIdempotent() throws IOException {
        FakeHolder holder = new FakeHolder();
        CompoundTag file = ForgeCapsFixtures.read("forge-player-both.dat");

        ForgeCapsMigration.applyTo(holder, file, "tester");
        GiftMemoryData first = holder.require(ConversationsAttachments.GIFT_MEMORY.get());

        // No flag is persisted, so the only thing stopping a second import is the hasData check.
        // In the one window where this can happen for real — migrate, then crash before saving —
        // it must land on the same result.
        ForgeCapsMigration.applyTo(holder, file, "tester");
        assertEquals(first, holder.require(ConversationsAttachments.GIFT_MEMORY.get()),
                "a second pass must not replace the instance it already installed");
        assertEquals(2, holder.stored.size());
    }

    // --- Helpers --------------------------------------------------------------

    private static void assertLastGift(GiftMemoryData memory, java.util.UUID villager, LastGift expected) {
        Optional<LastGift> actual = memory.lastGiftTo(villager);
        assertTrue(actual.isPresent(), "no gift remembered for " + villager);
        assertEquals(expected.itemId(), actual.get().itemId());
        assertEquals(expected.count(), actual.get().count());
        assertEquals(expected.gameTime(), actual.get().gameTime());
    }

    /**
     * A minimal {@link IAttachmentHolder} over a map.
     *
     * <p>The migration only ever asks whether an attachment is present and sets one, so a real
     * entity is unnecessary — and using one would drag a live level into a test whose subject is a
     * pure NBT read. {@code getData} deliberately does not create on read: this double exists to
     * make "was it set?" observable, which the real create-on-read accessor cannot.
     */
    private static final class FakeHolder implements IAttachmentHolder {

        private final Map<AttachmentType<?>, Object> stored = new HashMap<>();

        @SuppressWarnings("unchecked")
        <T> T require(AttachmentType<T> type) {
            assertTrue(stored.containsKey(type), "attachment was never set");
            return (T) stored.get(type);
        }

        @Override
        public boolean hasAttachments() {
            return !stored.isEmpty();
        }

        @Override
        public boolean hasData(AttachmentType<?> type) {
            return stored.containsKey(type);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T getData(AttachmentType<T> type) {
            return (T) stored.get(type);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T setData(AttachmentType<T> type, T data) {
            return (T) stored.put(type, data);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T removeData(AttachmentType<T> type) {
            return (T) stored.remove(type);
        }
    }
}
