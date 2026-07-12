package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;

import java.util.List;

/**
 * Orchestrates the chat-mode pipeline (spec §3). The only class that sees {@link ServerChatEvent} data.
 *
 * <p><b>Threading:</b> {@code ServerChatEvent} fires on a background thread (Forge 1.20.1 wraps chat
 * decoration in {@code CompletableFuture.supplyAsync}). The subscriber therefore captures only the
 * plain {@code (player, raw text)} and hops to the main thread via {@code server.execute} before any
 * entity access. All matching, session mutation, {@code selectAnswer}, and delivery run on the server
 * thread from {@link #handle} onward.
 *
 * <p>Phase 1 wires the plumbing and the {@code /conversations chat debug-ask} milestone driver; the NLU
 * matching that turns free text into a {@code (question, answer)} binding arrives in Phase 2, in
 * {@link #handle}.
 */
public final class ChatModeDispatcher {

    private ChatModeDispatcher() {
    }

    /** Background-thread entry point: capture plain data and hop to the server thread. */
    public static void onChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        if (player == null) {
            return;
        }
        String raw = event.getRawText();
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }
        try {
            server.execute(() -> {
                try {
                    handle(player, raw);
                } catch (Throwable t) {
                    McaConversations.LOGGER.warn("chat-mode handler failed; ignoring message", t);
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("chat-mode thread hop failed; ignoring message", t);
        }
    }

    /** Server-thread pipeline. Phase 1: guard-only (matching lands in Phase 2). */
    static void handle(ServerPlayer player, String raw) {
        if (player.hasDisconnected() || !player.isAlive() || player.isSpectator()) {
            return;
        }
        if (!McaBridge.isAvailable() || !McaConversationsConfig.COMMON.enableChatMode.get()) {
            return;
        }
        if (raw == null) {
            return;
        }
        String message = raw.strip();
        if (message.isEmpty() || message.startsWith("/")) {
            return;
        }
        if (!isOptedIn(player)) {
            return;
        }
        // Phase 2: VillagerFinder -> Addressing -> Normalizer -> IntentMatcher -> GatePreview ->
        //          ChatModeSession.open + McaCompat.selectAnswer. Skeleton stops here for now.
    }

    /**
     * The Phase-1 de-risk milestone: drive the nearest villager's dialogue engine at an explicit
     * {@code (question, answer)} and let the redirect mixin surface the reply in chat. Returns a short
     * status the command reports. Op-gated by the command.
     */
    public static String debugAsk(ServerPlayer player, String questionId, String answerName) {
        if (!McaBridge.isAvailable()) {
            return "MCA is not available; chat mode is inert.";
        }
        double radius = McaConversationsConfig.COMMON.chatModeAddressedRadius.get();
        List<VillagerFinder.VillagerCandidate> candidates = VillagerFinder.candidates(player, radius);
        if (candidates.isEmpty()) {
            return "No MCA villager within " + (int) radius + " blocks.";
        }
        VillagerFinder.VillagerCandidate target = candidates.get(0);
        boolean ok;
        try (ChatModeSession.Scope scope = ChatModeSession.open(player, target.entity())) {
            ok = McaCompat.selectAnswer(target.entity(), player, questionId, answerName);
        }
        if (!ok) {
            return "selectAnswer failed for (" + questionId + ", " + answerName + ") — see debug log.";
        }
        MinecraftServer server = player.getServer();
        long now = server != null ? server.overworld().getGameTime() : 0L;
        ChatModeSession.recordExchange(player.getUUID(), target.entity().getUUID(), now);
        String name = target.name().isBlank() ? "villager" : target.name();
        String redirect = ChatModeSession.redirectionAvailable() ? "" : " (warning: delivery redirect not active)";
        return "Asked " + name + " (" + questionId + " / " + answerName + ")." + redirect;
    }

    private static boolean isOptedIn(ServerPlayer player) {
        return ConversationsCapabilities.getChatMode(player)
                .map(ChatModePlayerState::isEnabled)
                .orElse(McaConversationsConfig.COMMON.chatModeDefaultOn.get());
    }
}
