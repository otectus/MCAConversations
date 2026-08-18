package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code conversations_session} condition. 114 shipped results write a branch and nothing ever
 * read one back, so this is the read half arriving several releases after the write half.
 */
class SessionQueryTest {

    private static JsonObject json(String raw) {
        return JsonParser.parseString(raw).getAsJsonObject();
    }

    private static ConversationSession session(String topic, String branch) {
        ConversationSession s = new ConversationSession(UUID.randomUUID(), 0L);
        s.beginTopic(topic, DepthClass.QUICK, 0L);
        if (branch != null) {
            s.setBranch(branch);
        }
        return s;
    }

    @Test
    void anEmptyQueryMatchesAnyOpenTopic() {
        assertTrue(SessionQuery.fromJson(json("{}")).matches(session("day", "rough")));
        assertTrue(SessionQuery.fromJson(json("{}")).matches(session("fears", null)));
    }

    @Test
    void nothingMatchesWhenNoSessionIsOpen() {
        assertFalse(SessionQuery.fromJson(json("{}")).matches(null));
        assertFalse(SessionQuery.fromJson(json("{\"topic\":\"day\"}")).matches(null));
    }

    @Test
    void topicAndBranchBothHaveToMatchWhenBothAreNamed() {
        SessionQuery query = SessionQuery.fromJson(json("{\"topic\":\"day\",\"branch\":\"rough\"}"));
        assertTrue(query.matches(session("day", "rough")));
        assertFalse(query.matches(session("day", "good")), "same topic, wrong branch");
        assertFalse(query.matches(session("checkin", "rough")), "same branch, wrong topic");
        assertFalse(query.matches(session("day", null)), "no branch set at all");
    }

    @Test
    void eitherFieldAloneIsEnough() {
        assertTrue(SessionQuery.fromJson(json("{\"branch\":\"rough\"}")).matches(session("checkin", "rough")),
                "a branch query spans topics on purpose — that is what lets siblings share a node");
        assertTrue(SessionQuery.fromJson(json("{\"topic\":\"day\"}")).matches(session("day", "good")));
    }

    @Test
    void matchingIsCaseInsensitiveBecauseAuthorsAreNot() {
        assertTrue(SessionQuery.fromJson(json("{\"topic\":\"DAY\"}")).matches(session("day", null)));
    }

    @Test
    void blankFieldsAreAnAuthoringErrorRatherThanAWildcard() {
        assertThrows(IllegalArgumentException.class,
                () -> SessionQuery.fromJson(json("{\"topic\":\"  \"}")));
    }
}
