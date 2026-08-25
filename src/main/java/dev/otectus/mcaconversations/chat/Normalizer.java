package dev.otectus.mcaconversations.chat;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * The §6.2 normalization pipeline: turns a raw chat message into a {@link NormalizedMessage} of
 * stemmed, stop/negation-flagged tokens. Ordered stages — fold, de-noise, expand contractions,
 * tokenize, tag negation, flag stopwords, light suffix stem, canonicalize synonyms — each small and
 * deterministic. The same primitives ({@link #stemToken}, {@link #canonicalStems}) stem index
 * keywords and phrases at load, so both sides of a match are stemmed identically.
 *
 * <p>Pure ✦ class: imports only the JDK. Query-time synonym canonicalization takes a
 * {@link SynonymTable} argument (pass {@link SynonymTable#EMPTY} to test the raw pipeline).
 */
public final class Normalizer {

    private Normalizer() {
    }

    /** A single normalized token. {@code stem} is post-stem, post-synonym; {@code pos} is 0-based. */
    public static final class Token {
        public final String stem;
        public final boolean stop;
        public final boolean negated;
        public final int pos;

        Token(String stem, boolean stop, boolean negated, int pos) {
            this.stem = stem;
            this.stop = stop;
            this.negated = negated;
            this.pos = pos;
        }
    }

    /** Output of {@link #normalize}: the token stream plus derived views the matcher scores over. */
    public static final class NormalizedMessage {
        public final List<Token> tokens;
        /** Non-stop stems present un-negated (usable as positive keyword evidence). */
        public final Set<String> contentStems;
        /** Non-stop stems that appear only negated (keyword evidence they suppress). */
        public final Set<String> negatedStems;
        /** Consecutive non-stop stem pairs {@code "a b"} (both un-negated). */
        public final Set<String> bigrams;
        /** Leading question word or a trailing {@code ?} in the raw message. */
        public final boolean interrogative;

        NormalizedMessage(List<Token> tokens, Set<String> contentStems, Set<String> negatedStems,
                          Set<String> bigrams, boolean interrogative) {
            this.tokens = tokens;
            this.contentStems = contentStems;
            this.negatedStems = negatedStems;
            this.bigrams = bigrams;
            this.interrogative = interrogative;
        }

        /** Count of non-stop tokens — the denominator of the §6.3 coverage term. */
        public int contentTokenCount() {
            int n = 0;
            for (Token t : tokens) {
                if (!t.stop) {
                    n++;
                }
            }
            return n;
        }
    }

    private static final int MAX_TOKENS = 32;

    private static final Set<String> STOPWORDS = Set.of(
            "the", "a", "an", "i", "me", "my", "we", "us", "it", "its", "is", "are", "was", "be",
            // NOTE: "hey" is deliberately NOT a stopword — it is the leading token of a greeting
            // ("hey Anna"), which Addressing needs in order to spot a greeting-prefixed address.
            "been", "of", "to", "in", "on", "at", "for", "with", "and", "or", "so", "well", "um",
            "uh", "please", "ok", "okay", "just", "really", "very");

    /** The stopword set, exposed so {@link Addressing} can treat them as non-name tokens. */
    static Set<String> stopwords() {
        return STOPWORDS;
    }

    /** Kept as features (they carry intent shape) — never flagged stop. */
    private static final Set<String> QUESTION_WORDS = Set.of(
            "how", "what", "why", "when", "where", "who", "do", "does", "did", "can", "could",
            "would", "will", "tell", "about");

    private static final Set<String> NEGATORS = Set.of(
            "not", "no", "never", "none", "nobody", "nothing", "hardly", "without");

    /** Coordinating conjunctions break a negation window (§6.2.5). */
    private static final Set<String> CONJUNCTIONS = Set.of("but", "and", "or");

    private static final Map<String, String[]> CONTRACTIONS = buildContractions();

    /** Small silent-e restore list for {@code -ing} stripping (§6.2.7). */
    private static final Map<String, String> ING_EXCEPTIONS = Map.of(
            "having", "have", "making", "make", "coming", "come", "giving", "give",
            "taking", "take", "living", "live", "coping", "cope");

    // --- Public API ------------------------------------------------------------

    /** Full pipeline. {@code syn} canonicalizes synonyms (pass {@link SynonymTable#EMPTY} for none). */
    public static NormalizedMessage normalize(String raw, SynonymTable syn) {
        String folded = fold(raw == null ? "" : raw);
        boolean interrogative = folded.strip().endsWith("?");

        List<Lexeme> lexemes = lex(folded);
        List<String> expanded = new ArrayList<>();
        List<Boolean> breaks = new ArrayList<>();
        for (Lexeme lx : lexemes) {
            String[] parts = CONTRACTIONS.get(lx.word);
            if (parts != null) {
                for (int i = 0; i < parts.length; i++) {
                    expanded.add(parts[i]);
                    breaks.add(i == 0 && lx.breakBefore);
                }
            } else {
                expanded.add(lx.word);
                breaks.add(lx.breakBefore);
            }
            if (expanded.size() >= MAX_TOKENS) {
                break;
            }
        }
        if (expanded.size() > MAX_TOKENS) {
            expanded = expanded.subList(0, MAX_TOKENS);
            breaks = breaks.subList(0, MAX_TOKENS);
        }

        if (!expanded.isEmpty() && QUESTION_WORDS.contains(expanded.get(0))) {
            interrogative = true;
        }

        List<Token> tokens = new ArrayList<>(expanded.size());
        int negRemaining = 0;
        for (int i = 0; i < expanded.size(); i++) {
            String surface = expanded.get(i);
            boolean breakBefore = breaks.get(i);
            boolean isNegator = NEGATORS.contains(surface);
            boolean isConjunction = CONJUNCTIONS.contains(surface);
            boolean stop = STOPWORDS.contains(surface) && !QUESTION_WORDS.contains(surface);

            if (breakBefore || isConjunction) {
                negRemaining = 0;
            }
            boolean negated = false;
            if (isNegator) {
                negRemaining = 3;
            } else if (negRemaining > 0) {
                negated = true;
                if (!stop) {
                    negRemaining--;
                }
            }
            String stem = syn.canonical(stem(stripApostrophes(surface)));
            if (stem.isEmpty()) {
                continue;
            }
            tokens.add(new Token(stem, stop, negated, tokens.size()));
        }

        Set<String> contentStems = new LinkedHashSet<>();
        Set<String> negatedStems = new LinkedHashSet<>();
        for (Token t : tokens) {
            if (t.stop) {
                continue;
            }
            if (t.negated) {
                negatedStems.add(t.stem);
            } else {
                contentStems.add(t.stem);
            }
        }
        negatedStems.removeAll(contentStems); // a stem seen un-negated anywhere counts as positive

        Set<String> bigrams = new LinkedHashSet<>();
        Token prev = null;
        for (Token t : tokens) {
            if (t.stop || t.negated) {
                prev = null;
                continue;
            }
            if (prev != null) {
                bigrams.add(prev.stem + " " + t.stem);
            }
            prev = t;
        }

        return new NormalizedMessage(tokens, contentStems, negatedStems, bigrams, interrogative);
    }

    /** Stems one surface word (fold + suffix stem, no contraction split, no synonym). */
    public static String stemToken(String raw) {
        if (raw == null) {
            return "";
        }
        String folded = fold(raw);
        StringBuilder alnum = new StringBuilder();
        for (int i = 0; i < folded.length(); i++) {
            char c = folded.charAt(i);
            if (Character.isLetterOrDigit(c) || c == '\'') {
                alnum.append(c);
            }
        }
        return stem(stripApostrophes(alnum.toString()));
    }

    /**
     * Lexes {@code text} into an ordered list of canonical stems (fold + contraction expand + tokenize
     * + stem + synonym), dropping stop/negation bookkeeping. Used to stem phrase patterns and multi-
     * word synonym entries at load, so they match the query pipeline's stems.
     */
    public static List<String> canonicalStems(String text, SynonymTable syn) {
        List<String> out = new ArrayList<>();
        for (Lexeme lx : lex(fold(text == null ? "" : text))) {
            String[] parts = CONTRACTIONS.get(lx.word);
            String[] words = parts != null ? parts : new String[]{lx.word};
            for (String w : words) {
                String stem = syn.canonical(stem(stripApostrophes(w)));
                if (!stem.isEmpty()) {
                    out.add(stem);
                }
            }
        }
        return out;
    }

    // --- Stages ----------------------------------------------------------------

    /** Steps 1–2: lowercase, strip diacritics, ASCII-fold quotes/dashes, collapse 3+ repeats to 2. */
    static String fold(String raw) {
        String s = raw.toLowerCase(Locale.ROOT);
        s = java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFKD).replaceAll("\\p{M}+", "");
        s = s.replace('‘', '\'').replace('’', '\'')
                .replace('“', '"').replace('”', '"')
                .replace('–', '-').replace('—', '-');
        s = s.replaceAll("([a-z])\\1{2,}", "$1$1");
        return s;
    }

    /** A raw surface token plus whether a clause-breaking punctuation preceded it. */
    private record Lexeme(String word, boolean breakBefore) {
    }

    /** Step 4: tokenize on non-alphanumerics (apostrophes kept), tracking clause boundaries. */
    private static List<Lexeme> lex(String folded) {
        List<Lexeme> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean pendingBreak = false;
        for (int i = 0; i < folded.length(); i++) {
            char c = folded.charAt(i);
            if (Character.isLetterOrDigit(c) || c == '\'') {
                cur.append(c);
            } else {
                if (cur.length() > 0) {
                    out.add(new Lexeme(cur.toString(), pendingBreak));
                    cur.setLength(0);
                    pendingBreak = false;
                }
                if (c == ',' || c == ';' || c == ':' || c == '.' || c == '!' || c == '?') {
                    pendingBreak = true;
                }
            }
            if (out.size() >= MAX_TOKENS) {
                return out;
            }
        }
        if (cur.length() > 0 && out.size() < MAX_TOKENS) {
            out.add(new Lexeme(cur.toString(), pendingBreak));
        }
        return out;
    }

    private static String stripApostrophes(String w) {
        // Strip a possessive tail and any stray leading/trailing apostrophes.
        if (w.endsWith("'s")) {
            w = w.substring(0, w.length() - 2);
        }
        int start = 0;
        int end = w.length();
        while (start < end && w.charAt(start) == '\'') {
            start++;
        }
        while (end > start && w.charAt(end - 1) == '\'') {
            end--;
        }
        return w.substring(start, end);
    }

    /** Step 7: light, suffix-only stemming. Plural strip then a single verb-suffix strip (chained). */
    static String stem(String w) {
        if (w.length() < 4) {
            return w;
        }
        w = stripPlural(w);
        w = stripVerb(w);
        return w;
    }

    private static String stripPlural(String w) {
        if (w.length() < 4 || !w.endsWith("s") || w.endsWith("ss")) {
            return w;
        }
        if (w.endsWith("ies")) {
            return w.substring(0, w.length() - 3) + "y"; // parties -> party
        }
        if (w.endsWith("ses") || w.endsWith("xes") || w.endsWith("zes")
                || w.endsWith("ches") || w.endsWith("shes")) {
            return w.substring(0, w.length() - 2); // boxes -> box, wishes -> wish
        }
        return w.substring(0, w.length() - 1); // storms -> storm, scares -> scare
    }

    private static String stripVerb(String w) {
        if (w.endsWith("ing") && w.length() >= 6) {
            String exception = ING_EXCEPTIONS.get(w);
            if (exception != null) {
                return exception;
            }
            return w.substring(0, w.length() - 3); // farming -> farm, feeling -> feel
        }
        if (w.endsWith("ed") && w.length() >= 5 && !w.endsWith("eed")) {
            return w.substring(0, w.length() - 2); // worried -> worri (fuzzy absorbs the -y)
        }
        return w;
    }

    private static Map<String, String[]> buildContractions() {
        java.util.HashMap<String, String[]> m = new java.util.HashMap<>();
        String[][] pairs = {
                {"how's", "how", "is"}, {"hows", "how", "is"},
                {"what's", "what", "is"}, {"whats", "what", "is"},
                {"where's", "where", "is"}, {"wheres", "where", "is"},
                {"who's", "who", "is"}, {"whos", "who", "is"},
                {"when's", "when", "is"}, {"whens", "when", "is"},
                {"that's", "that", "is"}, {"thats", "that", "is"},
                {"it's", "it", "is"},
                {"i'm", "i", "am"}, {"im", "i", "am"},
                {"you're", "you", "are"}, {"youre", "you", "are"},
                {"we're", "we", "are"}, {"they're", "they", "are"},
                {"don't", "do", "not"}, {"dont", "do", "not"},
                {"doesn't", "does", "not"}, {"doesnt", "does", "not"},
                {"didn't", "did", "not"}, {"didnt", "did", "not"},
                {"can't", "can", "not"}, {"cant", "can", "not"}, {"cannot", "can", "not"},
                {"won't", "will", "not"}, {"wont", "will", "not"},
                {"isn't", "is", "not"}, {"isnt", "is", "not"},
                {"aren't", "are", "not"}, {"arent", "are", "not"},
                {"wasn't", "was", "not"}, {"wasnt", "was", "not"},
                {"weren't", "were", "not"}, {"werent", "were", "not"},
                {"haven't", "have", "not"}, {"havent", "have", "not"},
                {"hasn't", "has", "not"}, {"hasnt", "has", "not"},
                {"i've", "i", "have"}, {"ive", "i", "have"},
                {"you've", "you", "have"},
                {"i'd", "i", "would"}, {"i'll", "i", "will"},
                {"let's", "let", "us"}, {"lets", "let", "us"},
                {"gonna", "going", "to"}, {"wanna", "want", "to"}, {"gotta", "got", "to"},
                {"ain't", "is", "not"}, {"aint", "is", "not"},
                // Common typo, folded here so it reaches the index as "the" rather than an
                // unknown stem that only fuzzy matching could rescue.
                {"teh", "the"},
        };
        for (String[] p : pairs) {
            String[] parts = new String[p.length - 1];
            System.arraycopy(p, 1, parts, 0, parts.length);
            m.put(p[0], parts);
        }
        return m;
    }
}
