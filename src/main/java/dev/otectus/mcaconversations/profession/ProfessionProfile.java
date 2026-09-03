package dev.otectus.mcaconversations.profession;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * What one profession actually does all day (spec §7.2).
 *
 * <p>A profile is not dialogue. It is the declared shape of a working life — the subjects a villager
 * of this trade can be got talking about, the materials and risks those subjects involve, who in the
 * village depends on them, and which facts about their work are worth remembering between
 * conversations. Dialogue is authored against it; lint checks the dialogue actually covers it.
 *
 * <p>The field that carries the most weight is {@link #subjects}. "Work" was one question for
 * thirty-seven trades because nothing anywhere said that a farmer's work has a crop, a soil, a
 * season and a harvest in it while a librarian's has an acquisition, a catalogue, a damaged volume
 * and a reader. Once that is written down, "does this profession have six real things to talk
 * about?" becomes a question the build can answer.
 *
 * <p>{@link #owner} records which mod supplies the profession, so an optional pack can be shipped as
 * data and simply never match when its mod is absent.
 */
public record ProfessionProfile(String id,
                                WorkArchetype archetype,
                                String owner,
                                String displayFallback,
                                Set<String> subjects,
                                Set<String> materials,
                                Set<String> risks,
                                Set<String> beneficiaries,
                                Set<String> callbackTypes,
                                boolean seasonAffinity,
                                boolean weatherAffinity) {

    /** The mod-independent professions MCA itself always has. */
    public static final String OWNER_BASE = "base";

    /** Registry ids, exactly as MCA reports them. */
    public static final Pattern ID = Pattern.compile("[a-z0-9_.-]+:[a-z0-9_./-]+");

    /** Dotted lowercase ids for subjects, materials, risks and callbacks. */
    public static final Pattern TOKEN = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");

    /** Fewest distinct work subjects a shipped profession must be able to discuss (spec §7.3). */
    public static final int MIN_SUBJECTS = 6;

    /** Fewest durable fact types a profession must be able to call back to (spec §7.2). */
    public static final int MIN_CALLBACK_TYPES = 2;

    public ProfessionProfile {
        subjects = Set.copyOf(subjects);
        materials = Set.copyOf(materials);
        risks = Set.copyOf(risks);
        beneficiaries = Set.copyOf(beneficiaries);
        callbackTypes = Set.copyOf(callbackTypes);
    }

    /** True when this profession exists without any optional mod installed. */
    public boolean isBase() {
        return OWNER_BASE.equals(owner);
    }

    /** The namespace of the registry id — {@code minecraft}, {@code mca}, {@code morevillagers}… */
    public String namespace() {
        return id.substring(0, id.indexOf(':'));
    }

    /** The bare id, used to build lang keys: {@code minecraft:farmer} → {@code farmer}. */
    public String path() {
        return id.substring(id.indexOf(':') + 1);
    }

    /** True when weather or season should be allowed to choose this profession's subject. */
    public boolean caresAboutTheSky() {
        return seasonAffinity || weatherAffinity;
    }

    public static ProfessionProfile fromJson(String id, JsonObject json) {
        if (!ID.matcher(id).matches()) {
            throw new IllegalArgumentException("profession id '" + id + "' must be a namespaced registry id");
        }
        WorkArchetype archetype = WorkArchetype.byKey(require(json, "archetype", id))
                .orElseThrow(() -> new IllegalArgumentException(
                        "profession '" + id + "' archetype '" + json.get("archetype").getAsString() + "' is unknown"));

        String owner = json.has("owner") ? json.get("owner").getAsString().trim() : OWNER_BASE;
        String displayFallback = json.has("display_fallback")
                ? json.get("display_fallback").getAsString().trim()
                : id.substring(id.indexOf(':') + 1).replace('_', ' ');

        Set<String> subjects = tokens(json, "subjects", id);
        if (subjects.isEmpty()) {
            throw new IllegalArgumentException("profession '" + id + "' must declare at least one subject");
        }
        Set<String> callbacks = tokens(json, "callback_types", id);

        return new ProfessionProfile(id, archetype, owner, displayFallback, subjects,
                tokens(json, "materials", id), tokens(json, "risks", id),
                tokens(json, "beneficiaries", id), callbacks,
                json.has("season_affinity") && json.get("season_affinity").getAsBoolean(),
                json.has("weather_affinity") && json.get("weather_affinity").getAsBoolean());
    }

    /** The profile an unknown third-party profession is answered with (spec §14.2). */
    public static ProfessionProfile generic(String id, String displayFallback) {
        return new ProfessionProfile(id, WorkArchetype.fallback(), "unknown", displayFallback,
                Set.of("identity", "day", "value"), Set.of(), Set.of(), Set.of("village"),
                Set.of(), false, false);
    }

    private static String require(JsonObject json, String field, String id) {
        if (!json.has(field) || !json.get(field).isJsonPrimitive()) {
            throw new IllegalArgumentException("profession '" + id + "' requires a \"" + field + "\"");
        }
        return json.get(field).getAsString().trim();
    }

    private static Set<String> tokens(JsonObject json, String field, String id) {
        Set<String> out = new LinkedHashSet<>();
        if (!json.has(field)) {
            return out;
        }
        JsonElement element = json.get(field);
        if (!element.isJsonArray()) {
            throw new IllegalArgumentException("profession '" + id + "' field \"" + field + "\" must be an array");
        }
        JsonArray array = element.getAsJsonArray();
        for (JsonElement item : array) {
            String token = item.getAsString().trim().toLowerCase(Locale.ROOT);
            if (!TOKEN.matcher(token).matches()) {
                throw new IllegalArgumentException("profession '" + id + "' " + field + " entry '" + token
                        + "' must match " + TOKEN.pattern());
            }
            out.add(token);
        }
        return out;
    }

    /** Convenience for callers that only have a display name. */
    public static Optional<String> displayOf(ProfessionProfile profile) {
        return profile == null ? Optional.empty() : Optional.of(profile.displayFallback());
    }
}
