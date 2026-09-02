package dev.otectus.mcaconversations.locale;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Which sentence out of a pool this villager says to this player, and the memory that stops it being
 * the same one twice running.
 *
 * <h2>The problem</h2>
 *
 * <p>Almost every villager line in this mod is a {@code /N} pool, and the median pool holds three
 * sentences. MCA resolves a pool on the client with a bare {@code nextInt} per component instance and
 * remembers nothing, so three sentences do not read as three: over ten exchanges a uniform draw from
 * three repeats the previous sentence about three times, and a player who has just been told the same
 * thing twice concludes there is only one thing to be told. Repetition is the loudest complaint in the
 * whole life-sim genre, and it is not usually a shortage of writing — it is a shortage of memory.
 *
 * <h2>The rule</h2>
 *
 * <p>Valve's dialogue system solved this with response groups that will not repeat a line until the
 * group is exhausted, and that is what happens here. Each (villager, player, line) keeps a bitmask of
 * which variants have already been spoken. A pick chooses from the ones that have not; when the mask
 * fills it resets, minus the sentence just said, so the boundary between one pass and the next is not
 * where a repeat sneaks in. A pool of three is therefore heard as three, in a varying order, and never
 * twice in a row — which is the difference a player actually notices.
 *
 * <p>The choice is seeded rather than random: world seed, both UUIDs, the line, and the mask itself.
 * That inherits the property {@code ConversationDirector} is built around — closing and reopening a
 * screen is not a reroll — and it means one utterance rendered to the speaker and to every bystander
 * is one sentence, which is the reason {@link VariantPools} exists at all.
 *
 * <h2>Why this is not in the save file</h2>
 *
 * <p>The state is a bounded in-memory map, not a record on {@code PairHistory}. Persisting it would
 * mean a save-data write for every sentence a villager speaks rather than one per scene, and it would
 * buy only that a relog cannot repeat one line. That is the same trade {@code InitiativeGate} makes
 * for its cooldown: a value measured in the last few minutes, which would earn nothing as a schema
 * field. An eviction or a restart costs one possible repeat and nothing else.
 */
public final class LineVoice {

    /**
     * Distinct (villager, player, line) pools tracked at once, least-recently-used evicted.
     *
     * <p>Sized like {@code InitiativeGate.MAX_TRACKED_PAIRS}, and forgiving for the same reason: an
     * evicted line simply starts a fresh pass, which costs at most one repeat.
     */
    public static final int MAX_TRACKED_LINES = 8192;

    /**
     * Widest pool the exhaustion mask covers. Our deepest shipped pool holds six; beyond eight a pick
     * falls back to "anything but the last one", which is the property that matters most anyway.
     */
    static final int MAX_MASKED_POOL = 8;

    private static final int MASK_BITS = 8;
    private static final int MASK = (1 << MASK_BITS) - 1;

