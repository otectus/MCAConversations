package dev.otectus.mcaconversations.event;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.command.ConversationsCommand;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.gift.GiftMemoryProvider;
import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import dev.otectus.mcaconversations.gossip.GossipDetectors;
import dev.otectus.mcaconversations.state.ConversationState;
import dev.otectus.mcaconversations.state.StateTracker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Forge-bus wiring. Every MCA-dependent handler early-outs on {@link McaBridge#isAvailable()} and
 * the relevant config toggle before touching {@link McaCompat}.
 */
@Mod.EventBusSubscriber(modid = McaConversations.MOD_ID)
public final class ConversationsEvents {

    private ConversationsEvents() {
    }

    // --- Capability lifecycle -------------------------------------------------

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            GiftMemoryProvider provider = new GiftMemoryProvider();
            event.addCapability(ConversationsCapabilities.ID, provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        // The original player's caps are invalidated on death; revive to read, then re-invalidate.
        event.getOriginal().reviveCaps();
        ConversationsCapabilities.get(event.getOriginal()).ifPresent(old ->
                ConversationsCapabilities.get(event.getEntity()).ifPresent(fresh -> fresh.copyFrom(old)));
        event.getOriginal().invalidateCaps();
    }

    // --- Gossip detection ------------------------------------------------------

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!McaBridge.isAvailable() || event.getEntity().level().isClientSide()) {
            return;
        }
        if (McaCompat.isMcaVillager(event.getEntity())) {
            GossipDetectors.onVillagerDeath(event.getEntity());
        }
    }

    // --- Conversation states ---------------------------------------------------

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!McaBridge.isAvailable() || event.getEntity().level().isClientSide()
                || !McaConversationsConfig.COMMON.enableStates.get()) {
            return;
        }
        Entity target = event.getEntity();
        if (McaCompat.isMcaVillager(target) && event.getSource().getEntity() instanceof ServerPlayer player) {
            StateTracker.apply(target, player, ConversationState.ANNOYED);
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !McaBridge.isAvailable()) {
            return;
        }
        int interval = McaConversationsConfig.COMMON.gossipScanIntervalTicks.get();
        if (event.getServer().getTickCount() % interval == 0) {
            GossipDetectors.scan(event.getServer());
        }
    }

    // --- Commands ----------------------------------------------------------------

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ConversationsCommand.register(event.getDispatcher());
    }
}
