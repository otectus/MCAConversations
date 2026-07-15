package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.Normalizer.NormalizedMessage;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pure dispatcher helpers from the default-on polish pass: the engagement cue that keeps sticky
 * targeting from turning player-to-player chat into confused villager lines (spec §5 tier 2), and the
 * eligible-topic ordering behind the dynamic hint (spec §11 step 2).
 */
class ChatModeDispatcherHelperTest {

    private static NormalizedMessage n(String raw) {
        return Normalizer.normalize(raw, SynonymTable.EMPTY);
    }

    // --- looksEngaged: is the message plausibly aimed at the villager? -------------

    @Test
    void plainPlayerChatterIsNotEngaged() {
        assertFalse(ChatModeDispatcher.looksEngaged(n("lets go mining")));
        assertFalse(ChatModeDispatcher.looksEngaged(n("gg wp everyone")));
        assertFalse(ChatModeDispatcher.looksEngaged(n("meet me at the farm")));
    }

    @Test
    void questionsAreEngaged() {
        assertTrue(ChatModeDispatcher.looksEngaged(n("how are you")), "leading question word");
        assertTrue(ChatModeDispatcher.looksEngaged(n("what about the harvest?")), "question mark");
    }

    @Test
    void secondPersonIsEngaged() {
        assertTrue(ChatModeDispatcher.looksEngaged(n("you could face it")));
        assertTrue(ChatModeDispatcher.looksEngaged(n("tell me about your work")));
    }

    // --- topic suffix + ordering for the dynamic hint ------------------------------

    @Test
    void topicSuffixIsTheLastSegment() {
        assertEquals("chitchat", ChatModeDispatcher.topicSuffix("conversations.cat.chitchat"));
        assertEquals("us", ChatModeDispatcher.topicSuffix("conversations.us"));
        assertEquals("greet", ChatModeDispatcher.topicSuffix("greet"));
    }

    @Test
    void shippedHubsKeepTheirFixedOrder() {
        List<String> ordered = ChatModeDispatcher.orderedTopics(
                Set.of("personal", "village", "chitchat", "us", "profession"));
        assertEquals(List.of("chitchat", "profession", "village", "personal", "us"), ordered);
    }

    @Test
    void datapackTopicsFollowAlphabetically() {
        List<String> ordered = ChatModeDispatcher.orderedTopics(
                Set.of("zebra_care", "chitchat", "alchemy", "family"));
        assertEquals(List.of("chitchat", "family", "alchemy", "zebra_care"), ordered);
    }
}
