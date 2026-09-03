package dev.otectus.mcaconversations.context;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Everything the conversation layer knows about one exchange, captured once (spec §7).
 *
 * <p>This type exists to kill a specific class of bug. Before it, each dialogue condition queried MCA
 * for itself, so within a single player click one condition could see the villager at their workplace
 * and the next could see them halfway home — and a scene could be selected on facts that were no
 * longer true by the time its line was written. Here the world is read once, frozen, and handed to
 * every selector, condition, template and trace.
 *
 * <p><b>Immutable in its values.</b> {@link #refreshed} produces a <em>new</em> snapshot carrying the
 * updated volatile fields and the identical pinned ones; nothing mutates a snapshot in place.
 *
 * <p><b>Mutable only in its read log.</b> {@link #consulted()} records which fields were actually
 * looked at, because the trace must show the fields a decision consulted rather than dumping every
 * world value the providers happened to collect (spec §7.4). That log is observation, not state — it
 * never affects a value, a fingerprint or a decision.
 */
public final class ConversationContextSnapshot {

    public static final ConversationContextSnapshot EMPTY = new ConversationContextSnapshot(
            Map.of(), ContextCapabilities.EMPTY, 0L, 0L);

    private final Map<ContextKey<?>, ContextValue<?>> values;
    private final ContextCapabilities capabilities;
    private final long capturedGameTime;
    private final long capturedDay;
    private final ContextFingerprint fingerprint;
    private final Set<String> consulted = Collections.synchronizedSet(new LinkedHashSet<>());

    ConversationContextSnapshot(Map<ContextKey<?>, ContextValue<?>> values,
                                ContextCapabilities capabilities,
                                long capturedGameTime,
                                long capturedDay) {
        this.values = Collections.unmodifiableMap(new LinkedHashMap<>(values));
        this.capabilities = capabilities == null ? ContextCapabilities.EMPTY : capabilities;
        this.capturedGameTime = capturedGameTime;
        this.capturedDay = capturedDay;
        this.fingerprint = ContextFingerprint.of(this.values);
    }

    /**
     * Reads a field, recording the read.
     *
     * <p>A key no provider wrote reads as {@link ContextStatus#UNAVAILABLE}, never as absent — the
     * caller must still choose an unknown policy for it rather than falling through to {@code false}.
     */
    public <T> ContextValue<T> get(ContextKey<T> key) {
        if (key == null) {
            return ContextValue.unavailable();
        }
        consulted.add(key.id());
        ContextValue<?> raw = values.get(key);
        return raw == null ? ContextValue.unavailable() : key.cast(raw);
    }

    /** Convenience read for the common case of a present, known value. */
    public <T> Optional<T> value(ContextKey<T> key) {
        return get(key).opt();
    }

    /** True when the field is known and equal to {@code expected}, ignoring case for strings. */
    public boolean is(ContextKey<String> key, String expected) {
        return get(key).opt()
                .map(actual -> actual.equalsIgnoreCase(expected))
                .orElse(false);
    }

    /** Reads a field <b>without</b> recording it — for reports that enumerate everything. */
    public <T> ContextValue<T> peek(ContextKey<T> key) {
        ContextValue<?> raw = key == null ? null : values.get(key);
        return raw == null ? ContextValue.unavailable() : key.cast(raw);
    }

    public ContextCapabilities capabilities() {
        return capabilities;
    }

    public ContextFingerprint fingerprint() {
        return fingerprint;
    }

    /** Game time when this snapshot was captured; the scene's clock for the rest of the exchange. */
    public long capturedGameTime() {
        return capturedGameTime;
    }

    /** Game day when this snapshot was captured. */
    public long capturedDay() {
        return capturedDay;
    }

    /** Every field present, whatever its status. Ordered by declaration, so reports are diffable. */
    public Map<ContextKey<?>, ContextValue<?>> all() {
        return values;
    }

    /** Field ids a consumer has actually read since capture, in first-read order. */
    public Set<String> consulted() {
        synchronized (consulted) {
            return Set.copyOf(consulted);
        }
    }

    /**
     * A new snapshot with the volatile fields of {@code fresh} and the pinned fields of this one
     * (spec §7.4).
     *
     * <p>Pinned fields deliberately win even when the fresher capture disagrees: once a scene has
     * bound a named person, a village or an episode, changing that binding underneath the player is
     * exactly the referent drift the whole design is built to prevent. A pinned fact that has become
     * false ends the scene through its declared fallback; it never silently becomes a different fact.
     */
    public ConversationContextSnapshot refreshed(ConversationContextSnapshot fresh) {
        if (fresh == null) {
            return this;
        }
        Map<ContextKey<?>, ContextValue<?>> merged = new LinkedHashMap<>(values);
        fresh.values.forEach((key, value) -> {
            if (key.isVolatile()) {
                merged.put(key, value);
            }
        });
        return new ConversationContextSnapshot(merged, fresh.capabilities,
                fresh.capturedGameTime, fresh.capturedDay);
    }

    @Override
    public String toString() {
        return "ContextSnapshot[fields=" + values.size() + " fp=" + fingerprint.hex()
                + " day=" + capturedDay + "]";
    }
}
