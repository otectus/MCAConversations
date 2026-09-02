package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.util.FormattedCharSequence;

/**
 * Reveals an already-wrapped line a few code points at a time.
 *
 * <h2>Why it works on the wrapped line</h2>
 *
 * <p>The obvious implementation -- shorten the text, then wrap it -- re-wraps every frame, which is
 * both the allocation the renderer works hardest to avoid and visibly wrong: words jump between lines
 * as the reveal grows, because a half-written sentence breaks in different places from a whole one.
 * Limiting the finished line instead means every character appears in the position it will keep.
 *
 * <p>Styling is preserved exactly, because the limit is applied to the sink rather than to the text:
 * each code point is passed through with the style the wrapper already gave it, so an accented
 * speaker name reveals in gold rather than turning gold once it finishes.
 */
public final class QuestionReveal {

    /** Code points per tick. Fast enough not to be a wait, slow enough to read as speech. */
    private static final float RATE = 4.0F;

    /** However long the line, the reveal is over within this many ticks. */
    private static final float MAX_TICKS = 20.0F;

    private QuestionReveal() {
    }

    /** Number of code points in a wrapped line. */
    public static int length(FormattedCharSequence line) {
        if (line == null) {
            return 0;
        }
        int[] count = {0};
        line.accept((index, style, codePoint) -> {
            count[0]++;
            return true;
        });
        return count[0];
    }

    /** How long a whole question of {@code totalCodePoints} should take to appear. */
    public static float revealTicks(int totalCodePoints) {
        return Math.min(MAX_TICKS, Math.max(1.0F, totalCodePoints / RATE));
    }

    /**
     * The first {@code codePoints} of {@code line}, styled exactly as the full line would be.
     * A limit at or beyond the line's length returns the line itself.
     */
    public static FormattedCharSequence limit(FormattedCharSequence line, int codePoints) {
        if (line == null || codePoints <= 0) {
            return FormattedCharSequence.EMPTY;
        }
        return sink -> {
            int[] shown = {0};
            line.accept((index, style, codePoint) -> {
                if (shown[0] >= codePoints) {
                    return false;
                }
                shown[0]++;
                return sink.accept(index, style, codePoint);
            });
            return true;
        };
    }
}
