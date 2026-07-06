package dev.otectus.mcarealtalk.gift;

import dev.otectus.mcarealtalk.McaRealTalk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

import java.util.Optional;

/** Holds the {@link GiftMemoryData} capability token and registration. */
public final class RealTalkCapabilities {

    public static final Capability<GiftMemoryData> GIFT_MEMORY =
            CapabilityManager.get(new CapabilityToken<>() {
            });

    public static final ResourceLocation ID = new ResourceLocation(McaRealTalk.MOD_ID, "gift_memory");

    private RealTalkCapabilities() {
    }

    /** Registered on the mod event bus. */
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(GiftMemoryData.class);
    }

    public static Optional<GiftMemoryData> get(Player player) {
        return player.getCapability(GIFT_MEMORY).resolve();
    }
}
