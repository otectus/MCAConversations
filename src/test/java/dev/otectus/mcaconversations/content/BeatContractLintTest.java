package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.BeatCatalog;
import dev.otectus.mcaconversations.conversation.BeatContract;
import dev.otectus.mcaconversations.conversation.Openness;
import dev.otectus.mcaconversations.conversation.ReplyContract;
import dev.otectus.mcaconversations.conversation.SemanticFact;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import dev.otectus.mcaconversations.debug.DialogueGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * The semantic contract lint (spec §13.1 test 1, invariants §5.5).
 *
 * <p>Everything the old suites checked was structural: does this key resolve, is this node reachable,
 * does this path stay inside its heart budget. All of it passed while the conversation itself made no
 * sense, because nothing in the data said what any line <em>meant</em>. These tests read the beat
 * contracts and ask the questions that were previously unaskable — chiefly this one:
 *
 * <blockquote>Given every villager line that can open this page, is every button on it a sensible
 * thing for the player to say?</blockquote>
 *
 * <p>Migration is incremental by design (spec §15 Phase 1). Routes that have not been contracted yet
 * are listed in {@code legacy_unverified_routes.txt}; that ledger may only shrink, and a route that
 * leaves it can never come back.
 */
class BeatContractLintTest {

    private static final String SEP = System.lineSeparator();

    /** The migration-debt ledger: routes still allowed to have no declared meaning. */
    private static final Path DEBT = TestPaths.of("src/test/resources/legacy_unverified_routes.txt");

    private static DialogueGraph graph;
    private static BeatCatalog catalog;
    private static Map<String, String> lang;
    private static Set<String> debt;
    private static Set<String> hubs;

