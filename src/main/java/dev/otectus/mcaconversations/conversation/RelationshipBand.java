package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * The one documented source of truth for "how well do these two know each other" (spec §9.4).
 *
 * <p>The bands exist so dialogue JSON stops carrying magic heart numbers. Authors write
 * {@code "relationships": ["confidant", "partner"]} and the thresholds live here, where they can be
 * changed once. Two of the bands are not points on the warmth line at all: {@link #FAMILY} is a role
 * (a parent does not become a parent at 70 hearts) and {@link #TENSE}/{@link #HOSTILE} describe a
 * relationship that has gone wrong regardless of how many hearts preceded it.
 *
 * <p>Bands govern what may be <em>disclosed</em> and how directly the player may speak — not merely
 * how the same line is phrased.
 */
public enum RelationshipBand {

    /** Never really spoken. Small talk, public facts, nothing personal. */
    STRANGER("stranger", 0, true),
    /** A known face. Ordinary village subjects, mild personal detail. */
    ACQUAINTANCE("acquaintance", 25, true),
    /**
     * Trusted. Real opinions, real complaints, some history.
     *
     * <p>Sixty rather than a round fifty because the corpus was already gated at 24 and 59 before
     * these bands existed: those two numbers are the tops of {@link #STRANGER} and
     * {@link #ACQUAINTANCE}, so the bands describe the content that is actually written rather than
     * asking it to move.
     */
    FRIEND("friend", 60, true),
    /** Told things they would not tell the village. Secrets, fears, regrets. */
    CONFIDANT("confidant", 80, true),
    /** Married or courting. The only band where romance and intimacy are legal. */
    PARTNER("partner", 100, false),
    /** Parent, child or sibling by MCA's family graph. A role, not a score. */
    FAMILY("family", 0, false),
    /** Something is unresolved between them. Warmth is available only after repair. */
    TENSE("tense", 0, false),
    /** Actively unfriendly. Intimacy and teasing are both out of bounds. */
    HOSTILE("hostile", 0, false);

    private final String key;
    private final int minHearts;
    private final boolean onWarmthLine;

    RelationshipBand(String key, int minHearts, boolean onWarmthLine) {
        this.key = key;
        this.minHearts = minHearts;
        this.onWarmthLine = onWarmthLine;
    }

    public String key() {
        return key;
    }

    /** Fewest MCA hearts this band requires. Meaningless for the role and rupture bands. */
    public int minHearts() {
        return minHearts;
    }

    /** True for the four bands that really are an ordered warmth scale. */
    public boolean onWarmthLine() {
        return onWarmthLine;
    }

    /** True when romantic and intimate content is permitted at all. */
    public boolean allowsRomance() {
        return this == PARTNER;
    }

    /** The warmth band a heart total falls in, ignoring roles and ruptures. */
    public static RelationshipBand fromHearts(int hearts) {
        RelationshipBand best = STRANGER;
        for (RelationshipBand band : values()) {
            if (band.onWarmthLine && hearts >= band.minHearts && band.minHearts >= best.minHearts) {
                best = band;
            }
        }
        return best;
    }

    /**
     * The band these two are actually in, from everything the game can tell us.
     *
     * <p>Order is the whole of the logic. A rupture outranks warmth, because forty hearts and an
     * unresolved incident is not a friendship in any sense an author cares about. Marriage outranks
     * a heart total, because a spouse is a spouse at any number. A family role outranks the warmth
     * line for the same reason: a parent does not become a parent at seventy hearts.
     *
     * @param hearts          MCA hearts for this villager/player pair
     * @param marriedToPlayer whether MCA has them married to this player
     * @param family          whether MCA's family tree makes them relatives
     * @param unresolved      whether something between them is still outstanding
     */
    public static RelationshipBand of(int hearts, boolean marriedToPlayer, boolean family,
                                      boolean unresolved) {
        if (hearts <= HOSTILE_HEARTS) {
            return HOSTILE;
        }
        if (unresolved || hearts < 0) {
            return TENSE;
        }
        if (marriedToPlayer) {
            return PARTNER;
        }
        if (family) {
            return FAMILY;
        }
        return fromHearts(hearts);
    }

    /** Below this, the relationship is not cold — it is against you. */
    public static final int HOSTILE_HEARTS = -50;

    /**
     * True when this band is at least as close as {@code floor} on the warmth line. The two roles
     * count as at least {@link #CONFIDANT}: a spouse or a parent may hear anything a confidant may.
     * The ruptured bands are never at or above anything, whatever preceded them.
     */
    public boolean isAtLeast(RelationshipBand floor) {
        if (!floor.onWarmthLine) {
            return this == floor;
        }
        return closeness() >= floor.minHearts;
    }

    private int closeness() {
        if (this == PARTNER || this == FAMILY) {
            return CONFIDANT.minHearts;
        }
        return onWarmthLine ? minHearts : Integer.MIN_VALUE;
    }

    public static Optional<RelationshipBand> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (RelationshipBand band : values()) {
            if (band.key.equals(normalized)) {
                return Optional.of(band);
            }
        }
        return Optional.empty();
    }
}
