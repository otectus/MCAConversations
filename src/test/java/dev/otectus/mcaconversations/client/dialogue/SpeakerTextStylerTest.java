package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpeakerTextStylerTest {

    @Test
    void stylesOnlyTheLeadingUnicodeNameAndPreservesBodyText() {
        Component source = Component.literal("[MCA] José 🐝: José saw a rose.")
                .withStyle(style -> style.withItalic(true));
        FormattedText styled = SpeakerTextStyler.style(source, Component.literal("José 🐝"), false);
        assertEquals(source.getString(), styled.getString());

        List<Run> runs = runs(styled);
        assertTrue(runs.stream().anyMatch(run -> run.text().equals("José 🐝")
                && run.style().isBold()
                && run.style().getColor().getValue()
                        == (ConversationPalette.SPEAKER_NAME & 0x00FFFFFF)));
        // The colon is its own run only so the bold stops at the name; it keeps the source style.
        assertTrue(runs.stream().anyMatch(run -> run.text().equals(":")
                && !run.style().isBold() && run.style().isItalic()));
        assertTrue(runs.stream().anyMatch(run -> run.text().contains("José saw a rose")
                && !run.style().isBold() && run.style().isItalic()));
    }

    @Test
    void silentOrAmbiguousPromptsRemainUntouched() {
        Component source = Component.literal("Think about Rose: the rose garden");
        assertSame(source, SpeakerTextStyler.style(source, Component.literal("Rose"), true));
        assertSame(source, SpeakerTextStyler.style(source, Component.literal("Rose"), false));
    }

    private static List<Run> runs(FormattedText text) {
        List<Run> result = new ArrayList<>();
        text.visit((style, value) -> {
            result.add(new Run(value, style));
            return java.util.Optional.empty();
        }, Style.EMPTY);
        return result;
    }

    private record Run(String text, Style style) {}
}
