package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.Normalizer.NormalizedMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The §6.2 pipeline: folding, contractions, stemming, negation windows, synonym canonicalization. */
class NormalizerTest {

    private static List<String> stems(String raw) {
        NormalizedMessage m = Normalizer.normalize(raw, SynonymTable.EMPTY);
        return m.tokens.stream().map(t -> t.stem).toList();
    }

    // --- Folding ---------------------------------------------------------------

    @Test
    void foldLowercasesStripsDiacriticsAndCollapsesRepeats() {
        assertEquals("cafe", Normalizer.fold("Café"));
        assertEquals("heyy", Normalizer.fold("heyyyy"));
        assertEquals("hello", Normalizer.fold("HELLO"));
    }

    @Test
    void curlyQuotesAndDashesFoldToAscii() {
        assertTrue(Normalizer.fold("don’t").contains("don't"));
        assertTrue(Normalizer.fold("well — fine").contains("-"));
    }

    // --- Contractions ----------------------------------------------------------

    @Test
    void contractionsExpandBeforeTokenizing() {
        assertEquals(List.of("how", "is", "your", "day", "going"), stems("how's your day going?"));
        assertEquals(List.of("do", "not", "know"), stems("dont know"));
        assertEquals(List.of("i", "am", "here"), stems("im here"));
    }

    // --- Stemming --------------------------------------------------------------

    @Test
    void pluralStemming() {
        assertEquals("storm", Normalizer.stem("storms"));
        assertEquals("scare", Normalizer.stem("scares"));
        assertEquals("wish", Normalizer.stem("wishes"));
        assertEquals("party", Normalizer.stem("parties"));
        assertEquals("box", Normalizer.stem("boxes"));
    }

    @Test
    void doesNotStripDoubleSOrShortWords() {
        assertEquals("kiss", Normalizer.stem("kiss"));
        assertEquals("job", Normalizer.stem("job"));   // < 4 chars untouched
    }

    @Test
    void verbSuffixStemmingChainsAfterPlural() {
        assertEquals("feel", Normalizer.stem("feelings")); // -s then -ing
        assertEquals("feel", Normalizer.stem("feeling"));
        assertEquals("farm", Normalizer.stem("farming"));
        assertEquals("have", Normalizer.stem("having"));   // silent-e exception
    }

    @Test
    void shortIngIsLeftAlone() {
        assertEquals("going", Normalizer.stem("going")); // len 5 < 6 guard
    }

    // --- Negation windows ------------------------------------------------------

    @Test
    void negatorTagsFollowingContentTokens() {
        NormalizedMessage m = Normalizer.normalize("I don't like the rain", SynonymTable.EMPTY);
        assertTrue(m.negatedStems.contains("rain"), "rain follows the negator → negated");
        assertTrue(m.negatedStems.contains("like"));
        assertFalse(m.contentStems.contains("rain"), "a negated stem is not positive keyword evidence");
    }

    @Test
    void conjunctionBreaksTheNegationWindow() {
        NormalizedMessage m = Normalizer.normalize("not happy but sad", SynonymTable.EMPTY);
        assertTrue(m.negatedStems.contains("happy"));
        assertTrue(m.contentStems.contains("sad"), "conjunction ends the window before 'sad'");
    }

    // --- Bigrams & interrogative ----------------------------------------------

    @Test
    void bigramsSkipStopwordsAndCarryOrder() {
        NormalizedMessage m = Normalizer.normalize("how's your day going?", SynonymTable.EMPTY);
        assertTrue(m.bigrams.contains("your day"));
        assertTrue(m.bigrams.contains("day going"));
    }

    @Test
    void interrogativeDetectedByQuestionMarkOrLeadingWord() {
        assertTrue(Normalizer.normalize("the weather?", SynonymTable.EMPTY).interrogative);
        assertTrue(Normalizer.normalize("what do you do", SynonymTable.EMPTY).interrogative);
        assertFalse(Normalizer.normalize("the weather is nice", SynonymTable.EMPTY).interrogative);
    }

    // --- Synonyms --------------------------------------------------------------

    @Test
    void synonymCanonicalizationAppliesAtQueryTime() {
        SynonymTable syn = SynonymTable.builder()
                .addClass("work", List.of("job", "trade", "profession"))
                .build();
        assertEquals(List.of("work"), Normalizer.canonicalStems("job", syn));
        assertEquals(List.of("work"), Normalizer.canonicalStems("trade", syn));
        NormalizedMessage m = Normalizer.normalize("do you like your job", syn);
        assertTrue(m.contentStems.contains("work"), "job canonicalizes to work");
    }

    @Test
    void maxTokenCapIsEnforced() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb.append("word ");
        }
        assertTrue(Normalizer.normalize(sb.toString(), SynonymTable.EMPTY).tokens.size() <= 32);
    }
}
