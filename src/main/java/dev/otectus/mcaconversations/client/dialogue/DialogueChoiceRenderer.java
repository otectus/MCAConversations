package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

/** Cached translation/wrapping plus the restrained, opaque-enough response card renderer. */
public final class DialogueChoiceRenderer {

    private static final int PANEL = 0xE0181818;
    private static final int PANEL_BORDER = 0xFF777777;
    private static final int DIVIDER = 0xFF696969;
    private static final int ROW = 0xB0262626;
    private static final int ROW_HOVER = 0xE03A3A3A;
    private static final int ROW_LOCKED = 0xE0443521;
    private static final int FOCUS = 0xFFFFC34D;
    private static final int TEXT = 0xFFF2F2F2;
    private static final int MUTED = 0xFFAAAAAA;

    private CacheKey key;
    private Prepared prepared;

    public void render(GuiGraphics graphics, int mouseX, int mouseY,
                       List<FormattedCharSequence> questionLines) {
        ClientChoiceState state = ClientChoiceMessages.state();
        ClientChoiceState.ClientChoiceOffer offer = state.offer().orElse(null);
        if (offer == null) {
            prepared = null;
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        int width = graphics.guiWidth();
        int height = graphics.guiHeight();
        boolean footer = ClientChoiceController.showHints() || state.pageCount() > 1;
        CacheKey wanted = new CacheKey(offer.revision(), state.page(), width, height, footer,
                questionLines == null ? 0 : questionLines.size(), System.identityHashCode(questionLines));
        if (!wanted.equals(key)) {
            key = wanted;
            prepared = prepare(font, width, height, questionLines, offer, state, footer);
        }

        for (int i = 0; i < prepared.layout.rows().size(); i++) {
            if (prepared.layout.rows().get(i).contains(mouseX, mouseY) && !state.locked()) {
                state.focus(prepared.firstAbsoluteIndex + i);
                break;
            }
        }

        DialogueChoiceLayout.Rect panel = prepared.layout.panel();
        graphics.fill(panel.x(), panel.y(), panel.x() + panel.width(), panel.y() + panel.height(), PANEL);
        outline(graphics, panel, PANEL_BORDER);

        int questionY = prepared.layout.questionY();
        for (FormattedCharSequence line : prepared.questionLines) {
            graphics.drawString(font, line, prepared.layout.questionX(), questionY, TEXT, false);
            questionY += 9;
        }
        graphics.fill(panel.x() + DialogueChoiceLayout.INNER_PADDING, prepared.layout.dividerY(),
                panel.x() + panel.width() - DialogueChoiceLayout.INNER_PADDING,
                prepared.layout.dividerY() + 1, DIVIDER);

        for (int i = 0; i < prepared.rows.size(); i++) {
            int absolute = prepared.firstAbsoluteIndex + i;
            DialogueChoiceLayout.Rect rect = prepared.layout.rows().get(i);
            boolean focused = state.focusedIndex() == absolute;
            boolean selected = state.lockedIndex() == absolute;
            graphics.fill(rect.x(), rect.y(), rect.x() + rect.width(), rect.y() + rect.height(),
                    selected ? ROW_LOCKED : focused ? ROW_HOVER : ROW);
            if (focused || selected) {
                outline(graphics, rect, FOCUS);
                graphics.fill(rect.x(), rect.y(), rect.x() + 2, rect.y() + rect.height(), FOCUS);
            }
            graphics.drawString(font, (i + 1) + ".", rect.x() + 6, rect.y() + 5, FOCUS, false);
            int textY = rect.y() + 5;
            for (FormattedCharSequence line : prepared.rows.get(i)) {
                graphics.drawString(font, line,
                        rect.x() + DialogueChoiceLayout.NUMBER_COLUMN, textY, TEXT, false);
                textY += 9;
            }
        }

        if (prepared.layout.footerY() >= 0) {
            int footerY = prepared.layout.footerY();
            if (ClientChoiceController.showHints()) {
                Component hint = state.locked()
                        ? Component.translatable("gui.mcaconversations.responses.selecting")
                        : Component.translatable("gui.mcaconversations.responses.hint",
                                "1-" + state.visibleCount());
                graphics.drawString(font, hint, panel.x() + DialogueChoiceLayout.INNER_PADDING,
                        footerY, MUTED, false);
            }
            if (state.pageCount() > 1) {
                Component page = Component.translatable("gui.mcaconversations.responses.page",
                        state.page() + 1, state.pageCount());
                int pageWidth = font.width(page);
                graphics.drawString(font, page,
                        panel.x() + panel.width() - DialogueChoiceLayout.INNER_PADDING - pageWidth,
                        footerY, MUTED, false);
            }
        }
    }

    public int hitIndex(double mouseX, double mouseY) {
        if (prepared == null) {
            return -1;
        }
        for (int i = 0; i < prepared.layout.rows().size(); i++) {
            if (prepared.layout.rows().get(i).contains(mouseX, mouseY)) {
                return prepared.firstAbsoluteIndex + i;
            }
        }
        return -1;
    }

    private static Prepared prepare(Font font, int screenWidth, int screenHeight,
                                    List<FormattedCharSequence> questionLines,
                                    ClientChoiceState.ClientChoiceOffer offer,
                                    ClientChoiceState state, boolean footer) {
        int first = state.firstOnPage();
        int end = Math.min(offer.answerIds().size(), first + ClientChoiceState.PAGE_SIZE);
        List<FormattedCharSequence> reflowedQuestion = reflowQuestion(font, screenWidth, questionLines);
        int textWidth = DialogueChoiceLayout.answerTextWidth(screenWidth);
        List<List<FormattedCharSequence>> rows = new ArrayList<>(end - first);
        List<Integer> heights = new ArrayList<>(end - first);
        for (int i = first; i < end; i++) {
            Component answer = Component.translatable(
                    "dialogue." + offer.questionId() + "." + offer.answerIds().get(i));
            List<FormattedCharSequence> lines = List.copyOf(font.split(answer, textWidth));
            rows.add(lines);
            heights.add(Math.max(18, lines.size() * 9 + 8));
        }
        DialogueChoiceLayout.Layout layout = DialogueChoiceLayout.create(screenWidth, screenHeight,
                reflowedQuestion.size(), heights, footer);
        return new Prepared(first, reflowedQuestion, rows, layout);
    }

    /** Rejoins MCA's legacy narrow lines and wraps them to the responsive card width. */
    private static List<FormattedCharSequence> reflowQuestion(Font font, int screenWidth,
                                                               List<FormattedCharSequence> legacyLines) {
        if (legacyLines == null || legacyLines.isEmpty()) {
            return List.of();
        }

        MutableComponent question = Component.empty();
        for (int lineIndex = 0; lineIndex < legacyLines.size(); lineIndex++) {
            if (lineIndex > 0) {
                question.append(" ");
            }
            legacyLines.get(lineIndex).accept((characterIndex, style, codePoint) -> {
                question.append(Component.literal(Character.toString(codePoint)).setStyle(style));
                return true;
            });
        }
        return List.copyOf(font.split(question, DialogueChoiceLayout.questionTextWidth(screenWidth)));
    }

    private static void outline(GuiGraphics graphics, DialogueChoiceLayout.Rect rect, int color) {
        graphics.fill(rect.x(), rect.y(), rect.x() + rect.width(), rect.y() + 1, color);
        graphics.fill(rect.x(), rect.y() + rect.height() - 1,
                rect.x() + rect.width(), rect.y() + rect.height(), color);
        graphics.fill(rect.x(), rect.y(), rect.x() + 1, rect.y() + rect.height(), color);
        graphics.fill(rect.x() + rect.width() - 1, rect.y(),
                rect.x() + rect.width(), rect.y() + rect.height(), color);
    }

    private record CacheKey(long revision, int page, int width, int height, boolean footer,
                            int questionLines, int questionIdentity) {
    }

    private record Prepared(int firstAbsoluteIndex, List<FormattedCharSequence> questionLines,
                            List<List<FormattedCharSequence>> rows,
                            DialogueChoiceLayout.Layout layout) {
    }
}
