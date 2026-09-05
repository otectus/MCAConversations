package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The capability table from §20 of the specification, asserted once so the renderer never has to
 * spell out {@code style != MINIMAL && style != MCA_ORIGINAL} again.
 *
 * <p>The floor is here rather than in the layout because it is a presentation decision: a style may
 * draw a plainer badge, but the numeral it holds is the same numeral at the same font size.
 */
class DialogueStyleProfileTest {

    @Test
    void responsiveKeepsEveryCapability() {
        DialogueStyleProfile profile = DialogueStyleProfile.of(DialogueMenuStyle.RESPONSIVE);
        assertTrue(profile.customRenderer());
        assertTrue(profile.portrait());
        assertTrue(profile.texturedBadges());
        assertTrue(profile.focusPopout());
        assertEquals(DialogueChoiceLayout.NUMBER_COLUMN, profile.numberColumnFloor(),
                "the 1.5.1 card must keep the gutter it has today");
    }

    @Test
    void minimalDrawsTheSameMenuWithFewerGraphics() {
        DialogueStyleProfile profile = DialogueStyleProfile.of(DialogueMenuStyle.MINIMAL);
        assertTrue(profile.customRenderer(), "MINIMAL is still the Conversations menu");
        assertFalse(profile.portrait(), "§10: no live portrait");
        assertFalse(profile.texturedBadges());
        assertFalse(profile.focusPopout(), "§9.3: focus must not pop out");
        assertTrue(profile.numberColumnFloor() > 0, "a numeral still needs a column");
        assertTrue(profile.numberColumnFloor() <= DialogueChoiceLayout.NUMBER_COLUMN);
    }

    @Test
    void mcaOriginalRunsNoRendererAtAll() {
        DialogueStyleProfile profile = DialogueStyleProfile.of(DialogueMenuStyle.MCA_ORIGINAL);
        assertFalse(profile.customRenderer(), "MCA Reborn draws its own menu");
        assertFalse(profile.portrait());
        assertFalse(profile.texturedBadges());
        assertFalse(profile.focusPopout());
    }

    @Test
    void profileLookupIsTotalAndAllocationFree() {
        // Resolved once per frame, so the constants must be shared rather than rebuilt.
        for (DialogueMenuStyle style : DialogueMenuStyle.values()) {
            assertNotNull(DialogueStyleProfile.of(style), style.name());
            assertSame(DialogueStyleProfile.of(style), DialogueStyleProfile.of(style), style.name());
        }
        assertSame(DialogueStyleProfile.RESPONSIVE, DialogueStyleProfile.of(null),
                "a null style means an unreadable config, which falls back to the shipped default");
    }

    @Test
    void everyStyleHasASkinIncludingTheOneThatNeverDraws() {
        // DialogueSkin.of is called on a render path; it answers rather than throwing even for the
        // style whose renderer is never reached.
        for (DialogueMenuStyle style : DialogueMenuStyle.values()) {
            assertNotNull(DialogueSkin.of(style), style.name());
        }
    }
}
