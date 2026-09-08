package dev.otectus.mcaconversations.context;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /** How deep a nested collection is followed before the rest is summarised as {@code deep}. */
    private static final int MAX_TOKEN_DEPTH = 8;

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
     * How this field prints in a trace and hashes into a {@link ContextFingerprint}: a canonical,
     * type-tagged rendering of the value, or the status otherwise. Never the raw object identity, so
     * a fingerprint is stable across restarts.
     *
     * <p>The grammar, which every shape a context key can hold has exactly one spelling in:
     * <pre>
     *   status:UNKNOWN | status:UNAVAILABLE   a field no provider answered
     *   nil                                   a null inside a collection
     *   str:&lt;text&gt;                            String and any other CharSequence
     *   bool:true | bool:false                Boolean
     *   int:&lt;Long.toString&gt;                   Byte, Short, Integer, Long
     *   num:&lt;toString&gt;                        Float, Double and every other Number
     *   enum:&lt;name&gt;                           any enum constant, by name
     *   uuid:&lt;toString&gt;                       UUID
     *   list:[n]{e0,e1,…}                     List — order preserved, length explicit
     *   set:{e0,e1,…}                         any other Collection — elements sorted by encoding
     *   map:{k=v,…}                           Map — entries sorted by encoding
     *   obj:&lt;String.valueOf&gt;                  anything else
     * </pre>
     *
     * <p>Three properties this buys, all of which the fingerprint depends on and none of which the
     * old {@code String.valueOf} had:
     * <ul>
     *   <li><b>Set order cannot matter.</b> Two {@code HashSet}s with the same members hash the same
     *       however they were built, so a fingerprint no longer flickers with insertion order and
     *       invalidates a plan for nothing. A {@code List} keeps its order, because for a list of
     *       recent subjects the order <em>is</em> the fact.</li>
     *   <li><b>A status cannot alias a value.</b> The literal string {@code "UNKNOWN"} encodes as
     *       {@code str:UNKNOWN}; the UNKNOWN status encodes as {@code status:UNKNOWN}.</li>
     *   <li><b>Numbers are locale-independent.</b> {@code Long.toString} and {@code Double.toString}
     *       never consult a default locale, so a French client and an English server agree — which
     *       {@code String.format("%d")} would not.</li>
     * </ul>
     *
     * <p>Delimiters inside a payload are backslash-escaped, so no value can forge a separator and
     * collide with a differently-shaped one; nesting recurses to a fixed depth and then stops, so a
     * pathological or self-referential structure costs a token rather than a stack.
     *
     * <p>The encoding is free to change: a fingerprint is never persisted, only compared within one
     * run and printed in a trace.
     */
    public String token() {
        return isKnown() ? encode(value, 0) : "status:" + status.name();
    }

    private static String encode(Object value, int depth) {
        if (value == null) {
            return "nil";
        }
        if (depth >= MAX_TOKEN_DEPTH) {
            return "deep";
        }
        if (value instanceof CharSequence text) {
            return "str:" + escape(text.toString());
        }
        if (value instanceof Boolean flag) {
            return "bool:" + flag;
        }
        if (value instanceof Byte || value instanceof Short
                || value instanceof Integer || value instanceof Long) {
            return "int:" + Long.toString(((Number) value).longValue());
        }
        if (value instanceof Float number) {
            return "num:" + Float.toString(number);
        }
        if (value instanceof Double number) {
            return "num:" + Double.toString(number);
        }
        if (value instanceof Number number) {
            return "num:" + escape(number.toString());
        }
        if (value instanceof Enum<?> constant) {
            return "enum:" + escape(constant.name());
        }
        if (value instanceof UUID uuid) {
            return "uuid:" + uuid;
        }
        if (value instanceof List<?> list) {
            StringBuilder out = new StringBuilder("list:[").append(list.size()).append("]{");
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    out.append(',');
                }
                out.append(encode(list.get(i), depth + 1));
            }
            return out.append('}').toString();
        }
        if (value instanceof Collection<?> collection) {
            return "set:" + joinSorted(collection.stream().map(element -> encode(element, depth + 1)));
        }
        if (value instanceof Map<?, ?> map) {
            return "map:" + joinSorted(map.entrySet().stream()
                    .map(entry -> encode(entry.getKey(), depth + 1)
                            + "=" + encode(entry.getValue(), depth + 1)));
        }
        return "obj:" + escape(String.valueOf(value));
    }

    /**
     * Sorted by the encoded element, never by the element itself.
     *
     * <p>Sorting the encodings means the order does not depend on a {@code Comparable} the element
     * type may not implement, and {@code String}'s natural ordering is by code point — no collator,
     * no locale, the same answer on every machine.
     */
    private static String joinSorted(Stream<String> encoded) {
        return encoded.sorted().collect(Collectors.joining(",", "{", "}"));
    }

    /** Backslashes the delimiters the grammar uses, so a payload can never forge one. */
    private static String escape(String text) {
        if (text.indexOf('\\') < 0 && text.chars().noneMatch(ContextValue::isDelimiter)) {
            return text;
        }
        StringBuilder out = new StringBuilder(text.length() + 8);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\\' || isDelimiter(c)) {
                out.append('\\');
            }
            out.append(c);
        }
        return out.toString();
    }

    private static boolean isDelimiter(int c) {
        return c == ',' || c == '{' || c == '}' || c == '[' || c == ']' || c == ';' || c == '=';
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
