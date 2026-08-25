package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * How much conversation a topic is worth, and therefore how much it may move hearts (plan §4.2, §5.2).
 *
 * <p>Each class fixes three things a topic cannot argue with:
 * <ul>
 *   <li>a <b>per-conversation</b> positive and negative heart budget, counted separately so a player
 *       cannot antagonise a villager first to manufacture extra room to earn hearts back;</li>
 *   <li>the <b>minimum number of player decisions</b> the graph must offer on a normal adult path —
 *       the rule that stops a "converted" topic from quietly staying a one-click response;</li>
 *   <li>the shared {@link #MAX_DECISIONS} ceiling, so no ordinary topic becomes an endless chain.</li>
 * </ul>
 *
 * <p>The budgets here are the <em>authored</em> ceiling. The runtime clamps again against the per-day
 * budget and the config multiplier, so the number a player actually receives is always ≤ these.
 */
public enum DepthClass {

    /** Weather, season, food, a routine check-in. Small stakes, but still a real exchange. */
    QUICK("quick", 2, 3, 2),
    /** Work, village, neighbours, news, general life. */
    STANDARD("standard", 4, 5, 2),
    /** Dreams, fears, hopes, regrets, secrets — cross-session arcs live here. */
    DEEP("deep", 8, 10, 3),
    /** Feelings, spouse, parent/child/family. Same stakes as deep, stricter age and romance gates. */
    RELATIONSHIP("relationship", 8, 10, 3),
    /** A work offer or quest handoff: little or no immediate affection; the quest owns the payoff. */
    SERVICE("service", 2, 2, 1);

    /** No ordinary topic may ask the player to choose more than this many times (plan §4.3). */
    public static final int MAX_DECISIONS = 5;

    private final String key;
    private final int positiveBudget;
    private final int negativeBudget;
    private final int minDecisions;

    DepthClass(String key, int positiveBudget, int negativeBudget, int minDecisions) {
        this.key = key;
        this.positiveBudget = positiveBudget;
        this.negativeBudget = negativeBudget;
        this.minDecisions = minDecisions;
    }

    public String key() {
        return key;
    }

    /** Most hearts one conversation of this class may grant, before the per-day budget is applied. */
    public int positiveBudget() {
        return positiveBudget;
    }

    /** Most hearts one conversation of this class may take, as a positive number. */
    public int negativeBudget() {
        return negativeBudget;
    }

    /** Fewest player decisions a topic of this class must offer after its opener. */
    public int minDecisions() {
        return minDecisions;
    }

    public static Optional<DepthClass> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (DepthClass depth : values()) {
            if (depth.key.equals(normalized)) {
                return Optional.of(depth);
            }
        }
        return Optional.empty();
    }
}
