package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModePlayerState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * This mod's per-player persistent data, held as NeoForge data attachments.
 *
 * <p>Replaces the 1.20.1 build's {@code ConversationsCapabilities} together with both of its
 * {@code ICapabilityProvider} classes. Attachments subsume the lot: {@code serializable} gives the
 * save/load that {@code INBTSerializable} gave through the providers, and {@code copyOnDeath} gives
 * the death/respawn copy that {@code PlayerEvent.Clone} did — including the
 * {@code reviveCaps()}/{@code invalidateCaps()} dance, which has no NeoForge analogue and needs
 * none. NeoForge already carries serializable attachments through an End return, so there is no
 * clone handler here either.
 *
 * <p>The attachment ids are the same {@link ResourceLocation}s the capabilities used —
 * {@code mcaconversations:gift_memory} and {@code mcaconversations:chat_mode} — which keeps the
 * one-time {@code ForgeCaps} migration a straight key-for-key read. See {@link ForgeCapsMigration}.
 *
 * <p>Neither value is synced: both are read and mutated only on the logical server.
 *
 * <p><b>Fresh instances are mandatory.</b> The supplier must build a new object every call. An
 * attachment handing out one shared mutable instance would silently give every player on the server
 * the same gift memory.
 */
public final class ConversationsAttachments {

    public static final DeferredRegister<AttachmentType<?>> REGISTER =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, McaConversations.MOD_ID);

    public static final Supplier<AttachmentType<GiftMemoryData>> GIFT_MEMORY =
            REGISTER.register("gift_memory", () -> AttachmentType
                    .serializable(GiftMemoryData::new)
                    .copyOnDeath()
                    .build());

    public static final Supplier<AttachmentType<ChatModePlayerState>> CHAT_MODE =
            REGISTER.register("chat_mode", () -> AttachmentType
                    .serializable(ChatModePlayerState::new)
                    .copyOnDeath()
                    .build());

    /** {@code mcaconversations:gift_memory} — also the key the 1.20.1 {@code ForgeCaps} blob used. */
    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "gift_memory");

    /** {@code mcaconversations:chat_mode} — likewise. */
    public static final ResourceLocation CHAT_MODE_ID =
            ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "chat_mode");

    private ConversationsAttachments() {
    }

    /**
     * This player's gift memory, creating an empty record on first access.
     *
     * <p>Never null and never empty-Optional: unlike a capability, an attachment always resolves.
     * Call sites that used to branch on absence should ask {@link GiftMemoryData#isEmpty()} instead
     * — "no data yet" and "data that happens to be empty" are the same state here.
     */
    public static GiftMemoryData giftMemory(Player player) {
        return player.getData(GIFT_MEMORY);
    }

    /** This player's chat-mode opt-in, creating a default record on first access. See {@link #giftMemory}. */
    public static ChatModePlayerState chatMode(Player player) {
        return player.getData(CHAT_MODE);
    }
}
