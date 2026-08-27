package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.ConversationGuard;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

/**
 * Validates GUI dialogue submissions for this mod's own questions before MCA acts on them.
 *
 * <p>MCA's {@code receive} resolves the villager by UUID out of the player's level and calls
 * {@code selectAnswer} — no distance check, no open-screen check, no constraint re-check, no replay
 * protection (verified byte-identical in MCA 7.6.20 and 7.7.0-beta.2). A crafted or duplicated
 * client packet can therefore drive any question/answer pair against any villager in the level. That
 * is cheap today and expensive once an answer can set a one-shot milestone.
 *
 * <p>Scope is deliberately tiny: {@link ConversationGuard} judges only questions whose id starts with
 * {@code conversations}, and every native MCA question returns immediately. Nothing about MCA's own
 * dialogue semantics changes for anyone else.
 *
 * <p>{@code remap = false}: MCA's own method. {@code require = 0} (config default): if MCA ever
 * reshapes this class the injection silently no-ops and submissions behave exactly as they do
 * without this mod. Any runtime failure likewise falls through to normal handling — the guarded
 * affection and progress actions enforce their own idempotency and caps regardless.
 *
 * <p><b>Two targets, one jar</b> — see {@link NetworkHandlerMixin} for why both MCA package roots are
 * listed and why {@link org.spongepowered.asm.mixin.Pseudo} is set. No {@code @Coerce} is needed
 * here: every shadowed field is a {@link UUID} or a {@link String}, and {@code receive} takes only a
 * {@code ServerPlayer}, so this mixin never had to name an MCA type in the first place.
 */
@Pseudo
@Mixin(targets = {
        "forge.net.mca.network.c2s.InteractionDialogueMessage",
        "forge.net.conczin.mca.network.c2s.InteractionDialogueMessage",
}, remap = false)
public abstract class InteractionDialogueMessageMixin {

    @Shadow
    @Final
    private UUID villagerUUID;

    @Shadow
    @Final
    private String question;

    @Shadow
    @Final
    private String answer;

    @Inject(method = "receive", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcaconversations$validateSubmission(ServerPlayer player, CallbackInfo ci) {
        try {
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
                return;
            }
            // The submission is going through, so this is the last moment before MCA scores the
            // answer's results — and therefore the only place a scene can be chosen once for the whole
            // exchange rather than once per candidate condition (see ConversationPlanner).
            dev.otectus.mcaconversations.scene.ConversationPlanner
                    .onAnswerSubmitted(villager, player, question, answer);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("dialogue submission validation failed; passing through", t);
        }
    }
}
