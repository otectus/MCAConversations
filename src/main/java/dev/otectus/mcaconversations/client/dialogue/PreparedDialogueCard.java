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

    /**
     * Hit testing by region. A row is only hit inside the response viewport: a row scrolled past the
     * top of the viewport is still in the document, and must not be clickable through the question.
     */
    public DialogueHitTarget hit(double mouseX, double mouseY) {
        if (layout.previousPage() != null && layout.previousPage().contains(mouseX, mouseY)) {
            return new DialogueHitTarget.PreviousPage();
        }
        if (layout.nextPage() != null && layout.nextPage().contains(mouseX, mouseY)) {
            return new DialogueHitTarget.NextPage();
        }
        if (layout.responseViewport() != null && layout.responseViewport().contains(mouseX, mouseY)) {
            for (PreparedChoiceRow row : visibleRows) {
                if (row.hitRect().contains(mouseX, mouseY)) {
                    return new DialogueHitTarget.Choice(row.absoluteIndex());
                }
            }
            return new DialogueHitTarget.Responses();
        }
        if (layout.questionViewport() != null && layout.questionViewport().contains(mouseX, mouseY)) {
            return new DialogueHitTarget.Question();
        }
        return new DialogueHitTarget.None();
    }

    /** Whether the question needs more lines than its reading region can show at once. */
    public boolean questionOverflows() {
        return questionLines.size() > layout.questionLines();
    }

    /** Whether the laid-out rows are taller than the viewport they scroll inside. */
    public boolean responsesOverflow() {
        return layout.responseViewport() != null
                && layout.documentHeight() > layout.responseViewport().height();
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
