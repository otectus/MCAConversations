package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModePlayerState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * One-time migration of this mod's per-player data out of a Forge-written 1.20.1 player file.
 *
 * <p><b>What is being read.</b> On the Forge build, {@code ConversationsEvents.onAttachCapabilities}
 * attached two {@code ICapabilityProvider}s under the ids {@code mcaconversations:gift_memory} and
 * {@code mcaconversations:chat_mode}. Forge's {@code CapabilityDispatcher} writes every attached
 * capability into a single root-level {@code ForgeCaps} compound on the entity, keyed by that id,
 * with each value being the provider's {@code serializeNBT()}. Both providers did nothing but
 * delegate to {@link GiftMemoryData} and {@link ChatModePlayerState}, whose tag shapes are unchanged
 * in this port. That is why the migration is a straight key-for-key read and needs no bespoke
 * parser: only the envelope changed, never the contents.
 *
 * <p>NeoForge stores attachments under {@code neoforge:attachments} and will not import
 * {@code ForgeCaps} on its own, so without this the data would simply be dropped.
 *
 * <p><b>New data always wins.</b> Every read checks {@code hasData} first. A player file can
 * legitimately carry both — {@code ForgeCaps} is an unknown root field that neither vanilla nor
 * NeoForge rewrites, so it lingers until the next save — and in that state the attachment is the
 * live copy and the {@code ForgeCaps} entry is a fossil. Overwriting the former with the latter
 * would silently roll a player's gift memory back to whenever they last played on Forge.
 *
 * <p>This ordering is only correct because NeoForge deserializes attachments inside
 * {@code Entity.load} <em>before</em> it calls {@code readAdditionalSaveData}, which is where
 * {@link dev.otectus.mcaconversations.mixin.PlayerLegacyDataMixin} invokes this. By the time we
 * run, {@code hasData} genuinely reflects what was in the file.
 *
 * <p><b>No migration flag is stored.</b> None is needed. Neither vanilla nor NeoForge re-emits an
 * unknown root field, so the first save after a migration writes {@code neoforge:attachments} and
 * drops {@code ForgeCaps} altogether; from then on the attachment is the only copy. And in the one
 * window where it could run twice — migrate, then crash before saving — it reads the same bytes and
 * produces the same result, so a flag would guard nothing.
 *
 * @see dev.otectus.mcaconversations.mixin.PlayerLegacyDataMixin
 */
public final class ForgeCapsMigration {

    /** The root-level compound Forge's capability dispatcher wrote attached capabilities into. */
    static final String FORGE_CAPS_KEY = "ForgeCaps";

    private ForgeCapsMigration() {
    }

    /** Called from the mixin, once, as the player's saved data finishes loading. */
    public static void apply(Player player, CompoundTag playerNbt) {
        applyTo(player, playerNbt, player.getName().getString());
    }

    /**
     * The migration proper, over the attachment interface rather than a {@link Player}, so it can be
     * exercised without a running game.
     *
     * @param who a human-readable player identifier, for the log line only
     */
    static void applyTo(IAttachmentHolder target, CompoundTag playerNbt, String who) {
        // The overwhelmingly common path: a NeoForge-native or vanilla player file, which has no
        // ForgeCaps at all. Cheap enough to run for every player on every load.
        if (!playerNbt.contains(FORGE_CAPS_KEY, Tag.TAG_COMPOUND)) {
            return;
        }
        CompoundTag caps = playerNbt.getCompound(FORGE_CAPS_KEY);

        migrate(target, caps, ConversationsAttachments.GIFT_MEMORY, ConversationsAttachments.ID,
                GiftMemoryData::fromNbt, data -> data.size() + " remembered gift(s)", who);
        migrate(target, caps, ConversationsAttachments.CHAT_MODE, ConversationsAttachments.CHAT_MODE_ID,
                ChatModePlayerState::fromNbt, ForgeCapsMigration::describeChoice, who);
    }

    /**
     * Reads the migrated choice straight off the record rather than through {@code isEnabled()},
     * which consults the config default when no explicit choice was ever made — and the config is
     * exactly what an operator reading this line is trying to reason about.
     */
    private static String describeChoice(ChatModePlayerState state) {
        if (!state.isExplicit()) {
            return "no explicit choice";
        }
        return state.storedChoice() ? "explicitly on" : "explicitly off";
    }

    /**
     * Moves one capability into its attachment, or leaves everything alone.
     *
     * <p>The {@code describe} function exists so the log line can say how much was carried over
     * without ever printing the data itself: an operator wants to know the migration happened and
     * looks plausible, not what any player was given by whom.
     */
    private static <A> void migrate(IAttachmentHolder target, CompoundTag caps,
                                    Supplier<AttachmentType<A>> type, ResourceLocation id,
                                    Function<CompoundTag, A> reader, Function<A, String> describe,
                                    String who) {
        if (target.hasData(type)) {
            return;
        }
        String key = id.toString();
        if (!caps.contains(key, Tag.TAG_COMPOUND)) {
            return;
        }
        try {
            A migrated = reader.apply(caps.getCompound(key));
            target.setData(type, migrated);
            McaConversations.LOGGER.info("Migrated Forge capability {} for {} ({}).",
                    key, who, describe.apply(migrated));
        } catch (Throwable t) {
            // A corrupt or foreign compound under our key must not stop the player loading, and must
            // not stop the other capability migrating either. They keep the fresh default.
            McaConversations.LOGGER.warn(
                    "Could not migrate Forge capability {} for {}; keeping a fresh default.", key, who, t);
        }
    }
}
