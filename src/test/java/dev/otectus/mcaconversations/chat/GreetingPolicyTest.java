package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.SocialContact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Greeting pools and frequency (Stability spec §9.2, §10.1). */
class GreetingPolicyTest {

    @Test
    void aStrangerNeverDrawsTheFamiliarPool() {
        for (SocialContact contact : SocialContact.values()) {
            assertNotEquals(GreetingPolicy.FAMILIAR, GreetingPolicy.pool(RelationshipBand.STRANGER, contact));
        }
        assertEquals(GreetingPolicy.STRANGER, GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.UNMET));
        assertEquals(GreetingPolicy.RECOGNIZED, GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.STRANGER, GreetingPolicy.pool(null, SocialContact.RECOGNIZED));
    }

    @Test
    void eachBandHasItsPool() {
        assertEquals(GreetingPolicy.RECOGNIZED, GreetingPolicy.pool(RelationshipBand.ACQUAINTANCE, SocialContact.RECOGNIZED));
        for (RelationshipBand warm : new RelationshipBand[] {RelationshipBand.FRIEND, RelationshipBand.CONFIDANT}) {
            assertEquals(GreetingPolicy.FAMILIAR, GreetingPolicy.pool(warm, SocialContact.RECOGNIZED));
        }
        assertEquals(GreetingPolicy.PARTNER, GreetingPolicy.pool(RelationshipBand.PARTNER, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.FAMILY, GreetingPolicy.pool(RelationshipBand.FAMILY, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.COLD, GreetingPolicy.pool(RelationshipBand.TENSE, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.COLD, GreetingPolicy.pool(RelationshipBand.HOSTILE, SocialContact.RECOGNIZED));
    }

    @Test
    void aRespectedStrangerGetsCourtesyAndNothingMore() {
        assertEquals(GreetingPolicy.RESPECTED_STRANGER,
                GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.UNMET, true));
        assertEquals(GreetingPolicy.RECOGNIZED,
                GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.RECOGNIZED, true),
                "having actually met outranks a good name");
        assertEquals(GreetingPolicy.COLD, GreetingPolicy.pool(RelationshipBand.TENSE, SocialContact.RECOGNIZED, true),
                "a good public name does not paper over a private quarrel");
        assertEquals(GreetingPolicy.STRANGER, GreetingPolicy.pool(RelationshipBand.STRANGER, SocialContact.UNMET, false));
    }

    @Test
    void aStrangerIsNeverSeenOffByName() {
        assertEquals(GreetingPolicy.FAREWELL_STRANGER, GreetingPolicy.farewell(RelationshipBand.STRANGER, SocialContact.UNMET));
        assertEquals(GreetingPolicy.FAREWELL_STRANGER, GreetingPolicy.farewell(null, null));
        assertEquals(GreetingPolicy.FAREWELL, GreetingPolicy.farewell(RelationshipBand.STRANGER, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.FAREWELL, GreetingPolicy.farewell(RelationshipBand.FAMILY, SocialContact.UNMET),
                "family is never a stranger");
        assertTrue(UtteranceAudience.ofStaticLine(GreetingPolicy.FAREWELL_STRANGER).bystandersMayHear());
        assertEquals(GreetingPolicy.FAREWELL_STRANGER + ".toddler",
                AgeVoice.phrase(GreetingPolicy.FAREWELL_STRANGER, java.util.Optional.of("toddler")));
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
        for (String pool : new String[] {GreetingPolicy.RESPECTED_STRANGER, GreetingPolicy.PARTNER, GreetingPolicy.FAMILY}) {
            assertTrue(UtteranceAudience.ofStaticLine(pool).bystandersMayHear(), pool);
        }
        assertEquals(GreetingPolicy.FAMILY + ".toddler",
                AgeVoice.phrase(GreetingPolicy.FAMILY, java.util.Optional.of("toddler")));
        assertEquals(GreetingPolicy.STRANGER + ".toddler",
                AgeVoice.phrase(GreetingPolicy.RESPECTED_STRANGER, java.util.Optional.of("toddler")),
                "a toddler does not greet anybody by their reputation");
    }
}
