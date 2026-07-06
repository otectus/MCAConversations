package dev.otectus.mcarealtalk.gift;

import dev.otectus.mcarealtalk.McaRealTalk;
import dev.otectus.mcarealtalk.McaRealTalkConfig;
import dev.otectus.mcarealtalk.compat.McaCompat;
import dev.otectus.mcarealtalk.state.LastGift;
import dev.otectus.mcarealtalk.state.MemoryIds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Records an accepted gift (called from the {@code BreedableRelationship.acceptGift} mixin hook):
 * <ol>
 *   <li>player capability — last gift per villager, for the {@code last_gift_item} template var</li>
 *   <li>villager LongTermMemory — player-scoped {@code mcarealtalk.state.grateful} with the
 *       configured expiry window, so pure-JSON dialogue can condition on recent gratitude</li>
 * </ol>
 */
public final class GiftTracker {

    private GiftTracker() {
    }

    public static void recordAcceptedGift(Entity villager, ServerPlayer player, ItemStack stack) {
        if (!McaRealTalkConfig.COMMON.enableStates.get() && !McaRealTalkConfig.COMMON.enableTemplates.get()) {
            return;
        }
        if (villager == null || player == null || stack == null || stack.isEmpty()) {
            return;
        }
        try {
            String itemId = String.valueOf(ForgeRegistries.ITEMS.getKey(stack.getItem()));
            long now = player.serverLevel().getGameTime();

            RealTalkCapabilities.get(player).ifPresent(data -> data.recordGift(
                    villager.getUUID(),
                    new LastGift(itemId, stack.getCount(), now),
                    McaRealTalkConfig.COMMON.giftMemoryPerPlayerCap.get()));

            McaCompat.remember(villager, MemoryIds.playerScoped(MemoryIds.state("grateful"), player.getUUID()),
                    McaRealTalkConfig.COMMON.gratitudeWindowTicks.get());

            if (McaRealTalkConfig.COMMON.debugLogging.get()) {
                McaRealTalk.LOGGER.info("Recorded gift {} x{} from {} to {}", itemId, stack.getCount(),
                        player.getGameProfile().getName(), villager.getUUID());
            }
        } catch (Throwable t) {
            McaRealTalk.LOGGER.debug("Gift recording failed; ignoring", t);
        }
    }
}
