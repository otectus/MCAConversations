package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeSession;
import dev.otectus.mcaconversations.compat.mca.McaHandles;
import dev.otectus.mcaconversations.conversation.ConversationHandle;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.InteractionBoundary;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import dev.otectus.mcaconversations.network.ChoiceOfferS2C;
import dev.otectus.mcaconversations.network.ConversationRef;
import dev.otectus.mcaconversations.network.ConversationsNetwork;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

/**
 * Chat mode's one delivery-redirection hook. MCA's {@code say}/{@code next} dialogue actions deliver
 * villager lines and question prompts as S2C GUI packets through {@code NetworkHandler.sendToPlayer};
 * with no interact screen open they are useless to the client. While a {@link ChatModeSession} redirect
 * scope is open for the target player, this converts those packets into chat instead and cancels the
 * send. Every other packet (gift responses, village packets, and all dialogue packets when no scope is
 * open) passes through byte-identical.
 *
 * <p><b>Two targets, one jar.</b> MCA's Forge classes moved from {@code forge.net.mca.*} to
 * {@code forge.net.conczin.mca.*} in 7.7.1-alpha.1. Mixin resolves each declared target
 * independently and simply omits one it cannot find, so listing both makes this apply on either
 * generation. {@link Pseudo} keeps the unmatched root a DEBUG line rather than a startup warning
 * that would be indistinguishable from a real breakage in a bug report.
 *
 * <p><b>Why {@link Coerce}.</b> The target parameter is MCA's {@code Message}, whose package is
 * exactly what varies, so it cannot be named here. {@code @Coerce} lets the handler declare a
 * supertype — {@code Object} — and Mixin inserts the cast; {@code McaHandles} then identifies the
 * packet by a {@link Class} resolved from the probed root.
 *
 * <p><b>It is also the interaction boundary.</b> A graphical dialogue packet is the earliest point
 * at which this mod can see both that MCA accepted an interaction and which villager it accepted;
 * {@link InteractionBoundary} mints the discussion's handle there, so the offer it records names a
 * real villager instead of {@code null}.
 *
 * <p>{@code remap = false}: MCA's own method. {@code require = 0} (config default): if MCA reshapes
 * {@code sendToPlayer} the injection silently no-ops, {@code ChatModeSession.redirectionAvailable()}
 * stays false, and chat mode degrades gracefully. Any runtime failure falls through to normal delivery.
 */
@Pseudo
@Mixin(targets = {
        "forge.net.mca.cobalt.network.NetworkHandler",
        "forge.net.conczin.mca.cobalt.network.NetworkHandler",
}, remap = false)
public abstract class NetworkHandlerMixin {

    @Inject(method = "sendToPlayer", at = @At("HEAD"), cancellable = true, require = 0)
    private static void mcaconversations$redirectDialogueToChat(@Coerce Object message, ServerPlayer player,
                                                               CallbackInfo ci) {
        // Minting an offer stamps it with a generation, and the same packet decides which answers
        // the player will be able to submit. Both come from one captured bundle.
        try (dev.otectus.mcaconversations.conversation.ContentOperation ignored =
                     dev.otectus.mcaconversations.conversation.ContentOperation.open()) {
            ChatModeSession.markRedirectInstalled();
            if (McaHandles.isQuestionResponse(message)) {
                // questionText() re-parses the line out of JSON, so ask whether we are redirecting
                // before paying for it: with no scope open this fires for every GUI dialogue packet.
                if (ChatModeSession.activeFor(player)
                        && ChatModeSession.deliverQuestion(player, McaHandles.questionText(message),
                                McaHandles.isSilentQuestion(message))) {
                    ci.cancel();
                }
            } else if (McaHandles.isDialogueResponse(message)) {
                // Record what the player was actually offered for BOTH frontends. This packet is the
                // only place the constraint-filtered answer list exists, and knowing it is what lets
                // the submission validator reject an answer that was never on screen.
                boolean chat = ChatModeSession.activeFor(player);
                // A graphical offer's villager is MCA's answer, not a guess and not a null. This is
                // also where a graphical discussion is accepted: it is the first moment the server
                // both knows MCA took the interaction and has something to put on the screen.
                ConversationHandle handle = chat ? null
                        : InteractionBoundary.beginGui(player).orElse(null);
                UUID speaker = chat ? ChatModeSession.activeVillagerId(player)
                        : handle == null ? null : handle.villagerId();
                ConversationSession.ChoiceOffer offer = ConversationSessions.recordOffer(
                        player.getUUID(), speaker,
                        McaHandles.responseQuestion(message), McaHandles.responseAnswers(message),
                        chat ? ConversationSession.Frontend.CHAT : ConversationSession.Frontend.GUI,
                        player.level().getGameTime());
                ConversationRef ref = handle == null
                        ? ConversationsNetwork.refFor(player) : ConversationRef.of(handle);
                if (offer.answerIds().size() <= ChoiceOfferS2C.MAX_CHOICES) {
                    if (offer.answerIds().isEmpty()) {
                        ConversationsNetwork.clearOffer(player, ref, offer.revision(),
                                ChoiceClearS2C.Reason.NONE);
                    } else {
                        ConversationsNetwork.sendOffer(player, ChoiceOfferS2C.from(ref, offer));
                    }
                } else {
                    ConversationsNetwork.clearOffer(player, ref, offer.revision(),
                            ChoiceClearS2C.Reason.NONE);
                    ConversationsNetwork.warnOversizedOffer(offer.questionId(), offer.answerIds().size());
                }
                if (chat) {
                    ci.cancel();
                }
            } else if (McaHandles.isAnalysisResults(message)) {
                if (ChatModeSession.swallowAnalysis(player)) {
                    ci.cancel();
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("chat-mode delivery redirect failed; passing packet through", t);
        }
    }
}
