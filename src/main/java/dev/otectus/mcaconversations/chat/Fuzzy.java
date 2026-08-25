package dev.otectus.mcaconversations.chat;

/**
 * Pure string-distance utilities for typo absorption (spec §6.5). Two metrics, one home:
 *
 * <ul>
 *   <li><b>Banded Damerau-Levenshtein</b> — edit distance with adjacent transposition, early-exiting
 *       once the running minimum of a row exceeds {@code maxDistance}. Used for message-token vs
 *       keyword-stem fuzzing (insert/delete/substitute/transpose all weight 1).</li>
 *   <li><b>Jaro-Winkler</b> — prefix-weighted similarity in {@code [0,1]}. Used for villager-name
 *       matching (§5): names are short, transposition-prone, and share leading characters, which
 *       Winkler's prefix bonus rewards.</li>
 * </ul>
 *
 * No Minecraft or MCA imports — unit-testable without a bootstrap (a ✦ class).
 */
public final class Fuzzy {

    private Fuzzy() {
    }

    /**
     * Damerau-Levenshtein distance (adjacent transpositions) with a per-row early exit. Returns the
     * true edit distance when it is {@code <= maxDistance}, otherwise exactly {@code maxDistance + 1}
     * (callers only compare against the bound). The early exit — bail once an entire row's minimum
     * exceeds {@code maxDistance} — keeps this cheap; on the short words fuzzed here (both under a
     * dozen characters) the full matrix is a few hundred operations regardless.
     */
    public static int damerauLevenshtein(String a, String b, int maxDistance) {
        if (a == null || b == null) {
            return maxDistance + 1;
        }
        if (a.equals(b)) {
            return 0;
        }
        int la = a.length();
        int lb = b.length();
        // A length gap alone already exceeds the bound — cheap reject before allocating.
        if (Math.abs(la - lb) > maxDistance) {
            return maxDistance + 1;
        }
        if (la == 0) {
            return lb;
        }
        if (lb == 0) {
            return la;
        }

        int[] prevPrev = new int[lb + 1]; // row i-2 (for transposition)
        int[] prev = new int[lb + 1];     // row i-1
        int[] curr = new int[lb + 1];     // row i

        for (int j = 0; j <= lb; j++) {
            prev[j] = j;
        }

        for (int i = 1; i <= la; i++) {
            curr[0] = i;
            int rowMin = curr[0];
            char ca = a.charAt(i - 1);
            for (int j = 1; j <= lb; j++) {
                char cb = b.charAt(j - 1);
                int cost = (ca == cb) ? 0 : 1;
                int best = Math.min(Math.min(prev[j] + 1, curr[j - 1] + 1), prev[j - 1] + cost);
                if (i > 1 && j > 1 && ca == b.charAt(j - 2) && a.charAt(i - 2) == cb) {
                    best = Math.min(best, prevPrev[j - 2] + 1); // adjacent transposition
                }
                curr[j] = best;
                if (best < rowMin) {
                    rowMin = best;
                }
            }
            if (rowMin > maxDistance) {
                return maxDistance + 1; // every alignment through this row already exceeds the bound
            }
            int[] tmp = prevPrev;
            prevPrev = prev;
            prev = curr;
            curr = tmp;
        }
        return Math.min(prev[lb], maxDistance + 1);
    }

    /**
     * The §6.5 fuzzy-match predicate for a message stem against a keyword stem: first letters must be
     * equal (initials are rarely mistyped and this keeps {@code work != fork}), and the edit distance
     * must be within a length-scaled bound — {@code <= 1} for the shorter of the two lengths in 4..6,
     * {@code <= 2} for length {@code >= 7}. Tokens shorter than 4 never fuzzy-match (return false).
     */
    public static boolean typoMatches(String messageStem, String keywordStem) {
        if (messageStem == null || keywordStem == null) {
            return false;
        }
        int shorter = Math.min(messageStem.length(), keywordStem.length());
        if (shorter < 4) {
            return false;
        }
        if (messageStem.charAt(0) != keywordStem.charAt(0)) {
            return false;
        }
        int bound = (Math.max(messageStem.length(), keywordStem.length()) >= 7) ? 2 : 1;
        return damerauLevenshtein(messageStem, keywordStem, bound) <= bound;
    }

    /**
     * Jaro-Winkler similarity in {@code [0,1]} (1.0 = identical). Standard Jaro with Winkler's prefix
     * boost (prefix length capped at 4, scaling factor 0.1). Case-sensitive — callers lowercase names
     * first. Empty-vs-empty is 1.0; empty-vs-nonempty is 0.0.
     */
    public static double jaroWinkler(String a, String b) {
        if (a == null || b == null) {
            return 0.0;
        }
        if (a.equals(b)) {
            return 1.0;
        }
        int la = a.length();
        int lb = b.length();
        if (la == 0 || lb == 0) {
            return 0.0;
        }

        int matchWindow = Math.max(la, lb) / 2 - 1;
        if (matchWindow < 0) {
            matchWindow = 0;
        }
        boolean[] aMatched = new boolean[la];
        boolean[] bMatched = new boolean[lb];
        int matches = 0;
        for (int i = 0; i < la; i++) {
            int start = Math.max(0, i - matchWindow);
            int end = Math.min(i + matchWindow + 1, lb);
            for (int j = start; j < end; j++) {
                if (bMatched[j] || a.charAt(i) != b.charAt(j)) {
                    continue;
                }
                aMatched[i] = true;
                bMatched[j] = true;
                matches++;
                break;
            }
        }
        if (matches == 0) {
            return 0.0;
        }

        double transpositions = 0;
        int k = 0;
        for (int i = 0; i < la; i++) {
            if (!aMatched[i]) {
                continue;
            }
            while (!bMatched[k]) {
                k++;
            }
            if (a.charAt(i) != b.charAt(k)) {
                transpositions++;
            }
            k++;
        }
        transpositions /= 2.0;

        double m = matches;
        double jaro = (m / la + m / lb + (m - transpositions) / m) / 3.0;

        int prefix = 0;
        int maxPrefix = Math.min(4, Math.min(la, lb));
        while (prefix < maxPrefix && a.charAt(prefix) == b.charAt(prefix)) {
            prefix++;
        }
        return jaro + prefix * 0.1 * (1.0 - jaro);
    }
}
