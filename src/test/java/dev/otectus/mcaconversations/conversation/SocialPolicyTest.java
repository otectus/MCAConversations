package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The social ladder and its precedence (Stability spec §8.4, §8.6, §11.3). */
class SocialPolicyTest {

    private static final SocialThresholds T = SocialThresholds.DEFAULTS;

    private static SocialFacts facts(int hearts, int days, int familiarity, int trust) {
        return new SocialFacts(hearts, RelationshipRoles.NONE, false, OptionalInt.of(days), false,
                OptionalInt.of(familiarity), OptionalInt.of(trust));
    }

    private static RelationshipBand band(SocialFacts facts) {
        return SocialPolicy.band(facts, T, true);
    }

    @Test
    @DisplayName("zero hearts, no history, no contact: a stranger nobody has met")
    void firstMeetingIsAnUnmetStranger() {
        SocialFacts facts = facts(0, 0, 0, 0);
        assertEquals(RelationshipBand.STRANGER, band(facts));
        assertEquals(SocialContact.UNMET, SocialPolicy.contact(facts));
        assertEquals(SocialAttitude.NEUTRAL, SocialPolicy.attitude(band(facts), SocialContact.UNMET, 0));
    }

    @Test
    @DisplayName("hearts alone never make a friend or a confidant")
    void heartsAloneAreNotFriendship() {
        assertEquals(RelationshipBand.STRANGER, band(facts(95, 1, 0, 0)));
        assertEquals(RelationshipBand.ACQUAINTANCE, band(facts(95, 2, 8, 0)));
        assertEquals(RelationshipBand.ACQUAINTANCE, band(facts(95, 3, 19, 0)));
    }

    @Test
    @DisplayName("each band's boundary is inclusive and needs every clause")
    void boundaries() {
        assertEquals(RelationshipBand.STRANGER, band(facts(0, 2, 7, 0)));
        assertEquals(RelationshipBand.STRANGER, band(facts(0, 1, 8, 0)));
        assertEquals(RelationshipBand.ACQUAINTANCE, band(facts(0, 2, 8, 0)));
        assertEquals(RelationshipBand.FRIEND, band(facts(60, 4, 20, 0)));
        assertEquals(RelationshipBand.ACQUAINTANCE, band(facts(59, 4, 20, 0)));
        assertEquals(RelationshipBand.ACQUAINTANCE, band(facts(60, 3, 20, 0)));
        assertEquals(RelationshipBand.ACQUAINTANCE, band(facts(60, 4, 20, -1)), "trust below baseline");
        assertEquals(RelationshipBand.CONFIDANT, band(facts(80, 8, 40, 10)));
        assertEquals(RelationshipBand.FRIEND, band(facts(80, 8, 40, 9)), "trust margin short");
        assertEquals(RelationshipBand.FRIEND, band(facts(80, 7, 40, 10)), "one day short");
    }

    @Test
    @DisplayName("a rupture outranks eighty hearts, and hostility outranks everything")
    void conflictOutranksWarmth() {
        SocialFacts ruptured = new SocialFacts(90, RelationshipRoles.NONE, true, OptionalInt.of(20), false,
                OptionalInt.of(80), OptionalInt.of(30));
        assertEquals(RelationshipBand.TENSE, band(ruptured));
        assertEquals(SocialAttitude.GUARDED, SocialPolicy.attitude(band(ruptured), SocialContact.RECOGNIZED, 90));
        assertEquals(RelationshipBand.HOSTILE, band(facts(-50, 20, 80, 30)));
        assertEquals(RelationshipBand.TENSE, band(facts(-1, 20, 80, 30)));
    }

    @Test
    @DisplayName("a spouse in a quarrel is tense but still a spouse and still recognised")
    void rolesSurviveConflict() {
        RelationshipRoles spouse = new RelationshipRoles(true, false, false, false);
        SocialFacts facts = new SocialFacts(40, spouse, true, OptionalInt.of(0), false,
                OptionalInt.of(0), OptionalInt.of(0));
        assertEquals(RelationshipBand.TENSE, band(facts));
        assertEquals(SocialContact.RECOGNIZED, SocialPolicy.contact(facts));
    }

