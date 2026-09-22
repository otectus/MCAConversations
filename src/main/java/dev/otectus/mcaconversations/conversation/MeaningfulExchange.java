package dev.otectus.mcaconversations.conversation;

/**
 * Which executed replies count as having actually talked (Stability spec §8.5).
 *
 * <p>Navigation is not conversation. Opening the hub, picking a category and pressing "back" move
 * the player around a menu; they earn no contact day. A reply on one of this mod's own questions, or
 * a catalog starter wherever it is merged, does — the villager answered something the player chose to
 * say. Everything that is not an executed reply (a heartbeat, a greeting, reading a card) never
 * reaches this test at all.
 */
public final class MeaningfulExchange {

    private MeaningfulExchange() {
    }

    public static boolean counts(String question, String answer) {
        if (question == null || answer == null) {
            return false;
        }
        if ("conversations".equals(question) || "main".equals(question) || "back".equals(answer)) {
            return false;
        }
        return ConversationGuard.isOurQuestion(question) || TopicGate.isCatalogStarter(question, answer);
    }
}
