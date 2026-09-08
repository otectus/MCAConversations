package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The nine topics that carry {@code "ages": ["teen", "adult"]} are the reason this gate exists: MCA has
 * no {@code child} constraint, so their answers were offered to children and toddlers for nine
 * releases while the token meant to exclude them did nothing.
 *
 * <p>Built from the shipped catalog rather than a fixture, because what is being checked is that the
 * shipped rows really do refuse the ages they claim to.
 */
class TopicAgeGateTest {

    private static final Path TOPICS =
            Path.of("src/main/resources/data/mcaconversations/conversation_catalog/topics.json");

    /** The ages the nine entries must refuse, including the one we could not read. */
    private static final Set<AgeGroup> REFUSED =
            Set.of(AgeGroup.BABY, AgeGroup.TODDLER, AgeGroup.CHILD, AgeGroup.UNKNOWN);

    private static final Set<AgeGroup> ALLOWED = Set.of(AgeGroup.TEEN, AgeGroup.ADULT);

    @Test
    @DisplayName("teen-and-adult topics are refused to every younger age through the shared predicate")
    void teenAndAdultTopicsRefuseTheYoung() throws IOException {
        ConversationCatalog catalog = shippedCatalog();
        List<String> checked = new ArrayList<>();
        List<String> problems = new ArrayList<>();
        for (TopicEntry topic : catalog.topics()) {
            if (!topic.ages().equals(ALLOWED)) {
                continue;
            }
            checked.add(topic.id());
            for (AgeGroup age : REFUSED) {
                if (TopicAgeGate.allows(catalog, topic.entryQuestion(), topic.entryAnswer(), age)) {
                    problems.add(topic.id() + " is still offered to " + age);
                }
            }
            for (AgeGroup age : ALLOWED) {
                if (!TopicAgeGate.allows(catalog, topic.entryQuestion(), topic.entryAnswer(), age)) {
                    problems.add(topic.id() + " is refused to " + age + ", which it declares");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
        assertTrue(checked.size() >= 9,
                "expected the nine teen-and-adult topics, found " + checked);
    }

    @Test
    @DisplayName("an answer the catalog does not know is left exactly as MCA offered it")
    void unknownAnswersAreNotGated() throws IOException {
        ConversationCatalog catalog = shippedCatalog();
        assertTrue(TopicAgeGate.allows(catalog, "conversations.cat.village", "not_a_topic", AgeGroup.CHILD));
        assertTrue(TopicAgeGate.allows(catalog, null, null, AgeGroup.CHILD));
        assertTrue(TopicAgeGate.allows(null, "conversations.cat.village", "crown", AgeGroup.CHILD),
                "with no catalog loaded the gate is inert, never a blanket refusal");
    }

    @Test
    @DisplayName("an age we could not read passes no allow-list, however wide")
    void unknownAgeIsNeverAllowed() throws IOException {
        ConversationCatalog catalog = shippedCatalog();
        for (TopicEntry topic : catalog.topics()) {
            assertFalse(topic.allowsAge(AgeGroup.UNKNOWN), topic.id());
            assertFalse(topic.allowsAge(null), topic.id());
        }
    }

    private static ConversationCatalog shippedCatalog() throws IOException {
        JsonObject topics = JsonParser.parseString(Files.readString(TOPICS))
                .getAsJsonObject().getAsJsonObject("topics");
        List<TopicEntry> entries = new ArrayList<>();
        for (Map.Entry<String, JsonElement> topic : topics.entrySet()) {
            entries.add(TopicEntry.fromJson(topic.getKey(), topic.getValue().getAsJsonObject()));
        }
        return ConversationCatalog.build(entries);
    }
}
