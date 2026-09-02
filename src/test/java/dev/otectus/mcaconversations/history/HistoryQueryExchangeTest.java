package dev.otectus.mcaconversations.history;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.OutcomeFamily;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The condition a callback is written against.
 *
 * <p>{@code conversations_recent} already answers "how long since the ink came up".
 * {@code conversations_exchange} answers the question a callback actually needs: "and when it did,
 * did they tell me to save it, and did I take that". The tests that matter most here are the ones
 * where it must <em>not</em> match — a condition that fires too readily does not produce a vague
 * line, it produces a villager confidently recalling a conversation that never happened.
 */
class HistoryQueryExchangeTest {

    private static HistoryQuery.Exchange parse(String json) {
        return HistoryQuery.Exchange.fromJson(JsonParser.parseString(json).getAsJsonObject());
    }

    private static Optional<PairHistory> pairWith(StanceEchoRecord... decisions) {
        PairHistory pair = new PairHistory();
        for (StanceEchoRecord decision : decisions) {
            pair.recordExchange(decision);
        }
        return Optional.of(pair);
    }

    private static final StanceEchoRecord SAVED_THE_INK = new StanceEchoRecord(
            StanceFamily.PRACTICAL_HELP, OutcomeFamily.ACCEPTED, "work.ink", 100);

    @Test
    void namingTheWholeDecisionMatchesIt() {
        HistoryQuery.Exchange query = parse("""
                {"subject": "work.ink", "stance": "practical_help", "outcome": "accepted",
                 "within_days": 14}""");
        assertTrue(query.isValid());
        assertTrue(query.matches(pairWith(SAVED_THE_INK), 105));
    }

    @Test
    void anyOneTermIsEnoughToAsk() {
        assertTrue(parse("{\"subject\": \"work.ink\"}").matches(pairWith(SAVED_THE_INK), 105));
        assertTrue(parse("{\"stance\": \"practical_help\"}").matches(pairWith(SAVED_THE_INK), 105));
        assertTrue(parse("{\"outcome\": \"accepted\"}").matches(pairWith(SAVED_THE_INK), 105));
    }

    @Test
    void theTermsIntersectRatherThanAccumulate() {
        // The right subject with the wrong stance is not a match. Without this, "when we talked about
        // the ink you told me to save it" would fire for a player who said the opposite.
        assertFalse(parse("""
                {"subject": "work.ink", "stance": "dismissal"}""")
                .matches(pairWith(SAVED_THE_INK), 105));
        assertFalse(parse("""
                {"subject": "work.ink", "outcome": "hurt"}""")
                .matches(pairWith(SAVED_THE_INK), 105));
        assertFalse(parse("""
                {"subject": "fears.dark", "stance": "practical_help"}""")
                .matches(pairWith(SAVED_THE_INK), 105));
    }

    @Test
    void aDecisionOlderThanTheWindowIsNotNamed() {
        HistoryQuery.Exchange query = parse("{\"subject\": \"work.ink\", \"within_days\": 7}");
        assertTrue(query.matches(pairWith(SAVED_THE_INK), 107), "exactly at the edge still counts");
        assertFalse(query.matches(pairWith(SAVED_THE_INK), 108));
    }

    @Test
    void theDefaultWindowIsThirtyDays() {
        HistoryQuery.Exchange query = parse("{\"subject\": \"work.ink\"}");
        assertEquals(HistoryQuery.Exchange.DEFAULT_WINDOW_DAYS, query.withinDays());
        assertTrue(query.matches(pairWith(SAVED_THE_INK), 130));
        assertFalse(query.matches(pairWith(SAVED_THE_INK), 131));
    }

    @Test
    void askingWithoutASubjectSearchesEveryDecision() {
        StanceEchoRecord pushedBack = new StanceEchoRecord(
                StanceFamily.RESPECTFUL_DISAGREEMENT, OutcomeFamily.QUALIFIED, "village.frost", 100);
        assertTrue(parse("{\"stance\": \"respectful_disagreement\"}")
                .matches(pairWith(SAVED_THE_INK, pushedBack), 102));
    }

    @Test
    void namingNothingMatchesNothing() {
        // A condition with no discriminator would fire for every player who has ever finished a
        // conversation. That is not a callback, it is a leak, so it is refused at parse time.
        assertFalse(parse("{}").isValid());
        assertFalse(parse("{}").matches(pairWith(SAVED_THE_INK), 100));
        assertFalse(parse("{\"within_days\": 5}").isValid());
    }

    @Test
    void aMisspeltStanceOrOutcomeIsRefusedRatherThanIgnored() {
        // Dropping the term it could not read would silently widen the query: "you told me to save
        // the ink" would become "we talked about the ink at all".
        assertFalse(parse("{\"subject\": \"work.ink\", \"stance\": \"helpfulness\"}").isValid());
        assertFalse(parse("{\"subject\": \"work.ink\", \"outcome\": \"delighted\"}").isValid());
        assertFalse(parse("{\"subject\": \"work.ink\", \"stance\": \"helpfulness\"}")
                .matches(pairWith(SAVED_THE_INK), 100));
    }

    @Test
    void notInvertsTheAnswer() {
        assertFalse(parse("{\"subject\": \"work.ink\", \"not\": true}")
                .matches(pairWith(SAVED_THE_INK), 105));
        assertTrue(parse("{\"subject\": \"fears.dark\", \"not\": true}")
                .matches(pairWith(SAVED_THE_INK), 105));
    }

    @Test
    void anEmptyHistoryNeverMatches_butNotStillDoes() {
        assertFalse(parse("{\"subject\": \"work.ink\"}").matches(Optional.empty(), 100));
        assertTrue(parse("{\"subject\": \"work.ink\", \"not\": true}").matches(Optional.empty(), 100));
    }

    @Test
    void subjectsAreMatchedCaseInsensitively() {
        JsonObject json = JsonParser.parseString("{\"subject\": \"Work.INK\"}").getAsJsonObject();
        assertTrue(HistoryQuery.Exchange.fromJson(json).matches(pairWith(SAVED_THE_INK), 105));
    }

    @Test
    void aNullJsonObjectIsRefused() {
        assertFalse(HistoryQuery.Exchange.fromJson(null).isValid());
    }
}
