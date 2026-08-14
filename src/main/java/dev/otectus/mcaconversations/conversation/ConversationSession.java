package dev.otectus.mcaconversations.conversation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * One player's live conversation, shared by both frontends (plan §7.1). Transient by design: it is
 * never persisted, and a restart or relog loses nothing that matters, because every durable
 * consequence lives in MCA memories, the progress ledger, or the disposition store.
 *
 * <p>It answers four questions the rest of the system keeps asking:
 * <ul>
 *   <li><b>Who is talking, about what?</b> — villager, topic, depth class, current branch.</li>
 *   <li><b>What was the player actually offered?</b> — the current question and its constraint-filtered
 *       answers, so a submitted answer can be checked against what was on screen.</li>
 *   <li><b>How much has this conversation already paid out?</b> — the per-conversation heart budget
 *       counters that {@code conversations_affection_apply} clamps against.</li>
 *   <li><b>Have I seen this exact submission already?</b> — a short ring of transaction ids.</li>
 * </ul>
 *
 * <p>Mutated on the server thread only, from the dialogue actions, the network mixins and the chat
 * dispatcher.
 */
public final class ConversationSession {

    /** Which frontend last drove this session. Diagnostics only — behaviour never branches on it. */
    public enum Frontend { GUI, CHAT }

    private static final int MAX_TRANSACTIONS = 8;

    private final UUID playerId;

    private UUID villagerId;
    private String topicId;
    private DepthClass budget = DepthClass.QUICK;
    private String branch;
    private String currentQuestion;
    private List<String> currentAnswers = List.of();
    private long startedGameTime;
    private long lastActivityGameTime;
    private int positiveApplied;
    private int negativeApplied;
    private Frontend frontend = Frontend.GUI;

    private final Deque<String> transactions = new ArrayDeque<>();

    ConversationSession(UUID playerId, long now) {
        this.playerId = playerId;
        this.startedGameTime = now;
        this.lastActivityGameTime = now;
    }

    public UUID playerId() {
        return playerId;
    }

    public UUID villagerId() {
        return villagerId;
    }

    public void setVillagerId(UUID villagerId) {
        if (this.villagerId != null && !this.villagerId.equals(villagerId)) {
            // Switching target ends the old conversation: its budget must not carry over.
            resetTopic();
        }
        this.villagerId = villagerId;
    }

    public Optional<String> topicId() {
        return Optional.ofNullable(topicId);
    }

    public DepthClass budget() {
        return budget;
    }

    public Optional<String> branch() {
        return Optional.ofNullable(branch);
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String currentQuestion() {
        return currentQuestion;
    }

    public List<String> currentAnswers() {
        return currentAnswers;
    }

    /** True when {@code answer} was among the answers this player was last offered for {@code question}. */
    public boolean wasOffered(String question, String answer) {
        return question != null && question.equals(currentQuestion) && currentAnswers.contains(answer);
    }

    public void setOffer(String question, List<String> answers) {
        this.currentQuestion = question;
        this.currentAnswers = answers == null ? List.of() : List.copyOf(answers);
    }

    /** Drops the open question without ending the conversation (a subject change, "never mind"). */
    public void clearOffer() {
        this.currentQuestion = null;
        this.currentAnswers = List.of();
    }

    public long startedGameTime() {
        return startedGameTime;
    }

    public long lastActivityGameTime() {
        return lastActivityGameTime;
    }

    public void touch(long now) {
        this.lastActivityGameTime = now;
    }

    public Frontend frontend() {
        return frontend;
    }

    public void setFrontend(Frontend frontend) {
        this.frontend = frontend;
    }

    // --- Topic lifecycle --------------------------------------------------------

    /**
     * Starts a topic, resetting the per-conversation heart budget. Re-entering the same topic without
     * having left resets it too — the budget belongs to the exchange, not to the session object.
     */
    public void beginTopic(String topicId, DepthClass budget, long now) {
        this.topicId = topicId;
        this.budget = budget == null ? DepthClass.QUICK : budget;
        this.branch = null;
        this.positiveApplied = 0;
        this.negativeApplied = 0;
        this.startedGameTime = now;
        this.lastActivityGameTime = now;
    }

    /** Ends the current topic but keeps the session (the player is still standing there). */
    public void endTopic() {
        resetTopic();
    }

    private void resetTopic() {
        this.topicId = null;
        this.branch = null;
        this.budget = DepthClass.QUICK;
        this.positiveApplied = 0;
        this.negativeApplied = 0;
        clearOffer();
    }

    // --- Per-conversation heart budget -----------------------------------------

    public int positiveApplied() {
        return positiveApplied;
    }

    public int negativeApplied() {
        return negativeApplied;
    }

    /** Books an applied delta against this conversation's budget. */
    public void recordApplied(int granted) {
        if (granted > 0) {
            positiveApplied += granted;
        } else if (granted < 0) {
            negativeApplied += -granted;
        }
    }

    // --- Idempotency ------------------------------------------------------------

    /** Returns false when this exact submission has already been seen (a duplicated packet). */
    public boolean claimTransaction(String transactionId) {
        if (transactions.contains(transactionId)) {
            return false;
        }
        transactions.addLast(transactionId);
        while (transactions.size() > MAX_TRANSACTIONS) {
            transactions.removeFirst();
        }
        return true;
    }

    /** True when nothing has happened for longer than {@code timeoutTicks}. */
    public boolean isExpired(long now, int timeoutTicks) {
        return now - lastActivityGameTime > timeoutTicks;
    }
}
