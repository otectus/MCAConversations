package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.conversation.UtteranceTag;
import net.minecraft.network.chat.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * The utterances this client has been delivered, and which surfaces have shown them (feedback
 * remediation §2.1). Client-only by placement; the logic is plain so a unit test can drive it.
 *
 * <p>Keyed by utterance identity, never by text: two deliberately identical sentences are two
 * entries, and one line that reaches the card, the chat log and speech is one entry with three
 * sightings. It holds the component the screen actually drew, so a later reading resolves the same
 * sentence rather than drawing a pooled line again, and the plain text speech would read, derived once.
 *
 * <p>Bounded (the last {@link #CAPACITY}) and connection-local: {@code ClientChoiceMessages} clears it
 * with the rest of the connection state, so nothing outlives the server it came from.
 */
public final class ClientUtteranceLedger {

    /** Utterances remembered per connection. */
    public static final int CAPACITY = 16;

    /** Where an utterance was seen. */
    public enum Surface { SCREEN, CHAT, SPEECH }

    /** One remembered utterance. Mutable only in its sightings. */
    public static final class Entry {
        private final UtteranceTag.Id id;
        private final Component display;
        private final String speechText;
        private final Set<Surface> seenBy = EnumSet.noneOf(Surface.class);
        private final long firstSeenTick;

        private Entry(UtteranceTag.Id id, Component display, String speechText, long firstSeenTick) {
            this.id = id;
            this.display = display;
            this.speechText = speechText == null ? "" : speechText;
            this.firstSeenTick = firstSeenTick;
        }

        public UtteranceTag.Id id() {
            return id;
        }

        /** The component the first surface was handed: what the card drew. */
        public Component display() {
            return display;
        }

        /** The plain sentence, emotion markup stripped. */
        public String speechText() {
            return speechText;
        }

        public Set<Surface> seenBy() {
            return Collections.unmodifiableSet(seenBy);
        }

        public boolean seenBy(Surface surface) {
            return seenBy.contains(surface);
        }

        public long firstSeenTick() {
            return firstSeenTick;
        }
    }

    private static final ClientUtteranceLedger INSTANCE = new ClientUtteranceLedger(
            ClientUtteranceLedger::plainText);

    private final Deque<Entry> entries = new ArrayDeque<>();
    private final Function<Component, String> speechText;
    private UtteranceAgreement.Displayed lastDisplayed;

    /** Test seam: {@code speechText} turns a component into the sentence speech would read. */
    ClientUtteranceLedger(Function<Component, String> speechText) {
        this.speechText = speechText;
    }

    /** The ledger for this client's connection. */
    public static ClientUtteranceLedger get() {
        return INSTANCE;
    }

    /**
     * Records that {@code surface} was handed this utterance, creating the entry on first sight.
     *
     * @return the entry, now carrying the sighting
     */
    public synchronized Entry record(UtteranceTag.Id id, Component display, Surface surface, long tick) {
        Entry entry = find(id);
        if (entry == null) {
            entry = new Entry(id, display, safeText(display), tick);
            entries.addLast(entry);
            while (entries.size() > CAPACITY) {
                entries.removeFirst();
            }
        }
        entry.seenBy.add(surface);
        if (surface == Surface.SCREEN) {
            lastDisplayed = new UtteranceAgreement.Displayed(id.identity(), id.seq(), true);
        }
        return entry;
    }

    /** A new interaction screen: what the previous one drew says nothing about this one. */
    public synchronized void screenOpened() {
        lastDisplayed = null;
    }

    /** The screen drew a line that carries no identity: from now on, nothing to compare against. */
    public synchronized void displayedUntagged() {
        lastDisplayed = UtteranceAgreement.Displayed.untagged();
    }

    /** The line the screen drew last, or empty when it has drawn nothing on this connection. */
    public synchronized Optional<UtteranceAgreement.Displayed> lastDisplayed() {
        return Optional.ofNullable(lastDisplayed);
    }

    /**
     * Claims the one reading this utterance gets. True the first time, false after: that is the
     * exactly-once the optional chat speech relies on, keyed by identity and never by text.
     */
    public synchronized boolean claimSpeech(UtteranceTag.Id id, Component display, long tick) {
        Entry entry = find(id);
        if (entry != null && entry.seenBy(Surface.SPEECH)) {
            return false;
        }
        record(id, display, Surface.SPEECH, tick);
        return true;
    }

    /** The entry for this utterance, or empty. */
    public synchronized Optional<Entry> entry(UtteranceTag.Id id) {
        return Optional.ofNullable(find(id));
    }

    /** The most recent {@code max} entries, newest first. */
    public synchronized List<Entry> recent(int max) {
        List<Entry> out = new ArrayList<>(Math.min(max, entries.size()));
        Iterator<Entry> newestFirst = entries.descendingIterator();
        while (newestFirst.hasNext() && out.size() < max) {
            out.add(newestFirst.next());
        }
        return out;
    }

    public synchronized int size() {
        return entries.size();
    }

    /** Forgets everything: a new connection, a new world. */
    public synchronized void clear() {
        entries.clear();
        lastDisplayed = null;
    }

    private Entry find(UtteranceTag.Id id) {
        if (id == null) {
            return null;
        }
        for (Entry entry : entries) {
            if (entry.id.sameUtterance(id)) {
                return entry;
            }
        }
        return null;
    }

    private String safeText(Component display) {
        try {
            return display == null ? "" : speechText.apply(display);
        } catch (Throwable t) {
            return "";
        }
    }

    /** The sentence as speech would read it: the drawn text with emotion markup stripped. */
    static String plainText(Component display) {
        return dev.otectus.mcaconversations.client.townstead.ConversationsEmotionTags
                .strip(display.getString());
    }
}
