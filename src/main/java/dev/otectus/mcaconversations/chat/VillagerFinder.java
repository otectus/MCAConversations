package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    /**
     * The same candidate with no entity attached: identity and geometry only, which is everything
     * ranking needs and nothing that requires a running server.
     */
    record Ranked(UUID id, String name, double distSqr, double lookDot) {
    }

    static final int MAX_CANDIDATES = 16;

    private VillagerFinder() {
    }

    public static List<VillagerCandidate> candidates(ServerPlayer player, double radius) {
        Vec3 look = player.getViewVector(1.0f).normalize();
        Vec3 eye = player.getEyePosition();
        // The AABB is the cheap broad phase and stays inflated by the radius; rank() applies the
        // sphere. Without it a villager standing at a corner of the box — up to sqrt(3)·radius away —
        // counted as being within "radius" and could win the addressing contest.
        List<LivingEntity> found = player.serverLevel().getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(radius),
                e -> McaCompat.isMcaVillager(e) && e.isAlive() && !e.isSleeping());
        Map<UUID, Entity> byId = new HashMap<>(found.size());
        List<Ranked> gathered = new ArrayList<>(found.size());
        for (LivingEntity e : found) {
            byId.put(e.getUUID(), e);
            gathered.add(new Ranked(
                    e.getUUID(),
                    McaCompat.getVillagerName(e).orElse(""),
                    e.distanceToSqr(player),
                    lookDot(look, eye, e.position().add(0.0, e.getEyeHeight(), 0.0))));
        }
        List<Ranked> ranked = rank(gathered, radius * radius, MAX_CANDIDATES);
        List<VillagerCandidate> out = new ArrayList<>(ranked.size());
        for (Ranked r : ranked) {
            out.add(new VillagerCandidate(byId.get(r.id()), r.name(), r.distSqr(), r.lookDot()));
        }
        return out;
    }

    /**
     * Pure ranking: keep only what is genuinely inside the sphere of {@code radiusSqr}, nearest first,
     * capped at {@code maxCandidates}. Ties break by name and then by id so the order does not depend
     * on the order the level happened to hand the entities back in.
     */
    static List<Ranked> rank(List<Ranked> gathered, double radiusSqr, int maxCandidates) {
        return gathered.stream()
                .filter(c -> c.distSqr() <= radiusSqr)
                .sorted(Comparator.comparingDouble(Ranked::distSqr)
                        .thenComparing(Ranked::name)
                        .thenComparing(c -> c.id() == null ? "" : c.id().toString()))
                .limit(maxCandidates)
                .toList();
    }

    /** Cosine of the angle between the player's view vector and the direction to {@code target}. */
    static double lookDot(Vec3 look, Vec3 eye, Vec3 target) {
        Vec3 toTarget = target.subtract(eye);
        double lenSqr = toTarget.lengthSqr();
        if (lenSqr < 1.0e-6) {
            return 1.0;
        }
        return look.dot(toTarget.scale(1.0 / Math.sqrt(lenSqr)));
    }
}
