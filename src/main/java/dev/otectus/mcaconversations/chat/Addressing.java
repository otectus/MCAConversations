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

    private Addressing() {
    }

    /**
     * @param targetIndex index into the candidate list, or -1 if there is no candidate at all
     * @param message     the message with any matched vocative stripped (tiers 2–4 leave it intact)
     * @param named       true iff resolved by an explicit name vocative (tier 1) — a name address
     *                    breaks and re-sets stickiness
     * @param directed    true for tiers 1–3 (a single villager is meant to answer, uses the addressed
     *                    threshold and the graduated-confusion ladder); false for tier 4 (ambient)
     */
    public record Address(int targetIndex, String message, boolean named, boolean directed) {
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
        String s = rawMessage == null ? "" : rawMessage.strip();
        if (candidateNames == null || candidateNames.isEmpty()) {
            return new Address(-1, s, false, false);
        }

        // Tier 1: explicit name vocative.
        Address leading = tryLeading(s, candidateNames);
        if (leading != null) {
            return leading;
        }
        Address trailing = tryTrailing(s, candidateNames);
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

    private static Address tryLeading(String s, List<String> names) {
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

        int target = firstMatch(lead, names, comma);
        if (target < 0) {
            return null;
        }
        int j = i;
        while (j < s.length() && !isWordChar(s.charAt(j))) {
            j++;
        }
        return new Address(target, s.substring(j).strip(), true, true);
    }

    private static Address tryTrailing(String s, List<String> names) {
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
        int target = firstMatch(trail, names, true);
        if (target < 0) {
            return null;
        }
        return new Address(target, s.substring(0, p).strip(), true, true);
    }

    /** First candidate (nearest-first) whose name matches {@code word}; -1 if none. */
    private static int firstMatch(String word, List<String> names, boolean allowFuzzy) {
        for (int idx = 0; idx < names.size(); idx++) {
            if (nameMatches(word, names.get(idx), allowFuzzy)) {
                return idx;
            }
        }
        return -1;
    }

    private static boolean nameMatches(String word, String name, boolean allowFuzzy) {
        if (name == null || name.isBlank()) {
            return false;
        }
        String w = word.toLowerCase(java.util.Locale.ROOT);
        String full = name.toLowerCase(java.util.Locale.ROOT).strip();
        String first = full.split("\\s+")[0];
        if (w.equals(full) || w.equals(first)) {
            return true;
        }
        return allowFuzzy && w.length() >= 3 && Fuzzy.jaroWinkler(w, first) >= NAME_FUZZY_THRESHOLD;
    }

    private static boolean isWordChar(char c) {
        return Character.isLetterOrDigit(c) || c == '\'';
    }
}
