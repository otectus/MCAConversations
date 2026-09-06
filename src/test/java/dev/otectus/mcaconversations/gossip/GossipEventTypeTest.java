package dev.otectus.mcaconversations.gossip;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GossipEventTypeTest {

    @Test
    void arrivalAndDepartureAreDistinctTypes() {
        assertEquals("arrival", GossipEventType.ARRIVAL.jsonName());
        assertEquals("departure", GossipEventType.DEPARTURE.jsonName());
    }

    @Test
    void jsonNameRoundTripsForEveryType() {
        for (GossipEventType type : GossipEventType.values()) {
            Optional<GossipEventType> back = GossipEventType.byJsonName(type.jsonName());
            assertTrue(back.isPresent(), "no round-trip for " + type);
            assertEquals(type, back.get());
        }
    }

    @Test
    void jsonNameIsLowercaseAndStable() {
        for (GossipEventType type : GossipEventType.values()) {
            assertEquals(type.jsonName().toLowerCase(Locale.ROOT), type.jsonName());
        }
    }

    @Test
    void theCapitalsTypesAreNamedTheWayTheLangKeysAre() {
        // Each of these has a dialogue.conversations.gossip.<name> family behind it; a rename here is
        // a villager reading a raw lang key aloud.
        assertEquals("coronation", GossipEventType.CORONATION.jsonName());
        assertEquals("royal_marriage", GossipEventType.ROYAL_MARRIAGE.jsonName());
        assertEquals("royal_birth", GossipEventType.ROYAL_BIRTH.jsonName());
        assertEquals("royal_death", GossipEventType.ROYAL_DEATH.jsonName());
        assertEquals("appointment", GossipEventType.APPOINTMENT.jsonName());
        assertEquals("disgrace", GossipEventType.DISGRACE.jsonName());
        assertEquals("war", GossipEventType.WAR.jsonName());
        assertEquals("peace", GossipEventType.PEACE.jsonName());
        assertEquals("alliance", GossipEventType.ALLIANCE.jsonName());
        assertEquals("capital_founded", GossipEventType.CAPITAL_FOUNDED.jsonName());
        assertEquals("court_news", GossipEventType.COURT_NEWS.jsonName());
    }

    @Test
    void byJsonNameIsCaseInsensitiveAndRejectsUnknown() {
        assertEquals(Optional.of(GossipEventType.ARRIVAL), GossipEventType.byJsonName("ARRIVAL"));
        assertFalse(GossipEventType.byJsonName("nonsense").isPresent());
    }
}
