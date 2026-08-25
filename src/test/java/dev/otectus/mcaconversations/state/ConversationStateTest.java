package dev.otectus.mcaconversations.state;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConversationStateTest {

    @Test
    void jsonNamesAreLowercaseAndUnique() {
        Set<String> seen = new HashSet<>();
        for (ConversationState s : ConversationState.values()) {
            assertEquals(s.jsonName(), s.jsonName().toLowerCase(Locale.ROOT), s + " json name must be lowercase");
            assertTrue(seen.add(s.jsonName()), "duplicate json name: " + s.jsonName());
        }
    }

    @Test
    void byJsonNameRoundTrips() {
        for (ConversationState s : ConversationState.values()) {
            assertEquals(s, ConversationState.byJsonName(s.jsonName()).orElseThrow());
        }
        assertTrue(ConversationState.byJsonName("not_a_state").isEmpty());
        assertTrue(ConversationState.byJsonName(null).isEmpty());
    }

    @Test
    void durationsArePositive() {
        for (ConversationState s : ConversationState.values()) {
            assertTrue(s.defaultDurationTicks() > 0, s + " must have a positive default duration");
        }
    }

    @Test
    void memoryIdIsStateNamespaced() {
        assertEquals("mcaconversations.state.grateful", ConversationState.GRATEFUL.memoryId());
        for (ConversationState s : ConversationState.values()) {
            assertEquals(MemoryIds.state(s.jsonName()), s.memoryId());
        }
    }

    @Test
    void scopingMatchesCauseKind() {
        // Player-caused moods are felt toward that player; village-wide moods are ambient.
        assertTrue(ConversationState.GRATEFUL.playerScoped());
        assertTrue(ConversationState.SMITTEN.playerScoped());
        assertTrue(ConversationState.PROUD.playerScoped());
        assertTrue(ConversationState.ANNOYED.playerScoped());
        assertFalse(ConversationState.GRIEVING.playerScoped());
        assertFalse(ConversationState.ELATED.playerScoped());
    }
}
