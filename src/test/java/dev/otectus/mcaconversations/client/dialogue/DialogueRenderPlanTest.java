package dev.otectus.mcaconversations.client.dialogue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Geometry assertions over the screen sizes and font heights a real client actually produces.
 *
 * <p>This is the CI half of checking the card. The in-game preview screen covers how it looks;
 * nothing but this covers whether it still fits, because {@code runClient} cannot load MCA and the
 * Townstead probe needs a hand-supplied jar, so neither runs here. A layout regression at GUI scale
 * 4 on a small window is invisible to everyone developing at scale 2 on a large one.
 *
 * <p>The viewport sizes below are {@code guiWidth}/{@code guiHeight} after scaling, which is what
 * the layout is handed: a 1920x1080 window at GUI scale 4 presents as 480x270.
 *
 * <p>Every case runs at both style gutter floors. A style that draws fewer graphics must not get
 * weaker layout coverage for it: MINIMAL wraps the same answers, packs the same pages and shows the
 * same nine shortcuts, and the floor is the only number it changes.
 */
class DialogueRenderPlanTest {

    /** Scaled viewports for common window sizes at GUI scale 1 through 4, plus vanilla's floor. */
    private static final int[][] VIEWPORTS = {
            {1280, 720}, {640, 360}, {426, 240}, {320, 180},
            {1920, 1080}, {960, 540}, {640, 360}, {480, 270},
            {2560, 1440}, {1280, 720}, {853, 480}, {640, 360},
            {320, 240},
    };

    /** Vanilla is 9; resource packs and Unicode fallbacks move it in both directions. */
    private static final int[] FONT_LINE_HEIGHTS = {7, 8, 9, 10, 12, 16};

