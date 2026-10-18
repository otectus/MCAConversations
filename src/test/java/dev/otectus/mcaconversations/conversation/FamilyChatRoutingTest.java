package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.chat.ChatIntentTestData;
import dev.otectus.mcaconversations.chat.IntentIndex;
import dev.otectus.mcaconversations.chat.IntentMatcher;
import dev.otectus.mcaconversations.chat.IntentMatcher.Decision;
import dev.otectus.mcaconversations.chat.IntentMatcher.Scored;
import dev.otectus.mcaconversations.chat.Normalizer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "How are the kids", typed at three different villagers (feedback remediation WP-C3 acceptance).
 *
 * <p>Chat mode ranks every intent, keeps the ones {@code GatePreview} lets through — MCA's answer
 * constraints, then {@link TopicGate} — and decides among those. This replays that pipeline with the
 * shipped intents, dialogue constraints and catalog, the villager supplied as plain facts instead of
 * an entity: a childless adult understands the question and is not the one to ask (the gated-topic
 * miss {@code ChatModeDispatcher.gatedTopicMiss} recognises), a parent opens {@code their_children},
 * and the player's own child opens nothing from that phrase — in particular not {@code checkin_child},
 * the topic about the child themselves.
 */
class FamilyChatRoutingTest {

    private static final double MIN = 0.55;
    private static final double AMBIENT = 0.75;
    private static final String KIDS = "how are the kids";
    private static final String THEIR_CHILDREN = "family.their_children";

    private static final RelationshipRoles PLAYER_IS_PARENT = new RelationshipRoles(false, true, false, false);

    private static IntentIndex index;
    private static ConversationCatalog catalog;
    private static final Map<String, JsonObject> questions = new HashMap<>();

    /** One villager as the gate sees them. {@code traits} are MCA constraint tokens that hold. */
    private record Villager(AgeGroup age, String ageToken, RelationshipRoles roles, boolean hasChildren,
                            Set<String> traits) {
    }

