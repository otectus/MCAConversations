package dev.otectus.mcaconversations.client.dialogue;

/**
 * Where the keyboard is and how far each reading region has been scrolled.
 *
 * <p>Separate from {@link ClientChoiceState} on purpose: that is the offer the server sent and the
 * answer the player will send back, this is only how the player is reading it. Nothing here ever
 * changes which answer is focused or which page is shown, so entering and leaving reading mode
 * cannot lose the player's place in the offer.
 *
 * <p>There is exactly one response scroll offset. A per-row offset was the previous design and it
 * meant a long answer could only be read while the pointer sat on it, and only that answer.
 */
public final class DialogueReadingState {

    /** The three things Tab moves between. */
    public enum Region { QUESTION, RESPONSES, FOOTER }

    private Region region = Region.RESPONSES;
    private int questionScroll;
    private int questionMaxScroll;
    private int responseScroll;
    private int responseMaxScroll;
    private int expandedIndex = -1;

    public Region region() {
        return region;
    }

    public boolean region(Region wanted) {
        if (wanted == null || wanted == region) {
            return false;
        }
        region = wanted;
        return true;
    }

    /** Tab and Shift-Tab. The order is the reading order of the card. */
    public boolean cycleRegion(boolean backwards, boolean footerReachable) {
        Region[] order = footerReachable
                ? new Region[]{Region.QUESTION, Region.RESPONSES, Region.FOOTER}
                : new Region[]{Region.QUESTION, Region.RESPONSES};
        int at = 0;
        for (int i = 0; i < order.length; i++) {
            if (order[i] == region) {
                at = i;
            }
        }
        int next = Math.floorMod(at + (backwards ? -1 : 1), order.length);
        return region(order[next]);
    }

    /** True while arrows and page keys are reading text rather than moving the selection. */
    public boolean readingText() {
        return region == Region.QUESTION || expandedIndex >= 0;
    }

    // --- question ---------------------------------------------------------------------------

    /** Re-clamps the question offset after a re-wrap; a shorter question cannot stay scrolled off. */
    public void questionBounds(int totalLines, int visibleLines) {
        questionMaxScroll = Math.max(0, totalLines - Math.max(1, visibleLines));
        questionScroll = Math.max(0, Math.min(questionMaxScroll, questionScroll));
    }

    public int questionScroll() {
        return questionScroll;
    }

    public int questionMaxScroll() {
        return questionMaxScroll;
    }

    public boolean questionOverflows() {
        return questionMaxScroll > 0;
    }

    public boolean scrollQuestion(int lines) {
        int next = Math.max(0, Math.min(questionMaxScroll, questionScroll + lines));
        if (next == questionScroll) {
            return false;
        }
        questionScroll = next;
        return true;
    }

    public boolean questionBoundary(boolean end) {
        return scrollQuestion(end ? questionMaxScroll : -questionMaxScroll);
    }

    // --- responses --------------------------------------------------------------------------

    public void responseBounds(int documentHeight, int viewportHeight) {
        responseMaxScroll = Math.max(0, documentHeight - Math.max(1, viewportHeight));
        responseScroll = Math.max(0, Math.min(responseMaxScroll, responseScroll));
    }

    public int responseScroll() {
        return responseScroll;
    }

    public int responseMaxScroll() {
        return responseMaxScroll;
    }

    public boolean responsesOverflow() {
        return responseMaxScroll > 0;
    }

    public boolean scrollResponses(int pixels) {
        int next = Math.max(0, Math.min(responseMaxScroll, responseScroll + pixels));
        if (next == responseScroll) {
            return false;
        }
        responseScroll = next;
        return true;
    }

    /**
     * Brings a row that is already placed on screen fully into the viewport. Used after a keyboard
     * focus move, so selecting with the keyboard can never leave the selected answer off-screen.
     */
    public boolean revealRow(int rowTop, int rowHeight, int viewportTop, int viewportHeight) {
        int delta = 0;
        if (rowTop < viewportTop) {
            delta = rowTop - viewportTop;
        } else if (rowTop + rowHeight > viewportTop + viewportHeight) {
            delta = Math.min(rowTop - viewportTop,
                    rowTop + rowHeight - viewportTop - viewportHeight);
        }
        return delta != 0 && scrollResponses(delta);
    }

    // --- expansion --------------------------------------------------------------------------

    public int expandedIndex() {
        return expandedIndex;
    }

    public boolean expanded(int absoluteIndex) {
        return expandedIndex >= 0 && expandedIndex == absoluteIndex;
    }

    public boolean expand(int absoluteIndex) {
        if (absoluteIndex < 0 || expandedIndex == absoluteIndex) {
            return false;
        }
        expandedIndex = absoluteIndex;
        region = Region.RESPONSES;
        return true;
    }

    public boolean collapse() {
        if (expandedIndex < 0) {
            return false;
        }
        expandedIndex = -1;
        return true;
    }

    public boolean toggleExpanded(int absoluteIndex) {
        return expanded(absoluteIndex) ? collapse() : expand(absoluteIndex);
    }

    /**
     * A new offer keeps the player's region -- a keyboard reader does not want to be put back on the
     * list every turn -- but nothing else survives: the scroll offsets and the expansion belong to
     * text that no longer exists.
     */
    public void offerChanged() {
        questionScroll = 0;
        questionMaxScroll = 0;
        responseScroll = 0;
        responseMaxScroll = 0;
        expandedIndex = -1;
    }

    public void reset() {
        offerChanged();
        region = Region.RESPONSES;
    }
}
