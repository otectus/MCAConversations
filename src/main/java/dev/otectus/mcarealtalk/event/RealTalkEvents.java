package dev.otectus.mcarealtalk.event;

import dev.otectus.mcarealtalk.McaRealTalk;
import dev.otectus.mcarealtalk.McaRealTalkConfig;
import dev.otectus.mcarealtalk.command.RealTalkCommand;
import dev.otectus.mcarealtalk.compat.McaBridge;
import dev.otectus.mcarealtalk.compat.McaCompat;
import dev.otectus.mcarealtalk.gift.GiftMemoryProvider;
import dev.otectus.mcarealtalk.gift.RealTalkCapabilities;
import dev.otectus.mcarealtalk.gossip.GossipDetectors;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Forge-bus wiring. Every MCA-dependent handler early-outs on {@link McaBridge#isAvailable()} and
 * the relevant config toggle before touching {@link McaCompat}.
 */
@Mod.EventBusSubscriber(modid = McaRealTalk.MOD_ID)
public final class RealTalkEvents {

    private RealTalkEvents() {
    }

    // --- Capability lifecycle -------------------------------------------------

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            GiftMemoryProvider provider = new GiftMemoryProvider();
            event.addCapability(RealTalkCapabilities.ID, provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        // The original player's caps are invalidated on death; revive to read, then re-invalidate.
        event.getOriginal().reviveCaps();
        RealTalkCapabilities.get(event.getOriginal()).ifPresent(old ->
                RealTalkCapabilities.get(event.getEntity()).ifPresent(fresh -> fresh.copyFrom(old)));
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

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !McaBridge.isAvailable()) {
            return;
        }
        int interval = McaRealTalkConfig.COMMON.gossipScanIntervalTicks.get();
        if (event.getServer().getTickCount() % interval == 0) {
            GossipDetectors.scan(event.getServer());
        }
    }

    // --- Commands ----------------------------------------------------------------

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        RealTalkCommand.register(event.getDispatcher());
    }
}