    /** (villager, player, line) -> packed {@link #pack state}. Access-ordered, so eviction is LRU. */
    private static final Map<String, Integer> STATE = Collections.synchronizedMap(
            new LinkedHashMap<>(256, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                    return size() > MAX_TRACKED_LINES;
                }
            });

    private LineVoice() {
    }

    /**
     * A line with its pooled variant chosen.
     *
     * @param line   the component to send; the original when nothing was pinned
     * @param length the chosen variant's text length, or 0 when it is not a pool we own — callers that
     *               scale a typing delay by sentence length need the length of the sentence actually
     *               said, and on a dedicated server they cannot measure it themselves because
     *               {@code assets/} is never mounted
     */
    public record Voiced(Component line, int length) {
    }

    /**
     * Names one concrete {@code …/N} variant of a pooled line for this pair.
     *
     * <p>Appending the index to the marker-laden key is safe: MCA's {@code applyFallback} strips its
     * {@code #G}/{@code #E}/{@code #P}/{@code #T} tokens by dot-separated prefix and its pool regex is
     * anchored at {@code /[0-9]+$}, so the client sees a concrete key, finds no pool for it, and
     * renders that one line — through the personality overlay when it has one, which is what
     * {@link VariantPools#deliverablePoolSize} keeps the index inside.
     *
     * <p>Fails open at every step: anything that is not a pooled translatable we own is returned
     * untouched and keeps MCA's own per-client pick.
     */
    public static Voiced pin(Component line, Entity villager, ServerPlayer player) {
        try {
            if (line != null && line.getContents() instanceof TranslatableContents contents) {
                String base = stripMarkers(contents.getKey());
                int pool = VariantPools.deliverablePoolSize(base,
                        McaCompat.getPersonality(villager).orElse(""));
                if (pool >= 2) {
                    int n = next(villager, player, base, pool);
                    MutableComponent pinned = Component.translatable(contents.getKey() + "/" + n,
                                    contents.getArgs())
                            .setStyle(line.getStyle());
                    line.getSiblings().forEach(pinned::append);
                    return new Voiced(pinned, Math.max(0, VariantPools.variantLength(base, n)));
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("variant pinning failed; leaving MCA's per-client pick", t);
        }
        return new Voiced(line, 0);
    }

    /** {@link #pin} for callers that only want the component. */
    public static Component pinned(Component line, Entity villager, ServerPlayer player) {
        return pin(line, villager, player).line();
    }

    /**
     * The next variant for this pair and line, advancing the pass.
     *
     * <p>Impure only in reading the world seed and the map; the decision itself is
     * {@link #chooseVariant}.
     */
    private static int next(Entity villager, ServerPlayer player, String base, int pool) {
        String key = stateKey(villager, player, base);
        long seedBase = seedBasis(villager, player, base);
        // Read, decide and write under one lock: two villagers answering one shouted question are
        // delivered from the same thread today, but a split second of interleaving here would silently
        // undo the exhaustion rule rather than fail, and that is the kind of bug nobody reports.
        synchronized (STATE) {
            int state = STATE.getOrDefault(key, 0);
            int chosen = chooseVariant(pool, state, seedBase);
            STATE.put(key, advance(pool, state, chosen));
            return chosen;
        }
    }

    // --- The rule, as pure functions ---------------------------------------------------------------

    /**
     * Packs a pass into one int: the low byte is the set of variants already spoken (bit 0 is variant
     * 1), the next byte is the variant spoken last, 1-based, 0 for none.
     *
     * <p>For a pool too wide for a mask the low byte carries a step counter instead. It is not
     * decoration: without a term that changes on every pick, the wide-pool choice is a function of the
     * last index alone, and a function iterated on its own output falls into a short cycle almost at
     * once — two sentences alternating forever, which is worse than the random draw being replaced.
     */
    static int pack(int usedMask, int lastIndex) {
        return (usedMask & MASK) | ((lastIndex & MASK) << MASK_BITS);
    }

    /** Variants already spoken in the current pass. */
    static int usedMask(int state) {
        return state & MASK;
    }

    /** The variant spoken last, 1-based; 0 before anything has been said. */
    static int lastIndex(int state) {
        return (state >>> MASK_BITS) & MASK;
    }

    /**
     * Chooses a 1-based variant from {@code pool} given the current pass.
     *
     * <p>Candidates are the variants this pass has not used. When none are left the pass is over and
     * every variant is a candidate again <em>except</em> the one just said — the exhaustion rule is
     * worth nothing if the last line of one pass can be the first line of the next.
     *
     * <p>A pool wider than {@link #MAX_MASKED_POOL} keeps only the no-immediate-repeat half of the
     * rule; a mask cannot describe it, and at that width a player will not notice the difference.
     */
    static int chooseVariant(int pool, int state, long seed) {
        if (pool <= 1) {
            return 1;
        }
        int last = lastIndex(state);
        if (pool > MAX_MASKED_POOL) {
            return pickExcluding(pool, last, mix(seed, usedMask(state)));
        }
        int used = usedMask(state) & ((1 << pool) - 1);
        int available = ~used & ((1 << pool) - 1);
        if (available == 0) {
            return pickExcluding(pool, last, seed);
        }
        int count = Integer.bitCount(available);
        int wanted = (int) Math.floorMod(mix(seed, used), count);
        for (int i = 0; i < pool; i++) {
            if ((available & (1 << i)) != 0 && wanted-- == 0) {
                return i + 1;
            }
        }
        return 1;
    }

    /** The pass after {@code chosen} was said: a full mask starts over, holding only the new choice. */
    static int advance(int pool, int state, int chosen) {
        if (pool > MAX_MASKED_POOL && chosen >= 1 && chosen <= pool) {
            return pack(usedMask(state) + 1, chosen);
        }
        if (pool <= 1 || pool > MAX_MASKED_POOL || chosen < 1 || chosen > pool) {
            return pack(0, Math.max(0, Math.min(MASK, chosen)));
        }
        int all = (1 << pool) - 1;
        int used = usedMask(state) & all;
        // A pick made after the mask filled belongs to the new pass, not the finished one.
        int next = used == all ? 0 : used;
        return pack(next | (1 << (chosen - 1)), chosen);
    }

    /** A seeded pick from {@code 1..pool}, never {@code excluded} unless the pool has nothing else. */
    private static int pickExcluding(int pool, int excluded, long seed) {
        if (excluded < 1 || excluded > pool) {
            return (int) Math.floorMod(mix(seed, pool), pool) + 1;
        }
        int picked = (int) Math.floorMod(mix(seed, excluded), pool - 1) + 1;
        return picked >= excluded ? picked + 1 : picked;
    }

    /**
     * SplitMix64 finalisation over the basis and one varying term.
     *
     * <p>The mask is part of the input on purpose: it is what makes two consecutive picks for the same
     * pair and line differ without a clock or an RNG anywhere in the path.
     */
    static long mix(long basis, long varying) {
        long z = basis * 0x9E3779B97F4A7C15L + varying * 0xBF58476D1CE4E5B9L;
        z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
        z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
        return z ^ (z >>> 31);
    }

    /**
     * Pure: drops MCA's leading {@code #G…}/{@code #E…}/{@code #P…}/{@code #T…} marker tokens from a
     * {@code getTranslatable} key, leaving the plain lang key. Mirrors what
     * {@code DialogueType.applyFallback} does client-side. A malformed key is returned unchanged.
     */
    public static String stripMarkers(String key) {
        if (key == null) {
            return "";
        }
        int i = 0;
        while (i < key.length() && key.charAt(i) == '#') {
            int dot = key.indexOf('.', i);
            if (dot < 0) {
                return key;
            }
            i = dot + 1;
        }
        return key.substring(i);
    }

    // --- Keys and seeds ----------------------------------------------------------------------------

    private static String stateKey(Entity villager, ServerPlayer player, String base) {
        return uuid(villager) + "|" + uuid(player) + "|" + base;
    }

    private static long seedBasis(Entity villager, ServerPlayer player, String base) {
        long seed = worldSeed(villager);
        UUID v = villager == null ? null : villager.getUUID();
        UUID p = player == null ? null : player.getUUID();
        long h = seed;
        h = h * 31 + (v == null ? 0 : v.getMostSignificantBits() ^ v.getLeastSignificantBits());
        h = h * 31 + (p == null ? 0 : p.getMostSignificantBits() ^ p.getLeastSignificantBits());
        h = h * 31 + base.hashCode();
        return h;
    }

    /**
     * The world seed, so two worlds do not walk their pools in lockstep. Zero when it cannot be read —
     * the pair UUIDs already carry more than enough separation on their own.
     */
    private static long worldSeed(Entity villager) {
        try {
            if (villager != null
                    && villager.level() instanceof net.minecraft.server.level.ServerLevel level) {
                return level.getSeed();
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("world seed unavailable for variant selection", t);
        }
        return 0L;
    }

    private static String uuid(Entity entity) {
        return entity == null ? "-" : entity.getUUID().toString();
    }

    /** Test seam: drops every remembered pass. */
    public static void resetForTesting() {
        STATE.clear();
    }

    /** Test seam: how many passes are currently remembered. */
    public static int trackedForTesting() {
        return STATE.size();
    }
}
