package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The reveal shows a prefix of the finished line, with its styling intact.
 *
 * <p>The failure worth guarding is subtle: an implementation that shortens the text and re-wraps it
 * produces the right number of characters and the wrong layout, because a half-written sentence
 * breaks lines differently from a whole one, so words visibly jump as it types. Working on the
 * wrapped line makes that impossible, and these assertions pin it there.
 */
class QuestionRevealTest {

    private record Emitted(int codePoint, Style style) {
    }

    private static List<Emitted> collect(FormattedCharSequence sequence) {
        List<Emitted> out = new ArrayList<>();
        sequence.accept((index, style, codePoint) -> {
            out.add(new Emitted(codePoint, style));
            return true;
        });
        return out;
    }

    private static FormattedCharSequence line(String text, Style style) {
        return Component.literal(text).withStyle(style).getVisualOrderText();
    }

    @Test
    void lengthCountsEveryCodePoint() {
        assertEquals(0, QuestionReveal.length(FormattedCharSequence.EMPTY));
        assertEquals(5, QuestionReveal.length(line("Hello", Style.EMPTY)));
        assertEquals(0, QuestionReveal.length(null));
    }

    @Test
    void limitEmitsExactlyThePrefix() {
        FormattedCharSequence source = line("Good morning", Style.EMPTY);
        for (int limit = 0; limit <= 12; limit++) {
            List<Emitted> shown = collect(QuestionReveal.limit(source, limit));
            assertEquals(limit, shown.size(), "limit " + limit);
            StringBuilder text = new StringBuilder();
            shown.forEach(e -> text.appendCodePoint(e.codePoint()));
            assertEquals("Good morning".substring(0, limit), text.toString());
        }
    }

    @Test
    void limitBeyondTheLineEmitsTheWholeLine() {
        FormattedCharSequence source = line("Hi", Style.EMPTY);
        assertEquals(2, collect(QuestionReveal.limit(source, 99)).size());
    }

    @Test
    void revealedCharactersKeepTheStyleTheWholeLineGivesThem() {
        Style gold = Style.EMPTY.withColor(0xFFC34D).withBold(true);
        FormattedCharSequence source = line("Rose", gold);
        List<Emitted> whole = collect(source);
        List<Emitted> partial = collect(QuestionReveal.limit(source, 2));
        assertEquals(2, partial.size());
        for (int i = 0; i < partial.size(); i++) {
            assertEquals(whole.get(i).style(), partial.get(i).style(),
                    "character " + i + " must not change appearance as the reveal grows");
            assertEquals(whole.get(i).codePoint(), partial.get(i).codePoint());
        }
    }

    @Test
    void aLongLineStillFinishesWithinTheCap() {
        assertTrue(QuestionReveal.revealTicks(10_000) <= 20.0F,
                "no question may take longer than the cap to appear");
        assertTrue(QuestionReveal.revealTicks(1) >= 1.0F);
        assertTrue(QuestionReveal.revealTicks(120) > QuestionReveal.revealTicks(12),
                "a longer line should take longer, up to the cap");
    }

    @Test
    void anEmptyOrNegativeBudgetShowsNothing() {
        FormattedCharSequence source = line("Hello", Style.EMPTY);
        assertTrue(collect(QuestionReveal.limit(source, 0)).isEmpty());
        assertTrue(collect(QuestionReveal.limit(source, -5)).isEmpty());
    }
}