    @Test
    @DisplayName("family and marriage come from roles, never from a heart total")
    void rolesDecideFamilyAndPartner() {
        RelationshipRoles child = new RelationshipRoles(false, false, true, false);
        assertEquals(RelationshipBand.FAMILY, band(new SocialFacts(0, child, false, OptionalInt.of(0), false,
                OptionalInt.of(0), OptionalInt.of(0))));
        RelationshipRoles spouse = new RelationshipRoles(true, false, false, false);
        assertEquals(RelationshipBand.PARTNER, band(new SocialFacts(0, spouse, false, OptionalInt.of(0), false,
                OptionalInt.of(0), OptionalInt.of(0))));
        assertEquals(RelationshipBand.STRANGER, band(facts(100, 0, 0, 0)), "100 hearts is not a partner");
    }

    @Test
    @DisplayName("an upgraded world's legacy pair keeps the heart band it always had")
    void legacyPairsKeepHeartBands() {
        SocialFacts legacy = new SocialFacts(65, RelationshipRoles.NONE, false, OptionalInt.of(0), true,
                OptionalInt.of(0), OptionalInt.of(0));
        assertEquals(RelationshipBand.FRIEND, band(legacy));
        assertEquals(SocialContact.RECOGNIZED, SocialPolicy.contact(legacy));
    }

    @Test
    @DisplayName("history unreadable or the switch off: heart bands, roles and ruptures still honoured")
    void degradedModes() {
        SocialFacts noHistory = new SocialFacts(65, RelationshipRoles.NONE, false, OptionalInt.empty(), false,
                OptionalInt.of(0), OptionalInt.of(0));
        assertEquals(RelationshipBand.FRIEND, band(noHistory));
        assertEquals(RelationshipBand.FRIEND, SocialPolicy.band(facts(65, 0, 0, 0), T, false));
        SocialFacts rupturedOff = new SocialFacts(65, RelationshipRoles.NONE, true, OptionalInt.of(0), false,
                OptionalInt.empty(), OptionalInt.empty());
        assertEquals(RelationshipBand.TENSE, SocialPolicy.band(rupturedOff, T, false));
    }

    @Test
    @DisplayName("dispositions off: familiarity is derived from contact days and the trust clause is skipped")
    void dispositionsOffFallback() {
        SocialFacts twoDays = new SocialFacts(0, RelationshipRoles.NONE, false, OptionalInt.of(2), false,
                OptionalInt.empty(), OptionalInt.empty());
        assertEquals(RelationshipBand.ACQUAINTANCE, band(twoDays), "2 days x 4 = 8 familiarity");
        SocialFacts tenDays = new SocialFacts(80, RelationshipRoles.NONE, false, OptionalInt.of(10), false,
                OptionalInt.empty(), OptionalInt.empty());
        assertEquals(RelationshipBand.CONFIDANT, band(tenDays));
    }

    @Test
    @DisplayName("a recognised stranger with some goodwill is cordial, not warm")
    void recognisedStrangerAttitude() {
        SocialFacts met = facts(10, 1, 4, 0);
        assertEquals(RelationshipBand.STRANGER, band(met));
        assertEquals(SocialContact.RECOGNIZED, SocialPolicy.contact(met));
        assertEquals(SocialAttitude.CORDIAL, SocialPolicy.attitude(RelationshipBand.STRANGER, SocialContact.RECOGNIZED, 10));
        assertEquals(SocialAttitude.NEUTRAL, SocialPolicy.attitude(RelationshipBand.STRANGER, SocialContact.RECOGNIZED, 0));
    }

    @Test
    @DisplayName("a mistyped ladder is normalised, never inverted")
    void thresholdsAreNormalised() {
        SocialThresholds t = new SocialThresholds(50, 9, 60, 10, 2, 40, 5, 1, -3);
        assertEquals(50, t.friendFamiliarity());
        assertEquals(9, t.friendDays());
        assertEquals(60, t.confidantHearts());
        assertEquals(50, t.confidantFamiliarity());
        assertEquals(9, t.confidantDays());
        assertEquals(0, t.confidantTrustMargin());
    }

    @Test
    @DisplayName("nothing known about the pair discloses least")
    void unknownFacts() {
        assertEquals(RelationshipBand.STRANGER, band(SocialFacts.unknown()));
        assertEquals(RelationshipBand.STRANGER, SocialPolicy.band(null, T, true));
        assertEquals(SocialContact.UNMET, SocialPolicy.contact(SocialFacts.unknown()));
    }
}
