package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.state.ConversationState;
import dev.otectus.mcaconversations.state.LastGift;
import dev.otectus.mcaconversations.state.StateTracker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * Records an accepted gift (called from the {@code BreedableRelationship.acceptGift} mixin hook):
 * <ol>
 *   <li>player capability — last gift per villager, for the {@code last_gift_item} template var</li>
 *   <li>villager LongTermMemory — player-scoped {@code mcaconversations.state.grateful} with the
 *       configured expiry window, so pure-JSON dialogue can condition on recent gratitude</li>
 * </ol>
 */
public final class GiftTracker {

    private GiftTracker() {
    }

    public static void recordAcceptedGift(Entity villager, ServerPlayer player, ItemStack stack) {
        if (!McaConversationsConfig.COMMON.enableStates.get() && !McaConversationsConfig.COMMON.enableTemplates.get()) {
            return;
        }
        if (villager == null || player == null || stack == null || stack.isEmpty()) {
            return;
        }
        try {
            String itemId = String.valueOf(BuiltInRegistries.ITEM.getKey(stack.getItem()));
            long now = player.serverLevel().getGameTime();

            ConversationsAttachments.giftMemory(player).recordGift(
                    villager.getUUID(),
                    new LastGift(itemId, stack.getCount(), now),
                    McaConversationsConfig.COMMON.giftMemoryPerPlayerCap.get());

            StateTracker.apply(villager, player, ConversationState.GRATEFUL);
            // A gift given while already very fond deepens gratitude into being smitten.
            if (McaCompat.getHearts(player, villager) >= McaConversationsConfig.COMMON.stateSmittenMinHearts.get()) {
                StateTracker.apply(villager, player, ConversationState.SMITTEN);
            }

            if (McaConversationsConfig.COMMON.debugLogging.get()) {
                McaConversations.LOGGER.info("Recorded gift {} x{} from {} to {}", itemId, stack.getCount(),
                        player.getGameProfile().getName(), villager.getUUID());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Gift recording failed; ignoring", t);
        }
    }
}
