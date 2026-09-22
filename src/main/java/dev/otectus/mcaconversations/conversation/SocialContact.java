package dev.otectus.mcaconversations.conversation;

import java.util.Locale;

/**
 * Whether a villager has actually met this player (Stability spec §8.4).
 *
 * <p>A presentation distinction inside {@link RelationshipBand#STRANGER}, not another friendship
 * currency: a recognised stranger says "hello again" and still owes the player nothing personal.
 * Hostility does not un-meet anybody — a known enemy is still known.
 */
public enum SocialContact {
    /** No verified contact and no legacy evidence. */
    UNMET,
    /** At least one meaningful exchange, or an upgraded world's legacy relationship. */
    RECOGNIZED;

    public String key() {
        return name().toLowerCase(Locale.ROOT);
    }

    /** The constant with this lowercase key, or empty. */
    public static java.util.Optional<SocialContact> byKey(String raw) {
        if (raw == null) {
            return java.util.Optional.empty();
        }
        for (SocialContact value : values()) {
            if (value.key().equals(raw.trim().toLowerCase(Locale.ROOT))) {
                return java.util.Optional.of(value);
            }
        }
        return java.util.Optional.empty();
    }
}
