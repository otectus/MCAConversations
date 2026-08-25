package dev.otectus.mcaconversations.client;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.network.TypingStatusC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Client-side typing detector (the mod's only client code): edge-detects the vanilla chat screen and
 * tells the server so nearby villagers can turn toward the typing player. Re-pings once a second while
 * the screen stays open — the server expires a hold ~3 s after the last ping, so a lost "closed"
 * packet can never pin villagers forever. Sends nothing while not connected or outside a world.
 */
@EventBusSubscriber(modid = McaConversations.MOD_ID, value = Dist.CLIENT)
public final class ChatTypingTracker {

    /** Re-ping cadence while the chat screen is open (ticks). Must stay well under the server expiry. */
    private static final int PING_INTERVAL_TICKS = 20;

    private static boolean wasOpen = false;
    private static int ticksSincePing = 0;

    private ChatTypingTracker() {
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        // Post replaces the old TickEvent.ClientTickEvent + phase == END guard.
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.getConnection() == null) {
            wasOpen = false;
            return;
        }
        boolean open = mc.screen instanceof ChatScreen;
        try {
            if (open) {
                ticksSincePing++;
                if (!wasOpen || ticksSincePing >= PING_INTERVAL_TICKS) {
                    PacketDistributor.sendToServer(new TypingStatusC2S(true));
                    ticksSincePing = 0;
                }
            } else if (wasOpen) {
                PacketDistributor.sendToServer(new TypingStatusC2S(false));
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("typing ping failed; ignoring", t);
        }
        wasOpen = open;
    }
}
