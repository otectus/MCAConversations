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
    /** Two adjacent pattern literals may be separated by at most one filler token. */
    private static final int ADJACENT_GAP_CAP = 1;
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

        // --- Phrase evidence (computed first: a matched phrase IS the anchor) ---
        // requiresAll/requiresAny exist to stop a bag of weak keywords from claiming an intent.
        // A whole authored phrase matching is stronger evidence than any single anchor stem, and
        // insisting on the anchor as well rejected natural rewordings that the phrase already
        // recognised. So the guards below apply only when no phrase matched.
        double phrase = 0;
        Set<String> phraseStems = new HashSet<>();
        for (List<PhraseToken> pattern : intent.phrases) {
            if (phraseMatches(allStems, pattern, index.synonyms())) {
                phrase += PHRASE_BONUS;
                for (PhraseToken pt : pattern) {
                    if (!pt.wildcard()) {
                        phraseStems.add(pt.stem());
                    }
                }
            }
        }
        phrase = Math.min(phrase, PHRASE_CAP);

        // --- Guards (cheap rejects) ---
        if (phrase == 0) {
            for (String req : intent.requiresAllStems) {
                if (!hasStem(index, req, pos, fuzzable)) {
                    return null;
                }
            }
            if (!intent.requiresAnyStems.isEmpty()) {
                boolean any = false;
                for (String req : intent.requiresAnyStems) {
                    if (hasStem(index, req, pos, fuzzable)) {
                        any = true;
                        break;
                    }
                }
                if (!any) {
                    return null;
                }
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
                String fuzzy = fuzzyHit(index, k, fuzzable);
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
        matchedStems.addAll(phraseStems);

        // --- Bigram boost ---
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

    private static boolean hasStem(IntentIndex index, String req, Set<String> pos,
                                   List<String> posTokenStems) {
        if (pos.contains(req)) {
            return true;
        }
        // Fuzzy may satisfy an anchor only for sufficiently long stems (§6.5).
        if (req.length() >= MIN_FUZZY_ANCHOR_LEN && directFuzzyHit(req, posTokenStems) != null) {
            return true;
        }
        // A typo'd *alias* should satisfy the anchor too: the index stores the canonical stem, but
        // the player typed some member of the synonym class and misspelled it.
        for (String alias : index.synonyms().aliasesOf(req)) {
            if (alias.length() >= MIN_FUZZY_ANCHOR_LEN && directFuzzyHit(alias, posTokenStems) != null) {
                return true;
            }
        }
        return false;
    }

    /** As {@link #directFuzzyHit} but also tries every alias of {@code keyword}. */
    private static String fuzzyHit(IntentIndex index, String keyword, List<String> posTokenStems) {
        String direct = directFuzzyHit(keyword, posTokenStems);
        if (direct != null) {
            return direct;
        }
        for (String alias : index.synonyms().aliasesOf(keyword)) {
            String hit = directFuzzyHit(alias, posTokenStems);
            if (hit != null) {
                return hit;
            }
        }
        return null;
    }

    /** The message token stem that typo-matches {@code stem}, or null. */
    private static String directFuzzyHit(String stem, List<String> posTokenStems) {
        for (String t : posTokenStems) {
            if (Fuzzy.typoMatches(t, stem)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Greedy ordered-subsequence match of {@code literals} over the full token-stem list (stop tokens
     * included), with each gap capped at {@link #PHRASE_GAP_CAP} tokens and the whole span capped at
     * {@link #PHRASE_WINDOW_CAP} (§6.4).
     */
    /**
     * True when {@code pattern} occurs in the message token stream.
     *
     * <p>A pattern is a list of literal stems with optional {@code *} wildcards between them. The
     * search is a small backtracking walk rather than a greedy scan, because a greedy first-match
     * can consume a literal at a position that makes a later one unreachable, failing a phrase that
     * does occur. Each literal carries its own gap allowance: the first is free, one that follows a
     * wildcard run may skip {@code 4 × wildcards} tokens, and two adjacent literals tolerate a
     * single filler token ({@link #ADJACENT_GAP_CAP}) so "how is the work" still matches "how work".
     * The whole match must span at most {@link #PHRASE_WINDOW_CAP} tokens.
     *
     * <p>Literals compare through {@link #literalMatches}, so a phrase also matches when the player
     * used a synonym of the authored word or misspelled it.
     */
    static boolean phraseMatches(List<String> tokenStems, List<PhraseToken> pattern, SynonymTable syn) {
        List<String> literals = new ArrayList<>();
        List<Integer> maxGaps = new ArrayList<>();
        int wildcards = 0;
        for (PhraseToken pt : pattern) {
            if (pt.wildcard()) {
                wildcards++;
                continue;
            }
            literals.add(pt.stem());
            maxGaps.add(literals.size() == 1
                    ? 0
                    : (wildcards > 0 ? wildcards * PHRASE_GAP_CAP : ADJACENT_GAP_CAP));
            wildcards = 0;
        }
        if (literals.isEmpty()) {
            return false;
        }
        for (int start = 0; start < tokenStems.size(); start++) {
            if (literalMatches(tokenStems.get(start), literals.get(0), syn)
                    && matchFrom(tokenStems, literals, maxGaps, 1, start, start, syn)) {
                return true;
            }
        }
        return false;
    }

    /** Backtracking continuation of {@link #phraseMatches} from literal {@code li}. */
    private static boolean matchFrom(List<String> tokenStems, List<String> literals,
                                     List<Integer> maxGaps, int li, int prevIdx, int firstIdx,
                                     SynonymTable syn) {
        if (li == literals.size()) {
            return prevIdx - firstIdx + 1 <= PHRASE_WINDOW_CAP;
        }
        int limit = Math.min(tokenStems.size() - 1, prevIdx + 1 + maxGaps.get(li));
        for (int j = prevIdx + 1; j <= limit; j++) {
            if (literalMatches(tokenStems.get(j), literals.get(li), syn)
                    && matchFrom(tokenStems, literals, maxGaps, li + 1, j, firstIdx, syn)) {
                return true;
            }
        }
        return false;
    }

    /** A pattern literal matches a token exactly, as a typo, or through its synonym class. */
    private static boolean literalMatches(String token, String literal, SynonymTable syn) {
        if (token.equals(literal)) {
            return true;
        }
        if (literal.length() >= MIN_FUZZY_ANCHOR_LEN && Fuzzy.typoMatches(token, literal)) {
            return true;
        }
        for (String alias : syn.aliasesOf(literal)) {
            if (alias.length() >= MIN_FUZZY_ANCHOR_LEN && Fuzzy.typoMatches(token, alias)) {
                return true;
            }
        }
        return false;
    }

    private static double clamp01(double v) {
        return v < 0 ? 0 : Math.min(1.0, v);
    }
}
