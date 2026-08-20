package dev.otectus.mcaconversations.locale;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.personality.Personalities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The shape of this mod's own {@code /N} dialogue pools, readable on a dedicated server.
 *
 * <p><b>Why this is needed.</b> MCA picks a pooled variant on the <em>client</em>, at random, every
 * time a line is resolved ({@code PooledTranslationStorage.get}). That is fine for one recipient and
 * wrong for several: a chat-mode reply goes to the speaker and to every bystander inside
 * {@code chatModeAddressedRadius} as separate messages, so each client rolls its own sentence and two
 * players standing together read different words from the same villager. Choosing the variant once,
 * server-side, and sending a concrete {@code …/N} key makes everyone read the same line.
 *
 * <p><b>Where the numbers come from.</b> Lang files live under {@code assets/}, which a dedicated
 * server never mounts as a resource pack — but they are still physically inside our jar, so they are
 * read here straight off the classpath. Nothing is generated at build time, so the index cannot drift
 * from the file it describes. Only {@code en_us} is read: {@code LocaleParityTest} already fails the
 * build unless every authored locale has identical variant runs.
 *
 * <p><b>What is deliberately excluded.</b> A pool is recorded only when the indices we ship are
 * exactly {@code 1..N}. Five of our pools instead <em>extend</em> MCA's own — we append
 * {@code dialogue.main/8../12} to MCA's {@code /1../7} — and for those we know only part of the pool,
 * so they are left out and keep MCA's own per-client pick. The same rule covers any future partial
 * pool automatically.
 *
 * <p>Every lookup answers {@code 0} for anything unknown, and a load failure leaves the index empty,
 * so the caller's fail-open branch (leave the component alone) is the natural default.
 */
public final class VariantPools {

    /** Variant text lengths per base key, index 0 holding {@code /1}. Null until loaded. */
    private static volatile Map<String, int[]> basePools;

    /** Variant counts for {@code <personality>.<base>} overlay keys. */
    private static volatile Map<String, Integer> overlayPools;

    private VariantPools() {
    }

    /**
     * How many variants of {@code baseKey} this mod ships, or 0 when the key is unpooled, unknown, or
     * part of a pool we only partly own. {@code baseKey} is the full lang key
     * ({@code dialogue.conversations.…}), not the bare {@code say} phrase.
     */
    public static int poolSize(String baseKey) {
        load();
        int[] lengths = basePools.get(baseKey);
        return lengths == null ? 0 : lengths.length;
    }

    /** How many variants the {@code personality} overlay ships for {@code baseKey}; 0 when it has none. */
    public static int overlayPoolSize(String baseKey, String personality) {
        load();
        if (personality == null || personality.isEmpty()) {
            return 0;
        }
        Integer size = overlayPools.get(personality + "." + baseKey);
        return size == null ? 0 : size;
    }

    /**
     * The highest variant index it is safe to name for a villager of this personality: the smaller of
     * the base pool and that personality's overlay pool.
     *
     * <p>The minimum is what keeps a personality villager in voice. MCA's client resolver prefers
     * {@code <personality>.<key>} and falls back to the bare key only when that lookup misses, so
     * naming a {@code /3} on a key whose overlay stops at {@code /2} would quietly drop the villager
     * back to the generic line. Overlays are shorter than base pools for most keys, so this is the
     * common case rather than an edge one.
     */
    public static int deliverablePoolSize(String baseKey, String personality) {
        int base = poolSize(baseKey);
        if (base == 0) {
            return 0;
        }
        int overlay = overlayPoolSize(baseKey, personality);
        return overlay == 0 ? base : Math.min(base, overlay);
    }

