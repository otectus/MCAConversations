package dev.otectus.mcaconversations.hub;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.EpisodeState;
import dev.otectus.mcaconversations.history.PrivacyLevel;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.ThreadStatus;
import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The dynamic hub's rules (spec §14.2, §14.3): at most three entries, never the same conversation
 * twice, and no label that could tell the player something they have not been told.
 */
class DynamicHubTest {

    private static final UUID VILLAGER = UUID.fromString("00000000-0000-0000-0000-00000000f001");
    private static final UUID PLAYER = UUID.fromString("00000000-0000-0000-0000-00000000f002");

    private static EpisodeRecord episode(String subject, PrivacyLevel privacy, int salience,
                                         boolean playerKnows) {
        EpisodeRecord record = EpisodeRecord.opened(UUID.randomUUID(), "kind." + subject, subject,
                EpisodeState.ACTIVE, VILLAGER, Map.of(), privacy, salience, 1L);
        return playerKnows ? record.witnessedBy(PLAYER) : record;
    }

    private static SharedThreadRecord thread(String topic, PrivacyLevel privacy) {
        return new SharedThreadRecord("t." + topic, topic, topic + ".subject", Optional.empty(),
                ThreadStatus.OPEN, "", "", "", "", privacy, 0L, OptionalLong.empty(), 0, 0L);
    }

    @Test
    @DisplayName("the three entries answer three different questions")
    void allThreeKindsCanAppear() {
        HubPlan plan = DynamicHub.build(
                List.of(thread("work", PrivacyLevel.ORDINARY)),
                List.of(episode("village.wall", PrivacyLevel.ORDINARY, 60, false),
                        episode("secret.old_debt", PrivacyLevel.DISCREET, 50, true)),
                PLAYER, 3);

        assertEquals(3, plan.slots().size());
        assertEquals(HubSlot.Kind.CONTINUE, plan.slots().get(0).kind());
        assertEquals(HubSlot.Kind.MIND, plan.slots().get(1).kind());
        assertEquals(HubSlot.Kind.ASK, plan.slots().get(2).kind());
    }

    @Test
    @DisplayName("what the player has not been told is offered without a word about what it is")
    void theMindEntryNamesNothing() {
        HubPlan plan = DynamicHub.build(List.of(),
                List.of(episode("secret.the_thing", PrivacyLevel.DISCREET, 80, false)),
                PLAYER, 3);

        HubSlot slot = plan.slots().get(0);
        assertEquals(HubSlot.Kind.MIND, slot.kind());
        assertEquals("dynamic_mind", slot.answerName(),
                "there is exactly one such button, so it cannot be read for a hint");
        assertFalse(slot.kind().namesDomain());
    }

    @Test
    @DisplayName("a subject the player already knows may name its domain")
    void theAskEntryNamesItsDomain() {
        HubPlan plan = DynamicHub.build(List.of(),
                List.of(episode("village.wall", PrivacyLevel.PUBLIC, 60, true)), PLAYER, 3);

        HubSlot slot = plan.slots().get(0);
        assertEquals(HubSlot.Kind.ASK, slot.kind());
        assertEquals(HubDomain.VILLAGE, slot.domain());
        assertEquals("dynamic_ask_village", slot.answerName());
    }

    @Test
    @DisplayName("a personal subject is offered without naming which part of it")
    void personalSubjectsStayVague() {
        HubPlan plan = DynamicHub.build(List.of(),
                List.of(episode("fears.the_dark", PrivacyLevel.DISCREET, 70, true)), PLAYER, 3);

        HubSlot slot = plan.slots().get(0);
        assertEquals(HubDomain.PERSONAL, slot.domain());
        assertEquals("dynamic_ask_personal", slot.answerName(),
                "fears, regrets and a secret all reach the player as the same word: something");
        assertEquals(HubDomain.PERSONAL,
                HubDomain.ofTopic("regrets").orElseThrow(),
                "so no personal topic can be told apart from another by its label");
    }

    @Test
    @DisplayName("a thing the villager would not repeat is never offered as a button")
    void speakerOnlyIsNeverAdvertised() {
        HubPlan plan = DynamicHub.build(
                List.of(thread("secret", PrivacyLevel.SPEAKER_ONLY)),
                List.of(episode("secret.mine", PrivacyLevel.SPEAKER_ONLY, 90, true)),
                PLAYER, 3);

        assertTrue(plan.isEmpty(),
                "a menu entry is a standing offer, and a confidence is not");
    }

