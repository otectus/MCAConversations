package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.Townstead;
import dev.otectus.mcaconversations.compat.TownsteadCapability;
import dev.otectus.mcaconversations.compat.TownsteadNeedsView;
import dev.otectus.mcaconversations.conversation.ConversationOutcomes;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ReactionSemantic;
import dev.otectus.mcaconversations.state.MemoryIds;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.lang.ref.WeakReference;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Whether an accepted gift actually helped, judged by Townstead rather than guessed (Townstead spec §14).
 *
 * <p>MCA owns acceptance and hearts, and Townstead owns whether food or drink changes a need and by
 * how much. Conversations never fills a need. It reads the needs as the gift is accepted, reads them
 * again a tick later, and only when Townstead's own value improved does it leave a short memory a
 * gratitude line may consult — and play a heart-neutral grateful reaction. A gift that changed nothing
 * claims nothing, and no heart is ever added for it.
 */
public final class GiftNeedObservation {

    /** What an accepted gift was seen to relieve. */
    public enum Relief {
        HUNGER("relieved_hunger"),
        THIRST("relieved_thirst"),
        RECOVERY("helped_recovery");

        private final String key;

        Relief(String key) {
            this.key = key;
        }

        /** The player-scoped villager memory, {@code mcaconversations.gift.<key>}. */
        public String memoryId() {
            return MemoryIds.PREFIX + "gift." + key;
        }
    }

    /** How long "you brought me food when I was starving" stays fresh: a quarter of a day. */
    static final long MEMORY_TICKS = 6000L;

    /** Villager and player with an observation already queued, so a doubled hook observes once. */
    private static final Map<String, Long> PENDING = new ConcurrentHashMap<>();

    private GiftNeedObservation() {
    }

    /** Called as MCA accepts a gift. Queues the second read for the next tick. */
    public static void onAccepted(Entity villager, ServerPlayer player) {
        if (villager == null || player == null || !Townstead.giftNeedObservationEnabled()
                || !Townstead.has(TownsteadCapability.READ_NEEDS)) {
            return;
        }
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }
        long now = server.overworld().getGameTime();
        String key = villager.getUUID() + "|" + player.getUUID();
        Long pending = PENDING.get(key);
        if (pending != null && pending >= now) {
            return;
        }
        TownsteadNeedsView before;
        try {
            before = Townstead.bridge().villager(villager).needs();
        } catch (Throwable t) {
            return;
        }
        PENDING.put(key, now + 1);
        WeakReference<Entity> villagerRef = new WeakReference<>(villager);
        WeakReference<ServerPlayer> playerRef = new WeakReference<>(player);
        ChatModeScheduler.schedule(now + 1, () -> {
            PENDING.remove(key, now + 1);
            observe(villagerRef.get(), playerRef.get(), before);
        });
    }

    private static void observe(Entity villager, ServerPlayer player, TownsteadNeedsView before) {
        // Unloaded, dead, or a player gone in the tick between: nothing is claimed.
        if (villager == null || player == null || villager.isRemoved() || !villager.isAlive()
                || player.hasDisconnected()) {
            return;
        }
        try {
            Set<Relief> relieved = compare(before, Townstead.bridge().villager(villager).needs());
            for (Relief relief : relieved) {
                McaCompat.remember(villager, MemoryIds.playerScoped(relief.memoryId(), player.getUUID()), MEMORY_TICKS);
            }
            if (!relieved.isEmpty()) {
                ConversationOutcomes.react(villager, player, ReactionSemantic.GRATEFUL, ConversationSession.Frontend.GUI);
            }
            if (Townstead.debug()) {
                McaConversations.LOGGER.info("[townstead] gift to {} relieved {}", villager.getUUID(), relieved);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead gift observation failed; nothing claimed", t);
        }
    }

    /**
     * What improved between the two reads, by Townstead's own numbers. Thirst counts only where
     * Townstead is simulating it; recovery is waking from a collapse or a real drop in fatigue.
     */
    public static Set<Relief> compare(TownsteadNeedsView before, TownsteadNeedsView after) {
        Set<Relief> relieved = EnumSet.noneOf(Relief.class);
        if (before == null || after == null) {
            return relieved;
        }
        if (after.hunger() > before.hunger()) {
            relieved.add(Relief.HUNGER);
        }
        if (after.thirstActive() && before.thirstActive() && after.thirst() > before.thirst()) {
            relieved.add(Relief.THIRST);
        }
        if (before.collapsed() && !after.collapsed() || after.fatigue() < before.fatigue()) {
            relieved.add(Relief.RECOVERY);
        }
        return relieved;
    }

    /** Server stop. */
    public static void reset() {
        PENDING.clear();
    }

    /** Test seam. */
    static boolean pending(UUID villager, UUID player) {
        return PENDING.containsKey(villager + "|" + player);
    }
}
