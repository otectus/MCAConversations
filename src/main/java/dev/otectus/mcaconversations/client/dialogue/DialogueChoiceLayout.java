package dev.otectus.mcaconversations.client.dialogue;

import java.util.ArrayList;
import java.util.List;

/** Pure, font-aware geometry and height-driven page packing for the response card. */
public final class DialogueChoiceLayout {

    public static final int OUTER_MARGIN = 16;
    public static final int SMALL_MARGIN = 8;
    public static final int INNER_PADDING = 8;
    public static final int NUMBER_COLUMN = 24;
    public static final int ROW_GAP = 2;
    public static final int MAX_VISIBLE_SHORTCUTS = 9;
    public static final int SAFE_TOP = 8;
    /** Below this panel width the portrait is dropped rather than narrowing the reading column. */
    public static final int PORTRAIT_MIN_PANEL_WIDTH = 260;
    public static final int PORTRAIT_GAP = 6;

    public record Rect(int x, int y, int width, int height) {
        public boolean contains(double mouseX, double mouseY) {
            return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
        }

        public Rect outset(int horizontal, int vertical) {
            return new Rect(x - horizontal, y - vertical,
                    width + horizontal * 2, height + vertical * 2);
        }
    }

    public record ChoicePage(int firstInclusive, int lastExclusive) {
        public ChoicePage {
            if (firstInclusive < 0 || lastExclusive <= firstInclusive) {
                throw new IllegalArgumentException("empty or reversed choice page");
            }
        }

        public int size() {
            return lastExclusive - firstInclusive;
        }

        public boolean contains(int absoluteIndex) {
            return absoluteIndex >= firstInclusive && absoluteIndex < lastExclusive;
        }
    }

    public record PageMap(List<ChoicePage> pages, boolean compact, int availableRowsHeight) {
        public PageMap {
            pages = List.copyOf(pages);
        }
    }

    /**
     * What the header has to make room for besides the question text.
     *
     * <p>Grouped rather than threaded through as loose integers because the two values must agree:
     * a portrait that reserves a column but not a matching minimum height would be drawn over the
     * divider, and one that reserves height but no column would be drawn under the question.
     */
    public record HeaderSpec(int portraitColumn, int portraitSize) {
        public static final HeaderSpec NONE = new HeaderSpec(0, 0);

        public boolean hasPortrait() {
            return portraitColumn > 0 && portraitSize > 0;
        }

        /** Height the header must reserve whatever the question's own line count turns out to be. */
        public int minHeight() {
            return portraitSize;
        }
    }

    public record Layout(Rect panel, Rect header, Rect portrait, int questionX, int questionY,
                         int questionLines, int dividerY, List<Rect> rows, int footerY,
                         Rect previousPage, Rect nextPage) {
        public Layout {
            rows = List.copyOf(rows);
        }
    }

    private DialogueChoiceLayout() {
    }

    public static int panelWidth(int screenWidth) {
        int margin = screenWidth < 252 ? SMALL_MARGIN : OUTER_MARGIN;
        int cap = Math.max(1, screenWidth - margin * 2);
        int preferred = Math.round(screenWidth * 0.60F);
        return Math.min(cap, Math.max(220, Math.min(420, preferred)));
    }

    public static int answerTextWidth(int screenWidth) {
        return answerTextWidth(screenWidth, NUMBER_COLUMN);
    }

    public static int answerTextWidth(int screenWidth, int numberColumn) {
        return Math.max(40, panelWidth(screenWidth) - INNER_PADDING * 2 - numberColumn - 6);
    }

    /**
     * Width of the badge gutter. {@link #NUMBER_COLUMN} is the vanilla-font baseline; a resource
     * pack with a wider font needs more, and without this the badge is drawn underneath the first
     * character of every answer.
     */
    public static int numberColumn(int numeralWidth) {
        return Math.max(NUMBER_COLUMN, 4 + badgeWidth(numeralWidth) + 5);
    }

    private static int badgeWidth(int numeralWidth) {
        return Math.max(numeralWidth + 5, NUMBER_COLUMN - 9);
    }

    public static int questionTextWidth(int screenWidth) {
        return questionTextWidth(screenWidth, HeaderSpec.NONE);
    }

    public static int questionTextWidth(int screenWidth, HeaderSpec header) {
        return Math.max(40, panelWidth(screenWidth) - INNER_PADDING * 2 - header.portraitColumn());
    }

    /** Portrait edge length: three lines of the active font, never smaller than a readable box. */
    public static int portraitSize(int fontLineHeight) {
        return Math.max(28, lineStep(fontLineHeight) * 3);
    }

