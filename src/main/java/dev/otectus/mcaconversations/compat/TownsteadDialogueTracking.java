package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;

/**
 * Tells Townstead when a typed-chat conversation opens and closes (Townstead spec §12.5), so its
 * {@code in_dialogue_with_player} and {@code dialogue_just_ended} tags are true for chat as well as
 * for its own dialogue screen.
 *
 * <p>One open villager per player. Opening a second closes the first; opening the same one again, or
 * closing one that is not open, does nothing. No packet is sent: the bridge calls Townstead's server
 * tracker directly. Entities are held weakly, so a villager or player that unloads is not kept alive
 * by a conversation nobody closed.
 */
public final class TownsteadDialogueTracking {

    private record Open(UUID villagerId, WeakReference<Entity> villager, WeakReference<ServerPlayer> player) {
    }

    private static final Map<UUID, Open> OPEN = new ConcurrentHashMap<>();

    private TownsteadDialogueTracking() {
    }

    /** This player is now talking to this villager in chat. */
    public static void open(ServerPlayer player, Entity villager) {
        if (player == null || villager == null || !Townstead.dialogueTrackingEnabled()
                || !Townstead.has(TownsteadCapability.TRACK_DIALOGUE)) {
            return;
        }
        Open current = OPEN.get(player.getUUID());
        if (current != null && current.villagerId().equals(villager.getUUID())) {
            return;
        }
        if (current != null) {
            close(player.getUUID());
        }
        OPEN.put(player.getUUID(), new Open(villager.getUUID(), new WeakReference<>(villager),
                new WeakReference<>(player)));
        try {
            Townstead.bridge().dialogueOpen(villager, player, villager.level().getGameTime());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead dialogueOpen failed", t);
        }
    }

    /** This player's chat conversation, if any, has ended. */
    public static void close(UUID playerId) {
        Open open = playerId == null ? null : OPEN.remove(playerId);
        if (open == null) {
            return;
        }
        Entity villager = open.villager().get();
        ServerPlayer player = open.player().get();
        if (villager == null || player == null) {
            return;
        }
        try {
            Townstead.bridge().dialogueClose(villager, player, villager.level().getGameTime());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead dialogueClose failed", t);
        }
    }

    /**
     * Closes every conversation the predicate says has lapsed — a sticky window that ran out without a
     * farewell, for one. Called on a slow cadence; the predicate gets the player and villager ids.
     */
    public static void sweep(BiPredicate<UUID, UUID> lapsed) {
        for (Map.Entry<UUID, Open> entry : OPEN.entrySet()) {
            Open open = entry.getValue();
            if (open.villager().get() == null || open.player().get() == null
                    || lapsed.test(entry.getKey(), open.villagerId())) {
                close(entry.getKey());
            }
        }
    }

    /** The villager this player is tracked as talking to, for diagnostics and tests. */
    public static java.util.Optional<UUID> openWith(UUID playerId) {
        return java.util.Optional.ofNullable(OPEN.get(playerId)).map(Open::villagerId);
    }

    /** Server stop: forget everything without calling out, since Townstead is stopping too. */
    public static void reset() {
        OPEN.clear();
    }
}
