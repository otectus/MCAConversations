package dev.otectus.mcaconversations.chat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Edit-distance + Jaro-Winkler behavior underpinning §6.5 typo tolerance and §5 name matching. */
class FuzzyTest {

    // --- Damerau-Levenshtein ---------------------------------------------------

    @Test
    void identicalStringsAreDistanceZero() {
        assertEquals(0, Fuzzy.damerauLevenshtein("work", "work", 2));
    }

    @Test
    void singleSubstitutionIsOne() {
        assertEquals(1, Fuzzy.damerauLevenshtein("work", "wark", 2));
        assertEquals(1, Fuzzy.damerauLevenshtein("rain", "main", 2));
    }

    @Test
    void singleInsertionAndDeletionAreOne() {
        assertEquals(1, Fuzzy.damerauLevenshtein("weather", "weathe", 2));   // deletion
        assertEquals(1, Fuzzy.damerauLevenshtein("weathe", "weather", 2));   // insertion
    }

    @Test
    void adjacentTranspositionIsOneNotTwo() {
        assertEquals(1, Fuzzy.damerauLevenshtein("weather", "wetaher", 2));  // th->ht transposition
        assertEquals(1, Fuzzy.damerauLevenshtein("form", "from", 2));
    }

    @Test
    void distanceBeyondBoundReturnsBoundPlusOne() {
        // "work" -> "fork" is 1, but a length gap or many edits should overflow cleanly.
        assertEquals(2, Fuzzy.damerauLevenshtein("work", "planks", 1)); // gap > bound -> bound+1
        assertTrue(Fuzzy.damerauLevenshtein("village", "kitchen", 2) > 2);
    }

    @Test
    void earlyExitStillReportsExactWhenWithinBound() {
        assertEquals(2, Fuzzy.damerauLevenshtein("rumor", "armor", 3));
    }

    // --- typoMatches predicate -------------------------------------------------

    @Test
    void typoMatchesAcceptsWithinTolerance() {
        assertTrue(Fuzzy.typoMatches("wether", "weather"));   // len>=7 keyword, dist 1
        assertTrue(Fuzzy.typoMatches("rumer", "rumor"));      // len 5, dist 1
        assertTrue(Fuzzy.typoMatches("vilage", "village"));   // dist 1
    }

    @Test
    void typoMatchesRejectsShortTokens() {
        assertFalse(Fuzzy.typoMatches("job", "jab"));  // shorter length < 4
        assertFalse(Fuzzy.typoMatches("cat", "car"));
    }

    @Test
    void typoMatchesRequiresEqualFirstLetter() {
        assertFalse(Fuzzy.typoMatches("fork", "work")); // one edit apart but different initial
    }

    @Test
    void typoMatchesRejectsBeyondLengthScaledBound() {
        assertFalse(Fuzzy.typoMatches("work", "world")); // len 4..6 -> bound 1, distance 2
    }

    // --- Jaro-Winkler ----------------------------------------------------------

    @Test
    void jaroWinklerIdenticalIsOne() {
        assertEquals(1.0, Fuzzy.jaroWinkler("agnes", "agnes"), 1e-9);
    }

    @Test
    void jaroWinklerRewardsSharedPrefixTypo() {
        assertTrue(Fuzzy.jaroWinkler("agnes", "anges") >= 0.90, "transposed name should stay >= 0.90");
        assertTrue(Fuzzy.jaroWinkler("agnes", "agness") >= 0.90, "trailing insertion should stay >= 0.90");
    }

    @Test
    void jaroWinklerSeparatesUnrelatedShortWords() {
        assertTrue(Fuzzy.jaroWinkler("agnes", "agent") < 0.90, "different name must fall below the 0.90 gate");
    }

    @Test
    void jaroWinklerEmptyHandling() {
        assertEquals(1.0, Fuzzy.jaroWinkler("", ""), 1e-9);
        assertEquals(0.0, Fuzzy.jaroWinkler("", "agnes"), 1e-9);
    }
}