    /**
     * The header reservation for a portrait, or {@link HeaderSpec#NONE} when one should not be
     * shown. A narrow panel keeps its full reading width instead: on a 220-pixel card a portrait
     * would take a sixth of the line, which costs more than the portrait adds.
     */
    public static HeaderSpec headerFor(int screenWidth, int screenHeight, int fontLineHeight,
                                       boolean portrait, boolean footer, boolean compact) {
        if (!portrait || panelWidth(screenWidth) < PORTRAIT_MIN_PANEL_WIDTH) {
            return HeaderSpec.NONE;
        }
        int size = portraitSize(fontLineHeight);
        // A short screen gives its height to the answers; the portrait is the first thing to go.
        return size > questionSpace(screenHeight, fontLineHeight, footer, compact)
                ? HeaderSpec.NONE : new HeaderSpec(size + PORTRAIT_GAP, size);
    }

    public static int lineStep(int fontLineHeight) {
        return Math.max(1, fontLineHeight) + 1;
    }

    public static int rowHeight(int lineCount, int fontLineHeight, boolean compact) {
        int step = lineStep(fontLineHeight);
        int padding = compact ? 3 : 5;
        return Math.max(step + padding * 2, Math.max(1, lineCount) * step + padding * 2);
    }

    /**
     * Number-badge box for a row, sized from the active font rather than vanilla's historical
     * nine-pixel glyph. {@code numeralWidth} is the measured width of the label that will be drawn,
     * so a wide font or a two-character number still fits inside the badge instead of spilling into
     * the answer column.
     */
    public static Rect badgeRect(Rect row, int fontLineHeight, int numeralWidth) {
        int height = Math.min(row.height(), lineStep(fontLineHeight) + 1);
        return new Rect(row.x() + 4, row.y() + Math.max(0, (row.height() - height) / 2),
                badgeWidth(numeralWidth), height);
    }

    /** Top-left Y that vertically centres one line of the active font inside {@code box}. */
    public static int centeredTextY(Rect box, int fontLineHeight) {
        return box.y() + Math.max(0, (box.height() - Math.max(1, fontLineHeight)) / 2);
    }

    public static int maxPanelHeight(int screenHeight) {
        int safeBottom = screenHeight >= 220 ? 36 : 8;
        return Math.max(1, screenHeight - SAFE_TOP - safeBottom);
    }

    public static PageMap packPages(int screenHeight, int questionLines, int fontLineHeight,
                                    List<Integer> normalRowHeights, List<Integer> compactRowHeights,
                                    boolean footer) {
        return packPages(screenHeight, questionLines, fontLineHeight, normalRowHeights,
                compactRowHeights, footer, HeaderSpec.NONE);
    }

    public static PageMap packPages(int screenHeight, int questionLines, int fontLineHeight,
                                    List<Integer> normalRowHeights, List<Integer> compactRowHeights,
                                    boolean footer, HeaderSpec header) {
        if (normalRowHeights == null || normalRowHeights.isEmpty()) {
            return new PageMap(List.of(), false, 0);
        }
        int normalAvailable = availableRowsHeight(screenHeight, questionLines, fontLineHeight,
                footer, false, header);
        boolean compact = normalRowHeights.get(0) > normalAvailable;
        List<Integer> heights = compact && compactRowHeights != null
                && compactRowHeights.size() == normalRowHeights.size()
                ? compactRowHeights : normalRowHeights;
        int available = availableRowsHeight(screenHeight, questionLines, fontLineHeight,
                footer, compact, header);

        List<ChoicePage> pages = new ArrayList<>();
        int first = 0;
        int used = 0;
        int count = 0;
        for (int i = 0; i < heights.size(); i++) {
            int row = Math.max(1, heights.get(i));
            int added = row + (count == 0 ? 0 : ROW_GAP);
            // A full page is already closed at the foot of this loop, so only the height
            // bound can force a break here.
            if (count > 0 && used + added > available) {
                pages.add(new ChoicePage(first, i));
                first = i;
                used = 0;
                count = 0;
                added = row;
            }
            // An oversized answer owns a page; layout clips its viewport to the safe region.
            used += added;
            count++;
            if (count >= MAX_VISIBLE_SHORTCUTS || (row > available && count == 1)) {
                pages.add(new ChoicePage(first, i + 1));
                first = i + 1;
                used = 0;
                count = 0;
            }
        }
        if (first < heights.size()) {
            pages.add(new ChoicePage(first, heights.size()));
        }
        return new PageMap(pages, compact, available);
    }

    /**
     * How many question lines the card can show before it would have no room left for an answer.
     * A tall font on a short screen can want more question height than the whole safe area; the
     * question is clipped there rather than pushing the answer rows off the bottom of the panel.
     */
    public static int visibleQuestionLines(int screenHeight, int questionLines, int fontLineHeight,
                                           boolean footer, boolean compact) {
        int step = lineStep(fontLineHeight);
        int forQuestion = questionSpace(screenHeight, fontLineHeight, footer, compact);
        return Math.max(1, Math.min(Math.max(1, questionLines), Math.max(1, forQuestion / step)));
    }

    /** Vertical space the header may use before the card would have no room for an answer. */
    private static int questionSpace(int screenHeight, int fontLineHeight,
                                     boolean footer, boolean compact) {
        int step = lineStep(fontLineHeight);
        int padding = compact ? 6 : INNER_PADDING;
        int footerHeight = footer ? step + (compact ? 6 : 10) : 0;
        return maxPanelHeight(screenHeight) - padding * 2 - 16 - footerHeight - (step + 2);
    }

