package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Client-only focus, paging and one-shot lock state for a synchronized offer. */
public final class ClientChoiceState {

    public static final int MAX_VISIBLE_SHORTCUTS = DialogueChoiceLayout.MAX_VISIBLE_SHORTCUTS;
    /** @deprecated A page is now height-driven; this is only the maximum visible digit count. */
    @Deprecated(forRemoval = false)
    public static final int PAGE_SIZE = MAX_VISIBLE_SHORTCUTS;

    /**
     * An offer as the client holds it.
     *
     * <p>{@code villagerId} is the villager whose screen was open when the offer arrived, or null
     * when nothing on this client owns it — a chat offer, or a graphical one that reached a client
     * with no interaction screen. It is what lets the state left behind name the conversation it
     * belonged to, instead of being inherited by whoever is spoken to next.
     */
    public record ClientChoiceOffer(long revision, UUID villagerId, String questionId,
                                    List<String> answerIds,
                                    ConversationSession.Frontend frontend, long receivedClientTick) {
        public ClientChoiceOffer {
            answerIds = answerIds == null ? List.of() : List.copyOf(answerIds);
        }
    }

    /**
     * What is left on screen after an offer was cleared with an explanation: the sentence to read
     * and the one action that is safe to take. Never interactive as a choice — the answers it
     * replaced are gone, and nothing here re-sends them.
     *
     * <p>It carries the villager the cleared offer belonged to. An explanation outlives the answers
     * it replaced, so without that name the sentence outlives the conversation too and greets the
     * next villager the player speaks to.
     */
    public record Lapse(long revision, UUID villagerId, ChoiceClearS2C.Reason reason,
                        boolean backToTopics, ConversationSession.Frontend frontend) {
    }

    private ClientChoiceOffer offer;
    private Lapse lapse;
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
        lapse = null;
        offer = incoming.answerIds().isEmpty() ? null : incoming;
        focusedIndex = 0;
        page = 0;
        lockedIndex = -1;
        pages = fixedPages(incoming.answerIds().size());
        return true;
    }

    /** Applies only an equal/newer clear; delayed packets cannot erase a newer offer. */
    public boolean clear(long revision) {
        return clear(revision, ChoiceClearS2C.Reason.NONE);
    }

    /**
     * Applies only an equal/newer clear; a delayed rejection of an old decision cannot erase the
     * newer, unrelated one the player is looking at.
     *
     * <p>An explained reason leaves a {@link Lapse} behind. The answers really are gone — nothing
     * below keeps them selectable — but the explanation of why has to outlive them, or the card
     * vanishes mid-click and the player is told nothing at all.
     */
    public boolean clear(long revision, ChoiceClearS2C.Reason reason) {
        if (revision < highestRevision) {
            return false;
        }
        highestRevision = revision;
        ConversationSession.Frontend frontend = offer != null ? offer.frontend()
                : lapse != null ? lapse.frontend() : null;
        UUID villagerId = offer != null ? offer.villagerId()
                : lapse != null ? lapse.villagerId() : null;
        boolean changed = offer != null || lapse != null && lapse.reason() != reason;
        offer = null;
        focusedIndex = 0;
        page = 0;
        lockedIndex = -1;
        pages = List.of();
        lapse = reason != null && reason.explained()
                ? new Lapse(revision, villagerId, reason, offersReturnToTopics(reason), frontend) : null;
        return changed;
    }

    /**
     * Whether a lapse may offer to go back to the topic list rather than only to close.
     *
     * <p>Going back is a fresh request the server revalidates like any other, so it is safe exactly
     * when the reason says the conversation itself is still viable. A failed action is not: it stops
     * at navigation, because nothing here knows how much of it ran, and re-entering a menu whose
     * state is unknown would be a retry wearing a different label.
     */
    public static boolean offersReturnToTopics(ChoiceClearS2C.Reason reason) {
        return reason == ChoiceClearS2C.Reason.EXPIRED
                || reason == ChoiceClearS2C.Reason.CONTENT_RELOADED
                || reason == ChoiceClearS2C.Reason.REQUIREMENTS_CHANGED;
    }

    public Optional<Lapse> lapse() {
        return Optional.ofNullable(lapse);
    }

    /** A lapse retains its frontend after the offer and its exit animation are gone. */
    public boolean lapseFor(ConversationSession.Frontend frontend) {
        return lapse != null && lapse.frontend() == frontend;
    }

    /**
     * Whether a lapse is the given villager's to show.
     *
     * <p>The ownership rule the interaction screen asks before it draws or keys anything: the
     * explanation belongs to one conversation, so a lapse left by another villager — or one that
     * names no villager at all, which is a lapse no screen can claim — is not this screen's to
     * display.
     */
    public boolean lapseFor(ConversationSession.Frontend frontend, UUID villagerId) {
        return lapse != null && lapse.frontend() == frontend
                && lapse.villagerId() != null && lapse.villagerId().equals(villagerId);
    }

    /** Dismisses the explanation shell once the player has acted on it. */
    public boolean dismissLapse() {
        boolean had = lapse != null;
        lapse = null;
        return had;
    }

    /** A new server connection has its own revision sequence. UI closes keep the existing sequence. */
    public void resetConnection() {
        clearLocal();
        highestRevision = -1L;
    }

    public void clearLocal() {
        offer = null;
        lapse = null;
        focusedIndex = 0;
        page = 0;
        lockedIndex = -1;
        pages = List.of();
    }

    /**
     * Drops everything one frontend owns — offer and lapse alike — and leaves the other frontend's
     * state alone.
     *
     * <p>What a closing screen has to retire. Both halves, unconditionally: a lapse has no offer, so
     * a teardown that only looked for a live offer would leave the explanation behind.
     */
    public boolean clearLocal(ConversationSession.Frontend frontend) {
        boolean cleared = false;
        if (offer != null && offer.frontend() == frontend) {
            offer = null;
            focusedIndex = 0;
            page = 0;
            lockedIndex = -1;
            pages = List.of();
            cleared = true;
        }
        if (lapse != null && lapse.frontend() == frontend) {
            lapse = null;
            cleared = true;
        }
        return cleared;
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
