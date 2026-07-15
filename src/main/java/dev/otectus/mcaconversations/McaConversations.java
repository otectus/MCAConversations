package dev.otectus.mcaconversations;

import com.mojang.logging.LogUtils;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.QuestsBridge;
import dev.otectus.mcaconversations.compat.SeasonsBridge;
import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import dev.otectus.mcaconversations.network.ConversationsNetwork;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * Main entrypoint for MCA: Conversations.
 *
 * <p>Deeper, less repetitive villager conversations for Minecraft Comes Alive: Reborn —
 * heart-gated personal topics, per-player conversation memory, gift gratitude, personalized
 * (templated) lines, and village gossip. Dialogue content ships as datapack JSON merged into MCA's
 * own dialogue tree; the Java side contributes custom dialogue conditions/actions registered into
 * MCA's public registries plus the gossip/gift subsystems. Every MCA Reborn call is isolated behind
 * {@code dev.otectus.mcaconversations.compat} so MCA internal API changes only ever require edits in one
 * place.
 */
@Mod(McaConversations.MOD_ID)
public final class McaConversations {

    public static final String MOD_ID = "mcaconversations";
    public static final Logger LOGGER = LogUtils.getLogger();

    public McaConversations() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, McaConversationsConfig.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, McaConversationsConfig.CLIENT_SPEC);

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::onCommonSetup);
        modBus.addListener(ConversationsCapabilities::onRegisterCapabilities);

        // The chat-mode typing-attention channel (one C2S ping; server re-validates everything).
        ConversationsNetwork.register();

        LOGGER.info("MCA: Conversations initialising (mod id '{}')", MOD_ID);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        // Register our conversations_* dialogue conditions/actions into MCA's static registries on the
        // main thread, before any datapack reload parses dialogue JSON that references them.
        event.enqueueWork(McaBridge::tryRegister);
        // Then wire the optional MCA: Quests integration (no-op when that mod is absent). Ordered after
        // MCA so our quest-aware conditions exist first; we load after Quests so its registries are present.
        event.enqueueWork(QuestsBridge::tryRegister);
        // And the optional Serene Seasons integration (reflection-only; calendar fallback when absent).
        event.enqueueWork(SeasonsBridge::tryRegister);
    }
}
