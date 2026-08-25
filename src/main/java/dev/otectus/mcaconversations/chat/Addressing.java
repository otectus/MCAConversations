package dev.otectus.mcaconversations.chat;

import java.util.List;

/**
 * Target resolution (spec §5), all four tiers in strict precedence <b>1 &gt; 2 &gt; 3 &gt; 4</b>:
 * <b>tier 1</b> explicit name vocative (leading {@code "Agnes, ..."} or trailing {@code "..., Agnes?"});
 * <b>tier 2</b> stickiness (the last conversation partner keeps answering follow-ups without being
 * re-addressed); <b>tier 3</b> look-at (the villager inside the player's view cone); <b>tier 4</b>
 * nearest candidate (ambient).
 *
 * <p>Pure ✦: resolves over the candidate <em>names</em> (nearest-first, as {@code VillagerFinder}
 * sorts them), the per-candidate look cosines, and a pre-resolved sticky index — no Minecraft or MCA
 * types. A leading vocative without a comma must match a name <em>exactly</em> (first token or full
 * name); a comma-delimited vocative additionally tolerates a Jaro-Winkler ≥ 0.90 typo (so
 * {@code "Agnes,"} accepts {@code "Anges,"} but bare {@code "same here"} never grabs a villager named
 * Sam). Tiers 1–3 are <em>directed</em> (single responder, addressed threshold, graduated confusion);
 * tier 4 is ambient.
 */
public final class Addressing {

    private static final double NAME_FUZZY_THRESHOLD = 0.90;

    /**
     * Interjections that commonly precede a vocative ({@code "Hey Coralia! ..."}). Deliberately a
     * small static set (pure class — independent of the synonym datapack); the greeting word itself
     * already signals address, so the following name is matched with typo tolerance.
     */
    private static final java.util.Set<String> GREETING_PREFIXES = java.util.Set.of(
            "hey", "hi", "hello", "hiya", "heya", "yo", "howdy", "greetings", "oi");

    /**
     * Everyday words that must never fuzzy-match a villager's name. Without this, a reply like
     * {@code "yes"} or {@code "thanks"} scores ≥ 0.90 Jaro-Winkler against a short name and silently
     * re-addresses a villager the player was not talking to. Checked both raw and stemmed, and
     * combined with a caller-supplied {@code reserved} set (the loaded intent vocabulary), so words
     * the datapack treats as meaningful never double as names either.
     */
    private static final java.util.Set<String> COMMON_WORDS = java.util.Set.of(
            "yes", "no", "ok", "okay", "same", "sure", "thanks", "thank", "yeah", "yep", "nope",
            "nah", "guys", "guy", "everyone", "everybody", "folks", "all", "lads");

    private Addressing() {
    }

    /**
     * @param targetIndex index into the candidate list, or -1 if there is no candidate at all
     * @param message     the message with any matched vocative stripped (tiers 2–4 leave it intact)
     * @param named       true iff resolved by an explicit name vocative (tier 1) — a name address
     *                    breaks and re-sets stickiness
     * @param directed    true for tiers 1–3 (a single villager is meant to answer, uses the addressed
     *                    threshold and the graduated-confusion ladder); false for tier 4 (ambient)
     * @param greeting    true iff the vocative was greeting-prefixed ({@code "Hey Coralia"}), so the
     *                    dispatcher can answer the greeting itself rather than treat the remainder
     *                    as the whole message
     */
    public record Address(int targetIndex, String message, boolean named, boolean directed,
                          boolean greeting) {
        Address(int targetIndex, String message, boolean named, boolean directed) {
            this(targetIndex, message, named, directed, false);
        }
    }

    /** Two-arg convenience: name vocative (tier 1) then nearest (tier 4); no sticky/look context. */
    public static Address resolve(String rawMessage, List<String> candidateNames) {
        return resolve(rawMessage, candidateNames, List.of(), -1, Double.NaN);
    }

