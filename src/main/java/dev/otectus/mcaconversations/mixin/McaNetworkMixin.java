package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import net.conczin.mca.network.HandleablePayload;
import net.conczin.mca.network.Network;
import net.conczin.mca.network.s2c.AnalysisResults;
import net.conczin.mca.network.s2c.InteractionDialogueQuestionResponse;
import net.conczin.mca.network.s2c.InteractionDialogueResponse;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Chat mode's one delivery-redirection hook. MCA's {@code say}/{@code next} dialogue actions deliver
 * villager lines and question prompts as S2C GUI payloads through {@code Network.sendToPlayer}; with
 * no interact screen open they are useless to the client. While a {@link ChatModeSession} redirect
 * scope is open for the target player, this converts those payloads into chat instead and cancels
 * the send. Every other payload — gift responses, village payloads, and all dialogue payloads when
 * no scope is open — passes through byte-identical.
 *
 * <p>Renamed from {@code NetworkHandlerMixin}: MCA 1.21.1 replaced the {@code cobalt.network}
 * {@code NetworkHandler}/{@code Message} pair with {@code Network}/{@code HandleablePayload}, and the
 * three payloads read here became records, so every field access is now an accessor call.
 *
 * <p>The method descriptor is written out in full rather than matching on the bare name, so a future
 * {@code sendToPlayer} overload cannot be silently captured instead.
 *
 * <p>{@code remap = false}: MCA's own method. {@code require = 0} (config default): if MCA reshapes
 * {@code sendToPlayer} the injection no-ops, {@code ChatModeSession.redirectionAvailable()} stays
 * false, and chat mode degrades gracefully — {@code /conversations chat debug-ask} reports the
 * missing hook rather than the feature failing mutely. Any runtime failure falls through to normal
 * delivery.
 */
@Mixin(value = Network.class, remap = false)
public abstract class McaNetworkMixin {

    @Inject(
            method = "sendToPlayer(Lnet/conczin/mca/network/HandleablePayload;"
                    + "Lnet/minecraft/server/level/ServerPlayer;)V",
            at = @At("HEAD"),
            cancellable = true,
            require = 0)
    private static void mcaconversations$redirectDialogueToChat(HandleablePayload payload, ServerPlayer player,
                                                                CallbackInfo ci) {
        try {
            ChatModeSession.markRedirectInstalled();
            if (payload instanceof InteractionDialogueQuestionResponse response) {
                // Cheap now: questionText() is a plain record accessor over a Component. The 1.20.1
                // packet stored the line as JSON and re-parsed it on every read, which is why this
                // used to check for an open scope before touching it.
                if (ChatModeSession.activeFor(player)
                        && ChatModeSession.deliverQuestion(player, response.questionText().copy(),
                                response.silent())) {
                    ci.cancel();
                }
            } else if (payload instanceof InteractionDialogueResponse response) {
                // Record what the player was actually offered for BOTH frontends. This payload is the
                // only place the constraint-filtered answer list exists, and knowing it is what lets
                // the submission validator reject an answer that was never on screen.
                ConversationSessions.recordOffer(player.getUUID(), response.question(), response.answers(),
                        player.level().getGameTime());
                if (ChatModeSession.swallowDialogue(player)) {
                    ci.cancel();
                }
            } else if (payload instanceof AnalysisResults) {
                if (ChatModeSession.swallowAnalysis(player)) {
                    ci.cancel();
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("chat-mode delivery redirect failed; passing packet through", t);
        }
    }
}
