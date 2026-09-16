package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.conversation.ConversationHandle;
import dev.otectus.mcaconversations.conversation.ConversationLifecycle;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * Teardown stage 6: ending a discussion also ends MCA's own interaction (spec §4.5 step 6).
 *
 * <p>Until now a managed ending cleared this mod's state and left MCA's untouched, so MCA went on
 * believing the player had a screen open on that villager — which blocks its own interaction
 * eligibility, keeps its {@code InteractTask} eligible, and leaves the next player's right-click
 * arguing with a conversation that no longer exists.
 *
 * <p>It lives here, in {@code compat/}, for the same reason the terminal packet lives in
 * {@code network/}: {@link ConversationLifecycle} must not import either layer. It plugs in as a
 * {@link ConversationLifecycle.TeardownHook} and is registered <b>before</b> the packet bridge, so
 * MCA's half is closed before the client is told the discussion is over.
 *
 * <p><b>Conditional, never unconditional.</b> {@link McaCompat#stopInteractingIfOwned} first asks MCA
 * whether the interaction still belongs to this handle's player. A villager handed to somebody else
 * has an interaction that is now theirs, and closing it from the previous owner's teardown would
 * shut a window somebody is reading — the mirror image of the straggler close
 * {@code McaInteractionCloseMixin} refuses from the other direction.
 */
public final class NativeInteractionClose {

    private static volatile MinecraftServer server;
    private static volatile boolean installed;

    private NativeInteractionClose() {
    }

    /**
     * Binds the running server and registers the stage-6 hook, once per process; the server
     * reference is refreshed on every start so an integrated world opened twice in one session never
     * reads a stopped server's entity lists.
     */
    public static void install(MinecraftServer running) {
        server = running;
        if (installed) {
            return;
        }
        installed = true;
        ConversationLifecycle.addTeardownHook(NativeInteractionClose::closeNative);
    }

    /** Releases the server reference when the world stops. */
    public static void shutdown() {
        server = null;
    }

    private static void closeNative(ConversationHandle handle, CloseReason reason) {
        MinecraftServer running = server;
        if (running == null || handle == null || !McaBridge.isAvailable()) {
            return;
        }
        try {
            ServerPlayer player = running.getPlayerList().getPlayer(handle.playerId());
            if (player == null) {
                // The player is gone; MCA drops its own reference with them, and there is no screen
                // left to close. Never scan the world for a villager on behalf of nobody.
                return;
            }
            Entity villager = player.serverLevel().getEntity(handle.villagerId());
            if (villager == null) {
                return; // unloaded or elsewhere: nothing to tell MCA about
            }
            if (McaCompat.stopInteractingIfOwned(villager, handle.playerId())) {
                McaConversations.LOGGER.debug("closed MCA's interaction for {} ({})", handle, reason);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("could not end MCA's interaction for {}", handle, t);
        }
    }
}
