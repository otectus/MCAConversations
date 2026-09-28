package dev.otectus.mcaconversations.compat.quests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Every translation key the MCA: Quests objective and reward types name is this mod's own, and exists in
 * every locale it ships.
 *
 * <p>MCA: Quests renders whatever {@code Component} an add-on objective or reward describes itself with,
 * on the offer screen, the journal and the quest card. The keys used to be
 * {@code mcaquests.objective.mcaconversations.talk_about} and
 * {@code mcaquests.reward.mcaconversations.unlock_topic}: their text lived in MCA: Quests' lang file,
 * under the pre-rename {@code mcarealtalk} names, so both lines showed as raw keys to every player from
 * the 0.4.0 rename until 1.8.0. Nothing held the two repositories together; this does, from this side
 * only, by keeping the text here.
 *
 * <p>Only whole literal keys are checked. A key built at runtime, such as the per-topic fallback
 * {@code "mcaconversations.topic." + topic}, is skipped: it carries its own fallback text.
 */
class QuestsCompatLangKeysTest {

    private static final Path PACKAGE = TestPaths.of("src/main/java/dev/otectus/mcaconversations/compat/quests");
    private static final Path LANG = TestPaths.of("src/main/resources/assets/mcaconversations/lang");
    private static final List<String> LOCALES = List.of("en_us", "pt_br");

    /** A literal key passed whole: the closing quote is followed by an argument separator or ')'. */
    private static final Pattern LITERAL_KEY =
            Pattern.compile("Component\\.translatable(?:WithFallback)?\\(\\s*\"([^\"]+)\"\\s*[,)]");

    @Test
    void everyQuestsCompatKeyIsOursAndTranslatedInEveryLocale() throws IOException {
        Set<String> keys = literalKeys();
        assertFalse(keys.isEmpty(), "found no translation key under " + PACKAGE + "; is the scan broken?");

        List<String> foreign = new ArrayList<>();
        for (String key : keys) {
            if (!key.startsWith("mcaconversations.")) {
                foreign.add(key);
            }
        }
        assertEquals(List.of(), foreign, "keys outside this mod's namespace live in another mod's lang "
                + "file, where nothing keeps them in step with this code");

        for (String locale : LOCALES) {
            JsonObject lang = JsonParser.parseString(Files.readString(LANG.resolve(locale + ".json"),
                    StandardCharsets.UTF_8)).getAsJsonObject();
            List<String> missing = new ArrayList<>();
            for (String key : keys) {
                if (!lang.has(key)) {
                    missing.add(key);
                }
            }
            assertEquals(List.of(), missing, locale + ".json lacks these MCA: Quests objective/reward keys, "
                    + "so the quest UI would print them raw");
        }
    }

    private static Set<String> literalKeys() throws IOException {
        Set<String> keys = new TreeSet<>();
        try (Stream<Path> files = Files.list(PACKAGE)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".java")).toList()) {
                Matcher matcher = LITERAL_KEY.matcher(Files.readString(file, StandardCharsets.UTF_8));
                while (matcher.find()) {
                    keys.add(matcher.group(1));
                }
            }
        }
        return keys;
    }
}
