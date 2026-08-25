package dev.otectus.mcaconversations.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A load-time alias map from stemmed word to canonical stemmed word (spec §6.2 step 8, §7 {@code
 * synonyms}). Datapack synonym classes ({@code "work": ["job","trade",...]}) are stemmed by the
 * {@link Normalizer} and flattened into this one-level map so both index keywords and query tokens
 * canonicalize identically. Pure data — no Minecraft imports.
 *
 * <p>Resolution is deliberately one level deep (no recursive rewriting): {@link #canonical} maps a
 * stem once, or returns it unchanged. Building the table detects a stem claimed by two different
 * canonicals (a datapack authoring error); {@link Builder#conflicts()} exposes them for the lint.
 */
public final class SynonymTable {

    public static final SynonymTable EMPTY = new SynonymTable(Collections.emptyMap());

    private final Map<String, String> stemToCanonical;

    /** Reverse view of {@link #stemToCanonical}, canonical → the aliases that resolve to it. */
    private final Map<String, List<String>> canonicalToAliases;

    private SynonymTable(Map<String, String> stemToCanonical) {
        this.stemToCanonical = stemToCanonical;
        Map<String, List<String>> reverse = new HashMap<>();
        stemToCanonical.forEach((stem, canonical) -> {
            if (!stem.equals(canonical)) {
                reverse.computeIfAbsent(canonical, k -> new ArrayList<>()).add(stem);
            }
        });
        this.canonicalToAliases = reverse;
    }

    /** The canonical stem for {@code stem}, or {@code stem} itself when it is not an alias. */
    public String canonical(String stem) {
        return stemToCanonical.getOrDefault(stem, stem);
    }

    /**
     * Every alias that canonicalizes to {@code canonical}, empty when it has none.
     *
     * <p>Phrase patterns are stored canonicalized, but a player's message is matched token by
     * token, so a pattern token has to be able to recognise any spelling of itself. Expanding the
     * canonical back to its aliases here lets phrase matching compare against the whole class
     * without re-stemming the query.
     */
    public List<String> aliasesOf(String canonical) {
        return canonicalToAliases.getOrDefault(canonical, Collections.emptyList());
    }

    public boolean isEmpty() {
        return stemToCanonical.isEmpty();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Accumulates synonym classes into a flat stem→canonical map. Aliases and their canonical are
     * stemmed via {@link Normalizer#stemToken(String)} (never synonym-expanded — that would recurse),
     * so authors may write natural surface forms. A stem mapping to a second, different canonical is
     * recorded as a conflict and the first mapping wins (mirrors MCA's first-wins datapack merge).
     */
    public static final class Builder {
        private final Map<String, String> map = new HashMap<>();
        private final Map<String, String> conflicts = new HashMap<>();

        /** Adds one class: every alias (and the canonical key itself) resolves to the canonical stem. */
        public Builder addClass(String canonicalWord, Iterable<String> aliasWords) {
            String canonical = Normalizer.stemToken(canonicalWord);
            if (canonical.isEmpty()) {
                return this;
            }
            put(canonical, canonical);
            for (String alias : aliasWords) {
                String aliasStem = Normalizer.stemToken(alias);
                if (!aliasStem.isEmpty()) {
                    put(aliasStem, canonical);
                }
            }
            return this;
        }

        private void put(String stem, String canonical) {
            String existing = map.get(stem);
            if (existing != null) {
                if (!existing.equals(canonical)) {
                    conflicts.putIfAbsent(stem, existing + " vs " + canonical);
                }
                return; // first-wins
            }
            map.put(stem, canonical);
        }

        /** Stems whose synonym class is ambiguous (mapped to two canonicals). Empty when clean. */
        public Map<String, String> conflicts() {
            return Collections.unmodifiableMap(conflicts);
        }

        public SynonymTable build() {
            return new SynonymTable(new HashMap<>(map));
        }
    }
}
