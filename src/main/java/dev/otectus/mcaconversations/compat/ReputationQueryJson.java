package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Parses the reputation dialogue conditions' and signal action's JSON (spec §30.2, §30.6), in pure
 * Gson and Java types so it stays on the always-loaded side of {@link ReputationBridge}'s
 * classloading gate.
 *
 * <p>All three conditions are registered <b>unconditionally</b>, with or without MCA: Reputation installed.
 * That is deliberate: dialogue JSON referencing an unregistered key is an unknown-key error, so a
 * datapack written for the full suite would break an MCA-only install. Registered and scoring zero,
 * it simply never matches, and the pack's authored fallback branch fires — which is what §30.2 means
 * by "return 0 so authored disabled-context fallbacks fire".
 *
 * <p>Malformed supplied filters reject the whole query. The registrar wraps these parsers in
 * {@code SafeParse}, so an invalid condition scores zero without abandoning the conversation or
 * silently broadening the query into an unrelated standing or incident match.
 */
public final class ReputationQueryJson {

    private ReputationQueryJson() {
    }

    /**
     * <pre>{@code
     * { "min": 75, "max": 299, "min_tier": "friend", "max_tier": "honored",
     *   "has_title": "mcareputation:village_guardian" }
     * }</pre>
     */
    public static ReputationBridge.StandingQuery standing(JsonObject json) {
        Integer min = optionalInt(json, "min");
        Integer max = optionalInt(json, "max");
        if (min != null && max != null && min > max) {
            throw new IllegalArgumentException("Reputation min exceeds max");
        }
        return new ReputationBridge.StandingQuery(
                min,
                max,
                optionalString(json, "min_tier"),
                optionalString(json, "max_tier"),
                optionalString(json, "has_title"));
    }

    /**
     * <pre>{@code
     * { "types": ["mcareputation:villager_assaulted"], "statuses": ["active", "apologized"],
     *   "tags": ["crime"], "known_to_speaker": true, "max_age": 168000 }
     * }</pre>
     */
    public static ReputationBridge.IncidentQuery incident(JsonObject json) {
        return new ReputationBridge.IncidentQuery(
                stringList(json, "types"),
                lowerList(json, "statuses"),
                lowerList(json, "tags"),
                optionalBoolean(json, "known_to_speaker"),
                nonnegativeLong(json, "max_age"));
    }

