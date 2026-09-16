package dev.otectus.mcaconversations.conversation;

import java.util.ArrayList;
import java.util.List;

/**
 * One reload's worth of work, from the moment the event fires to the moment it commits or is thrown
 * away. Every problem, every section verdict and the identity of the MCA instance the attempt is
 * bound to live here, so a late callback from a superseded attempt is recognisable as such.
 *
 * <p>Attempt ids advance separately from generations: several rejected attempts in a row all target
 * the same next generation, because none of them reached it.
 */
public final class ContentReloadAttempt {

    /** Where an attempt is in the sequence the boundary document describes. */
    public enum Verdict {
        /** Staged, cross-validated, and waiting for MCA to parse the executable half. */
        READY_FOR_MCA,
        /** Something was wrong. Nothing publishes; the previous bundle stays in force. */
        REJECTED,
        /** Committed. Exactly one bundle and one generation were published. */
        COMMITTED
    }

    private final long id;
    private final ConversationContentBundle previous;
    private final long targetGeneration;
    private final List<ContentProblem> problems = new ArrayList<>();

    private volatile ConversationContentBundle staged;
    private volatile Verdict verdict = Verdict.REJECTED;
    private volatile Object mcaInstance;
    private volatile boolean applyStarted;
    private volatile boolean applyFinished;
    private volatile boolean tailObserved;

    ContentReloadAttempt(long id, ConversationContentBundle previous) {
        this.id = id;
        this.previous = previous == null ? ConversationContentBundle.UNAVAILABLE : previous;
        this.targetGeneration = this.previous.generation() + 1L;
    }

    public long id() {
        return id;
    }

    /** The bundle that must still be in force if this attempt is rejected. */
    public ConversationContentBundle previous() {
        return previous;
    }

    /** The generation this attempt is trying to reach; only a commit actually reaches it. */
    public long targetGeneration() {
        return targetGeneration;
    }

    public List<ContentProblem> problems() {
        return List.copyOf(problems);
    }

    public ConversationContentBundle staged() {
        return staged;
    }

    public Verdict verdict() {
        return verdict;
    }

    public boolean tailObserved() {
        return tailObserved;
    }

    public boolean applyStarted() {
        return applyStarted;
    }

    public boolean applyFinished() {
        return applyFinished;
    }

    /** The exact MCA listener instance this attempt's executable half belongs to. */
    public Object mcaInstance() {
        return mcaInstance;
    }

    void record(List<ContentProblem> found) {
        if (found == null) {
            return;
        }
        found.forEach(problem -> problems.add(problem.stamped(id, targetGeneration)));
    }

    void record(ContentProblem problem) {
        if (problem != null) {
            problems.add(problem.stamped(id, targetGeneration));
        }
    }

    void stage(ConversationContentBundle bundle) {
        this.staged = bundle;
    }

    void verdict(Verdict value) {
        this.verdict = value;
    }

    void mcaInstance(Object instance) {
        this.mcaInstance = instance;
    }

    void applyStarted(boolean value) {
        this.applyStarted = value;
    }

    void applyFinished(boolean value) {
        this.applyFinished = value;
    }

    void tailObserved(boolean value) {
        this.tailObserved = value;
    }

    /** True when at least one recorded problem forbids publication. */
    public boolean fatal() {
        return problems.stream().anyMatch(p -> p.severity().fatal());
    }
}
