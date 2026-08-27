package dev.otectus.mcaconversations.village;

import java.util.Locale;
import java.util.Optional;

/**
 * What one resident makes of one of their village's own tokens (spec §17.3).
 *
 * <p>The plan's sentence is the whole design: "Culture creates common ground without making every
 * resident agree." A village that every resident endorsed would be a hive; a village nobody had a
 * view about would be scenery. So a token reaches each villager through their identity, and comes out
 * as one of three postures — which is what makes the festival worth asking two different people
 * about.
 *
 * <p>Ignoring is the default and is not a failure state. Most people have no opinion about most of
 * what their village believes about itself, and a scene that treats indifference as hostility gets
 * villagers wrong.
 */
public enum CultureStance {

    /** They are for it, and will say so. */
    ENDORSE("endorse"),

    /** They go along with it and have a reservation they will voice if asked. */
    QUESTION("question"),

    /** It is simply the weather to them. The default, and the commonest. */
    IGNORE("ignore");

    private final String key;

    CultureStance(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when the villager has something of their own to say about it. */
    public boolean hasAView() {
        return this != IGNORE;
    }

    public static Optional<CultureStance> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (CultureStance stance : values()) {
            if (stance.key.equals(normalized)) {
                return Optional.of(stance);
            }
        }
        return Optional.empty();
    }
}
