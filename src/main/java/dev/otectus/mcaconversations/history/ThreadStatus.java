package dev.otectus.mcaconversations.history;

import java.util.Locale;
import java.util.Optional;

/**
 * What a shared conversation thread is waiting for (spec §8.4).
 *
 * <p>A thread is not a second dialogue tree. It is a small frame that answers one question — "may this
 * pair resume this subject, and on what footing?" — and the status is the answer. Separating "waiting
 * on the world" from "waiting on the player" is what lets a villager say "the book dried, mostly"
 * without also implying the player was supposed to do something about it.
 */
public enum ThreadStatus {

    /** Raised, live, nothing outstanding. Ordinary continuation. */
    OPEN("open"),

    /** Something in the world has to change first. The villager will not ask again yet. */
    WAITING_ON_WORLD("waiting_on_world"),

    /** The player owes an answer or an action. The villager may check, within the daily cap. */
    WAITING_ON_PLAYER("waiting_on_player"),

    /** A change happened and has not been mentioned. The highest-value thing to open with. */
    READY_TO_RESUME("ready_to_resume"),

    /** Finished. Still recallable as a shared memory; never re-offered as business. */
    RESOLVED("resolved"),

    /** Quietly expired. Neither party is pretending it is still live (spec §11.5). */
    LAPSED("lapsed"),

    /** Broken by something said. Nothing else about this subject until it is repaired. */
    RUPTURED("ruptured");

    private final String key;

    ThreadStatus(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True while the thread is still business between the two of them. */
    public boolean isLive() {
        return this == OPEN || this == WAITING_ON_WORLD || this == WAITING_ON_PLAYER
                || this == READY_TO_RESUME;
    }

    /** True when the thread may be offered as something to continue right now. */
    public boolean isResumable() {
        return this == READY_TO_RESUME || this == OPEN || this == WAITING_ON_PLAYER;
    }

    /**
     * True when this thread must be acknowledged before ordinary subjects are available.
     *
     * <p>Only rupture. An overdue commitment is urgent but not blocking: a villager who is annoyed
     * with you will still talk about the weather, and one who feels wronged will not (spec §9.5).
     */
    public boolean blocksOrdinaryTopics() {
        return this == RUPTURED;
    }

    /** True when the thread is finished and may be pruned once nothing else references it. */
    public boolean isClosed() {
        return this == RESOLVED || this == LAPSED;
    }

    public static Optional<ThreadStatus> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (ThreadStatus status : values()) {
            if (status.key.equals(normalized)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
