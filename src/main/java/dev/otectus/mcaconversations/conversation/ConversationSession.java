package dev.otectus.mcaconversations.conversation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
 *   <li><b>What was just said, and what does it mean?</b> — the semantic turn state: the current beat,
 *       what the villager just did with it, how the player's last line landed, and the facts this
 *       exchange has established so far (spec §6.4).</li>
 * </ul>
 *
 * <p>The turn state is bounded on purpose. Facts are capped, the beat history is a short ring, and
 * none of it is persisted — anything that must outlive the conversation is written explicitly as a
 * callback through the progress and memory stores.</p>
 *
 * <p>Mutated on the server thread only, from the dialogue actions, the network mixins and the chat
 * dispatcher.
 */
public final class ConversationSession {

    /** Which frontend last drove this session. Diagnostics only — behaviour never branches on it. */
    public enum Frontend { GUI, CHAT }

    /** Immutable view of the exact ordered answer set MCA most recently offered this player. */
    public record ChoiceOffer(long revision, UUID villagerId, String questionId,
                              List<String> answerIds, Frontend frontend,
                              long createdGameTime, boolean consumed) {
        public ChoiceOffer {
            answerIds = answerIds == null ? List.of() : List.copyOf(answerIds);
        }
    }

    private static final int MAX_TRANSACTIONS = 8;

    /** How many beats back anti-repetition and the debug trace can see. */
    private static final int MAX_RECENT_BEATS = 8;

    /**
     * Ceiling on facts one exchange may establish. A conversation that has said more than this many
     * distinct things has drifted; the cap keeps the per-click checks trivially cheap (spec §6.10).
     */
    private static final int MAX_TURN_FACTS = 24;

    private final UUID playerId;

    private UUID villagerId;
    private String topicId;
    private DepthClass budget = DepthClass.QUICK;
    private String branch;
    private String currentQuestion;
    private List<String> currentAnswers = List.of();
    private UUID offerVillagerId;
    private long offerRevision;
    private long offerCreatedGameTime;
    private boolean offerConsumed;
    private Frontend offerFrontend = Frontend.GUI;
    private long startedGameTime;
    private long lastActivityGameTime;
    private int positiveApplied;
    private int negativeApplied;
    private Frontend frontend = Frontend.GUI;

    private String currentBeatId;
    private String currentSubject;
    private NpcSpeechAct lastNpcAct;
    private OutcomeFamily lastOutcome;
    private StanceFamily lastPlayerStance;

    private dev.otectus.mcaconversations.scene.ConversationPlan plan;
    private dev.otectus.mcaconversations.context.ConversationContextSnapshot snapshot;

    private final Set<SemanticFact> turnFacts = new LinkedHashSet<>();
    private final Deque<String> recentBeats = new ArrayDeque<>();
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

    /** Records a new offer and returns its monotonically increasing immutable snapshot. */
    public ChoiceOffer setOffer(String question, List<String> answers, Frontend frontend,
                                UUID offeredVillagerId, long now) {
        if (offeredVillagerId != null) {
            setVillagerId(offeredVillagerId);
        }
        // GUI response packets do not identify a villager. Keep that absence explicit instead of
        // leaking the broader session's previous villager into this offer and rejecting a valid
        // candidate captured from the currently open interaction screen.
        this.offerVillagerId = offeredVillagerId;
        this.offerRevision++;
        this.currentQuestion = question == null ? "" : question;
        this.currentAnswers = answers == null ? List.of() : List.copyOf(answers);
        this.offerFrontend = frontend == null ? Frontend.GUI : frontend;
        this.offerCreatedGameTime = now;
        this.offerConsumed = false;
        return currentOffer().orElseThrow();
    }

    public Optional<ChoiceOffer> currentOffer() {
        if (currentQuestion == null) {
            return Optional.empty();
        }
        return Optional.of(new ChoiceOffer(offerRevision, offerVillagerId, currentQuestion,
                currentAnswers, offerFrontend, offerCreatedGameTime, offerConsumed));
    }

    /**
     * Claims one exact offered index before dialogue execution. The server thread is the normal
     * caller, but synchronization makes the replay guarantee explicit and testable.
     */
    public synchronized Optional<String> consumeOffer(long revision, int absoluteIndex) {
        if (currentQuestion == null || offerConsumed || revision != offerRevision
                || absoluteIndex < 0 || absoluteIndex >= currentAnswers.size()) {
            return Optional.empty();
        }
        offerConsumed = true;
        return Optional.of(currentAnswers.get(absoluteIndex));
    }

    /** Claims the exact answer sent through MCA's ordinary mouse packet. */
    public synchronized boolean consumeOfferedAnswer(String question, String answer) {
        if (currentQuestion == null || offerConsumed || !currentQuestion.equals(question)) {
            return false;
        }
        int index = currentAnswers.indexOf(answer);
        return index >= 0 && consumeOffer(offerRevision, index).isPresent();
    }

    // --- Frozen plan and context snapshot (spec §9.3, §10.5) --------------------

