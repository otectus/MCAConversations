package dev.otectus.mcaconversations.compat.quests;

import dev.otectus.mcaquests.api.event.QuestCompletedEvent;
import dev.otectus.mcaquests.api.event.QuestFailedEvent;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.gossip.GossipEvent;
import dev.otectus.mcaconversations.gossip.GossipEventType;
import dev.otectus.mcaconversations.gossip.GossipSavedData;
import dev.otectus.mcaconversations.state.MemoryIds;
import dev.otectus.mcaconversations.state.StateRules;
import dev.otectus.mcaconversations.state.StateTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

/**
 * Bridges MCA: Quests lifecycle events into Conversations's memory + gossip so a player's quest deeds ripple
 * through village conversation. Registered on {@code NeoForge.EVENT_BUS} <b>only</b> from
 * {@code ConversationsQuestsCompat.register()} (i.e. only when Quests is present) — deliberately NOT an
 * {@code @EventBusSubscriber}, which would force {@code dev.otectus.mcaquests.*} onto the classpath on
 * an MCA-only install and break the standalone case.
 *
 * <p>A completed quest writes a permanent {@code mcaconversations.quest.done.*} flag on the giver (so dialogue
 * can reference "you did that for me") and seeds a {@code QUEST} gossip event other villagers can tell.
 * A failed quest writes the {@code mcaconversations.quest.failed.*} flag only (failures aren't gossiped).
 */
public final class ConversationsQuestsEvents {

    private static final int VILLAGE_SEARCH_RADIUS = 128;

    @SubscribeEvent
    public void onQuestCompleted(QuestCompletedEvent event) {
        handle(event.getPlayer(), event.getVillager(), event.getQuestId(), true);
    }

    @SubscribeEvent
    public void onQuestFailed(QuestFailedEvent event) {
        handle(event.getPlayer(), event.getVillager(), event.getQuestId(), false);
    }

    private void handle(ServerPlayer player, @Nullable Entity villager, ResourceLocation questId, boolean completed) {
        if (!McaConversationsConfig.COMMON.enableQuests.get() || player == null || player.level().isClientSide()) {
            return;
        }
        // Self-complete quests may have no giver; nothing to remember or gossip about then.
        if (villager == null || !McaBridge.isAvailable() || !McaCompat.isMcaVillager(villager)) {
            return;
        }
        try {
            String memId = completed
                    ? MemoryIds.questCompleted(questId.toString())
                    : MemoryIds.questFailed(questId.toString());
            McaCompat.rememberForever(villager, MemoryIds.playerScoped(memId, player.getUUID()));
            StateTracker.apply(villager, player, StateRules.forQuest(completed));
            // §30.4: with MCA: Reputation active, its named quest incident is the canonical story —
            // seeding the generic QUEST event as well would have villagers telling the same deed
            // twice in two voices. Memories and state above still apply either way.
            if (completed && McaConversationsConfig.COMMON.enableGossip.get()
                    && !shouldSuppressGenericQuestGossip()) {
                seedGossip(player, villager);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Quest event -> gossip/memory failed for {}; ignoring", questId, t);
        }
    }

    /**
     * §30.4's duplicate suppression, as its own decision so the regression test can pin it without a
     * live quest event. {@code ReputationBridge} is always-loaded and names no Reputation type, so
     * consulting it here keeps the classloading discipline.
     */
    static boolean shouldSuppressGenericQuestGossip() {
        return dev.otectus.mcaconversations.compat.ReputationBridge.isAvailable();
    }

    /** Seeds a village-scoped QUEST gossip event about the giver, so other residents can tell of it. */
    private void seedGossip(ServerPlayer player, Entity villager) {
        MinecraftServer server = player.getServer();
        if (server == null || !(villager.level() instanceof ServerLevel level)) {
            return;
        }
        OptionalInt villageId = McaCompat.getHomeVillageId(villager);
        if (villageId.isEmpty()) {
            villageId = McaCompat.findNearestVillageId(level, villager.blockPosition(), VILLAGE_SEARCH_RADIUS);
        }
        if (villageId.isEmpty()) {
            return;
        }
        String name = McaCompat.getVillagerName(villager).orElse("");
        long now = level.getGameTime();
        // The giver is the subject (aUuid); other villagers tell it, the giver won't gossip about itself.
        // bUuid/bName stay empty — the quest gossip line references only the giver name (%2$s).
        GossipEvent event = new GossipEvent(UUID.randomUUID(), GossipEventType.QUEST, villageId.getAsInt(), now,
                villager.getUUID(), name, Optional.empty(), "");
        if (GossipSavedData.get(server).addEvent(event, McaConversationsConfig.COMMON.maxEventsPerVillage.get())
                && McaConversationsConfig.COMMON.debugLogging.get()) {
            McaConversations.LOGGER.info("Gossip: quest help for {} ({}) in village {}",
                    name, villager.getUUID(), villageId.getAsInt());
        }
    }
}
