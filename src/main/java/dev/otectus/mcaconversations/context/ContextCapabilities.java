package dev.otectus.mcaconversations.context;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * What each context provider was actually able to do on this snapshot (spec §7.1, §22.4).
 *
 * <p>Capability status is reported <em>separately from the field values</em> because the two answer
 * different questions. A field says what the world looks like; a capability says whether anyone was
 * in a position to look. Only the second can distinguish "MCA is installed but this handle missed on
 * your version" from "MCA answered, and the answer was no" — and that distinction is what
 * {@code /conversations compat status} exists to print and what the absent-integration tests assert
 * (spec §21.1, §24.7).
 */
public final class ContextCapabilities {

    /** How far one provider got. */
    public enum Status {
        /** The provider ran and supplied everything it declares. */
        READY,
        /** The provider ran, but at least one declared field could not be resolved. */
        DEGRADED,
        /** The provider is installed-but-off, or its optional mod is absent. */
        ABSENT,
        /** The provider threw. Its fields are unavailable and the failure is reported once. */
        FAILED
    }

    public static final ContextCapabilities EMPTY = new ContextCapabilities(Map.of(), Map.of());

    private final Map<String, Status> providers;
    private final Map<String, String> notes;

    private ContextCapabilities(Map<String, Status> providers, Map<String, String> notes) {
        this.providers = Collections.unmodifiableMap(new TreeMap<>(providers));
        this.notes = Collections.unmodifiableMap(new TreeMap<>(notes));
    }

    public static Builder builder() {
        return new Builder();
    }

    /** Status of one provider by id; {@link Status#ABSENT} for a provider that never registered. */
    public Status statusOf(String providerId) {
        return providers.getOrDefault(normalize(providerId), Status.ABSENT);
    }

    /** True when the named provider produced usable data. */
    public boolean isReady(String providerId) {
        return statusOf(providerId) == Status.READY;
    }

    /**
     * True when a scene requiring {@code capability} may be selected at all.
     *
     * <p>A degraded provider still counts: it supplied <em>some</em> fields, and the scene's own slot
     * requirements decide whether the ones it needs arrived. Refusing every degraded provider would
     * turn one missed handle into a silent content blackout (spec §22.4).
     */
    public boolean permits(String capability) {
        Status status = statusOf(capability);
        return status == Status.READY || status == Status.DEGRADED;
    }

    public Set<String> providerIds() {
        return providers.keySet();
    }

    /** A one-line explanation of a non-ready provider, for the trace and the compat report. */
    public String noteOf(String providerId) {
        return notes.getOrDefault(normalize(providerId), "");
    }

    public Map<String, Status> asMap() {
        return providers;
    }

    /** Deterministic, sorted, and free of prose — safe to hash into a fingerprint. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        providers.forEach((id, status) -> sb.append(id).append('=').append(status).append(';'));
        return sb.toString();
    }

    private static String normalize(String id) {
        return id == null ? "" : id.trim().toLowerCase(Locale.ROOT);
    }

    public static final class Builder {
        private final Map<String, Status> providers = new LinkedHashMap<>();
        private final Map<String, String> notes = new LinkedHashMap<>();

        public Builder report(String providerId, Status status) {
            return report(providerId, status, "");
        }

        public Builder report(String providerId, Status status, String note) {
            String id = normalize(providerId);
            if (id.isEmpty() || status == null) {
                return this;
            }
            providers.put(id, status);
            if (note != null && !note.isBlank()) {
                notes.put(id, note);
            }
            return this;
        }

        public ContextCapabilities build() {
            return providers.isEmpty() ? EMPTY : new ContextCapabilities(providers, notes);
        }
    }
}
