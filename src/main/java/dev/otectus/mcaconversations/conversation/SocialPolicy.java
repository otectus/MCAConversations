package dev.otectus.mcaconversations.conversation;

import java.util.OptionalInt;

/**
 * The one place a relationship band, contact state and attitude are decided (Stability spec §8).
 *
 * <p>Pure: it reads a {@link SocialFacts} and a {@link SocialThresholds} and touches nothing else,
 * so every boundary is a unit test rather than a playtest. {@link Relationships} gathers the facts.
 *
 * <p>Precedence is the whole of the design (§8.6): hostility and unresolved harm outrank everything;
 * a real role outranks the warmth line; an upgraded world's legacy pair keeps the heart-based band it
 * always had; and only then does the new progression ask for contact days and familiarity as well as
 * hearts.
 */
public final class SocialPolicy {

    /** Familiarity credited per contact day when dispositions are off (§11.3). */
    static final int FALLBACK_FAMILIARITY_PER_DAY = 4;

    private SocialPolicy() {
    }

    /**
     * The band these two are in.
     *
     * @param relationshipAware the {@code [social]} master switch; off keeps the heart-based warmth
     *                          line while still honouring real roles and ruptures
     */
    public static RelationshipBand band(SocialFacts facts, SocialThresholds thresholds,
                                        boolean relationshipAware) {
        if (facts == null) {
            return RelationshipBand.STRANGER;
        }
        RelationshipRoles roles = facts.roles();
        RelationshipBand legacy = RelationshipBand.of(facts.hearts(), roles.spouse(), roles.family(),
                facts.rupture());
        if (!legacy.onWarmthLine() || !relationshipAware || facts.legacyRecognized()
                || facts.contactDays().isEmpty()) {
            // Hostile, tense, partner and family bands, the switch being off, an upgraded world's
            // legacy pair, and a history store nobody can read all keep the heart-based band.
            return legacy;
        }
        SocialThresholds t = thresholds == null ? SocialThresholds.DEFAULTS : thresholds;
        int hearts = facts.hearts();
        int days = facts.contactDays().getAsInt();
        int familiarity = effectiveFamiliarity(facts);
        OptionalInt trust = facts.trustMargin();
        if (hearts >= t.confidantHearts() && familiarity >= t.confidantFamiliarity()
                && days >= t.confidantDays() && trust.orElse(t.confidantTrustMargin()) >= t.confidantTrustMargin()) {
            return RelationshipBand.CONFIDANT;
        }
        if (hearts >= t.friendHearts() && familiarity >= t.friendFamiliarity()
                && days >= t.friendDays() && trust.orElse(0) >= 0) {
            return RelationshipBand.FRIEND;
        }
        if (familiarity >= t.acquaintanceFamiliarity() && days >= t.acquaintanceDays()) {
            return RelationshipBand.ACQUAINTANCE;
        }
        return RelationshipBand.STRANGER;
    }

    /** Whether the villager has met this player at all. */
    public static SocialContact contact(SocialFacts facts) {
        if (facts == null) {
            return SocialContact.UNMET;
        }
        if (facts.legacyRecognized() || facts.roles().any()
                || facts.contactDays().orElse(0) > 0) {
            return SocialContact.RECOGNIZED;
        }
        return SocialContact.UNMET;
    }

    /** How the villager is disposed to speak, from the band and the contact state. */
    public static SocialAttitude attitude(RelationshipBand band, SocialContact contact, int hearts) {
        if (band == null) {
            return SocialAttitude.NEUTRAL;
        }
        return switch (band) {
            case HOSTILE -> SocialAttitude.HOSTILE;
            case TENSE -> SocialAttitude.GUARDED;
            case PARTNER, FAMILY, CONFIDANT -> SocialAttitude.AFFECTIONATE;
            case FRIEND -> SocialAttitude.WARM;
            case ACQUAINTANCE -> SocialAttitude.CORDIAL;
            case STRANGER -> contact == SocialContact.RECOGNIZED && hearts > 0
                    ? SocialAttitude.CORDIAL : SocialAttitude.NEUTRAL;
        };
    }

    /**
     * The familiarity the ladder compares against. With dispositions off it is derived, read-only,
     * from verified contact days (§11.3) — never a second stored axis.
     */
    static int effectiveFamiliarity(SocialFacts facts) {
        if (facts.familiarity().isPresent()) {
            return facts.familiarity().getAsInt();
        }
        return Math.min(100, FALLBACK_FAMILIARITY_PER_DAY * facts.contactDays().orElse(0));
    }
}
