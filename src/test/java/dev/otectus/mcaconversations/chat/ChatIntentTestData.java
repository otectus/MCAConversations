package dev.otectus.mcaconversations.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Shared test fixture: loads the shipped {@code chat_intents/*.json} straight off disk (mirroring
 * {@link ChatIntentLoader}'s two-pass synonym-then-intent build) so both {@code IntentMatcherTest} and
 * {@code content.ChatIntentLintTest} validate the real content, not a hand-built stand-in.
 */
public final class ChatIntentTestData {

    public static final Path INTENTS = Path.of("src/main/resources/data/mcaconversations/chat_intents");

    private ChatIntentTestData() {
    }

    public static List<Path> files() {
        try (Stream<Path> s = Files.list(INTENTS)) {
            return s.filter(p -> p.toString().endsWith(".json")).sorted().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static JsonObject read(Path file) {
        try {
            return JsonParser.parseString(Files.readString(file)).getAsJsonObject();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static SynonymTable synonyms() {
        SynonymTable.Builder b = SynonymTable.builder();
        for (Path file : files()) {
            JsonObject obj = read(file);
            if (obj.has("synonyms")) {
                for (Map.Entry<String, JsonElement> e : obj.getAsJsonObject("synonyms").entrySet()) {
                    List<String> aliases = new ArrayList<>();
                    e.getValue().getAsJsonArray().forEach(a -> aliases.add(a.getAsString()));
                    b.addClass(e.getKey(), aliases);
                }
            }
        }
        return b.build();
    }

    /** Every shipped intent, keyed by id (insertion order across sorted files). */
    public static Map<String, IntentBinding> bindings() {
        Map<String, IntentBinding> byId = new LinkedHashMap<>();
        for (Path file : files()) {
            JsonObject obj = read(file);
            if (!obj.has("intents")) {
                continue;
            }
            for (Map.Entry<String, JsonElement> e : obj.getAsJsonObject("intents").entrySet()) {
                byId.put(e.getKey(), IntentBinding.fromJson(e.getKey(), e.getValue().getAsJsonObject()));
            }
        }
        return byId;
    }

    public static IntentIndex index() {
        return IntentIndex.build(new ArrayList<>(bindings().values()), synonyms());
    }
}