    @BeforeAll
    static void load() throws IOException {
        index = ChatIntentTestData.index();
        List<TopicEntry> topics = new ArrayList<>();
        JsonObject root = JsonParser.parseString(Files.readString(
                Path.of("src/main/resources/data/mcaconversations/conversation_catalog/topics.json"))).getAsJsonObject();
        for (Map.Entry<String, JsonElement> e : root.getAsJsonObject("topics").entrySet()) {
            topics.add(TopicEntry.fromJson(e.getKey(), e.getValue().getAsJsonObject()));
        }
        catalog = ConversationCatalog.build(topics);
        try (var files = Files.list(Path.of("src/main/resources/data/mcaconversations/dialogues"))) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                questions.put(file.getFileName().toString().replace(".json", ""),
                        JsonParser.parseString(Files.readString(file)).getAsJsonObject());
            }
        }
    }

    /** {@code GatePreview.eligible}, minus the entity: the answer's MCA constraints, then the catalog gate. */
    private static boolean eligible(Scored scored, Villager villager) {
        if (scored.isSystem()) {
            return true;
        }
        if (!constraintsHold(scored.question(), scored.answer(), villager)) {
            return false;
        }
        return TopicGate.allows(catalog, scored.question(), scored.answer(), villager.age(), gate -> true,
                () -> true, () -> true, entry -> false, villager::roles,
                requirement -> requirement.has() == FamilyRequirement.Relation.CHILDREN && villager.hasChildren());
    }

    /** MCA's constraint string on the answer: every bare token must hold, every {@code !}-token must not. */
    private static boolean constraintsHold(String question, String answer, Villager villager) {
        JsonObject page = questions.get(question);
        if (page == null) {
            return true;
        }
        for (JsonElement element : page.getAsJsonArray("answers")) {
            JsonObject candidate = element.getAsJsonObject();
            if (!answer.equals(candidate.get("name").getAsString()) || !candidate.has("constraints")
                    || !candidate.get("constraints").isJsonPrimitive()) {
                continue;
            }
            for (String raw : candidate.get("constraints").getAsString().split(",")) {
                String token = raw.trim();
                if (token.isEmpty()) {
                    continue;
                }
                boolean negated = token.startsWith("!");
                String name = negated ? token.substring(1) : token;
                boolean holds = Set.of("baby", "toddler", "child", "teen", "adult").contains(name)
                        ? name.equals(villager.ageToken())
                        : villager.traits().contains(name);
                if (holds == negated) {
                    return false;
                }
            }
        }
        return true;
    }

    private record Typed(List<Scored> ranked, List<Scored> eligible, Decision decision) {
    }

    /** The message typed at the villager with nothing open, as {@code ChatModeDispatcher} routes it. */
    private static Typed typed(String message, Villager villager) {
        List<Scored> ranked = IntentMatcher.rank(index, Normalizer.normalize(message, index.synonyms()), null);
        List<Scored> eligible = new ArrayList<>();
        for (Scored s : ranked) {
            if (eligible(s, villager)) {
                eligible.add(s);
            }
        }
        return new Typed(ranked, eligible, IntentMatcher.decide(eligible, true, MIN, AMBIENT));
    }

    private static boolean gatedTopicMiss(Typed typed) {
        Scored top = typed.ranked().get(0);
        return !top.isSystem() && top.score() >= MIN && !typed.eligible().contains(top);
    }

    @Test
    @DisplayName("the phrase means their_children before any gate is applied")
    void thePhraseIsUnderstood() {
        List<Scored> ranked = IntentMatcher.rank(index, Normalizer.normalize(KIDS, index.synonyms()), null);
        assertFalse(ranked.isEmpty());
        assertEquals(THEIR_CHILDREN, ranked.get(0).id());
        assertTrue(ranked.get(0).score() >= MIN, "score " + ranked.get(0).score());
    }

    @Test
    @DisplayName("a childless adult understands the question and is not the one to ask: a gated-topic miss")
    void aChildlessAdultIsAGatedMiss() {
        Typed typed = typed(KIDS, new Villager(AgeGroup.ADULT, "adult", RelationshipRoles.NONE, false, Set.of()));
        assertEquals(IntentMatcher.Outcome.NONE, typed.decision().outcome(),
                "opened " + (typed.decision().chosen() == null ? null : typed.decision().chosen().id()));
        assertTrue(gatedTopicMiss(typed));
    }

    @Test
    @DisplayName("a villager with children opens their_children")
    void aParentOpensTheirChildren() {
        Typed typed = typed(KIDS, new Villager(AgeGroup.ADULT, "adult", RelationshipRoles.NONE, true, Set.of()));
        assertEquals(IntentMatcher.Outcome.MATCH, typed.decision().outcome());
        assertEquals(THEIR_CHILDREN, typed.decision().chosen().id());
        assertEquals("conversations.cat.relationships", typed.decision().chosen().question());
        assertEquals("their_children", typed.decision().chosen().answer());
    }

    @Test
    @DisplayName("the player's own child opens nothing from that phrase, checkin_child included")
    void thePlayersOwnChildOpensNothing() {
        for (String age : List.of("toddler", "child", "teen")) {
            AgeGroup group = AgeGroup.valueOf(age.toUpperCase(java.util.Locale.ROOT));
            Typed typed = typed(KIDS, new Villager(group, age, PLAYER_IS_PARENT, false, Set.of("kids", "family")));
            assertEquals(IntentMatcher.Outcome.NONE, typed.decision().outcome(), age + " opened "
                    + (typed.decision().chosen() == null ? null : typed.decision().chosen().id()));
            assertTrue(gatedTopicMiss(typed), age);
        }
    }

    @Test
    @DisplayName("the player's own grown child with children of their own is asked about the grandchildren")
    void aGrownChildWithChildrenOpensTheirChildren() {
        Typed typed = typed(KIDS, new Villager(AgeGroup.ADULT, "adult", PLAYER_IS_PARENT, true, Set.of("kids", "family")));
        assertEquals(IntentMatcher.Outcome.MATCH, typed.decision().outcome());
        assertEquals(THEIR_CHILDREN, typed.decision().chosen().id(),
                "their_children needs children and no particular role");
    }
}
