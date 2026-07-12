package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModePlayerState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

import java.util.Optional;

/** Holds this mod's per-player capability tokens and registration. */
public final class ConversationsCapabilities {

    public static final Capability<GiftMemoryData> GIFT_MEMORY =
            CapabilityManager.get(new CapabilityToken<>() {
            });

    public static final Capability<ChatModePlayerState> CHAT_MODE =
            CapabilityManager.get(new CapabilityToken<>() {
            });

    public static final ResourceLocation ID = new ResourceLocation(McaConversations.MOD_ID, "gift_memory");

    public static final ResourceLocation CHAT_MODE_ID = new ResourceLocation(McaConversations.MOD_ID, "chat_mode");

    private ConversationsCapabilities() {
    }

    /** Registered on the mod event bus. */
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(GiftMemoryData.class);
        event.register(ChatModePlayerState.class);
    }

    public static Optional<GiftMemoryData> get(Player player) {
        return player.getCapability(GIFT_MEMORY).resolve();
    }

    public static Optional<ChatModePlayerState> getChatMode(Player player) {
        return player.getCapability(CHAT_MODE).resolve();
    }
}
