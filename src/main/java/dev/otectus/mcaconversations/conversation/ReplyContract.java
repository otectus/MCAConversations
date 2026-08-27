package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonObject;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * The declared meaning of one player button (spec §5.3).
 *
 * <p>Bound to an exact {@code question/answer} pair, because that pair is what MCA actually shows.
 * Three of the fields carry the weight:
 *
 * <ul>
 *   <li>{@link #stance} — what the player is doing. Checked against the inbound beat's allowed and
 *       forbidden stances, which is how "you don't sound like you enjoy it" stops appearing under a
 *       line about being proud of one's work.</li>
 *   <li>{@link #requiresFacts} — what the wording takes for granted. "I'll bring you some" requires a
 *       {@code some}; lint refuses the button on any route where no beat established one.</li>
 *   <li>{@link #tone} — how it sounds, so a gentle wording cannot hide a hostile consequence.</li>
 * </ul>
 *
 * <p>{@link #respondsTo} is the author's claim about where this button is legal. It accepts exact beat
 * ids and {@code subject:} patterns, so a page shared by a genuinely equivalent family of beats does
 * not need its answers restating once per beat.
 *
 * <p>{@link #move} is the optional v2 half (spec §10.2): which obligation the button fulfils, which
 * referents its wording presupposes, what the player is claiming by choosing it, and which promise it
 * makes. A button can have a perfectly legal stance and still fail to answer the question that was
 * asked, and v1 has no way to see that. Every reply authored before this release carries
 * {@link ReplyMove#V1_DEFAULT} and behaves exactly as before.
 */
public record ReplyContract(String question,
                            String answer,
                            StanceFamily stance,
                            Set<String> respondsTo,
                            Set<SemanticFact> requiresFacts,
                            Set<SemanticFact> introducesFacts,
                            Tone tone,
                            Set<OutcomeFamily> outcomes,
                            boolean exit,
                            ReplyMove move) {

    /** Wildcard {@code responds_to}: this button is legal after any beat that opens its question. */
    public static final String ANY_BEAT = "*";

    public ReplyContract {
        move = move == null ? ReplyMove.V1_DEFAULT : move;
        respondsTo = Set.copyOf(respondsTo);
        requiresFacts = Set.copyOf(new TreeSet<>(requiresFacts));
        introducesFacts = Set.copyOf(new TreeSet<>(introducesFacts));
        outcomes = Set.copyOf(outcomes);
    }

    /** The id this contract is filed under: {@code question/answer}. */
    public String key() {
        return question + "/" + answer;
    }

    /** True when this button claims to be legal after {@code beat}. */
    public boolean accepts(BeatContract beat) {
        if (respondsTo.isEmpty() || respondsTo.contains(ANY_BEAT)) {
            return true;
        }
        if (respondsTo.contains(beat.id())) {
            return true;
        }
        for (String pattern : respondsTo) {
            if (pattern.startsWith("subject:") && subjectMatches(pattern.substring("subject:".length()), beat.subject())) {
                return true;
            }
        }
        return false;
    }

    /** {@code work.farmer.*} matches {@code work.farmer.crop_health}; a bare id matches exactly. */
    private static boolean subjectMatches(String pattern, String subject) {
        if (pattern.endsWith(".*")) {
            String prefix = pattern.substring(0, pattern.length() - 1);
            return subject.startsWith(prefix);
        }
        return pattern.equals("*") || pattern.equals(subject);
    }

    /** True when this reply carries declared v2 metadata rather than the v1 default. */
    public boolean hasMove() {
        return move.isDeclared();
    }

    /**
     * True when this button is a legal thing to put on a page that declares {@code obligations}.
     *
     * <p>An exit always is. Anything else must fulfil one of the obligations the inbound beat made
     * relevant, or perform a permitted topic move — the §10.3 invariant, checked here so both the
     * build lint and the runtime guard read the same rule.
     */
    public boolean isResponsiveTo(java.util.Set<Obligation> obligations) {
        if (exit || move.move().isPresent()) {
            return true;
        }
        if (!move.isDeclared()) {
            // A v1 reply makes no claim either way; v1 routing rules already govern it.
            return true;
        }
        if (obligations == null || obligations.isEmpty()) {
            return true;
        }
        for (Obligation obligation : obligations) {
            if (move.fulfils(obligation)) {
                return true;
            }
        }
        return false;
    }

    /** True when the wording carries warmth that a hostile stance would contradict. */
    public boolean isWarmlyWorded() {
        return tone.warmth() > 0;
    }

    public static ReplyContract fromJson(String key, JsonObject json) {
        int slash = key.lastIndexOf('/');
        if (slash <= 0 || slash == key.length() - 1) {
            throw new IllegalArgumentException("reply id '" + key + "' must be written question/answer");
        }
        String question = key.substring(0, slash);
        String answer = key.substring(slash + 1);

        StanceFamily stance = StanceFamily.byKey(requireString(json, "stance", key))
                .orElseThrow(() -> new IllegalArgumentException(
                        "reply '" + key + "' stance '" + json.get("stance").getAsString() + "' is unknown"));

        Set<String> respondsTo = new LinkedHashSet<>(BeatContract.strings(json, "responds_to"));

        Set<SemanticFact> requires = new TreeSet<>();
        for (String fact : BeatContract.strings(json, "requires_facts")) {
            requires.add(SemanticFact.parse(fact));
        }
        Set<SemanticFact> introduces = new TreeSet<>();
        for (String fact : BeatContract.strings(json, "introduces_facts")) {
            introduces.add(SemanticFact.parse(fact));
        }

        Tone tone = json.has("tone")
                ? Tone.byKey(json.get("tone").getAsString()).orElseThrow(() -> new IllegalArgumentException(
                        "reply '" + key + "' tone '" + json.get("tone").getAsString() + "' is unknown"))
                : Tone.PLAIN;

        Set<OutcomeFamily> outcomes = new LinkedHashSet<>();
        for (String family : BeatContract.strings(json, "outcomes")) {
            outcomes.add(OutcomeFamily.byKey(family).orElseThrow(() -> new IllegalArgumentException(
                    "reply '" + key + "' outcome '" + family + "' is unknown")));
        }

        boolean exit = json.has("exit") && json.get("exit").getAsBoolean();
        if (exit && stance != StanceFamily.EXIT) {
            throw new IllegalArgumentException("reply '" + key + "' is marked exit but its stance is not 'exit'");
        }
        if (!exit && stance == StanceFamily.EXIT) {
            throw new IllegalArgumentException("reply '" + key + "' has the exit stance but is not marked exit");
        }
        if (exit && !requires.isEmpty()) {
            throw new IllegalArgumentException("reply '" + key + "' is an exit and must presuppose nothing");
        }

        ReplyMove move = json.has("move") && json.get("move").isJsonObject()
                ? ReplyMove.fromJson(json.getAsJsonObject("move"), key)
                : ReplyMove.fromJson(json, key);
        if (exit && move.hasCommitment()) {
            throw new IllegalArgumentException("reply '" + key
                    + "' is an exit and cannot also make a promise");
        }

        return new ReplyContract(question, answer, stance, respondsTo, requires, introduces, tone,
                outcomes, exit, move);
    }

    private static String requireString(JsonObject json, String field, String key) {
        if (!json.has(field) || !json.get(field).isJsonPrimitive()) {
            throw new IllegalArgumentException("reply '" + key + "' requires a \"" + field + "\"");
        }
        String value = json.get(field).getAsString().trim().toLowerCase(Locale.ROOT);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("reply '" + key + "' has an empty \"" + field + "\"");
        }
        return value;
    }

    /** A reply that has not been contracted yet, so migration can proceed one node at a time. */
    public static ReplyContract legacyUnverified(String question, String answer) {
        return new ReplyContract(question, answer, StanceFamily.CURIOSITY, Set.of(ANY_BEAT),
                Set.of(), Set.of(), Tone.PLAIN, Set.of(), false, ReplyMove.V1_DEFAULT);
    }
}
