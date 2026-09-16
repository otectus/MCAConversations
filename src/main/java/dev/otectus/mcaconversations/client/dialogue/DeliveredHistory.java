package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.network.ChoiceClearS2C;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * What this client has received and sent, bounded, in order, and in memory only.
 *
 * <p>Three properties are the whole point. It is <b>bounded</b>, so a long session cannot grow a
 * transcript; it is <b>connection-local</b>, so leaving a world leaves the history behind; and it is
 * keyed by <b>delivery identity</b>, so one utterance that reaches the card and the chat log is one
 * entry rather than two. Nothing here is written to disk and nothing here can be exported.
 *
 * <p>Only this client's own deliveries ever reach it: the recording points are the places where the
 * client itself received a line or sent a reply, so a line the server dropped, queued, or addressed
 * to somebody else is absent by construction rather than by a filter that could be got wrong.
 */
public final class DeliveredHistory {

    /** Entries kept before the oldest is forgotten. */
    public static final int DEFAULT_CAP = 64;

    private final int cap;
    private final Map<String, DeliveredLine> entries = new LinkedHashMap<>();

    public DeliveredHistory() {
        this(DEFAULT_CAP);
    }

    public DeliveredHistory(int cap) {
        this.cap = Math.max(0, cap);
    }

    public int cap() {
        return cap;
    }

    public boolean enabled() {
        return cap > 0;
    }

    /**
     * Records one delivery. A repeat of an identity already held is ignored rather than appended:
     * the same utterance drawn twice is still one thing that was said.
     */
    public boolean record(DeliveredLine line) {
        if (cap == 0 || line == null || line.identity().isEmpty() || entries.containsKey(line.identity())) {
            return false;
        }
        entries.put(line.identity(), line);
        evict();
        return true;
    }

    /** Applies the outcome the server reported for one submitted reply. */
    public boolean resolve(String identity, DeliveredLine.Status outcome, ChoiceClearS2C.Reason reason) {
        DeliveredLine held = identity == null ? null : entries.get(identity);
        if (held == null) {
            return false;
        }
        DeliveredLine updated = held.resolved(outcome, reason);
        if (updated == held) {
            return false;
        }
        entries.put(identity, updated);
        return true;
    }

    /**
     * Applies an outcome to every still-submitted reply whose identity begins with {@code prefix}.
     *
     * <p>A clear names a revision, not the index that was sent, so the reply it answers is found by
     * the revision it belonged to.
     */
    public boolean resolveAll(String prefix, DeliveredLine.Status outcome, ChoiceClearS2C.Reason reason) {
        if (prefix == null || prefix.isEmpty()) {
            return false;
        }
        boolean changed = false;
        for (Map.Entry<String, DeliveredLine> entry : entries.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                DeliveredLine updated = entry.getValue().resolved(outcome, reason);
                if (updated != entry.getValue()) {
                    entry.setValue(updated);
                    changed = true;
                }
            }
        }
        return changed;
    }

    /** Oldest first, which is the order a conversation happened in. */
    public List<DeliveredLine> entries() {
        return List.copyOf(new ArrayList<>(entries.values()));
    }

    public int size() {
        return entries.size();
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public void clear() {
        entries.clear();
    }

    private void evict() {
        Iterator<String> oldest = entries.keySet().iterator();
        while (entries.size() > cap && oldest.hasNext()) {
            oldest.next();
            oldest.remove();
        }
    }
}
