package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.fml.ModList;

/**
 * The classloading gate in front of everything MCA: Quests-shaped — the exact sibling of
 * {@link McaBridge}. This class has <b>no</b> {@code dev.otectus.mcaquests.*} imports;
 * {@code compat.quests.ConversationsQuestsCompat} (which does) is only <em>named</em> here, so the JVM does
 * not load any Quests class until after the {@link ModList#isLoaded} check, and {@code catch (Throwable)}
 * additionally absorbs {@code NoClassDefFoundError}/{@code NoSuchMethodError} from API drift.
 *
 * <p>MCA: Quests is an <b>optional</b> integration: when it is absent, {@link #isAvailable()} stays
 * {@code false}, the quest-aware dialogue conditions return a neutral score, the {@code conversations_quest_open}
 * action is a no-op, and no objective/reward/resolver/event subscriber is ever registered. Conversations then
 * behaves exactly as it does without Quests.
 *
 * <p>Everything that reaches into Quests must consult {@link #isAvailable()} first.
 */
public final class QuestsBridge {

    private static volatile boolean available = false;

    /**
     * The Quests query façade, or {@code null} until (and unless) MCA: Quests registers it. This is the
     * SPI seam that keeps the classloading gate intact: the interface uses only Minecraft types, so the
     * MCA-importing {@code compat.mca.ConversationsMcaRegistrar} can call it without ever forcing a
     * {@code dev.otectus.mcaquests.*} class to load on an MCA-only install. The implementing class (which
     * does import Quests) is only instantiated inside {@link #tryRegister()}, after the mod-present check.
     */
    private static volatile QuestQueries queries;

    private QuestsBridge() {
    }

    /**
     * Everything Conversations asks of MCA: Quests, expressed in pure Minecraft types. Implemented by
     * {@code compat.quests.ConversationsQuestsCompat} and installed via {@link #setQueries}. Every method must
     * fail safe (documented default), because callers may invoke it during MCA dialogue evaluation.
     */
    public interface QuestQueries {
        /** True when {@code villager} currently has an eligible quest offer for {@code player}. */
        boolean hasEligibleOffer(ServerPlayer player, Entity villager);

        /** Whether the player has {@code >= min} active quests — from this villager only, or anywhere. */
        boolean hasActive(ServerPlayer player, Entity villager, boolean thisVillagerOnly, int min);

        /** Whether the player has a ready-to-turn-in quest — at this villager, or anywhere. */
        boolean hasReadyTurnIn(ServerPlayer player, Entity villager, boolean thisVillagerOnly);

        /** Count of quests the player completed — for this villager, or in total. */
        int completedCount(ServerPlayer player, Entity villager, boolean thisVillagerOnly);

        /** Opens this villager's Quests menu for the player (server-side). */
        void openMenu(ServerPlayer player, Entity villager);

        /** Accepts a specific quest from this villager directly (server-side). */
        void accept(ServerPlayer player, Entity villager, String questId);

        /** Signals that the player just talked to this villager about {@code topic} (drives talk_about objectives). */
        void signalTopicTalked(ServerPlayer player, Entity villager, String topic);
    }

    /** Installs the query façade (called by {@code ConversationsQuestsCompat.register()} when Quests is present). */
    public static void setQueries(QuestQueries impl) {
        queries = impl;
    }

    /** The query façade, or {@code null} when MCA: Quests is absent — callers must null-check. */
    public static QuestQueries queries() {
        return queries;
    }

    /** True once MCA: Quests is confirmed present and our integration registered successfully. */
    public static boolean isAvailable() {
        return available;
    }

    /**
     * Called from {@code FMLCommonSetupEvent.enqueueWork}, after {@link McaBridge#tryRegister()} — so our
     * quest-aware MCA conditions/actions register first. We load AFTER MCA: Quests (mods.toml ordering),
     * so its objective/reward registries and dialogue-hook API are already present here.
     */
    public static void tryRegister() {
        if (!ModList.get().isLoaded("mcaquests")) {
            McaConversations.LOGGER.info("MCA: Quests not present; Conversations quest integration disabled.");
            available = false;
            return;
        }
        try {
            // Named as a string, never referenced directly: a direct call would put every
            // MCA: Quests type that class mentions into this class's constant pool, and this
            // class loads everywhere. It also lets a core-only build (no sibling classes on
            // the compile path) drop the Quests adapter without breaking this bridge.
            Class.forName("dev.otectus.mcaconversations.compat.quests.ConversationsQuestsCompat")
                    .getMethod("register")
                    .invoke(null);
            available = true;
            McaConversations.LOGGER.info("MCA: Quests detected; Conversations quest integration registered.");
        } catch (Throwable t) {
            available = false;
            McaConversations.LOGGER.error(
                    "Failed to register MCA: Quests integration; quest features disabled. "
                            + "This usually means an incompatible MCA: Quests version.", t);
        }
    }
}
