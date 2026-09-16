package dev.otectus.mcaconversations.client;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages;
import dev.otectus.mcaconversations.network.ConversationPresenceC2S;
import dev.otectus.mcaconversations.network.ConversationRef;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Says "still here" while the player has a conversation window open (spec §4.3).
 *
 * <p>Reading a long reply is not being idle, which is why a graphical offer has no reading timeout
 * at all. The cost of that is a server with no way to tell a player who is reading from a client
 * that crashed — so the client reports its own presence instead, on the same pattern as the typing
 * ping: a cheap payload on a fixed cadence, carrying nothing the server has to trust.
 *
 * <p>Sends only while a handle exists <em>and</em> a screen is actually on top. A heartbeat from a
 * client with no window open would be exactly the lie this is meant to catch.
 *
 * <p>What the server does with the timestamps is the presence lease: a discussion that stops
 * reporting itself is closed and its villager released, so a crashed client cannot pin an NPC to a
 * conversation nobody is in. Nothing here decides that — this end of it only tells the truth about
 * whether a window is open.
 */
@EventBusSubscriber(modid = McaConversations.MOD_ID, value = Dist.CLIENT)
public final class ConversationPresenceTracker {

    /** Heartbeat cadence in ticks. An internal constant, deliberately not a config knob. */
    private static final int PING_INTERVAL_TICKS = 20;

    private static int ticksSincePing = PING_INTERVAL_TICKS;

    private ConversationPresenceTracker() {
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        // Post replaces the old TickEvent.ClientTickEvent + phase == END guard.
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.getConnection() == null || minecraft.screen == null) {
            ticksSincePing = PING_INTERVAL_TICKS;
            return;
        }
        ConversationRef ref = ClientChoiceMessages.currentRef();
        if (!ref.identified()) {
            ticksSincePing = PING_INTERVAL_TICKS;
            return;
        }
        if (++ticksSincePing < PING_INTERVAL_TICKS) {
            return;
        }
        ticksSincePing = 0;
        try {
            PacketDistributor.sendToServer(new ConversationPresenceC2S(ref));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("conversation presence ping failed; ignoring", t);
        }
    }
}
