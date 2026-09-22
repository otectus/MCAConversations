package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Navigation is not conversation (Stability spec §8.5). */
class MeaningfulExchangeTest {

    @Test
    void menusAndBackEarnNothing() {
        assertFalse(MeaningfulExchange.counts("conversations", "cat_chitchat"));
        assertFalse(MeaningfulExchange.counts("main", "conversations"));
        assertFalse(MeaningfulExchange.counts("conversations.cat.chitchat", "back"));
        assertFalse(MeaningfulExchange.counts(null, "day"));
        assertFalse(MeaningfulExchange.counts("conversations.topic.day.respond", null));
    }

    @Test
    void aReplyOnAnOwnedQuestionCounts() {
        assertTrue(MeaningfulExchange.counts("conversations.topic.day.respond", "sympathize"));
        assertTrue(MeaningfulExchange.counts("conversations.cat.chitchat", "day"));
    }

    @Test
    void anUnownedQuestionThatIsNotACatalogStarterDoesNot() {
        assertFalse(MeaningfulExchange.counts("somepack.shop", "buy"));
    }
}
