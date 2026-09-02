package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.util.FormattedCharSequence;

import java.util.List;

/** One immutable render plan for the currently visible height-aware page. */
public record PreparedDialogueCard(
        long offerRevision,
        DialogueChoiceLayout.Layout layout,
        List<FormattedCharSequence> questionLines,
        List<PreparedChoiceRow> visibleRows,
        int lineStep,
        int numberColumn,
        boolean compact
) {
    public PreparedDialogueCard {
        questionLines = List.copyOf(questionLines);
        visibleRows = List.copyOf(visibleRows);
    }

    public DialogueHitTarget hit(double mouseX, double mouseY) {
        for (PreparedChoiceRow row : visibleRows) {
            if (row.hitRect().contains(mouseX, mouseY)) {
                return new DialogueHitTarget.Choice(row.absoluteIndex());
            }
        }
        if (layout.previousPage() != null && layout.previousPage().contains(mouseX, mouseY)) {
            return new DialogueHitTarget.PreviousPage();
        }
        if (layout.nextPage() != null && layout.nextPage().contains(mouseX, mouseY)) {
            return new DialogueHitTarget.NextPage();
        }
        return new DialogueHitTarget.None();
    }

    public PreparedChoiceRow row(int absoluteIndex) {
        for (PreparedChoiceRow row : visibleRows) {
            if (row.absoluteIndex() == absoluteIndex) {
                return row;
            }
        }
        return null;
    }
}
