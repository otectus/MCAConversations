package dev.otectus.mcaconversations.client;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

import java.util.concurrent.atomic.AtomicInteger;

/** Monotonic cache token for language/font/resource-pack reloads. */
@EventBusSubscriber(modid = McaConversations.MOD_ID, value = Dist.CLIENT,
        bus = EventBusSubscriber.Bus.MOD)
public final class ClientUiResourceGeneration implements ResourceManagerReloadListener {

    private static final AtomicInteger GENERATION = new AtomicInteger();

    private ClientUiResourceGeneration() {
    }

    public static int current() {
        return GENERATION.get();
    }

    @SubscribeEvent
    public static void register(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new ClientUiResourceGeneration());
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        GENERATION.incrementAndGet();
    }
}
