package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import forge.net.mca.cobalt.network.Message;
import forge.net.mca.cobalt.network.NetworkHandler;
import forge.net.mca.network.s2c.AnalysisResults;
import forge.net.mca.network.s2c.InteractionDialogueQuestionResponse;
import forge.net.mca.network.s2c.InteractionDialogueResponse;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Chat mode's one delivery-redirection hook. MCA's {@code say}/{@code next} dialogue actions deliver
 * villager lines and question prompts as S2C GUI packets through {@code NetworkHandler.sendToPlayer};
 * with no interact screen open they are useless to the client. While a {@link ChatModeSession} redirect
 * scope is open for the target player, this converts those packets into chat instead and cancels the
 * send. Every other packet (gift responses, village packets, and all dialogue packets when no scope is
 * open) passes through byte-identical.
 *
 * <p>{@code remap = false}: MCA's own method. {@code require = 0} (config default): if MCA reshapes
 * {@code sendToPlayer} the injection silently no-ops, {@code ChatModeSession.redirectionAvailable()}
 * stays false, and chat mode degrades gracefully. Any runtime failure falls through to normal delivery.
 */
@Mixin(value = NetworkHandler.class, remap = false)
public abstract class NetworkHandlerMixin {

    @Inject(method = "sendToPlayer", at = @At("HEAD"), cancellable = true, require = 0)
    private static void mcaconversations$redirectDialogueToChat(Message message, ServerPlayer player,
                                                               CallbackInfo ci) {
        try {
            ChatModeSession.markRedirectInstalled();
            if (message instanceof InteractionDialogueQuestionResponse response) {
                // getQuestionText() re-parses the line out of JSON, so ask whether we are redirecting
                // before paying for it: with no scope open this fires for every GUI dialogue packet.
                if (ChatModeSession.activeFor(player)
                        && ChatModeSession.deliverQuestion(player, response.getQuestionText(), response.silent)) {
                    ci.cancel();
                }
            } else if (message instanceof InteractionDialogueResponse response) {
                // Record what the player was actually offered for BOTH frontends. This packet is the
                // only place the constraint-filtered answer list exists, and knowing it is what lets
                // the submission validator reject an answer that was never on screen.
                ConversationSessions.recordOffer(player.getUUID(), response.question, response.answers,
                        player.level().getGameTime());
                if (ChatModeSession.swallowDialogue(player)) {
                    ci.cancel();
                }
            } else if (message instanceof AnalysisResults) {
                if (ChatModeSession.swallowAnalysis(player)) {
                    ci.cancel();
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("chat-mode delivery redirect failed; passing packet through", t);
        }
    }
}
