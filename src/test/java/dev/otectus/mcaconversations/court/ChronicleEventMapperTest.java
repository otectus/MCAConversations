package dev.otectus.mcaconversations.court;

import dev.otectus.mcaconversations.gossip.GossipEventType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Every one of Capitals' twenty coarse event types, and the two that carry no type at all.
 *
 * <p>The list below is the contract with MCA Capitals: if the mod adds a constant, this test still
 * passes (the new name falls through to the substring guess) but the constant is not covered here,
 * which is exactly the signal to come back and decide what a village would call it.
 */
class ChronicleEventMapperTest {

    /** The twenty constants of {@code CapitalChronicleEventType}, lower-cased as they cross. */
    private static final Map<String, GossipEventType> COARSE = Map.ofEntries(
            Map.entry("capital_founded", GossipEventType.CAPITAL_FOUNDED),
            Map.entry("heir_apparent_named", GossipEventType.COURT_NEWS),
            Map.entry("crown_child_born", GossipEventType.ROYAL_BIRTH),
            Map.entry("royal_birth", GossipEventType.ROYAL_BIRTH),
            Map.entry("royal_marriage", GossipEventType.ROYAL_MARRIAGE),
            Map.entry("sovereign_death", GossipEventType.ROYAL_DEATH),
            Map.entry("throne_seized", GossipEventType.CORONATION),
            Map.entry("peaceful_transfer", GossipEventType.CORONATION),
            Map.entry("abdication", GossipEventType.COURT_NEWS),
            Map.entry("disinherited", GossipEventType.DISGRACE),
            Map.entry("legitimized", GossipEventType.APPOINTMENT),
            Map.entry("new_duke_or_duchess", GossipEventType.APPOINTMENT),
            Map.entry("lord_commander_appointed", GossipEventType.APPOINTMENT),
            Map.entry("hand_appointed", GossipEventType.APPOINTMENT),
            Map.entry("grand_maester_appointed", GossipEventType.APPOINTMENT),
            Map.entry("royal_guard_appointed", GossipEventType.APPOINTMENT),
            Map.entry("court_herald_appointed", GossipEventType.APPOINTMENT),
            Map.entry("mourning_ended", GossipEventType.COURT_NEWS),
            // The two that say nothing: they are resolved by the translation key, not the type.
            Map.entry("generic_notable", GossipEventType.COURT_NEWS),
            Map.entry("none", GossipEventType.COURT_NEWS));

    @Test
    void everyCoarseTypeMaps() {
        assertEquals(20, COARSE.size(), "Capitals has twenty coarse chronicle types");
        COARSE.forEach((coarse, expected) ->
                assertEquals(expected, ChronicleEventMapper.map(coarse, ""), coarse));
    }

    @Test
    void theTypeWinsOverTheTranslationKey() {
        // A hand's appointment whose key happens to mention a war is still an appointment.
        assertEquals(GossipEventType.APPOINTMENT,
                ChronicleEventMapper.map("hand_appointed", "capitals.chronicle.hand_after_the_war"));
    }

    @Test
    void aLegacyEntryIsPlainCourtNews() {
        // Old plain-text entries carry no semantic type at all, so there is nothing to guess from.
        assertEquals(GossipEventType.COURT_NEWS, ChronicleEventMapper.map("legacy", ""));
        assertEquals(GossipEventType.COURT_NEWS,
                ChronicleEventMapper.map("legacy", "capitals.chronicle.war_declared"));
    }

    @Test
    void anUntypedEntryFallsBackToItsTranslationKey() {
        assertEquals(GossipEventType.WAR,
                ChronicleEventMapper.map("generic_notable", "capitals.chronicle.war_declared"));
        assertEquals(GossipEventType.WAR,
                ChronicleEventMapper.map("generic_notable", "capitals.chronicle.siege_lifted"));
        assertEquals(GossipEventType.PEACE,
                ChronicleEventMapper.map("generic_notable", "capitals.chronicle.truce_signed"));
        assertEquals(GossipEventType.ALLIANCE,
                ChronicleEventMapper.map("generic_notable", "capitals.chronicle.trade_pact"));
        assertEquals(GossipEventType.DISGRACE,
                ChronicleEventMapper.map("none", "capitals.chronicle.executed_by_crown"));
        assertEquals(GossipEventType.APPOINTMENT,
                ChronicleEventMapper.map("none", "capitals.chronicle.royal_pardon"));
    }

    @Test
    void anythingUnrecognisedIsCourtNewsRatherThanDropped() {
        assertEquals(GossipEventType.COURT_NEWS, ChronicleEventMapper.map("", ""));
        assertEquals(GossipEventType.COURT_NEWS, ChronicleEventMapper.map(null, null));
        assertEquals(GossipEventType.COURT_NEWS,
                ChronicleEventMapper.map("a_type_from_a_later_capitals", "capitals.chronicle.who_knows"));
    }

    @Test
    void theTypeNameIsReadCaseInsensitively() {
        assertEquals(GossipEventType.COURT_NEWS, ChronicleEventMapper.map("ABDICATION", ""));
        assertEquals(GossipEventType.COURT_NEWS, ChronicleEventMapper.map(" Abdication ", ""));
    }

    @Test
    void embeddedWordsDoNotInventWarOrPeace() {
        assertEquals(GossipEventType.DISGRACE,
                ChronicleEventMapper.map("none", "capitals.chronicle.warrant_issued"));
        assertEquals(GossipEventType.COURT_NEWS,
                ChronicleEventMapper.map("none", "capitals.chronicle.reward_issued"));
        assertEquals(GossipEventType.COURT_NEWS,
                ChronicleEventMapper.map("none", "capitals.chronicle.research_completed"));
    }

    @Test
    void noCoarseTypeMapsToATypeThisModCannotSay() {
        // Every mapping target must be a real gossip type, or a villager renders a raw lang key.
        List<GossipEventType> known = List.of(GossipEventType.values());
        COARSE.values().forEach(type -> assertNotEquals(-1, known.indexOf(type)));
    }
}
