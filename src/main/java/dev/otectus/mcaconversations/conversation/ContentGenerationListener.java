package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

/**
 * Registered last, after every catalog loader, so it runs last: reload listeners apply in
 * registration order on the server thread. Advancing the generation here is what makes a reload
 * atomic from the outside — the new generation appears only once all the content behind it has been
 * published.
 *
 * <p>It loads nothing itself, so it is a plain {@link ResourceManagerReloadListener} rather than a
 * JSON listener.
 */
public final class ContentGenerationListener implements ResourceManagerReloadListener {

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        McaConversations.LOGGER.info("Conversation content generation {} published.",
                ContentGeneration.advance());
    }
}
