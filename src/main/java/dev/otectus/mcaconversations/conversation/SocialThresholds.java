package dev.otectus.mcaconversations.conversation;

/**
 * The evidence each warmth band needs in a relationship that started under the social model
 * (Stability spec §8.4). Hearts alone never make a friend: a friendship also needs time on the clock
 * and repeated meetings, so a single generous gift cannot turn a stranger into a confidant.
 *
 * <p>The compact constructor normalises any combination into a coherent, nondecreasing ladder, so a
 * mistyped config can loosen or tighten the progression but never invert it.
 */
public record SocialThresholds(int acquaintanceFamiliarity, int acquaintanceDays,
                               int friendHearts, int friendFamiliarity, int friendDays,
                               int confidantHearts, int confidantFamiliarity, int confidantDays,
                               int confidantTrustMargin) {

    public static final SocialThresholds DEFAULTS = new SocialThresholds(8, 2, 60, 20, 4, 80, 40, 8, 10);

    public SocialThresholds {
        acquaintanceFamiliarity = clamp(acquaintanceFamiliarity, 0, 100);
        acquaintanceDays = Math.max(1, acquaintanceDays);
        friendHearts = Math.max(1, friendHearts);
        friendFamiliarity = clamp(Math.max(friendFamiliarity, acquaintanceFamiliarity), 0, 100);
        friendDays = Math.max(friendDays, acquaintanceDays);
        confidantHearts = Math.max(confidantHearts, friendHearts);
        confidantFamiliarity = clamp(Math.max(confidantFamiliarity, friendFamiliarity), 0, 100);
        confidantDays = Math.max(confidantDays, friendDays);
        confidantTrustMargin = Math.max(0, confidantTrustMargin);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
