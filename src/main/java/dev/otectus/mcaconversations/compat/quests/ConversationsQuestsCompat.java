package dev.otectus.mcaconversations.compat.quests;

import dev.otectus.mcaquests.api.McaQuestsApi;
import dev.otectus.mcaquests.api.QuestDialogueHooks;
import dev.otectus.mcaquests.quest.QuestDefinition;
import dev.otectus.mcaquests.quest.QuestManager;
import dev.otectus.mcaquests.quest.situation.QuestDefinitions;
import dev.otectus.mcaquests.state.ActiveQuest;
import dev.otectus.mcaquests.state.PlayerQuestData;
import dev.otectus.mcaquests.state.QuestCapabilities;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.QuestsBridge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

import java.util.Optional;

/**
 * The single class that imports {@code dev.otectus.mcaquests.*} — the exact sibling of
 * {@code compat.McaCompat}. Only loaded through {@link QuestsBridge#tryRegister()} (after the
 * mod-present check), so an MCA-only install never touches a Quests class. Implements the pure
 * {@link QuestsBridge.QuestQueries} SPI so the MCA-importing registrar can reach Quests without
 * importing it. Every method fails safe: {@code try/catch (Throwable)} + a documented default, because
 * the query methods are invoked during MCA dialogue evaluation.
 */
public final class ConversationsQuestsCompat implements QuestsBridge.QuestQueries {

    private ConversationsQuestsCompat() {
    }

    /**
     * Sole entry point from {@link QuestsBridge#tryRegister()}: installs the query façade, registers the
     * {@code mcaconversations:talk_about} objective + {@code mcaconversations:unlock_topic} reward into Quests'
     * registries, installs the personality-voice dialogue resolver, and subscribes to quest lifecycle
     * events for gossip/memory.
     */
    public static void register() {
        QuestsBridge.setQueries(new ConversationsQuestsCompat());
        TalkAboutObjective.TYPE = McaQuestsApi.registerObjective(
                new ResourceLocation("mcaconversations", "talk_about"), TalkAboutObjective.CODEC);
        UnlockTopicReward.TYPE = McaQuestsApi.registerReward(
                new ResourceLocation("mcaconversations", "unlock_topic"), UnlockTopicReward.CODEC);
        QuestDialogueHooks.setResolver(new QuestVoiceResolver());
        MinecraftForge.EVENT_BUS.register(new ConversationsQuestsEvents());
        McaConversations.LOGGER.info("MCA: Quests integration: registered talk_about objective, unlock_topic reward, "
                + "voice resolver, and quest-event subscriber.");
    }

    // ------------------------------------------------------------------ QuestQueries (read + drive)

    @Override
    public boolean hasEligibleOffer(ServerPlayer player, Entity villager) {
        try {
            return QuestManager.hasEligibleOffer(player, villager);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Quests hasEligibleOffer failed; defaulting false", t);
            return false;
        }
    }

    @Override
    public boolean hasActive(ServerPlayer player, Entity villager, boolean thisVillagerOnly, int min) {
        try {
            Optional<PlayerQuestData> data = QuestCapabilities.get(player);
            if (data.isEmpty()) {
                return false;
            }
            int count = thisVillagerOnly
                    ? data.get().byVillager(villager.getUUID()).size()
                    : data.get().activeCount();
            return count >= min;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Quests hasActive failed; defaulting false", t);
            return false;
        }
    }

    @Override
    public boolean hasReadyTurnIn(ServerPlayer player, Entity villager, boolean thisVillagerOnly) {
        try {
            if (thisVillagerOnly) {
                return QuestManager.hasReadyTurnIn(player, villager);
            }
            Optional<PlayerQuestData> data = QuestCapabilities.get(player);
            if (data.isEmpty()) {
                return false;
            }
            for (ActiveQuest active : data.get().active()) {
                QuestDefinition base = QuestDefinitions.resolve(active.questId()).orElse(null);
                if (base != null && QuestManager.isComplete(player, active.resolve(base), active)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Quests hasReadyTurnIn failed; defaulting false", t);
            return false;
        }
    }

    @Override
    public int completedCount(ServerPlayer player, Entity villager, boolean thisVillagerOnly) {
        try {
            Optional<PlayerQuestData> data = QuestCapabilities.get(player);
            if (data.isEmpty()) {
                return 0;
            }
            return thisVillagerOnly
                    ? data.get().history().completionCountByGiver(villager.getUUID())
                    : data.get().history().totalCompletions();
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Quests completedCount failed; defaulting 0", t);
            return 0;
        }
    }

    @Override
    public void openMenu(ServerPlayer player, Entity villager) {
        try {
            QuestManager.open(player, villager);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Quests openMenu failed; ignoring", t);
        }
    }

    @Override
    public void accept(ServerPlayer player, Entity villager, String questId) {
        try {
            ResourceLocation id = ResourceLocation.tryParse(questId);
            if (id != null) {
                QuestManager.accept(player, villager, id);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Quests accept({}) failed; ignoring", questId, t);
        }
    }

    @Override
    public void signalTopicTalked(ServerPlayer player, Entity villager, String topic) {
        try {
            ResourceLocation signal = ResourceLocation.tryParse("mcaconversations:" + topic);
            if (signal != null) {
                QuestManager.notifyExternalObjective(player, signal, villager.getUUID());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Quests signalTopicTalked({}) failed; ignoring", topic, t);
        }
    }
}
