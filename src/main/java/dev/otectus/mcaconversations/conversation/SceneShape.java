package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * The rhetorical form of an exchange (spec §9.4).
 *
 * <p>This enum exists because of a specific way a large corpus fails. Two scenes can share no id, no
 * subject and no noun, and still be the same conversation: the villager names a problem, the player
 * offers help, the villager is grateful. Do that eight times with eight different problems and the
 * player has had one conversation eight times. Suppressing repeats by scene id cannot see it;
 * suppressing by shape can.
 *
 * <p>Every scene declares one. The director penalises a recently used shape even when the nouns
 * differ, and the coverage report flags any profession whose scenes collapse onto one or two.
 */
public enum SceneShape {

    /** A problem is named, help or advice is weighed, something is decided. */
    PROBLEM_SOLVE("problem_solve"),

    /** Something past is recalled, and what it meant is negotiated. */
    REMINISCE("reminisce"),

    /** Two positions are held, argued and possibly moved. */
    DEBATE("debate"),

    /** The villager explains, and the player is invited to show they understood. */
    TEACH_BACK("teach_back"),

    /** Something guarded is offered, and how it is received matters. */
    CONFIDE("confide"),

    /** Something went well, and the exchange is about sharing that. */
    CELEBRATE("celebrate"),

    /** Something is broken, and the exchange is about whether it mends. */
    REPAIR("repair"),

    /** What happens next is worked out together. */
    PLAN("plan"),

    /** Something present is noticed and remarked on, with nothing at stake. */
    OBSERVE("observe");

    private final String key;

    SceneShape(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /**
     * How many days this shape should stay suppressed after use.
     *
     * <p>Not uniform. Low-stakes shapes recur naturally — people do remark on the weather two days
     * running — while a confession or a repair landing twice in a week reads as a system, not a
     * person.
     */
    public int cooldownDays() {
        return switch (this) {
            case OBSERVE -> 1;
            case PROBLEM_SOLVE, PLAN, TEACH_BACK -> 2;
            case REMINISCE, DEBATE, CELEBRATE -> 3;
            case CONFIDE, REPAIR -> 5;
        };
    }

    /** True when this shape is too weighty to be selected as ordinary unprompted small talk. */
    public boolean isHighStakes() {
        return this == CONFIDE || this == REPAIR;
    }

    public static Optional<SceneShape> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (SceneShape shape : values()) {
            if (shape.key.equals(normalized)) {
                return Optional.of(shape);
            }
        }
        return Optional.empty();
    }
}
