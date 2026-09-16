package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.conversation.ConversationHandle;
import dev.otectus.mcaconversations.conversation.ConversationLifecycle;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

/**
 * The teardown coordinator's outbound half: it tells the client that one named discussion is over.
 *
 * <p>It exists as a separate class because of the direction of the dependency.
 * {@link ConversationLifecycle} deliberately knows nothing about the wire — it is the ordering and
 * ownership rules, and it has to stay unit-testable without a server — so the packet is sent from a
 * registered {@link ConversationLifecycle.TeardownHook} instead of from inside the teardown itself
 * (spec §4.5 steps 6–8). A hook that fails is one logged line; the villager is still released.
 *
 * <p>Nothing waits for the packet. A terminal message is a courtesy to the client, never a condition
 * for freeing the villager — a crashed or malicious client must not be able to keep an NPC pinned by
 * declining to acknowledge an ending.
 */
public final class ConversationLifecycleBridge {

    private static volatile MinecraftServer server;
    private static volatile boolean installed;

    private ConversationLifecycleBridge() {
    }

    /**
     * Binds the running server and registers the terminal-packet hook.
     *
     * <p>The hook is registered once per process; the server reference is refreshed on every start,
     * so an integrated world opened twice in one session sends to the second world's players and
     * never to a stopped server's stale player list.
     */
    public static void install(MinecraftServer running) {
        server = running;
        if (installed) {
            return;
        }
        installed = true;
        ConversationLifecycle.addTeardownHook(ConversationLifecycleBridge::announceClosed);
    }

    /** Releases the server reference when the world stops. */
    public static void shutdown() {
        server = null;
    }

    private static void announceClosed(ConversationHandle handle, CloseReason reason) {
        MinecraftServer running = server;
        if (running == null || handle == null) {
            return;
        }
        try {
            ServerPlayer player = running.getPlayerList().getPlayer(handle.playerId());
            if (player != null) {
                ConversationsNetwork.sendClosed(player, ConversationRef.of(handle), reason);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("could not announce the close of {}", handle, t);
        }
    }
}
