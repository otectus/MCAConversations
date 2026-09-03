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
        boolean textClipped
) {
    public PreparedChoiceRow {
        lines = List.copyOf(lines);
    }
}