    @Test
    @DisplayName("the same conversation is never offered twice under two labels")
    void topicsAreNotDuplicated() {
        HubPlan plan = DynamicHub.build(
                List.of(thread("village", PrivacyLevel.ORDINARY)),
                List.of(episode("village.wall", PrivacyLevel.PUBLIC, 60, true)), PLAYER, 3);

        assertEquals(1, plan.slots().size());
        assertEquals(HubSlot.Kind.CONTINUE, plan.slots().get(0).kind());
    }

    @Test
    @DisplayName("the budget is honoured, and zero reproduces the fixed hub exactly")
    void budgetCaps() {
        List<EpisodeRecord> live = List.of(
                episode("village.wall", PrivacyLevel.PUBLIC, 60, false),
                episode("work.ledger", PrivacyLevel.ORDINARY, 50, true));

        assertEquals(1, DynamicHub.build(List.of(thread("day", PrivacyLevel.ORDINARY)),
                live, PLAYER, 1).slots().size());
        assertTrue(DynamicHub.build(List.of(thread("day", PrivacyLevel.ORDINARY)),
                live, PLAYER, 0).isEmpty());
    }

    @Test
    @DisplayName("a topic no domain claims is not offered at all")
    void unknownTopicsAreSkipped() {
        HubPlan plan = DynamicHub.build(List.of(thread("something_a_pack_added", PrivacyLevel.ORDINARY)),
                List.of(), PLAYER, 3);

        assertTrue(plan.isEmpty(), "a label with no domain could say anything, so it says nothing");
    }

    @Test
    @DisplayName("a topic is read off the subject the episode is actually about")
    void topicComesFromTheSubject() {
        assertEquals("work", DynamicHub.topicOf(episode("work.librarian.damaged_volume",
                PrivacyLevel.ORDINARY, 10, false)));
        assertEquals("weather", DynamicHub.topicOf(episode("weather", PrivacyLevel.ORDINARY, 10, false)));
    }

    @Test
    @DisplayName("only the exact offer picks an entry; anything else falls through")
    void resolutionIsExact() {
        HubPlan plan = DynamicHub.build(List.of(),
                List.of(episode("village.wall", PrivacyLevel.PUBLIC, 60, true)), PLAYER, 3);

        assertEquals(Optional.of(HubSlot.Kind.ASK),
                HubLabels.resolve(plan, "Can I ask about the village?").map(HubSlot::kind));
        assertEquals(Optional.empty(), HubLabels.resolve(plan, "village"),
                "a bare topic word must reach the ordinary matcher, not be swallowed as a hub pick");
        assertEquals(Optional.empty(), HubLabels.resolve(HubPlan.EMPTY, "can i ask about the village"));
    }

    @Test
    @DisplayName("every label a hub can show exists in both locales")
    void labelsAreLocalized() throws Exception {
        JsonObject en = readLang("en_us");
        JsonObject pt = readLang("pt_br");

        List<String> missing = new ArrayList<>();
        for (String key : HubLabels.allLangKeys()) {
            if (!en.has(key)) {
                missing.add(key + " (en_us)");
            }
            if (!pt.has(key)) {
                missing.add(key + " (pt_br)");
            }
        }
        assertTrue(missing.isEmpty(), String.join(", ", missing));
    }

    @Test
    @DisplayName("every catalog topic belongs to a domain, so nothing is silently unofferable")
    void everyTopicHasADomain() throws Exception {
        JsonObject topics = JsonParser.parseString(Files.readString(TestPaths.of(
                        "src/main/resources/data/mcaconversations/conversation_catalog/topics.json")))
                .getAsJsonObject().getAsJsonObject("topics");

        List<String> orphans = new ArrayList<>();
        for (String topic : topics.keySet()) {
            if (HubDomain.ofTopic(topic).isEmpty()) {
                orphans.add(topic);
            }
        }
        assertTrue(orphans.isEmpty(),
                "these topics can never be surfaced contextually: " + String.join(", ", orphans));
    }

    private static JsonObject readLang(String locale) throws Exception {
        return JsonParser.parseString(Files.readString(TestPaths.of(
                "src/main/resources/assets/mcaconversations/lang/" + locale + ".json"))).getAsJsonObject();
    }
}
