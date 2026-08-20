package dev.otectus.mcaconversations.content;

import java.util.Map;

/**
 * One rule for "does this lang key name a line a player can actually see?", shared by the content
 * lints.
 *
 * <p>It is not simply {@code lang.containsKey(key)}. MCA's client-side
 * {@code PooledTranslationStorage} indexes only keys matching {@code /[0-9]+$}, grouped under their
 * base, and {@code mca$onGet} always draws from that index once it is non-empty. So a family is
 * either a single plain key or a {@code /1../N} pool, and a plain sentence left beside a pool is dead
 * content — which is why the pooled families here carry no plain key at all.
 *
 * <p>Every lint that asks "is there a line for X" therefore has to accept both spellings, or it will
 * report a perfectly good pool as missing.
 */
final class LangKeys {

    private LangKeys() {
    }

    /** True when {@code key} resolves at runtime — as a plain key, or as the head of a {@code /N} pool. */
    static boolean hasLine(Map<String, String> lang, String key) {
        return lang.containsKey(key) || lang.containsKey(key + "/1");
    }
}
