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
     * The stale-offer preflight, which must run before any content-dependent lookup.
     *
     * <p>{@link #evaluate} already refuses a submission whose offer was minted under content a reload
     * has replaced, but it runs after MCA's constraint check and the catalog's age gate. Those are
     * both content lookups, so asking them about an offer that is already known to be stale means
     * resolving a question name against a body of content that no longer matches the answers the
     * player is looking at. This is the same refusal, taken first; {@link #evaluate} keeps its own,
     * because it is also reached from paths that do not come through here.
     */
    public static ChoiceOutcome preflight(UUID playerId, String question, long now) {
        if (!isOurQuestion(question) || playerId == null) {
            return ChoiceOutcome.CONSUMED;
        }
        ConversationSession session = ConversationSessions.raw(playerId).orElse(null);
        if (session == null) {
            return ChoiceOutcome.CONSUMED;
        }
        ConversationSession.ChoiceOffer offer = session.currentOffer().orElse(null);
        if (offer == null || offer.generation() == ContentGeneration.current()) {
            return ChoiceOutcome.CONSUMED;
        }
        session.clearOffer();
        ConversationSessions.endTopic(playerId, now, CloseReason.CONTENT_RELOADED);
        return reject(playerId, question, "<preflight>", ChoiceOutcome.CONTENT_RELOADED,
                "content was reloaded after the offer was made");
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
        return !evaluate(playerId, villagerId, question, answer, otherPlayerInteracting, now).ok();
    }

    /**
     * The same decision, naming what was wrong instead of only that something was.
     *
     * <p>{@link ChoiceOutcome#CONSUMED} means "let it through" — including for a native MCA question,
     * which this guard has no business judging. Every other value is a refusal the caller may explain
     * to the player.
     */
    public static ChoiceOutcome evaluate(UUID playerId, UUID villagerId, String question, String answer,
                                         boolean otherPlayerInteracting, long now) {
        if (!isOurQuestion(question)) {
            return ChoiceOutcome.CONSUMED;
        }
        if (playerId == null || villagerId == null || answer == null) {
            return ChoiceOutcome.INVALID_SUBMISSION;
        }
        if (otherPlayerInteracting) {
            return reject(playerId, question, answer, ChoiceOutcome.OWNERSHIP_MISMATCH,
                    "villager is mid-conversation with another player");
        }
        ConversationSession session = ConversationSessions.raw(playerId).orElse(null);
        if (session == null || session.currentQuestion() == null) {
            return reject(playerId, question, answer, ChoiceOutcome.INVALID_SUBMISSION,
                    "there is no live offer");
        }
        ConversationSession.ChoiceOffer offer = session.currentOffer().orElseThrow();
        if (offer.frontend() != ConversationSession.Frontend.GUI) {
            return reject(playerId, question, answer, ChoiceOutcome.INVALID_SUBMISSION,
                    "a chat offer cannot be submitted as a GUI packet");
        }
        if (offer.villagerId() != null && !offer.villagerId().equals(villagerId)) {
            return reject(playerId, question, answer, ChoiceOutcome.OWNERSHIP_MISMATCH,
                    "offer belongs to another villager");
        }
        if (!session.wasOffered(question, answer)) {
            return reject(playerId, question, answer, ChoiceOutcome.INVALID_SUBMISSION,
                    "answer was not among the offered choices for " + session.currentQuestion());
        }
        if (!session.consumeOfferedAnswer(question, answer)) {
            return reject(playerId, question, answer, ChoiceOutcome.INVALID_SUBMISSION,
                    "offer revision was already consumed");
        }
        if (offer.generation() != ContentGeneration.current()) {
            // The answers on screen were written by content a reload has since replaced. Drop the
            // page and close the topic safely — nothing runs — so the next click is refused for the
            // ordinary reason instead of executing against a catalog that no longer exists.
            session.clearOffer();
            ConversationSessions.endTopic(playerId, now, CloseReason.CONTENT_RELOADED);
            return reject(playerId, question, answer, ChoiceOutcome.CONTENT_RELOADED,
                    "content was reloaded after the offer was made");
        }
        session.setVillagerId(villagerId);
        session.touch(now);
        return ChoiceOutcome.CONSUMED;
    }

    private static ChoiceOutcome reject(UUID playerId, String question, String answer,
                                        ChoiceOutcome outcome, String why) {
        if (debugBranching()) {
            McaConversations.LOGGER.info("[branch] rejected submission {}/{} from {}: {}",
                    question, answer, playerId, why);
        } else {
            McaConversations.LOGGER.debug("rejected dialogue submission {}/{} from {}: {}",
                    question, answer, playerId, why);
        }
        return outcome;
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
