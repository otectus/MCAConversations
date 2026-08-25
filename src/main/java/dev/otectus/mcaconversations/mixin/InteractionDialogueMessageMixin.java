package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.ConversationGuard;
import net.conczin.mca.network.c2s.InteractionDialogueMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

/**
 * Validates GUI dialogue submissions for this mod's own questions before MCA acts on them.
 *
 * <p>MCA's {@code handleServer} resolves the villager by UUID out of the player's level and calls
 * {@code selectAnswer} — no distance check, no open-screen check, no constraint re-check, no replay
 * protection (verified against the resolved MCA 7.7.36-beta.3 jar; the 1.20.1 {@code receive} it
 * replaced was byte-identical in this respect). A crafted or duplicated client packet can therefore
 * drive any question/answer pair against any villager in the level. That is cheap today and
 * expensive once an answer can set a one-shot milestone.
 *
 * <p>Scope is deliberately tiny: {@link ConversationGuard} judges only questions whose id starts with
 * {@code conversations}, and every native MCA question returns immediately. Nothing about MCA's own
 * dialogue semantics changes for anyone else.
 *
 * <p>{@code remap = false}: MCA's own method. {@code require = 0} (config default): if MCA ever
 * reshapes this class the injection silently no-ops and submissions behave exactly as they do
 * without this mod. Any runtime failure likewise falls through to normal handling — the guarded
 * affection and progress actions enforce their own idempotency and caps regardless.
 */
@Mixin(value = InteractionDialogueMessage.class, remap = false)
public abstract class InteractionDialogueMessageMixin {

    @Inject(
            method = "handleServer(Lnet/minecraft/server/level/ServerPlayer;)V",
            at = @At("HEAD"),
            cancellable = true,
            require = 0)
    private void mcaconversations$validateSubmission(ServerPlayer player, CallbackInfo ci) {
        try {
            // The target is a record in 1.21.1. Reading its components through the generated
            // accessors rather than @Shadow-ing the private final fields avoids shadowing a record
            // component, which Mixin cannot do safely.
            InteractionDialogueMessage self = (InteractionDialogueMessage) (Object) this;
            UUID villagerUUID = self.villagerUUID();
            String question = self.question();
            String answer = self.answer();

            if (player == null || !ConversationGuard.isOurQuestion(question)) {
                return;
            }
            boolean otherPlayerInteracting = false;
            Entity villager = player.serverLevel().getEntity(villagerUUID);
            if (villager != null) {
                // Fail open on an empty answer: MCA's interacting-player state is authoritative when
                // it names someone, but an unset value must never break a legitimate click.
                otherPlayerInteracting = McaCompat.isInteractingWith(villager)
                        .filter(uuid -> !uuid.equals(player.getUUID()))
                        .isPresent();
            }
            if (ConversationGuard.rejectSubmission(player.getUUID(), villagerUUID, question, answer,
                    otherPlayerInteracting, player.level().getGameTime())) {
                ci.cancel();
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("dialogue submission validation failed; passing through", t);
        }
    }
}
