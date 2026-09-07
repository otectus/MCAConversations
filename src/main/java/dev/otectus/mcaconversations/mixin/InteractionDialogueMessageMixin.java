package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.ChoiceSelectionService;
import dev.otectus.mcaconversations.conversation.ConversationGuard;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
 * without this mod. When the hook is installed, validation failures reject owned submissions;
 * the affection and progress actions also enforce their own idempotency and caps.
 *
 * <p><b>One target, one jar</b> — see {@link NetworkHandlerMixin} for why the MCA package root is
 * given as a string and why {@link org.spongepowered.asm.mixin.Pseudo} is set. No {@code @Coerce} is
 * needed here: every shadowed field is a {@link UUID} or a {@link String}, and {@code handleServer}
 * takes only a {@code ServerPlayer}, so this mixin never had to name an MCA type in the first place.
 * The target is a record on 1.21.1, so the three fields shadowed here are its components; they are
 * read and never written.
 */
@Pseudo
@Mixin(targets = "net.conczin.mca.network.c2s.InteractionDialogueMessage", remap = false)
public abstract class InteractionDialogueMessageMixin {

    /**
     * The offer revision each in-flight submission started from, keyed by player.
     *
     * <p>Static rather than a {@link Unique} instance field because the target is a record: adding an
     * instance field to one is not something this mod is willing to bet a dedicated server on. Written
     * only when the HEAD guard lets a submission through and removed at TAIL, so an entry never
     * outlives the handler and a cancelled packet leaves nothing behind.
     */
    @Unique
    private static final Map<UUID, Long> mcaconversations$revisionBefore = new ConcurrentHashMap<>();

    @Shadow
    @Final
    private UUID villagerUUID;

    @Shadow
    @Final
    private String question;

    @Shadow
    @Final
    private String answer;

    @Inject(
            method = "handleServer(Lnet/minecraft/server/level/ServerPlayer;)V",
            at = @At("HEAD"),
            cancellable = true,
            require = 0)
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
                    || !McaCompat.checkConstraints(villager, player, question, answer)) {
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
            mcaconversations$revisionBefore.put(player.getUUID(), ConversationSessions
                    .raw(player.getUUID())
                    .flatMap(ConversationSession::currentOffer)
                    .map(ConversationSession.ChoiceOffer::revision)
                    .orElse(-1L));
        } catch (Throwable t) {
            ci.cancel();
            McaConversations.LOGGER.warn("dialogue submission validation failed; rejecting submission", t);
        }
    }

    /**
     * Re-arms the offer when MCA's own button drove a say-only answer. Vanilla MCA leaves its buttons
     * on screen in that case; this mod's offer is consume-once, so without this the next click on the
     * same question is rejected as a replay.
     */
    @Inject(
            method = "handleServer(Lnet/minecraft/server/level/ServerPlayer;)V",
            at = @At("TAIL"),
            require = 0)
    private void mcaconversations$reofferAfterSubmission(ServerPlayer player, CallbackInfo ci) {
        try {
            if (player == null) {
                return;
            }
            Long revisionBefore = mcaconversations$revisionBefore.remove(player.getUUID());
            if (revisionBefore == null) {
                return;
            }
            Entity villager = player.serverLevel().getEntity(villagerUUID);
            ChoiceSelectionService.reofferAfterTerminal(player, villager, question, revisionBefore,
                    player.level().getGameTime());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("dialogue submission re-offer failed", t);
        }
    }
}
