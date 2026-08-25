package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * One row of the conversation catalog: the machine-readable claim that a topic exists, where it is
 * entered, how deep it is allowed to be, and which durable state it is allowed to write.
 *
 * <p>The catalog is not a second dialogue engine — MCA's JSON stays authoritative for what actually
 * happens. The catalog exists so that "every shipped topic is a real conversation" is a statement
 * lint can check, and so arc ids, milestone ids and exclusive-choice groups are declared in one place
 * a typo cannot slip past (plan §4.5, §6.2).
 *
 * <p>Parse failures throw; the loader contains them per entry so one bad topic never takes down a
 * datapack reload.
 */
public record TopicEntry(String id,
                         String entryQuestion,
                         String entryAnswer,
                         DepthClass depth,
                         String returnQuestion,
                         Set<String> ages,
                         Set<StanceFamily> requiredStanceFamilies,
                         boolean chatRequired,
                         Optional<Arc> arc,
                         Set<String> milestones,
                         Map<String, Set<String>> exclusiveGroups) {

    /** MCA's age vocabulary, minus {@code baby} — babies babble and are never catalog topics. */
    public static final Set<String> AGE_GROUPS = Set.of("toddler", "child", "teen", "adult");

    /** Bare, dot-separated lowercase ids — the shape every arc, milestone and exclusive id must take. */
    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");

    /** Highest arc stage any topic may declare; keeps ordered progressions bounded and lintable. */
    public static final int MAX_ARC_STAGE = 8;

    /**
     * An ordered, bounded progression that advances across separate conversations. Stage 0 means
     * "never started"; {@code maxStage} is the final stage and the clamp the store enforces.
     */
    public record Arc(String id, int maxStage) {
    }

    public static TopicEntry fromJson(String id, JsonObject json) {
        requireId(id, "topic id");
        if (!json.has("entry") || !json.get("entry").isJsonObject()) {
            throw new IllegalArgumentException("topic '" + id + "' requires an \"entry\" object");
        }
        JsonObject entry = json.getAsJsonObject("entry");
        String entryQuestion = requireString(entry, "question", id);
        String entryAnswer = requireString(entry, "answer", id);

        DepthClass depth = DepthClass.byKey(requireString(json, "depth", id))
                .orElseThrow(() -> new IllegalArgumentException(
                        "topic '" + id + "' has an unknown depth class"));
        String returnQuestion = requireString(json, "return_question", id);

        Set<String> ages = new LinkedHashSet<>();
        for (JsonElement element : requireArray(json, "ages", id)) {
            String age = element.getAsString();
            if (!AGE_GROUPS.contains(age)) {
                throw new IllegalArgumentException("topic '" + id + "' lists unknown age group '" + age + "'");
            }
            ages.add(age);
        }
        if (ages.isEmpty()) {
            throw new IllegalArgumentException("topic '" + id + "' must list at least one age group");
        }

        Set<StanceFamily> families = new LinkedHashSet<>();
        for (JsonElement element : requireArray(json, "required_stance_families", id)) {
            String family = element.getAsString();
            families.add(StanceFamily.byKey(family).orElseThrow(() -> new IllegalArgumentException(
                    "topic '" + id + "' lists unknown stance family '" + family + "'")));
        }
        if (!families.contains(StanceFamily.EXIT)) {
            // Every node needs a graceful exit (plan §3.5); requiring it in the catalog means lint can
            // fail a topic that forgot one rather than discovering it as a dead end in play.
            throw new IllegalArgumentException("topic '" + id + "' must require the 'exit' stance family");
        }

        boolean chatRequired = !json.has("chat_required") || json.get("chat_required").getAsBoolean();

        Optional<Arc> arc = Optional.empty();
        if (json.has("arc") && json.get("arc").isJsonObject()) {
            JsonObject arcJson = json.getAsJsonObject("arc");
            String arcId = requireId(requireString(arcJson, "id", id), "arc id of topic '" + id + "'");
            int maxStage = arcJson.has("max_stage") ? arcJson.get("max_stage").getAsInt() : 0;
            if (maxStage < 1 || maxStage > MAX_ARC_STAGE) {
                throw new IllegalArgumentException(
                        "topic '" + id + "' declares arc max_stage " + maxStage + " outside 1.." + MAX_ARC_STAGE);
            }
            arc = Optional.of(new Arc(arcId, maxStage));
        }

        Set<String> milestones = new LinkedHashSet<>();
        if (json.has("milestones")) {
            for (JsonElement element : json.getAsJsonArray("milestones")) {
                milestones.add(requireId(element.getAsString(), "milestone id of topic '" + id + "'"));
            }
        }

        Map<String, Set<String>> exclusiveGroups = new LinkedHashMap<>();
        if (json.has("exclusive_groups") && json.get("exclusive_groups").isJsonObject()) {
            JsonObject groups = json.getAsJsonObject("exclusive_groups");
            for (String group : groups.keySet()) {
                requireId(group, "exclusive group of topic '" + id + "'");
                Set<String> members = new LinkedHashSet<>();
                for (JsonElement element : groups.getAsJsonArray(group)) {
                    members.add(requireId(element.getAsString(),
                            "member of exclusive group '" + group + "' in topic '" + id + "'"));
                }
                if (members.size() < 2) {
                    throw new IllegalArgumentException("exclusive group '" + group + "' in topic '" + id
                            + "' needs at least two mutually exclusive members");
                }
                exclusiveGroups.put(group, Set.copyOf(members));
            }
        }

        return new TopicEntry(id, entryQuestion, entryAnswer, depth, returnQuestion,
                Set.copyOf(ages), Set.copyOf(families), chatRequired, arc,
                Set.copyOf(milestones), Map.copyOf(exclusiveGroups));
    }

    /** True when this topic may be entered by a villager of the given MCA age group. */
    public boolean allowsAge(String ageGroup) {
        return ageGroup != null && ages.contains(ageGroup);
    }

    private static String requireString(JsonObject json, String field, String topicId) {
        if (!json.has(field) || json.get(field).getAsString().isBlank()) {
            throw new IllegalArgumentException("topic '" + topicId + "' requires a non-blank \"" + field + "\"");
        }
        return json.get(field).getAsString();
    }

    private static JsonArray requireArray(JsonObject json, String field, String topicId) {
        if (!json.has(field) || !json.get(field).isJsonArray()) {
            throw new IllegalArgumentException("topic '" + topicId + "' requires a \"" + field + "\" array");
        }
        return json.getAsJsonArray(field);
    }

    private static String requireId(String value, String what) {
        if (value == null || !ID.matcher(value).matches()) {
            throw new IllegalArgumentException(what + " must match " + ID.pattern() + " (got '" + value + "')");
        }
        return value;
    }
}