    /**
     * The scene the director chose for this exchange, if the dynamic layer produced one.
     *
     * <p>Held here, on the session both frontends share, precisely so that reopening the screen or
     * switching between the GUI and chat reuses the same decision instead of re-running selection.
     * That is the whole of reroll resistance: a player who does not like the subject cannot close and
     * reopen until they get a different one.
     */
    public Optional<dev.otectus.mcaconversations.scene.ConversationPlan> plan() {
        return Optional.ofNullable(plan);
    }

    /** Freezes a plan onto the session. Replacing one is legal only when starting a new topic. */
    public void setPlan(dev.otectus.mcaconversations.scene.ConversationPlan plan) {
        this.plan = plan;
    }

    /**
     * The world as it was when this exchange began.
     *
     * <p>Captured once and reused by every condition, so two checks in one click cannot see two
     * different worlds. Volatile fields are refreshed through
     * {@link #refreshSnapshot}; pinned fields never change for the life of the scene (spec §7.4).
     */
    public Optional<dev.otectus.mcaconversations.context.ConversationContextSnapshot> snapshot() {
        return Optional.ofNullable(snapshot);
    }

    public void setSnapshot(dev.otectus.mcaconversations.context.ConversationContextSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    /** Merges a fresh capture's volatile fields onto the pinned snapshot at a turn boundary. */
    public void refreshSnapshot(dev.otectus.mcaconversations.context.ConversationContextSnapshot fresh) {
        if (snapshot != null && fresh != null) {
            snapshot = snapshot.refreshed(fresh);
        }
    }

    /** Drops the open question without ending the conversation (a subject change, "never mind"). */
    public void clearOffer() {
        this.currentQuestion = null;
        this.currentAnswers = List.of();
        this.offerVillagerId = null;
        this.offerConsumed = false;
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
        resetTurn();
    }

    /** Ends the current topic but keeps the session (the player is still standing there). */
    public void endTopic() {
        resetTopic();
    }

    private void resetTopic() {
        // The plan and the snapshot belong to the exchange, not to the session object: carrying them
        // into the next topic would let one scene's pinned referents leak into another's.
        this.plan = null;
        this.snapshot = null;
        this.topicId = null;
        this.branch = null;
        this.budget = DepthClass.QUICK;
        this.positiveApplied = 0;
        this.negativeApplied = 0;
        clearOffer();
        resetTurn();
    }

    // --- Semantic turn state ----------------------------------------------------

    /** The beat the villager is currently standing on, if the route was contracted. */
    public Optional<String> currentBeatId() {
        return Optional.ofNullable(currentBeatId);
    }

    /** The subject under discussion — {@code work.farmer.crop_health}, not the topic label. */
    public Optional<String> currentSubject() {
        return Optional.ofNullable(currentSubject);
    }

    /** What the villager's last line did. */
    public Optional<NpcSpeechAct> lastNpcAct() {
        return Optional.ofNullable(lastNpcAct);
    }

    /** How the villager took the player's last line, when the last beat was a reaction. */
    public Optional<OutcomeFamily> lastOutcome() {
        return Optional.ofNullable(lastOutcome);
    }

    /** What the player's last button was doing. */
    public Optional<StanceFamily> lastPlayerStance() {
        return Optional.ofNullable(lastPlayerStance);
    }

    /** Everything this exchange has established, in the order it was established. */
    public Set<SemanticFact> turnFacts() {
        return Set.copyOf(turnFacts);
    }

    /** True when {@code fact} has been established by something already said in this exchange. */
    public boolean knows(SemanticFact fact) {
        return turnFacts.contains(fact);
    }

    /** The most recent beats, newest last. Anti-repetition and the debug trace read this. */
    public List<String> recentBeats() {
        return List.copyOf(recentBeats);
    }

    /** True when {@code beatId} has already been played in this exchange. */
    public boolean playedRecently(String beatId) {
        return recentBeats.contains(beatId);
    }

    /**
     * Moves the villager onto {@code beat}: records what was said, adds the facts it establishes, and
     * pushes it onto the recent ring. Called from the {@code conversations_session} turn op as the
     * result's actions run, before {@code say} delivers the line.
     */
    public void enterBeat(BeatContract beat) {
        if (beat == null) {
            return;
        }
        this.currentBeatId = beat.id();
        this.currentSubject = beat.subject();
        this.lastNpcAct = beat.npcAct();
        this.lastOutcome = beat.outcome().orElse(null);
        beat.facts().forEach(this::recordFact);
        recentBeats.addLast(beat.id());
        while (recentBeats.size() > MAX_RECENT_BEATS) {
            recentBeats.removeFirst();
        }
    }

    /** Records what the player's chosen button meant, and the facts its wording introduces. */
    public void recordPlayerStance(StanceFamily stance, Iterable<SemanticFact> introduced) {
        this.lastPlayerStance = stance;
        if (introduced != null) {
            introduced.forEach(this::recordFact);
        }
    }

    private void recordFact(SemanticFact fact) {
        if (fact == null || turnFacts.contains(fact)) {
            return;
        }
        if (turnFacts.size() >= MAX_TURN_FACTS) {
            return;
        }
        turnFacts.add(fact);
    }

    private void resetTurn() {
        this.currentBeatId = null;
        this.currentSubject = null;
        this.lastNpcAct = null;
        this.lastOutcome = null;
        this.lastPlayerStance = null;
        this.turnFacts.clear();
        this.recentBeats.clear();
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
