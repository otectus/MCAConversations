package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.ReplyContract;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * Every button a player can press can also be spoken (spec §16, frontends and locales).
 *
 * <p>The acceptance criterion is "every non-exit GUI answer is selectable through chat mode". Two
 * kinds of answer are outside it, and both are the mod's own existing distinction rather than one
 * invented here:
 *
 * <ul>
 *   <li><b>Hubs.</b> {@code ChatModeDispatcher.isHubQuestion} already treats {@code conversations}
 *       and {@code conversations.cat.*} as "menus in the GUI, and nothing to answer in chat" — chat
 *       jumps straight to a {@code (question, answer)} pair, so the menu path is never walked. The
 *       two sub-hubs, {@code conversations.family} and {@code conversations.us}, are menus in the
 *       same sense.</li>
 *   <li><b>Ways out.</b> An answer whose reply contract declares {@code exit}, and the {@code back}
 *       button that returns to a menu. Chat mode has {@code never mind} for both.</li>
 * </ul>
 *
 * <p>Everything else is a reply to something a villager said, and a reply that cannot be typed is a
 * conversation the chat-mode player cannot have.
 */
class ChatCoverageTest {

    private static final String SEP = System.lineSeparator();

    private static final Path DIALOGUES =
            TestPaths.of("src/main/resources/data/mcaconversations/dialogues");
    private static final Path INTENTS =
            TestPaths.of("src/main/resources/data/mcaconversations/chat_intents");
    private static final Path BEATS =
            TestPaths.of("src/main/resources/data/mcaconversations/conversation_beats");

    /** The menu pages: pressed in the GUI, jumped past in chat. */
    private static boolean isMenu(String question) {
        return question.equals("conversations")
                || question.equals("main")
                || question.startsWith("conversations.cat.")
                || question.equals("conversations.family")
                || question.equals("conversations.us");
    }

    private record Answer(String question, String answer) {
        String key() {
            return question + "/" + answer;
        }
    }

    private static List<Answer> answers;
    private static Set<String> spoken;
    private static Set<String> ways_out;

    @BeforeAll
    static void load() throws IOException {
        answers = new ArrayList<>();
        try (Stream<Path> files = Files.list(DIALOGUES)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                String node = file.getFileName().toString().replace(".json", "");
                JsonObject page = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                if (!page.has("answers")) {
                    continue;
                }
                for (JsonElement element : page.getAsJsonArray("answers")) {
                    JsonObject entry = element.getAsJsonObject();
                    if (entry.has("name")) {
                        answers.add(new Answer(node, entry.get("name").getAsString()));
                    }
                }
            }
        }

        spoken = new LinkedHashSet<>();
        try (Stream<Path> files = Files.list(INTENTS)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                JsonObject doc = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                if (!doc.has("intents")) {
                    continue;
                }
                JsonObject table = doc.getAsJsonObject("intents");
                for (String id : table.keySet()) {
                    JsonObject intent = table.getAsJsonObject(id);
                    if (intent.has("question") && intent.has("answer")) {
                        spoken.add(intent.get("question").getAsString() + "/"
                                + intent.get("answer").getAsString());
                    }
                }
            }
        }

        ways_out = new LinkedHashSet<>();
        for (ReplyContract reply : ContentFixture.catalog().replies()) {
            if (reply.exit()) {
                ways_out.add(reply.key());
            }
        }
    }

    @Test
    @DisplayName("every reply a player can press, a player can also type")
    void everyReplyIsSpeakable() {
        List<String> problems = new ArrayList<>();
        for (Answer answer : answers) {
            if (isMenu(answer.question()) || "back".equals(answer.answer())) {
                continue;
            }
            if (ways_out.contains(answer.key()) || spoken.contains(answer.key())) {
                continue;
            }
            problems.add(answer.key() + " has no chat intent — it can be pressed in the GUI and"
                    + " not said out loud, which spec section 16 does not allow");
        }
        assertTrue(problems.isEmpty(), problems.size() + " unspeakable reply button(s):" + SEP
                + String.join(SEP, problems));
    }

    /**
     * The other direction. An intent naming a button that no longer exists is a phrase the player
     * can type into silence, and it survives every rename because nothing else reads these ids.
     */
    @Test
    @DisplayName("every chat intent names a button that exists")
    void everyIntentNamesALiveButton() {
        Set<String> live = new LinkedHashSet<>();
        answers.forEach(a -> live.add(a.key()));
        List<String> problems = new ArrayList<>();
        for (String binding : spoken) {
            if (!live.contains(binding)) {
                problems.add(binding + " is bound by a chat intent and is not an answer on that page");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }
}
