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
            "confused", "hint", "shrug", "clarify", "dropped", "busy", "muted", "topics", "farewell", "insult");

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
            String question;
            String answer;
            if (b.isSystem()) {
                if (!"greet".equals(b.system())) {
                    continue; // farewell/mute/drop route to dispatcher behaviors, not a dialogue answer
                }
                question = "greet";
                answer = "checkin";
            } else {
                question = b.question();
                answer = b.answer();
            }
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
            if (!lang.containsKey(full)) {
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
