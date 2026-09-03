package dev.otectus.mcaconversations.village;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * One thing a village might believe, keep, argue about or give directions by (spec §17.3).
 *
 * <p>A token is authored, not generated. What the runtime chooses is which of the authored tokens a
 * given village ends up with, which is why the interesting fields here are the ones that decide who
 * agrees with it: {@link #endorsedBy} and {@link #questionedBy} name identity tokens, so a village's
 * public value reaches each resident through who that resident already is. A villager who values
 * independence questions a tradition of doing things together; a villager who values tradition
 * endorses it. Neither was written for that villager.
 *
 * <p>A token that appears in both lists is refused at parse, because it would make one villager
 * endorse and question the same thing and the tie would be settled by map ordering.
 *
 * @param id           the token id; its lang key is {@code mcaconversations.culture.<id>}
 * @param family       which of the six slots in a village's culture it can fill
 * @param weight       relative likelihood of being drawn, before integrations are considered
 * @param endorsedBy   identity token ids whose holders are for it
 * @param questionedBy identity token ids whose holders have a reservation about it
 * @param integrations optional mods that must be present for this token to be drawn at all
 */
public record CultureToken(String id,
                           CultureFamily family,
                           int weight,
                           Set<String> endorsedBy,
                           Set<String> questionedBy,
                           Set<String> integrations) {

    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");
    public static final int MAX_WEIGHT = 100;

    public CultureToken {
        id = normalize(id);
        weight = Math.max(1, Math.min(MAX_WEIGHT, weight));
        endorsedBy = Set.copyOf(endorsedBy == null ? Set.of() : endorsedBy);
        questionedBy = Set.copyOf(questionedBy == null ? Set.of() : questionedBy);
        integrations = Set.copyOf(integrations == null ? Set.of() : integrations);
    }

    public boolean isWellFormed() {
        return family != null && ID.matcher(id).matches()
                && java.util.Collections.disjoint(endorsedBy, questionedBy);
    }

    /** The lang key the token speaks through. Content never writes the English itself. */
    public String langKey() {
        return "mcaconversations.culture." + id;
    }

    /**
     * How a villager holding {@code identityTokens} takes this.
     *
     * <p>Endorsement is checked first. Where somebody's identity gives them a reason to be for a
     * thing and a reason to doubt it, being for it is the reading that produces a villager with a
     * position rather than one who equivocates — and the reservation is still available to a scene
     * that wants it, because the token lists it.
     */
    public CultureStance stanceFor(Set<String> identityTokens) {
        if (identityTokens == null || identityTokens.isEmpty()) {
            return CultureStance.IGNORE;
        }
        for (String token : endorsedBy) {
            if (identityTokens.contains(token)) {
                return CultureStance.ENDORSE;
            }
        }
        for (String token : questionedBy) {
            if (identityTokens.contains(token)) {
                return CultureStance.QUESTION;
            }
        }
        return CultureStance.IGNORE;
    }

    public static CultureToken fromJson(String id, JsonObject json) {
        if (json == null) {
            return null;
        }
        CultureFamily family = json.has("family")
                ? CultureFamily.byKey(json.get("family").getAsString()).orElse(null) : null;
        return new CultureToken(id, family,
                json.has("weight") ? json.get("weight").getAsInt() : 10,
                strings(json, "endorsed_by"),
                strings(json, "questioned_by"),
                strings(json, "integrations"));
    }

    private static Set<String> strings(JsonObject json, String field) {
        Set<String> out = new LinkedHashSet<>();
        if (json == null || !json.has(field)) {
            return out;
        }
        JsonElement element = json.get(field);
        if (element.isJsonPrimitive()) {
            out.add(normalize(element.getAsString()));
            return out;
        }
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (JsonElement item : array) {
                if (item.isJsonPrimitive()) {
                    String value = normalize(item.getAsString());
                    if (!value.isEmpty()) {
                        out.add(value);
                    }
                }
            }
        }
        return out;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
