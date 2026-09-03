package dev.otectus.mcaconversations.context;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * How a {@link ConversationContextSource} writes its fields (spec §7.2).
 *
 * <p>The builder enforces the one structural rule that makes a snapshot trustworthy: <b>one field,
 * one owner</b>. A second provider writing a key another already wrote is rejected rather than
 * silently overwriting, because "whichever source ran last wins" is not an answer anybody can author
 * against or explain in a trace.
 *
 * <p>It also gives providers a vocabulary for not knowing. {@link #unknown} and {@link #unavailable}
 * are ordinary calls, so a source that cannot answer says so explicitly instead of leaving a hole
 * that later reads as {@code false}.
 */
public final class ContextSnapshotBuilder {

    private final Map<ContextKey<?>, ContextValue<?>> values = new LinkedHashMap<>();
    private final Map<ContextKey<?>, String> owners = new LinkedHashMap<>();
    private final ContextCapabilities.Builder capabilities = ContextCapabilities.builder();

    private String currentSource = "";
    private long capturedGameTime;
    private long capturedDay;

    /** Called by the runner before each source contributes, so conflicts can name both writers. */
    void beginSource(String sourceId) {
        this.currentSource = sourceId == null ? "" : sourceId;
    }

    void reportCapability(ContextCapabilities.Status status, String note) {
        capabilities.report(currentSource, status, note);
    }

    public ContextSnapshotBuilder clock(long gameTime, long day) {
        this.capturedGameTime = gameTime;
        this.capturedDay = day;
        return this;
    }

    /** Writes a known value. A null value is stored as {@link ContextStatus#UNKNOWN}. */
    public <T> ContextSnapshotBuilder put(ContextKey<T> key, T value) {
        return write(key, ContextValue.known(value));
    }

    /** Writes an {@link Optional} answer from a source that ran: empty means unknown, not absent. */
    public <T> ContextSnapshotBuilder put(ContextKey<T> key, Optional<T> value) {
        return write(key, ContextValue.of(value));
    }

    /** The source ran and genuinely does not know. */
    public <T> ContextSnapshotBuilder unknown(ContextKey<T> key) {
        return write(key, ContextValue.unknown());
    }

    /** Nothing was in a position to answer: absent mod, missed compat handle, disabled feature. */
    public <T> ContextSnapshotBuilder unavailable(ContextKey<T> key) {
        return write(key, ContextValue.unavailable());
    }

    /**
     * Marks every field a source declares as unavailable in one call.
     *
     * <p>Used when an optional integration is absent. Writing the keys explicitly rather than leaving
     * them out is the point: a scene that requires one of them must be able to see {@code UNAVAILABLE}
     * and take its declared path, and the capability report must be able to name the fields that went
     * missing (spec §10.7).
     */
    public ContextSnapshotBuilder allUnavailable(Iterable<ContextKey<?>> keys) {
        if (keys != null) {
            for (ContextKey<?> key : keys) {
                write(key, ContextValue.unavailable());
            }
        }
        return this;
    }

    private <T> ContextSnapshotBuilder write(ContextKey<T> key, ContextValue<T> value) {
        if (key == null) {
            return this;
        }
        String existingOwner = owners.get(key);
        if (existingOwner != null && !existingOwner.equals(currentSource)) {
            throw new IllegalStateException("context field '" + key.id() + "' is written by both '"
                    + existingOwner + "' and '" + currentSource + "'; one field has exactly one owner");
        }
        owners.put(key, currentSource);
        values.put(key, value);
        return this;
    }

    /** True when a field has already been written; lets a fallback source fill a gap it does not own. */
    public boolean has(ContextKey<?> key) {
        return values.containsKey(key);
    }

    /** Which source wrote a field, for the trace and the capability report. */
    public Optional<String> ownerOf(ContextKey<?> key) {
        return Optional.ofNullable(owners.get(key));
    }

    public ConversationContextSnapshot build() {
        return new ConversationContextSnapshot(values, capabilities.build(), capturedGameTime, capturedDay);
    }
}
