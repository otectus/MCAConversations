package dev.otectus.mcaconversations.event;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import dev.otectus.mcaconversations.chat.ChatModePlayerStateProvider;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.ChatModeSession;
import dev.otectus.mcaconversations.command.ConversationsCommand;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.disposition.DispositionSavedData;
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
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

            ChatModePlayerStateProvider chatProvider = new ChatModePlayerStateProvider();
            event.addCapability(ConversationsCapabilities.CHAT_MODE_ID, chatProvider);
            event.addListener(chatProvider::invalidate);
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        // The original player's caps are invalidated on death; revive to read, then re-invalidate.
        event.getOriginal().reviveCaps();
        ConversationsCapabilities.get(event.getOriginal()).ifPresent(old ->
                ConversationsCapabilities.get(event.getEntity()).ifPresent(fresh -> fresh.copyFrom(old)));
        ConversationsCapabilities.getChatMode(event.getOriginal()).ifPresent(old ->
                ConversationsCapabilities.getChatMode(event.getEntity()).ifPresent(fresh -> fresh.copyFrom(old)));
        event.getOriginal().invalidateCaps();
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            ChatModeSession.clear(player.getUUID());
        }
    }

    // --- Chat mode -------------------------------------------------------------

    /**
     * Chat-mode entry point. Cheap early-out before the thread hop; the dispatcher captures only the
     * raw text on this (background) thread and hops to the server thread. Never cancels or mutates the
     * player's message — villagers respond around normal chat (1.19+ signed-chat safety).
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerChat(ServerChatEvent event) {
        if (!McaBridge.isAvailable() || !McaConversationsConfig.COMMON.enableChatMode.get()) {
            return;
        }
        ChatModeDispatcher.onChat(event);
    }

    // --- Player name sync ------------------------------------------------------

    /**
     * Keeps the player's MCA family-tree name in sync with the name they chose in the MCA editor, so
     * every villager — ours and MCA's own — addresses them by that name instead of the vanilla username.
     */
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!McaBridge.isAvailable() || !(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        McaCompat.syncPlayerFamilyName(player);
    }

    // --- Gossip detection ------------------------------------------------------

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!McaBridge.isAvailable() || event.getEntity().level().isClientSide()) {
            return;
        }
        if (McaCompat.isMcaVillager(event.getEntity())) {
            GossipDetectors.onVillagerDeath(event.getEntity());
            dropDispositions(event.getEntity());
        }
    }

    /** A dead villager's disposition records are meaningless — drop them (scars live in its LTM anyway). */
    private static void dropDispositions(Entity villager) {
        if (!McaConversationsConfig.COMMON.enableDispositions.get()) {
            return;
        }
        try {
            if (villager.getServer() != null) {
                DispositionSavedData.get(villager.getServer()).removeVillager(villager.getUUID());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("disposition death-prune failed; ignoring", t);
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
        // Deferred chat-mode replies are due-checked every tick (deadline queue, not the modulo cadence).
        ChatModeScheduler.drain(event.getServer().overworld().getGameTime());

        int interval = McaConversationsConfig.COMMON.gossipScanIntervalTicks.get();
        if (event.getServer().getTickCount() % interval == 0) {
            GossipDetectors.scan(event.getServer());
            pruneStaleDispositions(event.getServer());
        }
    }

    /** Age-based disposition pruning, riding the gossip scan cadence (no extra tick work). */
    private static void pruneStaleDispositions(net.minecraft.server.MinecraftServer server) {
        int staleDays = McaConversationsConfig.COMMON.dispositionStaleDays.get();
        if (staleDays <= 0 || !McaConversationsConfig.COMMON.enableDispositions.get()) {
            return;
        }
        try {
            DispositionSavedData.get(server).prune(server.overworld().getGameTime(), staleDays * 24_000L);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("disposition prune failed; ignoring", t);
        }
    }

    // --- Commands ----------------------------------------------------------------

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ConversationsCommand.register(event.getDispatcher());
    }
}
