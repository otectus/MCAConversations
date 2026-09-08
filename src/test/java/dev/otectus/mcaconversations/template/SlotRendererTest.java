package dev.otectus.mcaconversations.template;

import dev.otectus.mcaconversations.history.NarrativeValue;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A slot renders into the middle of a sentence, so the two failures that matter are a raw lang key
 * spoken out loud and a name that is not the name the player gave.
 */
class SlotRendererTest {

    private static String keyOf(Component component) {
        return component.getContents() instanceof TranslatableContents contents ? contents.getKey() : "";
    }

    @Test
    void aVillageNameIsSpokenExactlyAsThePlayerWroteIt() {
        Component rendered = SlotRenderer.render(NarrativeValue.literal("São João's Hollow!"), null, "village");
        assertEquals("São João's Hollow!", rendered.getString());
        assertTrue(keyOf(rendered).isEmpty(), "a name is literal text, never a lang key");
    }

    @Test
    void formattingCodesAreStrippedAndTheLengthIsBounded() {
        assertEquals("magic Hollow",
                SlotRenderer.render(NarrativeValue.literal("§kmagic §rHollow"), null, "village").getString());

        String long_ = "A".repeat(200);
        String rendered = SlotRenderer.render(NarrativeValue.literal(long_), null, "village").getString();
        assertEquals(NarrativeValue.MAX_LITERAL_LENGTH, rendered.length());
    }

    @Test
    void anAbsentVillageNameSaysTheVillageAndNeverARawSlotKey() {
        // Both routes into the fallback: a value that sanitised away to nothing, and no value at all.
        for (Component rendered : new Component[]{
                SlotRenderer.render(NarrativeValue.literal("§a§b"), null, "village"),
                SlotRenderer.render(null, null, "village"),
                SlotRenderer.fallbackFor("village")}) {
            assertEquals("mcaconversations.fallback.village", keyOf(rendered));
            assertFalse(keyOf(rendered).startsWith(SlotRenderer.TOKEN_PREFIX));
        }
        // An unnamed slot keeps the neutral phrase it always had.
        assertEquals(SlotRenderer.FALLBACK_KEY, keyOf(SlotRenderer.render(null, null, "")));
    }

    @Test
    void anAuthoredLocationTokenStillGoesThroughTheLocale() {
        assertEquals(SlotRenderer.TOKEN_PREFIX + "the_east_field",
                keyOf(SlotRenderer.render(NarrativeValue.token("the_east_field"), null, "location")));
    }

    @Test
    void aMalformedRegistryIdFallsBackRatherThanSpeakingItsOwnId() {
        assertEquals(SlotRenderer.FALLBACK_KEY,
                keyOf(SlotRenderer.render(new NarrativeValue(NarrativeValue.Kind.REGISTRY_ID,
                        "not a registry id"), null, "item")));
    }
}
