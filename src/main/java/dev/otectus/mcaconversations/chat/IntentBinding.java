package dev.otectus.mcaconversations.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * One parsed chat-intent entry (spec §7.1): the raw, un-stemmed datapack description of how a phrasing
 * maps to a dialogue answer. Either a <b>topic</b> intent (binds {@code question}+{@code answer} to
 * drive {@code selectAnswer}) or a <b>system</b> intent (routes to a dispatcher behavior like
 * {@code greet}); the two are mutually exclusive.
 *
 * <p>Pure ✦ record — imports only Gson + the JDK. {@link #fromJson} throws
 * {@link IllegalArgumentException} (id-prefixed) on any structural problem so
 * {@link dev.otectus.mcaconversations.util.SafeParse} contains a bad datapack entry to a skipped
 * intent rather than a failed reload. Keywords/phrases are kept as authored surface words; the
 * {@link IntentIndex} stems and synonym-canonicalizes them at load with the same {@link Normalizer}
 * the query path uses.
 */
public record IntentBinding(
        String id,
        String question,      // null for system intents
        String answer,        // null for system intents
        String system,        // null for topic intents; else greet|farewell|mute|drop
        Map<String, Double> keywords,
        List<String> requiresAny,
        List<String> requiresAll,
        List<String> phrases,
        List<String> antiKeywords,
        List<String> bigrams,
        String context,       // nullable: only live when session.currentQuestion equals this id
        String category) {    // nullable: gates on isFeatureEnabled(category)

    public boolean isSystem() {
        return system != null;
    }

    /** True when the intent can never match — no positive evidence declared (keywords or phrases). */
    public boolean hasNoEvidence() {
        return keywords.isEmpty() && phrases.isEmpty();
    }

    public static IntentBinding fromJson(String id, JsonObject json) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("chat intent requires a non-blank id");
        }
        boolean hasSystem = json.has("system");
        boolean hasQuestion = json.has("question");
        if (hasSystem == hasQuestion) {
            throw new IllegalArgumentException(id + ": exactly one of \"question\" or \"system\" is required");
        }

        String system = null;
        String question = null;
        String answer = null;
        if (hasSystem) {
            system = nonBlank(id, "system", json.get("system").getAsString());
        } else {
            question = nonBlank(id, "question", json.get("question").getAsString());
            if (!json.has("answer")) {
                throw new IllegalArgumentException(id + ": a topic intent requires an \"answer\"");
            }
            answer = nonBlank(id, "answer", json.get("answer").getAsString());
        }

        Map<String, Double> keywords = parseKeywords(id, json);
        List<String> phrases = parseStringArray(id, json, "phrases");
        if (keywords.isEmpty() && phrases.isEmpty()) {
            throw new IllegalArgumentException(id + ": needs at least one keyword or phrase");
        }

        List<String> requiresAny = parseStringArray(id, json, "requiresAny");
        List<String> requiresAll = parseStringArray(id, json, "requiresAll");
        List<String> antiKeywords = parseStringArray(id, json, "antiKeywords");
        List<String> bigrams = parseStringArray(id, json, "bigrams");
        String context = json.has("context") ? nonBlank(id, "context", json.get("context").getAsString()) : null;
        String category = json.has("category") ? nonBlank(id, "category", json.get("category").getAsString()) : null;

        return new IntentBinding(id, question, answer, system,
                Map.copyOf(keywords), List.copyOf(requiresAny), List.copyOf(requiresAll),
                List.copyOf(phrases), List.copyOf(antiKeywords), List.copyOf(bigrams), context, category);
    }

    private static Map<String, Double> parseKeywords(String id, JsonObject json) {
        Map<String, Double> out = new LinkedHashMap<>();
        if (!json.has("keywords")) {
            return out;
        }
        if (!json.get("keywords").isJsonObject()) {
            throw new IllegalArgumentException(id + ": \"keywords\" must be an object of word:weight");
        }
        for (Map.Entry<String, JsonElement> e : json.getAsJsonObject("keywords").entrySet()) {
            String word = e.getKey();
            if (word.isBlank()) {
                throw new IllegalArgumentException(id + ": blank keyword");
            }
            double weight;
            try {
                weight = e.getValue().getAsDouble();
            } catch (RuntimeException ex) {
                throw new IllegalArgumentException(id + ": keyword \"" + word + "\" weight must be a number");
            }
            if (weight <= 0 || weight > 10) {
                throw new IllegalArgumentException(id + ": keyword \"" + word + "\" weight out of range (0,10]: " + weight);
            }
            out.put(word, weight);
        }
        return out;
    }

    private static List<String> parseStringArray(String id, JsonObject json, String key) {
        List<String> out = new ArrayList<>();
        if (!json.has(key)) {
            return out;
        }
        if (!json.get(key).isJsonArray()) {
            throw new IllegalArgumentException(id + ": \"" + key + "\" must be an array of strings");
        }
        JsonArray arr = json.getAsJsonArray(key);
        for (JsonElement el : arr) {
            String s = el.getAsString();
            if (s.isBlank()) {
                throw new IllegalArgumentException(id + ": blank entry in \"" + key + "\"");
            }
            out.add(s);
        }
        return out;
    }

    private static String nonBlank(String id, String field, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(id + ": \"" + field + "\" must be non-blank");
        }
        return value;
    }
}
