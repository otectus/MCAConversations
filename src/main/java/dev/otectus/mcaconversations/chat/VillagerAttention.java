package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.AttentionLedger.Hold;
import dev.otectus.mcaconversations.chat.AttentionLedger.Source;
import dev.otectus.mcaconversations.chat.VillagerFinder.VillagerCandidate;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Applies {@link AttentionLedger} holds to live villagers each tick: while held, a villager stops
 * walking (walk-target erased + navigation stopped — the brain re-paths only after the hold lapses)
 * and keeps facing its player (brain {@code LOOK_TARGET}, so the vanilla look sink turns the head
 * smoothly). Works on any vanilla {@link Mob}; MCA villagers extend {@code Villager}, so no MCA API
 * is involved.
 *
 * <p><b>Safety valve:</b> a panicking or freshly-hurt villager is left alone — attention must never
 * pin a villager that is fleeing a zombie. Sleeping/removed villagers drop their holds. Server thread
 * only.
 */
public final class VillagerAttention {

    /** How long one typing ping holds attention; the client re-pings while the chat screen is open. */
    static final int TYPING_HOLD_TICKS = 60;

    private static final AttentionLedger LEDGER = new AttentionLedger();
    private static final Map<UUID, Entity> VILLAGERS = new ConcurrentHashMap<>();

    private VillagerAttention() {
    }

    /** Requests attention (see {@link AttentionLedger#hold} for the precedence rules). */
    public static void hold(Entity villager, ServerPlayer player, long untilTick, Source source) {
        if (villager == null || player == null) {
            return;
        }
        VILLAGERS.put(villager.getUUID(), villager);
        LEDGER.hold(villager.getUUID(), player.getUUID(), untilTick, source);
    }

    /** The conversation ended (farewell/mute/shrug): the villager goes back to its day. */
    public static void release(Entity villager) {
        if (villager != null) {
            LEDGER.release(villager.getUUID());
        }
    }

    /** A typing ping from {@code player}: nearby villagers glance over until the pings stop. */
    public static void playerTyping(ServerPlayer player, long now) {
        double radius = McaConversationsConfig.chatModeRadius();
        for (VillagerCandidate c : VillagerFinder.candidates(player, radius)) {
            hold(c.entity(), player, now + TYPING_HOLD_TICKS, Source.TYPING);
        }
    }

    /** The player closed the chat screen: drop their TYPING holds (conversations keep attending). */
    public static void playerStoppedTyping(ServerPlayer player) {
        LEDGER.releaseTyping(player.getUUID());
    }

    /** Logout: drop every hold aimed at the player. */
    public static void clearPlayer(UUID playerId) {
        LEDGER.releasePlayer(playerId);
    }

    /** Server-tick driver: sweep expiries, then pin + face for each live hold. */
    public static void tick(MinecraftServer server, long now) {
        if (!McaConversationsConfig.COMMON.enableChatMode.get()) {
            if (!LEDGER.activeHolds().isEmpty()) {
                LEDGER.clear();
                VILLAGERS.clear();
            }
            return;
        }
        LEDGER.sweep(now);
        if (LEDGER.activeHolds().isEmpty()) {
            if (!VILLAGERS.isEmpty()) {
                VILLAGERS.keySet().retainAll(LEDGER.activeHolds().keySet());
            }
            return;
        }

        List<UUID> drop = new ArrayList<>(0);
        for (Map.Entry<UUID, Hold> e : LEDGER.activeHolds().entrySet()) {
            try {
                Entity villager = VILLAGERS.get(e.getKey());
                ServerPlayer player = server.getPlayerList().getPlayer(e.getValue().playerId());
                if (villager == null || villager.isRemoved() || !villager.isAlive()
                        || player == null || player.hasDisconnected()
                        || !(villager instanceof Mob mob) || mob.isSleeping()) {
                    drop.add(e.getKey());
                    continue;
                }
                Brain<?> brain = mob.getBrain();
                if (mob.hurtTime > 0 || brain.isActive(Activity.PANIC)) {
                    continue; // in danger — never pin a fleeing villager; hold resumes if it survives
                }
                brain.eraseMemory(MemoryModuleType.WALK_TARGET);
                brain.setMemory(MemoryModuleType.LOOK_TARGET, new EntityTracker(player, true));
                mob.getNavigation().stop();
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("villager attention tick failed; dropping hold", t);
                drop.add(e.getKey());
            }
        }
        for (UUID id : drop) {
            LEDGER.release(id);
            VILLAGERS.remove(id);
        }
        VILLAGERS.keySet().retainAll(LEDGER.activeHolds().keySet());
    }

    /** Test/server-stop reset. */
    public static void reset() {
        LEDGER.clear();
        VILLAGERS.clear();
    }
}
