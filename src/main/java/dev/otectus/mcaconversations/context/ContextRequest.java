package dev.otectus.mcaconversations.context;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Locale;
import java.util.Objects;

/**
 * Who is being asked about, by whom, and why (spec §7.2).
 *
 * <p>{@link #purpose} matters more than it looks. It is part of the director's selection seed, so
 * "which scene opens this topic" and "which initiative is worth interrupting for" derive different
 * choices from the same world state rather than colliding on one hash (spec §9.3). It is also what
 * lets a source skip expensive work: a greeting check does not need the family tree.
 *
 * <p>{@link #volatileOnly} asks the sources for a cheap turn-boundary refresh instead of a full
 * capture. Sources that own no volatile fields return immediately.
 */
public record ContextRequest(Entity villager,
                             ServerPlayer player,
                             String purpose,
                             boolean volatileOnly) {

    /** Selection purposes the mod itself uses. A datapack scene may name its own. */
    public static final String PURPOSE_TOPIC = "topic";
    public static final String PURPOSE_INITIATIVE = "initiative";
    public static final String PURPOSE_GREETING = "greeting";
    public static final String PURPOSE_HUB = "hub";
    public static final String PURPOSE_REFRESH = "refresh";

    public ContextRequest {
        purpose = purpose == null || purpose.isBlank()
                ? PURPOSE_TOPIC
                : purpose.trim().toLowerCase(Locale.ROOT);
    }

    public static ContextRequest of(Entity villager, ServerPlayer player, String purpose) {
        return new ContextRequest(villager, player, purpose, false);
    }

    /** A turn-boundary refresh of the same pair: volatile fields only. */
    public ContextRequest asRefresh() {
        return new ContextRequest(villager, player, PURPOSE_REFRESH, true);
    }

    /** True when both participants are present; sources may assume this much and no more. */
    public boolean isComplete() {
        return villager != null && player != null;
    }

    /** The server, or null when the request was built off-thread or from a test double. */
    public net.minecraft.server.MinecraftServer server() {
        if (player != null && player.getServer() != null) {
            return player.getServer();
        }
        return villager == null ? null : villager.getServer();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ContextRequest other
                && other.villager == villager
                && other.player == player
                && other.volatileOnly == volatileOnly
                && Objects.equals(other.purpose, purpose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(System.identityHashCode(villager), System.identityHashCode(player),
                purpose, volatileOnly);
    }
}
