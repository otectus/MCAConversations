package dev.otectus.mcaconversations.client.dialogue;

import java.util.ArrayList;
import java.util.List;

/** Pure responsive geometry for the question card and content-sized choice rows. */
public final class DialogueChoiceLayout {

    public static final int OUTER_MARGIN = 16;
    public static final int SMALL_MARGIN = 8;
    public static final int INNER_PADDING = 8;
    public static final int NUMBER_COLUMN = 24;
    public static final int ROW_GAP = 2;

    public record Rect(int x, int y, int width, int height) {
        public boolean contains(double mouseX, double mouseY) {
            return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
        }
    }

    public record Layout(Rect panel, int questionX, int questionY, int dividerY,
                         List<Rect> rows, int footerY) {
        public Layout {
            rows = List.copyOf(rows);
        }
    }

    private DialogueChoiceLayout() {
    }

    public static int panelWidth(int screenWidth) {
        int margin = screenWidth < 252 ? SMALL_MARGIN : OUTER_MARGIN;
        return Math.max(120, Math.min(380, screenWidth - margin * 2));
    }

    public static int answerTextWidth(int screenWidth) {
        return Math.max(40, panelWidth(screenWidth) - INNER_PADDING * 2 - NUMBER_COLUMN - 6);
    }

    public static int questionTextWidth(int screenWidth) {
        return Math.max(40, panelWidth(screenWidth) - INNER_PADDING * 2);
    }

    public static Layout create(int screenWidth, int screenHeight, int questionLines,
                                List<Integer> rowHeights, boolean footer) {
        int width = panelWidth(screenWidth);
        int questionHeight = Math.max(9, Math.max(1, questionLines) * 9);
        int rowsHeight = rowHeights.stream().mapToInt(height -> Math.max(18, height)).sum()
                + Math.max(0, rowHeights.size() - 1) * ROW_GAP;
        int footerHeight = footer ? 17 : 0;
        int height = INNER_PADDING + questionHeight + 8 + 1 + 7
                + rowsHeight + footerHeight + INNER_PADDING;
        int x = (screenWidth - width) / 2;
        int y = Math.max(SMALL_MARGIN, (screenHeight - height) / 2);
        int questionY = y + INNER_PADDING;
        int dividerY = questionY + questionHeight + 7;
        int rowY = dividerY + 8;
        List<Rect> rows = new ArrayList<>(rowHeights.size());
        for (int rowHeight : rowHeights) {
            int actual = Math.max(18, rowHeight);
            rows.add(new Rect(x + INNER_PADDING, rowY, width - INNER_PADDING * 2, actual));
            rowY += actual + ROW_GAP;
        }
        int footerY = footer ? rowY + 4 : -1;
        return new Layout(new Rect(x, y, width, height), x + INNER_PADDING, questionY,
                dividerY, rows, footerY);
    }
}
