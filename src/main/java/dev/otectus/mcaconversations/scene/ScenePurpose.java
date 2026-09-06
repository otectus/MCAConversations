package dev.otectus.mcaconversations.scene;

import java.util.Locale;
import java.util.Optional;

/**
 * Why a scene would be selected, and therefore which index it lives in (spec §9.1, §11.1).
 *
 * <p>The index lookup is the first stage of the candidate pipeline and the reason the director never
 * scans the whole catalog. A purpose plus a topic is enough to cut thousands of scenes to a handful
 * before any eligibility check runs, which is what keeps the per-interaction cost flat as the corpus
 * grows (spec §21.6).
 *
 * <p>{@link #interruptionCost} is attached here rather than to each scene because it is a property of
 * the <em>kind</em> of thing being done: interrupting a working villager to remark on the weather is
 * expensive whatever the weather scene says, and telling them they are bleeding is not.
 */
public enum ScenePurpose {

    /** The player chose a topic from the hub. The ordinary case. */
    TOPIC("topic", 0),

    /** The villager opens with a callback to a ready thread. */
    GREETING("greeting", 2),

    /** An episode changed state since it was last mentioned. */
    STATE_CHANGE("state_change", 2),

    /** A promise has come due. */
    DUE_COMMITMENT("due_commitment", 3),

    /**
     * Something about the player's current state overrides ordinary small talk — injury, danger.
     *
     * <p>Zero cost by design: the whole point of an acute scene is that it is worth interrupting
     * anything for, including a villager mid-chore (spec §11.1).
     */
    ACUTE("acute", 0),

    /** Something happened in the village worth telling. */
    SHARED_EVENT("shared_event", 4),

    /** The villager wants the player's view on something. */
    OPINION_REQUEST("opinion_request", 6),

    /** Something is broken between them and has not been acknowledged. */
    REPAIR("repair", 3),

    /**
     * The player's standing with this village has just crossed a tier, and this resident knows a deed
     * behind it.
     *
     * <p>Costs the same as {@link #REPAIR}: a village changing its mind about somebody is worth about
     * as much of their attention as a rupture between the two of them, and no more. Only ever live
     * with MCA: Reputation installed, which is what keeps it silent on an ordinary install.
     */
    STANDING_REMARK("standing_remark", 3),

    /**
     * This villager's own place at court has just changed, and the player has not heard it from them.
     *
     * <p>Costs the same as {@link #STANDING_REMARK}: being made Hand is exactly the kind of news
     * somebody stops what they are doing to tell you, and exactly as easily overdone. Only ever live
     * with MCA Capitals installed, which is what keeps it silent on an ordinary install.
     */
    COURT_REMARK("court_remark", 3),

    /** A comfort, an origin motif, a remark that costs nothing and means something. */
    LOW_STAKES("low_stakes", 8),

    /** Picking a subject back up after time has passed. */
    RESUME("resume", 2);

    private final String key;
    private final int interruptionCost;

    ScenePurpose(String key, int interruptionCost) {
        this.key = key;
        this.interruptionCost = interruptionCost;
    }

    public String key() {
        return key;
    }

    /** Score penalty for opening this unprompted while the villager is busy (spec §9.2). */
    public int interruptionCost() {
        return interruptionCost;
    }

    /** True when the villager, not the player, opens the exchange. */
    public boolean isInitiative() {
        return this != TOPIC;
    }

    /**
     * True when this purpose may be raised even while the villager is working, panicking or grieving.
     *
     * <p>Only the acute case. A due promise is important but can wait until the fire is out.
     */
    public boolean overridesBusyState() {
        return this == ACUTE;
    }

    /** True when this purpose counts against the daily unprompted-initiative cap (spec §11.2). */
    public boolean countsAgainstDailyCap() {
        return isInitiative() && this != ACUTE && this != STATE_CHANGE;
    }

    public static Optional<ScenePurpose> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        int colon = normalized.indexOf(':');
        String head = colon > 0 ? normalized.substring(0, colon) : normalized;
        for (ScenePurpose purpose : values()) {
            if (purpose.key.equals(head)) {
                return Optional.of(purpose);
            }
        }
        return Optional.empty();
    }

    /** The topic id from a {@code topic:work} purpose string, or empty for any other form. */
    public static Optional<String> topicOf(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        int colon = normalized.indexOf(':');
        return colon > 0 && colon < normalized.length() - 1
                ? Optional.of(normalized.substring(colon + 1))
                : Optional.empty();
    }
}
