package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.conversation.ConversationHandle;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;

/**
 * What a villager's body does while somebody is talking to them (spec §5.2).
 *
 * <p>The rule the whole class exists to serve is written in the spec as "the meaning of stationary":
 * <b>suppress self-directed locomotion, and nothing else</b>. A held villager does not walk off, does
 * not wander, does not set out for work and does not follow anybody — but they are not invulnerable,
 * they still fall, they are still knocked back, they are never teleported to where they used to be,
 * and a villager pushed out of range by something else simply ends the discussion the ordinary way.
 * That is the difference between a conversation partner who stays put and a statue.
 *
 * <p>Three things do the suppressing, and they are deliberately small:
 * <ul>
 *   <li>the brain's {@code WALK_TARGET} is erased, so nothing it proposed this tick survives;</li>
 *   <li>navigation is stopped, so a path computed before the hold is abandoned;</li>
 *   <li>MCA's own follow-the-player step is cancelled at the source by
 *       {@code InteractTaskMovementMixin}, which asks {@link VillagerAttention#holdsMovement} and so
 *       never touches a villager this mod has no hold on.</li>
 * </ul>
 *
 * <p>Facing is the other half and is kept separate on purpose: {@code LOOK_TARGET} points at the
 * current owner alone, so the vanilla look sink turns the head and body smoothly rather than snapping,
 * and a villager somebody else has since taken over faces the player who actually owns them. Turning
 * {@code holdVillagerDuringInteraction} off gives up movement suppression and keeps exactly this: the
 * villager may wander away, but while they are there they look at you.
 *
 * <p><b>The decision is a pure function.</b> {@link #decide} takes booleans and returns a
 * {@link Stance}; only {@link #apply} touches a {@code Brain}. A unit test cannot prove a villager
 * stopped walking — that needs a client, and the spec says so (§13.1) — but it can prove that a
 * panicking villager is never held, that an attacked one is revoked rather than resumed, and that the
 * config switch demotes a hold to facing. Server thread only.
 */
public final class ConversationMovementController {

    /**
     * How long a refreshed managed hold outlives the tick that booked it.
     *
     * <p>Short and renewed every tick by the lifecycle, so the hold is a lease rather than a timer: a
     * discussion that keeps being judged keeps its villager indefinitely — three minutes of reading
     * changes nothing — and a lifecycle that stops running for any reason releases the villager two
     * seconds later without anybody having to notice.
     */
    public static final int HOLD_REFRESH_TICKS = 40;

    /** What this tick may do with a held villager. */
    public enum Stance {
        /** Nothing here to hold: the pair are gone, dead, disconnected, or asleep. Drop the hold. */
        DROP,
        /** The villager has just been hurt: end the discussion, and never let the hold resume. */
        REVOKE_ATTACKED,
        /** The villager is fleeing: end the discussion so they can. */
        REVOKE_DANGER,
        /** Leave the villager entirely alone this tick, without ending anything. */
        LEAVE,
        /** Cosmetic only — face the player, never touch movement. */
        FACE,
        /** Face the player and suppress self-directed locomotion. */
        HOLD;

        /** True when this stance ends the discussion rather than shaping it. */
        public boolean revokes() {
            return this == REVOKE_ATTACKED || this == REVOKE_DANGER;
        }
    }

    /**
     * Everything {@link #decide} is allowed to know, as plain booleans.
     *
     * @param villagerUsable the villager is loaded, alive, a mob, and awake
     * @param playerUsable   the owner is online and still present
     * @param hurt           the villager's hurt animation is running — confirmed, recent damage
     * @param panicking      the brain is in {@link Activity#PANIC}
     * @param ownsMovement   this hold is a discussion's, not a passing glance's (spec §5.1 table)
     * @param holdEnabled    {@code holdVillagerDuringInteraction}
     * @param interruptOnDanger {@code interruptOnImmediateDanger}
     */
    public record Situation(boolean villagerUsable, boolean playerUsable, boolean hurt,
                            boolean panicking, boolean ownsMovement, boolean holdEnabled,
                            boolean interruptOnDanger) {
    }

    private ConversationMovementController() {
    }

    /**
     * The whole policy, as one pure function.
     *
     * <p>Order matters and states the priorities out loud: a villager who is not there cannot be
     * held; damage outranks every reason to keep talking and is never merely waited out; a fleeing
     * villager is either released with their discussion or left completely alone, never pinned; and
     * only then does the question of whether this hold is entitled to stop movement arise at all.
     */
    public static Stance decide(Situation s) {
        if (!s.villagerUsable() || !s.playerUsable()) {
            return Stance.DROP;
        }
        if (s.hurt()) {
            // Confirmed damage. The old behaviour skipped this tick and resumed when hurtTime ran
            // out, which is exactly how an attacked villager was recaptured by the conversation they
            // were trying to escape (spec §1.1 item 4).
            return Stance.REVOKE_ATTACKED;
        }
        if (s.panicking()) {
            return s.interruptOnDanger() ? Stance.REVOKE_DANGER : Stance.LEAVE;
        }
        if (!s.ownsMovement() || !s.holdEnabled()) {
            return Stance.FACE;
        }
        return Stance.HOLD;
    }

