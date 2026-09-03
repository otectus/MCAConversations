package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pins the two UI cues to their documented character. These values are easy to nudge while tuning
 * something else, and the result is not a test failure but a conversation that clicks at the player
 * on every arrow key. The focus cue is deliberately the quieter and higher of the two.
 */
class DialogueUiSoundsTest {

    private record Cue(float pitch, float volume) {
    }

    private List<Cue> capture(Runnable body) {
        List<Cue> cues = new ArrayList<>();
        DialogueUiSounds.withSink((pitch, volume) -> cues.add(new Cue(pitch, volume)), body);
        return cues;
    }

    @Test
    void focusIsQuieterAndHigherThanPaging() {
        Cue focus = capture(DialogueUiSounds::focus).get(0);
        Cue page = capture(DialogueUiSounds::page).get(0);
        assertEquals(1.35F, focus.pitch(), 0.0001F, "the focus cue's documented pitch");
        assertEquals(1.0F, page.pitch(), 0.0001F, "the paging cue's documented pitch");
        assertTrue(focus.volume() < page.volume(),
                "moving focus happens far more often than paging and must stay the quieter cue");
        assertEquals(0.18D / 0.25D, focus.volume() / page.volume(), 0.0001D,
                "both cues must scale from the same configured volume");
    }

    @Test
    void cuesAreAudibleAtTheDefaultVolume() {
        assertTrue(capture(DialogueUiSounds::focus).get(0).volume() > 0.0F);
        assertTrue(capture(DialogueUiSounds::page).get(0).volume() > 0.0F);
    }

    @Test
    void aFailingSoundSystemNeverEscapesIntoTheRenderPath() {
        assertDoesNotThrow(() -> DialogueUiSounds.withSink((pitch, volume) -> {
            throw new IllegalStateException("no sound engine");
        }, () -> {
            DialogueUiSounds.focus();
            DialogueUiSounds.page();
        }));
    }

    @Test
    void theSinkIsRestoredAfterAnOverride() {
        capture(DialogueUiSounds::focus);
        assertDoesNotThrow(DialogueUiSounds::focus,
                "the production sink must be restored, and must swallow a headless failure");
    }
}
