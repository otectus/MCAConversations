package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The five Townstead conditions (Townstead spec §8): strict parsing, and matching on hand-built views. */
class TownsteadQueriesTest {

    private static final TownsteadSnapshot SNAPSHOT = TownsteadFixtures.snapshot(TownsteadFixtures.villager(),
            Set.of("townstead:working", "townstead:well_fed", "townstead:day"));

    private static JsonElement json(String raw) {
        return JsonParser.parseString(raw);
    }

    private static TownsteadQuery query(String raw) {
        return TownsteadQuery.fromJson(json(raw));
    }

    @AfterEach
    void restore() {
        TownsteadCompat.resetForTest();
    }

    // --- conversations_townstead --------------------------------------------------------------

    @Test
    @DisplayName("allow-listed fields compare by their declared type")
    void fieldQueriesMatch() {
        assertTrue(query("{\"source\": \"villager\", \"path\": \"needs.hunger\", \"op\": \"gte\", \"value\": 50}")
                .matches(SNAPSHOT));
        assertFalse(query("{\"source\": \"villager\", \"path\": \"needs.hunger\", \"op\": \"lt\", \"value\": 50}")
                .matches(SNAPSHOT));
        assertTrue(query("{\"source\": \"villager\", \"path\": \"schedule.activity\", \"op\": \"eq\", \"value\": \"work\"}")
                .matches(SNAPSHOT));
        assertTrue(query("{\"source\": \"villager\", \"path\": \"profession.skills\", \"op\": \"contains\", "
                + "\"value\": \"townstead:artisan_baking\"}").matches(SNAPSHOT));
        assertTrue(query("{\"source\": \"calendar\", \"path\": \"month\", \"op\": \"in\", \"value\": [6, 7, 8]}")
                .matches(SNAPSHOT));
        assertTrue(query("{\"source\": \"building\", \"path\": \"family\", \"op\": \"eq\", \"value\": \"bakery\"}")
                .matches(SNAPSHOT));
        assertTrue(query("{\"source\": \"spirit\", \"path\": \"tier\", \"op\": \"gte\", \"value\": 2}")
                .matches(SNAPSHOT));
    }

    @Test
    @DisplayName("a path outside the allow-list, a wrong op for the type, or a stray key is refused at parse")
    void strictParsing() {
        assertThrows(IllegalArgumentException.class,
                () -> query("{\"source\": \"villager\", \"path\": \"genes.fertility\", \"op\": \"exists\"}"));
        assertThrows(IllegalArgumentException.class,
                () -> query("{\"source\": \"villager\", \"path\": \"schedule.activity\", \"op\": \"gt\", \"value\": 1}"));
        assertThrows(IllegalArgumentException.class,
                () -> query("{\"source\": \"villager\", \"path\": \"needs.hunger\", \"op\": \"eq\", \"value\": 5, \"x\": 1}"));
        assertThrows(IllegalArgumentException.class,
                () -> query("{\"source\": \"player\", \"path\": \"needs.hunger\", \"op\": \"exists\"}"),
                "the player source is not supported: Townstead needs are villager state");
        assertThrows(IllegalArgumentException.class,
                () -> query("{\"source\": \"calendar\", \"path\": \"month\", \"op\": \"in\", \"value\": []}"));
    }

    @Test
    @DisplayName("a source Townstead cannot supply never matches, whatever the op")
    void absentSourcesNeverMatch() {
        TownsteadQuery missing = query("{\"source\": \"calendar\", \"path\": \"season\", \"op\": \"missing\"}");
        assertFalse(missing.matches(TownsteadSnapshot.EMPTY));
        TownsteadSnapshot noCalendar = TownsteadSnapshot.of(TownsteadFixtures.villager(), TownsteadCalendarView.EMPTY,
                TownsteadBuildingView.EMPTY, TownsteadRootView.EMPTY, TownsteadSpiritView.EMPTY, Set.of());
        assertFalse(missing.matches(noCalendar), "no calendar is not a calendar with no season");
    }

    // --- tags, spirit, skills, capabilities ----------------------------------------------------

