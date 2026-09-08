package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * One reload, one generation (audit F12). The terminal listener is what makes a reload look atomic
 * from the outside, so it must bump the counter exactly once per apply — twice would strand offers
 * that were minted correctly, never would let a stale one through.
 */
class ContentGenerationTest {

    @Test
    @DisplayName("the terminal listener advances the generation exactly once per apply")
    void listenerAdvancesOncePerApply() {
        ContentGenerationListener listener = new ContentGenerationListener();
        long before = ContentGeneration.current();

        listener.onResourceManagerReload(null);

        assertEquals(before + 1, ContentGeneration.current());

        listener.onResourceManagerReload(null);

        assertEquals(before + 2, ContentGeneration.current(), "a second reload is a second generation");
    }

    @Test
    @DisplayName("the generation only ever moves forward")
    void generationIsMonotonic() {
        long first = ContentGeneration.advance();
        long second = ContentGeneration.advance();

        assertEquals(first + 1, second);
        assertEquals(second, ContentGeneration.current());
    }
}
