package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.IntentIndex.CompiledIntent;
import dev.otectus.mcaconversations.chat.IntentIndex.PhraseToken;
import dev.otectus.mcaconversations.chat.Normalizer.NormalizedMessage;
import dev.otectus.mcaconversations.chat.Normalizer.Token;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The §6.3–6.6 scoring engine: ranks a {@link NormalizedMessage} against the active intents of an
 * {@link IntentIndex} and turns the ranking into a {@link Decision} (match / ambiguous / none) under
 * the two-threshold + margin rules. Pure ✦ — deterministic given (message, index, context, config),
 * which is what makes the {@code /conversations chat debug} score breakdown reproducible.
 */
public final class IntentMatcher {

    private static final double FUZZY_QUALITY = 0.8;
    private static final double PHRASE_BONUS = 0.35;
    private static final double PHRASE_CAP = 0.50;
    private static final double BIGRAM_BONUS = 0.05;
    private static final double BIGRAM_CAP = 0.10;
    private static final double ANTI_PENALTY = 0.30;
    private static final double CONTEXT_BONUS = 0.25;
    private static final double COVER_SLOPE = 0.25;
    private static final double COVER_FREE_RATIO = 0.6;
    private static final double MARGIN = 0.10;
    private static final double CONTEXT_THRESHOLD_RELIEF = 0.10;
    private static final int PHRASE_GAP_CAP = 4;
    private static final int PHRASE_WINDOW_CAP = 12;
    private static final int MIN_FUZZY_ANCHOR_LEN = 5;

    private IntentMatcher() {
    }

    /** A scored intent. {@code score} is clamped to [0,1]. */
    public record Scored(String id, double score, String question, String answer, String system,
                         String category, boolean contextScoped) {
        public boolean isSystem() {
            return system != null;
        }

        /** The target identity used by the margin rule: system name or bound question id. */
        String targetKey() {
            return system != null ? "sys:" + system : question;
        }
    }

    public enum Outcome {
        /** A single confident winner: drive {@code chosen}. */
        MATCH,
        /** Two near-tied candidates on different topics: ask a clarifying question (addressed only). */
        AMBIGUOUS,
        /** Nothing crossed threshold: deflect (addressed) or stay silent (ambient). */
        NONE
    }

    public record Decision(Outcome outcome, Scored chosen, Scored alternative) {
        static Decision none() {
            return new Decision(Outcome.NONE, null, null);
        }
    }

    /**
     * Scores every active intent (global ∪ context-scoped for {@code currentQuestion}) and returns the
     * survivors with score {@code > 0}, highest first (ties broken by id for determinism).
     */
    public static List<Scored> rank(IntentIndex index, NormalizedMessage msg, String currentQuestion) {
        List<String> allStems = new ArrayList<>(msg.tokens.size());
        List<String> posTokenStems = new ArrayList<>();
        for (Token t : msg.tokens) {
            allStems.add(t.stem);
            if (!t.stop && !t.negated) {
                posTokenStems.add(t.stem);
            }
        }

        // A message token that is itself an exact keyword somewhere has a real home — never let it
        // fuzzy-bleed into another topic (e.g. "storm" must not typo-match the keyword "story").
        Set<String> vocabulary = index.keywordVocabulary();
        List<String> fuzzable = new ArrayList<>();
        for (String t : posTokenStems) {
            if (!vocabulary.contains(t)) {
                fuzzable.add(t);
            }
        }

        List<Scored> out = new ArrayList<>();
        for (CompiledIntent intent : index.activeIntents(currentQuestion)) {
            Scored scored = score(index, intent, msg, allStems, fuzzable, currentQuestion);
            if (scored != null && scored.score() > 0) {
                out.add(scored);
            }
        }
        out.sort(Comparator.comparingDouble(Scored::score).reversed().thenComparing(Scored::id));
        return out;
    }

    private static Scored score(IntentIndex index, CompiledIntent intent, NormalizedMessage msg,
                                List<String> allStems, List<String> fuzzable, String currentQuestion) {
        Set<String> pos = msg.contentStems;

        // --- Guards (cheap rejects) ---
        for (String req : intent.requiresAllStems) {
            if (!hasStem(req, pos, fuzzable)) {
                return null;
            }
        }
        if (!intent.requiresAnyStems.isEmpty()) {
            boolean any = false;
            for (String req : intent.requiresAnyStems) {
                if (hasStem(req, pos, fuzzable)) {
                    any = true;
                    break;
                }
            }
            if (!any) {
                return null;
            }
        }

        // --- Keyword evidence ---
        double kw = 0;
        Set<String> matchedStems = new HashSet<>();
        for (var e : intent.keywordStems.entrySet()) {
            String k = e.getKey();
            double w = e.getValue();
            double quality = 0;
            if (pos.contains(k)) {
                quality = 1.0;
                matchedStems.add(k);
            } else {
                String fuzzy = fuzzyHit(k, fuzzable);
                if (fuzzy != null) {
                    quality = FUZZY_QUALITY;
                    matchedStems.add(fuzzy);
                }
            }
            if (quality > 0) {
                kw += w * index.idf(k) * quality;
            }
        }
        double base = intent.norm > 0 ? Math.min(1.0, kw / intent.norm) : 0;

        // --- Phrase & bigram boosts ---
        double phrase = 0;
        for (List<PhraseToken> pattern : intent.phrases) {
            List<String> literals = literals(pattern);
            if (phraseMatches(allStems, literals)) {
                phrase += PHRASE_BONUS;
                matchedStems.addAll(literals);
            }
        }
        phrase = Math.min(phrase, PHRASE_CAP);

        double bigram = 0;
        for (String bg : intent.bigramStems) {
            if (msg.bigrams.contains(bg)) {
                bigram += BIGRAM_BONUS;
            }
        }
        bigram = Math.min(bigram, BIGRAM_CAP);

        // --- Anti-keywords (negation-aware) ---
        double anti = 0;
        for (String a : intent.antiStems) {
            if (pos.contains(a)) {
                anti -= ANTI_PENALTY;
            }
        }

        // --- Context bonus ---
        boolean contextScoped = intent.context() != null && intent.context().equals(currentQuestion);
        double ctx = contextScoped ? CONTEXT_BONUS : 0;

        // --- Coverage penalty ---
        int contentCount = pos.size();
        double cover = 0;
        if (contentCount > 0) {
            int unmatched = 0;
            for (String s : pos) {
                if (!matchedStems.contains(s)) {
                    unmatched++;
                }
            }
            double ratio = (double) unmatched / contentCount;
            cover = -COVER_SLOPE * Math.max(0, ratio - COVER_FREE_RATIO);
        }

        double score = clamp01(base + phrase + bigram + anti + ctx + cover);
        IntentBinding b = intent.source;
        return new Scored(b.id(), score, b.question(), b.answer(), b.system(), b.category(), contextScoped);
    }

