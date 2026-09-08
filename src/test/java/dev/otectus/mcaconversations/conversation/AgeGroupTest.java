package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The age vocabulary is a closed type, and the two ways in are deliberately not the same: content may
 * only author the four groups a topic can be offered to, while the runtime has to be able to name
 * whatever MCA read — including nothing at all.
 */
class AgeGroupTest {

    @Test
    @DisplayName("the four authorable groups parse and nothing else does")
    void parseAcceptsOnlyAuthorableGroups() {
        for (AgeGroup group : AgeGroup.values()) {
            assertEquals(group.authorable() ? java.util.Optional.of(group) : java.util.Optional.empty(),
                    AgeGroup.parse(group.id()), group.id());
        }
        assertTrue(AgeGroup.parse(null).isEmpty());
        assertTrue(AgeGroup.parse("").isEmpty());
        assertTrue(AgeGroup.parse("teenager").isEmpty(), "a near miss is a typo, not a teen");
    }

    @Test
    @DisplayName("authored values are trimmed and case-insensitive")
    void parseNormalises() {
        assertEquals(java.util.Optional.of(AgeGroup.ADULT), AgeGroup.parse("  ADULT "));
    }

    @Test
    @DisplayName("an unreadable MCA age state is UNKNOWN, never a guess")
    void fromMcaMapsWhatMcaReports() {
        assertEquals(AgeGroup.BABY, AgeGroup.fromMca("baby"));
        assertEquals(AgeGroup.TODDLER, AgeGroup.fromMca("toddler"));
        assertEquals(AgeGroup.CHILD, AgeGroup.fromMca("child"));
        assertEquals(AgeGroup.TEEN, AgeGroup.fromMca("teen"));
        assertEquals(AgeGroup.ADULT, AgeGroup.fromMca("ADULT"));
        // MCA's own AgeState carries UNASSIGNED, and a future build may carry more.
        assertEquals(AgeGroup.UNKNOWN, AgeGroup.fromMca("unassigned"));
        assertEquals(AgeGroup.UNKNOWN, AgeGroup.fromMca(null));
        assertEquals(AgeGroup.UNKNOWN, AgeGroup.fromMca("unknown"));
    }
}