    private static List<Integer> rowHeights(int count, int lines, int fontLineHeight, boolean compact) {
        List<Integer> heights = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            heights.add(DialogueChoiceLayout.rowHeight(lines, fontLineHeight, compact));
        }
        return heights;
    }

    /** The two styles that draw a card of their own. MCA_ORIGINAL has no geometry to check. */
    private static final DialogueStyleProfile[] PROFILES = {
            DialogueStyleProfile.RESPONSIVE,
            DialogueStyleProfile.MINIMAL,
    };

    private record Case(int width, int height, int fontLineHeight, int questionLines,
                        int answers, int answerLines, boolean footer, DialogueStyleProfile profile) {
        @Override
        public String toString() {
            return width + "x" + height + " font=" + fontLineHeight + " question=" + questionLines
                    + " answers=" + answers + "x" + answerLines + " footer=" + footer
                    + " floor=" + profile.numberColumnFloor()
                    + " texturedBadges=" + profile.texturedBadges();
        }
    }

    private static List<Case> cases() {
        List<Case> cases = new ArrayList<>();
        for (int[] viewport : VIEWPORTS) {
            for (int fontLineHeight : FONT_LINE_HEIGHTS) {
                for (int questionLines : new int[]{1, 3, 8}) {
                    for (int answers : new int[]{1, 3, 9, 18}) {
                        for (int answerLines : new int[]{1, 2, 5}) {
                            for (boolean footer : new boolean[]{true, false}) {
                                for (DialogueStyleProfile profile : PROFILES) {
                                    cases.add(new Case(viewport[0], viewport[1], fontLineHeight,
                                            questionLines, answers, answerLines, footer, profile));
                                }
                            }
                        }
                    }
                }
            }
        }
        return cases;
    }

    /** Builds the first page's layout exactly the way the presentation builder does. */
    private static DialogueChoiceLayout.Layout firstPage(Case c) {
        List<Integer> normal = rowHeights(c.answers(), c.answerLines(), c.fontLineHeight(), false);
        List<Integer> compact = rowHeights(c.answers(), c.answerLines(), c.fontLineHeight(), true);
        DialogueChoiceLayout.PageMap pages = DialogueChoiceLayout.packPages(
                c.height(), c.questionLines(), c.fontLineHeight(), normal, compact, c.footer());
        assertFalse(pages.pages().isEmpty(), "no pages for " + c);
        DialogueChoiceLayout.ChoicePage page = pages.pages().get(0);
        List<Integer> source = pages.compact() ? compact : normal;
        return DialogueChoiceLayout.create(c.width(), c.height(), c.questionLines(),
                c.fontLineHeight(), source.subList(page.firstInclusive(), page.lastExclusive()),
                c.footer(), pages.compact(), false, pages.pages().size() > 1);
    }

    @Test
    void theCardNeverLeavesItsSafeVerticalRegion() {
        List<String> problems = new ArrayList<>();
        for (Case c : cases()) {
            DialogueChoiceLayout.Rect panel = firstPage(c).panel();
            int bottomLimit = DialogueChoiceLayout.SAFE_TOP
                    + DialogueChoiceLayout.maxPanelHeight(c.height());
            if (panel.y() < DialogueChoiceLayout.SAFE_TOP) {
                problems.add(c + ": panel top " + panel.y() + " above the safe area");
            }
            if (panel.y() + panel.height() > bottomLimit) {
                problems.add(c + ": panel bottom " + (panel.y() + panel.height())
                        + " past the safe limit " + bottomLimit);
            }
            if (panel.y() + panel.height() > c.height()) {
                problems.add(c + ": panel runs off the bottom of the screen");
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void theCardNeverLeavesItsHorizontalMargins() {
        List<String> problems = new ArrayList<>();
        for (Case c : cases()) {
            DialogueChoiceLayout.Rect panel = firstPage(c).panel();
            if (panel.x() < 0 || panel.x() + panel.width() > c.width()) {
                problems.add(c + ": panel spans " + panel.x() + ".." + (panel.x() + panel.width())
                        + " outside a " + c.width() + "-wide viewport");
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void rowsStayInsideThePanelAndNeverOverlap() {
        List<String> problems = new ArrayList<>();
        for (Case c : cases()) {
            DialogueChoiceLayout.Layout layout = firstPage(c);
            DialogueChoiceLayout.Rect panel = layout.panel();
            DialogueChoiceLayout.Rect previous = null;
            for (DialogueChoiceLayout.Rect row : layout.rows()) {
                if (row.x() < panel.x() || row.x() + row.width() > panel.x() + panel.width()) {
                    problems.add(c + ": a row is wider than its panel");
                }
                if (row.y() < panel.y() || row.y() + row.height() > panel.y() + panel.height()) {
                    problems.add(c + ": a row escapes the panel vertically");
                }
                if (previous != null
                        && row.y() < previous.y() + previous.height() + DialogueChoiceLayout.ROW_GAP) {
                    problems.add(c + ": rows overlap");
                }
                previous = row;
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void theNumberBadgeNeverOverlapsTheAnswerColumn() {
        List<String> problems = new ArrayList<>();
        for (Case c : cases()) {
            for (DialogueChoiceLayout.Rect row : firstPage(c).rows()) {
                // A wide font is the case that matters: the gutter has to grow with it.
                int numeralWidth = c.fontLineHeight() * 2;
                int column = DialogueChoiceLayout.numberColumn(numeralWidth, c.profile());
                DialogueChoiceLayout.Rect badge = DialogueChoiceLayout.badgeRect(
                        row, c.fontLineHeight(), numeralWidth, c.profile());
                if (badge.x() + badge.width() > row.x() + column) {
                    problems.add(c + ": badge spills into the answer text column");
                }
                if (badge.y() < row.y() || badge.y() + badge.height() > row.y() + row.height()) {
                    problems.add(c + ": badge escapes its row at font height " + c.fontLineHeight());
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void pageControlsStayInsideThePanelAndRemainClickable() {
        List<String> problems = new ArrayList<>();
        for (Case c : cases()) {
            DialogueChoiceLayout.Layout layout = firstPage(c);
            DialogueChoiceLayout.Rect panel = layout.panel();
            for (DialogueChoiceLayout.Rect control :
                    new DialogueChoiceLayout.Rect[]{layout.previousPage(), layout.nextPage()}) {
                if (control == null) {
                    continue;
                }
                if (Math.min(control.width(), control.height()) < 18) {
                    problems.add(c + ": a page control is smaller than 18 pixels");
                }
                if (control.x() < panel.x()
                        || control.x() + control.width() > panel.x() + panel.width()
                        || control.y() < panel.y()
                        || control.y() + control.height() > panel.y() + panel.height()) {
                    problems.add(c + ": a page control escapes the panel");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void bothPageControlsShareTheSameFooterRowAndDoNotOverlap() {
        DialogueChoiceLayout.Layout layout = DialogueChoiceLayout.create(
                640, 360, 2, 9, rowHeights(4, 1, 9, false), true, false, true, true);
        DialogueChoiceLayout.Rect previous = layout.previousPage();
        DialogueChoiceLayout.Rect next = layout.nextPage();
        assertNotNull(previous);
        assertNotNull(next);
        assertEquals(previous.y(), next.y(), "the paging controls must sit on one row");
        assertTrue(previous.x() + previous.width() <= next.x(), "the paging controls must not overlap");
    }

    @Test
    void aFooterlessCardStillFitsAndReportsNoFooter() {
        List<String> problems = new ArrayList<>();
        for (Case c : cases()) {
            if (c.footer()) {
                continue;
            }
            DialogueChoiceLayout.Layout layout = firstPage(c);
            if (layout.footerY() >= 0) {
                problems.add(c + ": reserved a footer that was not requested");
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void numberColumnNeverNarrowerThanTheNumeral() {
        // A floor sets how narrow a style may be, not how narrow the digit may be drawn. If a floor
        // could win over the numeral, a Unicode font would put the answer's first word on top of it.
        List<String> problems = new ArrayList<>();
        for (int fontLineHeight : FONT_LINE_HEIGHTS) {
            for (int numeralWidth : new int[]{1, 4, 8, 12, 20, 32}) {
                for (DialogueStyleProfile profile : PROFILES) {
                    int floor = profile.numberColumnFloor();
                    int column = DialogueChoiceLayout.numberColumn(numeralWidth, profile);
                    if (column < numeralWidth) {
                        problems.add("floor " + floor + " numeral " + numeralWidth
                                + ": gutter " + column + " is narrower than the numeral");
                    }
                    if (column < floor) {
                        problems.add("floor " + floor + " numeral " + numeralWidth
                                + ": gutter " + column + " is below the style floor");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void textColumnsStayPositiveOnTheNarrowestSupportedViewport() {
        for (int[] viewport : VIEWPORTS) {
            int width = viewport[0];
            assertTrue(DialogueChoiceLayout.answerTextWidth(width) >= 40,
                    width + " leaves no room for answer text");
            assertTrue(DialogueChoiceLayout.questionTextWidth(width)
                            > DialogueChoiceLayout.answerTextWidth(width),
                    width + " must give the question more room than an indented answer");
        }
    }
}