    /** Reads the live pair and the server config into a {@link Situation}, then {@link #decide}s. */
    public static Stance judge(Entity villager, ServerPlayer player, boolean ownsMovement) {
        boolean usable = villager instanceof Mob mob && !mob.isRemoved() && mob.isAlive()
                && !mob.isSleeping();
        boolean hurt = usable && ((Mob) villager).hurtTime > 0;
        boolean panicking = usable && ((Mob) villager).getBrain().isActive(Activity.PANIC);
        return decide(new Situation(usable, player != null && !player.hasDisconnected(), hurt,
                panicking, ownsMovement, McaConversationsConfig.holdVillagerDuringInteraction(),
                McaConversationsConfig.interruptOnImmediateDanger()));
    }

    /**
     * Applies a stance to a live villager. Only {@link Stance#FACE} and {@link Stance#HOLD} do
     * anything here; ending a discussion belongs to the lifecycle, not to the body.
     */
    public static void apply(Entity villager, ServerPlayer player, Stance stance) {
        if (!(villager instanceof Mob mob) || player == null
                || (stance != Stance.FACE && stance != Stance.HOLD)) {
            return;
        }
        Brain<?> brain = mob.getBrain();
        if (stance == Stance.HOLD) {
            brain.eraseMemory(MemoryModuleType.WALK_TARGET);
        }
        brain.setMemory(MemoryModuleType.LOOK_TARGET, new EntityTracker(player, true));
        if (stance == Stance.HOLD) {
            mob.getNavigation().stop();
        }
    }

    /**
     * Step 1 of spec §5.2: the moment a discussion is accepted, whatever the villager was walking
     * toward is abandoned — without waiting for the next lifecycle tick to notice.
     */
    public static void onAccepted(ConversationHandle handle, Entity villager, ServerPlayer player,
                                  long now) {
        tickHold(handle, villager, player, now);
    }

    /**
     * One live discussion's hold, renewed and applied for this tick.
     *
     * <p>Books the attention hold under the discussion's own handle, so a villager taken over by
     * somebody else cannot be released by the previous owner, and applies the stance. The returned
     * stance is the caller's to act on: revocation ends a discussion, and that decision belongs to
     * the lifecycle rather than to this class.
     */
    public static Stance tickHold(ConversationHandle handle, Entity villager, ServerPlayer player,
                                  long now) {
        if (handle == null || villager == null || player == null) {
            return Stance.DROP;
        }
        Stance stance;
        try {
            stance = judge(villager, player, true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("movement hold judgement failed for {}; dropping it", handle, t);
            stance = Stance.DROP;
        }
        switch (stance) {
            case DROP, REVOKE_ATTACKED, REVOKE_DANGER ->
                    VillagerAttention.releaseIfOwned(villager.getUUID(), handle);
            case LEAVE -> { /* a fleeing villager this server has chosen not to interrupt */ }
            case FACE, HOLD -> {
                VillagerAttention.hold(villager, player, now + HOLD_REFRESH_TICKS,
                        AttentionLedger.Source.CONVERSATION, handle);
                apply(villager, player, stance);
            }
        }
        return stance;
    }

    /**
     * {@code attackedBehavior = RETREAT}: break away from the attacker whatever the profession is.
     *
     * <p>Two vanilla brain writes and no MCA API at all, because there is no MCA combat API to call —
     * {@code EntityCommandHandler} commands a villager to follow, stay or work, and knows nothing
     * about fighting. So RETREAT means precisely this: forget any attack target the villager had just
     * acquired, and switch the brain to {@link Activity#PANIC} straight away rather than waiting for
     * the next panic sensor tick. A brain with no panic behaviours registered is left as it is, which
     * is what makes this safe to call on any villager MCA hands us.
     *
     * <p>Under the default {@code NATIVE_COMBAT} this is never called and MCA's own reaction — a
     * guard fighting back, a civilian's own panic — is left completely alone.
     */
    public static void retreat(Entity villager) {
        if (!(villager instanceof Mob mob)) {
            return;
        }
        try {
            Brain<?> brain = mob.getBrain();
            brain.eraseMemory(MemoryModuleType.ATTACK_TARGET);
            brain.eraseMemory(MemoryModuleType.WALK_TARGET);
            mob.getNavigation().stop();
            brain.setActiveActivityIfPossible(Activity.PANIC);
        } catch (Throwable t) {
            // A villager that cannot be told to flee is still an unheld villager, which is the part
            // that actually matters. Never let the escape hint break the interruption.
            McaConversations.LOGGER.debug("could not start a retreat for {}; leaving native AI alone",
                    villager.getUUID(), t);
        }
    }
}
