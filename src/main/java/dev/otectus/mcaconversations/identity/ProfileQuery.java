package dev.otectus.mcaconversations.identity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * The parsed form of the {@code conversations_profile} dialogue condition (spec §10.6).
 *
 * <pre>{@code
 * {"conversations_profile": {"family": "value", "any_of": ["duty", "precision"]}}
 * {"conversations_profile": {"family": "work_style", "is": "methodical"}}
 * {"conversations_profile": {"has_former_profession": true}}
 * }</pre>
 *
 * <p>One condition over the closed family vocabulary, rather than one condition per interest — the
 * same rule the context condition follows, and for the same reason: a datapack that wants a new
 * anchor adds a token, not a Java class (spec §10.6).
 *
 * <p><b>Preference, never a rail.</b> An unprofiled villager — identity off, a baby, a catalog that
 * cannot fill a family — reads as a non-match, so a profile condition can only ever <em>add</em> a
 * route. Content must never gate a required subject behind one, which is what
 * {@code IdentityDistributionTest} asserts for the shipped corpus (spec §24.2).
 */
public record ProfileQuery(Optional<IdentityFamily> family,
                           Set<String> anyOf,
                           boolean negate,
                           Optional<Boolean> hasFormerProfession,
                           Optional<Boolean> hasFormativeEvent) {

    /** A query that can never match: unknown family, or JSON that did not parse. */
    public static final ProfileQuery INVALID = new ProfileQuery(
            Optional.empty(), Set.of(), false, Optional.empty(), Optional.empty());

    public ProfileQuery {
        anyOf = Set.copyOf(anyOf);
    }

    public boolean isValid() {
        return family.isPresent() || hasFormerProfession.isPresent() || hasFormativeEvent.isPresent();
    }

    public static ProfileQuery fromJson(JsonObject json) {
        if (json == null) {
            return INVALID;
        }
        Optional<IdentityFamily> family = Optional.empty();
        if (json.has("family")) {
            family = IdentityFamily.byKey(json.get("family").getAsString());
            if (family.isEmpty()) {
                return INVALID;
            }
        }
        Set<String> anyOf = tokens(json, "is");
        anyOf.addAll(tokens(json, "any_of"));
        if (family.isPresent() && anyOf.isEmpty()) {
            // "family with no tokens" would silently match every profiled villager. Refuse it.
            return INVALID;
        }
        return new ProfileQuery(family, anyOf,
                json.has("not") && json.get("not").getAsBoolean(),
                optionalBool(json, "has_former_profession"),
                optionalBool(json, "has_formative_event"));
    }

    /** Scores against a profile. An absent profile is always a non-match, before negation. */
    public boolean matches(VillagerIdentityRecord profile) {
        if (!isValid() || profile == null) {
            return false;
        }
        boolean matched = true;
        if (family.isPresent()) {
            boolean any = false;
            for (String token : anyOf) {
                if (profile.has(family.get(), token)) {
                    any = true;
                    break;
                }
            }
            matched = any;
        }
        if (matched && hasFormerProfession.isPresent()) {
            matched = profile.formerProfession().isPresent() == hasFormerProfession.get();
        }
        if (matched && hasFormativeEvent.isPresent()) {
            matched = profile.formativeEvent().isPresent() == hasFormativeEvent.get();
        }
        return negate != matched;
    }

    private static Optional<Boolean> optionalBool(JsonObject json, String member) {
        return json.has(member) && json.get(member).isJsonPrimitive()
                ? Optional.of(json.get(member).getAsBoolean())
                : Optional.empty();
    }

    private static Set<String> tokens(JsonObject json, String member) {
        Set<String> out = new LinkedHashSet<>();
        if (json == null || !json.has(member)) {
            return out;
        }
        JsonElement element = json.get(member);
        if (element.isJsonPrimitive()) {
            out.add(element.getAsString().trim().toLowerCase(Locale.ROOT));
            return out;
        }
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (JsonElement item : array) {
                out.add(item.getAsString().trim().toLowerCase(Locale.ROOT));
            }
        }
        return out;
    }
}
