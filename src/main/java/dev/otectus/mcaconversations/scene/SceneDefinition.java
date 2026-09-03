package dev.otectus.mcaconversations.scene;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.context.ContextQuery;
import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.SceneShape;
import dev.otectus.mcaconversations.history.EpisodeState;
import dev.otectus.mcaconversations.identity.ProfileQuery;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * One authored scene: what it is for, who may be shown it, what it needs bound, and where it routes
 * (spec §10.4).
 *
 * <p>A scene is <b>not</b> dialogue. It names an existing contracted question and opening beat, and
 * everything else here is the rule for when that route is the right one to take. That separation is
 * what lets the whole dynamic layer sit on top of MCA's dialogue engine without replacing it: the
 * director picks a scene, the scene names a route, and the route is the same contracted JSON the mod
 * has always shipped.
 *
 * <p>The fields fall into four groups, and it is worth naming them because the parser enforces
 * different rules for each:
 *
 * <ul>
 *   <li><b>Identity</b> — {@link #id}, {@link #purpose}, {@link #topic}, {@link #shape}.</li>
 *   <li><b>Hard eligibility</b> — professions, ages, bands, episode kind and states, context and
 *       profile conditions, required slots, integrations. Every one is a gate; none of them is ever
 *       expressed as a large negative score, because a hard gate that can be outvoted is not a gate
 *       (spec §9.2).</li>
 *   <li><b>Scoring</b> — base priority, the identity tokens this scene suits, cooldowns and mention
 *       caps. Preferences only.</li>
 *   <li><b>Route and consequence</b> — question, opening beat, the thread it opens, the fallback it
 *       degrades to.</li>
 * </ul>
 */
public record SceneDefinition(String id,
                              ScenePurpose purpose,
                              String topic,
                              SceneShape shape,
                              Set<String> professions,
                              Set<String> archetypes,
                              Set<String> subjectsAny,
                              Set<String> ages,
                              Set<RelationshipBand> relationships,
                              String episodeKind,
                              Set<EpisodeState> episodeStates,
                              Map<String, SlotType> requiredSlots,
                              List<ContextQuery> contextConditions,
                              List<ProfileQuery> profileConditions,
                              Set<String> integrations,
                              int basePriority,
                              Set<String> identityValues,
                              Set<String> identityInterests,
                              Set<String> identityStyles,
                              int cooldownDays,
                              int maxMentionsPerWeek,
                              String questionId,
                              String openingBeatId,
                              String threadTemplate,
                              String fallbackScene) {

    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");
    public static final Pattern PROFESSION = Pattern.compile("[a-z0-9_.-]+:[a-z0-9_./-]+");

    /** Scoring ceiling, so no single authored priority can outrank a due obligation by itself. */
    public static final int MAX_BASE_PRIORITY = 60;

    public SceneDefinition {
        id = normalize(id);
        topic = normalize(topic);
        episodeKind = normalize(episodeKind);
        questionId = questionId == null ? "" : questionId.trim();
        openingBeatId = normalize(openingBeatId);
        threadTemplate = normalize(threadTemplate);
        fallbackScene = normalize(fallbackScene);
        purpose = purpose == null ? ScenePurpose.TOPIC : purpose;
        shape = shape == null ? SceneShape.OBSERVE : shape;
        professions = Set.copyOf(professions);
        archetypes = Set.copyOf(archetypes);
        subjectsAny = Set.copyOf(subjectsAny);
        ages = Set.copyOf(ages);
        relationships = Set.copyOf(relationships);
        episodeStates = Set.copyOf(episodeStates);
        requiredSlots = Map.copyOf(requiredSlots);
        contextConditions = List.copyOf(contextConditions);
        profileConditions = List.copyOf(profileConditions);
        integrations = Set.copyOf(integrations);
        identityValues = Set.copyOf(identityValues);
        identityInterests = Set.copyOf(identityInterests);
        identityStyles = Set.copyOf(identityStyles);
        basePriority = Math.max(0, Math.min(MAX_BASE_PRIORITY, basePriority));
        cooldownDays = Math.max(0, cooldownDays);
        maxMentionsPerWeek = maxMentionsPerWeek <= 0 ? Integer.MAX_VALUE : maxMentionsPerWeek;
    }

    /** True when this scene needs a live episode of a declared kind to be selectable. */
    public boolean needsEpisode() {
        return !episodeKind.isEmpty();
    }

    /** True when this scene opens a resumable thread when it is played. */
    public boolean opensThread() {
        return !threadTemplate.isEmpty();
    }

    /** True when this scene declares a less specific, truthful route to degrade to. */
    public boolean hasFallback() {
        return !fallbackScene.isEmpty();
    }

    /** The editorial bucket this scene belongs to: purpose plus topic. */
    public String indexKey() {
        return purpose.key() + "/" + (topic.isEmpty() ? "*" : topic);
    }

    /**
     * The index leaves this scene is filed under: {@link #indexKey()} plus one profession each.
     *
     * <p>A scene naming professions is filed once per profession, because profession is a hard gate —
     * naming three professions is three separate audiences, not one bucket of three. A scene naming
     * none is filed under {@link SceneCatalog#ANY_PROFESSION}, which every lookup merges in.
     */
    public List<String> indexKeys() {
        String base = indexKey();
        if (professions.isEmpty()) {
            return List.of(base + "#" + SceneCatalog.ANY_PROFESSION);
        }
        return professions.stream().sorted().map(profession -> base + "#" + profession).toList();
    }

    /** True when this scene is one a specific profession owns rather than a shared affordance. */
    public boolean isProfessionSpecific() {
        return !professions.isEmpty();
    }

    public static SceneDefinition fromJson(String id, JsonObject json) {
        String sceneId = normalize(id);
        if (!ID.matcher(sceneId).matches()) {
            throw new IllegalArgumentException("scene '" + id + "' must match " + ID.pattern());
        }
        String purposeKey = require(json, "purpose", sceneId);
        ScenePurpose purpose = ScenePurpose.byKey(purposeKey).orElseThrow(() ->
                new IllegalArgumentException("scene '" + sceneId + "' purpose '" + purposeKey
                        + "' is unknown"));
        String topic = ScenePurpose.topicOf(purposeKey).orElse("");

        SceneShape shape = json.has("shape")
                ? SceneShape.byKey(json.get("shape").getAsString()).orElseThrow(() ->
                        new IllegalArgumentException("scene '" + sceneId + "' shape '"
                                + json.get("shape").getAsString() + "' is unknown"))
                : SceneShape.OBSERVE;

        JsonObject profile = object(json, "profile");
        JsonObject context = object(json, "context");
        JsonObject selection = object(json, "selection");
        JsonObject route = object(json, "route");
        JsonObject episode = object(json, "episode");

        if (route == null || !route.has("question") || !route.has("opening_beat")) {
            throw new IllegalArgumentException("scene '" + sceneId
                    + "' must declare route.question and route.opening_beat: a scene that names no "
                    + "contracted route has nothing to say");
        }

        Set<RelationshipBand> bands = new LinkedHashSet<>();
        for (String key : strings(profile, "relationships")) {
            bands.add(RelationshipBand.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "scene '" + sceneId + "' relationship band '" + key + "' is unknown")));
        }

        Set<EpisodeState> states = new LinkedHashSet<>();
        for (String key : strings(context, "episode_state")) {
            states.add(EpisodeState.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "scene '" + sceneId + "' episode state '" + key + "' is unknown")));
        }

        Map<String, SlotType> slots = new LinkedHashMap<>();
        if (context != null && context.has("required_slots") && context.get("required_slots").isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry
                    : context.getAsJsonObject("required_slots").entrySet()) {
                String slotName = entry.getKey().trim().toLowerCase(Locale.ROOT);
                String typeKey = entry.getValue().getAsString();
                slots.put(slotName, SlotType.byKey(typeKey).orElseThrow(() ->
                        new IllegalArgumentException("scene '" + sceneId + "' slot '" + slotName
                                + "' declares unknown type '" + typeKey + "'")));
            }
        }

        List<ContextQuery> contextConditions = new ArrayList<>();
        for (JsonObject entry : objects(context, "conditions")) {
            ContextQuery query = ContextQuery.fromJson(entry);
            if (!query.isValid()) {
                throw new IllegalArgumentException("scene '" + sceneId
                        + "' has a context condition naming a field no provider declares");
            }
            contextConditions.add(query);
        }

        List<ProfileQuery> profileConditions = new ArrayList<>();
        for (JsonObject entry : objects(context, "identity")) {
            ProfileQuery query = ProfileQuery.fromJson(entry);
            if (!query.isValid()) {
                throw new IllegalArgumentException("scene '" + sceneId
                        + "' has an identity condition that names no family or flag");
            }
            profileConditions.add(query);
        }

        String episodeKind = context != null && context.has("episode_kind")
                ? context.get("episode_kind").getAsString() : "";
        if (!episodeKind.isEmpty() && states.isEmpty()) {
            throw new IllegalArgumentException("scene '" + sceneId + "' binds episode kind '"
                    + episodeKind + "' but declares no episode_state, so it would claim to be true in "
                    + "every state including the terminal ones");
        }

        return new SceneDefinition(sceneId, purpose, topic, shape,
                namespaced(profile, "profession", sceneId), strings(profile, "archetypes"),
                strings(profile, "subjects_any"), strings(profile, "ages"), bands,
                episodeKind, states, slots, contextConditions, profileConditions,
                strings(context, "integrations"),
                selection != null && selection.has("base_priority")
                        ? selection.get("base_priority").getAsInt() : 10,
                strings(selection, "identity_values"), strings(selection, "identity_interests"),
                strings(selection, "identity_styles"),
                selection != null && selection.has("cooldown_days")
                        ? selection.get("cooldown_days").getAsInt() : 1,
                selection != null && selection.has("max_mentions_per_7_days")
                        ? selection.get("max_mentions_per_7_days").getAsInt() : 0,
                route.get("question").getAsString().trim(),
                route.get("opening_beat").getAsString().trim(),
                episode != null && episode.has("thread") ? episode.get("thread").getAsString() : "",
                json.has("fallback") ? json.get("fallback").getAsString() : "");
    }

    private static String require(JsonObject json, String field, String id) {
        if (json == null || !json.has(field) || !json.get(field).isJsonPrimitive()) {
            throw new IllegalArgumentException("scene '" + id + "' requires a \"" + field + "\"");
        }
        return json.get(field).getAsString().trim();
    }

    private static JsonObject object(JsonObject json, String field) {
        return json != null && json.has(field) && json.get(field).isJsonObject()
                ? json.getAsJsonObject(field) : null;
    }

    private static List<JsonObject> objects(JsonObject json, String field) {
        List<JsonObject> out = new ArrayList<>();
        if (json == null || !json.has(field) || !json.get(field).isJsonArray()) {
            return out;
        }
        JsonArray array = json.getAsJsonArray(field);
        for (JsonElement item : array) {
            if (item.isJsonObject()) {
                out.add(item.getAsJsonObject());
            }
        }
        return out;
    }

    private static Set<String> strings(JsonObject json, String field) {
        Set<String> out = new LinkedHashSet<>();
        if (json == null || !json.has(field)) {
            return out;
        }
        JsonElement element = json.get(field);
        if (element.isJsonPrimitive()) {
            out.add(element.getAsString().trim().toLowerCase(Locale.ROOT));
            return out;
        }
        if (element.isJsonArray()) {
            for (JsonElement item : element.getAsJsonArray()) {
                out.add(item.getAsString().trim().toLowerCase(Locale.ROOT));
            }
        }
        return out;
    }

    private static Set<String> namespaced(JsonObject json, String field, String id) {
        Set<String> out = strings(json, field);
        for (String value : out) {
            if (!PROFESSION.matcher(value).matches()) {
                throw new IllegalArgumentException("scene '" + id + "' " + field + " entry '" + value
                        + "' must be a namespaced registry id");
            }
        }
        return out;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
