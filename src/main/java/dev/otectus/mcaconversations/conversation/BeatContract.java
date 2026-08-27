package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * The declared meaning of one villager turn (spec §5.1, §5.2).
 *
 * <p>A <b>beat</b> is a {@code say} pool paired with the {@code next} question it opens. That pairing
 * is the whole idea: the mod's central defect was treating those two fields as independent, so forty
 * different professions' opening lines could all hand control to one page of five fixed replies. Here
 * they are one authored unit, and the reply page is a property of the meaning rather than of the
 * topic name.
 *
 * <p>Every variant under {@link #say} — {@code /1}, {@code /2}, {@code /3} — shares this contract.
 * MCA picks the variant on the client and the server never learns which one a player read, so a claim
 * that only one variant makes is a claim no reply may safely answer (spec §3.4).
 *
 * <p>{@link #outcome} is present when the beat is the villager <em>reacting</em> to a player line
 * rather than opening a subject. That is what lets the routing lint prove a rebuff never opens a warm
 * page.
 *
 * <p>{@link #frame} is the optional v2 half (spec §10.1): the typed predicate, tense, footing, privacy
 * and obligations a dynamic scene needs in order to prove that a reply page answers the exact line
 * above it. Every beat authored before this release has {@link DiscourseSpec#V1_DEFAULT} and behaves
 * identically to before, which is what makes the upgrade non-breaking for existing datapacks.
 */
public record BeatContract(String id,
                           String topic,
                           String say,
                           String responseQuestion,
                           NpcSpeechAct npcAct,
                           String subject,
                           Polarity polarity,
                           Openness openness,
                           Set<SemanticFact> facts,
                           Set<StanceFamily> allowedStances,
                           Set<StanceFamily> forbiddenStances,
                           Optional<OutcomeFamily> outcome,
                           BeatContext context,
                           Optional<Callback> callback,
                           DiscourseSpec frame) {

    /** Beat and subject ids: dotted lowercase, same shape the catalog already uses for everything else. */
    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");

    /** MCA lang keys, which may carry a namespace-ish prefix but never a variant suffix. */
    public static final Pattern SAY_KEY = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)+");

    /**
     * A durable fact this beat writes, so a later conversation can refer to it accurately.
     *
     * @param fact       the semantic fact recorded against this villager/player pair
     * @param expiresAfter game ticks after which the fact stops being callable back, or empty for no expiry
     * @param resumes    beat ids that are allowed to consume this fact; empty means any beat may
     */
    public record Callback(SemanticFact fact, Optional<Long> expiresAfter, Set<String> resumes) {
    }

    /**
     * The world this beat is claiming to describe: who may say it, and when (spec §5.2).
     *
     * <p>Declared rather than inferred, because the conditions in MCA JSON are a scoring system, not a
     * gate. Two results can both score positively and MCA picks by weight, so "this line is only for
     * farmers" has to be something an author states and lint checks, not something a {@code chance}
     * number implies (spec §6.5).
     */
    public record BeatContext(Optional<String> profession,
                              Set<String> ages,
                              Set<RelationshipBand> relationships,
                              Set<String> states,
                              Set<String> integrations) {

        public static final BeatContext ANY =
                new BeatContext(Optional.empty(), Set.of(), Set.of(), Set.of(), Set.of());

        /** Exact registry ids, so an absent mod's profession simply never matches. */
        public static final Pattern PROFESSION = Pattern.compile("[a-z0-9_.-]+:[a-z0-9_./-]+");

        /** True when this beat is only reachable for one exact profession. */
        public boolean isProfessionSpecific() {
            return profession.isPresent();
        }

        /** True when the beat needs an optional mod present to be selectable at all. */
        public boolean needsIntegration() {
            return !integrations.isEmpty();
        }

        static BeatContext fromJson(JsonObject json, String beatId) {
            if (json == null) {
                return ANY;
            }
            Optional<String> profession = Optional.empty();
            if (json.has("profession")) {
                String value = json.get("profession").getAsString().trim().toLowerCase(Locale.ROOT);
                if (!PROFESSION.matcher(value).matches()) {
                    throw new IllegalArgumentException(
                            "beat '" + beatId + "' profession '" + value + "' must be a namespaced registry id");
                }
                profession = Optional.of(value);
            }

            Set<String> ages = new LinkedHashSet<>();
            for (String age : strings(json, "ages")) {
                if (!TopicEntry.AGE_GROUPS.contains(age)) {
                    throw new IllegalArgumentException(
                            "beat '" + beatId + "' age '" + age + "' is not one of " + TopicEntry.AGE_GROUPS);
                }
                ages.add(age);
            }

            Set<RelationshipBand> relationships = new LinkedHashSet<>();
            for (String band : strings(json, "relationships")) {
                relationships.add(RelationshipBand.byKey(band).orElseThrow(() -> new IllegalArgumentException(
                        "beat '" + beatId + "' relationship band '" + band + "' is unknown")));
            }

            Set<String> states = new LinkedHashSet<>();
            for (String state : strings(json, "states")) {
                if (!ID.matcher(state).matches()) {
                    throw new IllegalArgumentException(
                            "beat '" + beatId + "' state '" + state + "' must match " + ID.pattern());
                }
                states.add(state);
            }

            Set<String> integrations = new LinkedHashSet<>(strings(json, "integrations"));
            return new BeatContext(profession, Set.copyOf(ages), Set.copyOf(relationships),
                    Set.copyOf(states), Set.copyOf(integrations));
        }
    }

    public BeatContract {
        frame = frame == null ? DiscourseSpec.V1_DEFAULT : frame;
        facts = Set.copyOf(new TreeSet<>(facts));
        allowedStances = Set.copyOf(allowedStances);
        forbiddenStances = Set.copyOf(forbiddenStances);
    }

    /** True when this beat is the villager reacting to something the player chose. */
    public boolean isReaction() {
        return outcome.isPresent();
    }

    /** True when nothing more may be asked about this subject without repairing something first. */
    public boolean isClosed() {
        return openness.isClosed() || npcAct.isRupture() || outcome.map(OutcomeFamily::isRupture).orElse(false);
    }

    /** True when this beat carries a declared v2 discourse frame rather than the v1 default. */
    public boolean hasFrame() {
        return frame.isDeclared();
    }

    /** The rhetorical shape used by repetition suppression, when the beat declares one. */
    public Optional<SceneShape> shape() {
        return frame.shape();
    }

    /** True when {@code stance} is a sensible thing for the player to say straight after this line. */
    public boolean permits(StanceFamily stance) {
        return !forbiddenStances.contains(stance) && allowedStances.contains(stance);
    }

    /** The subject's leading segment — {@code work} for {@code work.farmer.crop_health}. */
    public String subjectDomain() {
        int dot = subject.indexOf('.');
        return dot < 0 ? subject : subject.substring(0, dot);
    }

    public static BeatContract fromJson(String id, JsonObject json) {
        if (!ID.matcher(id).matches()) {
            throw new IllegalArgumentException("beat id '" + id + "' must match " + ID.pattern());
        }
        String topic = requireString(json, "topic", id);
        if (!ID.matcher(topic).matches()) {
            throw new IllegalArgumentException("beat '" + id + "' topic '" + topic + "' must match " + ID.pattern());
        }

        String say = requireString(json, "say", id);
        if (say.contains("/")) {
            throw new IllegalArgumentException(
                    "beat '" + id + "' say '" + say + "' must be the base key; variants share one contract");
        }
        if (!SAY_KEY.matcher(say).matches()) {
            throw new IllegalArgumentException("beat '" + id + "' say '" + say + "' must match " + SAY_KEY.pattern());
        }

        String responseQuestion = requireString(json, "response_question", id);

        NpcSpeechAct act = NpcSpeechAct.byKey(requireString(json, "npc_act", id))
                .orElseThrow(() -> new IllegalArgumentException(
                        "beat '" + id + "' npc_act '" + json.get("npc_act").getAsString() + "' is unknown"));

        String subject = requireString(json, "subject", id);
        if (!ID.matcher(subject).matches()) {
            throw new IllegalArgumentException("beat '" + id + "' subject '" + subject + "' must match " + ID.pattern());
        }

        Polarity polarity = Polarity.byKey(requireString(json, "polarity", id))
                .orElseThrow(() -> new IllegalArgumentException(
                        "beat '" + id + "' polarity '" + json.get("polarity").getAsString() + "' is unknown"));

        Openness openness = Openness.byKey(requireString(json, "openness", id))
                .orElseThrow(() -> new IllegalArgumentException(
                        "beat '" + id + "' openness '" + json.get("openness").getAsString() + "' is unknown"));

        Set<SemanticFact> facts = new TreeSet<>();
        for (String fact : strings(json, "facts")) {
            facts.add(SemanticFact.parse(fact));
        }

        Set<StanceFamily> allowed = stances(json, "allowed_stances", id);
        Set<StanceFamily> forbidden = stances(json, "forbidden_stances", id);
        if (allowed.isEmpty()) {
            throw new IllegalArgumentException("beat '" + id + "' must allow at least one stance");
        }
        for (StanceFamily stance : forbidden) {
            if (allowed.contains(stance)) {
                throw new IllegalArgumentException(
                        "beat '" + id + "' both allows and forbids stance '" + stance.key() + "'");
            }
        }
        if (!allowed.contains(StanceFamily.EXIT)) {
            throw new IllegalArgumentException("beat '" + id + "' must allow the exit stance — every page needs a door");
        }

        Optional<OutcomeFamily> outcome = Optional.empty();
        if (json.has("outcome")) {
            String key = json.get("outcome").getAsString();
            outcome = Optional.of(OutcomeFamily.byKey(key).orElseThrow(() ->
                    new IllegalArgumentException("beat '" + id + "' outcome '" + key + "' is unknown")));
        }

        BeatContext context = BeatContext.fromJson(
                json.has("context") && json.get("context").isJsonObject() ? json.getAsJsonObject("context") : null, id);

        Optional<Callback> callback = Optional.empty();
        if (json.has("callback") && json.get("callback").isJsonObject()) {
            JsonObject cb = json.getAsJsonObject("callback");
            SemanticFact fact = SemanticFact.parse(
                    requireString(cb, "fact", id) + ":" + requireString(cb, "value", id));
            Optional<Long> expiry = cb.has("expires_after")
                    ? Optional.of(cb.get("expires_after").getAsLong())
                    : Optional.empty();
            expiry.ifPresent(ticks -> {
                if (ticks <= 0) {
                    throw new IllegalArgumentException("beat '" + id + "' callback expires_after must be positive");
                }
            });
            Set<String> resumes = new LinkedHashSet<>(strings(cb, "resumes"));
            callback = Optional.of(new Callback(fact, expiry, Set.copyOf(resumes)));
        }

        DiscourseSpec frame = json.has("frame") && json.get("frame").isJsonObject()
                ? DiscourseSpec.fromJson(json.getAsJsonObject("frame"), id)
                : DiscourseSpec.V1_DEFAULT;

        return new BeatContract(id, topic, say, responseQuestion, act, subject, polarity, openness,
                facts, allowed, forbidden, outcome, context, callback, frame);
    }

    // --- Parsing helpers ---------------------------------------------------------

    private static String requireString(JsonObject json, String field, String id) {
        if (!json.has(field) || !json.get(field).isJsonPrimitive()) {
            throw new IllegalArgumentException("beat '" + id + "' requires a \"" + field + "\"");
        }
        String value = json.get(field).getAsString().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("beat '" + id + "' has an empty \"" + field + "\"");
        }
        return value;
    }

    private static Set<StanceFamily> stances(JsonObject json, String field, String id) {
        Set<StanceFamily> out = new LinkedHashSet<>();
        for (String key : strings(json, field)) {
            out.add(StanceFamily.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "beat '" + id + "' " + field + " contains unknown stance '" + key + "'")));
        }
        return out;
    }

    static Set<String> strings(JsonObject json, String field) {
        Set<String> out = new LinkedHashSet<>();
        if (json == null || !json.has(field)) {
            return out;
        }
        JsonElement element = json.get(field);
        if (element.isJsonPrimitive()) {
            out.add(element.getAsString().trim().toLowerCase(Locale.ROOT));
            return out;
        }
        if (!element.isJsonArray()) {
            throw new IllegalArgumentException("\"" + field + "\" must be a string or an array of strings");
        }
        JsonArray array = element.getAsJsonArray();
        for (JsonElement item : array) {
            out.add(item.getAsString().trim().toLowerCase(Locale.ROOT));
        }
        return out;
    }
}
