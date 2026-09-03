package dev.otectus.mcaconversations.context;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

/**
 * A short, stable digest of the context a decision was made in (spec §9.3).
 *
 * <p>Two jobs, both about determinism:
 * <ul>
 *   <li>it feeds the director's selection seed, so the same villager in the same world state on the
 *       same day picks the same scene however many times the player reopens the screen;</li>
 *   <li>it is stored on the frozen plan, so the next turn can ask whether the world has drifted far
 *       enough to invalidate the plan — and say so in the trace instead of silently rerolling.</li>
 * </ul>
 *
 * <p>Computed over the <b>pinned</b> fields only. Volatile fields are excluded by construction: if
 * the time band ticking over from {@code midday} to {@code afternoon} changed the fingerprint, every
 * plan would invalidate itself mid-conversation and the reroll-resistance guarantee would be worth
 * nothing.
 *
 * <p>Not a security hash. It is FNV-1a over a sorted {@code key=token} rendering, which is fast,
 * allocation-light, and identical on every platform and JVM — unlike {@code Object.hashCode}, whose
 * value for a {@code String} is stable but whose value for anything else is not.
 */
public record ContextFingerprint(long value) {

    public static final ContextFingerprint EMPTY = new ContextFingerprint(0xcbf29ce484222325L);

    private static final long FNV_OFFSET = 0xcbf29ce484222325L;
    private static final long FNV_PRIME = 0x100000001b3L;

    /** Digests the pinned fields of a snapshot, in key order. */
    public static ContextFingerprint of(Map<ContextKey<?>, ContextValue<?>> values) {
        if (values == null || values.isEmpty()) {
            return EMPTY;
        }
        Map<String, String> sorted = new TreeMap<>();
        values.forEach((key, value) -> {
            if (!key.isVolatile()) {
                sorted.put(key.id(), value.token());
            }
        });
        long hash = FNV_OFFSET;
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            hash = mix(hash, entry.getKey());
            hash = mix(hash, "=");
            hash = mix(hash, entry.getValue());
            hash = mix(hash, ";");
        }
        return new ContextFingerprint(hash);
    }

    private static long mix(long hash, String text) {
        for (byte b : text.getBytes(StandardCharsets.UTF_8)) {
            hash ^= (b & 0xff);
            hash *= FNV_PRIME;
        }
        return hash;
    }

    /** Sixteen lowercase hex digits — how the fingerprint appears in traces and plan nonces. */
    public String hex() {
        return String.format("%016x", value);
    }

    @Override
    public String toString() {
        return hex();
    }
}
