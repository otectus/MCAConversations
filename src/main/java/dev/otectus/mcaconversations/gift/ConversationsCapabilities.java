package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

import java.util.Optional;

/** Holds the {@link GiftMemoryData} capability token and registration. */
public final class ConversationsCapabilities {

    public static final Capability<GiftMemoryData> GIFT_MEMORY =
            CapabilityManager.get(new CapabilityToken<>() {
            });

    public static final ResourceLocation ID = new ResourceLocation(McaConversations.MOD_ID, "gift_memory");

    private ConversationsCapabilities() {
    }

    /** Registered on the mod event bus. */
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(GiftMemoryData.class);
    }

    public static Optional<GiftMemoryData> get(Player player) {
        return player.getCapability(GIFT_MEMORY).resolve();
    }
}
