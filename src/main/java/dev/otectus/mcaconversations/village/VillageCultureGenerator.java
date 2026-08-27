package dev.otectus.mcaconversations.village;

import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Turns a world seed and a village id into that village's six tokens (spec §17.3).
 *
 * <p>Deterministic for the same reason villager identity is: two players walking into the same
 * village must find the same place, and a server restart must not quietly rewrite what the village
 * believes about itself. The seed is the world seed, the village id and a schema string — and nothing
 * else. Not the day, not the population, not who is asking.
 *
 * <p>The RNG is SplitMix64, written out rather than taken from {@code java.util.Random}, for the same
 * reason the identity generator writes it out: it is a pure function of seed and draw index, so the
 * fifth family can be drawn without replaying the first four, and it cannot change under a JDK
 * upgrade.
 *
 * <p>Generation happens once. The result is persisted, and a later datapack that adds ten new
 * festivals leaves existing villages exactly as they were — which is what makes a village somewhere
 * you can come back to.
 */
public final class VillageCultureGenerator {

    /** Bumped only when the meaning of the draw changes; a bump rerolls every village. */
    public static final int SCHEMA_VERSION = 1;

    private VillageCultureGenerator() {
    }

    /** The stable seed for one village. */
    public static long seedFor(long worldSeed, int villageId) {
        long hash = 0xcbf29ce484222325L ^ worldSeed;
        hash = mix(hash, "village/" + villageId);
        hash = mix(hash, "culture/v" + SCHEMA_VERSION);
        return hash;
    }

    /**
     * Generates a village's culture, or empty when the catalog cannot fill every family.
     *
     * <p>Partial cultures are refused rather than stored. A village with a festival and no landmark
     * would give every scene that binds a landmark a hole to handle, and the honest answer for a
     * datapack that has not offered one is that this install has no village culture at all.
     */
    public static Optional<VillageCultureRecord> generate(VillageCultureCatalog catalog,
                                                          long seed,
                                                          int villageId,
                                                          long day,
                                                          Predicate<String> modPresent) {
        if (catalog == null || catalog.isEmpty()) {
            return Optional.empty();
        }
        Map<CultureFamily, String> tokens = new EnumMap<>(CultureFamily.class);
        int draw = 0;
        for (CultureFamily family : CultureFamily.values()) {
            List<CultureToken> candidates = catalog.candidates(family, modPresent);
            if (candidates.isEmpty()) {
                return Optional.empty();
            }
            tokens.put(family, weighted(candidates, next(seed, draw++)).id());
        }
        return Optional.of(new VillageCultureRecord(villageId, tokens, day, java.util.Set.of()));
    }

    /** Picks one candidate proportionally to its weight, from one drawn value. */
    private static CultureToken weighted(List<CultureToken> candidates, long value) {
        long total = 0;
        for (CultureToken token : candidates) {
            total += token.weight();
        }
        if (total <= 0) {
            return candidates.get(0);
        }
        long roll = Math.floorMod(value, total);
        for (CultureToken token : candidates) {
            roll -= token.weight();
            if (roll < 0) {
                return token;
            }
        }
        return candidates.get(candidates.size() - 1);
    }

    /** SplitMix64: the {@code index}-th draw from {@code seed}, without replaying the earlier ones. */
    private static long next(long seed, int index) {
        long z = seed + 0x9E3779B97F4A7C15L * (index + 1L);
        z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
        z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
        return z ^ (z >>> 31);
    }

    private static long mix(long hash, String value) {
        for (byte b : value.getBytes(StandardCharsets.UTF_8)) {
            hash ^= (b & 0xff);
            hash *= 0x100000001b3L;
        }
        return hash;
    }
}
