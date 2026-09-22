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
}
