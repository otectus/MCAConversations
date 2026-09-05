package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import dev.otectus.mcaconversations.McaConversationsConfig.MotionMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Motion is now derived from two inputs rather than one, and the risk that creates is that a style
 * quietly gains motion the player switched off, or that the default card's numbers drift while
 * someone is tuning the new one. Both are asserted here against the profile itself, which is where
 * every duration on the card comes from.
 */
class ConversationMotionSpecTest {

    @Test
    void offIsInstantForEveryStyle() {
        for (DialogueMenuStyle style : DialogueMenuStyle.values()) {
            ConversationMotionSpec spec = ConversationMotionSpec.of(MotionMode.OFF, style);
            assertEquals(0.0F, spec.enterTicks(), style.name());
            assertEquals(0.0F, spec.rowEntryTicks(), style.name());
            assertEquals(0.0F, spec.focusTicks(), style.name());
            assertEquals(0.0F, spec.focusExitTicks(), style.name());
            assertEquals(0.0F, spec.selectionPressTicks(), style.name());
            assertEquals(0.0F, spec.selectionSettleTicks(), style.name());
            assertEquals(0.0F, spec.pageTicks(), style.name());
            assertEquals(0.0F, spec.exitTicks(), style.name());
            assertEquals(0, spec.enterDistance(), style.name());
            assertEquals(0, spec.focusOutset(), style.name());
            assertEquals(0, spec.focusLift(), style.name());
            assertEquals(0, spec.rowEntryDistance(), style.name());
            assertEquals(0, spec.pageDistance(), style.name());
            assertEquals(0.0F, spec.rowStagger(), style.name());
            assertEquals(0.0F, spec.selectionPressDepth(), style.name());
            assertEquals(0.0F, spec.selectionSettleRise(), style.name());
            assertTrue(spec.instant(), style.name());
        }
    }

    @Test
    void reducedFadesWithoutMovingForEveryStyle() {
        for (DialogueMenuStyle style : DialogueMenuStyle.values()) {
            ConversationMotionSpec spec = ConversationMotionSpec.of(MotionMode.REDUCED, style);
            assertEquals(0, spec.enterDistance(), style.name());
            assertEquals(0, spec.rowEntryDistance(), style.name());
            assertEquals(0, spec.pageDistance(), style.name());
            assertEquals(0, spec.focusOutset(), style.name());
            assertEquals(0, spec.focusLift(), style.name());
            assertEquals(0.0F, spec.rowStagger(), style.name());
            assertEquals(0.0F, spec.selectionPressDepth(), style.name());
            assertEquals(0.0F, spec.selectionSettleRise(), style.name());
            assertTrue(spec.enterTicks() > 0.0F, "a fade is still a fade");
        }
    }

    @Test
    void responsiveFullStillHasTheShipped151Numbers() {
        // These are what a player upgrading from 1.5.1 already sees. Nothing about adding a second
        // style is a reason for them to change.
        ConversationMotionSpec spec =
                ConversationMotionSpec.of(MotionMode.FULL, DialogueMenuStyle.RESPONSIVE);
        assertEquals(4.0F, spec.enterTicks());
        assertEquals(3.0F, spec.rowEntryTicks());
        assertEquals(2.5F, spec.focusTicks());
        assertEquals(2.0F, spec.focusExitTicks());
        assertEquals(1.5F, spec.selectionPressTicks());
        assertEquals(2.0F, spec.selectionSettleTicks());
        assertEquals(3.0F, spec.pageTicks());
        assertEquals(2.0F, spec.exitTicks());
        assertEquals(4, spec.enterDistance());
        assertEquals(4, spec.focusOutset());
        assertEquals(1, spec.focusLift());
        assertEquals(3, spec.rowEntryDistance());
        assertEquals(4, spec.pageDistance());
        assertEquals(0.35F, spec.rowStagger());
        assertEquals(3.0F, spec.selectionPressDepth(), "the press depth the visual state used to own");
        assertEquals(2.0F, spec.selectionSettleRise(), "the settle rise the visual state used to own");
    }

    @Test
    void minimalFullIsRestrainedRatherThanFull() {
        ConversationMotionSpec spec =
                ConversationMotionSpec.of(MotionMode.FULL, DialogueMenuStyle.MINIMAL);
        assertTrue(spec.enterDistance() <= 2, "an entrance you notice only if you look for it");
        assertEquals(0.0F, spec.rowStagger());
        assertEquals(0, spec.rowEntryDistance());
        assertEquals(0, spec.focusOutset());
        assertEquals(0, spec.focusLift());
        assertEquals(0.0F, spec.selectionPressDepth());
        assertEquals(0.0F, spec.selectionSettleRise());
        assertTrue(spec.focusTicks() > 0.0F, "the focus transition is what tells you focus moved");
    }

    @Test
    void mcaOriginalNeverGetsItsOwnMotionProfile() {
        // Conversations does not draw under that style, so asking for its motion must not be a
        // special case that could go wrong on a render path; it answers the default card's profile.
        assertSame(ConversationMotionSpec.of(MotionMode.FULL, DialogueMenuStyle.RESPONSIVE),
                ConversationMotionSpec.of(MotionMode.FULL, DialogueMenuStyle.MCA_ORIGINAL));
    }
}
