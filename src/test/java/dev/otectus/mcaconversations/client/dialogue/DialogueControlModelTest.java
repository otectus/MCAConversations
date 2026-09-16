package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.ToIntFunction;

import static org.junit.jupiter.api.Assertions.*;

/**
 * One model of what the player can actually press, so the footer and the narrator cannot disagree.
 * Advertising a digit the key handler ignores is the failure this exists to prevent.
 *
 * <p>Without a loaded language a translatable renders as its bare key, so these assertions read the
 * key and the arguments rather than the sentence.
 */
class DialogueControlModelTest {

    private static String key(Component component) {
        return component.getContents() instanceof TranslatableContents contents
                ? contents.getKey() : component.getString();
    }

    private static Object[] args(Component component) {
        return component.getContents() instanceof TranslatableContents contents
                ? contents.getArgs() : new Object[0];
    }

    /** Deliberately not the rendered width: the choice between variants is what is under test. */
    private static final ToIntFunction<Component> WIDTH = component -> switch (key(component)) {
        case "gui.mcaconversations.responses.hint_reading" -> 200;
        case "gui.mcaconversations.responses.hint" -> 120;
        case "gui.mcaconversations.responses.hint_compact" -> 40;
        default -> 30;
    };

    @Test
    void disabledShortcutsNeverAppearAsControls() {
        DialogueControlModel off = new DialogueControlModel(false, 5, false, false);
        assertTrue(off.shortcutFor(1).isEmpty());
        assertEquals("", off.shortcutRange());
        for (Component variant : off.hintVariants(false)) {
            assertTrue(key(variant).contains("hint_keys"),
                    "with digits off the footer must use the keys-only hints: " + key(variant));
            assertEquals(0, args(variant).length,
                    "a keys-only hint has no shortcut range to name");
        }
    }

    @Test
    void enabledShortcutsAreNamedOnceAndBoundedByThePage() {
        DialogueControlModel on = new DialogueControlModel(true, 4, false, false);
        assertEquals("1-4", on.shortcutRange());
        assertEquals(1, on.shortcutFor(1).orElseThrow());
        assertTrue(on.shortcutFor(5).isEmpty(), "a row past the last digit has no shortcut");
        assertEquals("1", new DialogueControlModel(true, 1, false, false).shortcutRange());
        assertEquals(DialogueChoiceLayout.MAX_VISIBLE_SHORTCUTS,
                new DialogueControlModel(true, 40, false, false).shortcutCount(),
                "there are never more shortcuts than there are digits");
    }

    @Test
    void narrationNamesTheShortcutOnlyWhenThereIsOne() {
        DialogueControlModel on = new DialogueControlModel(true, 9, true, false);
        Component named = on.narration(9, 12, 1, Component.literal("Tell me more"));
        assertEquals("gui.mcaconversations.responses.narration_shortcut", key(named));
        assertArrayEquals(new Object[]{10, 12, 1, Component.literal("Tell me more")}, args(named));

        DialogueControlModel off = new DialogueControlModel(false, 9, true, false);
        assertEquals("gui.mcaconversations.responses.narration",
                key(off.narration(9, 12, 1, Component.literal("Tell me more"))));
        assertEquals("gui.mcaconversations.responses.narration",
                key(on.narration(11, 12, 10, Component.literal("x"))),
                "a row with no digit is announced without one");
    }

    @Test
    void theHintShrinksToItsBudgetAndDisappearsRatherThanOverlap() {
        DialogueControlModel model = new DialogueControlModel(true, 4, true, true);
        List<Component> variants = model.hintVariants(false);
        assertEquals(3, variants.size());
        assertEquals("gui.mcaconversations.responses.hint_reading", key(model.hint(300, false, WIDTH)));
        assertEquals("gui.mcaconversations.responses.hint", key(model.hint(150, false, WIDTH)));
        assertEquals("gui.mcaconversations.responses.hint_compact", key(model.hint(50, false, WIDTH)));
        assertNull(model.hint(10, false, WIDTH),
                "nothing is drawn rather than something drawn over the page controls");
    }

    @Test
    void theReadingActionIsOnlyOfferedWhenThereIsSomethingToRead() {
        DialogueControlModel without = new DialogueControlModel(true, 4, false, false);
        DialogueControlModel with = new DialogueControlModel(true, 4, false, true);
        assertEquals("gui.mcaconversations.responses.hint", key(without.hintVariants(false).get(0)));
        assertEquals("gui.mcaconversations.responses.hint_reading",
                key(with.hintVariants(false).get(0)));
    }
}