    /** Height of the whole header block: the visible question lines, or the portrait if taller. */
    private static int questionBlockHeight(int screenHeight, int questionLines, int fontLineHeight,
                                           boolean footer, boolean compact, HeaderSpec header) {
        int lines = visibleQuestionLines(screenHeight, questionLines, fontLineHeight, footer, compact);
        return Math.max(lines * lineStep(fontLineHeight), header.minHeight());
    }

    private static int availableRowsHeight(int screenHeight, int questionLines, int fontLineHeight,
                                           boolean footer, boolean compact, HeaderSpec header) {
        int step = lineStep(fontLineHeight);
        int questionHeight = questionBlockHeight(screenHeight, questionLines, fontLineHeight,
                footer, compact, header);
        int padding = compact ? 6 : INNER_PADDING;
        int footerHeight = footer ? step + (compact ? 6 : 10) : 0;
        int fixedChrome = padding + questionHeight + 16 + footerHeight + padding;
        return Math.max(step + 2, maxPanelHeight(screenHeight) - fixedChrome);
    }

    /** Compatibility overload for callers/tests that use vanilla's historical nine-pixel line height. */
    public static Layout create(int screenWidth, int screenHeight, int questionLines,
                                List<Integer> rowHeights, boolean footer) {
        return create(screenWidth, screenHeight, questionLines, 9, rowHeights,
                footer, false, false, false);
    }

    public static Layout create(int screenWidth, int screenHeight, int questionLines,
                                int fontLineHeight, List<Integer> rowHeights, boolean footer,
                                boolean compact, boolean hasPrevious, boolean hasNext) {
        return create(screenWidth, screenHeight, questionLines, fontLineHeight, rowHeights, footer,
                compact, hasPrevious, hasNext, HeaderSpec.NONE);
    }

    public static Layout create(int screenWidth, int screenHeight, int questionLines,
                                int fontLineHeight, List<Integer> rowHeights, boolean footer,
                                boolean compact, boolean hasPrevious, boolean hasNext,
                                HeaderSpec header) {
        int width = panelWidth(screenWidth);
        int step = lineStep(fontLineHeight);
        int padding = compact ? 6 : INNER_PADDING;
        int visibleQuestionLines = visibleQuestionLines(screenHeight, questionLines, fontLineHeight,
                footer, compact);
        int questionHeight = questionBlockHeight(screenHeight, questionLines, fontLineHeight,
                footer, compact, header);
        int rowsAvailable = availableRowsHeight(screenHeight, questionLines, fontLineHeight,
                footer, compact, header);
        int rowsHeight = 0;
        for (int i = 0; i < rowHeights.size(); i++) {
            int remaining = Math.max(step + 2, rowsAvailable - rowsHeight - (i == 0 ? 0 : ROW_GAP));
            rowsHeight += (i == 0 ? 0 : ROW_GAP) + Math.min(Math.max(1, rowHeights.get(i)), remaining);
        }
        int footerHeight = footer ? step + (compact ? 6 : 10) : 0;
        int height = Math.min(maxPanelHeight(screenHeight),
                padding + questionHeight + 16 + rowsHeight + footerHeight + padding);
        int x = (screenWidth - width) / 2;
        int y = Math.max(SAFE_TOP, SAFE_TOP + (maxPanelHeight(screenHeight) - height) / 2);
        int questionY = y + padding;
        int dividerY = questionY + questionHeight + 7;
        int rowY = dividerY + 8;
        int rowBottom = y + height - padding - footerHeight;
        List<Rect> rows = new ArrayList<>(rowHeights.size());
        for (int rowHeight : rowHeights) {
            int actual = Math.min(Math.max(step + 2, rowHeight), Math.max(step + 2, rowBottom - rowY));
            rows.add(new Rect(x + padding, rowY, width - padding * 2, actual));
            rowY += actual + ROW_GAP;
        }
        int footerY = footer ? y + height - padding - footerHeight + (compact ? 3 : 5) : -1;
        int controlY = footer ? y + height - padding - Math.max(18, step + 4) : -1;
        boolean paged = footer && (hasPrevious || hasNext);
        Rect previous = paged ? new Rect(x + width - padding - 38, controlY, 18, 18) : null;
        Rect next = paged ? new Rect(x + width - padding - 18, controlY, 18, 18) : null;
        Rect headerRect = new Rect(x + 1, y + 1, width - 2, dividerY - y);
        Rect portrait = header.hasPortrait()
                ? new Rect(x + padding, questionY, header.portraitSize(), header.portraitSize())
                : null;
        return new Layout(new Rect(x, y, width, height), headerRect, portrait,
                x + padding + header.portraitColumn(), questionY,
                visibleQuestionLines, dividerY, rows, footerY, previous, next);
    }
}
