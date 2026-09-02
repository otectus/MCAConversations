package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Guards narration deduplication. A narrator that repeats itself is not a visual defect and never
 * shows up in a screenshot or a frame time -- it is only experienced by players using a screen
 * reader, as the focused answer being read out again on every frame. The rule being protected is
 * that narration fires on transitions only, and never for a stale offer revision.
 */
class DialogueChoiceNarratorTest {

    private final List<Component> spoken = new ArrayList<>();

    private DialogueChoiceNarrator narrator() {
        return new DialogueChoiceNarrator(spoken::add);
    }

    /**
     * Without a loaded language a translatable renders as its bare key, so the answer has to be read
     * from the component's arguments rather than from its rendered text.
     */
    private String answerIn(int index) {
        Component component = spoken.get(index);
        if (component.getContents() instanceof TranslatableContents contents
                && contents.getArgs().length > 0) {
            Object last = contents.getArgs()[contents.getArgs().length - 1];
            return last instanceof Component nested ? nested.getString() : String.valueOf(last);
        }
        return component.getString();
    }

    @Test
    void repeatedIdenticalStateNarratesExactlyOnce() {
        DialogueChoiceNarrator narrator = narrator();
        for (int frame = 0; frame < 5; frame++) {
            narrator.offer(7L, 3);
            narrator.focus(7L, 1, 3, Component.literal("Tell me more"));
            narrator.page(7L, 0, 2);
            narrator.locked(7L, 1, Component.literal("Tell me more"));
        }
        assertEquals(4, spoken.size(),
                "each transition must speak once no matter how many frames observe it");
    }

    @Test
    void movingFocusNarratesEachNewRow() {
        DialogueChoiceNarrator narrator = narrator();
        narrator.offer(1L, 3);
        spoken.clear();
        narrator.focus(1L, 0, 3, Component.literal("A"));
        narrator.focus(1L, 1, 3, Component.literal("B"));
        narrator.focus(1L, 1, 3, Component.literal("B"));
        narrator.focus(1L, 2, 3, Component.literal("C"));
        assertEquals(3, spoken.size());
        assertEquals("A", answerIn(0));
        assertEquals("B", answerIn(1));
        assertEquals("C", answerIn(2));
    }

    @Test
    void narrationForAStaleRevisionIsDropped() {
        DialogueChoiceNarrator narrator = narrator();
        narrator.offer(2L, 4);
        spoken.clear();
        narrator.focus(1L, 0, 4, Component.literal("stale"));
        narrator.page(1L, 1, 2);
        narrator.locked(1L, 0, Component.literal("stale"));
        assertTrue(spoken.isEmpty(), "a superseded offer must never narrate over the current one");
    }

    @Test
    void aNewOfferResetsDeduplicationSoTheSameIndexNarratesAgain() {
        DialogueChoiceNarrator narrator = narrator();
        narrator.offer(1L, 2);
        narrator.focus(1L, 0, 2, Component.literal("Yes"));
        spoken.clear();
        narrator.offer(2L, 2);
        narrator.focus(2L, 0, 2, Component.literal("Yes"));
        assertEquals(2, spoken.size(),
                "the new offer and its first focused row must both be announced");
    }

    @Test
    void resetClearsEveryDeduplicationLatch() {
        DialogueChoiceNarrator narrator = narrator();
        narrator.offer(1L, 2);
        narrator.focus(1L, 0, 2, Component.literal("Yes"));
        narrator.reset();
        spoken.clear();
        narrator.offer(1L, 2);
        narrator.focus(1L, 0, 2, Component.literal("Yes"));
        assertEquals(2, spoken.size());
    }

    @Test
    void aThrowingNarratorNeverEscapesIntoTheRenderPath() {
        DialogueChoiceNarrator narrator = new DialogueChoiceNarrator(component -> {
            throw new IllegalStateException("no narrator on this client");
        });
        assertDoesNotThrow(() -> {
            narrator.offer(1L, 2);
            narrator.focus(1L, 0, 2, Component.literal("Yes"));
            narrator.page(1L, 0, 1);
            narrator.locked(1L, 0, Component.literal("Yes"));
        });
    }
}
