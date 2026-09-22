package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopicGateTest {

    private static ConversationCatalog catalog() {
        TopicEntry entry = TopicEntry.fromJson("restricted", JsonParser.parseString("""
                {"entry":{"question":"greet","answer":"checkin"},"depth":"quick",
                 "return_question":"main","ages":["adult"],
                 "required_stance_families":["exit"],
                 "kingdom_gate":{"include":["ultima_kingdoms:lunari"]}}
                """).getAsJsonObject());
        return ConversationCatalog.build(List.of(entry));
    }

    private static ConversationCatalog civicCatalog() {
        TopicEntry entry = TopicEntry.fromJson("guild_contact", JsonParser.parseString("""
                {"entry":{"question":"village","answer":"guild_contact"},"depth":"quick",
                 "return_question":"main","ages":["adult"],
                 "required_stance_families":["exit"],"civic_contact":true}
                """).getAsJsonObject());
        return ConversationCatalog.build(List.of(entry));
    }

    @Test
    void nativeStarterCannotBypassKingdomDecision() {
        ConversationCatalog catalog = catalog();
        assertFalse(TopicGate.allows(catalog, "greet", "checkin", AgeGroup.ADULT, gate -> false));
        assertTrue(TopicGate.allows(catalog, "greet", "checkin", AgeGroup.ADULT, gate -> true));
    }

    @Test
    void ageAndKingdomAreAndedAndUnknownAnswersStayUntouched() {
        ConversationCatalog catalog = catalog();
        assertFalse(TopicGate.allows(catalog, "greet", "checkin", AgeGroup.CHILD, gate -> true));
        assertTrue(TopicGate.allows(catalog, "greet", "not_catalogued", AgeGroup.CHILD, gate -> false));
        assertTrue(TopicGate.allows(null, "greet", "checkin", AgeGroup.CHILD, gate -> false));
    }

    @Test
    void civicStarterFailsClosedAndCannotBeEnteredByDirectOrChatSelection() {
        ConversationCatalog catalog = civicCatalog();
        assertFalse(TopicGate.allows(catalog, "village", "guild_contact", AgeGroup.ADULT,
                gate -> true, () -> false));
        assertTrue(TopicGate.allows(catalog, "village", "guild_contact", AgeGroup.ADULT,
                gate -> true, () -> true));
        assertFalse(TopicGate.allows(catalog, "village", "guild_contact", AgeGroup.CHILD,
                gate -> true, () -> true));
    }

    @Test
    void townsteadTopicIsOfferedOnlyWhileTownsteadContentIsLive() {
        TopicEntry entry = TopicEntry.fromJson("wellbeing", JsonParser.parseString("""
                {"entry":{"question":"conversations.cat.townstead","answer":"wellbeing"},"depth":"quick",
                 "return_question":"conversations.cat.townstead","ages":["teen","adult"],
                 "required_stance_families":["exit"],"townstead":true}
                """).getAsJsonObject());
        ConversationCatalog catalog = ConversationCatalog.build(List.of(entry));
        assertFalse(TopicGate.allows(catalog, "conversations.cat.townstead", "wellbeing", AgeGroup.ADULT,
                gate -> true, () -> false, () -> false));
        assertTrue(TopicGate.allows(catalog, "conversations.cat.townstead", "wellbeing", AgeGroup.ADULT,
                gate -> true, () -> false, () -> true));
        assertFalse(TopicGate.allows(catalog, "conversations.cat.townstead", "wellbeing", AgeGroup.ADULT,
                gate -> true), "the default seam is an install without Townstead");
    }

    @Test
    void townsteadFlagIsAStrictBoolean() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> TopicEntry.fromJson("x",
                JsonParser.parseString("""
                {"entry":{"question":"q","answer":"a"},"depth":"quick","return_question":"q",
                 "ages":["adult"],"required_stance_families":["exit"],"townstead":"yes"}
                """).getAsJsonObject()));
    }
}