    @Test
    @DisplayName("tag queries combine all, any and none, and an unknown tag set matches nothing")
    void tagQueries() {
        TownsteadTagQuery query = TownsteadTagQuery.fromJson(json(
                "{\"all\": [\"townstead:working\"], \"any\": [\"townstead:day\", \"townstead:dusk\"], "
                        + "\"none\": [\"townstead:collapsed\"]}"));
        assertTrue(query.matches(SNAPSHOT.tags()));
        assertFalse(query.matches(Set.of("townstead:working", "townstead:day", "townstead:collapsed")));
        TownsteadTagQuery noneOnly = TownsteadTagQuery.fromJson(json("{\"none\": [\"townstead:collapsed\"]}"));
        assertFalse(noneOnly.matches(Set.of()), "no tags known is not none of these tags");
        assertThrows(IllegalArgumentException.class, () -> TownsteadTagQuery.fromJson(json("{}")));
    }

    @Test
    @DisplayName("spirit queries read Townstead's own tier and classification, and share needs a spirit")
    void spiritQueries() {
        assertTrue(TownsteadSpiritQuery.fromJson(json(
                "{\"classification\": [\"single\"], \"min_tier\": 2, \"primary\": \"townstead:hearth\", "
                        + "\"min_share\": 0.5}")).matches(SNAPSHOT.spirit()));
        assertFalse(TownsteadSpiritQuery.fromJson(json("{\"min_tier\": 3}")).matches(SNAPSHOT.spirit()));
        assertFalse(TownsteadSpiritQuery.fromJson(json("{\"min_tier\": 0}")).matches(TownsteadSpiritView.EMPTY));
        assertThrows(IllegalArgumentException.class,
                () -> TownsteadSpiritQuery.fromJson(json("{\"min_share\": 0.5}")));
        assertThrows(IllegalArgumentException.class,
                () -> TownsteadSpiritQuery.fromJson(json("{\"classification\": [\"theocracy\"]}")));
    }

    @Test
    @DisplayName("skill queries are exact, namespaced and read-only")
    void skillQueries() {
        Set<String> learned = SNAPSHOT.villager().profession().skills();
        assertTrue(TownsteadSkillQuery.fromJson(json("{\"has\": \"townstead:artisan_baking\"}")).matches(learned));
        assertFalse(TownsteadSkillQuery.fromJson(json("{\"all\": [\"townstead:artisan_baking\", \"townstead:smoking\"]}"))
                .matches(learned));
        assertTrue(TownsteadSkillQuery.fromJson(json("{\"any\": [\"townstead:smoking\", \"townstead:artisan_baking\"]}"))
                .matches(learned));
        assertFalse(TownsteadSkillQuery.fromJson(json("{\"has\": \"artisan_baking\"}")).matches(learned),
                "a bare id is not the namespaced one");
    }

    @Test
    @DisplayName("a capability gate with an unknown id never matches, rather than matching everything")
    void availabilityQueries() {
        Set<TownsteadCapability> bound = EnumSet.of(TownsteadCapability.READ_NEEDS, TownsteadCapability.READ_CALENDAR);
        assertTrue(TownsteadAvailableQuery.fromJson(json("\"read_needs\"")).matches(bound));
        assertTrue(TownsteadAvailableQuery.fromJson(json("{\"any\": [\"read_spirit\", \"READ_CALENDAR\"]}")).matches(bound));
        assertFalse(TownsteadAvailableQuery.fromJson(json("{\"all\": [\"read_needs\", \"read_spirit\"]}")).matches(bound));
        assertFalse(TownsteadAvailableQuery.fromJson(json("[\"read_needs\", \"read_minds\"]")).matches(bound));
    }

    // --- the shared evaluator ------------------------------------------------------------------

    @Test
    @DisplayName("with Townstead absent every condition is false, so a Townstead branch falls to its fallback")
    void absentTownsteadNeverMatches() {
        for (String type : TownsteadConditions.TYPES) {
            JsonElement body = switch (type) {
                case TownsteadConditions.AVAILABLE -> json("\"read_needs\"");
                case TownsteadConditions.QUERY -> json("{\"source\": \"villager\", \"path\": \"needs.hunger\", \"op\": \"exists\"}");
                case TownsteadConditions.TAGS -> json("{\"none\": [\"townstead:collapsed\"]}");
                case TownsteadConditions.SPIRIT -> json("{\"min_tier\": 0}");
                case TownsteadConditions.SKILL -> json("{\"has\": \"townstead:artisan_baking\"}");
                default -> throw new AssertionError(type);
            };
            assertFalse(TownsteadConditions.test(TownsteadConditions.parse(type, body), null), type);
        }
        assertThrows(IllegalArgumentException.class,
                () -> TownsteadConditions.parse("conversations_townstead_react", json("{}")));
    }
}