    /**
     * Selects an outcome from a GatePreview-filtered, score-ranked candidate list (spec §6.6). Applies
     * the two-threshold rule, the context-scoped threshold relief, system-intent precedence within a
     * near tie, and the different-topic margin ambiguity rule (addressed only; ambient ambiguity is
     * silence).
     */
    public static Decision decide(List<Scored> eligibleRanked, boolean addressed,
                                  double minScore, double ambientMinScore) {
        if (eligibleRanked.isEmpty()) {
            return Decision.none();
        }
        double baseThreshold = addressed ? minScore : ambientMinScore;
        List<Scored> passing = new ArrayList<>();
        for (Scored s : eligibleRanked) {
            double threshold = s.contextScoped() ? baseThreshold - CONTEXT_THRESHOLD_RELIEF : baseThreshold;
            if (s.score() >= threshold) {
                passing.add(s);
            }
        }
        if (passing.isEmpty()) {
            return Decision.none();
        }

        Scored top = passing.get(0);
        // System-intent precedence: a decline/mute/greet within a near tie of the top wins outright.
        for (Scored s : passing) {
            if (top.score() - s.score() >= MARGIN) {
                break;
            }
            if (s.isSystem()) {
                return new Decision(Outcome.MATCH, s, null);
            }
        }

        if (passing.size() >= 2) {
            Scored second = passing.get(1);
            boolean differentTopic = !top.targetKey().equals(second.targetKey());
            if (differentTopic && (top.score() - second.score()) < MARGIN) {
                return addressed
                        ? new Decision(Outcome.AMBIGUOUS, top, second)
                        : Decision.none();
            }
        }
        return new Decision(Outcome.MATCH, top, null);
    }

    // --- Helpers ---------------------------------------------------------------

    private static boolean hasStem(String req, Set<String> pos, List<String> posTokenStems) {
        if (pos.contains(req)) {
            return true;
        }
        // Fuzzy may satisfy an anchor only for sufficiently long stems (§6.5).
        if (req.length() >= MIN_FUZZY_ANCHOR_LEN) {
            return fuzzyHit(req, posTokenStems) != null;
        }
        return false;
    }

    /** The message token stem that typo-matches {@code keyword}, or null. */
    private static String fuzzyHit(String keyword, List<String> posTokenStems) {
        for (String t : posTokenStems) {
            if (Fuzzy.typoMatches(t, keyword)) {
                return t;
            }
        }
        return null;
    }

    private static List<String> literals(List<PhraseToken> pattern) {
        List<String> out = new ArrayList<>();
        for (PhraseToken pt : pattern) {
            if (!pt.wildcard()) {
                out.add(pt.stem());
            }
        }
        return out;
    }

    /**
     * Greedy ordered-subsequence match of {@code literals} over the full token-stem list (stop tokens
     * included), with each gap capped at {@link #PHRASE_GAP_CAP} tokens and the whole span capped at
     * {@link #PHRASE_WINDOW_CAP} (§6.4).
     */
    static boolean phraseMatches(List<String> tokenStems, List<String> literals) {
        if (literals.isEmpty()) {
            return false;
        }
        int firstIdx = -1;
        int prevIdx = -1;
        int search = 0;
        for (String lit : literals) {
            int found = -1;
            for (int j = search; j < tokenStems.size(); j++) {
                if (prevIdx >= 0 && (j - prevIdx - 1) > PHRASE_GAP_CAP) {
                    break; // gap to this literal exceeds the cap — no later index can be closer
                }
                if (tokenStems.get(j).equals(lit)) {
                    found = j;
                    break;
                }
            }
            if (found < 0) {
                return false;
            }
            if (firstIdx < 0) {
                firstIdx = found;
            }
            prevIdx = found;
            search = found + 1;
        }
        return (prevIdx - firstIdx + 1) <= PHRASE_WINDOW_CAP;
    }

    private static double clamp01(double v) {
        return v < 0 ? 0 : Math.min(1.0, v);
    }
}
