package dev.otectus.mcaconversations.chat;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

/**
 * An immutable snapshot of a chat message at the moment it was accepted, taken on the event thread
 * before the hop to the server thread.
 *
 * <p>{@code ServerChatEvent} fires on a background thread and is only valid while Forge is still
 * dispatching it: reading it from a later {@code server.execute} task races the event's own
 * lifecycle and, in the local-chat path, races the cancellation. Everything the pipeline needs is
 * copied here first — by value, so a later mutation of the player, the level, or the event cannot
 * change what was said or where.
 *
 * <p>The position and dimension are recorded because "where the player was when they spoke" is not
 * the same as "where the player is when the villager answers"; the engagement checks on the delivery
 * side are what compare the two.
 */
public record AcceptedChat(UUID playerId, String playerName, String text,
                           ResourceKey<Level> dimension, Vec3 position, long gameTime) {

    /**
     * Snapshots {@code player} together with an already-chosen {@code text}. The caller chooses the
     * text because the two chat paths want different things from the event — see the call sites in
     * {@link ChatModeDispatcher}.
     */
    public static AcceptedChat of(ServerPlayer player, String text, long gameTime) {
        return new AcceptedChat(player.getUUID(), player.getGameProfile().getName(), text,
                player.level().dimension(), player.position(), gameTime);
    }
}
