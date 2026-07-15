package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.ChatModeSession.Session;
import dev.otectus.mcaconversations.chat.VillagerFinder.VillagerCandidate;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.state.MemoryIds;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Proactive greeting (spec §15 Phase 4, {@code chatModeGreetOnApproach}): a villager greets an
 * opted-in player on the scan tick the player <em>enters</em> its hearing radius — never merely for
 * being inside it (edge detection, so standing in the square doesn't re-trigger).
 *
 * <p>The greet drives the ordinary {@code greet/checkin} engine path, so the daily budget is the same
 * {@code mcaconversations.greet.today} cooldown memory the GUI records — a villager who already greeted
 * you (either frontend) today stays quiet. At most one villager (the nearest eligible) greets per scan
 * per player. Server thread only; scan cadence is owned by the tick handler.
 */
public final class GreetOnApproach {

    /** The greet cooldown memory the dialogue itself records (see {@code dialogues/greet.json}). */
    private static final String GREET_MEMORY = "mcaconversations.greet.today";

    /** Villagers each player was already near at the previous scan (edge-detection baseline). */
    private static final Map<UUID, Set<UUID>> INSIDE_LAST_SCAN = new ConcurrentHashMap<>();

    private GreetOnApproach() {
    }

    /** One scan over all online players. Caller gates on the config flags and the tick cadence. */
    public static void scan(MinecraftServer server) {
        double radius = McaConversationsConfig.COMMON.chatModeRadius.get();
        long now = server.overworld().getGameTime();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (player.hasDisconnected() || !player.isAlive() || player.isSpectator()) {
                continue;
            }
            if (!ChatModeDispatcher.isOptedIn(player)) {
                INSIDE_LAST_SCAN.remove(player.getUUID());
                continue;
            }
            scanPlayer(player, radius, now);
        }
    }

    private static void scanPlayer(ServerPlayer player, double radius, long now) {
        List<VillagerCandidate> candidates = VillagerFinder.candidates(player, radius);
        Set<UUID> current = new HashSet<>(candidates.size());
        for (VillagerCandidate c : candidates) {
            current.add(c.entity().getUUID());
        }
        Set<UUID> entered = newlyEntered(INSIDE_LAST_SCAN.get(player.getUUID()), current);
        INSIDE_LAST_SCAN.put(player.getUUID(), current);
        if (entered.isEmpty()) {
            return;
        }

        Session session = ChatModeSession.peek(player.getUUID());

        // Nearest newly-entered villager that may greet; at most one greeter per scan per player.
        for (VillagerCandidate c : candidates) { // already nearest-first
            if (!entered.contains(c.entity().getUUID())) {
                continue;
            }
            if (session != null && session.isMuted(c.entity().getUUID(), now)) {
                continue; // this pairing was told "stop talking" — no proactive greeting either
            }
            if (McaCompat.hasMemory(c.entity(),
                    MemoryIds.playerScoped(GREET_MEMORY, player.getUUID()))) {
                continue; // greeted this player today (either frontend)
            }
            Optional<UUID> interacting = McaCompat.isInteractingWith(c.entity());
            if (interacting.isPresent() && !interacting.get().equals(player.getUUID())) {
                continue; // busy in another player's GUI
            }
            ChatModeDispatcher.proactiveGreet(c, player, now);
            return;
        }
    }

    /**
     * Pure edge detection: the ids in {@code current} that were not in {@code previous} (null previous
     * = first scan for this player: everyone present counts as newly entered, so a fresh login near a
     * villager still gets one greeting attempt).
     */
    static Set<UUID> newlyEntered(Set<UUID> previous, Set<UUID> current) {
        if (current.isEmpty()) {
            return Set.of();
        }
        if (previous == null || previous.isEmpty()) {
            return current;
        }
        Set<UUID> entered = new HashSet<>();
        for (UUID id : current) {
            if (!previous.contains(id)) {
                entered.add(id);
            }
        }
        return entered;
    }

    /** Drops a player's tracking on logout (mirrors {@code ChatModeSession.clear}). */
    public static void clear(UUID playerId) {
        INSIDE_LAST_SCAN.remove(playerId);
    }
}
