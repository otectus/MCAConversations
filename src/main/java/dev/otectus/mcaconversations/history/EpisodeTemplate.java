package dev.otectus.mcaconversations.history;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The authored shape of one kind of situation (spec §8.3, §10.4).
 *
 * <p>An episode <em>record</em> is a thing that is happening; a template is the rule for what may
 * happen to it. The template declares which states this kind of situation can be in, which
 * transitions are legal, what slots a scene will need bound, how private it is, and when it stops
 * mattering — so a datapack can add a whole new kind of working life without a line of Java, and so
 * the build can prove every declared transition is reachable.
 *
 * <p>{@link #allowedTransitions} narrows {@link EpisodeState}'s own graph; it can never widen it. A
 * template that tries to declare {@code succeeded -> active} is rejected at parse, because a legal
 * transition table that contradicts the state machine would let state drift back in through data.
 *
 * @param kind              the template id, e.g. {@code work.damaged_volume}
 * @param subject           the conversational subject episodes of this kind belong to
 * @param initialState      the state a new episode of this kind opens in
 * @param states            every state this kind may occupy
 * @param allowedTransitions from-state to legal to-states, a subset of the base state machine
 * @param requiredSlots     slot names a scene binding this episode must find in the payload
 * @param slotOptions       per-slot pools a payload is filled from when the caller supplies none
 * @param provenance        how a villager comes to know episodes of this kind, how firmly, how
 *                          private they are, and what may be repeated of them (spec §16.3)
 * @param baseSalience      how much a fresh episode of this kind matters
 * @param dueAfterDays      days until it becomes overdue, when this kind has a deadline
 * @param expiresAfterDays  days until it stops being live regardless of state
 * @param professions       professions this kind belongs to; empty means any
 * @param integrations      optional mods required for this kind to be created at all
 */
public record EpisodeTemplate(String kind,
                              String subject,
                              EpisodeState initialState,
                              Set<EpisodeState> states,
                              Map<EpisodeState, Set<EpisodeState>> allowedTransitions,
                              Set<String> requiredSlots,
                              Map<String, java.util.List<String>> slotOptions,
                              Provenance provenance,
                              int baseSalience,
                              OptionalLong dueAfterDays,
                              OptionalLong expiresAfterDays,
                              Set<String> professions,
                              Set<String> integrations) {

    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");
    public static final Pattern PROFESSION = Pattern.compile("[a-z0-9_.-]+:[a-z0-9_./-]+");

    public EpisodeTemplate {
        kind = normalize(kind);
        subject = normalize(subject);
        initialState = initialState == null ? EpisodeState.ACTIVE : initialState;
        states = Set.copyOf(states);
        requiredSlots = Set.copyOf(requiredSlots);
        Map<String, java.util.List<String>> frozenOptions = new LinkedHashMap<>();
        if (slotOptions != null) {
            slotOptions.forEach((slot, options) ->
                    frozenOptions.put(slot, java.util.List.copyOf(options)));
        }
        slotOptions = Map.copyOf(frozenOptions);
        professions = Set.copyOf(professions);
        integrations = Set.copyOf(integrations);
        provenance = provenance == null
                ? Provenance.participant(PrivacyLevel.defaultLevel()) : provenance;
        baseSalience = Math.max(EpisodeRecord.MIN_SALIENCE,
                Math.min(EpisodeRecord.MAX_SALIENCE, baseSalience));
        Map<EpisodeState, Set<EpisodeState>> frozen = new LinkedHashMap<>();
        allowedTransitions.forEach((from, to) -> frozen.put(from, Set.copyOf(to)));
        allowedTransitions = Map.copyOf(frozen);
        dueAfterDays = dueAfterDays == null ? OptionalLong.empty() : dueAfterDays;
        expiresAfterDays = expiresAfterDays == null ? OptionalLong.empty() : expiresAfterDays;
    }

    /**
     * How freely episodes of this kind may be repeated.
     *
     * <p>Privacy was a component of this template before provenance bundled it with the rest of what
     * §16.3 asks for. Callers keep asking the template, so the bundle's shape stays an implementation
     * detail of the history package.
     */
    public PrivacyLevel privacy() {
        return provenance.privacy();
    }

    /** True when this template permits {@code from -> to}. Same-state is always a legal no-op. */
    public boolean permits(EpisodeState from, EpisodeState to) {
        if (from == null || to == null) {
            return false;
        }
        if (from == to) {
            return true;
        }
        if (!from.allows(to) || !states.contains(to)) {
            return false;
        }
        Set<EpisodeState> declared = allowedTransitions.get(from);
        // An undeclared source state falls back to the base machine, so a template only has to write
        // out the transitions it actually wants to restrict.
        return declared == null || declared.contains(to);
    }

    /** True when this kind may be created on the running install. */
    public boolean isAvailable(String professionId, java.util.function.Predicate<String> modPresent) {
        if (!professions.isEmpty() && (professionId == null || !professions.contains(professionId))) {
            return false;
        }
        for (String integration : integrations) {
            if (modPresent == null || !modPresent.test(integration)) {
                return false;
            }
        }
        return true;
    }

    /** The day an episode opened today would be due, if this kind has a deadline. */
    public OptionalLong dueDayFrom(long today) {
        return dueAfterDays.isPresent() ? OptionalLong.of(today + dueAfterDays.getAsLong())
                : OptionalLong.empty();
    }

    public OptionalLong expiryDayFrom(long today) {
        return expiresAfterDays.isPresent() ? OptionalLong.of(today + expiresAfterDays.getAsLong())
                : OptionalLong.empty();
    }

    /** True when {@code payload} carries everything a scene bound to this kind will need. */
    public boolean satisfiedBy(Map<String, NarrativeValue> payload) {
        if (requiredSlots.isEmpty()) {
            return true;
        }
        if (payload == null) {
            return false;
        }
        for (String slot : requiredSlots) {
            NarrativeValue value = payload.get(slot);
            if (value == null || value.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static EpisodeTemplate fromJson(String kind, JsonObject json) {
        String id = normalize(kind);
        if (!ID.matcher(id).matches()) {
            throw new IllegalArgumentException("episode template '" + kind + "' must match " + ID.pattern());
        }
        String subject = require(json, "subject", id);
        if (!ID.matcher(subject).matches()) {
            throw new IllegalArgumentException("episode template '" + id + "' subject '" + subject
                    + "' must match " + ID.pattern());
        }

        Set<EpisodeState> states = new LinkedHashSet<>();
        for (String key : strings(json, "states")) {
            states.add(EpisodeState.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "episode template '" + id + "' state '" + key + "' is unknown")));
        }
        if (states.isEmpty()) {
            states = EnumSet.allOf(EpisodeState.class);
        }

        EpisodeState initial = json.has("initial_state")
                ? EpisodeState.byKey(json.get("initial_state").getAsString())
                        .orElseThrow(() -> new IllegalArgumentException("episode template '" + id
                                + "' initial_state is unknown"))
                : EpisodeState.ACTIVE;
        if (!states.contains(initial)) {
            throw new IllegalArgumentException("episode template '" + id + "' initial_state '"
                    + initial.key() + "' is not in its declared states");
        }

        Map<EpisodeState, Set<EpisodeState>> transitions = new LinkedHashMap<>();
        for (String declared : strings(json, "transitions")) {
            int arrow = declared.indexOf("->");
            if (arrow <= 0) {
                throw new IllegalArgumentException("episode template '" + id + "' transition '"
                        + declared + "' must be written from->to");
            }
            EpisodeState from = state(id, declared.substring(0, arrow).trim());
            EpisodeState to = state(id, declared.substring(arrow + 2).trim());
            if (!from.allows(to)) {
                throw new IllegalArgumentException("episode template '" + id + "' declares '" + declared
                        + "', which the episode state machine does not permit");
            }
            transitions.computeIfAbsent(from, key -> new LinkedHashSet<>()).add(to);
        }

        Map<String, java.util.List<String>> slotOptions = new LinkedHashMap<>();
        if (json.has("slot_options") && json.get("slot_options").isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject("slot_options").entrySet()) {
                java.util.List<String> options = new java.util.ArrayList<>();
                if (entry.getValue().isJsonArray()) {
                    for (JsonElement item : entry.getValue().getAsJsonArray()) {
                        options.add(item.getAsString().trim().toLowerCase(Locale.ROOT));
                    }
                }
                if (options.isEmpty()) {
                    throw new IllegalArgumentException("episode template '" + id + "' slot_options '"
                            + entry.getKey() + "' is empty, so nothing could ever fill that slot");
                }
                slotOptions.put(entry.getKey().trim().toLowerCase(Locale.ROOT),
                        java.util.List.copyOf(options));
            }
        }

        return new EpisodeTemplate(id, subject, initial, states, transitions,
                strings(json, "required_slots"), slotOptions,
                provenance(json, id),
                json.has("salience") ? json.get("salience").getAsInt() : 30,
                json.has("due_after_days") ? OptionalLong.of(json.get("due_after_days").getAsLong())
                        : OptionalLong.empty(),
                json.has("expires_after_days")
                        ? OptionalLong.of(json.get("expires_after_days").getAsLong())
                        : OptionalLong.empty(),
                namespaced(json, "professions", id), strings(json, "integrations"));
    }

    /**
     * The provenance an episode of this kind opens with.
     *
     * <p>Defaults are the villager's own life: they are a participant in it, they are certain of it,
     * and how freely they will repeat it follows from the privacy level. A pack may name a different
     * {@code knowledge} source — a thing the whole village was told, a thing a coworker mentioned —
     * and may narrow {@code share} below what the privacy level implies. It may also declare a
     * {@code distortion}, which is the only place in the mod one can come from: nothing in the
     * runtime marks an account mistaken on its own (spec §16.3).
     */
    private static Provenance provenance(JsonObject json, String id) {
        PrivacyLevel privacy = json.has("privacy")
                ? PrivacyLevel.byKey(json.get("privacy").getAsString())
                        .orElseThrow(() -> new IllegalArgumentException("episode template '" + id
                                + "' names unknown privacy '" + json.get("privacy").getAsString() + "'"))
                : PrivacyLevel.defaultLevel();
        KnowledgeSource knowledge = json.has("knowledge")
                ? KnowledgeSource.byKey(json.get("knowledge").getAsString())
                        .orElseThrow(() -> new IllegalArgumentException("episode template '" + id
                                + "' names unknown knowledge source '"
                                + json.get("knowledge").getAsString() + "'"))
                : KnowledgeSource.PARTICIPANT;
        SharePermission share = json.has("share")
                ? SharePermission.byKey(json.get("share").getAsString())
                        .orElseThrow(() -> new IllegalArgumentException("episode template '" + id
                                + "' names unknown share permission '"
                                + json.get("share").getAsString() + "'"))
                : null;
        Distortion distortion = json.has("distortion")
                ? Distortion.byKey(json.get("distortion").getAsString())
                        .orElseThrow(() -> new IllegalArgumentException("episode template '" + id
                                + "' names unknown distortion '"
                                + json.get("distortion").getAsString() + "'"))
                : Distortion.NONE;
        // Hop zero: a template describes how a villager first comes to know a thing, never how far
        // it has since travelled.
        return new Provenance(knowledge, java.util.Optional.empty(), null, privacy, share,
                distortion, 0);
    }

    private static EpisodeState state(String id, String key) {
        return EpisodeState.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                "episode template '" + id + "' names unknown state '" + key + "'"));
    }

    private static String require(JsonObject json, String field, String id) {
        if (json == null || !json.has(field) || !json.get(field).isJsonPrimitive()) {
            throw new IllegalArgumentException("episode template '" + id + "' requires a \"" + field + "\"");
        }
        return json.get(field).getAsString().trim().toLowerCase(Locale.ROOT);
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

    static Set<String> namespaced(JsonObject json, String field, String id) {
        Set<String> out = strings(json, field);
        for (String value : out) {
            if (!PROFESSION.matcher(value).matches()) {
                throw new IllegalArgumentException("'" + id + "' " + field + " entry '" + value
                        + "' must be a namespaced registry id");
            }
        }
        return out;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Fills the slots this template declares options for, from a stable per-villager seed
     * (spec §12.2, §12.3).
     *
     * <p>This is what makes two librarians different people rather than two copies of one. The seed is
     * the villager and the episode kind — <b>not</b> the day — so the damaged volume a librarian is
     * worrying about is hers, and stays hers until it is resolved. A daily seed would give her a
     * different book every morning, which is combinatorial variety pretending to be a life.
     *
     * <p>Values the caller already supplied win. An authored scene that knows exactly which object it
     * means is always more specific than a generated one.
     */
    public Map<String, NarrativeValue> fillSlots(Map<String, NarrativeValue> supplied, long seed) {
        if (slotOptions.isEmpty()) {
            return supplied == null ? Map.of() : Map.copyOf(supplied);
        }
        Map<String, NarrativeValue> out = new LinkedHashMap<>();
        if (supplied != null) {
            supplied.forEach((slot, value) -> {
                if (value != null && !value.isEmpty()) {
                    out.put(slot, value);
                }
            });
        }
        // Sorted, so adding a slot to the template later cannot change which option an existing slot
        // resolved to for an already-generated episode.
        for (String slot : new java.util.TreeSet<>(slotOptions.keySet())) {
            if (out.containsKey(slot)) {
                continue;
            }
            java.util.List<String> options = slotOptions.get(slot);
            long roll = splitMix(seed ^ fnv(slot));
            NarrativeValue value = NarrativeValue.token(options.get((int) (roll % options.size())));
            if (!value.isEmpty()) {
                out.put(slot, value);
            }
        }
        return Map.copyOf(out);
    }

    /** The stable seed for one villager's episode of this kind. */
    public static long seedFor(long worldSeed, java.util.UUID villager, String kind) {
        long hash = 0xcbf29ce484222325L ^ worldSeed;
        hash ^= fnv(villager == null ? "" : villager.toString());
        hash ^= fnv(kind == null ? "" : kind);
        return splitMix(hash);
    }

    private static long fnv(String text) {
        long hash = 0xcbf29ce484222325L;
        for (byte b : text.getBytes(java.nio.charset.StandardCharsets.UTF_8)) {
            hash ^= (b & 0xffL);
            hash *= 0x100000001b3L;
        }
        return hash;
    }

    private static long splitMix(long seed) {
        long z = seed + 0x9e3779b97f4a7c15L;
        z = (z ^ (z >>> 30)) * 0xbf58476d1ce4e5b9L;
        z = (z ^ (z >>> 27)) * 0x94d049bb133111ebL;
        return (z ^ (z >>> 31)) & Long.MAX_VALUE;
    }

    /** Convenience for callers that only have a kind string. */
    public static Optional<EpisodeTemplate> none() {
        return Optional.empty();
    }
}
