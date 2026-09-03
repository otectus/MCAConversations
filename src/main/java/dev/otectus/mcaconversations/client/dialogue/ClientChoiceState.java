package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;

import java.util.List;
import java.util.Optional;

/** Client-only focus, paging and one-shot lock state for a synchronized offer. */
public final class ClientChoiceState {

    public static final int MAX_VISIBLE_SHORTCUTS = DialogueChoiceLayout.MAX_VISIBLE_SHORTCUTS;
    /** @deprecated A page is now height-driven; this is only the maximum visible digit count. */
    @Deprecated(forRemoval = false)
    public static final int PAGE_SIZE = MAX_VISIBLE_SHORTCUTS;

    public record ClientChoiceOffer(long revision, String questionId, List<String> answerIds,
                                    ConversationSession.Frontend frontend, long receivedClientTick) {
        public ClientChoiceOffer {
            answerIds = answerIds == null ? List.of() : List.copyOf(answerIds);
        }
    }

    private ClientChoiceOffer offer;
    private long highestRevision = -1L;
    private int focusedIndex;
    private int page;
    private int lockedIndex = -1;
    private List<DialogueChoiceLayout.ChoicePage> pages = List.of();

    public boolean accept(ClientChoiceOffer incoming) {
        if (incoming == null || incoming.revision() <= highestRevision) {
            return false;
        }
        highestRevision = incoming.revision();
        offer = incoming.answerIds().isEmpty() ? null : incoming;
        focusedIndex = 0;
        page = 0;
        lockedIndex = -1;
        pages = fixedPages(incoming.answerIds().size());
        return true;
    }

    /** Applies only an equal/newer clear; delayed packets cannot erase a newer offer. */
    public boolean clear(long revision) {
        if (revision < highestRevision) {
            return false;
        }
        highestRevision = revision;
        boolean changed = offer != null;
        offer = null;
        focusedIndex = 0;
        page = 0;
        lockedIndex = -1;
        pages = List.of();
        return changed;
    }

    public void clearLocal() {
        offer = null;
        focusedIndex = 0;
        page = 0;
        lockedIndex = -1;
        pages = List.of();
    }

    /** Highest revision seen, so a caller can synthesise an offer that will not be rejected. */
    public long highestRevision() {
        return highestRevision;
    }

    public Optional<ClientChoiceOffer> offer() {
        return Optional.ofNullable(offer);
    }

    public boolean activeFor(ConversationSession.Frontend frontend) {
        return offer != null && offer.frontend() == frontend;
    }

    public int focusedIndex() {
        return focusedIndex;
    }

    public int lockedIndex() {
        return lockedIndex;
    }

    public boolean locked() {
        return lockedIndex >= 0;
    }

    public int page() {
        return page;
    }

    public int pageCount() {
        return offer == null ? 0 : pages.size();
    }

    public int firstOnPage() {
        return currentPage().map(DialogueChoiceLayout.ChoicePage::firstInclusive).orElse(0);
    }

    public int visibleCount() {
        return currentPage().map(DialogueChoiceLayout.ChoicePage::size).orElse(0);
    }

    public List<DialogueChoiceLayout.ChoicePage> pages() {
        return pages;
    }

    /** Installs a font/height-aware page map while keeping the absolute focus visible. */
    public boolean updatePages(List<DialogueChoiceLayout.ChoicePage> incoming) {
        if (offer == null || !validPages(incoming, offer.answerIds().size())) {
            return false;
        }
        List<DialogueChoiceLayout.ChoicePage> copy = List.copyOf(incoming);
        if (copy.equals(pages)) {
            return false;
        }
        pages = copy;
        page = pageContaining(focusedIndex);
        return true;
    }

    public boolean focus(int absoluteIndex) {
        if (offer == null || locked() || absoluteIndex < firstOnPage()
                || absoluteIndex >= firstOnPage() + visibleCount()) {
            return false;
        }
        if (focusedIndex == absoluteIndex) {
            return false;
        }
        focusedIndex = absoluteIndex;
        return true;
    }

    public boolean moveFocus(int delta) {
        if (offer == null || locked()) {
            return false;
        }
        int next = Math.max(0, Math.min(offer.answerIds().size() - 1, focusedIndex + delta));
        if (next == focusedIndex) {
            return false;
        }
        focusedIndex = next;
        page = pageContaining(next);
        return true;
    }

    public boolean focusBoundary(boolean end) {
        if (offer == null || locked()) {
            return false;
        }
        int next = end ? firstOnPage() + visibleCount() - 1 : firstOnPage();
        if (focusedIndex == next) {
            return false;
        }
        focusedIndex = next;
        return true;
    }

    public boolean changePage(int delta) {
        if (offer == null || locked() || pageCount() <= 1) {
            return false;
        }
        int next = Math.max(0, Math.min(pageCount() - 1, page + delta));
        if (next == page) {
            return false;
        }
        page = next;
        focusedIndex = firstOnPage();
        return true;
    }

    public boolean lock(int absoluteIndex) {
        if (offer == null || locked() || absoluteIndex < 0 || absoluteIndex >= offer.answerIds().size()) {
            return false;
        }
        focusedIndex = absoluteIndex;
        lockedIndex = absoluteIndex;
        return true;
    }

    private Optional<DialogueChoiceLayout.ChoicePage> currentPage() {
        if (page < 0 || page >= pages.size()) {
            return Optional.empty();
        }
        return Optional.of(pages.get(page));
    }

    private int pageContaining(int absoluteIndex) {
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).contains(absoluteIndex)) {
                return i;
            }
        }
        return 0;
    }

    private static List<DialogueChoiceLayout.ChoicePage> fixedPages(int answerCount) {
        if (answerCount <= 0) {
            return List.of();
        }
        java.util.ArrayList<DialogueChoiceLayout.ChoicePage> result = new java.util.ArrayList<>();
        for (int first = 0; first < answerCount; first += MAX_VISIBLE_SHORTCUTS) {
            result.add(new DialogueChoiceLayout.ChoicePage(first,
                    Math.min(answerCount, first + MAX_VISIBLE_SHORTCUTS)));
        }
        return List.copyOf(result);
    }

    private static boolean validPages(List<DialogueChoiceLayout.ChoicePage> incoming, int answerCount) {
        if (incoming == null || incoming.isEmpty()) {
            return false;
        }
        int expected = 0;
        for (DialogueChoiceLayout.ChoicePage choicePage : incoming) {
            if (choicePage.firstInclusive() != expected
                    || choicePage.size() > MAX_VISIBLE_SHORTCUTS) {
                return false;
            }
            expected = choicePage.lastExclusive();
        }
        return expected == answerCount;
    }
}
