package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.support.TestPaths;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.context.ContextKey;
import dev.otectus.mcaconversations.context.ContextQuery;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/** Source-level checks supplement compiled graph contracts; they do not claim to prove prose meaning. */
class SourceNarrativeLintTest {
    private static final Path SOURCES = TestPaths.of("src/content");
    private static final Pattern PLACEHOLDER = Pattern.compile("%(?:\\d+\\$)?[a-zA-Z]|\\{[a-zA-Z_][a-zA-Z0-9_.]*}");
    private static final Map<String, Set<String>> DOMAINS = Map.of(
            "time.band", Set.of("dawn", "morning", "midday", "afternoon", "dusk", "night"),
            "speaker.age", Set.of("baby", "child", "teen", "adult"),
            "speaker.mood", Set.of("depressed", "sad", "unhappy", "passive", "fine", "happy", "overjoyed"),
            "weather.state", Set.of("clear", "rain", "storm"),
            "player.health_band", Set.of("hale", "hurt", "grave"),
            "speaker.health_band", Set.of("hale", "hurt", "grave"));

    private static Map<String, JsonObject> sources() throws IOException {
        Map<String, JsonObject> result = new TreeMap<>();
        try (var paths = Files.walk(SOURCES)) {
            for (Path path : paths.filter(p -> p.toString().endsWith(".json")).sorted().toList()) {
                result.put(SOURCES.relativize(path).toString().replace('\\', '/'),
                        JsonParser.parseString(Files.readString(path)).getAsJsonObject());
            }
        }
        return result;
    }

    private static void walk(JsonElement value, String path, BiConsumer<String, JsonObject> visitor) {
        if (value.isJsonObject()) {
            JsonObject object = value.getAsJsonObject();
            visitor.accept(path, object);
            object.entrySet().forEach(e -> walk(e.getValue(), path + "/" + e.getKey(), visitor));
        } else if (value.isJsonArray()) {
            for (int i = 0; i < value.getAsJsonArray().size(); i++) {
                walk(value.getAsJsonArray().get(i), path + "/" + i, visitor);
            }
        }
    }

    @Test
    void authoredLocalesHaveMatchingShapesPlaceholdersAndDistinctPoolVariants() throws IOException {
        List<String> errors = new ArrayList<>();
        sources().forEach((file, source) -> walk(source, file, (path, object) -> {
            for (String[] locale : List.of(new String[]{"en", "pt"}, new String[]{"en_us", "pt_br"})) {
                if (!object.has(locale[0]) && !object.has(locale[1])) continue;
                if (!object.has(locale[0]) || !object.has(locale[1])) {
                    errors.add(path + ": missing authored locale"); continue;
                }
                List<String> english = strings(object.get(locale[0]));
                List<String> portuguese = strings(object.get(locale[1]));
                if (english.size() != portuguese.size()) {
                    errors.add(path + ": locale variant counts differ"); continue;
                }
                for (int i = 0; i < english.size(); i++) {
                    if (english.get(i).isBlank() || portuguese.get(i).isBlank()) errors.add(path + ": blank variant " + i);
                    if (!placeholders(english.get(i)).equals(placeholders(portuguese.get(i)))) {
                        errors.add(path + ": placeholder multiset differs at variant " + i);
                    }
                }
                // Detect duplicate variants inside a pool; shared labels across different pages are legal.
                for (List<String> lines : List.of(english, portuguese)) {
                    Set<String> seen = new HashSet<>();
                    for (String line : lines) if (!seen.add(normalized(line))) errors.add(path + ": duplicate variant " + line);
                }
            }
        }));
        assertTrue(errors.isEmpty(), String.join("\n", errors));
    }

    @Test
    void contextGatesUseSupportedValuesAndHaveANonemptyIntersection() throws IOException {
        List<String> errors = new ArrayList<>();
        sources().forEach((file, source) -> walk(source, file, (path, object) -> {
            if (object.has("conditions") && object.get("conditions").isJsonArray()) {
                errors.addAll(gateErrors(object.getAsJsonArray("conditions"), path));
            }
        }));
        assertTrue(errors.isEmpty(), String.join("\n", errors));
    }

    @Test
    void contradictionDetectorRejectsDisjointAndInvertedConditionsButAllowsCollectionMembership() {
        assertFalse(gateErrors(array("""
                [{"field":"time.band","is":"morning"},{"field":"time.band","is":"night"}]
                """), "fixture").isEmpty());
        assertFalse(gateErrors(array("""
                [{"field":"time.days_since_first_met","min":30},{"field":"time.days_since_first_met","max":7}]
                """), "fixture").isEmpty());
        assertFalse(gateErrors(array("""
                [{"field":"weather.state","is":"rain"},{"field":"weather.state","is":"rain","not":true}]
                """), "fixture").isEmpty());
        assertFalse(gateErrors(array("""
                [{"field":"time.band","is":"evening"}]
                """), "fixture").isEmpty());
        assertTrue(gateErrors(array("""
                [{"field":"player.held_tags","has":"food"},{"field":"player.held_tags","has":"tool"}]
                """), "fixture").isEmpty());
    }

