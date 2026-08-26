package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.debug.ConversationTrace;
import dev.otectus.mcaconversations.debug.ConversationTraceExporter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Generates the editorial review artifact, and proves it is worth reviewing (spec §5.6, §13.1 test 6).
 *
 * <p>The release gate in §13.4 is a human reading every adjacency in the mod. That gate is only
 * enforceable if the reading material exists and is stable, so it is produced by the build rather
 * than by hand: {@code build/reports/conversations/adjacency.md} contains every question node, every
 * line that can open it, every button, every reaction and every onward page.
 *
 * <p>The determinism assertion matters as much as the content. The report is meant to be diffed
 * between revisions — "what did this change do to the conversation" — and a report that reshuffles
 * itself answers nothing.
 */
class ConversationTraceGenerationTest {

    private static final Path REPORT_DIR = Path.of("build/reports/conversations");
    private static final Path REPORT = REPORT_DIR.resolve("adjacency.md");
    private static final Path DEBT_REPORT = REPORT_DIR.resolve("uncontracted-routes.txt");
    private static final Path REPORT_PT = REPORT_DIR.resolve("adjacency.pt_br.md");

    private static ConversationTraceExporter exporter() {
        return new ConversationTraceExporter(ContentFixture.graph(), ContentFixture.catalog(), ContentFixture::lines);
    }

    @Test
    @DisplayName("the adjacency report generates, covers every node, and is byte-identical run to run")
    void reportGenerates() throws IOException {
        ConversationTraceExporter exporter = exporter();
        String first = exporter.renderMarkdown("MCA: Conversations — conversation adjacency report");
        String second = exporter.renderMarkdown("MCA: Conversations — conversation adjacency report");
        assertEquals(first, second, "the adjacency report must be deterministic so it can be diffed");

        Files.createDirectories(REPORT_DIR);
        Files.writeString(REPORT, first);
        Files.writeString(DEBT_REPORT, String.join(System.lineSeparator(), exporter.uncontractedRoutes()));

        List<ConversationTrace> traces = exporter.traceAll();
        assertEquals(ContentFixture.graph().questionIds().size(), traces.size(),
                "every shipped question node must appear in the report");
        assertFalse(first.isBlank(), "the adjacency report is empty");
    }

    @Test
    @DisplayName("every trace renders real sentences, not bare lang keys")
    void tracesCarryProse() {
        List<String> problems = new java.util.ArrayList<>();
        for (ConversationTrace trace : exporter().traceAll()) {
            if ("(no localization)".equals(trace.prompt()) && ownsPrompt(trace.questionId())) {
                problems.add(trace.questionId() + ": node prompt has no English line");
            }
            for (ConversationTrace.Inbound inbound : trace.inbound()) {
                if (inbound.variants().isEmpty()) {
                    problems.add(trace.questionId() + " <- " + inbound.sayKey() + ": say pool has no English lines");
                }
            }
            for (ConversationTrace.Button button : trace.buttons()) {
                if ("(no localization)".equals(button.label()) && !"(auto)".equals(button.name())) {
                    problems.add(trace.questionId() + "/" + button.name() + ": button has no English wording");
                }
            }
        }
        assertTrue(problems.isEmpty(), "The review artifact cannot be read where content is missing:"
                + System.lineSeparator() + String.join(System.lineSeparator(), problems));
    }

    /**
     * The same review artifact in Portuguese (spec phase 8: "run generated transcript review in
     * both locales").
     *
     * <p>Key and placeholder parity is asserted elsewhere and says nothing about whether the
     * Portuguese conversation <em>reads</em>: parity is satisfied by a key that exists. This renders
     * the whole adjacency report through the Portuguese lang file and fails on the two things a
     * reviewer cannot work around — a pool with no Portuguese lines at all, and a button with no
     * Portuguese wording. What is left is a document a Portuguese speaker can actually read end to
     * end, written to {@code build/reports/conversations/adjacency.pt_br.md} beside the English one.
     */
    @Test
    @DisplayName("the review artifact also renders, and reads, in Portuguese")
    void theReportRendersInPortuguese() throws IOException {
        Map<String, String> pt = ContentFixture.readLang(ContentFixture.LANG_PT);
        ConversationTraceExporter exporter = new ConversationTraceExporter(
                ContentFixture.graph(), ContentFixture.catalog(), key -> LangKeys.linesOf(pt, key));

        String report = exporter.renderMarkdown(
                "MCA: Conversations — relatório de adjacência");
        Files.createDirectories(REPORT_DIR);
        Files.writeString(REPORT_PT, report);
        assertFalse(report.isBlank(), "the Portuguese adjacency report is empty");

        List<String> problems = new java.util.ArrayList<>();
        for (ConversationTrace trace : exporter.traceAll()) {
            for (ConversationTrace.Inbound inbound : trace.inbound()) {
                if (inbound.variants().isEmpty()) {
                    problems.add(trace.questionId() + " <- " + inbound.sayKey()
                            + ": say pool has no Portuguese lines");
                }
            }
            for (ConversationTrace.Button button : trace.buttons()) {
                if ("(no localization)".equals(button.label()) && !"(auto)".equals(button.name())) {
                    problems.add(trace.questionId() + "/" + button.name()
                            + ": button has no Portuguese wording");
                }
            }
        }
        assertTrue(problems.isEmpty(), "The Portuguese review artifact cannot be read where content"
                + " is missing:" + System.lineSeparator()
                + String.join(System.lineSeparator(), problems));
    }

    /**
     * True when the node's villager prompt is ours to localize. {@code main} and {@code greet} are
     * MCA's own questions that we merge answers into, and {@code auto}/{@code silent} nodes never show
     * a prompt at all — in both cases a missing English line is correct, not a gap.
     */
    private static boolean ownsPrompt(String questionId) {
        if (!questionId.startsWith("conversations.")) {
            return false;
        }
        return ContentFixture.graph().question(questionId)
                .map(q -> !q.silent() && !q.auto())
                .orElse(false);
    }

    @Test
    @DisplayName("no beat contract describes a route the corpus no longer contains")
    void noOrphanedContracts() {
        List<String> orphans = exporter().orphanedBeats();
        assertTrue(orphans.isEmpty(), "Beat contracts left behind by content changes:"
                + System.lineSeparator() + String.join(System.lineSeparator(), orphans));
    }
}
