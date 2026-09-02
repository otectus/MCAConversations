package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.conversation.BeatContract;
import dev.otectus.mcaconversations.debug.DialogueGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The arcs are a promise, and a promise has two halves (spec §9.7).
 *
 * <p>Tell a villager a fear and days later they raise it again, and what they say depends on what
 * you did about it. All of that worked before this release and none of it was <em>declared</em>: the
 * beat contract has carried a {@code callback} field since the metadata layer landed and not one
 * beat used it. So nothing checked that the line which advances an arc and the line which resumes it
 * are talking about the same thing — the two halves could drift apart and every existing lint would
 * still pass, because each half is individually well-formed.
 *
 * <p>This suite is the join. It reads which routes really advance an arc from the shipped results,
 * so the declaration cannot quietly fall behind the content it describes.
 */
class CallbackContractTest {

    private static final String SEP = System.lineSeparator();

    private static final String ARC_PREFIX = "conversations.arc.";

    /** arc -> beat ids whose route advances it, read from the dialogue. */
    private static Map<String, Set<String>> advancing;
    /** arc -> beat ids that open one of its pages. */
    private static Map<String, Set<String>> resuming;
    /** beat id -> its contract, for every beat in the corpus. */
    private static Map<String, BeatContract> beats;

    @BeforeAll
    static void load() {
        beats = new TreeMap<>();
        Map<String, String> byRoute = new LinkedHashMap<>();
        resuming = new TreeMap<>();
        for (BeatContract beat : ContentFixture.catalog().beats()) {
            beats.put(beat.id(), beat);
            byRoute.putIfAbsent(beat.say() + " -> " + beat.responseQuestion(), beat.id());
            if (beat.responseQuestion().startsWith(ARC_PREFIX)) {
                resuming.computeIfAbsent(arcOf(beat.responseQuestion()), a -> new TreeSet<>())
                        .add(beat.id());
            }
        }

        advancing = new TreeMap<>();
        DialogueGraph graph = ContentFixture.graph();
        for (String questionId : graph.questionIds()) {
            DialogueGraph.Question question = graph.question(questionId).orElseThrow();
            for (DialogueGraph.Answer answer : question.answers()) {
                for (DialogueGraph.Result result : answer.results()) {
                    JsonObject actions = result.actions();
                    JsonElement applied = actions.get("conversations_progress_apply");
                    if (applied == null) {
                        continue;
                    }
                    for (JsonElement entry : applied.isJsonArray()
                            ? applied.getAsJsonArray() : List.of(applied)) {
                        if (!entry.isJsonObject() || !entry.getAsJsonObject().has("arc")) {
                            continue;
                        }
                        String arc = entry.getAsJsonObject().get("arc").getAsString();
                        String spoken = ContentFixture.spokenPhrase(actions);
                        String route = (spoken == null ? "" : spoken)
                                + " -> " + (actions.has("next") ? actions.get("next").getAsString() : "");
                        String beat = byRoute.get(route);
                        if (beat != null) {
                            advancing.computeIfAbsent(arc, a -> new TreeSet<>()).add(beat);
                        }
                    }
                }
            }
        }
    }

    /** {@code conversations.arc.fears.plan.respond} -> {@code fears}. */
    private static String arcOf(String question) {
        String tail = question.substring(ARC_PREFIX.length());
        int dot = tail.indexOf('.');
        return dot < 0 ? tail : tail.substring(0, dot);
    }

    @Test
    @DisplayName("the corpus really has arcs to check")
    void thereAreArcs() {
        assertTrue(resuming.size() >= 5, "only " + resuming.size() + " arc(s) have resume pages,"
                + " which means this suite is looking in the wrong place");
        assertTrue(advancing.size() >= 5, "only " + advancing.size() + " arc(s) are advanced by any"
                + " route, which would mean the arcs cannot be started");
    }

    @Test
    @DisplayName("every line that advances an arc declares the callback it is making")
    void advancingBeatsDeclareTheirCallback() {
        List<String> problems = new ArrayList<>();
        advancing.forEach((arc, ids) -> ids.forEach(id -> {
            BeatContract beat = beats.get(id);
            if (beat == null) {
                return;
            }
            if (beat.callback().isEmpty()) {
                problems.add(id + ": advances the '" + arc + "' arc and declares no callback");
                return;
            }
            var fact = beat.callback().get().fact();
            if (!"arc".equals(fact.type()) || !arc.equals(fact.value())) {
                problems.add(id + ": advances the '" + arc + "' arc but its callback records "
                        + fact.type() + ":" + fact.value());
            }
        }));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("a callback names lines that can actually consume it")
    void resumesPointAtRealArcBeats() {
        List<String> problems = new ArrayList<>();
        beats.forEach((id, beat) -> beat.callback().ifPresent(callback -> {
            String arc = callback.fact().value();
            if (callback.resumes().isEmpty()) {
                problems.add(id + ": records " + callback.fact().type() + ":" + arc
                        + " and names nothing that may consume it");
            }
            for (String target : callback.resumes()) {
                BeatContract resume = beats.get(target);
                if (resume == null) {
                    problems.add(id + ": names resume beat '" + target + "', which does not exist");
                } else if (!resume.responseQuestion().startsWith(ARC_PREFIX)) {
                    problems.add(id + ": names '" + target + "' as a resume, but it opens "
                            + resume.responseQuestion() + ", which is not an arc page");
                } else if (!arcOf(resume.responseQuestion()).equals(arc)) {
                    problems.add(id + ": records the '" + arc + "' arc but names '" + target
                            + "', which belongs to the '" + arcOf(resume.responseQuestion()) + "' arc");
                }
            }
        }));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * Both directions, because either one alone is a half-built feature: an arc nothing advances can
     * never be reached, and an arc nothing resumes is a fact recorded for no reason.
     */
    @Test
    @DisplayName("every arc can be both started and resumed")
    void arcsAreWholeInBothDirections() {
        List<String> problems = new ArrayList<>();
        Set<String> all = new TreeSet<>(resuming.keySet());
        all.addAll(advancing.keySet());
        for (String arc : all) {
            if (!advancing.containsKey(arc)) {
                problems.add(arc + ": has resume pages and no route advances it — nothing can start it");
            }
            if (!resuming.containsKey(arc)) {
                problems.add(arc + ": is advanced by " + advancing.get(arc).size()
                        + " route(s) and has no resume pages — the fact is recorded for nothing");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * Spec §8.1 asks standard and deep topics for durable callbacks. This is the floor: a topic with
     * an arc must have more than one way into it, or the "callback" is a single scripted follow-up.
     */
    @Test
    @DisplayName("an arc has more than one way in and more than one way back")
    void arcsAreNotSingleScriptedFollowUps() {
        List<String> problems = new ArrayList<>();
        advancing.forEach((arc, ids) -> {
            if (ids.size() < 2) {
                problems.add(arc + ": only " + ids.size() + " line advances it, so the callback is a"
                        + " scripted sequel rather than something the player did");
            }
            Set<String> back = resuming.getOrDefault(arc, Set.of());
            if (back.size() < 2) {
                problems.add(arc + ": only " + back.size() + " line resumes it, so every player gets"
                        + " the same callback whatever they chose");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }
}
