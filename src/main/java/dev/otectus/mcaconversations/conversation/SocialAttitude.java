package dev.otectus.mcaconversations.conversation;

import java.util.Locale;

/**
 * How a villager is disposed to speak to this player right now (Stability spec §8.6).
 *
 * <p>A rendering and eligibility outcome derived from the band and roles, never stored and never a
 * score. It exists so a line family can ask for "cordial" without re-deriving it from hearts.
 */
public enum SocialAttitude {
    HOSTILE,
    GUARDED,
    NEUTRAL,
    CORDIAL,
    WARM,
    AFFECTIONATE;

    public String key() {
        return name().toLowerCase(Locale.ROOT);
    }
}
