package dev.otectus.mcaconversations.client.dialogue.dev;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

/**
 * Registers the response-card preview under {@code /mcaconversations-preview}.
 *
 * <p>Gated on a development runtime, so the command does not exist in a shipped jar. The screen it
 * opens is a build-time tool, not a player-facing feature, which is also why its labels are literal
 * English rather than translated: preview copy has no place in the language files that ship, and
 * every string there is held to English/Portuguese parity by the locale lint.
 */
@EventBusSubscriber(modid = McaConversations.MOD_ID, value = Dist.CLIENT)
public final class DialoguePreviewCommand {

    private DialoguePreviewCommand() {
    }

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        if (FMLLoader.isProduction()) {
            return;
        }
        event.getDispatcher().register(Commands.literal("mcaconversations-preview")
                .executes(context -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    // Deferred: the chat screen is still closing while the command runs.
                    minecraft.tell(() -> minecraft.setScreen(new DialogueCardPreviewScreen()));
                    return 1;
                }));
    }
}