    /**
     * The {@code conversations_reputation_profile} condition (§16.2).
     *
     * <pre>{@code
     * { "scope": "speaker",
     *   "recognition": { "min": 15, "max": 900, "min_tier": "recognized" },
     *   "facets": {
     *     "mcareputation:bravery": { "min": 10, "min_evidence": 1 },
     *     "mcareputation:violence": { "max": 0, "allow_unobserved": true }
     *   },
     *   "allow_partial_history": false }
     * }</pre>
     *
     * <p>{@code scope} defaults to {@code speaker}, because the question this condition exists to ask
     * is what <em>this</em> villager knows the player for; {@code community} asks what the village as
     * a whole can say. A speaker-scoped condition never falls back to the community answer — a
     * speaker nobody can resolve is unavailable, and the pack's own fallback branch is what runs.
     *
     * <p>Every clause is ANDed and the defaults are conservative, matching Reputation's own predicate
     * semantics: a facet clause needs at least one live deed behind it ({@code min_evidence} 1), so
     * "not violent" is not satisfied by a village that has never seen the player;
     * {@code allow_unobserved} is the named way to mean "no contrary evidence is known"; and
     * {@code allow_partial_history} is false, so a clause resting on an upper bound or on an absence
     * refuses on a save that cannot prove its history is complete.
     */
    public static ReputationBridge.ProfileQuerySpec profile(JsonObject json) {
        if (json == null) {
            throw new IllegalArgumentException("a profile condition needs an object");
        }
        ReputationBridge.ProfileQuerySpec.Scope scope = ReputationBridge.ProfileQuerySpec.Scope.SPEAKER;
        if (json.has("scope")) {
            String raw = text(json.get("scope"), "scope");
            scope = ReputationBridge.ProfileQuerySpec.Scope.byKey(raw).orElseThrow(() ->
                    new IllegalArgumentException("scope '" + raw + "' is neither community nor speaker"));
        }

        Integer min = null;
        Integer max = null;
        String minTier = null;
        if (json.has("recognition")) {
            JsonObject recognition = object(json.get("recognition"), "recognition");
            min = optionalInt(recognition, "min");
            max = optionalInt(recognition, "max");
            minTier = optionalString(recognition, "min_tier");
            if (min != null && min < 0) {
                throw new IllegalArgumentException("recognition min must not be negative");
            }
            if (max != null && max < 0) {
                throw new IllegalArgumentException("recognition max must not be negative");
            }
            if (min != null && max != null && min > max) {
                throw new IllegalArgumentException("recognition min exceeds max");
            }
        }

        List<ReputationBridge.ProfileQuerySpec.FacetSpec> facets = new ArrayList<>();
        if (json.has("facets")) {
            JsonObject authored = object(json.get("facets"), "facets");
            if (authored.size() > ReputationBridge.ProfileQuerySpec.MAX_FACETS) {
                throw new IllegalArgumentException("a profile condition may name at most "
                        + ReputationBridge.ProfileQuerySpec.MAX_FACETS + " facets");
            }
            for (String facet : authored.keySet()) {
                facets.add(facetSpec(facet, authored.get(facet)));
            }
        }

        boolean allowPartialHistory = optionalBoolean(json, "allow_partial_history");
        return new ReputationBridge.ProfileQuerySpec(scope, min, max, minTier,
                List.copyOf(facets), allowPartialHistory);
    }

    /** One facet clause. A blank id, an inverted range or a silly evidence count fails the query. */
    private static ReputationBridge.ProfileQuerySpec.FacetSpec facetSpec(String facet,
                                                                         JsonElement element) {
        String id = facet == null ? "" : facet.trim();
        if (id.isEmpty() || id.indexOf(':') <= 0 || id.endsWith(":")) {
            throw new IllegalArgumentException("facet '" + facet + "' is not a namespaced id");
        }
        JsonObject clause = object(element, "facet " + id);
        Integer min = optionalInt(clause, "min");
        Integer max = optionalInt(clause, "max");
        if (min != null && max != null && min > max) {
            throw new IllegalArgumentException("facet " + id + " min exceeds max");
        }
        if (min == null && max == null) {
            throw new IllegalArgumentException("facet " + id + " states neither min nor max");
        }
        int minEvidence = 1;
        if (clause.has("min_evidence")) {
            Integer authored = optionalInt(clause, "min_evidence");
            if (authored == null || authored < 0
                    || authored > ReputationBridge.ProfileQuerySpec.MAX_MIN_EVIDENCE) {
                throw new IllegalArgumentException("facet " + id + " min_evidence must be 0.."
                        + ReputationBridge.ProfileQuerySpec.MAX_MIN_EVIDENCE);
            }
            minEvidence = authored;
        }
        boolean allowUnobserved = optionalBoolean(clause, "allow_unobserved");
        return new ReputationBridge.ProfileQuerySpec.FacetSpec(id.toLowerCase(Locale.ROOT), min, max,
                minEvidence, allowUnobserved);
    }

