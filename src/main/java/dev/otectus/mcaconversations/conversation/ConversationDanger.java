package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.McaConversationsConfig.AttackedBehavior;
import dev.otectus.mcaconversations.chat.ConversationMovementController;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

/**
 * The one place a discussion ends because the villager is in trouble (spec §1.1 item 4, §5.3).
 *
 * <p>Both loaders' damage events, and the lifecycle tick that notices a villager already fleeing,
 * funnel through here, so the sequence is identical wherever the danger was seen:
 *
 * <ol>
 *   <li><b>One incident, one interruption.</b> {@link DangerLockout#noteIncident} folds the attack
 *       event and the damage event of a single blow into one.</li>
 *   <li><b>Revoke the handle.</b> {@link ConversationLifecycle#terminate} clears the offer and any
 *       pending reply, releases this handle's hold, sends the terminal packet, and ends MCA's own
 *       interaction — the whole teardown, not a movement pause. There is no state left that a
 *       finished hurt animation could resume, which is the bug this replaces: the old code skipped
 *       the tick while {@code hurtTime} ran and pinned the villager again the moment it reached
 *       zero.</li>
 *   <li><b>Drop any remaining hold.</b> A glance or a chat engagement booked by somebody else is not
 *       this handle's to keep either: a villager being hit is nobody's conversation partner.</li>
 *   <li><b>Refuse the immediate re-open</b> for {@code attackReopenDelayTicks}.</li>
 *   <li><b>Apply {@code attackedBehavior}</b> — see {@link #applyBehavior}.</li>
 * </ol>
 *
 * <p>None of this is a social judgement. {@link CloseReason#ATTACKED} and {@link CloseReason#DANGER}
 * are operational endings like every other reason in that enum: no hearts move, no disposition
 * changes, and MCA's own crime and reputation handling stays the sole owner of what attacking a
 * villager <em>means</em>. This mod only stops talking.
 *
 * <p>Server thread only.
 */
public final class ConversationDanger {

    private ConversationDanger() {
    }

    /**
     * A blow landed on this villager — absorbed, blocked or damaging, from a player, a mob, a
     * projectile or the world itself.
     *
     * <p>Deliberately indifferent to the attacker. The spec's requirement is that an attack ends the
     * conversation, not that an attack <em>by the person you are talking to</em> does; a zombie
     * behind the villager is the case that matters most, and a villager caught in a friend's
     * crossfire is in exactly as much trouble.
     *
     * @param villager  the villager that was hit, for the retreat hint; may be null
     * @param villagerId the villager's identity, which is all the teardown needs
     * @return true when a live discussion was actually interrupted
     */
    public static boolean onAttacked(Entity villager, UUID villagerId, long gameTime) {
        if (villagerId == null || !DangerLockout.noteIncident(villagerId, gameTime)) {
            return false; // the other half of a blow already handled
        }
        return interrupt(villager, villagerId, CloseReason.ATTACKED, gameTime);
    }

    /**
     * The villager is in immediate danger without new damage having arrived — the lifecycle tick
     * found them panicking. Guarded by {@code interruptOnImmediateDanger} at the call site, since a
     * server that has switched that off wants the discussion left standing.
     */
    public static boolean onDanger(Entity villager, UUID villagerId, long gameTime) {
        if (villagerId == null) {
            return false;
        }
        DangerLockout.noteIncident(villagerId, gameTime);
        return interrupt(villager, villagerId, CloseReason.DANGER, gameTime);
    }

    private static boolean interrupt(Entity villager, UUID villagerId, CloseReason reason,
                                     long gameTime) {
        boolean owned = ConversationPresence.ofVillager(villagerId).isPresent();
        if (owned) {
            ConversationLifecycle.terminateVillager(villagerId, reason);
        }
        // Whatever is left — a glance, a chat engagement, a hold whose discussion was already gone —
        // goes with it. This is the villager itself becoming unavailable, which is the one case
        // release-by-villager is for.
        VillagerAttention.release(villagerId);
        DangerLockout.lock(villagerId, gameTime, McaConversationsConfig.attackReopenDelayTicks());
        if (owned) {
            applyBehavior(villager);
            McaConversations.LOGGER.debug("discussion with villager {} interrupted as {}",
                    villagerId, reason);
        }
        return owned;
    }

    /**
     * What the villager is free to do now the discussion is over.
     *
     * <p><b>{@code NATIVE_COMBAT} (the default) does nothing at all</b>, and that is the point: the
     * villager is simply unheld, and MCA's own reaction — a guard fighting back, a civilian's panic
     * behaviour, a crime record — runs exactly as it would if this mod were not installed.
     *
     * <p>{@code RETREAT} adds the smallest possible nudge on top, described on
     * {@link ConversationMovementController#retreat}: forget the attack target and enter the panic
     * activity, using vanilla brain calls only. MCA exposes no combat API to call instead, and
     * inventing one would be guessing at somebody else's AI.
     */
    private static void applyBehavior(Entity villager) {
        if (villager != null && McaConversationsConfig.attackedBehavior() == AttackedBehavior.RETREAT) {
            ConversationMovementController.retreat(villager);
        }
    }

    /** Whether this villager is still inside the post-attack re-open delay. */
    public static boolean lockedOut(UUID villagerId, long gameTime) {
        return DangerLockout.locked(villagerId, gameTime);
    }
}