    static List<String> gateErrors(JsonArray conditions, String path) {
        List<String> errors = new ArrayList<>();
        Map<String, Set<String>> possible = new LinkedHashMap<>();
        Map<String, Set<String>> denied = new LinkedHashMap<>();
        Map<String, double[]> ranges = new LinkedHashMap<>();
        for (JsonElement item : conditions) {
            if (!item.isJsonObject() || !item.getAsJsonObject().has("field")) continue;
            JsonObject gate = item.getAsJsonObject();
            String field = gate.get("field").getAsString();
            ContextQuery query;
            try { query = ContextQuery.fromJson(gate); }
            catch (RuntimeException malformed) { errors.add(path + ": malformed " + field); continue; }
            if (!query.isValid()) { errors.add(path + ": unknown context field " + field); continue; }
            ContextKey<?> key = query.field();
            if (DOMAINS.containsKey(field) && !DOMAINS.get(field).containsAll(query.anyOf())) {
                errors.add(path + ": unsupported value for " + field + " " + query.anyOf());
            }
            boolean scalar = !Collection.class.isAssignableFrom(key.type());
            if (scalar && !query.anyOf().isEmpty()) {
                if (query.negate()) denied.computeIfAbsent(field, k -> new HashSet<>()).addAll(query.anyOf());
                else if (!possible.containsKey(field)) possible.put(field, new HashSet<>(query.anyOf()));
                else possible.get(field).retainAll(query.anyOf());
            }
            if (!query.negate() && (query.min().isPresent() || query.max().isPresent())) {
                double[] range = ranges.computeIfAbsent(field, k -> new double[]{Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY});
                query.min().ifPresent(min -> range[0] = Math.max(range[0], min));
                query.max().ifPresent(max -> range[1] = Math.min(range[1], max));
            }
        }
        possible.forEach((field, values) -> {
            values.removeAll(denied.getOrDefault(field, Set.of()));
            if (values.isEmpty()) errors.add(path + ": contradictory scalar gates for " + field);
        });
        ranges.forEach((field, bounds) -> {
            if (!Double.isFinite(bounds[0]) && bounds[0] != Double.NEGATIVE_INFINITY
                    || !Double.isFinite(bounds[1]) && bounds[1] != Double.POSITIVE_INFINITY
                    || bounds[0] > bounds[1]) errors.add(path + ": contradictory numeric gates for " + field);
        });
        return errors;
    }

    @Test
    void replyReferentsArePresentInEveryPooledOpeningAndHighFrequencyPoolsKeepThreeVariants() throws IOException {
        List<String> errors = new ArrayList<>();
        sources().forEach((file, source) -> {
            if (!file.startsWith("topics/")) return;
            for (JsonElement value : source.getAsJsonArray("scenes")) {
                JsonObject scene = value.getAsJsonObject();
                String path = file + "/" + scene.get("name").getAsString();
                if (scene.has("reply_referents")) {
                    for (String locale : List.of("en", "pt")) {
                        for (String referent : strings(scene.getAsJsonObject("reply_referents").get(locale))) {
                            for (String line : strings(scene.getAsJsonObject("lines").get(locale))) {
                                if (!normalized(line).contains(normalized(referent))) errors.add(path + ": reply referent " + referent + " absent from " + line);
                            }
                        }
                    }
                }
                int cooldown = scene.has("cooldown_days") ? scene.get("cooldown_days").getAsInt() : 2;
                if (cooldown <= 1 && scene.getAsJsonObject("lines").getAsJsonArray("en").size() < 3) {
                    errors.add(path + ": high-frequency opening needs at least three edited variants");
                }
            }
        });
        assertTrue(errors.isEmpty(), String.join("\n", errors));
    }

    @Test
    void confidentialTopicsHaveExecutablePrivacyAndTrustGates() throws IOException {
        List<String> errors = new ArrayList<>();
        sources().forEach((file, source) -> {
            if (!file.startsWith("topics/")) return;
            for (JsonElement value : source.getAsJsonArray("scenes")) {
                JsonObject scene = value.getAsJsonObject();
                if (!scene.has("privacy") || !scene.get("privacy").getAsString().equals("confidential")) continue;
                boolean alone = false, trusted = false;
                for (JsonElement condition : scene.has("conditions") ? scene.getAsJsonArray("conditions") : new JsonArray()) {
                    JsonObject gate = condition.getAsJsonObject();
                    String field = gate.get("field").getAsString();
                    if (field.equals("social.nearby") && gate.has("max") && gate.get("max").getAsInt() == 0
                            && "fail".equals(gate.get("unknown").getAsString())) alone = true;
                    if (field.equals("player.relationship_band") && gate.has("any_of")) {
                        List<String> bands = strings(gate.get("any_of"));
                        trusted = !bands.isEmpty() && Set.of("friend", "confidant", "partner", "family").containsAll(bands);
                    }
                }
                if (!alone || !trusted) errors.add(file + "/" + scene.get("name").getAsString() + ": confidential scene lacks actual privacy/trust gates");
            }
        });
        assertTrue(errors.isEmpty(), String.join("\n", errors));
    }

    private static JsonArray array(String json) { return JsonParser.parseString(json).getAsJsonArray(); }
    private static List<String> strings(JsonElement element) {
        if (element == null || element.isJsonNull()) return List.of();
        if (element.isJsonPrimitive()) return List.of(element.getAsString());
        List<String> result = new ArrayList<>();
        element.getAsJsonArray().forEach(value -> result.add(value.getAsString()));
        return result;
    }
    private static String normalized(String line) {
        return java.text.Normalizer.normalize(line.toLowerCase(Locale.ROOT), java.text.Normalizer.Form.NFKD)
                .replaceAll("\\p{M}+", "").replaceAll("[^a-z0-9%$]+", " ").trim();
    }
    private static Map<String, Integer> placeholders(String line) {
        Map<String, Integer> counts = new TreeMap<>();
        Matcher matcher = PLACEHOLDER.matcher(line);
        while (matcher.find()) counts.merge(matcher.group(), 1, Integer::sum);
        return counts;
    }
}
