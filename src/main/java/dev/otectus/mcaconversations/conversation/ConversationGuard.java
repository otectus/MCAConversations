package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;

import java.util.UUID;

/**
 * Server-side validation of a GUI dialogue submission (plan §7.2).
 *
 * <p>MCA's {@code InteractionDialogueMessage.receive} resolves the villager by UUID and runs the
 * answer with no distance check, no open-screen check, no constraint re-check and no replay
 * protection — verified identical in 7.6.20 and 7.7.0-beta.2. That is tolerable while a click is
 * worth a couple of hearts; it is not once a click can set a one-shot milestone or advance an arc.
 *
 * <p>This guard is deliberately narrow:
 * <ul>
 *   <li>it only ever judges <b>this mod's own questions</b>; every native MCA question passes
 *       through untouched, so MCA's dialogue semantics are unchanged for everyone else;</li>
 *   <li>it rejects an answer that was <b>not in the set the player was actually offered</b>, which is
 *       recorded from MCA's own outgoing packet and is therefore exactly what the screen showed;</li>
 *   <li>it rejects a <b>repeat of the same submission within the same tick</b> — the signature of a
 *       duplicated or replayed packet, and something no human produces;</li>
 *   <li>it rejects a submission aimed at a villager that MCA says is <b>mid-conversation with a
 *       different player</b>.</li>
 * </ul>
 *
 * <p>Everywhere else it fails <b>open</b>: no session, no recorded offer, or an uncertain MCA state
 * all mean "allow", because a guard that breaks legitimate conversation is worse than the exploit it
 * prevents. The guarded affection and progress actions enforce idempotency, caps and age safety on
 * their own regardless, so this layer is defence in depth rather than the only line.
 */
public final class ConversationGuard {

    private ConversationGuard() {
    }

    /** True when the question belongs to this mod and is therefore ours to validate. */
    public static boolean isOurQuestion(String question) {
        return question != null && question.startsWith("conversations");
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
        if (playerId == null || !isOurQuestion(question) || answer == null) {
            return false;
        }
        if (otherPlayerInteracting) {
            return reject(playerId, question, answer, "villager is mid-conversation with another player");
        }
        ConversationSession session = ConversationSessions.raw(playerId).orElse(null);
        if (session == null || session.currentQuestion() == null) {
            // Never saw an offer for this player (fresh join, reload, or the packet mixin not applying).
            // Nothing to compare against, so let MCA handle it as it always has.
            return false;
        }
        if (!session.wasOffered(question, answer)) {
            return reject(playerId, question, answer, "answer was not among the offered choices for "
                    + session.currentQuestion());
        }
        if (!session.claimTransaction(question + "|" + answer + "|" + villagerId + "@" + now)) {
            return reject(playerId, question, answer, "duplicate submission in the same tick");
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
