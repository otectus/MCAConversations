package dev.otectus.mcaconversations;

import com.mojang.logging.LogUtils;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.QuestsBridge;
import dev.otectus.mcaconversations.compat.SeasonsBridge;
import dev.otectus.mcaconversations.gift.ConversationsAttachments;
import dev.otectus.mcaconversations.network.ConversationsNetwork;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
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

    public McaConversations(IEventBus modBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, McaConversationsConfig.COMMON_SPEC);
        // SERVER: gameplay values the server decides and Forge synchronises to every client, stored
        // per world under serverconfig/. COMMON is loaded on both sides and synchronised on neither,
        // so anything a client must agree with the server about belongs here instead.
        container.registerConfig(ModConfig.Type.SERVER, McaConversationsConfig.SERVER_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, McaConversationsConfig.CLIENT_SPEC);

        modBus.addListener(this::onCommonSetup);

        // Gift memory and the chat-mode opt-in are NeoForge data attachments now, not Forge
        // capabilities: no provider, no LazyOptional, no attach event, and copyOnDeath replaces
        // the old PlayerEvent.Clone copy.
        ConversationsAttachments.REGISTER.register(modBus);

        // The chat-mode typing-attention payload (one C2S ping; the server re-validates
        // everything). This has to be a mod-bus listener — registering a payload later, from
        // common setup's enqueueWork the way the Forge SimpleChannel did, throws on NeoForge.
        modBus.addListener(ConversationsNetwork::onRegisterPayloads);

        LOGGER.info("MCA: Conversations initialising (mod id '{}')", MOD_ID);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        // Register our conversations_* dialogue conditions/actions into MCA's static registries on the
        // main thread, before any datapack reload parses dialogue JSON that references them.
        event.enqueueWork(McaBridge::tryRegister);
        // Then wire the optional MCA: Quests integration (no-op when that mod is absent). Ordered after
        // MCA so our quest-aware conditions exist first; we load after Quests so its registries are present.
        event.enqueueWork(QuestsBridge::tryRegister);
        // And the optional MCA: Reputation integration (no-op when that mod is absent). Ordered after
        // MCA for the same reason as Quests: our reputation-aware conditions are registered by the MCA
        // registrar and must exist before any dialogue JSON referencing them is parsed.
        event.enqueueWork(dev.otectus.mcaconversations.compat.ReputationBridge::tryRegister);
        // And the optional Serene Seasons integration (reflection-only; calendar fallback when absent).
        event.enqueueWork(SeasonsBridge::tryRegister);
        // Last, and by name: the Townstead implementation is reached through a string so no
        // Townstead class resolves on the many installs that do not have it. Must follow
        // McaBridge, because the spirit read needs an MCA village that only McaCompat produces.
        event.enqueueWork(dev.otectus.mcaconversations.compat.TownsteadCompat::init);
    }
}
