package dev.otectus.mcaconversations.check;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoodModifiersTest {

    @Test
    void moodTableCoversEveryMcaMoodName() {
        // Names pinned against MCA's lang (mood.<name>) — the same set ContentLintTest pins.
        assertEquals(-12, MoodModifiers.moodAdjust("depressed"));
        assertEquals(-8, MoodModifiers.moodAdjust("sad"));
        assertEquals(-4, MoodModifiers.moodAdjust("unhappy"));
        assertEquals(0, MoodModifiers.moodAdjust("passive"));
        assertEquals(0, MoodModifiers.moodAdjust("fine"));
        assertEquals(3, MoodModifiers.moodAdjust("happy"));
        assertEquals(6, MoodModifiers.moodAdjust("overjoyed"));
    }

    @Test
    void unknownOrMissingMoodIsNeutral() {
        assertEquals(0, MoodModifiers.moodAdjust("jubilant"));
        assertEquals(0, MoodModifiers.moodAdjust(null));
    }

    @Test
    void conversationStatesShiftTheCheck() {
        assertEquals(-12, MoodModifiers.stateAdjust(true, false, false, false));
        assertEquals(-8, MoodModifiers.stateAdjust(false, true, false, false));
        assertEquals(4, MoodModifiers.stateAdjust(false, false, true, false));
        assertEquals(6, MoodModifiers.stateAdjust(false, false, false, true));
        // They stack: grieving but grateful is still a bad moment to push.
        assertEquals(-8, MoodModifiers.stateAdjust(true, false, true, false));
        assertEquals(0, MoodModifiers.stateAdjust(false, false, false, false));
    }
}
