package dev.otectus.mcaconversations.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The load-time compiled intent table (spec §6.3): every {@link IntentBinding} stemmed and
 * synonym-canonicalized with the same {@link Normalizer} the query path uses, plus IDF specificity
 * weights, per-intent norms, an inverted stem→intent index, and context partitioning. Pure ✦ — built
 * once per datapack reload and read concurrently by {@link IntentMatcher} thereafter (immutable).
 */
public final class IntentIndex {

    /** One phrase-pattern token: a literal stem, or a wildcard matching 0–4 arbitrary tokens (§6.4). */
    public record PhraseToken(String stem, boolean wildcard) {
    }

    /** A binding compiled to stems + derived scoring data. */
    public static final class CompiledIntent {
        public final IntentBinding source;
        public final Map<String, Double> keywordStems;   // canonical stem -> weight
        public final Set<String> requiresAnyStems;
        public final Set<String> requiresAllStems;
        public final Set<String> antiStems;
        public final Set<String> bigramStems;            // "a b" canonical
        public final List<List<PhraseToken>> phrases;
        public final double norm;

        CompiledIntent(IntentBinding source, Map<String, Double> keywordStems,
                       Set<String> requiresAnyStems, Set<String> requiresAllStems,
                       Set<String> antiStems, Set<String> bigramStems,
                       List<List<PhraseToken>> phrases, double norm) {
            this.source = source;
            this.keywordStems = keywordStems;
            this.requiresAnyStems = requiresAnyStems;
            this.requiresAllStems = requiresAllStems;
            this.antiStems = antiStems;
            this.bigramStems = bigramStems;
            this.phrases = phrases;
            this.norm = norm;
        }

        public String id() {
            return source.id();
        }

        public String context() {
            return source.context();
        }
    }

    private static final int NORM_TOP_KEYWORDS = 2;

    private final List<CompiledIntent> all;
    private final List<CompiledIntent> global;                 // context == null
    private final Map<String, List<CompiledIntent>> byContext; // context id -> scoped intents
    private final Map<String, Double> idf;
    private final Map<String, List<CompiledIntent>> inverted;  // keyword stem -> intents
    private final Set<String> vocabulary;                      // every exact keyword stem
    private final SynonymTable synonyms;

    private IntentIndex(List<CompiledIntent> all, List<CompiledIntent> global,
                        Map<String, List<CompiledIntent>> byContext, Map<String, Double> idf,
                        Map<String, List<CompiledIntent>> inverted, Set<String> vocabulary,
                        SynonymTable synonyms) {
        this.all = all;
        this.global = global;
        this.byContext = byContext;
        this.idf = idf;
        this.inverted = inverted;
        this.vocabulary = vocabulary;
        this.synonyms = synonyms;
    }

    public static IntentIndex build(List<IntentBinding> bindings, SynonymTable synonyms) {
        int n = Math.max(1, bindings.size());

        // Pass 1: stem keyword sets so IDF's df can be computed over canonical stems.
        List<Map<String, Double>> stemmedKeywords = new ArrayList<>(bindings.size());
        Map<String, Integer> df = new HashMap<>();
        for (IntentBinding b : bindings) {
            Map<String, Double> ks = stemKeywords(b, synonyms);
            stemmedKeywords.add(ks);
            for (String stem : ks.keySet()) {
                df.merge(stem, 1, Integer::sum);
            }
        }
        Map<String, Double> idf = new HashMap<>();
        for (Map.Entry<String, Integer> e : df.entrySet()) {
            idf.put(e.getKey(), 1.0 + Math.log((double) n / e.getValue()));
        }

        // Pass 2: compile each intent, compute norm from the stemmed keywords + IDF.
        List<CompiledIntent> all = new ArrayList<>(bindings.size());
        List<CompiledIntent> global = new ArrayList<>();
        Map<String, List<CompiledIntent>> byContext = new HashMap<>();
        Map<String, List<CompiledIntent>> inverted = new HashMap<>();
        for (int i = 0; i < bindings.size(); i++) {
            IntentBinding b = bindings.get(i);
            Map<String, Double> ks = stemmedKeywords.get(i);
            double norm = computeNorm(ks, idf);
            CompiledIntent ci = new CompiledIntent(b, ks,
                    stemWords(b.requiresAny(), synonyms), stemWords(b.requiresAll(), synonyms),
                    stemWords(b.antiKeywords(), synonyms), stemBigrams(b.bigrams(), synonyms),
                    compilePhrases(b.phrases(), synonyms), norm);
            all.add(ci);
            if (b.context() == null) {
                global.add(ci);
            } else {
                byContext.computeIfAbsent(b.context(), k -> new ArrayList<>()).add(ci);
            }
            for (String stem : ks.keySet()) {
                inverted.computeIfAbsent(stem, k -> new ArrayList<>()).add(ci);
            }
        }
        return new IntentIndex(all, global, byContext, idf, inverted, inverted.keySet(), synonyms);
    }

