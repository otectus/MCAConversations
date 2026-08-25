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
 * Proactive greeting (spec §15 Phase 4, {@code chatModeGreetOnApproach}): a villager <em>may</em>
 * greet an opted-in player on the scan tick the player <em>enters</em> its hearing radius — never
 * merely for being inside it (edge detection, so standing in the square doesn't re-trigger).
 *
 * <p>Whether a villager greets is a per-(villager, player, day) coin flip: {@code chatModeGreetChance}
 * scaled by personality (the peppy farmer usually says hi; the shy librarian rarely does), hashed
 * deterministically so leaving and re-entering the radius can never re-roll — but tomorrow's roll is a
 * fresh one. The hail itself is a genuine hello ({@code chatmode.hail} pool, hearts-aware), tracked by
 * its own {@code chatgreet.today} memory so it never consumes the GUI's ask-how-you've-been budget.
 * At most one villager (the nearest eligible) greets per scan per player. Server thread only; scan
 * cadence is owned by the tick handler.
 */
public final class GreetOnApproach {

    /** Daily proactive-hail budget, per villager→player (distinct from the checkin greet.today). */
    private static final String GREET_MEMORY = "mcaconversations.chatgreet.today";

    /** Half a Minecraft day, matching the checkin cooldown convention in {@code greet.json}. */
    private static final long GREET_MEMORY_TICKS = 12000L;

    /** Outgoing personalities greet more, reserved ones less (multiplier on {@code chatModeGreetChance}). */
    private static final Map<String, Double> PERSONALITY_WEIGHT = Map.of(
            "friendly", 1.5, "peppy", 1.5, "flirty", 1.5, "confident", 1.5, "athletic", 1.5,
            "shy", 0.5, "gloomy", 0.5, "grumpy", 0.5, "lazy", 0.5, "odd", 0.5);

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
        double baseChance = McaConversationsConfig.COMMON.chatModeGreetChance.get();
        long gameDay = now / 24000L;

        // Nearest newly-entered villager that may greet; at most one greeter per scan per player.
        for (VillagerCandidate c : candidates) { // already nearest-first
            if (!entered.contains(c.entity().getUUID())) {
                continue;
            }
            if (session != null && session.isMuted(c.entity().getUUID(), now)) {
                continue; // this pairing was told "stop talking" — no proactive greeting either
            }
            String memoryId = MemoryIds.playerScoped(GREET_MEMORY, player.getUUID());
            if (McaCompat.hasMemory(c.entity(), memoryId)) {
                continue; // already hailed this player today
            }
            double weight = personalityWeight(McaCompat.getPersonality(c.entity()).orElse(""));
            if (!rollGreet(c.entity().getUUID(), player.getUUID(), gameDay, baseChance * weight)) {
                continue; // not the greeting type today — no memory spent, tomorrow re-rolls
            }
            Optional<UUID> interacting = McaCompat.isInteractingWith(c.entity());
            if (interacting.isPresent() && !interacting.get().equals(player.getUUID())) {
                continue; // busy in another player's GUI
            }
            ChatModeDispatcher.proactiveGreet(c, player, now);
            McaCompat.remember(c.entity(), memoryId, GREET_MEMORY_TICKS);
            return;
        }
    }

    /** Multiplier on the greet chance for this personality (1.0 for unknown/neutral ones). */
    static double personalityWeight(String personality) {
        return PERSONALITY_WEIGHT.getOrDefault(personality, 1.0);
    }

    /**
     * Pure, deterministic per-(villager, player, day) greet roll: a splitmix64-style hash mapped to
     * [0,1) and compared to {@code effectiveChance}. Same inputs → same answer all day (leaving and
     * re-entering the radius cannot re-roll); a new day is a fresh flip.
     */
    static boolean rollGreet(UUID villager, UUID player, long gameDay, double effectiveChance) {
        if (effectiveChance >= 1.0) {
            return true;
        }
        if (effectiveChance <= 0.0) {
            return false;
        }
        long h = villager.getMostSignificantBits()
                ^ Long.rotateLeft(villager.getLeastSignificantBits(), 17)
                ^ Long.rotateLeft(player.getMostSignificantBits(), 31)
                ^ player.getLeastSignificantBits()
                ^ (gameDay * 0x9E3779B97F4A7C15L);
        h += 0x9E3779B97F4A7C15L;
        h = (h ^ (h >>> 30)) * 0xBF58476D1CE4E5B9L;
        h = (h ^ (h >>> 27)) * 0x94D049BB133111EBL;
        h ^= (h >>> 31);
        double unit = (h >>> 11) * 0x1.0p-53; // uniform [0,1)
        return unit < effectiveChance;
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
