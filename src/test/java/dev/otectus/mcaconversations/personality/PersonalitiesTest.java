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
    void rosterMatchesTargetMcaExactly() {
        // Read off `javap net.conczin.mca.entity.ai.relationship.Personality` for the resolved
        // 7.7.36-beta.3+1.21.1 jar, minus UNASSIGNED (a sentinel, excluded from MCA's own
        // getRandom and never rolled onto a villager). See docs/PORT-1.21.1-EVIDENCE.md.
        assertEquals(14, Personalities.CANONICAL.size());
        for (String p : new String[]{"friendly", "flirty", "playful", "gloomy", "sensitive",
                "greedy", "odd", "crabby", "extroverted", "introverted", "relaxed", "anxious",
                "peaceful", "upbeat"}) {
            assertTrue(Personalities.CANONICAL.contains(p), "roster missing " + p);
        }
        assertFalse(Personalities.CANONICAL.contains("unassigned"));
        // Renamed away in 7.7, or dropped between the 1.20.1-era 7.7 beta and 1.21.1 — none of
        // these may reappear as rollable.
        for (String gone : new String[]{"witty", "shy", "lazy", "grumpy", "athletic",
                "confident", "peppy"}) {
            assertFalse(Personalities.CANONICAL.contains(gone),
                    gone + " is not registered by the target MCA");
        }
    }

    @Test
    void normalizeAcceptsEverySpellingMcaCanProduce() {
        assertEquals("odd", Personalities.normalize("mca:odd"));   // VillagerBrain.getPersonalityId()
        assertEquals("odd", Personalities.normalize("ODD"));       // a 7.6-era enum name in old data
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
    void droppedPersonalitiesSurviveAsLegacyOnlyAndAreNotCanonical() {
        // 7.7 turned athletic into the mca:athletic trait; confident and peppy were rollable in the
        // 1.20.1-era 7.7 beta and are simply not registered on 1.21.1. None can be rolled onto a
        // villager here, but all three can still arrive from an upgraded save or a third-party
        // pack, so their voices stay — they just must never be presented as target personalities.
        for (String legacy : new String[]{"athletic", "confident", "peppy"}) {
            assertTrue(Personalities.LEGACY_ONLY.contains(legacy), legacy + " should be legacy-only");
            assertFalse(Personalities.isCanonical(legacy));
            assertEquals(legacy, Personalities.canonical(legacy));
            assertTrue(Personalities.overlayPrefixes().contains(legacy));
        }
    }

    @Test
    void overlayPrefixesCoverCanonicalAndLegacy() {
        // 14 canonical + 4 renamed aliases + 3 legacy-only. Unchanged at 21 across the port: the
        // roster shrank by two, and exactly those two moved into LEGACY_ONLY rather than being
        // dropped, so every shipped lang overlay still has a prefix that claims it.
        assertEquals(21, Personalities.overlayPrefixes().size());
        assertTrue(Personalities.overlayPrefixes().containsAll(Personalities.CANONICAL));
        assertTrue(Personalities.overlayPrefixes().containsAll(Personalities.LEGACY_ALIASES.keySet()));
        assertTrue(Personalities.overlayPrefixes().containsAll(Personalities.LEGACY_ONLY));
    }

    @Test
    void matchesBridgesBothMcaVersions() {
        // The whole point: one authored id matches a villager whose personality was written by
        // either MCA generation, so dialogue authored once keeps working across an upgrade.
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
        assertFalse(single.matches("mca:playful"));

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
