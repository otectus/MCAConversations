package dev.otectus.mcaconversations.compat.mca;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Regression: MCA: Quests' {@code talk_about} objective is advanced from topic cooldown records, and a
 * topic's "again" branch — chosen precisely because the cooldown is still live — re-records it. Every
 * write used to count, so clicking the same topic three times finished "three heart-to-hearts".
 */
class TopicConversationSignalTest {

    private static final String COOLDOWN = "mcaconversations.cooldown.checkin_child";

    @Test
    @DisplayName("a topic cooldown written while none was running is one conversation")
    void freshCooldownCounts() {
        assertTrue(ConversationsMcaRegistrar.countsAsTopicConversation(COOLDOWN, false));
        assertTrue(ConversationsMcaRegistrar.countsAsTopicConversation(
                COOLDOWN + ".00000000-0000-0000-0000-000000000001", false), "a player-scoped cooldown too");
    }

    @Test
    @DisplayName("an again re-entry that refreshes a live cooldown is not another conversation")
    void refreshedCooldownDoesNotCount() {
        assertFalse(ConversationsMcaRegistrar.countsAsTopicConversation(COOLDOWN, true));
    }

    @Test
    @DisplayName("records that are not topic cooldowns never signal a topic")
    void otherRecordsNeverCount() {
        assertFalse(ConversationsMcaRegistrar.countsAsTopicConversation("mcaconversations.topic.dreams", false));
        assertFalse(ConversationsMcaRegistrar.countsAsTopicConversation("mca.greeted", false));
        assertFalse(ConversationsMcaRegistrar.countsAsTopicConversation(null, false));
    }
}
