package dev.otectus.mcaconversations.client;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages;
import dev.otectus.mcaconversations.network.ConversationsNetwork;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Physical-client setup: installs the sink that decoded choice packets are handed to.
 *
 * <p>Decoding and channel registration are common work — a dedicated server reads the same protocol
 * — but only a client can act on an offer. Installing the handler from here is what lets both S2C
 * packet classes stay free of any {@code client} import, so a dedicated server never has a client
 * class in reach of its class loader at all.
 */
@EventBusSubscriber(modid = McaConversations.MOD_ID, value = Dist.CLIENT,
        bus = EventBusSubscriber.Bus.MOD)
public final class ConversationsClient {

    private ConversationsClient() {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ConversationsNetwork.installSink(ClientChoiceMessages.INSTANCE));
    }
}
