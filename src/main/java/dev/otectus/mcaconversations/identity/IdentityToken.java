package dev.otectus.mcaconversations.identity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * One stable anchor a villager may be given, and the rules about who may be given it (spec §6.3).
 *
 * <h2>Gates, favours and bans are three different things</h2>
 *
 * <ul>
 *   <li>{@link #ages}, {@link #professions}, {@link #archetypes} are <b>gates</b>: non-empty means
 *       "only these". A child does not get {@code inherited_land}; a token about the sea is not handed
 *       to a villager with no water anywhere.</li>
 *   <li>{@link #favourArchetypes} and {@link #favourPersonalities} are <b>weights</b>. A scholar is
 *       more likely to be interested in local history — and may still love animals instead. This is
 *       the "thumb on the scale, never a rail" rule the mod already applies to personality
 *       (spec §9.2).</li>
 *   <li>{@link #neverWithProfessions} and {@link #neverWithPersonalities} are <b>bans</b>, and they
 *       exist for one reason: a profile must not infer a sensitive identity from a job or a mood. A
 *       cleric is not automatically devout in a particular way, an outlaw is not automatically cruel,
 *       a "sensitive" villager is not fragile, and a nitwit is not incompetent (spec §6.2). A ban is
 *       checked in generation <em>and</em> asserted by {@code IdentityConstraintTest}, so the rule
 *       cannot rot into a comment.</li>
 * </ul>
 *
 * <p>{@link #conflicts} keeps one villager from holding two anchors that contradict or merely
 * duplicate each other — {@code crowds} against {@code hospitality}, {@code solitary} against
 * {@code collaborative}. Conflicts are symmetric: declaring one direction is enough.
 *
 * @param id                  dotted lowercase token id, unique within its family
 * @param family              which anchor this is
 * @param weight              base selection weight; higher is commoner
 * @param ages                MCA age groups this token is eligible for; empty means all
 * @param professions         exact profession registry ids; empty means all
 * @param archetypes          work archetype keys; empty means all
 * @param favourArchetypes    archetypes that add {@link #FAVOUR_BONUS} to the weight
 * @param favourPersonalities personalities that add {@link #FAVOUR_BONUS} to the weight
 * @param neverWithProfessions professions this token may never be generated for
 * @param neverWithPersonalities personalities this token may never be generated for
 * @param conflicts           tokens that may not co-occur with this one
 * @param integrations        optional-mod ids required for this token to be eligible
 */
public record IdentityToken(String id,
                            IdentityFamily family,
                            int weight,
                            Set<String> ages,
                            Set<String> professions,
                            Set<String> archetypes,
                            Set<String> favourArchetypes,
                            Set<String> favourPersonalities,
                            Set<String> neverWithProfessions,
                            Set<String> neverWithPersonalities,
                            Set<String> conflicts,
                            Set<String> integrations) {

    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");
    public static final Pattern PROFESSION = Pattern.compile("[a-z0-9_.-]+:[a-z0-9_./-]+");

    /** Weight added once for a favoured archetype and once for a favoured personality. */
    public static final int FAVOUR_BONUS = 8;

    public static final int MIN_WEIGHT = 1;
    public static final int MAX_WEIGHT = 100;

    public IdentityToken {
        ages = Set.copyOf(ages);
        professions = Set.copyOf(professions);
        archetypes = Set.copyOf(archetypes);
        favourArchetypes = Set.copyOf(favourArchetypes);
        favourPersonalities = Set.copyOf(favourPersonalities);
        neverWithProfessions = Set.copyOf(neverWithProfessions);
        neverWithPersonalities = Set.copyOf(neverWithPersonalities);
        conflicts = Set.copyOf(conflicts);
        integrations = Set.copyOf(integrations);
    }

    /** The id a record and a trace store: {@code interest:animals}. */
    public String qualifiedId() {
        return family.key() + ":" + id;
    }

    /**
     * True when this token may be generated for a villager with this age, profession, archetype and
     * personality — gates and bans only. Favours never make a token eligible or ineligible.
     */
    public boolean isEligible(String age, String professionId, String archetype, String personality) {
        if (!ages.isEmpty() && (age == null || !ages.contains(age))) {
            return false;
        }
        if (!professions.isEmpty() && (professionId == null || !professions.contains(professionId))) {
            return false;
        }
        if (!archetypes.isEmpty() && (archetype == null || !archetypes.contains(archetype))) {
            return false;
        }
        if (professionId != null && neverWithProfessions.contains(professionId)) {
            return false;
        }
        return personality == null || !neverWithPersonalities.contains(personality);
    }

    /** Base weight plus favour bonuses. Never below {@link #MIN_WEIGHT}. */
    public int weightFor(String archetype, String personality) {
        int total = weight;
        if (archetype != null && favourArchetypes.contains(archetype)) {
            total += FAVOUR_BONUS;
        }
        if (personality != null && favourPersonalities.contains(personality)) {
            total += FAVOUR_BONUS;
        }
        return Math.max(MIN_WEIGHT, total);
    }

    public static IdentityToken fromJson(String id, JsonObject json) {
        String normalized = id == null ? "" : id.trim().toLowerCase(Locale.ROOT);
        if (!ID.matcher(normalized).matches()) {
            throw new IllegalArgumentException("identity token '" + id + "' must match " + ID.pattern());
        }
        IdentityFamily family = IdentityFamily.byKey(require(json, "family", normalized))
                .orElseThrow(() -> new IllegalArgumentException(
                        "identity token '" + normalized + "' family '"
                                + json.get("family").getAsString() + "' is unknown"));

        int weight = json.has("weight") ? json.get("weight").getAsInt() : 10;
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            throw new IllegalArgumentException("identity token '" + normalized + "' weight " + weight
                    + " must be between " + MIN_WEIGHT + " and " + MAX_WEIGHT);
        }

        Set<String> professions = namespacedIds(json, "professions", normalized);
        Set<String> neverWithProfessions = namespacedIds(json, "never_with_professions", normalized);
        for (String profession : neverWithProfessions) {
            if (professions.contains(profession)) {
                throw new IllegalArgumentException("identity token '" + normalized + "' both requires and "
                        + "bans profession '" + profession + "'");
            }
        }

        Set<String> conflicts = tokens(json, "conflicts");
        if (conflicts.contains(normalized)) {
            throw new IllegalArgumentException("identity token '" + normalized + "' conflicts with itself");
        }

        return new IdentityToken(normalized, family, weight,
                tokens(json, "ages"), professions, tokens(json, "archetypes"),
                tokens(json, "favour_archetypes"), tokens(json, "favour_personalities"),
                neverWithProfessions, tokens(json, "never_with_personalities"),
                conflicts, tokens(json, "integrations"));
    }

    private static String require(JsonObject json, String field, String id) {
        if (json == null || !json.has(field) || !json.get(field).isJsonPrimitive()) {
            throw new IllegalArgumentException("identity token '" + id + "' requires a \"" + field + "\"");
        }
        return json.get(field).getAsString().trim();
    }

    private static Set<String> tokens(JsonObject json, String field) {
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

    private static Set<String> namespacedIds(JsonObject json, String field, String id) {
        Set<String> out = tokens(json, field);
        for (String value : out) {
            if (!PROFESSION.matcher(value).matches()) {
                throw new IllegalArgumentException("identity token '" + id + "' " + field + " entry '"
                        + value + "' must be a namespaced registry id");
            }
        }
        return out;
    }
}
