package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

/** Immutable translated row with separate static hit and animated visual geometry. */
public record PreparedChoiceRow(
        int absoluteIndex,
        int visibleNumber,
        DialogueChoiceLayout.Rect hitRect,
        DialogueChoiceLayout.Rect baseVisualRect,
        Component answer,
        List<FormattedCharSequence> lines,
        boolean textClipped,
        boolean expanded
) {
    public PreparedChoiceRow {
        lines = List.copyOf(lines);
    }

    /** Compatibility constructor for callers that never expand a row. */
    public PreparedChoiceRow(int absoluteIndex, int visibleNumber,
                             DialogueChoiceLayout.Rect hitRect,
                             DialogueChoiceLayout.Rect baseVisualRect, Component answer,
                             List<FormattedCharSequence> lines, boolean textClipped) {
        this(absoluteIndex, visibleNumber, hitRect, baseVisualRect, answer, lines, textClipped, false);
    }
}
