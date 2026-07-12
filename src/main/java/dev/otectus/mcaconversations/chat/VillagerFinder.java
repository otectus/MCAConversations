package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

/**
 * Proximity query for chat-mode targeting: the loaded, awake MCA villagers within a radius of a player,
 * nearest first. There is no existing spatial utility in the mod, so this is the one AABB query chat
 * mode runs per processed message (chat is a low-frequency event, so the cost is negligible).
 *
 * <p>MCA types stay behind {@code compat/}: candidates carry only the vanilla {@link Entity} plus the
 * name/geometry the pure {@code Addressing} layer needs.
 */
public final class VillagerFinder {

    /** A nearby villager and the geometry chat-mode addressing scores it by. */
    public record VillagerCandidate(Entity entity, String name, double distSqr, double lookDot) {
    }

    private static final int MAX_CANDIDATES = 16;

    private VillagerFinder() {
    }

    public static List<VillagerCandidate> candidates(ServerPlayer player, double radius) {
        Vec3 look = player.getViewVector(1.0f).normalize();
        Vec3 eye = player.getEyePosition();
        return player.serverLevel().getEntitiesOfClass(
                        LivingEntity.class,
                        player.getBoundingBox().inflate(radius),
                        e -> McaCompat.isMcaVillager(e) && e.isAlive() && !e.isSleeping())
                .stream()
                .map(e -> new VillagerCandidate(
                        e,
                        McaCompat.getVillagerName(e).orElse(""),
                        e.distanceToSqr(player),
                        lookDot(look, eye, e)))
                .sorted(Comparator.comparingDouble(VillagerCandidate::distSqr))
                .limit(MAX_CANDIDATES)
                .toList();
    }

    /** Cosine of the angle between the player's view vector and the direction to the villager's eyes. */
    private static double lookDot(Vec3 look, Vec3 eye, Entity villager) {
        Vec3 toVillager = villager.position().add(0.0, villager.getEyeHeight(), 0.0).subtract(eye);
        double lenSqr = toVillager.lengthSqr();
        if (lenSqr < 1.0e-6) {
            return 1.0;
        }
        return look.dot(toVillager.scale(1.0 / Math.sqrt(lenSqr)));
    }
}
