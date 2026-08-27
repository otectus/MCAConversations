package dev.otectus.mcaconversations.history;

import java.util.Locale;
import java.util.Optional;

/**
 * What a villager may do with a thing they know, when someone else is listening (spec §16.3).
 *
 * <p>Privacy says how sensitive a fact is. Permission says what this holder is allowed to do with
 * it, which is not always the same thing: a confidential worry the player explicitly said could be
 * passed on is still confidential, and a piece of ordinary news can be under an authored embargo.
 * Keeping the two apart is what makes "I can tell you what happened, but not who" a normal sentence
 * for this mod rather than a special case.
 *
 * <p>Ordered from freest to most restricted, so {@link #atMost} is one comparison.
 */
public enum SharePermission {

    /** Repeat it, and name the people in it. */
    MAY_NAME("may_name", 0),

    /** Repeat what happened, but not whose it was. */
    MAY_DESCRIBE_ANONYMOUSLY("may_describe_anonymously", 1),

    /** Not theirs to tell. Declining is the content; there is no wording that gets round it. */
    MAY_NOT_SHARE("may_not_share", 2);

    private final String key;
    private final int rank;

    SharePermission(String key, int rank) {
        this.key = key;
        this.rank = rank;
    }

    public String key() {
        return key;
    }

    public int rank() {
        return rank;
    }

    public boolean maySpeak() {
        return this != MAY_NOT_SHARE;
    }

    public boolean mayName() {
        return this == MAY_NAME;
    }

    /** The stricter of the two. Permission narrows as a fact travels; it never widens. */
    public SharePermission atMost(SharePermission ceiling) {
        return ceiling == null || rank >= ceiling.rank ? this : ceiling;
    }

    /**
     * What a fact at {@code privacy} may be done with when nothing was authored.
     *
     * <p>This is the default, not the rule. An authored episode may be stricter than its privacy
     * level suggests — and, where the player has given permission, freer — which is why permission
     * is stored rather than derived every time it is asked for.
     */
    public static SharePermission impliedBy(PrivacyLevel privacy) {
        if (privacy == null) {
            return MAY_NAME;
        }
        return switch (privacy) {
            case PUBLIC, ORDINARY -> MAY_NAME;
            case DISCREET -> MAY_DESCRIBE_ANONYMOUSLY;
            case CONFIDENTIAL, SPEAKER_ONLY -> MAY_NOT_SHARE;
        };
    }

    public static Optional<SharePermission> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (SharePermission permission : values()) {
            if (permission.key.equals(normalized)) {
                return Optional.of(permission);
            }
        }
        return Optional.empty();
    }
}
