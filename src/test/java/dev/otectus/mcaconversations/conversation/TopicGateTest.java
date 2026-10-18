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
                 "kingdom_gate":{"include":["mcakingdoms:lunari"]}}
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

    @Test
    void aFinishedStarterIsHiddenUnlessTheTopicIsRepeatable() {
        ConversationCatalog catalog = ConversationCatalog.build(List.of(
                TopicEntry.fromJson("life", JsonParser.parseString("""
                        {"entry":{"question":"conversations.cat.personal","answer":"life"},"depth":"deep",
                         "return_question":"conversations.cat.personal","ages":["adult"],
                         "required_stance_families":["exit"]}
                        """).getAsJsonObject()),
                TopicEntry.fromJson("news", JsonParser.parseString("""
                        {"entry":{"question":"conversations.cat.events","answer":"news"},"depth":"quick",
                         "return_question":"conversations.cat.events","ages":["adult"],
                         "required_stance_families":["exit"],"repeatable":true}
                        """).getAsJsonObject())));
        // Nothing finished: both offered.
        assertTrue(TopicGate.allows(catalog, "conversations.cat.personal", "life", AgeGroup.ADULT,
                gate -> true, () -> true, () -> true, entry -> false));
        // Both finished: the biography hides, the news does not, and an unknown answer is untouched.
        assertFalse(TopicGate.allows(catalog, "conversations.cat.personal", "life", AgeGroup.ADULT,
                gate -> true, () -> true, () -> true, entry -> true));
        assertTrue(TopicGate.allows(catalog, "conversations.cat.events", "news", AgeGroup.ADULT,
                gate -> true, () -> true, () -> true, entry -> true));
        assertTrue(TopicGate.allows(catalog, "conversations.cat.personal", "not_catalogued", AgeGroup.ADULT,
                gate -> true, () -> true, () -> true, entry -> true));
    }

    // --- Family perspective (feedback remediation WP-C1) ---------------------------------------------------

    private static ConversationCatalog familyCatalog() {
        TopicEntry child = TopicEntry.fromJson("checkin_child", JsonParser.parseString("""
                {"entry":{"question":"conversations.family","answer":"checkin_child"},"depth":"relationship",
                 "return_question":"conversations.family","ages":["toddler","child","teen","adult"],
                 "required_stance_families":["exit"],"player_role":"parent"}
                """).getAsJsonObject());
        TopicEntry parent = TopicEntry.fromJson("ask_parent", JsonParser.parseString("""
                {"entry":{"question":"conversations.family","answer":"ask_parent"},"depth":"relationship",
                 "return_question":"conversations.family","ages":["toddler","child","teen","adult"],
                 "required_stance_families":["exit"],"player_role":"child"}
                """).getAsJsonObject());
        TopicEntry kids = TopicEntry.fromJson("their_children", JsonParser.parseString("""
                {"entry":{"question":"conversations.cat.relationships","answer":"their_children"},"depth":"relationship",
                 "return_question":"conversations.cat.relationships","ages":["adult"],
                 "required_stance_families":["exit"],"family":{"has":"children"}}
                """).getAsJsonObject());
        return ConversationCatalog.build(List.of(child, parent, kids));
    }

    private static boolean family(ConversationCatalog catalog, String question, String answer, AgeGroup age,
                                  RelationshipRoles roles, boolean speakerHasChildren) {
        return TopicGate.allows(catalog, question, answer, age, gate -> true, () -> false, () -> false,
                entry -> false, () -> roles, requirement -> speakerHasChildren);
    }

    private static final RelationshipRoles PLAYER_IS_PARENT = new RelationshipRoles(false, true, false, false);
    private static final RelationshipRoles PLAYER_IS_CHILD = new RelationshipRoles(false, false, true, false);

    @Test
    void aChildTopicIsOnlyForThePlayersOwnChild() {
        ConversationCatalog catalog = familyCatalog();
        assertTrue(family(catalog, "conversations.family", "checkin_child", AgeGroup.CHILD, PLAYER_IS_PARENT, false));
        assertFalse(family(catalog, "conversations.family", "checkin_child", AgeGroup.CHILD, PLAYER_IS_CHILD, false),
                "the speaker is the player's parent: a child's lines must not reach them");
        assertFalse(family(catalog, "conversations.family", "checkin_child", AgeGroup.CHILD, RelationshipRoles.NONE, false),
                "an unrelated child, or an unreadable family tree, opens nothing here");
    }

    @Test
    void aParentTopicIsOnlyForThePlayersOwnParent() {
        ConversationCatalog catalog = familyCatalog();
        assertTrue(family(catalog, "conversations.family", "ask_parent", AgeGroup.ADULT, PLAYER_IS_CHILD, false));
        assertFalse(family(catalog, "conversations.family", "ask_parent", AgeGroup.ADULT, PLAYER_IS_PARENT, false));
        assertFalse(family(catalog, "conversations.family", "ask_parent", AgeGroup.ADULT,
                new RelationshipRoles(true, false, false, true), false), "a spouse or a sibling is not a parent");
    }

    @Test
    void theirChildrenNeedsChildrenAndNoParticularRole() {
        ConversationCatalog catalog = familyCatalog();
        assertTrue(family(catalog, "conversations.cat.relationships", "their_children", AgeGroup.ADULT,
                RelationshipRoles.NONE, true), "anyone may ask a villager who has children about them");
        assertFalse(family(catalog, "conversations.cat.relationships", "their_children", AgeGroup.ADULT,
                RelationshipRoles.NONE, false), "a childless villager is never asked how the kids are");
        assertFalse(family(catalog, "conversations.cat.relationships", "their_children", AgeGroup.TEEN,
                RelationshipRoles.NONE, true), "the catalog's age allow-list still applies");
    }

    @Test
    void theDefaultSeamKnowsNoRoleAndSoOpensNoFamilyTopic() {
        ConversationCatalog catalog = familyCatalog();
        assertFalse(TopicGate.allows(catalog, "conversations.family", "checkin_child", AgeGroup.CHILD, gate -> true));
        assertFalse(TopicGate.allows(catalog, "conversations.cat.relationships", "their_children", AgeGroup.ADULT,
                gate -> true));
    }

    @Test
    void playerRoleAndFamilyAreStrict() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> TopicEntry.fromJson("x",
                JsonParser.parseString("""
                {"entry":{"question":"q","answer":"a"},"depth":"quick","return_question":"q",
                 "ages":["adult"],"required_stance_families":["exit"],"player_role":"grandparent"}
                """).getAsJsonObject()));
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> TopicEntry.fromJson("x",
                JsonParser.parseString("""
                {"entry":{"question":"q","answer":"a"},"depth":"quick","return_question":"q",
                 "ages":["adult"],"required_stance_families":["exit"],"family":{"has":"pets"}}
                """).getAsJsonObject()));
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> TopicEntry.fromJson("x",
                JsonParser.parseString("""
                {"entry":{"question":"q","answer":"a"},"depth":"quick","return_question":"q",
                 "ages":["adult"],"required_stance_families":["exit"],"player_role":true}
                """).getAsJsonObject()));
        TopicEntry plain = TopicEntry.fromJson("plain", JsonParser.parseString("""
                {"entry":{"question":"q","answer":"a"},"depth":"quick","return_question":"q",
                 "ages":["adult"],"required_stance_families":["exit"]}
                """).getAsJsonObject());
        assertFalse(plain.familyGated(), "a topic without the fields is exactly as before");
    }

    @Test
    void aFamilyRequirementCountsTheRightRelatives() {
        FamilyRequirement children = FamilyRequirement.fromJson(JsonParser.parseString("{\"has\":\"children\"}"));
        assertTrue(children.satisfiedBy(1, 0, 0));
        assertFalse(children.satisfiedBy(0, 2, 3));
        assertTrue(FamilyRequirement.fromJson(JsonParser.parseString("{\"has\":\"parents\"}")).satisfiedBy(0, 1, 0));
        assertTrue(FamilyRequirement.fromJson(JsonParser.parseString("{\"has\":\"siblings\"}")).satisfiedBy(0, 0, 1));
        assertFalse(children.test(null), "no villager, no family");
    }
}
