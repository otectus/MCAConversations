package dev.otectus.mcaconversations.context;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The fingerprint's whole job is to say "the world is the same" and "the world has changed" and never
 * to confuse the two. These are the ways the old {@code String.valueOf} encoding got that wrong.
 */
class ContextFingerprintTest {

    private static ContextFingerprint of(ContextKey<?> key, Object value) {
        Map<ContextKey<?>, ContextValue<?>> values = new LinkedHashMap<>();
        values.put(key, ContextValue.known(value));
        return ContextFingerprint.of(values);
    }

    @Test
    void aSetHashesTheSameWhateverOrderItWasBuiltIn() {
        // Two providers reading the same three interests off the same profile can hand back two
        // HashSets that iterate differently. Before this they produced different fingerprints, so a
        // plan invalidated itself mid-conversation for a change that had not happened.
        Set<String> forwards = new LinkedHashSet<>(List.of("bees", "roofs", "weather"));
        Set<String> backwards = new LinkedHashSet<>(List.of("weather", "bees", "roofs"));
        assertEquals(of(ContextKeys.IDENTITY_INTERESTS, forwards),
                of(ContextKeys.IDENTITY_INTERESTS, backwards));
    }

    @Test
    void aListKeepsItsOrderBecauseTheOrderIsTheFact() {
        // Recent subjects are newest-first; two lists with the same members in a different order are
        // two different conversations.
        assertNotEquals(of(ContextKeys.NARRATIVE_RECENT_SUBJECTS, List.of("work", "family")),
                of(ContextKeys.NARRATIVE_RECENT_SUBJECTS, List.of("family", "work")));
    }

    @Test
    void aStatusCannotAliasALiteralSpelledTheSameWay() {
        Map<ContextKey<?>, ContextValue<?>> literal = new LinkedHashMap<>();
        literal.put(ContextKeys.PLACE_VILLAGE_NAME, ContextValue.known("UNKNOWN"));
        Map<ContextKey<?>, ContextValue<?>> unknown = new LinkedHashMap<>();
        unknown.put(ContextKeys.PLACE_VILLAGE_NAME, ContextValue.unknown());

        assertNotEquals(ContextFingerprint.of(literal), ContextFingerprint.of(unknown));
        assertEquals("str:UNKNOWN", ContextValue.known("UNKNOWN").token());
        assertEquals("status:UNKNOWN", ContextValue.unknown().token());
        assertEquals("status:UNAVAILABLE", ContextValue.unavailable().token());
    }

    @Test
    void numbersEncodeIdenticallyUnderAnyDefaultLocale() {
        Locale original = Locale.getDefault();
        try {
            Locale.setDefault(Locale.US);
            String usLong = ContextValue.known(1234567L).token();
            String usDouble = ContextValue.known(1234.5d).token();
            // Turkish lowercases 'I' to a dotless one and German writes a decimal comma; a token built
            // with String.format would differ between a German client and an English server.
            Locale.setDefault(Locale.GERMANY);
            assertEquals(usLong, ContextValue.known(1234567L).token());
            assertEquals(usDouble, ContextValue.known(1234.5d).token());
            Locale.setDefault(Locale.forLanguageTag("tr"));
            assertEquals(usLong, ContextValue.known(1234567L).token());
            assertEquals(usDouble, ContextValue.known(1234.5d).token());
            assertEquals("int:1234567", usLong);
            assertEquals("num:1234.5", usDouble);
        } finally {
            Locale.setDefault(original);
        }
    }

    @Test
    void nestedCollectionsSortAtEveryLevel() {
        Set<Object> outerA = new LinkedHashSet<>();
        outerA.add(new LinkedHashSet<>(List.of("b", "a")));
        outerA.add(new LinkedHashSet<>(List.of("d", "c")));
        Set<Object> outerB = new LinkedHashSet<>();
        outerB.add(new LinkedHashSet<>(List.of("c", "d")));
        outerB.add(new LinkedHashSet<>(List.of("a", "b")));
        assertEquals(ContextValue.known(outerA).token(), ContextValue.known(outerB).token());

        // A nested list inside a set still keeps its own order.
        assertNotEquals(ContextValue.known(Set.of(List.of("a", "b"))).token(),
                ContextValue.known(Set.of(List.of("b", "a"))).token());
    }

    @Test
    void aValueCannotForgeADelimiterAndCollideWithADifferentShape() {
        // "a,b" as one string and {"a","b"} as two are different facts, and stay different.
        assertNotEquals(ContextValue.known(Set.of("a,b")).token(),
                ContextValue.known(Set.of("a", "b")).token());
        assertTrue(ContextValue.known("a,b").token().startsWith("str:"));
    }

    @Test
    void volatileFieldsStayOutOfTheDigest() {
        Map<ContextKey<?>, ContextValue<?>> midday = new LinkedHashMap<>();
        midday.put(ContextKeys.TIME_BAND, ContextValue.known("midday"));
        Map<ContextKey<?>, ContextValue<?>> dusk = new LinkedHashMap<>();
        dusk.put(ContextKeys.TIME_BAND, ContextValue.known("dusk"));
        assertEquals(ContextFingerprint.of(midday), ContextFingerprint.of(dusk));
    }
}
