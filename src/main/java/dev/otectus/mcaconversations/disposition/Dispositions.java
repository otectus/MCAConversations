package dev.otectus.mcaconversations.disposition;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.interiority.Interiority;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Map;

/**
 * The facade every dialogue adapter goes through to read or move the disposition vector. Resolves
 * the world store, the game-time clock, config multipliers, and the personality baseline; guards the
 * Attraction axis behind {@link McaCompat#isRomanceEligible} on both the read and write paths (in
 * addition to the JSON/adapter gates — romance gating is layered, never single-point). Everything
 * fails safe to the baseline / a no-op.
 *
 * <p>Baselines come from the reloadable per-personality interiority registry; this is the single seam
 * where they plug in.
 */
public final class Dispositions {

    private Dispositions() {
    }

    public static boolean enabled() {
        return McaConversationsConfig.COMMON.enableDispositions.get();
    }

    /**
     * The personality resting baseline for an axis: where a villager of this personality sits before
     * the player has done anything at all. Read from the reloadable interiority registry, so a
     * personality with no profile is simply neutral and a pack can retune one without touching Java.
     */
    public static int baseline(Entity villager, DispositionAxis axis) {
        if (villager == null || axis == null) {
            return 0;
        }
        try {
            return Interiority.baseline(villager, axis);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("interiority baseline read failed; defaulting neutral", t);
            return 0;
        }
    }

    /**
     * The decayed axis value dialogue conditions and checks consume. Returns the baseline when the
     * subsystem is off, the store is unreachable, or the record does not exist (pre-1.0 saves); the
     * Attraction axis reads 0 for ineligible targets no matter what is stored.
     */
    public static int axis(Entity villager, ServerPlayer player, DispositionAxis axis) {
        if (villager == null || player == null) {
            return 0;
        }
        if (axis == DispositionAxis.ATTRACTION && !McaCompat.isRomanceEligible(villager, player)) {
            return 0;
        }
        int baseline = baseline(villager, axis);
        if (!enabled()) {
            return baseline;
        }
        try {
            MinecraftServer server = player.getServer();
            if (server == null) {
                return baseline;
            }
            int value = DispositionSavedData.get(server).readAxis(villager.getUUID(), player.getUUID(),
                    axis, baseline, villager.level().getGameTime(),
                    McaConversationsConfig.dispositionDecayMultiplier());
            if (McaConversationsConfig.COMMON.debugRpg.get()) {
                McaConversations.LOGGER.info("[rpg] read {}={} villager={} player={}",
                        axis.key(), value, villager.getUUID(), player.getName().getString());
            }
            return value;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("disposition read failed; defaulting baseline", t);
            return baseline;
        }
    }

    /**
     * The guarded write path for {@code conversations_disposition_apply}. Attraction deltas are
     * silently dropped for ineligible targets; the whole call is a no-op when the subsystem is off.
     */
    public static void apply(Entity villager, ServerPlayer player, DispositionApply directive) {
        if (villager == null || player == null || directive == null || !enabled()) {
            return;
        }
        try {
            MinecraftServer server = player.getServer();
            if (server == null) {
                return;
            }
            Map<DispositionAxis, Integer> deltas = directive.deltas();
            if (deltas.containsKey(DispositionAxis.ATTRACTION)
                    && !McaCompat.isRomanceEligible(villager, player)) {
                deltas = new java.util.EnumMap<>(deltas);
                deltas.remove(DispositionAxis.ATTRACTION);
            }
            if (deltas.isEmpty()) {
                return;
            }
            Map<DispositionAxis, Integer> applied = DispositionSavedData.get(server).apply(
                    villager.getUUID(), player.getUUID(), directive.topic(), deltas,
                    axis -> baseline(villager, axis), villager.level().getGameTime(),
                    McaConversationsConfig.dispositionDailyAxisCap(),
                    McaConversationsConfig.dispositionGainMultiplier(),
                    McaConversationsConfig.dispositionDecayMultiplier());
            if (McaConversationsConfig.COMMON.debugRpg.get()) {
                McaConversations.LOGGER.info("[rpg] apply topic={} requested={} applied={} villager={} player={}",
                        directive.topic(), directive.deltas(), applied, villager.getUUID(),
                        player.getName().getString());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("disposition apply failed; ignoring", t);
        }
    }
}
