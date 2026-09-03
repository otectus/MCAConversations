package dev.otectus.mcaconversations.identity;

import java.util.Locale;
import java.util.Optional;

/**
 * The eight kinds of stable anchor a villager profile carries (spec §6.3).
 *
 * <p>Each family answers a different question, and the split is what keeps the profile from becoming
 * a personality inventory. An <b>interest</b> makes a subject available; a <b>value</b> decides how a
 * disagreement resolves; a <b>comfort</b> supplies a recovery beat; an <b>aversion</b> draws a
 * boundary; the three <b>styles</b> shape route rhythm rather than adjectives; an <b>origin motif</b>
 * unlocks life history. None of them is a trait, a diagnosis, or a score.
 *
 * <p>{@link #cap} is the hard number of tokens of this family one villager may hold. Two interests and
 * two values are enough to make selection distinctive across thousands of villagers; more would make
 * every villager interested in everything, which is the same as none of them being interesting.
 */
public enum IdentityFamily {

    /** Optional topic availability, examples, and questions asked back. */
    INTEREST("interest", 2),
    /** Scene weighting and how disagreements resolve. */
    VALUE("value", 2),
    /** Positive small talk and recovery beats. */
    COMFORT("comfort", 1),
    /** Boundaries, humour fit, and low-stakes conflict. */
    AVERSION("aversion", 1),
    /** Which profession subjects recur and what kind of help is welcome. */
    WORK_STYLE("work_style", 1),
    /** Initiative and named-person scene weighting. */
    SOCIAL_STYLE("social_style", 1),
    /** Route shape — how disclosure is paced — not an adjective in prose. */
    DISCLOSURE_STYLE("disclosure_style", 1),
    /** Life and history scenes, only when compatible with observed residency. */
    ORIGIN_MOTIF("origin_motif", 1);

    private final String key;
    private final int cap;

    IdentityFamily(String key, int cap) {
        this.key = key;
        this.cap = cap;
    }

    public String key() {
        return key;
    }

    /** How many tokens of this family a single villager may hold. */
    public int cap() {
        return cap;
    }

    /** True when this family holds several tokens, so the record stores a set rather than a value. */
    public boolean isMulti() {
        return cap > 1;
    }

    public static Optional<IdentityFamily> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (IdentityFamily family : values()) {
            if (family.key.equals(normalized)) {
                return Optional.of(family);
            }
        }
        return Optional.empty();
    }
}
