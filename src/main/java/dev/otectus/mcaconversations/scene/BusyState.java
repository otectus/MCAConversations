package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;
import java.util.UUID;

/**
 * What a villager is in the middle of, as far as anything here can actually observe (spec §11.2).
 *
 * <p>The plan lists the states that must suppress ordinary initiative: panicking, fighting, sleeping,
 * pathing to safety, trading with someone else, and performing a time-critical chore. This enum is
 * the observable half of that list. Pathing to safety is not separable from panicking through MCA's
 * surface, so it is folded into it; "time-critical" is not separable from any other chore, so a chore
 * in progress is treated as a soft state rather than pretended to be readable.
 *
 * <p><b>Hard states</b> refuse everything except an acute scene. Somebody asleep, fighting, in fear
 * for their life, or in another player's interaction screen is not available for conversation, and a
 * mod that talked to them anyway would be the one breaking the fiction.
 *
 * <p><b>Soft states</b> refuse only the initiatives that were expensive to begin with. A grieving
 * villager can still be told a promise has come due; being asked their opinion of a door hinge is a
 * different matter. The line is drawn at {@link #SOFT_SUPPRESSION_THRESHOLD} on the purpose's own
 * interruption cost, so it moves with the scene taxonomy rather than with a list kept in two places.
 */
public enum BusyState {

    /** Nothing in the way. */
    NONE("none", false),

    /** Running from something. Covers pathing to safety, which MCA does not expose separately. */
    PANICKING("panicking", true),

    /** In a fight. */
    FIGHTING("fighting", true),

    /** Asleep. */
    SLEEPING("sleeping", true),

    /** In someone else's interaction screen. Their conversation, not this player's. */
    WITH_ANOTHER_PLAYER("with_another_player", true),

    /** Bereaved. Not an emergency, and not a moment for small talk either. */
    GRIEVING("grieving", false),

    /** Part-way through an assigned chore. */
    ON_A_CHORE("on_a_chore", false);

    /**
     * Interruption cost at or above which a soft state refuses an initiative.
     *
     * <p>Five sits between a shared event (4) and an opinion request (6), which is the line the plan
     * implies: news worth telling can be told to somebody weeding, and "tell me what you think of
     * this handle" can wait until they are not.
     */
    public static final int SOFT_SUPPRESSION_THRESHOLD = 5;

    private final String key;
    private final boolean hard;

    BusyState(String key, boolean hard) {
        this.key = key;
        this.hard = hard;
    }

    public String key() {
        return key;
    }

    public boolean isHard() {
        return hard;
    }

    public boolean isBusy() {
        return this != NONE;
    }

    /**
     * True when this state should refuse {@code purpose}.
     *
     * <p>An acute scene passes everything, which is the one exception §11.1 grants by name: the point
     * of "you're bleeding, sit down" is that it is worth interrupting anything for.
     */
    public boolean suppresses(ScenePurpose purpose) {
        if (!isBusy() || purpose == null || !purpose.isInitiative()
                || purpose.overridesBusyState()) {
            return false;
        }
        return hard || purpose.interruptionCost() >= SOFT_SUPPRESSION_THRESHOLD;
    }

    /**
     * What this villager is in the middle of, from the player's point of view.
     *
     * <p>Never throws. This runs on the initiative path, where a villager whose state cannot be read
     * should simply be left alone rather than take an exception into a tick handler — so anything
     * unreadable reports {@link #NONE} and the ordinary caps still apply.
     */
    public static BusyState of(Entity villager, UUID player) {
        if (villager == null) {
            return NONE;
        }
        try {
            if (McaCompat.isPanicking(villager)) {
                return PANICKING;
            }
            if (villager instanceof Mob mob && mob.getTarget() != null && mob.getTarget().isAlive()) {
                return FIGHTING;
            }
            if (villager instanceof LivingEntity living && living.isSleeping()) {
                return SLEEPING;
            }
            Optional<UUID> interacting = McaCompat.isInteractingWith(villager);
            if (interacting.isPresent() && !interacting.get().equals(player)) {
                return WITH_ANOTHER_PLAYER;
            }
            if (McaCompat.isGrieving(villager)) {
                return GRIEVING;
            }
            String chore = McaCompat.getCurrentChore(villager).orElse("none");
            if (!chore.isBlank() && !"none".equals(chore)) {
                return ON_A_CHORE;
            }
        } catch (Throwable t) {
            return NONE;
        }
        return NONE;
    }

    /** Convenience for the common case of a player entity. */
    public static BusyState of(Entity villager, Player player) {
        return of(villager, player == null ? null : player.getUUID());
    }
}