    /**
     * Full four-tier resolution.
     *
     * @param lookDots     per-candidate cosine of the angle to the player's view vector (same order as
     *                     {@code candidateNames}); empty to disable look-at
     * @param stickyIndex  index of the still-in-range sticky partner in the candidate list, or -1
     * @param lookConeCos  {@code cos(coneHalfAngle)}; a candidate qualifies for tier 3 when its look
     *                     cosine is ≥ this. {@code NaN} disables look-at (config cone = 0)
     */
    public static Address resolve(String rawMessage, List<String> candidateNames,
                                  List<Double> lookDots, int stickyIndex, double lookConeCos) {
        return resolve(rawMessage, candidateNames, lookDots, stickyIndex, lookConeCos, java.util.Set.of());
    }

    /**
     * As above, plus {@code reserved}: words the loaded intent vocabulary treats as meaningful.
     * They are excluded from fuzzy name matching so a content word can never be mistaken for a
     * vocative.
     */
    public static Address resolve(String rawMessage, List<String> candidateNames,
                                  List<Double> lookDots, int stickyIndex, double lookConeCos,
                                  java.util.Set<String> reserved) {
        String s = rawMessage == null ? "" : rawMessage.strip();
        if (candidateNames == null || candidateNames.isEmpty()) {
            return new Address(-1, s, false, false);
        }

        // Tier 1: explicit name vocative.
        Address leading = tryLeading(s, candidateNames, reserved);
        if (leading != null) {
            return leading;
        }
        Address greeted = tryGreetingPrefix(s, candidateNames, reserved);
        if (greeted != null) {
            return greeted;
        }
        Address trailing = tryTrailing(s, candidateNames, reserved);
        if (trailing != null) {
            return trailing;
        }

        // Tier 2: stickiness — the last partner answers a follow-up without being re-named.
        if (stickyIndex >= 0 && stickyIndex < candidateNames.size()) {
            return new Address(stickyIndex, s, false, true);
        }

        // Tier 3: look-at — the villager most centered in the player's view cone.
        int looked = bestInCone(lookDots, candidateNames.size(), lookConeCos);
        if (looked >= 0) {
            return new Address(looked, s, false, true);
        }

        // Tier 4: nearest candidate answers; ambient, not an explicit address.
        return new Address(0, s, false, false);
    }

    /** Index of the candidate with the greatest look cosine at or above the cone, or -1. */
    private static int bestInCone(List<Double> lookDots, int candidateCount, double lookConeCos) {
        if (lookDots == null || lookDots.isEmpty() || Double.isNaN(lookConeCos)) {
            return -1;
        }
        int best = -1;
        double bestDot = -Double.MAX_VALUE;
        int n = Math.min(candidateCount, lookDots.size());
        for (int i = 0; i < n; i++) {
            double d = lookDots.get(i);
            if (d >= lookConeCos && d > bestDot) {
                best = i;
                bestDot = d;
            }
        }
        return best;
    }

    private static Address tryLeading(String s, List<String> names, java.util.Set<String> reserved) {
        int i = 0;
        while (i < s.length() && isWordChar(s.charAt(i))) {
            i++;
        }
        if (i == 0) {
            return null; // message does not start with a word
        }
        String lead = s.substring(0, i);
        char sep = i < s.length() ? s.charAt(i) : '\0';
        boolean comma = sep == ',' || sep == ':' || sep == ';';
        int j = i;
        while (j < s.length() && !isWordChar(s.charAt(j))) {
            j++;
        }
        // A single-word message ("Nayaliya?") is almost certainly a call by name — tolerate a typo,
        // unless the word is itself a greeting ("hello?" must never fuzzy-grab a villager named Hella).
        boolean bareWord = j >= s.length();
        boolean allowFuzzy = comma
                || (bareWord && !GREETING_PREFIXES.contains(lead.toLowerCase(java.util.Locale.ROOT)));

        int target = firstMatch(lead, names, allowFuzzy, reserved);
        if (target < 0) {
            return null;
        }
        return new Address(target, s.substring(j).strip(), true, true);
    }

