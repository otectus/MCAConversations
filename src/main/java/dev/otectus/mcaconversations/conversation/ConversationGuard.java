package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;

import java.util.UUID;

/**
 * Validates this mod's ordinary MCA mouse submissions against the server's exact live GUI offer.
 * Native MCA questions remain outside this guard. Owned questions require a one-shot offered
 * answer, the correct frontend and, where the packet supplied it, the same villager. The receive
 * mixin additionally checks the player's state, distance, active interaction owner and constraints.
 * Missing or failed validation rejects the submission; actions must never run from fabricated
 * question/answer pairs after a session has expired or been cleared.
 */
public final class ConversationGuard {

    private ConversationGuard() {
    }

    /** True when the question belongs to this mod and is therefore ours to validate. */
    public static boolean isOurQuestion(String question) {
        return "conversations".equals(question)
                || question != null && question.startsWith("conversations.");
    }

    /**
     * Decides whether to drop a submission.
     *
     * @param otherPlayerInteracting true when MCA reports a <em>different</em> player currently in a
     *                               GUI interaction with the target villager; the caller resolves
     *                               this, so this class stays free of MCA imports
     * @return true to cancel the packet
     */
    public static boolean rejectSubmission(UUID playerId, UUID villagerId, String question, String answer,
                                           boolean otherPlayerInteracting, long now) {
        if (!isOurQuestion(question)) {
            return false;
        }
        if (playerId == null || villagerId == null || answer == null) {
            return true;
        }
        if (otherPlayerInteracting) {
            return reject(playerId, question, answer, "villager is mid-conversation with another player");
        }
        ConversationSession session = ConversationSessions.raw(playerId).orElse(null);
        if (session == null || session.currentQuestion() == null) {
            return reject(playerId, question, answer, "there is no live offer");
        }
        ConversationSession.ChoiceOffer offer = session.currentOffer().orElseThrow();
        if (offer.frontend() != ConversationSession.Frontend.GUI) {
            return reject(playerId, question, answer, "a chat offer cannot be submitted as a GUI packet");
        }
        if (offer.villagerId() != null && !offer.villagerId().equals(villagerId)) {
            return reject(playerId, question, answer, "offer belongs to another villager");
        }
        if (!session.wasOffered(question, answer)) {
            return reject(playerId, question, answer, "answer was not among the offered choices for "
                    + session.currentQuestion());
        }
        if (!session.consumeOfferedAnswer(question, answer)) {
            return reject(playerId, question, answer, "offer revision was already consumed");
        }
        if (offer.generation() != ContentGeneration.current()) {
            // The answers on screen were written by content a reload has since replaced. Drop the
            // page and close the topic safely — nothing runs — so the next click is refused for the
            // ordinary reason instead of executing against a catalog that no longer exists.
            session.clearOffer();
            ConversationSessions.endTopic(playerId, now);
            return reject(playerId, question, answer, "content was reloaded after the offer was made");
        }
        session.setVillagerId(villagerId);
        session.touch(now);
        return false;
    }

    private static boolean reject(UUID playerId, String question, String answer, String why) {
        if (debugBranching()) {
            McaConversations.LOGGER.info("[branch] rejected submission {}/{} from {}: {}",
                    question, answer, playerId, why);
        } else {
            McaConversations.LOGGER.debug("rejected dialogue submission {}/{} from {}: {}",
                    question, answer, playerId, why);
        }
        return true;
    }

    private static boolean debugBranching() {
        try {
            return McaConversationsConfig.COMMON.debugBranching.get();
        } catch (Throwable t) {
            // Config not loaded (unit tests, early startup): the quieter path is the safe one.
            return false;
        }
    }
}
