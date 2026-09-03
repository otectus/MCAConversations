package dev.otectus.mcaconversations.content;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * The corpus does not develop a catchphrase (spec phase 8, "remove repeated verbal tics").
 *
 * <p>Nine thousand lines written to one brief drift towards each other. Two habits in particular
 * are invisible while writing and unmistakable while playing: a marked interjection creeping to the
 * front of every fifteenth line, and one all-purpose goodbye standing in for every farewell in the
 * mod. Both were real here — "Aye" opened 3.7% of the English corpus and "Right you are." appeared
 * fifty times — and neither is something a paraphrase lint can see, because each individual line is
 * perfectly good.
 *
 * <p>Two caps, then. A word that is not an ordinary sentence opener may begin at most 2% of the
 * lines in a locale, and no exact sentence of two words or more may appear more than twelve times.
 * Ordinary openers are listed per locale: "I", "You", "That's" and their Portuguese counterparts
 * are how sentences begin, not tics, and capping them would be capping the language.
 */
class VerbalTicLintTest {

    private static final String SEP = System.lineSeparator();

    private static final Path LANG = TestPaths.of("src/main/resources/assets/mca_dialogue/lang");
    private static final String PREFIX = "dialogue.conversations.";

    /** A word that is not one of these may open at most this share of a locale's lines. */
    private static final double OPENER_CAP = 0.020;

    /** No exact sentence of two words or more may stand this many times. */
    private static final int REPEAT_CAP = 12;

    private static final Pattern FIRST_WORD = Pattern.compile("[\"'…. ]*([\\p{L}']+)");

    private static final Set<String> ORDINARY_EN = Set.copyOf(Arrays.asList(
            "i", "i'll", "i'd", "i've", "i'm", "you", "you'll", "you're", "you'd", "it", "it's",
            "the", "that", "that's", "we", "we'll", "we're", "they", "they're", "there", "there's",
            "a", "an", "no", "not", "what", "when", "where", "why", "how", "if", "and", "but", "so",
            "my", "his", "her", "their", "this", "these", "then", "he", "she", "of", "for", "to",
            "in", "on", "at", "as", "or", "one", "two", "some", "most", "every", "all", "nobody",
            "nothing", "somebody", "let", "do", "don't", "did", "does", "is", "was", "were", "have",
            "has", "had", "can", "could", "would", "will", "shall", "should", "yes", "half", "both",
            "since", "after", "before", "because", "about", "with", "from", "by", "up", "out", "go",
            "come", "take", "tell", "ask", "say", "said", "good", "better", "best", "more", "less",
            "enough"));

    private static final Set<String> ORDINARY_PT = Set.copyOf(Arrays.asList(
            "é", "e", "você", "você", "o", "a", "os", "as", "um", "uma", "eu", "vou", "não",
            "não", "então", "então", "que", "de", "do", "da", "em", "no", "na", "por",
            "para", "pra", "com", "se", "me", "meu", "minha", "ele", "ela", "eles", "elas", "nós",
            "nós", "isso", "isto", "esse", "essa", "este", "esta", "aquilo", "tem", "temos",
            "foi", "era", "está", "está", "estou", "sim", "mas", "quando", "onde", "como",
            "porque", "quem", "qual", "mais", "menos", "bem", "bom", "boa", "pode", "posso",
            "sempre", "nunca", "nada", "tudo", "todos", "todo", "toda", "já", "já", "ainda",
            "depois", "antes", "agora", "hoje", "amanhã", "amanhã", "aqui", "ali", "lá",
            "lá", "vá", "vá", "diga", "conte", "fale", "leve", "faça", "faça",
            "deixe", "certo", "claro", "talvez"));

    private record Locale_(String name, Set<String> ordinary) {
    }

    private static final List<Locale_> LOCALES = List.of(
            new Locale_("en_us", ORDINARY_EN),
            new Locale_("pt_br", ORDINARY_PT));

    private static Set<String> buttons;

    /**
     * The keys that are button labels rather than villager lines, asked of the dialogue files
     * rather than guessed from the key's shape. A repeated label is an affordance, not a tic.
     */
    private static Set<String> buttonKeys() throws IOException {
        if (buttons != null) {
            return buttons;
        }
        Set<String> out = new LinkedHashSet<>();
        Path dialogues = TestPaths.of("src/main/resources/data/mcaconversations/dialogues");
        try (Stream<Path> files = Files.list(dialogues)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                String node = file.getFileName().toString().replace(".json", "");
                JsonObject page = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                if (!page.has("answers")) {
                    continue;
                }
                page.getAsJsonArray("answers").forEach(element -> {
                    JsonObject answer = element.getAsJsonObject();
                    if (answer.has("name")) {
                        out.add("dialogue." + node + "." + answer.get("name").getAsString());
                    }
                });
            }
        }
        buttons = out;
        return out;
    }

    private static List<String> linesOf(String locale) throws IOException {
        JsonObject doc = JsonParser.parseString(Files.readString(LANG.resolve(locale + ".json")))
                .getAsJsonObject();
        Set<String> labels = buttonKeys();
        List<String> lines = new ArrayList<>();
        for (String key : doc.keySet()) {
            if (key.startsWith(PREFIX) && !labels.contains(key)) {
                lines.add(doc.get(key).getAsString());
            }
        }
        return lines;
    }

    @Test
    @DisplayName("no marked word opens more than one line in fifty")
    void noWordBecomesACatchphrase() throws IOException {
        List<String> problems = new ArrayList<>();
        for (Locale_ locale : LOCALES) {
            List<String> lines = linesOf(locale.name());
            Map<String, Integer> openers = new LinkedHashMap<>();
            for (String line : lines) {
                Matcher matcher = FIRST_WORD.matcher(line);
                if (matcher.lookingAt()) {
                    openers.merge(matcher.group(1).toLowerCase(Locale.ROOT), 1, Integer::sum);
                }
            }
            int cap = (int) (lines.size() * OPENER_CAP);
            openers.forEach((word, count) -> {
                if (count > cap && !locale.ordinary().contains(word)) {
                    problems.add(String.format(
                            "%s: \"%s\" opens %d of %d lines (%.2f%%), and the cap is %d — it has"
                                    + " stopped being a voice and become a catchphrase",
                            locale.name(), word, count, lines.size(),
                            100.0 * count / lines.size(), cap));
                }
            });
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("no one sentence is everybody's goodbye")
    void noSentenceIsReusedTooOften() throws IOException {
        List<String> problems = new ArrayList<>();
        for (Locale_ locale : LOCALES) {
            Map<String, Integer> repeats = new LinkedHashMap<>();
            for (String line : linesOf(locale.name())) {
                String trimmed = line.trim();
                if (trimmed.split("\\s+").length >= 2) {
                    repeats.merge(trimmed, 1, Integer::sum);
                }
            }
            repeats.forEach((line, count) -> {
                if (count > REPEAT_CAP) {
                    problems.add(String.format(
                            "%s: \"%s\" stands %d times, and the cap is %d — give some of them"
                                    + " their own words", locale.name(), line, count, REPEAT_CAP));
                }
            });
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }
}
