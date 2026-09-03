package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import dev.otectus.mcaconversations.network.ChoiceOfferS2C;
import dev.otectus.mcaconversations.network.ConversationsNetwork;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

/** Server-authoritative validation and one-shot execution for numeric choice packets. */
public final class ChoiceSelectionService {

    private static final double GUI_DISTANCE_SQR = 64.0D;

    private ChoiceSelectionService() {
    }

    public static boolean select(ServerPlayer player, long revision, int absoluteIndex, UUID candidateVillagerId) {
        if (player == null || player.hasDisconnected() || !player.isAlive() || player.isSpectator()
                || !McaBridge.isAvailable()) {
            return false;
        }
        long now = player.level().getGameTime();
        ConversationSession session = ConversationSessions.raw(player.getUUID()).orElse(null);
        ConversationSession.ChoiceOffer offer = session == null ? null : session.currentOffer().orElse(null);
        if (offer == null || offer.consumed() || offer.revision() != revision
                || absoluteIndex < 0 || absoluteIndex >= offer.answerIds().size()
                || now - offer.createdGameTime() > timeoutTicks()) {
            rejectClient(player, revision);
            return false;
        }

        Entity villager = resolveVillager(player, offer, candidateVillagerId);
        if (villager == null || !villager.isAlive() || !McaCompat.isMcaVillager(villager)) {
            rejectClient(player, revision);
            return false;
        }
        if (offer.villagerId() != null && !offer.villagerId().equals(villager.getUUID())) {
            rejectClient(player, revision);
            return false;
        }

        String question = offer.questionId();
        String answer = offer.answerIds().get(absoluteIndex);
        if (!McaCompat.checkConstraints(villager, player, question, answer)) {
            rejectClient(player, revision);
            return false;
        }
        if (session.consumeOffer(revision, absoluteIndex).isEmpty()) {
            rejectClient(player, revision);
            return false;
        }

        session.setVillagerId(villager.getUUID());
        session.setFrontend(offer.frontend());
        session.touch(now);
        try {
            if (offer.frontend() == ConversationSession.Frontend.CHAT) {
                ChatModeDispatcher.selectOfferedChoice(villager, player, question, answer, now);
            } else {
                dev.otectus.mcaconversations.scene.ConversationPlanner
                        .onAnswerSubmitted(villager, player, question, answer);
                McaCompat.selectAnswer(villager, player, question, answer);
                reofferAfterTerminal(player, villager, question, revision, now);
            }
            return true;
        } catch (Throwable t) {
            McaConversations.LOGGER.error("numbered response execution failed for {}/{}", question, answer, t);
            return false;
        } finally {
            // A re-offer has already replaced the offer this packet consumed, so clearing it here
            // would blink the restored card away for a frame.
            if (ConversationSessions.raw(player.getUUID())
                    .flatMap(ConversationSession::currentOffer)
                    .map(current -> current.revision() == revision)
                    .orElse(true)) {
                ConversationsNetwork.clearOffer(player, revision, ChoiceClearS2C.Reason.CONSUMED);
            }
        }
    }

    /**
     * Re-arms the choices after a villager reply that ended the branch, mirroring vanilla MCA, where
     * a say-only answer leaves the buttons on screen.
     *
     * <p>Called once MCA has finished scoring the answer, so a genuine next question has already
     * bumped the revision and is left untouched. A GUI offer is restored only while this player still
     * has the screen open on this villager; a CHAT offer only for the villager it named, so a second
     * ambient responder cannot re-offer the sticky partner's question. Never throws: a failure here
     * leaves the conversation exactly where it was.
     */
    public static void reofferAfterTerminal(ServerPlayer player, Entity villager, String question,
                                            long revisionBefore, long now) {
        if (player == null || player.hasDisconnected() || villager == null || !villager.isAlive()) {
            return;
        }
        try {
            ConversationSession.ChoiceOffer current = ConversationSessions.raw(player.getUUID())
                    .flatMap(ConversationSession::currentOffer)
                    .orElse(null);
            if (current == null || current.revision() != revisionBefore) {
                return;
            }
            if (current.frontend() == ConversationSession.Frontend.CHAT) {
                if (!villager.getUUID().equals(current.villagerId())) {
                    return;
                }
            } else if (McaCompat.isInteractingWith(villager)
                    .filter(player.getUUID()::equals)
                    .isEmpty()) {
                return;
            }
            ConversationSessions.reofferConsumed(player.getUUID(), question, revisionBefore, now)
                    .filter(offer -> !offer.answerIds().isEmpty()
                            && offer.answerIds().size() <= ChoiceOfferS2C.MAX_CHOICES)
                    .ifPresent(offer -> ConversationsNetwork.sendOffer(player, ChoiceOfferS2C.from(offer)));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("re-offer after terminal reply failed for {}", question, t);
        }
    }

    private static Entity resolveVillager(ServerPlayer player, ConversationSession.ChoiceOffer offer,
                                          UUID candidateVillagerId) {
        if (offer.frontend() == ConversationSession.Frontend.CHAT) {
            UUID authoritative = offer.villagerId();
            if (authoritative == null || candidateVillagerId != null
                    && !authoritative.equals(candidateVillagerId)) {
                return null;
            }
            Entity villager = player.serverLevel().getEntity(authoritative);
            double radius = McaConversationsConfig.chatModeAddressedRadius();
            return villager != null && player.distanceToSqr(villager) <= radius * radius ? villager : null;
        }
        if (candidateVillagerId == null) {
            return null;
        }
        Entity villager = player.serverLevel().getEntity(candidateVillagerId);
        if (villager == null || player.distanceToSqr(villager) > GUI_DISTANCE_SQR) {
            return null;
        }
        return McaCompat.isInteractingWith(villager)
                .filter(player.getUUID()::equals)
                .map(ignored -> villager)
                .orElse(null);
    }

    private static void rejectClient(ServerPlayer player, long revision) {
        ConversationsNetwork.clearOffer(player, revision, ChoiceClearS2C.Reason.EXPIRED);
        McaConversations.LOGGER.debug("ignored stale or invalid numbered response revision {} from {}",
                revision, player.getGameProfile().getName());
    }

    private static int timeoutTicks() {
        try {
            return McaConversationsConfig.conversationSessionTimeoutTicks();
        } catch (Throwable ignored) {
            return 1200;
        }
    }
}