    /**
     * Character length of the {@code n}th variant (1-based) of {@code baseKey}, or 0 when unknown —
     * the input to the humanised chat-mode reply delay, which cannot resolve the component itself on a
     * dedicated server. The base pool's text is measured even when the client will render the
     * personality overlay instead; the two are close enough for a typing-speed heuristic.
     */
    public static int variantLength(String baseKey, int n) {
        load();
        int[] lengths = basePools.get(baseKey);
        if (lengths == null || n < 1 || n > lengths.length) {
            return 0;
        }
        return lengths[n - 1];
    }

    /** Test seam: drop the cached index so a test can observe a cold load. */
    public static void resetForTesting() {
        basePools = null;
        overlayPools = null;
    }

    // ------------------------------------------------------------------

    private static void load() {
        if (basePools != null) {
            return;
        }
        synchronized (VariantPools.class) {
            if (basePools != null) {
                return;
            }
            Map<String, int[]> base = Map.of();
            Map<String, Integer> overlays = Map.of();
            try {
                base = readBasePools("/assets/mca_dialogue/lang/en_us.json");
                overlays = readOverlayPools();
            } catch (Throwable t) {
                McaConversations.LOGGER.debug(
                        "variant-pool index failed to load; chat lines keep MCA's per-client pick", t);
            }
            overlayPools = overlays;
            basePools = base; // published last: it is the field load() gates on
        }
    }

    /** Groups {@code <base>/N} keys and keeps only runs that are exactly {@code 1..N}. */
    private static Map<String, int[]> readBasePools(String resource) {
        Map<String, TreeMap<Integer, Integer>> runs = new HashMap<>();
        forEachEntry(resource, (key, value) -> {
            int slash = variantSplit(key);
            if (slash < 0) {
                return;
            }
            runs.computeIfAbsent(key.substring(0, slash), k -> new TreeMap<>())
                    .put(Integer.parseInt(key.substring(slash + 1)), value.length());
        });
        Map<String, int[]> pools = new HashMap<>(runs.size());
        runs.forEach((base, run) -> {
            if (!isOneBasedRun(run)) {
                return; // a pool we only partly own (e.g. our extensions of MCA's dialogue.main)
            }
            int[] lengths = new int[run.size()];
            run.forEach((n, len) -> lengths[n - 1] = len);
            pools.put(base, lengths);
        });
        return Collections.unmodifiableMap(pools);
    }

    private static Map<String, Integer> readOverlayPools() {
        Map<String, Integer> counts = new HashMap<>();
        for (String personality : Personalities.overlayPrefixes()) {
            forEachEntry("/assets/mca_dialogue_" + personality + "/lang/en_us.json", (key, value) -> {
                int slash = variantSplit(key);
                if (slash >= 0) {
                    counts.merge(key.substring(0, slash), 1, Integer::sum);
                }
            });
        }
        return Collections.unmodifiableMap(counts);
    }

    /** Index of the {@code /} introducing a trailing all-digit variant suffix, or -1. */
    private static int variantSplit(String key) {
        int slash = key.lastIndexOf('/');
        if (slash < 0 || slash == key.length() - 1) {
            return -1;
        }
        for (int i = slash + 1; i < key.length(); i++) {
            if (!Character.isDigit(key.charAt(i))) {
                return -1;
            }
        }
        return slash;
    }

    private static boolean isOneBasedRun(TreeMap<Integer, Integer> run) {
        int expected = 1;
        for (int n : run.keySet()) {
            if (n != expected++) {
                return false;
            }
        }
        return true;
    }

    private interface EntryVisitor {
        void accept(String key, String value);
    }

    private static void forEachEntry(String resource, EntryVisitor visitor) {
        try (InputStream in = VariantPools.class.getResourceAsStream(resource)) {
            if (in == null) {
                McaConversations.LOGGER.debug("variant-pool resource {} missing; skipping", resource);
                return;
            }
            JsonObject json = JsonParser.parseReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                if (entry.getValue().isJsonPrimitive()) {
                    visitor.accept(entry.getKey(), entry.getValue().getAsString());
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("variant-pool resource {} unreadable; skipping", resource, t);
        }
    }
}
