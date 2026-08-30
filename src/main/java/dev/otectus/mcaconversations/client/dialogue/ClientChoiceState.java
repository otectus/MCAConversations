package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;

import java.util.List;
import java.util.Optional;

/** Client-only focus, paging and one-shot lock state for a synchronized offer. */
public final class ClientChoiceState {

    public static final int PAGE_SIZE = 9;

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

    public boolean accept(ClientChoiceOffer incoming) {
        if (incoming == null || incoming.revision() <= highestRevision) {
            return false;
        }
        highestRevision = incoming.revision();
        offer = incoming.answerIds().isEmpty() ? null : incoming;
        focusedIndex = 0;
        page = 0;
        lockedIndex = -1;
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
        return changed;
    }

    public void clearLocal() {
        offer = null;
        focusedIndex = 0;
        page = 0;
        lockedIndex = -1;
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
        return offer == null ? 0 : Math.max(1, (offer.answerIds().size() + PAGE_SIZE - 1) / PAGE_SIZE);
    }

    public int firstOnPage() {
        return page * PAGE_SIZE;
    }

    public int visibleCount() {
        return offer == null ? 0 : Math.min(PAGE_SIZE, offer.answerIds().size() - firstOnPage());
    }

    public boolean focus(int absoluteIndex) {
        if (offer == null || locked() || absoluteIndex < firstOnPage()
                || absoluteIndex >= firstOnPage() + visibleCount()) {
            return false;
        }
        focusedIndex = absoluteIndex;
        return true;
    }

    public boolean moveFocus(int delta) {
        if (offer == null || locked()) {
            return false;
        }
        int first = firstOnPage();
        int last = first + visibleCount() - 1;
        focusedIndex = Math.max(first, Math.min(last, focusedIndex + delta));
        return true;
    }

    public boolean focusBoundary(boolean end) {
        if (offer == null || locked()) {
            return false;
        }
        focusedIndex = end ? firstOnPage() + visibleCount() - 1 : firstOnPage();
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
}
