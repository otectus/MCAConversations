package dev.otectus.mcaconversations.content;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dev.otectus.mcaconversations.chat.ChatIntentTestData;
import dev.otectus.mcaconversations.chat.IntentBinding;
import dev.otectus.mcaconversations.chat.Normalizer;
import dev.otectus.mcaconversations.chat.SynonymTable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Build-time lint over the shipped {@code chat_intents/*.json} (spec §7.2, §14). A keyword-table typo
 * or a binding to a non-existent dialogue answer fails CI, not a play session — the same philosophy as
 * {@link ContentLintTest}. Every intent parses through the real {@link IntentBinding#fromJson}; every
 * {@code (question, answer)} must exist in {@code dialogues/}; every deflection line must resolve in
 * lang; every intent must carry enough evidence to ever match.
 */
class ChatIntentLintTest {

    private static final Path DIALOGUES = Path.of("src/main/resources/data/mcaconversations/dialogues");
    private static final Path LANG = Path.of("src/main/resources/assets/mca_dialogue/lang/en_us.json");

    /** Deflection/system phrase families referenced from chat/ Java (ChatModeDispatcher). */
    private static final Set<String> DEFLECTION_KEYS = Set.of(
            "confused", "hint", "shrug", "clarify", "dropped", "busy", "muted", "topics", "farewell", "insult",
            "hail", "hail_cold", "attentive");

    private static Map<String, IntentBinding> intents;
    private static Map<String, Set<String>> dialogueAnswers; // questionId -> answer names
    private static Map<String, String> lang;

    @BeforeAll
    static void load() throws IOException {
        intents = ChatIntentTestData.bindings();

        dialogueAnswers = new HashMap<>();
        try (Stream<Path> files = Files.list(DIALOGUES)) {
            for (Path file : (Iterable<Path>) files::iterator) {
                String questionId = file.getFileName().toString().replace(".json", "");
                JsonObject json = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                Set<String> answers = new HashSet<>();
                if (json.has("answers")) {
                    json.getAsJsonArray("answers").forEach(a -> {
                        JsonObject ao = a.getAsJsonObject();
                        if (ao.has("name")) {
                            answers.add(ao.get("name").getAsString());
                        }
                    });
                }
                dialogueAnswers.put(questionId, answers);
            }
        }

        lang = new Gson().fromJson(Files.readString(LANG),
                TypeToken.getParameterized(Map.class, String.class, String.class).getType());
    }

    @Test
    void everyIntentParsesAndThereIsContent() {
        assertTrue(intents.size() >= 15, "expected the shipped Phase-2 intent set, got " + intents.size());
    }

    @Test
    void topicBindingsExistInDialogues() {
        List<String> problems = new ArrayList<>();
        for (IntentBinding b : intents.values()) {
            if (b.isSystem()) {
                continue; // system intents (greet/farewell/mute/drop/insult) route to dispatcher
                          // behaviors backed by lang pools, not dialogue answers
            }
            String question = b.question();
            String answer = b.answer();
            Set<String> answers = dialogueAnswers.get(question);
            if (answers == null) {
                problems.add(b.id() + ": no dialogue question '" + question + "'");
            } else if (!answers.contains(answer)) {
                problems.add(b.id() + ": question '" + question + "' has no answer '" + answer + "'");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyContextNamesARealSubQuestion() {
        List<String> problems = new ArrayList<>();
        for (IntentBinding b : intents.values()) {
            if (b.context() != null && !dialogueAnswers.containsKey(b.context())) {
                problems.add(b.id() + ": context '" + b.context() + "' is not a real dialogue question");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyIntentHasEnoughEvidence() {
        List<String> problems = new ArrayList<>();
        for (IntentBinding b : intents.values()) {
            if (b.keywords().size() < 3 && b.phrases().isEmpty()) {
                problems.add(b.id() + ": needs >=3 keywords or >=1 phrase (has "
                        + b.keywords().size() + " keywords, " + b.phrases().size() + " phrases)");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * Intents that can be live at the same moment and key on the same words must carry
     * {@code antiKeywords} to tell themselves apart.
     *
     * <p>{@code antiKeywords} shipped on 3 of 340-odd intents. The globally-live
     * {@code personal.regrets} keys on <em>regret</em>, <em>sorry</em> and <em>wrong</em>, which
     * shadows every context-scoped apology stance in the mod, and {@code chatmode.greeting} collides
     * with {@code chitchat.day} on <em>morning</em>, <em>afternoon</em> and <em>evening</em>. When
     * two intents overlap this much and neither says what it is <em>not</em>, the matcher is
     * guessing, and a player apologising for pushing gets a conversation about regrets instead.
     */
    @Test
    @DisplayName("intents that can fire together and share words say what they are not")
    void antiKeywordsDisambiguateCoLiveIntents() {
        final double JACCARD = 0.30;
        List<String> problems = new ArrayList<>();
        List<Map.Entry<String, IntentBinding>> all = new ArrayList<>(intents.entrySet());
        for (int i = 0; i < all.size(); i++) {
            for (int j = i + 1; j < all.size(); j++) {
                IntentBinding a = all.get(i).getValue();
                IntentBinding b = all.get(j).getValue();
                if (!canBeLiveTogether(a, b)) {
                    continue;
                }
                Set<String> left = a.keywords().keySet();
                Set<String> right = b.keywords().keySet();
                Set<String> shared = new HashSet<>(left);
                shared.retainAll(right);
                if (shared.isEmpty()) {
                    continue;
                }
                Set<String> union = new HashSet<>(left);
                union.addAll(right);
                double jaccard = (double) shared.size() / union.size();
                if (jaccard < JACCARD) {
                    continue;
                }
                boolean separated = notEmpty(a.antiKeywords()) || notEmpty(b.antiKeywords());
                if (!separated) {
                    problems.add(String.format(
                            "%s and %s can be live together and share %s (Jaccard %.2f) with no"
                                    + " antiKeywords on either — the matcher is guessing between them",
                            all.get(i).getKey(), all.get(j).getKey(), shared, jaccard));
                }
            }
        }
        problems.sort(null);
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    /**
     * Two intents can be live at the same moment when neither is context-scoped, or when one is
     * scoped to a node and the other is global — a global intent stays live inside every node, which
     * is exactly how the shadowing happens.
     */
    private static boolean canBeLiveTogether(IntentBinding a, IntentBinding b) {
        return a.context() == null || b.context() == null || a.context().equals(b.context());
    }

    private static boolean notEmpty(List<String> values) {
        return values != null && !values.isEmpty();
    }

    @Test
    void noTwoIntentsShareAnIdenticalKeywordSet() {
        SynonymTable syn = ChatIntentTestData.synonyms();
        Map<Set<String>, String> seen = new HashMap<>();
        List<String> problems = new ArrayList<>();
        for (IntentBinding b : intents.values()) {
            if (b.keywords().isEmpty()) {
                continue;
            }
            Set<String> stemmed = new TreeSet<>();
            b.keywords().keySet().forEach(k -> stemmed.add(syn.canonical(Normalizer.stemToken(k))));
            String prior = seen.putIfAbsent(stemmed, b.id());
            if (prior != null) {
                problems.add(b.id() + " and " + prior + " share the keyword set " + stemmed);
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void deflectionAndSystemLinesResolveInLang() {
        List<String> problems = new ArrayList<>();
        for (String key : DEFLECTION_KEYS) {
            String full = "dialogue.chatmode." + key;
            if (!LangKeys.hasLine(lang, full)) {
                problems.add("missing deflection line: " + full);
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void synonymTableHasNoConflicts() {
        SynonymTable.Builder b = SynonymTable.builder();
        for (Path file : ChatIntentTestData.files()) {
            JsonObject obj = ChatIntentTestData.read(file);
            if (obj.has("synonyms")) {
                obj.getAsJsonObject("synonyms").entrySet().forEach(e -> {
                    List<String> aliases = new ArrayList<>();
                    e.getValue().getAsJsonArray().forEach(a -> aliases.add(a.getAsString()));
                    b.addClass(e.getKey(), aliases);
                });
            }
        }
        assertTrue(b.conflicts().isEmpty(), "synonym conflicts: " + b.conflicts());
    }

    @Test
    void systemAndTopicIntentsAreDistinctlyFormed() {
        List<String> problems = new ArrayList<>();
        for (IntentBinding b : intents.values()) {
            boolean topic = b.question() != null;
            boolean system = b.system() != null;
            if (topic == system) {
                problems.add(b.id() + ": must be exactly one of topic or system");
            }
        }
        assertFalse(intents.isEmpty());
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }
}
