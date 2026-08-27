package dev.otectus.mcaconversations.event;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.ChatIntentLoader;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import dev.otectus.mcaconversations.chat.ChatModePlayerStateProvider;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.ChatModeSession;
import dev.otectus.mcaconversations.chat.GreetOnApproach;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import dev.otectus.mcaconversations.command.ConversationsCommand;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.BeatContractLoader;
import dev.otectus.mcaconversations.conversation.ConversationCatalogLoader;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.disposition.DispositionSavedData;
import dev.otectus.mcaconversations.interiority.Interiority;
import dev.otectus.mcaconversations.profession.ProfessionProfileLoader;
import dev.otectus.mcaconversations.progress.ProgressSavedData;
import dev.otectus.mcaconversations.gift.GiftMemoryProvider;
import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import dev.otectus.mcaconversations.gossip.GossipDetectors;
import dev.otectus.mcaconversations.state.ConversationState;
import dev.otectus.mcaconversations.state.StateTracker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Forge-bus wiring. Every MCA-dependent handler early-outs on {@link McaBridge#isAvailable()} and
 * the relevant config toggle before touching {@link McaCompat}.
 */
@Mod.EventBusSubscriber(modid = McaConversations.MOD_ID)
public final class ConversationsEvents {

    /** Greet-on-approach proximity-scan cadence (2 s) — cheap AABB queries, not worth a config knob. */
    private static final int GREET_SCAN_INTERVAL_TICKS = 40;

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
            GreetOnApproach.clear(player.getUUID());
            dev.otectus.mcaconversations.hub.DynamicHub.clear(player.getUUID());
            VillagerAttention.clearPlayer(player.getUUID());
        }
    }

    // --- Chat mode -------------------------------------------------------------

    /**
     * Chat-mode entry point. Cheap early-out before the thread hop; the dispatcher captures only the
     * raw text on this (background) thread and hops to the server thread. The default path never
     * cancels or mutates the player's message — villagers respond around normal chat (1.19+ signed-chat
     * safety). The one exception is the EXPERIMENTAL opt-in {@code chatModeLocalChat}, which cancels
     * and rebroadcasts within a radius (trade-off documented on the dispatcher method + config).
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerChat(ServerChatEvent event) {
        if (!McaBridge.isAvailable() || !McaConversationsConfig.COMMON.enableChatMode.get()) {
            return;
        }
        if (ChatModeDispatcher.interceptLocalChat(event)) {
            return; // consumed: local rebroadcast + pipeline run on one main-thread hop
        }
        ChatModeDispatcher.onChat(event);
    }

    // --- Startup summary -------------------------------------------------------

    /**
     * Logs, once, what chat mode will actually do on this server.
     *
     * <p>Chat mode changes how player chat behaves — who is opted in, whether villager replies are
     * visible to bystanders, and (when {@code chatModeLocalChat} is on) whether messages stay
     * radius-local and unsigned. Those are consequential enough that an operator should not have to
     * read four config keys to discover them, so the effective combination is stated plainly in the
     * log at startup.
     */
    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        McaConversationsConfig.Common c = McaConversationsConfig.COMMON;
        if (!c.enableChatMode.get()) {
            return;
        }
        McaConversations.LOGGER.info(
                "Chat mode is ON: players are opted {} by default; villager replies are {}; "
                        + "radius-local player chat is {}. "
                        + "Knobs: enableChatMode, chatModeDefaultOn, chatModePublicReplies, chatModeLocalChat.",
                c.chatModeDefaultOn.get() ? "IN" : "OUT",
                c.chatModePublicReplies.get()
                        ? "public (nearby players see them)" : "private (whisper model)",
                c.chatModeLocalChat.get()
                        ? "ENABLED (opted-in players' chat becomes unsigned and radius-limited)"
                        : "disabled (vanilla chat untouched)");
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
            dropProgress(event.getEntity());
            dropLivingHistory(event.getEntity());
            ConversationSessions.clearVillager(event.getEntity().getUUID());
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

    /**
     * A dead villager can never have the conversation its ledger exists to remember, so its progress
     * rows are dropped with its disposition. Milestones are per (villager, player): with the villager
     * gone there is nothing left that could ever read them back.
     */
    private static void dropProgress(Entity villager) {
        try {
            if (villager.getServer() != null) {
                ProgressSavedData.get(villager.getServer()).removeVillager(villager.getUUID());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("progress death-prune failed; ignoring", t);
        }
    }

    /**
     * A dead villager's identity and history go the same way as their progress rows.
     *
     * <p>Their episodes were theirs, their threads were with them, and their opinions were about
     * neighbours they can no longer discuss. Nothing left in the world could read any of it back, and
     * keeping it would be the one way this store could grow without bound in a long-lived world.
     */
    private static void dropLivingHistory(Entity villager) {
        try {
            if (villager.getServer() != null) {
                dev.otectus.mcaconversations.identity.Identity.forget(
                        villager.getServer(), villager.getUUID());
                dev.otectus.mcaconversations.history.History.forget(
                        villager.getServer(), villager.getUUID());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("living-history death-prune failed; ignoring", t);
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
        long gameTime = event.getServer().overworld().getGameTime();
        ChatModeScheduler.drain(gameTime);

        // Villager attention (typing awareness + conversation presence) is applied every tick.
        VillagerAttention.tick(event.getServer(), gameTime);

        // Proactive greeting rides its own light cadence (Phase 4, double-gated, off by default).
        if (event.getServer().getTickCount() % GREET_SCAN_INTERVAL_TICKS == 0
                && McaConversationsConfig.COMMON.enableChatMode.get()
                && McaConversationsConfig.COMMON.chatModeGreetOnApproach.get()) {
            GreetOnApproach.scan(event.getServer());
        }

        int interval = McaConversationsConfig.COMMON.gossipScanIntervalTicks.get();
        if (event.getServer().getTickCount() % interval == 0) {
            GossipDetectors.scan(event.getServer());
            pruneStaleDispositions(event.getServer());
            pruneStaleProgress(event.getServer());
            pruneLivingHistory(event.getServer());
            spreadVillageTalk(event.getServer());
            ConversationSessions.sweep(gameTime);
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

    /**
     * Age-based progress pruning, riding the same low-frequency cadence as the gossip scan. Uses the
     * disposition stale-days knob rather than adding a second one: both are "this pair has not spoken
     * in a very long time" and an operator should not have to reason about two numbers.
     */
    private static void pruneStaleProgress(net.minecraft.server.MinecraftServer server) {
        int staleDays = McaConversationsConfig.COMMON.dispositionStaleDays.get();
        if (staleDays <= 0) {
            return;
        }
        try {
            ProgressSavedData.get(server).prune(server.overworld().getGameTime(), staleDays * 24_000L);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("progress prune failed; ignoring", t);
        }
    }

    /**
     * Expiry-based history pruning, riding the same low-frequency cadence as everything else here.
     *
     * <p>Deliberately not a tick job of its own. §21.6 forbids a per-tick scan of history, and the
     * gossip sweep already runs at exactly the frequency this needs: expired episodes, lapsed threads
     * and settled promises are not urgent, and a pass every few thousand ticks is plenty.
     */
    private static void pruneLivingHistory(net.minecraft.server.MinecraftServer server) {
        try {
            long today = server.overworld().getDayTime() / 24000L;
            dev.otectus.mcaconversations.history.History.prune(server, today);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("living-history prune failed; ignoring", t);
        }
    }

    /**
     * One pass of village talk, riding the same low-frequency cadence as everything else here.
     *
     * <p>Deliberately not a job of its own. §16.4 says propagation runs on the existing village sweep
     * or as a conversation consequence, and the sweep already runs at the right frequency: a story
     * moving between two neighbours is not urgent, and a pass every few thousand ticks is the point.
     */
    private static void spreadVillageTalk(net.minecraft.server.MinecraftServer server) {
        try {
            long today = server.overworld().getDayTime() / 24000L;
            dev.otectus.mcaconversations.history.RumourPropagation.sweep(server, today);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("rumour sweep failed; ignoring", t);
        }
    }

    // --- Commands ----------------------------------------------------------------

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ConversationsCommand.register(event.getDispatcher());
    }

    // --- Datapack listeners ------------------------------------------------------

    /**
     * Registers this mod's datapack loaders — chat intents, the conversation catalog, the semantic beat
     * contracts, the profession profiles and the per-personality interiority profiles, each merged across namespaces so packs can extend them.
     * MCA-independent — these are our own resources — so they attach regardless of
     * {@link McaBridge#isAvailable()}; each loaded index is inert until its feature is on.
     */
    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new ChatIntentLoader());
        event.addListener(new ConversationCatalogLoader());
        event.addListener(new BeatContractLoader());
        event.addListener(new ProfessionProfileLoader());
        event.addListener(new Interiority());
        // Living histories: identity tokens, conversation scenes, and the three narrative
        // template directories. Inert until dynamic.enabled is on, like every other index here.
        event.addListener(new dev.otectus.mcaconversations.identity.IdentityCatalogLoader());
        event.addListener(new dev.otectus.mcaconversations.scene.SceneCatalogLoader());
        event.addListener(new dev.otectus.mcaconversations.village.VillageCultureCatalogLoader());
        dev.otectus.mcaconversations.history.NarrativeCatalogLoader.listeners()
                .forEach(event::addListener);
    }
}
