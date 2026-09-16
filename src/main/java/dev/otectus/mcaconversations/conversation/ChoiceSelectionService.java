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
        return submit(player, revision, absoluteIndex, candidateVillagerId).ok();
    }

    /**
     * Validates and, if it survives, executes one numbered reply, naming what happened.
     *
     * <p>Every refusal carries its own outcome to the client, and the success signal is sent once
     * execution has actually succeeded — not from a {@code finally} that fired after a thrown
     * action, and not while ignoring the {@code false} the reflective call came back with.
     *
     * <p>The whole submission runs against one captured bundle. Validating the offer's generation,
     * resolving the villager, checking the catalog's age gate and then executing the answer are half
     * a dozen content reads; a reload committing between any two of them would mean the reply was
     * judged against one body of content and executed against another.
     */
    public static ChoiceOutcome submit(ServerPlayer player, long revision, int absoluteIndex,
                                       UUID candidateVillagerId) {
        try (ContentOperation ignored = ContentOperation.open()) {
            return submitPinned(player, revision, absoluteIndex, candidateVillagerId);
        }
    }

    private static ChoiceOutcome submitPinned(ServerPlayer player, long revision, int absoluteIndex,
                                              UUID candidateVillagerId) {
        if (player == null || player.hasDisconnected() || !player.isAlive() || player.isSpectator()) {
            // There is nobody left to explain anything to.
            return ChoiceOutcome.SPEAKER_UNAVAILABLE;
        }
        if (!McaBridge.isAvailable()) {
            return reject(player, revision, ChoiceOutcome.FEATURE_DISABLED);
        }
        long now = player.level().getGameTime();
        ConversationSession session = ConversationSessions.raw(player.getUUID()).orElse(null);
        ConversationSession.ChoiceOffer offer = session == null ? null : session.currentOffer().orElse(null);
        if (offer == null || offer.consumed() || offer.revision() != revision
                || absoluteIndex < 0 || absoluteIndex >= offer.answerIds().size()) {
            return reject(player, revision, ChoiceOutcome.INVALID_SUBMISSION);
        }
        if (offerTimedOut(offer, now, timeoutTicks())) {
            return reject(player, revision, ChoiceOutcome.TIMED_OUT);
        }
        if (offer.generation() != ContentGeneration.current()) {
            // The numbered card the player is answering was minted from content a reload has since
            // replaced. Claim the offer so a second reply is refused too, close the topic safely,
            // and execute nothing.
            session.consumeOffer(revision, absoluteIndex);
            session.clearOffer();
            ConversationSessions.endTopic(player.getUUID(), now, CloseReason.CONTENT_RELOADED);
            return reject(player, revision, ChoiceOutcome.CONTENT_RELOADED);
        }

        Resolution resolution = resolveVillager(player, offer, candidateVillagerId);
        if (!resolution.ok()) {
            return reject(player, revision, resolution.outcome());
        }
        Entity villager = resolution.villager();

        if (offer.frontend() == ConversationSession.Frontend.CHAT
                && !ChatModeDispatcher.canSelectOfferedChoice(villager, player, now)) {
            return reject(player, revision, ChoiceOutcome.SPEAKER_UNAVAILABLE);
        }

        String question = offer.questionId();
        String answer = offer.answerIds().get(absoluteIndex);
        if (!McaCompat.checkConstraints(villager, player, question, answer)
                // The same age allow-list the GUI answer list and the direct packet enforce; a
                // numbered reply must not be a way around it.
                || !TopicAgeGate.allows(question, answer, villager)) {
            return reject(player, revision, ChoiceOutcome.REQUIREMENTS_CHANGED);
        }
        // Claim the offer before anything runs: exactly one attempt per offer, whatever the attempt
        // then does.
        if (session.consumeOffer(revision, absoluteIndex).isEmpty()) {
            return reject(player, revision, ChoiceOutcome.INVALID_SUBMISSION);
        }

        session.setVillagerId(villager.getUUID());
        session.setFrontend(offer.frontend());
        session.touch(now);
        ChoiceOutcome outcome;
        try {
            if (offer.frontend() == ConversationSession.Frontend.CHAT) {
                outcome = ChatModeDispatcher.selectOfferedChoice(villager, player, question, answer, now);
            } else {
                dev.otectus.mcaconversations.scene.ConversationPlanner
                        .onAnswerSubmitted(villager, player, question, answer);
                outcome = McaCompat.selectAnswer(villager, player, question, answer)
                        ? ChoiceOutcome.CONSUMED : ChoiceOutcome.EXECUTION_FAILED;
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.error("numbered response execution failed for {}/{}", question, answer, t);
            outcome = ChoiceOutcome.EXECUTION_FAILED;
        }
        if (outcome.ok()) {
            reofferAfterTerminal(player, villager, question, revision, now);
            ConversationsNetwork.clearOffer(player, revision, ChoiceClearS2C.Reason.CONSUMED);
            return outcome;
        }
        return failed(player, revision, question, answer, outcome);
    }

    /** Preserves MCA's menu after a successful say-only reply; never overwrites a successor. */
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

    /**
     * A reply that did not go through, reported without pretending it did.
     *
     * <p>A partially completed action may already have scheduled the next card. That successor was
     * minted by an attempt that then failed, so it is not an offer the player may answer: it is
     * cleared along with the one they did answer. Nothing is retried and nothing is rolled back —
     * only the ending is honest.
     */
    private static ChoiceOutcome failed(ServerPlayer player, long revision, String question,
                                        String answer, ChoiceOutcome outcome) {
        ConversationsNetwork.clearOffer(player, revision, outcome.wireReason());
        long successor = ConversationSessions.raw(player.getUUID())
                .flatMap(ConversationSession::currentOffer)
                .map(ConversationSession.ChoiceOffer::revision)
                .filter(next -> next != revision)
                .orElse(-1L);
        if (successor >= 0) {
            ConversationsNetwork.clearOffer(player, successor, outcome.wireReason());
        }
        outcome.closeReason().ifPresent(reason -> ConversationSessions.close(player.getUUID(), reason));
        McaConversations.LOGGER.warn("numbered response {}/{} ended as {}", question, answer, outcome);
        return outcome;
    }

    /** The villager an offer may be answered against, or the reason it may not be. */
    private record Resolution(Entity villager, ChoiceOutcome outcome) {
        static Resolution refused(ChoiceOutcome outcome) {
            return new Resolution(null, outcome);
        }

        boolean ok() {
            return villager != null && outcome.ok();
        }
    }

    private static Resolution resolveVillager(ServerPlayer player, ConversationSession.ChoiceOffer offer,
                                              UUID candidateVillagerId) {
        if (offer.frontend() == ConversationSession.Frontend.CHAT) {
            UUID authoritative = offer.villagerId();
            if (authoritative == null || candidateVillagerId != null
                    && !authoritative.equals(candidateVillagerId)) {
                return Resolution.refused(ChoiceOutcome.OWNERSHIP_MISMATCH);
            }
            Entity villager = player.serverLevel().getEntity(authoritative);
            double radius = McaConversationsConfig.chatModeAddressedRadius();
            return alive(villager)
                    ? (player.distanceToSqr(villager) <= radius * radius
                            ? new Resolution(villager, ChoiceOutcome.CONSUMED)
                            : Resolution.refused(ChoiceOutcome.OUT_OF_RANGE))
                    : Resolution.refused(ChoiceOutcome.SPEAKER_UNAVAILABLE);
        }
        if (candidateVillagerId == null) {
            return Resolution.refused(ChoiceOutcome.OWNERSHIP_MISMATCH);
        }
        Entity villager = player.serverLevel().getEntity(candidateVillagerId);
        if (!alive(villager)) {
            return Resolution.refused(ChoiceOutcome.SPEAKER_UNAVAILABLE);
        }
        if (offer.villagerId() != null && !offer.villagerId().equals(villager.getUUID())) {
            return Resolution.refused(ChoiceOutcome.OWNERSHIP_MISMATCH);
        }
        // Same policy the chat frontend refuses by, so a click and a typed number are rejected for
        // the same stated reason; only the radius is this frontend's own.
        ChoiceOutcome engagement = ChoiceOutcome.of(
                EngagementPolicy.evaluate(player, villager, GUI_DISTANCE_SQR));
        if (!engagement.ok()) {
            return Resolution.refused(engagement);
        }
        return McaCompat.isInteractingWith(villager)
                .filter(player.getUUID()::equals)
                .map(ignored -> new Resolution(villager, ChoiceOutcome.CONSUMED))
                .orElseGet(() -> Resolution.refused(ChoiceOutcome.OWNERSHIP_MISMATCH));
    }

    private static boolean alive(Entity villager) {
        return villager != null && villager.isAlive() && McaCompat.isMcaVillager(villager);
    }

    /**
     * A chat offer ages out with the session: nothing on screen keeps it alive, and a numbered reply
     * typed minutes later must not fire. A GUI offer is valid for exactly as long as MCA keeps its
     * screen open on that villager, which {@link #resolveVillager} already checks, so time alone
     * never expires it. Timing it out here used to reject the first click after a minute of reading
     * and hand the screen back to MCA's own answer list.
     */
    static boolean offerTimedOut(ConversationSession.ChoiceOffer offer, long now, int timeoutTicks) {
        return offer.frontend() == ConversationSession.Frontend.CHAT
                && now - offer.createdGameTime() > timeoutTicks;
    }

    /**
     * Tells the client this reply was refused, and why. Refusing a reply is not an ending: an
     * obsolete or duplicated packet must never tear down the session the player is still using.
     */
    public static ChoiceOutcome reject(ServerPlayer player, long revision, ChoiceOutcome outcome) {
        if (outcome == ChoiceOutcome.CONTENT_RELOADED || outcome == ChoiceOutcome.REQUIREMENTS_CHANGED
                || outcome == ChoiceOutcome.INVALID_SUBMISSION || outcome == ChoiceOutcome.TIMED_OUT) {
            ConversationSessions.raw(player.getUUID()).ifPresent(session -> session.allowTopicReturn(revision));
        }
        ConversationsNetwork.clearOffer(player, revision, outcome.wireReason());
        McaConversations.LOGGER.debug("refused numbered response revision {} from {}: {}",
                revision, player.getGameProfile().getName(), outcome);
        return outcome;
    }

    /** Opens a newly filtered menu after a refusal, without executing any answer from the old offer. */
    public static void returnToTopics(ServerPlayer player, long revision, UUID villagerId) {
        if (player == null || player.hasDisconnected() || !player.isAlive() || player.isSpectator()) {
            return;
        }
        try (ContentOperation ignored = ContentOperation.open()) {
            ConversationSession session = ConversationSessions.raw(player.getUUID()).orElse(null);
            if (session == null || !session.claimTopicReturn(revision)) {
                return;
            }
            Entity villager = villagerId == null ? null : player.serverLevel().getEntity(villagerId);
            ChoiceOutcome outcome = ChoiceOutcome.of(EngagementPolicy.evaluate(player, villager, GUI_DISTANCE_SQR));
            if (outcome.ok() && (!alive(villager) || McaCompat.isInteractingWith(villager)
                    .filter(player.getUUID()::equals).isEmpty())) {
                outcome = ChoiceOutcome.SPEAKER_UNAVAILABLE;
            }
            if (!outcome.ok()) {
                reject(player, revision, outcome);
                return;
            }
            session.setVillagerId(villagerId);
            ConversationSessions.endTopic(player.getUUID(), player.level().getGameTime(), CloseReason.COMPLETED);
            String question = McaConversationsConfig.hubEntryMode() == dev.otectus.mcaconversations.HubEntryMode.HIDDEN
                    ? "main" : "conversations";
            if (!dev.otectus.mcaconversations.compat.mca.McaHandles.sendDialogueMenu(villager, player, question)) {
                failed(player, revision, question, "<return>", ChoiceOutcome.EXECUTION_FAILED);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.warn("topic return failed", t);
            failed(player, revision, "<return>", "<return>", ChoiceOutcome.EXECUTION_FAILED);
        }
    }

    /** Refuses whatever offer this player currently has open, for callers that never saw a revision. */
    public static ChoiceOutcome rejectCurrent(ServerPlayer player, ChoiceOutcome outcome) {
        long revision = ConversationSessions.raw(player.getUUID())
                .flatMap(ConversationSession::currentOffer)
                .map(ConversationSession.ChoiceOffer::revision)
                .orElse(-1L);
        return revision < 0 ? outcome : reject(player, revision, outcome);
    }

    private static int timeoutTicks() {
        try {
            return McaConversationsConfig.conversationSessionTimeoutTicks();
        } catch (Throwable ignored) {
            return 1200;
        }
    }
}
