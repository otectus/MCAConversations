package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Stops MCA walking a villager toward the player it is already talking to (spec §5.2 step 2).
 *
 * <p>MCA's {@code InteractTask} is the brain task that runs while a villager has an interacting
 * player, and its {@code followPlayer} step writes a walk target every tick so the villager closes
 * the distance. Erasing that walk target at the end of the server tick — which is all this mod used
 * to do — is a race the task can win: it proposes, the movement controls act, and only afterwards
 * does anything erase it. Cancelling the proposal at its source is the only version that actually
 * holds still, and it leaves the rest of the task alone, so MCA's own eligibility, its look
 * behaviour, and its {@code stop()} all keep working exactly as they do without this mod.
 *
 * <p><b>Why this method and not the Behavior lifecycle.</b> {@code InteractTask}'s lifecycle
 * overrides carry vanilla names that have been renamed across the MCA line — {@code shouldRun}/
 * {@code shouldKeepRunning}/{@code run} on the 7.6 era, {@code checkExtraStartConditions}/
 * {@code canStillUse}/{@code start} since — and on this branch they also arrive through the
 * loader's own mapping. {@code private void followPlayer(VillagerEntityMCA)} carries the same name
 * and the same descriptor on every probed build of both lines, and it is exactly the walking
 * component the hold wants gone.
 *
 * <p><b>The villager arrives as an MCA type this mod may not name</b>, so it is taken as a
 * {@link Coerce}d {@link Object} and read only as a vanilla {@link Entity} for its UUID — which is
 * all {@link VillagerAttention#holdsMovement} needs. No MCA class is referenced from {@code src/}.
 *
 * <p><b>One NeoForge target</b> — see {@link NetworkHandlerMixin} for why the MCA target is named as
 * a string and why {@link Pseudo} is set. {@code remap = false}: MCA's own method.
 * {@code require = 0}: a renamed target silently no-ops, leaving MCA's follow behaviour as it is
 * without this mod, and {@code MixinTargetProbeTest} turns that rename into a build failure rather
 * than a hold that quietly stopped working in somebody's world.
 */
@Pseudo
@Mixin(targets = "net.conczin.mca.entity.ai.brain.tasks.InteractTask", remap = false)
public abstract class InteractTaskMovementMixin {

    @Inject(method = "followPlayer", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcaconversations$holdStillWhileTalking(@Coerce Object villager, CallbackInfo ci) {
        if (!(villager instanceof Entity entity)) {
            return;
        }
        try {
            if (VillagerAttention.holdsMovement(entity.getUUID())) {
                ci.cancel();
            }
        } catch (Throwable t) {
            // Fail open: a villager that follows is a cosmetic disappointment, a brain task that
            // throws every tick is not.
            McaConversations.LOGGER.debug("movement hold check failed; letting MCA follow", t);
        }
    }
}