    /**
     * The {@code conversations_reputation_signal} action (§30.6, §16.2).
     *
     * <pre>{@code
     * { "incident": "mcareputation:public_apology", "visibility": "witnessed",
     *   "decision": "standing.amends.public_apology",
     *   "binds": "known_incident", "bind_types": ["mcareputation:villager_assaulted"],
     *   "bind_max_age": 168000,
     *   "supersedes": "standing.amends.grudging_apology", "supersede_window": 168000 }
     * }</pre>
     *
     * <p>Parsed here rather than inline at the registration site so the identity rules have a unit
     * test: {@code binds} is what stops one apology being paid for twice while leaving a second,
     * unrelated incident independently addressable, and {@code supersedes} is what stops a fuller
     * apology stacking on the partial one that preceded it.
     */
    public static ReputationBridge.SignalRequest signal(JsonObject json) {
        if (json == null) {
            throw new IllegalArgumentException("a reputation signal needs an object");
        }
        String incident = text(json.get("incident"), "incident");
        String decision = json.has("decision") ? text(json.get("decision"), "decision") : incident;
        String visibility = optionalString(json, "visibility");

        boolean binds = false;
        if (json.has("binds")) {
            String raw = text(json.get("binds"), "binds");
            binds = switch (raw.toLowerCase(Locale.ROOT)) {
                case "known_incident" -> true;
                case "none" -> false;
                default -> throw new IllegalArgumentException("binds '" + raw
                        + "' is neither known_incident nor none");
            };
        }
        List<String> bindTypes = stringList(json, "bind_types");
        if (!binds && !bindTypes.isEmpty()) {
            throw new IllegalArgumentException("bind_types names types for a signal that binds nothing");
        }
        long bindMaxAge = nonnegativeLong(json, "bind_max_age");
        String supersedes = optionalString(json, "supersedes");
        long supersedeWindow = nonnegativeLong(json, "supersede_window");
        return new ReputationBridge.SignalRequest(incident, visibility, decision, binds,
                bindTypes.stream().map(type -> type.toLowerCase(Locale.ROOT)).toList(), bindMaxAge,
                supersedes, supersedeWindow);
    }

    private static JsonObject object(JsonElement element, String key) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException(key + " must be an object");
        }
        return element.getAsJsonObject();
    }

    private static Integer optionalInt(JsonObject json, String key) {
        return json.has(key) ? scalar(json.get(key), key).getAsBigDecimal().intValueExact() : null;
    }

    private static long nonnegativeLong(JsonObject json, String key) {
        if (!json.has(key)) {
            return 0L;
        }
        long value = scalar(json.get(key), key).getAsBigDecimal().longValueExact();
        if (value < 0L) {
            throw new IllegalArgumentException(key + " must not be negative");
        }
        return value;
    }

    private static boolean optionalBoolean(JsonObject json, String key) {
        if (!json.has(key)) {
            return false;
        }
        var value = scalar(json.get(key), key);
        if (!value.isBoolean()) {
            throw new IllegalArgumentException(key + " must be a boolean");
        }
        return value.getAsBoolean();
    }

    private static com.google.gson.JsonPrimitive scalar(JsonElement element, String key) {
        if (element == null || !element.isJsonPrimitive()) {
            throw new IllegalArgumentException(key + " must be a scalar");
        }
        return element.getAsJsonPrimitive();
    }

    private static String text(JsonElement element, String key) {
        var value = scalar(element, key);
        if (!value.isString() || value.getAsString().isBlank()) {
            throw new IllegalArgumentException(key + " must be a nonblank string");
        }
        return value.getAsString().trim();
    }

    private static String optionalString(JsonObject json, String key) {
        return json.has(key) ? text(json.get(key), key) : null;
    }

    private static List<String> stringList(JsonObject json, String key) {
        if (!json.has(key)) {
            return List.of();
        }
        List<String> out = new ArrayList<>();
        JsonElement element = json.get(key);
        if (element.isJsonArray()) {
            for (JsonElement entry : element.getAsJsonArray()) {
                out.add(text(entry, key));
            }
        } else {
            // Keep the documented shorthand, but never discard an invalid list member.
            out.add(text(element, key));
        }
        return List.copyOf(out);
    }

    private static List<String> lowerList(JsonObject json, String key) {
        return stringList(json, key).stream().map(value -> value.toLowerCase(Locale.ROOT)).toList();
    }
}
