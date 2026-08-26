package dev.otectus.mcaconversations.debug;

import dev.otectus.mcaconversations.conversation.BeatCatalog;
import dev.otectus.mcaconversations.conversation.BeatContract;
import dev.otectus.mcaconversations.conversation.ReplyContract;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Turns the whole conversation graph into a reviewable document (spec §5.6, §15 Phase 0).
 *
 * <p>This is the artifact the release gate hangs on. Every question node is rendered with every line
 * that can open it, every button it offers, every reaction to those buttons and the page each
 * reaction leads to — so the editorial passes in §13.4 become reading, not archaeology.
 *
 * <p>Output is deterministic: nodes sorted by id, inbound lines sorted by say key, buttons in
 * authored order. That matters because the report is committed and diffed — a reviewer should see
 * exactly what this change did to the conversation, not a reshuffle.
 */
public final class ConversationTraceExporter {

    /** Marker the report uses for a route with no declared meaning. Counted in the summary. */
    public static final String UNCONTRACTED = "(uncontracted)";

    private final DialogueGraph graph;
    private final BeatCatalog beats;
    private final LineLookup lines;

    /** Resolves a base lang key to its variants, so a reviewer reads sentences rather than ids. */
    @FunctionalInterface
    public interface LineLookup {
        List<String> variants(String langKey);
    }

    public ConversationTraceExporter(DialogueGraph graph, BeatCatalog beats, LineLookup lines) {
        this.graph = graph;
        this.beats = beats;
        this.lines = lines;
    }

    /** Builds a trace for every question node, sorted by id. */
    public List<ConversationTrace> traceAll() {
        Map<String, List<DialogueGraph.Route>> inboundRoutes = inboundRoutes();
        List<ConversationTrace> out = new ArrayList<>();
        for (DialogueGraph.Question question : graph.allQuestions()) {
            out.add(trace(question, inboundRoutes.getOrDefault(question.id(), List.of())));
        }
        return List.copyOf(out);
    }

    /** question id → every speaking route that hands control to it. */
    public Map<String, List<DialogueGraph.Route>> inboundRoutes() {
        Map<String, List<DialogueGraph.Route>> byNext = new TreeMap<>();
        for (DialogueGraph.Route route : graph.speakingRoutes()) {
            byNext.computeIfAbsent(route.result().next().orElseThrow(), k -> new ArrayList<>()).add(route);
        }
        byNext.replaceAll((key, value) -> {
            value.sort((a, b) -> {
                int bySay = a.result().say().orElse("").compareTo(b.result().say().orElse(""));
                return bySay != 0 ? bySay : a.id().compareTo(b.id());
            });
            return List.copyOf(value);
        });
        return byNext;
    }

    private ConversationTrace trace(DialogueGraph.Question question, List<DialogueGraph.Route> inboundRoutes) {
        List<ConversationTrace.Inbound> inbound = new ArrayList<>();
        for (DialogueGraph.Route route : inboundRoutes) {
            String say = route.result().say().orElseThrow();
            inbound.add(new ConversationTrace.Inbound(
                    say,
                    lines.variants("dialogue." + say),
                    route.result().conditionSummary(),
                    beats.forRoute(say, question.id()),
                    route.id()));
        }

        List<ConversationTrace.Button> buttons = new ArrayList<>();
        for (DialogueGraph.Answer answer : question.answers()) {
            Optional<ReplyContract> contract = answer.isAuto()
                    ? Optional.empty()
                    : beats.reply(question.id(), answer.name());
            List<ConversationTrace.Reaction> reactions = new ArrayList<>();
            for (DialogueGraph.Result result : answer.results()) {
                String next = result.next().orElse("(none)");
                reactions.add(new ConversationTrace.Reaction(
                        result.say().orElse("(silent)"),
                        result.say().map(s -> lines.variants("dialogue." + s)).orElse(List.of()),
                        result.conditionSummary(),
                        next,
                        buttonLabels(next),
                        result.consequences(),
                        result.say().flatMap(s -> beats.forRoute(s, next))));
            }
            buttons.add(new ConversationTrace.Button(
                    answer.isAuto() ? "(auto)" : answer.name(),
                    first(lines.variants(answer.labelKey(question.id()))),
                    contract.map(c -> c.stance().key()).orElse(UNCONTRACTED),
                    contract.map(c -> c.tone().key()).orElse(UNCONTRACTED),
                    contract.isPresent(),
                    List.copyOf(reactions)));
        }

        return new ConversationTrace(question.id(),
                first(lines.variants(question.promptKey())),
                List.copyOf(inbound),
                List.copyOf(buttons));
    }

    private List<String> buttonLabels(String questionId) {
        return graph.question(questionId)
                .map(q -> q.answers().stream()
                        .map(a -> a.isAuto() ? "(auto)" : first(lines.variants(a.labelKey(questionId))))
                        .toList())
                .orElse(List.of());
    }

    private static String first(List<String> variants) {
        return variants.isEmpty() ? "(no localization)" : variants.get(0);
    }

    // --- Rendering ---------------------------------------------------------------

    /** Renders the full review artifact as Markdown. */
    public String renderMarkdown(String heading) {
        List<ConversationTrace> traces = traceAll();
        StringBuilder out = new StringBuilder();
        out.append("# ").append(heading).append('\n').append('\n');
        appendSummary(out, traces);
        for (ConversationTrace trace : traces) {
            appendTrace(out, trace);
        }
        return out.toString();
    }

