package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.RelationshipRoles;
import dev.otectus.mcaconversations.conversation.SocialContact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Greeting pools and frequency (Stability spec §9.2, §10.1). */
class GreetingPolicyTest {

    @Test
    void aStrangerNeverDrawsAWarmerPool() {
        for (SocialContact contact : SocialContact.values()) {
            for (String warmer : new String[] {GreetingPolicy.ACQUAINTANCE, GreetingPolicy.FRIEND,
                    GreetingPolicy.CONFIDANT, GreetingPolicy.LEGACY}) {
                assertNotEquals(warmer, GreetingPolicy.pool(RelationshipBand.STRANGER, contact));
            }
        }
        assertEquals(GreetingPolicy.STRANGER, GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.UNMET));
        assertEquals(GreetingPolicy.RECOGNIZED, GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.STRANGER, GreetingPolicy.pool(null, SocialContact.RECOGNIZED));
    }

    @Test
    void eachBandHasItsPool() {
        assertEquals(GreetingPolicy.ACQUAINTANCE, GreetingPolicy.pool(RelationshipBand.ACQUAINTANCE, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.FRIEND, GreetingPolicy.pool(RelationshipBand.FRIEND, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.CONFIDANT, GreetingPolicy.pool(RelationshipBand.CONFIDANT, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.PARTNER, GreetingPolicy.pool(RelationshipBand.PARTNER, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.FAMILY_PARENT, GreetingPolicy.pool(RelationshipBand.FAMILY, SocialContact.RECOGNIZED),
                "a relative whose role is not known gets the common case");
        assertEquals(GreetingPolicy.GUARDED, GreetingPolicy.pool(RelationshipBand.TENSE, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.COLD, GreetingPolicy.pool(RelationshipBand.HOSTILE, SocialContact.RECOGNIZED));
    }

    @Test
    void noTwoBandsShareAPoolAndNoneUsesTheLegacyOne() {
        java.util.Map<String, RelationshipBand> owner = new java.util.HashMap<>();
        for (RelationshipBand band : RelationshipBand.values()) {
            String pool = GreetingPolicy.pool(band, SocialContact.RECOGNIZED);
            assertNotEquals(GreetingPolicy.LEGACY, pool, band.key());
            RelationshipBand previous = owner.putIfAbsent(pool, band);
            assertTrue(previous == null, band.key() + " shares " + pool + " with " + previous);
        }
    }

    @Test
    void neitherAGrudgeNorAHostileGreetingComesWithAWave() {
        assertFalse(GreetingPolicy.waves(GreetingPolicy.GUARDED));
        assertFalse(GreetingPolicy.waves(GreetingPolicy.COLD));
        for (String pool : new String[] {GreetingPolicy.STRANGER, GreetingPolicy.RECOGNIZED, GreetingPolicy.ACQUAINTANCE,
                GreetingPolicy.FRIEND, GreetingPolicy.CONFIDANT, GreetingPolicy.PARTNER, GreetingPolicy.FAMILY_PARENT}) {
            assertTrue(GreetingPolicy.waves(pool), pool);
        }
    }

    @Test
    void familyIsGreetedByWhoTheyAreToEachOther() {
        RelationshipRoles playerIsParent = new RelationshipRoles(false, true, false, false);
        RelationshipRoles playerIsChild = new RelationshipRoles(false, false, true, false);
        RelationshipRoles playerIsSibling = new RelationshipRoles(false, false, false, true);
        assertEquals(GreetingPolicy.FAMILY_PARENT,
                GreetingPolicy.pool(RelationshipBand.FAMILY, SocialContact.RECOGNIZED, false, playerIsParent));
        assertEquals(GreetingPolicy.FAMILY_CHILD,
                GreetingPolicy.pool(RelationshipBand.FAMILY, SocialContact.RECOGNIZED, false, playerIsChild));
        assertEquals(GreetingPolicy.FAMILY_SIBLING,
                GreetingPolicy.pool(RelationshipBand.FAMILY, SocialContact.RECOGNIZED, false, playerIsSibling));
        assertEquals(GreetingPolicy.FAMILY_PARENT, GreetingPolicy.family(new RelationshipRoles(false, true, false, true)),
                "being their parent outranks sharing a parent with them");
        assertEquals(GreetingPolicy.FAMILY_PARENT, GreetingPolicy.family(null));
        assertEquals(GreetingPolicy.PARTNER,
                GreetingPolicy.pool(RelationshipBand.PARTNER, SocialContact.RECOGNIZED, false,
                        new RelationshipRoles(true, false, false, false)));
        assertEquals(GreetingPolicy.GUARDED,
                GreetingPolicy.pool(RelationshipBand.TENSE, SocialContact.RECOGNIZED, false, playerIsChild),
                "a relative in the middle of a quarrel is guarded like anybody else");
    }

    @Test
    void aRespectedStrangerGetsCourtesyAndNothingMore() {
        RelationshipRoles none = RelationshipRoles.NONE;
        assertEquals(GreetingPolicy.RESPECTED_STRANGER,
                GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.UNMET, true, none));
        assertEquals(GreetingPolicy.RECOGNIZED,
                GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.RECOGNIZED, true, none),
                "having actually met outranks a good name");
        assertEquals(GreetingPolicy.GUARDED, GreetingPolicy.pool(RelationshipBand.TENSE, SocialContact.RECOGNIZED, true, none),
                "a good public name does not paper over a private quarrel");
        assertEquals(GreetingPolicy.STRANGER, GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.UNMET, false, none));
    }

    @Test
    void aStrangerIsNeverSeenOffByName() {
        assertEquals(GreetingPolicy.FAREWELL_STRANGER, GreetingPolicy.farewell(RelationshipBand.STRANGER, SocialContact.UNMET));
        assertEquals(GreetingPolicy.FAREWELL_STRANGER, GreetingPolicy.farewell(null, null));
        assertEquals(GreetingPolicy.FAREWELL, GreetingPolicy.farewell(RelationshipBand.STRANGER, SocialContact.RECOGNIZED));
        assertNotEquals(GreetingPolicy.FAREWELL_STRANGER, GreetingPolicy.farewell(RelationshipBand.FAMILY, SocialContact.UNMET),
                "family is never a stranger");
        assertTrue(UtteranceAudience.ofStaticLine(GreetingPolicy.FAREWELL_STRANGER).bystandersMayHear());
        assertEquals(GreetingPolicy.FAREWELL_STRANGER + ".toddler",
                AgeVoice.phrase(GreetingPolicy.FAREWELL_STRANGER, java.util.Optional.of("toddler")));
    }

    @Test
    void aGoodbyeIsChangedOnlyWhereTheOrdinaryOneWouldBeWrong() {
        for (SocialContact contact : SocialContact.values()) {
            assertEquals(GreetingPolicy.FAREWELL_GUARDED, GreetingPolicy.farewell(RelationshipBand.TENSE, contact),
                    "a quarrel is seen off carefully, introduced or not");
            assertEquals(GreetingPolicy.FAREWELL_HOSTILE, GreetingPolicy.farewell(RelationshipBand.HOSTILE, contact));
        }
        RelationshipRoles playerIsParent = new RelationshipRoles(false, true, false, false);
        RelationshipRoles playerIsChild = new RelationshipRoles(false, false, true, false);
        RelationshipRoles playerIsSibling = new RelationshipRoles(false, false, false, true);
        assertEquals(GreetingPolicy.FAREWELL_FAMILY_PARENT,
                GreetingPolicy.farewell(RelationshipBand.FAMILY, SocialContact.RECOGNIZED, playerIsParent));
        assertEquals(GreetingPolicy.FAREWELL_FAMILY_PARENT,
                GreetingPolicy.farewell(RelationshipBand.FAMILY, SocialContact.RECOGNIZED, RelationshipRoles.NONE),
                "a relative whose role is not known gets the common case, which claims least");
        assertEquals(GreetingPolicy.FAREWELL,
                GreetingPolicy.farewell(RelationshipBand.FAMILY, SocialContact.RECOGNIZED, playerIsChild));
        assertEquals(GreetingPolicy.FAREWELL,
                GreetingPolicy.farewell(RelationshipBand.FAMILY, SocialContact.RECOGNIZED, playerIsSibling));
        assertEquals(GreetingPolicy.FAREWELL_GUARDED,
                GreetingPolicy.farewell(RelationshipBand.TENSE, SocialContact.RECOGNIZED, playerIsParent),
                "your own child in the middle of a quarrel is guarded like anybody else");
        for (RelationshipBand band : new RelationshipBand[] {RelationshipBand.ACQUAINTANCE, RelationshipBand.FRIEND,
                RelationshipBand.CONFIDANT, RelationshipBand.PARTNER}) {
            assertEquals(GreetingPolicy.FAREWELL, GreetingPolicy.farewell(band, SocialContact.RECOGNIZED), band.key());
        }
        assertFalse(GreetingPolicy.waves(GreetingPolicy.FAREWELL_GUARDED));
        assertFalse(GreetingPolicy.waves(GreetingPolicy.FAREWELL_HOSTILE));
        assertTrue(GreetingPolicy.waves(GreetingPolicy.FAREWELL));
        assertTrue(GreetingPolicy.waves(GreetingPolicy.FAREWELL_FAMILY_PARENT));
        for (String pool : new String[] {GreetingPolicy.FAREWELL_GUARDED, GreetingPolicy.FAREWELL_HOSTILE,
                GreetingPolicy.FAREWELL_FAMILY_PARENT}) {
            assertTrue(UtteranceAudience.ofStaticLine(pool).bystandersMayHear(), pool);
            assertEquals(pool + ".toddler", AgeVoice.phrase(pool, java.util.Optional.of("toddler")));
        }
    }

    @Test
    void friendsGreetMoreOftenThanStrangersAndGrudgesLeast() {
        double stranger = GreetingPolicy.frequency(RelationshipBand.STRANGER, SocialContact.UNMET);
        double known = GreetingPolicy.frequency(RelationshipBand.ACQUAINTANCE, SocialContact.RECOGNIZED);
        double friend = GreetingPolicy.frequency(RelationshipBand.FRIEND, SocialContact.RECOGNIZED);
        double tense = GreetingPolicy.frequency(RelationshipBand.TENSE, SocialContact.RECOGNIZED);
        double hostile = GreetingPolicy.frequency(RelationshipBand.HOSTILE, SocialContact.RECOGNIZED);
        assertTrue(hostile < tense && tense < stranger && stranger < known && known < friend);
        assertEquals(1.0, friend);
    }

    @Test
    void newFamiliesAreOverhearableAndToddlerVoiced() {
        assertTrue(UtteranceAudience.ofStaticLine(GreetingPolicy.STRANGER).bystandersMayHear());
        assertTrue(UtteranceAudience.ofStaticLine(GreetingPolicy.RECOGNIZED).bystandersMayHear());
        assertEquals(GreetingPolicy.STRANGER + ".toddler",
                AgeVoice.phrase(GreetingPolicy.STRANGER, java.util.Optional.of("toddler")));
        assertEquals(GreetingPolicy.RECOGNIZED + ".toddler",
                AgeVoice.phrase(GreetingPolicy.RECOGNIZED, java.util.Optional.of("toddler")));
        for (String pool : new String[] {GreetingPolicy.RESPECTED_STRANGER, GreetingPolicy.ACQUAINTANCE,
                GreetingPolicy.FRIEND, GreetingPolicy.PARTNER, GreetingPolicy.FAMILY_PARENT, GreetingPolicy.FAMILY_CHILD,
                GreetingPolicy.FAMILY_SIBLING, GreetingPolicy.GUARDED}) {
            assertTrue(UtteranceAudience.ofStaticLine(pool).bystandersMayHear(), pool);
        }
        assertFalse(UtteranceAudience.ofStaticLine(GreetingPolicy.CONFIDANT).bystandersMayHear(),
                "a confidant's hello is for the player alone");
        for (String pool : new String[] {GreetingPolicy.ACQUAINTANCE, GreetingPolicy.FRIEND, GreetingPolicy.CONFIDANT,
                GreetingPolicy.GUARDED}) {
            assertEquals(pool + ".toddler", AgeVoice.phrase(pool, java.util.Optional.of("toddler")));
        }
        for (String pool : new String[] {GreetingPolicy.FAMILY_PARENT, GreetingPolicy.FAMILY_SIBLING,
                GreetingPolicy.FAMILY_CHILD}) {
            assertEquals(GreetingPolicy.FAMILY_PARENT + ".toddler", AgeVoice.phrase(pool, java.util.Optional.of("toddler")),
                    "a toddler greets every relative with arms up: " + pool);
        }
        assertEquals(GreetingPolicy.STRANGER + ".toddler",
                AgeVoice.phrase(GreetingPolicy.RESPECTED_STRANGER, java.util.Optional.of("toddler")),
                "a toddler does not greet anybody by their reputation");
    }
}