    // --- Query surface ---------------------------------------------------------

    /** All intents that may score against a message in the given context: global ∪ scoped(currentQuestion). */
    public List<CompiledIntent> activeIntents(String currentQuestion) {
        if (currentQuestion == null) {
            return Collections.unmodifiableList(global);
        }
        List<CompiledIntent> scoped = byContext.get(currentQuestion);
        if (scoped == null || scoped.isEmpty()) {
            return Collections.unmodifiableList(global);
        }
        List<CompiledIntent> out = new ArrayList<>(global.size() + scoped.size());
        out.addAll(global);
        out.addAll(scoped);
        return out;
    }

    public List<CompiledIntent> all() {
        return Collections.unmodifiableList(all);
    }

    public double idf(String stem) {
        return idf.getOrDefault(stem, 1.0 + Math.log((double) Math.max(1, all.size())));
    }

    public SynonymTable synonyms() {
        return synonyms;
    }

    /** Every exact keyword stem across all intents — tokens with an exact home are not fuzzy-matched. */
    public java.util.Set<String> keywordVocabulary() {
        return Collections.unmodifiableSet(vocabulary);
    }

    /** Intents whose keyword set contains {@code stem} (inverted-index lookup). Empty if none. */
    public List<CompiledIntent> withKeyword(String stem) {
        return inverted.getOrDefault(stem, Collections.emptyList());
    }

    public int size() {
        return all.size();
    }

    // --- Compilation helpers ---------------------------------------------------

    private static Map<String, Double> stemKeywords(IntentBinding b, SynonymTable syn) {
        Map<String, Double> out = new HashMap<>();
        for (Map.Entry<String, Double> e : b.keywords().entrySet()) {
            String stem = syn.canonical(Normalizer.stemToken(e.getKey()));
            if (!stem.isEmpty()) {
                out.merge(stem, e.getValue(), Math::max);
            }
        }
        return out;
    }

    private static Set<String> stemWords(List<String> words, SynonymTable syn) {
        Set<String> out = new LinkedHashSet<>();
        for (String w : words) {
            String stem = syn.canonical(Normalizer.stemToken(w));
            if (!stem.isEmpty()) {
                out.add(stem);
            }
        }
        return out;
    }

    private static Set<String> stemBigrams(List<String> raw, SynonymTable syn) {
        Set<String> out = new LinkedHashSet<>();
        for (String bg : raw) {
            List<String> stems = Normalizer.canonicalStems(bg, syn);
            if (stems.size() == 2) {
                out.add(stems.get(0) + " " + stems.get(1));
            }
        }
        return out;
    }

    private static List<List<PhraseToken>> compilePhrases(List<String> raw, SynonymTable syn) {
        List<List<PhraseToken>> out = new ArrayList<>();
        for (String phrase : raw) {
            List<PhraseToken> pattern = new ArrayList<>();
            for (String part : phrase.trim().split("\\s+")) {
                if (part.equals("*")) {
                    pattern.add(new PhraseToken(null, true));
                } else {
                    for (String stem : Normalizer.canonicalStems(part, syn)) {
                        pattern.add(new PhraseToken(stem, false));
                    }
                }
            }
            if (!pattern.isEmpty()) {
                out.add(pattern);
            }
        }
        return out;
    }

    private static double computeNorm(Map<String, Double> keywordStems, Map<String, Double> idf) {
        List<Double> contributions = new ArrayList<>(keywordStems.size());
        for (Map.Entry<String, Double> e : keywordStems.entrySet()) {
            double weight = e.getValue();
            double specificity = idf.getOrDefault(e.getKey(), 1.0);
            contributions.add(weight * specificity);
        }
        contributions.sort(Collections.reverseOrder());
        double norm = 0;
        for (int i = 0; i < Math.min(NORM_TOP_KEYWORDS, contributions.size()); i++) {
            norm += contributions.get(i);
        }
        return norm > 0 ? norm : 1.0; // guard: a phrase-only intent still gets a positive denominator
    }
}
