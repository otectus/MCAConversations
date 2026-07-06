package dev.otectus.mcarealtalk;

import com.mojang.logging.LogUtils;
import dev.otectus.mcarealtalk.compat.McaBridge;
import dev.otectus.mcarealtalk.gift.RealTalkCapabilities;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * Main entrypoint for MCA: Real Talk.
 *
 * <p>Deeper, less repetitive villager conversations for Minecraft Comes Alive: Reborn —
 * heart-gated personal topics, per-player conversation memory, gift gratitude, personalized
 * (templated) lines, and village gossip. Dialogue content ships as datapack JSON merged into MCA's
 * own dialogue tree; the Java side contributes custom dialogue conditions/actions registered into
 * MCA's public registries plus the gossip/gift subsystems. Every MCA Reborn call is isolated behind
 * {@code dev.otectus.mcarealtalk.compat} so MCA internal API changes only ever require edits in one
 * place.
 */
@Mod(McaRealTalk.MOD_ID)
public final class McaRealTalk {

    public static final String MOD_ID = "mcarealtalk";
    public static final Logger LOGGER = LogUtils.getLogger();

    public McaRealTalk() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, McaRealTalkConfig.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, McaRealTalkConfig.CLIENT_SPEC);

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::onCommonSetup);
        modBus.addListener(RealTalkCapabilities::onRegisterCapabilities);

        LOGGER.info("MCA: Real Talk initialising (mod id '{}')", MOD_ID);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        // Register our realtalk_* dialogue conditions/actions into MCA's static registries on the
        // main thread, before any datapack reload parses dialogue JSON that references them.
        event.enqueueWork(McaBridge::tryRegister);
    }
}
