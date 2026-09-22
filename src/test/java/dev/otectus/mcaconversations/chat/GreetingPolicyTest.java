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
        for (RelationshipBand warm : new RelationshipBand[] {RelationshipBand.FRIEND, RelationshipBand.CONFIDANT,
                RelationshipBand.PARTNER, RelationshipBand.FAMILY}) {
            assertEquals(GreetingPolicy.FAMILIAR, GreetingPolicy.pool(warm, SocialContact.RECOGNIZED));
        }
        assertEquals(GreetingPolicy.COLD, GreetingPolicy.pool(RelationshipBand.TENSE, SocialContact.RECOGNIZED));
        assertEquals(GreetingPolicy.COLD, GreetingPolicy.pool(RelationshipBand.HOSTILE, SocialContact.RECOGNIZED));
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
    }
}
