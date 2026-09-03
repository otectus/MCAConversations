package dev.otectus.mcaconversations.scene;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The additive score behind one candidate, with every non-zero term kept (spec §9.2, §9.6).
 *
 * <p>Terms are stored rather than summed away because of §25.5: a selector that cannot explain its
 * choice is not allowed to exist. "The librarian raised the damaged ledger because its episode
 * changed state (+18) and you two have discussed it before (+6), beating the weather remark that was
 * penalised for shape recency (−8)" is a debuggable sentence; a single number is not.
 *
 * <p><b>Hard ineligibility is never a term here.</b> A candidate that fails a gate does not reach
 * scoring at all — it is recorded in {@link SelectionExplanation} with its decisive reason. That
 * separation is what stops a large enough bonus from smuggling an ineligible scene onto the screen
 * (spec §9.2).
 */
public final class SelectionScore {

    /** The term names, in the order the plan lists them and the order the trace prints them. */
    public static final String BASE_PRIORITY = "base_priority";
    public static final String DUE_OBLIGATION = "due_obligation";
    public static final String UNRESOLVED_CONTINUITY = "unresolved_continuity";
    public static final String EPISODE_SALIENCE = "episode_salience";
    public static final String ACUTE_CONTEXT_FIT = "acute_context_fit";
    public static final String STABLE_IDENTITY_FIT = "stable_identity_fit";
    public static final String RELATIONSHIP_FIT = "relationship_fit";
    public static final String PERSONALITY_FIT = "personality_selection_fit";
    public static final String SOCIAL_RELEVANCE = "social_relevance";
    public static final String NOVELTY = "novelty";
    public static final String SCENE_RECENCY = "scene_recency";
    public static final String SUBJECT_RECENCY = "subject_recency";
    public static final String SHAPE_RECENCY = "rhetorical_recency";
    public static final String TOPIC_RECENCY = "topic_recency";
    public static final String INTERRUPTION_COST = "interruption_cost";
    public static final String INTERACTION_FATIGUE = "interaction_fatigue";

    private final Map<String, Integer> terms = new LinkedHashMap<>();
    private int total;

    /** Adds a term. A zero contributes nothing and is not recorded, keeping traces readable. */
    public SelectionScore add(String term, int value) {
        if (value == 0 || term == null) {
            return this;
        }
        terms.merge(term.toLowerCase(Locale.ROOT), value, Integer::sum);
        total += value;
        return this;
    }

    /** Subtracts a penalty. Written as its own method so call sites read as the plan's formula does. */
    public SelectionScore penalise(String term, int value) {
        return add(term, -Math.abs(value));
    }

    public int total() {
        return total;
    }

    public Map<String, Integer> terms() {
        return Map.copyOf(terms);
    }

    /** One line per finalist in the trace: {@code 34 = base_priority+20, episode_salience+18, …}. */
    public String describe() {
        StringBuilder sb = new StringBuilder().append(total).append(" = ");
        boolean first = true;
        for (Map.Entry<String, Integer> entry : terms.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(entry.getKey()).append(entry.getValue() >= 0 ? "+" : "").append(entry.getValue());
            first = false;
        }
        return terms.isEmpty() ? sb.append("(no terms)").toString() : sb.toString();
    }

    @Override
    public String toString() {
        return describe();
    }
}
