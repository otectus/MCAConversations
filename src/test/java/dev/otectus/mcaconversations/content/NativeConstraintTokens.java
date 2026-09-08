package dev.otectus.mcaconversations.content;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * MCA's real {@code Constraint} vocabulary, checked in so the corpus can be linted without an MCA jar
 * and so a token MCA never had cannot ship again.
 *
 * <p>Every id here is a registry key of {@code net.conczin.mca.entity.interaction.Constraint}, read
 * from the MCA jar; {@code ConstraintVocabularyProbeTest} re-derives the set from a real jar whenever
 * probe jars are supplied. Both signs exist for every one of them.
 *
 * <p>Note what is <b>absent</b>: there is no {@code child}. Nine shipped answers used to carry
 * {@code !child}, which MCA parsed as nothing at all, so the exclusion those answers were written to
 * express never happened. Age above the small-kid line is the catalog's {@code ages} allow-list, and
 * it is enforced by {@code TopicAgeGate}, not by a constraint.
 */
public final class NativeConstraintTokens {

    /**
     * The base ids, without the {@code !} prefix.
     *
     * <p>Only ids every supported MCA build has. This 1.21.1 port's runtime range starts at 7.7.13, so
     * {@code relative} and {@code riding} — which arrived in 7.7 and are absent from the 1.20.1 build's
     * oldest supported jar — are present on every build this port can run against.
     */
    public static final List<String> BASE = List.of(
            "family", "relative", "baby", "toddler", "teen", "adult",
            "spouse", "engaged", "promised", "kids", "parent",
            "cleric", "adventurer", "mercenary", "outlawed", "trader",
            "peasant", "noble", "mayor", "monarch", "orphan",
            "following", "staying", "village_has_space", "has_village", "hit_by",
            "riding");

    /** Every token an answer or a condition may write: each base id, plain and negated. */
    public static final Set<String> ALL = all();

    private NativeConstraintTokens() {
    }

    private static Set<String> all() {
        Set<String> tokens = new LinkedHashSet<>();
        for (String id : BASE) {
            tokens.add(id);
            tokens.add("!" + id);
        }
        return Set.copyOf(tokens);
    }
}
