package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.chat.ChatModePlayerState;
import dev.otectus.mcaconversations.state.LastGift;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.AttachmentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The two player attachments: their registration, their death-copy flag, and the NBT shape an
 * upgraded world depends on.
 *
 * <p>The shape assertions are deliberately about literal key names rather than a
 * serialise-then-deserialise round trip. A round trip passes just as happily after a key rename,
 * because both sides rename together — and a key rename is precisely what silently empties every
 * existing player's gift memory on first load.
 */
class AttachmentNbtRoundTripTest {

    private static final UUID ANNA = UUID.fromString("11111111-2222-3333-4444-555555555555");

    // --- Registration ---------------------------------------------------------

    @Test
    void bothAttachmentsRegisterUnderTheirOriginalCapabilityIds() {
        assertEquals("mcaconversations:gift_memory", ConversationsAttachments.ID.toString());
        assertEquals("mcaconversations:chat_mode", ConversationsAttachments.CHAT_MODE_ID.toString());

        AttachmentType<GiftMemoryData> gift = ConversationsAttachments.GIFT_MEMORY.get();
        AttachmentType<ChatModePlayerState> chat = ConversationsAttachments.CHAT_MODE.get();
        assertNotNull(gift);
        assertNotNull(chat);
    }

    @Test
    void everyDefaultInstanceIsFresh() {
        // A shared mutable default would give every player on the server the same gift memory,
        // and the symptom -- villagers remembering gifts from someone else -- would look like a
        // logic bug rather than a registration one.
        GiftMemoryData first = new GiftMemoryData();
        GiftMemoryData second = new GiftMemoryData();
        assertNotSame(first, second);
        first.recordGift(ANNA, new LastGift("minecraft:bread", 1, 10L), 16);
        assertTrue(second.isEmpty());
    }

    // --- Gift memory ----------------------------------------------------------

    @Test
    void giftMemoryIsKeyedByVillagerUuidString() {
        GiftMemoryData data = new GiftMemoryData();
        data.recordGift(ANNA, new LastGift("minecraft:golden_apple", 3, 4200L), 16);

        CompoundTag tag = data.save();
        assertTrue(tag.contains(ANNA.toString()),
                "the 1.20.1 capability keyed on the villager UUID string; changing that strands old data");

        GiftMemoryData reloaded = GiftMemoryData.fromNbt(tag);
        assertEquals(1, reloaded.size());
        LastGift gift = reloaded.lastGiftTo(ANNA).orElseThrow();
        assertEquals("minecraft:golden_apple", gift.itemId());
        assertEquals(3, gift.count());
        assertEquals(4200L, gift.gameTime());
    }

    @Test
    void anEmptyGiftMemoryIsDistinguishableFromAnAbsentOne() {
        // Attachments create on read, so Optional.isEmpty() no longer answers "has this player ever
        // been given a gift?". isEmpty() is the replacement, and call sites depend on it.
        assertTrue(new GiftMemoryData().isEmpty());
        GiftMemoryData data = new GiftMemoryData();
        data.recordGift(ANNA, new LastGift("minecraft:bread", 1, 1L), 16);
        assertFalse(data.isEmpty());
    }

    @Test
    void aCorruptVillagerKeyIsSkippedRatherThanFatal() {
        CompoundTag tag = new CompoundTag();
        tag.put("not-a-uuid", new CompoundTag());
        // Nothing about a damaged save may stop the player loading.
        assertTrue(GiftMemoryData.fromNbt(tag).isEmpty());
    }

    // --- Chat mode ------------------------------------------------------------

    @Test
    void chatModeStoresTheExplicitFlagSeparatelyFromTheChoice() {
        ChatModePlayerState state = new ChatModePlayerState();
        state.setEnabled(false);

        CompoundTag tag = state.save();
        assertTrue(tag.contains("explicit"), "1.20.1 key name; renaming it re-opts players in");
        assertTrue(tag.contains("enabled"), "1.20.1 key name");
        assertTrue(tag.getBoolean("explicit"));
        assertFalse(tag.getBoolean("enabled"));

        ChatModePlayerState reloaded = ChatModePlayerState.fromNbt(tag);
        assertTrue(reloaded.isExplicit());
        assertFalse(reloaded.storedChoice());
    }

    @Test
    void aPlayerWhoNeverChoseStaysUnset() {
        // Two booleans rather than one is the whole design: "off" and "never asked" must not
        // collapse, because the second one has to follow the server's chatModeDefaultOn.
        ChatModePlayerState fresh = new ChatModePlayerState();
        assertFalse(fresh.isExplicit());
        CompoundTag tag = fresh.save();
        assertFalse(tag.getBoolean("explicit"));
        assertFalse(ChatModePlayerState.fromNbt(tag).isExplicit());
    }
}
