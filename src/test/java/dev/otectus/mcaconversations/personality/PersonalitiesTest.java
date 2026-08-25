package dev.otectus.mcaconversations.personality;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The canonical roster, its aliases, and the parse-safe condition built on them. */
class PersonalitiesTest {

    @Test
    void rosterMatchesMca77Exactly() {
        // Read off `javap forge.net.mca.entity.ai.relationship.Personality` for
        // 7.7.0-beta.2, minus UNASSIGNED (a sentinel, never rolled onto a villager).
        assertEquals(16, Personalities.CANONICAL.size());
        for (String p : new String[]{"confident", "peppy", "friendly", "flirty", "playful", "gloomy",
                "sensitive", "greedy", "odd", "crabby", "extroverted", "introverted", "relaxed",
                "anxious", "peaceful", "upbeat"}) {
            assertTrue(Personalities.CANONICAL.contains(p), "roster missing " + p);
        }
        assertFalse(Personalities.CANONICAL.contains("unassigned"));
        // Renamed away in 7.7 — must not reappear as canonical.
        for (String gone : new String[]{"witty", "shy", "lazy", "grumpy", "athletic"}) {
            assertFalse(Personalities.CANONICAL.contains(gone), gone + " is not a 7.7 personality");
        }
    }

    @Test
    void normalizeAcceptsEverySpellingMcaCanProduce() {
        assertEquals("odd", Personalities.normalize("mca:odd"));   // 7.7 Personality.toString()
        assertEquals("odd", Personalities.normalize("ODD"));       // 7.6 enum toString()
        assertEquals("odd", Personalities.normalize("odd"));       // dialogue JSON
        assertEquals("odd", Personalities.normalize("  Odd  "));
        assertEquals("", Personalities.normalize(null));
        assertEquals("", Personalities.normalize("   "));
    }

    @Test
    void addonNamespacesKeepOnlyTheirPath() {
        // MCA 7.7 lets addons register namespaced personalities; the lang prefix is
        // ExtensibleTypeRegistry.translationSuffix, which drops the default namespace only.
        assertEquals("brave", Personalities.normalize("someaddon:brave"));
    }

    @Test
    void legacyIdsResolveToTheirSuccessor() {
        assertEquals("upbeat", Personalities.canonical("witty"));
        assertEquals("introverted", Personalities.canonical("shy"));
        assertEquals("relaxed", Personalities.canonical("lazy"));
        assertEquals("crabby", Personalities.canonical("grumpy"));
        assertEquals("upbeat", Personalities.canonical("mca:witty"));
        assertEquals("upbeat", Personalities.canonical("WITTY"));
    }

    @Test
    void athleticSurvivesAsLegacyOnlyAndIsNotCanonical() {
        // 7.7 turned athletic into the mca:athletic trait. It is still a *personality* on 7.6, so
        // its voice stays, but it must never be presented as a 7.7 personality.
        assertTrue(Personalities.LEGACY_ONLY.contains("athletic"));
        assertFalse(Personalities.isCanonical("athletic"));
        assertEquals("athletic", Personalities.canonical("athletic"));
        assertTrue(Personalities.overlayPrefixes().contains("athletic"));
    }

    @Test
    void overlayPrefixesCoverCanonicalAndLegacy() {
        assertEquals(21, Personalities.overlayPrefixes().size()); // 16 + 4 aliases + athletic
        assertTrue(Personalities.overlayPrefixes().containsAll(Personalities.CANONICAL));
        assertTrue(Personalities.overlayPrefixes().containsAll(Personalities.LEGACY_ALIASES.keySet()));
    }

    @Test
    void matchesBridgesBothMcaVersions() {
        // The whole point: one authored id matches a villager on either MCA version.
        assertTrue(Personalities.matches("upbeat", "mca:upbeat"));  // 7.7 villager
        assertTrue(Personalities.matches("upbeat", "WITTY"));       // same villager on 7.6
        assertTrue(Personalities.matches("witty", "mca:upbeat"));
        assertFalse(Personalities.matches("upbeat", "mca:odd"));
        assertFalse(Personalities.matches("", "mca:odd"));
        assertFalse(Personalities.matches("upbeat", null));
    }

    @Test
    void queryParsesStringAndArrayAndNeverThrows() {
        PersonalityQuery single = PersonalityQuery.fromJson(JsonParser.parseString("\"odd\""));
        assertNotNull(single);
        assertTrue(single.matches("mca:odd"));
        assertFalse(single.matches("mca:peppy"));

        PersonalityQuery many = PersonalityQuery.fromJson(JsonParser.parseString("[\"odd\",\"playful\"]"));
        assertNotNull(many);
        assertTrue(many.matches("mca:playful"));
        assertTrue(many.matches("mca:odd"));
        assertFalse(many.matches("mca:greedy"));
    }

    @Test
    void queryRejectsUnusableShapesInsteadOfThrowing() {
        // MCA's own `personality` condition throws JsonSyntaxException here and takes the whole
        // datapack reload (and world load) down with it. Ours must return null instead.
        assertNull(PersonalityQuery.fromJson(null));
        assertNull(PersonalityQuery.fromJson(JsonParser.parseString("{}")));
        assertNull(PersonalityQuery.fromJson(JsonParser.parseString("[]")));
        assertNull(PersonalityQuery.fromJson(JsonParser.parseString("[\"\"]")));
    }

    @Test
    void unknownPersonalityNeverMatchesButNeverThrows() {
        PersonalityQuery q = PersonalityQuery.fromJson(JsonParser.parseString("\"not_a_personality\""));
        assertNotNull(q);
        assertFalse(q.matches("mca:odd"));
        assertTrue(q.matches("not_a_personality")); // only itself
    }
}
