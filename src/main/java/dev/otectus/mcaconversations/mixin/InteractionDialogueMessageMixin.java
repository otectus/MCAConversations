package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.ConversationGuard;
import dev.otectus.mcaconversations.conversation.TopicAgeGate;
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
 * without this mod. When the hook is installed, validation failures reject owned submissions;
 * the affection and progress actions also enforce their own idempotency and caps.
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
            if (!ConversationGuard.isOurQuestion(question)) {
                return;
            }
            if (player == null || player.hasDisconnected() || !player.isAlive() || player.isSpectator()
                    || villagerUUID == null) {
                ci.cancel();
                return;
            }
            boolean otherPlayerInteracting = false;
            Entity villager = player.serverLevel().getEntity(villagerUUID);
            if (villager != null) {
                // Resolve busy ownership once; an active interaction with this player is also
                // required below before a GUI packet may drive the dialogue engine.
                otherPlayerInteracting = McaCompat.isInteractingWith(villager)
                        .filter(uuid -> !uuid.equals(player.getUUID()))
                        .isPresent();
            }
            if (villager == null || !villager.isAlive() || !McaCompat.isMcaVillager(villager)
                    || player.distanceToSqr(villager) > 64.0D
                    || !McaCompat.isInteractingWith(villager).filter(player.getUUID()::equals).isPresent()
                    || !McaCompat.checkConstraints(villager, player, question, answer)
                    // MCA's constraints cannot express the catalog's age allow-list (there is no
                    // 'child' token), so a packet naming a teen-and-adult topic has to be refused here
                    // rather than trusted because the button was clickable.
                    || !TopicAgeGate.allows(question, answer, villager)) {
                ci.cancel();
                return;
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
            ci.cancel();
            McaConversations.LOGGER.warn("dialogue submission validation failed; rejecting submission", t);
        }
    }
}