    private void appendSummary(StringBuilder out, List<ConversationTrace> traces) {
        long uncontracted = traces.stream().mapToLong(ConversationTrace::uncontractedInboundCount).sum();
        long fanIn = traces.stream().filter(ConversationTrace::hasSemanticFanIn).count();
        long contractedButtons = traces.stream()
                .flatMap(t -> t.buttons().stream())
                .filter(ConversationTrace.Button::contracted)
                .count();
        long totalButtons = traces.stream().mapToLong(t -> t.buttons().size()).sum();

        out.append("Generated from the shipped dialogue corpus. Every question node appears once, with\n")
                .append("every line that can open it and every button it can offer.\n\n")
                .append("| Measure | Value |\n|---|---:|\n")
                .append("| Question nodes | ").append(traces.size()).append(" |\n")
                .append("| Speaking routes | ").append(graph.speakingRoutes().size()).append(" |\n")
                .append("| Uncontracted inbound routes | ").append(uncontracted).append(" |\n")
                .append("| Nodes with mixed inbound meanings | ").append(fanIn).append(" |\n")
                .append("| Contracted buttons | ").append(contractedButtons).append(" / ").append(totalButtons)
                .append(" |\n\n");

        out.append("## Semantic fan-in, worst first\n\n");
        List<ConversationTrace> hotspots = new ArrayList<>(traces.stream()
                .filter(t -> t.inbound().size() > 1)
                .toList());
        hotspots.sort((a, b) -> {
            int byCount = Integer.compare(b.inbound().size(), a.inbound().size());
            return byCount != 0 ? byCount : a.questionId().compareTo(b.questionId());
        });
        out.append("| Node | Inbound lines | Distinct declared meanings |\n|---|---:|---:|\n");
        for (ConversationTrace trace : hotspots) {
            out.append("| `").append(trace.questionId()).append("` | ")
                    .append(trace.inbound().size()).append(" | ")
                    .append(trace.inboundContractFamilies().size()).append(" |\n");
        }
        out.append('\n');
    }

    private void appendTrace(StringBuilder out, ConversationTrace trace) {
        out.append("---\n\n## `").append(trace.questionId()).append("`\n\n");
        out.append("> ").append(trace.prompt()).append("\n\n");

        out.append("### Reached from ").append(trace.inbound().size()).append(" line(s)\n\n");
        if (trace.inbound().isEmpty()) {
            out.append("_Entry node — reached from a category hub or MCA itself._\n\n");
        }
        for (ConversationTrace.Inbound line : trace.inbound()) {
            out.append("- **`").append(line.sayKey()).append("`** — ")
                    .append(line.contract()
                            .map(c -> c.npcAct().key() + " / " + c.polarity().key() + " / " + c.openness().key()
                                    + " / subject `" + c.subject() + "`")
                            .orElse("**" + UNCONTRACTED + "**"))
                    .append("\n  - selected when: ").append(line.selectionContext()).append('\n');
            for (String variant : line.variants()) {
                out.append("  - > ").append(variant).append('\n');
            }
        }
        out.append('\n');

        if (trace.hasSemanticFanIn()) {
            out.append("> **Semantic fan-in:** this page is opened by lines with ")
                    .append(trace.inboundContractFamilies().size())
                    .append(" different declared meanings. Every button below must be sensible after all of them.\n\n");
        }

        out.append("### Buttons\n\n");
        for (ConversationTrace.Button button : trace.buttons()) {
            out.append("#### `").append(button.name()).append("` — \"").append(button.label()).append("\"\n\n")
                    .append("stance: `").append(button.stance()).append("` · tone: `")
                    .append(button.tone()).append("`\n\n");
            for (ConversationTrace.Reaction reaction : button.reactions()) {
                out.append("- says **`").append(reaction.sayKey()).append("`** → `")
                        .append(reaction.next()).append("`")
                        .append(reaction.contract()
                                .map(c -> " _(" + c.outcome().map(o -> o.key()).orElse(c.npcAct().key()) + ")_")
                                .orElse(" _" + UNCONTRACTED + "_"))
                        .append('\n')
                        .append("  - selected when: ").append(reaction.selectionContext()).append('\n');
                if (!reaction.consequences().isEmpty()) {
                    out.append("  - writes: ").append(String.join(", ", reaction.consequences())).append('\n');
                }
                for (String variant : reaction.variants()) {
                    out.append("  - > ").append(variant).append('\n');
                }
                if (!reaction.nextButtons().isEmpty()) {
                    out.append("  - then offers: ")
                            .append(String.join(" | ", reaction.nextButtons()))
                            .append('\n');
                }
            }
            out.append('\n');
        }
    }

    /** The uncontracted {@code say -> next} routes, sorted — the migration-debt list (spec §15 Phase 1). */
    public List<String> uncontractedRoutes() {
        Map<String, String> byRoute = new LinkedHashMap<>();
        for (DialogueGraph.Route route : graph.speakingRoutes()) {
            String key = route.routeKey().orElseThrow();
            if (beats.forRoute(route.result().say().orElseThrow(), route.result().next().orElseThrow()).isEmpty()) {
                byRoute.put(key, route.id());
            }
        }
        return List.copyOf(new TreeMap<>(byRoute).keySet());
    }

    /** The declared beats that no shipped result actually plays — dead contracts. */
    public List<String> orphanedBeats() {
        List<String> live = graph.speakingRoutes().stream().map(r -> r.routeKey().orElseThrow()).toList();
        List<String> orphans = new ArrayList<>();
        for (BeatContract beat : beats.beats()) {
            if (!live.contains(BeatCatalog.route(beat.say(), beat.responseQuestion()))) {
                orphans.add(beat.id());
            }
        }
        orphans.sort(String::compareTo);
        return List.copyOf(orphans);
    }
}
