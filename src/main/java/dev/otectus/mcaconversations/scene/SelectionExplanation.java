package dev.otectus.mcaconversations.scene;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Why this scene and not the others (spec §9.6).
 *
 * <p>Built during selection and frozen onto the {@link ConversationPlan}, so
 * {@code /conversations trace last} can answer the only question that matters when a line reads
 * wrong: <em>why did this villager raise this subject now?</em>
 *
 * <p>Rejections carry the <b>first decisive reason</b> rather than every reason. A candidate that
 * fails four gates failed at the first one; listing the other three is noise that makes the real
 * answer harder to find.
 */
public final class SelectionExplanation {

    /** One candidate that did not survive, and why. */
    public record Rejection(String sceneId, String reason) {
    }

    /** One candidate that reached scoring, with its terms. */
    public record Finalist(String sceneId, int total, Map<String, Integer> terms) {
    }

    private final String purpose;
    private final List<Rejection> rejections = new ArrayList<>();
    private final List<Finalist> finalists = new ArrayList<>();
    private final Map<String, String> slotProvenance = new LinkedHashMap<>();

    private int indexed;
    private int afterHardFilters;
    private String selected = "";
    private String seedBasis = "";
    private String contextFingerprint = "";
    private String note = "";

    public SelectionExplanation(String purpose) {
        this.purpose = purpose == null ? "" : purpose;
    }

    public SelectionExplanation indexed(int count) {
        this.indexed = count;
        return this;
    }

    public SelectionExplanation afterHardFilters(int count) {
        this.afterHardFilters = count;
        return this;
    }

    /** Records a candidate and the first gate it failed. */
    public SelectionExplanation reject(String sceneId, String reason) {
        rejections.add(new Rejection(sceneId, reason));
        return this;
    }

    public SelectionExplanation finalist(String sceneId, SelectionScore score) {
        finalists.add(new Finalist(sceneId, score.total(), score.terms()));
        return this;
    }

    public SelectionExplanation selected(String sceneId) {
        this.selected = sceneId == null ? "" : sceneId;
        return this;
    }

    /** Records where a bound slot value came from — the §4.2 requirement that specificity has a source. */
    public SelectionExplanation slot(String name, String provenance) {
        if (name != null && provenance != null) {
            slotProvenance.put(name, provenance);
        }
        return this;
    }

    public SelectionExplanation seed(String basis) {
        this.seedBasis = basis == null ? "" : basis;
        return this;
    }

    public SelectionExplanation context(String fingerprint) {
        this.contextFingerprint = fingerprint == null ? "" : fingerprint;
        return this;
    }

    /** A free-text note for the one case a structured field would not cover, such as a fallback. */
    public SelectionExplanation note(String text) {
        this.note = text == null ? "" : text;
        return this;
    }

    public String purpose() {
        return purpose;
    }

    public String selectedScene() {
        return selected;
    }

    public List<Rejection> rejections() {
        return List.copyOf(rejections);
    }

    public List<Finalist> finalists() {
        return List.copyOf(finalists);
    }

    public Map<String, String> slotProvenance() {
        return Map.copyOf(slotProvenance);
    }

    public int indexedCount() {
        return indexed;
    }

    public int eligibleCount() {
        return afterHardFilters;
    }

    /** The whole explanation, as the trace exporter and the debug command print it. */
    public List<String> lines() {
        List<String> out = new ArrayList<>();
        out.add("purpose=" + purpose + " indexed=" + indexed + " eligible=" + afterHardFilters
                + " selected=" + (selected.isEmpty() ? "(none)" : selected));
        if (!seedBasis.isEmpty()) {
            out.add("  seed=" + seedBasis);
        }
        if (!contextFingerprint.isEmpty()) {
            out.add("  context=" + contextFingerprint);
        }
        for (Finalist finalist : finalists) {
            StringBuilder sb = new StringBuilder("  finalist ")
                    .append(finalist.sceneId()).append(" -> ").append(finalist.total());
            finalist.terms().forEach((term, value) ->
                    sb.append(' ').append(term).append(value >= 0 ? "+" : "").append(value));
            out.add(sb.toString());
        }
        for (Rejection rejection : rejections) {
            out.add("  rejected " + rejection.sceneId() + ": " + rejection.reason());
        }
        slotProvenance.forEach((name, provenance) -> out.add("  slot " + name + " <- " + provenance));
        if (!note.isEmpty()) {
            out.add("  note: " + note);
        }
        return List.copyOf(out);
    }

    @Override
    public String toString() {
        return String.join("\n", lines());
    }
}
