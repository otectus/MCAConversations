package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The owned-question lookup boundary, as a decision rather than as an injection.
 *
 * <p>{@code DialoguesMixin} does nothing but apply {@link ContentReloadCoordinator#lookup} to
 * {@code getQuestion}. That is on purpose: the injection itself cannot be driven in the unit JVM
 * (boundary note §5), so the rule it applies is kept somewhere that can be. What the injection adds
 * on top — that the method exists, is public, and returns what was put in the map — is pinned against
 * the real jars by {@code McaDialogueReloadProbeTest}.
 */
class OwnedDialogueRetentionTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    private static ConversationContentBundle bundleHolding(Map<String, Object> owned) {
        return ConversationContentBundle.UNAVAILABLE
                .published(7L, 3L, owned, true, java.util.List.of());
    }

    @Test
    @DisplayName("an empty replacement instance still resolves the old owned questions")
    void anEmptyReplacementStillResolvesOldQuestions() {
        Object hub = new Object();
        ContentReloadCoordinator.setCommittedForTesting(bundleHolding(Map.of("conversations", hub)));

        // MCA's map has been cleared in place and not refilled yet — the §3.4 window. The boundary
        // answers from the bundle, so a click landing in it still sees the hub it was offered.
        assertTrue(ContentReloadCoordinator.lookup("conversations").intercepted());
        assertSame(hub, ContentReloadCoordinator.lookup("conversations").question());
    }

    @Test
    @DisplayName("a retained automatic next hop resolves to the same bundle's question, not the new map's")
    void retainedNextChainsStayPinned() {
        Object hub = new Object();
        Object followUp = new Object();
        ContentReloadCoordinator.setCommittedForTesting(bundleHolding(new LinkedHashMap<>(Map.of(
                "conversations", hub,
                "conversations.cat.chitchat", followUp))));

        // MCA's `next` action resolves through getQuestion at trigger time, which is exactly the
        // method this boundary owns; so an old question's hop lands in the old bundle.
        assertSame(followUp, ContentReloadCoordinator.lookup("conversations.cat.chitchat").question());
    }

    @Test
    @DisplayName("a name the committed bundle does not declare is absent, not passed through")
    void absenceIsAuthoritative() {
        ContentReloadCoordinator.setCommittedForTesting(bundleHolding(Map.of("conversations", new Object())));

        ContentReloadCoordinator.OwnedLookup lookup = ContentReloadCoordinator.lookup("conversations.brand.new");

        assertTrue(lookup.intercepted(),
                "a rejected addition must not become reachable merely because MCA parsed it");
        assertNull(lookup.question());
    }

    @Test
    @DisplayName("external names pass through, and stay outside the guarantee")
    void externalNamesPassThrough() {
        ContentReloadCoordinator.setCommittedForTesting(bundleHolding(Map.of("conversations", new Object())));

        assertFalse(ContentReloadCoordinator.lookup("main").intercepted());
        assertFalse(ContentReloadCoordinator.lookup("chat.topic").intercepted());
        assertFalse(ContentReloadCoordinator.lookup(null).intercepted());
    }

    @Test
    @DisplayName("before any commit the boundary is inert and MCA behaves exactly as it did")
    void theBoundaryIsInertBeforeTheFirstCommit() {
        ContentReloadCoordinator.setCommittedForTesting(ConversationContentBundle.UNAVAILABLE);

        assertFalse(ContentReloadCoordinator.lookup("conversations").intercepted(),
                "answering 'absent' for a table this mod has never seen would break a starting server");
        assertFalse(ContentReloadCoordinator.governsOwnedLookups());
    }

    @Test
    @DisplayName("an operation's lookups come from the bundle it pinned, not from a commit that landed since")
    void anOperationResolvesAgainstItsPinnedBundle() {
        Object oldHub = new Object();
        Object newHub = new Object();
        ContentReloadCoordinator.setCommittedForTesting(bundleHolding(Map.of("conversations", oldHub)));

        try (ContentOperation ignored = ContentOperation.open()) {
            ContentReloadCoordinator.setCommittedForTesting(bundleHolding(Map.of("conversations", newHub)));

            assertSame(oldHub, ContentReloadCoordinator.lookup("conversations").question(),
                    "the exchange that is mid-flight finishes against the content it started on");
        }

        assertSame(newHub, ContentReloadCoordinator.lookup("conversations").question(),
                "and the next one starts on the new content");
    }
}
