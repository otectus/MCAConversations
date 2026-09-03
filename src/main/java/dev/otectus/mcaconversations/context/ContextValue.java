package dev.otectus.mcaconversations.context;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * One field of a {@link ConversationContextSnapshot}: a value plus the reason it is or is not there
 * (spec §7.1).
 *
 * <p>Deliberately not {@code Optional<T>}. An {@code Optional} can only say "absent", and the whole
 * point of the context layer is that "the provider does not know" and "there is no provider" have to
 * reach the selector as different answers, so a scene can declare different behaviour for each
 * (spec §10.7).
 *
 * @param <T> the field's value type, fixed by its {@link ContextKey}
 */
public final class ContextValue<T> {

    private static final ContextValue<?> UNKNOWN = new ContextValue<>(ContextStatus.UNKNOWN, null);
    private static final ContextValue<?> UNAVAILABLE = new ContextValue<>(ContextStatus.UNAVAILABLE, null);

    private final ContextStatus status;
    private final T value;

    private ContextValue(ContextStatus status, T value) {
        this.status = status;
        this.value = value;
    }

    /** A field a provider answered. A null value is treated as {@link ContextStatus#UNKNOWN}. */
    public static <T> ContextValue<T> known(T value) {
        return value == null ? unknown() : new ContextValue<>(ContextStatus.KNOWN, value);
    }

    /** A field whose provider ran and could not answer. */
    @SuppressWarnings("unchecked")
    public static <T> ContextValue<T> unknown() {
        return (ContextValue<T>) UNKNOWN;
    }

    /** A field no provider was able to attempt — absent mod, missed compat handle. */
    @SuppressWarnings("unchecked")
    public static <T> ContextValue<T> unavailable() {
        return (ContextValue<T>) UNAVAILABLE;
    }

    /** Wraps an {@link Optional} from a provider that did run: empty becomes UNKNOWN, not absent. */
    public static <T> ContextValue<T> of(Optional<T> value) {
        return value == null || value.isEmpty() ? unknown() : known(value.get());
    }

    public ContextStatus status() {
        return status;
    }

    public boolean isKnown() {
        return status == ContextStatus.KNOWN;
    }

    /** The value, or empty for every non-KNOWN status. */
    public Optional<T> opt() {
        return Optional.ofNullable(value);
    }

    public T orElse(T fallback) {
        return value == null ? fallback : value;
    }

    /** Maps a known value, preserving the status of an unknown or unavailable one. */
    public <R> ContextValue<R> map(Function<? super T, ? extends R> mapper) {
        if (!isKnown()) {
            return status == ContextStatus.UNKNOWN ? ContextValue.unknown() : ContextValue.unavailable();
        }
        return known(mapper.apply(value));
    }

    /**
     * How this field prints in a trace and hashes into a {@link ContextFingerprint}: the value for a
     * known field, the status name otherwise. Never the raw object identity, so a fingerprint is
     * stable across restarts.
     */
    public String token() {
        return isKnown() ? String.valueOf(value) : status.name();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ContextValue<?> other
                && other.status == status
                && Objects.equals(other.value, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, value);
    }

    @Override
    public String toString() {
        return token();
    }
}
