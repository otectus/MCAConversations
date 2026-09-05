package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/** Performs all translation, styling, wrapping and page measurement outside steady-state drawing. */
public final class DialoguePresentationBuilder {

    public record Model(
            long offerRevision,
            int screenWidth,
            int screenHeight,
            int fontLineHeight,
            List<FormattedCharSequence> questionLines,
            List<Component> answers,
            List<List<FormattedCharSequence>> answerLines,
            List<Integer> normalHeights,
            List<Integer> compactHeights,
            DialogueChoiceLayout.PageMap pageMap,
            DialogueChoiceLayout.HeaderSpec header,
            int numberColumn,
            boolean footer
    ) {
        public Model {
            questionLines = List.copyOf(questionLines);
            answers = List.copyOf(answers);
            answerLines = answerLines.stream().map(List::copyOf).toList();
            normalHeights = List.copyOf(normalHeights);
            compactHeights = List.copyOf(compactHeights);
        }

        /**
         * Re-packs pages for a different footer reservation. Only the page map depends on whether a
         * footer strip is reserved, so this reuses the wrapped text rather than splitting every
         * answer a second time.
         */
        public Model withFooter(boolean footer) {
            if (footer == this.footer) {
                return this;
            }
            // Reserving a footer changes how much height is left, which can be the difference
            // between the portrait fitting and not, so the header is re-resolved rather than kept.
            DialogueChoiceLayout.HeaderSpec resolved = DialogueChoiceLayout.headerFor(screenWidth,
                    screenHeight, fontLineHeight, header.hasPortrait(), footer, false);
            return new Model(offerRevision, screenWidth, screenHeight, fontLineHeight, questionLines,
                    answers, answerLines, normalHeights, compactHeights,
                    DialogueChoiceLayout.packPages(screenHeight, questionLines.size(), fontLineHeight,
                            normalHeights, compactHeights, footer, resolved),
                    resolved, numberColumn, footer);
        }
    }

    /**
     * Development-preview seam. Production resolves an answer through its dialogue translation key;
     * the preview screen substitutes literal fixture text so wrapping and paging can be exercised at
     * controlled lengths without shipping preview strings in the language files. Null in production,
     * and only ever installed for the duration of one scoped call.
     */
    private static Function<String, Component> answerTextOverride;

    private DialoguePresentationBuilder() {
    }

    /** Runs {@code body} with answer text resolved by {@code resolver} instead of by translation. */
    public static void withAnswerText(Function<String, Component> resolver, Runnable body) {
        Function<String, Component> previous = answerTextOverride;
        answerTextOverride = resolver;
        try {
            body.run();
        } finally {
            answerTextOverride = previous;
        }
    }

    public static Model prepare(Font font, int screenWidth, int screenHeight,
                                FormattedText exactQuestion,
                                List<FormattedCharSequence> legacyQuestion,
                                Component speakerName, boolean silent,
                                ClientChoiceState.ClientChoiceOffer offer,
                                boolean footer, boolean portrait,
                                DialogueStyleProfile profile) {
        DialogueChoiceLayout.HeaderSpec header = DialogueChoiceLayout.headerFor(
                screenWidth, screenHeight, font.lineHeight, portrait, footer, false);
        FormattedText question = exactQuestion != null ? exactQuestion
                : SpeakerTextStyler.fromLegacy(legacyQuestion);
        question = SpeakerTextStyler.style(question, speakerName, silent);
        List<FormattedCharSequence> questionLines = List.copyOf(
                font.split(question, DialogueChoiceLayout.questionTextWidth(screenWidth, header)));
        if (questionLines.isEmpty()) {
            questionLines = List.of(FormattedCharSequence.EMPTY);
        }

        // The badge gutter follows the active font, so a wide font widens the column rather than
        // letting the badge sit underneath the first character of every answer.
        int numberColumn = DialogueChoiceLayout.numberColumn(font.width("9."), profile);
        int answerWidth = DialogueChoiceLayout.answerTextWidth(screenWidth, numberColumn);
        List<Component> answers = new ArrayList<>(offer.answerIds().size());
        List<List<FormattedCharSequence>> lines = new ArrayList<>(offer.answerIds().size());
        List<Integer> normal = new ArrayList<>(offer.answerIds().size());
        List<Integer> compact = new ArrayList<>(offer.answerIds().size());
        for (String answerId : offer.answerIds()) {
            Component answer = answerTextOverride != null
                    ? answerTextOverride.apply(answerId)
                    : Component.translatable("dialogue." + offer.questionId() + "." + answerId);
            List<FormattedCharSequence> wrapped = List.copyOf(font.split(answer, answerWidth));
            if (wrapped.isEmpty()) {
                wrapped = List.of(FormattedCharSequence.EMPTY);
            }
            answers.add(answer);
            lines.add(wrapped);
            normal.add(DialogueChoiceLayout.rowHeight(wrapped.size(), font.lineHeight, false));
            compact.add(DialogueChoiceLayout.rowHeight(wrapped.size(), font.lineHeight, true));
        }
        DialogueChoiceLayout.PageMap pages = DialogueChoiceLayout.packPages(screenHeight,
                questionLines.size(), font.lineHeight, normal, compact, footer, header);
        return new Model(offer.revision(), screenWidth, screenHeight, font.lineHeight,
                questionLines, answers, lines, normal, compact, pages, header, numberColumn, footer);
    }

    public static PreparedDialogueCard page(Model model, ClientChoiceState state) {
        if (model.pageMap().pages().isEmpty()) {
            return null;
        }
        state.updatePages(model.pageMap().pages());
        int pageIndex = Math.max(0, Math.min(state.page(), model.pageMap().pages().size() - 1));
        DialogueChoiceLayout.ChoicePage page = model.pageMap().pages().get(pageIndex);
        boolean compact = model.pageMap().compact();
        List<Integer> sourceHeights = compact ? model.compactHeights() : model.normalHeights();
        List<Integer> visibleHeights = sourceHeights.subList(page.firstInclusive(), page.lastExclusive());
        DialogueChoiceLayout.Layout layout = DialogueChoiceLayout.create(
                model.screenWidth(), model.screenHeight(), model.questionLines().size(),
                model.fontLineHeight(), visibleHeights, model.footer(), compact,
                pageIndex > 0, pageIndex + 1 < model.pageMap().pages().size(), model.header());
        List<PreparedChoiceRow> rows = new ArrayList<>(page.size());
        for (int i = 0; i < page.size(); i++) {
            int absolute = page.firstInclusive() + i;
            DialogueChoiceLayout.Rect rect = layout.rows().get(i);
            rows.add(new PreparedChoiceRow(absolute, i + 1, rect, rect,
                    model.answers().get(absolute), model.answerLines().get(absolute),
                    sourceHeights.get(absolute) > rect.height()));
        }
        return new PreparedDialogueCard(model.offerRevision(), layout, model.questionLines(), rows,
                DialogueChoiceLayout.lineStep(model.fontLineHeight()), model.numberColumn(), compact);
    }
}