    /**
     * {@code "Hey Coralia! ..."}: a greeting interjection followed by a name is a vocative — the
     * greeting word already signals address, so the name is matched with typo tolerance. Both tokens
     * are stripped from the message.
     */
    private static Address tryGreetingPrefix(String s, List<String> names, java.util.Set<String> reserved) {
        int i = 0;
        while (i < s.length() && isWordChar(s.charAt(i))) {
            i++;
        }
        if (i == 0 || !GREETING_PREFIXES.contains(s.substring(0, i).toLowerCase(java.util.Locale.ROOT))) {
            return null;
        }
        int j = i;
        while (j < s.length() && !isWordChar(s.charAt(j))) {
            j++;
        }
        int k = j;
        while (k < s.length() && isWordChar(s.charAt(k))) {
            k++;
        }
        if (k == j) {
            return null; // just a greeting, no second word — plain "hello" stays a greeting
        }
        int target = firstMatch(s.substring(j, k), names, true, reserved);
        if (target < 0) {
            return null;
        }
        int rest = k;
        while (rest < s.length() && !isWordChar(s.charAt(rest))) {
            rest++;
        }
        return new Address(target, s.substring(rest).strip(), true, true, true);
    }

    private static Address tryTrailing(String s, List<String> names, java.util.Set<String> reserved) {
        int end = s.length();
        while (end > 0 && !isWordChar(s.charAt(end - 1))) {
            end--; // trim trailing punctuation/space
        }
        int start = end;
        while (start > 0 && isWordChar(s.charAt(start - 1))) {
            start--;
        }
        if (start == end || start == 0) {
            return null; // no trailing word, or the whole message is one word (handled by leading)
        }
        int p = start - 1;
        while (p >= 0 && s.charAt(p) == ' ') {
            p--;
        }
        if (p < 0 || s.charAt(p) != ',') {
            return null; // a trailing vocative must be comma-delimited
        }
        String trail = s.substring(start, end);
        int target = firstMatch(trail, names, true, reserved);
        if (target < 0) {
            return null;
        }
        return new Address(target, s.substring(0, p).strip(), true, true);
    }

    /** First candidate (nearest-first) whose name matches {@code word}; -1 if none. */
    private static int firstMatch(String word, List<String> names, boolean allowFuzzy,
                                  java.util.Set<String> reserved) {
        for (int idx = 0; idx < names.size(); idx++) {
            if (nameMatches(word, names.get(idx), allowFuzzy, reserved)) {
                return idx;
            }
        }
        return -1;
    }

    private static boolean nameMatches(String word, String name, boolean allowFuzzy,
                                       java.util.Set<String> reserved) {
        if (name == null || name.isBlank()) {
            return false;
        }
        String w = word.toLowerCase(java.util.Locale.ROOT);
        String full = name.toLowerCase(java.util.Locale.ROOT).strip();
        String first = full.split("\\s+")[0];
        if (w.equals(full) || w.equals(first)) {
            return true;
        }
        if (!allowFuzzy || isReserved(w, reserved)) {
            return false;
        }
        return w.length() >= 3 && Fuzzy.jaroWinkler(w, first) >= NAME_FUZZY_THRESHOLD;
    }

    /** True when a word is too ordinary to be a vocative (checked raw and stemmed). */
    private static boolean isReserved(String lowerWord, java.util.Set<String> reserved) {
        if (COMMON_WORDS.contains(lowerWord) || reserved.contains(lowerWord)) {
            return true;
        }
        String stem = Normalizer.stemToken(lowerWord);
        return COMMON_WORDS.contains(stem) || reserved.contains(stem);
    }

    private static boolean isWordChar(char c) {
        return Character.isLetterOrDigit(c) || c == '\'';
    }
}
