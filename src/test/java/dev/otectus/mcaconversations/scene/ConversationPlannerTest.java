package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.context.ContextKeys;
import dev.otectus.mcaconversations.context.ContextSnapshotBuilder;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.conversation.DepthClass;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ConversationPlannerTest {
    private static ConversationContextSnapshot snapshot(String profession, boolean spouse, boolean played) {
        return new ContextSnapshotBuilder().clock(100, 4)
                .put(ContextKeys.WORK_PROFESSION_ID, profession)
                .put(ContextKeys.PLAYER_IS_SPOUSE, spouse)
                .put(ContextKeys.PLAYER_HEARTS, played ? 52 : 49)
                .put(ContextKeys.PLAYER_RELATIONSHIP_BAND, played ? "friend" : "acquaintance")
                .put(ContextKeys.TIME_DAYS_SINCE_LAST_TALK, played ? 0L : 5L)
                .put(ContextKeys.TIME_DAYS_SINCE_FIRST_MET, played ? 0L : 365L)
                .put(ContextKeys.TIME_ABSENCE_BAND, played ? "recent" : "long")
                .put(ContextKeys.NARRATIVE_ACTIVE_EPISODES, played ? List.of("work.repair") : List.of())
                .put(ContextKeys.NARRATIVE_RECENT_SUBJECTS, played ? List.of("work") : List.of())
                .build();
    }

    @Test
    void ownOpeningEffectsDoNotRerollButChangedReferentsAndEndedTopicsDo() {
        UUID player = UUID.randomUUID();
        ConversationSession session = ConversationSessions.beginTopic(player, UUID.randomUUID(),
                "work", DepthClass.DEEP, 100);
        try {
            ConversationContextSnapshot original = snapshot("minecraft:mason", false, false);
            session.setSnapshot(original);
            session.setPlan(new ConversationPlan("work.test", "question", "beat", Map.of(),
                    Optional.empty(), Optional.empty(), original.fingerprint(), "nonce",
                    new SelectionExplanation("topic")));
            ConversationContextSnapshot afterOpening = snapshot("minecraft:mason", false, true);
            assertNotEquals(original.fingerprint(), afterOpening.fingerprint(), "full selection seed changes");
            assertTrue(ConversationPlanner.canReusePlan(session, "work", afterOpening));
            assertFalse(ConversationPlanner.canReusePlan(session, "food", afterOpening));
            assertFalse(ConversationPlanner.canReusePlan(session, "work", snapshot("minecraft:farmer", false, true)));
            assertFalse(ConversationPlanner.canReusePlan(session, "work", snapshot("minecraft:mason", true, true)));
            session.endTopic();
            assertFalse(ConversationPlanner.canReusePlan(session, "work", afterOpening));
        } finally {
            ConversationSessions.clear(player,
                    dev.otectus.mcaconversations.conversation.CloseReason.COMPLETED);
        }
    }
}
