package dev.otectus.mcaconversations.context;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * The name and type of one context field (spec §7.1).
 *
 * <p>Identity-compared and interned by id in a global registry, so that:
 * <ul>
 *   <li>a datapack condition can name a field as a string and be resolved to the typed key the
 *       providers actually write, with an unknown name failing at parse time rather than silently
 *       never matching;</li>
 *   <li>the capability report and the trace can enumerate every field the mod knows about without a
 *       hand-maintained second list.</li>
 * </ul>
 *
 * <p>{@link #isVolatile()} marks the handful of fields allowed to be refreshed mid-scene. Everything
 * else is pinned for the life of the scene, because a scene that re-reads its own facts between turns
 * is how a bound referent silently becomes a different person (spec §7.4).
 *
 * @param <T> the value type providers must write for this key
 */
public final class ContextKey<T> {

    /** Field ids: dotted lowercase, matching the token shape used everywhere else in the mod. */
    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");

    private static final Map<String, ContextKey<?>> REGISTRY = new LinkedHashMap<>();

    private final String id;
    private final Class<T> type;
    private final boolean volatileField;

    private ContextKey(String id, Class<T> type, boolean volatileField) {
        this.id = id;
        this.type = type;
        this.volatileField = volatileField;
    }

    /** Declares a pinned field. Called only from {@link ContextKeys} static initialisers. */
    static <T> ContextKey<T> of(String id, Class<T> type) {
        return register(id, type, false);
    }

    /** Declares a field that may legally be re-read at a turn boundary (spec §7.4). */
    static <T> ContextKey<T> volatileOf(String id, Class<T> type) {
        return register(id, type, true);
    }

    /**
     * Declares a field whose value type is generic — {@code Set<String>}, {@code List<String>} — and
     * therefore has no matching class literal. The erasure is what gets stored for the runtime type
     * check; the declared parameter is what consumers read, so they need no cast of their own.
     */
    @SuppressWarnings("unchecked")
    static <T> ContextKey<T> generic(String id, Class<?> erasure) {
        return register(id, (Class<T>) erasure, false);
    }

    /** {@link #generic} for a field that may be refreshed at a turn boundary. */
    @SuppressWarnings("unchecked")
    static <T> ContextKey<T> volatileGeneric(String id, Class<?> erasure) {
        return register(id, (Class<T>) erasure, true);
    }

    private static synchronized <T> ContextKey<T> register(String id, Class<T> type, boolean volatileField) {
        String normalized = id == null ? "" : id.trim().toLowerCase(Locale.ROOT);
        if (!ID.matcher(normalized).matches()) {
            throw new IllegalArgumentException("context key '" + id + "' must match " + ID.pattern());
        }
        if (REGISTRY.containsKey(normalized)) {
            throw new IllegalStateException("context key '" + normalized + "' is declared twice");
        }
        ContextKey<T> key = new ContextKey<>(normalized, type, volatileField);
        REGISTRY.put(normalized, key);
        return key;
    }

    /** Resolves a datapack-supplied field name. Empty for a name no provider declares. */
    public static Optional<ContextKey<?>> byId(String id) {
        if (id == null) {
            return Optional.empty();
        }
        ContextKeys.touch();
        synchronized (ContextKey.class) {
            return Optional.ofNullable(REGISTRY.get(id.trim().toLowerCase(Locale.ROOT)));
        }
    }

    /** Every declared field, in declaration order. Used by the capability report and the trace. */
    public static Collection<ContextKey<?>> all() {
        ContextKeys.touch();
        synchronized (ContextKey.class) {
            return List.copyOf(REGISTRY.values());
        }
    }

    public String id() {
        return id;
    }

    public Class<T> type() {
        return type;
    }

    public boolean isVolatile() {
        return volatileField;
    }

    /** Narrows an untyped value from the snapshot map back to this key's type. */
    @SuppressWarnings("unchecked")
    ContextValue<T> cast(ContextValue<?> raw) {
        return (ContextValue<T>) raw;
    }

    @Override
    public String toString() {
        return id;
    }
}