    @BeforeAll
    static void load() throws IOException {
        graph = ContentFixture.graph();
        catalog = ContentFixture.catalog();
        lang = ContentFixture.english();
        hubs = ContentFixture.hubQuestions();
        debt = new TreeSet<>();
        if (Files.exists(DEBT)) {
            for (String line : Files.readAllLines(DEBT)) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty() && !trimmed.startsWith("#")) {
                    debt.add(trimmed);
                }
            }
        }
    }

    // ------------------------------------------------------------------
    // The contracts themselves
    // ------------------------------------------------------------------

    @Test
    @DisplayName("every shipped beat and reply contract parses")
    void contractsParse() {
        List<String> problems = ContentFixture.beatProblems();
        assertTrue(problems.isEmpty(),
                "Beat metadata failed to parse:" + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("every beat names a say pool and a response question that exist")
    void beatsPointAtRealContent() {
        List<String> problems = new ArrayList<>();
        for (BeatContract beat : catalog.beats()) {
            if (!LangKeys.hasLine(lang, "dialogue." + beat.say())) {
                problems.add(beat.id() + ": say '" + beat.say() + "' has no English line");
            }
            if (graph.question(beat.responseQuestion()).isEmpty()) {
                problems.add(beat.id() + ": response_question '" + beat.responseQuestion() + "' is not a shipped node");
            }
        }
        assertTrue(problems.isEmpty(),
                "Beat contracts reference content that does not exist:" + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("every beat contract is actually played by a shipped result")
    void noOrphanedBeats() {
        Set<String> live = new LinkedHashSet<>();
        for (DialogueGraph.Route route : graph.speakingRoutes()) {
            live.add(route.routeKey().orElseThrow());
        }
        List<String> orphans = new ArrayList<>();
        for (BeatContract beat : catalog.beats()) {
            if (!live.contains(BeatCatalog.route(beat.say(), beat.responseQuestion()))) {
                orphans.add(beat.id() + " (" + BeatCatalog.route(beat.say(), beat.responseQuestion()) + ")");
            }
        }
        assertTrue(orphans.isEmpty(),
                "These beats describe a route no result plays — the content moved and the contract did not:"
                        + SEP + String.join(SEP, orphans));
    }

    @Test
    @DisplayName("every reply contract binds to a button that exists")
    void repliesBindToRealButtons() {
        List<String> problems = new ArrayList<>();
        for (ReplyContract reply : catalog.replies()) {
            Optional<DialogueGraph.Question> question = graph.question(reply.question());
            if (question.isEmpty()) {
                problems.add(reply.key() + ": question does not exist");
                continue;
            }
            if (question.get().answer(reply.answer()).isEmpty()) {
                problems.add(reply.key() + ": question has no answer named '" + reply.answer() + "'");
            }
        }
        assertTrue(problems.isEmpty(),
                "Reply contracts bind to buttons that do not exist:" + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("a result that names a beat names one that exists, and describes its own route")
    void declaredBeatsMatchTheirRoute() {
        List<String> problems = new ArrayList<>();
        for (DialogueGraph.Route route : graph.routes()) {
            Optional<String> declared = route.result().declaredBeat();
            if (declared.isEmpty()) {
                continue;
            }
            Optional<BeatContract> beat = catalog.beat(declared.get());
            if (beat.isEmpty()) {
                problems.add(route.id() + ": names beat '" + declared.get() + "', which no file declares");
                continue;
            }
            String expected = route.routeKey().orElse("(no say/next)");
            String actual = BeatCatalog.route(beat.get().say(), beat.get().responseQuestion());
            if (!actual.equals(expected)) {
                problems.add(route.id() + ": names beat '" + declared.get() + "' (" + actual
                        + ") but this result plays " + expected);
            }
        }
        assertTrue(problems.isEmpty(),
                "conversations_session beat ids disagree with the results that carry them:"
                        + SEP + String.join(SEP, problems));
    }

    // ------------------------------------------------------------------
    // Coverage and migration debt
    // ------------------------------------------------------------------

    @Test
    @DisplayName("every speaking route is contracted or explicitly listed as migration debt")
    void everyRouteIsAccountedFor() {
        List<String> undeclared = new ArrayList<>();
        for (DialogueGraph.Route route : graph.speakingRoutes()) {
            String key = route.routeKey().orElseThrow();
            if (catalog.forRoute(route.result().say().orElseThrow(), route.result().next().orElseThrow()).isPresent()) {
                continue;
            }
            if (!debt.contains(key)) {
                undeclared.add(key + "   (from " + route.id() + ")");
            }
        }
        assertTrue(undeclared.isEmpty(),
                "New content must declare what it means. These say/next routes have no beat contract and are"
                        + " not listed as pre-existing migration debt:" + SEP + String.join(SEP, undeclared));
    }

    @Test
    @DisplayName("the migration-debt ledger only ever shrinks")
    void debtLedgerIsCurrent() {
        Set<String> live = new TreeSet<>();
        for (DialogueGraph.Route route : graph.speakingRoutes()) {
            live.add(route.routeKey().orElseThrow());
        }
        List<String> stale = new ArrayList<>();
        for (String entry : debt) {
            if (!live.contains(entry)) {
                stale.add(entry + " — route no longer exists");
            } else if (catalog.forRoute(entry.split(" -> ")[0], entry.split(" -> ")[1]).isPresent()) {
                stale.add(entry + " — now contracted; delete this line");
            }
        }
        assertTrue(stale.isEmpty(),
                "legacy_unverified_routes.txt lists routes that are done or gone. Remove them so the ledger"
                        + " keeps measuring real debt:" + SEP + String.join(SEP, stale));
    }

    @Test
    @DisplayName("a node is migrated all at once — no page half-contracted")
    void noHalfMigratedNodes() {
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, List<DialogueGraph.Route>> entry : inboundByQuestion().entrySet()) {
            if (hubs.contains(entry.getKey())) {
                continue;
            }
            List<String> contracted = new ArrayList<>();
            List<String> uncontracted = new ArrayList<>();
            for (DialogueGraph.Route route : entry.getValue()) {
                String say = route.result().say().orElseThrow();
                if (catalog.forRoute(say, entry.getKey()).isPresent()) {
                    contracted.add(say);
                } else {
                    uncontracted.add(say);
                }
            }
            if (!contracted.isEmpty() && !uncontracted.isEmpty()) {
                problems.add(entry.getKey() + ": contracted " + contracted + " but left " + uncontracted
                        + " undeclared — the page's buttons cannot be checked until every route into it is declared");
            }
        }
        assertTrue(problems.isEmpty(),
                "Half-migrated response pages:" + SEP + String.join(SEP, problems));
    }

    // ------------------------------------------------------------------
    // The invariants that make a page coherent (spec §5.5)
    // ------------------------------------------------------------------

    @Test
    @DisplayName("every button on a page is allowed by every line that can open it")
    void everyButtonFitsEveryInboundLine() {
        List<String> problems = new ArrayList<>();
        for (String questionId : fullyContractedQuestions()) {
            List<BeatContract> inbound = catalog.inbound(questionId);
            DialogueGraph.Question question = graph.question(questionId).orElseThrow();
            for (DialogueGraph.Answer answer : question.answers()) {
                if (answer.isAuto()) {
                    continue;
                }
                Optional<ReplyContract> reply = catalog.reply(questionId, answer.name());
                if (reply.isEmpty()) {
                    problems.add(questionId + "/" + answer.name()
                            + ": page is contracted but this button declares no stance");
                    continue;
                }
                StanceFamily stance = reply.get().stance();
                for (BeatContract beat : inbound) {
                    if (!reply.get().accepts(beat)) {
                        problems.add(questionId + "/" + answer.name() + ": does not claim to answer beat '"
                                + beat.id() + "', which can open this page");
                        continue;
                    }
                    if (beat.forbiddenStances().contains(stance)) {
                        problems.add(questionId + "/" + answer.name() + ": stance '" + stance.key()
                                + "' is forbidden after beat '" + beat.id() + "'");
                    } else if (!beat.allowedStances().contains(stance)) {
                        problems.add(questionId + "/" + answer.name() + ": stance '" + stance.key()
                                + "' is not among the stances beat '" + beat.id() + "' allows "
                                + beat.allowedStances().stream().map(StanceFamily::key).sorted().toList());
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(),
                "Buttons offered after lines they do not answer:" + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("a button never presupposes something the villager may not have said")
    void everyButtonsFactsAreEstablished() {
        List<String> problems = new ArrayList<>();
        for (String questionId : fullyContractedQuestions()) {
            List<BeatContract> inbound = catalog.inbound(questionId);
            for (ReplyContract reply : catalog.repliesFor(questionId)) {
                if (reply.requiresFacts().isEmpty()) {
                    continue;
                }
                for (BeatContract beat : inbound) {
                    if (!reply.accepts(beat)) {
                        continue;
                    }
                    Set<SemanticFact> missing = new TreeSet<>(reply.requiresFacts());
                    missing.removeAll(beat.facts());
                    if (!missing.isEmpty()) {
                        problems.add(reply.key() + ": needs " + missing + " but beat '" + beat.id()
                                + "' establishes only " + new TreeSet<>(beat.facts()));
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(),
                "Buttons that refer to something no line established — the \"I'll bring you some\" bug:"
                        + SEP + String.join(SEP, problems));
    }

    /**
     * Spec §5.5 rule 12 bans a page reachable from both an invitation and a closed subject
     * <em>without explicitly partitioning its offered answers</em>. The partition that matters is not
     * a structural one: it is whether every button on the page is something the closing line leaves
     * room for. A page offering only "press anyway", "back off" and "leave" is right after a partial
     * answer and right after a refusal, and splitting it would produce two identical pages.
     *
     * <p>So the mix is allowed exactly when the closing line permits every stance the page offers,
     * and reported otherwise — naming the buttons that do not fit, which are the reason to split.
     */
    @Test
    @DisplayName("an open subject and a closed one never share a page that assumes warmth")
    void opennessIsNotMixed() {
        List<String> problems = new ArrayList<>();
        for (String questionId : fullyContractedQuestions()) {
            List<BeatContract> closed = catalog.inbound(questionId).stream()
                    .filter(BeatContract::isClosed).toList();
            boolean anyOpen = catalog.inbound(questionId).stream().anyMatch(b -> !b.isClosed());
            if (closed.isEmpty() || !anyOpen) {
                continue;
            }
            for (BeatContract shut : closed) {
                List<String> unfit = new ArrayList<>();
                for (ReplyContract reply : catalog.repliesFor(questionId)) {
                    if (reply.accepts(shut) && !shut.permits(reply.stance())) {
                        unfit.add(reply.answer() + " (" + reply.stance().key() + ")");
                    }
                }
                if (!unfit.isEmpty()) {
                    problems.add(questionId + ": also opened by '" + shut.id() + "' ("
                            + shut.openness().key() + "), which leaves no room for " + unfit
                            + " — split it; MCA shows the same buttons either way");
                }
            }
        }
        assertTrue(problems.isEmpty(),
                "Pages shared by lines that invite more and lines that shut the subject:"
                        + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("every contracted page offers a way out")
    void everyContractedPageHasADoor() {
        List<String> problems = new ArrayList<>();
        for (String questionId : fullyContractedQuestions()) {
            DialogueGraph.Question question = graph.question(questionId).orElseThrow();
            if (question.auto()) {
                continue;
            }
            boolean hasExit = question.answers().stream()
                    .filter(a -> !a.isAuto())
                    .anyMatch(a -> catalog.reply(questionId, a.name()).map(ReplyContract::exit).orElse(false));
            if (!hasExit) {
                problems.add(questionId + ": no button declares the exit stance");
            }
        }
        assertTrue(problems.isEmpty(),
                "Contracted pages the player cannot leave:" + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("a closing line never opens a page that keeps probing the same subject")
    void closedSubjectsAreRespected() {
        List<String> problems = new ArrayList<>();
        for (BeatContract beat : catalog.beats()) {
            if (!beat.isClosed() || hubs.contains(beat.responseQuestion())) {
                continue;
            }
            for (ReplyContract reply : catalog.repliesFor(beat.responseQuestion())) {
                if (!reply.accepts(beat)) {
                    continue;
                }
                if (beat.openness() == Openness.ENDS_CONVERSATION && !reply.exit()) {
                    problems.add(beat.id() + " ends the conversation but '" + reply.key()
                            + "' keeps it going");
                }
                if (!dev.otectus.mcaconversations.conversation.OutcomeFamily.mayFollowRupture(reply.stance())) {
                    problems.add(beat.id() + " (" + beat.openness().key() + ") is followed by '" + reply.key()
                            + "' with stance '" + reply.stance().key()
                            + "' — a closed subject may only be met with repair, respect or leaving");
                }
            }
        }
        assertTrue(problems.isEmpty(),
                "Boundaries the graph walks straight through:" + SEP + String.join(SEP, problems));
    }

    // ------------------------------------------------------------------
    // Helpers
    // ------------------------------------------------------------------

    /** question id → every speaking route that leads to it. */
    private static Map<String, List<DialogueGraph.Route>> inboundByQuestion() {
        Map<String, List<DialogueGraph.Route>> byNext = new TreeMap<>();
        for (DialogueGraph.Route route : graph.speakingRoutes()) {
            byNext.computeIfAbsent(route.result().next().orElseThrow(), k -> new ArrayList<>()).add(route);
        }
        return byNext;
    }

    /**
     * Questions whose every inbound route is contracted. Only these can be checked — a page still fed
     * by an undeclared line has no complete answer to "what can open this?".
     *
     * <p>Category hubs are excluded. Their buttons are not replies to anything: they are the list of
     * subjects the player may raise next, and "shall we talk about the weather" is a sensible thing to
     * offer after any line at all. Holding them to adjacency would make every topic's ending
     * incompatible with every other topic's ending, which is not a real defect.
     */
    private static Set<String> fullyContractedQuestions() {
        Set<String> out = new TreeSet<>();
        for (Map.Entry<String, List<DialogueGraph.Route>> entry : inboundByQuestion().entrySet()) {
            if (hubs.contains(entry.getKey())) {
                continue;
            }
            boolean all = entry.getValue().stream().allMatch(route ->
                    catalog.forRoute(route.result().say().orElseThrow(), entry.getKey()).isPresent());
            if (all && !entry.getValue().isEmpty()) {
                out.add(entry.getKey());
            }
        }
        return out;
    }
}
