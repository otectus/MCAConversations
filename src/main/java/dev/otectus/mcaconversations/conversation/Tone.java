package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * How a player button <em>sounds</em>, as distinct from what it wants (spec §5.3, §10.7).
 *
 * <p>Stance says the player is disagreeing; tone says whether they are disagreeing kindly or
 * contemptuously. Keeping them apart is what lets lint enforce consequence honesty: a button worded
 * gently must not be authored as an insult behind the player's back, and a button worded
 * contemptuously must not quietly earn hearts.
 */
public enum Tone {

    /** Careful, soft, deliberately unpushy. */
    GENTLE("gentle", 1),
    /** Ordinary, unmarked speech. */
    PLAIN("plain", 0),
    /** Light, teasing, fond. */
    PLAYFUL("playful", 0),
    /** Direct to the point of rudeness, but not meant to wound. */
    BLUNT("blunt", -1),
    /** Meant to wound, belittle or drive off. */
    HOSTILE("hostile", -2),
    /** Warm in a way only a partner or family member may be. */
    INTIMATE("intimate", 1);

    private final String key;
    private final int warmth;

    Tone(String key, int warmth) {
        this.key = key;
        this.warmth = warmth;
    }

    public String key() {
        return key;
    }

    /** Rough sign of the button's warmth; used to catch wording that contradicts its consequence. */
    public int warmth() {
        return warmth;
    }

    public static Optional<Tone> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (Tone tone : values()) {
            if (tone.key.equals(normalized)) {
                return Optional.of(tone);
            }
        }
        return Optional.empty();
    }
}
